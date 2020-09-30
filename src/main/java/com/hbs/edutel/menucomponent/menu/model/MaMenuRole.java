package com.hbs.edutel.menucomponent.menu.model;

public class MaMenuRole implements java.io.Serializable
{
	private static final long	serialVersionUID	= 7589959540619401593L;
	private String				maMenuId;
	private int					maMRAutoId;
	private String				rlRoleId;

	public MaMenuRole()
	{
		super();
	}

	public MaMenuRole(int maMRAutoId, String maMenuId, String rlRoleId)
	{
		super();
		this.maMRAutoId = maMRAutoId;
		this.maMenuId = maMenuId;
		this.setRlRoleId(rlRoleId);
	}

	public String getMaMenuId()
	{
		return maMenuId;
	}

	public int getMaMRAutoId()
	{
		return maMRAutoId;
	}

	public String getRlRoleId()
	{
		return rlRoleId;
	}

	public void setMaMenuId(String maMenuId)
	{
		this.maMenuId = maMenuId;
	}

	public void setMaMRAutoId(int maMRAutoId)
	{
		this.maMRAutoId = maMRAutoId;
	}

	public void setRlRoleId(String rlRoleId)
	{
		this.rlRoleId = rlRoleId;
	}

}