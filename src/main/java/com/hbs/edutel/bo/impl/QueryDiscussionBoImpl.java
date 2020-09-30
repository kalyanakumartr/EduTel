package com.hbs.edutel.bo.impl;

import java.util.List;

import com.hbs.edutel.action.QueryDiscussionAction;
import com.hbs.edutel.bo.QueryDiscussionBo;
import com.hbs.edutel.bo.QueryDiscussionModel;
import com.hbs.edutel.common.action.CommonActionDAOData;
import com.hbs.edutel.model.QueryDiscussion;
import com.hbs.edutel.util.JQueryDataTableParam;

public class QueryDiscussionBoImpl extends CommonActionDAOData implements QueryDiscussionBo
{

	
	public QueryDiscussion deleteQueryDiscussion(String queryId)
	{
		return queryDiscussionDAO.deleteQueryDiscussion(queryId);
	}

	
	public List<QueryDiscussion> queryAnswerList(JQueryDataTableParam jdtParam)
	{
		return queryDiscussionDAO.queryAnswerList(jdtParam);
	}

	
	public List<QueryDiscussion> queryAnswerListAll(JQueryDataTableParam jdtParam)
	{
		return queryDiscussionDAO.queryAnswerListAll(jdtParam);
	}

	
	public boolean queryAnswerUpdate(QueryDiscussionAction qdAction)
	{
		return queryDiscussionDAO.queryAnswerUpdate(qdAction);
	}

	
	public boolean submitQueryAnswerByAdmin(QueryDiscussionModel qryDiscModel)
	{
		return queryDiscussionDAO.submitQueryAnswerByAdmin(qryDiscModel);
	}

	
	public boolean submitUserQuery(QueryDiscussionModel qryDiscModel)
	{
		return queryDiscussionDAO.submitUserQuery(qryDiscModel);
	}

	
	public List<QueryDiscussion> viewAdminQueryAnswerList(QueryDiscussionModel qryDiscModel)
	{
		return queryDiscussionDAO.viewAdminQueryAnswerList(qryDiscModel);
	}

	
	public List<QueryDiscussion> viewAdminQueryList(QueryDiscussionModel qryDiscModel)
	{
		return queryDiscussionDAO.viewAdminQueryList(qryDiscModel);
	}

	
	public List<QueryDiscussion> viewUserQueryAnswerList(QueryDiscussionModel qryDiscModel)
	{
		return queryDiscussionDAO.viewUserQueryAnswerList(qryDiscModel);
	}
}
