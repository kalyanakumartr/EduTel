package com.hbs.edutel.util.common.consts;

import java.io.Serializable;

import com.hbs.edutel.util.common.ConstEnumUtil.EAudit;
import com.hbs.edutel.util.common.ConstEnumUtil.EDR;
import com.hbs.edutel.util.common.ConstEnumUtil.EFileServer;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.ELDAPAuth;
import com.hbs.edutel.util.common.ConstEnumUtil.ERole;
import com.hbs.edutel.util.common.factory.RolesFactory;
import com.hbs.edutel.util.common.property.factory.PropFactory;

public interface ConstInterface extends Serializable
{
	public String			COMMA_SPACE								= ", ";
	public String			WHERE_1_1								= " Where 1=1 ";
	public String			YES										= "yes";
	public String			IST										= "IST";
	public String			HYPEN									= " - ";
	public String			SPACE									= " ";
	public String			DECIMAL_FORMAT_0_00						= "#0.00";
	public String			DOT										= ".";
	public String			FROM									= " From ";
	public String			GET										= "get";
	public String			IS										= "is";
	public String			SLASH									= "/";
	public String			BACKSLASH								= "\\";
	public String			STATUS									= "status";
	public String			HASH									= "#";
	public String			DELETE									= " Delete ";
	public String			SELECT_DISTINCT							= " Select Distinct ";
	public String			UTF_8									= "UTF-8";
	public String			LASTLOGINID								= "lastLoginId";
	public String			OAUTH_TOKEN								= "OAUTH_TOKEN";
	public String			DATE_FORMAT_DD_MM_YYYY_HH_MM			= "dd-MM-yyyy HH:mm";
	public String			DATE_FORMAT_DD_MMM_YYYY					= "dd-MMM-yyyy";
	public String			DATE_FORMAT_DD_MMM_YYYY_HH_MM_AM_PM		= "dd-MMM-yyyy hh:mm a";
	public String			DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS_AM_PM	= "dd-MMM-yyyy hh:mm:ss a";
	public String			DATE_FORMAT_MM_DD_YYYY					= "MM-dd-yyyy";
	public String			DATE_FORMAT_MM_DD_YYYY_HH_MM			= "MM-dd-yyyy HH:mm";
	public String			DATE_FORMAT_MM_DD_YYYY_HHMM				= "MM-dd-yyyy HH-mm";
	public String			DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_AM_PM	= "MM-dd-yyyy hh:mm:ss a";
	public String			DATE_FORMAT_YYYY_MM_DD					= "yyyy-MM-dd";
	public String			DATE_FORMAT_YYYY_MM_DD_HH_MM			= "yyyy-MM-dd HH:mm";
	public String			DATE_FORMAT_YYYY_MM_DD_HH_MM_SS			= "yyyy-MM-dd hh:mm:ss";
	public String			DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24		= "yyyy-MM-dd HH:mm:ss";
	public String			DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS		= "yyyy-MM-dd hh:mm:ss.SSS";
	public String			DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_24	= "yyyy-MM-dd HH:mm:ss.SSS";
	public String			DATE_FORMAT_YYYY_MMM_DD					= "yyyy-MMM-dd";

