package com.hbs.edutel.common.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.AdminStudentEnquiryAction;
import com.hbs.edutel.action.AdminUserManagementAction;
import com.hbs.edutel.admin.action.PasswordEncrypt;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.ReminderQABean;
import com.hbs.edutel.common.dao.UserDAO;
import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.UserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUserLog;
import com.hbs.edutel.common.model.interfaces.IUserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUserRoles;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.StudentEnquiry;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EGenerate;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ERole;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;
import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.factory.RolesFactory;
import com.hbs.edutel.util.common.factory.UserLogFactory;
import com.hbs.edutel.util.common.factory.UserRolesFactory;
import com.hbs.edutel.util.common.factory.UsersFactory;

public class UserDAOImpl extends CommonHibernateDaoSupport implements UserDAO, ConstInterface
{
	private static final long		serialVersionUID	= -6282872836045369567L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	
	public boolean changeShowAppStatus(IUsers users)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			session.saveOrUpdate(users);
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : changeShowAppStatus", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : changeShowAppStatus", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	
	public boolean changeUserPassword(String usEmployeeId, String userId, String newPwd)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			String encryptedPassword = PasswordEncrypt.encrypt(newPwd, "SHA", UTF_8).trim();

			StringBuffer sbUpdateQry = new StringBuffer();
			sbUpdateQry.append(" Update " + UsersFactory.getInstance().getUsersInstanceName() + " SET ");
			sbUpdateQry.append(" usUserPwd = " + EWrap.Quote.enclose(encryptedPassword) + WHERE_1_1);
			sbUpdateQry.append(" AND usEmployeeId = " + EWrap.Quote.enclose(usEmployeeId));
			sbUpdateQry.append(" AND usUserId = " + EWrap.Quote.enclose(userId));

			session.createQuery(sbUpdateQry.toString()).executeUpdate();
			_Txn.commit();

			String param = "";
			Query query = session.createSQLQuery(" { CALL sp_ChangePasswordEmail(:usrId,:newPwd,:param) }");
			query.setString("usrId", userId);
			query.setString("newPwd", newPwd);
			query.setString("param", param);
			query.executeUpdate();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : changeUserPassword", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : changeUserPassword", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	/**
	 * method is used to check the user has approver role or not
	 */
	
