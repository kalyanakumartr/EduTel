package com.hbs.edutel.common.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.hbs.edutel.common.model.abstracts.ARoles;
import com.hbs.edutel.common.model.interfaces.IUserRoles;

public class Roles extends ARoles
{

	private static final long	serialVersionUID	= -2116332459093903688L;
	private String				enumKey;
	private String				rlCreatedBy;
	private Timestamp			rlCreatedDate;
	private String				rlModifiedBy;
	private Timestamp			rlModifiedDate;
	private String				rlRoleDescription;
	private String				rlRoleId;
	private String				rlRoleLongName;
	private String				rlRoleName;
	private String				rlRoleShortName;
	private String				rlRoleType;
	private Boolean				rlStatus;
	private Set<IUserRoles>		userRoleses			= new HashSet<IUserRoles>(0);

	public Roles()
	{
		super();
	}

	public Roles(String rlRoleId)
	{
		super();
		this.rlRoleId = rlRoleId;
	}

	
	public String getEnumKey()
	{
		return enumKey;
	}

	
	public String getRlCreatedBy()
	{
		return rlCreatedBy;
	}

	
	public Timestamp getRlCreatedDate()
	{
		return rlCreatedDate;
	}

	
	public String getRlModifiedBy()
	{
		return rlModifiedBy;
	}

	
	public Timestamp getRlModifiedDate()
	{
		return rlModifiedDate;
	}

	
	public String getRlRoleDescription()
	{
		return rlRoleDescription;
	}

	
	public String getRlRoleId()
	{
		return rlRoleId;
	}

	
	public String getRlRoleLongName()
	{
		return rlRoleLongName;
	}

	
	public String getRlRoleName()
	{
		return rlRoleName;
	}

	
	public String getRlRoleShortName()
	{
		return rlRoleShortName;
	}

	public String getRlRoleType()
	{
		return rlRoleType;
	}

	
	public Boolean getRlStatus()
	{
		return rlStatus;
	}

	
	public Set<IUserRoles> getUserRoleses()
	{
		return userRoleses;
	}

	
	public void setEnumKey(String enumKey)
	{
		this.enumKey = enumKey;
	}

	
	public void setRlCreatedBy(String rlCreatedBy)
	{
		this.rlCreatedBy = rlCreatedBy;
	}

	
	public void setRlCreatedDate(Timestamp rlCreatedDate)
	{
		this.rlCreatedDate = rlCreatedDate;
	}

	
	public void setRlModifiedBy(String rlModifiedBy)
	{
		this.rlModifiedBy = rlModifiedBy;
	}

	
	public void setRlModifiedDate(Timestamp rlModifiedDate)
	{
		this.rlModifiedDate = rlModifiedDate;
	}

	
	public void setRlRoleDescription(String rlRoleDescription)
	{
		this.rlRoleDescription = rlRoleDescription;
	}

	
	public void setRlRoleId(String rlRoleId)
	{
		this.rlRoleId = rlRoleId;
	}

	
	public void setRlRoleLongName(String rlRoleLongName)
	{
		this.rlRoleLongName = rlRoleLongName;
	}

	
	public void setRlRoleName(String rlRoleName)
	{
		this.rlRoleName = rlRoleName;
	}

	
	public void setRlRoleShortName(String rlRoleShortName)
	{
		this.rlRoleShortName = rlRoleShortName;
	}

	public void setRlRoleType(String rlRoleType)
	{
		this.rlRoleType = rlRoleType;
	}

	
	public void setRlStatus(Boolean rlStatus)
	{
		this.rlStatus = rlStatus;
	}

	
	public void setUserRoleses(Set<IUserRoles> userRoleses)
	{
		this.userRoleses = userRoleses;
	}

}