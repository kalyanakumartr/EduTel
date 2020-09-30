package com.hbs.edutel.common.action.login.factory;

import com.hbs.edutel.common.action.login.ChangePasswordAction;
import com.hbs.edutel.common.action.login.ForgotPasswordAction;
import com.hbs.edutel.common.action.login.LoginAction;
import com.hbs.edutel.common.exception.CustomException;

public interface LoginFactoryInterface
{
	public String getPasswordRetriveSecurityQuestion(ForgotPasswordAction forgotVo) throws CustomException;

	public String initiateAuthentication(LoginAction loginVo) throws CustomException;

	public String initiateDeAuthentication(LoginAction loginVo) throws CustomException;

	public String resetUserPassword(ForgotPasswordAction forgotVo) throws CustomException;

	public String saveReminderQuestions(LoginAction loginVo) throws CustomException;

	public String updateUserPassword(ChangePasswordAction changeVo) throws CustomException;
}
