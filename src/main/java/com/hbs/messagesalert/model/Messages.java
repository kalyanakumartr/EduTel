package com.hbs.messagesalert.model;

import java.sql.Timestamp;

import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.common.ConstEnumUtil.EMessageType;

public class Messages extends CommonBeanFields
{

	private static final long	serialVersionUID	= 1L;
	protected Timestamp			deliveryDate;
	protected String			deliveryDateTime;
	protected String			message;
	protected String			messageId;
	protected String			messageName;
	protected String			messageType			= EMessageType.SMS.name();
	protected String			messageSubject		= "System Message";

	public Messages()
	{
		super();
	}

	public Messages(String messageId, String messageType)
	{
		super();
		this.messageId = messageId;
		this.messageType = messageType;
	}

	public Timestamp getDeliveryDate()
	{
		return deliveryDate;
	}

	public String getDeliveryDateTime()
	{
		return deliveryDateTime;
	}

	public String getMessage()
	{
		return message;
	}

	public String getMessageId()
	{
		return messageId;
	}

	public String getMessageName()
	{
		return messageName;
	}

	public String getMessageType()
	{
		return messageType;
	}

	public void setDeliveryDate(Timestamp deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}

	public void setDeliveryDateTime(String deliveryDateTime)
	{
		this.deliveryDateTime = deliveryDateTime;
		setDeliveryDate(CommonUtil.getTimeZoneDateInFormat(deliveryDateTime, DATE_FORMAT_DD_MM_YYYY_HH_MM, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS_24, IST));
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	public void setMessageName(String messageName)
	{
		this.messageName = messageName;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public String getMessageSubject()
	{
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject)
	{
		this.messageSubject = messageSubject;
	}

}
