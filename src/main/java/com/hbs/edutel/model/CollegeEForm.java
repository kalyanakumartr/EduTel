package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hbs.edutel.common.model.interfaces.IUsers;

public class CollegeEForm implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	protected Double			clCourierCharge		= new Double(0);
	protected Double			clEduTelCharge		= new Double(0);
	protected Double			clFormCostOthers	= new Double(0);
	protected Double			clFormCostSCST		= new Double(0);
	protected String			collegeId;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected IUsers			createdUser;
	protected String			employeeId;
	protected int				formAutoId;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected boolean			purchased;
	protected boolean			status;

	public CollegeEForm()
	{
		super();
	}

	public CollegeEForm(int formAutoId, String collegeId, String employeeId, Timestamp createdDate, Double clFormCostOthers, Double clFormCostSCST, Double clCourierCharge, Double clEduTelCharge,
			boolean status, boolean purchased, String modifiedBy, Timestamp modifiedDate)
	{
		super();
		this.formAutoId = formAutoId;
		this.collegeId = collegeId;
		this.employeeId = employeeId;
		this.createdDate = createdDate;
		this.clFormCostOthers = clFormCostOthers;
		this.clFormCostSCST = clFormCostSCST;
		this.clCourierCharge = clCourierCharge;
		this.clEduTelCharge = clEduTelCharge;
		this.status = status;
		this.purchased = purchased;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public Double getClCourierCharge()
	{
		return clCourierCharge;
	}

	public Double getClEduTelCharge()
	{
		return clEduTelCharge;
	}

	public Double getClFormCostOthers()
	{
		return clFormCostOthers;
	}

	public Double getClFormCostSCST()
	{
		return clFormCostSCST;
	}

	public String getCollegeId()
	{
		return collegeId;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	public String getEmployeeId()
	{
		return employeeId;
	}

	public int getFormAutoId()
	{
		return formAutoId;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	public boolean isPurchased()
	{
		return purchased;
	}

	public boolean isStatus()
	{
		return status;
	}

	public void setClCourierCharge(Double clCourierCharge)
	{
		this.clCourierCharge = clCourierCharge;
	}

	public void setClEduTelCharge(Double clEduTelCharge)
	{
		this.clEduTelCharge = clEduTelCharge;
	}

	public void setClFormCostOthers(Double clFormCostOthers)
	{
		this.clFormCostOthers = clFormCostOthers;
	}

	public void setClFormCostSCST(Double clFormCostSCST)
	{
		this.clFormCostSCST = clFormCostSCST;
	}

	public void setCollegeId(String collegeId)
	{
		this.collegeId = collegeId;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setCreatedUser(IUsers createdUser)
	{
		this.createdUser = createdUser;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public void setFormAutoId(int formAutoId)
	{
		this.formAutoId = formAutoId;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public void setPurchased(boolean purchased)
	{
		this.purchased = purchased;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}
}
