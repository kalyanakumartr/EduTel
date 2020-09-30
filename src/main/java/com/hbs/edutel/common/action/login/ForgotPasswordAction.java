package com.hbs.edutel.common.action.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hbs.edutel.common.action.login.factory.DBAuthentication;
import com.hbs.edutel.common.action.login.factory.LDAPAuthentication;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.PwdGenerator;
import com.hbs.edutel.util.common.ConstEnumUtil.EAuthType;
import com.hbs.edutel.util.common.ConstEnumUtil.EDR;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;

public class ForgotPasswordAction extends ForgotPasswordActionData
{
	private static final long		serialVersionUID	= 3714586316856228154L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(ForgotPasswordAction.class);

	public String getSecurityQuestion()
	{
		String result = EPage.Failure.name();
		try
		{
			clearErrorsAndMessages();
			boolean isDREnabled = propFactory.getProperty(EDR.DR_Site).equals(YES);
			if (isDREnabled)
			{
				result = new DBAuthentication().getPasswordRetriveSecurityQuestion(this);
			}
			else
			{
				String authType = propFactory.getProperty(EGeneral.Authentication_Type);

				switch ( EAuthType.valueOf(authType) )
				{
					case DB :
						result = new DBAuthentication().getPasswordRetriveSecurityQuestion(this);
						break;
					case LDAP :
						result = new LDAPAuthentication().getPasswordRetriveSecurityQuestion(this);
						break;
				}
			}
		}
		catch (CustomException custEx)
		{
			caLogger.error(Audit_Logging_Event_Session, "getSecurityQuestion", custEx.getLogMessage(), ForgotPasswordAction.class.getName(), userId);
		}
		return result;
	}

	public String resetUserPassword()
	{
		try
		{
			clearErrorsAndMessages();
			if (StringUtils.isBlank(quesAnswer))
			{
				addActionError("You have entered an Invalid answer.");
				isQuesEmpty = "two";
			}
			else if (quesAnswer.equals(answer))
			{
				String authType = propFactory.getProperty(EGeneral.Authentication_Type);
				user = userBo.getUserByUserId(userId);
				String usUserPwd = PwdGenerator.getPassword();
				user.setUsUserPwd(usUserPwd);
				switch ( EAuthType.valueOf(authType) )
				{
					case DB :
						new DBAuthentication().resetUserPassword(this);
						break;
					case LDAP :
						new LDAPAuthentication().resetUserPassword(this);
						break;
				}

				if (user != null)
				{
					userBo.updateUserPasswordAndSendEmail(user.getUsEmployeeId(), userId, usUserPwd, user.getUsModifiedBy(), user.getUsModifiedDate());
					caLogger.info(Audit_Logging_Event_Session, "resetUserPassword", "User Password Resetted Successfully", userId, user.getUsEmployeeId());
				}

				List<String> actionMsgs = new ArrayList<String>();
				actionMsgs.add("Your request processed Successfully.");
				setActionMessages(actionMsgs);
			}
			else
			{
				setIsQuesEmpty("two");
				addActionError("You have entered invalid Answer. Please try again");
			}
		}
		catch (CustomException custEx)
		{
			caLogger.error(Audit_Logging_Event_Session, "resetUserPassword", custEx.getLogMessage(), ForgotPasswordAction.class.getName(), user.getUsEmployeeId());
			addActionError("You have entered invalid Answer. Please try again");
			// Success and Failure Scenario resulted to same page purposefully.
		}
		return EPage.Success.name();
	}

	
	public void validate()
	{
		clearErrorsAndMessages();
		if (StringUtils.isBlank(userId))
		{
			addActionError("You have entered invalid data!");
			setIsQuesEmpty("one");
		}
	}
}
