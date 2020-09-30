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
				var quesAnswer = $('#quesAnswer').val();
				if (quesAnswer == 'undefined' || quesAnswer == null
						|| allTrim(quesAnswer) == '')
					enableSaveButton();
			});

	function enableSaveButton() {
		var len = allTrim($('#quesAnswer').val()).length;
		if (len > 0) {
			$('#save').attr("disabled", false);
			$('#save').attr("style", "color:black");
		} else {
			$('#save').attr("disabled", true);
			$('#save').attr("style", "color:gray");
		}
	}
</script>
<style>
.reminderInfo {
	border: 1px solid #A7CEDF;
	background-color: #DFF4FF;
	color: #34404F;
	font-weight: bold;
	margin: 2px auto 14px;
	padding: 6px 6px 6px 30px;
	text-align: left;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
}

.reminderSelectBox {
	background-image: none;
	border: 1px solid #ABABAB;
	display: inline;
	float: left;
	margin-bottom: 12px;
	margin-left: 15px;
	padding: 4px;
}

.megStyle {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.tdLabel {
	color: #ffffff;
	font-weight: bold;
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
}

.errorMessage {
	color: #006600;
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: center;
}

.actionMessage {
	color: #006600;
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: center;
}

.tablePwd {
	border: 1px solid #FFFFFF;
	background-color: #FFFFFF;
}

.textStyle {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.lblspan {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: bold;
}

.lblstyle {
	font-weight: bold;
	font-size: 11px;
	font-family: Tahoma;
}

.inputtextbox {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #000000;
	text-decoration: none;
	width: 145px;
}

.addLabel {
	padding: 0px;
	width: 100%;
	margin: 0px;
	font-size: 11px;
	font-weight: bold;
	background-color: #6392CE;
	color: #ffffff;
	font-family: verdana;
}

.divStyle {
	top: 10px;
	margin: 10px;
	background-color: #FFFFFF;
	width: 40%;
	margin-left: 10px;
}

.tabStyle {
	padding: 0px;
	width: 100%;
	margin: 0px;
	font-size: 13px;
	background-color: #316099;
	color: #ffffff;
	font-family: Tahoma;
	border: thick;
	border-color: gray;
	border-width: medium;
}

.resTab {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #000000;
	text-decoration: none;
	overflow: auto;
	height: auto;
	margin-bottom: 20px;
	margin-left: 15px;
	width: 98%;
	background-color: white;
}
</style>
</head>
<body class="bodybg">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="10%"><img src="images/logo.png" width="275"
				height="61" /></td>
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
											Details - Password Reset</b></td>
								</tr>
							</table>
							<table width="100%" style="border: 5px; border-color: gray;">
								<tr>
									<td align="center">&nbsp;</td>
								</tr>
								<tr>
									<td align="center"><s:if test="hasActionMessages()">
											<span style="color: red;" class="lblspan"> <s:actionmessage /></span>
										</s:if> <s:if test="hasActionErrors()">
											<span style="color: red;" class="lblspan"> <s:actionerror /></span>
										</s:if></td>
								</tr>
								<tr>
									<td align="center" width="100%"><s:form
											name="reminderForm" action="saveReminderQues.do"
											id="reminderForm" method="post">
											<table border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td class="reminderInfo" colspan="2">Please choose
														your security question and enter your answer.</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr align="center">
													<td align="center" width="100%"><span class="lblspan">Question&nbsp;:&nbsp;</span>
														<s:select name="remQuestion" list="reminderQues"
															cssClass="bigtextbox"
															cssStyle="width:250px;height:22px;font-size:13px;"
															theme="simple" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr align="center">
													<td align="center" width="100%"><span class="lblspan">Answer&nbsp;:&nbsp;&nbsp;</span>
														<s:textfield id="quesAnswer" name="quesAnswer"
															cssClass="bigtextbox" maxlength="32"
															cssStyle="width:245px;height:16px;font-size:13px;"
															theme="simple" onkeyup="enableSaveButton();"
															onchange="enableSaveButton();"
															onblur="enableSaveButton();"
															onmouseout="enableSaveButton();" /></td>
												</tr>
												<tr>
													<td>&nbsp;<s:hidden name="LOGIN_ATTEMPT"
															value="%{'1'}"></s:hidden> <s:hidden name="userId"></s:hidden></td>
												</tr>
												<tr>
													<td align="center" width="100%"><input type="submit"
														class="btnstyle" id="save" name="save" value="  Save  "
														tabindex="200" /> &nbsp;&nbsp; <input type="button"
														class="btnstyle" id="save" name="save" value="Cancel"
														onclick="location.href='index.do'" tabindex="205" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
											</table>
										</s:form></td>
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
