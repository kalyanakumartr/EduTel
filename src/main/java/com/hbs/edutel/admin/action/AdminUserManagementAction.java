package com.hbs.edutel.admin.action;

import java.util.Date;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.PwdGenerator;
import com.hbs.edutel.util.common.ConstEnumUtil.EAuthType;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.factory.UsersFactory;

/**
 * This class is used for search,add ,update,activate/deactivate user
 */
public class AdminUserManagementAction extends AdminUserManagementActionData
{
	private static final long		serialVersionUID	= 7482526278838998688L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(AdminUserManagementAction.class);

	public String adminHasApproverTier()
	{
		return EPage.Success.name();
	}

	/**
	 * method is used to display user security questions in change password screen scenarios are
	 * below in code
	 * 
	 * @return String - status message
	 * @throws Exception
	 */
	public String changePasswordByAdmin()
	{
		IUsers user = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		try
		{

			forgotPasswordAction.setUserId(user.getUsEmployeeId());
			if (CommonValidator.isEqual(forgotPasswordAction.getSecurityQuestion(), EPage.Success.name()))
			{
				question = forgotPasswordAction.getQuestion();
				answer = forgotPasswordAction.getAnswer();
				String[] questionArr = Users_Reminder_Queries_Questions.split(",");
				for (String question : questionArr)
				{
					reminderQues.put(question, question.replace("-", " ") + "?");
				}
				userPassWord = request.getSession().getAttribute("userPassword").toString();
			}
			else
				addActionMessage("System uses NT Password, You cannot change NT password here!");

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DataEntry, "changePasswordByAdmin", excep.getMessage(), AdminUserManagementAction.class.getName(), user.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	public String deleteUsersDetails()
	{
		IUsers user = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		try
		{
			String usEmployeeId = request.getParameter("usEmployeeId");

			if (userBo.deleteUserDetails(usEmployeeId))
				request.setAttribute(STATUS, "User deleted Successfully.");
			else
				throw new CustomException("userBo.deleteUserDetails() returns false");
		}
		catch (Exception excep)
		{
			request.setAttribute(STATUS, "Failure occurred while deleting user.");
			caLogger.error(Audit_Logging_Event_DataEntry, "deleteUsersDetails", excep.getMessage(), AdminUserManagementAction.class.getName(), user.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	/**
	 * method is used to check whether user has approver role or not
	 * 
	 * @return rolesList
	 */
	public String getUserRoles()
	{
		IUsers user = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		try
		{
			hasApprovalAuthority = userBo.checkApproverRole(request.getParameter("userId"));
			rolesList = userBo.getAvailRoles();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DataEntry, "getUsersListBySearch", excep.getMessage(), AdminUserManagementAction.class.getName(), user.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	/**
	 * method is used to Re-set the user password in Portal and argo db
	 * 
	 * @return String - status message
	 * @throws Exception
	 */
	public String resetPasswordByAdmin()
	{
		IUsers user = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		try
		{
			String userId = request.getParameter("userId");
			forgotPasswordAction.setUserId(userId);
			forgotPasswordAction.setAnswer("AdminResetPassword");
			forgotPasswordAction.setQuesAnswer("AdminResetPassword");

			if (CommonValidator.isEqual(forgotPasswordAction.resetUserPassword(), EPage.Success.name()))
				request.setAttribute(STATUS, "Password has been resetted Successfully for the User Id : " + userId);
			else
				throw new CustomException("Failure occur while resetting user password by Admin for the UserId : " + userId);
		}
		catch (Exception excep)
		{
			request.setAttribute(STATUS, "Failure occurred while resetting user password by Admin.");
			caLogger.error(Audit_Logging_Event_DataEntry, "resetPasswordByAdmin", excep.getMessage(), AdminUserManagementAction.class.getName(), user.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	/**
	 * method is used to save the user details in Portal scenarios are below in code
	 * 
	 * @return String - status message
	 */
	public String saveUsersDetails()
	{
		IUsers usr = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		try
		{

			boolean saveOrUpdateResult = false;
			String password = PwdGenerator.getPassword();
			String userId = request.getParameter("userId");
			String userName = request.getParameter("userName");
			String empId = request.getParameter("empId");

			IUsers user = UsersFactory.getInstance().getUsersInstance();
			user.setUsEmployeeId(empId);
			user.setUsUserName(userName);
			user.setUsUserID(userId);
			user.setUsUserPwd(password);
			// if its value is zero then the approver profile page will open
			user.setUsCreatedBy(usr.getUsEmployeeId().toString());
			user.setUsModifiedBy(usr.getUsEmployeeId().toString());
			user.setUsCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			user.setUsModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			String authType = propFactory.getProperty(EGeneral.Authentication_Type);

			IUsers extUser = userBo.getUser(userId);
			if (EAuthType.valueOf(authType) == EAuthType.DB)
			{
				if (extUser != null)
				{
					request.setAttribute(STATUS, "User already exists.");
					return EPage.Success.name();
				}
				else
				{
					saveOrUpdateResult = userBo.saveUserDetails(user);
				}
			}
		}
		catch (Exception excep)
		{
			request.setAttribute(STATUS, "Adding User Failed.");
			caLogger.error(Audit_Logging_Event_DataEntry, "saveUsersDetails", excep.getMessage(), AdminUserManagementAction.class.getName(), usr.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	public boolean subSaveUserDetails(IUsers usr, IUsers user, IUsers extUser)
	{
		boolean saveOrUpdateResult;
		if (extUser != null)
		{
			extUser.setUsEmployeeId(user.getUsEmployeeId());
			extUser.setUsUserName(user.getUsUserName());
			extUser.setUsUserID(user.getUsUserID());
			extUser.setUsUserPwd(user.getUsUserPwd());
			extUser.setUsCreatedBy(user.getUsEmployeeId().toString());
			extUser.setUsModifiedBy(user.getUsEmployeeId().toString());
			extUser.setUsCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			extUser.setUsModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			saveOrUpdateResult = userBo.saveUserDetails(extUser);
		}
		else
		{
			saveOrUpdateResult = userBo.saveUserDetails(user);
		}

		return saveOrUpdateResult;
	}

	/**
	 * method is used to update the user details in Portal and db
	 * 
	 * @return String - status message
	 * @throws Exception
	 */
	public String updateUsersDetails()
	{
		IUsers usr = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		try
		{
			EAuthType authType = EAuthType.valueOf(propFactory.getProperty(EGeneral.Authentication_Type));
			IUsers user = UsersFactory.getInstance().getUsersInstance();
			boolean status = Boolean.parseBoolean(request.getParameter(STATUS));
			String oldUserId = request.getParameter("oldUserId");// current userId
			String newUserId = request.getParameter("userId");// new userId
			String userName = request.getParameter("userName");
			String usEmployeeId = request.getParameter("empId");
			StringBuffer auaBuffer = new StringBuffer();

			IUsers extUser = userBo.getUserByUserId(oldUserId);

			// set the user object
			user.setUsEmployeeId(usEmployeeId);
			user.setUsUserName(userName);
			user.setUsStatus(status);
			user.setUsModifiedBy(usr.getUsEmployeeId().toString());
			user.setUsModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			user.setUsUserPwd(extUser.getUsUserPwd());

			// check if old user and new user same or not.
			if (CommonValidator.isEqual(oldUserId, newUserId))
			{
				user.setUsUserID(oldUserId);

				if (authType == EAuthType.DB)
				{
					if (userBo.updateUserDetails(user))
					{
						request.setAttribute(STATUS, "User updated successfully.");

					}
					else
					{
						request.setAttribute(STATUS, "Updating user failure in Database.");
					}
				}
				else
					request.setAttribute(STATUS, "User not registered.");
			}
			else
			{
				// check new user in local DB or not
				IUsers newUser = userBo.getUser(newUserId);
				if (newUser != null)
				{
					request.setAttribute(STATUS, "New Email-Id already exists.");
				}
				else
				{
					// update new email id instead of old email id
					user.setUsUserID(newUserId);
					if (authType == EAuthType.DB && userBo.updateUserDetails(user))
					{
						request.setAttribute(STATUS, "User updated successfully.");

						int log_count = userBo.userLogCount(usEmployeeId);
						// update user email address
						userBo.spChangeEmailAdress(oldUserId, newUserId, log_count);
					}
					else
					{
						request.setAttribute(STATUS, "Update User Failed.");
					}
				}
			}
		}
		catch (Exception excep)
		{
			request.setAttribute(STATUS, "Failure occurred while updating user details.");
			caLogger.error(Audit_Logging_Event_DataEntry, "updateUsersDetails", excep.getMessage(), AdminUserManagementAction.class.getName(), usr.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	public String userLoginStatus()
	{
		IUsers user = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		try
		{
			String usEmployeeId = request.getParameter("usEmployeeId");
			request.setAttribute(STATUS, userBo.userLogCount(usEmployeeId));
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DataEntry, "userLoginStatus", excep.getMessage(), AdminUserManagementAction.class.getName(), user.getUsEmployeeId());
		}
		return EPage.Success.name();
	}
}
