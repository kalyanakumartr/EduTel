package com.hbs.edutel.action;

import com.hbs.edutel.model.OnlinePractiseExam;

public interface IOnlineExamAction
{
	public OnlinePractiseExam getOnlinePractiseExam();

	public String getUsEmployeeId();

	public void setOnlinePractiseExam(OnlinePractiseExam onlinePractiseExam);

	public void setUsEmployeeId(String usEmployeeId);
}
