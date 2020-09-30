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
import com.hbs.edutel.model.OnlineEKeyPoints;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EChapter;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineEKeyPointsAction extends OnlineEKeyPointsActionData
{
	private static final long	serialVersionUID	= 2797243022538282861L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String deleteOnlineEKeyPoints() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			String oeKeyPointsId = request.getParameter("oeKeyPointsId");
			OnlineEKeyPoints delKeyPoints = adminBo.deleteOnlineEKeyPoints(oeKeyPointsId);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(delKeyPoints))
			{
				caLogger.info(Delete_EKeyPoints, "deleteOnlineEKeyPoints", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "EKeyPoints : " + delKeyPoints.getOeKeyPointsName() + " has been deleted successfully");
			}
			else
			{
				caLogger.error(Delete_EKeyPoints, "deleteOnlineEKeyPoints", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete the selected E-KeyPoints. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String downloadEKeyPointsByAjax()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null)
		{
			try
			{
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				jdtParam.oeKeyPointsId = request.getParameter("oeKeyPointsId");
				onlineEKeyPointsList = adminBo.onlineEKeyPointsListByAjax(jdtParam);

				if (CommonValidator.isListFirstNotEmpty(onlineEKeyPointsList))
				{
					OnlineEKeyPoints oeBP = onlineEKeyPointsList.iterator().next();
					ImageDataVO imVo = new ImageDataVO();
					imVo.request = request;
					imVo.response = response;
					imVo.uploadFileFolderURL = oeBP.getUploadFileFolderURL();
					imVo.uploadFileName = oeBP.getUploadFileName();
					imVo.isClientDownload = true;

					downloadFileUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);
					if (CommonValidator.isNotNullNotEmpty(downloadFileUrl) && downloadFileUrl.indexOf("/content/") > 0)
						downloadFileUrl = downloadFileUrl.substring(downloadFileUrl.indexOf("/content/"));

					caLogger.info(Search_EKeyPoints, "downloadEKeyPointsByAjax", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
					return EPage.Success.name();
				}
			}
			catch (Exception e)
			{
				caLogger.error(Search_EKeyPoints, "downloadEKeyPointsByAjax", EPage.Failure.name(), this.getClass().getName(), null);
			}

		}
		return EPage.Failure.name();
	}

	public String onlineEKeyPointsListByAjax() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);

		if (CommonValidator.isNotNullNotEmpty(jdtParam) == false || CommonValidator.isNotNullNotEmpty(jdtParam.sSearch) == false)
		{
			jdtParam = new JQueryDataTableParam();
			jdtParam.sSearch = subjectList.iterator().next().getValue();
		}
		jdtParam.oeChapter = request.getParameter("oeChapter");
		onlineEKeyPointsList = adminBo.onlineEKeyPointsListByAjax(jdtParam);
		return EPage.Success.name();
	}

	public String onlineEKeyPointsListByJson() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
			List<OnlineEKeyPoints> onlineEKeyPointssList = adminBo.getOnlineEKeyPointsListByJson(jdtParam);
			List<OnlineEKeyPoints> onlineEKeyPointssListAll = adminBo.getOnlineEKeyPointsListByJsonAll(jdtParam);

			List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

			for (OnlineEKeyPoints oeKeyPoints : onlineEKeyPointssList)
			{
				ArrayList<String> newArray = new ArrayList<String>(0);
				newArray.add(oeKeyPoints.getOeKeyPointsName());
				newArray.add(oeKeyPoints.getOeSubject());
				newArray.add(EChapter.getEChapter(oeKeyPoints.getOeChapter()).name());
				newArray.add(oeKeyPoints.getOeSchoolType());
				newArray.add(oeKeyPoints.getCreatedBy());
				newArray.add(oeKeyPoints.getCreatedDate() + "");
				newArray.add(oeKeyPoints.getOeKeyPointsId());
				aaData.add(newArray);
			}

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(aaData);
			dataTableObject.setiTotalDisplayRecords(onlineEKeyPointssListAll.size());
			dataTableObject.setiTotalRecords(onlineEKeyPointssListAll.size());

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(dataTableObject);
			response.getWriter().write(jsonString);
			response.getWriter().close();
			caLogger.info(Search_EKeyPoints, "onlineEKeyPointsListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String onlineEKeyPointsRepository()
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
			jdtParam.sSearch = subjectList.iterator().next().getValue();
		}
		onlineEKeyPointsList = adminBo.onlineEKeyPointsListByAjax(jdtParam);
		return result;
	}

	public String onlineEKeyPointsRepositoryUpload()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			if (adminBo.onlineEKeyPointsRepositoryUpload(this))
			{
				caLogger.info(Create_EKeyPoints, "onlineEKeyPointsRepositoryUpload", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("E-KeyPoints has been saved Successfully.");
			}
			else
			{
				caLogger.info(Create_EKeyPoints, "onlineEKeyPointsRepositoryUpload", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("E-KeyPoints Upload Failed.");
			}
			onlineEKeyPointsList = adminBo.onlineEKeyPointsListByAjax(new JQueryDataTableParam());
			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

}
