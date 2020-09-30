package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;

public class School implements Serializable, IUploadImageOrDocuments
{
	private static final long	serialVersionUID	= 1L;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected String			scAddress1;
	protected String			scAddress2;
	protected String			scAlternateMobileNo;
	protected String			scAlternativeEmail;
	protected String			scCity;
	protected String			scCountry;
	protected String			scEmail;
	protected String			scFaxNo;
	protected String			scLandMark;
	protected String			scMobileNo;
	protected String			scPhoneNo1;
	protected String			scPhoneNo2;
	protected String			scPhoneNo3;
	protected String			scPhoneNo4;
	protected String			scPhoneNo5;
	protected String			scPinCode;
	protected String			scSchoolId;
	protected String			scSchoolName;
	protected String			scSchoolType;
	protected String			scState;
	protected String			scWebSiteURL;
	protected boolean			status				= true;
	protected Timestamp			uploadFileDate;
	protected String			uploadFileFolderURL;
	protected Timestamp			uploadFileLastModifiedDate;
	protected String			uploadFileName;
	protected String			uploadFileNamePrimaryId;
	protected String			uploadFileScreenShot;
	protected long				uploadFileSize;

	public School()
	{
		super();
	}

	public School(String scSchoolId)
	{
		super();
		this.scSchoolId = scSchoolId;
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

	public String getScAddress1()
	{
		return scAddress1;
	}

	public String getScAddress2()
	{
		return scAddress2;
	}

	public String getScAlternateMobileNo()
	{
		return scAlternateMobileNo;
	}

	public String getScAlternativeEmail()
	{
		return scAlternativeEmail;
	}

	public String getScCity()
	{
		return scCity;
	}

	public String getScCountry()
	{
		return scCountry;
	}

	public String getScEmail()
	{
		return scEmail;
	}

	public String getScFaxNo()
	{
		return scFaxNo;
	}

	public String getScLandMark()
	{
		return scLandMark;
	}

	public String getScMobileNo()
	{
		return scMobileNo;
	}

	public String getScPhoneNo1()
	{
		return scPhoneNo1;
	}

	public String getScPhoneNo2()
	{
		return scPhoneNo2;
	}

	public String getScPhoneNo3()
	{
		return scPhoneNo3;
	}

	public String getScPhoneNo4()
	{
		return scPhoneNo4;
	}

	public String getScPhoneNo5()
	{
		return scPhoneNo5;
	}

	public String getScPinCode()
	{
		return scPinCode;
	}

	public String getScSchoolId()
	{
		return scSchoolId;
	}

	public String getScSchoolName()
	{
		return scSchoolName;
	}

	public String getScSchoolType()
	{
		return scSchoolType;
	}

	public String getScState()
	{
		return scState;
	}

	public String getScWebSiteURL()
	{
		return scWebSiteURL;
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
		return scSchoolId;
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

	public void setScAddress1(String scAddress1)
	{
		this.scAddress1 = scAddress1;
	}

	public void setScAddress2(String scAddress2)
	{
		this.scAddress2 = scAddress2;
	}

	public void setScAlternateMobileNo(String scAlternateMobileNo)
	{
		this.scAlternateMobileNo = scAlternateMobileNo;
	}

	public void setScAlternativeEmail(String scAlternativeEmail)
	{
		this.scAlternativeEmail = scAlternativeEmail;
	}

	public void setScCity(String scCity)
	{
		this.scCity = scCity;
	}

	public void setScCountry(String scCountry)
	{
		this.scCountry = scCountry;
	}

	public void setScEmail(String scEmail)
	{
		this.scEmail = scEmail;
	}

	public void setScFaxNo(String scFaxNo)
	{
		this.scFaxNo = scFaxNo;
	}

	public void setScLandMark(String scLandMark)
	{
		this.scLandMark = scLandMark;
	}

	public void setScMobileNo(String scMobileNo)
	{
		this.scMobileNo = scMobileNo;
	}

	public void setScPhoneNo1(String scPhoneNo1)
	{
		this.scPhoneNo1 = scPhoneNo1;
	}

	public void setScPhoneNo2(String scPhoneNo2)
	{
		this.scPhoneNo2 = scPhoneNo2;
	}

	public void setScPhoneNo3(String scPhoneNo3)
	{
		this.scPhoneNo3 = scPhoneNo3;
	}

	public void setScPhoneNo4(String scPhoneNo4)
	{
		this.scPhoneNo4 = scPhoneNo4;
	}

	public void setScPhoneNo5(String scPhoneNo5)
	{
		this.scPhoneNo5 = scPhoneNo5;
	}

	public void setScPinCode(String scPinCode)
	{
		this.scPinCode = scPinCode;
	}

	public void setScSchoolId(String scSchoolId)
	{
		this.scSchoolId = scSchoolId;
	}

	public void setScSchoolName(String scSchoolName)
	{
		this.scSchoolName = scSchoolName;
	}

	public void setScSchoolType(String scSchoolType)
	{
		this.scSchoolType = scSchoolType;
	}

	public void setScState(String scState)
	{
		this.scState = scState;
	}

	public void setScWebSiteURL(String scWebSiteURL)
	{
		this.scWebSiteURL = scWebSiteURL;
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

	public void setUploadFileNamePrimaryId(String scSchoolId)
	{
		this.uploadFileNamePrimaryId = scSchoolId;
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
