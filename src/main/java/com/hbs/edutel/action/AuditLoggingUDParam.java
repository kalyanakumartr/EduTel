package com.hbs.edutel.action;

public class AuditLoggingUDParam
{
	public String	clazz;
	public String	createdBy;
	public String	createdDate;
	public String[]	eAuditList;
	public int		maxDisplay	= 100;
	public int		minDisplay	= 1;

	public AuditLoggingUDParam(String createdBy, int minDisplay, int maxDisplay)
	{
		super();
		this.createdBy = createdBy;
		this.minDisplay = minDisplay;
		this.maxDisplay = maxDisplay;
	}

}
