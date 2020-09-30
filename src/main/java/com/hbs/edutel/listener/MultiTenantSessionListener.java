package com.hbs.edutel.listener;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hbs.edutel.common.bo.UserBo;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.image.factory.ImageDownloadFactory;

public class MultiTenantSessionListener implements HttpSessionListener, Serializable
{
	private static final String	EVT_SESSION_00000	= "EVT_SESSION_00000";

	private static final long	serialVersionUID	= 1L;

	private CustomAuditLogger	caLogger			= new CustomAuditLogger(MultiTenantSessionListener.class);

	
	public void sessionCreated(HttpSessionEvent event)
	{
		caLogger.info(EVT_SESSION_00000, "Session Created : " + new Date(), "Session Id : " + event.getSession().getId(), MultiTenantSessionListener.class.getName(), null);
	}

	
	public void sessionDestroyed(HttpSessionEvent event)
	{
		HttpSession session = event.getSession();

		if (session != null)
		{
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
			IUsers user = (IUsers) session.getAttribute(ESession.UserObject.getAttribute());

			try
			{
				String userId = "";
				if (user != null)
				{
					UserBo userBo = (UserBo) wac.getBean("userBo");
					userBo.userLogAtLogOut(user);
					userId = user.getUsEmployeeId();
				}
				caLogger.info(EVT_SESSION_00000, "sessionDestroyed" + new Date(), "User Id : " + userId + " Session Id : " + session.getId(), MultiTenantSessionListener.class.getName(), null);
			}
			catch (Exception excep)
			{
				caLogger.error(EVT_SESSION_00000, "sessionDestroyed", excep.getMessage(), MultiTenantSessionListener.class.getName(), null);
			}
			ImageDownloadFactory.getInstance().getImageDownloadInstance().deleteFiles(session.getServletContext().getRealPath("/content") + "/" + session.getId());
		}
	}

}
