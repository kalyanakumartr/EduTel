<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<link type="text/css" rel="stylesheet"
	href="css/dataTables.tableTools.css">
<script type="text/javascript" charset="utf-8" src="js/ZeroClipboard.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/dataTables.tableTools.js"></script>
<link href="css/bootbox/components.css" rel="stylesheet" type="text/css" />
<link href="css/bootbox/plugins.css" rel="stylesheet" type="text/css" />
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="js/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style type='text/css'>
.successbox,.warningbox,.errormsgbox {
	font-weight: bold;
	border: 2px solid;
	margin: 10px 0px;
	padding: 15px 10px 15px 70px;
	background-repeat: no-repeat;
	background-position: 10px center;
	width: 600px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	font-size: 14px;
}

.alertbox {
	font-family: segoe ui, sans-serif, verdana, arial;
	color: #ffffff;
	font-size: 14px;
	font-family: Arial;
	background-color: #0072C6;
	padding: 20px 20px 20px 20px;
	font-size: 16px;
	font-weight: normal;
	font-stretch:;
	color: #ffffff;
	line-height: 200%;
	text-align: justify;
}

.successbox {
	color: #ffffff;
	background-color: #60A917;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	font-size: 14px;
}

.warningbox {
	color: #ffffff;
	background-color: #F0A30A;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	font-size: 14px;
}

.errormsgbox {
	color: #ffffff;
	background-color: #E51400;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	font-size: 14px;
}

.img viewImg {
	background-image: url('images/png/view.png');
}

.img viewImg:HOVER {
	background-image: url('images/png/view_disabled.png');
}

.modal-open .modal {
	height: 600px;
}

