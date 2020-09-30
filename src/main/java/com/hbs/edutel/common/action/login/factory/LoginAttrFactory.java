package com.hbs.edutel.common.action.login.factory;

import com.hbs.edutel.action.login.factory.EduTelApprovalAttribute;
import com.hbs.edutel.common.action.login.LoginAction;
import com.hbs.edutel.common.exception.CustomException;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class LoginAttrFactory implements ConstInterface
{
	private static final long	serialVersionUID	= -5015756101813447114L;

	public String assignLoginUserAttributes(LoginAction loginVo) throws CustomException
	{

		if (loginVo.getUser() == null || loginVo.getUser().isUsStatus() == false)
		{
			throw new CustomException(loginVo.getUser().getUsUserName() + " is not an Active User.");
		}

		return new EduTelApprovalAttribute().assignApprovalUserAttribute(loginVo);
	}

}
