package com.hbs.edutel.common.action;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonValidator
{
	public static boolean isArrayFirstNotNull(Object[] objArr)
	{
		return isNotNullNotEmpty(objArr) && objArr.length > 0 && isNotNullNotEmpty(objArr[0]);
	}

	public static boolean isEqual(String strObject, String eqParam)
	{
		return isNotNullNotEmpty(strObject) && isNotNullNotEmpty(eqParam) && strObject.equalsIgnoreCase(eqParam);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isListFirstNotEmpty(List list)
	{
		return isNotNullNotEmpty(list) && isNotNullNotEmpty(list.iterator().next());
	}

	public static boolean isNotEqual(String strObject, String eqParam)
	{
		return isNotNullNotEmpty(strObject) && isNotNullNotEmpty(eqParam) && strObject.equalsIgnoreCase(eqParam) == false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotNullNotEmpty(Object object)
	{
		if (object instanceof String)
		{
			String objStr = (String) object;
			return objStr != null && objStr.trim().equals("") == false;
		}
		else if (object instanceof String[])
		{
			String[] objStr = (String[]) object;
			return objStr != null && objStr.length > 0;
		}
		else if (object instanceof Integer || object instanceof Long || object instanceof Double)
		{
			return isNotNullNotEmpty(String.valueOf(object));
		}
		else if (object instanceof List)
		{
			List objList = (List) object;
			return objList != null && objList.isEmpty() == false;
		}
		else if (object instanceof Set)
		{
			Set objSet = (Set) object;
			return objSet != null && objSet.isEmpty() == false;
		}
		else if (object instanceof Map)
		{
			Map objMap = (Map) object;
			return objMap != null && objMap.isEmpty() == false;
		}
		else if (object instanceof Collection<?>)
		{
			Collection<?> objSet = (Collection<?>) object;
			return objSet != null && objSet.isEmpty() == false;
		}
		else
			return object != null;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isSetFirstNotEmpty(Set set)
	{
		return isNotNullNotEmpty(set) && isNotNullNotEmpty(set.iterator().next());
	}
}
