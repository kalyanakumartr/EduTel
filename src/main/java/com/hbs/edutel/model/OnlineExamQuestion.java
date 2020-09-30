package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUploadExplantion;

public class OnlineExamQuestion implements IUploadExplantion, Serializable
{
	private static final long									serialVersionUID			= 2633768746559234453L;
	protected String											createdBy;
	protected Timestamp											createdDate;
	protected int												explanationHeight			= 20;
	protected int												explanationWidth			= 20;
	protected String											modifiedBy;
	protected Timestamp											modifiedDate;
	protected Set<OnlineExamQuestionAnswerMapping>				oeAnswers					= new LinkedHashSet<OnlineExamQuestionAnswerMapping>(0);
	protected String											oeCorrectAnswer;

	protected String											oeExplanation;
	protected String											oeExplanationImageURL;
	protected String											oeMarkPerAnswer;
	protected String											oeQuestion;
	protected String											oeQuestionId;
	protected String											oeQuestionImageURL;
	protected String											oeQuestionType;
	protected String											oeSelectedAnswer			= "";
	protected Set<OnlineTestSeriesExamQuestionAnswerMapping>	oeTestSeriesAnswers			= new LinkedHashSet<OnlineTestSeriesExamQuestionAnswerMapping>(0);
	protected OnlineExam										onlineExam;

	protected int												questionHeight				= 20;
	protected int												questionWidth				= 20;
	protected boolean											status						= true;
	protected Timestamp											uploadExplanationFileDate;
	protected String											uploadExplanationFileFolderURL;
	protected Timestamp											uploadExplanationFileLastModifiedDate;
	protected String											uploadExplanationFileName;
	protected long												uploadExplanationFileSize;

	protected Timestamp											uploadFileDate;
	protected String											uploadFileFolderURL;
	protected Timestamp											uploadFileLastModifiedDate;
	protected String											uploadFileName;
	protected String											uploadFileNamePrimaryId;
	protected String											uploadFileScreenShot;
	protected long												uploadFileSize;

	protected boolean											testSeriesAnsStatus			= false;
	protected boolean											testSeriesAnsValidateStatus	= false;

	public OnlineExamQuestion()
	{
		super();
	}