.modal-dialog {
	width: 880px;
}
</style>
<script>
	var userListTable;
	var uNameColumn = 0;
	var uUserIdColumn = 1;
	var uEmailColumn = 2;
	var uMobileColumn = 3;
	var uCityColumn = 4;
	var uTypeColumn = 5;
	var uEmpIdColumn = 6;

	$(document)
			.ready(
					function() {
						$("#viewMessage").hide();
						$("#assignBtn").attr("disabled", true);
						$("#assignBtn").show();
						$("#assignGroupBtn").hide();
						$("#viewMessageDisable").show();

						userListTable = $('#userListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lfrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "usersForMessageListByJson.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ uUserIdColumn,
													'desc' ] ],
											"aoColumns" : [
													null,
													null,
													null,
													null,
													null,
													null,
													{
														"sName" : "ID6",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															var idText = obj.aData[uUserIdColumn]
																	.replace(
																			"@",
																			"");

															idText = idText
																	.replace(
																			".",
																			"");
															idText = idText
																	.replace(
																			"#",
																			"");
															idText = idText
																	.toUpperCase();

															return "<input type='hidden' id='" + idText + "' name='" + idText + "' value='" + obj.aData[uEmpIdColumn]+ "'/>";
														}
													} ],
											"oTableTools" : {
												"sRowSelect" : "multi",
												"aButtons" : [
														{
															"sExtends" : "text",
															"sButtonText" : "Select All",
															"fnClick" : function(
																	nButton,
																	oConfig,
																	oFlash) {
																var messageId = $(
																		"#messageId")
																		.val();
																if (messageId != null
																		&& messageId != undefined
																		&& allTrim(messageId) != "") {
																	var oTT = TableTools
																			.fnGetInstance('userListTableId');
																	oTT
																			.fnSelectAll();
																	$(
																			'#userGroupNameSelect')
																			.val(
																					"");
																	$(
																			"#assignBtn")
																			.attr(
																					"disabled",
																					false);
																	$(
																			"#assignBtn")
																			.show();
																	$(
																			"#assignGroupBtn")
																			.hide();
																}
															}
														},
														{
															"sExtends" : "text",
															"sButtonText" : "DeSelect All",
															"fnClick" : function(
																	nButton,
																	oConfig,
																	oFlash) {
																var messageId = $(
																		"#messageId")
																		.val();
																if (messageId != null
																		&& messageId != undefined
																		&& allTrim(messageId) != "") {
																	$(
																			"#employeeIds")
																			.val(
																					"");
																	$(
																			"#assignBtn")
																			.attr(
																					"disabled",
																					true);
																	$(
																			"#assignBtn")
																			.show();
																	$(
																			"#assignGroupBtn")
																			.hide();
																	deSelectUsers();
																}
															}
														} ]
											}
										});

						$('#userGroupNameSelect').on(
								'change',
								function() {
									var messageId = $("#messageId").val();
									if (messageId != null
											&& messageId != undefined
											&& allTrim(messageId) != "") {
										assignBasedOnGroupOrUsers();
									} else {
										onresetCalled();
									}
								});

						$('#userListTableId')
								.on(
										'click',
										function() {

											var messageId = $("#messageId")
													.val();
											if (messageId != null
													&& messageId != undefined
													&& allTrim(messageId) != "") {
												var oTT = TableTools
														.fnGetInstance('userListTableId');
												var aSelectedTrs = oTT
														.fnGetSelected();
												$('#userGroupNameSelect').val(
														"");
												$("#assignGroupBtn").attr(
														"disabled", true);
												$("#assignBtn")
														.attr(
																"disabled",
																(aSelectedTrs.length <= 0));
												$("#assignBtn").show();
												$("#assignGroupBtn").hide();
											} else {
												onresetCalled();
											}
										});
						$("#assignBtn").attr("disabled", true);
						$("#assignGroupBtn").attr("disabled", true);
					});

	function loadMessageToView() {
		var messageId = $("#messageId").val();

		if (messageId != null && messageId != undefined
				&& allTrim(messageId) != '') {
			$("#viewMessage").hide();
			$("#viewMessageDisable").show();
			try {
				var url = 'viewMessageForUser.do?messageId=' + messageId
						+ "&uid=" + Math.random();

				$.getJSON(url, function(data) {
					if (data != null & data.length > 0) {
						$("#titleHidden").val(data[0].title);
						$("#messageHidden").val(data[0].value);
						$("#messageType").val(data[0].type);
						$("#viewMessage").show();
						$("#viewMessageDisable").hide();
						$("#dashBoardDiv2").html("&nbsp;");
						$("#dashBoardDiv2").removeClass("successbox");
						$("#dashBoardDiv2").removeClass("warningbox");
						$("#dashBoardDiv2").removeClass("errormsgbox");
						assignBasedOnGroupOrUsers();
					}
				});
			} catch (e) {
				alert("Loading Messages getting error. Please contact System Administrator.");
				onresetCalled();
			}
		} else {
			onresetCalled();
		}
	}

	function assignBasedOnGroupOrUsers() {
		var ugnSelect = $('#userGroupNameSelect').val();
		if (ugnSelect != null && ugnSelect != undefined
				&& allTrim(ugnSelect) != '') {
			$("#employeeIds").val("");
			$("#assignBtn").attr("disabled", true);
			$("#assignGroupBtn").attr("disabled", false);
			$("#assignBtn").hide();
			$("#assignGroupBtn").show();
			deSelectUsers();
		} else {
			$('#userGroupNameSelect').val("");
			$("#assignGroupBtn").attr("disabled", true);
			$("#assignBtn").show();
			$("#assignGroupBtn").hide();
			var oTT = TableTools.fnGetInstance('userListTableId');
			var aSelectedTrs = oTT.fnGetSelected();
			$("#assignBtn").attr("disabled", (aSelectedTrs.length <= 0));
		}
	}

	function viewMessageForUser() {
		messageBox($("#titleHidden").val(), $("#messageHidden").val());
	}
	function messageBox(title, msgContent) {
		bootbox.dialog({
			message : msgContent,
			title : title,
			buttons : {

				danger : {
					label : "Close",
					className : "btn-danger",
					callback : function() {

					}
				}
			}
		});
	}

	function assignUserAndMessage() {

		var usrgrpName = $("#userGroupNameSelect").val();

		var oTT = TableTools.fnGetInstance('userListTableId');
		var aSelectedTrs = oTT.fnGetSelected();
		var empIds = "";
		for ( var i = 0; i < aSelectedTrs.length; i++) {
			var data = aSelectedTrs[i];
			// and parse the row:
			var nTds = $('td', data);

			// then list elements of the row by:
			var idText = $(nTds[1]).text();
			idText = idText.replace("@", "");
			idText = idText.replace(".", "");
			idText = idText.replace("#", "");
			idText = idText.toUpperCase();

			empIds += $("#" + idText).val() + ",";
		}
		if (allTrim(empIds) == "" && allTrim(usrgrpName) == "") {
			alert("Please Select the Users from the Table or Users Group to send Messages.");
			return false;
		} else {
			if (allTrim(empIds) != "") {
				$("#employeeIds").val(empIds.substring(0, empIds.length - 1));
			}
			confirmToSaveGroup();
		}
	}

	function onresetCalled() {
		$("#messageId").val("");
		$("#messageType").val("");
		$("#employeeIds").val("");
		$("#titleHidden").val("");
		$("#messageHidden").val("");
		$("#viewMessage").hide();
		$("#viewMessageDisable").show();
		$("#dashBoardDiv2").html("&nbsp;");
		$("#dashBoardDiv2").removeClass("successbox");
		$("#dashBoardDiv2").removeClass("warningbox");
		$("#dashBoardDiv2").removeClass("errormsgbox");
		$("#assignBtn").attr("disabled", true);
		$('#userGroupNameSelect').val("");
		$("#assignBtn").show();
		$("#assignGroupBtn").hide();
		deSelectUsers();
	}

	function deSelectUsers() {
		var oTT = TableTools.fnGetInstance('userListTableId');
		var aSelectedTrs = oTT.fnGetSelected();
		for ( var i = 0; i < aSelectedTrs.length; i++) {
			oTT.fnDeselect(aSelectedTrs[i]);
		}
	}

	function confirmToSaveGroup() {
		$("#message_user_group").modal("show");
	}

	function assignMessageToUsersGroup() {
		document.forms["messagesUserForm"].submit();
	}

	function viewUserGroup() {
		var ugnSelect = $('#userGroupNameSelect').val();
		if (ugnSelect != null && ugnSelect != undefined
				&& allTrim(ugnSelect) != '') {
			var url = 'viewUserGroup.do?userGroupName=' + ugnSelect + "&uid="
					+ Math.random();

			$
					.getJSON(
							url,
							function(data) {
								if (data != null & data.length > 0) {
									var usersList = "<div style=\"font-size:12px;height:400px;overflow-y:auto;\">";
									usersList += "<table class=\"table table-bordered table-advance table-hover\" >";
									usersList += "<thead><tr><th width=\"5%\">SNo</th><th width=\"25%\">User Name</th><th width=\"25%\">User Id</th>";
									usersList += "<th width=\"20%\">Mobile No</th><th width=\"25%\">Email Id</th></tr></thead><tbody>";
									for ( var i = 0; i < data.length; i++) {
										usersList += "<tr style=\"padding:5px;height:20px;\"><td>"
												+ (i + 1)
												+ "</td><td>"
												+ data[i].name
												+ "</td><td>"
												+ data[i].id
												+ "</td><td>"
												+ data[i].mobile
												+ "</td><td>"
												+ data[i].email + "</td></tr>";
									}
									usersList += "</tbody></table></div>";

									messageBox(ugnSelect, usersList);
								}
							});
		}
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width: 70%">
			<div class="latest-title">
				<p>Message User Mapping Panel</p>
			</div>
			<br>
			<div id="dashBoardDiv1" align="center"
				style="width: 100%; padding: 10px; font-size: 12px;">
				<div id="dashBoardDiv2" align="left"
					style="width: 100%; padding: 10px; min-height: 30px; max-height: 100px; overflow: auto;">
					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors">
							<span class="errorMessage" id="errorSpan"><s:property
									escape="false" /></span>
						</s:iterator>
						<script>
							$(function() {
								var errorSpan = $("#errorSpan").text();
								if (errorSpan.indexOf("partially") > 0) {
									$("#dashBoardDiv2").addClass("warningbox");
									$("#dashBoardDiv2").removeClass(
											"successbox");
									$("#dashBoardDiv2").removeClass(
											"errormsgbox");
								} else if (errorSpan.indexOf("failed") > 0
										|| errorSpan.indexOf("already mapped") > 0) {
									$("#dashBoardDiv2").removeClass(
											"warningbox");
									$("#dashBoardDiv2").removeClass(
											"successbox");
									$("#dashBoardDiv2").addClass("errormsgbox");
								} else {
									$("#dashBoardDiv2").removeClass(
											"warningbox");
									$("#dashBoardDiv2").addClass("successbox");
									$("#dashBoardDiv2").removeClass(
											"errormsgbox");
								}
								$("#dashBoardDiv2").fadeIn(4000);
							});
						</script>
					</s:if>
				</div>
				<BR>
				<div id="signupwrapper" align="center"
					style="width: 100%; padding: 0px;">
					<s:form name="messagesUserForm" id="messagesUserForm"
						action="messageUserMappingCreate.do" method="post"
						enctype="multipart/form-data" autocomplete="off" show_alert="1">
						<table class="display">
							<tr>
								<td width="11%"><span class="lblStyle"
									style="font-size: 16px; color: #000000;">Messages&nbsp;</span></td>
								<td width="20%"><s:select name="messages.messageId"
										id="messageId"
										style="font-size: 14px;width: 250px;height:30px;"
										list="messagesList" listKey="messageId"
										listValue="messageName" onchange="loadMessageToView();"
										headerKey="" headerValue="--- Select Message ---"
										theme="simple" /></td>
								<td width="10%"><img class="viewImg"
									src="images/png/view.png" id="viewMessage"
									onclick="viewMessageForUser()"
									style="vertical-align: bottom; cursor: pointer; height: 30px; width: 30px;"
									title="View User Message" /> <img
									src="images/png/view_disabled.png" id="viewMessageDisable"
									style="display: none; vertical-align: bottom; cursor: pointer; height: 30px; width: 30px;"
									title="View User Message" /></td>
								<td width="5%">&nbsp;</td>
								<td width="54%"><s:hidden name="titleHidden"
										id="titleHidden" theme="simple" /> <s:hidden
										name="messageHidden" id="messageHidden" theme="simple" /> <s:hidden
										name="employeeIds" id="employeeIds" theme="simple" /> <s:hidden
										name="messages.messageType" id="messageType" theme="simple" />
									<input id="assignBtn" type="button" class="btnstyle"
									value="Assign Message To Users"
									onclick="assignUserAndMessage()"
									style="width: 175px; height: 30px;" /><input
									id="assignGroupBtn" type="button" class="btnstyle"
									value="Assign Message To Group"
									onclick="assignMessageToUsersGroup()"
									style="width: 175px; height: 30px;" />&nbsp;&nbsp;&nbsp; <input
									id="resetBtn" type="button" class="btnstyle" value="Reset"
									onclick="onresetCalled()" style="width: 100px; height: 30px;" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="11%"><span class="lblStyle"
									style="font-size: 16px; color: #000000;">User
										Groups&nbsp;</span></td>
								<td width="20%"><s:select name="userGroupName"
										id="userGroupNameSelect"
										style="font-size: 14px;width: 250px;height:30px;"
										list="messageUsersGroupList" listKey="userGroupName"
										listValue="userGroupName" headerKey=""
										headerValue="--- Select Users Group ---" theme="simple" /></td>
								<td width="10%"><img src="images/png/public_access.png"
									id="viewUsers" onclick="viewUserGroup()"
									style="vertical-align: bottom; cursor: pointer; height: 30px; width: 30px;"
									title="View Group User" /> <img
									src="images/png/private_access.png" id="viewUsersDisable"
									style="display: none; vertical-align: bottom; cursor: pointer; height: 30px; width: 30px;"
									title="View Group User" /></td>

								<td width="5%">&nbsp;</td>
								<td width="54%"><s:hidden
										name="messageUsersGroup.userGroupName"
										id="userGroupNameHidden" theme="simple" />&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
					</s:form>
					<div id="dashBoardDiv" class="ui-state-default" align="center"
						style="width: 100%; padding: 25px;">
						<table id="userListTableId" class="display">
							<thead>
								<tr>
									<th width="25%">User Name</th>
									<th width="20%">User Id</th>
									<th width="20%">Email Id</th>
									<th width="10%">Mobile No</th>
									<th width="10%">City</th>
									<th width="10%">User Type</th>
									<th width="0%" align="center"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="7" class="dataTables_empty">Loading data from
										Server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<s:include value="m_user_message_group.jsp"></s:include>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
