package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.common.ConstEnumUtil.EExamType;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class OnlineExamAssigned implements Serializable, ConstInterface
{
	private static final long	serialVersionUID	= 1L;
	protected String			createdBy;
	protected Timestamp			createdDate;
	protected String			modifiedBy;
	protected Timestamp			modifiedDate;
	protected String			oeAssignedExamDate;
	protected Integer			oeExamAutoId;
	protected OnlineExam		onlineExam;
	protected boolean			status				= true;
	protected IUsers			users;

	public OnlineExamAssigned()
	{
		super();
	}

	public OnlineExamAssigned(OnlineExam onlineExam, IUsers users)
	{
		super();
		this.onlineExam = onlineExam;
		this.users = users;
	}

	public void checkTimeReached()
	{
		Date examDate = CommonUtil.getDateInFormat(oeAssignedExamDate, DATE_FORMAT_MM_DD_YYYY_HHMM);
		long currentTime = CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST).getTime();
		onlineExam.timeReached = currentTime >= examDate.getTime();
		if (onlineExam.timeReached && onlineExam.onlineAssessment && CommonValidator.isEqual(onlineExam.oeExamType, EExamType.MCQ.name()))
		{
			long convertedTime = (long) (onlineExam.oeExamDuration * 60 * 60 * 1000);
			if (currentTime > (examDate.getTime() + convertedTime))
			{
				onlineExam.timeReached = false;
				onlineExam.timeElapsed = true;
			}
		}

		onlineExam.oeExamDate = CommonUtil.getDateStringInFormat(oeAssignedExamDate, DATE_FORMAT_MM_DD_YYYY_HHMM, "dd-MMM-yyyy HH-mm a");
		onlineExam.assignedExam = true;
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

	public String getOeAssignedExamDate()
	{
		return oeAssignedExamDate;
	}

	public Integer getOeExamAutoId()
	{
		return oeExamAutoId;
	}

	public OnlineExam getOnlineExam()
	{
		return onlineExam;
	}

	public IUsers getUsers()
	{
		return users;
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

	public void setOeAssignedExamDate(String oeAssignedExamDate)
	{
		this.oeAssignedExamDate = oeAssignedExamDate;
	}

	public void setOeExamAutoId(Integer oeExamAutoId)
	{
		this.oeExamAutoId = oeExamAutoId;
	}

	public void setOnlineExam(OnlineExam onlineExam)
	{
		this.onlineExam = onlineExam;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}
}
