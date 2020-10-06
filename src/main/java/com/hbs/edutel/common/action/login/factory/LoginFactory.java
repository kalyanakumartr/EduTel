package com.hbs.edutel.common.action.login.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import com.hbs.edutel.util.common.property.factory.PropFactory;

public class LoginFactory implements ConstInterface
{
	private static final String	CORE_OAUTH_URL	= PropFactory.getInstance().getProperty(EGeneral.OAuthURL);

	private static final long	serialVersionUID			= 9000746948049684702L;

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

			loginVo.getRequest().getSession().setAttribute(OAUTH_TOKEN, getExternalOAuthToken(loginVo));
		}
		else if (result.equals(EPage.RemindQuestion.name()))
		{
			result = new LoginAttrFactory().assignLoginUserAttributes(loginVo);
			loginVo.getRequest().getSession().setAttribute(OAUTH_TOKEN, getExternalOAuthToken(loginVo));

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
				loginVo.getRequest().getSession(false).removeAttribute(OAUTH_TOKEN);
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

	public String getExternalOAuthToken(LoginAction loginVo)
	{

		try
		{
			HttpPost post = new HttpPost(CORE_OAUTH_URL + "/oauth/token");
			post.addHeader("Authorization", "Basic SEJTQVBQTElDQVRJT046S2FsYW1AMTUxMDMx");
			post.addHeader("Content-Type", "application/x-www-form-urlencoded");

			// add request parameters or form parameters
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("username", loginVo.getUser().getUsEmail()));
			urlParameters.add(new BasicNameValuePair("password", "Test@1234")); // We are hard code
																				// to avoid password
																				// change work
			urlParameters.add(new BasicNameValuePair("grant_type", "password"));

			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse response = httpClient.execute(post);

			JsonObject jsonObject = new JsonParser().parse(EntityUtils.toString(response.getEntity())).getAsJsonObject();

			return jsonObject.get("access_token").getAsString();

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return null;
	}
}
