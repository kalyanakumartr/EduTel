package com.hbs.edutel.action;

import com.hbs.edutel.model.StudentEnquiry;

public class AdminStudentEnquiryActionData extends EduTelCommonData
{
	private static final long	serialVersionUID	= 1L;
	protected StudentEnquiry	studentEnquiry		= new StudentEnquiry();
	protected String			usEmployeeId;

	public StudentEnquiry getStudentEnquiry()
	{
		return studentEnquiry;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public void setStudentEnquiry(StudentEnquiry studentEnquiry)
	{
		this.studentEnquiry = studentEnquiry;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}
}
