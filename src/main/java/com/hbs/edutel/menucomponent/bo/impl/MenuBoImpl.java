package com.hbs.edutel.menucomponent.bo.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.hbs.edutel.menucomponent.action.MaMenuData;
import com.hbs.edutel.menucomponent.bo.MenuBo;
import com.hbs.edutel.menucomponent.dao.MenuDAO;
import com.hbs.edutel.menucomponent.menu.model.MaMenu;
import com.hbs.edutel.util.common.ConstEnumUtil.EWrap;

public class MenuBoImpl implements MenuBo, Serializable
{
	private static final long	serialVersionUID	= 1L;

	private MenuDAO				menuDAO;

	private StringBuilder		sbHTML				= new StringBuilder();

	public MenuDAO getMenuDAO()
	{
		return menuDAO;
	}

	
	public String getMenusByRoleHTML(MaMenuData menuData)
	{
		LinkedHashMap<String, String> mnuMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> endTagMap = new LinkedHashMap<String, String>();
		List<MaMenu> maMenuList = menuDAO.getMenusByRole(menuData);

		for (MaMenu menu : maMenuList)
		{
			if (menu.getMaSubMenuOrder() > 0)
			{
				endTagMap.put(menu.getMaParentId(), menu.getMaMenuId());
			}
		}

		for (MaMenu menu : maMenuList)
		{
			sbHTML = new StringBuilder();

			if (menu.getMaMenuLevel().length() == 1 && menu.getMaMenuLevel().equals("A") == false)
				sbHTML.append(" <li> <a href='javascript:void(0);' style='cursor: default;'>|</a></li> ");

			sbHTML.append("<li><a href=");
			sbHTML.append(EWrap.Quote.enclose(menu.getMaMenuActionURL()));
			getOnClickEvent(menu);
			getOnBlurEvent(menu);
			getOnMouseOver(menu);
			sbHTML.append(" id=\"Anlevel" + menu.getMaMenuLevel() + "\"");

			if (menu.getMaMenuId().equals(menu.getMaParentId()) == false)
			{
				if (menu.getMaMenuLevel().length() == 2)
				{
					sbHTML.append(" class=\"sub\" ");
					// sbHTML.append(" style=\"width:185px;\" ");
				}
				else if (menu.getMaMenuLevel().length() > 2)
					sbHTML.append(" class=\"fly\" ");
			}

			sbHTML.append(" > ");
			sbHTML.append(menu.getMaMenuName());
			if (menu.getMaMenuId().equals(menu.getMaParentId()))
			{
				if (menu.getMaSubMenuCount() == 0)
					sbHTML.append("</a></li>");
				else
					sbHTML.append("</a><ul>");
			}
			else if (menu.getMaMenuId().equals(menu.getMaParentId()) == false && menu.getMaSubMenuCount() > 0)
			{
				sbHTML.append("</a><ul>");
			}
			else
				sbHTML.append("</a>");
			mnuMap.put(menu.getMaMenuId(), sbHTML.toString());
		}

		for (MaMenu menu : maMenuList)
		{
			String mnuParentId = menu.getMaParentId();

			if (menu.getMaMenuId().equals(mnuParentId) == false)
			{

				String htmlTag = mnuMap.get(mnuParentId);
				if (htmlTag == null || htmlTag.trim().equals(""))
				{
					for (MaMenu subMenu : maMenuList)
					{
						if (subMenu.getMaMenuId().equals(mnuParentId))
						{
							htmlTag = mnuMap.get(subMenu.getMaParentId());
							mnuParentId = subMenu.getMaParentId();
							break;
						}

					}
				}
				StringBuilder mnuHTML = new StringBuilder(htmlTag == null ? "" : htmlTag);
				if (mnuHTML != null && mnuHTML.toString().equals("") == false)
				{
					mnuHTML.append(mnuMap.get(menu.getMaMenuId()));
					mnuMap.remove(menu.getMaMenuId());
					if (menu.getMaSubMenuCount() == 0)
						mnuHTML.append("</li>");

					if (endTagMap.containsValue(menu.getMaMenuId()))
					{
						mnuHTML.append("</ul></li>");
					}

					mnuMap.put(mnuParentId, mnuHTML.toString());
				}
			}
		}
		sbHTML = new StringBuilder();
		sbHTML.append("<ul id=\"menu\">");
		for (String key : mnuMap.keySet())
		{
			sbHTML.append(mnuMap.get(key));
		}
		sbHTML.append("</ul></li></ul>");
		return sbHTML.toString();
	}

	private void getOnBlurEvent(MaMenu menu)
	{
		if (menu.getMaMenuOnBlur() != null && !menu.getMaMenuOnBlur().trim().equals(""))
		{
			sbHTML.append(" OnBlur=");
			sbHTML.append(EWrap.Quote.enclose(menu.getMaMenuOnBlur()));
		}
	}

	private void getOnClickEvent(MaMenu menu)
	{
		if (menu.getMaMenuOnClick() != null && !menu.getMaMenuOnClick().trim().equals(""))
		{
			sbHTML.append(" OnClick=");
			sbHTML.append(EWrap.Quote.enclose(menu.getMaMenuOnClick()));
		}
	}

	private void getOnMouseOver(MaMenu menu)
	{
		if (menu.getMaMenuOnMouseOver() != null && !menu.getMaMenuOnMouseOver().trim().equals(""))
		{
			sbHTML.append(" OnMouseOver=");
			sbHTML.append(EWrap.Quote.enclose(menu.getMaMenuOnMouseOver()));
		}
	}

	public StringBuilder getSbHTML()
	{
		return sbHTML;
	}

	public void setMenuDAO(MenuDAO menuDAO)
	{
		this.menuDAO = menuDAO;
	}

	public void setSbHTML(StringBuilder sbHTML)
	{
		this.sbHTML = sbHTML;
	}

}
