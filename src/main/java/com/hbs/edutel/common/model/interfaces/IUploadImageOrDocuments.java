package com.hbs.edutel.common.model.interfaces;

import java.sql.Timestamp;

public interface IUploadImageOrDocuments extends java.io.Serializable
{
	public Timestamp getUploadFileDate();

	public String getUploadFileFolderURL();

	public Timestamp getUploadFileLastModifiedDate();

	public String getUploadFileName();

	public String getUploadFileNamePrimaryId();

	public String getUploadFileScreenShot();

	public long getUploadFileSize();

	public void setUploadFileDate(Timestamp uploadFileDate);

	public void setUploadFileFolderURL(String uploadFileFolderURL);

	public void setUploadFileLastModifiedDate(Timestamp uploadFileLastModifiedDate);

	public void setUploadFileName(String uploadFileName);

	public void setUploadFileScreenShot(String uploadFileScreenShot);

	public void setUploadFileSize(long uploadFileSize);

}
