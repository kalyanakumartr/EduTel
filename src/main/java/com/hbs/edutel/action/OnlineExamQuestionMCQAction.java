package com.hbs.edutel.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.hbs.edutel.admin.action.PasswordEncrypt;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamAssigned;
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlineExamQuestionAnswerMapping;
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EExamType;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineExamQuestionMCQAction extends OnlineExamQuestionAdminAction
{
	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLoggerMCQ			= new CustomAuditLogger(this.getClass());

	public OnlineExamQuestionMCQAction()
	{
		super();
	}

	public OnlineExamQuestionMCQAction(List<OnlineExamQuestion> onlineExamQuestionList, String usEmployeeId, String oeSubject, String oeChapter, String oeSchoolType, String overallSubjects)
	{
		super(onlineExamQuestionList, usEmployeeId, oeSubject, oeChapter, oeSchoolType, overallSubjects);
	}

	public String executeOnlineExamQuestion() throws Exception
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();

			String questionId = request.getParameter("questionId");
			questionNo = request.getParameter("questionNo");
			isAnswered = request.getParameter("isAnswered");
			List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineExamQuestionListByJson(new JQueryDataTableParam(questionId));
			onlineExamQuestion = onlineExamQuestionsList.iterator().next();
			String onlineExamSessionFolder = PasswordEncrypt.encrypt(onlineExamQuestion.getOnlineExam().getOeExamId(), "SHA", "UTF-8").trim();

			for (OnlineExamQuestionAnswerMapping oeQA : onlineExamQuestion.getOeAnswers())
			{
				if (CommonValidator.isNotNullNotEmpty(oeQA.getUploadFileFolderURL()))
				{
					ImageDataVO imVO = new ImageDataVO();
					imVO.request = request;
					imVO.uploadFileFolderURL = oeQA.getUploadFileFolderURL();
					imVO.uploadFileName = oeQA.getUploadFileName();
					imVO.isOnlineAssessment = onlineExamQuestion.getOnlineExam().isOnlineAssessment();
					imVO.onlineExamSessionFolder = onlineExamSessionFolder;
					oeQA.setOeAnswerImageURL(imageDownload.downloadImageFromRepositoryToSessionFolder(imVO));

					if (CommonValidator.isNotNullNotEmpty(imVO.uploadFileName)
							&& (!imVO.uploadFileName.toLowerCase().endsWith("doc") || !imVO.uploadFileName.toLowerCase().endsWith("docx") || !imVO.uploadFileName.toLowerCase().endsWith("pdf")))
					{
						String repositorySourceFile = FileServer_Intermediate_Server_Path + SLASH + imVO.uploadFileFolderURL + SLASH + imVO.uploadFileName;
						File imgFile = new File(repositorySourceFile);
						if (imgFile.exists() && imgFile.isFile())
						{
							BufferedImage bimg = ImageIO.read(imgFile);
							int width = bimg.getWidth() > 300 ? 300 : bimg.getWidth();
							int height = bimg.getHeight() > 75 ? 75 : bimg.getHeight();
							oeQA.setImageWidth(width);
							oeQA.setImageHeight(height);
						}
					}
				}
			}

			if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion.getUploadFileFolderURL()))
			{
				ImageDataVO imVO = new ImageDataVO();
				imVO.request = request;
				imVO.uploadFileFolderURL = onlineExamQuestion.getUploadFileFolderURL();
				imVO.uploadFileName = onlineExamQuestion.getUploadFileName();
				imVO.isOnlineAssessment = onlineExamQuestion.getOnlineExam().isOnlineAssessment();
				imVO.onlineExamSessionFolder = onlineExamSessionFolder;
				onlineExamQuestion.setOeQuestionImageURL(imageDownload.downloadImageFromRepositoryToSessionFolder(imVO));

				if (CommonValidator.isNotNullNotEmpty(imVO.uploadFileName)
						&& (!imVO.uploadFileName.toLowerCase().endsWith("doc") || !imVO.uploadFileName.toLowerCase().endsWith("docx") || !imVO.uploadFileName.toLowerCase().endsWith("pdf")))
				{
					String repositorySourceFile = FileServer_Intermediate_Server_Path + SLASH + imVO.uploadFileFolderURL + SLASH + imVO.uploadFileName;
					BufferedImage bimg = ImageIO.read(new File(repositorySourceFile));
					int questionWidth = bimg.getWidth() > 650 ? 650 : bimg.getWidth();
					int questionHeight = bimg.getHeight() > 100 ? 100 : bimg.getHeight();
					onlineExamQuestion.setQuestionWidth(questionWidth);
					onlineExamQuestion.setQuestionHeight(questionHeight);
				}
			}

			if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion.getUploadExplanationFileFolderURL()))
			{
				ImageDataVO imVO = new ImageDataVO();
				imVO.request = request;
				imVO.uploadFileFolderURL = onlineExamQuestion.getUploadExplanationFileFolderURL();
				imVO.uploadFileName = onlineExamQuestion.getUploadExplanationFileName();
				imVO.isOnlineAssessment = onlineExamQuestion.getOnlineExam().isOnlineAssessment();
				imVO.onlineExamSessionFolder = onlineExamSessionFolder;
				onlineExamQuestion.setOeExplanationImageURL(imageDownload.downloadImageFromRepositoryToSessionFolder(imVO));

				if (CommonValidator.isNotNullNotEmpty(imVO.uploadFileName)
						&& (!imVO.uploadFileName.toLowerCase().endsWith("doc") || !imVO.uploadFileName.toLowerCase().endsWith("docx") || !imVO.uploadFileName.toLowerCase().endsWith("pdf")))
				{
					String repositorySourceFile = FileServer_Intermediate_Server_Path + SLASH + imVO.uploadFileFolderURL + SLASH + imVO.uploadFileName;
					BufferedImage bimg = ImageIO.read(new File(repositorySourceFile));
					int questionWidth = bimg.getWidth() > 500 ? 500 : bimg.getWidth();
					int questionHeight = bimg.getHeight() > 200 ? 200 : bimg.getHeight();
					onlineExamQuestion.setExplanationWidth(questionWidth);
					onlineExamQuestion.setExplanationHeight(questionHeight);
				}
			}
		}
		return EPage.Success.name();
	}

	public String performOnlineMCQExam()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			oeExamId = request.getParameter("oeExamId");

			if (CommonValidator.isNotNullNotEmpty(oeExamId))
			{
				jdtParam.oeExamId = oeExamId;
				List<OnlineExam> onlineExamList = adminBo.getOnlineExamListByJson(jdtParam);

				onlineExam = onlineExamList.iterator().next();
				oeSubject = onlineExam.getOeSubject();
				oeChapter = onlineExam.getOeChapter();
				oeSchoolType = onlineExam.getOeSchoolType();

				if (CommonValidator.isNotNullNotEmpty(onlineExam))
				{
					jdtParam.oeExamId = onlineExam.getOeExamId();
					jdtParam.usEmployeeId = sessionUser.getUsEmployeeId();

					if (CommonValidator.isEqual(request.getParameter("oeExamAssigned"), "true"))
					{
						List<OnlineExamAssigned> oeAssignedList = adminBo.getOnlineExamAssigned(jdtParam);
						if (CommonValidator.isListFirstNotEmpty(oeAssignedList))
						{
							OnlineExamAssigned oeAssigned = oeAssignedList.iterator().next();
							oeAssigned.checkTimeReached();
							onlineExam = oeAssigned.getOnlineExam();
						}
					}
					else
					{
						onlineExam.checkTimeReached();
					}

					if (onlineExam.isTimeReached() && adminBo.isExamTakenAlready(jdtParam) == false)
					{
						onlinePractiseExam = new OnlinePractiseExam();
						onlinePractiseExam.setOeSubject(oeSubject);
						onlinePractiseExam.setOeChapter(oeChapter);
						onlinePractiseExam.setOeIsOnlineExam(true);
						onlinePractiseExam.setOnlineExamId(onlineExam.getOeExamId());
						onlinePractiseExam.setOePractiseExamName(onlineExam.getOeExamName());
						onlinePractiseExam.setOeNoOfQuestions(onlineExam.getOeNoOfQuestions());
						boolean isExam = adminBo.createOnlinePractiseExam(this);
						if (isExam)
						{
							oePractiseExamId = adminBo.getOePractiseExamIdForOnlineExam();
							onlinePractiseExam = adminBo.getOnlinePractiseExamList(this).iterator().next();
							List<OnlineExamQuestion> oeQuestionList = adminBo.getOnlineExamQuestionList(this);
							List<Integer> randomQuesNo = new ArrayList<Integer>(0);
							onlineExamQuestionList = new ArrayList<OnlineExamQuestion>(0);
							while ( onlineExamQuestionList.size() < onlineExam.getOeNoOfQuestions() && onlineExamQuestionList.size() < oeQuestionList.size() )
							{
								int idx = new Random().nextInt(oeQuestionList.size() + 1);
								if (randomQuesNo.contains(idx) == false && idx < oeQuestionList.size())
								{
									randomQuesNo.add(idx);
									onlineExamQuestionList.add(oeQuestionList.get(idx));
								}
							}
							onlinePractiseExam.setOeMCQDurationPerExam(Integer.parseInt(mcqDurationPerQuestion) * onlineExamQuestionList.size());
							caLoggerMCQ.infoMCQ(Perform_MCQ_Exam, "performOnlineMCQExam", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
						}
						else
						{
							return EPage.Failure.name();
						}
					}
				}
			}
			else
			{
				return EPage.Failure.name();
			}
		}
		return EPage.Success.name();
	}

	public String performOnlineMCQPracticeExam()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			oePractiseExamId = request.getParameter("oePractiseExamId");
			oeSubject = request.getParameter("oeSubject");
			oeChapter = request.getParameter("oeChapter");
			oeSchoolType = sessionUser.getUsBoardName();

			onlinePractiseExam = adminBo.getOnlinePractiseExamList(this).iterator().next();

			int noOfQuestions = Integer.parseInt(request.getParameter("oeNoOfQuestions"));
			onlinePractiseExam.setOeNoOfQuestions(noOfQuestions);

			List<OnlineExamQuestion> oeQuestionList = adminBo.getOnlineExamQuestionList(this);
			List<Integer> randomQuesNo = new ArrayList<Integer>(0);
			onlineExamQuestionList = new ArrayList<OnlineExamQuestion>(0);
			int idx = 0;
			while ( onlineExamQuestionList.size() < noOfQuestions && onlineExamQuestionList.size() < oeQuestionList.size() )
			{
				if (sessionUser.isAdmin())
				{
					idx++;
				}
				else
				{
					idx = new Random().nextInt(oeQuestionList.size() + 1);
				}
				if (randomQuesNo.contains(idx) == false && idx < oeQuestionList.size())
				{
					randomQuesNo.add(idx);
					onlineExamQuestionList.add(oeQuestionList.get(idx));
				}
			}
			onlinePractiseExam.setOeMCQDurationPerExam(Integer.parseInt(mcqDurationPerQuestion) * onlineExamQuestionList.size());
			caLoggerMCQ.infoMCQ(Perform_MCQ_Practice_Exam, "performOnlineMCQPracticeExam", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	public String updatePractiseExamMarkInHistory() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			oePractiseExamName = request.getParameter("oePractiseExamName");
			oeSubject = request.getParameter("oeSubject");
			oeChapter = request.getParameter("oeChapter");
			oeNoOfQuestions = request.getParameter("oeNoOfQuestions");
			scoredMark = request.getParameter("scoredMark");
			answeredQNos = request.getParameter("answeredQNos");
			unAnsweredQNos = request.getParameter("unAnsweredQNos");
			usEmployeeId = sessionUser.getUsEmployeeId();
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");

//			LogStudentOnlineMCQPractice logStudentOnlineMCQPractice = new LogStudentOnlineMCQPractice();
//			AuditEventMaster auditEventMaster = new AuditEventMaster();
//			auditEventMaster.setEventId(Result_MCQ_Practice_Exam);
//			logStudentOnlineMCQPractice.setEvent(auditEventMaster);
//			logStudentOnlineMCQPractice.setCreatedBy(usEmployeeId);
//			logStudentOnlineMCQPractice.setMessage("updatePractiseExamMarkInHistory");
//			logStudentOnlineMCQPractice.setMessage1(oePractiseExamName);
//			logStudentOnlineMCQPractice.setMessage2(oeSubject + "#" + oeChapter + "#" + oeNoOfQuestions + "#" + scoredMark + "#" + answeredQNos + "#" + unAnsweredQNos);

			Timestamp timeStamp = CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST);
			oePractiseExamId = request.getParameter("oePractiseExamId");
			onlinePractiseExam = adminBo.getOnlinePractiseExamList(this).iterator().next();
			if (onlinePractiseExam.isOeIsOnlineExam())
			{

				toppersClub.setTpExamName(oePractiseExamName);
				toppersClub.setTpExamDate(timeStamp);
				toppersClub.setTpExamType(EExamType.MCQ.name());
				toppersClub.setTpStartTime(CommonUtil.convertDateTime(startTime));
				toppersClub.setTpEndTime(CommonUtil.convertDateTime(endTime));
				toppersClub.setTpExamDuration(CommonUtil.getExamTiming(startTime, endTime));
				toppersClub.setTpSubject(oeSubject);
				toppersClub.setTpChapter(oeChapter);
				toppersClub.setTpNoOfQuestions(Integer.parseInt(oeNoOfQuestions));
				toppersClub.setTpMarks(Integer.parseInt(scoredMark));

				float percentage = (Float.parseFloat(String.valueOf(toppersClub.getTpMarks())) / Float.parseFloat(String.valueOf(toppersClub.getTpNoOfQuestions()))) * 100;
				int ratings = 0;
				if (percentage >= 90 && percentage <= 100)
				{
					ratings = 5;
				}
				else if (percentage >= 80 && percentage <= 90)
				{
					ratings = 4;
				}
				else if (percentage >= 70 && percentage <= 80)
				{
					ratings = 3;
				}
				else if (percentage >= 60 && percentage <= 70)
				{
					ratings = 2;
				}
				else if (percentage >= 50 && percentage <= 60)
				{
					ratings = 1;
				}
				else
				{
					ratings = 0;
				}
				toppersClub.setTpRatings(ratings);
				toppersClub.setTpStudentName(sessionUser.getUsUserName());
				toppersClub.setTpSchoolName(sessionUser.getUsSchoolId());
				toppersClub.setCreatedBy(sessionUser.getUsEmployeeId());
				if (CommonValidator.isNotNullNotEmpty(toppersClub))
				{
					if (adminBo.createToppersClubInfo(toppersClub))
					{
						// caLoggerMCQ.infoMCQ("Create Toppers Clup",
						// "updatePractiseExamMarkInHistory", oePractiseExamName, scoredMark,
						// sessionUser.getUsEmployeeId());

						//auditEventMaster.setEventId(Result_MCQ_Exam);

//						if (adminBo.setLogStudentOnlineMCQPracticeInfo(logStudentOnlineMCQPractice))
//						{
//							caLoggerMCQ.infoMCQ(Result_MCQ_Exam, "updatePractiseExamMarkInHistory", oePractiseExamName, oeSubject + "#" + oeChapter + "#" + oeNoOfQuestions + "#" + scoredMark + "#"
//									+ answeredQNos + "#" + unAnsweredQNos, sessionUser.getUsEmployeeId());
//							return EPage.Success.name();
//						}
						return EPage.Success.name();
					}
				}

			}
