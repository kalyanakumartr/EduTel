package com.hbs.edutel.util.common.consts.bo.impl;

import java.util.List;

import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.consts.bo.ConstantBo;
import com.hbs.edutel.util.common.consts.dao.ConstantDAO;

public class ConstantBoImpl implements ConstantBo
{
	private ConstantDAO	constantDAO;

	public ConstantDAO getConstantDAO()
	{
		return constantDAO;
	}

	
	public List<ConstInterface> getRolesList()
	{
		return getConstantDAO().getRolesList();
	}

	public void setConstantDAO(ConstantDAO constantDAO)
	{
		this.constantDAO = constantDAO;
	}
}
