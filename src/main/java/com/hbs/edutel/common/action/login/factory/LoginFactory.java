package com.hbs.edutel.common.action.login.factory;

import com.hbs.edutel.common.action.login.LoginAction;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.common.ConstEnumUtil.EAuthType;
import com.hbs.edutel.util.common.ConstEnumUtil.EDR;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.image.factory.ImageDownloadFactory;

public class LoginFactory implements ConstInterface
{
	private static final long	serialVersionUID	= 9000746948049684702L;

	public String authenticate(LoginAction loginVo) throws CustomException
	{
		String result = EPage.Failure.name();
		boolean isDREnabled = loginVo.getPropFactory().getProperty(EDR.DR_Site).equals(YES);
		if (isDREnabled)
		{
			result = new DBAuthentication().initiateAuthentication(loginVo);
		}
		else
		{
			String authType = loginVo.getPropFactory().getProperty(EGeneral.Authentication_Type);

			switch ( EAuthType.valueOf(authType) )
			{
				case DB :
					result = new DBAuthentication().initiateAuthentication(loginVo);
					break;
				case LDAP :
					result = new LDAPAuthentication().initiateAuthentication(loginVo);
					break;
				default :
					result = new DBAuthentication().initiateAuthentication(loginVo);
					break;
			}
		}
		if (result.equals(EPage.Success.name()))
		{
			result = new LoginAttrFactory().assignLoginUserAttributes(loginVo);
		}
		else if (result.equals(EPage.RemindQuestion.name()))
		{
			result = new LoginAttrFactory().assignLoginUserAttributes(loginVo);
			if (result.equals(EPage.Success.name()))
			{
				result = EPage.RemindQuestion.name();
			}
		}
		else
		{
			throw new CustomException("Login Id or Password is Incorrect");
		}
		if (loginVo.getUser().isStudent())
			loginVo.setLoginOption(EPage.Student.name());
		else if (loginVo.getUser().isEmployee())
			loginVo.setLoginOption(EPage.Employee.name());
		else if (loginVo.getUser().isFranchisee())
			loginVo.setLoginOption(EPage.Franchisee.name());

		return result;
	}

	public String deAuthenticate(LoginAction loginVo) throws CustomException
	{
		String result = EPage.Success.name();

		try
		{
			// Unlock Invoice on LogOut by User based
			IUsers user = (IUsers) loginVo.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());
			if (user != null)
			{
				loginVo.getUserBo().userLogAtLogOut(user);
				loginVo.getRequest().getSession(false).removeAttribute(ESession.UserObject.getAttribute());
				loginVo.getRequest().getSession(false).removeAttribute(ESession.OriginalUserId.getAttribute());
				loginVo.getRequest().getSession(false).removeAttribute(ESession.OriginalUserName.getAttribute());
				loginVo.getRequest().getSession(false).removeAttribute(ESession.UserPassword.getAttribute());
				// Remove alias value if any
				loginVo.getRequest().getSession(false).removeAttribute(ESession.DelegationAlias.getAttribute());
				loginVo.getRequest().getSession(false).removeAttribute(ESession.SelectDelUserId.getAttribute());
				loginVo.getRequest().getSession(false).removeAttribute(ESession.SelectDelUserName.getAttribute());
				ImageDownloadFactory.getInstance().getImageDownloadInstance()
						.deleteFiles(loginVo.getRequest().getSession().getServletContext().getRealPath("/content") + "/" + loginVo.getRequest().getSession().getId());
				loginVo.getRequest().getSession(false).invalidate();

				String authType = loginVo.getPropFactory().getProperty(EGeneral.Authentication_Type);
				switch ( EAuthType.valueOf(authType) )
				{
					case DB :
						result = new DBAuthentication().initiateDeAuthentication(loginVo);
						break;
					case LDAP :
						result = new LDAPAuthentication().initiateDeAuthentication(loginVo);
						break;
				}
			}
		}
		catch (Exception e)
		{
			throw new CustomException(e.getMessage());
		}

		return result;
	}

	public String saveReminderQuestion(LoginAction loginVo) throws CustomException
	{
		String result = EPage.Input.name();
		boolean isDREnabled = loginVo.getPropFactory().getProperty(EDR.DR_Site).equals(YES);
		if (isDREnabled)
		{
			result = new DBAuthentication().saveReminderQuestions(loginVo);
		}
		else
		{
			String authType = loginVo.getPropFactory().getProperty(EGeneral.Authentication_Type);

			switch ( EAuthType.valueOf(authType) )
			{
				case DB :
					result = new DBAuthentication().saveReminderQuestions(loginVo);
					break;
				case LDAP :
					result = new LDAPAuthentication().saveReminderQuestions(loginVo);
					break;
				default :
					result = new DBAuthentication().saveReminderQuestions(loginVo);
					break;
			}
		}
		return result;
	}
}
