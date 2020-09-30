package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;

public class College implements Serializable, IUploadImageOrDocuments
{
	private static final long	serialVersionUID	= 1L;
	protected String			clAddress1;
	protected String			clAddress2;
	protected String			clAlternateMobileNo;
	protected String			clAlternativeEmail;
	protected String			clCity;
	protected String			clCollegeId;
	protected String			clCollegeName;
	protected String			clCollegeType;
	protected String			clCountry;
	protected Double			clCourierCharge		= new Double(0);
	protected Double			clEduTelCharge		= new Double(0);
	protected String			clEmail;
	protected String			clFaxNo;
	protected String			clFormCloseDate;
	protected Double			clFormCostOthers	= new Double(0);
	protected Double			clFormCostSCST		= new Double(0);
	protected String			clFormOpenDate;
	protected String			clFormType;
	protected String			clLandMark;
	protected String			clMobileNo;
	protected String			clPhoneNo1;
	protected String			clPhoneNo2;
	protected String			clPhoneNo3;
	protected String			clPhoneNo4;
	protected String			clPhoneNo5;
	protected String			clPinCode;
	protected String			clState;
	protected String			clWebSiteURL;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected boolean			status				= true;
	protected Timestamp			uploadFileDate;
	protected String			uploadFileFolderURL;
	protected Timestamp			uploadFileLastModifiedDate;
	protected String			uploadFileName;
	protected String			uploadFileNamePrimaryId;
	protected String			uploadFileScreenShot;
	protected long				uploadFileSize;

	public College()
	{
		super();
	}

	public College(String clCollegeId)
	{
		super();
		this.clCollegeId = clCollegeId;
	}

	public String getClAddress1()
	{
		return clAddress1;
	}

	public String getClAddress2()
	{
		return clAddress2;
	}

	public String getClAlternateMobileNo()
	{
		return clAlternateMobileNo;
	}

	public String getClAlternativeEmail()
	{
		return clAlternativeEmail;
	}

	public String getClCity()
	{
		return clCity;
	}

	public String getClCollegeId()
	{
		return clCollegeId;
	}

	public String getClCollegeName()
	{
		return clCollegeName;
	}

	public String getClCollegeType()
	{
		return clCollegeType;
	}

	public String getClCountry()
	{
		return clCountry;
	}

	public Double getClCourierCharge()
	{
		return clCourierCharge;
	}

	public Double getClEduTelCharge()
	{
		return clEduTelCharge;
	}

	public String getClEmail()
	{
		return clEmail;
	}

	public String getClFaxNo()
	{
		return clFaxNo;
	}

	public String getClFormCloseDate()
	{
		return clFormCloseDate;
	}

	public Double getClFormCostOthers()
	{
		return clFormCostOthers;
	}

	public Double getClFormCostSCST()
	{
		return clFormCostSCST;
	}

	public String getClFormOpenDate()
	{
		return clFormOpenDate;
	}

	public String getClFormType()
	{
		return clFormType;
	}

	public String getClLandMark()
	{
		return clLandMark;
	}

	public String getClMobileNo()
	{
		return clMobileNo;
	}

	public String getClPhoneNo1()
	{
		return clPhoneNo1;
	}

	public String getClPhoneNo2()
	{
		return clPhoneNo2;
	}

	public String getClPhoneNo3()
	{
		return clPhoneNo3;
	}

	public String getClPhoneNo4()
	{
		return clPhoneNo4;
	}

	public String getClPhoneNo5()
	{
		return clPhoneNo5;
	}

	public String getClPinCode()
	{
		return clPinCode;
	}

	public String getClState()
	{
		return clState;
	}

	public String getClWebSiteURL()
	{
		return clWebSiteURL;
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

	public void setClAddress1(String clAddress1)
	{
		this.clAddress1 = clAddress1;
	}

	public void setClAddress2(String clAddress2)
	{
		this.clAddress2 = clAddress2;
	}

	public void setClAlternateMobileNo(String clAlternateMobileNo)
	{
		this.clAlternateMobileNo = clAlternateMobileNo;
	}

	public void setClAlternativeEmail(String clAlternativeEmail)
	{
		this.clAlternativeEmail = clAlternativeEmail;
	}

	public void setClCity(String clCity)
	{
		this.clCity = clCity;
	}

	public void setClCollegeId(String clCollegeId)
	{
		this.clCollegeId = clCollegeId;
	}

	public void setClCollegeName(String clCollegeName)
	{
		this.clCollegeName = clCollegeName;
	}

	public void setClCollegeType(String clCollegeType)
	{
		this.clCollegeType = clCollegeType;
	}

	public void setClCountry(String clCountry)
	{
		this.clCountry = clCountry;
	}

	public void setClCourierCharge(Double clCourierCharge)
	{
		this.clCourierCharge = clCourierCharge;
	}

	public void setClEduTelCharge(Double clEduTelCharge)
	{
		this.clEduTelCharge = clEduTelCharge;
	}

	public void setClEmail(String clEmail)
	{
		this.clEmail = clEmail;
	}

	public void setClFaxNo(String clFaxNo)
	{
		this.clFaxNo = clFaxNo;
	}

	public void setClFormCloseDate(String clFormCloseDate)
	{
		this.clFormCloseDate = clFormCloseDate;
	}

	public void setClFormCostOthers(Double clFormCostOthers)
	{
		this.clFormCostOthers = clFormCostOthers;
	}

	public void setClFormCostSCST(Double clFormCostSCST)
	{
		this.clFormCostSCST = clFormCostSCST;
	}

	public void setClFormOpenDate(String clFormOpenDate)
	{
		this.clFormOpenDate = clFormOpenDate;
	}

	public void setClFormType(String clFormType)
	{
		this.clFormType = clFormType;
	}

	public void setClLandMark(String clLandMark)
	{
		this.clLandMark = clLandMark;
	}

	public void setClMobileNo(String clMobileNo)
	{
		this.clMobileNo = clMobileNo;
	}

	public void setClPhoneNo1(String clPhoneNo1)
	{
		this.clPhoneNo1 = clPhoneNo1;
	}

	public void setClPhoneNo2(String clPhoneNo2)
	{
		this.clPhoneNo2 = clPhoneNo2;
	}

	public void setClPhoneNo3(String clPhoneNo3)
	{
		this.clPhoneNo3 = clPhoneNo3;
	}

	public void setClPhoneNo4(String clPhoneNo4)
	{
		this.clPhoneNo4 = clPhoneNo4;
	}

	public void setClPhoneNo5(String clPhoneNo5)
	{
		this.clPhoneNo5 = clPhoneNo5;
	}

	public void setClPinCode(String clPinCode)
	{
		this.clPinCode = clPinCode;
	}

	public void setClState(String clState)
	{
		this.clState = clState;
	}

	public void setClWebSiteURL(String clWebSiteURL)
	{
		this.clWebSiteURL = clWebSiteURL;
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