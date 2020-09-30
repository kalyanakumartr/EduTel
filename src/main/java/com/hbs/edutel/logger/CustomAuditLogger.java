package com.hbs.edutel.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbs.edutel.util.common.consts.ConstInterface;

public class CustomAuditLogger implements ConstInterface
{

	private static final long	serialVersionUID	= -8133287023950401124L;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public CustomAuditLogger(Class<?> clazz)
	{
		//super(clazz);
	}

	private String appendQuoteInLogData(Object data)
	{
		return String.valueOf(data).indexOf("'") > 0 ? String.valueOf(data).replaceAll("'", "''") : String.valueOf(data);
	}

	public void debug(String strEventId, Object msg1, Object msg2, Object msg3, Object createdBy)
	{
		logger.debug(strEventId, String.valueOf(msg1) +  String.valueOf(msg2) + String.valueOf(msg3) + createdBy);
	}

	public void error(String strEventId, Object msg1, Object msg2, Object msg3, Object createdBy)
	{
		logger.error(strEventId, String.valueOf(msg1) +  String.valueOf(msg2) + String.valueOf(msg3) + createdBy);
	}

	public void errorMCQ(String strEventId, Object msg1, Object msg2, Object msg3, Object createdBy)
	{
		logger.error(strEventId, String.valueOf(msg1) +  String.valueOf(msg2) + String.valueOf(msg3) + createdBy);
	}

	
	public void info(String strEventId, Object msg1, Object msg2, Object msg3, Object createdBy)
	{
		logger.info(strEventId, String.valueOf(msg1) +  String.valueOf(msg2) + String.valueOf(msg3) + createdBy);
	}

	public void infoMCQ(String strEventId, Object msg1, Object msg2, Object msg3, Object createdBy)
	{
		logger.info(strEventId,  String.valueOf(msg1) +  String.valueOf(msg2) + String.valueOf(msg3) + createdBy);
	}

}
