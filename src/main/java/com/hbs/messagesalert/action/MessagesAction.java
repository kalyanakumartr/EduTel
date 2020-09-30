package com.hbs.messagesalert.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.messagesalert.model.Messages;
import com.hbs.messagesalert.model.MessagesBeanFieldInformation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessagesAction extends MessagesActionData
{

	private static final long		serialVersionUID	= 1L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String blockUnlockMessages() throws IOException
	{
		sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		String messageId = request.getParameter("messageId");
		String status = request.getParameter("status");
		if (CommonValidator.isNotNullNotEmpty(status) == false)
			status = "true";

		Messages msg = messagesBo.blockUnlockMessages(messageId, status, sessionUser.getUsEmployeeId());
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();

		if (CommonValidator.isNotNullNotEmpty(msg))
		{
			if (CommonValidator.isEqual(status, "false"))
				jsonObj.put("value", "Message : " + msg.getMessageName() + " has been blocked successfully.");
			else
				jsonObj.put("value", "Message : " + msg.getMessageName() + " has been unblocked successfully.");

			caLogger.info(Update_User, "blockUnlockMessages", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Message : " + msg.getMessageName() + " blocking failed.");
			caLogger.info(Update_User, "blockUnlockMessages", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}

		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	private void constructComboFields() throws ClassNotFoundException
	{
		messages = new Messages();
		messagesBeanList = messagesBo.getMessagesBeanList(null);
		if (CommonValidator.isListFirstNotEmpty(messagesBeanList))
		{
			String beanDisplayName = messagesBeanList.iterator().next().getBeanDisplayName();
			messagesBo.autoPopulateMessagesBeanFields();
			messagesBeanFieldList = messagesBo.getMessagesBeanFieldList(new MessagesParam(beanDisplayName, "true"));
		}
	}

	public String deleteMessages() throws IOException
	{
		String messageId = request.getParameter("messageId");
		Messages message = messagesBo.deleteMessages(messageId);
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (CommonValidator.isNotNullNotEmpty(message))
		{
			jsonObj.put("value", "Message : " + message.getMessageName() + " has been DELETED successfully.");
			caLogger.info(Delete_User, "deleteMessages", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Unable to delete the selected Message : " + message.getMessageName() + "\n\n\nPlease contact your Administrator!.");
			caLogger.info(Delete_User, "deleteMessages", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String loadBeanFieldList() throws IOException
	{
		sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		String beanDisplayName = request.getParameter("beanName");
		if (CommonValidator.isNotNullNotEmpty(beanDisplayName))
		{
			messagesBeanFieldList = messagesBo.getMessagesBeanFieldList(new MessagesParam(beanDisplayName, "true"));
			if (CommonValidator.isListFirstNotEmpty(messagesBeanFieldList))
			{
				for (MessagesBeanFieldInformation _MBFI : messagesBeanFieldList)
				{
					jsonObj = new JSONObject();
					jsonObj.put("key", _MBFI.getBeanFieldDisplayName());
					jsonObj.put("value", _MBFI.getMessageDisplayName());
					jsonArr.add(jsonObj);
				}
			}
		}
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String messageCreate()
	{
		try
		{
			clearErrors();
			clearErrorsAndMessages();
			sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
			{
				boolean isUpdate = false;
				messages.setDeliveryDate(CommonUtil.getTimeZoneDateInFormat(messages.getDeliveryDateTime(), DATE_FORMAT_DD_MM_YYYY_HH_MM, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_24, null)); // Purposely_NULL
				if (CommonValidator.isNotNullNotEmpty(messages.getMessageId()))
				{
					isUpdate = true;
					messages.setModifiedBy(sessionUser.getUsEmployeeId());
					messages.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				}
				else
				{
					messages.setCreatedBy(sessionUser.getUsEmployeeId());
					messages.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				}
				if (messagesBo.createMessage(this))
				{
					caLogger.info(Create_User, "messageCreate", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
					if (isUpdate)
						addActionError("User Messages : \"" + messages.getMessageName() + "\" has been updated Successfully.");
					else
						addActionError("User Messages : \"" + messages.getMessageName() + "\" has been created Successfully.");
				}
				else
				{
					caLogger.info(Create_User, "messageCreate", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
					if (isUpdate)
						addActionError("User Messages : \"" + messages.getMessageName() + "\" update failed.");
					else
						addActionError("User Messages : \"" + messages.getMessageName() + "\" creation failed.");
				}
				constructComboFields();
				return EPage.Success.name();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Create_User, "messageCreate", excep.getMessage(), this.getClass().getName(), null);
		}
		return EPage.AccessDenied.name();
	}

	public String messagePreCreate()
	{
		try
		{
			clearErrors();
			clearErrorsAndMessages();
			sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
			{
				constructComboFields();
				return EPage.Success.name();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Create_User, "messagePreCreate", excep.getMessage(), this.getClass().getName(), null);
		}
		return EPage.AccessDenied.name();
	}

	public String messagePreUpdate()
	{
		try
		{
			clearErrors();
			clearErrorsAndMessages();
			sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
			{
				constructComboFields();
				String messageId = request.getParameter("messageId");
				List<Messages> msgList = messagesBo.getMessagesList(new JQueryDataTableParam(messageId));

				if (CommonValidator.isListFirstNotEmpty(msgList))
				{
					messages = msgList.iterator().next();
					messages.setDeliveryDateTime(CommonUtil.getDateInFormat(messages.getDeliveryDate(), DATE_FORMAT_DD_MM_YYYY_HH_MM));
				}

				return EPage.Success.name();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Create_User, "messagePreCreate", excep.getMessage(), this.getClass().getName(), null);
		}
		return EPage.AccessDenied.name();
	}

	public String messagesListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<Messages> msgList = messagesBo.getMessagesList(jdtParam);
		List<Messages> msgListAll = messagesBo.getMessagesList(null);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (Messages msg : msgList)
		{
			if (CommonValidator.isNotNullNotEmpty(msg))
			{
				ArrayList<String> newArray = new ArrayList<String>(0);
				newArray.add(CommonUtil.initCapsName(msg.getMessageName()));
				newArray.add(msg.getDeliveryDate() + "");
				newArray.add(msg.getMessageType());
				newArray.add(msg.getCreatedUser().getUsUserName());
				newArray.add(msg.getCreatedDate() + "");
				newArray.add(msg.getMessageId());
				newArray.add(msg.isStatus() + "");
				newArray.add(msg.getMessageId());
				aaData.add(newArray);
			}
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(msgList.size());
		dataTableObject.setiTotalRecords(msgListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();
		sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		caLogger.info(Search_User, "messagesListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		return EPage.Success.name();
	}

	public String viewMessageForUser() throws IOException
	{
		String messageId = request.getParameter("messageId");
		if (CommonValidator.isNotNullNotEmpty(messageId))
		{
			List<Messages> msgList = messagesBo.getMessagesList(new JQueryDataTableParam(messageId));

			if (CommonValidator.isListFirstNotEmpty(msgList))
			{
				JSONArray jsonArr = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				Messages msg = msgList.iterator().next();
				jsonObj.put("title", "Name : " + msg.getMessageName() + " - " + CommonUtil.getDateInFormat(msg.getDeliveryDate(), DATE_FORMAT_DD_MMM_YYYY_HH_MM_AM_PM));
				jsonObj.put("value", msg.getMessage());
				jsonObj.put("type", msg.getMessageType());
				jsonArr.add(jsonObj);
				response.getWriter().write(String.valueOf(jsonArr));
				response.getWriter().close();
			}
		}
		return EPage.Success.name();
	}
}
