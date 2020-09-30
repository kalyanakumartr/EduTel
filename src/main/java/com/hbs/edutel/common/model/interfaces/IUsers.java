package com.hbs.edutel.common.model.interfaces;

import java.sql.Timestamp;
import java.util.Set;

import com.hbs.edutel.model.School;
import com.hbs.edutel.model.SerialKeyGenerate;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public interface IUsers extends IUploadImageOrDocuments
{
	public String	UsUserPwd		= "usUserPwd";

	public String	ExternalMessage	= "ExternalMessage";

	public IUsers getCreatedUser();

	public String getProfileBy();

	public String getSessionId();

	public String getUsAddress1();

	public String getUsAddress2();

	public String getUsAlternateMobileNo();

	public String getUsAlternativeEmail();

	public String getUsBoardName();

	public String getUsCity();

	public String getUsCompanyName();

	public String getUsCountry();

	public String getUsCreatedBy();

	public Timestamp getUsCreatedDate();

	public String getUsDateOfJoin();

	public String getUsDateOfTerminate();

	public String getUsDob();

	public String getUsEmail();

	public String getUsEmployeeId();

	public Set<IUserLog> getUserLogs();

	public Set<IUserRoles> getUserRoleses();

	public String getUsExperience();

	public String getUsFatherName();

	public String getUsFaxNo();

	public String getUsFrancheesArea();

	public String getUsGroupName();

	public String getUsLandMark();

	public String getUsLastName();

	public String getUsMobileNo();

	public String getUsModifiedBy();

	public Timestamp getUsModifiedDate();

	public String getUsPhoneNo();

	public String getUsPhoto();

	public String getUsPinCode();

	public String getUsProofId();

	public String getUsProofType();

	public String getUsQualification();

	public String getUsSchoolId();

	public String getUsSerialKey();

	public String getUsSex();

	public String getUsState();

	public String getUsUserID();

	public String getUsUserName();

	public String getUsUserPwd();

	public String getUsUserPwdOriginal();

	public String getUsUsersType();

	public boolean isAdmin();

	public boolean isEmployee();

	public boolean isFranchisee();

	public boolean isOnline();

	public boolean isStudent();

	public boolean isSuperAdmin();

	public boolean isUsStatus();

	public void setCreatedUser(IUsers createdUser);

	public void setProfileBy(String profileBy);

	public void setSessionId(String sessionId);

	public void setUsAddress1(String usAddress1);

	public void setUsAddress2(String usAddress2);

	public void setUsAlternateMobileNo(String usAlternateMobileNo);

	public void setUsAlternativeEmail(String usAlternativeEmail);

	public void setUsBoardName(String usBoardName);

	public void setUsCity(String usCity);

	public void setUsCompanyName(String usCompanyName);

	public void setUsCountry(String usCountry);

	public void setUsCreatedBy(String usCreatedBy);

	public void setUsCreatedDate(Timestamp usCreatedDate);

	public void setUsDateOfJoin(String usDateOfJoin);

	public void setUsDateOfTerminate(String usDateOfTerminate);

	public void setUsDob(String usDob);

	public void setUsEmail(String usEmail);

	public void setUsEmployeeId(String usEmployeeId);

	public void setUserLogs(Set<IUserLog> userLogs);

	public void setUserRoleses(Set<IUserRoles> userRoleses);

	public void setUsExperience(String usExperience);

	public void setUsFatherName(String usFatherName);

	public void setUsFaxNo(String usFaxNo);

	public void setUsFrancheesArea(String usFrancheesArea);

	public void setUsGroupName(String usGroupName);

	public void setUsLandMark(String usLandMark);

	public void setUsLastName(String usLastName);

	public void setUsMobileNo(String usMobileNo);

	public void setUsModifiedBy(String usModifiedBy);

	public void setUsModifiedDate(Timestamp usModifiedDate);

	public void setUsPhoneNo(String usPhoneNo);

	public void setUsPhoto(String usPhoto);

	public void setUsPinCode(String usPinCode);

	public void setUsProofId(String usProofId);

	public void setUsProofType(String usProofType);

	public void setUsQualification(String usQualification);

	public void setUsSchoolId(String usSchoolId);

	public void setUsSerialKey(String usSerialKey);

	public void setUsSex(String usSex);

	public void setUsState(String usState);

	public void setUsStatus(boolean usStatus);

	public void setUsUserID(String usUserID);

	public void setUsUserName(String usUserName);

	public void setUsUserPwd(String usUserPwd);

	public void setUsUserPwdOriginal(String usUserPwd);

	public void setUsUsersType(String usUsersType);

	public String getUsBatch();

	public void setUsBatch(String usBatch);

	public School getSchool();

	public void setSchool(School school);

	public SerialKeyGenerate getSerialKey();

	public void setSerialKey(SerialKeyGenerate serialKey);

}