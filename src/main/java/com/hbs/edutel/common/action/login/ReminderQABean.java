package com.hbs.edutel.common.action.login;

public class ReminderQABean
{

	private String	secAnswer;
	private String	secQuestion;

	public ReminderQABean()
	{

	}

	public ReminderQABean(String secQuestion, String secAnswer)
	{
		super();
		this.secQuestion = secQuestion;
		this.secAnswer = secAnswer;
	}

	public String getSecAnswer()
	{
		return secAnswer;
	}

	public String getSecQuestion()
	{
		return secQuestion;
	}

	public void setSecAnswer(String secAnswer)
	{
		this.secAnswer = secAnswer;
	}

	public void setSecQuestion(String secQuestion)
	{
		this.secQuestion = secQuestion;
	}
}
