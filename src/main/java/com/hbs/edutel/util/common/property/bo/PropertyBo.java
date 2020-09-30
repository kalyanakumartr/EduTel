package com.hbs.edutel.util.common.property.bo;

import java.util.HashMap;

public interface PropertyBo
{
	public String getProperty(String key);

	public void setConstPropHM(HashMap<String, String> constPropHM);
}