//			if (adminBo.setLogStudentOnlineMCQPracticeInfo(logStudentOnlineMCQPractice))
//			{
//				caLoggerMCQ.infoMCQ(Result_MCQ_Practice_Exam, "updatePractiseExamMarkInHistory", oePractiseExamName, oeSubject + "#" + oeChapter + "#" + oeNoOfQuestions + "#" + scoredMark + "#"
//						+ answeredQNos + "#" + unAnsweredQNos, sessionUser.getUsEmployeeId());
//				return EPage.Success.name();
//			}
			
			return EPage.Success.name();

		}
		return EPage.AccessDenied.name();
	}

	public String validateSelectedAnswer() throws Exception
	{
		String questionId = request.getParameter("questionId");
		String selectedAnswer = request.getParameter("selectedAnswer");
		isAnswered = request.getParameter("isAnswered");

		List<OnlineExamQuestion> onlineExamQuestionsList = adminBo.getOnlineExamQuestionListByJson(new JQueryDataTableParam(questionId));
		onlineExamQuestion = onlineExamQuestionsList.iterator().next();

		if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion.getUploadExplanationFileFolderURL()))
		{
			ImageDataVO imVO = new ImageDataVO();
			imVO.request = request;
			imVO.uploadFileFolderURL = onlineExamQuestion.getUploadExplanationFileFolderURL();
			imVO.uploadFileName = onlineExamQuestion.getUploadExplanationFileName();
			onlineExamQuestion.setOeExplanationImageURL(imageDownload.downloadImageFromRepositoryToSessionFolder(imVO));

			if (CommonValidator.isNotNullNotEmpty(imVO.uploadFileName)
					&& (!imVO.uploadFileName.toLowerCase().endsWith("doc") || !imVO.uploadFileName.toLowerCase().endsWith("docx") || !imVO.uploadFileName.toLowerCase().endsWith("pdf")))
			{
				String repositorySourceFile = FileServer_Intermediate_Server_Path + SLASH + imVO.uploadFileFolderURL + SLASH + imVO.uploadFileName;
				BufferedImage bimg = ImageIO.read(new File(repositorySourceFile));
				int questionWidth = bimg.getWidth() > 500 ? 500 : bimg.getWidth();
				int questionHeight = bimg.getHeight() > 200 ? 200 : bimg.getHeight();
				onlineExamQuestion.setExplanationWidth(questionWidth);
				onlineExamQuestion.setExplanationHeight(questionHeight);
			}
		}

		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion))
		{
			jsonObj.put("isCorrect", CommonValidator.isEqual(onlineExamQuestion.getOeCorrectAnswer(), selectedAnswer));
			if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion.getOeCorrectAnswer()))
				jsonObj.put("correctAnswer", onlineExamQuestion.getOeCorrectAnswer());
			jsonObj.put("scoredMark", onlineExamQuestion.getOeMarkPerAnswer());
			jsonObj.put("explanation", onlineExamQuestion.getOeExplanation());
			jsonObj.put("explanationImageUrl", onlineExamQuestion.getOeExplanationImageURL());
			jsonObj.put("explanationWidth", onlineExamQuestion.getExplanationWidth());
			jsonObj.put("explanationHeight", onlineExamQuestion.getExplanationHeight());

		}

		jsonArr.add(jsonObj);
		response.getWriter().write(String.valueOf(jsonArr));
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String downloadCATQuestionsByAjax() throws Exception
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null)
		{
			try
			{
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				oeSchoolType = sessionUser.getUsBoardName();
				jdtParam.oeSubject = request.getParameter("oeSubject");
				jdtParam.oeChapter = request.getParameter("oeChapter");
				// jdtParam.oeQuestionType = request.getParameter("oeExamType");
				jdtParam.sSearch = request.getParameter("oeExamMode");
				jdtParam.oeExamId = request.getParameter("oeExamId");
				jdtParam.oeSchoolType = oeSchoolType;
				jdtParam.usEmployeeId = sessionUser.getUsEmployeeId();
				jdtParam.isDownloaded = true;
				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeExamId))
				{
					usEmployeeId = jdtParam.usEmployeeId;

					List<OnlineExam> onlineExamList = adminBo.getOnlineExamListByJson(jdtParam);

					onlineExam = onlineExamList.iterator().next();
					oeSubject = onlineExam.getOeSubject();
					oeChapter = onlineExam.getOeChapter();
					oeSchoolType = onlineExam.getOeSchoolType();

					if (CommonValidator.isNotNullNotEmpty(onlineExam))
					{
						jdtParam.oeExamId = onlineExam.getOeExamId();
						jdtParam.usEmployeeId = sessionUser.getUsEmployeeId();

						if (CommonValidator.isEqual(request.getParameter("oeExamAssigned"), "true"))
						{
							List<OnlineExamAssigned> oeAssignedList = adminBo.getOnlineExamAssigned(jdtParam);
							if (CommonValidator.isListFirstNotEmpty(oeAssignedList))
							{
								OnlineExamAssigned oeAssigned = oeAssignedList.iterator().next();
								oeAssigned.checkTimeReached();
								onlineExam = oeAssigned.getOnlineExam();
							}
						}
						else
						{
							onlineExam.checkTimeReached();
						}

						if (onlineExam.isTimeReached() && adminBo.isExamTakenAlready(jdtParam) == false)
						{
							onlinePractiseExam = new OnlinePractiseExam();
							onlinePractiseExam.setOeSubject(oeSubject);
							onlinePractiseExam.setOeChapter(oeChapter);
							onlinePractiseExam.setOeIsOnlineExam(true);
							onlinePractiseExam.setOnlineExamId(onlineExam.getOeExamId());
							onlinePractiseExam.setOePractiseExamName(onlineExam.getOeExamName());
							onlinePractiseExam.setOeNoOfQuestions(onlineExam.getOeNoOfQuestions());
							boolean isExam = adminBo.createOnlinePractiseExam(this);
							if (isExam)
							{
								oePractiseExamId = adminBo.getOePractiseExamIdForOnlineExam();
								onlinePractiseExam = adminBo.getOnlinePractiseExamList(this).iterator().next();
								List<OnlineExamQuestion> oeQuestionList = adminBo.getOnlineExamQuestionList(this);
								List<Integer> randomQuesNo = new ArrayList<Integer>(0);
								onlineExamQuestionList = new ArrayList<OnlineExamQuestion>(0);
								while ( onlineExamQuestionList.size() < onlineExam.getOeNoOfQuestions() && onlineExamQuestionList.size() < oeQuestionList.size() )
								{
									int idx = new Random().nextInt(oeQuestionList.size() + 1);
									if (randomQuesNo.contains(idx) == false && idx < oeQuestionList.size())
									{
										randomQuesNo.add(idx);
										onlineExamQuestionList.add(oeQuestionList.get(idx));
									}
								}

								String onlineExamSessionFolder = PasswordEncrypt.encrypt(onlinePractiseExam.getOePractiseExamId(), "SHA", "UTF-8").trim();

								if (CommonValidator.isListFirstNotEmpty(onlineExamQuestionList))
								{

									for (OnlineExamQuestion oeExamQuestion : onlineExamQuestionList)
									{
										if (CommonValidator.isNotNullNotEmpty(oeExamQuestion.getUploadFileFolderURL()))
										{
											ImageDataVO imVO = new ImageDataVO();
											imVO.request = request;
											imVO.uploadFileFolderURL = oeExamQuestion.getUploadFileFolderURL();
											imVO.uploadFileName = oeExamQuestion.getUploadFileName();
											// imVO.isOnlineAssessment =
											// onlineExamQuestion.getOnlineExam().isOnlineAssessment();
											imVO.onlineExamSessionFolder = onlineExamSessionFolder;
											oeExamQuestion.setOeQuestionImageURL(imageDownload.downloadImageFromRepositoryToSessionFolder(imVO));

											if (CommonValidator.isNotNullNotEmpty(imVO.uploadFileName)
													&& (!imVO.uploadFileName.toLowerCase().endsWith("doc") || !imVO.uploadFileName.toLowerCase().endsWith("docx") || !imVO.uploadFileName.toLowerCase()
															.endsWith("pdf")))
											{
												String repositorySourceFile = FileServer_Intermediate_Server_Path + SLASH + imVO.uploadFileFolderURL + SLASH + imVO.uploadFileName;
												File imgFile = new File(repositorySourceFile);
												if (imgFile.exists() && imgFile.isFile())
												{
													BufferedImage bimg = ImageIO.read(imgFile);
													int width = bimg.getWidth() > 300 ? 300 : bimg.getWidth();
													int height = bimg.getHeight() > 75 ? 75 : bimg.getHeight();
													oeExamQuestion.setQuestionWidth(width);
													oeExamQuestion.setQuestionHeight(height);
												}
											}
										}

										for (OnlineExamQuestionAnswerMapping oeQA : oeExamQuestion.getOeAnswers())
										{
											if (CommonValidator.isNotNullNotEmpty(oeQA.getUploadFileFolderURL()))
											{
												ImageDataVO imVO = new ImageDataVO();
												imVO.request = request;
												imVO.uploadFileFolderURL = oeQA.getUploadFileFolderURL();
												imVO.uploadFileName = oeQA.getUploadFileName();
												// imVO.isOnlineAssessment =
												// onlineExamQuestion.getOnlineExam().isOnlineAssessment();
												imVO.onlineExamSessionFolder = onlineExamSessionFolder;
												oeQA.setOeAnswerImageURL(imageDownload.downloadImageFromRepositoryToSessionFolder(imVO));

												if (CommonValidator.isNotNullNotEmpty(imVO.uploadFileName)
														&& (!imVO.uploadFileName.toLowerCase().endsWith("doc") || !imVO.uploadFileName.toLowerCase().endsWith("docx") || !imVO.uploadFileName
																.toLowerCase().endsWith("pdf")))
												{
													String repositorySourceFile = FileServer_Intermediate_Server_Path + SLASH + imVO.uploadFileFolderURL + SLASH + imVO.uploadFileName;
													File imgFile = new File(repositorySourceFile);
													if (imgFile.exists() && imgFile.isFile())
													{
														BufferedImage bimg = ImageIO.read(imgFile);
														int width = bimg.getWidth() > 300 ? 300 : bimg.getWidth();
														int height = bimg.getHeight() > 75 ? 75 : bimg.getHeight();
														oeQA.setImageWidth(width);
														oeQA.setImageHeight(height);
													}
												}
											}
										}
									}

									CATQuestionsGeneratePDF.createCATQuestionsPDF(onlineExamQuestionList, onlineExam, this);

								}

								onlinePractiseExam.setOeMCQDurationPerExam(Integer.parseInt(mcqDurationPerQuestion) * onlineExamQuestionList.size());

								caLoggerMCQ.infoMCQ(Download_MCA_Exam_Questions, "downloadCATQuestionsByAjax", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
							}
							else
							{
								return EPage.Failure.name();
							}
						}
					}

				}

				return EPage.Success.name();
			}
			catch (Exception excep)
			{
				caLoggerMCQ.error(Download_MCA_Exam_Questions, "downloadCATQuestionsByAjax", EPage.Failure.name() + " : " + excep.getMessage(), this.getClass().getName(), null);
			}
		}
		return EPage.AccessDenied.name();
	}
}