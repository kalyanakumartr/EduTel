package com.hbs.messagesalert.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessageType;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.messagesalert.model.Messages;
import com.hbs.messagesalert.model.MessagesUserMapping;
import com.hbs.messagesalert.model.MessagesUsersGroup;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessagesUserAction extends MessagesActionData
{

	private static final long		serialVersionUID	= 1L;
	private final CustomAuditLogger	caLoggerMUA			= new CustomAuditLogger(this.getClass());

	public MessagesUserAction()
	{
		super();
	}

	public String blockUnlockMessagesUser() throws IOException
	{
		sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		String msgAutoId = request.getParameter("msgAutoId");
		String status = request.getParameter("status");
		if (CommonValidator.isNotNullNotEmpty(status) == false)
			status = "true";

		MessagesUserMapping _MUM = messagesBo.blockUnlockMessageUser(msgAutoId, status, sessionUser.getUsEmployeeId());
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();

		if (CommonValidator.isNotNullNotEmpty(_MUM))
		{
			if (CommonValidator.isEqual(status, "false"))
				jsonObj.put("value", "Message : " + _MUM.getMessages().getMessageName() + " has been BLOCKED successfully for the User : " + _MUM.getUser().getUsUserName());
			else
				jsonObj.put("value", "Message : " + _MUM.getMessages().getMessageName() + " has been UN-BLOCKED successfully for the User : " + _MUM.getUser().getUsUserName());

			caLoggerMUA.info(Update_User, "blockUnlockMessagesUser", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Message : " + _MUM.getMessages().getMessageName() + " blocking FAILED for the User Id : " + _MUM.getUser().getUsUserName());
			caLoggerMUA.info(Update_User, "blockUnlockMessagesUser", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}

		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String deleteMessagesUserMapping() throws IOException
	{
		String msgAutoId = request.getParameter("msgAutoId");
		MessagesUserMapping _MUM = messagesBo.deleteMessageUser(msgAutoId);
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (CommonValidator.isNotNullNotEmpty(_MUM))
		{
			jsonObj.put("value", "Message : " + _MUM.getMessages().getMessageName() + " has been DELETED successfully for the User : " + _MUM.getUser().getUsUserName());
			caLoggerMUA.info(Delete_User, "deleteMessagesUserMapping", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Unable to delete the selected Message : " + _MUM.getMessages().getMessageName() + " for the User : " + _MUM.getUser().getUsUserName()
					+ "\n\n\nPlease contact your Administrator!.");
			caLoggerMUA.info(Delete_User, "deleteMessagesUserMapping", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String messagesUsersGroup()
	{
		try
		{
			sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
			{
				return EPage.Success.name();
			}
		}
		catch (Exception excep)
		{
			caLoggerMUA.error(Create_User, "messagesUsersGroup", excep.getMessage(), this.getClass().getName(), null);
		}
		return EPage.AccessDenied.name();
	}

	public String messagesUsersListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<MessagesUserMapping> msgUserList = messagesBo.getMessagesUsersList(jdtParam);
		List<MessagesUserMapping> msgUserListAll = messagesBo.getMessagesUsersList(null);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (MessagesUserMapping msgUser : msgUserList)
		{
			if (CommonValidator.isNotNullNotEmpty(msgUser))
			{
				ArrayList<String> newArray = new ArrayList<String>(0);
				newArray.add(CommonUtil.initCapsName(msgUser.getMessages().getMessageName()));
				newArray.add(msgUser.getUser().getUsUserName());
				newArray.add(msgUser.getUser().getUsUserID());
				newArray.add(msgUser.getMessages().getDeliveryDate() + "");
				newArray.add(msgUser.getMessageStatus());
				newArray.add(msgUser.getCreatedUser().getUsUserName());
				newArray.add(msgUser.getCreatedDate() + "");
				newArray.add(msgUser.getAutoId() + "");
				newArray.add(msgUser.getMessages().getMessage());
				aaData.add(newArray);
			}
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(msgUserList.size());
		dataTableObject.setiTotalRecords(msgUserListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();
		sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		caLoggerMUA.info(Search_User, "messagesUsersListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		return EPage.Success.name();
	}

	public String messageUserMappingCreate()
	{
		try
		{
			clearErrors();
			clearErrorsAndMessages();
			sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
			{
				if (CommonValidator.isNotNullNotEmpty(employeeIds) || CommonValidator.isNotNullNotEmpty(userGroupName))
				{
					if (CommonValidator.isNotNullNotEmpty(employeeIds))
					{
						usEmployeeIdList.addAll(Arrays.asList(employeeIds.split(COMMA_SPACE.trim())));
					}
					else
					{
						MessagesParam msgParam = new MessagesParam();
						msgParam.userGroupName = userGroupName;
						for (MessagesUsersGroup _MUG : messagesBo.getMessageUsersGroupList(msgParam))
						{
							usEmployeeIdList.add(_MUG.getUsEmployeeId());
						}
					}
					if (messagesBo.createMessageUserMapping(this))
					{
						messagesBo.createMessageUsersGroup(this);// Create_Or_Update_UserGroup
						caLoggerMUA.info(Create_User, "messageUserMappingCreation", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
						addActionError("Messages has been mapped to User(s) Successfully.");
					}
					else
					{
						caLoggerMUA.info(Create_User, "messageUserMappingCreation", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
						addActionError("Messages mapping to User(s) failed.");
					}
				}

				return EPage.Success.name();
			}
		}
		catch (Exception excep)
		{
			messagesBo.createMessageUsersGroup(this);// Create_Or_Update_UserGroup
			addActionError(excep.getMessage());
			caLoggerMUA.error(Create_User, "messageUserMappingCreation", excep.getMessage(), this.getClass().getName(), null);
			return EPage.Success.name();
		}
		finally
		{
			reInitializeFields();
		}
		return EPage.AccessDenied.name();
	}

	public String messageUserMappingPreCreate()
	{
		try
		{
			clearErrors();
			clearErrorsAndMessages();
			sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
			{
				reInitializeFields();
				return EPage.Success.name();
			}
		}
		catch (Exception excep)
		{
			caLoggerMUA.error(Create_User, "messageUserMappingPreCreate", excep.getMessage(), this.getClass().getName(), null);
		}
		return EPage.AccessDenied.name();
	}

	private void reInitializeFields()
	{
		employeeIds = "";
		userGroupName = "";
		usEmployeeIdList = new ArrayList<String>(0);
		messages = new Messages();
		messagesUserList = new ArrayList<MessagesUserMapping>(0);
		messagesList = messagesBo.getMessagesList(new JQueryDataTableParam("Active"));
		messageUsersGroup = new MessagesUsersGroup();
		messageType = EMessageType.SMS.name();
		messageUsersGroupList = messagesBo.getDistinctMessageUsersGroupList();
	}

	public String usersForMessageListByJson() throws IOException
	{
		sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);

			List<IUsers> usersList = adminBo.getUsersList(jdtParam);
			List<IUsers> usersListAll = adminBo.getUsersListAll(jdtParam);

			List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

			for (IUsers iUsers : usersList)
			{
				ArrayList<String> newArray = new ArrayList<String>(0);
				newArray.add(CommonUtil.initCapsName(iUsers.getUsUserName()));// 0
				newArray.add(iUsers.getUsUserID());// 1
				newArray.add(iUsers.getUsEmail());// 2
				newArray.add(iUsers.getUsMobileNo() + "");// 3
				newArray.add(iUsers.getUsCity());// 4
				newArray.add(iUsers.getUsUsersType());// 5
				newArray.add(iUsers.getUsEmployeeId());// 6
				aaData.add(newArray);
			}

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(aaData);
			dataTableObject.setiTotalDisplayRecords(usersListAll.size());
			dataTableObject.setiTotalRecords(usersListAll.size());

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(dataTableObject);
			response.getWriter().write(jsonString);
			response.getWriter().close();

			caLoggerMUA.info(Search_User, "userListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String viewUserGroup() throws IOException
	{
		String userGroupName = request.getParameter("userGroupName");
		if (CommonValidator.isNotNullNotEmpty(userGroupName))
		{
			MessagesParam msgParam = new MessagesParam();
			msgParam.userGroupName = userGroupName;

			List<MessagesUsersGroup> mugList = messagesBo.getMessageUsersGroupList(msgParam);

			if (CommonValidator.isListFirstNotEmpty(mugList))
			{
				JSONArray jsonArr = new JSONArray();
				for (MessagesUsersGroup _MUG : mugList)
				{
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("name", _MUG.getUser().getUsUserName());
					jsonObj.put("id", _MUG.getUser().getUsUserID());
					jsonObj.put("mobile", _MUG.getUser().getUsMobileNo());
					jsonObj.put("email", _MUG.getUser().getUsEmail());
					jsonArr.add(jsonObj);
				}
				response.getWriter().write(String.valueOf(jsonArr));
				response.getWriter().close();
			}
		}
		return EPage.Success.name();
	}
}