package com.hbs.edutel.model;

import java.io.Serializable;

public class OnlineQuestionDepth implements Serializable
{
	private static final long	serialVersionUID	= -149409855566268776L;
	protected Long				depthAutoId;
	protected String			oeChapter;
	protected Integer			oeNoOfQuestions		= 0;
	protected String			oeSchoolType;
	protected String			oeSubject;

	public OnlineQuestionDepth()
	{
		super();
	}

	public Long getDepthAutoId()
	{
		return depthAutoId;
	}

	public String getOeChapter()
	{
		return oeChapter;
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

	public void setDepthAutoId(Long depthAutoId)
	{
		this.depthAutoId = depthAutoId;
	}

	public void setOeChapter(String oeChapter)
	{
		this.oeChapter = oeChapter;
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

}
