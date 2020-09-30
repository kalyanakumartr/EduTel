<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var onlineTestSeriesExamQuestionListTable;
	$(document)
			.ready(
					function() {
						onlineTestSeriesExamQuestionListTable = $(
								'#onlineTestSeriesExamQuestionListTableId')
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "onlineTestSeriesExamQuestionListByJson.do?uid="
													+ Math.random(),
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 4, 'desc' ] ],
											"sPaginationType" : "full_numbers",
											"aoColumns" : [
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															if ((obj.aData[0] == "true" || obj.aData[0] == true)
																	&& (obj.aData[8] == "false" || obj.aData[8] == false)) {
																return "<img src=\"images/png/download_green.png\" id=\"testSeriesAnswesAvail\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Test Series Answers Available\"/>";
															} else if ((obj.aData[0] == "false" || obj.aData[0] == false)
																	&& (obj.aData[8] == "true" || obj.aData[8] == true)) {
																return "<img src=\"images/png/download_orange.png\" id=\"testSeriesAnswesValidated\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Test Series Answers Validated\"/>";
															} else {
																return "<img src=\"images/png/download_red.png\" id=\"testSeriesAnswesNotAvail\"  style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Test Series Answers Not Available\"/>";
															}
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<a href=\"onlineTestSeriesExamAnswerSearch.do?oeExamQuestionId="
																	+ obj.aData[7]
																	+ "&uid="
																	+ Math
																			.random()
																	+ "\" style=\"text-decoration: underline;color:blue;\">"
																	+ obj.aData[1]
																	+ "</a>";
														}
													},
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
															if (obj.aData[6] == "true"
																	|| obj.aData[6] == true) {
																return "<img src=\"images/png/unlock.png\" id=\"blockExamQuestion\" onclick=\"blockUnlockExamQuestion(\'"
																		+ obj.aData[7]
																		+ "','false')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Block Online Exam Question\"/>";
															} else {
																return "<img src=\"images/png/lock.png\" id=\"unBlockExamQuestion\" onclick=\"blockUnlockExamQuestion(\'"
																		+ obj.aData[7]
																		+ "','true')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"UnBlock Online Exam Question\"/>";
															}
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<img src=\"images/png/cancel_48.png\" id=\"delExamQuestion\" onclick=\"deleteExamQuestion(\'"
																	+ obj.aData[7]
																	+ "\')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Online Exam Question\"/>";
														}
													} ]
										});
						setFooter();
					});

	function deleteExamQuestion(oeExamQuestionId) {
		var confirmDelete = confirm("Are sure want to delete the Question?");
		if (confirmDelete) {
			var url = 'deleteOnlineExamQuestion.do?oeExamQuestionId='
					+ oeExamQuestionId + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					onlineTestSeriesExamQuestionListTable.fnFilter(' ', 0);
				}
			});
		}
	}

	function blockUnlockExamQuestion(oeExamQuestionId, status) {
		var url = 'blockUnlockExamQuestion.do?oeExamQuestionId='
				+ oeExamQuestionId + "&status=" + status + "&uid="
				+ Math.random();

		$.getJSON(url, function(data) {
			if (data != null & data.length > 0) {
				alert(data[0].value);
				onlineTestSeriesExamQuestionListTable.fnFilter(' ', 0);
			}
		});
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp" />
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online TestSeries Exam Question Panel</p>
			</div>
			<div id="signupwrapper" align="center">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv"
					style="padding: 10px; font-size: 12px; width: 950px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center"
						style="width: 900px;">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="onlineTestSeriesExamQuestionListTableId">
							<thead>
								<tr>
									<th width="3%" align="center">Answers</th>
									<th width="20%" align="center">Exam Name</th>
									<th width="20%" align="center">TestSeries Question</th>
									<th width="20%" align="center">Uploaded Question Filename</th>
									<th width="15%" align="center">Created By</th>
									<th width="17%" align="center">Created Date</th>
									<th width="2%" align="center">Status</th>
									<th width="3%" align="center">Delete</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="8" class="dataTables_empty">Loading data from
										Server</td>
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
