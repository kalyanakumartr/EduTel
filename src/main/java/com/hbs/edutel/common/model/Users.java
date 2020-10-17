package com.hbs.edutel.common.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.abstracts.AUsers;
import com.hbs.edutel.common.model.interfaces.IUserLog;
import com.hbs.edutel.common.model.interfaces.IUserRoles;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.School;
import com.hbs.edutel.model.SerialKeyGenerate;
import com.hbs.edutel.util.common.ConstEnumUtil.ERole;

public class Users extends AUsers
{
	private static final long		serialVersionUID	= -4884855208684700753L;
	protected IUsers				createdUser;
	protected String				profileBy;
	protected String				sessionId;
	protected Timestamp				uploadFileDate;
	protected String				uploadFileFolderURL;
	protected Timestamp				uploadFileLastModifiedDate;
	protected String				uploadFileName;
	protected String				uploadFileNamePrimaryId;
	protected String				uploadFileScreenShot;
	protected long					uploadFileSize;
	protected String				usAddress1;
	protected String				usAddress2;
	protected String				usAlternateMobileNo;
	protected String				usAlternativeEmail;
	protected String				usBoardName;
	protected String				usCity;
	protected String				usCompanyName;
	protected String				usCountry;
	protected String				usCreatedBy;
	protected Timestamp				usCreatedDate;
	protected String				usDateOfJoin;
	protected String				usDateOfTerminate;
	protected String				usDob;
	protected String				usEmail;
	protected String				usEmployeeId;
	protected Set<IUserLog>			userLogs			= new HashSet<IUserLog>(0);
	protected Set<IUserRoles>		userRoleses			= new HashSet<IUserRoles>(0);
	protected String				usExperience;
	protected String				usFatherName;
	protected String				usFaxNo;
	protected String				usFrancheesArea;
	protected String				usGroupName;
	protected String				usLandMark;
	protected String				usLastName;
	protected String				usMobileNo;
	protected String				usModifiedBy;
	protected Timestamp				usModifiedDate;
	protected String				usPhoneNo;
	protected String				usPinCode;
	protected String				usProofId;
	protected String				usProofType;
	protected String				usQualification;
	protected String				usSchoolId;
	protected String				usSchoolName;
	protected String				usSerialKey;
	protected String				usSex;
	protected String                usStandard;
	protected String				usState;
	protected boolean				usStatus			= true;
	protected String				usUserID;
	protected String				usUserName;
	protected String				usUserPwd;
	protected String				usUsersType;
	protected String				usBatch;
	protected String				usUserPwdOriginal;
	protected Set<StudentsMarks>	studMarks			= new HashSet<StudentsMarks>(0);
	protected School				school;
	protected SerialKeyGenerate		serialKey;

	public Users()
	{
		super();
	}

