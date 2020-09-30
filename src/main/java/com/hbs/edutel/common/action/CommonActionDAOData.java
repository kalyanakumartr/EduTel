package com.hbs.edutel.common.action;

import com.hbs.edutel.common.dao.AdminDAO;
import com.hbs.edutel.common.dao.UserDAO;
import com.hbs.edutel.dao.QueryDiscussionDAO;
import com.hbs.edutel.dao.SerialKeyGenDAO;
import com.hbs.edutel.menucomponent.dao.MenuDAO;

public abstract class CommonActionDAOData
{
	protected AdminDAO				adminDAO;
	protected SerialKeyGenDAO		keyGenDAO;
	protected MenuDAO				menuDAO;
	protected QueryDiscussionDAO	queryDiscussionDAO;
	protected UserDAO				userDAO;

	public AdminDAO getAdminDAO()
	{
		return adminDAO;
	}

	public SerialKeyGenDAO getKeyGenDAO()
	{
		return keyGenDAO;
	}

	public MenuDAO getMenuDAO()
	{
		return menuDAO;
	}

	public QueryDiscussionDAO getQueryDiscussionDAO()
	{
		return queryDiscussionDAO;
	}

	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}

	public void setKeyGenDAO(SerialKeyGenDAO keyGenDAO)
	{
		this.keyGenDAO = keyGenDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO)
	{
		this.menuDAO = menuDAO;
	}

	public void setQueryDiscussionDAO(QueryDiscussionDAO queryDiscussionDAO)
	{
		this.queryDiscussionDAO = queryDiscussionDAO;
	}

	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

}