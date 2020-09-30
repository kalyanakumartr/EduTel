package com.hbs.edutel.common.action.login.factory;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hbs.edutel.common.action.login.ChangePasswordAction;
import com.hbs.edutel.common.action.login.ForgotPasswordAction;
import com.hbs.edutel.common.action.login.LoginAction;
import com.hbs.edutel.common.action.login.ReminderQABean;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.UserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class DBAuthentication implements LoginFactoryInterface, ConstInterface
{
	private static final long		serialVersionUID	= 1021449188953682581L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(DBAuthentication.class);

	public String getPasswordRetriveSecurityQuestion(ForgotPasswordAction forgotVo) throws CustomException
	{
		IUsers user = forgotVo.getUserBo().getUser(forgotVo.getUserId());
		ReminderQABean remindQA = null;
		if (user != null)
			remindQA = forgotVo.getUserBo().userSecurityQuestion(user.getUsEmployeeId());

		if (remindQA != null)
		{
			forgotVo.setIsQuesEmpty("two");
			forgotVo.setQuestion(remindQA.getSecQuestion().replace("-", " "));
			forgotVo.setAnswer(remindQA.getSecAnswer());
		}
		else
		{
			forgotVo.setIsQuesEmpty("one");
			forgotVo.addActionError("Password reminder question not avail. Please set new question.");
		}
		return EPage.Success.name();
	}

	
	public String initiateAuthentication(LoginAction loginVo) throws CustomException
	{
		loginVo.setUser(loginVo.getUserBo().getUser(loginVo.getUserId(), loginVo.getPassWord()));
		return EPage.Success.name();
	}

	
	public String initiateDeAuthentication(LoginAction loginVo) throws CustomException
	{
		return EPage.Success.name();
	}

	
	public String resetUserPassword(ForgotPasswordAction forgotVo) throws CustomException
	{
		return EPage.Success.name();
	}

	
	public String saveReminderQuestions(LoginAction loginVo) throws CustomException
	{
		IUsers user = null;
		String userId = "";
		if (StringUtils.isBlank(loginVo.getQuesAnswer()))
		{
			loginVo.addActionError(" Enter the Answer.");
			return EPage.Input.name();
		}
		else
		{
			try
			{

				user = loginVo.getUserBo().getUser(loginVo.getUserId());

				if (user != null)
				{
					userId = user.getUsEmployeeId();

					loginVo.getUserBo().userLogAtLogin(user, loginVo.getRequest().getRemoteAddr());
					loginVo.getSession().setAttribute(ESession.UserObject.getAttribute(), user);
					loginVo.getSession().setAttribute(ESession.OriginalUserId.getAttribute(), user.getUsEmployeeId());
					loginVo.getSession().setAttribute(ESession.OriginalEmployeeId.getAttribute(), user.getUsEmployeeId() + "");
					loginVo.getSession().setAttribute(ESession.OriginalUserName.getAttribute(), user.getUsUserName());
					loginVo.getSession().setAttribute(ESession.UserPassword.getAttribute(), loginVo.getPassWord());

					// Added for storing Reminder getQuestion() & getAnswer() in local DB
					IUserReminderQuery urq = new UserReminderQuery();
					urq.setUsReminderQueryQuestion(loginVo.getRemQuestion());
					urq.setUsReminderQueryAnswer(loginVo.getQuesAnswer());
					urq.setUsModifiedBy(user.getUsEmployeeId());
					urq.setUsModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
					urq.setUsers(user);

					loginVo.getUserBo().updateUserReminderQuery(urq);
					caLogger.info(Audit_Logging_Event_Login, " Save Reminder Query : User Id : " + userId + " - ", loginVo.getRemQuestion() + " - " + loginVo.getQuesAnswer(), EPage.Success.name(),
							user.getUsEmployeeId());

					loginVo.getRequest().setAttribute("actionFrm", "login");
					loginVo.addActionMessage("Please change your password!");
					return EPage.ChangePassword.name();
				}
			}
			catch (Exception excep)
			{
				caLogger.error(Audit_Logging_Event_Login, " saveReminderQuestions", excep.getMessage(), EPage.Failure.name(), null);
				loginVo.addActionError(excep.getMessage());
			}
		}

		return EPage.Input.name();
	}

	
	public String updateUserPassword(ChangePasswordAction changeVo) throws CustomException
	{
		IUsers user = changeVo.getUserBo().getUser(changeVo.getUser().getUsUserID(), changeVo.getOldPassword());
		if (user != null && changeVo.getUserBo().changeUserPassword(user.getUsEmployeeId(), user.getUsUserID(), changeVo.getNewPassword()))
			return EPage.Success.name();
		throw new CustomException("You have entered wrong old password!");
	}

}
