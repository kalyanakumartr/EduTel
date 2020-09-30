package com.hbs.edutel.admin.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.action.CommonActionBoData;
import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUsers;

public abstract class AdminRoleManagementActionData extends CommonActionBoData
{
	private static final long	serialVersionUID	= -5584025673386111400L;
	protected List<String>		avlRolesList		= new ArrayList<String>(0);
	protected boolean			hasApprovalAuthority;
	protected List<IRoles>		rolesList			= new ArrayList<IRoles>(0);
	protected IUsers			user;
	protected List<IUsers>		userList			= new ArrayList<IUsers>(0);

	public AdminRoleManagementActionData()
	{
		super();
	}

	public List<String> getAvlRolesList()
	{
		return avlRolesList;
	}

	public List<IRoles> getRolesList()
	{
		return rolesList;
	}

	public IUsers getUser()
	{
		return user;
	}

	public List<IUsers> getUserList()
	{
		return userList;
	}

	public boolean isHasApprovalAuthority()
	{
		return hasApprovalAuthority;
	}

	public void setAvlRolesList(List<String> avlRolesList)
	{
		this.avlRolesList = avlRolesList;
	}

	public void setHasApprovalAuthority(boolean hasApprovalAuthority)
	{
		this.hasApprovalAuthority = hasApprovalAuthority;
	}

	public void setRolesList(List<IRoles> rolesList)
	{
		this.rolesList = rolesList;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUserList(List<IUsers> userList)
	{
		this.userList = userList;
	}

}