package com.hbs.edutel.hibernate3.support;

public class ThreadLocalContextUtil
{
	private static final ThreadLocal<String>	contextHolder	= new ThreadLocal<String>();

	public static void clearDatabaseType()
	{
		contextHolder.remove();
	}

	public static String getDatabaseType()
	{
		String db = contextHolder.get();
		return db;
	}

	public static void setDatabaseType(String databaseType)
	{
		contextHolder.set(databaseType);
	}

}
