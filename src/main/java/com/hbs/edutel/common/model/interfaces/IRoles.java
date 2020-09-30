package com.hbs.edutel.common.model.interfaces;

import java.sql.Timestamp;
import java.util.Set;

import com.hbs.edutel.util.common.consts.ConstInterface;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */

public interface IRoles extends java.io.Serializable, ConstInterface
{

	public String getEnumKey();

	public String getRlCreatedBy();

	public Timestamp getRlCreatedDate();

	public String getRlModifiedBy();

	public Timestamp getRlModifiedDate();

	public String getRlRoleDescription();

	public String getRlRoleId();

	public String getRlRoleLongName();

	public String getRlRoleName();

	public String getRlRoleShortName();

	public String getRlRoleType();

	public Boolean getRlStatus();

	public Set<IUserRoles> getUserRoleses();

	public void setEnumKey(String enumKey);

	public void setRlCreatedBy(String rlCreatedBy);

	public void setRlCreatedDate(Timestamp rlCreatedDate);

	public void setRlModifiedBy(String rlModifiedBy);

	public void setRlModifiedDate(Timestamp rlModifiedDate);

	public void setRlRoleDescription(String rlRoleDescription);

	public void setRlRoleId(String rlRoleId);

	public void setRlRoleLongName(String rlRoleLongName);

	public void setRlRoleName(String rlRoleName);

	public void setRlRoleShortName(String rlRoleShortName);

	public void setRlRoleType(String rlRoleType);

	public void setRlStatus(Boolean rlStatus);

	public void setUserRoleses(Set<IUserRoles> userRoleses);

}