<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<style>
.custom-combobox {
	position: relative;
	display: inline-block;
	font-size: 12px;
	font-family: Tahoma;
	font-weight: normal;
}

.custom-combobox-toggle {
	position: absolute;
	top: 0;
	bottom: 0;
	margin-left: -1px;
	padding: 0;
	font-size: 13px;
	font-family: Tahoma;
	font-weight: normal;
}

.custom-combobox-input {
	margin: 0;
	padding: 2px 5px;
	font-size: 13px;
	font-family: Tahoma;
	font-weight: normal;
	width: 200px;
}

.ui-menu-item {
	margin: 0;
	padding: 2px 5px;
	font-size: 13px;
	font-family: Tahoma;
	font-weight: normal;
}
</style>
<script>
	(function($) {
		$
				.widget(
						"custom.combobox",
						{
							_create : function() {
								this.wrapper = $("<span>").addClass(
										"custom-combobox").insertAfter(
										this.element);

								this.element.hide();
								this._createAutocomplete();
								this._createShowAllButton();
							},

							_createAutocomplete : function() {
								var selected = this.element
										.children(":selected"), value = selected
										.val() ? selected.text() : "";

								this.input = $("<input>")
										.appendTo(this.wrapper)
										.val(value)
										.attr("title", "")
										.addClass(
												"custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left")
										.autocomplete({
											delay : 0,
											minLength : 0,
											source : $.proxy(this, "_source")
										}).tooltip({
											tooltipClass : "ui-state-highlight"
										});

								this._on(this.input, {
									autocompleteselect : function(event, ui) {
										ui.item.option.selected = true;
										this._trigger("select", event, {
											item : ui.item.option
										});
									},

									autocompletechange : "_removeIfInvalid"
								});
							},

							_createShowAllButton : function() {
								var input = this.input, wasOpen = false;

								$("<a>")
										.attr("tabIndex", -1)
										.tooltip()
										.appendTo(this.wrapper)
										.button(
												{
													icons : {
														primary : "ui-icon-triangle-1-s"
													},
													text : false
												})
										.removeClass("ui-corner-all")
										.addClass(
												"custom-combobox-toggle ui-corner-right")
										.mousedown(
												function() {
													wasOpen = input
															.autocomplete(
																	"widget")
															.is(":visible");
												}).click(function() {
											input.focus();

											// Close if already visible
											if (wasOpen) {
												return;
											}

											// Pass empty string as value to search for, displaying all results
											input.autocomplete("search", "");
										});
							},

							_source : function(request, response) {
								var matcher = new RegExp($.ui.autocomplete
										.escapeRegex(request.term), "i");
								response(this.element
										.children("option")
										.map(
												function() {
													var text = $(this).text();
													if (this.value
															&& (!request.term || matcher
																	.test(text)))
														return {
															label : text,
															value : text,
															option : this
														};
												}));
							},

							_removeIfInvalid : function(event, ui) {

								// Selected an item, nothing to do
								if (ui.item) {
									return;
								}

								// Search for a match (case-insensitive)
								var value = this.input.val(), valueLowerCase = value
										.toLowerCase(), valid = false;
								this.element
										.children("option")
										.each(
												function() {
													if ($(this).text()
															.toLowerCase() === valueLowerCase) {
														this.selected = valid = true;
														return false;
													}
												});

								// Found a match, nothing to do
								if (valid) {
									return;
								}

								// Remove invalid value
								this.input.val("").attr("title",
										value + " didn't match any item")
										.tooltip("open");
								this.element.val("");
								this._delay(function() {
									this.input.tooltip("close").attr("title",
											"");
								}, 2500);
								this.input.autocomplete("instance").term = "";
							},

							_destroy : function() {
								this.wrapper.remove();
								this.element.show();
							}
						});
	})(jQuery);

	$(function() {
		$("#oeExamId").combobox();
		$("#toggle").click(function() {
			$("#oeExamId").toggle();
		});
	});

	$(function() {
		$("#usEmployeeIdStudent").combobox();
		$("#toggle").click(function() {
			$("#usEmployeeIdStudent").toggle();
		});
	});
