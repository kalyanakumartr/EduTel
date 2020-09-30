package com.hbs.edutel.util.common.property.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hbs.edutel.common.model.ConstProperty;
import com.hbs.edutel.util.common.property.dao.PropertyDAO;

public class PropertyDAOImpl extends HibernateDaoSupport implements PropertyDAO, Serializable
{
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	
	public List<ConstProperty> getConstPropertyList()
	{
		Session session = getSession();
		try
		{
			return session.createQuery("FROM ConstProperty as CP WHERE CP.constActive = TRUE ORDER BY CP.constId").list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.clear();
			session.close();
		}
		return null;
	}
}
