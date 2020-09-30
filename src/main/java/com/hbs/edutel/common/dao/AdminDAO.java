package com.hbs.edutel.common.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hbs.edutel.action.AdminCollegeManagementAction;
import com.hbs.edutel.action.AdminCollegeManagementActionData;
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
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.interfaces.IUsers;
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
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.hbs.edutel.model.School;
import com.hbs.edutel.model.SerialKeyUserMapping;
import com.hbs.edutel.model.StudentEnquiry;
import com.hbs.edutel.model.ToppersClub;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;

public interface AdminDAO
{

	public OnlineExam blockUnlockExam(String oeExamId, String status, String usEmployeeId);

	public OnlineExamAssigned blockUnlockExamAssigned(String oeExamAutoId, String status, String modifiedBy);

	public OnlineExamQuestion blockUnlockExamQuestion(String oeExamQuestionId, String status, String modifiedBy);

	public List<CollegeEForm> collegeEFormListByJson(JQueryDataTableParam jdtParam);

	public List<CollegeEForm> collegeEFormListByJsonAll(JQueryDataTableParam jdtParam);

	public List<CollegeEForm> collegeEFormOrderedList(IUsers sessionUser);

	public boolean collegeRegistration(AdminCollegeManagementActionData adminCollegeManagementAction);

	public boolean createInformationAlert(InformationAlerts infoAlertMsg);

	public boolean createOnlineExam(OnlineExamAction adminExam);

	public boolean createOnlineExamAssigned(OnlineExamAssignedAction adminExam);

	public boolean createOnlineExamQuestion(OnlineExamQuestionAdminAction adminExamQuestion);

	public boolean createOnlinePractiseExam(IOnlineExamAction adminExam);

	public boolean createOnlineTestSeriesExamAnswer(OnlineExamQuestionAdminAction oeQuestionAction);

	public boolean createToppersClubInfo(ToppersClub toppersClub);

	public College deleteCollege(AdminCollegeManagementAction adminCollegeManagementAction);

	public boolean deleteEdutelEnquiry(AdminStudentEnquiryAction adminStudentEnquiryAction);

	public CollegeEForm deleteEForm(AdminCollegeManagementAction adminCollegeManagementAction);

	public boolean deleteInfoAlert(AdminInfoAlertAction adminUserManagementAction);

	public OnlineEBluePrint deleteOnlineEBluePrint(String oEBluePrintId);

	public OnlineEBook deleteOnlineEBook(String oeBookId);

	public OnlineEKeyPoints deleteOnlineEKeyPoints(String oeKeyPointsId);

	public OnlineExam deleteOnlineExam(String oeExamId);

	public OnlineTestSeriesExamQuestionAnswerMapping deleteOnlineExamAnswer(OnlineExamQuestionAction onlineExamQuestionAction);

	public OnlineExamAssigned deleteOnlineExamAssigned(String oeExamAutoId);

	public List<OnlinePractiseExam> deleteOnlineExamPracticeEntry(IDashBoardAction dashBoardAction);

	public OnlineExamQuestion deleteOnlineExamQuestion(String oeExamQuestionId);

	public School deleteSchool(AdminSchoolManagementAction adminSchoolManagementAction);

	public ToppersClub deleteTopper(JQueryDataTableParam jdtParam);

	public IUsers deleteUser(AdminUserManagementAction adminUserManagementAction);

	public OnlineExam displayExamPublic(String oeExamId, boolean displayPublic, String modifiedBy);

	public List<IAuditLogging> getAuditLoggingDataList(AuditLoggingUDParam auditLoggingUDParam);

	public String getAutoGeneratedId(EPage ePage, HttpServletRequest request);

	public College getCollegeById(JQueryDataTableParam jdtParam);

	public List<CollegeEForm> getCollegeEFormById(AdminCollegeManagementAction adminCollegeManagementAction);

	public List<College> getCollegesList(JQueryDataTableParam jdtParam);

	public List<College> getCollegesListAll(JQueryDataTableParam jdtParam);

	public List<StudentEnquiry> getEnquiryList(JQueryDataTableParam jdtParam);

	public List<String> getExamIdListFromPractiseTable(IDashBoardAction dashBoardAction);

	public List<InformationAlerts> getInfoAlertList(JQueryDataTableParam jdtParam);

	public List<InformationAlerts> getInfoAlertListAll(JQueryDataTableParam jdtParam);

	public List<OnlineEBluePrint> getOnlineEBluePrintListByJson(JQueryDataTableParam jdtParam);

	public List<OnlineEBluePrint> getOnlineEBluePrintListByJsonAll(JQueryDataTableParam jdtParam);

	public List<OnlineEBook> getOnlineEBookListByJson(JQueryDataTableParam jdtParam);

	public List<OnlineEBook> getOnlineEBookListByJsonAll(JQueryDataTableParam jdtParam);

	public List<OnlineEKeyPoints> getOnlineEKeyPointsListByJson(JQueryDataTableParam jdtParam);

	public List<OnlineEKeyPoints> getOnlineEKeyPointsListByJsonAll(JQueryDataTableParam jdtParam);

	public List<OnlineExamAssigned> getOnlineExamAssignedList(IDashBoardAction dashBoardAction);

	public List<OnlineExamAssigned> getOnlineExamAssignedList(JQueryDataTableParam jdtParam);

	public List<OnlineExamAssigned> getOnlineExamAssignedListByJson(JQueryDataTableParam jdtParam);

	public List<OnlineExamAssigned> getOnlineExamAssignedListByJsonAll(JQueryDataTableParam jdtParam);

	public List<OnlineExam> getOnlineExamList(IDashBoardAction dashBoardAction);

	public List<OnlineExam> getOnlineExamListByJson(JQueryDataTableParam jdtParam);

