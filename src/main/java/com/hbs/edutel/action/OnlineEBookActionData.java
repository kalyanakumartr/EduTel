package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.model.OnlineEBook;

public abstract class OnlineEBookActionData extends OnlineEductionUtil
{
	private static final long	serialVersionUID		= 1L;
	protected String			loginUserType;
	protected OnlineEBook[]		onlineEBookArray;
	protected List<OnlineEBook>	onlineEBookList			= new ArrayList<OnlineEBook>(0);
	protected List<String>		uploadFileDisplayName	= new ArrayList<String>(0);
	protected String			usEmployeeId;

	public String getLoginUserType()
	{
		return loginUserType;
	}

	public OnlineEBook[] getOnlineEBookArray()
	{
		return onlineEBookArray;
	}

	public List<OnlineEBook> getOnlineEBookList()
	{
		return onlineEBookList;
	}

	public List<String> getUploadFileDisplayName()
	{
		return uploadFileDisplayName;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public void setLoginUserType(String loginUserType)
	{
		this.loginUserType = loginUserType;
	}

	public void setOnlineEBookArray(OnlineEBook[] onlineEBookArray)
	{
		this.onlineEBookArray = onlineEBookArray;
	}

	public void setOnlineEBookList(List<OnlineEBook> onlineEBookList)
	{
		this.onlineEBookList = onlineEBookList;
	}

	public void setUploadFileDisplayName(List<String> uploadFileDisplayName)
	{
		this.uploadFileDisplayName = uploadFileDisplayName;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

}