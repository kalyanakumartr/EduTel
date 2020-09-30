<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var enquiryListTable;
	$(document)
			.ready(
					function() {
						enquiryListTable = $('#enquiryListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lfrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "studentEdutelEnquiryList.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 7, 'desc' ] ],
											"aoColumns" : [
													null,
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
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteEnquiry(\'"
																	+ obj.aData[8]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Selected Enquiry\"/></a>";
														}
													} ]
										});
					});
	function deleteEnquiry(enquiryId) {

		var confirmDelete = confirm("Are sure want to delete Enquiry?");
		if (confirmDelete) {
			var url = 'deleteEnquiry.do?enquiryId=' + enquiryId + "&uid="
					+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					enquiryListTable.fnFilter(' ', 0);

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
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>EduTel Enquires</p>
			</div>
			<br>
			<div id="signupwrapper1" align="center"
				style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv"
					style="width: 100%; padding: 20px; font-size: 12px;">
					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors">
							<span class="errorMessage" id="errorSpan"
								style="color: red; font-weight: bold;"><s:property
									escape="false" /></span>
						</s:iterator>
					</s:if>

					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="enquiryListTableId">
							<thead>
								<tr>
									<th width="15%">Name</th>
									<th width="15%">Email Id</th>
									<th width="15%">School Name</th>
									<th width="10%">Mobile No</th>
									<th width="10%">Board</th>
									<th width="10%">Class</th>
									<th width="10%">Enquirer State</th>
									<th widht="10%">Created Date</th>
									<th width="5%" align="center"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="9" class="dataTables_empty">Loading data from
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
</body>
</html>