	public final String		ADMIN_ROLE_NAME							= RolesFactory.getInstance().getRoleName(ERole.Admin);
	public final String		APPROVER_ROLE_NAME						= RolesFactory.getInstance().getRoleName(ERole.Approver);
	public final String		Access_Denied_EBluePrint				= PropFactory.getInstance().getProperty(EAudit.access_denied_eblueprint);
	public final String		Access_Denied_EBook						= PropFactory.getInstance().getProperty(EAudit.access_denied_ebook);
	public final String		Access_Denied_EKeyPoints				= PropFactory.getInstance().getProperty(EAudit.access_denied_ekeypoints);
	public final String		Access_Denied_MCQ_Exam					= PropFactory.getInstance().getProperty(EAudit.access_denied_mcq_exam);
	public final String		Access_Denied_MCQ_Practice_Exam			= PropFactory.getInstance().getProperty(EAudit.access_denied_mcq_practice_exam);
	public final String		Access_Denied_School					= PropFactory.getInstance().getProperty(EAudit.access_denied_school);
	public final String		Access_Denied_Serial_Key				= PropFactory.getInstance().getProperty(EAudit.access_denied_serial_key);
	public final String		Access_Denied_User						= PropFactory.getInstance().getProperty(EAudit.access_denied_user);
	public final String		Audit_Logging_Event_DAOImpl				= PropFactory.getInstance().getProperty(EAudit.audit_logging_event_daoimpl);
	public final String		Audit_Logging_Event_DataEntry			= PropFactory.getInstance().getProperty(EAudit.audit_logging_event_dataentry);
	public final String		Audit_Logging_Event_EndEntry			= PropFactory.getInstance().getProperty(EAudit.audit_logging_event_endentry);
	public final String		Audit_Logging_Event_Login				= PropFactory.getInstance().getProperty(EAudit.audit_logging_event_login);
	public final String		Audit_Logging_Event_Logout				= PropFactory.getInstance().getProperty(EAudit.audit_logging_event_logout);
	public final String		Audit_Logging_Event_Report				= PropFactory.getInstance().getProperty(EAudit.audit_logging_event_report);
	public final String		Audit_Logging_Event_Session				= PropFactory.getInstance().getProperty(EAudit.audit_logging_event_session);
	public final String		Audit_Logging_Event_StartEntry			= PropFactory.getInstance().getProperty(EAudit.audit_logging_event_startentry);
	public final String		Authenticate_Serial_Key					= PropFactory.getInstance().getProperty(EAudit.authenticate_serial_key);
	public final String		Create_College							= PropFactory.getInstance().getProperty(EAudit.create_college);
	public final String		Create_EBluePrint						= PropFactory.getInstance().getProperty(EAudit.create_eblueprint);
	public final String		Create_EBook							= PropFactory.getInstance().getProperty(EAudit.create_ebook);
	public final String		Create_EKeyPoints						= PropFactory.getInstance().getProperty(EAudit.create_ekeypoints);
	public final String		Create_MCQ_Exam							= PropFactory.getInstance().getProperty(EAudit.create_mcq_exam);
	public final String		Create_MCQ_Practice_Exam				= PropFactory.getInstance().getProperty(EAudit.create_mcq_practice_exam);
	public final String		Create_Query							= PropFactory.getInstance().getProperty(EAudit.create_query);
	public final String		Create_School							= PropFactory.getInstance().getProperty(EAudit.create_school);
	public final String		Create_Serial_Key						= PropFactory.getInstance().getProperty(EAudit.create_serial_key);
	public final String		Create_User								= PropFactory.getInstance().getProperty(EAudit.create_user);
	public final String		Delete_College							= PropFactory.getInstance().getProperty(EAudit.delete_college);
	public final String		Delete_EBluePrint						= PropFactory.getInstance().getProperty(EAudit.delete_eblueprint);
	public final String		Delete_EBook							= PropFactory.getInstance().getProperty(EAudit.delete_ebook);
	public final String		Delete_EKeyPoints						= PropFactory.getInstance().getProperty(EAudit.delete_ekeypoints);
	public final String		Delete_MCQ_Exam							= PropFactory.getInstance().getProperty(EAudit.delete_mcq_exam);
	public final String		Delete_MCQ_Practice_Exam				= PropFactory.getInstance().getProperty(EAudit.delete_mcq_practice_exam);
	public final String		Delete_Query							= PropFactory.getInstance().getProperty(EAudit.delete_query);
	public final String		Delete_School							= PropFactory.getInstance().getProperty(EAudit.delete_school);
	public final String		Delete_Serial_Key						= PropFactory.getInstance().getProperty(EAudit.delete_serial_key);
	public final String		Delete_User								= PropFactory.getInstance().getProperty(EAudit.delete_user);
	public final String		Download_EBluePrint						= PropFactory.getInstance().getProperty(EAudit.download_eblueprint);
	public final String		Download_EBook							= PropFactory.getInstance().getProperty(EAudit.download_ebook);
	public final String		Download_EKeyPoints						= PropFactory.getInstance().getProperty(EAudit.download_ekeypoints);
	public final String		Download_TestSeries_Exam_Answer			= PropFactory.getInstance().getProperty(EAudit.download_testseries_exam_answer);
	public final String		FileServer_Attachment_Path				= PropFactory.getInstance().getProperty(EFileServer.server_attachment_path);
	public final String		FileServer_EInvoice_Path				= PropFactory.getInstance().getProperty(EFileServer.server_einvoice_path);
	public final String		FileServer_Intermediate_Server_Path		= PropFactory.getInstance().getProperty(EFileServer.fileserver_intermediate_server_path);
	public final String		FileServer_Intermediate_Virtual_Path	= PropFactory.getInstance().getProperty(EFileServer.fileserver_intermediate_virtual_path);
	public final String		FileServer_MailRoom_Path				= PropFactory.getInstance().getProperty(EFileServer.server_mailroom_path);
	public final String		LDAPCredentials							= PropFactory.getInstance().getProperty(ELDAPAuth.ldap_security_credentials);
	public final String		LDAPPrincipal							= PropFactory.getInstance().getProperty(ELDAPAuth.ldap_security_principal);
	public final String		LDAPSearch								= PropFactory.getInstance().getProperty(ELDAPAuth.ldap_search_object);
	public final String		LDAPTimeout								= PropFactory.getInstance().getProperty(ELDAPAuth.ldap_connection_timeout);
	public final String		LDAPURL									= PropFactory.getInstance().getProperty(ELDAPAuth.ldap_host_url);
	public final String		Perform_MCQ_Exam						= PropFactory.getInstance().getProperty(EAudit.perform_mcq_exam);
	public final String		Perform_MCQ_Practice_Exam				= PropFactory.getInstance().getProperty(EAudit.perform_mcq_practice_exam);
	public final String		Perform_TestSeries_Exam					= PropFactory.getInstance().getProperty(EAudit.perform_testseries_exam);
	public final String		Project_Name							= PropFactory.getInstance().getProperty(EGeneral.Project_Name);
	public final String		Reply_Query								= PropFactory.getInstance().getProperty(EAudit.reply_query);
	public final String		Result_MCQ_Exam							= PropFactory.getInstance().getProperty(EAudit.result_mcq_exam);
	public final String		Result_MCQ_Practice_Exam				= PropFactory.getInstance().getProperty(EAudit.result_mcq_practice_exam);
	public final String		Result_TestSeries_Exam					= PropFactory.getInstance().getProperty(EAudit.result_testseries_exam);
	public final String		SUPER_ADMIN_ROLE_NAME					= RolesFactory.getInstance().getRoleName(ERole.SuperAdminRole);
	public final String		Search_College							= PropFactory.getInstance().getProperty(EAudit.search_college);
	public final String		Search_EBluePrint						= PropFactory.getInstance().getProperty(EAudit.search_eblueprint);
	public final String		Search_EBook							= PropFactory.getInstance().getProperty(EAudit.search_ebook);
	public final String		Search_EKeyPoints						= PropFactory.getInstance().getProperty(EAudit.search_ekeypoints);
	public final String		Search_MCQ_Exam							= PropFactory.getInstance().getProperty(EAudit.search_mcq_exam);
	public final String		Search_MCQ_Practice_Exam				= PropFactory.getInstance().getProperty(EAudit.search_mcq_practice_exam);
	public final String		Search_Query							= PropFactory.getInstance().getProperty(EAudit.search_query);
	public final String		Search_School							= PropFactory.getInstance().getProperty(EAudit.search_school);
	public final String		Search_Serial_Key						= PropFactory.getInstance().getProperty(EAudit.search_serial_key);
	public final String		Search_TestSeries_Exam					= PropFactory.getInstance().getProperty(EAudit.search_testseries_exam);
	public final String		Search_User								= PropFactory.getInstance().getProperty(EAudit.search_user);
	public final String		Update_College							= PropFactory.getInstance().getProperty(EAudit.update_college);
	public final String		Update_EBluePrint						= PropFactory.getInstance().getProperty(EAudit.update_eblueprint);
	public final String		Update_EBook							= PropFactory.getInstance().getProperty(EAudit.update_ebook);
	public final String		Update_EKeyPoints						= PropFactory.getInstance().getProperty(EAudit.update_ekeypoints);
	public final String		Update_MCQ_Exam							= PropFactory.getInstance().getProperty(EAudit.update_mcq_exam);
	public final String		Update_MCQ_Practice_Exam				= PropFactory.getInstance().getProperty(EAudit.update_mcq_practice_exam);
	public final String		Update_School							= PropFactory.getInstance().getProperty(EAudit.update_school);
	public final String		Update_Serial_Key						= PropFactory.getInstance().getProperty(EAudit.update_serial_key);
	public final String		Update_User								= PropFactory.getInstance().getProperty(EAudit.update_user);
	public final String		Users_Reminder_Queries_Questions		= PropFactory.getInstance().getProperty(EGeneral.users_reminder_queries_questions);
	public final String		project_authentication_employee			= PropFactory.getInstance().getProperty(EGeneral.project_authentication_employee);
	public final String		project_authentication_franchisee		= PropFactory.getInstance().getProperty(EGeneral.project_authentication_franchisee);
	public final String		project_authentication_student			= PropFactory.getInstance().getProperty(EGeneral.project_authentication_student);
	public final boolean	isDRSiteEnabled							= PropFactory.getInstance().getProperty(EDR.DR_Site).equalsIgnoreCase("YES");
	public final String		Access_Denied_College					= PropFactory.getInstance().getProperty(EAudit.access_denied_college);
	public final String		Message_Tracking_Class					= PropFactory.getInstance().getProperty(EGeneral.Message_Tracking_Class);
	public final String		Download_MCA_Exam_Questions				= PropFactory.getInstance().getProperty(EAudit.downolad_mca_exam_questions);

}
