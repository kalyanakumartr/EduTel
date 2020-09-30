package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class QueryDiscussion implements Serializable
{
	private static final long	serialVersionUID	= 3697657650908101796L;
	private String				qtAnswer;
	private long				qtAutoId;
	private String				qtQuery;
	private String				qtRaisedBy;
	private Timestamp			qtRaisedDate;
	private String				qtRaisedName;
	private Integer				qtRated				= 0;
	private String				qtReplyBy;
	private Timestamp			qtReplyDate;
	private String				qtReplyName;
	private String				qtSubject;

	public String getQtAnswer()
	{
		return qtAnswer;
	}

	public long getQtAutoId()
	{
		return qtAutoId;
	}

	public String getQtQuery()
	{
		return qtQuery;
	}

	public String getQtRaisedBy()
	{
		return qtRaisedBy;
	}

	public Timestamp getQtRaisedDate()
	{
		return qtRaisedDate;
	}

	public String getQtRaisedName()
	{
		return qtRaisedName;
	}

	public Integer getQtRated()
	{
		return qtRated;
	}

	public String getQtReplyBy()
	{
		return qtReplyBy;
	}

	public Timestamp getQtReplyDate()
	{
		return qtReplyDate;
	}

	public String getQtReplyName()
	{
		return qtReplyName;
	}

	public void setQtAnswer(String qtAnswer)
	{
		this.qtAnswer = qtAnswer;
	}

	public void setQtAutoId(long qtAutoId)
	{
		this.qtAutoId = qtAutoId;
	}

	public void setQtQuery(String qtQuery)
	{
		this.qtQuery = qtQuery;
	}

	public void setQtRaisedBy(String qtRaisedBy)
	{
		this.qtRaisedBy = qtRaisedBy;
	}

	public void setQtRaisedDate(Timestamp qtRaisedDate)
	{
		this.qtRaisedDate = qtRaisedDate;
	}

	public void setQtRaisedName(String qtRaisedName)
	{
		this.qtRaisedName = qtRaisedName;
	}

	public void setQtRated(Integer qtRated)
	{
		this.qtRated = qtRated;
	}

	public void setQtReplyBy(String qtReplyBy)
	{
		this.qtReplyBy = qtReplyBy;
	}

	public void setQtReplyDate(Timestamp qtReplyDate)
	{
		this.qtReplyDate = qtReplyDate;
	}

	public void setQtReplyName(String qtReplyName)
	{
		this.qtReplyName = qtReplyName;
	}

	public String getQtSubject() {
		return qtSubject;
	}

	public void setQtSubject(String qtSubject) {
		this.qtSubject = qtSubject;
	}

}
