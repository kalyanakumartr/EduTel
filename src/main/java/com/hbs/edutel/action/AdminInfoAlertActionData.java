package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.model.InformationAlerts;

public abstract class AdminInfoAlertActionData extends EduTelCommonData
{
	/**
	 * 
	 */
	private static final long		serialVersionUID				= -529491128482725100L;

	protected String				infoAlertmsg;
	protected List<LabelValueBean>	infoMsgList						= new ArrayList<LabelValueBean>(0);
	protected String				usUsersType;
	protected String				informationTypes;
	protected List<LabelValueBean>	informationTypeLabelValueList	= new ArrayList<LabelValueBean>();
	protected InformationAlerts		informationAlert				= new InformationAlerts();

	public AdminInfoAlertActionData()
	{
		super();
	}

	public String getInfoAlertmsg()
	{
		return infoAlertmsg;
	}

	public List<LabelValueBean> getInfoMsgList()
	{
		return infoMsgList;
	}

	public String getUsUsersType()
	{
		return usUsersType;
	}

	public void setInfoAlertmsg(String infoAlertmsg)
	{
		this.infoAlertmsg = infoAlertmsg;
	}

	public void setInfoMsgList(List<LabelValueBean> infoMsgList)
	{
		this.infoMsgList = infoMsgList;
	}

	public void setUsUsersType(String usUsersType)
	{
		this.usUsersType = usUsersType;
	}

	public String getInformationTypes()
	{
		return informationTypes;
	}

	public void setInformationTypes(String informationTypes)
	{
		this.informationTypes = informationTypes;
	}

	public List<LabelValueBean> getInformationTypeLabelValueList()
	{
		return informationTypeLabelValueList;
	}

	public void setInformationTypeLabelValueList(List<LabelValueBean> informationTypeLabelValueList)
	{
		this.informationTypeLabelValueList = informationTypeLabelValueList;
	}

	public InformationAlerts getInformationAlert()
	{
		return informationAlert;
	}

	public void setInformationAlert(InformationAlerts informationAlert)
	{
		this.informationAlert = informationAlert;
	}

}