package com.hbs.edutel.common.model;

import java.sql.Timestamp;

/**
 * FTPLog entity. @author MyEclipse Persistence Tools
 */

public class FTPLog implements java.io.Serializable
{

	// Fields

	private static final long	serialVersionUID	= -1672894280234585668L;
	private Integer				flFtpAutoId;
	private String				flFileName;
	private Integer				flFileSize;
	private Timestamp			flFileDate;
	private String				flInvoiceType;
	private String				flDmsPath;
	private String				flDmsId;
	private String				flNodeRef;
	private String				flFtpToDmsStatus;
	private String				flCreatedBy;
	private Timestamp			flCreatedDate;
	private String				flInvoiceProcessed;
	private Short				flWflogReadStatus;
	private String				flFailureAlerted;

	// Constructors

	/** default constructor */
	public FTPLog()
	{
	}

	/** minimal constructor */
	public FTPLog(String flFileName, Integer flFileSize, Timestamp flFileDate, String flInvoiceType, String flFtpToDmsStatus, String flCreatedBy, Timestamp flCreatedDate, Short flWflogReadStatus)
	{
		this.flFileName = flFileName;
		this.flFileSize = flFileSize;
		this.flFileDate = flFileDate;
		this.flInvoiceType = flInvoiceType;
		this.flFtpToDmsStatus = flFtpToDmsStatus;
		this.flCreatedBy = flCreatedBy;
		this.flCreatedDate = flCreatedDate;
		this.flWflogReadStatus = flWflogReadStatus;
	}

	// Property accessors

	public Integer getFlFtpAutoId()
	{
		return this.flFtpAutoId;
	}

	public void setFlFtpAutoId(Integer flFtpAutoId)
	{
		this.flFtpAutoId = flFtpAutoId;
	}

	public String getFlFileName()
	{
		return this.flFileName;
	}

	public void setFlFileName(String flFileName)
	{
		this.flFileName = flFileName;
	}

	public Integer getFlFileSize()
	{
		return this.flFileSize;
	}

	public void setFlFileSize(Integer flFileSize)
	{
		this.flFileSize = flFileSize;
	}

	public Timestamp getFlFileDate()
	{
		return this.flFileDate;
	}

	public void setFlFileDate(Timestamp flFileDate)
	{
		this.flFileDate = flFileDate;
	}

	public String getFlInvoiceType()
	{
		return this.flInvoiceType;
	}

	public void setFlInvoiceType(String flInvoiceType)
	{
		this.flInvoiceType = flInvoiceType;
	}

	public String getFlDmsPath()
	{
		return this.flDmsPath;
	}

	public void setFlDmsPath(String flDmsPath)
	{
		this.flDmsPath = flDmsPath;
	}

	public String getFlDmsId()
	{
		return this.flDmsId;
	}

	public void setFlDmsId(String flDmsId)
	{
		this.flDmsId = flDmsId;
	}

	public String getFlNodeRef()
	{
		return this.flNodeRef;
	}

	public void setFlNodeRef(String flNodeRef)
	{
		this.flNodeRef = flNodeRef;
	}

	public String getFlFtpToDmsStatus()
	{
		return this.flFtpToDmsStatus;
	}

	public void setFlFtpToDmsStatus(String flFtpToDmsStatus)
	{
		this.flFtpToDmsStatus = flFtpToDmsStatus;
	}

	public String getFlCreatedBy()
	{
		return this.flCreatedBy;
	}

	public void setFlCreatedBy(String flCreatedBy)
	{
		this.flCreatedBy = flCreatedBy;
	}

	public Timestamp getFlCreatedDate()
	{
		return this.flCreatedDate;
	}

	public void setFlCreatedDate(Timestamp flCreatedDate)
	{
		this.flCreatedDate = flCreatedDate;
	}

	public String getFlInvoiceProcessed()
	{
		return this.flInvoiceProcessed;
	}

	public void setFlInvoiceProcessed(String flInvoiceProcessed)
	{
		this.flInvoiceProcessed = flInvoiceProcessed;
	}

	public Short getFlWflogReadStatus()
	{
		return this.flWflogReadStatus;
	}

	public void setFlWflogReadStatus(Short flWflogReadStatus)
	{
		this.flWflogReadStatus = flWflogReadStatus;
	}

	public String getFlFailureAlerted()
	{
		return this.flFailureAlerted;
	}

	public void setFlFailureAlerted(String flFailureAlerted)
	{
		this.flFailureAlerted = flFailureAlerted;
	}

}