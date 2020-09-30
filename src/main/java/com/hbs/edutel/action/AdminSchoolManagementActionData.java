package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.model.School;

public abstract class AdminSchoolManagementActionData extends EduTelCommonData
{

	private static final long		serialVersionUID	= 1L;
	protected String				cities;
	protected List<LabelValueBean>	cityList			= new ArrayList<LabelValueBean>(0);
	protected School				school;
	protected List<LabelValueBean>	stateList			= new ArrayList<LabelValueBean>(0);
	protected String				states;

	public String getCities()
	{
		return cities;
	}

	public List<LabelValueBean> getCityList()
	{
		return cityList;
	}

	public School getSchool()
	{
		return school;
	}

	public List<LabelValueBean> getStateList()
	{
		return stateList;
	}

	public String getStates()
	{
		return states;
	}

	public void setCities(String cities)
	{
		this.cities = cities;
	}

	public void setCityList(List<LabelValueBean> cityList)
	{
		this.cityList = cityList;
	}

	public void setSchool(School school)
	{
		this.school = school;
	}

	public void setStateList(List<LabelValueBean> stateList)
	{
		this.stateList = stateList;
	}

	public void setStates(String states)
	{
		this.states = states;
	}

}