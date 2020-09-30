package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.common.ConstEnumUtil.EExamType;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class OnlineExam implements Serializable, ConstInterface
{
	private static final long	serialVersionUID	= -4828740717588887709L;

	protected boolean			assignedExam		= false;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected boolean			displayPublic		= false;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected String			oeChapter;
	protected String			oeExamDate;
	protected double			oeExamDuration;
	protected String			oeExamId;
	protected String			oeExamName;
	protected String			oeExamType			= EExamType.MCQ.name();
	protected Integer			oeNoOfQuestions		= 0;
	protected String			oeSchoolType;
	protected String			oeSubject;
	protected boolean			onlineAssessment	= false;
	protected boolean			status				= true;
	protected boolean			takenAlready		= false;
	protected boolean			timeElapsed			= false;
	protected boolean			timeReached			= false;

	public OnlineExam()
	{
		super();
	}

	public void checkTimeReached()
	{
		Date examDate = CommonUtil.getDateInFormat(oeExamDate, DATE_FORMAT_MM_DD_YYYY_HHMM);
		long currentTime = CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST).getTime();
		timeReached = currentTime >= examDate.getTime();
		if (timeReached && onlineAssessment && CommonValidator.isEqual(oeExamType, EExamType.MCQ.name()))
		{
			long convertedTime = (long) (oeExamDuration * 60 * 60 * 1000);
			if (currentTime > (examDate.getTime() + convertedTime))
			{
				timeReached = false;
				timeElapsed = true;
			}
		}
		oeExamDate = CommonUtil.getDateStringInFormat(oeExamDate, DATE_FORMAT_MM_DD_YYYY_HHMM, "dd-MMM-yyyy HH-mm a");
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

	public String getOeExamDate()
	{
		return oeExamDate;
	}

	public double getOeExamDuration()
	{
		return oeExamDuration;
	}

	public String getOeExamId()
	{
		return oeExamId;
	}

	public String getOeExamName()
	{
		return oeExamName;
	}

	public String getOeExamType()
	{
		return oeExamType;
	}

	public Integer getOeNoOfQuestions()
	{
		return oeNoOfQuestions;
	}

	public String getOeSchoolType()
	{
		return oeSchoolType;
	}

	public String getOeSubject()
	{
		return oeSubject;
	}

	public boolean isAssignedExam()
	{
		return assignedExam;
	}

	public boolean isDisplayPublic()
	{
		return displayPublic;
	}

	public boolean isOnlineAssessment()
	{
		return onlineAssessment;
	}

	public boolean isStatus()
	{
		return status;
	}

	public boolean isTakenAlready()
	{
		return takenAlready;
	}

	public boolean isTimeElapsed()
	{
		return timeElapsed;
	}

	public boolean isTimeReached()
	{
		return timeReached;
	}

	public void setAssignedExam(boolean assignedExam)
	{
		this.assignedExam = assignedExam;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setDisplayPublic(boolean displayPublic)
	{
		this.displayPublic = displayPublic;
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

	public void setOeExamDate(String oeExamDate)
	{
		this.oeExamDate = oeExamDate;
	}

	public void setOeExamDuration(double oeExamDuration)
	{
		this.oeExamDuration = oeExamDuration;
	}

	public void setOeExamId(String oeExamId)
	{
		this.oeExamId = oeExamId;
	}

	public void setOeExamName(String oeExamName)
	{
		this.oeExamName = oeExamName;
	}

	public void setOeExamType(String oeExamType)
	{
		this.oeExamType = oeExamType;
	}

	public void setOeNoOfQuestions(Integer oeNoOfQuestions)
	{
		this.oeNoOfQuestions = oeNoOfQuestions;
	}

	public void setOeSchoolType(String oeSchoolType)
	{
		this.oeSchoolType = oeSchoolType;
	}

	public void setOeSubject(String oeSubject)
	{
		this.oeSubject = oeSubject;
	}

	public void setOnlineAssessment(boolean onlineAssessment)
	{
		this.onlineAssessment = onlineAssessment;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public void setTakenAlready(boolean takenAlready)
	{
		this.takenAlready = takenAlready;
	}

	public void setTimeElapsed(boolean timeElapsed)
	{
		this.timeElapsed = timeElapsed;
	}

	public void setTimeReached(boolean timeReached)
	{
		this.timeReached = timeReached;
	}

}
