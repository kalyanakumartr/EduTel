package com.hbs.edutel.action.login.factory;

import com.hbs.edutel.common.action.login.LoginAction;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;

public class EduTelApprovalAttribute
{

	public String assignApprovalUserAttribute(LoginAction loginVo)
	{
		loginVo.getUserBo().userLogAtLogin(loginVo.getUser(), loginVo.getRequest().getRemoteAddr());
		return EPage.Success.name();
	}

}
