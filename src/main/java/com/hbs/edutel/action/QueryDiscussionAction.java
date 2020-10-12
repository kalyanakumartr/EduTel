package com.hbs.edutel.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.QueryDiscussion;
import com.hbs.edutel.model.School;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QueryDiscussionAction extends QueryDiscussionActionData
{

	private static final long	serialVersionUID	= 5935725077392445172L;
	public String subject;

	public String deleteQuery() throws Exception
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin() || sessionUser.isEmployee()))
		{
			String queryId = request.getParameter("queryId");
			QueryDiscussion delQuery = queryDiscussionBo.deleteQueryDiscussion(queryId);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(delQuery))
			{
				jsonObj.put("value", "Query has been deleted successfully");
			}
			else
			{
				jsonObj.put("value", "Unable to delete the selected Query. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();
		}
		return EPage.Success.name();
	}

	public String queryAnswerUpdate() throws Exception
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		qtAutoId = request.getParameter("autoId");
		if (CommonValidator.isNotNullNotEmpty(qtAutoId) && sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin() || sessionUser.isEmployee()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			usUserName = sessionUser.getUsUserName();
			qtAnswer = request.getParameter("answer");
			qtRated = request.getParameter("rated");
			queryDiscussionBo.queryAnswerUpdate(this);
		}
		return EPage.Success.name();
	}

	public String queryListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && sessionUser.isStudent())
		{
			jdtParam.isLibrary = CommonValidator.isNotNullNotEmpty(request.getParameter("p"));
			if (jdtParam.isLibrary == false)
				jdtParam.usEmployeeId = sessionUser.getUsEmployeeId();
		}
		subject = jdtParam.qtSubject = request.getParameter("subject");
		List<QueryDiscussion> queryList = queryDiscussionBo.queryAnswerList(jdtParam);
		List<QueryDiscussion> queryListAll = queryDiscussionBo.queryAnswerListAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (QueryDiscussion qd : queryList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(qd.getQtQuery());// Admin 0 //Student 0
			if (CommonValidator.isNotNullNotEmpty(qd.getQtReplyName()))
			{
				newArray.add(qd.getQtAnswer());// Admin 1 //Student 1
			}
			else
			{
				newArray.add("");// Admin 1 //Student 1
			}

			newArray.add(qd.getQtRated() + "");// Admin 2 //Student 2
			newArray.add(CommonUtil.initCapsName(qd.getQtRaisedName()));// Admin 3 //Student 3
			newArray.add(qd.getQtRaisedDate() + "");// Admin 4 //Student 4

			if (CommonValidator.isNotNullNotEmpty(qd.getQtRaisedBy()))
			{
				user = adminBo.getUserById(new JQueryDataTableParam(qd.getQtRaisedBy()));
				if (CommonValidator.isNotNullNotEmpty(user))
				{
					School school = adminBo.getSchoolById(new JQueryDataTableParam(user.getUsSchoolId()));
					if (CommonValidator.isNotNullNotEmpty(school))
					{
						newArray.add(CommonUtil.initCapsName(school.getScSchoolName())); // Admin 5 //Student 5
					}
					else
					{
						newArray.add("--");// Admin 5 //Student 5
					}
				}
				else
				{
					newArray.add("--");// Admin 5 //Student 5
				}

			}

			if (CommonValidator.isNotNullNotEmpty(qd.getQtReplyName()))
			{
				newArray.add(CommonUtil.initCapsName(qd.getQtReplyName()));// Admin 6 // Student 6
			}
			else
			{
				newArray.add("--");// Admin 6 // Student 6
			}

			if (CommonValidator.isNotNullNotEmpty(qd.getQtReplyDate()))
			{
				newArray.add(qd.getQtReplyDate() + "");// Admin 7 // Student 7
			}
			else
			{
				newArray.add("--");// Admin 7 // Student 7
			}

			if (sessionUser != null && sessionUser.isStudent() == false)
			{
				newArray.add(qd.getQtAutoId() + "");// Admin 8
				newArray.add(qd.getQtAutoId() + "");// Admin 9
			}
			
			newArray.add(qd.getQtSubject()+ "");// Admin 10 // Student 8
				
			
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(queryListAll.size());
		dataTableObject.setiTotalRecords(queryListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String submitQueryAnswer() throws Exception
	{
		queryList.clear();
		queryAnswerList.clear();

		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin() || sessionUser.isEmployee()))
		{
			qryDiscModel.setUsers(sessionUser);
			qryDiscModel.setQryDispParam(qryDiscDispParam);
			if (queryDiscussionBo.submitQueryAnswerByAdmin(qryDiscModel))
				addActionMessage(" Your Answer(s) posted successfully.");
			else
				addActionMessage(" Your Answer(s) posted unsuccessfully.");

			queryList = queryDiscussionBo.viewAdminQueryList(qryDiscModel);
			queryAnswerList = queryDiscussionBo.viewAdminQueryAnswerList(qryDiscModel);

			return EPage.Admin.name();
		}
		else if (sessionUser != null)
		{
			qryDisc.setQtRated(0);
			qryDisc.setQtRaisedBy(sessionUser.getUsEmployeeId());
			qryDisc.setQtRaisedName(sessionUser.getUsUserName());
			qryDisc.setQtRaisedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			qryDiscModel.setQryDiscussion(qryDisc);
			qryDiscModel.setUsers(sessionUser);
			subject = request.getParameter("subject");
			qryDisc.setQtSubject(subject);

			if (queryDiscussionBo.submitUserQuery(qryDiscModel))
				addActionMessage(" Your Question posted successfully.");
			else
				addActionMessage(" Your Question posted unsuccessfully.");

			queryAnswerList = queryDiscussionBo.viewUserQueryAnswerList(qryDiscModel);

			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String viewAdminQueryAnswerList() throws Exception
	{
		queryList.clear();
		queryAnswerList.clear();

		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		qryDiscModel.setUsers(sessionUser);
		queryList = queryDiscussionBo.viewAdminQueryList(qryDiscModel);
		queryAnswerList = queryDiscussionBo.viewAdminQueryAnswerList(qryDiscModel);

		return EPage.Success.name();
	}

	public String viewQueryAnswerList() throws Exception
	{
		queryList.clear();
		queryAnswerList.clear();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		subject = request.getParameter("subject");
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin() || sessionUser.isEmployee()))
		{

			qryDiscModel.setUsers(sessionUser);
			qryDiscModel.getQryDiscussion().setQtSubject(subject);
			queryList = queryDiscussionBo.viewAdminQueryList(qryDiscModel);
			queryAnswerList = queryDiscussionBo.viewAdminQueryAnswerList(qryDiscModel);
			return EPage.Admin.name();
		}
		else
		{
			qryDiscModel.setUsers(sessionUser);
			qryDiscModel.getQryDiscussion().setQtSubject(subject);
			queryAnswerList = queryDiscussionBo.viewUserQueryAnswerList(qryDiscModel);
			if (CommonValidator.isNotNullNotEmpty(request.getParameter("p")))
				return EPage.Library.name();
			return EPage.Success.name();
		}
	}

}
