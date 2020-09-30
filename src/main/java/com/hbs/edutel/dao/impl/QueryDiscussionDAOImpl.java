package com.hbs.edutel.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.QueryDiscussionAction;
import com.hbs.edutel.bo.QueryDiscussionModel;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.dao.impl.CommonHibernateDaoSupport;
import com.hbs.edutel.dao.QueryDiscussionDAO;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.QueryDiscussion;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public class QueryDiscussionDAOImpl extends CommonHibernateDaoSupport implements QueryDiscussionDAO
{

	private static final long	serialVersionUID	= -1169514393832485910L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	@SuppressWarnings("unchecked")
	
	public QueryDiscussion deleteQueryDiscussion(String qtAutoId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(qtAutoId))
			{
				session = getSession();

				StringBuffer sbSelectQry = new StringBuffer(FROM + QueryDiscussion.class.getName() + WHERE_1_1);
				sbSelectQry.append(" AND qtAutoId = " + qtAutoId);

				List<QueryDiscussion> qtList = session.createQuery(sbSelectQry.toString()).list();
				if (CommonValidator.isListFirstNotEmpty(qtList))
				{
					StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + QueryDiscussion.class.getCanonicalName() + WHERE_1_1);
					sbDeleteQry.append(" AND qtAutoId = " + qtAutoId);

					_Txn = session.beginTransaction();
					session.createQuery(sbDeleteQry.toString()).executeUpdate();
					_Txn.commit();
					return qtList.iterator().next();
				}
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, "deleteQueryDiscussion", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, "deleteQueryDiscussion", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<QueryDiscussion> queryAnswerList(JQueryDataTableParam jdtParam)
	{
		Session session = null;
		try
		{
			session = getSession();
			if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
			{
				return session.createQuery(queryAnswerListQry(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
			}
			else
			{
				return session.createQuery(queryAnswerListQry(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : queryAnswerList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<QueryDiscussion>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<QueryDiscussion> queryAnswerListAll(JQueryDataTableParam jdtParam)
	{
		Session session = null;
		try
		{
			session = getSession();
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(queryAnswerListQry(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : queryAnswerListAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<QueryDiscussion>(0);
	}

	public StringBuffer queryAnswerListQry(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = new StringBuffer();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry.append(FROM + QueryDiscussion.class.getName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (qtQuery Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR qtAnswer Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR qtRaisedName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR qtReplyName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" ) ");
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.usEmployeeId))
				{
					sbSelectQry.append(" AND qtRaisedBy Like " + EWrap.Quote.enclose(jdtParam.usEmployeeId));
				}
				if (jdtParam.isLibrary)
				{
					sbSelectQry.append(" AND qtRated > 2 AND qtRated < 6");
				}
				sbSelectQry.append(" ORDER BY qtAnswer, qtRaisedDate Desc ");
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : queryAnswerListQry", excep.getMessage(), this.getClass().getName(), null);

		}
		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public boolean queryAnswerUpdate(QueryDiscussionAction qdAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(qdAction.getQtAutoId()) && (CommonValidator.isNotNullNotEmpty(qdAction.getQtAnswer()) || CommonValidator.isNotNullNotEmpty(qdAction.getQtRated())))
			{
				session = getSession();

				StringBuffer sbSelectQry = new StringBuffer(FROM + QueryDiscussion.class.getName() + WHERE_1_1);
				sbSelectQry.append(" AND qtAutoId = " + qdAction.getQtAutoId());

				List<QueryDiscussion> qtList = session.createQuery(sbSelectQry.toString()).list();
				if (CommonValidator.isListFirstNotEmpty(qtList))
				{
					_Txn = session.beginTransaction();
					QueryDiscussion qt = qtList.iterator().next();
					if (CommonValidator.isNotNullNotEmpty(qdAction.getQtAnswer()))
					{
						qt.setQtAnswer(qdAction.getQtAnswer());
					}
					if (CommonValidator.isNotNullNotEmpty(qdAction.getQtRated()))
					{
						qt.setQtRated(Integer.parseInt(qdAction.getQtRated()));
					}
					qt.setQtReplyBy(qdAction.getUsEmployeeId());
					qt.setQtReplyName(qdAction.getUsUserName());
					qt.setQtReplyDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
					session.saveOrUpdate(qt);
					_Txn.commit();
					return true;
				}
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : queryAnswerUpdate", excep.getMessage(), this.getClass().getName(), qdAction.getUsEmployeeId());
			excep.printStackTrace();
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : queryAnswerUpdate", hibExcep.getMessage(), this.getClass().getName(), qdAction.getUsEmployeeId());
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
		return false;
	}

	
	public boolean submitQueryAnswerByAdmin(QueryDiscussionModel qryDiscModel)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			int count = qryDiscModel.getQryDispParam().qtQuery.length;

			for (int i = 0; i < count; i++)
			{
				if (CommonValidator.isNotNullNotEmpty(qryDiscModel.getQryDispParam().qtAnswer[i]))
				{
					StringBuffer sbUpdateQry = new StringBuffer();
					sbUpdateQry.append("UPDATE QueryDiscussion QD  SET QD.qtAnswer = " + EWrap.Quote.enclose(qryDiscModel.getQryDispParam().qtAnswer[i]));
					sbUpdateQry.append(COMMA_SPACE + " QD.qtReplyBy = " + EWrap.Quote.enclose(qryDiscModel.getUsers().getUsEmployeeId()));
					sbUpdateQry.append(COMMA_SPACE + " QD.qtReplyName = " + EWrap.Quote.enclose(qryDiscModel.getUsers().getUsUserName()));
					sbUpdateQry.append(COMMA_SPACE + " QD.qtReplyDate = " + EWrap.Quote.enclose(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST)));
					sbUpdateQry.append(WHERE_1_1 + " AND QD.qtAutoId = " + qryDiscModel.getQryDispParam().qtAutoId[i]);
					sbUpdateQry.append(" AND QD.qtQuery = " + EWrap.Quote.enclose(qryDiscModel.getQryDispParam().qtQuery[i]));
					sbUpdateQry.append(" AND QD.qtRaisedBy = " + EWrap.Quote.enclose(qryDiscModel.getQryDispParam().qtRaisedBy[i]));

					session.createQuery(sbUpdateQry.toString()).executeUpdate();
				}

			}

			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : submitQueryAnswerByAdmin", excep.getMessage(), this.getClass().getName(), qryDiscModel.getUsers().getUsEmployeeId());
			excep.printStackTrace();
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : submitQueryAnswerByAdmin", hibExcep.getMessage(), this.getClass().getName(), qryDiscModel.getUsers().getUsEmployeeId());
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

	
	public boolean submitUserQuery(QueryDiscussionModel qryDiscModel)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();

			session.save(qryDiscModel.getQryDiscussion());
			_Txn.commit();
			return true;

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : submitUserQuery", excep.getMessage(), this.getClass().getName(), qryDiscModel.getUsers().getUsEmployeeId());
			excep.printStackTrace();
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : submitUserQuery", hibExcep.getMessage(), this.getClass().getName(), qryDiscModel.getUsers().getUsEmployeeId());
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
	
	public List<QueryDiscussion> viewAdminQueryAnswerList(QueryDiscussionModel qryDiscModel)
	{
		Session session = null;
		try
		{
			session = getSession();
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append("From QueryDiscussion QD " + WHERE_1_1);
			sbSelectQry.append(" AND QD.qtReplyBy = " + EWrap.Quote.enclose(qryDiscModel.getUsers().getUsEmployeeId()));
			sbSelectQry.append(" AND QD.qtReplyName = " + EWrap.Quote.enclose(qryDiscModel.getUsers().getUsUserName()));
			sbSelectQry.append(" ORDER BY QD.qtReplyDate Desc ");

			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : viewAdminQueryAnswerList", excep.getMessage(), this.getClass().getName(), qryDiscModel.getUsers().getUsEmployeeId());
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<QueryDiscussion>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<QueryDiscussion> viewAdminQueryList(QueryDiscussionModel qryDiscModel)
	{
		Session session = null;
		try
		{
			session = getSession();
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append("From QueryDiscussion QD " + WHERE_1_1);
			sbSelectQry.append(" AND QD.qtAnswer IS NULL");
			sbSelectQry.append(" ORDER BY QD.qtRaisedDate Desc ");

			return session.createQuery(sbSelectQry.toString()).list();

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : viewAdminQueryList", excep.getMessage(), this.getClass().getName(), qryDiscModel.getUsers().getUsEmployeeId());
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<QueryDiscussion>(0);

	}

	@SuppressWarnings("unchecked")
	
	public List<QueryDiscussion> viewUserQueryAnswerList(QueryDiscussionModel qryDiscModel)
	{
		Session session = null;
		try
		{
			session = getSession();

			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append("From QueryDiscussion QD " + WHERE_1_1);
			sbSelectQry.append(" AND QD.qtRaisedBy = " + EWrap.Quote.enclose(qryDiscModel.getUsers().getUsEmployeeId()));
			sbSelectQry.append(" AND QD.qtRaisedName = " + EWrap.Quote.enclose(qryDiscModel.getUsers().getUsUserName()));
			sbSelectQry.append(" ORDER BY QD.qtReplyDate Desc ");

			return session.createQuery(sbSelectQry.toString()).list();

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " DAOImpl : viewUserQueryAnswerList", excep.getMessage(), this.getClass().getName(), qryDiscModel.getUsers().getUsEmployeeId());
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return new ArrayList<QueryDiscussion>(0);
	}
}
