package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.OnlineEKeyPointsAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlineEKeyPoints;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public abstract class EKeyPointsAdminDAOImpl extends EBluePrintAdminDAOImpl
{
	private static final long	serialVersionUID	= 8756372306300872912L;
	public CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public EKeyPointsAdminDAOImpl()
	{
		super();
	}

	
	public OnlineEKeyPoints deleteOnlineEKeyPoints(String oeKeyPointsId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(oeKeyPointsId))
			{
				session = getSession();
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				jdtParam.oeKeyPointsId = oeKeyPointsId;
				OnlineEKeyPoints oeDeleteKeyPoints = onlineEKeyPointsListByAjax(jdtParam).iterator().next();
				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + OnlineEKeyPoints.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND oeKeyPointsId = " + EWrap.Quote.enclose(oeKeyPointsId));

				_Txn = session.beginTransaction();
				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return oeDeleteKeyPoints;
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " deleteOnlineEKeyPoints", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " deleteOnlineEKeyPoints", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<OnlineEKeyPoints> getOnlineEKeyPointsListByJson(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
			{
				return session.createQuery(getOnlineEKeyPointsListByJsonQuery(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
			}
			else
			{
				return session.createQuery(getOnlineEKeyPointsListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " onlineEKeyPointsListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEKeyPoints>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineEKeyPoints> getOnlineEKeyPointsListByJsonAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getOnlineEKeyPointsListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " onlineEKeyPointsListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEKeyPoints>(0);
	}

	private StringBuffer getOnlineEKeyPointsListByJsonQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + OnlineEKeyPoints.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True ");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (oeKeyPointsName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeKeyPointsId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeSubject Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeChapter Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeSchoolType Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}
				sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " onlineEKeyPointsListByAjaxQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineEKeyPoints> onlineEKeyPointsListByAjax(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineEKeyPoints.class.getCanonicalName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND oeSubject Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeKeyPointsId))
				{
					sbSelectQry.append(" AND oeKeyPointsId Like " + EWrap.Quote.enclose(jdtParam.oeKeyPointsId));
				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeChapter))
				{
					sbSelectQry.append(" AND oeChapter Like " + EWrap.Quote.enclose(jdtParam.oeChapter));
				}
				sbSelectQry.append(" Order By createdDate Desc");
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " onlineEKeyPointsListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEKeyPoints>(0);
	}

	
	public boolean onlineEKeyPointsRepositoryUpload(OnlineEKeyPointsAction adminEKeyPoints)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			for (OnlineEKeyPoints oeKeyPoints : adminEKeyPoints.getOnlineEKeyPointsArray())
			{
				session.save(oeKeyPoints);
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " onlineEKeyPointsRepositoryUpload", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " onlineEKeyPointsRepositoryUpload", hibExcep.getMessage(), this.getClass().getName(), null);
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