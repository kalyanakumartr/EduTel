package com.hbs.messagesalert.bo;

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

public interface MessagesBo
{
	public boolean autoPopulateMessagesBeanFields() throws ClassNotFoundException;

	public Messages blockUnlockMessages(String messageId, String status, String usEmployeeId);

	public MessagesUserMapping blockUnlockMessageUser(String msgAutoId, String status, String usEmployeeId);

	public boolean createMessage(MessagesAction messagesAction);

	public boolean createMessageUserMapping(MessagesUserAction messagesAction) throws Exception;

	public boolean createMessageUsersGroup(MessagesUserAction messagesAction);

	public Messages deleteMessages(String messageId);

	public MessagesUserMapping deleteMessageUser(String msgAutoId);

	public List<MessagesUsersGroup> getDistinctMessageUsersGroupList();

	public List<MessagesBeanFieldInformation> getMessagesBeanFieldList(MessagesParam msgParam);

	public List<MessagesBeanInformation> getMessagesBeanList(MessagesParam msgParam);

	public List<Messages> getMessagesList(JQueryDataTableParam jdtParam);

	public List<MessagesUserMapping> getMessagesUserList(JQueryDataTableParam jdtParam);

	public List<MessagesUserMapping> getMessagesUsersList(JQueryDataTableParam jdtParam);

	public List<MessagesUsersGroup> getMessageUsersGroupList(MessagesParam msgParam);

}
