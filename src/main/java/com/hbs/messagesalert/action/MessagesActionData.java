package com.hbs.messagesalert.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.action.CommonActionData;
import com.hbs.edutel.common.bo.AdminBo;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessageType;
import com.hbs.edutel.util.common.factory.UsersFactory;
import com.hbs.messagesalert.bo.MessagesBo;
import com.hbs.messagesalert.model.Messages;
import com.hbs.messagesalert.model.MessagesBeanFieldInformation;
import com.hbs.messagesalert.model.MessagesBeanInformation;
import com.hbs.messagesalert.model.MessagesUserMapping;
import com.hbs.messagesalert.model.MessagesUsersGroup;

public class MessagesActionData extends CommonActionData
{
	private static final long						serialVersionUID		= 1L;
	protected AdminBo								adminBo;
	protected String								employeeIds;
	protected Messages								messages				= new Messages();
	protected List<MessagesBeanFieldInformation>	messagesBeanFieldList	= new ArrayList<MessagesBeanFieldInformation>(0);
	protected List<MessagesBeanInformation>			messagesBeanList		= new ArrayList<MessagesBeanInformation>(0);
	protected MessagesBo							messagesBo;
	protected List<Messages>						messagesList			= new ArrayList<Messages>(0);
	protected List<MessagesUserMapping>				messagesUserList		= new ArrayList<MessagesUserMapping>(0);
	protected String								messageType				= EMessageType.SMS.name();
	protected MessagesUsersGroup					messageUsersGroup		= new MessagesUsersGroup();
	protected List<MessagesUsersGroup>				messageUsersGroupList	= new ArrayList<MessagesUsersGroup>(0);
	protected IUsers								sessionUser				= UsersFactory.getInstance().getUsersInstance();
	protected List<String>							usEmployeeIdList		= new ArrayList<String>(0);
	protected String								userGroupName;

	public AdminBo getAdminBo()
	{
		return adminBo;
	}

	public String getEmployeeIds()
	{
		return employeeIds;
	}

	public Messages getMessages()
	{
		return messages;
	}

	public List<MessagesBeanFieldInformation> getMessagesBeanFieldList()
	{
		return messagesBeanFieldList;
	}

	public List<MessagesBeanInformation> getMessagesBeanList()
	{
		return messagesBeanList;
	}

	public MessagesBo getMessagesBo()
	{
		return messagesBo;
	}

	public List<Messages> getMessagesList()
	{
		return messagesList;
	}

	public List<MessagesUserMapping> getMessagesUserList()
	{
		return messagesUserList;
	}

	public String getMessageType()
	{
		return messageType;
	}

	public MessagesUsersGroup getMessageUsersGroup()
	{
		return messageUsersGroup;
	}

	public List<MessagesUsersGroup> getMessageUsersGroupList()
	{
		return messageUsersGroupList;
	}

	public IUsers getSessionUser()
	{
		return sessionUser;
	}

	public List<String> getUsEmployeeIdList()
	{
		return usEmployeeIdList;
	}

	public String getUserGroupName()
	{
		return userGroupName;
	}

	public void setAdminBo(AdminBo adminBo)
	{
		this.adminBo = adminBo;
	}

	public void setEmployeeIds(String employeeIds)
	{
		this.employeeIds = employeeIds;
	}

	public void setMessages(Messages messages)
	{
		this.messages = messages;
	}

	public void setMessagesBeanFieldList(List<MessagesBeanFieldInformation> messagesBeanFieldList)
	{
		this.messagesBeanFieldList = messagesBeanFieldList;
	}

	public void setMessagesBeanList(List<MessagesBeanInformation> messagesBeanList)
	{
		this.messagesBeanList = messagesBeanList;
	}

	public void setMessagesBo(MessagesBo messagesBo)
	{
		this.messagesBo = messagesBo;
	}

	public void setMessagesList(List<Messages> messagesList)
	{
		this.messagesList = messagesList;
	}

	public void setMessagesUserList(List<MessagesUserMapping> messagesUserList)
	{
		this.messagesUserList = messagesUserList;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public void setMessageUsersGroup(MessagesUsersGroup messageUsersGroup)
	{
		this.messageUsersGroup = messageUsersGroup;
	}

	public void setMessageUsersGroupList(List<MessagesUsersGroup> messageUsersGroupList)
	{
		this.messageUsersGroupList = messageUsersGroupList;
	}

	public void setSessionUser(IUsers sessionUser)
	{
		this.sessionUser = sessionUser;
	}

	public void setUsEmployeeIdList(List<String> usEmployeeIdList)
	{
		this.usEmployeeIdList = usEmployeeIdList;
	}

	public void setUserGroupName(String userGroupName)
	{
		this.userGroupName = userGroupName;
	}

}
