<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/jsp/common/header.jsp"%>
<%@ include file="/jsp/common/init.jsp"%>
<script type="text/javascript" src="js/passwordStrengthMeter.js"></script>
<style type="text/css">
.lblspan {
	font-family: Tahoma;
	font-size: 12px;
	font-weight: bold;
}

.lblstyle {
	font-weight: bold;
	font-size: 12px;
	font-family: Tahoma;
}

.inputtextbox {
	font-family: Tahoma;
	font-size: 12px;
	font-weight: normal;
	color: #000000;
	text-decoration: none;
	width: 145px;
}

.addLabel {
	padding: 0px;
	width: 100%;
	margin: 0px;
	font-size: 12px;
	font-weight: bold;
	background-color: #6392CE;
	color: #ffffff;
	font-family: verdana;
}

.divStyle {
	top: 10px;
	margin: 10px;
	background-color: #F1EFE2;
	width: 60%;
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
	font-size: 12px;
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
<script type="text/javascript" src="js/JspJs/admin/userMgmt.js"></script>
<div id="myDiv" align="center">
	<s:form name="input" action="" method="post" theme="simple">
		<div id="popupContact" class="divStyle">
			<div align="center">
				<table width="100%" align="center" frame="box" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
							<table class="tabStyle" width="100%">
								<tr>
									<td align="center" height="25px;"
										style="border: 1px; border-color: gray;"><b>User
											Management</b></td>
								</tr>
							</table>
							<table width="100%" style="border: 5px; border-color: gray;">
								<tr>
									<td align="center" colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td align="right"></td>
									<td width="100%" valign="top" align="center">
										<table id="searchTab" width="100%" border="0">
											<tr>
												<td valign="middle" class="lblspan" align="center">User
													Name/User Id <span style="color: red;">*</span>&nbsp; <input
													name="searchString" id="searchString" type="text"
													maxlength="100"
													style="width: 250px; height: 23px; font-family: Tahoma; font-size: 12px;"
													onKeyPress="return pressenter(event)"
													onkeyup="minimumSearchLength();adminNameEmailValidation(this);"
													tabindex="5" title="Type minimum 3 characters for Search" />
													&nbsp;<input type="button" disabled="disabled"
													id="searchBtn" name="searchBtn" class="btnstyle"
													value="Search" onclick="searchUser();" tabindex="10" />
													&nbsp;<input type="button" id="add" name="add"
													class="btnstyle" value="Add User"
													onclick="addUserDetails()" tabindex="75" /> <input
													type="hidden" name="showFilter" id="showFilter"
													value="${showFilter}" />
												</td>
											</tr>
											<tr>
												<td align="center">&nbsp;</td>
											</tr>
										</table>
										<div id="successMsg"></div>
										<div id="userListDiv" style="overflow: auto; height: auto;"></div>
										<div id="updateUser" style="display: none;">
											<div id="popupContact" class="divStyle"
												style="top: 10px; margin: 10px; width: 99%; margin-left: 5px;">
												<div align="center" width="100%">
													<table width="100%" align="center" frame="box"
														cellpadding="0" cellspacing="0">
														<tr>
															<td>
																<table class="tabStyle" width="100%">
																	<tr>
																		<td align="left" height="25px;"
																			style="border: 1px; border-color: gray;"><b>&nbsp;&nbsp;&nbsp;Update
																				User</b></td>
																	</tr>
																</table>
																<table width="100%"
																	style="border: 5px; border-color: gray;">
																	<tr>
																		<td align="center" colspan="2">&nbsp;</td>
																	</tr>
																	<tr>
																		<td width="57%">
																			<table>
																				<tr>
																					<td align="right" class="lblstyle">Employee Id<span
																						style="color: red;">*</span></td>
																					<td align="left"><s:textfield name="u_empId"
																							id="u_empId" size="30" maxlength="50"
																							theme="simple"
																							onkeyup="return alphanumericValidationHypenUnderScore(this)"
																							tabindex="15" readonly="true"
																							cssStyle="background-color:#C0C0C0;font-size: 12px; font-family: Tahoma;" />
																						<s:if test="%{gsiAdminRole}">
																							<input type="hidden" id="gsiadmin" value="true" />
																							<span
																								style="font-size: 12px; font-weight: bold; font-family: tahoma;"
																								id="chText">Change UserId</span>
																							<input type="checkbox" name="emailCheck"
																								id="emailCheck" tabindex="26" />
																						</s:if> <s:elseif test="%{gsdAdminRole}">
																							<input type="hidden" id="gsdadmin" value="true" />
																							<span
																								style="font-size: 12px; font-weight: bold; font-family: tahoma;"
																								id="chText">Change UserId</span>
																							<input type="checkbox" name="emailCheck"
																								id="emailCheck" tabindex="26" />
																						</s:elseif></td>
																				</tr>
																				<tr>
																					<td align="right" class="lblstyle">User Name<span
																						style="color: red;">*</span><br></td>
																					<td align="left"><s:textfield
																							name="u_userName" id="u_userName" size="30"
																							maxlength="50" theme="simple" tabindex="20"
																							onkeyup="adminUserNameValidation(this)"
																							cssStyle="font-size: 12px; font-family: Tahoma;" /></td>
																				</tr>
																				<tr>
																					<td align="right" class="lblstyle">User Id<span
																						style="color: red;">*</span></td>
																					<td align="left"><s:textfield name="u_userId"
																							id="u_userId" size="30" maxlength="100"
																							theme="simple" tabindex="25" readonly="true"
																							cssStyle="background-color:#C0C0C0;font-size: 12px; font-family: Tahoma;" /></td>
																				</tr>
																				<tr>
																					<td align="right" class="lblstyle">Active<span
																						style="color: red;">*</span></td>
																					<td align="left"><input type="checkbox"
																						name="u_Status" id="u_Status" tabindex="27"
																						checked="checked" /> &nbsp;&nbsp;<span
																						class="lblstyle">Login Status</span>&nbsp;<input
																						type="checkbox" name="log_Status" id="log_Status"
																						tabindex="28" /> &nbsp;&nbsp;<span
																						class="lblstyle">Processed Status</span>&nbsp;<input
																						type="checkbox" name="prcs_Status"
																						id="prcs_Status" tabindex="29" /></td>
																				</tr>
																			</table>
																		</td>
																		<td width="43%">
																			<table>
																				<tr id="emailChange">
																					<td colspan="2">
																						<table width="100%">
																							<tr>
																								<td align="right" class="lblstyle">New User
																									Id<span style="color: red;">*</span>
																								</td>
																								<td align="left"><s:textfield
																										name="nu_userId" id="nu_userId" size="30"
																										maxlength="100" theme="simple" tabindex="27"
																										onkeyup="adminNameEmailValidation(this);"
																										cssStyle="font-size: 12px; font-family: Tahoma;" /></td>
																							</tr>
																							<tr>
																								<td align="right" class="lblstyle">Confirm
																									User Id<span style="color: red;">*</span>
																								</td>
																								<td align="left"><s:textfield
																										name="cnf_userId" id="cnf_userId" size="30"
																										maxlength="100"
																										onmousedown="whichButton(event)"
																										onkeydown="return noCTRL(event);"
																										onkeyup="adminNameEmailValidation(this);"
																										theme="simple" tabindex="28"
																										cssStyle="font-size: 12px; font-family: Tahoma;" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																		<td><s:hidden name="userAutoId" id="userAutoId" /></td>
																	</tr>
																	<tr align="center">
																		<td id="approverTier">
																			<table>
																				<tr>
																					<td valign="top" class="lblstyle">
																						<!-- <a href="javascript:void(0)" onclick="editApproverData()"> Edit Approver Info </a>-->
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td align="right"><input type="button"
																			class="btnstyle" id="edit" name="edit"
																			onclick="updateUserDetails()" tabindex="30" /> <input
																			type="button" class="btnstyle" id="cancel"
																			name="cancel" onclick="cancelUserDetails()"
																			tabindex="35" /></td>
																	</tr>
																	<tr>
																		<td align="right"></td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</div>
										<div id="addUser" style="display: none;">
											<div id="popupContact" class="divStyle">
												<div align="center">
													<table width="100%" align="center" frame="box"
														cellpadding="0" cellspacing="0">
														<tr>
															<td>
																<table class="tabStyle" width="100%">
																	<tr>
																		<td align="left" height="25px;"
																			style="border: 1px; border-color: gray;"><b>&nbsp;&nbsp;&nbsp;Add
																				New User</b></td>
																	</tr>
																</table>
																<table width="100%"
																	style="border: 5px; border-color: gray;">
																	<tr>
																		<td align="center" colspan="2">&nbsp;</td>
																	</tr>
																	<tr>
																		<td>
																			<table width="100%">
																				<tr>
																					<td align="right" class="lblstyle">Employee Id<span
																						style="color: red;">*</span></td>
																					<td align="left"><s:textfield name="empId"
																							id="empId" size="28" maxlength="50"
																							onkeyup="return alphanumericValidationHypenUnderScore(this)"
																							theme="simple" tabindex="40"
																							cssStyle="font-size: 12px; font-family: Tahoma;" /></td>
																				</tr>
																				<tr>
																					<td align="right" class="lblstyle">User Name<span
																						style="color: red;">*</span><br></td>
																					<td align="left"><s:textfield name="userName"
																							id="userName" size="28" maxlength="50"
																							theme="simple" tabindex="45"
																							onkeyup="adminUserNameValidation(this)"
																							cssStyle="font-size: 12px; font-family: Tahoma;" /></td>
																				</tr>
																				<tr>
																					<td align="right" class="lblstyle">User Id<span
																						style="color: red;">*</span></td>
																					<td align="left"><s:textfield name="userId"
																							id="userId" size="28" maxlength="100"
																							theme="simple" tabindex="50"
																							onkeyup="adminNameEmailValidation(this);"
																							cssStyle="font-size: 12px; font-family: Tahoma;" /></td>
																				</tr>
																				<tr>
																					<td align="right" class="lblstyle">Confirm
																						User Id<span style="color: red;">*</span>
																					</td>
																					<td align="left"><s:textfield name="cUserId"
																							id="cUserId" size="28" maxlength="100"
																							theme="simple" tabindex="52"
																							onmousedown="whichButton(event)"
																							onkeyup="adminNameEmailValidation(this);"
																							onkeydown="return noCTRL(event)"
																							cssStyle="font-size: 12px; font-family: Tahoma;" /></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td align="center"><input type="button"
																			class="btnstyle" id="save" name="save"
																			onclick="saveUserDetails()" tabindex="60" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
																			type="button" class="btnstyle" id="close"
																			name="close" onclick="closeUserDetails()"
																			tabindex="65" /></td>
																	</tr>
																	<tr>
																		<td align="center">&nbsp;</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</div>
										<table width="100%" border="0">
											<tr>
												<td width="100%" align="right">&nbsp;</td>
											</tr>
										</table>
									</td>
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
