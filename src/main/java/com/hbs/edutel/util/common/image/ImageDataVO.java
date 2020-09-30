package com.hbs.edutel.util.common.image;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageDataVO
{
	public String				encryptedFileName;
	public String				imageUrl;
	public boolean				isClientDownload	= false;
	public boolean				isExplanation		= false;
	public boolean				isOnlineAssessment	= false;
	public String				onlineExamSessionFolder;
	public HttpServletRequest	request;
	public HttpServletResponse	response;
	public String				serverURL;
	public String				serverVirtualPath;
	public String				subFolderStructure;
	public File					uploadFile;
	public Timestamp			uploadFileDate;
	public String				uploadFileFolderURL;
	public Timestamp			uploadFileLastModifiedDate;
	public String				uploadFileName;
	public List<File>			uploadFiles;
	public List<String>			uploadFilesFileName;
	public int					uploadFileSize;
}
