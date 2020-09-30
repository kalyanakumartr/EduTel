package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.IDashBoardAction;
import com.hbs.edutel.action.IOnlineExamAction;
import com.hbs.edutel.action.OnlineExamQuestionAdminAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public abstract class ExamPractiseAdminDAOImpl extends ExamAdminDAOImpl
{
	private static final long	serialVersionUID	= -1478156614577681145L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public ExamPractiseAdminDAOImpl()
	{
		super();
	}

	
	public boolean createOnlinePractiseExam(IOnlineExamAction adminExam)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			session.save(adminExam.getOnlinePractiseExam());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, "createOnlinePractiseExam", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, "createOnlinePractiseExam", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public List<OnlinePractiseExam> deleteOnlineExamPracticeEntry(IDashBoardAction dashBoardAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(dashBoardAction.getUsEmployeeId()) && CommonValidator.isNotNullNotEmpty(dashBoardAction.getOeExamId()))
			{
				List<OnlinePractiseExam> oePractiseList = getOnlinePractiseExamList(dashBoardAction);

				if (CommonValidator.isListFirstNotEmpty(oePractiseList))
				{
					session = getSession();
					_Txn = session.beginTransaction();
					for (OnlinePractiseExam oePE : oePractiseList)
					{
						session.delete(oePE);
					}
					_Txn.commit();
					return oePractiseList;
				}
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExamPracticeEntry", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLogger.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExamPracticeEntry", hibExcep.getMessage(), this.getClass().getName(), null);
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
		return new ArrayList<OnlinePractiseExam>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlinePractiseExam> getOnlinePractiseExamList(IDashBoardAction dashBoardAction)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(dashBoardAction.getUsEmployeeId()))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlinePractiseExam.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status	= true AND oeIsOnlineExam = " + dashBoardAction.isOnlineExamFlag());
				if (CommonValidator.isNotNullNotEmpty(dashBoardAction.getOeExamId()))
				{
					sbSelectQry.append(" AND onlineExamId In " + EWrap.Brace.enclose(EWrap.Quote.enclose(dashBoardAction.getOeExamId().split(COMMA_SPACE))));
				}
				sbSelectQry.append(" AND createdBy = " + EWrap.Quote.enclose(dashBoardAction.getUsEmployeeId()));
				sbSelectQry.append(" Order By createdDate Desc ");
				return session.createQuery(sbSelectQry.toString()).setMaxResults(50).setFirstResult(0).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " getOnlinePractiseExamList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlinePractiseExam>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlinePractiseExam> getOnlinePractiseExamList(OnlineExamQuestionAdminAction onlineExamQuestion)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion.getUsEmployeeId()))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlinePractiseExam.class.getCanonicalName() + WHERE_1_1 + " AND status = true ");
				sbSelectQry.append(" AND oeSubject = " + EWrap.Quote.enclose(onlineExamQuestion.getOeSubject()));
				sbSelectQry.append(" AND oeChapter = " + EWrap.Quote.enclose(onlineExamQuestion.getOeChapter()));
				if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion.getOePractiseExamId()))
				{
					sbSelectQry.append(" AND oePractiseExamId = " + EWrap.Quote.enclose(onlineExamQuestion.getOePractiseExamId()));
				}
				sbSelectQry.append(" Order By createdDate Desc ");
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " getOnlinePractiseExamList-OnlineExamQuestionAction", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlinePractiseExam>(0);
	}

	@SuppressWarnings("unchecked")
	
	public boolean isExamTakenAlready(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer(FROM + OnlinePractiseExam.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND status	= true AND oeIsOnlineExam = true AND createdBy = " + EWrap.Quote.enclose(jdtParam.usEmployeeId));
			sbSelectQry.append(" AND onlineExamId = " + EWrap.Quote.enclose(jdtParam.oeExamId));
			sbSelectQry.append(" Order By createdDate Desc ");
			List<OnlinePractiseExam> oeList = session.createQuery(sbSelectQry.toString()).list();
			return CommonValidator.isListFirstNotEmpty(oeList);
		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DAOImpl, " isExamTakenAlready", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return false;
	}
}