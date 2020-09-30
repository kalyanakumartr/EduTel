package com.hbs.edutel.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlineExamQuestionAnswerMapping;
import com.hbs.edutel.model.OnlinePractiseExam;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.hbs.edutel.model.ToppersClub;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.factory.UsersFactory;
import com.hbs.edutel.util.common.image.ImageDataVO;
import com.hbs.edutel.util.common.property.factory.PropFactory;

public class OnlineExamQuestionActionData extends EduTelCommonData implements IOnlineExamAction
{
	private static final long								serialVersionUID					= -6971561606644059383L;
	protected String										answeredQNos;
	protected String										downloadFileUrl;
	protected String										downloadValidatedAnswerUrl;
	protected String										isAnswered							= "false";
	protected String										mcqDurationPerQuestion				= PropFactory.getInstance().getProperty(EGeneral.MCQ_Duration_Per_Question);
	protected List<String>									oeAnswerText						= new ArrayList<String>(0);
	protected String										oeChapter;
	protected String										oeExamId;

	protected String										oeMarkPerAnswer						= "1";
	protected String										oeNoOfQuestions;
	protected String										oePractiseExamId;
	protected String										oePractiseExamName;
	protected String										oeQuestionId;
	protected String										oeSchoolType;
	protected String										oeSubject;

	protected String										oeTestSeriesAnswerVIA				= "";
	protected String										oeTestSeriesAutoId;
	protected OnlineExam									onlineExam							= new OnlineExam();
	protected OnlineExamQuestion							onlineExamQuestion					= new OnlineExamQuestion();
	protected OnlineExamQuestionAnswerMapping[]				onlineExamQuestionAnswerArray		= null;

	protected List<OnlineExamQuestion>						onlineExamQuestionList				= new ArrayList<OnlineExamQuestion>(0);
	protected OnlinePractiseExam							onlinePractiseExam					= new OnlinePractiseExam();
	protected OnlineTestSeriesExamQuestionAnswerMapping		onlineTestSeriesExamAnswer			= new OnlineTestSeriesExamQuestionAnswerMapping();
	protected OnlineTestSeriesExamQuestionAnswerMapping		onlineTestSeriesExamAnswerValidated	= new OnlineTestSeriesExamQuestionAnswerMapping();
	protected String										overallSubjects						= "Physics,Chemistry,Maths,Botany,Zoology,Computer,BusinessMaths,Economics";
	protected String										questionNo;
	protected String										scoredMark;
	protected ToppersClub									toppersClub							= new ToppersClub();
	protected String										unAnsweredQNos;
	protected List<File>									uploadExplanationFile				= new ArrayList<File>(0);
	protected List<String>									uploadExplanationFileFileName		= new ArrayList<String>(0);
	protected List<File>									uploadQuestionFile					= new ArrayList<File>(0);
	protected List<String>									uploadQuestionFileFileName			= new ArrayList<String>(0);
	protected String										usEmployeeId;
	protected IUsers										user								= UsersFactory.getInstance().getUsersInstance();
	protected List<LabelValueBean>							answerFileNameList					= new ArrayList<LabelValueBean>(0);
	protected List<String>									answerFileName						= new ArrayList<String>(0);
	protected OnlineTestSeriesExamQuestionAnswerMapping[]	oeTestSeriesExamQuesAnsMappingArray;
	protected List<String>									employeeId							= new ArrayList<String>(0);
	protected List<String>									answerId							= new ArrayList<String>(0);
	protected OnlineTestSeriesExamQuestionAnswerMapping[]	oeTestSeriesExamQuesAnswerArray;

	public OnlineExamQuestionActionData()
	{
		super();
	}

