package com.hbs.edutel.common.model;

import java.sql.Timestamp;

import com.hbs.edutel.common.model.abstracts.AUserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUsers;

public class UserReminderQuery extends AUserReminderQuery
{
	private static final long	serialVersionUID	= -8329736080635110258L;
	private String				usCreatedBy;
	private Timestamp			usCreatedDate;
	private IUsers				users;
	private String				usModifiedBy;
	private Timestamp			usModifiedDate;
	private String				usReminderQueryAnswer;
	private String				usReminderQueryQuestion;

	public UserReminderQuery()
	{
	}

	
	public String getUsCreatedBy()
	{
		return usCreatedBy;
	}

	
	public Timestamp getUsCreatedDate()
	{
		return usCreatedDate;
	}

	
	public IUsers getUsers()
	{
		return users;
	}

	
	public String getUsModifiedBy()
	{
		return usModifiedBy;
	}

	
	public Timestamp getUsModifiedDate()
	{
		return usModifiedDate;
	}

	
	public String getUsReminderQueryAnswer()
	{
		return usReminderQueryAnswer;
	}

	
	public String getUsReminderQueryQuestion()
	{
		return usReminderQueryQuestion;
	}

	
	public void setUsCreatedBy(String usCreatedBy)
	{
		this.usCreatedBy = usCreatedBy;
	}

	
	public void setUsCreatedDate(Timestamp usCreatedDate)
	{
		this.usCreatedDate = usCreatedDate;
	}

	
	public void setUsers(IUsers users)
	{
		this.users = users;
	}

	
	public void setUsModifiedBy(String usModifiedBy)
	{
		this.usModifiedBy = usModifiedBy;
	}

	
	public void setUsModifiedDate(Timestamp usModifiedDate)
	{
		this.usModifiedDate = usModifiedDate;
	}

	
	public void setUsReminderQueryAnswer(String usReminderQueryAnswer)
	{
		this.usReminderQueryAnswer = usReminderQueryAnswer;
	}

	
	public void setUsReminderQueryQuestion(String usReminderQueryQuestion)
	{
		this.usReminderQueryQuestion = usReminderQueryQuestion;
	}

}
