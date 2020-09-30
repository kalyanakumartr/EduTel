package com.hbs.edutel.common.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.AdminCollegeManagementAction;
import com.hbs.edutel.action.AdminCollegeManagementActionData;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.dao.AdminDAO;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.College;
import com.hbs.edutel.model.CollegeEForm;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EFormStatus;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public abstract class CollegeAdminDAOImpl extends CommonHibernateDaoSupport implements AdminDAO
{
	private static final long	serialVersionUID	= -4767845524636152269L;
	private CustomAuditLogger	caLoggerClg			= new CustomAuditLogger(this.getClass());

	public CollegeAdminDAOImpl()
	{
		super();
	}

	@SuppressWarnings("unchecked")
	
	public List<CollegeEForm> collegeEFormListByJson(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
					return session.createQuery(getCollegeEFormListQuery(jdtParam).toString()).setMaxResults(jdtParam.iDisplayLength).setFirstResult(jdtParam.iDisplayStart).list();
				else
					return session.createQuery(getCollegeEFormListQuery(jdtParam).toString()).list();
			}

		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " getCollegesList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<CollegeEForm>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<CollegeEForm> collegeEFormListByJsonAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
				return session.createQuery(getCollegeEFormListQuery(jdtParam).toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " getCollegesList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<CollegeEForm>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<CollegeEForm> collegeEFormOrderedList(IUsers sessionUser)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer(FROM + CollegeEForm.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND status = True ");
			sbSelectQry.append(" AND purchased = False ");
			if (sessionUser.isStudent())
				sbSelectQry.append(" AND createdBy = " + EWrap.Quote.enclose(sessionUser.getUsEmployeeId()));
			sbSelectQry.append(" Order By createdDate desc");
			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " collegeEFormOrderedList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<CollegeEForm>(0);
	}

	
	public boolean collegeRegistration(AdminCollegeManagementActionData adminCollegeManagementAction)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			IUsers sessionUser = (IUsers) adminCollegeManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());

			session = getSession();
			_Txn = session.beginTransaction();
			adminCollegeManagementAction.getCollege().setCreatedBy(sessionUser.getUsEmployeeId());
			adminCollegeManagementAction.getCollege().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			session.save(adminCollegeManagementAction.getCollege());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, "collegeRegistration", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerClg.error(Audit_Logging_Event_DAOImpl, "collegeRegistration", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public College deleteCollege(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			String clCollegeId = adminCollegeManagementAction.getRequest().getParameter("clCollegeId");
			if (CommonValidator.isNotNullNotEmpty(clCollegeId))
			{
				IUsers sessionUser = (IUsers) adminCollegeManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());

				College college = getCollegeById(new JQueryDataTableParam(clCollegeId));
				college.setStatus(false);
				college.setModifiedBy(sessionUser.getUsEmployeeId());
				college.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				session = getSession();
				_Txn = session.beginTransaction();
				session.saveOrUpdate(college);
				_Txn.commit();

				return college;
			}
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, "deleteCollege", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerClg.error(Audit_Logging_Event_DAOImpl, "deleteCollege", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public CollegeEForm deleteEForm(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			String formId = adminCollegeManagementAction.getRequest().getParameter("formId");
			if (CommonValidator.isNotNullNotEmpty(formId))
			{
				CollegeEForm eForm = getCollegeEFormById(adminCollegeManagementAction).iterator().next();
				if (CommonValidator.isNotNullNotEmpty(eForm))
				{
					session = getSession();
					_Txn = session.beginTransaction();
					session.delete(eForm);
					_Txn.commit();
					return eForm;
				}
			}
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, "deleteEForm", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerClg.error(Audit_Logging_Event_DAOImpl, "deleteEForm", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public College getCollegeById(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + College.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True AND clCollegeId = " + EWrap.Quote.enclose(jdtParam.sSearch));

				return (College) session.createQuery(sbSelectQry.toString()).list().iterator().next();
			}
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " getCollegeById", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<CollegeEForm> getCollegeEFormById(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		Session session = getSession();
		try
		{
			IUsers sessionUser = (IUsers) adminCollegeManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());
			String formId = adminCollegeManagementAction.getRequest().getParameter("formId");
			StringBuffer sbSelectQry = new StringBuffer(FROM + CollegeEForm.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND status = True ");

			if (CommonValidator.isNotNullNotEmpty(formId))
			{
				sbSelectQry.append(" AND formAutoId = " + formId);
			}
			else if (CommonValidator.isNotNullNotEmpty(adminCollegeManagementAction.getCollegeId()))
			{
				sbSelectQry.append(" AND collegeId = " + EWrap.Quote.enclose(adminCollegeManagementAction.getCollegeId()));
				sbSelectQry.append(" AND employeeId = " + EWrap.Quote.enclose(sessionUser.getUsEmployeeId()));
			}
			sbSelectQry.append(" Order By createdDate desc");

			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " getCollegesList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<CollegeEForm>(0);

	}

	private StringBuffer getCollegeEFormListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + CollegeEForm.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True ");

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append("AND ( collegeId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdUser.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR employeeId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					if (CommonValidator.isEqual(jdtParam.sSearch, EFormStatus.Ordered.name()))
					{
						sbSelectQry.append(" OR purchased = True");
					}
					else if (CommonValidator.isEqual(jdtParam.sSearch, EFormStatus.Pending.name()))
					{
						sbSelectQry.append(" OR purchased = False");
					}
					sbSelectQry.append(" OR clFormCostOthers Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");

				}
				if (CommonValidator.isEqual(jdtParam.usUsersType, EPage.Student.name()))
				{
					sbSelectQry.append(" AND employeeId = " + EWrap.Quote.enclose(jdtParam.usEmployeeId));
				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSortDirection))
				{
					sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
				}
				else
				{
					sbSelectQry.append(" Order By createdDate desc");
				}
			}

		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " getCollegesListQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public List<College> getCollegesList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam) && CommonValidator.isNotNullNotEmpty(jdtParam.eFormType))
				return session.createQuery(getCollegesListQuery(jdtParam).toString()).list();
			else
				return session.createQuery(getCollegesListQuery(jdtParam).toString()).setMaxResults(jdtParam.iDisplayLength).setFirstResult(jdtParam.iDisplayStart).list();

		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " getCollegesList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<College>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<College> getCollegesListAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			return session.createQuery(getCollegesListQuery(jdtParam).toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " getCollegesListAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<College>(0);
	}

	private StringBuffer getCollegesListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + College.class.getCanonicalName() + WHERE_1_1 + " AND status = True");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (clCollegeId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clCollegeName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clCollegeType Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clEmail Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clMobileNo Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clAddress1 Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clAddress2 Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clCity Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clState Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clPinCode Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clCountry Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR clWebSiteURL Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.eFormType))
				{
					sbSelectQry.append(" AND clFormType = " + EWrap.Quote.enclose(jdtParam.eFormType.name()));
				}
				sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
			}

		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " getCollegesListQuery", excep.getMessage(), this.getClass().getName(), null);
		}
		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public List<CollegeEForm> getOrderedEFormList(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		Session session = getSession();
		try
		{
			IUsers sessionUser = (IUsers) adminCollegeManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());

			StringBuffer sbSelectQry = new StringBuffer(FROM + CollegeEForm.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND status = True ");
			sbSelectQry.append(" AND purchased = False ");
			sbSelectQry.append(" And employeeId Like " + EWrap.QuotePercent.enclose(sessionUser.getUsEmployeeId()));
			sbSelectQry.append(" Order By createdDate desc");

			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, " purchasedEFormList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<CollegeEForm>(0);

	}

	
	public boolean orderEForm(AdminCollegeManagementAction adminCollegeManagementAction)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			IUsers sessionUser = (IUsers) adminCollegeManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());

			session = getSession();
			_Txn = session.beginTransaction();
			if (CommonValidator.isNotNullNotEmpty(adminCollegeManagementAction.getCollegeId()))
			{
				College college = getCollegeById(new JQueryDataTableParam(adminCollegeManagementAction.getCollegeId()));
				adminCollegeManagementAction.getCollegeEForm().setEmployeeId(sessionUser.getUsEmployeeId());
				adminCollegeManagementAction.getCollegeEForm().setCollegeId(college.getClCollegeId());
				adminCollegeManagementAction.getCollegeEForm().setClFormCostOthers(college.getClFormCostOthers());
				adminCollegeManagementAction.getCollegeEForm().setClFormCostSCST(college.getClFormCostSCST());
				adminCollegeManagementAction.getCollegeEForm().setClCourierCharge(college.getClCourierCharge());
				adminCollegeManagementAction.getCollegeEForm().setClEduTelCharge(college.getClEduTelCharge());
				adminCollegeManagementAction.getCollegeEForm().setStatus(true);
				adminCollegeManagementAction.getCollegeEForm().setPurchased(false);
				adminCollegeManagementAction.getCollegeEForm().setCreatedUser(sessionUser);
				adminCollegeManagementAction.getCollegeEForm().setCreatedBy(sessionUser.getUsEmployeeId());
				adminCollegeManagementAction.getCollegeEForm().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			}
			session.save(adminCollegeManagementAction.getCollegeEForm());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, "purchaseEForm", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerClg.error(Audit_Logging_Event_DAOImpl, "purchaseEForm", hibExcep.getMessage(), this.getClass().getName(), null);
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

	public boolean setPurchasedEFormList(AdminCollegeManagementAction adminCollegeManagementAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			IUsers sessionUser = (IUsers) adminCollegeManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());
			_Txn = session.beginTransaction();
			List<CollegeEForm> orderedFormList = getOrderedEFormList(adminCollegeManagementAction);
			if (CommonValidator.isListFirstNotEmpty(orderedFormList))
			{
				for (CollegeEForm orderedForm : orderedFormList)
				{
					orderedForm.setModifiedBy(sessionUser.getUsEmployeeId());
					orderedForm.setModifiedDate(new Timestamp(System.currentTimeMillis()));
					orderedForm.setPurchased(true);
					session.saveOrUpdate(orderedForm);
				}
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, "setPurchasedEFormList", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerClg.error(Audit_Logging_Event_DAOImpl, "setPurchasedEFormList", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean updateCollege(AdminCollegeManagementAction adminCollegeManagementAction)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			String collegeId = adminCollegeManagementAction.getCollege().getClCollegeId();

			if (CommonValidator.isNotNullNotEmpty(collegeId))
			{
				IUsers sessionUser = (IUsers) adminCollegeManagementAction.getRequest().getSession().getAttribute(ESession.UserObject.getAttribute());

				session = getSession();
				_Txn = session.beginTransaction();
				adminCollegeManagementAction.getCollege().setModifiedBy(sessionUser.getUsEmployeeId());
				adminCollegeManagementAction.getCollege().setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				session.saveOrUpdate(adminCollegeManagementAction.getCollege());
				_Txn.commit();
				return true;
			}
		}
		catch (Exception excep)
		{
			caLoggerClg.error(Audit_Logging_Event_DAOImpl, "updateCollege", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerClg.error(Audit_Logging_Event_DAOImpl, "updateCollege", hibExcep.getMessage(), this.getClass().getName(), null);
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