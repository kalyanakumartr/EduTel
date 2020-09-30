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
import com.hbs.edutel.model.StudentEnquiry;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.factory.UsersFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminStudentEnquiryAction extends AdminStudentEnquiryActionData
{
	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String deleteEnquiry()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin() || sessionUser.isEmployee()))
		{
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (userBo.deleteEdutelEnquiry(this))
			{
				// caLogger.info("Delete Enquiry", "deleteEnquiry", EPage.Success.name(),
				// this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Enquiry Details has been deleted successfully");

			}
			else
			{
				// caLogger.info("Delete Enquiry", "deleteEnquiry", EPage.Failure.name(),
				// this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete an Enquiry Details. \n\n\nPlease contact your Administrator!.");

			}
			jsonArr.add(jsonObj);
			try
			{
				response.getWriter().write(String.valueOf(jsonArr));
				response.getWriter().close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();

	}

	public String searchEnquiry()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin() || sessionUser.isEmployee()))
		{
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String studentEdutelEnquiry()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = UsersFactory.getInstance().getUsersInstance("Dummy");
		if (sessionUser != null)
		{
			if (userBo.studentEdutelEnquiry(this))
			{
				caLogger.info(Create_Query, "studentEdutelEnquiry", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

				addActionError("Your Information has been registered Successfully.");
			}
			else
			{
				caLogger.info(Create_Query, "studentEdutelEnquiry", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("Registration Failed.");
			}
		}

		return EPage.Success.name();
	}

	public String studentEdutelEnquiryList()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (CommonValidator.isNotNullNotEmpty(sessionUser))
		{
			try
			{
				JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
				List<StudentEnquiry> studEnqList = userBo.getEnquiryList(jdtParam);
				List<StudentEnquiry> studEnqListAll = userBo.getEnquiryListAll(jdtParam);

				List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);
				for (StudentEnquiry studEnq : studEnqList)
				{
					ArrayList<String> newArray = new ArrayList<String>(0);
					newArray.add(studEnq.getEnquirerName());
					newArray.add(studEnq.getEnquirerEmail());
					newArray.add(studEnq.getEnquirerSchoolName());
					newArray.add(studEnq.getEnquirerMobileNo());
					newArray.add(studEnq.getEnquirerBoard());
					newArray.add(studEnq.getEnquirerClass());
					newArray.add(studEnq.getEnquirerState());
					newArray.add(studEnq.getCreatedDate() + "");
					newArray.add(String.valueOf(studEnq.getEnquirerAutoId()));
					aaData.add(newArray);
				}
				DataTableObject dataTableObject = new DataTableObject();
				dataTableObject.setAaData(aaData);
				dataTableObject.setiTotalDisplayRecords(studEnqListAll.size());
				dataTableObject.setiTotalRecords(studEnqListAll.size());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(dataTableObject);
				response.getWriter().write(jsonString);
				response.getWriter().close();
			}
			catch (Exception ex)
			{
				caLogger.error(Audit_Logging_Event_DataEntry, ex.getMessage(), EPage.Failure.name(), null, sessionUser.getUsEmployeeId());
			}
		}
		return EPage.Success.name();
	}

	public String studentEnquiryHome()
	{
		return EPage.Success.name();
	}
}
