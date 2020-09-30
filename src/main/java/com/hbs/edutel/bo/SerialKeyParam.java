package com.hbs.edutel.bo;

import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.SerialKeyGenerate;

public class SerialKeyParam
{
	public String[]				serialKey;
	public SerialKeyGenerate	serialKeyGenerate;
	public String				serialKeyStatus;
	public String[]				serialNo;
	public String				usEmployeeIdList;
	public IUsers				user;

	public SerialKeyParam(String... serialKey)
	{
		super();
		this.serialKey = serialKey;
	}

}