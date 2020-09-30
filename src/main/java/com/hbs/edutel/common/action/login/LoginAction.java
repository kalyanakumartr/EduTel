package com.hbs.edutel.common.action.login;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.factory.LoginFactory;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.menucomponent.action.MaMenuData;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

public class LoginAction extends LoginActionData implements LoginActionInterface
{
	private static final long		serialVersionUID	= -6266271056186876445L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(LoginAction.class);

	private void displayMenuByRoles()
	{
		if (maMenuHTML == null)
		{
			session = request.getSession();
			IUsers user = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			if (user != null)
			{
				MaMenuData menuData = new MaMenuData();
				menuData.setRolesSet(user.getUserRoleses());
				maMenuHTML = menuBo.getMenusByRoleHTML(menuData);

				if (CommonValidator.isNotNullNotEmpty(maMenuHTML))
				{
					maMenuHTML = maMenuHTML.replaceAll("#user.usUsersType", user.getUsUsersType());
				}
				if (session.getAttribute(ESession.MaMenuHTML.getAttribute()) == null)
					session.setAttribute(ESession.MaMenuHTML.getAttribute(), maMenuHTML);

			}
		}
	}

	public String loginAuthentication()
	{
		String result = EPage.Success.name();
		try
		{
			clearErrors();
			clearErrorsAndMessages();
			result = new LoginFactory().authenticate(this);

			Cookie lastLoginID = new Cookie("lastLoginId", userId);

			lastLoginID.setMaxAge(7 * 24 * 60 * 60); // Set maximum time of one
														// week
			lastLoginID.setPath(SLASH);
			response.addCookie(lastLoginID);
			request.getSession().setAttribute(ESession.UserObject.getAttribute(), user);
			request.getSession().setAttribute(ESession.OriginalUserId.getAttribute(), user.getUsEmployeeId());
			request.getSession().setAttribute(ESession.OriginalEmployeeId.getAttribute(), String.valueOf(user.getUsEmployeeId()));
			request.getSession().setAttribute(ESession.OriginalUserName.getAttribute(), CommonUtil.initCapsName(user.getUsUserName()));
			request.getSession().setAttribute(ESession.UserPassword.getAttribute(), getPassWord());
			if (CommonValidator.isEqual(result, EPage.Success.name()))
			{
				maMenuHTML = null;
				request.getSession().removeAttribute(ESession.MaMenuHTML.getAttribute());

				displayMenuByRoles();
				result = loginOption;
			}
			caLogger.info(Audit_Logging_Event_Login, "Login Authentication for User : " + user.getUsUserName(), request.getHeader("User-Agent"), result, user.getUsEmployeeId());
		}
		catch (CustomException custExcep)
		{
			result = EPage.Failure.name();
			clearErrors();
			clearErrorsAndMessages();
			addActionError(custExcep.getLogMessage());
			caLogger.error(Audit_Logging_Event_Login, custExcep.getLogMessage(), null, result, getUserId());
		}

		// caLogger.info(Audit_Logging_Event_Login, "Login Refresh Action", request.getRemoteAddr(),
		// request.getRequestedSessionId(), getUserId());
		return result;
	}

	public String logoutAction()
	{
		try
		{
			String result = new LoginFactory().deAuthenticate(this);
			caLogger.info(Audit_Logging_Event_Logout, "Logout action for User", result, null, user.getUsEmployeeId());
		}
		catch (CustomException custExcep)
		{
			caLogger.error(Audit_Logging_Event_Logout, custExcep.getLogMessage(), EPage.Failure.name(), null, user.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	/**
	 * Save user given reminder question and answer at portal.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveReminderQuestion()
	{
		try
		{
			clearErrorsAndMessages();
			return new LoginFactory().saveReminderQuestion(this);
		}
		catch (CustomException custExcep)
		{
			caLogger.error(Audit_Logging_Event_DataEntry, custExcep.getLogMessage(), EPage.Failure.name(), null, user.getUsEmployeeId());
		}
		return EPage.Input.name();
	}

	
	public void validate()
	{
		clearErrorsAndMessages();
		if (StringUtils.isBlank(getUserId()))
		{
			addActionError(" Please enter Login Id. ");
		}
		else if (StringUtils.isBlank(getPassWord()))
		{
			addActionError(" Please enter Password. ");
		}
		super.validate();
	}
}
