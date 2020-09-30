package com.hbs.edutel.util.common.image;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ImageDownload
{
	public boolean deleteFiles(String delFileDir);

	public String downloadImageFromRepositoryToSessionFolder(ImageDataVO imageDataVO) throws Exception;

	public String downloadZipFromRepository(List<String> fileNameList, String zipFileName, HttpServletRequest request, HttpServletResponse response);

}
