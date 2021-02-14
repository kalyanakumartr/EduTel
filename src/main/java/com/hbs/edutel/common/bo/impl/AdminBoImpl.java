package com.hbs.edutel.common.bo.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.hbs.edutel.action.AdminCollegeManagementAction;
import com.hbs.edutel.action.AdminInfoAlertAction;
import com.hbs.edutel.action.AdminSchoolManagementAction;
import com.hbs.edutel.action.AdminStudentEnquiryAction;
import com.hbs.edutel.action.AdminUserManagementAction;
import com.hbs.edutel.action.AuditLoggingUDParam;
import com.hbs.edutel.action.DashBoardAction;
import com.hbs.edutel.action.IDashBoardAction;
import com.hbs.edutel.action.IOnlineExamAction;
import com.hbs.edutel.action.OnlineEBluePrintAction;
import com.hbs.edutel.action.OnlineEBookAction;
import com.hbs.edutel.action.OnlineEKeyPointsAction;
import com.hbs.edutel.action.OnlineExamAction;
import com.hbs.edutel.action.OnlineExamAssignedAction;
import com.hbs.edutel.action.OnlineExamQuestionAction;
import com.hbs.edutel.action.OnlineExamQuestionAdminAction;
import com.hbs.edutel.admin.action.PasswordEncrypt;
import com.hbs.edutel.bo.SerialKeyParam;
import com.hbs.edutel.common.action.CommonActionDAOData;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.bo.AdminBo;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.logger.IAuditLogging;
import com.hbs.edutel.model.College;
import com.hbs.edutel.model.CollegeEForm;
import com.hbs.edutel.model.InformationAlerts;
import com.hbs.edutel.model.OnlineEBluePrint;
import com.hbs.edutel.model.OnlineEBook;
import com.hbs.edutel.model.OnlineEKeyPoints;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamAssigned;
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlineExamQuestionAnswerMapping;
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.hbs.edutel.model.School;
import com.hbs.edutel.model.SerialKeyGenerate;
import com.hbs.edutel.model.StudentEnquiry;
import com.hbs.edutel.model.ToppersClub;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.PwdGenerator;
import com.hbs.edutel.util.common.ConstEnumUtil.EExamType;
import com.hbs.edutel.util.common.ConstEnumUtil.EGenerate;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.image.ImageDataVO;

