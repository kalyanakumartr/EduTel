package com.hbs.edutel.common.model;

import java.sql.Timestamp;

public class AdminTransactions implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7462947112593832885L;
	private Integer				atAutoId;
	private String				atInvoiceUrns;
	private String				atAllotedApprover;
	private String				atMovedTo;
	private String				atAction;
	private String				atRemarks;
	private String				atCreatedBy;
	private Timestamp			atCreatedDate;

	// Constructors

	/** default constructor */
	public AdminTransactions()
	{
	}

	/** full constructor */
	public AdminTransactions(String atInvoiceUrns, String atAllotedApprover, String atMovedTo, String atCreatedBy, Timestamp atCreatedDate)
	{
		this.atInvoiceUrns = atInvoiceUrns;
		this.atAllotedApprover = atAllotedApprover;
		this.atMovedTo = atMovedTo;
		this.atCreatedBy = atCreatedBy;
		this.atCreatedDate = atCreatedDate;
	}

	// Property Accessors
	public Integer getAtAutoId()
	{
		return atAutoId;
	}

	public void setAtAutoId(Integer atAutoId)
	{
		this.atAutoId = atAutoId;
	}

	public String getAtInvoiceUrns()
	{
		return atInvoiceUrns;
	}

	public void setAtInvoiceUrns(String atInvoiceUrns)
	{
		this.atInvoiceUrns = atInvoiceUrns;
	}

	public String getAtAllotedApprover()
	{
		return atAllotedApprover;
	}

	public void setAtAllotedApprover(String atAllotedApprover)
	{
		this.atAllotedApprover = atAllotedApprover;
	}

	public String getAtMovedTo()
	{
		return atMovedTo;
	}

	public void setAtMovedTo(String atMovedTo)
	{
		this.atMovedTo = atMovedTo;
	}

	public String getAtAction()
	{
		return atAction;
	}

	public void setAtAction(String atAction)
	{
		this.atAction = atAction;
	}

	public String getAtRemarks()
	{
		return atRemarks;
	}

	public void setAtRemarks(String atRemarks)
	{
		this.atRemarks = atRemarks;
	}

	public String getAtCreatedBy()
	{
		return atCreatedBy;
	}

	public void setAtCreatedBy(String atCreatedBy)
	{
		this.atCreatedBy = atCreatedBy;
	}

	public Timestamp getAtCreatedDate()
	{
		return atCreatedDate;
	}

	public void setAtCreatedDate(Timestamp atCreatedDate)
	{
		this.atCreatedDate = atCreatedDate;
	}

}
