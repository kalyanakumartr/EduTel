<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isErrorPage="true" import="java.io.*"%>

<s:if test="hasActionErrors()">
	<s:iterator value="actionErrors" var="bean">
		<s:if test="#bean.indexOf('Login') > 0 ">
			<%
				response.sendRedirect("index.do");
			%>
		</s:if>
	</s:iterator>
</s:if>

<html>
<link href="css/style.css" rel="stylesheet" type="text/css" id="style">
<body>
	<div id="mydiv" align="center">
		<br> <br> <br> <br> <br>
		<table width="50%" frame="box" cellpadding="0" cellspacing="0"
			style="border-color: #ffffff;">
			<tr>
				<td>
					<table width="100%" height="100%" frame="box" border="0"
						cellpadding="0" cellspacing="0">
						<tr>
							<td bgcolor="#0073aa" colspan="5" align="left"
								style="height: 30px;"><span
								style="color: #ffffff; font-family: Tahoma; font-size: 12px;"><b>&nbsp;&nbsp;EduTel
										Application experienced an Unexpected Technical Error.</b></span></td>
						</tr>
						<tr bgcolor="#f9f9f9">
							<td align="center" width="30%" height="200px;"><img
								src="images/error_alert.png" border="0" alt=""></td>
							<td colspan="4" height="200px;">
								<table width="100%" height="100%" frame="box" cellpadding="0"
									cellspacing="0" style="border-color: #ffffff;">
									<tr>
										<td bgcolor="#e0e0e0" colspan="3" align="left"
											style="height: 27px; width: 200px"><span
											style="color: #336699; font-family: Tahoma; font-size: 12px;">&nbsp;&nbsp;<b>Technical
													Reason</b></span></td>
									</tr>
									<tr>
										<td width="0%"></td>
										<td width="100%" height="175px;"><span
											style="color: #ff0000; font-family: Tahoma; font-size: 12px;"><s:actionmessage />
												<s:actionerror /> <s:property value="%{exception.message}" /></span></td>
										<td width="0%"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td bgcolor="#bebebe" colspan="5" height="40px;" align="right"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<s:include value="/jsp/common/i_footer.jsp" />
</body>
</html>

