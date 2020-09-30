package com.hbs.edutel.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlineExamQuestionAnswerMapping;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.hbs.edutel.model.School;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineExamQuestionAction extends OnlineExamQuestionMCQAction
{
	private static final long	serialVersionUID	= 7410435704041391982L;
	CustomAuditLogger			caLogger			= new CustomAuditLogger(this.getClass());

	public String deleteOnlineExamAnswer()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			OnlineTestSeriesExamQuestionAnswerMapping delTestSeriesAnswer = adminBo.deleteOnlineExamAnswer(this);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(delTestSeriesAnswer))
			{
				// caLogger.info(Delete_EBook, "deleteOnlineExamAnswer", EPage.Success.name(),
				// this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Answer : " + delTestSeriesAnswer.getUploadFileName() + " has been deleted successfully");
			}
			else
			{
				// caLogger.error(Delete_EBook, "deleteOnlineEBook", EPage.Failure.name(),
				// this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete the Answer. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			try
			{
				response.getWriter().write(String.valueOf(jsonArr));
				response.getWriter().close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String downloadTestSeriesAnswerByAjax()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		String downloadBy = request.getParameter("downloadBy");
		if (sessionUser != null)
		{
			try
			{
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				jdtParam.oeQuestionId = request.getParameter("oeQuestionId");
				jdtParam.oeExamTestSeriesAutoId = request.getParameter("oeTestSeriesAutoId");
				jdtParam.uploadFileName = request.getParameter("oeFileName");

				OnlineTestSeriesExamQuestionAnswerMapping oeQAnswer = adminBo.getOnlineTestSeriesQuestionAnswer(jdtParam);
				if (CommonValidator.isNotNullNotEmpty(oeQAnswer))
				{
					ImageDataVO imVo = new ImageDataVO();
					imVo.request = request;
					imVo.response = response;
					imVo.uploadFileFolderURL = oeQAnswer.getUploadFileFolderURL();
					imVo.uploadFileName = oeQAnswer.getUploadFileName();
					imVo.isClientDownload = true;

					downloadFileUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);
					if (CommonValidator.isNotNullNotEmpty(downloadFileUrl) && downloadFileUrl.indexOf("/content/") > 0)
						downloadFileUrl = downloadFileUrl.substring(downloadFileUrl.indexOf("/content/"));

					if (CommonValidator.isNotNullNotEmpty(downloadBy) && CommonValidator.isEqual(downloadBy, EPage.Student.name()))
					{
						if (oeQAnswer.isOeValidatedAnswer() && oeQAnswer.isDownloaded() == false)
						{
							if (adminBo.updateDownloadStatusForTestSeriesAnswerMapping(oeQAnswer) == false)
							{
								return EPage.Student.name();
							}
						}
					}
					caLogger.info(Download_TestSeries_Exam_Answer, "downloadTestSeriesAnswerByAjax", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
					return EPage.Success.name();
				}
			}
			catch (Exception excep)
			{
				caLogger.error(Download_TestSeries_Exam_Answer, "downloadEBookByAjax", EPage.Failure.name() + " : " + excep.getMessage(), this.getClass().getName(), null);
			}

		}
		if (CommonValidator.isNotNullNotEmpty(downloadBy) && CommonValidator.isEqual(downloadBy, EPage.Student.name()))
		{
			return EPage.Student.name();
		}
		else
		{
			return EPage.Failure.name();

		}
	}

	public String onlineTestSeriesExamAnswerListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);

		String oeExamQuestionId = request.getParameter("oeExamQuestionId");
		String oeValidate = request.getParameter("oeValidate");

		if (CommonValidator.isNotNullNotEmpty(oeExamQuestionId))
		{
			jdtParam.sSearch = oeExamQuestionId;
			jdtParam.oeValidate = oeValidate;
		}
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineTestSeriesExamQuestionListByJson(jdtParam);
		List<OnlineExamQuestion> onlineExamQuestionsListAll = adminBo.getOnlineTestSeriesExamQuestionListByJsonAll(jdtParam);

		if (CommonValidator.isListFirstNotEmpty(onlineExamQuestionsList))
		{
			Set<OnlineTestSeriesExamQuestionAnswerMapping> oTestSeriesAnsList = onlineExamQuestionsList.iterator().next().getOeTestSeriesAnswers();

			List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);
			int idx = 0;
			for (OnlineTestSeriesExamQuestionAnswerMapping oTestSeriesAns : oTestSeriesAnsList)
			{
				if (CommonValidator.isEqual(oeValidate, String.valueOf(oTestSeriesAns.isOeValidatedAnswer())))
				{
					if (CommonValidator.isEqual(oeValidate, "false") && CommonValidator.isNotNullNotEmpty(oTestSeriesAns.getModifiedBy()))
						continue;

					ArrayList<String> newArray = new ArrayList<String>(0);
					newArray.add("row_" + idx++);
					newArray.add(oTestSeriesAns.getUploadFileName());
					newArray.add(oTestSeriesAns.getOeTestSeriesAnswerVIA());
					if (CommonValidator.isNotNullNotEmpty(oTestSeriesAns.getCreatedBy()))
					{
						user = adminBo.getUserById(new JQueryDataTableParam(oTestSeriesAns.getCreatedBy()));
						if (CommonValidator.isNotNullNotEmpty(user))
						{
							newArray.add(CommonUtil.initCapsName(user.getUsUserName()));
						}
						else
						{
							newArray.add("--");
						}
					}

					newArray.add(oTestSeriesAns.getCreatedDate() + "");
					if (CommonValidator.isEqual(oeValidate, "false"))
					{
						School school = adminBo.getSchoolById(new JQueryDataTableParam(user.getUsSchoolId()));
						if (CommonValidator.isNotNullNotEmpty(school))
						{
							newArray.add(CommonUtil.initCapsName(school.getScSchoolName()));
						}
						else
						{
							newArray.add("--");
						}
					}
					if (CommonValidator.isNotNullNotEmpty(oTestSeriesAns.getModifiedBy()))
						newArray.add(oTestSeriesAns.getModifiedBy());
					else
						newArray.add("--");

					if (CommonValidator.isNotNullNotEmpty(oTestSeriesAns.getModifiedDate()))
						newArray.add(oTestSeriesAns.getModifiedDate() + "");
					else
						newArray.add("--");
					newArray.add(oTestSeriesAns.getOeExamTestSeriesAutoId() + "");
					newArray.add(oTestSeriesAns.getCreatedBy());

					aaData.add(newArray);
				}

			}
			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(aaData);
			dataTableObject.setiTotalDisplayRecords(onlineExamQuestionsListAll.size());
			dataTableObject.setiTotalRecords(onlineExamQuestionsListAll.size());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(dataTableObject);
			response.getWriter().write(jsonString);
			response.getWriter().close();

			caLogger.info(Search_TestSeries_Exam, "onlineTestSeriesExamAnswerListByJson", jdtParam.sSearch, this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	public String onlineTestSeriesExamAnswerSearch()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			oeQuestionId = request.getParameter("oeExamQuestionId");

			jdtParam.oeQuestionId = oeQuestionId;

			List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineTestSeriesExamQuestionListByJson(jdtParam);
			List<OnlineTestSeriesExamQuestionAnswerMapping> onlineExamQuestionsAnswersList = new ArrayList<OnlineTestSeriesExamQuestionAnswerMapping>();
			if (CommonValidator.isListFirstNotEmpty(onlineExamQuestionsList))
			{
				onlineExamQuestion = onlineExamQuestionsList.iterator().next();

				if (onlineExamQuestion.getOeTestSeriesAnswers().isEmpty() == false)
				{
					for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesAnswer : onlineExamQuestion.getOeTestSeriesAnswers())
					{
						if ((oeTestSeriesAnswer.isOeValidatedAnswer() == false && oeTestSeriesAnswer.isDownloaded() == false && CommonValidator.isEqual(oeTestSeriesAnswer.getOeTestSeriesAnswerVIA(),
								"Online"))
								|| oeTestSeriesAnswer.isOeValidatedAnswer() == false
								&& oeTestSeriesAnswer.isDownloaded()
								&& CommonValidator.isEqual(oeTestSeriesAnswer.getOeTestSeriesAnswerVIA(), "CourierOrPost")
								&& CommonValidator.isNotNullNotEmpty(oeTestSeriesAnswer.getModifiedBy()) == false)
						{
							onlineExamQuestionsAnswersList.add(oeTestSeriesAnswer);
						}

					}
				}
			}
			answerFileNameList.clear();
			if (CommonValidator.isListFirstNotEmpty(onlineExamQuestionsAnswersList))
			{
				// answerFileNameList =
				// CommonUtil.getFileNameLabelValueList(onlineExamQuestionsAnswersList);

				for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesExamQuestionAns : onlineExamQuestionsAnswersList)
				{
					if (CommonValidator.isNotNullNotEmpty(oeTestSeriesExamQuestionAns.getUploadFileName()))
					{
						answerFileNameList.add(new LabelValueBean(oeTestSeriesExamQuestionAns.getUploadFileName(), String.valueOf(oeTestSeriesExamQuestionAns.getOeExamTestSeriesAutoId())));
					}
					else
					{
						user = adminBo.getUserById(new JQueryDataTableParam(oeTestSeriesExamQuestionAns.getCreatedBy()));
						if (CommonValidator.isNotNullNotEmpty(user))
						{
							answerFileNameList.add(new LabelValueBean("Courier By " + user.getUsUserName(), String.valueOf(oeTestSeriesExamQuestionAns.getOeExamTestSeriesAutoId())));
						}
					}
				}
			}

			oeTestSeriesAnswerVIA = "Online";

			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String onlineTestSeriesExamQuestionListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineTestSeriesExamQuestionListByJson(jdtParam);
		List<OnlineExamQuestion> onlineExamQuestionsListAll = adminBo.getOnlineTestSeriesExamQuestionListByJsonAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (OnlineExamQuestion oExamQuestion : onlineExamQuestionsList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);

			oExamQuestion.getTestSeriesAnswerDownloaededStatus();
			newArray.add(oExamQuestion.isTestSeriesAnsStatus() + ""); // 0
			newArray.add(oExamQuestion.getOnlineExam().getOeExamName()); // 1
			newArray.add(oExamQuestion.getOeQuestion()); // 2
			newArray.add(oExamQuestion.getOeAnswers().iterator().next().getUploadFileName()); // 3
			newArray.add(oExamQuestion.getCreatedBy()); // 4
			newArray.add(oExamQuestion.getCreatedDate() + ""); // 5
			newArray.add(oExamQuestion.isStatus() + ""); // 6
			newArray.add(oExamQuestion.getOeQuestionId()); // 7
			newArray.add(oExamQuestion.isTestSeriesAnsValidateStatus() + ""); // 8

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
		caLogger.info(Search_TestSeries_Exam, "onlineTestSeriesExamQuestionListByJson", jdtParam.sSearch, this.getClass().getName(), sessionUser.getUsEmployeeId());
		return EPage.Success.name();
	}

	public String onlineTestSeriesExamQuestionSearch()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String performOnlineTestSeriesExam() throws Exception
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			String oeExamId = request.getParameter("oeExamId");

			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.oeExamId = oeExamId;
			List<OnlineExam> onlineExamList = adminBo.getOnlineExamListByJson(jdtParam);

			onlineExam = onlineExamList.iterator().next();
			if (CommonValidator.isNotNullNotEmpty(onlineExam))
			{
				onlineExam.checkTimeReached();
			}

			onlineExamQuestionList = adminBo.getOnlineExamQuestionListByJson(new JQueryDataTableParam(oeExamId)); // sSearch

			onlineExamQuestion = new OnlineExamQuestion(); // Reinitialize
			if (CommonValidator.isListFirstNotEmpty(onlineExamQuestionList))
			{
				onlineExamQuestion = onlineExamQuestionList.iterator().next();
			}

			onlineTestSeriesExamAnswer = new OnlineTestSeriesExamQuestionAnswerMapping();// Reinitialize
			onlineTestSeriesExamAnswerValidated = new OnlineTestSeriesExamQuestionAnswerMapping();// Reinitialize
			List<OnlineTestSeriesExamQuestionAnswerMapping> oeTestSeriesAnswers = adminBo.getOnlineTestSeriesQuestionAnswerList(this);

			if (CommonValidator.isListFirstNotEmpty(oeTestSeriesAnswers) == false)
			{
				setIsAnswered(String.valueOf(false));
				oeTestSeriesAnswerVIA = "Online";
			}
			else
			{
				for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeries : oeTestSeriesAnswers)
				{
					if (CommonValidator.isEqual(oeTestSeries.getCreatedBy(), usEmployeeId))
					{
						setIsAnswered(String.valueOf(true));
						if (oeTestSeries.isOeValidatedAnswer())
						{
							onlineTestSeriesExamAnswerValidated = oeTestSeries;
							if (CommonValidator.isNotNullNotEmpty(oeTestSeries.getUploadFileFolderURL()))
							{
								// Create Validated Answer file URL
								ImageDataVO imVo = new ImageDataVO();
								imVo.request = request;
								imVo.response = response;
								imVo.uploadFileFolderURL = oeTestSeries.getUploadFileFolderURL();
								imVo.uploadFileName = oeTestSeries.getUploadFileName();
								imVo.isClientDownload = false;
								oeTestSeries.setOeAnswerImageURL(imageDownload.downloadImageFromRepositoryToSessionFolder(imVo));
								oeTestSeries.getUploadFileScreenShot(); // Just a call to
																		// initialize
																		// screen shot image.
								downloadValidatedAnswerUrl = oeTestSeries.getOeAnswerImageURL();
							}
						}
						else
						{
							oeTestSeriesAnswerVIA = oeTestSeries.getOeTestSeriesAnswerVIA();
							onlineTestSeriesExamAnswer = oeTestSeries;
							onlineTestSeriesExamAnswer.setCreatedBy(sessionUser.getUsUserName());
						}
					}
				}
			}

			// Create Question URL
			for (OnlineExamQuestionAnswerMapping oeQA : onlineExamQuestion.getOeAnswers())
			{
				if (CommonValidator.isNotNullNotEmpty(oeQA.getUploadFileFolderURL()))
				{
					ImageDataVO imageDataVO = new ImageDataVO();
					imageDataVO.request = request;
					imageDataVO.uploadFileFolderURL = oeQA.getUploadFileFolderURL();
					imageDataVO.uploadFileName = oeQA.getUploadFileName();
					oeQA.setOeAnswerImageURL(imageDownload.downloadImageFromRepositoryToSessionFolder(imageDataVO));
					oeQA.getUploadFileScreenShot(); // Just a call to initialize screen shot image.
					downloadFileUrl = oeQA.getOeAnswerImageURL();
					break;
				}
			}

			caLogger.infoMCQ(Perform_TestSeries_Exam, "performOnlineTestSeriesExam", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String preUploadValidatedAnswer()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.oeQuestionId = request.getParameter("oeQuestionId");
			jdtParam.usEmployeeId = request.getParameter("usEmployeeId");
			// Set as true to Check already Answered i.e get Validated Answer Row
			jdtParam.oeValidate = "true";

			onlineTestSeriesExamAnswer = adminBo.getOnlineTestSeriesQuestionAnswer(jdtParam);

			if (CommonValidator.isNotNullNotEmpty(onlineTestSeriesExamAnswer) == false)
			{
				// Set as false to get invalidated Answer Row
				jdtParam.oeValidate = "false";
				jdtParam.oeExamTestSeriesAutoId = request.getParameter("oeTestSeriesAutoId");
				String oeFilename = request.getParameter("oeFileName");
				if (CommonValidator.isNotEqual(oeFilename, "null"))
					jdtParam.uploadFileName = request.getParameter("oeFileName");
				onlineTestSeriesExamAnswer = adminBo.getOnlineTestSeriesQuestionAnswer(jdtParam);
				setIsAnswered(String.valueOf(false));
				oeTestSeriesAnswerVIA = "Online";
			}
			else
			{
				setIsAnswered(String.valueOf(true));
				oeTestSeriesAnswerVIA = onlineTestSeriesExamAnswer.getOeTestSeriesAnswerVIA();
			}

			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String submitOnlineTestSeriesExamAnswer() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			if (CommonValidator.isNotNullNotEmpty(onlineExam))
				System.out.println("Online Exam : " + onlineExam.getOeExamName());
			if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion))
			{
				System.out.println("Online Exam Question : " + onlineExamQuestion.getOeQuestion());
				System.out.println("Online Exam Name : " + onlineExamQuestion.getOnlineExam().getOeExamName());
			}
			user = sessionUser;
			if (adminBo.createOnlineTestSeriesExamAnswer(this))
			{
				caLogger.info(Perform_TestSeries_Exam, "submitOnlineTestSeriesExamAnswer", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			else
			{
				caLogger.info(Perform_TestSeries_Exam, "submitOnlineTestSeriesExamAnswer", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			if (sessionUser.isStudent())
				return EPage.Student.name();
			else
				return EPage.Employee.name();
		}

		return EPage.AccessDenied.name();
	}

	public String uploadValidatedAnswer()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			onlineExamQuestion = onlineTestSeriesExamAnswer.getOnlineExamQuestion();
			onlineTestSeriesExamAnswer.setOeValidatedAnswer(true);
			isAnswered = "true";
			if (adminBo.createOnlineTestSeriesExamAnswer(this))
			{

				caLogger.info(Result_TestSeries_Exam, "uploadValidatedAnswer", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			else
			{
				caLogger.info(Result_TestSeries_Exam, "uploadValidatedAnswer", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String downloadTestSeriesAnswersByZip()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			try
			{

				JQueryDataTableParam jdtParam = new JQueryDataTableParam();

				String oeExamQuestionId = request.getParameter("oeExamQuestionId");
				String oeValidate = request.getParameter("oeValidate");
				String key = request.getParameter("key");

				if (CommonValidator.isNotNullNotEmpty(oeExamQuestionId))
				{
					jdtParam.sSearch = oeExamQuestionId;
					jdtParam.oeValidate = oeValidate;
				}
				List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineTestSeriesExamQuestionListByJsonAll(jdtParam);
				List<String> fileNameList = new ArrayList<String>();

				for (OnlineExamQuestion oeQuestion : onlineExamQuestionsList)
				{
					if (oeQuestion.getOeTestSeriesAnswers().isEmpty() == false)
					{
						for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesAnswer : oeQuestion.getOeTestSeriesAnswers())
						{

							if (CommonValidator.isNotNullNotEmpty(key) && CommonValidator.isEqual(key, "AvailAnswers"))
							{
								if (oeTestSeriesAnswer.isOeValidatedAnswer() == Boolean.valueOf(oeValidate) && oeTestSeriesAnswer.isDownloaded() == false
										&& CommonValidator.isEqual(oeTestSeriesAnswer.getOeTestSeriesAnswerVIA(), "Online"))
								{
									if (CommonValidator.isNotNullNotEmpty(oeTestSeriesAnswer))
									{
										ImageDataVO imVo = new ImageDataVO();
										imVo.request = request;
										imVo.response = response;
										imVo.uploadFileFolderURL = oeTestSeriesAnswer.getUploadFileFolderURL();
										imVo.uploadFileName = oeTestSeriesAnswer.getUploadFileName();
										imVo.isClientDownload = true;
										fileNameList.add(oeTestSeriesAnswer.getUploadFileName());
										downloadFileUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);

									}
								}
							}
							else if (CommonValidator.isNotNullNotEmpty(key) && CommonValidator.isEqual(key, "AllAnswers"))
							{
								if (oeTestSeriesAnswer.isOeValidatedAnswer() == Boolean.valueOf(oeValidate) && CommonValidator.isEqual(oeTestSeriesAnswer.getOeTestSeriesAnswerVIA(), "Online"))
								{
									if (CommonValidator.isNotNullNotEmpty(oeTestSeriesAnswer))
									{
										ImageDataVO imVo = new ImageDataVO();
										imVo.request = request;
										imVo.response = response;
										imVo.uploadFileFolderURL = oeTestSeriesAnswer.getUploadFileFolderURL();
										imVo.uploadFileName = oeTestSeriesAnswer.getUploadFileName();
										imVo.isClientDownload = true;
										fileNameList.add(oeTestSeriesAnswer.getUploadFileName());
										downloadFileUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);

									}
								}

							}

						}
					}

				}
				String zipFileName = oeExamQuestionId.trim() + "_" + key.trim() + ".ZIP";
				String zipDownload = imageDownload.downloadZipFromRepository(fileNameList, zipFileName, getRequest(), response);
				if (CommonValidator.isEqual(zipDownload, EPage.Success.name()))
				{
					caLogger.info(Download_TestSeries_Exam_Answer, "downloadTestSeriesAnswersByZip", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
					return EPage.Success.name();
				}
			}
			catch (Exception excep)
			{
				caLogger.error(Download_TestSeries_Exam_Answer, "downloadTestSeriesAnswersByZip", EPage.Failure.name() + " : " + excep.getMessage(), this.getClass().getName(), null);
			}

		}
		return EPage.Failure.name();

	}

	public String getAnsweredStudentInfoByAjax() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			String oeAnswerId = request.getParameter("oeAnswerId");
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.sSearch = oeAnswerId;
			onlineTestSeriesExamAnswer = adminBo.getTestSeriesExamAnswerById(jdtParam);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(onlineTestSeriesExamAnswer))
			{
				JQueryDataTableParam jdtUserParam = new JQueryDataTableParam();
				jdtUserParam.sSearch = onlineTestSeriesExamAnswer.getCreatedBy();
				user = adminBo.getUserById(jdtUserParam);
				jsonObj.put("uploadDate", String.valueOf(onlineTestSeriesExamAnswer.getCreatedDate()));
				jsonObj.put("stuName", user.getUsUserName());
				jsonObj.put("empId", user.getUsEmployeeId());
				caLogger.info(Search_TestSeries_Exam, "getAnsweredStudentInfo", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			else
			{
				jsonObj.put("value", "Exam");
				caLogger.info(Search_TestSeries_Exam, "getAnsweredStudentInfo", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();
			return EPage.Success.name();
		}

		return EPage.Failure.name();
	}

	public String validatedAnswersBulkUpload() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			System.out.println("Test :" + answerId.size());
			if (adminBo.validatedAnswersBulkUpload(this))
			{
				addActionError("Validated Answers has been saved Successfully.");
			}
			else
			{
				addActionError("Validated Answers Upload Failed.");
			}
			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}
}
