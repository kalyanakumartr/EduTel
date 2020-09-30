package com.hbs.messagesalert.model;

import com.hbs.edutel.util.common.ConstEnumUtil.Delim;

public class MessagesBeanFieldInformation extends CommonBeanFields
{
	private static final long			serialVersionUID	= 1L;
	protected String					beanDisplayName;
	protected String					beanFieldDisplayName;
	protected String					beanFieldName;
	protected MessagesBeanInformation	messageBean;
	protected String					messageDisplayName;
	protected Integer					msgFieldAutoId;

	public MessagesBeanFieldInformation()
	{

	}

	public MessagesBeanFieldInformation(MessagesBeanInformation messageBean, String beanFieldName)
	{
		this.messageBean = messageBean;
		this.beanDisplayName = messageBean.getBeanDisplayName();
		this.beanFieldName = beanFieldName;
		this.beanFieldDisplayName = beanFieldName;
		constructMessageDisplayName();
	}

	private void constructMessageDisplayName()
	{
		String beanName = messageBean.getBeanNameWithPackage();
		beanName = beanName.substring(beanName.lastIndexOf(".") + 1);
		messageDisplayName = Delim.Start.getKey() + beanName + "." + beanFieldName + Delim.End.getKey();
	}

	public String getBeanDisplayName()
	{
		return beanDisplayName;
	}

	public String getBeanFieldDisplayName()
	{
		return beanFieldDisplayName;
	}

	public String getBeanFieldName()
	{
		return beanFieldName;
	}

	public MessagesBeanInformation getMessageBean()
	{
		return messageBean;
	}

	public String getMessageDisplayName()
	{
		return messageDisplayName;
	}

	public Integer getMsgFieldAutoId()
	{
		return msgFieldAutoId;
	}

	public void setBeanDisplayName(String beanDisplayName)
	{
		this.beanDisplayName = beanDisplayName;
	}

	public void setBeanFieldDisplayName(String beanFieldDisplayName)
	{
		this.beanFieldDisplayName = beanFieldDisplayName;
	}

	public void setBeanFieldName(String beanFieldName)
	{
		this.beanFieldName = beanFieldName;
	}

	public void setMessageBean(MessagesBeanInformation messageBean)
	{
		this.messageBean = messageBean;
	}

	public void setMessageDisplayName(String messageDisplayName)
	{
		this.messageDisplayName = messageDisplayName;
	}

	public void setMsgFieldAutoId(Integer msgFieldAutoId)
	{
		this.msgFieldAutoId = msgFieldAutoId;
	}

}
