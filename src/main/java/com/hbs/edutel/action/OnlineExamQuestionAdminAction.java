package com.hbs.edutel.action;

import java.io.File;
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
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlineExamQuestionAnswerMapping;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineExamQuestionAdminAction extends OnlineExamQuestionActionData
{
	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLoggerAdmin		= new CustomAuditLogger(this.getClass());

	public OnlineExamQuestionAdminAction()
	{
		super();
	}

	public OnlineExamQuestionAdminAction(List<OnlineExamQuestion> onlineExamQuestionList, String usEmployeeId, String oeSubject, String oeChapter, String oeSchoolType, String overallSubjects)
	{
		super(onlineExamQuestionList, usEmployeeId, oeSubject, oeChapter, oeSchoolType, overallSubjects);
	}

	public String blockUnlockExamQuestion() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		String oeExamQuestionId = request.getParameter("oeExamQuestionId");
		String status = request.getParameter("status");
		if (CommonValidator.isNotNullNotEmpty(status) == false)
			status = "true";

		OnlineExamQuestion blockQuestion = adminBo.blockUnlockExamQuestion(oeExamQuestionId, status, sessionUser.getUsEmployeeId());
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();

		if (CommonValidator.isNotNullNotEmpty(blockQuestion))
		{
			if (CommonValidator.isEqual(status, "false"))
				jsonObj.put("value", "Exam Question: " + blockQuestion.getOnlineExam().getOeExamName() + " has been blocked successfully");
			else
				jsonObj.put("value", "Exam Question: " + blockQuestion.getOnlineExam().getOeExamName() + " has been unblocked successfully");
			caLoggerAdmin.info(Update_MCQ_Exam, "blockUnlockExamQuestion", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Unable to block the selected Exam Question. \n\n\nPlease contact your Administrator!.");
			caLoggerAdmin.info(Update_MCQ_Exam, "blockUnlockExamQuestion", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}

		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String createOnlineExamQuestion()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			if (adminBo.createOnlineExamQuestion(this))
			{
				caLoggerAdmin.info(Create_MCQ_Exam, "createOnlineExamQuestion", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

				addActionError("Online Exam Question has been created Successfully.");
			}
			else
			{
				caLoggerAdmin.error(Create_MCQ_Exam, "createOnlineExamQuestion", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("Online Exam Question creation Failed.");
			}

			onlineExamQuestion = new OnlineExamQuestion();
			uploadQuestionFile = new ArrayList<File>(0);
			uploadQuestionFileFileName = new ArrayList<String>(0);
			uploadExplanationFile = new ArrayList<File>(0);
			uploadExplanationFileFileName = new ArrayList<String>(0);
			oeAnswerText = new ArrayList<String>(0);
			uploadFiles = new ArrayList<File>();
			uploadFilesFileName = new ArrayList<String>();
			onlineExamQuestionAnswerArray = null;

			return EPage.Success.name();
		}
		caLoggerAdmin.error(Access_Denied_MCQ_Exam, "createOnlineExamQuestion", EPage.Failure.name(), this.getClass().getName(), null);
		return EPage.AccessDenied.name();
	}

	public String createOnlinePreExamQuestion()
	{
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("p")) == false)
		{
			clearErrors();
			clearErrorsAndMessages();
		}
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.oeExamId = request.getParameter("oeExamId");

			List<OnlineExam> onlineExamsList = adminBo.getOnlineExamListByJson(jdtParam);
			if (CommonValidator.isListFirstNotEmpty(onlineExamsList))
			{
				onlineExamQuestion = new OnlineExamQuestion();
				onlineExam = onlineExamsList.iterator().next();
				uploadQuestionFile = new ArrayList<File>(0);
				uploadQuestionFileFileName = new ArrayList<String>(0);
				uploadExplanationFile = new ArrayList<File>(0);
				uploadExplanationFileFileName = new ArrayList<String>(0);
				oeAnswerText = new ArrayList<String>(0);
				uploadFiles = new ArrayList<File>();
				uploadFilesFileName = new ArrayList<String>();
				onlineExamQuestionAnswerArray = null;

			}

			return EPage.Success.name();
		}
		caLoggerAdmin.error(Access_Denied_MCQ_Exam, "createOnlinePreExamQuestion", EPage.Failure.name(), this.getClass().getName(), null);
		return EPage.AccessDenied.name();

	}

	public String deleteOnlineExamQuestion() throws IOException
	{
		String oeExamQuestionId = request.getParameter("oeExamQuestionId");
		OnlineExamQuestion delBk = adminBo.deleteOnlineExamQuestion(oeExamQuestionId);
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();

		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (CommonValidator.isNotNullNotEmpty(delBk))
		{
			jsonObj.put("value", "Exam Question: " + delBk.getOeQuestion() + " has been deleted successfully");
			caLoggerAdmin.info(Delete_MCQ_Exam, "deleteOnlineExamQuestion", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		else
		{
			jsonObj.put("value", "Unable to delete the selected Exam Question. \n\n\nPlease contact your Administrator!.");
			caLoggerAdmin.info(Delete_MCQ_Exam, "deleteOnlineExamQuestion", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}

		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String onlineExamQuestionListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		String oeExamId = request.getParameter("oeExamId");

		if (CommonValidator.isNotNullNotEmpty(oeExamId))
		{
			jdtParam.oeExamId = oeExamId;
		}

		List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineExamQuestionListByJson(jdtParam);
		List<OnlineExamQuestion> onlineExamQuestionsListAll = adminBo.getOnlineExamQuestionListByJsonAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (OnlineExamQuestion oExamQuestion : onlineExamQuestionsList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(oExamQuestion.getOnlineExam().getOeExamName());
			newArray.add(oExamQuestion.getOeQuestion());
			newArray.add(oExamQuestion.getUploadFileName());
			newArray.add(oExamQuestion.getMatchedAnswer());
			newArray.add(oExamQuestion.getCreatedBy());
			newArray.add(oExamQuestion.getCreatedDate() + "");
			newArray.add(oExamQuestion.isStatus() + "");
			newArray.add(oExamQuestion.getOeQuestionId());
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(onlineExamQuestionsListAll.size());
		dataTableObject.setiTotalRecords(onlineExamQuestionsListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();

		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		caLoggerAdmin.info(Search_MCQ_Exam, "onlineExamQuestionListByJson", oeExamId, this.getClass().getName(), sessionUser.getUsEmployeeId());
		return EPage.Success.name();
	}

	public String viewImage()
	{
		String oeExamQuestionId = request.getParameter("oeExamQuestionId");
		List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineExamQuestionListByJson(new JQueryDataTableParam(oeExamQuestionId));
		OnlineExamQuestion _OEQ = onlineExamQuestionsList.iterator().next();
		OnlineExamQuestionAnswerMapping _OEQA = _OEQ.getOeAnswers().iterator().next();
		ImageDataVO imVo = new ImageDataVO();
		imVo.request = request;
		imVo.response = response;
		imVo.uploadFileFolderURL = _OEQA.getUploadFileFolderURL();
		imVo.uploadFileName = _OEQA.getUploadFileName();
		imVo.isClientDownload = true;
		try
		{
			downloadFileUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);
			if (CommonValidator.isNotNullNotEmpty(downloadFileUrl) && downloadFileUrl.indexOf("/content/") > 0)
				downloadFileUrl = downloadFileUrl.substring(downloadFileUrl.indexOf("/content/"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return EPage.Success.name();
	}

}