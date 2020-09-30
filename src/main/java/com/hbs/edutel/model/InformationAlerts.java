package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class InformationAlerts implements Serializable
{

	private static final long	serialVersionUID	= -2600051791399628263L;

	protected String			iaCreatedBy;
	protected Timestamp			iaCreatedDate;
	protected String			iaInformationMsg;
	protected Integer			iaInformationMsgId;
	protected String			iaModifiedBy;
	protected Timestamp			iaModifiedDate;
	protected boolean			iaStatus			= true;
	protected String			iaInformationType;

	public InformationAlerts()
	{
		super();
	}

	public String getIaCreatedBy()
	{
		return iaCreatedBy;
	}

	public Timestamp getIaCreatedDate()
	{
		return iaCreatedDate;
	}

	public String getIaInformationMsg()
	{
		return iaInformationMsg;
	}

	public Integer getIaInformationMsgId()
	{
		return iaInformationMsgId;
	}

	public String getIaModifiedBy()
	{
		return iaModifiedBy;
	}

	public Timestamp getIaModifiedDate()
	{
		return iaModifiedDate;
	}

	public boolean isIaStatus()
	{
		return iaStatus;
	}

	public void setIaCreatedBy(String iaCreatedBy)
	{
		this.iaCreatedBy = iaCreatedBy;
	}

	public void setIaCreatedDate(Timestamp iaCreatedDate)
	{
		this.iaCreatedDate = iaCreatedDate;
	}

	public void setIaInformationMsg(String iaInformationMsg)
	{
		this.iaInformationMsg = iaInformationMsg;
	}

	public void setIaInformationMsgId(Integer iaInformationMsgId)
	{
		this.iaInformationMsgId = iaInformationMsgId;
	}

	public void setIaModifiedBy(String iaModifiedBy)
	{
		this.iaModifiedBy = iaModifiedBy;
	}

	public void setIaModifiedDate(Timestamp iaModifiedDate)
	{
		this.iaModifiedDate = iaModifiedDate;
	}

	public void setIaStatus(boolean iaStatus)
	{
		this.iaStatus = iaStatus;
	}

	public String getIaInformationType()
	{
		return iaInformationType;
	}

	public void setIaInformationType(String iaInformationType)
	{
		this.iaInformationType = iaInformationType;
	}

}
