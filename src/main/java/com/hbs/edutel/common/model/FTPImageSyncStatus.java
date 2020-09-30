package com.hbs.edutel.common.model;

public class FTPImageSyncStatus implements java.io.Serializable
{
	private static final long	serialVersionUID	= 9115397778210112470L;
	private int					inImageSyncId;
	private String				inImageSyncName;
	private String				inImageSyncStatus;

	public FTPImageSyncStatus()
	{

	}

	public FTPImageSyncStatus(String inImageSyncStatus)
	{
		this.inImageSyncStatus = inImageSyncStatus;
	}

	public int getInImageSyncId()
	{
		return inImageSyncId;
	}

	public void setInImageSyncId(int inImageSyncId)
	{
		this.inImageSyncId = inImageSyncId;
	}

	public String getInImageSyncName()
	{
		return inImageSyncName;
	}

	public void setInImageSyncName(String inImageSyncName)
	{
		this.inImageSyncName = inImageSyncName;
	}

	public String getInImageSyncStatus()
	{
		return inImageSyncStatus;
	}

	public void setInImageSyncStatus(String inImageSyncStatus)
	{
		this.inImageSyncStatus = inImageSyncStatus;
	}

}