	public OnlineExamQuestionActionData(List<OnlineExamQuestion> onlineExamQuestionList, String usEmployeeId, String oeSubject, String oeChapter, String oeSchoolType, String overallSubjects)
	{
		super();
		this.onlineExamQuestionList = onlineExamQuestionList;
		this.usEmployeeId = usEmployeeId;
		this.oeSubject = oeSubject;
		this.oeChapter = oeChapter;
		this.oeSchoolType = oeSchoolType;
		this.overallSubjects = overallSubjects;
	}

	public String getAnsweredQNos()
	{
		return answeredQNos;
	}

	public String getDownloadFileUrl()
	{
		return downloadFileUrl;
	}

	public String getDownloadValidatedAnswerUrl()
	{
		return downloadValidatedAnswerUrl;
	}

	public String getIsAnswered()
	{
		return isAnswered;
	}

	public String getMcqDurationPerQuestion()
	{
		return mcqDurationPerQuestion;
	}

	public List<String> getOeAnswerText()
	{
		return oeAnswerText;
	}

	public String getOeChapter()
	{
		return oeChapter;
	}

	public String getOeExamId()
	{
		return oeExamId;
	}

	public String getOeMarkPerAnswer()
	{
		return oeMarkPerAnswer;
	}

	public String getOeNoOfQuestions()
	{
		return oeNoOfQuestions;
	}

	public String getOePractiseExamId()
	{
		return oePractiseExamId;
	}

	public String getOePractiseExamName()
	{
		return oePractiseExamName;
	}

	public String getOeQuestionId()
	{
		return oeQuestionId;
	}

	public String getOeSchoolType()
	{
		return oeSchoolType;
	}

	public String getOeSubject()
	{
		return oeSubject;
	}

	public String getOeTestSeriesAnswerVIA()
	{
		return oeTestSeriesAnswerVIA;
	}

	public String getOeTestSeriesAutoId()
	{
		return oeTestSeriesAutoId;
	}

	public OnlineExam getOnlineExam()
	{
		return onlineExam;
	}

	public OnlineExamQuestion getOnlineExamQuestion()
	{
		return onlineExamQuestion;
	}

	public OnlineExamQuestionAnswerMapping[] getOnlineExamQuestionAnswerArray()
	{
		return onlineExamQuestionAnswerArray;
	}

	public List<OnlineExamQuestion> getOnlineExamQuestionList()
	{
		return onlineExamQuestionList;
	}

	public OnlinePractiseExam getOnlinePractiseExam()
	{
		return onlinePractiseExam;
	}

	public OnlineTestSeriesExamQuestionAnswerMapping getOnlineTestSeriesExamAnswer()
	{
		return onlineTestSeriesExamAnswer;
	}

	public OnlineTestSeriesExamQuestionAnswerMapping getOnlineTestSeriesExamAnswerValidated()
	{
		return onlineTestSeriesExamAnswerValidated;
	}

	public String getOverallSubjects()
	{
		return overallSubjects;
	}

	public String getQuestionNo()
	{
		return questionNo;
	}

	public String getScoredMark()
	{
		return scoredMark;
	}

	public ToppersClub getToppersClub()
	{
		return toppersClub;
	}

	public String getUnAnsweredQNos()
	{
		return unAnsweredQNos;
	}

	public List<File> getUploadExplanationFile()
	{
		return uploadExplanationFile;
	}

	public List<String> getUploadExplanationFileFileName()
	{
		return uploadExplanationFileFileName;
	}

	public List<File> getUploadQuestionFile()
	{
		return uploadQuestionFile;
	}

