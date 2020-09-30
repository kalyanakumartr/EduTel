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
import com.hbs.edutel.model.OnlineEBook;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EChapter;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineEBookAction extends OnlineEBookActionData
{
	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String deleteOnlineEBook() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			String oeBookId = request.getParameter("oeBookId");
			OnlineEBook delBk = adminBo.deleteOnlineEBook(oeBookId);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(delBk))
			{
				caLogger.info(Delete_EBook, "deleteOnlineEBook", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "EBook : " + delBk.getOeBookName() + "\n\nChapter " + delBk.getOeChapter() + " has been deleted successfully");
			}
			else
			{
				caLogger.error(Delete_EBook, "deleteOnlineEBook", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete the selected E-Book. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String downloadEBookByAjax()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null)
		{
			try
			{
				JQueryDataTableParam jdtParam = new JQueryDataTableParam();
				jdtParam.oeBookId = request.getParameter("oeBookId");

				onlineEBookList = adminBo.onlineEBookListByAjax(jdtParam);
				if (CommonValidator.isListFirstNotEmpty(onlineEBookList))
				{
					OnlineEBook oeBk = onlineEBookList.iterator().next();
					ImageDataVO imVo = new ImageDataVO();
					imVo.request = request;
					imVo.response = response;
					imVo.uploadFileFolderURL = oeBk.getUploadFileFolderURL();
					imVo.uploadFileName = oeBk.getUploadFileName();
					imVo.isClientDownload = true;

					downloadFileUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);
					if (CommonValidator.isNotNullNotEmpty(downloadFileUrl) && downloadFileUrl.indexOf("/content/") > 0)
						downloadFileUrl = downloadFileUrl.substring(downloadFileUrl.indexOf("/content/"));

					caLogger.info(Search_EBook, "downloadEBookByAjax", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
					return EPage.Success.name();
				}
			}
			catch (Exception excep)
			{
				caLogger.error(Search_EBook, "downloadEBookByAjax", EPage.Failure.name() + " : " + excep.getMessage(), this.getClass().getName(), null);
			}

		}
		return EPage.Failure.name();
	}

	public String onlineEBookListByAjax() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		if (CommonValidator.isNotNullNotEmpty(jdtParam) == false || CommonValidator.isNotNullNotEmpty(jdtParam.sSearch) == false)
		{
			jdtParam = new JQueryDataTableParam();
			jdtParam.sSearch = subjectList.iterator().next().getValue();
		}
		jdtParam.oeChapter = request.getParameter("oeChapter");
		onlineEBookList = adminBo.onlineEBookListByAjax(jdtParam);
		return EPage.Success.name();
	}

	public String onlineEBookListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<OnlineEBook> onlineEBooksList = adminBo.getOnlineEBookListByJson(jdtParam);
		List<OnlineEBook> onlineEBooksListAll = adminBo.getOnlineEBookListByJsonAll(jdtParam);
		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (OnlineEBook oeBook : onlineEBooksList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(oeBook.getOeSubject());
			newArray.add(oeBook.getOeBookName());
			newArray.add(EChapter.getEChapter(oeBook.getOeChapter()).name());
			newArray.add(oeBook.getCreatedBy());
			newArray.add(oeBook.getCreatedDate() + "");
			newArray.add(oeBook.getOeBookId());
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(onlineEBooksListAll.size());
		dataTableObject.setiTotalRecords(onlineEBooksListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String onlineEBookRepository()
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
		onlineEBookList = adminBo.onlineEBookListByAjax(jdtParam);
		return result;

	}

	public String onlineEBookRepositoryUpload()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			usEmployeeId = sessionUser.getUsEmployeeId();
			if (adminBo.onlineEBookRepositoryUpload(this))
			{
				caLogger.info(Create_EBook, "onlineEBookRepositoryUpload", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

				addActionError("EBook(s) has been saved Successfully.");
			}
			else
			{
				caLogger.info(Create_EBook, "onlineEBookRepositoryUpload", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("EBook(s) Upload Failed.");
			}
			onlineEBookList = adminBo.onlineEBookListByAjax(new JQueryDataTableParam());
			return generateSubjectsListComboValues();
		}

		return EPage.AccessDenied.name();
	}

}
