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
<title><%=PropFactory.getInstance().getProperty(
					EGeneral.Project_Title)%></title>
<%
	response.setContentType("text/html; charset=UTF-8");
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	request.setCharacterEncoding("UTF8");
%>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/commonUtil.js"></script>
<script type="text/javascript" src="js/jquery-1.6.1.js"></script>
<script language="javascript">
	$(document).ready(
			function() {

				var actError = $('#actError').val();
				var isQuesEmpty = $('#isQuesEmpty').val();
				if (isQuesEmpty == 'undefined' || isQuesEmpty == null
						|| isQuesEmpty == 'one' || allTrim(isQuesEmpty) == '')
					enableNextButton();

				if (actError == 'true') {
					$('#sendNew').attr("disabled", false);
					$('#sendNew').attr("style", "color:black");
					$('#sendBack').attr("disabled", false);
					$('#sendBack').attr("style", "color:black");
				}
			});

	function enableNextButton() {
		var len = allTrim($('#userId').val()).length;
		if (len > 0) {
			$('#next').attr("disabled", false);
		} else {
			$('#next').attr("disabled", true);
		}
	}

	function enableSendButton() {
		var len = allTrim($('#quesAnswer').val()).length;
		if (len > 0) {
			$('#sendNew').attr("disabled", false);
			$('#sendNew').attr("style", "color:black");
		} else {
			$('#sendNew').attr("disabled", true);
			$('#sendNew').attr("style", "color:gray");
		}
	}

	function resetPassWord() {
		$('#sendNew').attr("disabled", true);
		$('#sendNew').attr("style", "color:gray");
		$('#sendBack').attr("disabled", true);
		$('#sendBack').attr("style", "color:gray");
		document.forms["forgotForm"].action = "resetPasswordAction.do";
		document.forms["forgotForm"].submit();

	}
	function goBackLogin() {
		location.href = "index.do";
	}
</script>

</head>
<body class="bodybg">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="80%" valign="bottom">&nbsp;</td>
			<td width="10%" align="right"><img id="clientLogo"
				src="images/png/<%=PropFactory.getInstance().getProperty(EGeneral.Project_Logo)%>"
				height="50" width="220" /></td>
		</tr>
	</table>
	<div align="center">
		<br></br> <br></br>
		<div id="popupContact" class="divStyle">
			<div align="center">
				<table width="100%" align="center" frame="box" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
							<table class="tabStyle" width="100%">
								<tr>
									<td align="center" height="25px;"
										style="border: 1px; border-color: gray;" colspan="3"><b>User
											Details - Retrieve Password</b></td>
								</tr>
							</table>
							<table width="100%" style="border: 5px; border-color: gray;">
								<tr>
									<td align="center" colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td align="center" style="padding: 5px;"><s:if
											test="hasActionMessages()">
											<span style="color: red;" class="lblspan"> <s:actionmessage /></span>
										</s:if> <s:if test="hasActionErrors()">
											<span style="color: red;" class="lblspan"> <s:actionerror /></span>
											<input type="hidden" id="actError" value="true" />
										</s:if> <s:else>
											<input type="hidden" id="actError" value="false" />
										</s:else></td>
								</tr>
								<tr>
									<td><s:hidden name="isQuesEmpty"></s:hidden>
										<div id="loginTable" style="padding-top: 2px;">
											<s:if test="isQuesEmpty == null || isQuesEmpty == 'one'">
												<s:form name="forgotForm"
													action="forgotPasswordSecurityQuestion.do" id="forgotForm"
													method="post">
													<table border="0" align="center" cellpadding="0"
														cellspacing="0">
														<tr>
															<td align="left" class="lblspan">User Id / Email
																Id&nbsp;&nbsp;&nbsp;</td>
															<td align="right"><s:textfield name="userId"
																	cssClass="bigtextbox" id="userId" theme="simple"
																	onkeyup="enableNextButton();"
																	onchange="enableNextButton();"
																	onblur="enableNextButton();"
																	onmouseout="enableNextButton();"
																	onmouseover="enableNextButton();"></s:textfield> <s:hidden
																	name="LOGIN_ATTEMPT" value="%{'1'}"></s:hidden></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td align="center" colspan="2"><input type="submit"
																class="btnstyle" id="next" name="next" value="Next"
																tabindex="200" /> &nbsp;&nbsp; <input type="button"
																class="btnstyle" name="back" value="Back"
																onclick="goBackLogin();" tabindex="205" /></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
														</tr>
													</table>
												</s:form>
											</s:if>
											<s:elseif test="isQuesEmpty == 'two'">
												<s:form name="forgotForm" id="forgotForm" method="post">
													<table border="0" align="center" cellpadding="0"
														cellspacing="0">
														<tr>
															<td class="emailInfo" align="center" colspan="2">Once
																you answer the following question correctly, a new
																password will be sent to your email.</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td align="right" class="lblspan"><s:property
																	value="question" />&nbsp;?&nbsp;&nbsp;</td>
															<td align="left"><input name="quesAnswer"
																type="text" class="bigtextbox" id="quesAnswer"
																maxlength="32" /> <s:hidden name="LOGIN_ATTEMPT"
																	value="%{'1'}"></s:hidden> <s:hidden name="answer"></s:hidden>
																<s:hidden name="userId"></s:hidden></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td align="center" colspan="2"><input type="button"
																class="btnstyle" id="sendNew" name="sendNew"
																value="Send New Password" onkeyup="enableSendButton();"
																onchange="enableSendButton();"
																onblur="enableSendButton();"
																onmouseout="enableSendButton();"
																onclick="resetPassWord();" tabindex="200" />
																&nbsp;&nbsp; <input type="button" class="btnstyle"
																name="sendBack" id="sendBack" value="Back"
																onclick="goBackLogin();" tabindex="205" /></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
														</tr>
													</table>
												</s:form>
											</s:elseif>
											<s:else>
												<tr>
													<td align="center" class="megStyle">Click <a
														href="index.do">here</a> to go Login Page
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
											</s:else>
										</div></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/jsp/common/i_footer.jsp"%>
</body>
</html>
