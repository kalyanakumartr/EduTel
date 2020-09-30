package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.School;
import com.hbs.edutel.model.SerialKeyGenerate;
import com.hbs.edutel.util.common.factory.UsersFactory;

public class AdminSerialKeyGenerateActionData extends EduTelCommonData
{
	private static final long		serialVersionUID			= 1L;
	protected String				cities;
	protected List<LabelValueBean>	cityList					= new ArrayList<LabelValueBean>(0);
	protected String				loginOption;
	protected int					numberOfKeys;
	protected String				roleId;
	protected String				serialBatch;
	protected String				serialPromo;
	protected List<IRoles>			rolesList					= new ArrayList<IRoles>(0);
	protected List<School>			schoolList					= new ArrayList<School>(0);
	protected SerialKeyGenerate		serialKeyGenerate			= new SerialKeyGenerate();
	protected double				serialKeyPrice				= 0.0;
	protected double				serialKeySellingPrice		= 0.0;
	protected double				serialKeySellingPriceSearch	= 0.0;
	protected List<LabelValueBean>	stateList					= new ArrayList<LabelValueBean>(0);
	protected String				states;
	protected String				usEmployeeId;
	protected IUsers				user						= UsersFactory.getInstance().getUsersInstance();
	protected List<LabelValueBean>	usersList					= new ArrayList<LabelValueBean>(0);
	protected List<LabelValueBean>	serialBatchList				= new ArrayList<LabelValueBean>(0);
	protected List<LabelValueBean>	epromoList					= new ArrayList<LabelValueBean>(0);
	protected String				serialKeyAdvancedSearchColumns;
	protected List<LabelValueBean>	searchUsersList				= new ArrayList<LabelValueBean>(0);

	public AdminSerialKeyGenerateActionData()
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

	public String getLoginOption()
	{
		return loginOption;
	}

	public int getNumberOfKeys()
	{
		return numberOfKeys;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public List<IRoles> getRolesList()
	{
		return rolesList;
	}

	public List<School> getSchoolList()
	{
		return schoolList;
	}

	public SerialKeyGenerate getSerialKeyGenerate()
	{
		return serialKeyGenerate;
	}

	public double getSerialKeyPrice()
	{
		return serialKeyPrice;
	}

	public double getSerialKeySellingPrice()
	{
		return serialKeySellingPrice;
	}

	public double getSerialKeySellingPriceSearch()
	{
		return serialKeySellingPriceSearch;
	}

	public List<LabelValueBean> getStateList()
	{
		return stateList;
	}

	public String getStates()
	{
		return states;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public IUsers getUser()
	{
		return user;
	}

	public List<LabelValueBean> getUsersList()
	{
		return usersList;
	}

	public void setCities(String cities)
	{
		this.cities = cities;
	}

	public void setCityList(List<LabelValueBean> cityList)
	{
		this.cityList = cityList;
	}

	public void setLoginOption(String loginOption)
	{
		this.loginOption = loginOption;
	}

	public void setNumberOfKeys(int numberOfKeys)
	{
		this.numberOfKeys = numberOfKeys;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public void setRolesList(List<IRoles> rolesList)
	{
		this.rolesList = rolesList;
	}

	public void setSchoolList(List<School> schoolList)
	{
		this.schoolList = schoolList;
	}

	public void setSerialKeyGenerate(SerialKeyGenerate serialKeyGenerate)
	{
		this.serialKeyGenerate = serialKeyGenerate;
	}

	public void setSerialKeyPrice(double serialKeyPrice)
	{
		this.serialKeyPrice = serialKeyPrice;
	}

	public void setSerialKeySellingPrice(double serialKeySellingPrice)
	{
		this.serialKeySellingPrice = serialKeySellingPrice;
	}

	public void setSerialKeySellingPriceSearch(double serialKeySellingPriceSearch)
	{
		this.serialKeySellingPriceSearch = serialKeySellingPriceSearch;
	}

	public void setStateList(List<LabelValueBean> stateList)
	{
		this.stateList = stateList;
	}

	public void setStates(String states)
	{
		this.states = states;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUsersList(List<LabelValueBean> usersList)
	{
		this.usersList = usersList;
	}

	public String getSerialBatch()
	{
		return serialBatch;
	}

	public void setSerialBatch(String serialBatch)
	{
		this.serialBatch = serialBatch;
	}

	public String getSerialPromo()
	{
		return serialPromo;
	}

	public void setSerialPromo(String serialPromo)
	{
		this.serialPromo = serialPromo;
	}

	public List<LabelValueBean> getSerialBatchList()
	{
		return serialBatchList;
	}

	public void setSerialBatchList(List<LabelValueBean> serialBatchList)
	{
		this.serialBatchList = serialBatchList;
	}

	public List<LabelValueBean> getEpromoList()
	{
		return epromoList;
	}

	public void setEpromoList(List<LabelValueBean> epromoList)
	{
		this.epromoList = epromoList;
	}

	public String getSerialKeyAdvancedSearchColumns()
	{
		return serialKeyAdvancedSearchColumns;
	}

	public void setSerialKeyAdvancedSearchColumns(String serialKeyAdvancedSearchColumns)
	{
		this.serialKeyAdvancedSearchColumns = serialKeyAdvancedSearchColumns;
	}

	public List<LabelValueBean> getSearchUsersList()
	{
		return searchUsersList;
	}

	public void setSearchUsersList(List<LabelValueBean> searchUsersList)
	{
		this.searchUsersList = searchUsersList;
	}

}
