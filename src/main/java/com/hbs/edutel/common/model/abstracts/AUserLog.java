package com.hbs.edutel.common.model.abstracts;

import java.sql.Timestamp;

import com.hbs.edutel.common.model.interfaces.IUserLog;
import com.hbs.edutel.common.model.interfaces.IUsers;

public abstract class AUserLog implements IUserLog
{
	private static final long	serialVersionUID	= 653843773236076704L;

	
	public String getUlIPAddress()
	{

		return null;
	}

	
	public Integer getUlUserLogAutoId()
	{

		return null;
	}

	
	public Timestamp getUlUserLoginTime()
	{

		return null;
	}

	
	public Timestamp getUlUserLogoutTime()
	{

		return null;
	}

	
	public IUsers getUsers()
	{

		return null;
	}

	
	public void setUlIPAddress(String ulIPAddress)
	{

	}

	
	public void setUlUserLogAutoId(Integer ulUserLogAutoId)
	{

	}

	
	public void setUlUserLoginTime(Timestamp ulUserLoginTime)
	{

	}

	
	public void setUlUserLogoutTime(Timestamp ulUserLogoutTime)
	{

	}

	
	public void setUsers(IUsers users)
	{

	}

}
