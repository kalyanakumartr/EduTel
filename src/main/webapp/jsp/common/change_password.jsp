<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/jsp/common/header.jsp"%>
<script type="text/javascript" src="js/passwordStrengthMeter.js"></script>
<style>
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
<script type="text/javascript">
	var default_question;
	var default_answer;
	function comparePassword()
	{
		var actPass = $('#actualPass').val();
		var oldPwd = $("#oldPassword").val();
		var newPwd = $("#newPassword").val();
		var confPwd = $("#confirmPassword").val();
		var que = $("#question").val();
		var ans = $("#answer").val();

		if (oldPwd == '' || newPwd == '' || confPwd == '')
		{
			alert("Please enter password");
			$("#newPassword").val('');
			$("#confirmPassword").val('');
			$('#result').html('');
			$("#answer").val('');
			return false;
		}
		else if (actPass != oldPwd)
		{
			alert("You have entered wrong old password");
			$("#oldPassword").val('');
			$("#newPassword").val('');
			$("#confirmPassword").val('');
			$('#result').html('');
			$("#answer").val('');
		}
		else if (newPwd.length < 8 || confPwd.length < 8)
		{
			alert("Password should be minimum 8 characters!!..");
			$("#newPassword").val('');
			$("#confirmPassword").val('');
			$('#result').html('');
			$("#answer").val('');
			return false;
		}
		else if (oldPwd == newPwd)
		{
			alert("Old password and new password should not be same!!..");
			$("#newPassword").val('');
			$("#confirmPassword").val('');
			$('#result').html('');
			$("#answer").val('');
			return false;
		}
		else if (newPwd != confPwd)
		{
			alert("New password and confirm password should be same!!..");
			$("#newPassword").val('');
			$("#confirmPassword").val('');
			$('#result').html('');
			return false;
		}
		else if (que == '' || ans == '')
		{
			alert("Security Question and Answer mandatory.");
			$("#answer").val('');
			return false;
		}
		else
		{
			document.changePwdFrm.action="changePasswordAction.do";
			document.changePwdFrm.submit();
			return true;
		}
	}

	function secAnswer()
	{
		var getSelectedIndex = document.changePwdFrm.question.selectedIndex;
		var getSelectedOptionText = document.changePwdFrm.question[getSelectedIndex].value;
		if (getSelectedOptionText == default_question)
		{
			$('#answer').val(default_answer);
		}
		else
		{
			$('#answer').val('');
		}
	}
	$(document).ready(function()
	{
		default_question = $('#question').val();
		default_answer = $('#answer').val();
		$('#newPassword').keyup(function()
		{
			$('#result').html(passwordStrength($('#newPassword').val(), $('#username').val()))
		});
	});

	function backToHome()
	{
		window.location.href = "myQueueAction.do?page=MyQueue";
	}
</script>
<br>
<br>
<br>
<div align="center">
	<%
    	IUsers user = (IUsers) session.getAttribute(ESession.UserObject.getAttribute());
    	String isSuthEmployee = String.valueOf((user.getUsUserID().toLowerCase().indexOf("sutherlandglobal.com") > 0));
    	String actionForm = "";
    	if (request.getAttribute("actionFrm") != null)
    		actionForm = request.getAttribute("actionFrm").toString();
    %>
	<s:form name="changePwdFrm" action="changePasswordAction.do"
		method="POST" theme="simple">
		<s:set name="isSuthEmployee" id="isSuthEmployee"><%=isSuthEmployee%></s:set>
		<s:set id="actionForm" name="actionForm"><%=actionForm%></s:set>
		<s:hidden name="username" id="username" value="" />
		<s:hidden name="actualPass" id="actualPass" value="%{userPassWord}" />
		<s:hidden name="actionFrm" id="actionFrm" value="%{#actionForm}" />
		<s:token name="indexingToken" />
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
											Details - Change Password</b></td>
								</tr>
							</table>
							<table width="100%" style="border: 5px; border-color: gray;">
								<tr>
									<td align="center" colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3" align="left"><s:actionmessage /> <s:fielderror />
										<s:actionerror /></td>
								</tr>
								<s:if test="#isSuthEmployee == 'false'">
									<tr>
										<td width="40%" class="lblstyle" align="right">&nbsp;&nbsp;&nbsp;Old
											Password&nbsp;<span style="color: red;">*</span>
										</td>
										<td width="40%" class="lblstyle" align="left"><s:password
												key="oldPassword" id="oldPassword" name="oldPassword"
												cssClass="textbox" theme="simple" /></td>
										<td width="20%" class="lblstyle" align="left">&nbsp;</td>
									</tr>
									<tr>
										<td width="40%" class="lblstyle" align="right">&nbsp;&nbsp;&nbsp;New
											Password&nbsp;<span style="color: red;">*</span>
										</td>
										<td width="40%" class="lblstyle" align="left"><s:password
												key="newPassword" id="newPassword" name="newPassword"
												cssClass="textbox" theme="simple" /></td>
										<td width="20%" class="lblstyle" align="left"><span
											style="color: #006600" id='result'></span></td>
									</tr>
									<tr>
										<td width="40%" class="lblstyle" align="right">&nbsp;&nbsp;&nbsp;Confirm
											Password&nbsp;<span style="color: red;">*</span>
										</td>
										<td width="40%" class="lblstyle" align="left"><s:password
												key="confirmPassword" id="confirmPassword"
												name="confirmPassword" cssClass="textbox" theme="simple" /></td>
										<td width="20%" class="lblstyle" align="left">&nbsp;</td>
									</tr>
									<tr>
										<td width="100%" class="lblstyle" align="left" colspan="3">&nbsp;</td>
									</tr>
									<tr>
										<td width="40%" class="lblstyle" align="right">&nbsp;&nbsp;&nbsp;Security
											Question&nbsp;<span style="color: red;">*</span>
										</td>
										<td width="40%" class="lblstyle" align="left"><s:select
												key="question" id="question" name="question"
												list="reminderQues" cssStyle="width:250px"
												cssClass="textbox" label="Question" labelposition="top"
												theme="simple" onchange="return secAnswer()" /></td>
										<td width="20%" class="lblstyle" align="left">&nbsp;</td>
									</tr>
									<tr>
										<td width="40%" class="lblstyle" align="right">&nbsp;&nbsp;&nbsp;Answer&nbsp;<span
											style="color: red;">*</span></td>
										<td width="40%" class="lblstyle" align="left"><s:textfield
												key="answer" name="answer" id="answer" size="20"
												cssClass="textbox" theme="simple" maxlength="32" /></td>
										<td width="20%" class="lblstyle" align="left">&nbsp;</td>
									</tr>
									<tr>
										<td align="center" colspan="3">&nbsp;</td>
									</tr>
									<tr>
										<td width="100%" class="lblstyle" align="center" colspan="3"><input
											type="button" name="ChangePwd" value="Update"
											class="btnstyle" onclick="return comparePassword()">
											&nbsp;&nbsp;&nbsp;<input type="button" value="Cancel"
											class="btnstyle" onclick="backToHome()"></td>
									</tr>
								</s:if>
								<s:else>
									<tr>
										<td width="100%" class="errorMessage" align="center"
											colspan="3"><span style="color: red;"><b>System
													uses NT Password, You cannot change NT password here</b></span></td>
									</tr>
								</s:else>
								<tr>
									<td align="center" colspan="3">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</div>
<%@ include file="/jsp/common/i_footer.jsp"%>