package com.hbs.edutel.model;

import java.io.Serializable;

import com.hbs.edutel.common.model.interfaces.IUsers;

public class SerialKeyUserMapping implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String				serialKey;
	private SerialKeyGenerate	serialKeyGenerate;
	private IUsers				users;

	public SerialKeyUserMapping()
	{
	}

	public SerialKeyUserMapping(String serialKey, IUsers users, SerialKeyGenerate serialKeyGenerate)
	{
		super();
		this.serialKey = serialKey;
		this.users = users;
		this.serialKeyGenerate = serialKeyGenerate;
	}

	public String getSerialKey()
	{
		return serialKey;
	}

	public void setSerialKey(String serialKey)
	{
		this.serialKey = serialKey;
	}

	public SerialKeyGenerate getSerialKeyGenerate()
	{
		return serialKeyGenerate;
	}

	public IUsers getUsers()
	{
		return users;
	}

	public void setSerialKeyGenerate(SerialKeyGenerate serialKeyGenerate)
	{
		this.serialKeyGenerate = serialKeyGenerate;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

}
