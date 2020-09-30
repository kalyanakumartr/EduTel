package com.hbs.edutel.common.action;

import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

public class ReportAction extends CommonActionBoData
{
	private static final long	serialVersionUID	= 8832009380906776357L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());
	public String				reportName			= null;
	public String				reportURL			= null;

	public String getReport()
	{
		setSession(getRequest().getSession());
		IUsers user = (IUsers) getSession().getAttribute(ESession.UserObject.getAttribute());
		String reportPath = getRequest().getRequestURI();

		StringBuffer reqURL = new StringBuffer("");
		String reportName = request.getParameter("reportParam");

		if (CommonValidator.isNotNullNotEmpty(reportName))
		{
		}

		reportURL = reqURL.toString();

		if (CommonValidator.isNotNullNotEmpty(user) && (CommonValidator.isNotNullNotEmpty(reportPath) && reportPath.lastIndexOf(SLASH) > 0))
		{
			String reportNames = reportPath.substring(reportPath.lastIndexOf(SLASH) + 1);
			caLogger.info(Audit_Logging_Event_Report, reportNames, reportURL, this.getClass().getName(), user.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	public String getReportName()
	{
		return reportName;
	}

	public String getReportURL()
	{
		return reportURL;
	}

	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}

	public void setReportURL(String reportURL)
	{
		this.reportURL = reportURL;
	}

}
