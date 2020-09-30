<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/jsp/common/init.jsp"%>
<style type="text/css">
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
	background-color: #F1EFE2;
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

.gridtext {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #333333;
	text-decoration: none;
}

.textbox {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #000000;
	text-decoration: none;
	width: 165px;
}
</style>
<script type="text/javascript">
	function updateUserRoles()
	{
		document.forms[0].action = "assignUserRoles.do";
		document.forms[0].submit();
	}

	function checkAdminEnabled(chkBox)
	{
		var adminId = $("#Admin").val();
		var gsdadmin = $("#Gsdadmin").val();
		var opsadmin = $("#Opsadmin").val();

		if (chkBox.checked)
		{
			$('#' + adminId).attr('checked', 'checked');
		}
		else
		{
			var adminInit = $("#AdminInit").val();
			if (adminInit == 'checked')
				$('#' + adminId).attr('checked', 'checked');
			else
			{
				if ($('#' + gsdadmin).is(':checked') || $('#' + opsadmin).is(':checked'))
					$('#' + adminId).attr('checked', 'checked');
				else
					$('#' + adminId).removeAttr('checked');
			}

		}
	}

	function checkOPS_GSDEnabled(chkBox)
	{
		var GSDAdminInit = $("#GSDAdminInit").val();
		var OPSAdminInit = $("#OPSAdminInit").val();
		var gsdadmin = $("#Gsdadmin").val();
		var opsadmin = $("#Opsadmin").val();

		if (chkBox.checked)
		{
			if (GSDAdminInit == 'checked')
				$('#' + gsdadmin).attr('checked', 'checked');
			else
				$('#' + gsdadmin).removeAttr('checked');

			if (OPSAdminInit == 'checked')
				$('#' + opsadmin).attr('checked', 'checked');
			else
				$('#' + opsadmin).removeAttr('checked');
		}
		else
		{
			$('#' + gsdadmin).removeAttr('checked');
			$('#' + opsadmin).removeAttr('checked');
		}

	}
</script>
</head>
<body style="background: url('') no-repeat scroll right center #b3d9ff;">
	<table width="100%" align="center" cellpadding="20" cellspacing="0">
		<tr>
			<td><s:form action="assignUserRoles.do" name="userForm">
					<div id="popupContact" class="divStyle">
						<div align="center">
							<table width="100%" align="center" frame="box" cellpadding="0"
								cellspacing="0" bgcolor="#F1EFE2">
								<tr>
									<td>
										<table class="tabStyle" width="100%">
											<tr>
												<td align="center" height="25px;"
													style="border: 1px; border-color: gray;"><b>Assign
														User Roles</b></td>
											</tr>
										</table>
										<table width="100%" style="border: 5px; border-color: gray;"
											cellpadding="5" cellspacing="0" frame="hsides" rules="rows">
											<s:if test="rolesList.size > 0">
												<s:set name="incr" value="%{0}" />
												<s:iterator value="rolesList">
													<s:set name="mainRoles" value="%{rlRoleName}" />
													<s:set name="checked" value="%{''}" />
													<s:iterator value="avlRolesList" var="role">
														<s:set name="userRole" value="%{role}" />
														<s:if test="#mainRoles == #userRole">
															<s:set name="checked" value="%{'checked'}" />
														</s:if>
														<s:set name="userRole" value="" />
													</s:iterator>
													<s:set name="modByRow" value="%{#incr%3}" />
													<s:if test="#modByRow==0">
														<tr>
													</s:if>
													<td bgcolor="#F1EFE2" class="gridtext"><s:if
															test="#mainRoles == 'approver'">
															<s:if test="hasApprovalAuthority">
																<input type="checkbox" name="selectedRoles"
																	id="${rlRoleId}" value="${rlRoleId}"
																	<s:property value="#checked"/> />
																<span
																	style="font-family: Tahoma; font-weight: bold; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;<s:property
																		value="rlRoleLongName" /></span>
															</s:if>
															<s:else>
																<input type="checkbox" disabled="disabled"
																	name="selectedRoles" id="${rlRoleId}"
																	value="${rlRoleId}" <s:property value="#checked"/> />
																<span
																	style="font-family: Tahoma; font-weight: bold; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;<s:property
																		value="rlRoleLongName" /></span>
															</s:else>
														</s:if> <s:else>
															<s:if
																test="#mainRoles == 'Gsdadmin' || #mainRoles == 'Opsadmin'">
																<input type="checkbox" name="selectedRoles"
																	id="${rlRoleId}" value="${rlRoleId}"
																	<s:property value="#checked"/>
																	onclick="checkAdminEnabled(this);" />
																<s:if test="#mainRoles == 'Gsdadmin'">
																	<input type="hidden" id="Gsdadmin" name="Gsdadmin"
																		value="${rlRoleId}" />
																	<input type="hidden" id="GSDAdminInit"
																		name="GSDAdminInit"
																		value="<s:property value="#checked"/>" />
																</s:if>
																<s:else>
																	<input type="hidden" id="Opsadmin" name="Opsadmin"
																		value="${rlRoleId}" />
																	<input type="hidden" id="OPSAdminInit"
																		name="OPSAdminInit"
																		value="<s:property value="#checked"/>" />
																</s:else>
															</s:if>
															<s:else>
																<s:if test="#mainRoles == 'Admin'">
																	<input type="checkbox" name="selectedRoles"
																		id="${rlRoleId}" value="${rlRoleId}"
																		<s:property value="#checked"/>
																		onclick="checkOPS_GSDEnabled(this)" ;/>
																	<input type="hidden" id="Admin" name="Admin"
																		value="${rlRoleId}" />
																	<input type="hidden" id="AdminInit" name="AdminInit"
																		value="<s:property value="#checked"/>" />
																</s:if>
																<s:else>
																	<input type="checkbox" name="selectedRoles"
																		id="${rlRoleId}" value="${rlRoleId}"
																		<s:property value="#checked"/> />
																</s:else>
															</s:else>
															<span
																style="font-family: Tahoma; font-weight: bold; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;<s:property
																	value="rlRoleLongName" /></span>
														</s:else></td>
													<s:if test="#modByRow==2">
														</tr>
													</s:if>
													<s:set name="mainRoles" value="" />
													<s:set name="incr" value="%{#incr+1}" />
													<s:set name="modByRow" value="" />
												</s:iterator>
											</s:if>
										</table>
									</td>
								</tr>
							</table>
							<center>
								<br /> <input name="savebutton" type="button" class="btnstyle"
									id="savebutton" value="Save"
									onclick="updateUserRoles();setTimeout('self.close()',2000);" />&nbsp;&nbsp;
								<input name="closeButton" type="button" class="btnstyle"
									id="closebutton" value="Cancel" onclick="self.close()" />
							</center>
						</div>
					</div>
				</s:form></td>
		</tr>
	</table>
</body>
</html>
