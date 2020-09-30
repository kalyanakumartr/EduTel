package com.hbs.edutel.util.common.property.bo.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.hbs.edutel.common.model.ConstProperty;
import com.hbs.edutel.util.common.property.bo.PropertyBo;
import com.hbs.edutel.util.common.property.dao.PropertyDAO;

public class PropertyBoImpl implements PropertyBo, Serializable
{
	private static final long		serialVersionUID	= 1L;
	private HashMap<String, String>	constPropHM			= new HashMap<String, String>();
	private PropertyDAO				propertyDAO;

	private HashMap<String, String> getConstPropHM()
	{
		return constPropHM;
	}

	
	public String getProperty(String key)
	{
		if (key == null)
		{
			return null;
		}
		else if (getConstPropHM().containsKey(key) == false)
		{
			loadConstPropertyListInHM();
		}
		return getConstPropHM().get(key);
	}

	public PropertyDAO getPropertyDAO()
	{
		return propertyDAO;
	}

	private void loadConstPropertyListInHM()
	{
		List<ConstProperty> propList = getPropertyDAO().getConstPropertyList();
		if (propList != null)
		{
			for (ConstProperty constProp : propList)
			{
				if (constProp != null)
					getConstPropHM().put(constProp.getEnumKey(), constProp.getConstValue());
			}
		}
	}

	
	public void setConstPropHM(HashMap<String, String> constPropHM)
	{
		this.constPropHM = constPropHM;
	}

	public void setPropertyDAO(PropertyDAO propertyDAO)
	{
		this.propertyDAO = propertyDAO;
	}

}
