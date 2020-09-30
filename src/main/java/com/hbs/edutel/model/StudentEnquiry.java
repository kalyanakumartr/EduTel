package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StudentEnquiry implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String				createdBy;
	private Timestamp			createdDate;
	private int					enquirerAutoId;
	private String				enquirerBoard;
	private String				enquirerClass;
	private String				enquirerEmail;
	private String				enquirerMobileNo;
	private String				enquirerName;
	private String				enquirerSchoolName;
	private String				enquirerState;
	private String				modifiedBy;
	private Timestamp			modifiedDate;

	public StudentEnquiry()
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

	public int getEnquirerAutoId()
	{
		return enquirerAutoId;
	}

	public String getEnquirerBoard()
	{
		return enquirerBoard;
	}

	public String getEnquirerClass()
	{
		return enquirerClass;
	}

	public String getEnquirerEmail()
	{
		return enquirerEmail;
	}

	public String getEnquirerMobileNo()
	{
		return enquirerMobileNo;
	}

	public String getEnquirerName()
	{
		return enquirerName;
	}

	public String getEnquirerSchoolName()
	{
		return enquirerSchoolName;
	}

	public String getEnquirerState()
	{
		return enquirerState;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setEnquirerAutoId(int enquirerAutoId)
	{
		this.enquirerAutoId = enquirerAutoId;
	}

	public void setEnquirerBoard(String enquirerBoard)
	{
		this.enquirerBoard = enquirerBoard;
	}

	public void setEnquirerClass(String enquirerClass)
	{
		this.enquirerClass = enquirerClass;
	}

	public void setEnquirerEmail(String enquirerEmail)
	{
		this.enquirerEmail = enquirerEmail;
	}

	public void setEnquirerMobileNo(String enquirerMobileNo)
	{
		this.enquirerMobileNo = enquirerMobileNo;
	}

	public void setEnquirerName(String enquirerName)
	{
		this.enquirerName = enquirerName;
	}

	public void setEnquirerSchoolName(String enquirerSchoolName)
	{
		this.enquirerSchoolName = enquirerSchoolName;
	}

	public void setEnquirerState(String enquirerState)
	{
		this.enquirerState = enquirerState;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}
}
