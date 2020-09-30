package com.hbs.edutel.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.InformationAlerts;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminInfoAlertAction extends AdminInfoAlertActionData
{
	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String createInformationAlert()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			if (infoAlertmsg != null)
			{
				InformationAlerts infoAlert = new InformationAlerts();

				infoAlert.setIaInformationMsg(infoAlertmsg);
				infoAlert.setIaInformationType(informationAlert.getIaInformationType());
				infoAlert.setIaCreatedBy(sessionUser.getUsEmployeeId());
				infoAlert.setIaCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
				if (adminBo.createInformationAlert(infoAlert))
				{
					// caLogger.info("", "createInformationAlert", EPage.Success.name(),
					// this.getClass().getName(), sessionUser.getUsEmployeeId());
					addActionError(" Information & Alert has been saved Successfully.");
					return EPage.Success.name();

				}
				else
				{
					// caLogger.info("", "createInformationAlert", EPage.Failure.name(),
					// this.getClass().getName(), sessionUser.getUsEmployeeId());
					addActionError("Information & Alert save failed.");
				}
			}
		}

		return EPage.Failure.name();
	}

	public String deleteInfoAlert() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			boolean infoALert = adminBo.deleteInfoAlert(this);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (infoALert)
			{
				// caLogger.info(, "deleteInfoAlert", EPage.Success.name(),
				// this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Information Alert has been deleted successfully");
			}
			else
			{
				// caLogger.info(, "deleteInfoAlert", EPage.Failure.name(),
				// this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete the Information Alert. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String infoAlertListByJson() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<InformationAlerts> infoAlertsList = adminBo.getInfoAlertList(jdtParam);
		List<InformationAlerts> infoAlertsListAll = adminBo.getInfoAlertListAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (InformationAlerts infoAlert : infoAlertsList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(infoAlert.getIaInformationMsgId() + "");
			newArray.add(infoAlert.getIaInformationMsg());
			if (sessionUser.isStudent() == false)
			{
				newArray.add(infoAlert.getIaInformationType());
				newArray.add(infoAlert.getIaCreatedBy());
				newArray.add(infoAlert.getIaCreatedDate() + "");
				newArray.add(infoAlert.getIaInformationMsgId() + "");
			}
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(infoAlertsListAll.size());
		dataTableObject.setiTotalRecords(infoAlertsListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();

		// caLogger.info(null, "infoAlertListByJson", EPage.Success.name(),
		// this.getClass().getName(), sessionUser.getUsEmployeeId());

		return EPage.Success.name();
	}

	public String infoAlertSearch()
	{
		clearErrors();
		clearErrorsAndMessages();
		setInfoAlertmsg("");
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin() || sessionUser.isEmployee()))
		{
			usUsersType = sessionUser.getUsUsersType();
			informationTypeLabelValueList = CommonUtil.getLabelValueList(informationTypes);
			return EPage.Success.name();
		}
		else if (sessionUser != null && (sessionUser.isStudent()))
		{
			usUsersType = sessionUser.getUsUsersType();
			return EPage.Student.name();
		}
		return EPage.AccessDenied.name();

	}

}
