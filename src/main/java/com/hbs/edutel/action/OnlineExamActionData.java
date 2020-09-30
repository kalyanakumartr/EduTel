package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamAssigned;
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.util.common.factory.UsersFactory;

public class OnlineExamActionData extends OnlineEductionUtil implements IDashBoardAction
{
	private static final long			serialVersionUID		= 1L;
	protected String					oeExamId;
	protected OnlineExam				onlineExam				= new OnlineExam();
	protected OnlineExamAssigned		onlineExamAssigned		= new OnlineExamAssigned();
	protected boolean					onlineExamFlag			= false;
	protected List<OnlineExam>			onlineExamList			= new ArrayList<OnlineExam>(0);
	protected OnlinePractiseExam		onlinePractiseExam		= new OnlinePractiseExam();
	protected List<OnlinePractiseExam>	onlinePractiseExamList	= new ArrayList<OnlinePractiseExam>(0);
	protected IUsers					sessionUser				= UsersFactory.getInstance().getUsersInstance();
	protected String					usEmployeeId;
	protected String					usEmployeeIdStudent;
	protected IUsers					user					= UsersFactory.getInstance().getUsersInstance();
	protected List<IUsers>				usersList				= new ArrayList<IUsers>(0);

	public String getOeExamId()
	{
		return oeExamId;
	}

	public OnlineExam getOnlineExam()
	{
		return onlineExam;
	}

	public OnlineExamAssigned getOnlineExamAssigned()
	{
		return onlineExamAssigned;
	}

	public List<OnlineExam> getOnlineExamList()
	{
		return onlineExamList;
	}

	public OnlinePractiseExam getOnlinePractiseExam()
	{
		return onlinePractiseExam;
	}

	public List<OnlinePractiseExam> getOnlinePractiseExamList()
	{
		return onlinePractiseExamList;
	}

	public IUsers getSessionUser()
	{
		return sessionUser;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public String getUsEmployeeIdStudent()
	{
		return usEmployeeIdStudent;
	}

	public IUsers getUser()
	{
		return user;
	}

	public List<IUsers> getUsersList()
	{
		return usersList;
	}

	public boolean isOnlineExamFlag()
	{
		return onlineExamFlag;
	}

	public void setOeExamId(String oeExamId)
	{
		this.oeExamId = oeExamId;
	}

	public void setOnlineExam(OnlineExam onlineExam)
	{
		this.onlineExam = onlineExam;
	}

	public void setOnlineExamAssigned(OnlineExamAssigned onlineExamAssigned)
	{
		this.onlineExamAssigned = onlineExamAssigned;
	}

	public void setOnlineExamFlag(boolean onlineExamFlag)
	{
		this.onlineExamFlag = onlineExamFlag;

	}

	public void setOnlineExamList(List<OnlineExam> onlineExamList)
	{
		this.onlineExamList = onlineExamList;
	}

	public void setOnlinePractiseExam(OnlinePractiseExam onlinePractiseExam)
	{
		this.onlinePractiseExam = onlinePractiseExam;
	}

	public void setOnlinePractiseExamList(List<OnlinePractiseExam> onlinePractiseExamList)
	{
		this.onlinePractiseExamList = onlinePractiseExamList;
	}

	public void setSessionUser(IUsers sessionUser)
	{
		this.sessionUser = sessionUser;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUsEmployeeIdStudent(String usEmployeeIdStudent)
	{
		this.usEmployeeIdStudent = usEmployeeIdStudent;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUsersList(List<IUsers> usersList)
	{
		this.usersList = usersList;
	}

}
