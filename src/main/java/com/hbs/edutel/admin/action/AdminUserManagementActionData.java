package com.hbs.edutel.admin.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hbs.edutel.common.action.CommonActionBoData;
import com.hbs.edutel.common.action.login.ForgotPasswordAction;
import com.hbs.edutel.common.model.interfaces.IRoles;

public abstract class AdminUserManagementActionData extends CommonActionBoData
{
	private static final long		serialVersionUID	= -799096337485536576L;

	protected String				answer;
	protected String				cities;
	protected ForgotPasswordAction	forgotPasswordAction;
	protected boolean				gsdAdminRole		= false;
	protected boolean				gsiAdminRole		= false;
	protected boolean				hasApprovalAuthority;
	protected boolean				hasApprover;
	protected String				question;
	protected Map<String, String>	reminderQues		= new HashMap<String, String>();
	protected List<IRoles>			rolesList			= new ArrayList<IRoles>();
	protected boolean				showFilter			= false;
	protected Collection<?>			userList;
	protected String				userPassWord;

	public AdminUserManagementActionData()
	{
		super();
	}

	public String getAnswer()
	{
		return answer;
	}

	public String getCities()
	{
		return cities;
	}

	public ForgotPasswordAction getForgotPasswordAction()
	{
		return forgotPasswordAction;
	}

	public String getQuestion()
	{
		return question;
	}

	public Map<String, String> getReminderQues()
	{
		return reminderQues;
	}

	public List<IRoles> getRolesList()
	{
		return rolesList;
	}

	public Collection<?> getUserList()
	{
		return userList;
	}

	public String getUserPassWord()
	{
		return userPassWord;
	}

	public boolean isGsdAdminRole()
	{
		return gsdAdminRole;
	}

	public boolean isGsiAdminRole()
	{
		return gsiAdminRole;
	}

	public boolean isHasApprovalAuthority()
	{
		return hasApprovalAuthority;
	}

	public boolean isHasApprover()
	{
		return hasApprover;
	}

	public boolean isShowFilter()
	{
		return showFilter;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public void setCities(String cities)
	{
		this.cities = cities;
	}

	public void setForgotPasswordAction(ForgotPasswordAction forgotPasswordAction)
	{
		this.forgotPasswordAction = forgotPasswordAction;
	}

	public void setGsdAdminRole(boolean gsdAdminRole)
	{
		this.gsdAdminRole = gsdAdminRole;
	}

	public void setGsiAdminRole(boolean gsiAdminRole)
	{
		this.gsiAdminRole = gsiAdminRole;
	}

	public void setHasApprovalAuthority(boolean hasApprovalAuthority)
	{
		this.hasApprovalAuthority = hasApprovalAuthority;
	}

	public void setHasApprover(boolean hasApprover)
	{
		this.hasApprover = hasApprover;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public void setReminderQues(Map<String, String> reminderQues)
	{
		this.reminderQues = reminderQues;
	}

	public void setRolesList(List<IRoles> rolesList)
	{
		this.rolesList = rolesList;
	}

	public void setShowFilter(boolean showFilter)
	{
		this.showFilter = showFilter;
	}

	public void setUserList(Collection<?> userList)
	{
		this.userList = userList;
	}

	public void setUserPassWord(String userPassWord)
	{
		this.userPassWord = userPassWord;
	}

}