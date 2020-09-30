package com.hbs.edutel.common.model.interfaces;

import java.sql.Timestamp;

/**
 * UserLog entity. @author MyEclipse Persistence Tools
 */

public interface IUserLog extends java.io.Serializable
{
	public String getUlIPAddress();

	public Integer getUlUserLogAutoId();

	public Timestamp getUlUserLoginTime();

	public Timestamp getUlUserLogoutTime();

	public IUsers getUsers();

	public void setUlIPAddress(String ulIPAddress);

	public void setUlUserLogAutoId(Integer ulUserLogAutoId);

	public void setUlUserLoginTime(Timestamp ulUserLoginTime);

	public void setUlUserLogoutTime(Timestamp ulUserLogoutTime);

	public void setUsers(IUsers users);

}