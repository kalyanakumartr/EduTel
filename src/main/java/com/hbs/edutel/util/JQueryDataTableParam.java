package com.hbs.edutel.util;

import java.util.HashMap;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.util.common.ConstEnumUtil.EFormType;

public class JQueryDataTableParam
{

	public EFormType				eFormType;
	public int						iColumns;
	public int						iDisplayLength;
	public int						iDisplayStart;
	public boolean					isLibrary			= false;
	public boolean					isOnlineAssessment	= false;
	public int						iSortColumnIndex;
	public int						iSortingCols;
	public String					oeAssignedExamDate;
	public String					oeBluePrintId;
	public String					oeBookId;
	public String					oeChapter;
	public int						oeExamAutoId;
	public String					oeExamId;
	public String					oeExamTestSeriesAutoId;
	public String					oeKeyPointsId;
	public String					oeQuestionId;
	public String					oeSchoolType;
	public String					oeSubject;
	public String					oeValidate;
	public String					sColumns;
	public String					sEcho;
	public String					sSearch;
	public String					sSortDirection		= "desc";
	public String					status				= " True ";
	public String					subQuery			= "";
	public String					uploadFileName;
	public String					usEmployeeId;
	public HashMap<String, String>	userColumnMap		= new HashMap<String, String>(0);
	public String					usUsersType;
	public HashMap<String, String>	columnFilterMap		= new HashMap<String, String>(0);
	public String[]					sSearchArr;
	public String					studentBatch;
	public String					oeQuestionType;
	public boolean					isDownloaded		= false;
	public String					qtSubject;
	public JQueryDataTableParam()
	{
		super();
	}

	public JQueryDataTableParam(int oeExamAutoId)
	{
		super();
		this.oeExamAutoId = oeExamAutoId;
	}

	public JQueryDataTableParam(String sSearch)
	{
		super();
		this.sSearch = sSearch;
	}

	public JQueryDataTableParam(String sEcho, String sSearch, String sColumns, int iDisplayStart, int iDisplayLength, int iColumns, int iSortingCols, int iSortColumnIndex, String sSortDirection)
	{
		super();
		this.sEcho = sEcho;
		this.sSearch = sSearch;
		this.sColumns = sColumns;
		this.iDisplayStart = iDisplayStart;
		this.iDisplayLength = iDisplayLength;
		this.iColumns = iColumns;
		this.iSortingCols = iSortingCols;
		this.iSortColumnIndex = iSortColumnIndex;
		this.sSortDirection = sSortDirection;
	}

	public HashMap<String, String> queryTableDisplay(String columnNames)
	{

		HashMap<String, String> columnMap = new HashMap<String, String>(0);
		if (CommonValidator.isNotNullNotEmpty(columnNames))
		{
			String[] colNameMapArr = columnNames.split(",");
			for (int i = 0; i < colNameMapArr.length; i++)
			{
				String[] colMap = colNameMapArr[i].split("-");
				columnMap.put(String.valueOf(i + 1), colMap[1]);
			}
		}
		return columnMap;
	}

	public HashMap<String, String> userColumnMapping(String... columnValues)
	{

		if (columnValues != null && columnValues.length > 0)
		{
			String[] sSearchArr = sSearch.split("#");
			for (int i = 0; i < sSearchArr.length; i++)
			{
				userColumnMap.put(columnValues[i], sSearchArr[i]);
			}
		}

		return userColumnMap;

	}

	public HashMap<String, String> AdvanceSearchFilter(String... columnNames)
	{

		if (CommonValidator.isNotNullNotEmpty(sSearch))
		{
			String[] sSearchArr = sSearch.split("#");
			for (int i = 0; i < sSearchArr.length; i++)
			{
				columnFilterMap.put(columnNames[i], sSearchArr[i]);
			}
		}

		return columnFilterMap;

	}

	public String[] globalSearchFilter()
	{

		if (CommonValidator.isNotNullNotEmpty(sSearch))
		{
			sSearchArr = sSearch.split("#");
		}

		return sSearchArr;

	}
}
