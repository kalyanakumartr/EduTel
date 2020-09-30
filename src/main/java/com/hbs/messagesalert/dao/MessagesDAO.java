package com.hbs.messagesalert.dao;

import java.util.List;

import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.messagesalert.action.MessagesAction;
import com.hbs.messagesalert.action.MessagesParam;
import com.hbs.messagesalert.action.MessagesUserAction;
import com.hbs.messagesalert.model.Messages;
import com.hbs.messagesalert.model.MessagesBeanFieldInformation;
import com.hbs.messagesalert.model.MessagesBeanInformation;
import com.hbs.messagesalert.model.MessagesUserMapping;
import com.hbs.messagesalert.model.MessagesUsersGroup;

public interface MessagesDAO
{
	public Messages blockUnlockMessages(String messageId, String status, String modifiedBy);

	public MessagesUserMapping blockUnlockMessageUser(String msgAutoId, String status, String modifiedBy);

	public boolean createMessage(MessagesAction messagesAction);

	public boolean createMessagesBeanField(List<MessagesBeanFieldInformation> beanFieldList);

	public boolean createMessageUserMapping(MessagesUserAction messagesAction);

	public boolean createMessageUsersGroup(MessagesUserAction messagesAction);

	public Messages deleteMessages(String messageId);

	public MessagesUserMapping deleteMessageUser(String msgAutoId);

	public List<MessagesBeanFieldInformation> getMessagesBeanFieldList(MessagesParam msgParam);

	public List<String> getMessagesBeanFieldNameList(MessagesParam msgParam);

	public List<MessagesBeanInformation> getMessagesBeanList(MessagesParam msgParam);

	public List<Messages> getMessagesList(JQueryDataTableParam jdtParam);

	public List<MessagesUserMapping> getMessagesUserList(JQueryDataTableParam jdtParam);

	public List<MessagesUserMapping> getMessagesUserListByMessageOrUser(MessagesParam msgParam);

	public List<MessagesUserMapping> getMessagesUsersList(JQueryDataTableParam jdtParam);

	public List<MessagesUsersGroup> getMessageUsersGroupList(MessagesParam msgParam);

}
