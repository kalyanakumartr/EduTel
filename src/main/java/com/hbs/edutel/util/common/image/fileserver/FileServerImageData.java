package com.hbs.edutel.util.common.image.fileserver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.image.ImageDataVO;

public abstract class FileServerImageData implements ConstInterface
{

	private static final String	APPLICATION_OCTET_STREAM	= "application/octet-stream";
	private static final String	CONTENT_DISPOSITION			= "Content-Disposition";
	private static final String	ISO_ENCODE					= "ISO-8859-1";
	private static final long	serialVersionUID			= -1444269129273830566L;
	protected String			defaultImagePath;

	public FileServerImageData()
	{
		super();
	}

	private void downloadImageInServer(String imageDownloadURL, String serverDirPath, String uploadFileName) throws Exception
	{
		OutputStream outStream = null;
		InputStream inStream = null;
		try
		{
			byte[] buffer = new byte[4098];
			int data = 0;
			outStream = new BufferedOutputStream(new FileOutputStream(serverDirPath + SLASH + uploadFileName));
			URLConnection uCon = new URL(imageDownloadURL + SLASH + uploadFileName).openConnection();
			inStream = uCon.getInputStream();
			while ( (data = inStream.read(buffer)) != -1 )
			{
				outStream.write(buffer, 0, data);
			}
		}
		finally
		{
			try
			{
				if (outStream != null)
					outStream.close();
				if (inStream != null)
					inStream.close();

			}
			catch (IOException e)
			{

			}
		}
	}

	public String getDefaultImagePath()
	{
		return defaultImagePath;
	}

	protected String isServerDirExists(ImageDataVO imageDataVO) throws Exception
	{

		imageDataVO.uploadFileName = URLDecoder.decode(imageDataVO.uploadFileName, ISO_ENCODE);

		File serverDir = new File(imageDataVO.serverURL);

		if (serverDir.exists() == false)
		{
			serverDir.mkdirs();
			downloadImageInServer(imageDataVO.uploadFileFolderURL, serverDir.getAbsolutePath(), imageDataVO.uploadFileName);
		}
		else
		{
			File alfFile = new File(serverDir.getAbsolutePath() + SLASH + imageDataVO.uploadFileName);

			if (alfFile.exists() == false)
			{
				downloadImageInServer(imageDataVO.uploadFileFolderURL, serverDir.getAbsolutePath(), imageDataVO.uploadFileName);
			}
		}
		return imageDataVO.uploadFileName;
	}

	public void setDefaultImagePath(String defaultImagePath)
	{
		this.defaultImagePath = defaultImagePath;
	}

	/**
	 * @return image byte array
	 * @throws IOException
	 */
	protected byte[] uploadImageToServer(ImageDataVO imageDataVO, File sourceFile, String serverDestinationFile) throws IOException
	{
		InputStream inStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try
		{
			File fileDir = new File(serverDestinationFile);
			if (fileDir.exists() == false)
				fileDir.mkdir();

			File fileToCreate = new File(serverDestinationFile, sourceFile.getName());

			if (fileToCreate.exists() == false)
			{
				if (imageDataVO.isClientDownload)
				{
					// get MIME type of the file
					String mimeType = imageDataVO.request.getSession().getServletContext().getMimeType(serverDestinationFile);
					if (mimeType == null)
					{
						// set to binary type if MIME mapping not found
						mimeType = APPLICATION_OCTET_STREAM;
					}

					// set content attributes for the response
					imageDataVO.response.setContentType(mimeType);
					imageDataVO.response.setContentLength((int) fileToCreate.length());

					// set headers for the response
					String headerValue = String.format("attachment; filename=\"%s\"", sourceFile.getName());
					imageDataVO.response.setHeader(CONTENT_DISPOSITION, headerValue);
				}
				FileUtils.copyFile(sourceFile, fileToCreate);
				inStream = new FileInputStream(fileToCreate);
				byte[] bytes = new byte[4096];
				int data = 0;
				while ( (data = inStream.read(bytes)) != -1 )
				{
					baos.write(bytes, 0, data);
				}
			}

		}
		finally
		{
			try
			{
				if (inStream != null)
					inStream.close();
				if (baos != null)
					baos.close();
			}
			catch (IOException e)
			{

			}
		}
		return baos.toByteArray();
	}

}