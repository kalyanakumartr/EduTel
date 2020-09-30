package com.hbs.edutel.dao;

import java.util.List;

import com.hbs.edutel.action.QueryDiscussionAction;
import com.hbs.edutel.bo.QueryDiscussionModel;
import com.hbs.edutel.model.QueryDiscussion;
import com.hbs.edutel.util.JQueryDataTableParam;

public interface QueryDiscussionDAO
{
	public QueryDiscussion deleteQueryDiscussion(String queryId);

	public List<QueryDiscussion> queryAnswerList(JQueryDataTableParam jdtParam);

	public List<QueryDiscussion> queryAnswerListAll(JQueryDataTableParam jdtParam);

	public boolean queryAnswerUpdate(QueryDiscussionAction qdAction);

	public boolean submitQueryAnswerByAdmin(QueryDiscussionModel qryDiscModel);

	public boolean submitUserQuery(QueryDiscussionModel qryDiscModel);

	public List<QueryDiscussion> viewAdminQueryAnswerList(QueryDiscussionModel qryDiscModel);

	public List<QueryDiscussion> viewAdminQueryList(QueryDiscussionModel qryDiscModel);

	public List<QueryDiscussion> viewUserQueryAnswerList(QueryDiscussionModel qryDiscModel);

}
