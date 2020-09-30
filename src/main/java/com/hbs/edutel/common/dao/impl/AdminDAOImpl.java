package com.hbs.edutel.common.dao.impl;

import java.util.List;

import com.hbs.edutel.action.AuditLoggingUDParam;
import com.hbs.edutel.common.dao.AdminDAO;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.logger.IAuditLogging;

public class AdminDAOImpl extends InfoAlertAdminDAOImpl implements AdminDAO
{
	private static final long		serialVersionUID	= -6282872836045369567L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());
	public List<IAuditLogging> getAuditLoggingDataList(AuditLoggingUDParam auditLoggingUDParam) {
		// TODO Auto-generated method stub
		return null;
	}

	

}