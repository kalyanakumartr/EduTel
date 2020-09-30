<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<link type="text/css" rel="stylesheet"
	href="css/dataTables.tableTools.css">
<script type="text/javascript" charset="utf-8" src="js/ZeroClipboard.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/dataTables.tableTools.js"></script>
<style>
.activity {
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow: inset 0px 1px 0px 0px #ffffff;
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f9f9f9
		), color-stop(1, #e9e9e9));
	background: -moz-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -webkit-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -o-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -ms-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9',
		endColorstr='#e9e9e9', GradientType=0);
	background-color: #f9f9f9;
	border-bottom: thin;
	border-color: #dcdcdc;
	vertical-align: middle;
	width: 100%;
	height: 28px;
	display: inline-block;
	cursor: pointer;
	color: #006666;
	font-family: Trebuchet MS;
	font-size: 13px;
	font-weight: bold;
	padding-top: 5px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px 5px 0px 0px;
}
</style>
<script>
	var serialKeyTable;
	$(document).ready(function() {
		onresetCalled();
		serialKeyTable = $('#keyGenListTableId').dataTable({
			"sDom" : 'T<"clear">lrtip',
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "getSerialKeyAssignedUsersList.do",
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"sServerMethod" : "POST",
			"aaSorting" : [ [ 6, 'desc' ] ],
			"oTableTools" : {
				"sRowSelect" : "multi",
				"aButtons" : [ "select_all", "select_none" ]
			}
		});
	});

	function onsubmitCalled() {
		var batch = $("#serialBatch").val();
		var promo = $("#serialPromo").val();
		var empId = $("#employeeId").val();
		var noofKeys = $("#numberOfKeys").val();
		var price = $("#serialKeyPrice").val();
		var sellingPrice = $("#serialKeySellingPrice").val();

		if (allTrim(batch) == "") {
			alert("Please Select Student Batch");
			return false;
		}

		if (allTrim(promo) == "") {
			alert("Please Select Student Promotional Code");
			return false;
		}

		if (allTrim(empId) == "") {
			alert("Please Select Employee Name");
			return false;
		}

		if (allTrim(noofKeys) == "") {
			alert("Please type Number of Keys to Generate.");
			return false;
		}

		if (allTrim(price) == "" || parseFloat(price) < 0) {
			alert("Please type Key Amount.");
			return false;
		}
		if (allTrim(sellingPrice) == "" || parseFloat(sellingPrice) < 0) {
			alert("Please type Key Selling Amount.");
			return false;
		}
		document.forms["serialKeyGenerateForm"].submit();
	}

	function onresetCalled() {

		$("#serialBatch").val("");
		$("#serialPromo").val("");
		$("#numberOfKeys").val("");
		$("#serialKeyPrice").val("0.00");
		$("#serialKeySellingPrice").val("0.00");
		$("#roleId").text("");
		$("#employeeId").val("");
		$("#usEmployeeId").val("");
		$("#serialKeyTotalPrice").text("");
	}

	function splitAndSetEmployeeIdAndRole() {
		var spanValue = $("#employeeId").val();

		if (allTrim(spanValue) != "" && spanValue.indexOf("-") > 0) {

			var spanArray = spanValue.split("-");
			$("#usEmployeeId").val(spanArray[0]);
			$("#roleId").text(" " + spanArray[1]);
		} else {
			$("#usEmployeeId").val("");
			$("#roleId").text("");
		}
	}

	function serialKeyTotalPrice() {
		var noofKeys = $("#numberOfKeys").val();
		var price = $("#serialKeyPrice").val();

		if ((allTrim(noofKeys) != "" && allTrim(noofKeys) != "0")
				&& (allTrim(price) != "" && allTrim(noofKeys) > "0")) {
			$("#serialKeyTotalPrice").text(
					"Total Price : Rs. " + noofKeys * price);

		}

	}

	function copyToSellingPrice() {
		$("#serialKeySellingPrice").val($("#serialKeyPrice").val());
	}

	function searchAndPrintSerialKeys(url) {

		var oTT = TableTools.fnGetInstance('keyGenListTableId');
		var aSelectedTrs = oTT.fnGetSelected();
		var str = "";
		for ( var i = 0; i < aSelectedTrs.length; i++) {
			var data = aSelectedTrs[i];
			// and parse the row:
			var nTds = $('td', data);

			// then list elements of the row by:
			str += $(nTds[3]).text() + ",";
		}
		if (allTrim(str) == "") {
			alert("Please Select the Serial Key(s) from the Table.");
			return false;
		} else {
			window.location.href = url + "?key=" + str;
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
				<p>Serial Key Generation</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="serialKeyGenerateForm" id="serialKeyGenerateForm"
					action="createAndSaveSerialKey.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="serialKeyGenerateDiv"
										style="width: 775px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 20px; border: 1px; border-color: gray;">
											<tr
												style="background-color: #f9f9f9; height: 28px; width: 100%; border-bottom: 1px solid #dcdcdc; color: #006666; font-family: Trebuchet MS; font-size: 13px; font-weight: bold; padding-top: 5px; display: table-row; cursor: pointer; text-decoration: none; text-shadow: 0px 1px 0px #ffffff; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px 5px 0px 0px;">
												<td align="left" colspan="3" width="95%"><div
														align="left" style="width: 100%">&nbsp;&nbsp;Serial
														Key Form&nbsp;&nbsp;</div></td>
												<td align="right" width="5%"><a class="img-responsive"
													href="#advanced_serial_key_search" data-toggle="modal"><img
														src="images/png/advanced_search.png"
														style="cursor: pointer; height: 25px; width: 25px; float: right;"
														title="Advanced Serial Key Search" /></a></td>
											</tr>
											<tr>
												<td valign="top" align="center" width="100%" colspan="4"
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
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="15%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Student
														Batch</span><span style="color: red;">*</span></td>
												<td align="left" width="35%"><s:select
														name="serialBatch" id="serialBatch" theme="simple"
														list="serialBatchList" listKey="label" listValue="value"
														headerKey=""
														headerValue="--Select Student Batch For Serial Key--"
														style="width:250px;" cssClass="loginUserId" /></td>
												<td align="left" width="15%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Promotion
														Type</span><span style="color: red;">*</span></td>
												<td align="left" width="35%"><s:select
														name="serialPromo" id="serialPromo" theme="simple"
														list="epromoList" listKey="label" listValue="value"
														headerKey="" headerValue="--Select Promotional Type--"
														style="width:250px;" cssClass="loginUserId" /></td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="15%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Employee
														Name</span><span style="color: red;">*</span></td>
												<td align="left" width="15%"><s:select
														name="employeeId" id="employeeId" theme="simple"
														list="usersList" listKey="label" listValue="value"
														headerKey=""
														headerValue="--Select Employee For Serial Key--"
														style="width:250px;" cssClass="loginUserId"
														onchange="splitAndSetEmployeeIdAndRole();"
														onkeydown="splitAndSetEmployeeIdAndRole();"
														onkeyup="splitAndSetEmployeeIdAndRole();" /></td>
												<td align="left" width="15%">&nbsp;&nbsp;&nbsp;<span
													name="roleId" id="roleId" theme="simple"
													style="color: red; font-size: 12px;"></span></td>
												<td align="left" width="15%">&nbsp;&nbsp;&nbsp;<s:hidden
														name="usEmployeeId" id="usEmployeeId" /></td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="15%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Cost
														Price</span><span style="color: red;">*</span></td>
												<td align="left" width="35%"><s:textfield
														name="serialKeyPrice" id="serialKeyPrice" theme="simple"
														maxlength="5"
														onkeyup="checkOnlyAmount(this);serialKeyTotalPrice();"
														onkeydown="checkOnlyAmount(this);serialKeyTotalPrice();"
														onchange="amtDecimalValidation(this);copyToSellingPrice();" />&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="15%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Selling
														Price</span><span style="color: red;">*</span></td>
												<td align="left" width="35%"><s:textfield
														name="serialKeySellingPrice" id="serialKeySellingPrice"
														theme="simple" maxlength="5"
														onkeyup="checkOnlyAmount(this);serialKeyTotalPrice();"
														onkeydown="checkOnlyAmount(this)"
														onchange="amtDecimalValidation(this)" />&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="15%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;No
														of Keys</span><span style="color: red;">*</span></td>
												<td align="left" width="35%"><s:textfield
														name="numberOfKeys" id="numberOfKeys" theme="simple"
														onkeyup="checkIsNumberAndLength(this,3), serialKeyTotalPrice()"
														onkeydown="checkIsNumberAndLength(this,3), serialKeyTotalPrice()"
														onchange="serialKeyTotalPrice();" /></td>
												<td align="left" width="50%" colspan="2">&nbsp;&nbsp;&nbsp;<span
													name="serialKeyTotalPrice" id="serialKeyTotalPrice"
													theme="simple" style="color: red; font-size: 12px;"></span></td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="4"><input type="button"
													class="btnstyle" name="createSerialKey"
													value="Generate Serial Key" href="javascript:void(0);"
													onclick="onsubmitCalled();" style="width: 150px;" />
													&nbsp;&nbsp; <input type="button" class="btnstyle"
													value="Print Serial Key"
													onclick="searchAndPrintSerialKeys('printSerialKey.do')"
													style="width: 150px;" />&nbsp;&nbsp; <input type="button"
													class="btnstyle" value="Reset" onclick="onresetCalled()" /></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4">&nbsp;&nbsp;&nbsp;</td>
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
							id="keyGenListTableId">
							<thead>
								<tr>
									<th width="20%" align="center">Employee Name</th>
									<th width="10%" align="center">Batch</th>
									<th width="10%" align="center">Serial No</th>
									<th width="10%" align="center">Serial Key</th>
									<th width="10%" align="center">Price</th>
									<th width="10%" align="center">Promo</th>
									<th width="10%" align="center">Created Date</th>
									<th width="10%" align="center">Employee Type</th>
									<th width="10%" align="center">Status</th>
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
			<s:include value="m_edutel_advanced_serial_key_search.jsp"></s:include>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
