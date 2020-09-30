package com.hbs.edutel.common.model.abstracts;

import java.sql.Timestamp;
import java.util.Set;

import com.hbs.edutel.common.model.interfaces.IRoles;
import com.hbs.edutel.common.model.interfaces.IUserRoles;

public abstract class ARoles implements IRoles
{

	private static final long	serialVersionUID	= 1827095432159923994L;

	
	public String getEnumKey()
	{

		return null;
	}

	
	public String getRlCreatedBy()
	{

		return null;
	}

	
	public Timestamp getRlCreatedDate()
	{

		return null;
	}

	
	public String getRlModifiedBy()
	{

		return null;
	}

	
	public Timestamp getRlModifiedDate()
	{

		return null;
	}

	
	public String getRlRoleDescription()
	{

		return null;
	}

	
	public String getRlRoleId()
	{

		return null;
	}

	
	public String getRlRoleLongName()
	{

		return null;
	}

	
	public String getRlRoleName()
	{

		return null;
	}

	
	public String getRlRoleShortName()
	{

		return null;
	}

	
	public Boolean getRlStatus()
	{
		return true;
	}

	
	public Set<IUserRoles> getUserRoleses()
	{

		return null;
	}

	
	public void setEnumKey(String enumKey)
	{

	}

	
	public void setRlCreatedBy(String rlCreatedBy)
	{

	}

	
	public void setRlCreatedDate(Timestamp rlCreatedDate)
	{

	}

	
	public void setRlModifiedBy(String rlModifiedBy)
	{

	}

	
	public void setRlModifiedDate(Timestamp rlModifiedDate)
	{

	}

	
	public void setRlRoleDescription(String rlRoleDescription)
	{

	}

	
	public void setRlRoleId(String rlRoleId)
	{

	}

	
	public void setRlRoleLongName(String rlRoleLongName)
	{

	}

	
	public void setRlRoleName(String rlRoleName)
	{

	}

	
	public void setRlRoleShortName(String rlRoleShortName)
	{

	}

	
	public void setRlStatus(Boolean rlStatus)
	{

	}

	
	public void setUserRoleses(Set<IUserRoles> userRoleses)
	{

	}

}
