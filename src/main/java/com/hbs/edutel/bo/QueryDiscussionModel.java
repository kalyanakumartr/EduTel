package com.hbs.edutel.bo;

import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.QueryDiscussion;
import com.hbs.edutel.util.common.factory.UsersFactory;

public class QueryDiscussionModel
{

	protected QueryDiscussion				qryDiscussion	= new QueryDiscussion();	;
	protected QueryDiscussionDisplayParam	qryDispParam	= new QueryDiscussionDisplayParam();
	protected IUsers						users			= UsersFactory.getInstance().getUsersInstance();

	public QueryDiscussion getQryDiscussion()
	{
		return qryDiscussion;
	}

	public QueryDiscussionDisplayParam getQryDispParam()
	{
		return qryDispParam;
	}

	public IUsers getUsers()
	{
		return users;
	}

	public void setQryDiscussion(QueryDiscussion qryDiscussion)
	{
		this.qryDiscussion = qryDiscussion;
	}

	public void setQryDispParam(QueryDiscussionDisplayParam qryDispParam)
	{
		this.qryDispParam = qryDispParam;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

}
