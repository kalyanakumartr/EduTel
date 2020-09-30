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

.errorMessage {
	color: #FFFFFF;
	font-size: 14px;
	font-weight: bold;
	font-family: Arial, Helvetica, sans-serif;
	text-align: center;
}
</style>
<script type="text/javascript">
	setTimeout("location.href = 'logoutPage.do';", 3000);
</script>
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
									style="border: 1px; border-color: gray;" colspan="3"><b>Password
										Change Status</b></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="100%" class="errorMessage" align="center" colspan="3"
						height="50px;" valign="middle"><s:if
							test="hasActionMessages()">
							<span style="color: green;" class="lblspan"> <s:actionmessage /></span>
						</s:if> <s:if test="hasActionErrors()">
							<span style="color: red;" class="lblspan"> <s:actionerror /></span>
							<s:fielderror />
						</s:if></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<%@ include file="/jsp/common/i_footer.jsp"%>