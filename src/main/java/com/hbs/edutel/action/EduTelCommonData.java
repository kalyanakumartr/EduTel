package com.hbs.edutel.action;

import java.text.NumberFormat;

import com.hbs.edutel.common.action.CommonActionData;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.ImageServiceData;
import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;
import com.hbs.edutel.util.common.property.factory.PropFactory;

public abstract class EduTelCommonData extends ImageServiceData
{
	private static final long		serialVersionUID		= 2894122683387172415L;
	private final CustomAuditLogger	caLogger				= new CustomAuditLogger(CommonActionData.class);
	protected String				imageFileName;
	protected String				localFileUrl;
	protected String				userSelectionColName	= PropFactory.getInstance().getProperty(EGeneral.User_Selection_Column_Name);

	public EduTelCommonData()
	{
		super();
	}

	protected String convertTime(long milliSec)
	{
		try
		{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumIntegerDigits(3);
			String millSec = nf.format((milliSec) % 1000);
			nf.setMinimumIntegerDigits(2);
			String sec = nf.format(((milliSec) / 1000) % 60);
			String min = nf.format(((milliSec) / (1000 * 60)) % 60);
			String hrs = nf.format(((milliSec) / (1000 * 60 * 60)) % 60);
			return hrs + ":" + min + ":" + sec + ":" + millSec;
		}
		catch (Exception excep)
		{
		}
		return "";
	}

	protected String getImageFileNameFromImageUrl(String url)
	{
		if (CommonValidator.isNotNullNotEmpty(url))
		{
			if (url.lastIndexOf(SLASH) > 0)
				return url.substring(url.lastIndexOf(SLASH) + 1);
			else if (url.lastIndexOf("\\") > 0)
				return url.substring(url.lastIndexOf(BACKSLASH) + 1);
		}
		return "";
	}

	public String saveAttachment(ImageDataVO imageDataVO, IUploadImageOrDocuments... iUploadImgOrDocs)
	{
		session = request.getSession();

		try
		{
			if (CommonValidator.isNotNullNotEmpty(uploadFiles))
			{
				imageDataVO.request = request;
				imageDataVO.uploadFiles = uploadFiles;
				imageDataVO.uploadFilesFileName = uploadFilesFileName;
				imageUpload.uploadAttachmentFilesInRepository(imageDataVO, iUploadImgOrDocs);
			}
			return EPage.Success.name();
		}
		catch (Exception excep)
		{
			IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
			caLogger.error(Audit_Logging_Event_DataEntry, "saveAttachment", excep.getMessage(), this.getClass().getName(), sessionUser.getUsEmployeeId());
		}
		return EPage.Failure.name();
	}

}