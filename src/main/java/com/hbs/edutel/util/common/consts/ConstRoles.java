package com.hbs.edutel.util.common.consts;

import java.util.List;

import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.util.common.EnumInterface;

public class ConstRoles extends ConstData
{

	public ConstRoles()
	{
		super();
	}

	public String getRoleId(EnumInterface enumData)
	{
		try
		{
			if (getConstHM().containsKey(enumData.name()) == false)
			{
				loadRolesInHM();
			}
			return ((IRoles) getConstHM().get(enumData.name())).getRlRoleId();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public String getRoleLongName(EnumInterface enumData)
	{
		try
		{
			if (getConstHM().containsKey(enumData.name()) == false)
			{
				loadRolesInHM();
			}
			return ((IRoles) getConstHM().get(enumData.name())).getRlRoleLongName();
		}
		catch (Exception e)
		{
			return "";
		}
	}

	public String getRoleName(EnumInterface enumData)
	{
		try
		{
			if (getConstHM().containsKey(enumData.name()) == false)
			{
				loadRolesInHM();
			}
			return ((IRoles) getConstHM().get(enumData.name())).getRlRoleName();
		}
		catch (Exception e)
		{
			return "";
		}
	}

	public String getRoleShortName(EnumInterface enumData)
	{
		try
		{
			if (getConstHM().containsKey(enumData.name()) == false)
			{
				loadRolesInHM();
			}
			return ((IRoles) getConstHM().get(enumData.name())).getRlRoleShortName();
		}
		catch (Exception e)
		{
			return "";
		}
	}

	private void loadRolesInHM()
	{
		List<ConstInterface> constList = getConstantBo().getRolesList();
		if (constList != null)
		{
			for (ConstInterface constData : constList)
			{
				if (constData != null)
					getConstHM().put((((IRoles) constData).getEnumKey()), constData);
			}
		}
	}
}