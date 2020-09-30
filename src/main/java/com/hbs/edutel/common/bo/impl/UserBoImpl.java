package com.hbs.edutel.common.bo.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.codec.binary.Hex;

import com.hbs.edutel.action.AdminStudentEnquiryAction;
import com.hbs.edutel.action.AdminUserManagementAction;
import com.hbs.edutel.admin.action.PasswordEncrypt;
import com.hbs.edutel.common.action.CommonActionDAOData;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.ReminderQABean;
import com.hbs.edutel.common.bo.UserBo;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.StudentEnquiry;
import com.hbs.edutel.util.JQueryDataTableParam;

public class UserBoImpl extends CommonActionDAOData implements UserBo
{
	
	public void changeShowAppStatus(IUsers users)
	{
		userDAO.changeShowAppStatus(users);
	}

	
	public boolean changeUserPassword(String usEmployeeId, String userId, String newPwd)
	{
		return userDAO.changeUserPassword(usEmployeeId, userId, newPwd);
	}

	
	public boolean checkApproverRole(String userId)
	{
		return userDAO.checkApproverRole(userId);
	}

	
	public boolean checkFirstLogin(String userId)
	{
		return userDAO.checkFirstLogin(userId);
	}

	
	public boolean deleteEdutelEnquiry(AdminStudentEnquiryAction adminStudentEnquiryAction)
	{
		return userDAO.deleteEdutelEnquiry(adminStudentEnquiryAction);
	}

	
	public boolean deleteUserDetails(String usEmployeeId)
	{
		return userDAO.deleteUserDetails(usEmployeeId);
	}

	
	public List<IRoles> getAvailRoles()
	{
		return userDAO.getAvailRoles();
	}

	
	public List<IRoles> getAvailRoles(String[] rlRoleType)
	{
		return userDAO.getAvailRoles(rlRoleType);
	}

	
	public List<StudentEnquiry> getEnquiryList(JQueryDataTableParam jdtParam)
	{
		return userDAO.getEnquiryList(jdtParam);
	}

	
	public List<StudentEnquiry> getEnquiryListAll(JQueryDataTableParam jdtParam)
	{
		return userDAO.getEnquiryListAll(jdtParam);
	}

	
	public IRoles getRoleByRoleName(String roleName)
	{
		return userDAO.getRoleByRoleName(roleName);
	}

	
	public IUsers getUser(String userId)
	{
		List<IUsers> usersList = userDAO.getUsers(userId);
		if (CommonValidator.isListFirstNotEmpty(usersList))
			return usersList.iterator().next();
		else
			return null;
	}

	
	public IUsers getUser(String userId, String passWord) throws CustomException
	{
		String shaPassWord = PasswordEncrypt.encrypt(passWord, "SHA", "UTF-8").trim();
		String hexPassWord = new String(Hex.encodeHex(passWord.getBytes()));
		List<IUsers> userList = userDAO.getLoginUsers(userId);
		IUsers user = null;
		if (CommonValidator.isListFirstNotEmpty(userList))
			user = userList.iterator().next();

		if (user != null && (user.getUsUserPwd().equals(shaPassWord) || user.getUsUserPwd().equals(hexPassWord)))
		{
			return user;
		}

		throw new CustomException("Login Id or Password is Incorrect");
	}

	
	public IUsers getUserByEmployeeId(String employeeId)
	{
		return userDAO.getUserByEmployeeId(employeeId);
	}

	
	public IUsers getUserByLoginColumn(String userId) throws CustomException
	{
		List<IUsers> userList = userDAO.getLoginUsers(userId);
		if (CommonValidator.isListFirstNotEmpty(userList))
			return userList.iterator().next();
		throw new CustomException("Login Id or Password is Incorrect");
	}

	
	public List<IUsers> getUserByRole(String rlRoleId)
	{
		return userDAO.getUsersListByRoleId(rlRoleId);
	}

	
	public IUsers getUserByUserId(String userId)
	{
		List<IUsers> usersList = userDAO.getUsersList(userId, "usUserId");
		if (CommonValidator.isListFirstNotEmpty(usersList))
			return usersList.iterator().next();
		else
			return null;
	}

	
	public List<IUsers> getUsers(String userId)
	{
		return userDAO.getUsers(userId);
	}

	
	public List<IUsers> getUsersList()
	{
		return userDAO.getUsersList();
	}

	
	public List<IUsers> getUsersList(JQueryDataTableParam jdtParam)
	{
		return userDAO.getUsersList(jdtParam);
	}

	
	public boolean saveUserDetails(IUsers user)
	{
		return userDAO.saveUserDetails(user);
	}

	
	public boolean saveUserRoles(String usEmployeeId, String[] selectedRoles, String gsiRoleId)
	{
		return userDAO.saveUserRoles(usEmployeeId, selectedRoles, gsiRoleId);
	}

	
	public void sendPasswordMail(String email, String pwd, String ipAddr)
	{
		userDAO.sendPasswordMail(email, pwd, ipAddr);
	}

	
	public void spChangeEmailAdress(String oldUserId, String newUserId, int log_count)
	{
		userDAO.spChangeEmailAdress(oldUserId, newUserId, log_count);
	}

	
	public boolean studentEdutelEnquiry(AdminStudentEnquiryAction adminStudentEnquiryAction)
	{
		return userDAO.studentEdutelEnquiry(adminStudentEnquiryAction);
	}

	
	public boolean updateUserDetails(IUsers user)
	{
		return userDAO.updateUserDetails(user);
	}

	
	public boolean updateUserPasswordAndSendEmail(String usEmployeeId, String userId, String newPwd, String modifiedBy, Timestamp modDate)
	{
		return userDAO.updateUserPasswordAndSendEmail(usEmployeeId, userId, newPwd, modifiedBy, modDate);
	}

	
	public boolean updateUserReminderQuery(IUserReminderQuery urq)
	{
		return userDAO.updateUserReminderQuery(urq);
	}

	
	public void userLogAtLogin(IUsers user, String ipAddr)
	{
		userDAO.userLogAtLogin(user, ipAddr);

	}

	
	public void userLogAtLogOut(IUsers user)
	{
		userDAO.userLogAtLogOut(user);
	}

	
	public int userLogCount(String usEmployeeId)
	{
		return userDAO.userLogCount(usEmployeeId);
	}

	
	public boolean userRegistration(AdminUserManagementAction adminUserManagementAction)
	{
		return userDAO.userRegistration(adminUserManagementAction);
	}

	
	public ReminderQABean userSecurityQuestion(String usEmployeeId)
	{
		return userDAO.userSecurityQuestion(usEmployeeId);
	}

	
	public StudentsMarks getStudentMarkByEmpId(String usEmployeeId)
	{
		return userDAO.getStudentMarkByEmpId(usEmployeeId);
	}

}
