package com.hbs.edutel.common.model.abstracts;

import java.sql.Timestamp;

import com.hbs.edutel.common.model.interfaces.IUserReminderQuery;
import com.hbs.edutel.common.model.interfaces.IUsers;

public abstract class AUserReminderQuery implements IUserReminderQuery
{
	private static final long	serialVersionUID	= 2795352396099571077L;

	
	public String getUsCreatedBy()
	{

		return null;
	}

	
	public Timestamp getUsCreatedDate()
	{

		return null;
	}

	
	public IUsers getUsers()
	{

		return null;
	}

	
	public String getUsModifiedBy()
	{

		return null;
	}

	
	public Timestamp getUsModifiedDate()
	{

		return null;
	}

	
	public String getUsReminderQueryAnswer()
	{

		return null;
	}

	
	public String getUsReminderQueryQuestion()
	{

		return null;
	}

	
	public void setUsCreatedBy(String usCreatedBy)
	{

	}

	
	public void setUsCreatedDate(Timestamp usCreatedDate)
	{

	}

	
	public void setUsers(IUsers users)
	{

	}

	
	public void setUsModifiedBy(String usModifiedBy)
	{

	}

	
	public void setUsModifiedDate(Timestamp usModifiedDate)
	{

	}

	
	public void setUsReminderQueryAnswer(String usReminderQueryAnswer)
	{

	}

	
	public void setUsReminderQueryQuestion(String usReminderQueryQuestion)
	{

	}

}
