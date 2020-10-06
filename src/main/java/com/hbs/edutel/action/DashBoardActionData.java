package com.hbs.edutel.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.IAuditLogging;
import com.hbs.edutel.model.InformationAlerts;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.hbs.edutel.model.ToppersClub;
import com.hbs.edutel.util.common.factory.UsersFactory;

public abstract class DashBoardActionData extends EduTelCommonData implements IDashBoardAction
{
	private static final long									serialVersionUID								= 8775344068238496846L;
	protected String											chartDivData;
	protected InformationAlerts									informationAlerts								= new InformationAlerts();
	protected int[]												noOfUsersOnline;
	protected String											oeExamId;
	protected boolean											onlineExamFlag									= false;
	protected List<OnlineExam>									onlineExamList									= new ArrayList<OnlineExam>(0);
	protected List<OnlinePractiseExam>							onlinePractiseExamList							= new ArrayList<OnlinePractiseExam>(0);
	protected List<IAuditLogging>								onlinePractiseExamResultList					= new ArrayList<IAuditLogging>(0);
	protected IUsers											sessionUser										= UsersFactory.getInstance().getUsersInstance();
	List<ToppersClub>											toppersClubList									= new ArrayList<ToppersClub>();
	protected String											usEmployeeId;
	protected IUsers											user											= UsersFactory.getInstance().getUsersInstance();
	protected List<IAuditLogging>								userActivityList								= new ArrayList<IAuditLogging>(0);
	protected Timestamp											usLastLoginTime;
	protected String											usUserImageUrl;
	protected StudentsMarks										studentsMark									= new StudentsMarks();
	protected List<OnlineExamQuestion>							onlineExamQuestionsNotificationList				= new ArrayList<OnlineExamQuestion>(0);
	protected List<OnlineTestSeriesExamQuestionAnswerMapping>	oeTestSeriesValidatedAnswersNotificationList	= new ArrayList<OnlineTestSeriesExamQuestionAnswerMapping>(0);
	protected List<OnlineTestSeriesExamQuestionAnswerMapping>	oeTestSeriesUnvalidatedAnswersList				= new ArrayList<OnlineTestSeriesExamQuestionAnswerMapping>(0);
	protected String											marqueeMsg;
	protected String											accessToken;
	protected String											preSearchVideoURL;
	protected String											endUserVideoURL;
	List<InformationAlerts>										informationAlertList							= new ArrayList<InformationAlerts>(0);

	public DashBoardActionData()
	{
		super();
	}

	public String getChartDivData()
	{
		return chartDivData;
	}

	public InformationAlerts getInformationAlerts()
	{
		return informationAlerts;
	}

	public int[] getNoOfUsersOnline()
	{
		return noOfUsersOnline;
	}

	public String getOeExamId()
	{
		return oeExamId;
	}

	public List<OnlineExam> getOnlineExamList()
	{
		return onlineExamList;
	}

	public List<OnlinePractiseExam> getOnlinePractiseExamList()
	{
		return onlinePractiseExamList;
	}

	public List<IAuditLogging> getOnlinePractiseExamResultList()
	{
		return onlinePractiseExamResultList;
	}

	public IUsers getSessionUser()
	{
		return sessionUser;
	}

