<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<link rel="stylesheet" href="css/style_checkbox.css" />
<script type="text/javascript" src="js/jspatch.js"></script>
<style>
body {
	color: #000;
	font-size: 13px;
	font-family: "Lato", Calibri, Arial, sans-serif;
}

.ui-state-default,.ui-widget-content .ui-state-default,.ui-widget-header .ui-state-default
	{
	color: #000;
	font-size: 13px;
	font-weight: normal;
}
</style>
<script>
	var eFormListTable;
	$(document)
			.ready(
					function() {
						eFormListTable = $('#eFormListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lrti',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "eformListByJson.do",
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"bPaginate" : false,
											"aaSorting" : [ [ 0, 'desc' ] ],
											"aoColumns" : [
													null,
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															if (obj.aData[1] == "true")
																return "<img src=\"images/png/checked.png\"style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Form Purchased\"/>";
															else
																return "<img src=\"images/png/warning_48.png\"style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Form Pending\"/>";
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															if (obj.aData[2]=="true") {
																return "<img src=\"images/png/cancel_48_disabled.png\"style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Not Applicable\"/>";
															} 
															else 
															{
																return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteEForm(\'"
																+ obj.aData[2]
																+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Selected Enquiry\"/></a>";
															}
														}
													} ]
										});

					});

	function deleteEForm(formId) {

		var confirmDelete = confirm("Are sure want to Remove this College Application Form?");
		if (confirmDelete) {
			var url = 'deleteEForm.do?formId=' + formId + "&uid="
					+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					window.location.href = "viewOnlineFormRequest.do";
				}
			});
		}
	}
	function addToFormCart(clCollegeId) {
		window.location.href = "viewOnlineFormRequest.do" + "?clgId="
				+ clCollegeId;
	}
	function purchaseForms() {
		var formCount = $("#formCountSpan").text();
		var formCost = $("#formCost").text();
		var total = $("#total").text();

		if ((allTrim(formCount) == "0" || allTrim(formCount) == "")
				&& (allTrim(formCost) == "0.00" || allTrim(formCost) == "")
				&& (allTrim(total) == "0.00" || allTrim(total) == "")) {

			alert("Please Select Any One Form");
			return false;

		} else {

			window.location.href = "purchaseEForm.do";
		}
	}
</script>
<style>
.roundedCornerDiv {
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	border: 1px solid #DDD;
	box-shadow: 0 0 0px #888;
}

.spanForm {
	font-weight: bold;
	font-size: 13px;
	color: green;
}

