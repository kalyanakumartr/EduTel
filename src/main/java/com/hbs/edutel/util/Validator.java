package com.hbs.edutel.util;

public class Validator
{

	private static final int	_DIGIT_BEGIN	= 48;

	private static final int	_DIGIT_END		= 57;

	public static final char	LOWER_CASE_L	= 'l';

	public static final char	LOWER_CASE_N	= 'n';

	public static final char	LOWER_CASE_U	= 'u';

	public static final char	SPACE			= ' ';

	public static boolean isDigit(char c)
	{
		int x = c;

		if ((x >= _DIGIT_BEGIN) && (x <= _DIGIT_END))
		{
			return true;
		}

		return false;
	}

	public static boolean isNull(String s)
	{
		if (s == null)
		{
			return true;
		}

		int counter = 0;

		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);

			if (c == SPACE)
			{
				continue;
			}
			else if (counter > 3)
			{
				return false;
			}

			if (counter == 0)
			{
				if (c != LOWER_CASE_N)
				{
					return false;
				}
			}
			else if (counter == 1)
			{
				if (c != LOWER_CASE_U)
				{
					return false;
				}
			}
			else if ((counter == 2) || (counter == 3))
			{
				if (c != LOWER_CASE_L)
				{
					return false;
				}
			}

			counter++;
		}

		if ((counter == 0) || (counter == 4))
		{
			return true;
		}

		return false;
	}

}
