package com.hbs.edutel.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUserLog;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.logger.IAuditLogging;
import com.hbs.edutel.model.InformationAlerts;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamAssigned;
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.hbs.edutel.model.School;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DashBoardAction extends DashBoardActionData
{
	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String getDashBoardInformation()
	{
		user = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		try
		{
			if (CommonValidator.isNotNullNotEmpty(user))
			{
				String msgOption = request.getParameter("p");

				ImageDataVO imVo = new ImageDataVO();
				imVo.request = request;
				imVo.response = response;
				imVo.uploadFileFolderURL = user.getUploadFileFolderURL();
				imVo.uploadFileName = user.getUploadFileName();

				usUserImageUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);
				request.getSession().setAttribute(ESession.UserImagePath.getAttribute(), usUserImageUrl);
				request.getSession().setAttribute(ESession.UserJoinedDate.getAttribute(), user.getUsDateOfJoin());

				List<IUserLog> userLogs = new ArrayList<IUserLog>(0);
				userLogs.addAll(user.getUserLogs());
				if (CommonValidator.isListFirstNotEmpty(userLogs))
					usLastLoginTime = userLogs.iterator().next().getUlUserLoginTime();

				usEmployeeId = user.getUsEmployeeId();
				userActivityList = adminBo.getUserActivityList(new AuditLoggingUDParam(usEmployeeId, 0, 10));

				String lastExamName = "";

				if (user.isStudent())
				{
					sessionUser = user;
					onlineExamList = adminBo.getOnlineExamList(this);

					for (OnlineExam oe : onlineExamList)
					{
						if (CommonValidator.isNotNullNotEmpty(oe))
						{
							oe.checkTimeReached();
							if (oe.isTimeReached() && CommonValidator.isNotNullNotEmpty(lastExamName) == false)
							{
								lastExamName = oe.getOeExamName();
							}
						}
					}

					List<OnlineExamAssigned> oeAssignedList = adminBo.getOnlineExamAssignedList(this);
					if (CommonValidator.isListFirstNotEmpty(oeAssignedList))
					{
						for (OnlineExamAssigned oeAssigned : oeAssignedList)
						{
							if (CommonValidator.isNotNullNotEmpty(oeAssigned))
							{
								oeAssigned.checkTimeReached();
								onlineExamList.add(0, oeAssigned.getOnlineExam());
							}
						}
					}

					// For Getting only Practice Exam
					onlineExamFlag = false;
					onlinePractiseExamList = adminBo.getOnlinePractiseExamList(this);
					onlinePractiseExamResultList = adminBo.getPracticeExamResultList(new AuditLoggingUDParam(usEmployeeId, 0, 20));

					getStudentMarks(msgOption);

					getInformationAlertToDisplay(msgOption);
					getMarqueeMessage();
					getTopperClubList(lastExamName);

					getStudentChartData();

					getOnlineTestSeriesExamValidatedAnswersNotifications(msgOption);
				}
				else if (user.isEmployee())
				{
					noOfUsersOnline = adminBo.getLoginUsersCount();
					getOnlineTestSeriesExamQuestionAnswersNotifications();
				}

				return EPage.Success.name();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_StartEntry, "getDashBoardInformation", EPage.Failure.name(), excep.getMessage(), "");
		}
		return EPage.AccessDenied.name();
	}

	private void getInformationAlertToDisplay(String msgOption)
	{
		informationAlertList = adminBo.getInfoAlertListAll(new JQueryDataTableParam());
		if (CommonValidator.isListFirstNotEmpty(informationAlertList))
		{
			for (InformationAlerts infoAlert : informationAlertList)
			{
				if (CommonValidator.isEqual(infoAlert.getIaInformationType(), EPage.Alert.name()))
				{
					informationAlerts = infoAlert;
					if (msgOption == null || msgOption.equals(""))
					{
						informationAlerts.setIaStatus(false);
					}
					break;
				}
			}
		}
	}

	private void getMarqueeMessage()
	{
		if (CommonValidator.isListFirstNotEmpty(informationAlertList))
		{
			for (InformationAlerts infoAlert : informationAlertList)
			{
				if (CommonValidator.isEqual(infoAlert.getIaInformationType().trim(), EPage.Marquee.name()))
				{
					marqueeMsg = infoAlert.getIaInformationMsg();
					break;
				}
			}
		}
		if (CommonValidator.isNotNullNotEmpty(marqueeMsg) == false)
		{
			marqueeMsg = "Welcome to EduTel Academy..!";
		}
	}

	private void getStudentChartData()
	{
		if (CommonValidator.isListFirstNotEmpty(onlinePractiseExamResultList))
		{
			JSONArray jsArr = new JSONArray();
			JSONObject initJsObject = new JSONObject();
			initJsObject.accumulate("Subject", "No-Data");
			initJsObject.accumulate("Scored", "0.000001");
			boolean added = false;
			List<String> subjectZero = new ArrayList<String>();
			for (IAuditLogging logStud : onlinePractiseExamResultList)
			{
				if (CommonValidator.isNotNullNotEmpty(logStud.getMessage2()) && logStud.getMessage2().indexOf("#") > 0)
				{
					added = true;
					String resultData[] = logStud.getMessage2().split("#");
					String resultDatum = resultData[3].equals("0") ? "0.0001" : resultData[3];

					if (subjectZero.contains(resultData[0] + resultDatum) == false)
					{
						JSONObject jsO = new JSONObject();
						jsO.accumulate("Subject", resultData[0]);
						jsO.accumulate("Scored", resultDatum);
						jsArr.add(jsO);
						if (resultData[3].equals("0"))
							subjectZero.add(resultData[0] + resultDatum);
					}
				}

			}

			if (added == false)
				jsArr.add(initJsObject);
			chartDivData = jsArr.toString();
		}
	}

	private void getTopperClubList(String lastExamName)
	{
		toppersClubList = adminBo.getToppersClubListByMarks(lastExamName);
		if (CommonValidator.isListFirstNotEmpty(toppersClubList))
		{
			ImageDataVO imVo = new ImageDataVO();
			for (int index = 0; index < toppersClubList.size(); index++)
			{
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				if (CommonValidator.isNotNullNotEmpty(toppersClubList.get(index).getCreatedBy()))
				{
					jdtParam.sSearch = toppersClubList.get(index).getCreatedBy();// EmployeeId
					IUsers topperUser = adminBo.getUserById(jdtParam);
					if (CommonValidator.isNotNullNotEmpty(toppersClubList.get(index).getTpSchoolName()) && CommonValidator.isNotNullNotEmpty(topperUser))// SchoolId
					{
						jdtParam.sSearch = toppersClubList.get(index).getTpSchoolName(); // SchoolId
						School school = adminBo.getSchoolById(jdtParam);
						toppersClubList.get(index).setTpSchoolName(school.getScSchoolName());// Id_To_Name
						imVo.request = request;
						imVo.response = response;
						imVo.uploadFileFolderURL = topperUser.getUploadFileFolderURL();
						imVo.uploadFileName = topperUser.getUploadFileName();
						try
						{
							toppersClubList.get(index).setUsUserImageUrl(imageDownload.downloadImageFromRepositoryToSessionFolder(imVo));
						}
						catch (Exception e)
						{
						}
					}
				}
			}
		}
	}

	private void getStudentMarks(String msgOption)
	{
		String[] activeMonths = { "JAN", "FEB", "MAR" };
		studentsMark = userBo.getStudentMarkByEmpId(user.getUsEmployeeId());

		SimpleDateFormat sdfCurrentMonth = new SimpleDateFormat("MMM");
		String currentMonth = sdfCurrentMonth.format(new Date());

		boolean monStatus = false;
		if (CommonValidator.isNotNullNotEmpty(studentsMark))
		{
			String[] yearArr = studentsMark.getUsBatch().split("_");
			int curYear = Calendar.getInstance().get(Calendar.YEAR);
			if (CommonValidator.isEqual(yearArr[1], String.valueOf(curYear)))
			{
				for (String month : activeMonths)
				{
					if (CommonValidator.isEqual(currentMonth, month))
					{
						monStatus = true;
					}
				}
			}
		}

		if (monStatus)
		{
			if (CommonValidator.isNotNullNotEmpty(msgOption))
			{
				if (CommonValidator.isNotNullNotEmpty(studentsMark.getUsHSCNo()) && CommonValidator.isNotNullNotEmpty(studentsMark.getUsSSorPUCMark()))
				{
					studentsMark.setStatus(true);
				}
				else
				{
					studentsMark.setStatus(false);
				}
			}
		}

	}

	public String studentMarkEnroll()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && sessionUser.isStudent())
		{
			user = sessionUser;
			ImageDataVO imageDataVO = new ImageDataVO();
			imageDataVO.subFolderStructure = user.getUsUsersType();
			saveAttachment(imageDataVO, user);

			if (adminBo.saveStudentMarkEnroll(this))
			{
				return EPage.Success.name();
			}
		}

		return EPage.Failure.name();

	}

	private void getOnlineTestSeriesExamQuestionAnswersNotifications()
	{
		List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineTestSeriesExamQuestionListByJson(new JQueryDataTableParam());
		onlineExamQuestionsNotificationList.clear();
		oeTestSeriesUnvalidatedAnswersList.clear();
		if (CommonValidator.isListFirstNotEmpty(onlineExamQuestionsList))
		{
			for (OnlineExamQuestion oeQuestion : onlineExamQuestionsList)
			{
				if (CommonValidator.isSetFirstNotEmpty(oeQuestion.getOeTestSeriesAnswers()))
				{
					for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesAnsMap : oeQuestion.getOeTestSeriesAnswers())
					{
						oeTestSeriesAnsMap.getTestSeriesAnswerDownloaededStatus();
						if (oeTestSeriesAnsMap.isTsAnsStatus() && oeTestSeriesAnsMap.isTsAnsValidatedStatus() == false)
						{
							if (CommonValidator.isNotNullNotEmpty(oeTestSeriesAnsMap.getUploadFileName()) == false)
							{
								oeTestSeriesAnsMap.setUploadFileName("By Courier");
							}
							oeTestSeriesUnvalidatedAnswersList.add(oeTestSeriesAnsMap);
						}

					}
				}

				oeQuestion.getTestSeriesAnswerDownloaededStatus();

				if (oeQuestion.isTestSeriesAnsStatus() && oeQuestion.isTestSeriesAnsValidateStatus() == false)
				{
					onlineExamQuestionsNotificationList.add(oeQuestion);
				}
			}
		}
	}

	private void getOnlineTestSeriesExamValidatedAnswersNotifications(String messageOption)
	{
		if (CommonValidator.isNotNullNotEmpty(messageOption) == false)
		{
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.usEmployeeId = sessionUser.getUsEmployeeId();
			oeTestSeriesValidatedAnswersNotificationList.clear();
			oeTestSeriesValidatedAnswersNotificationList = adminBo.getTestSeriesExamValidatedAnswersListByCreatedBy(jdtParam);
		}
	}

	public String exportUnvaliatedTestSeriesStudnetAnswers()
	{

		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			if (CommonValidator.isListFirstNotEmpty(oeTestSeriesUnvalidatedAnswersList))
			{
				CATQuestionsGeneratePDF.exportUnvalidatedTestSeriesAnsPDF(oeTestSeriesUnvalidatedAnswersList, this);
			}
			return EPage.Success.name();

		}
		return EPage.Failure.name();
	}

}
