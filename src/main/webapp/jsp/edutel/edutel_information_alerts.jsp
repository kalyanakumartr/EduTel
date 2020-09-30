<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var infoAlertsListTable;

	$(document)
			.ready(
					function() {
						onInfoAlertTableforStudent();
						infoAlertsListTable = $('#infoAlertsListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lfrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "infoAlertListByJson.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 5, 'desc' ] ],
											"aoColumns" : [
													null,
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<div style='height:100px; width:700px; overflow-y:auto;'> "
																	+ obj.aData[1]
																	+ "</div>";
														}
													},
													null,
													null,
													null,
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteInfoAlerts(\'"
																	+ obj.aData[0]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Information Alerts\"/></a>";
														}
													} ]
										});
					});
	function deleteInfoAlerts(infoMsgId) {
		var url = 'deleteInfoAlert.do?iaInformationMsgId=' + infoMsgId
				+ "&uid=" + Math.random();

		$.getJSON(url, function(data) {
			if (data != null & data.length > 0) {
				alert(data[0].value);
				infoAlertsListTable.fnFilter(' ', 0);

			}
		});
	}

	function onInfoAlertTableforStudent() {
		if (allTrim($("#usUsersType").val()) == "Student") {
			$("#informationAlertForm").hide();
		}
	}

	function onsubmitCalled() {
		var infoVal = tinyMCE.get('infoAlertmsg').getContent();
		if (allTrim(infoVal) == "") {
			alert("Please Type Information & Alerts");
			return false;
		}
		document.forms["informationAlertForm"].submit();
	}

	function onresetCalled() {
		document.forms["informationAlertForm"].reset();
	}

	tinyMCE.init({
		mode : "textareas"
	});
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Information Alerts</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="informationAlertForm" id="informationAlertForm"
					action="createInformationAlert.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<s:hidden id="usUsersType" name="usUsersType"
							value="%{usUsersType}"></s:hidden>
						<table>
							<tr>
								<td>
									<div id="infoAlertDiv"
										style="width: 900px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 5px; border: 1px; border-color: gray;">
											<tr>
												<td valign="top" align="center" width="100%" colspan="3"
													height="30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:if
														test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan"
																style="color: red; font-weight: bold;"><s:property
																	escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="90%" colspan="2"><s:select
														name="informationAlert.iaInformationType"
														id="iaInformationType" style="width: 180px;"
														list="informationTypeLabelValueList" listKey="label"
														listValue="value" theme="simple"></s:select></td>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
												<td align="center" width="90%"><s:textarea
														name="infoAlertmsg" id="infoAlertmsg" theme="simple" /></td>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="3"><input type="button"
													class="btnstyle" name="sumbitInfoMsg" value="Submit"
													href="javascript:void(0);" onclick="onsubmitCalled();" />
													&nbsp;&nbsp; <input type="button" class="btnstyle"
													value="Reset" onclick="onresetCalled()" /></td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="7">&nbsp;&nbsp;&nbsp;</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</s:form>
			</div>
			<br>
			<div id="signupwrapper1" align="center"
				style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv"
					style="width: 87%; padding: 20px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="infoAlertsListTableId">
							<thead>
								<tr>
									<th width="2%">Info Id</th>
									<th width="65%">Information & Alert Message</th>
									<th width="5%">Information Type</th>
									<th width="10%">Created By</th>
									<th width="15%">Created Date</th>
									<th width="3%" align="center">Delete</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="6" class="dataTables_empty">Loading data from
										Server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<footer>
			<%@include file="../common/i_footer.jsp"%>
		</footer>
	</div>
</body>
</html>
