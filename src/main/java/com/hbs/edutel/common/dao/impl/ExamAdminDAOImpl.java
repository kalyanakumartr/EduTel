package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.IDashBoardAction;
import com.hbs.edutel.action.OnlineExamAction;
import com.hbs.edutel.action.OnlineExamAssignedAction;
import com.hbs.edutel.action.OnlineExamQuestionAction;
import com.hbs.edutel.action.OnlineExamQuestionAdminAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamAssigned;
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlineExamQuestionAnswerMapping;
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.hbs.edutel.model.ToppersClub;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessage;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessageTemplate;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessageType;
import com.hbs.edutel.util.common.ConstEnumUtil.EUser;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;
import com.hbs.messagesalert.model.Messages;
import com.hbs.messagesalert.model.MessagesUserMapping;

public abstract class ExamAdminDAOImpl extends EBookAdminDAOImpl
{
	private static final long	serialVersionUID	= -1199706846101110639L;
	private CustomAuditLogger	caLoggerExam		= new CustomAuditLogger(this.getClass());

	public ExamAdminDAOImpl()
	{
		super();
	}

	
	public OnlineExam blockUnlockExam(String oeExamId, String status, String modifiedBy)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(oeExamId))
			{
				session = getSession();
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				jdtParam.oeExamId = oeExamId;
				OnlineExam oeBlockExam = getOnlineExamListByJson(jdtParam).iterator().next();
				oeBlockExam.setOnlineAssessment(Boolean.valueOf(status));
				oeBlockExam.setStatus(Boolean.valueOf(status));
				oeBlockExam.setModifiedBy(modifiedBy);
				oeBlockExam.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				_Txn = session.beginTransaction();
				session.saveOrUpdate(oeBlockExam);
				_Txn.commit();
				return oeBlockExam;
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "blockUnlockExam", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "blockUnlockExam", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public OnlineExamAssigned blockUnlockExamAssigned(String oeExamAutoId, String status, String modifiedBy)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(oeExamAutoId))
			{
				session = getSession();
				JQueryDataTableParam jdtParam = new JQueryDataTableParam(Integer.parseInt(oeExamAutoId));
				jdtParam.status = null;
				OnlineExamAssigned oeBlockExam = getOnlineExamAssignedList(jdtParam).iterator().next();
				oeBlockExam.setStatus(Boolean.valueOf(status));
				oeBlockExam.setModifiedBy(modifiedBy);
				oeBlockExam.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				_Txn = session.beginTransaction();
				session.saveOrUpdate(oeBlockExam);
				_Txn.commit();
				return oeBlockExam;
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "blockUnlockExam", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "blockUnlockExam", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public OnlineExamQuestion blockUnlockExamQuestion(String oeExamQuestionId, String status, String modifiedBy)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(oeExamQuestionId))
			{
				session = getSession();

				OnlineExamQuestion oeBlockExamQuestion = getOnlineExamQuestionListByJson(new JQueryDataTableParam(oeExamQuestionId)).iterator().next();
				oeBlockExamQuestion.setStatus(Boolean.valueOf(status));
				oeBlockExamQuestion.setModifiedBy(modifiedBy);
				oeBlockExamQuestion.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				_Txn = session.beginTransaction();
				session.saveOrUpdate(oeBlockExamQuestion);
				_Txn.commit();
				return oeBlockExamQuestion;
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "blockUnlockExamQuestion", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "blockUnlockExamQuestion", hibExcep.getMessage(), this.getClass().getName(), null);
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

	public boolean createOnlineExam(OnlineExamAction adminExam)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			session.save(adminExam.getOnlineExam());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createOnlineExam", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createOnlineExam", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean createOnlineExamAssigned(OnlineExamAssignedAction adminExam)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			session.save(adminExam.getOnlineExamAssigned());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createOnlineExamAssigned", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createOnlineExamAssigned", hibExcep.getMessage(), this.getClass().getName(), null);
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

	public boolean createOnlineExamQuestion(OnlineExamQuestionAdminAction adminExamQuestion)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			session.save(adminExamQuestion.getOnlineExamQuestion());
			for (OnlineExamQuestionAnswerMapping oeExamQuestionAnswer : adminExamQuestion.getOnlineExamQuestionAnswerArray())
			{
				session.save(oeExamQuestionAnswer);
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createOnlineExamQuestion", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createOnlineExamQuestion", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean createOnlineTestSeriesExamAnswer(OnlineExamQuestionAdminAction adminExamQuestion)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.oeQuestionId = adminExamQuestion.getOnlineExamQuestion().getOeQuestionId();
			jdtParam.oeValidate = "false";
			jdtParam.usEmployeeId = adminExamQuestion.getOnlineTestSeriesExamAnswer().getCreatedBy();

			if (CommonValidator.isEqual(adminExamQuestion.getOnlineTestSeriesExamAnswer().getOeTestSeriesAnswerVIA(), "CourierOrPost"))
			{
				adminExamQuestion.getOnlineTestSeriesExamAnswer().setDownloaded(true);
			}
			session = getSession();
			_Txn = session.beginTransaction();
			session.save(adminExamQuestion.getOnlineTestSeriesExamAnswer());

			OnlineTestSeriesExamQuestionAnswerMapping oeaMap = getOnlineTestSeriesQuestionAnswer(jdtParam);
			if (CommonValidator.isNotNullNotEmpty(oeaMap))
			{
				oeaMap.setModifiedBy(adminExamQuestion.getOnlineTestSeriesExamAnswer().getModifiedBy());
				oeaMap.setModifiedDate(adminExamQuestion.getOnlineTestSeriesExamAnswer().getModifiedDate());
				oeaMap.setDownloaded(true);
				session.saveOrUpdate(oeaMap);
			}
			// Need to send mail here
			sendEmailToAdminForTestSeriesAnswerUploaded(adminExamQuestion);
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createOnlineTestSeriesExamAnswer", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createOnlineTestSeriesExamAnswer", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean createToppersClubInfo(ToppersClub toppersClub)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(toppersClub))
			{
				toppersClub.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				toppersClub.setStatus(true);

				session = getSession();
				_Txn = session.beginTransaction();
				session.save(toppersClub);
				_Txn.commit();
				return true;
			}

		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createToppersClubInfo", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "createToppersClubInfo", hibExcep.getMessage(), this.getClass().getName(), null);
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

	public OnlineExam deleteOnlineExam(String oeExamId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(oeExamId))
			{
				session = getSession();

				OnlineExam oeDeleteExam = getOnlineExamListByJson(new JQueryDataTableParam(oeExamId)).iterator().next();
				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + OnlineExam.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND oeExamId = " + EWrap.Quote.enclose(oeExamId));

				_Txn = session.beginTransaction();
				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return oeDeleteExam;
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExam", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExam", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public OnlineTestSeriesExamQuestionAnswerMapping deleteOnlineExamAnswer(OnlineExamQuestionAction onlineExamQuestionAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			String oeTestSeriesAutoId = onlineExamQuestionAction.getRequest().getParameter("oeTestSeriesAutoId");
			String employeeId = onlineExamQuestionAction.getRequest().getParameter("employeeId");
			if (CommonValidator.isNotNullNotEmpty(oeTestSeriesAutoId))
			{
				onlineExamQuestionAction.setOnlineTestSeriesExamAnswer(getTestSeriesExamAnswerById(new JQueryDataTableParam(oeTestSeriesAutoId)));
				OnlineTestSeriesExamQuestionAnswerMapping testSeriesExamAnswer = getTestSeriesExamAnswerByCreatedBy(new JQueryDataTableParam(employeeId));
				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + OnlineTestSeriesExamQuestionAnswerMapping.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND oeExamTestSeriesAutoId = " + EWrap.Quote.enclose(oeTestSeriesAutoId));
				session = getSession();
				_Txn = session.beginTransaction();

				if (CommonValidator.isNotNullNotEmpty(onlineExamQuestionAction.getOnlineTestSeriesExamAnswer()))
				{
					if (onlineExamQuestionAction.getOnlineTestSeriesExamAnswer().isOeValidatedAnswer() == true)
					{
						session.createQuery(sbDeleteQry.toString()).executeUpdate();
						if (CommonValidator.isNotNullNotEmpty(testSeriesExamAnswer))
						{
							testSeriesExamAnswer.setModifiedBy("");
							session.saveOrUpdate(testSeriesExamAnswer);
						}
					}
					else
					{
						if (CommonValidator.isNotNullNotEmpty(onlineExamQuestionAction.getUsEmployeeId()))
						{
							onlineExamQuestionAction.getOnlineTestSeriesExamAnswer().setModifiedBy(onlineExamQuestionAction.getUsEmployeeId());
							session.saveOrUpdate(onlineExamQuestionAction.getOnlineTestSeriesExamAnswer());
						}
					}
				}
				_Txn.commit();

				return onlineExamQuestionAction.getOnlineTestSeriesExamAnswer();
			}

		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExamAnswer", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExamAnswer", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public OnlineExamAssigned deleteOnlineExamAssigned(String oeExamAutoId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(oeExamAutoId))
			{
				session = getSession();
				JQueryDataTableParam jdtParam = new JQueryDataTableParam(Integer.parseInt(oeExamAutoId));
				jdtParam.status = null;
				OnlineExamAssigned oeDeleteExam = getOnlineExamAssignedList(jdtParam).iterator().next();
				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + OnlineExamAssigned.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND oeExamAutoId = " + oeExamAutoId);

				_Txn = session.beginTransaction();
				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return oeDeleteExam;
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExamAssigned", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExamAssigned", hibExcep.getMessage(), this.getClass().getName(), null);
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

	public OnlineExamQuestion deleteOnlineExamQuestion(String oeExamQuestionId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(oeExamQuestionId))
			{
				session = getSession();

				OnlineExamQuestion oeDeleteExamQuestion = getOnlineExamQuestionListByJson(new JQueryDataTableParam(oeExamQuestionId)).iterator().next();

				_Txn = session.beginTransaction();

				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + OnlineExamQuestionAnswerMapping.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND oeQuestionId = " + EWrap.Quote.enclose(oeExamQuestionId));

				session.createQuery(sbDeleteQry.toString()).executeUpdate();

				sbDeleteQry = new StringBuffer(DELETE + FROM + OnlineExamQuestion.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND oeQuestionId = " + EWrap.Quote.enclose(oeExamQuestionId));

				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return oeDeleteExamQuestion;
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExamQuestion", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "deleteOnlineExamQuestion", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public OnlineExam displayExamPublic(String oeExamId, boolean displayPublic, String modifiedBy)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(oeExamId))
			{
				session = getSession();
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				jdtParam.oeExamId = oeExamId;
				OnlineExam oeDispExam = getOnlineExamListByJson(jdtParam).iterator().next();
				// oeDispExam.setOnlineAssessment(displayPublic);
				oeDispExam.setDisplayPublic(displayPublic);
				oeDispExam.setModifiedBy(modifiedBy);
				oeDispExam.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				_Txn = session.beginTransaction();
				session.saveOrUpdate(oeDispExam);
				_Txn.commit();
				return oeDispExam;
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "displayExamPublic", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "displayExamPublic", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public List<String> getExamIdListFromPractiseTable(IDashBoardAction dashBoardAction)
	{
		List<String> oeExamIdList = new ArrayList<String>(0);
		if (CommonValidator.isNotNullNotEmpty(dashBoardAction.getUser()) && dashBoardAction.getUser().isStudent())
		{
			List<OnlinePractiseExam> onlinePractiseExamList = getOnlinePractiseExamList(dashBoardAction);
			if (CommonValidator.isListFirstNotEmpty(onlinePractiseExamList))
			{
				for (OnlinePractiseExam opExam : onlinePractiseExamList)
				{
					if (CommonValidator.isNotNullNotEmpty(opExam.getOnlineExamId()))
					{
						oeExamIdList.add(opExam.getOnlineExamId());
					}
				}
			}
		}
		return oeExamIdList;
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamAssigned> getOnlineExamAssignedList(IDashBoardAction dashBoardAction)
	{
		Session session = getSession();
		try
		{
			dashBoardAction.setOnlineExamFlag(true);
			// Check For Online Exam Assigned Taken Entries in Online Practice Exam Table
			// AND displayPublic = true Not Applicable
			StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineExamAssigned.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND onlineExam.onlineAssessment = true AND status = true");
			sbSelectQry.append(" AND onlineExam.oeSchoolType = " + EWrap.Quote.enclose(dashBoardAction.getUser().getUsBoardName()));
			sbSelectQry.append(" AND onlineExam.oeSubject In " + EWrap.Brace.enclose(EWrap.Quote.enclose(dashBoardAction.getUser().getUsGroupName().split(COMMA_SPACE.trim()))));
			sbSelectQry.append(" AND usEmployeeId = " + EWrap.Quote.enclose(dashBoardAction.getUser().getUsEmployeeId()));
			if (dashBoardAction.getSessionUser().isStudent())
			{
				List<String> oeExamIdList = getExamIdListFromPractiseTable(dashBoardAction);
				if (CommonValidator.isListFirstNotEmpty(oeExamIdList))
				{
					sbSelectQry.append(" AND onlineExam.oeExamId Not In " + EWrap.Brace.enclose(EWrap.Quote.enclose(oeExamIdList.toArray())));
				}
				sbSelectQry.append(" Order By status, oeAssignedExamDate Desc");
			}
			else
			{
				sbSelectQry.append(" Order By status, createdDate Desc");
			}
			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamAssignedList", excep.getMessage(), this.getClass().getName(), "DashBoardAction");
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamAssigned>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamAssigned> getOnlineExamAssignedList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				boolean isFilter = false;
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineExamAssigned.class.getCanonicalName() + WHERE_1_1);

				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeExamAutoId) && jdtParam.oeExamAutoId > 0)
				{
					sbSelectQry.append(" AND oeExamAutoId = " + jdtParam.oeExamAutoId);
					isFilter = true;
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeExamId))
				{
					sbSelectQry.append(" AND onlineExam.oeExamId = " + EWrap.Quote.enclose(jdtParam.oeExamId));
					isFilter = true;
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.usEmployeeId))
				{
					sbSelectQry.append(" AND users.usEmployeeId = " + EWrap.Quote.enclose(jdtParam.usEmployeeId));
					isFilter = true;
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeAssignedExamDate))
				{
					sbSelectQry.append(" AND oeAssignedExamDate = " + EWrap.Quote.enclose(jdtParam.oeAssignedExamDate));
					isFilter = true;
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.status))
				{
					sbSelectQry.append(" AND status = " + jdtParam.status); // boolean flag
					isFilter = true;
				}

				sbSelectQry.append(" Order By createdDate ");
				if (isFilter)
					return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamAssignedList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamAssigned>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamAssigned> getOnlineExamAssignedListByJson(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
					return session.createQuery(getOnlineExamAssignedListByJsonQuery(jdtParam).toString()).setMaxResults(jdtParam.iDisplayLength).setFirstResult(jdtParam.iDisplayStart).list();
				else
					return session.createQuery(getOnlineExamAssignedListByJsonQuery(jdtParam).toString()).list();
			}
			else
			{
				throw new Exception("getOnlineExamAssignedListByJson - JQueryDataTableParam is Null or Empty");
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamAssignedListByJson", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamAssigned>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamAssigned> getOnlineExamAssignedListByJsonAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getOnlineExamAssignedListByJsonQuery(jdtParam).toString()).list();
			}
			else
			{
				throw new Exception("getOnlineExamAssignedListByJsonAll - JQueryDataTableParam is Null or Empty");
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamAssignedListByJsonAll", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamAssigned>(0);
	}

	private StringBuffer getOnlineExamAssignedListByJsonQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineExamAssigned.class.getCanonicalName() + WHERE_1_1);
		if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
		{
			sbSelectQry.append(" AND (onlineExam.oeExamName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR onlineExam.oeExamDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR oeAssignedExamDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR users.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR users.usUserID Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR users.usEmployeeId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" ) Order By createdDate " + jdtParam.sSortDirection);
		}

		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExam> getOnlineExamList(IDashBoardAction dashBoardAction)
	{
		Session session = getSession();
		try
		{
			dashBoardAction.setOnlineExamFlag(true);

			// Check For Online Exam Taken Entries in Online Practice Exam Table
			StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineExam.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND onlineAssessment = true AND status = true");
			sbSelectQry.append(" AND oeSchoolType = " + EWrap.Quote.enclose(dashBoardAction.getUser().getUsBoardName()));
			sbSelectQry.append(" AND oeSubject In " + EWrap.Brace.enclose(EWrap.Quote.enclose(dashBoardAction.getUser().getUsGroupName().split(COMMA_SPACE.trim()))));

			if (dashBoardAction.getSessionUser().isStudent())
			{
				List<String> oeExamIdList = getExamIdListFromPractiseTable(dashBoardAction);
				if (CommonValidator.isListFirstNotEmpty(oeExamIdList))
				{
					sbSelectQry.append(" AND oeExamId Not In " + EWrap.Brace.enclose(EWrap.Quote.enclose(oeExamIdList.toArray())));
				}
				sbSelectQry.append(" AND displayPublic = true");
			}

			sbSelectQry.append(" Order By status, createdDate Desc");
			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamList", excep.getMessage(), this.getClass().getName(), "DashBoardAction");
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExam>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExam> getOnlineExamListByJson(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
					return session.createQuery(getOnlineExamListByJsonQuery(jdtParam).toString()).setMaxResults(jdtParam.iDisplayLength).setFirstResult(jdtParam.iDisplayStart).list();
				else
					return session.createQuery(getOnlineExamListByJsonQuery(jdtParam).toString()).list();
			}
			else
			{
				throw new Exception("getOnlineExamListByJson - JQueryDataTableParam is Null or Empty");
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamListByJson", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExam>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExam> getOnlineExamListByJsonAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getOnlineExamListByJsonQuery(jdtParam).toString()).list();
			}
			else
			{
				throw new Exception("getOnlineExamListByJson - JQueryDataTableParam is Null or Empty");
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamListByJson", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExam>(0);
	}

	private StringBuffer getOnlineExamListByJsonQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineExam.class.getCanonicalName() + WHERE_1_1);
		if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
		{
			sbSelectQry.append(" AND oeExamName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR oeSubject Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR oeChapter Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR oeSchoolType Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR oeExamDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			if (CommonValidator.isEqual(jdtParam.sSearch, "true") || CommonValidator.isEqual(jdtParam.sSearch, "false"))
			{
				sbSelectQry.append(" OR displayPublic = " + jdtParam.sSearch);
			}
			sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" OR oeExamId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
			sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
		}
		else if (CommonValidator.isNotNullNotEmpty(jdtParam.oeExamId))
		{
			sbSelectQry.append(" AND oeExamId = " + EWrap.Quote.enclose(jdtParam.oeExamId));
			sbSelectQry.append(" Order By createdDate desc");
		}
		/*
		 * else if (CommonValidator.isNotNullNotEmpty(jdtParam.isOnlineAssessment))//
		 * Online_Exam_Assigned { sbSelectQry.append(" AND oeExamType = 'MCQ'");
		 * sbSelectQry.append(" Order By createdDate desc"); }
		 */
		else
		{
			sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
		}

		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public OnlineExamQuestion getOnlineExamQuestion(OnlineExamQuestionAdminAction onlinenExamQuestion)
	{
		Session session = getSession();
		try
		{
			int idx = 0;
			if (CommonValidator.isNotNullNotEmpty(onlinenExamQuestion))
			{

				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineExamQuestion.class.getCanonicalName() + WHERE_1_1 + " AND status = true ");
				sbSelectQry.append(" AND oeQuestionId = " + EWrap.Quote.enclose(onlinenExamQuestion.getOnlineExamQuestion().getOeQuestionId()));
				sbSelectQry.append(" AND onlineExam.oeSubject = " + EWrap.Quote.enclose(onlinenExamQuestion.getOnlineExam().getOeSubject()));
				sbSelectQry.append(" AND onlineExam.oeChapter = " + EWrap.Quote.enclose(onlinenExamQuestion.getOnlineExam().getOeChapter()));
				sbSelectQry.append(" AND oeMarkPerAnswer = " + EWrap.Quote.enclose("1"));
				sbSelectQry.append(" Order By createdDate Desc ");
				List<OnlineExamQuestion> oEQList = session.createQuery(sbSelectQry.toString()).list();
				if (CommonValidator.isListFirstNotEmpty(oEQList))
				{
					if (oEQList.size() > 1)
					{
						idx = new Random().nextInt(oEQList.size() + 1);
					}

					return oEQList.get(idx);
				}
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamQuestion", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamQuestion> getOnlineExamQuestionList(OnlineExamQuestionAdminAction oeQuesAction)
	{

		Session session = getSession();
		try
		{
			List<OnlineExamQuestion> oeQuestionList = new ArrayList<OnlineExamQuestion>();
			String examIdQry = null;
			if (CommonValidator.isNotNullNotEmpty(oeQuesAction))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineExamQuestion.class.getCanonicalName() + WHERE_1_1 + " AND status = true ");
				sbSelectQry.append(" AND onlineExam.oeSubject = " + EWrap.Quote.enclose(oeQuesAction.getOeSubject()));
				if (CommonValidator.isNotNullNotEmpty(oeQuesAction.getOeExamId()))
				{
					examIdQry = " AND onlineExam.oeExamId = " + EWrap.Quote.enclose(oeQuesAction.getOeExamId());
					sbSelectQry.append(examIdQry);
				}

				if (CommonValidator.isNotNullNotEmpty(oeQuesAction.getOeChapter()))
				{
					sbSelectQry.append(" AND onlineExam.oeChapter In " + EWrap.Brace.enclose(EWrap.Quote.enclose(oeQuesAction.getOeChapter())));
				}
				if (CommonValidator.isNotNullNotEmpty(oeQuesAction.getOeSchoolType()))
				{
					sbSelectQry.append(" AND onlineExam.oeSchoolType = " + EWrap.Quote.enclose(oeQuesAction.getOeSchoolType()));
				}
				if (CommonValidator.isNotNullNotEmpty(oeQuesAction.getOeMarkPerAnswer()))
				{
					sbSelectQry.append(" AND oeMarkPerAnswer = " + EWrap.Quote.enclose(oeQuesAction.getOeMarkPerAnswer()));
				}
				sbSelectQry.append(" Order By oeChapter, createdDate Desc ");
				oeQuestionList = session.createQuery(sbSelectQry.toString()).list();
				if (CommonValidator.isListFirstNotEmpty(oeQuestionList))
				{
					return oeQuestionList;
				}
				else
				{
					while ( sbSelectQry != null && sbSelectQry.indexOf(examIdQry) != -1 )
					{
						sbSelectQry.delete(sbSelectQry.indexOf(examIdQry), sbSelectQry.indexOf(examIdQry) + examIdQry.length());
					}
					oeQuestionList = session.createQuery(sbSelectQry.toString()).list();
					return oeQuestionList;

				}
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamQuestionList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamQuestion>(0);

	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamQuestion> getOnlineExamQuestionListByJson(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
			{
				return session.createQuery(getOnlineExamQuestionListByJsonQuery(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
			}
			else
			{
				return session.createQuery(getOnlineExamQuestionListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamQuestionListByJson", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamQuestion>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamQuestion> getOnlineExamQuestionListByJsonAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getOnlineExamQuestionListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamQuestionListByJson", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamQuestion>(0);
	}

	private StringBuffer getOnlineExamQuestionListByJsonQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + OnlineExamQuestion.class.getCanonicalName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeExamId))
				{
					sbSelectQry.append(" AND onlineExam.oeExamId Like " + EWrap.Quote.enclose(jdtParam.oeExamId));
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (onlineExam.oeExamName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR onlineExam.oeExamId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeQuestion Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeCorrectAnswer Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeQuestionId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSortDirection))
				{
					sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
				}
				else
				{
					sbSelectQry.append(" Order By createdDate asc");
				}
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineExamQuestionListByJsonQuery", excep.getMessage(), this.getClass().getName(), null);
		}
		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamQuestion> getOnlineTestSeriesExamQuestionListByJson(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayStart) && CommonValidator.isNotNullNotEmpty(jdtParam.iDisplayLength) && jdtParam.iDisplayLength != 0)
			{
				return session.createQuery(getOnlineTestSeriesExamQuestionListByJsonQuery(jdtParam).toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
			}
			else
			{
				return session.createQuery(getOnlineTestSeriesExamQuestionListByJsonQuery(jdtParam).toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineTestSeriesExamQuestionListByJson", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamQuestion>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineExamQuestion> getOnlineTestSeriesExamQuestionListByJsonAll(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				return session.createQuery(getOnlineTestSeriesExamQuestionListByJsonQuery(jdtParam).toString()).list();
			}

		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineTestSeriesExamQuestionListByJson", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineExamQuestion>(0);
	}

	private StringBuffer getOnlineTestSeriesExamQuestionListByJsonQuery(JQueryDataTableParam jdtParam)
	{
		StringBuffer sbSelectQry = null;
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry = new StringBuffer(FROM + OnlineExamQuestion.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND oeQuestionType = 'TestSeries'");
				if (CommonValidator.isNotNullNotEmpty(jdtParam.oeQuestionId))
				{
					sbSelectQry.append(" AND oeQuestionId = " + EWrap.Quote.enclose(jdtParam.oeQuestionId));
				}
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND (onlineExam.oeExamName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR onlineExam.oeExamId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeQuestion Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR oeQuestionId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch) + ")");
				}

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSortDirection))
				{
					sbSelectQry.append(" Order By createdDate " + jdtParam.sSortDirection);
				}
				else
				{
					sbSelectQry.append(" Order By createdDate asc");
				}
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineTestSeriesExamQuestionListByJsonQuery", excep.getMessage(), this.getClass().getName(), null);
		}
		return sbSelectQry;
	}

	@SuppressWarnings("unchecked")
	
	public OnlineTestSeriesExamQuestionAnswerMapping getOnlineTestSeriesQuestionAnswer(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineTestSeriesExamQuestionAnswerMapping.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND status = true ");
			sbSelectQry.append(" AND onlineExamQuestion.oeQuestionId = " + EWrap.Quote.enclose(jdtParam.oeQuestionId));
			if (CommonValidator.isNotNullNotEmpty(jdtParam.uploadFileName))
			{
				sbSelectQry.append(" AND uploadFileName = " + EWrap.Quote.enclose(jdtParam.uploadFileName));
			}

			if (CommonValidator.isNotNullNotEmpty(jdtParam.oeExamTestSeriesAutoId))
			{
				sbSelectQry.append(" AND oeExamTestSeriesAutoId = " + jdtParam.oeExamTestSeriesAutoId);
			}

			if (CommonValidator.isNotNullNotEmpty(jdtParam.oeValidate))
			{
				sbSelectQry.append(" AND oeValidatedAnswer = " + jdtParam.oeValidate);
			}
			if (CommonValidator.isNotNullNotEmpty(jdtParam.usEmployeeId))
			{
				sbSelectQry.append(" AND createdBy = " + EWrap.Quote.enclose(jdtParam.usEmployeeId));
			}

			sbSelectQry.append(" Order By createdDate Desc");
			List<OnlineTestSeriesExamQuestionAnswerMapping> oeTestSeriesAnsList = session.createQuery(sbSelectQry.toString()).list();
			if (CommonValidator.isListFirstNotEmpty(oeTestSeriesAnsList))
			{
				return oeTestSeriesAnsList.iterator().next();
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineTestSeriesQuestionAnswer", excep.getMessage(), this.getClass().getName(), jdtParam.usEmployeeId);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<OnlineTestSeriesExamQuestionAnswerMapping> getOnlineTestSeriesQuestionAnswerList(OnlineExamQuestionAdminAction oeQuestionAction)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineTestSeriesExamQuestionAnswerMapping.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND status = true ");
			sbSelectQry.append(" AND onlineExamQuestion.oeQuestionId = " + EWrap.Quote.enclose(oeQuestionAction.getOnlineExamQuestion().getOeQuestionId()));
			sbSelectQry.append(" AND createdBy = " + EWrap.Quote.enclose(oeQuestionAction.getUsEmployeeId()));
			sbSelectQry.append(" Order By createdDate Desc");
			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getOnlineTestSeriesQuestionAnswerList", excep.getMessage(), this.getClass().getName(), oeQuestionAction.getUsEmployeeId());
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineTestSeriesExamQuestionAnswerMapping>(0);
	}

	public OnlineTestSeriesExamQuestionAnswerMapping getTestSeriesExamAnswerByCreatedBy(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineTestSeriesExamQuestionAnswerMapping.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True AND createdBy = " + EWrap.Quote.enclose(jdtParam.sSearch));

				return (OnlineTestSeriesExamQuestionAnswerMapping) session.createQuery(sbSelectQry.toString()).list().iterator().next();
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "getTestSeriesExamAnswerById", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	
	public OnlineTestSeriesExamQuestionAnswerMapping getTestSeriesExamAnswerById(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineTestSeriesExamQuestionAnswerMapping.class.getCanonicalName() + WHERE_1_1);
				sbSelectQry.append(" AND status = True AND oeExamTestSeriesAutoId = " + EWrap.Quote.enclose(jdtParam.sSearch));

				return (OnlineTestSeriesExamQuestionAnswerMapping) session.createQuery(sbSelectQry.toString()).list().iterator().next();
			}
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "getTestSeriesExamAnswerById", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<ToppersClub> getToppersClubListByMarks(String lastExamName)
	{
		Session session = getSession();
		StringBuffer sbSelectQry = null;
		try
		{
			int maxLimit = 3;
			sbSelectQry = new StringBuffer(FROM + ToppersClub.class.getCanonicalName() + WHERE_1_1);
			if (CommonValidator.isNotNullNotEmpty(lastExamName))
			{
				sbSelectQry.append(" AND tpExamName = " + EWrap.Quote.enclose(lastExamName));
			}
			sbSelectQry.append(" AND tpRatings >= 3 ");
			sbSelectQry.append(" Order By tpMarks desc");
			sbSelectQry.append(" AND tpExamDuration asc");
			return session.createQuery(sbSelectQry.toString()).setMaxResults(maxLimit).list();
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getToppersClubListByMarks", excep.getMessage(), this.getClass().getName(), null);
		}
		return new ArrayList<ToppersClub>(0);
	}

	private boolean sendEmailToAdminForTestSeriesAnswerUploaded(OnlineExamQuestionAdminAction adminExamQuestion)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();

			// Make an entry in MessagesUserMapping Table for Sending SMS
			_Txn = session.beginTransaction();
			MessagesUserMapping _MUM = new MessagesUserMapping();

			_MUM = new MessagesUserMapping();
			_MUM.setUsEmployeeId(EUser.SuperAdmin.name());
			_MUM.setMessageStatus(EMessage.Pending.name());
			_MUM.setStatus(true);
			_MUM.setCreatedBy(adminExamQuestion.getUser().getUsEmployeeId());
			_MUM.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			_MUM.setMessageId(EMessageTemplate.TestSeriesAnswer_Email_Admin.name());
			_MUM.setMessages(new Messages(EMessageTemplate.TestSeriesAnswer_Email_Admin.name(), EMessageType.Email.name()));
			_MUM.setDataObject(_MUM.serialize(adminExamQuestion.getOnlineExamQuestion(), adminExamQuestion.getUser()));
			session.save(_MUM);
			_Txn.commit();

			return true;
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "sendEmailToAdminForTestSeriesAnswerUploaded", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "sendEmailToAdminForTestSeriesAnswerUploaded", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean validatedAnswersBulkUpload(OnlineExamQuestionAction oeQuestionAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			if (updateStduentTestSeriesAnswers(oeQuestionAction))
			{
				for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesExamQuesAnsMapping : oeQuestionAction.getOeTestSeriesExamQuesAnsMappingArray())
				{
					session.save(oeTestSeriesExamQuesAnsMapping);
				}
			}
			_Txn.commit();

			return true;
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "validatedAnswersBulkUpload", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "validatedAnswersBulkUpload", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean updateStduentTestSeriesAnswers(OnlineExamQuestionAction oeQuestionAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			for (OnlineTestSeriesExamQuestionAnswerMapping oeExamQuestionAnswerMapping : oeQuestionAction.getOeTestSeriesExamQuesAnswerArray())
			{
				oeExamQuestionAnswerMapping.setModifiedBy(oeQuestionAction.getUsEmployeeId());
				oeExamQuestionAnswerMapping.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				oeExamQuestionAnswerMapping.setDownloaded(true);
				session.saveOrUpdate(oeExamQuestionAnswerMapping);
			}

			_Txn.commit();

			return true;
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "updateStduentTestSeriesAnswers", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "updateStduentTestSeriesAnswers", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<OnlineTestSeriesExamQuestionAnswerMapping> getTestSeriesExamValidatedAnswersListByCreatedBy(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer(FROM + OnlineTestSeriesExamQuestionAnswerMapping.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND status = true ");
			if (CommonValidator.isNotNullNotEmpty(jdtParam.usEmployeeId))
				sbSelectQry.append(" AND createdBy = " + EWrap.Quote.enclose(jdtParam.usEmployeeId));
			sbSelectQry.append(" AND oeValidatedAnswer = true");
			sbSelectQry.append(" AND downloaded = false");
			sbSelectQry.append(" Order By createdDate Desc");
			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, " getTestSeriesExamValidatedAnswersListByCreatedBy", excep.getMessage(), this.getClass().getName(), jdtParam.usEmployeeId);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<OnlineTestSeriesExamQuestionAnswerMapping>(0);
	}

	public boolean updateDownloadStatusForTestSeriesAnswerMapping(OnlineTestSeriesExamQuestionAnswerMapping oeValidatedAnswer)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			if (CommonValidator.isNotNullNotEmpty(oeValidatedAnswer))
			{
				oeValidatedAnswer.setDownloaded(true);
				session.saveOrUpdate(oeValidatedAnswer);
			}

			_Txn.commit();

			return true;
		}
		catch (Exception excep)
		{
			caLoggerExam.error(Audit_Logging_Event_DAOImpl, "updateDownloadStatusForTestSeriesAnswerMapping", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerExam.error(Audit_Logging_Event_DAOImpl, "updateDownloadStatusForTestSeriesAnswerMapping", hibExcep.getMessage(), this.getClass().getName(), null);
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
