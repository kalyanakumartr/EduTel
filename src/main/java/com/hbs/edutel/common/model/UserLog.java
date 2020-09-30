package com.hbs.edutel.common.model;

import java.sql.Timestamp;

import com.hbs.edutel.common.model.abstracts.AUserLog;
import com.hbs.edutel.common.model.interfaces.IUsers;

public class UserLog extends AUserLog
{
	private static final long	serialVersionUID	= -5336666543176665572L;
	private String				ulIPAddress;
	private Integer				ulUserLogAutoId;
	private Timestamp			ulUserLoginTime;
	private Timestamp			ulUserLogoutTime;
	private IUsers				users;

	public UserLog()
	{
		super();
	}

	
	public String getUlIPAddress()
	{
		return this.ulIPAddress;
	}

	
	public Integer getUlUserLogAutoId()
	{
		return this.ulUserLogAutoId;
	}

	
	public Timestamp getUlUserLoginTime()
	{
		return this.ulUserLoginTime;
	}

	
	public Timestamp getUlUserLogoutTime()
	{
		return this.ulUserLogoutTime;
	}

	
	public IUsers getUsers()
	{
		return this.users;
	}

	
	public void setUlIPAddress(String ulIPAddress)
	{
		this.ulIPAddress = ulIPAddress;
	}

	
	public void setUlUserLogAutoId(Integer ulUserLogAutoId)
	{
		this.ulUserLogAutoId = ulUserLogAutoId;
	}

	
	public void setUlUserLoginTime(Timestamp ulUserLoginTime)
	{
		this.ulUserLoginTime = ulUserLoginTime;
	}

	
	public void setUlUserLogoutTime(Timestamp ulUserLogoutTime)
	{
		this.ulUserLogoutTime = ulUserLogoutTime;
	}

	
	public void setUsers(IUsers users)
	{
		this.users = users;
	}

}
