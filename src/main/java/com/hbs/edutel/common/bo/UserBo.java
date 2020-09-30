package com.hbs.edutel.common.bo;

import java.sql.Timestamp;
import java.util.List;

import com.hbs.edutel.action.AdminStudentEnquiryAction;
import com.hbs.edutel.action.AdminUserManagementAction;
import com.hbs.edutel.common.action.login.ReminderQABean;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.StudentEnquiry;
import com.hbs.edutel.util.JQueryDataTableParam;

public interface UserBo
{
	public void changeShowAppStatus(IUsers users);

	public boolean changeUserPassword(String usEmployeeId, String userId, String newPwd);

	public boolean checkApproverRole(String userId);

	public boolean checkFirstLogin(String userId);

	public boolean deleteEdutelEnquiry(AdminStudentEnquiryAction adminStudentEnquiryAction);

	public boolean deleteUserDetails(String usEmployeeId);

	public List<IRoles> getAvailRoles();

	public List<IRoles> getAvailRoles(String[] rlRoleType);

	public List<StudentEnquiry> getEnquiryList(JQueryDataTableParam jdtParam);

	public List<StudentEnquiry> getEnquiryListAll(JQueryDataTableParam jdtParam);

	public IRoles getRoleByRoleName(String roleName);

	public IUsers getUser(String userId);

	public IUsers getUser(String userId, String passWord) throws CustomException;

	public IUsers getUserByEmployeeId(String employeeId);

	public IUsers getUserByLoginColumn(String userId) throws CustomException;

	public List<IUsers> getUserByRole(String rlRoleId);

	public IUsers getUserByUserId(String userId);

	public List<IUsers> getUsers(String userId);

	public List<IUsers> getUsersList();

	public List<IUsers> getUsersList(JQueryDataTableParam jdtParam);

	public boolean saveUserDetails(IUsers user);

	public boolean saveUserRoles(String usEmployeeId, String[] selectedRoles, String gsiRoleId);

	public void sendPasswordMail(String email, String pwd, String ipAddr);

	public void spChangeEmailAdress(String oldUserId, String newUserId, int log_count);

	public boolean studentEdutelEnquiry(AdminStudentEnquiryAction adminStudentEnquiryAction);

	public boolean updateUserDetails(IUsers user);

	public boolean updateUserPasswordAndSendEmail(String usEmployeeId, String userId, String newPwd, String modifiedBy, Timestamp modDate);

	public boolean updateUserReminderQuery(IUserReminderQuery urq);

	public void userLogAtLogin(IUsers user, String ipAddr);

	public void userLogAtLogOut(IUsers user);

	public int userLogCount(String usEmployeeId);

	public boolean userRegistration(AdminUserManagementAction adminUserManagementAction);

	public ReminderQABean userSecurityQuestion(String usEmployeeId);

	public StudentsMarks getStudentMarkByEmpId(String usEmployeeId);

}