public class AdminBoImpl extends CommonActionDAOData implements AdminBo, ConstInterface
{
	private static final long		serialVersionUID	= -3102017554303565616L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());
	private String					oePractiseExamIdForOnlineExam;

	public OnlineExam blockUnlockExam(String oeExamId, String status, String usEmployeeId)
	{
		return adminDAO.blockUnlockExam(oeExamId, status, usEmployeeId);
	}

	public OnlineExamAssigned blockUnlockExamAssigned(String oeExamAutoId, String status, String modifiedBy)
	{
		return adminDAO.blockUnlockExamAssigned(oeExamAutoId, status, modifiedBy);
	}

	public OnlineExamQuestion blockUnlockExamQuestion(String oeExamQuestionId, String status, String modifiedBy)
	{
		return adminDAO.blockUnlockExamQuestion(oeExamQuestionId, status, modifiedBy);
	}

	public List<CollegeEForm> collegeEFormListByJson(JQueryDataTableParam jdtParam)
	{
		return adminDAO.collegeEFormListByJson(jdtParam);
	}

	public List<CollegeEForm> collegeEFormListByJsonAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.collegeEFormListByJsonAll(jdtParam);
	}

	public List<CollegeEForm> collegeEFormOrderedList(IUsers sessionUser)
	{
		return adminDAO.collegeEFormOrderedList(sessionUser);

	}

	public boolean collegeRegistration(AdminCollegeManagementAction adminCollege)
	{
		ImageDataVO imageDataVO = new ImageDataVO();
		imageDataVO.subFolderStructure = EGenerate.College.name();
		adminCollege.saveAttachment(imageDataVO, adminCollege.getCollege());
		return adminDAO.collegeRegistration(adminCollege);
	}

	public boolean createInformationAlert(InformationAlerts infoAlertMsg)
	{
		return adminDAO.createInformationAlert(infoAlertMsg);
	}

	public boolean createOnlineExam(OnlineExamAction adminExam)
	{
		adminExam.getOnlineExam().setOeExamId(EGenerate.Exam.getPrimaryId());
		adminExam.getOnlineExam().setCreatedBy(adminExam.getUsEmployeeId());
		adminExam.getOnlineExam().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
		return adminDAO.createOnlineExam(adminExam);
	}

	public boolean createOnlineExamAssigned(OnlineExamAssignedAction adminExam) throws Exception
	{
		JQueryDataTableParam jdtParam = new JQueryDataTableParam();
		jdtParam.oeExamId = adminExam.getOnlineExamAssigned().getOnlineExam().getOeExamId();
		jdtParam.usEmployeeId = adminExam.getUsEmployeeIdStudent();
		jdtParam.oeAssignedExamDate = adminExam.getOnlineExamAssigned().getOeAssignedExamDate();
		jdtParam.status = null;

		List<OnlineExamAssigned> oeAssignedList = adminDAO.getOnlineExamAssignedList(jdtParam);
		if (CommonValidator.isListFirstNotEmpty(oeAssignedList) == false)
		{
			adminExam.getOnlineExamAssigned().setCreatedBy(adminExam.getUsEmployeeId());
			adminExam.getOnlineExamAssigned().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			adminExam.getOnlineExamAssigned().setUsers(adminDAO.getUserById(new JQueryDataTableParam(adminExam.getUsEmployeeIdStudent())));
			boolean isSuccess = adminDAO.createOnlineExamAssigned(adminExam);
			adminExam.setOnlineExamAssigned(adminDAO.getOnlineExamAssignedList(jdtParam).iterator().next());
			return isSuccess;
		}
		else
		{
			adminExam.setOnlineExamAssigned(oeAssignedList.iterator().next());
			throw new Exception();
		}

	}

	public boolean createOnlineExamQuestion(OnlineExamQuestionAdminAction adminExamQuestion)
	{
		adminExamQuestion.getOnlineExamQuestion().setOnlineExam(adminExamQuestion.getOnlineExam());
		adminExamQuestion.getOnlineExamQuestion().setOeQuestionId(EGenerate.ExamQuestion.getPrimaryId());
		adminExamQuestion.getOnlineExamQuestion().setCreatedBy(adminExamQuestion.getUsEmployeeId());
		adminExamQuestion.getOnlineExamQuestion().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

		if (CommonValidator.isEqual(adminExamQuestion.getOnlineExam().getOeExamType(), EExamType.MCQ.name()))
		{
			adminExamQuestion.setOnlineExamQuestionAnswerArray(new OnlineExamQuestionAnswerMapping[adminExamQuestion.getOeAnswerText().size()]);
			OnlineExamQuestionAnswerMapping oExamQA = null;
			int fileIdx = 0;
			int fileCnt = adminExamQuestion.getUploadFiles().size();
			boolean isInserted = false;
			for (String oeAnswerText : adminExamQuestion.getOeAnswerText())
			{
				oExamQA = new OnlineExamQuestionAnswerMapping();
				oExamQA.setOnlineExamQuestion(adminExamQuestion.getOnlineExamQuestion());
				if (CommonValidator.isNotNullNotEmpty(oeAnswerText))
				{
					if (fileCnt == 0 && !isInserted)
					{
						adminExamQuestion.getUploadFiles().add(new File("no_image.png"));
						adminExamQuestion.getUploadFilesFileName().add("no_image.png");
						isInserted = false;
					}
					else if (fileCnt == 1 && (fileIdx == 1 || fileIdx == 2 || fileIdx == 3) && !isInserted)
					{
						adminExamQuestion.getUploadFiles().add(new File("no_image.png"));
						adminExamQuestion.getUploadFilesFileName().add("no_image.png");
						isInserted = false;
					}
					else if (fileCnt == 2 && (fileIdx == 2 || fileIdx == 3) && !isInserted)
					{
						adminExamQuestion.getUploadFiles().add(new File("no_image.png"));
						adminExamQuestion.getUploadFilesFileName().add("no_image.png");
						isInserted = false;
					}
					else if (fileCnt == 3 && (fileIdx == 2 || fileIdx == 3) && !isInserted)
					{
						adminExamQuestion.getUploadFiles().add(new File("no_image.png"));
						adminExamQuestion.getUploadFilesFileName().add("no_image.png");
						isInserted = false;
					}
					else
					{
						if (fileIdx == 3 && isInserted)
						{
							adminExamQuestion.getUploadFiles().add(new File("no_image.png"));
							adminExamQuestion.getUploadFilesFileName().add("no_image.png");
							isInserted = false;
						}
						else
						{
							isInserted = true;
							adminExamQuestion.getUploadFiles().add(fileIdx, new File("no_image.png"));
							adminExamQuestion.getUploadFilesFileName().add(fileIdx, "no_image.png");
						}
					}
				}
				else
				{
					if (fileCnt == 2 && (fileIdx == 2 && !adminExamQuestion.getUploadFilesFileName().get(fileIdx).equals("no_image.png")))
						isInserted = true;
					else
						isInserted = false;
				}
				oExamQA.setOeAnswerText(oeAnswerText);
				oExamQA.setCreatedBy(adminExamQuestion.getUsEmployeeId());
				oExamQA.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				adminExamQuestion.getOnlineExamQuestionAnswerArray()[fileIdx++] = oExamQA;
			}
			adminExamQuestion.getOnlineExamQuestion().setOeQuestionType(EExamType.MCQ.name());

			// Here used for upload MCQ Questions Options
			ImageDataVO imageDataVO = new ImageDataVO();
			imageDataVO.subFolderStructure = EGenerate.ExamQuestion.name() + "/" + adminExamQuestion.getOnlineExam().getOeExamName() + "/"
					+ adminExamQuestion.getOnlineExamQuestion().getOeQuestionId();
			adminExamQuestion.saveAttachment(imageDataVO, adminExamQuestion.getOnlineExamQuestionAnswerArray());

			// Here used for upload MCQ Question
			if (CommonValidator.isListFirstNotEmpty(adminExamQuestion.getUploadQuestionFile()))
			{
				imageDataVO = new ImageDataVO();
				imageDataVO.subFolderStructure = EGenerate.ExamQuestion.name() + "/" + adminExamQuestion.getOnlineExam().getOeExamName() + "/"
						+ adminExamQuestion.getOnlineExamQuestion().getOeQuestionId();
				adminExamQuestion.saveQuestionAttachment(imageDataVO, adminExamQuestion.getOnlineExamQuestion());
			}
			// Here used for upload MCQ Answer Explanation
			if (CommonValidator.isListFirstNotEmpty(adminExamQuestion.getUploadQuestionFile()))
			{
				imageDataVO = new ImageDataVO();
				imageDataVO.subFolderStructure = EGenerate.ExamQuestion.name() + "/" + adminExamQuestion.getOnlineExam().getOeExamName() + "/"
						+ adminExamQuestion.getOnlineExamQuestion().getOeQuestionId();
				adminExamQuestion.saveExplanationAttachment(imageDataVO, adminExamQuestion.getOnlineExamQuestion());
			}

		}
		else
		{
			adminExamQuestion.setOnlineExamQuestionAnswerArray(new OnlineExamQuestionAnswerMapping[1]);
			OnlineExamQuestionAnswerMapping oExamQA = new OnlineExamQuestionAnswerMapping();
			oExamQA.setOnlineExamQuestion(adminExamQuestion.getOnlineExamQuestion());
			oExamQA.setCreatedBy(adminExamQuestion.getUsEmployeeId());
			oExamQA.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			adminExamQuestion.getOnlineExamQuestionAnswerArray()[0] = oExamQA;
			adminExamQuestion.getOnlineExamQuestion().setOeQuestionType(EExamType.TestSeries.name());
			adminExamQuestion.getOnlineExamQuestion().setOeQuestion(adminExamQuestion.getUploadFilesFileName().iterator().next());

			// Here used for upload Test Series Questions
			ImageDataVO imageDataVO = new ImageDataVO();
			imageDataVO.subFolderStructure = EGenerate.ExamQuestion.name() + "/" + adminExamQuestion.getOnlineExam().getOeExamName() + "/"
					+ adminExamQuestion.getOnlineExamQuestion().getOeQuestionId();
			adminExamQuestion.saveAttachment(imageDataVO, adminExamQuestion.getOnlineExamQuestionAnswerArray());
		}

		return adminDAO.createOnlineExamQuestion(adminExamQuestion);
	}

	public boolean createOnlinePractiseExam(IOnlineExamAction adminExam)
	{
		adminExam.getOnlinePractiseExam().setOePractiseExamId(EGenerate.ExamPractise.getPrimaryId());
		adminExam.getOnlinePractiseExam().setCreatedBy(adminExam.getUsEmployeeId());
		adminExam.getOnlinePractiseExam().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
		setOePractiseExamIdForOnlineExam(adminExam.getOnlinePractiseExam().getOePractiseExamId());
		return adminDAO.createOnlinePractiseExam(adminExam);
	}

	public boolean createOnlineTestSeriesExamAnswer(OnlineExamQuestionAdminAction oeQAction)
	{
		String oeExamId = oeQAction.getOnlineExamQuestion().getOnlineExam().getOeExamId();
		List<OnlineExamQuestion> oeQList = getOnlineExamQuestionListByJson(new JQueryDataTableParam(oeExamId));
		if (CommonValidator.isListFirstNotEmpty(oeQList))
		{
			OnlineExamQuestion oeQ = oeQList.iterator().next();
			oeQAction.getOnlineTestSeriesExamAnswer().setOnlineExamQuestion(oeQ);
			oeQAction.getOnlineTestSeriesExamAnswer().getUploadFileScreenShot();

			ImageDataVO imageDataVO = new ImageDataVO();

			if (oeQAction.getOnlineTestSeriesExamAnswer().isOeValidatedAnswer() == false)
			{
				oeQAction.getOnlineTestSeriesExamAnswer().setOeTestSeriesAnswerVIA(oeQAction.getOeTestSeriesAnswerVIA());
				oeQAction.getOnlineTestSeriesExamAnswer().setCreatedBy(oeQAction.getUsEmployeeId());
				oeQAction.getOnlineTestSeriesExamAnswer().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				imageDataVO.subFolderStructure = EExamType.TestSeries.name() + "/" + oeQ.getOeQuestionId() + "/" + oeQAction.getUsEmployeeId();
			}
			else
			{
				oeQAction.getOnlineTestSeriesExamAnswer().setOeTestSeriesAnswerVIA("Online");
				oeQAction.getOnlineTestSeriesExamAnswer().setModifiedBy(oeQAction.getUsEmployeeId());
				oeQAction.getOnlineTestSeriesExamAnswer().setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				imageDataVO.subFolderStructure = EExamType.TestSeries.name() + "/" + oeQ.getOeQuestionId() + "/" + oeQAction.getOnlineTestSeriesExamAnswer().getCreatedBy() + "/Validated";
			}

			oeQAction.saveAttachment(imageDataVO, oeQAction.getOnlineTestSeriesExamAnswer());
			return adminDAO.createOnlineTestSeriesExamAnswer(oeQAction);
		}
		return false;
	}

	public boolean createToppersClubInfo(ToppersClub toppersClub)
	{
		return adminDAO.createToppersClubInfo(toppersClub);
	}

	public College deleteCollege(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		return adminDAO.deleteCollege(adminCollegeManagementAction);
	}

	public boolean deleteEdutelEnquiry(AdminStudentEnquiryAction adminStudentEnquiryAction)
	{
		return adminDAO.deleteEdutelEnquiry(adminStudentEnquiryAction);
	}

	public CollegeEForm deleteEForm(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		return adminDAO.deleteEForm(adminCollegeManagementAction);
	}

	public boolean deleteInfoAlert(AdminInfoAlertAction adminUserManagementAction)
	{
		return adminDAO.deleteInfoAlert(adminUserManagementAction);
	}

	public OnlineEBluePrint deleteOnlineEBluePrint(String oEBluePrintId)
	{
		return adminDAO.deleteOnlineEBluePrint(oEBluePrintId);
	}

	public OnlineEBook deleteOnlineEBook(String oeBookId)
	{
		OnlineEBook delBk = adminDAO.deleteOnlineEBook(oeBookId);

		if (CommonValidator.isNotNullNotEmpty(delBk))
		{
			String delFilePath = FileServer_Intermediate_Server_Path + "/" + delBk.getUploadFileFolderURL() + "/" + delBk.getUploadFileName();
			File file2Delete = new File(delFilePath);
			if (file2Delete.exists())
			{
				file2Delete.delete();
			}
		}
		return delBk;
	}

	public OnlineEKeyPoints deleteOnlineEKeyPoints(String oeKeyPointsId)
	{
		return adminDAO.deleteOnlineEKeyPoints(oeKeyPointsId);
	}

	public OnlineExam deleteOnlineExam(String oeExamId)
	{
		return adminDAO.deleteOnlineExam(oeExamId);
	}

	public OnlineTestSeriesExamQuestionAnswerMapping deleteOnlineExamAnswer(OnlineExamQuestionAction onlineExamQuestionAction)
	{

		return adminDAO.deleteOnlineExamAnswer(onlineExamQuestionAction);

	}

	public OnlineExamAssigned deleteOnlineExamAssigned(String oeExamAutoId)
	{
		return adminDAO.deleteOnlineExamAssigned(oeExamAutoId);
	}

	public List<OnlinePractiseExam> deleteOnlineExamPracticeEntry(IDashBoardAction dashBoardAction)
	{
		return adminDAO.deleteOnlineExamPracticeEntry(dashBoardAction);
	}

	public OnlineExamQuestion deleteOnlineExamQuestion(String oeExamQuestionId)
	{
		return adminDAO.deleteOnlineExamQuestion(oeExamQuestionId);
	}

	public School deleteSchool(AdminSchoolManagementAction adminSchoolManagementAction)
	{
		return adminDAO.deleteSchool(adminSchoolManagementAction);
	}

	public ToppersClub deleteTopper(JQueryDataTableParam jdtParam)
	{
		return adminDAO.deleteTopper(jdtParam);
	}

	public IUsers deleteUser(AdminUserManagementAction adminUserManagementAction)
	{
		return adminDAO.deleteUser(adminUserManagementAction);
	}

	public OnlineExam displayExamPublic(String oeExamId, boolean displayPublic, String modifiedBy)
	{
		return adminDAO.displayExamPublic(oeExamId, displayPublic, modifiedBy);
	}

	public College getCollegeById(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getCollegeById(jdtParam);
	}

	public List<CollegeEForm> getCollegeEFormById(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		return adminDAO.getCollegeEFormById(adminCollegeManagementAction);
	}

	public List<College> getCollegesList(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getCollegesList(jdtParam);
	}

	public List<College> getCollegesListAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getCollegesListAll(jdtParam);
	}

	public List<StudentEnquiry> getEnquiryList(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getEnquiryList(jdtParam);
	}

	public List<InformationAlerts> getInfoAlertList(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getInfoAlertList(jdtParam);
	}

	public List<InformationAlerts> getInfoAlertListAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getInfoAlertListAll(jdtParam);
	}

	public int[] getLoginUsersCount()
	{
		return userDAO.getLoginUsersCount();
	}

	public String getOePractiseExamIdForOnlineExam()
	{
		return oePractiseExamIdForOnlineExam;
	}

	public List<OnlineEBluePrint> getOnlineEBluePrintListByJson(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineEBluePrintListByJson(jdtParam);
	}

	public List<OnlineEBluePrint> getOnlineEBluePrintListByJsonAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineEBluePrintListByJsonAll(jdtParam);
	}

	public List<OnlineEBook> getOnlineEBookListByJson(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineEBookListByJson(jdtParam);
	}

	public List<OnlineEBook> getOnlineEBookListByJsonAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineEBookListByJsonAll(jdtParam);
	}

	public List<OnlineEKeyPoints> getOnlineEKeyPointsListByJson(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineEKeyPointsListByJson(jdtParam);
	}

	public List<OnlineEKeyPoints> getOnlineEKeyPointsListByJsonAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineEKeyPointsListByJsonAll(jdtParam);
	}

	public List<OnlineExamAssigned> getOnlineExamAssigned(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineExamAssignedList(jdtParam);
	}

	public List<OnlineExamAssigned> getOnlineExamAssignedList(IDashBoardAction dashBoardAction)
	{
		return adminDAO.getOnlineExamAssignedList(dashBoardAction);
	}

	public List<OnlineExamAssigned> getOnlineExamAssignedListByJson(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineExamAssignedListByJson(jdtParam);
	}

	public List<OnlineExamAssigned> getOnlineExamAssignedListByJsonAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineExamAssignedListByJsonAll(jdtParam);
	}

	public List<OnlineExam> getOnlineExamList(IDashBoardAction dashBoardAction)
	{
		return adminDAO.getOnlineExamList(dashBoardAction);
	}

	public List<OnlineExam> getOnlineExamListByJson(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineExamListByJson(jdtParam);
	}

	public List<OnlineExam> getOnlineExamListByJsonAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineExamListByJsonAll(jdtParam);
	}

	public OnlineExamQuestion getOnlineExamQuestion(OnlineExamQuestionAdminAction onlineExamQuestionAction)
	{
		return adminDAO.getOnlineExamQuestion(onlineExamQuestionAction);
	}

	public List<OnlineExamQuestion> getOnlineExamQuestionList(OnlineExamQuestionAdminAction onlineExamQuestionAction)
	{
		return adminDAO.getOnlineExamQuestionList(onlineExamQuestionAction);
	}

	public List<OnlineExamQuestion> getOnlineExamQuestionListByJson(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineExamQuestionListByJson(jdtParam);
	}

	public List<OnlineExamQuestion> getOnlineExamQuestionListByJsonAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineExamQuestionListByJsonAll(jdtParam);
	}

	public List<OnlinePractiseExam> getOnlinePractiseExamList(IDashBoardAction dashBoardAction)
	{
		return adminDAO.getOnlinePractiseExamList(dashBoardAction);
	}

	public List<OnlinePractiseExam> getOnlinePractiseExamList(OnlineExamQuestionAdminAction onlineExamQuestionAction)
	{
		return adminDAO.getOnlinePractiseExamList(onlineExamQuestionAction);
	}

	public List<OnlineExamQuestion> getOnlineTestSeriesExamQuestionListByJson(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineTestSeriesExamQuestionListByJson(jdtParam);
	}

	public List<OnlineExamQuestion> getOnlineTestSeriesExamQuestionListByJsonAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineTestSeriesExamQuestionListByJson(jdtParam);
	}

	public OnlineTestSeriesExamQuestionAnswerMapping getOnlineTestSeriesQuestionAnswer(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getOnlineTestSeriesQuestionAnswer(jdtParam);
	}

	public List<OnlineTestSeriesExamQuestionAnswerMapping> getOnlineTestSeriesQuestionAnswerList(OnlineExamQuestionAdminAction oeQuestionAction)
	{
		return adminDAO.getOnlineTestSeriesQuestionAnswerList(oeQuestionAction);
	}

	public List<CollegeEForm> getOrderedEFormList(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		return adminDAO.getOrderedEFormList(adminCollegeManagementAction);
	}

	//
	// public List<IAuditLogging> getPracticeExamResultList(AuditLoggingUDParam adtParam)
	// {
	// adtParam.eAuditList = new String[] { Result_MCQ_Practice_Exam, Result_MCQ_Exam,
	// Result_TestSeries_Exam };
	// adtParam.clazz = LogStudentOnlineMCQPractice.class.getCanonicalName();
	// return adminDAO.getAuditLoggingDataList(adtParam);
	// }

	public School getSchoolById(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getSchoolById(jdtParam);
	}

	public List<School> getSchoolsList(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getSchoolsList(jdtParam);
	}

	public List<School> getSchoolsListAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getSchoolsListAll(jdtParam);
	}

	public OnlineTestSeriesExamQuestionAnswerMapping getTestSeriesExamAnswerByCreatedBy(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getTestSeriesExamAnswerByCreatedBy(jdtParam);
	}

	public OnlineTestSeriesExamQuestionAnswerMapping getTestSeriesExamAnswerById(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getTestSeriesExamAnswerById(jdtParam);
	}

	public List<ToppersClub> getToppersClubList(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getToppersClubList(jdtParam);
	}

	public List<ToppersClub> getToppersClubListAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getToppersClubListAll(jdtParam);
	}

	public List<ToppersClub> getToppersClubListByMarks(String lastExamName)
	{
		return adminDAO.getToppersClubListByMarks(lastExamName);
	}

	//
	// public List<IAuditLogging> getUserActivityList(AuditLoggingUDParam adtParam)
	// {
	// adtParam.clazz = AuditLoggingUD.class.getCanonicalName();
	// return adminDAO.getAuditLoggingDataList(adtParam);
	// }

	public IUsers getUserById(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getUserById(jdtParam);
	}

	public List<IUsers> getUsersList(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getUsersList(jdtParam);
	}

	public List<IUsers> getUsersListAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getUsersListAll(jdtParam);
	}

	public boolean isExamTakenAlready(JQueryDataTableParam jdtParam)
	{
		return adminDAO.isExamTakenAlready(jdtParam);
	}

	public List<OnlineEBluePrint> onlineEBluePrintListByAjax(JQueryDataTableParam jdtParam)
	{
		return adminDAO.onlineEBluePrintListByAjax(jdtParam);
	}

	public boolean onlineEBluePrintRepositoryUpload(OnlineEBluePrintAction adminEBluePrint)
	{
		adminEBluePrint.setOnlineEBluePrintArray(new OnlineEBluePrint[adminEBluePrint.getUploadFileDisplayName().size()]);
		OnlineEBluePrint oeBluePrint = null;
		int fileId = 0;
		for (String dispName : adminEBluePrint.getUploadFileDisplayName())
		{
			oeBluePrint = new OnlineEBluePrint();
			oeBluePrint.setOeBluePrintId(EGenerate.EBluePrint.getPrimaryId());
			oeBluePrint.setOeBluePrintName(dispName);
			if (adminEBluePrint.getUploadFilesFileName().get(fileId).endsWith(".pdf"))
				oeBluePrint.setUploadFileScreenShot("pdfIcon.png");
			else
				oeBluePrint.setUploadFileScreenShot("docxIcon.png");
			oeBluePrint.setCreatedBy(adminEBluePrint.getUsEmployeeId());
			oeBluePrint.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			oeBluePrint.setOeSubject(adminEBluePrint.getOeSubject());
			oeBluePrint.setOeSchoolType(adminEBluePrint.getOeSchoolType());
			adminEBluePrint.getOnlineEBluePrintArray()[fileId++] = oeBluePrint;
		}
		ImageDataVO imageDataVO = new ImageDataVO();
		imageDataVO.subFolderStructure = EGenerate.EBluePrint.name() + "/" + adminEBluePrint.getOeSchoolType() + "/" + adminEBluePrint.getOeSubject();
		adminEBluePrint.saveAttachment(imageDataVO, adminEBluePrint.getOnlineEBluePrintArray());
		return adminDAO.onlineEBluePrintRepositoryUpload(adminEBluePrint);
	}

	public List<OnlineEBook> onlineEBookListByAjax(JQueryDataTableParam jdtParam)
	{
		return adminDAO.onlineEBookListByAjax(jdtParam);
	}

	public boolean onlineEBookRepositoryUpload(OnlineEBookAction adminEBook)
	{
		adminEBook.setOnlineEBookArray(new OnlineEBook[adminEBook.getUploadFileDisplayName().size()]);
		OnlineEBook oeBook = null;
		int fileId = 0;
		for (String dispName : adminEBook.getUploadFileDisplayName())
		{
			oeBook = new OnlineEBook();
			oeBook.setOeBookId(EGenerate.EBook.getPrimaryId());
			oeBook.setOeBookName(dispName);

			if (adminEBook.getUploadFilesFileName().get(fileId).endsWith(".pdf"))
				oeBook.setUploadFileScreenShot("pdfIcon.png");
			else
				oeBook.setUploadFileScreenShot("docxIcon.png");
			oeBook.setCreatedBy(adminEBook.getUsEmployeeId());
			oeBook.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			oeBook.setOeSubject(adminEBook.getOeSubject());
			oeBook.setOeChapter(adminEBook.getOeChapter().get(fileId));
			adminEBook.getOnlineEBookArray()[fileId++] = oeBook;
		}
		ImageDataVO imageDataVO = new ImageDataVO();
		imageDataVO.subFolderStructure = EGenerate.EBook.name() + "/" + adminEBook.getOeSchoolType() + "/" + adminEBook.getOeSubject();
		adminEBook.saveAttachment(imageDataVO, adminEBook.getOnlineEBookArray());
		return adminDAO.onlineEBookRepositoryUpload(adminEBook);
	}

	public List<OnlineEKeyPoints> onlineEKeyPointsListByAjax(JQueryDataTableParam jdtParam)
	{
		return adminDAO.onlineEKeyPointsListByAjax(jdtParam);
	}

	public boolean onlineEKeyPointsRepositoryUpload(OnlineEKeyPointsAction adminEKeyPoints)
	{
		adminEKeyPoints.setOnlineEKeyPointsArray(new OnlineEKeyPoints[adminEKeyPoints.getUploadFileDisplayName().size()]);
		OnlineEKeyPoints oeKeyPoints = null;
		int fileId = 0;
		for (String dispName : adminEKeyPoints.getUploadFileDisplayName())
		{
			oeKeyPoints = new OnlineEKeyPoints();
			oeKeyPoints.setOeKeyPointsId(EGenerate.EKeyPoints.getPrimaryId());
			oeKeyPoints.setOeKeyPointsName(dispName);
			if (adminEKeyPoints.getUploadFilesFileName().get(fileId).endsWith(".pdf"))
				oeKeyPoints.setUploadFileScreenShot("pdfIcon.png");
			else
				oeKeyPoints.setUploadFileScreenShot("docxIcon.png");
			oeKeyPoints.setOeChapter(adminEKeyPoints.getOeChapter().get(fileId));
			oeKeyPoints.setOeSchoolType(adminEKeyPoints.getOeSchoolType());
			oeKeyPoints.setOeSubject(adminEKeyPoints.getOeSubject());
			oeKeyPoints.setCreatedBy(adminEKeyPoints.getUsEmployeeId());
			oeKeyPoints.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			adminEKeyPoints.getOnlineEKeyPointsArray()[fileId++] = oeKeyPoints;
		}
		ImageDataVO imageDataVO = new ImageDataVO();
		imageDataVO.subFolderStructure = EGenerate.EKeyPoints.name() + "/" + adminEKeyPoints.getOeSchoolType() + "/" + adminEKeyPoints.getOeSubject();
		adminEKeyPoints.saveAttachment(imageDataVO, adminEKeyPoints.getOnlineEKeyPointsArray());
		return adminDAO.onlineEKeyPointsRepositoryUpload(adminEKeyPoints);
	}

	public boolean orderEForm(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		List<CollegeEForm> eFormList = adminDAO.getCollegeEFormById(adminCollegeManagementAction);
		if (CommonValidator.isListFirstNotEmpty(eFormList) == false)
		{
			return adminDAO.orderEForm(adminCollegeManagementAction);
		}
		return false;

	}

	public IUsers resetPassword(AdminUserManagementAction adminUser)
	{
		String usEmployeeId = adminUser.getRequest().getParameter("usEmployeeId");
		if (CommonValidator.isNotNullNotEmpty(usEmployeeId))
		{
			adminUser.setUser(getUserById(new JQueryDataTableParam(usEmployeeId)));
			String autoGenPassword = PwdGenerator.getPassword();
			caLogger.info(Audit_Logging_Event_DataEntry, "Password Assigned By System", autoGenPassword, adminUser.getUser().getUsUserID(), null);
			String shaPassWord = PasswordEncrypt.encrypt(autoGenPassword, "SHA", "UTF-8").trim();
			adminUser.getUser().setUsUserPwd(shaPassWord);
			if (adminDAO.resetPassword(adminUser))
			{
				adminUser.getUser().setUsUserPwd(autoGenPassword);
				return adminUser.getUser();
			}
		}
		return null;

	}

	public boolean schoolRegistration(AdminSchoolManagementAction adminSchool)
	{
		ImageDataVO imageDataVO = new ImageDataVO();
		imageDataVO.subFolderStructure = EGenerate.School.name();
		adminSchool.saveAttachment(imageDataVO, adminSchool.getSchool());
		return adminDAO.schoolRegistration(adminSchool);
	}

	public String searchSchoolName(JQueryDataTableParam jdtParam)
	{
		return adminDAO.searchSchoolName(jdtParam);
	}

	//
	// public boolean setAuditLogginUdInfo(AuditLoggingUD auditLoggingUD)
	// {
	// return adminDAO.setAuditLogginUdInfo(auditLoggingUD);
	// }

	//
	// public boolean setLogStudentOnlineMCQPracticeInfo(LogStudentOnlineMCQPractice
	// logStudentOnlineMCQPractice)
	// {
	// return adminDAO.setLogStudentOnlineMCQPracticeInfo(logStudentOnlineMCQPractice);
	// }

	public void setOePractiseExamIdForOnlineExam(String oePractiseExamIdForOnlineExam)
	{
		this.oePractiseExamIdForOnlineExam = oePractiseExamIdForOnlineExam;
	}

	public boolean setPurchasedEFormList(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		// return adminDAO.setPurchasedEFormList(adminCollegeManagementAction);
		return false;
	}

	public boolean studentEdutelEnquiry(AdminStudentEnquiryAction adminStudentEnquiryAction)
	{
		return adminDAO.studentEdutelEnquiry(adminStudentEnquiryAction);
	}

	public boolean updateCollege(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		ImageDataVO imageDataVO = new ImageDataVO();
		imageDataVO.subFolderStructure = EGenerate.College.name();
		adminCollegeManagementAction.saveAttachment(imageDataVO, adminCollegeManagementAction.getCollege());
		return adminDAO.updateCollege(adminCollegeManagementAction);
	}

	public boolean updateSchool(AdminSchoolManagementAction adminSchoolManagementAction)
	{
		ImageDataVO imageDataVO = new ImageDataVO();
		imageDataVO.subFolderStructure = EGenerate.School.name();
		adminSchoolManagementAction.saveAttachment(imageDataVO, adminSchoolManagementAction.getSchool());
		return adminDAO.updateSchool(adminSchoolManagementAction);
	}

	public boolean updateUser(AdminUserManagementAction adminUser)
	{
		ImageDataVO imageDataVO = new ImageDataVO();
		imageDataVO.subFolderStructure = adminUser.getUser().getUsUsersType();
		adminUser.saveAttachment(imageDataVO, adminUser.getUser());
		if (CommonValidator.isEqual(adminUser.getPasswordUpdated(), "true"))
		{
			String shaPassWord = PasswordEncrypt.encrypt(adminUser.getUser().getUsUserPwd(), "SHA", "UTF-8").trim();
			adminUser.getUser().setUsUserPwd(shaPassWord);
		}
		if (CommonValidator.isEqual(adminUser.getUser().getUsUsersType(), EPage.Student.name()))
		{
			School school = adminDAO.getSchoolById(new JQueryDataTableParam(adminUser.getUser().getUsSchoolId()));
			adminUser.getUser().setSchool(school);
			List<SerialKeyGenerate> validatedSerialKeyList = keyGenDAO.getValidatedSerialKeyList(new SerialKeyParam(adminUser.getUser().getUsSerialKey()));
			if (CommonValidator.isNotNullNotEmpty(validatedSerialKeyList))
			{
				adminUser.getUser().setSerialKey(validatedSerialKeyList.iterator().next());
			}
		}
		else
		{
			adminUser.getUser().setUsSchoolId(null);
			adminUser.getUser().setUsSerialKey(null);
			adminUser.getUser().setSchool(null);
			adminUser.getUser().setSerialKey(null);
		}
		return adminDAO.updateUser(adminUser);
	}

	public boolean userRegistration(AdminUserManagementAction adminUser) throws CustomException
	{
		if (CommonValidator.isListFirstNotEmpty(adminDAO.getUsersListByMobileNo(adminUser)) == false)
		{
			if (CommonValidator.isEqual("Auto-Generated", adminUser.getUser().getUsUserID()))
			{
				// AutoGenerated Id From Database Table
				String usUserID = adminDAO.getAutoGeneratedId(EPage.valueOf(adminUser.getUser().getUsUsersType()), adminUser.getRequest());
				if (CommonValidator.isNotNullNotEmpty(usUserID))
				{
					adminUser.getUser().setUsUserID(usUserID);
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}

			adminUser.getUser().setUsEmployeeId(EGenerate.valueOf(adminUser.getUser().getUsUsersType()).getPrimaryId());
			if (CommonValidator.isNotNullNotEmpty(adminUser.getUser().getUsUserPwd()))
			{
				caLogger.info(Audit_Logging_Event_DataEntry, "Password Assigned By User", adminUser.getUser().getUsUserPwd(), adminUser.getUser().getUsUserID(), null);
				adminUser.getUser().setUsUserPwdOriginal(adminUser.getUser().getUsUserPwd());
				String shaPassWord = PasswordEncrypt.encrypt(adminUser.getUser().getUsUserPwd(), "SHA", "UTF-8").trim();
				adminUser.getUser().setUsUserPwd(shaPassWord);
			}
			else
			{
				if (CommonValidator.isNotNullNotEmpty(adminUser.getUser().getUsMobileNo()))
				{
					adminUser.getUser().setUsUserPwdOriginal(adminUser.getUser().getUsMobileNo());
					String shaPassWord = PasswordEncrypt.encrypt(adminUser.getUser().getUsMobileNo(), "SHA", "UTF-8").trim();
					adminUser.getUser().setUsUserPwd(shaPassWord);
				}
				else
				{
					String autoGenPassword = PwdGenerator.getPassword();
					caLogger.info(Audit_Logging_Event_DataEntry, "Password Assigned By System", autoGenPassword, adminUser.getUser().getUsUserID(), null);
					adminUser.getUser().setUsUserPwdOriginal(autoGenPassword);
					String shaPassWord = PasswordEncrypt.encrypt(autoGenPassword, "SHA", "UTF-8").trim();
					adminUser.getUser().setUsUserPwd(shaPassWord);
				}
			}
			ImageDataVO imageDataVO = new ImageDataVO();
			imageDataVO.subFolderStructure = adminUser.getUser().getUsUsersType();
			adminUser.saveAttachment(imageDataVO, adminUser.getUser());

			if (CommonValidator.isEqual(adminUser.getUser().getUsUsersType(), EPage.Student.name()))
			{
				School school = adminDAO.getSchoolById(new JQueryDataTableParam(adminUser.getUser().getUsSchoolId()));
				adminUser.getUser().setSchool(school);

				SerialKeyParam skParam = new SerialKeyParam(adminUser.getUser().getUsSerialKey());

				List<SerialKeyGenerate> validatedSerialKeyList = keyGenDAO.getValidatedSerialKeyList(skParam);
				if (CommonValidator.isListFirstNotEmpty(validatedSerialKeyList))
				{
					SerialKeyGenerate validatedSerialKey = validatedSerialKeyList.iterator().next();
					adminUser.getUser().setSerialKey(validatedSerialKey);
					adminUser.getUser().setUsBatch(validatedSerialKey.getSerialBatch());
				}
			}
			else
			{
				adminUser.getUser().setUsSchoolId(null);
				adminUser.getUser().setUsSerialKey(null);
				adminUser.getUser().setSchool(null);
				adminUser.getUser().setSerialKey(null);
			}

			// Assign Auto Generated Employee Id
			adminUser.getUser().setUsEmployeeId(EGenerate.valueOf(adminUser.getUser().getUsUsersType()).getPrimaryId());
			return adminDAO.userRegistration(adminUser);
		}
		else
		{
			throw new CustomException("Username : " + adminUser.getUser().getUsUserName() + " Or Mobile number : " + adminUser.getUser().getUsMobileNo()
					+ " is already registered. Please contact Edutel Acadmey Administrator for further assistance.");
		}
	}

	public boolean saveStudentMarkEnroll(DashBoardAction dashBoardAction)
	{
		return adminDAO.saveStudentMarkEnroll(dashBoardAction);
	}

	public List<StudentsMarks> getStudentMarksList(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getStudentMarksList(jdtParam);
	}

	public List<StudentsMarks> getStudentMarksListAll(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getStudentMarksListAll(jdtParam);
	}

	public List<StudentsMarks> getStudentMarksUpdatingList(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getStudentMarksUpdatingList(jdtParam);
	}

	public StudentsMarks updateStudentHSCMark(String employeeId, String HSCMark)
	{
		return adminDAO.updateStudentHSCMark(employeeId, HSCMark);
	}

	public List<StudentsMarks> getStudentMarkByEmpId(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getStudentMarkByEmpId(jdtParam);
	}

	public boolean saveStudentHSCMarkUpdate(AdminUserManagementAction adminUserManagementAction)
	{
		return adminDAO.saveStudentHSCMarkUpdate(adminUserManagementAction);
	}

	public boolean validatedAnswersBulkUpload(OnlineExamQuestionAction oeQuestionAction)
	{
		System.out.println(oeQuestionAction.getAnswerFileName().size());
		oeQuestionAction.setOeTestSeriesExamQuesAnsMappingArray(new OnlineTestSeriesExamQuestionAnswerMapping[oeQuestionAction.getAnswerFileName().size()]);
		oeQuestionAction.setOeTestSeriesExamQuesAnswerArray(new OnlineTestSeriesExamQuestionAnswerMapping[oeQuestionAction.getAnswerFileName().size()]);

		OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesExamQuestionAnswerMapping = null;

		int fileId = 0;
		boolean oeValidated = true;
		for (String ansFileName : oeQuestionAction.getAnswerFileName())
		{
			oeTestSeriesExamQuestionAnswerMapping = new OnlineTestSeriesExamQuestionAnswerMapping();
			oeTestSeriesExamQuestionAnswerMapping.setOnlineExamQuestion(oeQuestionAction.getOnlineExamQuestion());
			oeTestSeriesExamQuestionAnswerMapping.setOeValidatedAnswer(oeValidated);
			oeTestSeriesExamQuestionAnswerMapping.setOeTestSeriesAnswerVIA("Online");

			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.sSearch = oeQuestionAction.getAnswerId().get(fileId);
			oeQuestionAction.setOnlineTestSeriesExamAnswer(adminDAO.getTestSeriesExamAnswerById(jdtParam));

			oeQuestionAction.getOeTestSeriesExamQuesAnswerArray()[fileId] = oeQuestionAction.getOnlineTestSeriesExamAnswer();

			oeTestSeriesExamQuestionAnswerMapping.setCreatedBy(oeQuestionAction.getOnlineTestSeriesExamAnswer().getCreatedBy());
			oeTestSeriesExamQuestionAnswerMapping.setCreatedDate(oeQuestionAction.getOnlineTestSeriesExamAnswer().getCreatedDate());

			oeTestSeriesExamQuestionAnswerMapping.setModifiedBy(oeQuestionAction.getUsEmployeeId());
			oeTestSeriesExamQuestionAnswerMapping.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			oeQuestionAction.getOeTestSeriesExamQuesAnsMappingArray()[fileId++] = oeTestSeriesExamQuestionAnswerMapping;

		}

		ImageDataVO imageDataVO = new ImageDataVO();

		if (oeValidated)
		{
			imageDataVO.subFolderStructure = EExamType.TestSeries.name() + "/" + oeQuestionAction.getOeQuestionId() + "/" + oeQuestionAction.getUsEmployeeId() + "/Validated";
		}

		oeQuestionAction.saveAttachment(imageDataVO, oeQuestionAction.getOeTestSeriesExamQuesAnsMappingArray());

		return adminDAO.validatedAnswersBulkUpload(oeQuestionAction);
	}

	public List<OnlineTestSeriesExamQuestionAnswerMapping> getTestSeriesExamValidatedAnswersListByCreatedBy(JQueryDataTableParam jdtParam)
	{
		return adminDAO.getTestSeriesExamValidatedAnswersListByCreatedBy(jdtParam);
	}

	public boolean updateDownloadStatusForTestSeriesAnswerMapping(OnlineTestSeriesExamQuestionAnswerMapping oeValidatedAnswer)
	{
		return adminDAO.updateDownloadStatusForTestSeriesAnswerMapping(oeValidatedAnswer);
	}

	public List<IAuditLogging> getPracticeExamResultList(AuditLoggingUDParam adtParam)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<IAuditLogging> getUserActivityList(AuditLoggingUDParam adtParam)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
