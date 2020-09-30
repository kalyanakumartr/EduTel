package com.hbs.edutel.util.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.util.CommonUtil;

public class ConstEnumUtil
{
	public enum Delim implements EnumInterface
	{
		End("}$"), Start("${");

		private final String	eKey;

		private Delim(String eKey)
		{
			this.eKey = eKey;
		}

		public String getKey()
		{
			return eKey;
		}

		public int index(String message)
		{
			return message.indexOf(this.eKey);
		}

		public int length()
		{
			return eKey.length();
		}

	}

	public enum EAudit implements EnumInterface
	{
		access_denied_college, access_denied_eblueprint, access_denied_ebook, access_denied_ekeypoints, access_denied_mcq_exam, access_denied_mcq_practice_exam, access_denied_school, access_denied_serial_key, access_denied_user, audit_logging_event_daoimpl, audit_logging_event_dataentry, audit_logging_event_endentry, audit_logging_event_login, audit_logging_event_logout, audit_logging_event_report, audit_logging_event_session, audit_logging_event_startentry, authenticate_serial_key, create_college, create_eblueprint, create_ebook, create_ekeypoints, create_mcq_exam, create_mcq_practice_exam, create_query, create_school, create_serial_key, create_user, delete_college, delete_eblueprint, delete_ebook, delete_ekeypoints, delete_mcq_exam, delete_mcq_practice_exam, delete_query, delete_school, delete_serial_key, delete_user, download_eblueprint, download_ebook, download_ekeypoints, download_testseries_exam_answer, perform_mcq_exam, perform_mcq_practice_exam, perform_testseries_exam, reply_query, result_mcq_exam, result_mcq_practice_exam, result_testseries_exam, search_college, search_eblueprint, search_ebook, search_ekeypoints, search_mcq_exam, search_mcq_practice_exam, search_query, search_school, search_serial_key, search_testseries_exam, search_user, update_college, update_eblueprint, update_ebook, update_ekeypoints, update_mcq_exam, update_mcq_practice_exam, update_school, update_serial_key, update_user, downolad_mca_exam_questions;
	}

	public enum EAuthType implements EnumInterface
	{
		DB, LDAP
	}

	public enum EChapter implements EnumInterface
	{
		Chapter_All(""), Chapter_I("1"), Chapter_II("2"), Chapter_III("3"), Chapter_IV("4"), Chapter_V("5"), Chapter_VI("6"), Chapter_VII("7"), Chapter_VIII("8"),Chapter_IX("9"), Chapter_X("10"), Chapter_XI(
				"11"), Chapter_XII("12"), Chapter_XIII("13"), Chapter_XIV("14"), Chapter_XV("15"), Chapter_XVI("16"), Chapter_XVII("17"), Chapter_XVIII("18"),Chapter_XIX("19"), Chapter_XX("20"), Chapter_XXI(
				"21"), Chapter_XXII("22"), Chapter_XXIII("23"), Chapter_XXIV("24"), Chapter_XXV("25");
		public static List<LabelValueBean> getChaptersComboList()
		{
			List<LabelValueBean> chapList = new ArrayList<LabelValueBean>(0);

			for (EChapter eChap : EChapter.values())
			{
				chapList.add(new LabelValueBean(eChap.name().replaceAll("_", " "), eChap.getChapter()));
			}
			return chapList;
		}

		public static EChapter getEChapter(String chapCode)
		{
			for (EChapter eChap : EChapter.values())
			{
				if (CommonValidator.isEqual(chapCode, eChap.getChapter()))
					return eChap;
			}
			return EChapter.Chapter_All;
		}

		private final String	eChapter;

		private EChapter(String eChapter)
		{
			this.eChapter = eChapter;
		}

		public String getChapter()
		{
			return eChapter;
		}

	}

	public enum EDR implements EnumInterface
	{
		DR_Site
	}

	public enum EEmail implements EnumInterface
	{
		mail_service_bcc, mail_service_category, mail_service_cc, mail_service_event, mail_service_process, mail_service_to, mail_service_url
	}

	public enum EExamType implements EnumInterface
	{
		MCQ, TestSeries
	}

	public enum EFileServer implements EnumInterface
	{
		fileserver_intermediate_server_path, fileserver_intermediate_virtual_path, server_attachment_path, server_einvoice_path, server_mailroom_path
	}

	public enum EFormStatus implements EnumInterface
	{
		Ordered, Pending
	}

	public enum EFormType implements EnumInterface
	{
		CollegeForm, EntranceForm
	}

