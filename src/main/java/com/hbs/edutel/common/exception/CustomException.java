package com.hbs.edutel.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CustomException extends Exception
{
	private static final long	serialVersionUID	= -3033215444998388428L;
	private String				logExcepType;
	private String				logMessage			= null;
	private String				logMessageTrace;

	public CustomException()
	{

	}

	public CustomException(String logMessage)
	{
		this.logMessage = logMessage;
	}

	public CustomException(String logMessage, String logExcepType)
	{
		this.logMessage = logMessage;
		this.logExcepType = logExcepType;
	}

	public String getExceptionTraceMessage()
	{
		StringWriter logMessageWriter = new StringWriter();
		this.printStackTrace(new PrintWriter(logMessageWriter));
		return (logMessageWriter == null ? "" : logMessageWriter.toString());
	}

	public String getLogExcepType()
	{
		return logExcepType;
	}

	public String getLogMessage()
	{
		return logMessage;
	}

	public String getLogMessageTrace()
	{
		return logMessageTrace;
	}

	public void setLogExcepType(String logExcepType)
	{
		this.logExcepType = logExcepType;
	}

	public void setLogMessage(String logMessage)
	{
		this.logMessage = logMessage;
	}

	public void setLogMessageTrace(String logMessageTrace)
	{
		this.logMessageTrace = logMessageTrace;
	}

}
