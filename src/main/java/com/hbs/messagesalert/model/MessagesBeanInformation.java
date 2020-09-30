package com.hbs.messagesalert.model;

public class MessagesBeanInformation extends CommonBeanFields
{
	private static final long	serialVersionUID	= 1L;
	protected String			beanDisplayName;
	protected String			beanNameWithPackage;

	public String getBeanDisplayName()
	{
		return beanDisplayName;
	}

	public String getBeanNameWithPackage()
	{
		return beanNameWithPackage;
	}

	public void setBeanDisplayName(String beanDisplayName)
	{
		this.beanDisplayName = beanDisplayName;
	}

	public void setBeanNameWithPackage(String beanNameWithPackage)
	{
		this.beanNameWithPackage = beanNameWithPackage;
	}

}
