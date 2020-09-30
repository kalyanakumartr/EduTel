package com.hbs.edutel.common.action.login;

import java.util.HashMap;
import java.util.Map;

import com.hbs.edutel.common.action.CommonActionBoData;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.common.image.ImageDownload;
import com.hbs.edutel.util.common.image.factory.ImageDownloadFactory;

public class LoginActionData extends CommonActionBoData
{
	private static final long		serialVersionUID	= 6601285218584839532L;
	protected ImageDownload			imageDownload		= ImageDownloadFactory.getInstance().getImageDownloadInstance();
	protected String				loginOption;
	protected String				maMenuHTML			= null;
	protected String				passWord			= "";
	protected String				quesAnswer;
	protected Map<String, String>	reminderQues		= new HashMap<String, String>(0);
	protected String				remQuestion;
	protected IUsers				user;
	protected String				userId				= "";

	public ImageDownload getImageDownload()
	{
		return imageDownload;
	}

	public String getLoginOption()
	{
		return loginOption;
	}

	public String getMaMenuHTML()
	{
		return maMenuHTML;
	}

	public String getPassWord()
	{
		return passWord;
	}

	public String getQuesAnswer()
	{
		return quesAnswer;
	}

	public Map<String, String> getReminderQues()
	{
		return reminderQues;
	}

	public String getRemQuestion()
	{
		return remQuestion;
	}

	public IUsers getUser()
	{
		return user;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setImageDownload(ImageDownload imageDownload)
	{
		this.imageDownload = imageDownload;
	}

	public void setLoginOption(String loginOption)
	{
		this.loginOption = loginOption;
	}

	public void setMaMenuHTML(String maMenuHTML)
	{
		this.maMenuHTML = maMenuHTML;
	}

	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}

	public void setQuesAnswer(String quesAnswer)
	{
		this.quesAnswer = quesAnswer;
	}

	public void setReminderQues(Map<String, String> reminderQues)
	{
		this.reminderQues = reminderQues;
	}

	public void setRemQuestion(String remQuestion)
	{
		this.remQuestion = remQuestion;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	

}
