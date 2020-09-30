package com.hbs.edutel.hibernate3.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiTenantRoutingDataSource extends AbstractRoutingDataSource implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Map<Object, Object>	targetDataSources	= new HashMap<Object, Object>();

	public MultiTenantRoutingDataSource()
	{
	}

	
	protected Object determineCurrentLookupKey()
	{
		String lookupKey = ThreadLocalContextUtil.getDatabaseType();
		return lookupKey;
	}

	public Map<Object, Object> getTargetDataSources()
	{
		return targetDataSources;
	}

}
