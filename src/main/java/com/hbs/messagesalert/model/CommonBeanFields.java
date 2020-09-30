package com.hbs.messagesalert.model;

import java.sql.Timestamp;

import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.common.consts.ConstInterface;

public abstract class CommonBeanFields implements java.io.Serializable, ConstInterface
{

	private static final long	serialVersionUID	= 1L;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected IUsers			createdUser;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected IUsers			modifiedUser;
	protected boolean			status				= true;

	public CommonBeanFields()
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

	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	public IUsers getModifiedUser()
	{
		return modifiedUser;
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

	public void setCreatedUser(IUsers createdUser)
	{
		this.createdUser = createdUser;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public void setModifiedUser(IUsers modifiedUser)
	{
		this.modifiedUser = modifiedUser;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

}