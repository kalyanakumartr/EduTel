package com.hbs.edutel.util.common.factory;

import com.hbs.edutel.common.model.Users;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class UsersFactory implements ConstInterface
{
	private static UsersFactory	constFact			= null;
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3042851266444225107L;

	public static UsersFactory getInstance()
	{
		if (constFact == null)
		{
			constFact = new UsersFactory();
		}
		return constFact;
	}

	private UsersFactory()
	{
	}

	public IUsers getUsersInstance()
	{
		return new Users();
	}

	public IUsers getUsersInstance(String usEmployeeId)
	{
		return new Users(usEmployeeId);
	}

	public String getUsersInstanceName()
	{
		String clazzName = getUsersInstance().getClass().getName();
		if (clazzName.indexOf(".") > 0)
		{
			clazzName = clazzName.substring(clazzName.lastIndexOf(".") + 1);
		}
		return clazzName;
	}

}
