package com.hbs.messagesalert.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.CommonUtil;

public class MessagesUserMapping extends CommonBeanFields
{
	private static final long	serialVersionUID	= 1L;
	public static int			MAX_RETRY_COUNT		= 3;
	protected Integer			autoId;
	protected String			constructedMessage;
	protected String			messageId;
	protected Messages			messages;
	protected String			messageStatus;
	protected String			usEmployeeId;
	protected IUsers			user;
	protected Integer			retryCount			= 0;
	protected String			externalField;
	protected String			externalMessage;
	protected Blob				dataObject;

	public MessagesUserMapping()
	{
		super();
	}

	public Integer getAutoId()
	{
		return autoId;
	}

	public String getConstructedMessage()
	{
		return constructedMessage;
	}

	public String getMessageId()
	{
		return messageId;
	}

	public Messages getMessages()
	{
		return messages;
	}

	public String getMessageStatus()
	{
		return messageStatus;
	}

	public String getUsEmployeeId()
	{
		return usEmployeeId;
	}

	public IUsers getUser()
	{
		return user;
	}

	public void setAutoId(Integer autoId)
	{
		this.autoId = autoId;
	}

	public void setConstructedMessage(String constructedMessage)
	{
		this.constructedMessage = constructedMessage;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	public void setMessages(Messages messages)
	{
		this.messages = messages;
	}

	public void setMessageStatus(String messageStatus)
	{
		this.messageStatus = messageStatus;
	}

	public void setUsEmployeeId(String usEmployeeId)
	{
		this.usEmployeeId = usEmployeeId;
	}

	public void setUser(IUsers user)
	{
		this.user = user;
	}

	public Integer getRetryCount()
	{
		return retryCount;
	}

	public void setRetryCount(Integer retryCount)
	{
		this.retryCount = retryCount;
	}

	public String getExternalField()
	{
		return externalField;
	}

	public void setExternalField(String externalField)
	{
		this.externalField = externalField;
	}

	public String getExternalMessage()
	{
		return externalMessage;
	}

	public Blob getDataObject()
	{
		return dataObject;
	}

	public void setDataObject(Blob dataObject)
	{
		this.dataObject = dataObject;
	}

	public void setDataObjectSerialize(Object... objects) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SerialException, IOException, SQLException
	{
		this.dataObject = this.serialize(objects);
	}

	public void setExternalMessage(String externalMessage)
	{
		this.externalMessage = externalMessage;
	}

	@SuppressWarnings("unchecked")
	public Blob serialize(Object... obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException, SerialException, SQLException
	{
		HashMap<String, Object> hmBlob = new HashMap<String, Object>(0);
		for (Object object : obj)
		{
			if (object instanceof HashMap)
			{
				hmBlob.putAll((HashMap<String, Object>)object);
			}
			else
			{
				String clazzName = object.getClass().getSimpleName();
				extractValuesFromObject(object, hmBlob, clazzName);
			}
		}
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(hmBlob);
		return new SerialBlob(b.toByteArray());
	}

	public Object deserialize(Blob blob) throws IOException, ClassNotFoundException, SQLException
	{
		ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
		return ois.readObject();
	}

	private void extractValuesFromObject(Object obj, HashMap<String, Object> hmBlob, String clazzName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		String[] clazzes = Message_Tracking_Class.split(",");
		List<String> clazzList = Arrays.asList(clazzes);
		boolean clazzDepth = clazzName.split("\\.").length < 5; // Users.serialKey.serialKeyUserMap.users.usUserName

		if (clazzDepth)
		{
			for (Field field : obj.getClass().getDeclaredFields())
			{
				String mtdGetterName1 = GET + field.getName().toUpperCase();
				String mtdGetterName2 = IS + field.getName().toUpperCase();
				for (Method method : obj.getClass().getDeclaredMethods())
				{
					if (method.getName().equalsIgnoreCase(mtdGetterName1) || method.getName().equalsIgnoreCase(mtdGetterName2))
					{
						Object value = method.invoke(obj, new Object[] {});
						if (value != null)
						{
							if (value instanceof String || value instanceof Integer || value instanceof Float || value instanceof Long || value instanceof Boolean || value instanceof Character
									|| value instanceof Double || value instanceof Short)
							{
								hmBlob.put(clazzName.replaceAll("\\.", "_") + UNDERSCORE + field.getName(), value);
								break;
							}
							else if (value instanceof Timestamp)
							{
								String fmtDateTime = CommonUtil.getDateInFormat((Timestamp)value, DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS_AM_PM);
								hmBlob.put(clazzName.replaceAll("\\.", "_") + UNDERSCORE + field.getName(), fmtDateTime);
								break;
							}
							else if (clazzList.contains(value.getClass().getSimpleName()))
							{
								extractValuesFromObject(value, hmBlob, clazzName + DOT + field.getName());
							}
						}
						else
						{
							break;
						}
					}
				}

			}
		}
	}
}