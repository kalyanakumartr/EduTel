package com.hbs.edutel.common.model;

public class LabelValueBean implements java.io.Serializable
{
	private static final long	serialVersionUID	= -2065628036344367528L;
	private String				label;
	// Fields
	private String				value;

	public LabelValueBean(String value, String label)
	{
		this.value = value;
		this.label = label;
	}

	/**
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

}