.span14Form {
	font-weight: bold;
	font-size: 14px;
	color: red;
	align: right;
}

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
	font-family: Arial, Trebuchet MS, Tahoma;
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
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width: 85%;">
			<div class="latest-title">
				<p>Application Forms Request</p>
			</div>
			<div id="signupwrapper" align="center" style="width: 100%;">
				<table style="width: 100%;">
					<tr>
						<td colspan="2"><s:iterator value="actionErrors">
								<span class="errorMessage" id="errorSpan" style="color: red; font-weight: bold;"><s:property escape="false" /></span>
							</s:iterator></td>
					</tr>
					<tr>
						<td width="70%" valign="top" style="padding: 15px;">
							<table>
								<s:set name="incr" value="%{0}" />
								<s:iterator value="collegeList" var="forms">
									<s:set name="modByRow" value="%{#incr%5}" />
									<s:if test="#modByRow==5">
										<tr>
									</s:if>
									<td align="center" style="padding: 15px;"><label for="cb${incr}" class="css-label radGroup1">
											<div class="box effect1">
												<input name="cb${incr}" id="cb${incr}" type="checkbox" class="css-checkbox"> <label for="cb${incr}" class="css-label radGroup1"
													style="align: right; float: right; valign: top; margin-top: -18px; margin-right: -30px;"></label>
												<div class="headerDiv" onclick="addToFormCart('${clCollegeId}');">${clCollegeName}</div>
												<div class="bodyDiv">
													<img src="${uploadFileScreenShot}" alt="form" onclick="addToFormCart('${clCollegeId}');" width="100" height="80">
													<s:hidden name="clFormCostOthers" id="clFormCostOthers%{#incr}"></s:hidden>
													<s:hidden name="clFormCostSCST" id="clFormCostSCST%{#incr}"></s:hidden>
													<s:hidden name="clCourierCharge" id="clCourierCharge%{#incr}"></s:hidden>
													<s:hidden name="clEduTelCharge" id="clEduTelCharge%{#incr}"></s:hidden>
													<s:hidden name="clFormOpenDate" id="clFormOpenDate%{#incr}"></s:hidden>
													<s:hidden name="clFormCloseDate" id="clFormCloseDate%{#incr}"></s:hidden>
												</div>
											</div>
									</label></td>
									<s:if test="#modByRow==4">
										</tr>
									</s:if>
									<s:set name="incr" value="%{#incr+1}" />
									<s:set name="modByRow" value="" />
								</s:iterator>
							</table>
						</td>
						<td width="30%" valign="top" align="right">
							<div id="formPurchaseId" class="roundedCornerDiv" style="height: 400px;">
								<div align="left" class="activity">&nbsp;&nbsp;Application Forms&nbsp;&nbsp;</div>
								<div style="overflow-y: scroll; padding: 1px; height: 370px;">
									<table width="100%" border="1" style="border: 2px solid #ececec;">
										<tr>
											<td width="65%" style="border: 2px solid #ececec; padding: 7px;" width="65%"><span class="spanForm">No of Forms Selected</span></td>
											<td width="35%" align="right" style="border: 2px solid #ececec; padding: 7px;"><span id="formCountSpan" class="span14Form">${collegeListSize}</span></td>
										</tr>
										<tr>
											<td width="65%" style="border: 2px solid #ececec; padding: 7px;"><span class="spanForm">Form(s) Cost</span></td>
											<td width="35%" align="right" style="border: 2px solid #ececec; padding: 7px;"><span id="formCost" class="span14Form"> <s:property
														value="getText('\u20B9 {0,number,#,##0.00}',{formCost})" />
											</span></td>
										</tr>
										<tr>
											<td width="65%" style="border: 2px solid #ececec; padding: 7px;"><span class="spanForm">Courier Charge</span></td>
											<td width="35%" align="right" style="border: 2px solid #ececec; padding: 7px;"><span id="courierCost" class="span14Form"> <s:property
														value="getText('\u20B9 {0,number,#,##0.00}',{courierCost})" /></span></td>
										</tr>
										<tr>
											<td width="65%" style="border: 2px solid #ececec; padding: 7px;"><span class="spanForm">Edutel Service Charge</span></td>
											<td width="35%" align="right" style="border: 2px solid #ececec; padding: 7px;"><span id="serviceCost" class="span14Form"> <s:property
														value="getText('\u20B9 {0,number,#,##0.00}',{eduTelServiceCost})" /></span></td>
										</tr>
										<tr>
											<td width="65%" style="border: 2px solid #ececec; padding: 7px;"><span class="spanForm">Total</span></td>
											<td width="35%" align="right" style="border: 2px solid #ececec; padding: 7px;"><span id="total" class="span14Form"> <s:property
														value="getText('\u20B9 {0,number,#,##0.00}',{total})" /></span></td>
										</tr>
										<tr>
											<td align="center" colspan="2" style="border: 2px solid #ececec; padding: 8px;"><input type="button" class="btnstyle" name="submitForm"
												value="Purchase Application Form" onclick="purchaseForms();" style="width: 175px;" /></td>
									</table>
									<br>
									<table cellpadding="0" cellspacing="0" border="0" class="display" id="eFormListTableId">
										<thead>
											<tr>
												<th style="width: 80%;">College Application Forms</th>
												<th style="width: 10%;"></th>
												<th style="width: 10%;" align="center"></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="2" class="dataTables_empty">Loading data from Server</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
<style>
.box {
	background-color: #336699;
	width: 130px;
	border: 5px solid #336699;
}

.box:hover {
	background-color: #336699;
	width: 130px;
	border: 5px solid##336699;
	cursor: pointer;
}

.box .headerDiv {
	color: white;
	padding: 2px;
	font-family: Tahoma;
	font-size: 11px;
	font-weight: bold;
	height: 30px;
}

.box .bodyDiv {
	background: #ffffff;
	color: white;
	padding: 5px;
	font-size: 12px;
	height: 110px;
}

.box .effect1 {
	-webkit-box-shadow: 0 10px 6px -6px #777;
	-moz-box-shadow: 0 10px 6px -6px #777;
	box-shadow: 0 10px 6px -6px #777;
}
</style>
</html>
