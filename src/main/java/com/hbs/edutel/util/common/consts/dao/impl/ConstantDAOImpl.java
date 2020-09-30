package com.hbs.edutel.util.common.consts.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.consts.dao.ConstantDAO;
import com.hbs.edutel.util.common.factory.RolesFactory;

public class ConstantDAOImpl extends HibernateDaoSupport implements ConstantDAO
{

	@SuppressWarnings("unchecked")
	
	public List<ConstInterface> getRolesList()
	{
		Session session = getSession();
		try
		{
			return session.createQuery("FROM " + RolesFactory.getInstance().getRolesInstanceName() + " as RL WHERE RL.rlRoleId <> 'Dummy' AND RL.rlStatus = true ORDER BY RL.rlRoleId").list();
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
