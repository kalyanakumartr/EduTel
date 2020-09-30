package com.hbs.edutel.util.common.property.factory;

import java.io.Serializable;

import com.hbs.edutel.util.common.EnumInterface;
import com.hbs.edutel.util.common.property.bo.PropertyBo;

public class PropFactory implements Serializable
{
	private static PropFactory	propUtil			= null;
	private static final long	serialVersionUID	= 1L;
	public static PropFactory getInstance()
	{
		if (propUtil == null)
		{
			propUtil = new PropFactory();
		}
		return propUtil;
	}

	private PropertyBo			propertyBo;

	private PropFactory()
	{
	}

	public String getProperty(EnumInterface enumKey)
	{
		return propertyBo.getProperty(enumKey.name());
	}

	public PropertyBo getPropertyBo()
	{
		return propertyBo;
	}

	public void setPropertyBo(PropertyBo propertyBo)
	{
		this.propertyBo = propertyBo;
	}
}
