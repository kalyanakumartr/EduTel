package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.hbs.edutel.action.AdminUserManagementAction;
import com.hbs.edutel.action.DashBoardAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.UserRoles;
import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.SerialKeyUserMapping;
import com.hbs.edutel.model.ToppersClub;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessage;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessageTemplate;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessageType;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ERole;
import com.hbs.edutel.util.common.ConstEnumUtil.EUser;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;
import com.hbs.edutel.util.common.factory.RolesFactory;
import com.hbs.edutel.util.common.factory.UsersFactory;
import com.hbs.edutel.util.common.property.factory.PropFactory;
import com.hbs.messagesalert.model.Messages;
import com.hbs.messagesalert.model.MessagesUserMapping;

public abstract class UserAdminDAOImpl extends SchoolAdminDAOImpl
{
	private static final long		serialVersionUID	= 5278497501091374346L;
	private final CustomAuditLogger	caLoggerUser		= new CustomAuditLogger(this.getClass());

	public UserAdminDAOImpl()
	{
		super();
	}

	@SuppressWarnings("unchecked")
	
	public ToppersClub deleteTopper(JQueryDataTableParam jdtParam)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				session = getSession();

				StringBuffer sbSelectQry = new StringBuffer(FROM + ToppersClub.class.getName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True ");
				sbSelectQry.append(" AND tpId = " + jdtParam.sSearch);

				List<ToppersClub> toppersList = session.createQuery(sbSelectQry.toString()).list();
				if (CommonValidator.isListFirstNotEmpty(toppersList))
				{
					ToppersClub topper = toppersList.iterator().next();
					topper.setStatus(false);

					_Txn = session.beginTransaction();
					session.saveOrUpdate(topper);
					_Txn.commit();
					return topper;
				}
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "caLoggerUser", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "caLoggerUser", hibExcep.getMessage(), this.getClass().getName(), null);
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
		return null;
	}

	
	public IUsers deleteUser(AdminUserManagementAction adminUserManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			String usEmployeeId = adminUserManagementAction.getRequest().getParameter("usEmployeeId");
			if (CommonValidator.isNotNullNotEmpty(usEmployeeId))
			{
				IUsers user = getUserById(new JQueryDataTableParam(usEmployeeId));
				user.setUsStatus(false);
				user.setUsModifiedBy(adminUserManagementAction.getEmployeeId());
				user.setUsModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				session = getSession();
				_Txn = session.beginTransaction();
				session.saveOrUpdate(user);
				_Txn.commit();
				return user;
			}

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "deleteUser", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "deleteUser", hibExcep.getMessage(), this.getClass().getName(), null);
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
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<ToppersClub> getToppersClubList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && jdtParam.iDisplayLength != 0)
					return session.createQuery(getToppersClubListQuery(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
				else
					return session.createQuery(getToppersClubListQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getToppersClubList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<ToppersClub>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<ToppersClub> getToppersClubListAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getToppersClubListQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getToppersClubListAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<ToppersClub>(0);
	}

	private StringBuffer getToppersClubListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + ToppersClub.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True ");
				sbSelectQry.append(" AND tpRatings >= 3 ");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (tpExamName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpStudentName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpSchoolName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpSubject Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpChapter Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpStartTime Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpEndTime Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpMarks Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpRatings Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR tpNoOfQuestions Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}
				// sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
				sbSelectQry.append(" Order By tpMarks desc");
				sbSelectQry.append(" AND tpExamDuration asc");
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getToppersClubListQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	
	public IUsers getUserById(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + UsersFactory.getInstance().getUsersInstanceName() + WHERE_1_1);
				sbSelectQry.append(" AND usStatus = True AND usEmployeeId = " + EWrap.Quote.enclose(jdtParam.sSearch));

				return (IUsers) session.createQuery(sbSelectQry.toString()).list().iterator().next();
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "getUserById", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<IUsers> getUsersList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			String userSelectQuery = "";
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearchArr) && jdtParam.sSearchArr.length != 0)
				{
					if (CommonValidator.isEqual(jdtParam.sSearchArr[jdtParam.sSearchArr.length - 1], EPage.Global.name()))
					{
						jdtParam.sSearch = jdtParam.sSearchArr[0];
						userSelectQuery = getUsersListQuery(jdtParam).toString();
					}
					else if (CommonValidator.isEqual(jdtParam.sSearchArr[jdtParam.sSearchArr.length - 1], EPage.Advance.name()))
					{
						userSelectQuery = getUserAdvancedSearchListQuery(jdtParam).toString();
					}
				}
				else
				{
					userSelectQuery = getUsersListQuery(jdtParam).toString();

				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && jdtParam.iDisplayLength != 0)
					return session.createQuery(userSelectQuery).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
				else
					return session.createQuery(userSelectQuery).list();

			}

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getUsersList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IUsers>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<IUsers> getUsersListAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			String userSelectQuery = "";
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearchArr) && jdtParam.sSearchArr.length != 0)
				{
					if (CommonValidator.isEqual(jdtParam.sSearchArr[jdtParam.sSearchArr.length - 1], EPage.Global.name()))
					{
						jdtParam.sSearch = jdtParam.sSearchArr[0];
						userSelectQuery = getUsersListQuery(jdtParam).toString();
					}
					else if (CommonValidator.isEqual(jdtParam.sSearchArr[jdtParam.sSearchArr.length - 1], EPage.Advance.name()))
					{
						userSelectQuery = getUserAdvancedSearchListQuery(jdtParam).toString();
					}
				}
				else
				{
					userSelectQuery = getUsersListQuery(jdtParam).toString();
				}
				return session.createQuery(userSelectQuery).list();

			}

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getUsersListAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IUsers>(0);
	}

	
	@SuppressWarnings("unchecked")
	public List<IUsers> getUsersListByMobileNo(AdminUserManagementAction adminUser) throws CustomException
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer(FROM + UsersFactory.getInstance().getUsersInstanceName() + WHERE_1_1);
			sbSelectQry.append(" AND usMobileNo = " + EWrap.Quote.enclose(adminUser.getUser().getUsMobileNo().trim()));

			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getUsersListByMobileNo", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<IUsers>(0);
	}

	private StringBuffer getUsersListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + UsersFactory.getInstance().getUsersInstanceName() + WHERE_1_1);
				sbSelectQry.append(" AND usStatus = True ");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.usUsersType))
				{
					sbSelectQry.append(" AND usUsersType Like " + EWrap.QuotePercent.enclose(jdtParam.usUsersType));
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usUserID Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usEmail Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					String schoolId = searchSchoolName(jdtParam);
					if (CommonValidator.isNotNullNotEmpty(schoolId))
					{
						sbSelectQry.append(" OR usSchoolId  In " + EWrap.Brace.enclose(EWrap.Quote.enclose(schoolId.split(",".trim()))));
					}
					else
					{
						sbSelectQry.append(" OR usSchoolId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					}
					sbSelectQry.append(" OR usFatherName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usFrancheesArea Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usMobileNo Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usDateOfJoin Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usCity Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usUsersType Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usCreatedBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usCreatedDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}
				sbSelectQry.append(" Order By usCreatedDate " + jdtParam.sSortDirection);
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getUsersListQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	private StringBuffer getUserAdvancedSearchListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + UsersFactory.getInstance().getUsersInstanceName() + WHERE_1_1);
				sbSelectQry.append(" AND usStatus = True ");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.usUsersType))
				{
					sbSelectQry.append(" AND usUsersType Like " + EWrap.QuotePercent.enclose(jdtParam.usUsersType));
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.columnFilterMap))
				{
					for (String key : jdtParam.columnFilterMap.keySet())
					{
						String columnValue = jdtParam.columnFilterMap.get(key);

						if (key.contains("keySoldBy"))
						{
							if (CommonValidator.isNotNullNotEmpty(columnValue))
							{
								JQueryDataTableParam jdTableParam = new JQueryDataTableParam();
								jdTableParam.usEmployeeId = columnValue;
								List<SerialKeyUserMapping> serialKeyListByEmpId = getSerialKeyByEmpId(jdTableParam);

								String[] serialKeyArr = new String[serialKeyListByEmpId.size()];
								if (CommonValidator.isListFirstNotEmpty(serialKeyListByEmpId))
								{
									int index = 0;
									for (SerialKeyUserMapping serialKey : serialKeyListByEmpId)
									{
										serialKeyArr[index] = serialKey.getSerialKeyGenerate().getSerialKey();
										index++;
									}
								}
								if (serialKeyArr.length != 0)
								{
									sbSelectQry.append(" AND  usSerialKey In " + EWrap.Brace.enclose(EWrap.Quote.enclose(serialKeyArr)));
								}
							}
						}
						else if (CommonValidator.isNotNullNotEmpty(columnValue) && key.contains("studentBatch"))
						{
							if (CommonValidator.isNotNullNotEmpty(columnValue))
							{
								JQueryDataTableParam jdTableParam = new JQueryDataTableParam();
								jdTableParam.studentBatch = columnValue;
								List<StudentsMarks> studentsMarkListByBatch = getStudentMarkByBatch(jdTableParam);
								String[] employeeIDArr;
								if (CommonValidator.isListFirstNotEmpty(studentsMarkListByBatch))
								{
									employeeIDArr = new String[studentsMarkListByBatch.size()];
									if (CommonValidator.isListFirstNotEmpty(studentsMarkListByBatch))
									{
										int index = 0;
										for (StudentsMarks studentMark : studentsMarkListByBatch)
										{
											employeeIDArr[index] = studentMark.getUsEmployeeId();
											index++;
										}
									}
									if (employeeIDArr.length != 0)
									{
										sbSelectQry.append(" AND  usEmployeeId In " + EWrap.Brace.enclose(EWrap.Quote.enclose(employeeIDArr)));
									}
								}
								else
								{
									sbSelectQry.append(" AND  usEmployeeId IS NULL ");
								}
							}
						}
						else if (CommonValidator.isNotNullNotEmpty(columnValue) && key.contains("keyWord") == false)
						{
							sbSelectQry.append(" AND " + key + EWrap.QuotePercent.enclose(columnValue));
						}

					}
				}
				sbSelectQry.append(" Order By usCreatedDate " + jdtParam.sSortDirection);
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getUserAdvancedSearchListQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	
	public boolean resetPassword(AdminUserManagementAction adminUserManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(adminUserManagementAction.getUser()))
			{
				adminUserManagementAction.getUser().setUsModifiedBy(adminUserManagementAction.getEmployeeId());
				adminUserManagementAction.getUser().setUsModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				session = getSession();
				_Txn = session.beginTransaction();
				session.saveOrUpdate(adminUserManagementAction.getUser());
				_Txn.commit();
				return true;
			}

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "resetPassword", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "resetPassword", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean updateUser(AdminUserManagementAction adminUserManagementAction)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			String usUserType = adminUserManagementAction.getUser().getUsUsersType();
			String usEmployeeId = adminUserManagementAction.getUser().getUsEmployeeId();

			if (CommonValidator.isEqual(usUserType, "SuperAdmin"))
			{
				usUserType = EPage.Employee.name();
			}

			if (CommonValidator.isNotNullNotEmpty(usEmployeeId) || CommonValidator.isNotNullNotEmpty(usUserType))
			{
				session = getSession();
				_Txn = session.beginTransaction();
				session.saveOrUpdate(adminUserManagementAction.getUser());
				_Txn.commit();
				return true;
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "userUpdate", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "userUpdate", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean userRegistration(AdminUserManagementAction adminUser)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();

			String usUserType = adminUser.getUser().getUsUsersType();

			if (CommonValidator.isNotNullNotEmpty(usUserType))
			{
				if (CommonValidator.isEqual(adminUser.getUser().getProfileBy(), EPage.Student.name()))
				{
					adminUser.getUser().setUsCreatedBy(adminUser.getUser().getUsEmployeeId());
				}

				_Txn = session.beginTransaction();
				session.save(adminUser.getUser());
				IRoles iRole = RolesFactory.getInstance().getRolesInstance(usUserType);
				session.save(new UserRoles(adminUser.getUser(), iRole));

				if (CommonValidator.isEqual(usUserType, EPage.Employee.name()))
				{
					iRole = RolesFactory.getInstance().getRolesInstance(ERole.Marketing.name());
					session.save(new UserRoles(adminUser.getUser(), iRole));
					iRole = RolesFactory.getInstance().getRolesInstance(ERole.Admin.name());
					session.save(new UserRoles(adminUser.getUser(), iRole));
				}
				else if (CommonValidator.isEqual(usUserType, EPage.Student.name()))
				{
					StudentsMarks studentMark = new StudentsMarks(adminUser.getUser().getUsEmployeeId(), adminUser.getUser().getUsBatch());

					studentMark.setCreatedDate(adminUser.getUser().getUsCreatedDate());
					studentMark.setCreatedBy(adminUser.getUser().getUsCreatedBy());

					session.save(studentMark);
				}
				_Txn.commit();

				sendSMSAndEmailToNewUsersAndAdmin(adminUser);

				return true;
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "userRegistration", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "userRegistration", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	private boolean sendSMSAndEmailToNewUsersAndAdmin(AdminUserManagementAction adminUser)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();

//			Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
//			dataMap.put("userName", adminUser.getUser().getUsUserName());
//			dataMap.put("userId", adminUser.getUser().getUsUserID());
//			dataMap.put("password", adminUser.getUser().getUsUserPwdOriginal());
//			dataMap.put("emailId", new ArrayList<String>().add(adminUser.getUser().getUsEmail()));
//			dataMap.put("mobileNo", new ArrayList<String>().add(adminUser.getUser().getUsMobileNo()));
//			dataMap.put("groupName", adminUser.getUser().getUsGroupName());
			
			// Make an entry in MessagesUserMapping Table for Sending SMS
			
			_Txn = session.beginTransaction();
			MessagesUserMapping _MUM = new MessagesUserMapping();
			_MUM.setUsEmployeeId(adminUser.getUser().getUsEmployeeId());
			_MUM.setMessageStatus(EMessage.Pending.name());
			_MUM.setStatus(true);
			_MUM.setCreatedBy(EUser.SuperAdmin.name());
			_MUM.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			_MUM.setMessageId(EMessageTemplate.UserIdPassword_SMS.name());
			_MUM.setMessages(new Messages(EMessageTemplate.UserIdPassword_SMS.name(), EMessageType.SMS.name()));
			
			_MUM.setDataObjectSerialize(adminUser.getUser());
			session.save(_MUM);

			_MUM = new MessagesUserMapping();
			_MUM.setUsEmployeeId(adminUser.getUser().getUsEmployeeId());
			_MUM.setMessageStatus(EMessage.Pending.name());
			_MUM.setStatus(true);
			_MUM.setCreatedBy(EUser.SuperAdmin.name());
			_MUM.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			_MUM.setMessageId(EMessageTemplate.UserIdPassword_Email.name());
			_MUM.setMessages(new Messages(EMessageTemplate.UserIdPassword_Email.name(), EMessageType.Email.name()));

			_MUM.setDataObjectSerialize(adminUser.getUser());
			session.save(_MUM);

			_MUM = new MessagesUserMapping();
			_MUM.setUsEmployeeId(EUser.SuperAdmin.name());
			_MUM.setMessageStatus(EMessage.Pending.name());
			_MUM.setStatus(true);
			_MUM.setCreatedBy(EUser.SuperAdmin.name());
			_MUM.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			_MUM.setMessageId(EMessageTemplate.UserRegistration_Email_Admin.name());
			_MUM.setMessages(new Messages(EMessageTemplate.UserRegistration_Email_Admin.name(), EMessageType.Email.name()));
			
			Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
			dataMap.put("to", PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail));
			_MUM.setDataObjectSerialize(adminUser.getUser(), dataMap);
			
			session.save(_MUM);
			_Txn.commit();

			if (CommonValidator.isEqual(adminUser.getUser().getUsUsersType(), EPage.Student.name()))
			{
				_Txn = session.beginTransaction();

				_MUM = new MessagesUserMapping();
				_MUM.setUsEmployeeId(adminUser.getUser().getUsEmployeeId());
				_MUM.setMessageStatus(EMessage.Pending.name());
				_MUM.setStatus(true);
				_MUM.setCreatedBy(EUser.SuperAdmin.name());
				_MUM.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				_MUM.setMessageId(EMessageTemplate.UserRegistrationReceipt_SMS.name());
				_MUM.setMessages(new Messages(EMessageTemplate.UserRegistrationReceipt_SMS.name(), EMessageType.SMS.name()));
				_MUM.setDataObjectSerialize(adminUser.getUser());
				
				session.save(_MUM);

				_MUM = new MessagesUserMapping();
				_MUM.setUsEmployeeId(adminUser.getUser().getUsEmployeeId());
				_MUM.setMessageStatus(EMessage.Pending.name());
				_MUM.setStatus(true);
				_MUM.setCreatedBy(EUser.SuperAdmin.name());
				_MUM.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				_MUM.setMessageId(EMessageTemplate.UserRegistrationReceipt_Email.name());
				_MUM.setMessages(new Messages(EMessageTemplate.UserRegistrationReceipt_Email.name(), EMessageType.Email.name()));
				_MUM.setDataObjectSerialize(adminUser.getUser());

				session.save(_MUM);

				_MUM = new MessagesUserMapping();
				_MUM.setUsEmployeeId(adminUser.getUser().getUsEmployeeId());
				_MUM.setMessageStatus(EMessage.Pending.name());
				_MUM.setStatus(true);
				_MUM.setCreatedBy(EUser.SuperAdmin.name());
				_MUM.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				_MUM.setMessageId(EMessageTemplate.UserRegistrationWelcome_Email.name());
				_MUM.setMessages(new Messages(EMessageTemplate.UserRegistrationWelcome_Email.name(), EMessageType.Email.name()));
				_MUM.setDataObjectSerialize(adminUser.getUser());

				session.save(_MUM);

				_Txn.commit();
			}

			return true;
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "userRegistration", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "userRegistration", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean saveStudentMarkEnroll(DashBoardAction dashBoardAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();

			dashBoardAction.getStudentsMark().setModifiedBy(dashBoardAction.getStudentsMark().getUsEmployeeId());
			dashBoardAction.getStudentsMark().setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			dashBoardAction.getStudentsMark().setStatus(true);

			_Txn = session.beginTransaction();
			session.saveOrUpdate(dashBoardAction.getStudentsMark());

			if (CommonValidator.isNotNullNotEmpty(dashBoardAction.getUser().getUsEmployeeId()) || CommonValidator.isNotNullNotEmpty(dashBoardAction.getUser().getUsUsersType()))
			{
				session.saveOrUpdate(dashBoardAction.getUser());
			}
			_Txn.commit();

			return true;
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "saveStudentMarkEnroll", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "saveStudentMarkEnroll", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<SerialKeyUserMapping> getSerialKeyByEmpId(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		Session session = getSession();

		try
		{
			sbSelectQry = new StringBuffer();
			sbSelectQry.append("From SerialKeyUserMapping SKU " + WHERE_1_1);
			sbSelectQry.append(" AND SKU.users.usStatus = True ");
			if (CommonValidator.isNotNullNotEmpty(jdtParam.usEmployeeId))
			{
				sbSelectQry.append(" AND SKU.users.usEmployeeId Like " + EWrap.QuotePercent.enclose(jdtParam.usEmployeeId));
			}
			if (CommonValidator.isNotNullNotEmpty(jdtParam.studentBatch))
			{
				sbSelectQry.append(" AND SKU.serialKeyGenerate.serialBatch Like " + EWrap.QuotePercent.enclose(jdtParam.studentBatch));
			}

			sbSelectQry.append(" Order By SKU.serialKeyGenerate.createdDate " + jdtParam.sSortDirection);
			return session.createQuery(sbSelectQry.toString()).list();

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getSerialKeyByEmpId", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<SerialKeyUserMapping>(0);

	}

	@SuppressWarnings("unchecked")
	
	public List<StudentsMarks> getStudentMarksList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && jdtParam.iDisplayLength != 0)
					return session.createQuery(getgetStudentMarksListQuery(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
				else
					return session.createQuery(getgetStudentMarksListQuery(jdtParam).toString()).list();
			}

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getStudentMarksList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<StudentsMarks>(0);

	}

	@SuppressWarnings("unchecked")
	
	public List<StudentsMarks> getStudentMarksListAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getgetStudentMarksListQuery(jdtParam).toString()).list();
			}

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getStudentMarksListAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<StudentsMarks>(0);

	}

	private StringBuffer getgetStudentMarksListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + StudentsMarks.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True ");

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (usBatch Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usHSCNo Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usHSCMark Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR usEmployeeId  Like " + (EWrap.Quote.enclose(jdtParam.sSearch)));
					sbSelectQry.append(" OR createdBy  Like " + (EWrap.Quote.enclose(jdtParam.sSearch)));
					sbSelectQry.append(" OR createdDate  Like " + (EWrap.Quote.enclose(jdtParam.sSearch)));
					sbSelectQry.append(" OR usSSorPUCMark Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}
				sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getgetStudentMarksListQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public List<StudentsMarks> getStudentMarksUpdatingList(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + StudentsMarks.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND usHSCMark IS NULL ");
				sbSelectQry.append(" AND usHSCNo IS NOT NULL ");

				if (CommonValidator.isNotNullNotEmpty(jdtParam.usEmployeeId))
				{
					sbSelectQry.append(" AND usEmployeeId NOT IN " + EWrap.Brace.enclose(EWrap.Quote.enclose(jdtParam.usEmployeeId.split("#"))));
				}
				sbSelectQry.append(" AND status = True ");
				sbSelectQry.append(" Order By usEmployeeId asc");

				return session.createQuery(sbSelectQry.toString()).list();
			}

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getStudentMarksList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<StudentsMarks>(0);

	}

	
	public StudentsMarks updateStudentHSCMark(String employeeId, String HSCMark)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(employeeId))
			{
				StudentsMarks studentsMark = new StudentsMarks();
				session = getSession();
				List<StudentsMarks> studentMarksList = new ArrayList<StudentsMarks>();
				studentMarksList = getStudentMarkByEmpId(new JQueryDataTableParam(employeeId));
				if (CommonValidator.isListFirstNotEmpty(studentMarksList))
				{
					studentsMark = studentMarksList.iterator().next();
				}
				studentsMark.setUsHSCMark(HSCMark);
				_Txn = session.beginTransaction();
				session.saveOrUpdate(studentsMark);
				_Txn.commit();
				return studentsMark;

			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "updateStudentHSCMark", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "updateStudentHSCMark", hibExcep.getMessage(), this.getClass().getName(), null);
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
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<StudentsMarks> getStudentMarkByEmpId(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + StudentsMarks.class.getCanonicalName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
					sbSelectQry.append(" AND usEmployeeId = " + EWrap.Quote.enclose(jdtParam.sSearch));
				sbSelectQry.append(" AND status = True ");
				return session.createQuery(sbSelectQry.toString()).list();
			}

		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getStudentMarksList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}

		return new ArrayList<StudentsMarks>(0);

	}

	
	public boolean saveStudentHSCMarkUpdate(AdminUserManagementAction adminUserManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			adminUserManagementAction.getStudentMark().setStatus(true);
			_Txn = session.beginTransaction();
			session.saveOrUpdate(adminUserManagementAction.getStudentMark());
			_Txn.commit();

			return true;
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, "saveStudentMarkEnroll", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerUser.error(Audit_Logging_Event_DAOImpl, "saveStudentMarkEnroll", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<StudentsMarks> getStudentMarkByBatch(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + StudentsMarks.class.getCanonicalName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.studentBatch))
					sbSelectQry.append(" AND usBatch = " + EWrap.Quote.enclose(jdtParam.studentBatch));
				sbSelectQry.append(" AND status = True ");
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerUser.error(Audit_Logging_Event_DAOImpl, " getStudentMarkByBatch", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}

		return new ArrayList<StudentsMarks>(0);
	}

}