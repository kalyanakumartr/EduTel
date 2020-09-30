package com.hbs.edutel.common.model;

import java.sql.Timestamp;

public class AdminUserActivity
{

	private Integer		auaAutoId;
	private String		auaAction;
	private String		auaUserId;
	private String		auaActionDetails;
	private String		auaActionBy;
	private Timestamp	auaActionDate;

	// Constructors

	/** default constructor */
	public AdminUserActivity()
	{
	}

	/** full constructor */

	public AdminUserActivity(String auaAction, String auaActionDetails, String auaActionBy, Timestamp auaActionDate)
	{

		this.auaAction = auaAction;
		this.auaActionDetails = auaActionDetails;
		this.auaActionBy = auaActionBy;
		this.auaActionDate = auaActionDate;

	}

	// Property Accessors
	public Integer getAuaAutoId()
	{
		return auaAutoId;
	}

	public void setAuaAutoId(Integer auaAutoId)
	{
		this.auaAutoId = auaAutoId;
	}

	public String getAuaAction()
	{
		return auaAction;
	}

	public void setAuaAction(String auaAction)
	{
		this.auaAction = auaAction;
	}

	public String getAuaActionDetails()
	{
		return auaActionDetails;
	}

	public void setAuaActionDetails(String auaActionDetails)
	{
		this.auaActionDetails = auaActionDetails;
	}

	public String getAuaActionBy()
	{
		return auaActionBy;
	}

	public void setAuaActionBy(String auaActionBy)
	{
		this.auaActionBy = auaActionBy;
	}

	public Timestamp getAuaActionDate()
	{
		return auaActionDate;
	}

	public void setAuaActionDate(Timestamp auaActionDate)
	{
		this.auaActionDate = auaActionDate;
	}

	public void setAuaUserId(String auaUserId)
	{
		this.auaUserId = auaUserId;
	}

	public String getAuaUserId()
	{
		return auaUserId;
	}

}
