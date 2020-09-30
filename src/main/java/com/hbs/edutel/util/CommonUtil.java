package com.hbs.edutel.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class CommonUtil implements ConstInterface
{

	private static final long	serialVersionUID	= 5975112215738796905L;

	public static String beginUpperCase(String value)
	{
		StringBuffer result = new StringBuffer();

		String[] docArray = value.split(" ");
		for (String str : docArray)
		{
			if (str.length() > 0)
			{
				char[] stringArray = str.trim().toCharArray();
				stringArray[0] = Character.toUpperCase(stringArray[0]);
				str = new String(stringArray);

				result.append(str).append(" ");
			}
		}

		return result.toString().trim();
	}

	public static StringBuffer checkStringEndsWithDot(StringBuffer addressBuffer)
	{
		StringBuffer address = new StringBuffer();

		if (CommonValidator.isNotNullNotEmpty(addressBuffer.toString()))
		{

			if (addressBuffer.toString().endsWith("."))
			{
				address = addressBuffer;
			}
			else
			{
				address = new StringBuffer(addressBuffer.toString().substring(0, addressBuffer.toString().length() - 2) + ".");
			}
			return address;
		}
		return addressBuffer;
	}

	public static Timestamp convertDateTime(String time)
	{
		StringBuffer exDate = new StringBuffer("");
		StringBuffer exTime = new StringBuffer("");

		if (CommonValidator.isNotNullNotEmpty(time) && time.length() < 20)
		{

			String[] splitSpace = time.split(" ");
			for (int i = 0; i < splitSpace[0].split("-").length; i++)
			{
				if (splitSpace[0].split("-")[i].length() == 1)
				{
					exDate.append("0" + splitSpace[0].split("-")[i] + "-");
				}
				else
				{
					exDate.append(splitSpace[0].split("-")[i] + "-");
				}
			}

			for (int i = 0; i < splitSpace[1].split(":").length; i++)
			{

				if (splitSpace[1].split(":")[i].length() == 1)
				{
					exTime.append("0" + splitSpace[1].split(":")[i] + ":");
				}
				else
				{
					exTime.append(splitSpace[1].split(":")[i] + ":");
				}
			}

		}
		String result = exDate.toString().substring(0, 10) + " " + exTime.toString().substring(0, 8);
		Timestamp timeStamp = getTimeZoneDateInFormat(result, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST);
		return timeStamp;

	}

	public static String getDateInFormat(Date date, String dateFormat)
	{
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);
		return ((dateFormatType != null) ? dateFormatType.format(date) : "");
	}

	public static String getDateInFormat(Timestamp timeStamp, String dateFormat)
	{
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);

		return ((dateFormatType != null) ? dateFormatType.format(new Date(timeStamp.getTime())) : "");
	}

	public static Date getDateInFormat(String dateStr, String dateFormat)
	{
		try
		{
			return new SimpleDateFormat(dateFormat).parse(dateStr);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public static String getDateStringInFormat(String dateStr, String inDateFormat, String outDateFormat)
	{
		try
		{
			Date parseDate = new SimpleDateFormat(inDateFormat).parse(dateStr);
			return new SimpleDateFormat(outDateFormat).format(parseDate);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public static String getDecimalValueofAmt(Double amount)
	{
		if (CommonValidator.isNotNullNotEmpty(amount))
		{
			return new DecimalFormat(DECIMAL_FORMAT_0_00).format(amount);
		}
		return "";
	}

	public static String getExamNameFull(String examName, String subject, String chapter, int maxLimit)
	{
		String examNameFull = null;
		if (CommonValidator.isNotNullNotEmpty(examName))
		{
			examNameFull = examName;
		}
		if (CommonValidator.isNotNullNotEmpty(subject))
		{
			examNameFull = examNameFull + " - " + subject;
		}
		if (CommonValidator.isNotNullNotEmpty(chapter))
		{
			examNameFull = examNameFull + " - Chapter " + chapter;
		}
		if (CommonValidator.isNotNullNotEmpty(examNameFull))
		{
			StringBuffer examNameBuffer = new StringBuffer("");

			for (int index = 0; index < examNameFull.length(); index += maxLimit)
			{
				examNameBuffer.append(examNameFull.substring(index, Math.min(examNameFull.length(), index + maxLimit)));
				examNameBuffer.append("<br>");
			}
			return examNameBuffer.toString().trim();
		}
		return "";
	}

	public static long getExamTiming(String startTime, String endTime)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = null;
		Date endDate = null;
		long differenceInSeconds = 0;
		if (CommonValidator.isNotNullNotEmpty(startTime) && CommonValidator.isNotNullNotEmpty(endTime))
		{
			try
			{
				startDate = format.parse(startTime);
				endDate = format.parse(endTime);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			long timeDifference = endDate.getTime() - startDate.getTime();
			differenceInSeconds = timeDifference / 1000;
		}
		return differenceInSeconds;
	}

	public static JQueryDataTableParam getParam(HttpServletRequest request, String columnNames)
	{
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("sEcho")))
		{
			JQueryDataTableParam param = new JQueryDataTableParam();
			param.sEcho = request.getParameter("sEcho");
			param.sSearch = request.getParameter("sSearch");
			param.sColumns = request.getParameter("sColumns");

			if (CommonValidator.isNotNullNotEmpty(request.getParameter("iDisplayStart")))
				param.iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));

			if (CommonValidator.isNotNullNotEmpty(request.getParameter("iDisplayLength")))
				param.iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));

			if (CommonValidator.isNotNullNotEmpty(request.getParameter("iColumns")))
				param.iColumns = Integer.parseInt(request.getParameter("iColumns"));

			if (CommonValidator.isNotNullNotEmpty(request.getParameter("iSortingCols")))
				param.iSortingCols = Integer.parseInt(request.getParameter("iSortingCols"));

			if (CommonValidator.isNotNullNotEmpty(request.getParameter("iSortCol_0")))
				param.iSortColumnIndex = Integer.parseInt(request.getParameter("iSortCol_0"));

			param.sSortDirection = request.getParameter("sSortDir_0");

			param.queryTableDisplay(columnNames);
			return param;
		}
		else
			return null;
	}

	public static Timestamp getTimeZoneDateInFormat(Date date, String dateFormat, String timeZone)
	{
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);
		if (CommonValidator.isNotNullNotEmpty(timeZone))
			dateFormatType.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
		return Timestamp.valueOf(((dateFormatType != null) ? dateFormatType.format(date) : ""));
	}

	public static Timestamp getTimeZoneDateInFormat(String date, String dateFormat, String timeZone)
	{
		Date newDate = getDateInFormat(date, dateFormat);
		DateFormat dateFormatType = new SimpleDateFormat(dateFormat);
		if (CommonValidator.isNotNullNotEmpty(timeZone))
			dateFormatType.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
		return Timestamp.valueOf(((dateFormatType != null) ? dateFormatType.format(newDate) : ""));
	}

	public static Timestamp getTimeZoneDateInFormat(String date, String fromFormat, String toFormat, String timeZone)
	{
		Date newDate = getDateInFormat(date, fromFormat);
		DateFormat toFormatType = new SimpleDateFormat(toFormat);
		if (CommonValidator.isNotNullNotEmpty(timeZone))
			toFormatType.setTimeZone(TimeZone.getTimeZone(timeZone.toUpperCase()));
		return Timestamp.valueOf(toFormatType.format(newDate));
	}

	public static String initCapsName(String name)
	{
		if (CommonValidator.isNotNullNotEmpty(name))
		{
			char[] nameArr = name.toLowerCase().toCharArray();
			int i = 0;
			boolean capFlag = false;
			StringBuffer sbName = new StringBuffer();
			char appendChar = ' ';
			while ( i < nameArr.length )
			{
				if (i == 0)
					sbName.append(String.valueOf(nameArr[i]).toUpperCase());
				else if (nameArr[i] == ' ' || nameArr[i] == ',' || nameArr[i] == '-' || nameArr[i] == '_' || nameArr[i] == '.' || nameArr[i] == '#' || nameArr[i] == '(' || nameArr[i] == '$')
				{
					appendChar = nameArr[i];
					capFlag = true;
				}
				else if (capFlag)
				{
					sbName.append(appendChar + String.valueOf(nameArr[i]).toUpperCase());
					appendChar = ' ';
					capFlag = false;
				}
				else
					sbName.append(nameArr[i]);
				i++;
			}
			return sbName.toString();
		}
		return "";
	}

	public static String replaceSingleQuotes(String fieldValue)
	{

		if (fieldValue != null)
		{
			fieldValue = fieldValue.replace("'", "''");
		}

		return fieldValue;

	}

	public static List<LabelValueBean> getLabelValueList(String label)
	{
		List<String> lblList = Arrays.asList(label.split(COMMA_SPACE.trim()));
		List<LabelValueBean> lableValueList = new ArrayList<LabelValueBean>();
		for (String strLable : lblList)
		{
			lableValueList.add(new LabelValueBean(strLable, strLable));
		}
		return lableValueList;
	}

	public static List<LabelValueBean> getFileNameLabelValueList(List<OnlineTestSeriesExamQuestionAnswerMapping> onlineExamQuestionsAnswersList)
	{
		List<LabelValueBean> lableValueList = new ArrayList<LabelValueBean>();
		for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesExamQuestionAns : onlineExamQuestionsAnswersList)
		{
			if (CommonValidator.isNotNullNotEmpty(oeTestSeriesExamQuestionAns.getUploadFileName()))
				lableValueList.add(new LabelValueBean(oeTestSeriesExamQuestionAns.getUploadFileName(), String.valueOf(oeTestSeriesExamQuestionAns.getOeExamTestSeriesAutoId())));
			else
				lableValueList.add(new LabelValueBean("Courier By " + oeTestSeriesExamQuestionAns.getCreatedBy(), String.valueOf(oeTestSeriesExamQuestionAns.getOeExamTestSeriesAutoId())));
		}
		return lableValueList;
	}
}
