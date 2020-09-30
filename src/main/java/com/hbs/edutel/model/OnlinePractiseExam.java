package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OnlinePractiseExam implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected String			oeChapter;
	protected boolean			oeIsOnlineExam		= false;
	protected Integer			oeMCQDurationPerExam;
	protected Integer			oeNoOfQuestions;
	protected String			oePractiseExamId;
	protected String			oePractiseExamName;
	protected String			oeSubject;
	protected String			onlineExamId;
	protected boolean			status				= true;

	public OnlinePractiseExam()
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

	public Integer getOeMCQDurationPerExam()
	{
		return oeMCQDurationPerExam;
	}

	public Integer getOeNoOfQuestions()
	{
		return oeNoOfQuestions;
	}

	public String getOePractiseExamId()
	{
		return oePractiseExamId;
	}

	public String getOePractiseExamName()
	{
		return oePractiseExamName;
	}

	public String getOeSubject()
	{
		return oeSubject;
	}

	public String getOnlineExamId()
	{
		return onlineExamId;
	}

	public boolean isOeIsOnlineExam()
	{
		return oeIsOnlineExam;
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

	public void setOeIsOnlineExam(boolean oeIsOnlineExam)
	{
		this.oeIsOnlineExam = oeIsOnlineExam;
	}

	public void setOeMCQDurationPerExam(Integer oeMCQDurationPerExam)
	{
		this.oeMCQDurationPerExam = oeMCQDurationPerExam;
	}

	public void setOeNoOfQuestions(Integer oeNoOfQuestions)
	{
		this.oeNoOfQuestions = oeNoOfQuestions;
	}

	public void setOePractiseExamId(String oePractiseExamId)
	{
		this.oePractiseExamId = oePractiseExamId;
	}

	public void setOePractiseExamName(String oePractiseExamName)
	{
		this.oePractiseExamName = oePractiseExamName;
	}

	public void setOeSubject(String oeSubject)
	{
		this.oeSubject = oeSubject;
	}

	public void setOnlineExamId(String onlineExamId)
	{
		this.onlineExamId = onlineExamId;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}
}
