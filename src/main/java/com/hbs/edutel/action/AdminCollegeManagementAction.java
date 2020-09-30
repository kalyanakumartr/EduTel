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
import com.hbs.edutel.model.College;
import com.hbs.edutel.model.CollegeEForm;
import com.hbs.edutel.model.School;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EFormStatus;
import com.hbs.edutel.util.common.ConstEnumUtil.EFormType;
import com.hbs.edutel.util.common.ConstEnumUtil.EGenerate;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminCollegeManagementAction extends AdminCollegeManagementActionData
{
	private static final long		serialVersionUID	= -3557513384222013473L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());

	public String collegeEFormListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		IUsers sessionUsers = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUsers != null && sessionUsers.isStudent())
		{
			jdtParam.usUsersType = EPage.Student.name();
			jdtParam.usEmployeeId = sessionUsers.getUsEmployeeId();
		}

		List<CollegeEForm> collegeEFormList = adminBo.collegeEFormListByJson(jdtParam);
		List<CollegeEForm> collegeEFormListAll = adminBo.collegeEFormListByJsonAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (CollegeEForm eForm : collegeEFormList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			if (sessionUsers.isEmployee())
			{
				if (CommonValidator.isNotNullNotEmpty(eForm.getEmployeeId()))
				{
					IUsers user = adminBo.getUserById(new JQueryDataTableParam(eForm.getEmployeeId()));
					newArray.add(CommonUtil.initCapsName(user.getUsUserName()));
				}
				else
				{
					newArray.add("--");
				}
			}
			if (CommonValidator.isNotNullNotEmpty(eForm.getCollegeId()))
			{
				College college = adminBo.getCollegeById(new JQueryDataTableParam(eForm.getCollegeId()));
				newArray.add(CommonUtil.initCapsName(college.getClCollegeName()) + " - Rs. " + CommonUtil.getDecimalValueofAmt(college.getClFormCostOthers()));
			}
			else
			{
				newArray.add("--");
			}
			if (sessionUsers.isEmployee())
			{
				if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser()))
				{
					String address = "--";
					if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser().getUsAddress1()))
					{
						address = eForm.getCreatedUser().getUsAddress1().trim();
						if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser().getUsAddress2()))
						{
							address += ",<BR>" + eForm.getCreatedUser().getUsAddress2().trim();
						}
						if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser().getUsLandMark()))
						{
							address += ",<BR>" + eForm.getCreatedUser().getUsLandMark().trim();
						}
						if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser().getUsCity()))
						{
							address += ",<BR>" + eForm.getCreatedUser().getUsCity().trim().toUpperCase();
						}
						if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser().getUsCountry()))
						{
							address += ",<BR>" + eForm.getCreatedUser().getUsCountry().trim().toUpperCase();
						}
						if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser().getUsPinCode()))
						{
							address += " - " + eForm.getCreatedUser().getUsPinCode().trim();
						}
						if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser().getUsMobileNo()))
						{
							address += " .<BR> " + eForm.getCreatedUser().getUsMobileNo().trim();
						}
					}
					newArray.add(address);
				}

				Double totalCost = eForm.getClFormCostOthers() + eForm.getClCourierCharge() + eForm.getClEduTelCharge();
				newArray.add(String.valueOf(CommonUtil.getDecimalValueofAmt(totalCost)));
				if (eForm.isPurchased())
				{
					newArray.add(EFormStatus.Ordered.name());
				}
				else
				{
					newArray.add(EFormStatus.Pending.name());
				}

				if (CommonValidator.isNotNullNotEmpty(eForm.getCreatedUser().getUsSchoolId()))
				{
					School school = adminBo.getSchoolById(new JQueryDataTableParam(eForm.getCreatedUser().getUsSchoolId()));
					if (CommonValidator.isNotNullNotEmpty(school))
					{
						newArray.add(school.getScSchoolName() + " - " + school.getScCity());
					}
					else
					{
						newArray.add("--");
					}
				}
				newArray.add(eForm.getCreatedDate() + "");
			}
			newArray.add(eForm.isPurchased() + "");
			if (eForm.isPurchased() && sessionUsers.isStudent())
				newArray.add(eForm.isPurchased() + "");
			else
				newArray.add(String.valueOf(eForm.getFormAutoId()));
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(collegeEFormListAll.size());
		dataTableObject.setiTotalRecords(collegeEFormListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		caLogger.info(Search_College, "collegeEFormListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		return EPage.Success.name();
	}

	public String collegeListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		List<College> collegesList = adminBo.getCollegesList(jdtParam);
		List<College> collegesListAll = adminBo.getCollegesListAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (College college : collegesList)
		{
			if (CommonValidator.isNotNullNotEmpty(college))
			{
				ArrayList<String> newArray = new ArrayList<String>(0);
				newArray.add(CommonUtil.initCapsName(college.getClCollegeName()));
				newArray.add(college.getClCollegeType());
				newArray.add(college.getClEmail());
				newArray.add(college.getClMobileNo());
				newArray.add(CommonUtil.initCapsName(getCollegeAddress(college)));
				newArray.add(college.getClWebSiteURL());
				newArray.add(college.getCreatedBy());
				newArray.add(college.getCreatedDate() + "");
				newArray.add(college.getClCollegeId());
				aaData.add(newArray);
			}
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(collegesListAll.size());
		dataTableObject.setiTotalRecords(collegesListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		caLogger.info(Search_College, "collegeListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		return EPage.Success.name();
	}

	public String collegePreRegistration()
	{
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("p")) == false)
		{
			clearErrors();
			clearErrorsAndMessages();
		}
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			college = new College();
			cityList = CommonUtil.getLabelValueList(cities);
			stateList = CommonUtil.getLabelValueList(states);
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String collegePreUpdate()
	{

		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			String clCollegeId = request.getParameter("clCollegeId");

			college = adminBo.getCollegeById(new JQueryDataTableParam(clCollegeId));
			cityList = CommonUtil.getLabelValueList(cities);
			stateList = CommonUtil.getLabelValueList(states);
			if (CommonValidator.isNotNullNotEmpty(college))
			{
				return EPage.Success.name();
			}
		}
		return EPage.AccessDenied.name();
	}

	public String collegeRegistration()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			college.setClCollegeId(EGenerate.College.getPrimaryId());
			college.setCreatedBy(sessionUser.getUsEmployeeId());
			college.setCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			if (adminBo.collegeRegistration(this))
			{
				caLogger.info(Create_College, "collegeRegistration", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

				addActionError("College/Form Information has been saved Successfully.");

			}
			else
			{
				caLogger.info(Create_College, "collegeRegistration", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("College/Form Registration Failed.");
			}
		}

		return EPage.Success.name();
	}

	public String collegeUpdate()
	{

		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			if (adminBo.updateCollege(this))
			{
				caLogger.info(Update_College, "collegeUpdate", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("Information has been updated Successfully. Last updated College : " + college.getClCollegeId());
				return EPage.Success.name();
			}
			else
			{
				caLogger.info(Update_College, "collegeUpdate", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("College Update Failed.");
				return EPage.Failure.name();
			}
		}
		return EPage.AccessDenied.name();
	}

	public String deleteCollege() throws IOException
	{

		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			college = adminBo.deleteCollege(this);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(college))
			{
				caLogger.info(Delete_College, "deleteCollege", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "College Name : " + college.getClCollegeName() + "\n\nCollege Type " + college.getClCollegeType() + " has been deleted successfully");
			}
			else
			{
				caLogger.info(Delete_College, "deleteCollege", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete the selected College. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String deleteEForm() throws IOException
	{

		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isStudent() || sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			collegeForm = adminBo.deleteEForm(this);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(collegeForm))
			{
				college = adminBo.getCollegeById(new JQueryDataTableParam(collegeForm.getCollegeId()));
				if (CommonValidator.isNotNullNotEmpty(college))
				{
					// caLogger.info(Delete_College, "deleteEForm", EPage.Success.name(),
					// this.getClass().getName(), sessionUser.getUsEmployeeId());
					jsonObj.put("value", college.getClCollegeName() + " Application Form " + " has been Removed successfully");
				}
				else
				{
					// caLogger.info(Delete_College, "deleteEForm", EPage.Failure.name(),
					// this.getClass().getName(), sessionUser.getUsEmployeeId());
					jsonObj.put("value", "Unable to remove the selected College Application Form. \n\n\nPlease contact your Administrator!.");
				}

			}
			else
			{
				jsonObj.put("value", "Unable to process your request. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();
			return EPage.Success.name();
		}

		return EPage.Failure.name();

	}

	public String getCollegeAddress(College college)
	{
		StringBuffer addressBuffer = new StringBuffer();

		if (CommonValidator.isNotNullNotEmpty(college.getClAddress1()))
		{
			addressBuffer.append(college.getClAddress1().trim() + COMMA_SPACE);
		}

		if (CommonValidator.isNotNullNotEmpty(college.getClAddress2()))
		{
			addressBuffer.append(college.getClAddress2().trim() + COMMA_SPACE);
		}
		if (CommonValidator.isNotNullNotEmpty(college.getClLandMark().trim() + COMMA_SPACE))
		{
			addressBuffer.append(college.getClLandMark().trim() + COMMA_SPACE);
		}
		if (CommonValidator.isNotNullNotEmpty(college.getClCity()))
		{
			addressBuffer.append(college.getClCity().trim() + HYPEN);
		}
		if (CommonValidator.isNotNullNotEmpty(college.getClPinCode()))
		{
			addressBuffer.append(college.getClPinCode().trim() + COMMA_SPACE);
		}
		if (CommonValidator.isNotNullNotEmpty(addressBuffer))
		{
			if (CommonValidator.isNotNullNotEmpty(college.getClState()))
			{
				String add = addressBuffer.toString();
				add = add.substring(0, addressBuffer.toString().length() - 2) + COMMA_SPACE;
				addressBuffer.delete(0, addressBuffer.length());
				addressBuffer.append(add + college.getClState().trim() + COMMA_SPACE);
			}
			if (CommonValidator.isNotNullNotEmpty(college.getClCountry()))
			{
				addressBuffer.append(college.getClCountry().trim() + DOT);
			}
			if (CommonValidator.isNotNullNotEmpty(addressBuffer))
			{
				return CommonUtil.checkStringEndsWithDot(addressBuffer).toString();
			}
		}
		return "";
	}

	public String purchaseEForm()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isStudent()))
		{
			if (adminBo.setPurchasedEFormList(this))
			{
				addActionError("College Form Request has been send to EduTel Admin.");

				return EPage.Success.name();
			}
		}
		return EPage.AccessDenied.name();
	}

	public String searchCollege()
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

	public String viewOnlineFormRequest()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			return EPage.Admin.name();
		}
		if (sessionUser != null && (sessionUser.isStudent()))
		{
			Double eFormCost = 0.00;
			Double eFormCourierCost = 0.00;
			Double eFormServiceCost = 0.00;

			if (CommonValidator.isNotNullNotEmpty(request.getParameter("clgId")))
			{
				collegeId = request.getParameter("clgId");
				College college = adminBo.getCollegeById(new JQueryDataTableParam(collegeId));
				if (adminBo.orderEForm(this))
				{
					addActionError(college.getClCollegeName() + " Application Form Selected.");
				}
				else
				{
					addActionError(college.getClCollegeName() + " Application Form already Ordered.");
				}

			}

			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			jdtParam.eFormType = EFormType.EntranceForm;
			collegeList = adminBo.getCollegesList(jdtParam);

			collegeEFormList = adminBo.collegeEFormOrderedList(sessionUser);
			if (CommonValidator.isNotNullNotEmpty(collegeEFormList))
			{
				collegeListSize = collegeEFormList.size();

				for (CollegeEForm eForm : collegeEFormList)
				{
					jdtParam = new JQueryDataTableParam();
					jdtParam.eFormType = EFormType.EntranceForm;
					jdtParam.sSearch = eForm.getCollegeId();
					College college = adminBo.getCollegesList(jdtParam).iterator().next();

					if (CommonValidator.isNotNullNotEmpty(college.getClFormCostOthers()))
					{
						eFormCost = eFormCost + college.getClFormCostOthers();
					}
					if (CommonValidator.isNotNullNotEmpty(college.getClCourierCharge()))
					{
						eFormCourierCost = eFormCourierCost + college.getClCourierCharge();
					}
					if (CommonValidator.isNotNullNotEmpty(college.getClEduTelCharge()))
					{
						eFormServiceCost = eFormServiceCost + college.getClEduTelCharge();
					}
					total = eFormCost + eFormCourierCost + eFormServiceCost;
				}

				formCost = eFormCost;
				courierCost = eFormCourierCost;
				eduTelServiceCost = eFormServiceCost;
			}
			else
			{
				collegeListSize = 0;
				formCost = 0.00;
				courierCost = 0.00;
				eduTelServiceCost = 0.00;
				total = 0.00;
			}

			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}
}