	public Users(String usEmployeeId)
	{
		super();
		this.usEmployeeId = usEmployeeId;
	}

	
	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	
	public String getProfileBy()
	{
		return profileBy;
	}

	
	public String getSessionId()
	{
		return sessionId;
	}

	
	public Timestamp getUploadFileDate()
	{
		return uploadFileDate;
	}

	
	public String getUploadFileFolderURL()
	{
		return uploadFileFolderURL;
	}

	
	public Timestamp getUploadFileLastModifiedDate()
	{
		return uploadFileLastModifiedDate;
	}

	
	public String getUploadFileName()
	{
		return uploadFileName;
	}

	
	public String getUploadFileNamePrimaryId()
	{
		return usEmployeeId;
	}

	
	public String getUploadFileScreenShot()
	{
		return uploadFileScreenShot;
	}

	
	public long getUploadFileSize()
	{
		return uploadFileSize;
	}

	
	public String getUsAddress1()
	{
		return usAddress1;
	}

	
	public String getUsAddress2()
	{
		return usAddress2;
	}

	
	public String getUsAlternateMobileNo()
	{
		return usAlternateMobileNo;
	}

	
	public String getUsAlternativeEmail()
	{
		return usAlternativeEmail;
	}

	
	public String getUsBoardName()
	{
		return usBoardName;
	}

	
	public String getUsCity()
	{
		return usCity;
	}

	
	public String getUsCompanyName()
	{
		return usCompanyName;
	}

	
	public String getUsCountry()
	{
		return usCountry;
	}

	
	public String getUsCreatedBy()
	{
		return usCreatedBy;
	}

	
	public Timestamp getUsCreatedDate()
	{
		return usCreatedDate;
	}

	
	public String getUsDateOfJoin()
	{
		return usDateOfJoin;
	}

	
	public String getUsDateOfTerminate()
	{
		return usDateOfTerminate;
	}

	
	public String getUsDob()
	{
		return usDob;
	}

	
	public String getUsEmail()
	{
		return usEmail;
	}

	
	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	
	public Set<IUserLog> getUserLogs()
	{
		return userLogs;
	}

	
	public Set<IUserRoles> getUserRoleses()
	{
		return userRoleses;
	}

	
	public String getUsExperience()
	{
		return usExperience;
	}

	
	public String getUsFatherName()
	{
		return usFatherName;
	}

	
	public String getUsFaxNo()
	{
		return usFaxNo;
	}

	
	public String getUsFrancheesArea()
	{
		return usFrancheesArea;
	}

	
	public String getUsGroupName()
	{
		return usGroupName;
	}

	
	public String getUsLandMark()
	{
		return usLandMark;
	}

	
	public String getUsLastName()
	{
		return usLastName;
	}

	
	public String getUsMobileNo()
	{
		return usMobileNo;
	}

	
	public String getUsModifiedBy()
	{
		return usModifiedBy;
	}

	
	public Timestamp getUsModifiedDate()
	{
		return usModifiedDate;
	}

	
	public String getUsPhoneNo()
	{
		return usPhoneNo;
	}

	
	public String getUsPinCode()
	{
		return usPinCode;
	}

	
	public String getUsProofId()
	{
		return usProofId;
	}

	
	public String getUsProofType()
	{
		return usProofType;
	}

	
	public String getUsQualification()
	{
		return usQualification;
	}

	
	public String getUsSchoolId()
	{
		return usSchoolId;
	}

	
	public String getUsSerialKey()
	{
		return usSerialKey;
	}

	
	public String getUsSex()
	{
		return usSex;
	}

	
	public String getUsState()
	{
		return usState;
	}

	
	public String getUsUserID()
	{
		return usUserID;
	}

	
	public String getUsUserName()
	{
		return usUserName;
	}

	
	public String getUsUserPwd()
	{
		return usUserPwd;
	}

	
	public String getUsUsersType()
	{
		return usUsersType;
	}

	
	public boolean isAdmin()
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles usrRole : userRoleses)
			{
				if (CommonValidator.isEqual(usrRole.getRoles().getRlRoleId(), ERole.Admin.name()))
					return true;
			}
		}
		return false;
	}

	
	public boolean isEmployee()
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles usrRole : userRoleses)
			{
				if (CommonValidator.isEqual(usrRole.getRoles().getRlRoleId(), ERole.Employee.name()))
					return true;
			}
		}
		return false;
	}

	
	public boolean isFranchisee()
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles usrRole : userRoleses)
			{
				return CommonValidator.isEqual(usrRole.getRoles().getRlRoleId(), ERole.Franchisee.name());
			}
		}
		return false;
	}

	
	public boolean isOnline()
	{
		if (CommonValidator.isSetFirstNotEmpty(userLogs))
		{
			IUserLog iUL = userLogs.iterator().next();
			return CommonValidator.isNotNullNotEmpty(iUL.getUlUserLogoutTime()) == false;
		}
		return false;
	}

	
	public boolean isStudent()
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles usrRole : userRoleses)
			{
				return CommonValidator.isEqual(usrRole.getRoles().getRlRoleId(), ERole.Student.name());
			}
		}
		return false;
	}

	
	public boolean isSuperAdmin()
	{
		if (CommonValidator.isSetFirstNotEmpty(userRoleses))
		{
			for (IUserRoles usrRole : userRoleses)
			{
				if (CommonValidator.isEqual(usrRole.getRoles().getRlRoleId(), ERole.SuperAdminRole.name()))
					return true;
			}
		}
		return false;
	}

	
	public boolean isUsStatus()
	{
		return usStatus;
	}

	
	public void setCreatedUser(IUsers createdUser)
	{
		this.createdUser = createdUser;
	}

	
	public void setProfileBy(String profileBy)
	{
		this.profileBy = profileBy;
	}

	
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	
	public void setUploadFileDate(Timestamp uploadFileDate)
	{
		this.uploadFileDate = uploadFileDate;
	}

	
	public void setUploadFileFolderURL(String uploadFileFolderURL)
	{
		this.uploadFileFolderURL = uploadFileFolderURL;
	}

	
	public void setUploadFileLastModifiedDate(Timestamp uploadFileLastModifiedDate)
	{
		this.uploadFileLastModifiedDate = uploadFileLastModifiedDate;
	}

	
	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}

	public void setUploadFileNamePrimaryId(String usEmployeeId)
	{
		this.uploadFileNamePrimaryId = usEmployeeId;
	}

	
	public void setUploadFileScreenShot(String uploadFileScreenShot)
	{
		this.uploadFileScreenShot = uploadFileScreenShot;
	}

	
	public void setUploadFileSize(long uploadFileSize)
	{
		this.uploadFileSize = uploadFileSize;
	}

	
	public void setUsAddress1(String usAddress1)
	{
		this.usAddress1 = usAddress1;
	}

	
	public void setUsAddress2(String usAddress2)
	{
		this.usAddress2 = usAddress2;
	}

	
	public void setUsAlternateMobileNo(String usAlternateMobileNo)
	{
		this.usAlternateMobileNo = usAlternateMobileNo;
	}

	
	public void setUsAlternativeEmail(String usAlternativeEmail)
	{
		this.usAlternativeEmail = usAlternativeEmail;
	}

	
	public void setUsBoardName(String usBoardName)
	{
		this.usBoardName = usBoardName;
	}

	
	public void setUsCity(String usCity)
	{
		this.usCity = usCity;
	}

	
	public void setUsCompanyName(String usCompanyName)
	{
		this.usCompanyName = usCompanyName;
	}

	
	public void setUsCountry(String usCountry)
	{
		this.usCountry = usCountry;
	}

	
	public void setUsCreatedBy(String usCreatedBy)
	{
		this.usCreatedBy = usCreatedBy;
	}

	
	public void setUsCreatedDate(Timestamp usCreatedDate)
	{
		this.usCreatedDate = usCreatedDate;
	}

	
	public void setUsDateOfJoin(String usDateOfJoin)
	{
		this.usDateOfJoin = usDateOfJoin;
	}

	
	public void setUsDateOfTerminate(String usDateOfTerminate)
	{
		this.usDateOfTerminate = usDateOfTerminate;
	}

	
	public void setUsDob(String usDob)
	{
		this.usDob = usDob;
	}

	
	public void setUsEmail(String usEmail)
	{
		this.usEmail = usEmail;
	}

	
	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	
	public void setUserLogs(Set<IUserLog> userLogs)
	{
		this.userLogs = userLogs;
	}

	
	public void setUserRoleses(Set<IUserRoles> userRoleses)
	{
		this.userRoleses = userRoleses;
	}

	
	public void setUsExperience(String usExperience)
	{
		this.usExperience = usExperience;
	}

	
	public void setUsFatherName(String usFatherName)
	{
		this.usFatherName = usFatherName;
	}

	
	public void setUsFaxNo(String usFaxNo)
	{
		this.usFaxNo = usFaxNo;
	}

	
	public void setUsFrancheesArea(String usFrancheesArea)
	{
		this.usFrancheesArea = usFrancheesArea;
	}

	
	public void setUsGroupName(String usGroupName)
	{
		this.usGroupName = usGroupName;
	}

	
	public void setUsLandMark(String usLandMark)
	{
		this.usLandMark = usLandMark;
	}

	
	public void setUsLastName(String usLastName)
	{
		this.usLastName = usLastName;
	}

	
	public void setUsMobileNo(String usMobileNo)
	{
		this.usMobileNo = usMobileNo;
	}

	
	public void setUsModifiedBy(String usModifiedBy)
	{
		this.usModifiedBy = usModifiedBy;
	}

	
	public void setUsModifiedDate(Timestamp usModifiedDate)
	{
		this.usModifiedDate = usModifiedDate;
	}

	
	public void setUsPhoneNo(String usPhoneNo)
	{
		this.usPhoneNo = usPhoneNo;
	}

	
	public void setUsPinCode(String usPinCode)
	{
		this.usPinCode = usPinCode;
	}

	
	public void setUsProofId(String usProofId)
	{
		this.usProofId = usProofId;
	}

	
	public void setUsProofType(String usProofType)
	{
		this.usProofType = usProofType;
	}

	
	public void setUsQualification(String usQualification)
	{
		this.usQualification = usQualification;
	}

	
	public void setUsSchoolId(String usSchoolId)
	{
		this.usSchoolId = usSchoolId;
	}

	
	public void setUsSerialKey(String usSerialKey)
	{
		this.usSerialKey = usSerialKey;
	}

	
	public void setUsSex(String usSex)
	{
		this.usSex = usSex;
	}

	
	public void setUsState(String usState)
	{
		this.usState = usState;
	}

	
	public void setUsStatus(boolean usStatus)
	{
		this.usStatus = usStatus;
	}

	
	public void setUsUserID(String usUserID)
	{
		this.usUserID = usUserID;
	}

	
	public void setUsUserName(String usUserName)
	{
		this.usUserName = usUserName;
	}

	
	public void setUsUserPwd(String usUserPwd)
	{
		this.usUserPwd = usUserPwd;
	}

	
	public void setUsUsersType(String usUsersType)
	{
		this.usUsersType = usUsersType;
	}

	
	public String getUsBatch()
	{
		return usBatch;
	}

	
	public void setUsBatch(String usBatch)
	{
		this.usBatch = usBatch;
	}

	public Set<StudentsMarks> getStudMarks()
	{
		return studMarks;
	}

	public void setStudMarks(Set<StudentsMarks> studMarks)
	{
		this.studMarks = studMarks;
	}

	
	public String getUsUserPwdOriginal()
	{
		return usUserPwdOriginal;
	}

	
	public void setUsUserPwdOriginal(String usUserPwd)
	{
		this.usUserPwdOriginal = usUserPwd;
	}

	
	public School getSchool()
	{
		return school;
	}

	
	public void setSchool(School school)
	{
		this.school = school;
	}

	
	public SerialKeyGenerate getSerialKey()
	{
		return serialKey;
	}

	
	public void setSerialKey(SerialKeyGenerate serialKey)
	{
		this.serialKey = serialKey;
	}

	public String getUsSchoolName() {
		return usSchoolName;
	}

	public void setUsSchoolName(String usSchoolName) {
		this.usSchoolName = usSchoolName;
	}

	public String getUsStandard() {
		return usStandard;
	}

	public void setUsStandard(String usStandard) {
		this.usStandard = usStandard;
	}

}
