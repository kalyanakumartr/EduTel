package com.hbs.messagesalert.bo.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EGenerate;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessage;
import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.messagesalert.action.MessagesAction;
import com.hbs.messagesalert.action.MessagesParam;
import com.hbs.messagesalert.action.MessagesUserAction;
import com.hbs.messagesalert.bo.MessagesBo;
import com.hbs.messagesalert.dao.MessagesDAO;
import com.hbs.messagesalert.model.Messages;
import com.hbs.messagesalert.model.MessagesBeanFieldInformation;
import com.hbs.messagesalert.model.MessagesBeanInformation;
import com.hbs.messagesalert.model.MessagesUserMapping;
import com.hbs.messagesalert.model.MessagesUsersGroup;

public class MessagesBoImpl implements MessagesBo, ConstInterface
{
	private static final long	serialVersionUID	= 264323725343378889L;
	private MessagesDAO			messagesDAO;

	
	public boolean autoPopulateMessagesBeanFields() throws ClassNotFoundException
	{
		for (MessagesBeanInformation beanInfo : messagesDAO.getMessagesBeanList(null))
		{
			List<MessagesBeanFieldInformation> beanFieldList = new ArrayList<MessagesBeanFieldInformation>(0);
			List<String> fieldNameList = messagesDAO.getMessagesBeanFieldNameList(new MessagesParam(beanInfo.getBeanDisplayName()));

			Class<?> clazz = Class.forName(beanInfo.getBeanNameWithPackage());

			for (Field field : clazz.getDeclaredFields())
			{
				if (fieldNameList.contains(field.getName()) == false)
				{
					beanFieldList.add(new MessagesBeanFieldInformation(beanInfo, field.getName()));
					fieldNameList.add(field.getName());
				}
			}

			for (Field field : clazz.getFields())
			{
				if (fieldNameList.contains(field.getName()) == false)
				{
					beanFieldList.add(new MessagesBeanFieldInformation(beanInfo, field.getName()));
					fieldNameList.add(field.getName());
				}
			}
			messagesDAO.createMessagesBeanField(beanFieldList);
		}
		return true;
	}

	
	public Messages blockUnlockMessages(String messageId, String status, String usEmployeeId)
	{
		return messagesDAO.blockUnlockMessages(messageId, status, usEmployeeId);
	}

	
	public MessagesUserMapping blockUnlockMessageUser(String msgAutoId, String status, String usEmployeeId)
	{
		return messagesDAO.blockUnlockMessageUser(msgAutoId, status, usEmployeeId);
	}

	
	public boolean createMessage(MessagesAction messagesAction)
	{
		if (CommonValidator.isNotNullNotEmpty(messagesAction.getMessages().getMessageId()) == false)
		{
			messagesAction.getMessages().setMessageId(EGenerate.Message.getPrimaryId());
		}
		return messagesDAO.createMessage(messagesAction);
	}

	
	public boolean createMessageUserMapping(MessagesUserAction messagesAction) throws Exception
	{
		MessagesParam msgParam = new MessagesParam();
		msgParam.messageId = messagesAction.getMessages().getMessageId();
		msgParam.messageType = messagesAction.getMessages().getMessageType();
		msgParam.messageStatus = EMessage.Pending.name();
		List<MessagesUserMapping> removedMUMList = new ArrayList<MessagesUserMapping>(0);

		List<MessagesUserMapping> msgUserList = messagesDAO.getMessagesUserListByMessageOrUser(msgParam);

		for (MessagesUserMapping msgUserMap : msgUserList)
		{
			if (messagesAction.getUsEmployeeIdList().contains(msgUserMap.getUsEmployeeId()))
			{
				messagesAction.getUsEmployeeIdList().remove(msgUserMap.getUsEmployeeId());
				removedMUMList.add(msgUserMap);
			}
		}
		messagesAction.setMessagesUserList(new ArrayList<MessagesUserMapping>(0));
		for (String usEmployeeId : messagesAction.getUsEmployeeIdList())
		{
			MessagesUserMapping _MUM = new MessagesUserMapping();
			_MUM.setMessageId(messagesAction.getMessages().getMessageId());
			_MUM.setUsEmployeeId(usEmployeeId);
			_MUM.setMessageStatus(EMessage.Pending.name());
			_MUM.setStatus(true);
			_MUM.setCreatedBy(messagesAction.getSessionUser().getUsEmployeeId());
			_MUM.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			messagesAction.getMessagesUserList().add(_MUM);
		}
		if (messagesAction.getMessagesUserList().isEmpty())
			throw new Exception("Selected Users may already mapped to the Message ");
		boolean isMapped = messagesDAO.createMessageUserMapping(messagesAction);
		if (isMapped)
		{
			if (CommonValidator.isListFirstNotEmpty(removedMUMList))
			{
				String msgName = removedMUMList.iterator().next().getMessages().getMessageName();
				StringBuffer removedUsers = new StringBuffer("Users partially mapped to the message.<BR><BR>List of Users already mapped to the Selected Messgage \"" + msgName
						+ "\"<BR>User ID's are :<BR>");
				int i = 0;
				for (MessagesUserMapping _MUM : removedMUMList)
				{
					if (i++ % 3 == 0)
						removedUsers.append("<BR>");
					removedUsers.append(_MUM.getUser().getUsUserName() + " - " + _MUM.getUser().getUsUserID() + COMMA_SPACE);
				}
				throw new Exception(removedUsers.toString().substring(0, removedUsers.toString().lastIndexOf(COMMA_SPACE)));
			}
		}
		return isMapped;
	}

	
	public boolean createMessageUsersGroup(MessagesUserAction messagesAction)
	{
		MessagesParam msgParam = new MessagesParam();
		msgParam.userGroupName = messagesAction.getMessageUsersGroup().getUserGroupName();
		for (MessagesUsersGroup _MUG : messagesDAO.getMessageUsersGroupList(msgParam))
		{
			if (messagesAction.getUsEmployeeIdList().contains(_MUG.getUsEmployeeId()))
			{
				messagesAction.getUsEmployeeIdList().remove(_MUG.getUsEmployeeId());
			}
		}
		messagesAction.setMessageUsersGroupList(new ArrayList<MessagesUsersGroup>(0));
		for (String usEmployeeId : messagesAction.getUsEmployeeIdList())
		{
			MessagesUsersGroup _MUG = new MessagesUsersGroup(msgParam.userGroupName, usEmployeeId);
			_MUG.setStatus(true);
			_MUG.setCreatedBy(messagesAction.getSessionUser().getUsEmployeeId());
			_MUG.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			messagesAction.getMessageUsersGroupList().add(_MUG);
		}
		return messagesDAO.createMessageUsersGroup(messagesAction);
	}

	
	public Messages deleteMessages(String messageId)
	{
		return messagesDAO.deleteMessages(messageId);
	}

	
	public MessagesUserMapping deleteMessageUser(String msgAutoId)
	{
		return messagesDAO.deleteMessageUser(msgAutoId);
	}

	
	public List<MessagesUsersGroup> getDistinctMessageUsersGroupList()
	{
		List<MessagesUsersGroup> msgUserGroupList = new ArrayList<MessagesUsersGroup>(0);
		List<String> grpNameList = new ArrayList<String>(0);

		for (MessagesUsersGroup msgUsrGrp : messagesDAO.getMessageUsersGroupList(null))
		{
			if (grpNameList.contains(msgUsrGrp.getUserGroupName()) == false)
			{
				msgUserGroupList.add(msgUsrGrp);
				grpNameList.add(msgUsrGrp.getUserGroupName());
			}
		}
		return msgUserGroupList;
	}

	
	public List<MessagesBeanFieldInformation> getMessagesBeanFieldList(MessagesParam msgParam)
	{
		return messagesDAO.getMessagesBeanFieldList(msgParam);
	}

	
	public List<MessagesBeanInformation> getMessagesBeanList(MessagesParam msgParam)
	{
		return messagesDAO.getMessagesBeanList(msgParam);
	}

	public MessagesDAO getMessagesDAO()
	{
		return messagesDAO;
	}

	
	public List<Messages> getMessagesList(JQueryDataTableParam jdtParam)
	{
		return messagesDAO.getMessagesList(jdtParam);
	}

	
	public List<MessagesUserMapping> getMessagesUserList(JQueryDataTableParam jdtParam)
	{
		return messagesDAO.getMessagesUserList(jdtParam);
	}

	
	public List<MessagesUserMapping> getMessagesUsersList(JQueryDataTableParam jdtParam)
	{
		return messagesDAO.getMessagesUsersList(jdtParam);
	}

	
	public List<MessagesUsersGroup> getMessageUsersGroupList(MessagesParam msgParam)
	{
		return messagesDAO.getMessageUsersGroupList(msgParam);
	}

	public void setMessagesDAO(MessagesDAO messagesDAO)
	{
		this.messagesDAO = messagesDAO;
	}
}
