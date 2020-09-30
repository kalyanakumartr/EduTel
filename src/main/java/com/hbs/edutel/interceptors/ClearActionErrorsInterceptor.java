package com.hbs.edutel.interceptors;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ClearActionErrorsInterceptor extends AbstractInterceptor implements StrutsStatics
{
	private static final long	serialVersionUID	= -6426706726258567813L;

	private void clearActionError(ActionInvocation invocation)
	{
		Object action = invocation.getAction();
		if (action instanceof ValidationAware)
		{
			((ValidationAware) action).setActionErrors(null);
			((ValidationAware) action).setFieldErrors(null);
			((ValidationAware) action).setActionMessages(null);
		}
	}

	
	public String intercept(ActionInvocation invocation) throws Exception
	{
		clearActionError(invocation);
		return invocation.invoke();
	}
}
