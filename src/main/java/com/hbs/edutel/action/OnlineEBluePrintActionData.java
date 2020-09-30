package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.model.OnlineEBluePrint;

public abstract class OnlineEBluePrintActionData extends OnlineEductionUtil
{
	private static final long			serialVersionUID		= 1L;
	protected String					loginUserType;
	protected OnlineEBluePrint[]		onlineEBluePrintArray;
	protected List<OnlineEBluePrint>	onlineEBluePrintList	= new ArrayList<OnlineEBluePrint>(0);
	protected List<String>				uploadFileDisplayName	= new ArrayList<String>(0);
	protected String					usEmployeeId;

	public String getLoginUserType()
	{
		return loginUserType;
	}

	public OnlineEBluePrint[] getOnlineEBluePrintArray()
	{
		return onlineEBluePrintArray;
	}

	public List<OnlineEBluePrint> getOnlineEBluePrintList()
	{
		return onlineEBluePrintList;
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

	public void setOnlineEBluePrintArray(OnlineEBluePrint[] onlineEBluePrintArray)
	{
		this.onlineEBluePrintArray = onlineEBluePrintArray;
	}

	public void setOnlineEBluePrintList(List<OnlineEBluePrint> onlineEBluePrintList)
	{
		this.onlineEBluePrintList = onlineEBluePrintList;
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