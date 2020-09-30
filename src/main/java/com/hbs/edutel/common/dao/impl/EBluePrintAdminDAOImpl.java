package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.OnlineEBluePrintAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlineEBluePrint;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public abstract class EBluePrintAdminDAOImpl extends ExamPractiseAdminDAOImpl
{
	private static final long	serialVersionUID	= -3352711000701785962L;
	private CustomAuditLogger	caLoggerBP			= new CustomAuditLogger(this.getClass());

	public EBluePrintAdminDAOImpl()
	{
		super();
	}

	
	public OnlineEBluePrint deleteOnlineEBluePrint(String oeBluePrintId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(oeBluePrintId))
			{
				session = getSession();
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				jdtParam.oeBluePrintId = oeBluePrintId;
				OnlineEBluePrint oeDeleteBluePrint = onlineEBluePrintListByAjax(jdtParam).iterator().next();
				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + OnlineEBluePrint.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND oeBluePrintId = " + EWrap.Quote.enclose(oeBluePrintId));

				_Txn = session.beginTransaction();
				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return oeDeleteBluePrint;
			}
		}
		catch (Exception excep)
		{
			caLoggerBP.error(Audit_Logging_Event_DAOImpl, " onlineEBluePrintRepositoryUpload", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerBP.error(Audit_Logging_Event_DAOImpl, " onlineEBluePrintRepositoryUpload", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<OnlineEBluePrint> getOnlineEBluePrintListByJson(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
			{
				return session.createQuery(getOnlineEBluePrintListByJsonQuery(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
			}
			else
			{
				return session.createQuery(getOnlineEBluePrintListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerBP.error(Audit_Logging_Event_DAOImpl, " onlineEBluePrintListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEBluePrint>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineEBluePrint> getOnlineEBluePrintListByJsonAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getOnlineEBluePrintListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerBP.error(Audit_Logging_Event_DAOImpl, " onlineEBluePrintListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEBluePrint>(0);
	}

	private StringBuffer getOnlineEBluePrintListByJsonQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + OnlineEBluePrint.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True ");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (oeSubject Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeBluePrintName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeSchoolType Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeBluePrintId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}
				sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
			}
		}
		catch (Exception excep)
		{
			caLoggerBP.error(Audit_Logging_Event_DAOImpl, " onlineEBluePrintListByAjaxQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineEBluePrint> onlineEBluePrintListByAjax(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineEBluePrint.class.getCanonicalName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeBluePrintId))
				{
					sbSelectQry.append(" AND oeBluePrintId = " + EWrap.Quote.enclose(jdtParam.oeBluePrintId));
				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeSubject))
				{
					sbSelectQry.append(" AND oeSubject In " + EWrap.Brace.enclose(EWrap.Quote.enclose(jdtParam.oeSubject.split(COMMA_SPACE.trim()))));
				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeSchoolType))
				{
					sbSelectQry.append(" AND oeSchoolType = " + EWrap.Quote.enclose(jdtParam.oeSchoolType));
				}
				sbSelectQry.append(" Order By oeSubject ");
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerBP.error(Audit_Logging_Event_DAOImpl, " onlineEBluePrintListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEBluePrint>(0);
	}

	
	public boolean onlineEBluePrintRepositoryUpload(OnlineEBluePrintAction adminEBluePrint)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			for (OnlineEBluePrint oeBluePrint : adminEBluePrint.getOnlineEBluePrintArray())
			{
				session.save(oeBluePrint);
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerBP.error(Audit_Logging_Event_DAOImpl, " onlineEBluePrintRepositoryUpload", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerBP.error(Audit_Logging_Event_DAOImpl, " onlineEBluePrintRepositoryUpload", hibExcep.getMessage(), this.getClass().getName(), null);
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