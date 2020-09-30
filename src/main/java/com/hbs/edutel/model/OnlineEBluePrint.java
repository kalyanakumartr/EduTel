package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;
import com.hbs.edutel.util.CommonUtil;

public class OnlineEBluePrint implements Serializable, IUploadImageOrDocuments
{
	private static final long	serialVersionUID	= 1L;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected String			oeBluePrintId;
	protected String			oeBluePrintName;
	protected String			oeSchoolType;
	protected String			oeSubject;
	protected boolean			status				= true;
	protected Timestamp			uploadFileDate;
	protected String			uploadFileFolderURL;
	protected Timestamp			uploadFileLastModifiedDate;
	protected String			uploadFileName;
	protected String			uploadFileNamePrimaryId;
	protected String			uploadFileScreenShot;
	protected long				uploadFileSize;

	public OnlineEBluePrint()
	{
		super();
	}

	public OnlineEBluePrint(String oeBluePrintId)
	{
		super();
		this.oeBluePrintId = oeBluePrintId;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	public String getOeBluePrintId()
	{
		return oeBluePrintId;
	}

	public String getOeBluePrintName()
	{
		return CommonUtil.initCapsName(oeBluePrintName);
	}

	public String getOeSchoolType()
	{
		return oeSchoolType;
	}

	public String getOeSubject()
	{
		return oeSubject;
	}

	public Timestamp getUploadFileDate()
	{
		return uploadFileDate;
	}

	public String getUploadFileFolderURL()
	{
		return uploadFileFolderURL;
	}

	public Timestamp getUploadFileLastModifiedDate()
	{
		return uploadFileLastModifiedDate;
	}

	public String getUploadFileName()
	{
		return uploadFileName;
	}

	public String getUploadFileNamePrimaryId()
	{
		return uploadFileNamePrimaryId;
	}

	public String getUploadFileScreenShot()
	{
		return uploadFileScreenShot;
	}

	public long getUploadFileSize()
	{
		return uploadFileSize;
	}

	public boolean isStatus()
	{
		return status;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public void setOeBluePrintId(String oeBluePrintId)
	{
		this.oeBluePrintId = oeBluePrintId;
	}

	public void setOeBluePrintName(String oeBluePrintName)
	{
		this.oeBluePrintName = oeBluePrintName;
	}

	public void setOeSchoolType(String oeSchoolType)
	{
		this.oeSchoolType = oeSchoolType;
	}

	public void setOeSubject(String oeSubject)
	{
		this.oeSubject = oeSubject;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public void setUploadFileDate(Timestamp uploadFileDate)
	{
		this.uploadFileDate = uploadFileDate;
	}

	public void setUploadFileFolderURL(String uploadFileFolderURL)
	{
		this.uploadFileFolderURL = uploadFileFolderURL;
	}

	public void setUploadFileLastModifiedDate(Timestamp uploadFileLastModifiedDate)
	{
		this.uploadFileLastModifiedDate = uploadFileLastModifiedDate;
	}

	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}

	public void setUploadFileNamePrimaryId(String uploadFileNamePrimaryId)
	{
		this.uploadFileNamePrimaryId = uploadFileNamePrimaryId;
	}

	public void setUploadFileScreenShot(String uploadFileScreenShot)
	{
		this.uploadFileScreenShot = uploadFileScreenShot;
	}

	public void setUploadFileSize(long uploadFileSize)
	{
		this.uploadFileSize = uploadFileSize;
	}

}
