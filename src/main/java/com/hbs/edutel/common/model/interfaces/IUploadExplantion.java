package com.hbs.edutel.common.model.interfaces;

import java.sql.Timestamp;

public interface IUploadExplantion extends IUploadImageOrDocuments, java.io.Serializable
{
	public Timestamp getUploadExplanationFileDate();

	public String getUploadExplanationFileFolderURL();

	public Timestamp getUploadExplanationFileLastModifiedDate();

	public String getUploadExplanationFileName();

	public long getUploadExplanationFileSize();

	public void setUploadExplanationFileDate(Timestamp uploadExplanationFileDate);

	public void setUploadExplanationFileFolderURL(String uploadExplanationFileFolderURL);

	public void setUploadExplanationFileLastModifiedDate(Timestamp uploadExplanationFileLastModifiedDate);

	public void setUploadExplanationFileName(String uploadExplanationFileName);

	public void setUploadExplanationFileSize(long uploadExplanationFileSize);

}
