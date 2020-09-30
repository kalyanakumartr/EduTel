package com.hbs.edutel.util.common.image.factory;

import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.image.ImageUpload;
import com.hbs.edutel.util.common.image.fileserver.FileServerImageUpload;

public class ImageUploadFactory implements ConstInterface
{
	private static ImageUploadFactory	constFact			= null;
	private static final long			serialVersionUID	= -3448438292700201628L;

	public static ImageUploadFactory getInstance()
	{
		if (constFact == null)
		{
			constFact = new ImageUploadFactory();
		}
		return constFact;
	}

	private ImageUploadFactory()
	{
	}

	public ImageUpload getImageUploadInstance()
	{
		return new FileServerImageUpload();
	}

}
