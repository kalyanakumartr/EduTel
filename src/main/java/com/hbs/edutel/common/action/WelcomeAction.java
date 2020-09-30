package com.hbs.edutel.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.hbs.edutel.common.bo.UserBo;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.hibernate3.support.MultiTenantRoutingSessionFactory;
import com.hbs.edutel.hibernate3.support.ThreadLocalContextUtil;
import com.hbs.edutel.menucomponent.action.MaMenuData;
import com.hbs.edutel.menucomponent.bo.MenuBo;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.EProject;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction extends ActionSupport implements ServletRequestAware
{
	private static final long					serialVersionUID	= -3267677743786023741L;
	protected String							maMenuHTML			= null;
	protected MenuBo							menuBo;
	private MultiTenantRoutingSessionFactory	multiTenantSessionFactory;
	protected HttpServletRequest				request;
	protected UserBo							userBo;

	/**
	 * This method will display all the basic menus.
	 */
	private void displayMenuByRoles()
	{

		IUsers user = userBo.getUserByEmployeeId("Dummy");
		if (CommonValidator.isNotNullNotEmpty(user))
		{
			MaMenuData menuData = new MaMenuData();
			menuData.setRolesSet(user.getUserRoleses());
			maMenuHTML = menuBo.getMenusByRoleHTML(menuData);
			request.getSession().setAttribute(ESession.MaMenuHTML.getAttribute(), maMenuHTML);
		}
	}

	public String getMaMenuHTML()
	{
		return maMenuHTML;
	}

	public MenuBo getMenuBo()
	{
		return menuBo;
	}

	public MultiTenantRoutingSessionFactory getMultiTenantSessionFactory()
	{
		return multiTenantSessionFactory;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public UserBo getUserBo()
	{
		return userBo;
	}

	public String loadInitialPage()
	{
		if (request.getRequestURI().toLowerCase().contains(EProject.EduTel.name().toLowerCase()))
		{
			ThreadLocalContextUtil.setDatabaseType(EProject.EduTel.name().toLowerCase());
			multiTenantSessionFactory.setRunTimeSessionFactory(EProject.EduTel.name().toLowerCase());
			System.out.println("Property Loaded For The Project : " + EProject.EduTel.name());
		}

		clearErrors();
		clearErrorsAndMessages();
		displayMenuByRoles();

		return EPage.Success.name();
	}

	public String loadPage()
	{
		return request.getParameter("p");
	}

	public String loginByOption()
	{
		String loginOption = request.getParameter("loginOption");
		switch ( EPage.valueOf(loginOption) )
		{
			case Student :
			case Employee :
			case Franchisee :
				return EPage.valueOf(loginOption).name();
			default :
				break;
		}

		return EPage.Failure.name();
	}

	public void setMaMenuHTML(String maMenuHTML)
	{
		this.maMenuHTML = maMenuHTML;
	}

	public void setMenuBo(MenuBo menuBo)
	{
		this.menuBo = menuBo;
	}

	public void setMultiTenantSessionFactory(MultiTenantRoutingSessionFactory multiTenantSessionFactory)
	{
		this.multiTenantSessionFactory = multiTenantSessionFactory;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;

	}

	public void setUserBo(UserBo userBo)
	{
		this.userBo = userBo;
	}

}
