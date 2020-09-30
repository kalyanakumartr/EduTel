/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved. Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions: The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package com.hbs.edutel.util;

/**
 * <a href="PwdGenerator.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class PwdGenerator
{

	public static String	KEY1	= "0123456789";

	public static String	KEY2	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String	KEY3	= "abcdefghijklmnopqrstuvwxyz";

	private static String _getPassword(String key, int length, boolean useAllKeys)
	{

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++)
		{
			sb.append(key.charAt((int) (Math.random() * key.length())));
		}

		String password = sb.toString();

		if (!useAllKeys)
		{
			return password;
		}

		boolean invalidPassword = false;

		if (key.contains(KEY1))
		{
			if (Validator.isNull(extractDigits(password)))
			{
				invalidPassword = true;
			}
		}

		if (key.contains(KEY2))
		{
			if (password.equals(password.toLowerCase()))
			{
				invalidPassword = true;
			}
		}

		if (key.contains(KEY3))
		{
			if (password.equals(password.toUpperCase()))
			{
				invalidPassword = true;
			}
		}

		if (invalidPassword)
		{
			return _getPassword(key, length, useAllKeys);
		}

		return password;
	}

	public static String extractDigits(String s)
	{
		if (s == null)
		{
			return "";
		}

		StringBuilder sb = new StringBuilder();

		char[] charArray = s.toCharArray();

		for (int i = 0; i < charArray.length; i++)
		{
			if (Validator.isDigit(charArray[i]))
			{
				sb.append(charArray[i]);
			}
		}

		return sb.toString();
	}

	public static String getPassword()
	{
		return getPassword(8);
	}

	public static String getPassword(int length)
	{
		return _getPassword(KEY1 + KEY2 + KEY3, length, true);
	}

	public static String getPassword(String key, int length)
	{
		return getPassword(key, length, true);
	}

	public static String getPassword(String key, int length, boolean useAllKeys)
	{

		return _getPassword(key, length, useAllKeys);
	}

	public static String getPinNumber()
	{
		return _getPassword(KEY1, 4, true);
	}

}