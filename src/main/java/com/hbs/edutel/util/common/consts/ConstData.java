package com.hbs.edutel.util.common.consts;

import java.util.HashMap;

import com.hbs.edutel.util.common.consts.bo.ConstantBo;

public class ConstData
{
	private ConstantBo						constantBo;

	private HashMap<String, ConstInterface>	constHM	= null;

	public ConstData()
	{
		super();
	}

	public ConstantBo getConstantBo()
	{
		return constantBo;
	}

	public HashMap<String, ConstInterface> getConstHM()
	{
		return constHM;
	}

	public void setConstantBo(ConstantBo constantBo)
	{
		this.constantBo = constantBo;
	}

	public void setConstHM(HashMap<String, ConstInterface> constHM)
	{
		this.constHM = constHM;
	}
}