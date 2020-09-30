<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var toppersListTable;
	$(document)
			.ready(
					function() {
						toppersListTable = $('#toppersListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lfrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "toppersListByJson.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 6, 'desc' ] ],
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
														"sClass" : "nowrap_column",
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															var rating = "";
															for ( var i = 0; i < obj.aData[8]; i++) {
																rating = rating
																		+ "<img src=\"images/png/star_yellow.png\" style=\"cursor:pointer; vertical-align:middle;height:14px;width:14px;\"/>";
															}
															for ( var i = obj.aData[8]; i < 5; i++) {
																rating = rating
																		+ "<img src=\"images/png/star_yellow_disabled.png\" style=\"cursor:pointer; vertical-align:middle;height:14px;width:14px;\"/>";
															}
															return rating;
														}
													},
													null,
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteTopper(\'"
																	+ obj.aData[10]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Selected Enquiry\"/></a>";
														}
													} ]
										});
					});
	function deleteTopper(topperId) {

		var confirmDelete = confirm("Are sure want to delete this Topper?");
		if (confirmDelete) {
			var url = 'deleteTopper.do?topperId=' + topperId + "&uid="
					+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					toppersListTable.fnFilter(' ', 0);

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
				<p>Toppers Club</p>
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
							id="toppersListTableId">
							<thead>
								<tr>
									<th width="15%">Exam Name</th>
									<th width="10%">Student Name</th>
									<th width="10%">School Name</th>
									<th width="10%">Subject</th>
									<th width="10%">Chapter</th>
									<th width="10%">Start Time</th>
									<th width="10%">End Time</th>
									<th widht="5%">Marks</th>
									<th widht="10%">Ratings</th>
									<th widht="5%">No Of Questions</th>
									<th width="5%" align="center"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="11" class="dataTables_empty">Loading data
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
