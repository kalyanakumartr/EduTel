package com.hbs.edutel.common.action.login;

import java.util.ArrayList;
import java.util.List;

public class DataTableObject
{

	public List<ArrayList<String>>	aaData;

	public int		iTotalDisplayRecords;

	public int		iTotalRecords;

	public String	sColumns;

	public String	sEcho;

	public List<ArrayList<String>> getAaData()
	{
		return aaData;
	}

	public int getiTotalDisplayRecords()
	{
		return iTotalDisplayRecords;
	}

	public int getiTotalRecords()
	{
		return iTotalRecords;
	}

	public String getsColumns()
	{
		return sColumns;
	}

	public String getsEcho()
	{
		return sEcho;
	}

	public void setAaData(List<ArrayList<String>> aaData)
	{
		this.aaData = aaData;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords)
	{
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public void setiTotalRecords(int iTotalRecords)
	{
		this.iTotalRecords = iTotalRecords;
	}

	public void setsColumns(String sColumns)
	{
		this.sColumns = sColumns;
	}

	public void setsEcho(String sEcho)
	{
		this.sEcho = sEcho;
	}

}