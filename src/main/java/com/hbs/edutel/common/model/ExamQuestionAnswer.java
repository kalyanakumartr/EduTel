package com.hbs.edutel.common.model;

public class ExamQuestionAnswer implements java.io.Serializable
{
	private static final long	serialVersionUID	= -2065628036344367528L;
	public String				oeQuestionId;
	public String				oeSelectedAnswer;

	public ExamQuestionAnswer(String oeQuestionId)
	{
		this.oeQuestionId = oeQuestionId;
	}

	public ExamQuestionAnswer(String oeQuestionId, String isCorrectAnswer)
	{
		this.oeQuestionId = oeQuestionId;
		this.oeSelectedAnswer = isCorrectAnswer;
	}

}