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
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamAssigned;
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EChapter;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.factory.UsersFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineExamAction extends OnlineExamAssignedAction implements IOnlineExamAction
{
	private static final long	serialVersionUID	= -7318542890115565848L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String blockUnlockExam() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		String oeExamId = request.getParameter("oeExamId");
		String status = request.getParameter("status");
		if (CommonValidator.isNotNullNotEmpty(status) == false)
			status = "true";

		OnlineExam oe = adminBo.blockUnlockExam(oeExamId, status, sessionUser.getUsEmployeeId());
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();

		if (CommonValidator.isNotNullNotEmpty(oe))
		{
			if (CommonValidator.isEqual(status, "false"))
				jsonObj.put("value", "Exam : " + oe.getOeExamName() + " has been blocked successfully");
			else
				jsonObj.put("value", "Exam : " + oe.getOeExamName() + " has been unblocked successfully");

			caLogger.info(Update_MCQ_Exam, "blockUnlockExam", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Exam Question: " + oe.getOeExamName() + " blocking failed");
			caLogger.info(Update_MCQ_Exam, "blockUnlockExam", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}

		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String createOnlineExam()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			if (adminBo.createOnlineExam(this))
			{
				caLogger.info(Create_MCQ_Exam, "createOnlineExam", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

				addActionError("Online Exam has been created Successfully.");
			}
			else
			{
				caLogger.info(Create_MCQ_Exam, "createOnlineExam", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("Online Exam creation failed.");
			}
			chapterList = EChapter.getChaptersComboList();
			onlineExam = new OnlineExam();
			return generateSubjectsListComboValues();
		}

		return EPage.AccessDenied.name();
	}

	public String createOnlinePractiseExam()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			if (adminBo.createOnlinePractiseExam(this))
			{
				caLogger.info(Create_MCQ_Practice_Exam, "createOnlinePractiseExam", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			else
			{
				caLogger.info(Create_MCQ_Practice_Exam, "createOnlinePractiseExam", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			chapterList = EChapter.getChaptersComboList();
			return generateSubjectsListComboValues();
		}

		return EPage.AccessDenied.name();
	}

	public String createOnlinePreExam()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			chapterList = EChapter.getChaptersComboList();
			return generateSubjectsListComboValues();
		}
		return EPage.AccessDenied.name();

	}

	public String createOnlinePrePractiseExam()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			chapterList = EChapter.getChaptersComboList();
			return generateSubjectsListComboValues();
		}
		return EPage.AccessDenied.name();

	}

	public String deleteOnlineExam() throws IOException
	{
		String oeExamId = request.getParameter("oeExamId");
		OnlineExam delBk = adminBo.deleteOnlineExam(oeExamId);
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (CommonValidator.isNotNullNotEmpty(delBk))
		{
			jsonObj.put("value", "Exam : " + delBk.getOeExamName() + "\n\nChapter " + delBk.getOeChapter() + " has been deleted successfully");
			caLogger.info(Delete_MCQ_Exam, "deleteOnlineExam", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Unable to delete the selected Exam. \n\n\nPlease contact your Administrator!.");
			caLogger.info(Delete_MCQ_Exam, "deleteOnlineExam", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String deleteOnlineExamEntryForStudent() throws IOException
	{

		clearErrors();
		clearErrorsAndMessages();
		sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = request.getParameter("usEmployeeId");
			user = adminBo.getUserById(new JQueryDataTableParam(usEmployeeId));
			String trackedExamId = "";
			for (OnlineExam oeExam : adminBo.getOnlineExamList(this))
			{
				if (user.getUsGroupName().contains(oeExam.getOeSubject()))
				{
					oeExam.checkTimeReached();
					if (oeExam.isTimeReached() && !oeExam.isTimeElapsed())
					{
						trackedExamId += oeExam.getOeExamId() + COMMA_SPACE;
					}
				}
			}

			for (OnlineExamAssigned oeExamAssigned : adminBo.getOnlineExamAssignedList(this))
			{
				if (user.getUsGroupName().contains(oeExamAssigned.getOnlineExam().getOeSubject()))
				{
					oeExamAssigned.checkTimeReached();
					if (oeExamAssigned.getOnlineExam().isTimeReached() && !oeExamAssigned.getOnlineExam().isTimeElapsed())
					{
						trackedExamId += oeExamAssigned.getOnlineExam().getOeExamId() + COMMA_SPACE;
					}
				}
			}

			if (CommonValidator.isNotNullNotEmpty(trackedExamId))
			{
				trackedExamId = trackedExamId.substring(0, trackedExamId.lastIndexOf(COMMA_SPACE));
			}
			oeExamId = trackedExamId;

			List<OnlinePractiseExam> oePractiseExamList = adminBo.deleteOnlineExamPracticeEntry(this);

			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();

			if (CommonValidator.isListFirstNotEmpty(oePractiseExamList))
			{
				String examNameSubject = "";
				for (OnlinePractiseExam oePE : oePractiseExamList)
				{
					examNameSubject += "Exam Name : " + oePE.getOePractiseExamName() + "  -  Subject : " + oePE.getOeSubject();
				}
				jsonObj.put("value", examNameSubject + "\n\nhas been deleted successfully for the Student Name is \"" + user.getUsUserName() + "\" and Student User Id is \"" + user.getUsUserID()
						+ "\"");
				caLogger.info(Delete_MCQ_Practice_Exam, "deleteOnlineExamEntryForStudent", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			else
			{
				jsonObj.put(
						"value",
						"Unable to delete the selected Exam for the Student \""
								+ user.getUsUserName().toUpperCase()
								+ "\" whose User Id is \""
								+ user.getUsUserID().toUpperCase()
								+ "\"\n\n\nPlease contact your Administrator!.\n\nReason(s):\n\n1. Exam Time may be elasped for the Student.\n\n2. Exam may be Blocked/Inactive/Private View.\n\n3. No Exam avail for the Student Id.\n\n");
				caLogger.info(Delete_MCQ_Practice_Exam, "deleteOnlineExamEntryForStudent", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			// ReInitialize for Safety
			user = UsersFactory.getInstance().getUsersInstance();
			sessionUser = UsersFactory.getInstance().getUsersInstance();

			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();
		}

		return EPage.Success.name();
	}

	public String displayExamPublic() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		String oeExamId = request.getParameter("oeExamId");
		boolean displayPublic = Boolean.valueOf(request.getParameter("displayPublic"));

		OnlineExam oe = adminBo.displayExamPublic(oeExamId, displayPublic, sessionUser.getUsEmployeeId());
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();

		if (CommonValidator.isNotNullNotEmpty(oe))
		{
			if (CommonValidator.isEqual(request.getParameter("displayPublic"), "false"))
				jsonObj.put("value", "Exam - " + oe.getOeExamName() + " has been BLOCKED from view of Students");
			else
				jsonObj.put("value", "Exam - " + oe.getOeExamName() + " has been UN-BLOCKED to Students");

			caLogger.info(Update_MCQ_Exam, "displayExamPublic", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Exam Question: " + oe.getOeExamName() + " blocking failed");
			caLogger.info(Update_MCQ_Exam, "displayExamPublic", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}

		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String onlineExamListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<OnlineExam> onlineExamsList = adminBo.getOnlineExamListByJson(jdtParam);
		List<OnlineExam> onlineExamsListAll = adminBo.getOnlineExamListByJsonAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (OnlineExam oExam : onlineExamsList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(oExam.getOeExamName());
			newArray.add(oExam.getOeSubject());
			newArray.add(EChapter.getEChapter(oExam.getOeChapter()).name().replaceAll("_", " "));
			newArray.add(oExam.getOeSchoolType());
			newArray.add(oExam.getOeExamDate());
			newArray.add(oExam.getOeExamDuration() + " Hours");
			newArray.add(oExam.getCreatedBy());
			newArray.add(String.valueOf(oExam.getCreatedDate()));
			newArray.add(oExam.isDisplayPublic() + "");
			newArray.add(String.valueOf(oExam.isStatus()));
			newArray.add(oExam.getOeExamId());
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(onlineExamsListAll.size());
		dataTableObject.setiTotalRecords(onlineExamsListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String performOnlinePreMCQExam()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			chapterList = EChapter.getChaptersComboList();
			return generateSubjectsListComboValues();
		}
		return EPage.AccessDenied.name();

	}
}
