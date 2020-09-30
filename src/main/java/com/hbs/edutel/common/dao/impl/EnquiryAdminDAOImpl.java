package com.hbs.edutel.common.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hbs.edutel.action.AdminStudentEnquiryAction;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.StudentEnquiry;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public abstract class EnquiryAdminDAOImpl extends UserAdminDAOImpl
{
	private static final long	serialVersionUID	= 4170924115155037579L;
	private CustomAuditLogger	caLoggerEnq			= new CustomAuditLogger(this.getClass());

	public EnquiryAdminDAOImpl()
	{
		super();
	}

	
	public boolean deleteEdutelEnquiry(AdminStudentEnquiryAction studEnq)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			StringBuffer sbDeleteQry = new StringBuffer();
			sbDeleteQry.append(DELETE + FROM + StudentEnquiry.class.getCanonicalName() + WHERE_1_1);
			sbDeleteQry.append(" AND enquirerAutoId = " + studEnq.getStudentEnquiry().getEnquirerAutoId());
			session.createQuery(sbDeleteQry.toString()).executeUpdate();

			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerEnq.error(Audit_Logging_Event_DAOImpl, " deleteEdutelEnquiry", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerEnq.error(Audit_Logging_Event_DAOImpl, " deleteEdutelEnquiry", hibExcep.getMessage(), this.getClass().getName(), null);
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
	
	public List<StudentEnquiry> getEnquiryList(JQueryDataTableParam jdtParam)
	{
		Session session = getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(jdtParam))
			{
				StringBuffer sbSelectQry = new StringBuffer(FROM + StudentEnquiry.class.getCanonicalName() + WHERE_1_1);
				if (CommonValidator.isNotNullNotEmpty(jdtParam.sSearch))
				{
					sbSelectQry.append(" AND enquirerName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR enquirerEmail Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR enquirerSchoolName Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR enquirerMobileNo Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR enquirerBoard Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR enquirerClass Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR enquirerState Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
					sbSelectQry.append(" OR createdBy Like " + EWrap.QuotePercent.enclose(jdtParam.sSearch));
				}
				sbSelectQry.append(" Order By createdDate Desc ");
				return session.createQuery(sbSelectQry.toString()).list();
			}
		}
		catch (Exception excep)
		{
			caLoggerEnq.error(Audit_Logging_Event_DAOImpl, " getEnquiryList", excep.getMessage(), this.getClass().getName(), null);
		}
		finally
		{
			session.clear();
			session.close();
		}
		return new ArrayList<StudentEnquiry>(0);
	}

	
	public boolean studentEdutelEnquiry(AdminStudentEnquiryAction admStudEnquiry)
	{

		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSession();
			_Txn = session.beginTransaction();
			admStudEnquiry.getStudentEnquiry().setCreatedBy("SuperAdmin");
			admStudEnquiry.getStudentEnquiry().setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			session.save(admStudEnquiry.getStudentEnquiry());
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			caLoggerEnq.error(Audit_Logging_Event_DAOImpl, " studentEdutelEnquiry", excep.getMessage(), this.getClass().getName(), null);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					caLoggerEnq.error(Audit_Logging_Event_DAOImpl, " studentEdutelEnquiry", hibExcep.getMessage(), this.getClass().getName(), null);
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

}