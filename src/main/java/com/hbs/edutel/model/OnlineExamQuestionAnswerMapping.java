package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;
import com.hbs.edutel.util.common.ConstEnumUtil.EScreenShot;

public class OnlineExamQuestionAnswerMapping implements Serializable, IUploadImageOrDocuments
{
	private static final long		serialVersionUID	= 8238866882273804747L;
	protected String				createdBy;
	protected Timestamp				createdDate;
	protected int					imageHeight			= 20;
	protected int					imageWidth			= 20;
	protected String				modifiedBy;
	protected Timestamp				modifiedDate;
	protected String				oeAnswerImageURL;
	protected String				oeAnswerText;
	protected long					oeExamAutoId;
	protected OnlineExamQuestion	onlineExamQuestion;
	protected boolean				status				= true;
	protected Timestamp				uploadFileDate;
	protected String				uploadFileFolderURL;
	protected Timestamp				uploadFileLastModifiedDate;
	protected String				uploadFileName;
	protected String				uploadFileNamePrimaryId;
	protected String				uploadFileScreenShot;
	protected long					uploadFileSize;

	public OnlineExamQuestionAnswerMapping()
	{
		super();
	}

	public OnlineExamQuestionAnswerMapping(long oeExamAutoId, OnlineExamQuestion onlineExamQuestion, String oeAnswerText, Timestamp uploadFileLastModifiedDate, Timestamp uploadFileDate,
			long uploadFileSize, String uploadFileName, String uploadFileFolderURL, String uploadFileNamePrimaryId, String createdBy, Timestamp createdDate, String modifiedBy, Timestamp modifiedDate,
			boolean status)
	{
		super();
		this.oeExamAutoId = oeExamAutoId;
		this.onlineExamQuestion = onlineExamQuestion;
		this.oeAnswerText = oeAnswerText;
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

	public int getImageHeight()
	{
		return imageHeight;
	}

	public int getImageWidth()
	{
		return imageWidth;
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

	public String getOeAnswerText()
	{
		return oeAnswerText;
	}

	public long getOeExamAutoId()
	{
		return oeExamAutoId;
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

	public void setImageHeight(int imageHeight)
	{
		this.imageHeight = imageHeight;
	}

	public void setImageWidth(int imageWidth)
	{
		this.imageWidth = imageWidth;
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

	public void setOeAnswerText(String oeAnswerText)
	{
		this.oeAnswerText = oeAnswerText;
	}

	public void setOeExamAutoId(long oeExamAutoId)
	{
		this.oeExamAutoId = oeExamAutoId;
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

}
