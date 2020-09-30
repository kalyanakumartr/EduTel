package com.hbs.edutel.common.action.login.factory;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.ChangePasswordAction;
import com.hbs.edutel.common.action.login.ForgotPasswordAction;
import com.hbs.edutel.common.action.login.LoginAction;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class LDAPAuthentication implements LoginFactoryInterface, ConstInterface
{
	private static final String		COM_SUN_JNDI_LDAP_CONNECT_TIMEOUT	= "com.sun.jndi.ldap.connect.timeout";
	private static final String		COM_SUN_JNDI_LDAP_LDAP_CTX_FACTORY	= "com.sun.jndi.ldap.LdapCtxFactory";
	private static final long		serialVersionUID					= 7238892684778544845L;
	private final CustomAuditLogger	caLogger							= new CustomAuditLogger(this.getClass());

	
	public String getPasswordRetriveSecurityQuestion(ForgotPasswordAction forgotVo) throws CustomException
	{
		forgotVo.addActionError("You can't retrieve NT password here!");
		throw new CustomException("You can't retrieve NT password here!");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public String initiateAuthentication(LoginAction loginVo) throws CustomException
	{
		Properties authEnv = new Properties();

		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, COM_SUN_JNDI_LDAP_LDAP_CTX_FACTORY);
		authEnv.put(Context.PROVIDER_URL, LDAPURL);
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, LDAPPrincipal);
		authEnv.put(Context.SECURITY_CREDENTIALS, LDAPCredentials);
		authEnv.put(Context.REFERRAL, "ignore");
		authEnv.put(COM_SUN_JNDI_LDAP_CONNECT_TIMEOUT, LDAPTimeout);
		DirContext authContext = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(loginVo.getUserId()))
			{
				String filter = "";
				authContext = new InitialDirContext(authEnv);

				if (loginVo.getUserId().indexOf("@") > 0)
					filter = "(mail=" + loginVo.getUserId() + ")";
				else
					filter = "(sAMAccountName=" + loginVo.getUserId() + ")";

				String[] attrIDs = { "sn" };
				SearchControls ctls = new SearchControls();
				ctls.setReturningAttributes(attrIDs);
				ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
				NamingEnumeration answer = authContext.search(LDAPSearch, filter, ctls);
				while ( answer.hasMore() )
				{
					SearchResult sr = (SearchResult) answer.next();
					String dn = sr.getName();
					Hashtable env = new Hashtable();
					env.put(Context.INITIAL_CONTEXT_FACTORY, COM_SUN_JNDI_LDAP_LDAP_CTX_FACTORY);
					env.put(Context.PROVIDER_URL, LDAPURL);
					env.put(Context.SECURITY_AUTHENTICATION, "simple");
					env.put(Context.SECURITY_PRINCIPAL, dn + "," + LDAPSearch);
					env.put(Context.SECURITY_CREDENTIALS, loginVo.getPassWord());
					env.put(COM_SUN_JNDI_LDAP_CONNECT_TIMEOUT, LDAPTimeout);
					DirContext auth = null;
					try
					{
						auth = new InitialDirContext(env);
						auth.close();
						loginVo.setUser(loginVo.getUserBo().getUserByLoginColumn(loginVo.getUserId()));
						return EPage.Success.name();
					}
					catch (AuthenticationException authEx)
					{
						if (auth != null)
							auth.close();
						caLogger.error(Audit_Logging_Event_DAOImpl, "initiateAuthentication", authEx.getMessage(), this.getClass().getName(), null);
						throw new CustomException("Login Id or Password is Incorrect");
					}
					catch (NamingException namEx)
					{
						if (auth != null)
							auth.close();
						caLogger.error(Audit_Logging_Event_DAOImpl, "initiateAuthentication", namEx.getMessage(), this.getClass().getName(), null);
						throw new CustomException("Login Id or Password is Incorrect");
					}
				}
			}
			return EPage.Failure.name();
		}
		catch (AuthenticationException authEx)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, "initiateAuthentication", authEx.getMessage(), this.getClass().getName(), null);
			throw new CustomException("Login Id or Password is Incorrect");
		}
		catch (NamingException namEx)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, "initiateAuthentication", namEx.getMessage(), this.getClass().getName(), null);
			throw new CustomException("Login Id or Password is Incorrect");
		}
		finally
		{
			try
			{
				if (authContext != null)
					authContext.close();
			}
			catch (Exception excep)
			{

			}
		}
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
		loginVo.addActionError("System uses NT Password, Contact System/Network Administrator!");
		throw new CustomException("System uses NT Password, Contact System/Network Administrator!");
	}

	
	public String updateUserPassword(ChangePasswordAction changeVo) throws CustomException
	{
		changeVo.addActionError("System uses NT Password, You cannot change NT password here!");
		throw new CustomException("System uses NT Password, You cannot change NT password here!");
	}
}
