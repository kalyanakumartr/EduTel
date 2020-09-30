package com.hbs.edutel.util.common.image.fileserver;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.ImageDataVO;
import com.hbs.edutel.util.common.image.ImageDownload;

public class FileServerImageDownload extends FileServerImageData implements ImageDownload
{
	private static final long	serialVersionUID	= 753401735863240986L;
	private CustomAuditLogger	caLogger			= new CustomAuditLogger(FileServerImageDownload.class);
	public static final String	FILE_SEPARATOR		= System.getProperty("file.separator");

	
	public boolean deleteFiles(String delFileDir)
	{
		try
		{
			File file2Delete = new File(delFileDir);

			if (file2Delete.exists())
			{
				File[] imageFileList = file2Delete.listFiles();
				for (int idx = 0; idx < imageFileList.length; idx++)
				{
					imageFileList[idx].delete();
				}
				file2Delete.delete();
				return true;
			}

		}
		catch (Exception excep)
		{
			caLogger.error(Audit_Logging_Event_DataEntry, "deleteFiles", excep.getMessage(), FileServerImageDownload.class.getName(), null);
		}
		return false;
	}

	
	public String downloadImageFromRepositoryToSessionFolder(ImageDataVO imageDataVO)
	{

		if (CommonValidator.isNotNullNotEmpty(imageDataVO.uploadFileFolderURL) && CommonValidator.isNotNullNotEmpty(imageDataVO.uploadFileName))
		{
			try
			{
				if (CommonValidator.isNotEqual(imageDataVO.uploadFileName, "no_image.png"))
				{
					String onlineExamSessionFolder = imageDataVO.request.getSession().getId();
					if (imageDataVO.isOnlineAssessment && CommonValidator.isNotNullNotEmpty(imageDataVO.onlineExamSessionFolder))
					{
						onlineExamSessionFolder = imageDataVO.onlineExamSessionFolder;
					}
					String repositorySourceFile = FileServer_Intermediate_Server_Path + SLASH + imageDataVO.uploadFileFolderURL + SLASH + imageDataVO.uploadFileName;
					String serverDestinationFile = imageDataVO.request.getSession().getServletContext().getRealPath("/content") + "/" + onlineExamSessionFolder;
					File sourceFile = new File(repositorySourceFile);
					uploadImageToServer(imageDataVO, sourceFile, serverDestinationFile);
					return FileServer_Intermediate_Virtual_Path + "/content/" + onlineExamSessionFolder + SLASH + imageDataVO.uploadFileName;
				}
			}
			catch (Exception excep)
			{
				IUsers user = (IUsers) imageDataVO.request.getSession().getAttribute(ESession.UserObject.getAttribute());
				caLogger.error(Audit_Logging_Event_DataEntry, "downloadImageFromRepository", excep.getMessage(), FileServerImageDownload.class.getName(), user.getUsEmployeeId());
			}
		}
		return "images/png/no_image.png";
	}

	
	public String downloadZipFromRepository(List<String> fileNameList, String zipFileName, HttpServletRequest request, HttpServletResponse response)
	{

		String path = request.getSession().getServletContext().getRealPath("/content") + "/" + request.getSession().getId();

		try
		{
			File directory = new File(path);

			String[] files = directory.list();
			String[] downloadedFiles = new String[fileNameList.size()];
			int index = 0;
			if (CommonValidator.isListFirstNotEmpty(fileNameList))
			{
				for (String fileName : fileNameList)
				{
					for (String file : files)
					{
						if (CommonValidator.isEqual(file, fileName))
						{
							downloadedFiles[index] = file;
							index++;
						}

					}
				}
			}
			byte[] zip = zipFiles(directory, downloadedFiles);
			ServletOutputStream sos = response.getOutputStream();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=" + zipFileName);

			sos.write(zip);
			sos.flush();
			return EPage.Success.name();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return EPage.Failure.name();

	}

	private byte[] zipFiles(File directory, String[] files) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte bytes[] = new byte[2048];

		for (String fileName : files)
		{
			if (CommonValidator.isNotNullNotEmpty(fileName))
			{
				FileInputStream fis = new FileInputStream(directory.getPath() + FileServerImageDownload.FILE_SEPARATOR + fileName);
				BufferedInputStream bis = new BufferedInputStream(fis);

				zos.putNextEntry(new ZipEntry(fileName));

				int bytesRead;
				while ( (bytesRead = bis.read(bytes)) != -1 )
				{
					zos.write(bytes, 0, bytesRead);
				}
				zos.closeEntry();
				bis.close();
				fis.close();
			}
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();

		return baos.toByteArray();

	}
}