	public List<String> getUploadQuestionFileFileName()
	{
		return uploadQuestionFileFileName;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public IUsers getUser()
	{
		return user;
	}

	public String saveExplanationAttachment(ImageDataVO imageDataVO, IUploadImageOrDocuments... iUploadImgOrDocs)
	{
		session = request.getSession();

		try
		{
			if (CommonValidator.isNotNullNotEmpty(uploadFiles))
			{
				imageDataVO.request = request;
				imageDataVO.uploadFiles = uploadExplanationFile;
				imageDataVO.uploadFilesFileName = uploadExplanationFileFileName;
				imageDataVO.isExplanation = true;
				imageUpload.uploadAttachmentFilesInRepository(imageDataVO, iUploadImgOrDocs);
			}
			return EPage.Success.name();
		}
		catch (Exception excep)
		{
		}
		return EPage.Failure.name();
	}

	public String saveQuestionAttachment(ImageDataVO imageDataVO, IUploadImageOrDocuments... iUploadImgOrDocs)
	{
		session = request.getSession();
		try
		{
			if (CommonValidator.isNotNullNotEmpty(uploadFiles))
			{
				imageDataVO.request = request;
				imageDataVO.uploadFiles = uploadQuestionFile;
				imageDataVO.uploadFilesFileName = uploadQuestionFileFileName;
				imageUpload.uploadAttachmentFilesInRepository(imageDataVO, iUploadImgOrDocs);
			}
			return EPage.Success.name();
		}
		catch (Exception excep)
		{
		}
		return EPage.Failure.name();
	}

	public void setAnsweredQNos(String answeredQNos)
	{
		this.answeredQNos = answeredQNos;
	}

	public void setDownloadFileUrl(String downloadFileUrl)
	{
		this.downloadFileUrl = downloadFileUrl;
	}

	public void setDownloadValidatedAnswerUrl(String downloadValidatedAnswerUrl)
	{
		this.downloadValidatedAnswerUrl = downloadValidatedAnswerUrl;
	}

	public void setIsAnswered(String isAnswered)
	{
		this.isAnswered = isAnswered;
	}

	public void setMcqDurationPerQuestion(String mcqDurationPerQuestion)
	{
		this.mcqDurationPerQuestion = mcqDurationPerQuestion;
	}

	public void setOeAnswerText(List<String> OeAnswerText)
	{
		this.oeAnswerText = OeAnswerText;
	}

	public void setOeChapter(String oeChapter)
	{
		this.oeChapter = oeChapter;
	}

	public void setOeExamId(String oeExamId)
	{
		this.oeExamId = oeExamId;
	}

	public void setOeMarkPerAnswer(String oeMarkPerAnswer)
	{
		this.oeMarkPerAnswer = oeMarkPerAnswer;
	}

	public void setOeNoOfQuestions(String oeNoOfQuestions)
	{
		this.oeNoOfQuestions = oeNoOfQuestions;
	}

	public void setOePractiseExamId(String oePractiseExamId)
	{
		this.oePractiseExamId = oePractiseExamId;
	}

	public void setOePractiseExamName(String oePractiseExamName)
	{
		this.oePractiseExamName = oePractiseExamName;
	}

	public void setOeQuestionId(String oeQuestionId)
	{
		this.oeQuestionId = oeQuestionId;
	}

	public void setOeSchoolType(String oeSchoolType)
	{
		this.oeSchoolType = oeSchoolType;
	}

	public void setOeSubject(String oeSubject)
	{
		this.oeSubject = oeSubject;
	}

	public void setOeTestSeriesAnswerVIA(String oeTestSeriesAnswerVIA)
	{
		this.oeTestSeriesAnswerVIA = oeTestSeriesAnswerVIA;
	}

	public void setOeTestSeriesAutoId(String oeTestSeriesAutoId)
	{
		this.oeTestSeriesAutoId = oeTestSeriesAutoId;
	}

	public void setOnlineExam(OnlineExam onlineExam)
	{
		this.onlineExam = onlineExam;
	}

	public void setOnlineExamQuestion(OnlineExamQuestion onlineExamQuestion)
	{
		this.onlineExamQuestion = onlineExamQuestion;
	}

	public void setOnlineExamQuestionAnswerArray(OnlineExamQuestionAnswerMapping[] onlineExamQuestionAnswerArray)
	{
		this.onlineExamQuestionAnswerArray = onlineExamQuestionAnswerArray;
	}

	public void setOnlineExamQuestionList(List<OnlineExamQuestion> onlineExamQuestionList)
	{
		this.onlineExamQuestionList = onlineExamQuestionList;
	}

	public void setOnlinePractiseExam(OnlinePractiseExam onlinePractiseExam)
	{
		this.onlinePractiseExam = onlinePractiseExam;
	}

	public void setOnlineTestSeriesExamAnswer(OnlineTestSeriesExamQuestionAnswerMapping onlineTestSeriesExamAnswer)
	{
		this.onlineTestSeriesExamAnswer = onlineTestSeriesExamAnswer;
	}

	public void setOnlineTestSeriesExamAnswerValidated(OnlineTestSeriesExamQuestionAnswerMapping onlineTestSeriesExamAnswerValidated)
	{
		this.onlineTestSeriesExamAnswerValidated = onlineTestSeriesExamAnswerValidated;
	}

	public void setOverallSubjects(String overallSubjects)
	{
		this.overallSubjects = overallSubjects;
	}

	public void setQuestionNo(String questionNo)
	{
		this.questionNo = questionNo;
	}

	public void setScoredMark(String scoredMark)
	{
		this.scoredMark = scoredMark;
	}

	public void setToppersClub(ToppersClub toppersClub)
	{
		this.toppersClub = toppersClub;
	}

	public void setUnAnsweredQNos(String unAnsweredQNos)
	{
		this.unAnsweredQNos = unAnsweredQNos;
	}

	public void setUploadExplanationFile(List<File> uploadExplanationFile)
	{
		this.uploadExplanationFile = uploadExplanationFile;
	}

	public void setUploadExplanationFileFileName(List<String> uploadExplanationFileFileName)
	{
		this.uploadExplanationFileFileName = uploadExplanationFileFileName;
	}

	public void setUploadQuestionFile(List<File> uploadQuestionFile)
	{
		this.uploadQuestionFile = uploadQuestionFile;
	}

	public void setUploadQuestionFileFileName(List<String> uploadQuestionFileFileName)
	{
		this.uploadQuestionFileFileName = uploadQuestionFileFileName;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public List<LabelValueBean> getAnswerFileNameList()
	{
		return answerFileNameList;
	}

	public void setAnswerFileNameList(List<LabelValueBean> answerFileNameList)
	{
		this.answerFileNameList = answerFileNameList;
	}

	public List<String> getAnswerFileName()
	{
		return answerFileName;
	}

	public void setAnswerFileName(List<String> answerFileName)
	{
		this.answerFileName = answerFileName;
	}

	public OnlineTestSeriesExamQuestionAnswerMapping[] getOeTestSeriesExamQuesAnsMappingArray()
	{
		return oeTestSeriesExamQuesAnsMappingArray;
	}

	public void setOeTestSeriesExamQuesAnsMappingArray(OnlineTestSeriesExamQuestionAnswerMapping[] oeTestSeriesExamQuesAnsMappingArray)
	{
		this.oeTestSeriesExamQuesAnsMappingArray = oeTestSeriesExamQuesAnsMappingArray;
	}

	public List<String> getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(List<String> employeeId)
	{
		this.employeeId = employeeId;
	}

	public List<String> getAnswerId()
	{
		return answerId;
	}

	public void setAnswerId(List<String> answerId)
	{
		this.answerId = answerId;
	}

	public OnlineTestSeriesExamQuestionAnswerMapping[] getOeTestSeriesExamQuesAnswerArray()
	{
		return oeTestSeriesExamQuesAnswerArray;
	}

	public void setOeTestSeriesExamQuesAnswerArray(OnlineTestSeriesExamQuestionAnswerMapping[] oeTestSeriesExamQuesAnswerArray)
	{
		this.oeTestSeriesExamQuesAnswerArray = oeTestSeriesExamQuesAnswerArray;
	}

}