</script>
<script>
	var onlineAssignedExamListTable;
	$(document)
			.ready(
					function() {
						onresetCalled();
						onlineAssignedExamListTable = $(
								'#onlineAssignedExamListTableId')
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "onlineExamAssignedListByJson.do?uid="
													+ Math.random(),
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 6, 'desc' ] ],
											"sPaginationType" : "full_numbers",
											"aoColumns" : [
													null,
													null,
													null,
													null,
													null,
													null,
													null,
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															if (obj.aData[7] == "true"
																	|| obj.aData[7] == true) {
																return "<img src=\"images/png/public_access.png\" id=\"blockAssignedExam\" onclick=\"blockUnlockOnlineExamAssigned(\'"
																		+ obj.aData[8]
																		+ "','false')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Block Online Assigned Exam \"/>";
															} else {
																return "<img src=\"images/png/private_access.png\" id=\"unBlockAssignedExam\" onclick=\"blockUnlockOnlineExamAssigned(\'"
																		+ obj.aData[8]
																		+ "','true')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"UnBlock Online Assigned Exam \"/>";
															}
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<img src=\"images/png/cancel_48.png\" id=\"delAssignedExam\" onclick=\"deleteOnlineExamAssigned(\'"
																	+ obj.aData[8]
																	+ "\')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Online Assigned Exam \"/>";
														}
													} ]
										});
					});

	function deleteOnlineExamAssigned(oeAssignedExamId) {

		var confirmDelete = confirm("Are sure want to delete the Assigned Exam ?");
		if (confirmDelete) {
			var url = 'deleteOnlineExamAssigned.do?oeExamAutoId='
					+ oeAssignedExamId + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					var alertMsg = "<div align='left' style='background-color:#ffffff;vertical-align: top;'>";
					alertMsg += "<span style='color:red;font-size:14px; font-weight:bold;'>\n\n";
					alertMsg += data[0].value;
					alertMsg += "</span></div>";
					alertBox("Online Exam Delete" , alertMsg , 500, 225);
					onlineAssignedExamListTable.fnFilter(' ', 0);
				}
			});
		}
	}

	function blockUnlockOnlineExamAssigned(oeAssignedExamId, status) {
		var url = 'blockUnlockOnlineExamAssigned.do?oeExamAutoId='
				+ oeAssignedExamId + "&status=" + status + "&uid="
				+ Math.random();

		$.getJSON(url, function(data) {
			if (data != null & data.length > 0) {
				
				var alertMsg = "<div align='left' style='background-color:#ffffff;vertical-align: top;'>";
				alertMsg += "<span style='color:green;font-size:14px; font-weight:bold;'>\n\n";
				alertMsg += data[0].value;
				alertMsg += "</span></div>";
				alertBox("Online Exam Block/UnBlock" , alertMsg , 500, 225);
				
				onlineAssignedExamListTable.fnFilter(' ', 0);
			}
		});
	}

	function onsubmitCalled() {

		var oeExamId = $("#oeExamId").val();

		if (oeExamId == null || oeExamId == undefined
				|| allTrim(oeExamId) == "") {
			alert("Please select an Online Exam Name.");
			return false;
		}

		var usEmployeeIdStudent = $("#usEmployeeIdStudent").val();

		if (usEmployeeIdStudent == null || usEmployeeIdStudent == undefined
				|| allTrim(usEmployeeIdStudent) == "") {
			alert("Please select a User Id.");
			return false;
		}

		var oeAssignedExamDate = $("#oeAssignedExamDate").val();

		if (oeAssignedExamDate == null || oeAssignedExamDate == undefined
				|| allTrim(oeAssignedExamDate) == "") {
			alert("Please select a date to assign exam.");
			return false;
		}

		document.forms["onlineAssignedExamForm"].submit();
	}

	function onresetCalled() {
		document.forms["onlineAssignedExamForm"].reset();
	}

	function onCancelCalled(url) {
		window.location.href = url;
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp" />
		</header>
		<div class="container whiteBg clearfix" width="90%">
			<div class="latest-title">
				<p>Online Exam Assigning Panel</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="onlineAssignedExamForm" id="onlineAssignedExamForm" action="createOnlineExamAssigned.do" method="post" enctype="multipart/form-data"
					autocomplete="off" show_alert="1" onSubmit="return validateForm()">
					<div>
						<table width="95%">
							<tr>
								<td>
									<div id="onlineAssignedExamDiv"
										style="width: 100%; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0" width="100%" style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td align="left" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" nowrap="nowrap" colspan="10"><s:if test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan" style="color: red; font-weight: bold;"><s:property escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="right" nowrap="nowrap" width="10%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Exam Name&nbsp;&nbsp;&nbsp;</span></td>
												<td align="left" nowrap="nowrap" width="20%"><s:select name="onlineExamAssigned.onlineExam.oeExamId" id="oeExamId" theme="simple"
														cssStyle="width: 250px;" list="onlineExamList" listKey="oeExamId" listValue="oeExamName" /></td>
												<td align="left" nowrap="nowrap" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="right" nowrap="nowrap" width="13%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Student Id&nbsp;&nbsp;&nbsp;</span></td>
												<td align="left" nowrap="nowrap" width="15%"><s:select name="usEmployeeIdStudent" id="usEmployeeIdStudent" theme="simple"
														cssStyle="width: 185px;" list="usersList" listKey="usEmployeeId" listValue="usUserID" /></td>
												<td align="left" nowrap="nowrap" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="right" nowrap="nowrap" width="13%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Assigned Date&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
												<td align="left" nowrap="nowrap" width="10%"><s:textfield readonly="true" class="smalltextbox" name="onlineExamAssigned.oeAssignedExamDate"
														id="oeAssignedExamDate"
														style="width: 125px; background: url('js/datepicker/calendar.gif') no-repeat scroll right center #FFFFFF;vertical-align: middle;" theme="simple" />
													<script>
														new Zapatec.Calendar.setup(
																{
																	inputField : 'oeAssignedExamDate',
																	ifFormat : '%m-%d-%Y %H-%M',
																	showsTime : true,
																	button : 'oeAssignedExamDate',
																	singleClick : true,
																	step : 1,
																	timeFormat : '12'
																});
													</script>&nbsp;&nbsp;</td>
												<td align="left" nowrap="nowrap" width="2%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="10"><input type="button" class="btnstyle" name="createAssignedOnlineExam" value="Assign Online Exam To User"
													onclick="onsubmitCalled();" style="width: 250px;" /> &nbsp;&nbsp; <input type="button" class="btnstyle" value="Cancel"
													onclick="onCancelCalled('createOnlinePreExamAssigned.do')" /></td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
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
			<div id="signupwrapper1" align="center" style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv" style="padding: 10px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="onlineAssignedExamListTableId">
							<thead>
								<tr>
									<th width="20%" align="center">Exam Name</th>
									<th width="20%" align="center">Student Name</th>
									<th width="15%" align="center">Student Id</th>
									<th width="15%" align="center">Actual Date</th>
									<th width="15%" align="center">Assigned Date</th>
									<th width="11%" align="center">Created By</th>
									<th width="15%" align="center">Created Date</th>
									<th width="2%" align="center">Status</th>
									<th width="2%" align="center">Delete</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="9" class="dataTables_empty">Loading data from Server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
