<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<style>
.imgDelete {
	width: 25px;
	height: 25px;
	vertical-align: middle;
	cursor: pointer;
	background: url('images/png/cancel_48.png') no-repeat;
}

.imgDelete:hover {
	width: 25px;
	height: 25px;
	vertical-align: middle;
	cursor: pointer;
        background: url('images/png/cancel_48_disabled.png') no-repeat;
}
</style>
<script>
	var collegeListTable;
	$(document)
			.ready(
					function() {
						collegeListTable = $('#collegeListTableId')
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "collegeListByJson.do?uid="
													+ Math.random(),
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 7, 'desc' ] ],
											"aoColumns" : [
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<a href=\"collegePreUpdate.do?clCollegeId="
																	+ obj.aData[8]
																	+ "\" style=\"text-decoration: underline;color:blue;\">"
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
															return "<div style='width:95px; overflow-y:auto;'>"
																	+ obj.aData[4]
																	+ "</div>";
														}
													},
													null,
													null,
													null,
													{
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteCollege(\'"
																	+ obj.aData[8]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" title=\"Delete Selected College\"/></a>";
														}
													} ]
										});
					});
	function deleteCollege(collegeId) {
		
		var confirmDelete = confirm("Are sure want to delete College?");
		if(confirmDelete)
		{
			var url = 'deleteCollege.do?clCollegeId=' + collegeId + "&uid="
				+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					collegeListTable.fnFilter(' ', 0);
	
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
				<p>Search College</p>
			</div>
			<div id="signupwrapper1" align="center" style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv" style="width: 100%; padding: 20px; font-size: 12px;">
					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors">
							<span class="errorMessage" id="errorSpan" style="color: red; font-weight: bold;"><s:property escape="false" /></span>
						</s:iterator>
					</s:if>
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="collegeListTableId">
							<thead>
								<tr>
									<th width="20%">College Name</th>
									<th width="10%">College Type</th>
									<th width="10%">Email</th>
									<th width="7%">Mobile No</th>
									<th width="15%">Address</th>
									<th width="10%">WebSite URL</th>
									<th width="8%">Created By</th>
									<th width="10%">Created Date</th>
									<th width="5%" align="center"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="10" class="dataTables_empty">Loading data from Server</td>
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
