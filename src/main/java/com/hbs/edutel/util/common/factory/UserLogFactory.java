package com.hbs.edutel.util.common.factory;

import com.hbs.edutel.common.model.UserLog;
import com.hbs.edutel.common.model.interfaces.IUserLog;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class UserLogFactory implements ConstInterface
{
	private static UserLogFactory	constFact			= null;
	private static final long		serialVersionUID	= 1035097100965189177L;

	public static UserLogFactory getInstance()
	{
		if (constFact == null)
		{
			constFact = new UserLogFactory();
		}
		return constFact;
	}

	private UserLogFactory()
	{
	}

	public IUserLog getUserLogInstance()
	{
		return new UserLog();
	}

	public String getUserLogInstanceName()
	{
		String clazzName = getUserLogInstance().getClass().getName();
		if (clazzName.indexOf(".") > 0)
		{
			clazzName = clazzName.substring(clazzName.lastIndexOf(".") + 1);
		}
		return clazzName;
	}
}
