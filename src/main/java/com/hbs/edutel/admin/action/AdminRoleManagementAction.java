package com.hbs.edutel.admin.action;

import java.util.Set;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.interfaces.IUserRoles;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;

/**
 * This class is used to edit the User roles
 * 
 * @author chveera
 */
public class AdminRoleManagementAction extends AdminRoleManagementActionData
{
	private static final long	serialVersionUID	= -6894006222592669452L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(AdminRoleManagementAction.class);

	/**
	 * method to update the User roles based on selection
	 * 
	 * @return
	 */
	public String assignNewUserRoles()
	{
		try
		{
			String[] selectedRoles = request.getParameterValues("selectedRoles");
			String gsiRoleId = userBo.getRoleByRoleName(SUPER_ADMIN_ROLE_NAME).getRlRoleId();

			if (userBo.saveUserRoles(user.getUsEmployeeId(), selectedRoles, gsiRoleId))
				request.setAttribute(STATUS, "Roles Updated Successfully.");
			else
				throw new CustomException("userBo.saveUserRoles() returns false");
		}
		catch (Exception excep)
		{
			request.setAttribute(STATUS, "Failure occured while updating Roles.");
			caLogger.error(Audit_Logging_Event_DataEntry, "getAvailRolesList", excep.getMessage(), this.getClass().getName(), null);
		}
		return EPage.Success.name();
	}

	/**
	 * method used to get the user alloted roles and user has approver role permission or not
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAvailRolesList()
	{
		try
		{
			String employeeId = request.getParameter("userId");
			hasApprovalAuthority = userBo.checkApproverRole(employeeId);
			rolesList = userBo.getAvailRoles();
			avlRolesList.clear();
			if (CommonValidator.isNotNullNotEmpty(employeeId))
			{
				user = userBo.getUserByEmployeeId(employeeId);
				Set<IUserRoles> usrRoles = user.getUserRoleses();
				if (CommonValidator.isSetFirstNotEmpty(usrRoles))
				{
					for (IUserRoles usrRole : usrRoles)
						avlRolesList.add(usrRole.getRoles().getRlRoleName());
				}
			}
			if (avlRolesList.contains(SUPER_ADMIN_ROLE_NAME))
				avlRolesList.remove(SUPER_ADMIN_ROLE_NAME);
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DataEntry, "getAvailRolesList", excep.getMessage(), this.getClass().getName(), null);
		}
		return EPage.Success.name();
	}

	public String getUserDetailList()
	{
		try
		{
			String userId = request.getParameter("userId");
			if (CommonValidator.isNotNullNotEmpty(userId))
				userList = userBo.getUsers(userId);
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DataEntry, "getUserDetailList", excep.getMessage(), this.getClass().getName(), null);
		}
		return EPage.Success.name();
	}

	public String getUserPage()
	{
		return EPage.Success.name();
	}
}
