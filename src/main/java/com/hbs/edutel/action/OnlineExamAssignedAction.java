package com.hbs.edutel.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlineExamAssigned;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public abstract class OnlineExamAssignedAction extends OnlineExamActionData
{
	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLoggerAssign		= new CustomAuditLogger(this.getClass());

	public OnlineExamAssignedAction()
	{
		super();
	}

	public String blockUnlockOnlineExamAssigned() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		String oeExamAutoId = request.getParameter("oeExamAutoId");
		String status = request.getParameter("status");
		if (CommonValidator.isNotNullNotEmpty(status) == false)
			status = "true";

		OnlineExamAssigned oeAssigned = adminBo.blockUnlockExamAssigned(oeExamAutoId, status, sessionUser.getUsEmployeeId());
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();

		if (CommonValidator.isNotNullNotEmpty(oeAssigned))
		{
			if (CommonValidator.isEqual(status, "false"))
				jsonObj.put("value", "Assigned Exam : " + oeAssigned.getOnlineExam().getOeExamName() + " has been blocked successfully");
			else
				jsonObj.put("value", "Assigned Exam : " + oeAssigned.getOnlineExam().getOeExamName() + " has been unblocked successfully");

			caLoggerAssign.info(Update_MCQ_Exam, "blockUnlockAssignedExam", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Assigned Exam : " + oeAssigned.getOnlineExam().getOeExamName() + " blocking failed");
			caLoggerAssign.info(Update_MCQ_Exam, "blockUnlockAssignedExam", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}

		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String createOnlineExamAssigned()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			try
			{
				if (adminBo.createOnlineExamAssigned(this))
				{
					caLoggerAssign.info(Create_MCQ_Exam, "createOnlineExamAssigned", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

					addActionError("Online Exam : " + onlineExamAssigned.getOnlineExam().getOeExamName() + "\n\nFor Student Id " + onlineExamAssigned.getUsers().getUsUserID()
							+ " has been assigned Successfully.");
				}
				else
				{
					throw new Exception();
				}
			}
			catch (Exception e)
			{
				caLoggerAssign.info(Create_MCQ_Exam, "createOnlineExamAssigned", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("Online Exam : " + onlineExamAssigned.getOnlineExam().getOeExamName() + "\n\nFor Student Id " + onlineExamAssigned.getUsers().getUsUserID()
						+ " assigning failed or exists already.");
			}
			onlineExamAssigned = new OnlineExamAssigned();
			return createOnlinePreExamAssigned();
		}

		return EPage.AccessDenied.name();
	}

	public String createOnlinePreExamAssigned()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.usUsersType = EPage.Student.name();
			usersList = adminBo.getUsersListAll(jdtParam);

			jdtParam = new JQueryDataTableParam();
			jdtParam.isOnlineAssessment = true;
			onlineExamList = adminBo.getOnlineExamListByJsonAll(jdtParam);

			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();

	}

	public String deleteOnlineExamAssigned() throws IOException
	{
		String oeExamAutoId = request.getParameter("oeExamAutoId");
		OnlineExamAssigned delOEAssigned = adminBo.deleteOnlineExamAssigned(oeExamAutoId);
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (CommonValidator.isNotNullNotEmpty(delOEAssigned))
		{
			jsonObj.put("value", "Assigned Exam : " + delOEAssigned.getOnlineExam().getOeExamName() + "\n\nFor Student Id " + delOEAssigned.getUsers().getUsUserID() + " has been deleted successfully");
			caLoggerAssign.info(Delete_MCQ_Exam, "deleteOnlineExamAssigned", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Unable to delete the selected Assigend Online Exam. \n\n\nPlease contact your Administrator!.");
			caLoggerAssign.info(Delete_MCQ_Exam, "deleteOnlineExamAssigned", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String onlineExamAssignedListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<OnlineExamAssigned> onlineExamAssignedList = adminBo.getOnlineExamAssignedListByJson(jdtParam);
		List<OnlineExamAssigned> onlineExamAssignedListAll = adminBo.getOnlineExamAssignedListByJsonAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (OnlineExamAssigned oeExamAssign : onlineExamAssignedList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(oeExamAssign.getOnlineExam().getOeExamName());
			newArray.add(oeExamAssign.getUsers().getUsUserName());
			newArray.add(oeExamAssign.getUsers().getUsUserID());
			newArray.add(oeExamAssign.getOnlineExam().getOeExamDate());
			newArray.add(oeExamAssign.getOeAssignedExamDate());
			newArray.add(oeExamAssign.getCreatedBy());
			newArray.add(String.valueOf(oeExamAssign.getCreatedDate()));
			newArray.add(String.valueOf(oeExamAssign.isStatus()));
			newArray.add(oeExamAssign.getOeExamAutoId() + "");
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(onlineExamAssignedListAll.size());
		dataTableObject.setiTotalRecords(onlineExamAssignedListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();

		return EPage.Success.name();
	}

}