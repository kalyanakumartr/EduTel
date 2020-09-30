package com.hbs.edutel.hibernate3.support;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MultiTenantHibernateDaoSupport extends HibernateDaoSupport implements Serializable
{
	private static final long	serialVersionUID			= 1L;
	private SessionFactory		multiTenantSessionFactory	= null;

	public MultiTenantHibernateDaoSupport()
	{
		setSessionFactory(multiTenantSessionFactory);
	}

	public SessionFactory getMultiTenantSessionFactory()
	{
		return multiTenantSessionFactory;
	}

	public void setMultiTenantSessionFactory(SessionFactory multiTenantSessionFactory)
	{
		this.multiTenantSessionFactory = multiTenantSessionFactory;
	}

}
