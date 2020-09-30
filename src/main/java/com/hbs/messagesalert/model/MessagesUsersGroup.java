package com.hbs.messagesalert.model;

import com.hbs.edutel.common.model.interfaces.IUsers;

public class MessagesUsersGroup extends CommonBeanFields
{

	private static final long	serialVersionUID	= 1L;
	protected String			usEmployeeId;
	protected IUsers			user;
	protected int				userGroupAutoId;
	protected String			userGroupName;

	public MessagesUsersGroup()
	{

	}

	public MessagesUsersGroup(String userGroupName, String usEmployeeId)
	{
		this.userGroupName = userGroupName;
		this.usEmployeeId = usEmployeeId;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public IUsers getUser()
	{
		return user;
	}

	public int getUserGroupAutoId()
	{
		return userGroupAutoId;
	}

	public String getUserGroupName()
	{
		return userGroupName;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUserGroupAutoId(int userGroupAutoId)
	{
		this.userGroupAutoId = userGroupAutoId;
	}

	public void setUserGroupName(String userGroupName)
	{
		this.userGroupName = userGroupName;
	}

}
