package com.hbs.edutel.common.action.login;

import java.util.Date;

import com.hbs.edutel.common.action.login.factory.DBAuthentication;
import com.hbs.edutel.common.action.login.factory.LDAPAuthentication;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.UserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.common.ConstEnumUtil.EAuthType;
import com.hbs.edutel.util.common.ConstEnumUtil.EDR;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

public class ChangePasswordAction extends ChangePasswordActionData
{
	private static final long		serialVersionUID	= 4343336929734017367L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(ChangePasswordAction.class);

	public String updateUserPassword()
	{
		try
		{
			clearErrorsAndMessages();
			if (request.getSession().getAttribute(ESession.UserObject.getAttribute()) != null)
			{
				user = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

				boolean isDREnabled = propFactory.getProperty(EDR.DR_Site).equals(YES);
				if (isDREnabled)
				{
					new DBAuthentication().updateUserPassword(this);
				}
				else
				{
					String authType = propFactory.getProperty(EGeneral.Authentication_Type);

					switch ( EAuthType.valueOf(authType) )
					{
						case DB :
							new DBAuthentication().updateUserPassword(this);
							break;
						case LDAP :
							new LDAPAuthentication().updateUserPassword(this);
							break;
					}

					addActionMessage("Password updated Successfully, Re-login again.!");

					// Added for storing Reminder getQuestion() & getAnswer() in local DB
					IUserReminderQuery urq = new UserReminderQuery();
					urq.setUsReminderQueryQuestion(question);
					urq.setUsReminderQueryAnswer(answer);
					urq.setUsModifiedBy(user.getUsEmployeeId());
					urq.setUsModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
					urq.setUsers(user);
					if (userBo.updateUserReminderQuery(urq))
						caLogger.info(Audit_Logging_Event_Session, "User Password Updated Successfully", user.getUsUserID(), null, user.getUsEmployeeId());
					else
						caLogger.info(Audit_Logging_Event_Session, "User Password Updated Failed", user.getUsUserID(), null, user.getUsEmployeeId());

					return EPage.Success.name();
				}
			}
			else
				throw new CustomException("Your Session has been expired!\n\nPlease login to change your password.");
		}
		catch (CustomException custEx)
		{
			addActionMessage(custEx.getLogMessage());
			caLogger.error(Audit_Logging_Event_Session, "updateUserPassword", custEx.getLogMessage(), user.getUsUserID(), user.getUsEmployeeId());
		}
		return EPage.Failure.name();
	}

	
	public void validate()
	{
		clearErrorsAndMessages();
		if (oldPassword.equals(newPassword))
		{
			addActionError(" New password should not be same as old password.");
		}
		else if (!newPassword.equals(getConfirmPassword()))
		{
			addActionError(" New password and Confirm password should be equal.");
		}
		super.validate();
	}
}
