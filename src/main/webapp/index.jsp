<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.commons.codec.binary.Hex"%>
<%@ page import="javax.servlet.http.Cookie"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<head>

<s:include value="/jsp/common/init.jsp" />
<s:include value="/jsp/common/header.jsp">
	<s:param name="menuDisplay" value="true" />
</s:include>


<%
	response.setContentType("text/html; charset=UTF-8");
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	request.setCharacterEncoding("UTF8");
%>


<link href="css/stylelogin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/commonUtil.js"></script>
<script type="text/javascript" src="js/jquery-1.6.1.js"></script>
<script language="javascript">

$(document).ready(function() {
	$('#clientLogo').attr("src", "images/png/<%=PropFactory.getInstance().getProperty(EGeneral.Project_Logo)%>");
	});
	var elements = [ "passWord", "submitbtn" ];
	if (document.addEventListener)
	{
		document.addEventListener("keyup", keyCapt, false);
	}
	else
	{
		document.attachEvent("onkeyup", keyCapt);
	}

	function keyCapt(e)
	{
		if (typeof window.event != "undefined")
		{
			e = window.event;//code for IE
		}
		if (e.type == "keyup")
		{
			if (e.keyCode == 13)
			{
				var keyStatus = 0;
				var elementVal = document.activeElement.id;
				if (elementVal.length == 0)
				{
					var elementlist = document.getElementById("passWord");
					if (elementlist.length == 0)
						elementlist.focus();
					else
						onsubmitCalled();
				}
				else
				{
					if (elementVal == "passWord" || elementVal == "submitbtn")
					{
						onsubmitCalled();
					}

				}
				if (keyStatus == 0)
				{
					var elementlist = document.getElementById("passWord");
					if (elementlist.length == 0)
						elementlist.focus();
					else
						onsubmitCalled();
				}
			}
		}
	}

	function onsubmitCalled()
	{
		if (Validate(document.forms["loginForm"]))
		{
			document.forms["loginForm"].submit();
		}

	}

	function onresetCalled()
	{
		$("#userId").val("");
		$("#passWord").val("");
		$("#userId").focus();
		$("#errorSpan").hide();
		
	}

	function loadWindow()
	{
		$("#errorSpan").show();
		var userId = $("#userId").val();
		if (userId == "")
			$("#userId").focus();
		else
			$("#passWord").focus();
	}
</script>

</head>
<%
	String lastLoginID = "";
	if (session.getAttribute(ESession.UserObject.getAttribute()) != null)
	{
		response.sendRedirect("index.do");
		return;
	}
	else
	{

		boolean isDRSite = PropFactory.getInstance().getProperty(EDR.DR_Site).equals(ConstInterface.YES);

		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equalsIgnoreCase(ConstInterface.LASTLOGINID))
					lastLoginID = cookie.getValue();
			}
		}

	}
%>
<body class="bodybg" onload="loadWindow()">
	<s:form name="loginForm"
		action="loginAuthentication.do?LOGIN_ATTEMPT=1" id="loginForm"
		method="post" show_alert="1" onsubmit="return Validate(this);">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="20%"></td>
				<td width="50%" valign="bottom">&nbsp;</td>
				<td width="30%" align="center"><img id="clientLogo" src="" /></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="50" align="center">&nbsp;</td>
			</tr>
			<tr>
				<td height="400" align="center">
					<div id="loginForm"
						style="height: 62%; width: 30%; border: 5px solid #999; border-color: #336699; background-color: #F1EFE2; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
						<table cellpadding="0" width="100%" cellspacing="0">
							<tr class="welcomehead">
								<td align="left" valign="middle" width="100%"
									style="background-color: #336699;"><span>&nbsp;&nbsp;&nbsp;Login&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td>
									<div id="loginTable" style="padding: 20px;">
										<table border="0" align="center" cellpadding="0"
											cellspacing="0" width="100%">
											<tr>
												<td valign="top" align="center" width="20%" colspan="1"
													height="30px;">&nbsp;</td>
												<td valign="top" align="left" width="80%" colspan="3"
													height="30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:if
														test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan" style="color: red; font-weight:bold;"><s:property
																	escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr>
												<td align="left" class="usertext">User
													ID&nbsp;&nbsp;&nbsp;</td>
												<td align="right"><input name="userId" type="text"
													class="loginUserId" id="userId" value="<%=lastLoginID%>"
													validate="not_empty" msg="User Id should not be empty"
													fieldname="User Id" tabindex="1" /></td>
											</tr>
											<tr>
												<td align="left" class="usertext">&nbsp;</td>
												<td align="right">&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext">Password&nbsp;&nbsp;&nbsp;</td>
												<td align="right"><input name="passWord"
													type="password" autocomplete="off" class="loginPassword"
													id="passWord" validate="not_empty"
													msg="Password should not be empty" fieldname="Password"
													tabindex="2" /></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td nowrap align="right"></td>
											</tr>

											<tr>

												<td align="center" colspan="2"><s:hidden
														name="loginAttempt" value="%{'1'}"></s:hidden> <input
													type="button" class="buttonLogin" name="commit"
													value="Login" onclick="onsubmitCalled();" /> <input
													type="button" class="buttonLogin" name="clear"
													value="Reset" onclick="onresetCalled();" /></td>
											</tr>
											<tr>
												<td height="10px;">&nbsp;</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</s:form>
	<%@ include file="/jsp/common/i_footer.jsp"%>
</body>
</html>
