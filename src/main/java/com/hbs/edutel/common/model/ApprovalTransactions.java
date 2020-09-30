package com.hbs.edutel.common.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class ApprovalTransactions implements Cloneable, Serializable
{
	private static final long	serialVersionUID	= 969569691691933732L;
	private short				atApprovalLevel;
	private String				atApproverSubGroup;
	private Integer				atInvoiceTransactionHistoryAutoId;
	private String				atProcessApprovalStatus;
	private String				atProcessedBy;
	private Timestamp			atProcessedDate;

	public ApprovalTransactions()
	{

	}

	public ApprovalTransactions(int atInvoiceTransactionHistoryAutoId, String atProcessedBy, Timestamp atProcessedDate, String atProcessApprovalStatus, short atApprovalLevel, String atApproverSubGroup)
	{

		this.atInvoiceTransactionHistoryAutoId = atInvoiceTransactionHistoryAutoId;
		this.atProcessedBy = atProcessedBy;
		this.atProcessedDate = atProcessedDate;
		this.atProcessApprovalStatus = atProcessApprovalStatus;
		this.atApprovalLevel = atApprovalLevel;
		this.atApproverSubGroup = atApproverSubGroup;

	}

	// clone the object
	
	public ApprovalTransactions clone() throws CloneNotSupportedException
	{
		ApprovalTransactions mem = (ApprovalTransactions) super.clone();
		return mem;
	}

	public ApprovalTransactions deepClone()
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (ApprovalTransactions) ois.readObject();
		}
		catch (IOException e)
		{
			return null;
		}
		catch (ClassNotFoundException e)
		{
			return null;
		}
	}

	public short getAtApprovalLevel()
	{
		return atApprovalLevel;
	}

	public String getAtApproverSubGroup()
	{
		return atApproverSubGroup;
	}

	public Integer getAtInvoiceTransactionHistoryAutoId()
	{
		return atInvoiceTransactionHistoryAutoId;
	}

	public String getAtProcessApprovalStatus()
	{
		return atProcessApprovalStatus;
	}

	public String getAtProcessedBy()
	{
		return atProcessedBy;
	}

	public Timestamp getAtProcessedDate()
	{
		return atProcessedDate;
	}

	/*
	 * If you don't think instances of this class will ever be inserted into a HashMap/HashTable, t
	 * he recommended hashCode implementation to use is:(non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	public int hashCode()
	{
		assert false : "hashCode not designed";
		return 42; // any arbitrary constant will do
	}

	public void setAtApprovalLevel(short atApprovalLevel)
	{
		this.atApprovalLevel = atApprovalLevel;
	}

	public void setAtApproverSubGroup(String atApproverSubGroup)
	{
		this.atApproverSubGroup = atApproverSubGroup;
	}

	public void setAtInvoiceTransactionHistoryAutoId(Integer atInvoiceTransactionHistoryAutoId)
	{
		this.atInvoiceTransactionHistoryAutoId = atInvoiceTransactionHistoryAutoId;
	}

	public void setAtProcessApprovalStatus(String atProcessApprovalStatus)
	{
		this.atProcessApprovalStatus = atProcessApprovalStatus;
	}

	public void setAtProcessedBy(String atProcessedBy)
	{
		this.atProcessedBy = atProcessedBy;
	}

	public void setAtProcessedDate(Timestamp atProcessedDate)
	{
		this.atProcessedDate = atProcessedDate;
	}

}