	public List<ToppersClub> getToppersClubList()
	{
		return toppersClubList;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public IUsers getUser()
	{
		return user;
	}

	public List<IAuditLogging> getUserActivityList()
	{
		return userActivityList;
	}

	public Timestamp getUsLastLoginTime()
	{
		return usLastLoginTime;
	}

	public String getUsUserImageUrl()
	{
		return usUserImageUrl;
	}

	public boolean isOnlineExamFlag()
	{
		return onlineExamFlag;
	}

	public void setChartDivData(String chartDivData)
	{
		this.chartDivData = chartDivData;
	}

	public void setInformationAlerts(InformationAlerts informationAlerts)
	{
		this.informationAlerts = informationAlerts;
	}

	public void setNoOfUsersOnline(int[] noOfUsersOnline)
	{
		this.noOfUsersOnline = noOfUsersOnline;
	}

	public void setOeExamId(String oeExamId)
	{
		this.oeExamId = oeExamId;
	}

	public void setOnlineExamFlag(boolean onlineExamFlag)
	{
		this.onlineExamFlag = onlineExamFlag;
	}

	public void setOnlineExamList(List<OnlineExam> onlineExamList)
	{
		this.onlineExamList = onlineExamList;
	}

	public void setOnlinePractiseExamList(List<OnlinePractiseExam> onlinePractiseExamList)
	{
		this.onlinePractiseExamList = onlinePractiseExamList;
	}

	public void setOnlinePractiseExamResultList(List<IAuditLogging> onlinePractiseExamResultList)
	{
		this.onlinePractiseExamResultList = onlinePractiseExamResultList;
	}

	public void setSessionUser(IUsers sessionUser)
	{
		this.sessionUser = sessionUser;
	}

	public void setToppersClubList(List<ToppersClub> toppersClubList)
	{
		this.toppersClubList = toppersClubList;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUserActivityList(List<IAuditLogging> userActivityList)
	{
		this.userActivityList = userActivityList;
	}

	public void setUsLastLoginTime(Timestamp usLastLoginTime)
	{
		this.usLastLoginTime = usLastLoginTime;
	}

	public void setUsUserImageUrl(String usUserImageUrl)
	{
		this.usUserImageUrl = usUserImageUrl;
	}

	public StudentsMarks getStudentsMark()
	{
		return studentsMark;
	}

	public void setStudentsMark(StudentsMarks studentsMark)
	{
		this.studentsMark = studentsMark;
	}

	public List<OnlineExamQuestion> getOnlineExamQuestionsNotificationList()
	{
		return onlineExamQuestionsNotificationList;
	}

	public void setOnlineExamQuestionsNotificationList(List<OnlineExamQuestion> onlineExamQuestionsNotificationList)
	{
		this.onlineExamQuestionsNotificationList = onlineExamQuestionsNotificationList;
	}

	public List<OnlineTestSeriesExamQuestionAnswerMapping> getOeTestSeriesValidatedAnswersNotificationList()
	{
		return oeTestSeriesValidatedAnswersNotificationList;
	}

	public void setOeTestSeriesValidatedAnswersNotificationList(List<OnlineTestSeriesExamQuestionAnswerMapping> oeTestSeriesValidatedAnswersNotificationList)
	{
		this.oeTestSeriesValidatedAnswersNotificationList = oeTestSeriesValidatedAnswersNotificationList;
	}

	public List<OnlineTestSeriesExamQuestionAnswerMapping> getOeTestSeriesUnvalidatedAnswersList()
	{
		return oeTestSeriesUnvalidatedAnswersList;
	}

	public void setOeTestSeriesUnvalidatedAnswersList(List<OnlineTestSeriesExamQuestionAnswerMapping> oeTestSeriesUnvalidatedAnswersList)
	{
		this.oeTestSeriesUnvalidatedAnswersList = oeTestSeriesUnvalidatedAnswersList;
	}

	public String getMarqueeMsg()
	{
		return marqueeMsg;
	}

	public void setMarqueeMsg(String marqueeMsg)
	{
		this.marqueeMsg = marqueeMsg;
	}

	public List<InformationAlerts> getInformationAlertList()
	{
		return informationAlertList;
	}

	public void setInformationAlertList(List<InformationAlerts> informationAlertList)
	{
		this.informationAlertList = informationAlertList;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	public String getEndUserVideoURL()
	{
		return endUserVideoURL;
	}

	public void setEndUserVideoURL(String endUserVideoURL)
	{
		this.endUserVideoURL = endUserVideoURL;
	}

	public String getPreSearchVideoURL()
	{
		return preSearchVideoURL;
	}

	public void setPreSearchVideoURL(String preSearchVideoURL)
	{
		this.preSearchVideoURL = preSearchVideoURL;
	}

 
}