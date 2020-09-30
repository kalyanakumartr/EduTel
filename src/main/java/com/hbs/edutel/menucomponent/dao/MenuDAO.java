package com.hbs.edutel.menucomponent.dao;

import java.util.List;

import com.hbs.edutel.menucomponent.action.MaMenuData;
import com.hbs.edutel.menucomponent.menu.model.MaMenu;

public interface MenuDAO
{
	public List<MaMenu> getMenusByRole(MaMenuData menuData);
}
