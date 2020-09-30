package com.hbs.edutel.hibernate3.support;

import java.util.Map;

import javax.sql.DataSource;

public interface MultiTenantRoutingDataSourceFactory
{

	Map<Object, Object> fetchConfiguredTenantDataSources();

	DataSource getCommonSchemaDataSource();

	Map<Object, Object> initialiseConfiguredTenantDataSources();

}