	public enum EGeneral implements EnumInterface
	{
		approver_role_name, Authentication_Type, delegation_original_user, delegation_switch_process, EduTel_Mail, EduTel_Mobile, EduTel_Phone, MCQ_Duration_Per_Question, project_authentication_employee, project_authentication_franchisee, project_authentication_student, Project_Logo, Project_Name, Project_Title, session_cookie_domain, User_Login_Column_Name, User_Selection_Column_Name, users_reminder_queries_questions, Message_Tracking_Class
	}

	public enum EGenerate implements EnumInterface
	{
		College("CLG"), EBluePrint("EBP"), EBook("EBK"), EKeyPoints("EKP"), Employee("EMP"), Exam("EXM"), ExamPractise("EXMP"), ExamQuestion("QSN"), Franchisee("FRH"), Message("MSG"), School("SCH"), Student(
				"STD");

		private final String	eEmpCode;

		private EGenerate(String eEmpCode)
		{
			this.eEmpCode = eEmpCode;
		}

		public String getPrimaryId()
		{
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
			}
			return eEmpCode + CommonUtil.getDateInFormat(new Date(), "yyMMddhhmmssSSS");
		}
	}

	public enum EKeyGen implements EnumInterface
	{
		DeActivated("De-Activated"), Not_Sold("Not Sold"), Sold("Sold"), Validated("Validated");

		public static List<LabelValueBean> getKeyStatusComboList()
		{
			List<LabelValueBean> keyList = new ArrayList<LabelValueBean>(0);

			for (EKeyGen eKey : EKeyGen.values())
			{
				keyList.add(new LabelValueBean(eKey.getStatus(), eKey.getStatus()));
			}
			return keyList;
		}

		private final String	eKeyGen;

		private EKeyGen(String eKeyGen)
		{
			this.eKeyGen = eKeyGen;
		}

		public String getStatus()
		{
			return eKeyGen;
		}

	}

	public enum ELDAPAuth implements EnumInterface
	{
		ldap_base, ldap_connection_timeout, ldap_host_url, ldap_referral, ldap_search_object, ldap_security_credentials, ldap_security_principal,
	}

	public enum EMessage implements EnumInterface
	{
		Abort, Failed, InComplete, Pending, Processing, Ready, Retry, Send;
	}

	public enum EMessageTemplate implements EnumInterface
	{
		UserIdPassword_Email, UserIdPassword_SMS, UserRegistrationReceipt_SMS, UserRegistrationReceipt_Email, UserRegistration_Email_Admin, UserRegistrationWelcome_Email, TestSeriesAnswer_Email_Admin;
	}

	public enum EMessageType implements EnumInterface
	{
		Email, SMS;
	}

	public enum EPage implements EnumInterface
	{
		AccessDenied, Admin, Approval, AttachmentImage, AttachmentPages, ChangePassword, DashBoard, Employee, Exception, Failure, Franchisee, Input, JsonOutput, KeyGen, Library, PractiseExam, RemindQuestion, SearchResults, SerialNo, SessionExpired, Student, Success, View, Global, Advance, Alert, Marquee;
	}

	public enum EProject implements EnumInterface
	{
		EduTel
	}

	public enum EPromo implements EnumInterface
	{
		Promo_Bronze("Bronze"), Promo_Diamond("Diamond"), Promo_Gold("Gold"), Promo_Platinum("Platinum"), Promo_Silver("Silver"), Promo_Titanium("Titanium");

		public static List<LabelValueBean> getEPromoList()
		{
			List<LabelValueBean> lvbList = new ArrayList<LabelValueBean>(0);
			EPromo[] ePromos = EPromo.values();
			for (EPromo ePro : ePromos)
			{
				lvbList.add(new LabelValueBean(ePro.name().toUpperCase(), ePro.code()));
			}
			return lvbList;
		}

		private final String	ePromo;

		private EPromo(String ePromo)
		{
			this.ePromo = ePromo;
		}

		public String code()
		{
			return ePromo;
		}
	}

	public enum EQryCondition implements EnumInterface
	{
		EMPTY(""), EQUAL(" = "), LIKE(" Like "), NOT_EQUAL(" <> ");

		private final String	eQryCond;

		private EQryCondition(String eSessAttr)
		{
			this.eQryCond = eSessAttr;
		}

		public String getCond()
		{
			return eQryCond;
		}

	}

	public enum ERating implements EnumInterface
	{
		Better, Excellant, Fair, Good, Very_Good;

		public static ERating getRating(int ordinal)
		{
			ERating[] ratingArr = ERating.values();
			for (ERating eRating : ratingArr)
			{
				if (eRating.ordinal() == ordinal)
					return eRating;
			}
			return ERating.Fair;
		}
	}

	public enum ERole implements EnumInterface
	{
		Admin, Approver, Dummy, Employee, EUpload, Franchisee, Marketing, Student, SuperAdminRole;
	}

	public enum EScreenShot implements EnumInterface
	{
		DOC("docxIcon.png"), DOCX("docxIcon.png"), PDF("pdfIcon.png");

		private final String	image;

		private EScreenShot(String ext)
		{
			this.image = ext;
		}

		public String getImage()
		{
			return image;
		}

	}

	public enum ESession implements EnumInterface
	{
		DelegationAlias("delegationAlias"), MaMenuHTML("maMenuHTML"), OriginalEmployeeId("originalEmployeeId"), OriginalUserId("originalUserId"), OriginalUserName("originalUserName"), SelectDelUserId(
				"selectDelUserId"), SelectDelUserName("selectDelUserName"), UserImagePath("userImagePath"), UserJoinedDate("userJoinedDate"), UserObject("userObj"), UserPassword("userPassword");

		private final String	eSessAttr;

		private ESession(String eSessAttr)
		{
			this.eSessAttr = eSessAttr;
		}

		public String getAttribute()
		{
			return eSessAttr;
		}

	}

	public enum ESMS implements EnumInterface
	{
		Accept_Language("Accept-Language"), Message("message"), Post("POST"), Sender("sender"), User_Agent("User-Agent"), WebsiteURL("WebsiteURL"), UserName("username"), Password("password"), ReceiptantMobile(
				"to");

		private final String	eSMS;

		private ESMS(String eSMS)
		{
			this.eSMS = eSMS;
		}

		public String getValue()
		{
			return eSMS;
		}
	}

	public enum ESubjects implements EnumInterface
	{
		Botany, Business_Mathematics, Chemistry, Computer_Science, Mathematics, Physics, Zoology
	}

	public enum ESync implements EnumInterface
	{
		Image_In_FileServer_DR("IM002"), Image_In_FileServer_DRAndOriginal("IM004"), Image_In_FileServer_Original("IM001"), Image_In_FileServer_OriginalAndDR("IM003"), Image_Sync_Failure("IM000");

		private final String	eSyncCode;

		private ESync(String syncCode)
		{
			this.eSyncCode = syncCode;
		}

		public String getCode()
		{
			return eSyncCode;
		}
	}

	public enum EUser implements EnumInterface
	{
		EmailAdmin, SMSAdmin, SuperAdmin
	}

	public enum EWrap implements EnumInterface
	{
		Brace("()"), Percent("%"), Quote("'"), QuotePercent(""), QuotePercentLT("'%"), QuotePercentRT("%'");

		private final String	eWrap;

		private EWrap(String eWrap)
		{
			this.eWrap = eWrap;
		}

		public String enclose(Object data)
		{
			if (data != null)
			{
				String encData = String.valueOf(data);
				if (eWrap.equals(""))
					return Quote.eWrap + Percent.eWrap + encData.trim() + Percent.eWrap + Quote.eWrap;
				else if (eWrap.equals(Brace.eWrap))
					return "(" + encData.trim() + ")";
				else if (eWrap.equals(QuotePercentRT.eWrap))
					return Quote.eWrap + encData.trim() + QuotePercentRT.eWrap;
				else if (eWrap.equals(QuotePercentLT.eWrap))
					return QuotePercentLT.eWrap + encData.trim() + Quote.eWrap;
				else
					return eWrap + encData.trim() + eWrap;
			}
			else if (eWrap.equals(QuotePercent.eWrap))
			{
				return Quote.eWrap + Percent.eWrap + Percent.eWrap + Quote.eWrap;
			}

			return "";
		}

		public String enclose(Object[] dataArr)
		{
			String dataQt = "";
			if (CommonValidator.isNotNullNotEmpty(dataArr))
			{
				if (dataArr[0] instanceof String || dataArr[0] instanceof Integer || dataArr[0] instanceof Long || dataArr[0] instanceof Float || dataArr[0] instanceof Double
						|| dataArr[0] instanceof Boolean)
				{
					for (Object datum : dataArr)
					{
						dataQt += enclose(datum) + ", ";
					}
					if (dataQt.indexOf(", ") > 0)
						dataQt = dataQt.substring(0, dataQt.lastIndexOf(", "));
				}
			}
			return dataQt;
		}
	}
}
