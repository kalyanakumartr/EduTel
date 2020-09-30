package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.common.ConstEnumUtil.EScreenShot;

public class OnlineTestSeriesExamQuestionAnswerMapping implements Serializable, IUploadImageOrDocuments
{
	private static final long		serialVersionUID		= 8238866882273804747L;
	protected String				createdBy;
	protected Timestamp				createdDate;
	protected String				modifiedBy;
	protected Timestamp				modifiedDate;
	protected String				oeAnswerImageURL;
	protected long					oeExamTestSeriesAutoId;
	protected String				oeTestSeriesAnswerVIA;
	protected boolean				oeValidatedAnswer		= false;
	protected OnlineExamQuestion	onlineExamQuestion;
	protected boolean				status					= true;
	protected Timestamp				uploadFileDate;
	protected String				uploadFileFolderURL;
	protected Timestamp				uploadFileLastModifiedDate;
	protected String				uploadFileName;
	protected String				uploadFileNamePrimaryId;
	protected String				uploadFileScreenShot;
	protected long					uploadFileSize;
	protected IUsers				user;
	protected boolean				downloaded;
	protected String				downloadURl;

	protected boolean				tsAnsStatus				= false;
	protected boolean				tsAnsValidatedStatus	= false;
	protected IUsers				createdUser;
	protected IUsers				modifiedUser;

	public OnlineTestSeriesExamQuestionAnswerMapping()
	{
		super();
	}

	public OnlineTestSeriesExamQuestionAnswerMapping(long oeExamTestSeriesAutoId, OnlineExamQuestion onlineExamQuestion, Timestamp uploadFileLastModifiedDate, Timestamp uploadFileDate,
			long uploadFileSize, String uploadFileName, String uploadFileFolderURL, String uploadFileNamePrimaryId, String createdBy, Timestamp createdDate, String modifiedBy, Timestamp modifiedDate,
			boolean status)
	{
		super();
		this.oeExamTestSeriesAutoId = oeExamTestSeriesAutoId;
		this.onlineExamQuestion = onlineExamQuestion;
		this.uploadFileLastModifiedDate = uploadFileLastModifiedDate;
		this.uploadFileDate = uploadFileDate;
		this.uploadFileSize = uploadFileSize;
		this.uploadFileName = uploadFileName;
		this.uploadFileFolderURL = uploadFileFolderURL;
		this.uploadFileNamePrimaryId = uploadFileNamePrimaryId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	public String getOeAnswerImageURL()
	{
		return oeAnswerImageURL;
	}

	public long getOeExamTestSeriesAutoId()
	{
		return oeExamTestSeriesAutoId;
	}

	public String getOeTestSeriesAnswerVIA()
	{
		return oeTestSeriesAnswerVIA;
	}

	public OnlineExamQuestion getOnlineExamQuestion()
	{
		return onlineExamQuestion;
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
		if (CommonValidator.isNotNullNotEmpty(uploadFileScreenShot) == false && CommonValidator.isNotNullNotEmpty(uploadFileName))
		{
			if (uploadFileName.lastIndexOf(".") > 0)
			{
				String fileExt = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
				fileExt = EScreenShot.valueOf(fileExt.toUpperCase()).getImage();
				if (CommonValidator.isNotNullNotEmpty(fileExt))
				{
					uploadFileScreenShot = fileExt;
				}
				else
				{
					uploadFileScreenShot = "no_image.png";
				}
			}
			else
			{
				uploadFileScreenShot = "no_image.png";
			}
		}
		return uploadFileScreenShot;
	}

	public long getUploadFileSize()
	{
		return uploadFileSize;
	}

	public IUsers getUser()
	{
		return user;
	}

	public boolean isOeValidatedAnswer()
	{
		return oeValidatedAnswer;
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

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public void setOeAnswerImageURL(String oeAnswerImageURL)
	{
		this.oeAnswerImageURL = oeAnswerImageURL;
	}

	public void setOeExamTestSeriesAutoId(long oeExamTestSeriesAutoId)
	{
		this.oeExamTestSeriesAutoId = oeExamTestSeriesAutoId;
	}

	public void setOeTestSeriesAnswerVIA(String oeTestSeriesAnswerVIA)
	{
		this.oeTestSeriesAnswerVIA = oeTestSeriesAnswerVIA;
	}

	public void setOeValidatedAnswer(boolean oeValidatedAnswer)
	{
		this.oeValidatedAnswer = oeValidatedAnswer;
	}

	public void setOnlineExamQuestion(OnlineExamQuestion onlineExamQuestion)
	{
		this.onlineExamQuestion = onlineExamQuestion;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
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

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public boolean isDownloaded()
	{
		return downloaded;
	}

	public void setDownloaded(boolean downloaded)
	{
		this.downloaded = downloaded;
	}

	public String getDownloadURl()
	{
		return downloadURl;
	}

	public void setDownloadURl(String downloadURl)
	{
		this.downloadURl = downloadURl;
	}

	public boolean isTsAnsStatus()
	{
		return tsAnsStatus;
	}

	public void setTsAnsStatus(boolean tsAnsStatus)
	{
		this.tsAnsStatus = tsAnsStatus;
	}

	public boolean isTsAnsValidatedStatus()
	{
		return tsAnsValidatedStatus;
	}

	public void setTsAnsValidatedStatus(boolean tsAnsValidatedStatus)
	{
		this.tsAnsValidatedStatus = tsAnsValidatedStatus;
	}

	public void getTestSeriesAnswerDownloaededStatus()
	{

		if (status)
		{
			if ((oeValidatedAnswer == false && downloaded == false)
					|| (oeValidatedAnswer == false && downloaded && CommonValidator.isEqual(oeTestSeriesAnswerVIA, "CourierOrPost") && CommonValidator.isNotNullNotEmpty(modifiedBy) == false))
			{
				tsAnsStatus = true;
			}
		}
		if (tsAnsStatus == false)
		{

			if (status)
			{
				if (oeValidatedAnswer && downloaded == false)
				{
					tsAnsValidatedStatus = true;
				}
			}
		}
	}

	public void getTestSeriesAnswerDownloaeded()
	{

		if (oeValidatedAnswer == false && downloaded == false && CommonValidator.isEqual(oeTestSeriesAnswerVIA, "Online"))
		{
			tsAnsStatus = true;
		}
	}

	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	public void setCreatedUser(IUsers createdUser)
	{
		this.createdUser = createdUser;
	}

	public IUsers getModifiedUser()
	{
		return modifiedUser;
	}

	public void setModifiedUser(IUsers modifiedUser)
	{
		this.modifiedUser = modifiedUser;
	}
}
