package com.hbs.edutel.menucomponent.menu.model;

public class MaMenu implements java.io.Serializable
{

	private static final long	serialVersionUID	= 4897686429234828726L;
	private String				maMenuActionURL;
	private int					maMenuAutoId;
	private String				maMenuId;
	private String				maMenuLevel;
	private String				maMenuName;
	private String				maMenuOnBlur;
	private String				maMenuOnClick;
	private String				maMenuOnMouseOver;
	private String				maMenuToolTip;
	private String				maParentId;
	private int					maSubMenuCount;
	private int					maSubMenuOrder;

	public MaMenu()
	{
		super();
	}

	public MaMenu(int maMenuAutoId, String maMenuId, String maMenuName, String maMenuActionURL, String maMenuToolTip, String maMenuOnClick, String maMenuOnBlur, String maMenuOnMouseOver,
			String maParentId, int maMainMenuOrder, int maSubMenuOrder, int maSubMenuCount, String maMenuLevel)
	{
		super();
		this.maMenuAutoId = maMenuAutoId;
		this.maMenuId = maMenuId;
		this.maMenuName = maMenuName;
		this.maMenuActionURL = maMenuActionURL;
		this.maMenuToolTip = maMenuToolTip;
		this.maMenuOnClick = maMenuOnClick;
		this.maMenuOnBlur = maMenuOnBlur;
		this.maMenuOnMouseOver = maMenuOnMouseOver;
		this.maParentId = maParentId;
		this.maSubMenuOrder = maSubMenuOrder;
		this.maSubMenuCount = maSubMenuCount;
		this.maMenuLevel = maMenuLevel;
	}

	public String getMaMenuActionURL()
	{
		return maMenuActionURL;
	}

	public int getMaMenuAutoId()
	{
		return maMenuAutoId;
	}

	public String getMaMenuId()
	{
		return maMenuId;
	}

	public String getMaMenuLevel()
	{
		return maMenuLevel;
	}

	public String getMaMenuName()
	{
		return maMenuName;
	}

	public String getMaMenuOnBlur()
	{
		return maMenuOnBlur;
	}

	public String getMaMenuOnClick()
	{
		return maMenuOnClick;
	}

	public String getMaMenuOnMouseOver()
	{
		return maMenuOnMouseOver;
	}

	public String getMaMenuToolTip()
	{
		return maMenuToolTip;
	}

	public String getMaParentId()
	{
		return maParentId;
	}

	public int getMaSubMenuCount()
	{
		return maSubMenuCount;
	}

	public int getMaSubMenuOrder()
	{
		return maSubMenuOrder;
	}

	public void setMaMenuActionURL(String maMenuActionURL)
	{
		this.maMenuActionURL = maMenuActionURL;
	}

	public void setMaMenuAutoId(int maMenuAutoId)
	{
		this.maMenuAutoId = maMenuAutoId;
	}

	public void setMaMenuId(String maMenuId)
	{
		this.maMenuId = maMenuId;
	}

	public void setMaMenuLevel(String maMenuLevel)
	{
		this.maMenuLevel = maMenuLevel;
	}

	public void setMaMenuName(String maMenuName)
	{
		this.maMenuName = maMenuName;
	}

	public void setMaMenuOnBlur(String maMenuOnBlur)
	{
		this.maMenuOnBlur = maMenuOnBlur;
	}

	public void setMaMenuOnClick(String maMenuOnClick)
	{
		this.maMenuOnClick = maMenuOnClick;
	}

	public void setMaMenuOnMouseOver(String maMenuOnMouseOver)
	{
		this.maMenuOnMouseOver = maMenuOnMouseOver;
	}

	public void setMaMenuToolTip(String maMenuToolTip)
	{
		this.maMenuToolTip = maMenuToolTip;
	}

	public void setMaParentId(String maParentId)
	{
		this.maParentId = maParentId;
	}

	public void setMaSubMenuCount(int maSubMenuCount)
	{
		this.maSubMenuCount = maSubMenuCount;
	}

	public void setMaSubMenuOrder(int maSubMenuOrder)
	{
		this.maSubMenuOrder = maSubMenuOrder;
	}

}