<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/jsp/common/init.jsp"%>
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	function addAssignUserRoles()
	{

		var selectedRoles = new Array();
		$("input[@name='selectedRoles[]']:checked").each(function()
		{
			selectedRoles.push($(this).val());
		});

		window.opener.document.getElementById('chRole').value = selectedRoles;
	}
</script>
</head>
<body>
	<form name="roleFrm">
		<div id="popupContact" class="divStyle">
			<div align="center">
				<table width="100%" align="center" frame="box" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
							<table class="tabStyle" width="100%">
								<tr>
									<td align="center" height="25px;"
										style="border: 1px; border-color: gray;"><b>Assign
											Role</b></td>
								</tr>
							</table>
							<table width="100%" border="0" cellpadding="5" cellspacing="0"
								class="tableborder">
								<tr>
									<td align="center" colspan="2">&nbsp;</td>
								</tr>
								<s:if test="rolesList.size() > 0">
									<s:iterator value="rolesList" id="r" status="roleName">
										<s:set name="mainRoles" value="%{rlRoleName}" />
										<s:set name="checked" value="%{''}" />
										<tr>
											<td bgcolor="#FFFFFF" class="gridtext"><s:if
													test="#mainRoles == 'approver'">
													<s:if test="hasApprovalAuthority">
														<input type="checkbox" name="selectedRoles"
															id="${rlRoleId}" value="${rlRoleId}"
															<s:property value="#checked"/> />
														<s:property value="rlRoleName" />
													</s:if>
													<s:else>
														<input type="checkbox" disabled="disabled"
															name="selectedRoles" id="${rlRoleId}" value="${rlRoleId}"
															<s:property value="#checked"/> />
														<s:property value="rlRoleName" />
													</s:else>
												</s:if> <s:else>
													<input type="checkbox" name="selectedRoles"
														id="${rlRoleId}" value="${rlRoleId}"
														<s:property value="#checked"/> />
													<s:property value="rlRoleName" />
												</s:else></td>
										</tr>
									</s:iterator>
								</s:if>
							</table>
						</td>
					</tr>
				</table>
				<center>
					<br /> <input name="savebutton" type="submit" class="btnstyle"
						id="savebutton" value="Assign"
						onclick="addAssignUserRoles();setTimeout('self.close()',500);" />&nbsp;&nbsp;
				</center>
			</div>
		</div>
	</form>
</body>
</html>
