package com.hbs.edutel.common.action.login.factory;

import com.hbs.edutel.common.action.login.LoginAction;
import com.hbs.edutel.common.exception.CustomException;

public interface ApprovalInterface
{
	public enum EName
	{
		FirstName, LastName, MiddleName;
	}

	public String assignApprovalUserAttribute(LoginAction commonVo) throws CustomException;
}
