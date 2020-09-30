package com.hbs.edutel.common.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.hbs.edutel.util.EduTelMessageService;
import com.hbs.edutel.util.EmailSenderParam;
import com.hbs.edutel.util.common.consts.ConstInterface;
import com.hbs.edutel.util.common.property.factory.PropFactory;
import com.opensymphony.xwork2.ActionSupport;

public abstract class CommonActionData extends ActionSupport implements ServletRequestAware, ServletResponseAware, ConstInterface
{
	private static final long		serialVersionUID	= 6510195818200641145L;
	protected String				columnsDisplay;
	protected EduTelMessageService	eduTelMessageService;
	protected EmailSenderParam[]	emailParam			= null;
	protected PropFactory			propFactory;
	protected HttpServletRequest	request;
	protected HttpServletResponse	response;
	protected HttpSession			session;

	public String getColumnsDisplay()
	{
		return columnsDisplay;
	}

	public Object getDataObject(Object object, Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if (object != null)
		{
			Class<?> dataClass = object.getClass();
			Field[] fields = clazz.getSuperclass().getDeclaredFields();

			Object returnObj = clazz.newInstance();
			for (Field field : fields)
			{
				String mtdGetterName1 = "GET" + field.getName().toUpperCase();
				String mtdGetterName2 = "IS" + field.getName().toUpperCase();
				String mtdSetterName = "SET" + field.getName().toUpperCase();

				for (Method method : dataClass.getMethods())
				{
					if (method.getName().equalsIgnoreCase(mtdGetterName1) || method.getName().equalsIgnoreCase(mtdGetterName2))
					{
						Object value = method.invoke(object, new Object[] {});
						for (Method methodSetter : clazz.getMethods())
						{
							if (methodSetter.getName().equalsIgnoreCase(mtdSetterName))
							{
								methodSetter.invoke(returnObj, new Object[] { value });
								break;
							}
						}
						break;
					}
				}
			}
			return returnObj;
		}
		else
			return null;
	}

	public EduTelMessageService getEduTelMessageService()
	{
		return eduTelMessageService;
	}

	public EmailSenderParam[] getEmailParam()
	{
		return emailParam;
	}

	public Object getFieldValue(String fieldName, Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if (fieldName != null)
		{
			String mtdGetterName = "GET" + fieldName;
			String mtdGetterName1 = "IS" + fieldName;
			for (Method method : object.getClass().getMethods())
			{
				if (CommonValidator.isEqual(method.getName(), mtdGetterName) || CommonValidator.isEqual(method.getName(), mtdGetterName1))
				{
					return method.invoke(object, new Object[] {});
				}
			}
		}
		return null;
	}

	public PropFactory getPropFactory()
	{
		return propFactory;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public HttpSession getSession()
	{
		return session;
	}

	public void setColumnsDisplay(String columnsDisplay)
	{
		this.columnsDisplay = columnsDisplay;
	}

	public void setEduTelMessageService(EduTelMessageService eduTelMessageService)
	{
		this.eduTelMessageService = eduTelMessageService;
	}

	public void setEmailParam(EmailSenderParam[] emailParam)
	{
		this.emailParam = emailParam;
	}

	public void setPropFactory(PropFactory propFactory)
	{
		this.propFactory = propFactory;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;

	}

	public void setSession(HttpSession session)
	{
		this.session = session;
	}

}