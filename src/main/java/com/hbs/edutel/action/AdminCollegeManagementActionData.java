package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.model.College;
import com.hbs.edutel.model.CollegeEForm;

public abstract class AdminCollegeManagementActionData extends EduTelCommonData
{

	private static final long		serialVersionUID	= 1L;
	protected String				cities;
	protected List<LabelValueBean>	cityList			= new ArrayList<LabelValueBean>(0);
	protected College				college;
	protected CollegeEForm			collegeEForm		= new CollegeEForm();
	protected List<CollegeEForm>	collegeEFormList	= new ArrayList<CollegeEForm>(0);
	protected CollegeEForm			collegeForm;
	protected String				collegeId;
	protected List<College>			collegeList			= new ArrayList<College>(0);
	protected int					collegeListSize;

	protected Double				courierCost			= new Double(0);
	protected Double				eduTelServiceCost	= new Double(0);
	protected Double				formCost			= new Double(0);
	protected List<LabelValueBean>	stateList			= new ArrayList<LabelValueBean>(0);
	protected String				states;
	protected Double				total				= new Double(0);

	public AdminCollegeManagementActionData()
	{
		super();
	}

	public String getCities()
	{
		return cities;
	}

	public List<LabelValueBean> getCityList()
	{
		return cityList;
	}

	public College getCollege()
	{
		return college;
	}

	public CollegeEForm getCollegeEForm()
	{
		return collegeEForm;
	}

	public List<CollegeEForm> getCollegeEFormList()
	{
		return collegeEFormList;
	}

	public CollegeEForm getCollegeForm()
	{
		return collegeForm;
	}

	public String getCollegeId()
	{
		return collegeId;
	}

	public List<College> getCollegeList()
	{
		return collegeList;
	}

	public int getCollegeListSize()
	{
		return collegeListSize;
	}

	public Double getCourierCost()
	{
		return courierCost;
	}

	public Double getEduTelServiceCost()
	{
		return eduTelServiceCost;
	}

	public Double getFormCost()
	{
		return formCost;
	}

	public List<LabelValueBean> getStateList()
	{
		return stateList;
	}

	public String getStates()
	{
		return states;
	}

	public Double getTotal()
	{
		return total;
	}

	public void setCities(String cities)
	{
		this.cities = cities;
	}

	public void setCityList(List<LabelValueBean> cityList)
	{
		this.cityList = cityList;
	}

	public void setCollege(College college)
	{
		this.college = college;
	}

	public void setCollegeEForm(CollegeEForm collegeEForm)
	{
		this.collegeEForm = collegeEForm;
	}

	public void setCollegeEFormList(List<CollegeEForm> collegeEFormList)
	{
		this.collegeEFormList = collegeEFormList;
	}

	public void setCollegeForm(CollegeEForm collegeForm)
	{
		this.collegeForm = collegeForm;
	}

	public void setCollegeId(String collegeId)
	{
		this.collegeId = collegeId;
	}

	public void setCollegeList(List<College> collegeList)
	{
		this.collegeList = collegeList;
	}

	public void setCollegeListSize(int collegeListSize)
	{
		this.collegeListSize = collegeListSize;
	}

	public void setCourierCost(Double courierCost)
	{
		this.courierCost = courierCost;
	}

	public void setEduTelServiceCost(Double eduTelServiceCost)
	{
		this.eduTelServiceCost = eduTelServiceCost;
	}

	public void setFormCost(Double formCost)
	{
		this.formCost = formCost;
	}

	public void setStateList(List<LabelValueBean> stateList)
	{
		this.stateList = stateList;
	}

	public void setStates(String states)
	{
		this.states = states;
	}

	public void setTotal(Double total)
	{
		this.total = total;
	}

}