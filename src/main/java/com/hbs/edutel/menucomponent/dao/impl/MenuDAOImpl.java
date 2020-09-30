package com.hbs.edutel.menucomponent.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hbs.edutel.common.model.interfaces.IUserRoles;
import com.hbs.edutel.menucomponent.action.MaMenuData;
import com.hbs.edutel.menucomponent.dao.MenuDAO;
import com.hbs.edutel.menucomponent.menu.model.MaMenu;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class MenuDAOImpl extends HibernateDaoSupport implements MenuDAO, ConstInterface
{
	private static final long	serialVersionUID	= -1506618682884270152L;

	@SuppressWarnings("unchecked")
	
	public List<MaMenu> getMenusByRole(MaMenuData menuData)
	{
		Session session = getSession();

		try
		{
			StringBuffer menuQuery = new StringBuffer();
			menuQuery.append("Select Distinct MM From MaMenu MM, MaMenuRole MMR Where MM.maMenuId = MMR.maMenuId AND MMR.rlRoleId In (");
			for (IUserRoles userRole : menuData.getRolesSet())
			{
				menuQuery.append(EWrap.Quote.enclose(userRole.getRoles().getRlRoleId()));
				menuQuery.append(COMMA_SPACE.trim());
			}
			menuQuery.append(") Order By MM.maMenuLevel");
			String menuQry = menuQuery.toString();
			menuQry = menuQry.replace(COMMA_SPACE.trim() + ")", ")");

			return session.createQuery(menuQry).list();
		}
		catch (Exception excep)
		{
			return new ArrayList<MaMenu>(0);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
	}
}
