package com.hbs.messagesalert.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.dao.impl.CommonHibernateDaoSupport;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;
import com.hbs.messagesalert.action.MessagesAction;
import com.hbs.messagesalert.action.MessagesParam;
import com.hbs.messagesalert.action.MessagesUserAction;
import com.hbs.messagesalert.dao.MessagesDAO;
import com.hbs.messagesalert.model.Messages;
import com.hbs.messagesalert.model.MessagesBeanFieldInformation;
import com.hbs.messagesalert.model.MessagesBeanInformation;
import com.hbs.messagesalert.model.MessagesUserMapping;
import com.hbs.messagesalert.model.MessagesUsersGroup;

public class MessagesDAOImpl extends CommonHibernateDaoSupport implements MessagesDAO
{

	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLoggerMsg			= new CustomAuditLogger(this.getClass());

	
	public Messages blockUnlockMessages(String messageId, String status, String modifiedBy)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(messageId))
			{
				session = getSession();
				Messages messages = getMessagesList(new JQueryDataTableParam(messageId)).iterator().next();
				messages.setStatus(Boolean.valueOf(status));
				messages.setModifiedBy(modifiedBy);
				messages.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				_Txn = session.beginTransaction();
				session.saveOrUpdate(messages);
				_Txn.commit();
				return messages;
			}
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, "blockUnlockMessages", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerMsg.error(Audit_Logging_Event_DAOImpl, "blockUnlockMessages", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public MessagesUserMapping blockUnlockMessageUser(String msgAutoId, String status, String modifiedBy)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(msgAutoId))
			{
				session = getSession();
				MessagesUserMapping _MUM = getMessagesUserList(new JQueryDataTableParam(msgAutoId)).iterator().next();
				_MUM.setStatus(Boolean.valueOf(status));
				_MUM.setModifiedBy(modifiedBy);
				_MUM.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				_Txn = session.beginTransaction();
				session.saveOrUpdate(_MUM);
				_Txn.commit();
				return _MUM;
			}
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, "blockUnlockMessageUser", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerMsg.error(Audit_Logging_Event_DAOImpl, "blockUnlockMessageUser", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean createMessage(MessagesAction messagesAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			session.saveOrUpdate(messagesAction.getMessages());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : createMessage", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : createMessage", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean createMessagesBeanField(List<MessagesBeanFieldInformation> beanFieldList)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			if (CommonValidator.isListFirstNotEmpty(beanFieldList))
			{
				session = getSession();
				_Txn = session.beginTransaction();
				Timestamp tStamp = CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST);
				for (MessagesBeanFieldInformation beanField : beanFieldList)
				{
					beanField.setCreatedBy("SuperAdmin");
					beanField.setCreatedDate(tStamp);
					session.saveOrUpdate(beanField);
				}
				_Txn.commit();
			}
			return true;
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : createMessagesBeanField", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : createMessagesBeanField", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean createMessageUserMapping(MessagesUserAction messagesAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			for (MessagesUserMapping _MUM : messagesAction.getMessagesUserList())
			{
				session.saveOrUpdate(_MUM);
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : createMessageUserMapping", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : createMessageUserMapping", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public boolean createMessageUsersGroup(MessagesUserAction messagesAction)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			for (MessagesUsersGroup _MUG : messagesAction.getMessageUsersGroupList())
			{
				session.saveOrUpdate(_MUG);
			}
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : createMessageUsersGroup", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : createMessageUsersGroup", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public Messages deleteMessages(String messageId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(messageId))
			{
				session = getSession();

				Messages messages = getMessagesList(new JQueryDataTableParam(messageId)).iterator().next();
				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + Messages.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND messageId = " + EWrap.Quote.enclose(messageId));

				_Txn = session.beginTransaction();
				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return messages;
			}
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, "deleteMessages", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerMsg.error(Audit_Logging_Event_DAOImpl, "deleteMessages", hibExcep.getMessage(), this.getClass().getName(), null);
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

	
	public MessagesUserMapping deleteMessageUser(String msgAutoId)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{

			if (CommonValidator.isNotNullNotEmpty(msgAutoId))
			{
				session = getSession();

				MessagesUserMapping _MUM = getMessagesUserList(new JQueryDataTableParam(msgAutoId)).iterator().next();
				StringBuffer sbDeleteQry = new StringBuffer(DELETE + FROM + MessagesUserMapping.class.getCanonicalName() + WHERE_1_1);
				sbDeleteQry.append(" AND autoId = " + msgAutoId);

				_Txn = session.beginTransaction();
				session.createQuery(sbDeleteQry.toString()).executeUpdate();
				_Txn.commit();
				return _MUM;
			}
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, "deleteMessageUser", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerMsg.error(Audit_Logging_Event_DAOImpl, "deleteMessageUser", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<MessagesBeanFieldInformation> getMessagesBeanFieldList(MessagesParam msgParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + MessagesBeanFieldInformation.class.getCanonicalName() + WHERE_1_1);
			if (CommonValidator.isNotNullNotEmpty(msgParam.status))
				sbSelectQry.append(" AND status = " + msgParam.status);// Boolean Value
			if (CommonValidator.isNotNullNotEmpty(msgParam.beanDisplayName))
				sbSelectQry.append(" AND messageBean.beanDisplayName = " + EWrap.Quote.enclose(msgParam.beanDisplayName));

			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getMessagesBeanFieldList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<MessagesBeanFieldInformation>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<String> getMessagesBeanFieldNameList(MessagesParam msgParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(" Select beanFieldName From " + MessagesBeanFieldInformation.class.getCanonicalName() + WHERE_1_1);
			sbSelectQry.append(" AND beanDisplayName = " + EWrap.Quote.enclose(msgParam.beanDisplayName));
			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getMessagesBeanFieldNameList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<String>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<MessagesBeanInformation> getMessagesBeanList(MessagesParam msgParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + MessagesBeanInformation.class.getCanonicalName() + WHERE_1_1 + " AND status = true ");

			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getMessagesBeanList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<MessagesBeanInformation>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<Messages> getMessagesList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + Messages.class.getCanonicalName() + WHERE_1_1);

			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry.append(" AND (messageId Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR messageName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR deliveryDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR message Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR createdUser.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));

				if (CommonValidator.isEqual(jdtParam.sSearch, "Active") || CommonValidator.isEqual(jdtParam.sSearch, "InActive"))
					sbSelectQry.append(" OR status = " + CommonValidator.isEqual(jdtParam.sSearch, "Active"));

				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSortDirection))
				{
					sbSelectQry.append(" ) Order By createdDate " + jdtParam.sSortDirection);
				}
				else
				{
					sbSelectQry.append(" ) Order By createdDate Desc");
				}
				if (jdtParam.iDisplayLength > 0)
					return session.createQuery(sbSelectQry.toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
				else
					return session.createQuery(sbSelectQry.toString()).list();
			}
			else
			{
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getMessagesList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<Messages>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<MessagesUserMapping> getMessagesUserList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + MessagesUserMapping.class.getCanonicalName() + WHERE_1_1);

			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry.append(" AND messages.messageName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR autoId = " + jdtParam.sSearch);
				sbSelectQry.append(" OR user.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR user.usUserID Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR messageStatus Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR createdUser.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR modifiedUser.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));

				if (CommonValidator.isEqual(jdtParam.sSearch, "Active") || CommonValidator.isEqual(jdtParam.sSearch, "InActive"))
					sbSelectQry.append(" OR status = " + CommonValidator.isEqual(jdtParam.sSearch, "Active"));

				return session.createQuery(sbSelectQry.toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
			}
			else
			{
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getMessagesUserList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<MessagesUserMapping>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<MessagesUserMapping> getMessagesUserListByMessageOrUser(MessagesParam msgParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + MessagesUserMapping.class.getCanonicalName() + WHERE_1_1);

			if (CommonValidator.isNotNullNotEmpty(msgParam))
			{
				if (CommonValidator.isNotNullNotEmpty(msgParam.autoId) && msgParam.autoId > 0)
					sbSelectQry.append(" AND autoId = " + msgParam.autoId);
				if (CommonValidator.isNotNullNotEmpty(msgParam.messageId))
					sbSelectQry.append(" AND messages.messageId Like " + EWrap.Quote.enclose(msgParam.messageId));
				if (CommonValidator.isNotNullNotEmpty(msgParam.messageName))
					sbSelectQry.append(" AND messages.messageName Like " + EWrap.Quote.enclose(msgParam.messageName));
				if (CommonValidator.isNotNullNotEmpty(msgParam.messageType))
					sbSelectQry.append(" AND messages.messageType Like " + EWrap.Quote.enclose(msgParam.messageType));
				if (CommonValidator.isNotNullNotEmpty(msgParam.messageStatus))
					sbSelectQry.append(" AND messageStatus Like " + EWrap.Quote.enclose(msgParam.messageStatus));
				if (CommonValidator.isNotNullNotEmpty(msgParam.status))
					sbSelectQry.append(" AND status = " + msgParam.status);

				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getMessagesUserListByMessageOrUser", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<MessagesUserMapping>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<MessagesUserMapping> getMessagesUsersList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + MessagesUserMapping.class.getCanonicalName() + WHERE_1_1);

			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				sbSelectQry.append(" AND message.messageName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR user.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR user.usUserID Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR messageStatus Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR deliveryDate Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR createdUser.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				sbSelectQry.append(" OR modifiedUser.usUserName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));

				if (CommonValidator.isEqual(jdtParam.sSearch, "Active") || CommonValidator.isEqual(jdtParam.sSearch, "InActive"))
					sbSelectQry.append(" OR status = " + CommonValidator.isEqual(jdtParam.sSearch, "Active"));

				return session.createQuery(sbSelectQry.toString()).setFirstResult(jdtParam.iDisplayStart).setMaxResults(jdtParam.iDisplayLength).list();
			}
			else
			{
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getMessagesUsersList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<MessagesUserMapping>(0);
	}

	@SuppressWarnings("unchecked")
	
	public List<MessagesUsersGroup> getMessageUsersGroupList(MessagesParam msgParam)
	{
		Session session = getSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + MessagesUsersGroup.class.getCanonicalName() + WHERE_1_1);

			if (CommonValidator.isNotNullNotEmpty(msgParam))
			{
				if (CommonValidator.isNotNullNotEmpty(msgParam.userGroupName))
					sbSelectQry.append(" AND userGroupName Like " + EWrap.Quote.enclose(msgParam.userGroupName));
			}
			return session.createQuery(sbSelectQry.toString()).list();
		}
		catch (Exception excep)
		{
			caLoggerMsg.error(Audit_Logging_Event_DAOImpl, " DAOImpl : getMessageUsersGroupList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<MessagesUsersGroup>(0);
	}
}
