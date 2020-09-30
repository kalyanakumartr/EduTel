package com.hbs.edutel.util.common.image.fileserver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUploadExplantion;
import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.common.image.ImageDataVO;
import com.hbs.edutel.util.common.image.ImageUpload;

public class FileServerImageUpload extends FileServerImageData implements ImageUpload
{
	private static final long	serialVersionUID	= -2025957254980776052L;

	
	public void uploadAttachmentFilesInRepository(ImageDataVO imageDataVO, IUploadImageOrDocuments[] iUploadImgOrDoc) throws IOException
	{
		String serverRealPath = imageDataVO.request.getSession().getServletContext().getRealPath("/content") + SLASH + imageDataVO.request.getSession().getId();

		int fileIdx = 0;
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		if (CommonValidator.isNotNullNotEmpty(imageDataVO.uploadFiles))
		{
			for (File uploadFile : imageDataVO.uploadFiles)
			{
				String imageFileName = imageDataVO.uploadFilesFileName.get(fileIdx);
				String serverPhysicalPath = FileServer_Intermediate_Server_Path + SLASH + FileServer_Attachment_Path + SLASH + currentYear + SLASH + imageDataVO.subFolderStructure;
				String serverVirtualPath = SLASH + FileServer_Attachment_Path + SLASH + currentYear + SLASH + imageDataVO.subFolderStructure;

				if (uploadFile.getName().endsWith("no_image.png") == false)
				{
					byte[] fileByteArray = uploadImageToServer(imageDataVO, uploadFile, serverRealPath);
					uploadFileInFileServer(fileByteArray, serverPhysicalPath, imageFileName);
				}
				else
				{
					serverPhysicalPath = "";
					serverVirtualPath = "";
				}

				if (imageDataVO.isExplanation)
				{
					((IUploadExplantion) iUploadImgOrDoc[fileIdx]).setUploadExplanationFileName(imageFileName);
					((IUploadExplantion) iUploadImgOrDoc[fileIdx]).setUploadExplanationFileFolderURL(serverVirtualPath);
					((IUploadExplantion) iUploadImgOrDoc[fileIdx]).setUploadExplanationFileSize(uploadFile.length());
					((IUploadExplantion) iUploadImgOrDoc[fileIdx]).setUploadExplanationFileDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
					((IUploadExplantion) iUploadImgOrDoc[fileIdx]).setUploadExplanationFileLastModifiedDate(new Timestamp(uploadFile.lastModified()));
				}
				else
				{
					iUploadImgOrDoc[fileIdx].setUploadFileName(imageFileName);
					iUploadImgOrDoc[fileIdx].setUploadFileFolderURL(serverVirtualPath);
					iUploadImgOrDoc[fileIdx].setUploadFileSize(uploadFile.length());
					iUploadImgOrDoc[fileIdx].setUploadFileDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
					iUploadImgOrDoc[fileIdx].setUploadFileLastModifiedDate(new Timestamp(uploadFile.lastModified()));
				}

				fileIdx++;
			}
		}
	}

	private boolean uploadFileInFileServer(byte[] fileByteArray, String serverPhysicalPath, String imageFileName)
	{
		OutputStream outStream = null;
		try
		{
			File serverDir = new File(serverPhysicalPath);
			if (serverDir.exists() == false)
			{
				serverDir.mkdirs();
			}
			outStream = new BufferedOutputStream(new FileOutputStream(serverPhysicalPath + SLASH + imageFileName));
			for (byte data : fileByteArray)
			{
				outStream.write(data);
			}

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (outStream != null)
					outStream.close();
			}
			catch (IOException e)
			{

			}
		}
		return false;
	}

	
	public String uploadFilesInRepository(ImageDataVO imageDataVO) throws IOException
	{
		String serverRealPath = imageDataVO.request.getSession().getServletContext().getRealPath("/content") + "/" + imageDataVO.request.getSession().getId();
		int fileIdx = 0;

		if (CommonValidator.isNotNullNotEmpty(imageDataVO.uploadFile))
		{
			byte[] fileByteArray = uploadImageToServer(imageDataVO, imageDataVO.uploadFile, serverRealPath);

			String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			String imageFileName = fileIdx++ + "_" + imageDataVO.uploadFilesFileName;
			String serverPhysicalPath = FileServer_Intermediate_Server_Path + SLASH + FileServer_MailRoom_Path + SLASH + currentYear + SLASH + imageDataVO.subFolderStructure;
			uploadFileInFileServer(fileByteArray, serverPhysicalPath, imageFileName);
			return FileServer_Intermediate_Virtual_Path + SLASH + FileServer_MailRoom_Path + SLASH + currentYear + SLASH + imageDataVO.subFolderStructure + SLASH + imageFileName;
		}
		return null;
	}
}
