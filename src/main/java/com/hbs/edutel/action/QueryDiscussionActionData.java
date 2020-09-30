package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.bo.QueryDiscussionDisplayParam;
import com.hbs.edutel.bo.QueryDiscussionModel;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.QueryDiscussion;
import com.hbs.edutel.util.common.factory.UsersFactory;

public class QueryDiscussionActionData extends EduTelCommonData
{

	private static final long				serialVersionUID	= 2382039957294476118L;
	protected QueryDiscussion				qryDisc				= new QueryDiscussion();
	protected QueryDiscussionDisplayParam	qryDiscDispParam	= new QueryDiscussionDisplayParam();
	protected QueryDiscussionModel			qryDiscModel		= new QueryDiscussionModel();
	protected String						qtAnswer;
	protected String						qtAutoId;

	protected String						qtRated;
	protected List<QueryDiscussion>			queryAnswerList		= new ArrayList<QueryDiscussion>();
	protected List<QueryDiscussion>			queryList			= new ArrayList<QueryDiscussion>();

	protected String						usEmployeeId;
	protected IUsers						user				= UsersFactory.getInstance().getUsersInstance();
	protected String						usUserName;

	public QueryDiscussion getQryDisc()
	{
		return qryDisc;
	}

	public QueryDiscussionDisplayParam getQryDiscDispParam()
	{
		return qryDiscDispParam;
	}

	public QueryDiscussionModel getQryDiscModel()
	{
		return qryDiscModel;
	}

	public String getQtAnswer()
	{
		return qtAnswer;
	}

	public String getQtAutoId()
	{
		return qtAutoId;
	}

	public String getQtRated()
	{
		return qtRated;
	}

	public List<QueryDiscussion> getQueryAnswerList()
	{
		return queryAnswerList;
	}

	public List<QueryDiscussion> getQueryList()
	{
		return queryList;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public IUsers getUser()
	{
		return user;
	}

	public String getUsUserName()
	{
		return usUserName;
	}

	public void setQryDicDispParam(QueryDiscussionDisplayParam qryDiscDispParam)
	{
		this.qryDiscDispParam = qryDiscDispParam;
	}

	public void setQryDisc(QueryDiscussion qryDisc)
	{
		this.qryDisc = qryDisc;
	}

	public void setQryDiscDispParam(QueryDiscussionDisplayParam qryDiscDispParam)
	{
		this.qryDiscDispParam = qryDiscDispParam;
	}

	public void setQryDiscModel(QueryDiscussionModel qryDiscModel)
	{
		this.qryDiscModel = qryDiscModel;
	}

	public void setQtAnswer(String qtAnswer)
	{
		this.qtAnswer = qtAnswer;
	}

	public void setQtAutoId(String qtAutoId)
	{
		this.qtAutoId = qtAutoId;
	}

	public void setQtRated(String qtRated)
	{
		this.qtRated = qtRated;
	}

	public void setQueryAnswerList(List<QueryDiscussion> queryAnswerList)
	{
		this.queryAnswerList = queryAnswerList;
	}

	public void setQueryList(List<QueryDiscussion> queryList)
	{
		this.queryList = queryList;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUsUserName(String usUserName)
	{
		this.usUserName = usUserName;
	}

}
