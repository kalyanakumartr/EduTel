package com.hbs.edutel.util.common.image.factory;

import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.image.ImageDownload;
import com.hbs.edutel.util.common.image.fileserver.FileServerImageDownload;

public class ImageDownloadFactory implements ConstInterface
{
	private static ImageDownloadFactory	imageDownloadFact	= null;
	private static final long			serialVersionUID	= -3448438292700201628L;

	public static ImageDownloadFactory getInstance()
	{
		if (imageDownloadFact == null)
		{
			imageDownloadFact = new ImageDownloadFactory();
		}
		return imageDownloadFact;
	}

	private ImageDownloadFactory()
	{
	}

	public ImageDownload getImageDownloadInstance()
	{
		return new FileServerImageDownload();
	}

}
