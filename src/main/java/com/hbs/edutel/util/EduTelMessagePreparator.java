package com.hbs.edutel.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.hbs.edutel.common.action.CommonValidator;

public class EduTelMessagePreparator implements MimeMessagePreparator
{
	private EmailSenderParam	edutelParam;

	public EduTelMessagePreparator(EmailSenderParam edutelParam)
	{
		super();
		this.edutelParam = edutelParam;
	}

	
	public void prepare(MimeMessage mimeMessage) throws Exception
	{
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setTo(edutelParam.emailToAddress);
		mimeMessageHelper.setSubject(edutelParam.emailSubject);

		// Sets the text
		mimeMessageHelper.setText(edutelParam.emailContent, true);

		// In-line content
		if (CommonValidator.isArrayFirstNotNull(edutelParam.emailInlineImage))
		{
			for (String inlineImg : edutelParam.emailInlineImage)
			{
				File inlineImage = new File(inlineImg);
				if (inlineImage.isFile() && inlineImage.exists())
				{
					FileSystemResource inlineContent = new FileSystemResource(inlineImage);
					mimeMessageHelper.addInline(inlineImg, inlineContent);
				}
			}
		}

		// Attachment
		if (CommonValidator.isArrayFirstNotNull(edutelParam.emailAttachment))
		{
			for (String attachment : edutelParam.emailAttachment)
			{
				File attFile = new File(attachment);
				if (attFile.isFile() && attFile.exists())
				{
					FileSystemResource attachContent = new FileSystemResource(attFile);
					mimeMessageHelper.addInline(attachment, attachContent);
				}
			}
		}
	}

}
