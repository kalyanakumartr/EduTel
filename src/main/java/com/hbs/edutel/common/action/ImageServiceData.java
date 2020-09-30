package com.hbs.edutel.common.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.util.common.image.ImageDownload;
import com.hbs.edutel.util.common.image.ImageUpload;
import com.hbs.edutel.util.common.image.factory.ImageDownloadFactory;
import com.hbs.edutel.util.common.image.factory.ImageUploadFactory;

public abstract class ImageServiceData extends CommonActionBoData
{
	private static final long	serialVersionUID	= 366360750945439319L;
	protected String			contentDisposition;
	protected String			contentLength;
	protected String			contentType;
	protected InputStream		downloadInputStream;
	protected ImageDownload		imageDownload		= ImageDownloadFactory.getInstance().getImageDownloadInstance();
	protected ImageUpload		imageUpload			= ImageUploadFactory.getInstance().getImageUploadInstance();
	protected List<File>		uploadFiles			= new ArrayList<File>();
	protected List<String>		uploadFilesFileName	= new ArrayList<String>();
	protected File				uploadImage;
	protected String			uploadImageContentType;
	protected String			uploadImageFileName;

	public ImageServiceData()
	{
		super();
	}

	public String getContentDisposition()
	{
		return contentDisposition;
	}

	public String getContentLength()
	{
		return contentLength;
	}

	public String getContentType()
	{
		return contentType;
	}

	public InputStream getDownloadInputStream()
	{
		return downloadInputStream;
	}

	public List<File> getUploadFiles()
	{
		return uploadFiles;
	}

	public List<String> getUploadFilesFileName()
	{
		return uploadFilesFileName;
	}

	public File getUploadImage()
	{
		return uploadImage;
	}

	public String getUploadImageContentType()
	{
		return uploadImageContentType;
	}

	public String getUploadImageFileName()
	{
		return uploadImageFileName;
	}

	public void setContentDisposition(String contentDisposition)
	{
		this.contentDisposition = contentDisposition;
	}

	public void setContentLength(String contentLength)
	{
		this.contentLength = contentLength;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	public void setDownloadInputStream(InputStream downloadInputStream)
	{
		this.downloadInputStream = downloadInputStream;
	}

	public void setUploadFiles(List<File> uploadFiles)
	{
		this.uploadFiles = uploadFiles;
	}

	public void setUploadFilesFileName(List<String> uploadFilesFileName)
	{
		this.uploadFilesFileName = uploadFilesFileName;
	}

	public void setUploadImage(File uploadImage)
	{
		this.uploadImage = uploadImage;
	}

	public void setUploadImageContentType(String uploadImageContentType)
	{
		this.uploadImageContentType = uploadImageContentType;
	}

	public void setUploadImageFileName(String uploadImageFileName)
	{
		this.uploadImageFileName = uploadImageFileName;
	}

}