	public void getTestSeriesAnswerDownloaededStatus()
	{
		if (oeTestSeriesAnswers.isEmpty() == false)
		{
			for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesAnswer : oeTestSeriesAnswers)
			{
				if (oeTestSeriesAnswer.isStatus())
				{
					if ((oeTestSeriesAnswer.isOeValidatedAnswer() == false && oeTestSeriesAnswer.isDownloaded() == false)
							|| (oeTestSeriesAnswer.isOeValidatedAnswer() == false && oeTestSeriesAnswer.isDownloaded()
									&& CommonValidator.isEqual(oeTestSeriesAnswer.getOeTestSeriesAnswerVIA(), "CourierOrPost") && CommonValidator.isNotNullNotEmpty(oeTestSeriesAnswer.getModifiedBy()) == false))
					{
						testSeriesAnsStatus = true;
						break;
					}
				}
			}
			if (testSeriesAnsStatus == false)
			{
				for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesValidatedAnswer : oeTestSeriesAnswers)
				{
					if (oeTestSeriesValidatedAnswer.isStatus())
					{
						if (oeTestSeriesValidatedAnswer.isOeValidatedAnswer() && oeTestSeriesValidatedAnswer.isDownloaded() == false)
						{
							testSeriesAnsValidateStatus = true;
							break;
						}
					}
				}
			}
		}
	}

	public void getTestSeriesAnswerDownloaeded()
	{
		if (oeTestSeriesAnswers.isEmpty() == false)
		{
			for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesAnswer : oeTestSeriesAnswers)
			{
				if (oeTestSeriesAnswer.isOeValidatedAnswer() == false && oeTestSeriesAnswer.isDownloaded() == false && CommonValidator.isEqual(oeTestSeriesAnswer.getOeTestSeriesAnswerVIA(), "Online"))
				{
					testSeriesAnsStatus = true;
					break;
				}

			}
		}
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public int getExplanationHeight()
	{
		return explanationHeight;
	}

	public int getExplanationWidth()
	{
		return explanationWidth;
	}

	public String getMatchedAnswer()
	{
		List<OnlineExamQuestionAnswerMapping> oeQAList = new ArrayList<OnlineExamQuestionAnswerMapping>(0);

		oeQAList.addAll(this.oeAnswers);

		if (CommonValidator.isNotNullNotEmpty(oeCorrectAnswer) && CommonValidator.isListFirstNotEmpty(oeQAList))
		{
			int answerIdx = Integer.parseInt(oeCorrectAnswer);
			if (answerIdx > 0 && answerIdx < 5)
			{
				OnlineExamQuestionAnswerMapping oeQAns = oeQAList.get(answerIdx - 1);
				if (CommonValidator.isNotNullNotEmpty(oeQAns) && CommonValidator.isNotNullNotEmpty(oeQAns.getOeAnswerText()))
					return oeQAns.getOeAnswerText();
				else
					return oeQAns.getUploadFileName();
			}
		}
		return "None of these";
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	public Set<OnlineExamQuestionAnswerMapping> getOeAnswers()
	{
		return oeAnswers;
	}

	public String getOeCorrectAnswer()
	{
		return oeCorrectAnswer;
	}

	public String getOeExplanation()
	{
		return oeExplanation;
	}

	public String getOeExplanationImageURL()
	{
		return oeExplanationImageURL;
	}

	public String getOeMarkPerAnswer()
	{
		return oeMarkPerAnswer;
	}

	public String getOeQuestion()
	{
		return oeQuestion;
	}

	public String getOeQuestionId()
	{
		return oeQuestionId;
	}

	public String getOeQuestionImageURL()
	{
		return oeQuestionImageURL;
	}

	public String getOeQuestionType()
	{
		return oeQuestionType;
	}

	public String getOeSelectedAnswer()
	{
		return oeSelectedAnswer;
	}

	public Set<OnlineTestSeriesExamQuestionAnswerMapping> getOeTestSeriesAnswers()
	{
		return oeTestSeriesAnswers;
	}

	public OnlineExam getOnlineExam()
	{
		return onlineExam;
	}

	public int getQuestionHeight()
	{
		return questionHeight;
	}

	public int getQuestionWidth()
	{
		return questionWidth;
	}

	public Timestamp getUploadExplanationFileDate()
	{
		return uploadExplanationFileDate;
	}

	public String getUploadExplanationFileFolderURL()
	{
		return uploadExplanationFileFolderURL;
	}

	public Timestamp getUploadExplanationFileLastModifiedDate()
	{
		return uploadExplanationFileLastModifiedDate;
	}

	public String getUploadExplanationFileName()
	{
		return uploadExplanationFileName;
	}

	public long getUploadExplanationFileSize()
	{
		return uploadExplanationFileSize;
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
		return uploadFileNamePrimaryId;
	}

	public String getUploadFileScreenShot()
	{
		return uploadFileScreenShot;
	}

	public long getUploadFileSize()
	{
		return uploadFileSize;
	}

	public boolean isStatus()
	{
		return status;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setExplanationHeight(int explanationHeight)
	{
		this.explanationHeight = explanationHeight;
	}

	public void setExplanationWidth(int explanationWidth)
	{
		this.explanationWidth = explanationWidth;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public void setOeAnswers(Set<OnlineExamQuestionAnswerMapping> oeAnswers)
	{
		this.oeAnswers = oeAnswers;
	}

	public void setOeCorrectAnswer(String oeCorrectAnswer)
	{
		this.oeCorrectAnswer = oeCorrectAnswer;
	}

	public void setOeExplanation(String oeExplanation)
	{
		this.oeExplanation = oeExplanation;
	}

	public void setOeExplanationImageURL(String oeExplanationImageURL)
	{
		this.oeExplanationImageURL = oeExplanationImageURL;
	}

	public void setOeMarkPerAnswer(String oeMarkPerAnswer)
	{
		this.oeMarkPerAnswer = oeMarkPerAnswer;
	}

	public void setOeQuestion(String oeQuestion)
	{
		this.oeQuestion = oeQuestion;
	}

	public void setOeQuestionId(String oeQuestionId)
	{
		this.oeQuestionId = oeQuestionId;
	}

	public void setOeQuestionImageURL(String oeQuestionImageURL)
	{
		this.oeQuestionImageURL = oeQuestionImageURL;
	}

	public void setOeQuestionType(String oeQuestionType)
	{
		this.oeQuestionType = oeQuestionType;
	}

	public void setOeSelectedAnswer(String oeSelectedAnswer)
	{
		this.oeSelectedAnswer = oeSelectedAnswer;
	}

	public void setOeTestSeriesAnswers(Set<OnlineTestSeriesExamQuestionAnswerMapping> oeTestSeriesAnswers)
	{
		this.oeTestSeriesAnswers = oeTestSeriesAnswers;
	}

	public void setOnlineExam(OnlineExam onlineExam)
	{
		this.onlineExam = onlineExam;
	}

	public void setQuestionHeight(int questionHeight)
	{
		this.questionHeight = questionHeight;
	}

	public void setQuestionWidth(int questionWidth)
	{
		this.questionWidth = questionWidth;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public void setUploadExplanationFileDate(Timestamp uploadExplanationFileDate)
	{
		this.uploadExplanationFileDate = uploadExplanationFileDate;
	}

	public void setUploadExplanationFileFolderURL(String uploadExplanationFileFolderURL)
	{
		this.uploadExplanationFileFolderURL = uploadExplanationFileFolderURL;
	}

	public void setUploadExplanationFileLastModifiedDate(Timestamp uploadExplanationFileLastModifiedDate)
	{
		this.uploadExplanationFileLastModifiedDate = uploadExplanationFileLastModifiedDate;
	}

	public void setUploadExplanationFileName(String uploadExplanationFileName)
	{
		this.uploadExplanationFileName = uploadExplanationFileName;
	}

	public void setUploadExplanationFileSize(long uploadExplanationFileSize)
	{
		this.uploadExplanationFileSize = uploadExplanationFileSize;
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

	public void setUploadFileNamePrimaryId(String uploadFileNamePrimaryId)
	{
		this.uploadFileNamePrimaryId = uploadFileNamePrimaryId;
	}

	public void setUploadFileScreenShot(String uploadFileScreenShot)
	{
		this.uploadFileScreenShot = uploadFileScreenShot;
	}

	public void setUploadFileSize(long uploadFileSize)
	{
		this.uploadFileSize = uploadFileSize;
	}

	public boolean isTestSeriesAnsStatus()
	{
		return testSeriesAnsStatus;
	}

	public void setTestSeriesAnsStatus(boolean testSeriesAnsStatus)
	{
		this.testSeriesAnsStatus = testSeriesAnsStatus;
	}

	public boolean isTestSeriesAnsValidateStatus()
	{
		return testSeriesAnsValidateStatus;
	}

	public void setTestSeriesAnsValidateStatus(boolean testSeriesAnsValidateStatus)
	{
		this.testSeriesAnsValidateStatus = testSeriesAnsValidateStatus;
	}

}
