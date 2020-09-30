<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<link type="text/css" rel="stylesheet" href="css/dataTables.tableTools.css">
<script type="text/javascript" charset="utf-8" src="js/ZeroClipboard.js"></script>
<script type="text/javascript" charset="utf-8" src="js/dataTables.tableTools.js"></script>
<script>
	var eformListTable;
	$(document)
			.ready(
					function() {
						eformListTable = $('#eformListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lfrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "eformListByJson.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 6, 'desc' ] ],
											"oTableTools" : {
												"sSwfPath": "/swf/copy_csv_xls_pdf.swf",
												"sRowSelect" : "multi",
												"aButtons" : [{
									                "sExtends":    "copy",
									                "bSelectedOnly": "true"
									            },"print","xls"]
											},
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
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															if (obj.aData[7] == "true")
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
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteEForm(\'"
																	+ obj.aData[8]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Selected Enquiry\"/></a>";
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
					eformListTable.fnFilter(' ', 0);

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
		<div class="container whiteBg clearfix" style="width: 95%">
			<div class="latest-title">
				<p>EduTel Online E-Forms</p>
			</div>
			<br>
			<div id="signupwrapper1" align="center" style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv" style="width: 100%; padding: 20px; font-size: 12px;">
					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors">
							<span class="errorMessage" id="errorSpan" style="color: red; font-weight: bold;"><s:property escape="false" /></span>
						</s:iterator>
					</s:if>
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="eformListTableId">
							<thead>
								<tr>
									<th width="15%">Student Name</th>
									<th width="15%">College Name</th>
									<th width="20%">Address</th>
									<th width="5%">Total Form Cost</th>
									<th width="5%">Status</th>
									<th width="15%">School Name</th>
									<th width="15%">Created Date</th>
									<th width="5%"></th>
									<th width="5%"></th>
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
