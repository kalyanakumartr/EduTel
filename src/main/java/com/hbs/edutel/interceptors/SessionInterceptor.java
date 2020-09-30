package com.hbs.edutel.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.apache.struts2.StrutsStatics;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class SessionInterceptor extends AbstractInterceptor implements StrutsStatics
{

	private void addActionError(ActionInvocation invocation, String message)
	{
		Object action = invocation.getAction();
		if (action instanceof ValidationAware)
		{
			((ValidationAware) action).addActionError(message);
		}
	}

	
	public void destroy()
	{
		MDC.remove("SessionId");
		System.out.println("Destroying SessionInterceptor...");
	}

	
	public void init()
	{
		System.out.println("Initializing SessionInterceptor...");
	}

	
	public String intercept(ActionInvocation invocation) throws Exception
	{
		final ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
		HttpSession session = request.getSession(true);

		if (session.getAttribute(ESession.UserObject.getAttribute()) != null 
				|| request.getRequestURI().contains("index.do") 
				|| request.getRequestURI().contains("loadPage.do") 
				|| request.getRequestURI().contains("studentEnquiry.do")
				|| request.getRequestURI().contains("studentEnquiryHome.do")
				|| request.getRequestURI().contains("studentEdutelEnquiryList.do") 
				|| request.getRequestURI().contains("serialKeyValidate.do") 
				|| request.getRequestURI().contains("serialKeyValidateAndActivate.do") 
				|| request.getRequestURI().contains("loginAuthentication.do")
				|| request.getRequestURI().contains("loginPage.do")
				|| request.getRequestURI().contains("logoutPage.do")
				
				|| request.getRequestURI().contains("userPreRegistration.do")
				|| request.getRequestURI().contains("userRegistration.do"))
		{
			try
			{
				MDC.put("SessionId", session.getId());
				return invocation.invoke();
			}
			catch (Exception ex)
			{
				MDC.remove("SessionId");
				ex.printStackTrace();
			}
			return EPage.SessionExpired.name();
		}
		else
		{
			String loginAttempt = request.getParameter("LOGIN_ATTEMPT");
			String loginOption = request.getParameter("loginOption");

			if (StringUtils.isBlank(loginAttempt) == false || CommonValidator.isEqual(EPage.Student.name(), loginOption) || CommonValidator.isEqual(EPage.Employee.name(), loginOption)
					|| CommonValidator.isEqual(EPage.Franchisee.name(), loginOption))

			{
				MDC.put("SessionId", session.getId());
				return invocation.invoke();
			}
			else
			{
				MDC.remove("SessionId");
				addActionError(invocation, "Your session expired. Relogin again!");
				request.setAttribute("login", "sessionExpired");
				return EPage.SessionExpired.name();
			}
		}
	}

}
