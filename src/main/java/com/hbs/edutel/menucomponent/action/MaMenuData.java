package com.hbs.edutel.menucomponent.action;

import java.util.Set;

import com.hbs.edutel.common.model.interfaces.IUserRoles;
import com.hbs.edutel.menucomponent.menu.model.MaMenu;

public class MaMenuData
{
	private MaMenu			maMenu;
	private Set<IUserRoles>	rolesSet;

	public MaMenu getMaMenu()
	{
		return maMenu;
	}

	public Set<IUserRoles> getRolesSet()
	{
		return rolesSet;
	}

	public void setMaMenu(MaMenu maMenu)
	{
		this.maMenu = maMenu;
	}

	public void setRolesSet(Set<IUserRoles> rolesSet)
	{
		this.rolesSet = rolesSet;
	}

}
