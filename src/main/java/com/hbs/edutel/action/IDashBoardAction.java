package com.hbs.edutel.action;

import com.hbs.edutel.common.model.interfaces.IUsers;

public interface IDashBoardAction
{
	public String getOeExamId();

	public IUsers getSessionUser();

	public String getUsEmployeeId();

	public IUsers getUser();

	public boolean isOnlineExamFlag();

	public void setOnlineExamFlag(boolean b);

	public void setSessionUser(IUsers user);

}
