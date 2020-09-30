package com.hbs.edutel.util.common.image;

import java.io.IOException;

import com.hbs.edutel.common.model.interfaces.IUploadImageOrDocuments;

public interface ImageUpload
{

	public void uploadAttachmentFilesInRepository(ImageDataVO imageDataVO, IUploadImageOrDocuments[] iUploadImgOrDocs) throws IOException;

	public String uploadFilesInRepository(ImageDataVO imageDataVO) throws IOException;
}
