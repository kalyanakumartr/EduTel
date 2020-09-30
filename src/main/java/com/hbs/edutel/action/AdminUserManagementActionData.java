package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.School;
import com.hbs.edutel.model.SerialKeyGenerate;
import com.hbs.edutel.util.common.factory.UsersFactory;
import com.hbs.messagesalert.action.MessagesParam;

public abstract class AdminUserManagementActionData extends EduTelCommonData
{
	private static final long			serialVersionUID	= 1L;
	protected List<IUsers>				attachmentList		= new ArrayList<IUsers>(0);
	protected String					cities;
	protected List<LabelValueBean>		cityList			= new ArrayList<LabelValueBean>(0);
	protected String					employeeId;
	protected String					passwordUpdated		= "false";
	protected List<School>				schoolList			= new ArrayList<School>(0);
	protected List<SerialKeyGenerate>	serialList			= new ArrayList<SerialKeyGenerate>(0);
	protected List<LabelValueBean>		stateList			= new ArrayList<LabelValueBean>(0);
	protected String					states;
	protected IUsers					user				= UsersFactory.getInstance().getUsersInstance();
	protected String					userColumnNames;
	protected List<LabelValueBean>		usersList			= new ArrayList<LabelValueBean>(0);
	protected String					usUserImageUrl;
	protected MessagesParam				msgParam			= new MessagesParam();
	protected String					studentUserAdvancedSearchColumns;
	protected String					employeeUserAdvancedSearchColumns;
	protected String					franchiseeUserAdvancedSearchColumns;
	protected List<StudentsMarks>		studentMarksList	= new ArrayList<StudentsMarks>();
	protected StudentsMarks				studentMark			= new StudentsMarks();
	protected StringBuffer				employeeIdBuffer	= new StringBuffer();
	protected List<LabelValueBean>		serialBatchList		= new ArrayList<LabelValueBean>(0);

	public AdminUserManagementActionData()
	{
		super();
	}

	public List<IUsers> getAttachmentList()
	{
		return attachmentList;
	}

	public String getCities()
	{
		return cities;
	}

	public List<LabelValueBean> getCityList()
	{
		return cityList;
	}

	public String getEmployeeId()
	{
		return employeeId;
	}

	public String getPasswordUpdated()
	{
		return passwordUpdated;
	}

	public List<School> getSchoolList()
	{
		return schoolList;
	}

	public List<SerialKeyGenerate> getSerialList()
	{
		return serialList;
	}

	public List<LabelValueBean> getStateList()
	{
		return stateList;
	}

	public String getStates()
	{
		return states;
	}

	public IUsers getUser()
	{
		return user;
	}

	public String getUserColumnNames()
	{
		return userColumnNames;
	}

	public List<LabelValueBean> getUsersList()
	{
		return usersList;
	}

	public String getUsUserImageUrl()
	{
		return usUserImageUrl;
	}

	public void setAttachmentList(List<IUsers> attachmentList)
	{
		this.attachmentList = attachmentList;
	}

	public void setCities(String cities)
	{
		this.cities = cities;
	}

	public void setCityList(List<LabelValueBean> cityList)
	{
		this.cityList = cityList;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public void setPasswordUpdated(String passwordUpdated)
	{
		this.passwordUpdated = passwordUpdated;
	}

	public void setSchoolList(List<School> schoolList)
	{
		this.schoolList = schoolList;
	}

	public void setSerialList(List<SerialKeyGenerate> serialList)
	{
		this.serialList = serialList;
	}

	public void setStateList(List<LabelValueBean> stateList)
	{
		this.stateList = stateList;
	}

	public void setStates(String states)
	{
		this.states = states;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUserColumnNames(String userColumnNames)
	{
		this.userColumnNames = userColumnNames;
	}

	public void setUsersList(List<LabelValueBean> usersList)
	{
		this.usersList = usersList;
	}

	public void setUsUserImageUrl(String usUserImageUrl)
	{
		this.usUserImageUrl = usUserImageUrl;
	}

	public MessagesParam getMsgParam()
	{
		return msgParam;
	}

	public void setMsgParam(MessagesParam msgParam)
	{
		this.msgParam = msgParam;
	}

	public String getStudentUserAdvancedSearchColumns()
	{
		return studentUserAdvancedSearchColumns;
	}

	public void setStudentUserAdvancedSearchColumns(String studentUserAdvancedSearchColumns)
	{
		this.studentUserAdvancedSearchColumns = studentUserAdvancedSearchColumns;
	}

	public String getEmployeeUserAdvancedSearchColumns()
	{
		return employeeUserAdvancedSearchColumns;
	}

	public void setEmployeeUserAdvancedSearchColumns(String employeeUserAdvancedSearchColumns)
	{
		this.employeeUserAdvancedSearchColumns = employeeUserAdvancedSearchColumns;
	}

	public String getFranchiseeUserAdvancedSearchColumns()
	{
		return franchiseeUserAdvancedSearchColumns;
	}

	public void setFranchiseeUserAdvancedSearchColumns(String franchiseeUserAdvancedSearchColumns)
	{
		this.franchiseeUserAdvancedSearchColumns = franchiseeUserAdvancedSearchColumns;
	}

	public List<StudentsMarks> getStudentMarksList()
	{
		return studentMarksList;
	}

	public void setStudentMarksList(List<StudentsMarks> studentMarksList)
	{
		this.studentMarksList = studentMarksList;
	}

	public StudentsMarks getStudentMark()
	{
		return studentMark;
	}

	public void setStudentMark(StudentsMarks studentMark)
	{
		this.studentMark = studentMark;
	}

	public StringBuffer getEmployeeIdBuffer()
	{
		return employeeIdBuffer;
	}

	public void setEmployeeIdBuffer(StringBuffer employeeIdBuffer)
	{
		this.employeeIdBuffer = employeeIdBuffer;
	}

	public List<LabelValueBean> getSerialBatchList()
	{
		return serialBatchList;
	}

	public void setSerialBatchList(List<LabelValueBean> serialBatchList)
	{
		this.serialBatchList = serialBatchList;
	}
}