package com.hbs.edutel.common.model.abstracts;

import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUserRoles;
import com.hbs.edutel.common.model.interfaces.IUsers;

public abstract class AUserRoles implements IUserRoles
{

	private static final long	serialVersionUID	= 2755633637514848795L;

	
	public IRoles getRoles()
	{

		return null;
	}

	
	public Integer getUrUserRoleAutoId()
	{

		return null;
	}

	
	public IUsers getUsers()
	{

		return null;
	}

	
	public void setRoles(IRoles roles)
	{

	}

	
	public void setUrUserRoleAutoId(Integer urUserRoleAutoId)
	{

	}

	
	public void setUsers(IUsers users)
	{

	}

}
