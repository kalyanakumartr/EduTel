package com.hbs.edutel.common.model.interfaces;

/**
 * UserRoles entity. @author MyEclipse Persistence Tools
 */

public interface IUserRoles extends java.io.Serializable
{
	public IRoles getRoles();

	public Integer getUrUserRoleAutoId();

	public IUsers getUsers();

	public void setRoles(IRoles roles);

	public void setUrUserRoleAutoId(Integer urUserRoleAutoId);

	public void setUsers(IUsers users);

}