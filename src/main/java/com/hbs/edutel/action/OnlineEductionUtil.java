package com.hbs.edutel.action;

import java.util.ArrayList;
import java.util.List;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.common.ConstEnumUtil.EChapter;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;

public abstract class OnlineEductionUtil extends EduTelCommonData
{
	private static final long		serialVersionUID	= 1L;
	protected List<LabelValueBean>	chapterList			= new ArrayList<LabelValueBean>(0);
	protected String				downloadFileUrl;
	protected List<String>			oeChapter			= new ArrayList<String>(0);
	protected String				oeSchoolType;
	protected String				oeSubject;
	protected String				overallSubjects;
	protected List<LabelValueBean>	subjectList			= new ArrayList<LabelValueBean>(0);

	public OnlineEductionUtil()
	{
		super();
	}

	protected String generateSubjectsListComboValues()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());

		subjectList.clear();

		if (sessionUser.isStudent() && CommonValidator.isNotNullNotEmpty(sessionUser.getUsGroupName()))
		{
			String[] subjectArray = sessionUser.getUsGroupName().split(COMMA_SPACE.trim());
			for (String subject : subjectArray)
			{
				subjectList.add(new LabelValueBean(subject, subject));
				oeSchoolType = sessionUser.getUsBoardName();
			}
			return EPage.Student.name();
		}
		else
		{
			chapterList.clear();
			chapterList = EChapter.getChaptersComboList();

			String[] subjectArray = overallSubjects.split(COMMA_SPACE.trim());
			for (String subject : subjectArray)
			{
				subjectList.add(new LabelValueBean(subject, subject));
			}
			return EPage.Success.name();
		}
	}

	public List<LabelValueBean> getChapterList()
	{
		return chapterList;
	}

	public String getDownloadFileUrl()
	{
		return downloadFileUrl;
	}

	public List<String> getOeChapter()
	{
		return oeChapter;
	}

	public String getOeSchoolType()
	{
		return oeSchoolType;
	}

	public String getOeSubject()
	{
		return oeSubject;
	}

	public String getOverallSubjects()
	{
		return overallSubjects;
	}

	public List<LabelValueBean> getSubjectList()
	{
		return subjectList;
	}

	public void setChapterList(List<LabelValueBean> chapterList)
	{
		this.chapterList = chapterList;
	}

	public void setDownloadFileUrl(String downloadFileUrl)
	{
		this.downloadFileUrl = downloadFileUrl;
	}

	public void setOeChapter(List<String> oeChapter)
	{
		this.oeChapter = oeChapter;
	}

	public void setOeSchoolType(String oeSchoolType)
	{
		this.oeSchoolType = oeSchoolType;
	}

	public void setOeSubject(String oeSubject)
	{
		this.oeSubject = oeSubject;
	}

	public void setOverallSubjects(String overallSubjects)
	{
		this.overallSubjects = overallSubjects;
	}

	public void setSubjectList(List<LabelValueBean> subjectList)
	{
		this.subjectList = subjectList;
	}

}