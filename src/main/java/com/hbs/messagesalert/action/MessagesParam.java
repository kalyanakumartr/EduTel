package com.hbs.messagesalert.action;

public class MessagesParam
{
	public int		autoId;
	public String	beanDisplayName;
	public String	deliveryDateTime;
	public String	emailStatus;
	public String	messageId;
	public String	messageName;
	public String	messageStatus;
	public String	messageType;
	public String	modifiedBy;
	public String	status;
	public String	usEmailId;
	public String	usEmployeeId;
	public String	userGroupName;
	public String	usUserID;

	public MessagesParam()
	{
	}

	public MessagesParam(String beanDisplayName)
	{
		this.beanDisplayName = beanDisplayName;
	}

	public MessagesParam(String beanDisplayName, String status)
	{
		this.beanDisplayName = beanDisplayName;
		this.status = status;
	}

}
