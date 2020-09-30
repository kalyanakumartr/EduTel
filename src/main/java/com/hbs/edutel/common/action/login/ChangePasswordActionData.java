package com.hbs.edutel.common.action.login;

import com.hbs.edutel.common.action.CommonActionBoData;
import com.hbs.edutel.common.model.interfaces.IUsers;

public abstract class ChangePasswordActionData extends CommonActionBoData
{
	private static final long	serialVersionUID	= 4528417910474239834L;
	protected String			actionFrm;
	protected String			answer;
	protected String			confirmPassword;
	protected String			newPassword;
	protected String			oldPassword;
	protected String			question;
	protected IUsers			user;

	public String getActionFrm()
	{
		return actionFrm;
	}

	public String getAnswer()
	{
		return answer;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public String getQuestion()
	{
		return question;
	}

	public IUsers getUser()
	{
		return user;
	}

	public void setActionFrm(String actionFrm)
	{
		this.actionFrm = actionFrm;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

}