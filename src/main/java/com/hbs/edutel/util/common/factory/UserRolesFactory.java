package com.hbs.edutel.util.common.factory;

import com.hbs.edutel.common.model.UserRoles;
import com.hbs.edutel.common.model.interfaces.IUserRoles;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class UserRolesFactory implements ConstInterface
{
	private static UserRolesFactory	constFact			= null;
	/**
	 * 
	 */
	private static final long		serialVersionUID	= -2113737144066322727L;

	public static UserRolesFactory getInstance()
	{
		if (constFact == null)
		{
			constFact = new UserRolesFactory();
		}
		return constFact;
	}

	private UserRolesFactory()
	{
	}

	public IUserRoles getUserRolesInstance()
	{
		return new UserRoles();
	}

	public String getUserRolesInstanceName()
	{
		String clazzName = getUserRolesInstance().getClass().getName();
		if (clazzName.indexOf(".") > 0)
		{
			clazzName = clazzName.substring(clazzName.lastIndexOf(".") + 1);
		}
		return clazzName;
	}
}
