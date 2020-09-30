package com.hbs.edutel.common.model.interfaces;

import java.sql.Timestamp;

/**
 * UserReminderQuery entity. @author MyEclipse Persistence Tools
 */

public interface IUserReminderQuery extends java.io.Serializable
{
	public String getUsCreatedBy();

	public Timestamp getUsCreatedDate();

	public IUsers getUsers();

	public String getUsModifiedBy();

	public Timestamp getUsModifiedDate();

	public String getUsReminderQueryAnswer();

	public String getUsReminderQueryQuestion();

	public void setUsCreatedBy(String usCreatedBy);

	public void setUsCreatedDate(Timestamp usCreatedDate);

	public void setUsers(IUsers users);

	public void setUsModifiedBy(String usModifiedBy);

	public void setUsModifiedDate(Timestamp usModifiedDate);

	public void setUsReminderQueryAnswer(String usReminderQueryAnswer);

	public void setUsReminderQueryQuestion(String usReminderQueryQuestion);

}