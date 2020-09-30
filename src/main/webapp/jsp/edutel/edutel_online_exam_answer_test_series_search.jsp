<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<s:set name="noOfAnswers" value="answerFileNameList.size"></s:set>

<link type="text/css" rel="stylesheet"
	href="css/dataTables.tableTools.css">

<script type="text/javascript" charset="utf-8"
	src="js/dataTables.tableTools.js"></script>

<link href="css/bootbox/components.css" rel="stylesheet" type="text/css" />
<link href="css/bootbox/plugins.css" rel="stylesheet" type="text/css" />
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="js/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style>
details-control {
	background: url('images/png/plus.png') no-repeat center center;
	cursor: pointer;
}

details-control:HOVER {
	background: url('images/png/minus.png') no-repeat center center;
	cursor: pointer;
}
</style>
<script>
	var uploadWindow;
	var studentTestSeriesTable;
	var edutelTestSeriesTable;
	function uploadValidatedAnswerJS(ansId, oeFileName, studentId) {
		var url = "preUploadValidatedAnswer.do?oeQuestionId=${oeQuestionId}&oeTestSeriesAutoId="
				+ ansId
				+ "&oeFileName="
				+ oeFileName
				+ "&usEmployeeId="
				+ studentId + "&uid=" + Math.random();
		var attributes = 'width=800,height=500,left=200,top=50,toolbar=no,status=no';
		uploadWindow = window.open(url, 'Upload_Answer', attributes);
	}

	function deleteExamAnswer(answerId) {
		var confirmDelete = confirm("Are sure want to delete the Answer?");
		if (confirmDelete) {
			var url = 'deleteOnlineExamAnswer.do?oeQuestionId=${oeQuestionId}&oeTestSeriesAutoId='
					+ answerId + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					studentTestSeriesTable.fnFilter(' ', 0);
				}
			});
		}
	}

	function deleteExamValidatedAnswer(testSeriesAutoId, employeeId) {
		var confirmDelete = confirm("Are sure want to delete the Validated Answer?");
		if (confirmDelete) {
			var url = "deleteOnlineExamAnswer.do?&oeTestSeriesAutoId="
					+ testSeriesAutoId + "&employeeId=" + employeeId + "&uid="
					+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					studentTestSeriesTable.fnFilter(' ', 0);
				}
			});
		}
	}

	function downloadTestSeriesAnswerByAjax(ansId, answerFile) {
		var url = "downloadTestSeriesAnswerByAjax.do?oeQuestionId=${oeQuestionId}&oeTestSeriesAutoId="
				+ ansId + "&oeFileName=" + answerFile + "&uid=" + Math.random();
		window.open(url, 'open');
	}

	function refreshStudentTestSeriesTable() {
		studentTestSeriesTable.fnFilter(' ', 1);
	}

	function refreshEdutelTestSeriesTable() {
		edutelTestSeriesTable.fnFilter(' ', 1);
	}

	$(document)
			.ready(
					function() {
						studentTestSeriesTable = $('#studentTestSeriesTableId')
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "onlineTestSeriesExamAnswerListByJson.do?oeExamQuestionId=${oeQuestionId}&oeValidate=false&uid="
													+ Math.random(),
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 4, 'desc' ] ],
											"sPaginationType" : "full_numbers",
											"oTableTools" : {
												"sRowSelect" : "multi",
												"aButtons" : [ "select_all",
														"select_none" ]
											},
											"aoColumns" : [
													{
														"sName" : "IdUpload",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<img src=\"images/png/upload.png\" id=\"uploadValidatedAnswer\" onclick=\"uploadValidatedAnswerJS(\'"
																	+ obj.aData[8]
																	+ "','"
																	+ obj.aData[1]
																	+ "','"
																	+ obj.aData[9]
																	+ "\')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Upload Validated Answer\"/>";
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															if (obj.aData[2] == 'CourierOrPost') {
																return "<table><tr><td valign=\"bottom\"><img src=\"images/png/download_disabled.png\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\"/></td><td valign=\"middle\">&nbsp; Not Applicable</td></tr>";
															} else {
																return "<table><tr><td valign=\"bottom\"><img src=\"images/png/download.png\" id=\"downloadTestSeriesAnswer\" onclick=\"downloadTestSeriesAnswerByAjax(\'"
																		+ obj.aData[8]
																		+ "','"
																		+ obj.aData[1]
																		+ "\')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Download Online Test Series Exam Answers\"/> </td><td valign=\"middle\">&nbsp;"
																		+ obj.aData[1]
																		+ "</td></tr>";
															}
														}
													},
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
															return "<img src=\"images/png/cancel_48.png\" id=\"delExamQuestion\" onclick=\"deleteExamAnswer('"
																	+ obj.aData[8]
																	+ "')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Online Exam Answers\"/>";
														}
													} ]
										});

						setTimeout('loadValidatedAnswerTable()', 2000);
						// Set Footer Page below the page.  
						setFooter();
					});

	function loadValidatedAnswerTable() {
		edutelTestSeriesTable = $('#edutelTestSeriesTableId')
				.dataTable(
						{
							"bProcessing" : true,
							"bServerSide" : true,
							"sAjaxSource" : "onlineTestSeriesExamValidatedAnswerListByJson.do?oeExamQuestionId=${oeQuestionId}&oeValidate=true&uid="
									+ Math.random(),
							"bJQueryUI" : true,
							"sServerMethod" : "POST",
							"aaSorting" : [ [ 4, 'desc' ] ],
							"sPaginationType" : "full_numbers",
							"aoColumns" : [
									{
										"bSearchable" : false,
										"bSortable" : false,
										"fnRender" : function(obj) {
											return "";
										}
									},
									{
										"sName" : "ID",
										"bSearchable" : false,
										"bSortable" : false,
										"fnRender" : function(obj) {
											if (obj.aData[2] == 'CourierOrPost') {
												return "<table><tr><td valign=\"bottom\"><img src=\"images/png/download_disabled.png\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\"/></td><td valign=\"middle\">&nbsp; Not Applicable</td></tr>";
											} else {
												return "<table><tr><td valign=\"bottom\"><img src=\"images/png/download.png\" id=\"downloadTestSeriesAnswer\" onclick=\"downloadTestSeriesAnswerByAjax(\'"
														+ obj.aData[7]
														+ "','"
														+ obj.aData[1]
														+ "\')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Download Online Test Series Exam Answers\"/> </td><td valign=\"middle\">&nbsp;"
														+ obj.aData[1]
														+ "</td></tr>";

											}
										}
									},
									null,
									null,
									null,
									null,
									null,
									{
										"sName" : "ID",
										"bSearchable" : false,
										"bSortable" : false,
										"fnRender" : function(obj) {
											return "<img src=\"images/png/cancel_48.png\" id=\"deleteExamAnswer\" onclick=\"deleteExamValidatedAnswer('"
													+ obj.aData[7]
													+ "','"
													+ obj.aData[3]
													+ "')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Online Exam Answers\"/>";
										}
									} ]
						});
	}
	function bulkDownloadStudentTestSeriesAnswers() {
		bootbox
				.dialog({
					message : "Are you sure want to download all Test Series files for this exam?",
					title : "Test Series Answers Bulk Download",
					buttons : {
						success : {
							label : "Yes",
							className : "btn-success",
							callback : function() {
								allAnswersBulkDownload();
							}
						},
						danger : {
							label : "No",
							className : "btn-primary",
							callback : function() {
								availableAnswersBulkDownload();
							}
						},
						main : {
							label : "Cancel",
							className : "btn-danger",
							callback : function() {
							}
						}
					}
				});
	}

	function availableAnswersBulkDownload() {
		if ('${noOfAnswers}' != 0) {
			var questionId = $("#selectedQuestionId").val();
			var url = 'downloadTestSeriesAnswersByZip.do?oeExamQuestionId='
					+ questionId + '&oeValidate=false&key=AvailAnswers&uid='
					+ Math.random();
			window.location.href = url;
		} else {
			bootbox.alert("No Available Answer Files to download them.");
		}
	}

	function allAnswersBulkDownload() {
		var questionId = $("#selectedQuestionId").val();
		var url = 'downloadTestSeriesAnswersByZip.do?oeExamQuestionId='
				+ questionId + '&oeValidate=false&key=AllAnswers&uid='
				+ Math.random();
		window.location.href = url;
	}
	function showBulkUploadPopup() {
		if ('${noOfAnswers}' != 0) {
			$("#validated_answers_bulk_upload").modal("show");
		} else {
			bootbox
					.alert("No Answer Files to upload the Validated Answer Files");
		}
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp" />
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online Test Series Exam Answer Panel</p>
			</div>
			<div id="signupwrapper" align="center">
				<div style="padding: 10px;" align="left">
					<span style="font-weight: bold; font-size: 16px; color: green;">&nbsp;&nbsp;&nbsp;Student
						Test Series Answer</span> <input type="hidden" name="selectedQuestionId"
						id="selectedQuestionId" value="${oeQuestionId}" />
				</div>
				<div id="dashBoardDiv" align="center" class="dashBoardDiv"
					style="width: 950px; padding: 10px; font-size: 12px;">
					<a class="img-responsive" href="#"
						onclick="bulkDownloadStudentTestSeriesAnswers();"><img
						src="images/png/download_green.png"
						style="cursor: pointer; height: 25px; width: 25px; margin-right: 15px; float: right;"
						title="Student Test Series Answers Bulk Downloads" /></a> <a
						class="img-responsive" href="#" onclick="showBulkUploadPopup();"><img
						src="images/png/upload_blue.png"
						style="cursor: pointer; height: 25px; width: 25px; margin-right: 15px; float: right;"
						title="EduTel Validated Test Series Answers Bulk Upload" /></a>
					<div id="dashBoardDiv" class="ui-state-default" align="left"
						style="width: 900px;">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="studentTestSeriesTableId">
							<thead>
								<tr>
									<th width="2%"></th>
									<th width="30%" align="center">Student Answered Filename</th>
									<th width="10%" align="center">Answered Via</th>
									<th width="10%" align="center">Created By</th>
									<th width="10%" align="center">Created Date</th>
									<th width="15%" align="center">School Name</th>
									<th width="10%" align="center">Modified By</th>
									<th width="10%" align="center">Modified Date</th>
									<th width="3%" align="center">Delete</th>
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
				<br>
				<div style="padding: 10px;" align="left">
					<span style="font-weight: bold; font-size: 16px; color: green;">&nbsp;&nbsp;&nbsp;Validated
						Test Series Answers</span>
				</div>
				<div id="dashBoardDiv" align="center" class="dashBoardDiv"
					style="width: 950px; padding: 10px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="left"
						style="width: 900px;">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="edutelTestSeriesTableId">
							<thead>
								<tr>
									<th width="2%"></th>
									<th width="30%" align="center">EduTel Validated Answer
										Filename</th>
									<th width="10%" align="center">Replied Via</th>
									<th width="10%" align="center">Created By</th>
									<th width="15%" align="center">Created Date</th>
									<th width="10%" align="center">Replied By</th>
									<th width="20%" align="center">Replied Date</th>
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
			<s:include value="m_test_series_validated_answer_bulk_upload.jsp"></s:include>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
