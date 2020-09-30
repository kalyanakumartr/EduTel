package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.OnlineEBookAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlineEBook;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public abstract class EBookAdminDAOImpl extends EnquiryAdminDAOImpl
{
	private static final long	serialVersionUID	= -8270647374516807657L;
	private CustomAuditLogger	caLoggerEbk			= new CustomAuditLogger(this.getClass());

	public EBookAdminDAOImpl()
	{
		super();
	}

	
	public OnlineEBook deleteOnlineEBook(String oeBookId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(oeBookId))
			{
				session = getSession();

				OnlineEBook oeDeleteBook = getOnlineEBookListByJson(new JQueryDataTableParam(oeBookId)).iterator().next();
				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + OnlineEBook.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND oeBookId = " + EWrap.Quote.enclose(oeBookId));

				_Txn = session.beginTransaction();
				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return oeDeleteBook;
			}
		}
		catch (Exception excep)
		{
			caLoggerEbk.error(Audit_Logging_Event_DAOImpl, " onlineEBookRepositoryUpload", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerEbk.error(Audit_Logging_Event_DAOImpl, " onlineEBookRepositoryUpload", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<OnlineEBook> getOnlineEBookListByJson(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
			{
				return session.createQuery(getOnlineEBookListByJsonQuery(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
			}
			else
			{
				return session.createQuery(getOnlineEBookListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerEbk.error(Audit_Logging_Event_DAOImpl, " onlineEBookListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEBook>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineEBook> getOnlineEBookListByJsonAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getOnlineEBookListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerEbk.error(Audit_Logging_Event_DAOImpl, " onlineEBookListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEBook>(0);
	}

	private StringBuffer getOnlineEBookListByJsonQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + OnlineEBook.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True ");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (oeSubject Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeBookName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeChapter Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeBookId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}
				sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
			}
		}
		catch (Exception excep)
		{
			caLoggerEbk.error(Audit_Logging_Event_DAOImpl, " onlineEBookListByAjaxQuery", excep.getMessage(), this.getClass().getName(), null);
		}

		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineEBook> onlineEBookListByAjax(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineEBook.class.getCanonicalName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND oeSubject Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeBookId))
				{
					sbSelectQry.append(" AND oeBookId Like " + EWrap.Quote.enclose(jdtParam.oeBookId));
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeChapter))
				{
					sbSelectQry.append(" AND oeChapter Like " + EWrap.Quote.enclose(jdtParam.oeChapter));
				}
				sbSelectQry.append(" Order By oeChapter ");
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerEbk.error(Audit_Logging_Event_DAOImpl, " onlineEBookListByAjax", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineEBook>(0);
	}

	
	public boolean onlineEBookRepositoryUpload(OnlineEBookAction onlineEBookAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			for (OnlineEBook oeBook : onlineEBookAction.getOnlineEBookArray())
			{
				session.save(oeBook);
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerEbk.error(Audit_Logging_Event_DAOImpl, " onlineEBookRepositoryUpload", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerEbk.error(Audit_Logging_Event_DAOImpl, " onlineEBookRepositoryUpload", hibExcep.getMessage(), this.getClass().getName(), null);
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