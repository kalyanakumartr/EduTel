package com.hbs.edutel.util.common.factory;

import java.util.HashMap;

import com.hbs.edutel.common.model.Roles;
import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.util.common.ConstEnumUtil.EGeneral;
import com.hbs.edutel.util.common.ConstEnumUtil.EProject;
import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.consts.ConstRoles;
import com.hbs.edutel.util.common.property.factory.PropFactory;

public class RolesFactory extends ConstRoles
{
	private static RolesFactory	constFact	= null;

	public static RolesFactory getInstance()
	{
		if (constFact == null)
		{
			constFact = new RolesFactory();
		}
		return constFact;
	}

	private RolesFactory()
	{
		setConstHM(new HashMap<String, ConstInterface>());
	}

	public IRoles getRolesInstance()
	{
		String projectName = PropFactory.getInstance().getProperty(EGeneral.Project_Name);

		switch ( EProject.valueOf(projectName) )
		{
			case EduTel :
				return new Roles();
			default :
				return null;
		}
	}

	public IRoles getRolesInstance(String rlRoleId)
	{
		String projectName = PropFactory.getInstance().getProperty(EGeneral.Project_Name);

		switch ( EProject.valueOf(projectName) )
		{
			case EduTel :
				return new Roles(rlRoleId);
			default :
				return null;
		}
	}

	public String getRolesInstanceName()
	{
		String clazzName = getRolesInstance().getClass().getName();
		if (clazzName.indexOf(".") > 0)
		{
			clazzName = clazzName.substring(clazzName.lastIndexOf(".") + 1);
		}
		return clazzName;
	}

}