package com.hbs.edutel.common.model;

public class ConstProperty implements java.io.Serializable
{
	private static final long	serialVersionUID	= -8065112006200733126L;
	private boolean				constActive;
	private String				constAllowedValue;
	private String				constGroup;
	private int					constId;
	private String				constKey;
	private String				constValue;
	private String				enumKey;

	public String getConstAllowedValue()
	{
		return constAllowedValue;
	}

	public String getConstGroup()
	{
		return constGroup;
	}

	public int getConstId()
	{
		return constId;
	}

	public String getConstKey()
	{
		return constKey;
	}

	public String getConstValue()
	{
		return constValue;
	}

	public String getEnumKey()
	{
		return enumKey;
	}

	public boolean isConstActive()
	{
		return constActive;
	}

	public void setConstActive(boolean constActive)
	{
		this.constActive = constActive;
	}

	public void setConstAllowedValue(String constAllowedValue)
	{
		this.constAllowedValue = constAllowedValue;
	}

	public void setConstGroup(String constGroup)
	{
		this.constGroup = constGroup;
	}

	public void setConstId(int constId)
	{
		this.constId = constId;
	}

	public void setConstKey(String constKey)
	{
		this.constKey = constKey;
	}

	public void setConstValue(String constValue)
	{
		this.constValue = constValue;
	}

	public void setEnumKey(String enumKey)
	{
		this.enumKey = enumKey;
	}
}
