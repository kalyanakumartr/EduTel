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
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.School;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EGenerate;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminSchoolManagementAction extends AdminSchoolManagementActionData
{
	private static final long	serialVersionUID	= 1L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String deleteSchool() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			school = adminBo.deleteSchool(this);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(school))
			{
				caLogger.info(Delete_School, "deleteSchool", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "School Name : " + school.getScSchoolName() + "\n\nBoard Type " + school.getScSchoolType() + " has been deleted successfully");
			}
			else
			{
				caLogger.error(Delete_School, "deleteSchool", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete the selected School. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String getSchoolAddress(School school)
	{
		StringBuffer addressBuffer = new StringBuffer();

		if (CommonValidator.isNotNullNotEmpty(school.getScAddress1()))
		{
			addressBuffer.append(school.getScAddress1().trim() + COMMA_SPACE);
		}

		if (CommonValidator.isNotNullNotEmpty(school.getScAddress2()))
		{
			addressBuffer.append(school.getScAddress2().trim() + COMMA_SPACE);
		}
		if (CommonValidator.isNotNullNotEmpty(school.getScLandMark()))
		{
			addressBuffer.append(school.getScLandMark().trim() + COMMA_SPACE);
		}
		if (CommonValidator.isNotNullNotEmpty(school.getScCity()))
		{
			addressBuffer.append(school.getScCity().trim() + HYPEN);
		}
		if (CommonValidator.isNotNullNotEmpty(school.getScPinCode()))
		{
			addressBuffer.append(school.getScPinCode().trim() + COMMA_SPACE);
		}
		if (CommonValidator.isNotNullNotEmpty(addressBuffer))
		{
			if (CommonValidator.isNotNullNotEmpty(school.getScState()))
			{
				String add = addressBuffer.toString();
				add = add.substring(0, addressBuffer.toString().length() - 2) + COMMA_SPACE;
				addressBuffer.delete(0, addressBuffer.length());
				addressBuffer.append(add + school.getScState().trim() + COMMA_SPACE);
			}
			if (CommonValidator.isNotNullNotEmpty(school.getScCountry()))
			{
				addressBuffer.append(school.getScCountry().trim() + DOT);
			}
			if (CommonValidator.isNotNullNotEmpty(addressBuffer))
			{
				return CommonUtil.checkStringEndsWithDot(addressBuffer).toString();
			}
		}

		return "";
	}

	public String schoolListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<School> schoolsList = adminBo.getSchoolsList(jdtParam);
		List<School> schoolsListAll = adminBo.getSchoolsListAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (School school : schoolsList)
		{
			if (CommonValidator.isNotNullNotEmpty(school))
			{

				ArrayList<String> newArray = new ArrayList<String>(0);
				newArray.add(CommonUtil.initCapsName(school.getScSchoolName()));
				newArray.add(school.getScSchoolType());
				newArray.add(school.getScEmail());
				newArray.add(school.getScMobileNo());
				newArray.add(CommonUtil.initCapsName(getSchoolAddress(school)));
				newArray.add(school.getScWebSiteURL());
				newArray.add(school.getCreatedBy());
				newArray.add(school.getCreatedDate() + "");
				newArray.add(school.getScSchoolId());
				aaData.add(newArray);
			}
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(schoolsListAll.size());
		dataTableObject.setiTotalRecords(schoolsListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();

		return EPage.Success.name();
	}

	public String schoolPreRegistration()
	{
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("p")) == false)
		{
			clearErrors();
			clearErrorsAndMessages();
		}
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			cityList = CommonUtil.getLabelValueList(cities);
			stateList = CommonUtil.getLabelValueList(states);
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String schoolPreUpdate()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			String scSchoolId = request.getParameter("scSchoolId");

			school = adminBo.getSchoolById(new JQueryDataTableParam(scSchoolId));

			cityList = CommonUtil.getLabelValueList(cities);
			stateList = CommonUtil.getLabelValueList(states);

			if (CommonValidator.isNotNullNotEmpty(school))
			{
				return EPage.Success.name();
			}

		}
		return EPage.AccessDenied.name();
	}

	public String schoolRegistration()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			school.setScSchoolId(EGenerate.valueOf("School").getPrimaryId());
			school.setCreatedBy(sessionUser.getUsEmployeeId());
			school.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			if (adminBo.schoolRegistration(this))
			{
				caLogger.info(Create_School, "schoolRegistration", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

				addActionError("Your Information has been saved Successfully.");

			}
			else
			{
				caLogger.info(Create_School, "schoolRegistration", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("Registration Failed.");
			}
		}

		return EPage.Success.name();
	}

	public String schoolUpdate()
	{

		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			if (adminBo.updateSchool(this))
			{
				caLogger.info(Update_School, "schoolUpdate", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("School Information has been updated Successfully. Last updated School : " + school.getScSchoolName());
				return EPage.Success.name();
			}
			else
			{
				caLogger.info(Update_School, "schoolUpdate", EPage.Failure.name(), this.getClass().getName(), school.getScSchoolId());
				addActionError("School Update Failed.");
				return EPage.Failure.name();
			}
		}
		return EPage.AccessDenied.name();
	}

	public String searchSchool()
	{
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("p")) == false)
		{
			clearErrors();
			clearErrorsAndMessages();
		}
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}
}