	public boolean checkApproverRole(String userEmailId)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + " ApprovalTable Bean Name" + WHERE_1_1);
			sbSelectQry.append(" AND maEmailAddress = ");
			sbSelectQry.append(EWrap.Quote.enclose(userEmailId));

			Query query = session.createQuery(sbSelectQry.toString());

			return (CommonValidator.isListFirstNotEmpty(query.list()));
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : checkApproverRole", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	
	public boolean checkFirstLogin(String userId)
	{
		Session session = null;
		try
		{
			session = getSession();
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + UserLogFactory.getInstance().getUserLogInstanceName() + WHERE_1_1);
			sbSelectQry.append(" AND users.usUserId = " + EWrap.Quote.enclose(userId));

			List<IUsers> userList = session.createQuery(sbSelectQry.toString()).list();
			if (CommonValidator.isListFirstNotEmpty(userList))
				return false;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOimpl:checkFirstLogin", excep.getMessage(), this.getClass().getName(), userId);
		}
		finally
		{
			if (session != null)
				session.close();
		}
		return true;

	}

	
	public boolean deleteEdutelEnquiry(AdminStudentEnquiryAction studEnq)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{

			String enquirerAutoId = studEnq.getRequest().getParameter("enquiryId");

			StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + StudentEnquiry.class.getCanonicalName() + WHERE_1_1);
			sbDeleteQry.append(" AND enquirerAutoId = " + EWrap.Quote.enclose(enquirerAutoId));

			session = getSession();
			_Txn = session.beginTransaction();
			session.createQuery(sbDeleteQry.toString()).executeUpdate();
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : deleteEdutelEnquiry", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : deleteEdutelEnquiry", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}

	}

	/**
	 * delete the user details
	 */
	
	public boolean deleteUserDetails(String usEmployeeId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			session.createQuery("Delete FROM " + UserRolesFactory.getInstance().getUserRolesInstanceName() + " Where users.usEmployeeId = " + EWrap.Quote.enclose(usEmployeeId)).executeUpdate();

			session.createQuery("Delete FROM " + UserLogFactory.getInstance().getUserLogInstanceName() + " Where users.usEmployeeId = " + EWrap.Quote.enclose(usEmployeeId)).executeUpdate();

			session.createQuery("Delete FROM " + UsersFactory.getInstance().getUsersInstanceName() + " Where usEmployeeId = " + EWrap.Quote.enclose(usEmployeeId)).executeUpdate();

			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : deleteUserDetails", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : deleteUserDetails", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	
	public List<IRoles> getAvailRoles()
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + RolesFactory.getInstance().getRolesInstanceName() + WHERE_1_1);
			sbSelectQry.append(" AND rlStatus = true AND rlRoleId Not In ");
			sbSelectQry.append(EWrap.Brace.enclose(EWrap.Quote.enclose(new String[] { ERole.Dummy.name(), ERole.SuperAdminRole.name() })));
			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getAvailRoles", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IRoles>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<IRoles> getAvailRoles(String[] rlRoleType)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + RolesFactory.getInstance().getRolesInstanceName() + WHERE_1_1);
			sbSelectQry.append(" AND rlRoleId <> 'Dummy' AND rlStatus = true AND rlRoleType In ");
			String roleIds = EWrap.Quote.enclose(rlRoleType) + COMMA_SPACE + EWrap.Quote.enclose(ERole.SuperAdminRole);
			sbSelectQry.append(EWrap.Brace.enclose(roleIds));

			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getAvailRoles", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IRoles>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<StudentEnquiry> getEnquiryList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && jdtParam.iDisplayLength != 0)
				{
					return session.createQuery(getEnquiryListQuery(jdtParam).toString()).setMaxResults(jdtParam.iDisplayLength).setFirstResult(jdtParam.iDisplayStart).list();
				}
				else
				{
					return session.createQuery(getEnquiryListQuery(jdtParam).toString()).list();

				}

			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " getEnquiryList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<StudentEnquiry>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<StudentEnquiry> getEnquiryListAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getEnquiryListQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " getEnquiryListAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<StudentEnquiry>(0);
	}

	private StringBuffer getEnquiryListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = new StringBuffer("");
		if (CommonValidator.isNotNullNotEmpty(jdtParam))
		{
			sbSelectQry.append(FROM + StudentEnquiry.class.getCanonicalName() + WHERE_1_1);
			if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
			{
				sbSelectQry.append(" AND enquirerName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR enquirerEmail Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR enquirerSchoolName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR enquirerMobileNo Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR enquirerBoard Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR enquirerClass Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR enquirerState Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR createdDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			}
			sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
		}
		return sbSelectQry;
	}

	
	public List<IUsers> getLoginUsers(String userId)
	{
		String userSelectionColName = getPropFactory().getProperty(EGeneral.User_Login_Column_Name);
		return getUsersList(userId, userSelectionColName);
	}

	
	public int[] getLoginUsersCount()
	{
		int count[] = new int[3];
		count[0] = getUserCountByUsersType(EPage.Student.name());
		count[1] = getUserCountByUsersType(EPage.Employee.name());
		count[2] = getUserCountByUsersType(EPage.Franchisee.name());
		return count;
	}

	
	@SuppressWarnings("unchecked")
	public IRoles getRoleByRoleName(String roleName)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + RolesFactory.getInstance().getRolesInstanceName() + WHERE_1_1);
			sbSelectQry.append(" AND rlRoleId <> 'Dummy' AND rlStatus = true AND rlRoleName = " + EWrap.Quote.enclose(roleName));

			List<IRoles> rolesList = session.createQuery(sbSelectQry.toString()).list();

			if (CommonValidator.isListFirstNotEmpty(rolesList))
				return rolesList.iterator().next();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getRoleByRoleName", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public IUsers getUserByEmployeeId(String usEmployeeId)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + UsersFactory.getInstance().getUsersInstanceName() + WHERE_1_1);
			sbSelectQry.append(" AND usEmployeeId = ");
			sbSelectQry.append(EWrap.Quote.enclose(usEmployeeId));
			List<IUsers> userList = session.createQuery(sbSelectQry.toString()).list();
			if (CommonValidator.isListFirstNotEmpty(userList))
				return userList.iterator().next();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getUserByEmployeeId", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;

	}

	private int getUserCountByUsersType(String usUsersType)
	{
		Session session = getSession();
		try
		{
			Timestamp currentDateTime = CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST);
			Date oneDayBack = new Date(currentDateTime.getTime() - 86400000);
			Timestamp oneDayDateTime = CommonUtil.getTimeZoneDateInFormat(oneDayBack, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST);

			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(SELECT_DISTINCT + "users.usEmployeeId" + FROM + UserLogFactory.getInstance().getUserLogInstanceName() + WHERE_1_1);
			sbSelectQry.append(" AND ulUserLogoutTime IS NULL AND users.usUsersType = " + EWrap.Quote.enclose(usUsersType));
			sbSelectQry.append(" AND ulUserLoginTime >= " + EWrap.Quote.enclose(oneDayDateTime.toString()));
			sbSelectQry.append(" AND ulUserLoginTime <= " + EWrap.Quote.enclose(currentDateTime.toString()));
			sbSelectQry.append(" Order By ulUserLoginTime Desc");
			return session.createQuery(sbSelectQry.toString()).list().size();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getLoginUsersCount", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return 0;
	}

	
	public List<IUsers> getUsers(String userId)
	{
		String userSelectionColName = getPropFactory().getProperty(EGeneral.User_Selection_Column_Name);
		return getUsersList(userId, userSelectionColName);
	}

	
	@SuppressWarnings("unchecked")
	public List<IUsers> getUsersList()
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQuery = new StringBuffer();
			sbSelectQuery.append(FROM + UsersFactory.getInstance().getUsersInstanceName() + WHERE_1_1);
			sbSelectQuery.append(" AND usStatus = True ");
			return session.createQuery(sbSelectQuery.toString()).list();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getUsersList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IUsers>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<IUsers> getUsersList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + UsersFactory.getInstance().getUsersInstanceName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usUserID Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usFrancheesArea Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usSerialKey Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usSchoolId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usGroupName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usMobileNo Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usCreatedBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				}
				sbSelectQry.append(" Order By usCreatedDate Desc ");
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " getUsersList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IUsers>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<IUsers> getUsersList(String userId, String userSelectionColName)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(userId))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + UsersFactory.getInstance().getUsersInstanceName() + WHERE_1_1);
				sbSelectQry.append(" AND " + userSelectionColName.trim() + " = ");
				sbSelectQry.append(EWrap.Quote.enclose(userId));
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOimpl:getUser", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IUsers>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<IUsers> getUsersListByRoleId(String rlRoleId)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append("Select USR From " + UsersFactory.getInstance().getUsersInstanceName() + " USR " + COMMA_SPACE);
			sbSelectQry.append(UserRolesFactory.getInstance().getUserRolesInstanceName() + " UR " + WHERE_1_1 + " AND UR.users.usStatus = True ");
			sbSelectQry.append(" AND USR.usEmployeeId = UR.users.usEmployeeId ");
			sbSelectQry.append(" AND UR.roles.rlRoleId = " + EWrap.Quote.enclose(rlRoleId));

			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getUsersListByRoleId", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IUsers>(0);
	}

	/**
	 * Save the User details in users table
	 */
	
	public boolean saveUserDetails(IUsers user)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			String password = user.getUsUserPwd();

			// PASSWORD ENCRYPTION
			user.setUsUserPwd(PasswordEncrypt.encrypt(password, "SHA", UTF_8).trim());
			session.saveOrUpdate(user);

			Query q = session.createSQLQuery(" { CALL sp_AccountCreateEmail(:usrName,:usrId,:userPwd,:usrType) }");
			q.setString("usrName", user.getUsUserName());
			q.setString("usrId", user.getUsUserID());
			q.setString("userPwd", password);
			q.executeUpdate();

			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : saveUserDetails", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : saveUserDetails", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	/**
	 * method is used to save/update the user roles if the user have GsiAdmin permission remove
	 * roles except GSI Admin role.
	 */
	
	public boolean saveUserRoles(String usEmployeeId, String[] selectedRoles, String gsiRoleId)
	{
		if (CommonValidator.isNotNullNotEmpty(selectedRoles))
		{
			Transaction _Txn = null;
			Session session = null;
			try
			{
				boolean isGSIUser = false;
				List<IUsers> gsiUsersList = getUsersListByRoleId(gsiRoleId);
				for (IUsers usr : gsiUsersList)
				{
					if (CommonValidator.isEqual(usr.getUsEmployeeId(), usEmployeeId))
					{
						isGSIUser = true;
						break;
					}
				}

				String adminRoleId = String.valueOf(getRoleByRoleName(ADMIN_ROLE_NAME).getRlRoleId());

				List<String> refactRolesList = new ArrayList<String>(Arrays.asList(selectedRoles));

				if (isGSIUser && !refactRolesList.contains(adminRoleId))
				{
					refactRolesList.add(adminRoleId);
				}

				selectedRoles = refactRolesList.toArray(new String[refactRolesList.size()]);

				session = getSession();
				_Txn = session.beginTransaction();

				StringBuffer sbSelectQry = new StringBuffer();
				sbSelectQry.append("Delete From " + UserRolesFactory.getInstance().getUserRolesInstanceName() + WHERE_1_1);
				sbSelectQry.append(" AND users.usEmployeeId = " + EWrap.Quote.enclose(usEmployeeId));
				sbSelectQry.append(" AND roles.rlRoleId <> " + EWrap.Quote.enclose(gsiRoleId));

				session.createQuery(sbSelectQry.toString()).executeUpdate();

				for (String selRole : selectedRoles)
				{
					IUserRoles userRoles = UserRolesFactory.getInstance().getUserRolesInstance();
					userRoles.setUsers(UsersFactory.getInstance().getUsersInstance(usEmployeeId));
					userRoles.setRoles(RolesFactory.getInstance().getRolesInstance(selRole));
					session.save(userRoles);
				}
				_Txn.commit();
				return true;
			}
			catch (Exception excep)
			{
				caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : saveUserRoles", excep.getMessage(), this.getClass().getName(), null);
				if (_Txn != null && _Txn.isActive())
				{
					try
					{
						_Txn.rollback();
					}
					catch (HibernateException hibExcep)
					{
						caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : saveUserRoles", hibExcep.getMessage(), this.getClass().getName(), null);
					}
				}
				return false;
			}
			finally
			{
				if (session != null)
				{
					session.clear();
					session.close();
				}
			}
		}
		return true;

	}

	
	public void sendPasswordMail(String email, String pwd, String ipAddr)
	{
		Session session = getSession();

		try
		{
			session = getSession();
			Query q = session.createSQLQuery(" { CALL sp_ForgotPasswordEmail(:usrId,:userPwd,:ipAdd) }");
			q.setString("usrId", email);
			q.setString("userPwd", pwd);
			q.setString("ipAdd", ipAddr);
			q.executeUpdate();

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOimpl:sendPasswordMail", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
	}

	
	public void spChangeEmailAdress(String oldUserId, String newUserId, int log_count)
	{
		Session session = getSession();

		try
		{
			Query q = session.createSQLQuery(" { CALL sp_changeEmailAddress(:oldUsrId,:newUsrId) }");
			q.setString("oldUsrId", oldUserId);
			q.setString("newUsrId", newUserId);
			q.executeUpdate();

			if (log_count > 0)
			{
				Query q1 = session.createSQLQuery(" { CALL sp_WelcomeEmail(:newUsrId) }");
				q1.setString("newUsrId", newUserId);
				q1.executeUpdate();
			}

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOimpl:spChangeEmailAdress", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
	}

	
	public boolean studentEdutelEnquiry(AdminStudentEnquiryAction admStudEnquiry)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			admStudEnquiry.getStudentEnquiry().setCreatedBy("SuperAdmin");
			admStudEnquiry.getStudentEnquiry().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			session.save(admStudEnquiry.getStudentEnquiry());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : studentEdutelEnquiry", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : studentEdutelEnquiry", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}

	}

	/**
	 * update the user details
	 */
	@SuppressWarnings("unchecked")
	
	public boolean updateUserDetails(IUsers user)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			StringBuffer sbUpdateQry = new StringBuffer();
			sbUpdateQry.append(" Update " + UsersFactory.getInstance().getUsersInstanceName() + " SET ");
			sbUpdateQry.append(" usUserId = " + EWrap.Quote.enclose(user.getUsUserID()) + COMMA_SPACE);
			sbUpdateQry.append(" usUserName = " + EWrap.Quote.enclose(CommonUtil.replaceSingleQuotes(user.getUsUserName())) + COMMA_SPACE);
			sbUpdateQry.append(" usUserPwd = " + EWrap.Quote.enclose(user.getUsUserPwd()) + COMMA_SPACE);
			sbUpdateQry.append(" usModifiedBy = " + EWrap.Quote.enclose(user.getUsModifiedBy()) + COMMA_SPACE);
			sbUpdateQry.append(" usModifiedDate = " + EWrap.Quote.enclose(user.getUsModifiedDate()) + COMMA_SPACE);
			sbUpdateQry.append(" usStatus = " + user.isUsStatus() + WHERE_1_1);
			sbUpdateQry.append(" AND usEmployeeId = " + EWrap.Quote.enclose(user.getUsEmployeeId()));

			session.createQuery(sbUpdateQry.toString()).executeUpdate();

			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(SELECT_DISTINCT + " maEmployeeName " + FROM);
			sbSelectQry.append("AND maEmployeeId = " + EWrap.Quote.enclose(user.getUsEmployeeId()));

			List<String> userNameList = session.createQuery(sbSelectQry.toString()).list();

			String username = null;
			if (CommonValidator.isListFirstNotEmpty(userNameList))
				username = userNameList.iterator().next();

			if (CommonValidator.isNotNullNotEmpty(username))
			{
				sbUpdateQry = new StringBuffer();
				sbUpdateQry.append(" Update ApprovalTableBeanName SET ");
				sbUpdateQry.append(" maManagerName = " + EWrap.Quote.enclose(user.getUsUserName()) + WHERE_1_1);
				sbUpdateQry.append(" AND maManagerName = " + EWrap.Quote.enclose(username));

				session.createQuery(sbUpdateQry.toString()).executeUpdate();
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : updateUserDetails", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : updateUserDetails", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	/**
	 * update the user's existing password to new password
	 */
	
	public boolean updateUserPasswordAndSendEmail(String usEmployeeId, String userId, String newPwd, String modifiedBy, Timestamp modifiedDate)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();

			String encryptedPassword = PasswordEncrypt.encrypt(newPwd, "SHA", UTF_8).trim();

			StringBuffer sbUpdateQry = new StringBuffer();
			sbUpdateQry.append(" Update " + UsersFactory.getInstance().getUsersInstanceName() + " SET ");
			sbUpdateQry.append(" usUserPwd = " + EWrap.Quote.enclose(encryptedPassword) + COMMA_SPACE);
			sbUpdateQry.append(" usModifiedBy = " + EWrap.Quote.enclose(modifiedBy) + COMMA_SPACE);
			sbUpdateQry.append(" usModifiedDate = " + EWrap.Quote.enclose(modifiedDate) + WHERE_1_1);
			sbUpdateQry.append(" AND usEmployeeId = " + EWrap.Quote.enclose(usEmployeeId));
			sbUpdateQry.append(" AND usUserId = " + EWrap.Quote.enclose(userId));

			session.createQuery(sbUpdateQry.toString()).executeUpdate();
			_Txn.commit();
			sendPasswordMail(userId, newPwd, "");
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : updateUserPasswordAndSendEmail", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : updateUserPasswordAndSendEmail", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	
	public boolean updateUserReminderQuery(IUserReminderQuery urq)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			session.saveOrUpdate(urq);
			_Txn.commit();
			caLogger.info(Audit_Logging_Event_DAOImpl, " DAOimpl: updateUserReminderQuery", "UserReminderQuery saved Successfully", this.getClass().getName(), urq.getUsCreatedBy());
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : updateUserReminderQuery", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : saveAdminUserActivity", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}

	
	public boolean userLogAtLogin(IUsers user, String ipAddr)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();

			IUserLog ulog = UserLogFactory.getInstance().getUserLogInstance();
			ulog.setUsers(user);
			ulog.setUlUserLoginTime(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			ulog.setUlIPAddress(ipAddr);
			session.saveOrUpdate(ulog);
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : userLogAtLogin", excep.getMessage(), this.getClass().getName(), user.getUsEmployeeId());
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : userLogAtLogin", hibExcep.getMessage(), this.getClass().getName(), user.getUsEmployeeId());
				}
			}
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	
	public boolean userLogAtLogOut(IUsers user)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();

			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + UserLogFactory.getInstance().getUserLogInstanceName() + WHERE_1_1);
			sbSelectQry.append(" AND users.usEmployeeId = " + EWrap.Quote.enclose(user.getUsEmployeeId()));
			sbSelectQry.append(" Order By ulUserLoginTime Desc");
			List<IUserLog> userLogList = session.createQuery(sbSelectQry.toString()).list();
			if (CommonValidator.isListFirstNotEmpty(userLogList))
			{
				IUserLog userLog = userLogList.iterator().next();
				userLog.setUlUserLogoutTime(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				session.saveOrUpdate(userLog);
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : userLogAtLogOut", excep.getMessage(), this.getClass().getName(), user.getUsEmployeeId());
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : userLogAtLogOut", hibExcep.getMessage(), this.getClass().getName(), user.getUsEmployeeId());
				}
			}
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return false;

	}

	
	public int userLogCount(String usEmployeeId)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(" Select Count(UL.ulUserLogAutoId) From ");
			sbSelectQry.append(UserLogFactory.getInstance().getUserLogInstance().getClass().getCanonicalName());
			sbSelectQry.append(" AS UL Where UL.users.usEmployeeId = ");
			sbSelectQry.append(EWrap.Quote.enclose(usEmployeeId));

			Query query = session.createQuery(sbSelectQry.toString());
			return ((Number) query.uniqueResult()).intValue();

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : userLogCount", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return 0;

	}

	
	public boolean userRegistration(AdminUserManagementAction adminUserManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			String usUserType = adminUserManagementAction.getUser().getUsUsersType();

			if (CommonValidator.isNotNullNotEmpty(usUserType))
			{
				session = getSession();
				_Txn = session.beginTransaction();
				adminUserManagementAction.getUser().setUsEmployeeId(EGenerate.valueOf(adminUserManagementAction.getUser().getUsUsersType()).getPrimaryId());
				session.save(adminUserManagementAction.getUser());
				_Txn.commit();
				return true;
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : userRegistration", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : userRegistration", hibExcep.getMessage(), this.getClass().getName(), null);
				}
			}
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	
	public ReminderQABean userSecurityQuestion(String usEmployeeId)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + UserReminderQuery.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND users.usEmployeeId = " + usEmployeeId);

			List<IUserReminderQuery> userRemQAList = session.createQuery(sbSelectQry.toString()).list();
			if (CommonValidator.isListFirstNotEmpty(userRemQAList))
			{
				IUserReminderQuery userReminderQuery = userRemQAList.iterator().next();
				ReminderQABean reminderQABean = new ReminderQABean();
				reminderQABean.setSecQuestion(userReminderQuery.getUsReminderQueryQuestion());
				reminderQABean.setSecAnswer(userReminderQuery.getUsReminderQueryAnswer());
				return reminderQABean;
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : userSecurityQuestion", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public StudentsMarks getStudentMarkByEmpId(String usEmployeeId)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + StudentsMarks.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND usEmployeeId = " + EWrap.Quote.enclose(usEmployeeId));
			sbSelectQry.append(" AND status = True ");
			List<StudentsMarks> studentMarksList = session.createQuery(sbSelectQry.toString()).list();
			if (CommonValidator.isListFirstNotEmpty(studentMarksList))
			{
				return studentMarksList.iterator().next();
			}

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getStudentMarkByEmpId", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}
}
