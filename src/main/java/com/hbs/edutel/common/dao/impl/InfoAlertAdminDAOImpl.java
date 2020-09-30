package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.AdminInfoAlertAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.InformationAlerts;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public abstract class InfoAlertAdminDAOImpl extends EKeyPointsAdminDAOImpl
{
	private static final long	serialVersionUID	= -4767845524636152269L;
	private CustomAuditLogger	caLoggerInfoAlert	= new CustomAuditLogger(this.getClass());

	public InfoAlertAdminDAOImpl()
	{
		super();
	}

	
	public boolean createInformationAlert(InformationAlerts infoAlertMsg)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(infoAlertMsg))
			{
				session = getSession();
				_Txn = session.beginTransaction();
				session.save(infoAlertMsg);
				_Txn.commit();
				return true;
			}

		}
		catch (Exception excep)
		{
			caLoggerInfoAlert.error(Audit_Logging_Event_DAOImpl, "createInformationAlert", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerInfoAlert.error(Audit_Logging_Event_DAOImpl, "createInformationAlert", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean deleteInfoAlert(AdminInfoAlertAction adminInfoAlertAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			String infoMsgId = adminInfoAlertAction.getRequest().getParameter("iaInformationMsgId");
			if (CommonValidator.isNotNullNotEmpty(infoMsgId))
			{
				session = getSession();

				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + InformationAlerts.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND iaInformationMsgId = " + EWrap.Quote.enclose(infoMsgId));

				_Txn = session.beginTransaction();
				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return true;
			}

		}
		catch (Exception excep)
		{
			caLoggerInfoAlert.error(Audit_Logging_Event_DAOImpl, "deleteInfoAlert", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerInfoAlert.error(Audit_Logging_Event_DAOImpl, "deleteUser", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<InformationAlerts> getInfoAlertList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			return session.createQuery(getInfoAlertListQuery(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
		}
		catch (Exception excep)
		{
			caLoggerInfoAlert.error(Audit_Logging_Event_DAOImpl, " getInfoAlertList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<InformationAlerts>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<InformationAlerts> getInfoAlertListAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			return session.createQuery(getInfoAlertListQuery(jdtParam).toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerInfoAlert.error(Audit_Logging_Event_DAOImpl, " getInfoAlertListAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<InformationAlerts>(0);
	}

	private StringBuffer getInfoAlertListQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + InformationAlerts.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND iaStatus = True ");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND iaInformationMsg Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR iaInformationMsgId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR iaCreatedBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR iaCreatedDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSortDirection))
				{
					sbSelectQry.append(" Order By iaCreatedDate " + jdtParam.sSortDirection);
				}
				else
				{
					sbSelectQry.append(" Order By iaCreatedDate Desc");
				}
			}
		}
		catch (Exception excep)
		{
			caLoggerInfoAlert.error(Audit_Logging_Event_DAOImpl, " getInfoAlertListQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

}