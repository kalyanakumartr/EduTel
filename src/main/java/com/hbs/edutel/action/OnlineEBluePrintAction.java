package com.hbs.edutel.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.OnlineEBluePrint;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineEBluePrintAction extends OnlineEBluePrintActionData
{
	private static final long	serialVersionUID	= -5327712547552093572L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String deleteOnlineEBluePrint() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			String oeBluePrintId = request.getParameter("oeBluePrintId");
			OnlineEBluePrint delBluePrint = adminBo.deleteOnlineEBluePrint(oeBluePrintId);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(delBluePrint))
			{
				caLogger.info(Delete_EBluePrint, "deleteOnlineEBluePrint", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "EBluePrint : " + delBluePrint.getOeBluePrintName() + " has been deleted successfully");
			}
			else
			{
				caLogger.error(Delete_EBluePrint, "deleteOnlineEBluePrint", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete the selected E-BluePrint. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String downloadEBluePrintByAjax()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null)
		{
			try
			{
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				jdtParam.oeBluePrintId = request.getParameter("oeBluePrintId");

				onlineEBluePrintList = adminBo.onlineEBluePrintListByAjax(jdtParam);
				if (CommonValidator.isListFirstNotEmpty(onlineEBluePrintList))
				{
					OnlineEBluePrint oeBP = onlineEBluePrintList.iterator().next();
					ImageDataVO imVo = new ImageDataVO();
					imVo.request = request;
					imVo.response = response;
					imVo.uploadFileFolderURL = oeBP.getUploadFileFolderURL();
					imVo.uploadFileName = oeBP.getUploadFileName();
					imVo.isClientDownload = true;

					downloadFileUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);
					if (CommonValidator.isNotNullNotEmpty(downloadFileUrl) && downloadFileUrl.indexOf("/content/") > 0)
						downloadFileUrl = downloadFileUrl.substring(downloadFileUrl.indexOf("/content/"));

					caLogger.info(Search_EBluePrint, "downloadEBluePrintByAjax", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
					return EPage.Success.name();
				}
			}
			catch (Exception e)
			{
				caLogger.error(Search_EBluePrint, "downloadEBluePrintByAjax", EPage.Failure.name(), this.getClass().getName(), null);
			}

		}
		return EPage.Failure.name();
	}

	public String onlineEBluePrintListByAjax() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null)
		{
			JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
			if (CommonValidator.isNotNullNotEmpty(jdtParam) == false || CommonValidator.isNotNullNotEmpty(jdtParam.sSearch) == false)
			{
				jdtParam = new JQueryDataTableParam();
				if (sessionUser.isStudent())
				{
					jdtParam.oeSubject = sessionUser.getUsGroupName();
					jdtParam.oeSchoolType = sessionUser.getUsBoardName();
				}
				else
					jdtParam.oeSubject = overallSubjects;
			}
			onlineEBluePrintList = adminBo.onlineEBluePrintListByAjax(jdtParam);
			caLogger.info(Search_EBluePrint, "onlineEBluePrintListByAjax", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		return EPage.Success.name();
	}

	public String onlineEBluePrintListByJson() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
			List<OnlineEBluePrint> onlineEBluePrintsList = adminBo.getOnlineEBluePrintListByJson(jdtParam);
			List<OnlineEBluePrint> onlineEBluePrintsListAll = adminBo.getOnlineEBluePrintListByJsonAll(jdtParam);
			List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

			for (OnlineEBluePrint oeBluePrint : onlineEBluePrintsList)
			{
				ArrayList<String> newArray = new ArrayList<String>(0);
				newArray.add(oeBluePrint.getOeBluePrintName());
				newArray.add(oeBluePrint.getOeSubject());
				newArray.add(oeBluePrint.getOeSchoolType());
				newArray.add(oeBluePrint.getCreatedBy());
				newArray.add(oeBluePrint.getCreatedDate() + "");
				newArray.add(oeBluePrint.getOeBluePrintId());
				aaData.add(newArray);
			}

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(aaData);
			dataTableObject.setiTotalDisplayRecords(onlineEBluePrintsListAll.size());
			dataTableObject.setiTotalRecords(onlineEBluePrintsListAll.size());

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(dataTableObject);
			response.getWriter().write(jsonString);
			response.getWriter().close();
			caLogger.info(Search_EBluePrint, "onlineEBluePrintListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String onlineEBluePrintRepository()
	{
		clearErrors();
		clearErrorsAndMessages();
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		String result = generateSubjectsListComboValues();
		loginUserType = String.valueOf(sessionUser.isStudent());
		if (CommonValidator.isNotNullNotEmpty(jdtParam) == false || CommonValidator.isNotNullNotEmpty(jdtParam.sSearch) == false)
		{
			jdtParam = new JQueryDataTableParam();
			if (sessionUser.isStudent())
			{
				jdtParam.oeSubject = sessionUser.getUsGroupName();
				jdtParam.oeSchoolType = sessionUser.getUsBoardName();
			}
			else
				jdtParam.oeSubject = overallSubjects;
		}
		onlineEBluePrintList = adminBo.onlineEBluePrintListByAjax(jdtParam);
		return result;
	}

	public String onlineEBluePrintRepositoryUpload()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			if (adminBo.onlineEBluePrintRepositoryUpload(this))
			{
				caLogger.info(Create_EBluePrint, "onlineEBluePrintRepositoryUpload", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("E-BluePrint has been saved Successfully.");
			}
			else
			{
				caLogger.info(Create_EBluePrint, "onlineEBluePrintRepositoryUpload", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("E-BluePrint Upload Failed.");
			}
			onlineEBluePrintList = adminBo.onlineEBluePrintListByAjax(new JQueryDataTableParam());
			return generateSubjectsListComboValues();
		}

		return EPage.AccessDenied.name();
	}

}
