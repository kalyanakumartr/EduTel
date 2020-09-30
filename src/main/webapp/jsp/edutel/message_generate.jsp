<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<link href="css/bootbox/components.css" rel="stylesheet" type="text/css" />
<link href="css/bootbox/plugins.css" rel="stylesheet" type="text/css" />
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="js/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style>
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

.modal-open .modal {
	height: 600px;
}

.modal-dialog {
	width: 880px;
}
</style>
<script>
	var messageListTable;

	$(document)
			.ready(
					function() {
						hideShowSubject();
						messageListTable = $('#messageListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lfrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "messagesListByJson.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 4, 'desc' ] ],
											"aoColumns" : [
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<a href=\"messagePreUpdate.do?messageId="
																	+ obj.aData[7]
																	+ "&uid="
																	+ Math
																			.random()
																	+ "\"  style=\"text-decoration: underline;color:blue;\">"
																	+ obj.aData[0]
																	+ "</a>";
														}
													},
													null,
													null,
													null,
													null,
													{
														"sName" : "ID1",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"viewMessageForUser(\'"
																	+ obj.aData[5]
																	+ "\')\"><img src=\"images/png/view.png\" id=\"viewBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"View User Message\"/></a>";
														}
													},
													{
														"sName" : "ID2",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															if (obj.aData[6] == "true"
																	|| obj.aData[6] == true) {
																return "<img src=\"images/png/unlock.png\" id=\"blockMessages\" onclick=\"blockUnlockMessages(\'"
																		+ obj.aData[7]
																		+ "','false')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Block Messages \"/>";
															} else {
																return "<img src=\"images/png/lock.png\" id=\"unBlockMessages\" onclick=\"blockUnlockMessages(\'"
																		+ obj.aData[7]
																		+ "','true')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"UnBlock Messages \"/>";
															}
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteMessages(\'"
																	+ obj.aData[7]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete User Message\"/></a>";
														}
													} ]
										});
					});

	function viewMessageForUser(messageId) {

		var url = 'viewMessageForUser.do?messageId=' + messageId + "&uid="
				+ Math.random();

		$.getJSON(url, function(data) {
			if (data != null & data.length > 0) {
				bootbox.dialog({
					message : data[0].value,
					title : data[0].title,
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
		});

	}

	function deleteMessages(messageId) {
		var confirmDelete = confirm("Are sure want to Delete the Messages ?");
		if (confirmDelete) {
			var url = 'deleteMessages.do?messageId=' + messageId + "&uid="
					+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					messageListTable.fnFilter(' ', 0);

				}
			});
		}

	}

	function blockUnlockMessages(messageId, status) {
		var altMsg = "Are sure want to Block the Message ?";
		if (status == 'true' || status == true)
			altMsg = "Are sure want to UnBlock the Message ?";

		var confirmBlock = confirm(altMsg);
		if (confirmBlock) {
			var url = 'blockUnlockMessages.do?messageId=' + messageId
					+ "&status=" + status + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					messageListTable.fnFilter('  ', 0);
				}
			});
		}
	}

	function loadBeanFieldList() {

		var beanName = $("#beanName option:selected").text();
		var url = 'loadBeanFieldList.do?beanName=' + beanName + "&uid="
				+ Math.random();

		$.getJSON(url, function(data) {
			if (data != null & data.length > 0) {
				$('#beanFieldId').find('option').remove().end();
				for ( var i = 0; i < data.length; i++) {
					$('#beanFieldId').append(
							$('<option>').text(data[i].key).attr('value',
									data[i].value));
				}
			}
		});
	}

	function appendToMessage() {
		var message = $("#message").val();

		var cursorPosition = $('#message').prop("selectionStart");
		var before = message.substring(0, cursorPosition);
		var end = message.substring(cursorPosition);

		$("#message").val(before + " " + $("#beanFieldId").val() + " " + end);
	}

	function onsubmitCalled() {
		var message = $("#message").val();
		var messageName = $("#messageName").val();
		var deliveryDateTime = $("#deliveryDateTime").val();

		if (messageName != null && messageName != undefined
				&& allTrim(messageName) == "") {
			alert("Please Type Message Name.");
			return false;
		}
		if (deliveryDateTime != null && deliveryDateTime != undefined
				&& allTrim(deliveryDateTime) == "") {
			alert("Please Type Delivery Date for the message");
			return false;
		}
		if (message != null && message != undefined && allTrim(message) == "") {
			alert("Please Type Messages To Send Users");
			return false;
		}
		document.forms["messagesForm"].submit();
	}

	function onresetCalled() {
		$('#beanName').find('option:eq(0)').prop('selected', true);
		$("#messageName").val("");
		$("#deliveryDateTime").val("");
		$("#message").val("");
		$("#messageType").val("SMS");
		loadBeanFieldList();
		hideShowSubject();
	}

	function hideShowSubject() {
		var messageType = $("#messageType").val();
		if (messageType == "SMS") {
			$("#messageSubjectSpan").hide();
			$("#messageSubject").hide();
			$("#messageSubject").val("");
		} else {
			$("#messageSubjectSpan").show();
			$("#messageSubject").show();
		}
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Message Generate Panel</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="messagesForm" id="messagesForm"
					action="messageCreate.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="messagesDiv"
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
												<td align="left" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="center" width="96%">
													<table align="center" cellpadding="0" cellspacing="0"
														width="100%"
														style="padding: 5px; border: 1px; border-color: gray;">
														<tr>
															<td align="left" width="12%"><span class="lblstyle">Message
																	Name &nbsp;</span></td>
															<td align="left" width="20%"><s:textfield
																	name="messages.messageName" id="messageName"
																	theme="simple" cssStyle="width: 185px;"
																	label="Message Name" /> <s:hidden
																	name="messages.messageId" id="messageId" /></td>
															<td align="right" width="12%"><span class="lblstyle">Delivery
																	Date &nbsp;</span></td>
															<td align="left" width="20%"><s:textfield
																	readonly="true" class="smalltextbox"
																	name="messages.deliveryDateTime" id="deliveryDateTime"
																	style="width: 185px; background: url('js/datepicker/calendar.gif') no-repeat scroll right center #FFFFFF;vertical-align: middle;"
																	theme="simple" /> <script>
																		new Zapatec.Calendar.setup(
																				{
																					inputField : 'deliveryDateTime',
																					ifFormat : '%d-%m-%Y %H:%M',
																					showsTime : true,
																					button : 'deliveryDateTime',
																					singleClick : true,
																					step : 1,
																					timeFormat : '24'
																				});
																	</script></td>
															<td align="right" width="12%"><span class="lblstyle">Message
																	Type &nbsp;</span></td>
															<td align="left" width="20%"><s:select
																	name="messages.messageType" id="messageType"
																	list="#{'SMS':'SMS', 'Email':'Email'}" theme="simple"
																	cssStyle="width: 185px;" onchange="hideShowSubject();" /></td>
														</tr>
														<tr>
															<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;</td>
														</tr>
														<tr>
															<td align="left" width="12%" valign="middle"><span
																class="lblstyle" id="messageSubjectSpan">Email
																	Subject &nbsp;</span></td>
															<td align="left" width="52%" colspan="3"><s:textfield
																	name="messages.messageSubject" id="messageSubject"
																	theme="simple" cssStyle="width: 100%;"
																	label="Message Subject" /></td>
															<td align="right" width="12%"><span class="lblstyle">Data
																	Master&nbsp;</span></td>
															<td align="left" width="20%"><s:select
																	name="beanName" id="beanName" style="width: 185px;"
																	list="messagesBeanList" listKey="beanNameWithPackage"
																	listValue="beanDisplayName" label="Data Master"
																	labelposition="top" labelSeparator=""
																	onchange="loadBeanFieldList();" theme="simple" /></td>
														</tr>
													</table>
												</td>
												<td align="left" width="2%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="96%"><table align="center"
														cellpadding="0" cellspacing="0" width="100%"
														style="padding: 5px; border: 1px; border-color: gray;">
														<tr>
															<td align="left" width="75%" valign="top"><s:textarea
																	name="messages.message" id="message" theme="simple"
																	style="width: 630px;height:200px; resize:none;" /></td>
															<td align="left" width="2%">&nbsp;&nbsp;&nbsp;</td>
															<td align="left" width="20%" valign="top"><s:select
																	name="beanFieldId" id="beanFieldId"
																	style="width: 185px;padding: 10px;height:200px;"
																	list="messagesBeanFieldList"
																	listKey="messageDisplayName"
																	listValue="beanFieldDisplayName" label="Data Fields"
																	labelposition="top" labelSeparator="" multiple="true"
																	onchange="appendToMessage();" theme="simple" /></td>
														</tr>
													</table></td>
												<td align="left" width="2%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;</td>
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
							id="messageListTableId">
							<thead>
								<tr>
									<th width="25%">Message Name</th>
									<th width="15%">Delivery Date</th>
									<th width="15%">Message Type</th>
									<th width="15%">Created By</th>
									<th width="20%">Created Date</th>
									<th width="4%" align="center">View</th>
									<th width="3%" align="center">Status</th>
									<th width="3%" align="center">Delete</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="10" class="dataTables_empty">Loading data
										from Server</td>
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
<style>
textarea#message {
	color: #666;
	font-size: 14px;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	margin: 5px 0px 10px 0px;
	padding: 10px;
	height: 75px;
	width: 350px;
	border: #999 1px solid;
	font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
	transition: all 0.25s ease-in-out;
	-webkit-transition: all 0.25s ease-in-out;
	-moz-transition: all 0.25s ease-in-out;
	box-shadow: 0 0 5px rgba(81, 203, 238, 0);
	-webkit-box-shadow: 0 0 5px rgba(81, 203, 238, 0);
	-moz-box-shadow: 0 0 5px rgba(81, 203, 238, 0);
}

textarea#message:focus {
	color: #000;
	outline: none;
	border: #35a5e5 1px solid;
	font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
	box-shadow: 0 0 5px rgba(81, 203, 238, 1);
	-webkit-box-shadow: 0 0 5px rgba(81, 203, 238, 1);
	-moz-box-shadow: 0 0 5px rgba(81, 203, 238, 1);
}
</style>
</html>
