package com.hbs.edutel.common.action;

import com.hbs.edutel.bo.QueryDiscussionBo;
import com.hbs.edutel.bo.SerialKeyGenBo;
import com.hbs.edutel.common.bo.AdminBo;
import com.hbs.edutel.common.bo.ReportBo;
import com.hbs.edutel.common.bo.UserBo;
import com.hbs.edutel.menucomponent.bo.MenuBo;

public abstract class CommonActionBoData extends CommonActionData
{
	private static final long	serialVersionUID	= 8263222088391394815L;
	protected AdminBo			adminBo;
	protected SerialKeyGenBo	keyGenBo;
	protected MenuBo			menuBo;
	protected QueryDiscussionBo	queryDiscussionBo;
	protected ReportBo			reportBo;
	protected UserBo			userBo;

	public AdminBo getAdminBo()
	{
		return adminBo;
	}

	public SerialKeyGenBo getKeyGenBo()
	{
		return keyGenBo;
	}

	public MenuBo getMenuBo()
	{
		return menuBo;
	}

	public QueryDiscussionBo getQueryDiscussionBo()
	{
		return queryDiscussionBo;
	}

	public ReportBo getReportBo()
	{
		return reportBo;
	}

	public UserBo getUserBo()
	{
		return userBo;
	}

	public void setAdminBo(AdminBo adminBo)
	{
		this.adminBo = adminBo;
	}

	public void setKeyGenBo(SerialKeyGenBo keyGenBo)
	{
		this.keyGenBo = keyGenBo;
	}

	public void setMenuBo(MenuBo menuBo)
	{
		this.menuBo = menuBo;
	}

	public void setQueryDiscussionBo(QueryDiscussionBo queryDiscussionBo)
	{
		this.queryDiscussionBo = queryDiscussionBo;
	}

	public void setReportBo(ReportBo reportBo)
	{
		this.reportBo = reportBo;
	}

	public void setUserBo(UserBo userBo)
	{
		this.userBo = userBo;
	}

}