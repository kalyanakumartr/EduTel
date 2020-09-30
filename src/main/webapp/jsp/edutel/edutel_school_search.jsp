<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var schoolListTable;

	$(document)
			.ready(
					function() {
						schoolListTable = $('#schoolListTableId')
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "schoolListByJson.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"aaSorting" : [ [ 7, 'desc' ] ],
											"sServerMethod" : "POST",
											"aoColumns" : [
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<a href=\"schoolPreUpdate.do?scSchoolId="
																	+ obj.aData[8]
																	+ "\"  style=\"text-decoration: underline;color:blue;\">"
																	+ obj.aData[0]
																	+ "</a>";
														}
													},
													null,
													null,
													null,
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<div style='overflow-y:auto;'>"
																	+ obj.aData[4]
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
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteSchool(\'"
																	+ obj.aData[8]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Selected School\"/></a>";
														}
													} ]
										});
					});
	function deleteSchool(schoolId) {
		
		var confirmDelete = confirm("Are sure want to delete School?");
		if(confirmDelete)
		{
			var url = 'deleteSchool.do?scSchoolId=' + schoolId + "&uid="
					+ Math.random();
	
			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					schoolListTable.fnFilter(' ', 0);
	
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
				<p>Search School Details</p>
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
							id="schoolListTableId">
							<thead>
								<tr>
									<th width="20%">School Name</th>
									<th width="5%">School Type</th>
									<th width="10%">Email</th>
									<th width="5%">Mobile No</th>
									<th width="23%">Address</th>
									<th width="15%">WebSite URL</th>
									<th width="10%">Created By</th>
									<th width="10%">Created Date</th>
									<th width="2%" align="center">Delete</th>
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
</body>
</html>
