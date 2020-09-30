package com.hbs.edutel.logger;

import java.sql.Timestamp;

public interface IAuditLogging
{
	public String getCreatedBy();

	public Timestamp getCreatedDate();

	public String getEventId();

	public String getLevel();

	public long getLoggingAutoId();

	public String getMessage();

	public String getMessage1();

	public String getMessage2();

	public String getModifiedBy();

	public Timestamp getModifiedDate();

	public void setCreatedBy(String createdBy);

	public void setCreatedDate(Timestamp createdDate);

	public void setEventId(String eventId);

	public void setLevel(String level);

	public void setLoggingAutoId(long loggingAutoId);

	public void setMessage(String message);

	public void setMessage1(String message1);

	public void setMessage2(String message2);

	public void setModifiedBy(String modifiedBy);

	public void setModifiedDate(Timestamp modifiedDate);

}
