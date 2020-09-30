package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;

public class OnlineEKeyPoints implements Serializable, IUploadImageOrDocuments
{
	private static final long	serialVersionUID	= 1411247673630611797L;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected String			oeChapter;
	protected String			oeKeyPointsId;
	protected String			oeKeyPointsName;
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

	public OnlineEKeyPoints()
	{
		super();
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

	public String getOeChapter()
	{
		return oeChapter;
	}

	public String getOeKeyPointsId()
	{
		return oeKeyPointsId;
	}

	public String getOeKeyPointsName()
	{
		return oeKeyPointsName;
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

	public void setOeChapter(String oeChapter)
	{
		this.oeChapter = oeChapter;
	}

	public void setOeKeyPointsId(String oeKeyPointsId)
	{
		this.oeKeyPointsId = oeKeyPointsId;
	}

	public void setOeKeyPointsName(String oeKeyPointsName)
	{
		this.oeKeyPointsName = oeKeyPointsName;
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
