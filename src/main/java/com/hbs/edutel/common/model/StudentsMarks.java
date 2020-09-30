package com.hbs.edutel.common.model;

import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.messagesalert.model.CommonBeanFields;

public class StudentsMarks extends CommonBeanFields
{
	private static final long	serialVersionUID	= 7058681949781726786L;
	protected String			usEmployeeId;
	protected String			usBatch;
	protected String			usHSCNo;
	protected String			usHSCMark;
	protected String			usSSorPUCMark;
	protected IUsers			users;

	public StudentsMarks()
	{
		super();
	}

	public StudentsMarks(String usEmployeeId, String usBatch)
	{
		super();
		this.usEmployeeId = usEmployeeId;
		this.usBatch = usBatch;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public String getUsBatch()
	{
		return usBatch;
	}

	public void setUsBatch(String usBatch)
	{
		this.usBatch = usBatch;
	}

	public String getUsHSCNo()
	{
		return usHSCNo;
	}

	public void setUsHSCNo(String usHSCNo)
	{
		this.usHSCNo = usHSCNo;
	}

	public String getUsHSCMark()
	{
		return usHSCMark;
	}

	public void setUsHSCMark(String usHSCMark)
	{
		this.usHSCMark = usHSCMark;
	}

	public String getUsSSorPUCMark()
	{
		return usSSorPUCMark;
	}

	public void setUsSSorPUCMark(String usSSorPUCMark)
	{
		this.usSSorPUCMark = usSSorPUCMark;
	}

	public IUsers getUsers()
	{
		return users;
	}

	public void setUsers(IUsers users)
	{
		this.users = users;
	}

}
