package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ToppersClub implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected Boolean			status;
	protected String			tpChapter;
	protected Timestamp			tpEndTime;
	protected Timestamp			tpExamDate;
	protected long				tpExamDuration;
	protected String			tpExamName;
	protected String			tpExamType;
	protected Integer			tpId;
	protected Integer			tpMarks;
	protected Integer			tpNoOfQuestions;
	protected Integer			tpRatings;
	protected String			tpSchoolName;
	protected Timestamp			tpStartTime;
	protected String			tpStudentName;
	protected String			tpSubject;
	protected String			usUserImageUrl;

	public ToppersClub()
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

	public Boolean getStatus()
	{
		return status;
	}

	public String getTpChapter()
	{
		return tpChapter;
	}

	public Timestamp getTpEndTime()
	{
		return tpEndTime;
	}

	public Timestamp getTpExamDate()
	{
		return tpExamDate;
	}

	public long getTpExamDuration()
	{
		return tpExamDuration;
	}

	public String getTpExamName()
	{
		return tpExamName;
	}

	public String getTpExamType()
	{
		return tpExamType;
	}

	public Integer getTpId()
	{
		return tpId;
	}

	public Integer getTpMarks()
	{
		return tpMarks;
	}

	public Integer getTpNoOfQuestions()
	{
		return tpNoOfQuestions;
	}

	public Integer getTpRatings()
	{
		return tpRatings;
	}

	public String getTpSchoolName()
	{
		return tpSchoolName;
	}

	public Timestamp getTpStartTime()
	{
		return tpStartTime;
	}

	public String getTpStudentName()
	{
		return tpStudentName;
	}

	public String getTpSubject()
	{
		return tpSubject;
	}

	public String getUsUserImageUrl()
	{
		return usUserImageUrl;
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

	public void setStatus(Boolean status)
	{
		this.status = status;
	}

	public void setTpChapter(String tpChapter)
	{
		this.tpChapter = tpChapter;
	}

	public void setTpEndTime(Timestamp tpEndTime)
	{
		this.tpEndTime = tpEndTime;
	}

	public void setTpExamDate(Timestamp tpExamDate)
	{
		this.tpExamDate = tpExamDate;
	}

	public void setTpExamDuration(long tpExamDuration)
	{
		this.tpExamDuration = tpExamDuration;
	}

	public void setTpExamName(String tpExamName)
	{
		this.tpExamName = tpExamName;
	}

	public void setTpExamType(String tpExamType)
	{
		this.tpExamType = tpExamType;
	}

	public void setTpId(Integer tpId)
	{
		this.tpId = tpId;
	}

	public void setTpMarks(Integer tpMarks)
	{
		this.tpMarks = tpMarks;
	}

	public void setTpNoOfQuestions(Integer tpNoOfQuestions)
	{
		this.tpNoOfQuestions = tpNoOfQuestions;
	}

	public void setTpRatings(Integer tpRatings)
	{
		this.tpRatings = tpRatings;
	}

	public void setTpSchoolName(String tpSchoolName)
	{
		this.tpSchoolName = tpSchoolName;
	}

	public void setTpStartTime(Timestamp tpStartTime)
	{
		this.tpStartTime = tpStartTime;
	}

	public void setTpStudentName(String tpStudentName)
	{
		this.tpStudentName = tpStudentName;
	}

	public void setTpSubject(String tpSubject)
	{
		this.tpSubject = tpSubject;
	}

	public void setUsUserImageUrl(String usUserImageUrl)
	{
		this.usUserImageUrl = usUserImageUrl;
	}

}
