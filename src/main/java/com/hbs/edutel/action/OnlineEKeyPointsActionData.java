package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.model.OnlineEKeyPoints;

public abstract class OnlineEKeyPointsActionData extends OnlineEductionUtil
{
	private static final long			serialVersionUID		= 1L;
	protected String					loginUserType;
	protected OnlineEKeyPoints[]		onlineEKeyPointsArray;
	protected List<OnlineEKeyPoints>	onlineEKeyPointsList	= new ArrayList<OnlineEKeyPoints>(0);
	protected List<String>				uploadFileActiveDate	= new ArrayList<String>(0);
	protected List<String>				uploadFileDisplayName	= new ArrayList<String>(0);
	protected String					usEmployeeId;

	public String getLoginUserType()
	{
		return loginUserType;
	}

	public OnlineEKeyPoints[] getOnlineEKeyPointsArray()
	{
		return onlineEKeyPointsArray;
	}

	public List<OnlineEKeyPoints> getOnlineEKeyPointsList()
	{
		return onlineEKeyPointsList;
	}

	public List<String> getUploadFileActiveDate()
	{
		return uploadFileActiveDate;
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

	public void setOnlineEKeyPointsArray(OnlineEKeyPoints[] onlineEKeyPointsArray)
	{
		this.onlineEKeyPointsArray = onlineEKeyPointsArray;
	}

	public void setOnlineEKeyPointsList(List<OnlineEKeyPoints> onlineEKeyPointsList)
	{
		this.onlineEKeyPointsList = onlineEKeyPointsList;
	}

	public void setUploadFileActiveDate(List<String> uploadFileActiveDate)
	{
		this.uploadFileActiveDate = uploadFileActiveDate;
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