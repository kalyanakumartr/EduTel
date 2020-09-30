package com.hbs.edutel.common.model;

import com.hbs.edutel.common.model.abstracts.AUserRoles;
import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUsers;

public class UserRoles extends AUserRoles
{

	private static final long	serialVersionUID	= 8909239704346625769L;
	private IRoles				roles;
	private Integer				urUserRoleAutoId;
	private IUsers				users;

	public UserRoles()
	{
		super();
	}

	public UserRoles(IUsers users, IRoles roles)
	{
		super();
		this.users = users;
		this.roles = roles;
	}

	
	public IRoles getRoles()
	{
		return roles;
	}

	
	public Integer getUrUserRoleAutoId()
	{
		return urUserRoleAutoId;
	}

	
	public IUsers getUsers()
	{
		return users;
	}

	
	public void setRoles(IRoles roles)
	{
		this.roles = roles;
	}

	
	public void setUrUserRoleAutoId(Integer urUserRoleAutoId)
	{
		this.urUserRoleAutoId = urUserRoleAutoId;
	}

	
	public void setUsers(IUsers users)
	{
		this.users = users;
	}

}
