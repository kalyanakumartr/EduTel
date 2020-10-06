package com.hbs.edutel.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.bo.SerialKeyParam;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.common.model.StudentsMarks;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.School;
import com.hbs.edutel.model.SerialKeyUserMapping;
import com.hbs.edutel.model.ToppersClub;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EKeyGen;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ERole;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.factory.UsersFactory;
import com.hbs.edutel.util.common.image.ImageDataVO;
import com.hbs.edutel.util.common.property.factory.PropFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminUserManagementAction extends AdminUserManagementActionData
{
	private static final long		serialVersionUID	= 1L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());
	private static final String		V7EDUTEL			= PropFactory.getInstance().getProperty(EGeneral.VideoSiteURL);

	public String deleteTopper() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			String topperId = request.getParameter("topperId");
			ToppersClub deletedTopper = adminBo.deleteTopper(new JQueryDataTableParam(topperId));

			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(deletedTopper))
			{
				jsonObj.put("value", "Topper has been deleted successfully");
			}
			else
			{
				jsonObj.put("value", "Unable to delete the selected Topper. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String deleteUser() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			user = adminBo.deleteUser(this);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(user))
			{
				caLogger.info(Delete_User, "deleteUser", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "UserName : " + user.getUsUserName() + "\n\nUser Id " + user.getUsUserID() + " has been deleted successfully");
			}
			else
			{
				caLogger.info(Delete_User, "deleteUser", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to delete the selected User. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String resetPassword() throws IOException
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			user = adminBo.resetPassword(this);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			if (CommonValidator.isNotNullNotEmpty(user))
			{
				caLogger.info(Update_User, "resetPassword", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value",
						"USER NAME : " + user.getUsUserName() + "\n\nUSER ID : " + user.getUsUserID() + "\n\nPASSWORD : " + user.getUsUserPwd() + "\n\npassword has been resetted successfully");
			}
			else
			{
				caLogger.info(Update_User, "resetPassword", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				jsonObj.put("value", "Unable to Reset the selected User Password. \n\n\nPlease contact your Administrator!.");
			}
			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.AccessDenied.name();
	}

	public String toppersClub()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String topperSearch()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String toppersListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);

		List<ToppersClub> toppersList = adminBo.getToppersClubList(jdtParam);
		List<ToppersClub> toppersListAll = adminBo.getToppersClubListAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (ToppersClub topper : toppersList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(CommonUtil.initCapsName(topper.getTpExamName()));// 1
			newArray.add(CommonUtil.initCapsName(topper.getTpStudentName()));// 2

			if (CommonValidator.isNotNullNotEmpty(topper.getTpSchoolName()))
			{
				School school = adminBo.getSchoolById(new JQueryDataTableParam(topper.getTpSchoolName()));
				if (CommonValidator.isNotNullNotEmpty(school))
					newArray.add(CommonUtil.initCapsName(school.getScSchoolName()));// 3
				else
					newArray.add("--");
			}
			newArray.add(CommonUtil.initCapsName(topper.getTpSubject()));// 4
			newArray.add("Chapter " + topper.getTpChapter());// 5
			newArray.add(topper.getTpStartTime() + "");// 6
			newArray.add(topper.getTpEndTime() + "");// 7
			newArray.add(topper.getTpMarks() + "");// 8
			newArray.add(topper.getTpRatings() + "");// 9
			newArray.add(topper.getTpNoOfQuestions() + "");// 10
			newArray.add(topper.getTpId() + ""); // 11

			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(toppersListAll.size());
		dataTableObject.setiTotalRecords(toppersListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		caLogger.info(Search_User, "toppersListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

		return EPage.Success.name();
	}

	public String userListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
		jdtParam.usUsersType = user.getUsUsersType();
		jdtParam.globalSearchFilter();

		if (CommonValidator.isEqual(jdtParam.usUsersType, EPage.Student.name()))
		{
			jdtParam.AdvanceSearchFilter(studentUserAdvancedSearchColumns.split(","));
		}
		else if (CommonValidator.isEqual(jdtParam.usUsersType, EPage.Employee.name()))
		{
			jdtParam.AdvanceSearchFilter(employeeUserAdvancedSearchColumns.split(","));
		}
		else if (CommonValidator.isEqual(jdtParam.usUsersType, EPage.Franchisee.name()))
		{
			jdtParam.AdvanceSearchFilter(franchiseeUserAdvancedSearchColumns.split(","));
		}

		List<IUsers> usersList = adminBo.getUsersList(jdtParam);
		List<IUsers> usersListAll = adminBo.getUsersListAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (IUsers iUsers : usersList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(CommonUtil.initCapsName(iUsers.getUsUserName()));// 0
			newArray.add(iUsers.getUsUserID());// 1
			newArray.add(iUsers.getUsEmail());// 2
			if (CommonValidator.isEqual(iUsers.getUsUsersType(), EPage.Student.name()))
			{
				School school = adminBo.getSchoolById(new JQueryDataTableParam(iUsers.getUsSchoolId()));
				if (CommonValidator.isNotNullNotEmpty(school))
				{
					newArray.add(school.getScSchoolName() + " - " + school.getScCity());// 3
				}
				else
				{
					newArray.add("");// 3
				}
			}
			else if (CommonValidator.isEqual(iUsers.getUsUsersType(), EPage.Employee.name()))
			{
				newArray.add(iUsers.getUsFatherName());// 3
			}
			else if (CommonValidator.isEqual(iUsers.getUsUsersType(), EPage.Franchisee.name()))
			{
				newArray.add(iUsers.getUsFrancheesArea());// 3
			}
			newArray.add(iUsers.getUsMobileNo() + "");// 4
			newArray.add(iUsers.getUsDateOfJoin());// 5
			newArray.add(iUsers.getUsCity());// 6
			newArray.add(CommonUtil.initCapsName(iUsers.getCreatedUser().getUsUserName()));// 7
			newArray.add(iUsers.getUsCreatedDate() + "");// 8
			newArray.add(iUsers.isOnline() + "");// 9
			newArray.add(iUsers.getUsEmployeeId());// 10
			newArray.add(iUsers.getUsEmployeeId());// 11
			newArray.add(iUsers.getUsUsersType());// 12
			if (CommonValidator.isEqual(iUsers.getUsUsersType(), EPage.Student.name()) && CommonValidator.isNotNullNotEmpty(iUsers.getUsSerialKey()))
			{
				List<SerialKeyUserMapping> skuMap = keyGenBo.getPrintSerialKeyList(iUsers.getUsSerialKey().trim(), null);
				if (CommonValidator.isListFirstNotEmpty(skuMap))
				{
					newArray.add(skuMap.iterator().next().getUsers().getUsUserName());// 13
					newArray.add(skuMap.iterator().next().getSerialKeyGenerate().getSerialBatch());// 14
				}
				else
				{
					newArray.add("Error");// 13
					newArray.add("-");// 14
				}
			}
			else
			{
				newArray.add("-");// 13
				newArray.add("-");// 14
			}
			newArray.add(iUsers.getUsEmployeeId());// 15
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(usersListAll.size());
		dataTableObject.setiTotalRecords(usersListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		caLogger.info(Search_User, "userListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

		return EPage.Success.name();
	}

	public String userPreRegistration()
	{
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("p")) == false)
		{
			clearErrors();
			clearErrorsAndMessages();
		}

		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			user.setSessionId(sessionUser.getUsEmployeeId());
			String usersType = request.getParameter("usersType");
			user = UsersFactory.getInstance().getUsersInstance();
			user.setUsUsersType(usersType);
			schoolList = adminBo.getSchoolsListAll(new JQueryDataTableParam());

			cityList = CommonUtil.getLabelValueList(cities);
			stateList = CommonUtil.getLabelValueList(states);

			if (sessionUser.isStudent())
				user.setProfileBy(EPage.Student.name());
			else if (sessionUser.isFranchisee())
				user.setProfileBy(EPage.Franchisee.name());
			else
				user.setProfileBy(EPage.Employee.name());
		}
		return EPage.Success.name();
	}

	public String userPreUpdate()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null)
		{
			String employeeId = request.getParameter("usEmployeeId");

			user = adminBo.getUserById(new JQueryDataTableParam(employeeId));
			if (CommonValidator.isNotNullNotEmpty(user))
			{
				schoolList = adminBo.getSchoolsListAll(new JQueryDataTableParam());
				cityList = CommonUtil.getLabelValueList(cities);
				stateList = CommonUtil.getLabelValueList(states);

				if (sessionUser.isStudent())
					user.setProfileBy(EPage.Student.name());
				else if (sessionUser.isFranchisee())
					user.setProfileBy(EPage.Franchisee.name());
				else
					user.setProfileBy(EPage.Employee.name());

				if (user.isStudent())
				{
					user.setUsUsersType(EPage.Student.name());

				}
				else if (user.isFranchisee())
				{
					user.setUsUsersType(EPage.Franchisee.name());

				}
				else
				{
					user.setUsUsersType(EPage.Employee.name());

				}
			}
			else
			{
				return EPage.AccessDenied.name();
			}

			ImageDataVO imVo = new ImageDataVO();
			imVo.request = request;
			imVo.response = response;
			imVo.uploadFileFolderURL = user.getUploadFileFolderURL();
			imVo.uploadFileName = user.getUploadFileName();

			try
			{
				usUserImageUrl = imageDownload.downloadImageFromRepositoryToSessionFolder(imVo);
			}
			catch (Exception e)
			{
				usUserImageUrl = "images/png/no_image_main.png";
			}
		}
		return EPage.Success.name();
	}

	public String userRegistration()
	{
		clearErrors();
		clearErrorsAndMessages();
		try
		{
			IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
			{
				user.setUsCreatedBy(sessionUser.getUsEmployeeId());
				user.setUsCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				SerialKeyParam skParam = null;
				if (CommonValidator.isEqual(user.getUsUsersType(), EPage.Student.name())) // To_Check_Serial_Key_For_Student_Registration_by_Admin
				{
					skParam = new SerialKeyParam();
					skParam.serialKey = new String[1];
					skParam.serialKey[0] = user.getUsSerialKey();
					skParam.serialKeyStatus = EKeyGen.Not_Sold.getStatus();
				}
				if ((CommonValidator.isNotNullNotEmpty(skParam) && keyGenBo.validateSerialKey(skParam, user)) || CommonValidator.isEqual(user.getUsUsersType(), EPage.Employee.name())
						|| CommonValidator.isEqual(user.getUsUsersType(), EPage.Franchisee.name()))
				{
					if (adminBo.userRegistration(this))
					{
						caLogger.info(Create_User, "User Registration", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
						addActionError(user.getUsUsersType() + " User Information has been saved Successfully. Last saved User : " + user.getUsUserName());
						createEduTel2020User();
						return EPage.Success.name();
					}
					else
					{
						caLogger.info(Create_User, "User Registration", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
						addActionError("User Registration Failed.");
					}
				}
				else
				{
					addActionError("Serial Key : " + user.getUsSerialKey() + " is Invalid or already Validated. Please Check.");
					caLogger.error(Authenticate_Serial_Key, "User Registration : Key : " + skParam.serialKey[0], EPage.Failure.name(), this.getClass().getName(), null);
				}
			}
			else if (CommonValidator.isNotNullNotEmpty(user) && CommonValidator.isEqual(user.getProfileBy(), EPage.Student.name())) // For_Student_Registration_by_Student
			{

				user.setUsCreatedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

				if (adminBo.userRegistration(this))
				{
					caLogger.info(Create_User, "User Registration By " + EPage.Student.name(), EPage.Success.name(), this.getClass().getName(), user.getUsEmployeeId());
					addActionError("Your User Account has been created Successfully.\nCheck your registered email for login credentials.");
					createEduTel2020User();
					return EPage.Student.name();

				}
				else
				{
					caLogger.error(Create_User, "User Registration By " + EPage.Student.name(), EPage.Failure.name(), this.getClass().getName(), user.getUsEmployeeId());
					addActionError("Your User Account creation failed. Please contact EduTel Customer Care.");
					return EPage.Student.name();
				}
			}
		}
		catch (CustomException customExcep)
		{
			caLogger.error(Create_User, "User Registration By " + EPage.Student.name(), customExcep.getLogMessage(), this.getClass().getName(), user.getUsEmployeeId());
			addActionError(customExcep.getLogMessage());
		}
		if (CommonValidator.isNotNullNotEmpty(user) && CommonValidator.isEqual(user.getProfileBy(), EPage.Student.name()))
			return EPage.Student.name();
		else
			return EPage.Failure.name();
	}

	

	public String userSearch()
	{
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("p")) == false)
		{
			clearErrors();
			clearErrorsAndMessages();
		}
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			user.setUsUsersType(request.getParameter("usersType"));
			schoolList = adminBo.getSchoolsListAll(new JQueryDataTableParam());
			cityList = CommonUtil.getLabelValueList(cities);

			usersList.clear();
			for (IUsers usr : userBo.getUserByRole(ERole.Marketing.name()))
			{
				usersList.add(new LabelValueBean(usr.getUsUserName(), usr.getUsEmployeeId()));
			}
			for (IUsers usr : userBo.getUserByRole(ERole.Franchisee.name()))
			{
				usersList.add(new LabelValueBean(usr.getUsUserName(), usr.getUsEmployeeId()));
			}
			generateSerialKeyBatchList();
			return EPage.Success.name();
		}
		else if (sessionUser != null && sessionUser.isStudent())
		{
			user.setUsUsersType(request.getParameter("usersType"));
			return EPage.Student.name();
		}
		return EPage.AccessDenied.name();
	}

	public String userUpdate()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		if (sessionUser != null)
		{
			user.setUsModifiedBy(sessionUser.getUsEmployeeId());
			user.setUsModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));

			if (CommonValidator.isEqual(user.getUsUsersType(), EPage.Student.name())) // To_Check_Serial_Key_For_Student_Registration_by_Admin
			{
				SerialKeyParam skParam = new SerialKeyParam();
				skParam.serialKey = new String[1];
				skParam.serialKey[0] = user.getUsSerialKey();
				skParam.serialKeyStatus = EKeyGen.Not_Sold.getStatus(); // Update always hold Not
																		// Sold Key
				if (keyGenBo.validateSerialKey(skParam, user) == false)
				{
					caLogger.info(Update_User, "userUpdate", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
					addActionError("User Update Failed. Invalid Serial Key or Key already registered.");
					return (sessionUser.isStudent()) ? EPage.Student.name() : EPage.Failure.name();
				}
			}
			if (adminBo.updateUser(this))
			{
				caLogger.info(Update_User, "userUpdate", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError(user.getUsUsersType() + " Information has been updated Successfully. Last updated User : " + user.getUsUserName());
				if (CommonValidator.isEqual(sessionUser.getUsEmployeeId(), user.getUsEmployeeId()))
				{
					request.getSession().setAttribute(ESession.UserObject.getAttribute(), user);
					return sessionUser.getUsUsersType();
				}
				else
				{
					return (sessionUser.isStudent()) ? EPage.Student.name() : EPage.Success.name();
				}
			}
			else
			{
				caLogger.info(Update_User, "userUpdate", EPage.Failure.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
				addActionError("User Update Failed.");
				return (sessionUser.isStudent()) ? EPage.Student.name() : EPage.Failure.name();
			}
		}
		return EPage.AccessDenied.name();
	}

	public String adminMarkPreUpdate()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			studentMarksList = adminBo.getStudentMarksUpdatingList(new JQueryDataTableParam());
			if (CommonValidator.isListFirstNotEmpty(studentMarksList))
			{
				studentMark = studentMarksList.iterator().next();
				studentMark.setStatus(false);
			}
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String getStudentMarksListByJson() throws IOException
	{
		JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);

		List<StudentsMarks> studentMarksList = adminBo.getStudentMarksList(jdtParam);
		List<StudentsMarks> studentMarksListAll = adminBo.getStudentMarksListAll(jdtParam);

		List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);

		for (StudentsMarks studentMark : studentMarksList)
		{
			ArrayList<String> newArray = new ArrayList<String>(0);
			newArray.add(studentMark.getUsers().getUsUserID());// 0
			newArray.add(studentMark.getUsers().getUsUserName());// 1
			newArray.add(studentMark.getUsBatch());// 2
			newArray.add(studentMark.getUsHSCNo());// 3
			newArray.add(studentMark.getUsHSCMark());// 4
			newArray.add(studentMark.getUsSSorPUCMark());// 5
			newArray.add(studentMark.getCreatedBy());// 6
			newArray.add(studentMark.getCreatedDate() + "");// 7
			aaData.add(newArray);
		}

		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(aaData);
		dataTableObject.setiTotalDisplayRecords(studentMarksListAll.size());
		dataTableObject.setiTotalRecords(studentMarksListAll.size());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(dataTableObject);
		response.getWriter().write(jsonString);
		response.getWriter().close();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		caLogger.info(Search_User, "userListByJson", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());

		return EPage.Success.name();
	}

	public String saveStudentHSCMark() throws IOException
	{

		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			if (adminBo.saveStudentHSCMarkUpdate(this))
			{
				return EPage.Success.name();
			}
		}

		return EPage.Failure.name();

	}

	public String nextStudentMarkDetails() throws IOException
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			String usEmployeeId = request.getParameter("usEmployeeId");
			JQueryDataTableParam jdtParam = new JQueryDataTableParam();
			if (CommonValidator.isNotNullNotEmpty(usEmployeeId))
			{
				if (CommonValidator.isNotNullNotEmpty(employeeIdBuffer.toString()))
				{
					if (employeeIdBuffer.toString().contains(usEmployeeId) == false)
						employeeIdBuffer.append(usEmployeeId + "#");
				}
				else
				{
					employeeIdBuffer.append(usEmployeeId + "#");
				}
			}
			jdtParam.usEmployeeId = employeeIdBuffer.toString();
			studentMarksList = adminBo.getStudentMarksUpdatingList(jdtParam);
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();

			if (CommonValidator.isListFirstNotEmpty(studentMarksList))
			{
				studentMark = studentMarksList.iterator().next();
				if (studentMark.isStatus() == true)
				{
					jsonObj.put("user_id", studentMark.getUsers().getUsUserID());
					jsonObj.put("user_name", studentMark.getUsers().getUsUserName());
					jsonObj.put("student_batch", studentMark.getUsBatch());
					jsonObj.put("sslc_puc", studentMark.getUsSSorPUCMark());
					jsonObj.put("hsc_no", studentMark.getUsHSCNo());
					jsonObj.put("employee_id", studentMark.getUsers().getUsEmployeeId());
				}
				else
					jsonObj.put("value", "Not_Skipped");

			}
			else
			{
				jsonObj.put("value", "No_Data");
			}

			jsonArr.add(jsonObj);
			response.getWriter().write(String.valueOf(jsonArr));
			response.getWriter().close();

			return EPage.Success.name();
		}

		return EPage.Success.name();

	}

	private void generateSerialKeyBatchList()
	{
		serialBatchList = new ArrayList<LabelValueBean>();

		SimpleDateFormat sdfCurrentMonth = new SimpleDateFormat("MMM");
		String currentMonth = sdfCurrentMonth.format(new Date());
		int i = (CommonValidator.isEqual(currentMonth, "APR")) ? 0 : -1;
		while ( i < 2 )
		{
			String batch = getYear(i) + "_" + getYear(++i);
			serialBatchList.add(new LabelValueBean(batch, batch));
		}

	}

	private int getYear(int reqYear)
	{
		Calendar calYear = Calendar.getInstance();
		calYear.add(Calendar.YEAR, reqYear);
		return calYear.get(Calendar.YEAR);
	}

	private String join(String key, String value)
	{
		if (value.startsWith("{") || value.startsWith("[{"))
			return "\"" + key + "\" : " + value;
		else
			return "\"" + key + "\" : \"" + value +"\"";
	}
	
	private void createEduTel2020User()
	{
		// Add User In New EduTel 2020 Starts
		try
		{
			String accessToken = (String) request.getSession().getAttribute(OAUTH_TOKEN);
			HttpPost post = new HttpPost(V7EDUTEL + "/addEduTelUser");
			post.setHeader("Authorization", "Bearer " + accessToken);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("Accept", "application/json");
			
			StringBuffer userMediaBean = new StringBuffer();
			userMediaBean.append(join("emailId", user.getUsEmail()) + COMMA_SPACE);
			userMediaBean.append(join("mobileNo", user.getUsMobileNo()) );
			
			StringBuffer formUserBean = new StringBuffer();
			formUserBean.append(join("userName", user.getUsUserName()) + COMMA_SPACE);
			formUserBean.append(join("userId", user.getUsUserID()) + COMMA_SPACE);
			formUserBean.append(join("country", "{" + join("country", "Asia/Calcutta") + "}" ) + COMMA_SPACE );
			
			if (CommonValidator.isEqual(user.getUsUsersType(), EPage.Student.name()))
				formUserBean.append(join("userType", "Consumer") + COMMA_SPACE);
			else
				formUserBean.append(join("userType", "Employee") + COMMA_SPACE);
			
			formUserBean.append(join("mediaList", "[{" + userMediaBean.toString() + "}]" ));
			
			String finalJson = join("formUser", "{" + formUserBean.toString() + "}" );
			
		    StringEntity entity = new StringEntity("{" + finalJson + "}");
			post.setEntity(entity);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse response = httpClient.execute(post);
			
			System.out.println(">>>>>>>>>>>>>>CloseableHttpResponse>>>>>>>>>>>>>>>>> " + response.getStatusLine().getStatusCode());
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		// Add User In New EduTel 2020 Ends
	}
}
