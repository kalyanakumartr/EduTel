package com.hbs.edutel.util;

import java.io.Serializable;

public class EmailSenderParam implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	public String[]				emailAttachment;
	public String				emailContent;
	public String[]				emailInlineImage;
	public String				emailSubject;
	public String				emailToAddress;

}
