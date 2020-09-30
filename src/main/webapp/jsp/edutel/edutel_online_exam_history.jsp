<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var onlineTestSeriesExamAnswerListTable;
	$(document)
			.ready(
					function() {
						onlineTestSeriesExamAnswerListTable = $(
								'#onlineTestSeriesExamAnswerListTableId')
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "onlineTestSeriesExamAnswerListByJson.do?oeExamQuestionId=${oeQuestionId}&uid="
													+ Math.random(),
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 4, 'desc' ] ],
											"sPaginationType" : "full_numbers",
											"aoColumns" : [
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<img src=\"images/png/hard-drive-download_blue.png\" id=\"downloadTestSeriesAnswer\" onclick=\"downloadTestSeriesAnswerByAjax(\'"
																	+ obj.aData[5] + "','" + obj.aData[0] 
																	+ "\')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Download Online Test Series Exam Answers\"/> &nbsp;" + obj.aData[0];
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<img src=\"images/png/hard-drive-download_blue.png\" id=\"downloadTestSeriesAnswer\" onclick=\"downloadTestSeriesAnswerByAjax(\'"
																	+ obj.aData[5] + "','" + obj.aData[0] 
																	+ "\')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Download Online Test Series Exam Answers\"/> &nbsp;" + obj.aData[0];
														}
													},
													null,
													null,
													null,
													null ]
										});
						setFooter();
					});

	function downloadTestSeriesAnswerByAjax(ansId, answerFile) 
	{
		var url = "downloadTestSeriesAnswerByAjax.do?oeQuestionId=${oeQuestionId}&oeTestSeriesAutoId=" + ansId + "&oeFileName=" + answerFile + "&uid=" + Math.random();
		window.open(url, 'open');
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online Test Series Answer</p>
			</div>
			<div id="signupwrapper" align="center">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv" style="width:700; padding: 10px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center" style="width:650;">
						<table  cellpadding="0" cellspacing="0" border="0" class="display" id="onlineTestSeriesExamAnswerListTableId">
							<thead>
								<tr>
									<th width="25%" align="center">Test Series Question </th>
									<th width="25%" align="center">Test Series Answer</th>
									<th width="10%" align="center">Answered Via</th>
									<th width="15%" align="center">Created By</th>
									<th width="25%" align="center">Created Date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="6" class="dataTables_empty">Loading data from Server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
