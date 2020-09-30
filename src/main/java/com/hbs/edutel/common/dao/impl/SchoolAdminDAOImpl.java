package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.AdminSchoolManagementAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.School;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public abstract class SchoolAdminDAOImpl extends CollegeAdminDAOImpl
{
	private static final long	serialVersionUID	= -879603213181696218L;
	private CustomAuditLogger	caLoggerSch			= new CustomAuditLogger(this.getClass());

	public SchoolAdminDAOImpl()
	{
		super();
	}

	
	public School deleteSchool(AdminSchoolManagementAction adminSchoolManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			String scSchoolId = adminSchoolManagementAction.getRequest().getParameter("scSchoolId");
			if (CommonValidator.isNotNullNotEmpty(scSchoolId))
			{
				IUsers sessionUser = (IUsers) adminSchoolManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());
				School school = getSchoolById(new JQueryDataTableParam(scSchoolId));
				school.setStatus(false);
				school.setModifiedBy(sessionUser.getUsEmployeeId());
				school.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				session = getSession();
				_Txn = session.beginTransaction();
				session.saveOrUpdate(school);
				_Txn.commit();

				return school;
			}
		}
		catch (Exception excep)
		{
			caLoggerSch.error(Audit_Logging_Event_DAOImpl, "deleteSchool", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerSch.error(Audit_Logging_Event_DAOImpl, "deleteSchool", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public School getSchoolById(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam) && CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + School.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True AND scSchoolId = " + EWrap.Quote.enclose(jdtParam.sSearch));

				return (School) session.createQuery(sbSelectQry.toString()).list().iterator().next();
			}
		}
		catch (Exception excep)
		{
			caLoggerSch.error(Audit_Logging_Event_DAOImpl, " getSchoolById", excep.getMessage() + "\n" + excep.getCause(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<School> getSchoolsList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			return session.createQuery(getSchoolsListQuery(jdtParam).toString()).setMaxResults(jdtParam.iDisplayLength).setFirstResult(jdtParam.iDisplayStart).list();
		}
		catch (Exception excep)
		{
			caLoggerSch.error(Audit_Logging_Event_DAOImpl, " getSchoolsList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<School>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<School> getSchoolsListAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			return session.createQuery(getSchoolsListQuery(jdtParam).toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerSch.error(Audit_Logging_Event_DAOImpl, " getSchoolsListAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<School>(0);
	}

	private StringBuffer getSchoolsListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + School.class.getCanonicalName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (scSchoolId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scSchoolName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scSchoolType Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scEmail Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scMobileNo Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scAddress1 Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scAddress2 Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scCity Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scState Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scPinCode Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scCountry Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR scWebSiteURL Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}
				sbSelectQry.append(" AND status = True Order By createdDate " + jdtParam.sSortDirection);
			}
		}
		catch (Exception excep)
		{
			caLoggerSch.error(Audit_Logging_Event_DAOImpl, " getSchoolsListQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	
	public boolean schoolRegistration(AdminSchoolManagementAction adminSchoolManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			IUsers sessionUser = (IUsers) adminSchoolManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());

			session = getSession();
			_Txn = session.beginTransaction();
			adminSchoolManagementAction.getSchool().setCreatedBy(sessionUser.getUsEmployeeId());
			adminSchoolManagementAction.getSchool().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			session.save(adminSchoolManagementAction.getSchool());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerSch.error(Audit_Logging_Event_DAOImpl, "schoolRegistration", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerSch.error(Audit_Logging_Event_DAOImpl, "schoolRegistration", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public String searchSchoolName(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		StringBuffer schoolBuffer = new StringBuffer();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer(FROM + School.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND status = True");
			if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
			{
				sbSelectQry.append(" AND (scSchoolName Like  " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" Or scCity Like  " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
			}
			sbSelectQry.append(" Order By createdDate desc");

			List<School> schoolList = session.createQuery(sbSelectQry.toString()).list();
			if (CommonValidator.isListFirstNotEmpty(schoolList))
			{
				for (School school : schoolList)
				{
					schoolBuffer.append(school.getScSchoolId() + ",");
				}
				return schoolBuffer.toString();
			}
		}
		catch (Exception excep)
		{
			caLoggerSch.error(Audit_Logging_Event_DAOImpl, "searchSchoolName", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	
	public boolean updateSchool(AdminSchoolManagementAction adminSchoolManagementAction)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			String schoolId = adminSchoolManagementAction.getSchool().getScSchoolId();

			if (CommonValidator.isNotNullNotEmpty(schoolId))
			{
				IUsers sessionUser = (IUsers) adminSchoolManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());

				session = getSession();
				_Txn = session.beginTransaction();
				adminSchoolManagementAction.getSchool().setModifiedBy(sessionUser.getUsEmployeeId());
				adminSchoolManagementAction.getSchool().setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				session.saveOrUpdate(adminSchoolManagementAction.getSchool());
				_Txn.commit();
				return true;
			}
		}
		catch (Exception excep)
		{
			caLoggerSch.error(Audit_Logging_Event_DAOImpl, "updateSchool", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerSch.error(Audit_Logging_Event_DAOImpl, "updateSchool", hibExcep.getMessage(), this.getClass().getName(), null);
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

}