	public List<OnlineExam> getOnlineExamListByJsonAll(JQueryDataTableParam jdtParam);

	public OnlineExamQuestion getOnlineExamQuestion(OnlineExamQuestionAdminAction onlineExamQuestionAction);

	public List<OnlineExamQuestion> getOnlineExamQuestionList(OnlineExamQuestionAdminAction onlineExamQuestionAction);

	public List<OnlineExamQuestion> getOnlineExamQuestionListByJson(JQueryDataTableParam jdtParam);

	public List<OnlineExamQuestion> getOnlineExamQuestionListByJsonAll(JQueryDataTableParam jdtParam);

	public List<OnlinePractiseExam> getOnlinePractiseExamList(IDashBoardAction dashBoardAction);

	public List<OnlinePractiseExam> getOnlinePractiseExamList(OnlineExamQuestionAdminAction onlineExamQuestionAction);

	public List<OnlineExamQuestion> getOnlineTestSeriesExamQuestionListByJson(JQueryDataTableParam jdtParam);

	public List<OnlineExamQuestion> getOnlineTestSeriesExamQuestionListByJsonAll(JQueryDataTableParam jdtParam);

	public OnlineTestSeriesExamQuestionAnswerMapping getOnlineTestSeriesQuestionAnswer(JQueryDataTableParam jdtParam);

	public List<OnlineTestSeriesExamQuestionAnswerMapping> getOnlineTestSeriesQuestionAnswerList(OnlineExamQuestionAdminAction oeQuestionAction);

	public List<CollegeEForm> getOrderedEFormList(AdminCollegeManagementAction adminCollegeManagementAction);

	public School getSchoolById(JQueryDataTableParam jdtParam);

	public List<School> getSchoolsList(JQueryDataTableParam jdtParam);

	public List<School> getSchoolsListAll(JQueryDataTableParam jdtParam);

	public OnlineTestSeriesExamQuestionAnswerMapping getTestSeriesExamAnswerByCreatedBy(JQueryDataTableParam jdtParam);

	public OnlineTestSeriesExamQuestionAnswerMapping getTestSeriesExamAnswerById(JQueryDataTableParam jdtParam);

	public List<ToppersClub> getToppersClubList(JQueryDataTableParam jdtParam);

	public List<ToppersClub> getToppersClubListAll(JQueryDataTableParam jdtParam);

	public List<ToppersClub> getToppersClubListByMarks(String lastExamName);

	public IUsers getUserById(JQueryDataTableParam jdtParam);

	public List<IUsers> getUsersList(JQueryDataTableParam jdtParam);

	public List<IUsers> getUsersListAll(JQueryDataTableParam jdtParam);

	public List<IUsers> getUsersListByMobileNo(AdminUserManagementAction adminUser) throws CustomException;

	public boolean isExamTakenAlready(JQueryDataTableParam jdtParam);

	public List<OnlineEBluePrint> onlineEBluePrintListByAjax(JQueryDataTableParam jdtParam);

	public boolean onlineEBluePrintRepositoryUpload(OnlineEBluePrintAction adminEBluePrint);

	public List<OnlineEBook> onlineEBookListByAjax(JQueryDataTableParam jdtParam);

	public boolean onlineEBookRepositoryUpload(OnlineEBookAction onlineEBookAction);

	public List<OnlineEKeyPoints> onlineEKeyPointsListByAjax(JQueryDataTableParam jdtParam);

	public boolean onlineEKeyPointsRepositoryUpload(OnlineEKeyPointsAction onlineEKeyPointsAction);

	public boolean orderEForm(AdminCollegeManagementAction adminCollegeManagementAction);

	public boolean resetPassword(AdminUserManagementAction adminUser);

	public boolean schoolRegistration(AdminSchoolManagementAction adminSchoolManagementAction);

	public String searchSchoolName(JQueryDataTableParam jdtParam);

	public boolean studentEdutelEnquiry(AdminStudentEnquiryAction adminStudentEnquiryAction);

	public boolean updateCollege(AdminCollegeManagementAction adminCollegeManagementAction);

	public boolean updateSchool(AdminSchoolManagementAction adminSchoolManagementAction);

	public boolean updateUser(AdminUserManagementAction adminUserManagementAction);

	public boolean userRegistration(AdminUserManagementAction adminUserManagementAction);

	public boolean saveStudentMarkEnroll(DashBoardAction dashBoardAction);

	public List<StudentsMarks> getStudentMarksList(JQueryDataTableParam jdtParam);

	public List<StudentsMarks> getStudentMarksListAll(JQueryDataTableParam jdtParam);

	public List<StudentsMarks> getStudentMarksUpdatingList(JQueryDataTableParam jdtParam);

	public StudentsMarks updateStudentHSCMark(String employeeId, String HSCMark);

	public List<StudentsMarks> getStudentMarkByEmpId(JQueryDataTableParam jdtParam);

	public boolean saveStudentHSCMarkUpdate(AdminUserManagementAction adminUserManagementAction);

	public List<StudentsMarks> getStudentMarkByBatch(JQueryDataTableParam jdtParam);

	public List<SerialKeyUserMapping> getSerialKeyByEmpId(JQueryDataTableParam jdtParam);

	public boolean validatedAnswersBulkUpload(OnlineExamQuestionAction oeQuestionAction);

	public boolean updateStduentTestSeriesAnswers(OnlineExamQuestionAction oeQuestionAction);

	public List<OnlineTestSeriesExamQuestionAnswerMapping> getTestSeriesExamValidatedAnswersListByCreatedBy(JQueryDataTableParam jdtParam);

	public boolean updateDownloadStatusForTestSeriesAnswerMapping(OnlineTestSeriesExamQuestionAnswerMapping oeValidatedAnswer);

}