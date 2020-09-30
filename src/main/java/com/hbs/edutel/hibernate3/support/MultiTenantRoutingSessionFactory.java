package com.hbs.edutel.hibernate3.support;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.aop.TargetSource;

public class MultiTenantRoutingSessionFactory implements TargetSource, Serializable
{
	private static final long			serialVersionUID	= 1L;

	private Map<String, SessionFactory>	_sessionFactories;

	private ThreadLocal<SessionFactory>	_sessionFactory		= new ThreadLocal<SessionFactory>() {

																
																protected SessionFactory initialValue()
																{
																	return _sessionFactories.get(defaultSessionFactoryKey.toLowerCase());
																}

															};

	private String						defaultSessionFactoryKey;

	public String getDefaultSessionFactoryKey()
	{
		return defaultSessionFactoryKey;
	}

	public SessionFactory getSessionFactory()
	{
		return _sessionFactory.get();
	}

	
	public Object getTarget() throws Exception
	{
		SessionFactory sFactory = getSessionFactory();
		return sFactory;
	}

	
	public Class<?> getTargetClass()
	{
		return _sessionFactories.get(defaultSessionFactoryKey.toLowerCase()).getClass();
	}

	
	public boolean isStatic()
	{
		return false;
	}

	
	public void releaseTarget(Object target) throws Exception
	{

	}

	public void setDefaultSessionFactoryKey(String defaultSessionFactoryKey)
	{
		this.defaultSessionFactoryKey = defaultSessionFactoryKey;
	}

	public void setRunTimeSessionFactory(String shardName)
	{
		_sessionFactory.set(_sessionFactories.get(shardName));
	}

	public void setSessionFactories(Map<String, SessionFactory> sessionFactories)
	{
		_sessionFactories = sessionFactories;
	}

}
