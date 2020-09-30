package com.hbs.edutel.hibernate3.support;

public class TenantIdHolder
{
	private static final ThreadLocal<String>	contextHolder	= new ThreadLocal<String>();

	public static void clearTenantId()
	{
		contextHolder.remove();
	}

	public static String getTenantId()
	{
		return contextHolder.get();
	}

	public static void setTenantId(String tenantId)
	{
		contextHolder.set(tenantId);
	}

}
