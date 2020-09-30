package com.hbs.edutel.common.action.login.factory;

import com.hbs.edutel.util.common.consts.ConstInterface;

public abstract class ApprovalAttributeBaseData implements ApprovalInterface, ConstInterface
{
	private static final String	CNST_COMMA			= ",";
	private static final String	CNST_EMPTY			= "";
	private static final String	CNST_SPACE			= " ";
	private static final long	serialVersionUID	= -4934692752035346430L;

	public String getUsername(String appUserName, EName enumName)
	{
		try
		{
			String[] nameArr = appUserName.split(CNST_COMMA);

			switch ( enumName )
			{
				case FirstName :
				{
					if (nameArr.length > 1)
					{
						nameArr = nameArr[1].trim().split(CNST_SPACE);
						if (nameArr.length > 1)
							return nameArr[1];
					}
					return nameArr[0].trim();
				}
				case MiddleName :
				{
					if (nameArr.length > 1)
					{
						nameArr = nameArr[1].trim().split(CNST_SPACE);
						if (nameArr.length > 1)
							return nameArr[0].trim();
					}
					return CNST_EMPTY;
				}
				case LastName :
				{
					return nameArr[0].trim();
				}
				default :
					return CNST_EMPTY;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return CNST_EMPTY;
		}
	}
}
