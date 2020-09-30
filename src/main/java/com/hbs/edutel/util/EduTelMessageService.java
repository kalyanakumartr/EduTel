package com.hbs.edutel.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import com.hbs.edutel.common.action.CommonValidator;

public class EduTelMessageService
{
	private String			adminEmailId;
	private String			adminName;
	private JavaMailSender	sender;

	public void eduTelMessageSender(EmailSenderParam... emailParams)
	{
		try
		{
			MimeMessage mimeMessage = sender.createMimeMessage();
			for (EmailSenderParam esParam : emailParams)
			{
				if (CommonValidator.isNotNullNotEmpty(esParam.emailToAddress) == false)
					esParam.emailToAddress = adminEmailId;

				EduTelMessagePreparator msgPreparator = new EduTelMessagePreparator(esParam);
				msgPreparator.prepare(mimeMessage);

				sender.send(msgPreparator);
			}

		}
		catch (MailException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getAdminEmailId()
	{
		return adminEmailId;
	}

	public String getAdminName()
	{
		return adminName;
	}

	public JavaMailSender getSender()
	{
		return sender;
	}

	public void setAdminEmailId(String adminEmailId)
	{
		this.adminEmailId = adminEmailId;
	}

	public void setAdminName(String adminName)
	{
		this.adminName = adminName;
	}

	public void setSender(JavaMailSender sender)
	{
		this.sender = sender;
	}

}
