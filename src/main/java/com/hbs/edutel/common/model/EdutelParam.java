package com.hbs.edutel.common.model;

import com.hbs.edutel.model.College;
import com.hbs.edutel.model.School;

public class EdutelParam
{
	public String	clCollegeId;
	public College	college	= new College();
	public School	school	= new School();
	public String	scSchoolId;
	public String	usEmployeeId;
	public Users	users	= new Users();

	public College getCollege()
	{
		return college;
	}

	public School getSchool()
	{
		return school;
	}

	public String getScSchoolId()
	{
		return scSchoolId;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public Users getUsers()
	{
		return users;
	}

	public void setCollege(College college)
	{
		this.college = college;
	}

	public void setSchool(School school)
	{
		this.school = school;
	}

	public void setScSchoolId(String scSchoolId)
	{
		this.scSchoolId = scSchoolId;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

}
