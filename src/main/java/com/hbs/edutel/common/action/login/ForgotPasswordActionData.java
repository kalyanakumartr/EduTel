package com.hbs.edutel.common.action.login;

import com.hbs.edutel.common.action.CommonActionBoData;
import com.hbs.edutel.common.model.interfaces.IUsers;

public abstract class ForgotPasswordActionData extends CommonActionBoData
{
	private static final long	serialVersionUID	= -5192154702861133751L;
	protected String			answer;
	protected String			isQuesEmpty			= "one";
	protected String			quesAnswer;
	protected String			question;
	protected IUsers			user;
	protected String			userId;

	public String getAnswer()
	{
		return answer;
	}

	public String getIsQuesEmpty()
	{
		return isQuesEmpty;
	}

	public String getQuesAnswer()
	{
		return quesAnswer;
	}

	public String getQuestion()
	{
		return question;
	}

	public IUsers getUser()
	{
		return user;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public void setIsQuesEmpty(String isQuesEmpty)
	{
		this.isQuesEmpty = isQuesEmpty;
	}

	public void setQuesAnswer(String quesAnswer)
	{
		this.quesAnswer = quesAnswer;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}
}
