<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var onlineExamQuestionListTable;
	$(document)
			.ready(
					function() {
						onresetCalled();
						onlineExamQuestionListTable = $(
								'#onlineExamQuestionListTableId')
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "onlineExamQuestionListByJson.do?oeExamId=${onlineExam.oeExamId}&uid="
													+ Math.random(),
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 0, 'desc' ] ],
											"sPaginationType" : "full_numbers",
											"aoColumns" : [
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void("
																	+ obj.aData[7]
																	+ "&uid="
																	+ Math
																			.random()
																	+ ");\"  style=\"text-decoration: underline;color:blue;\">"
																	+ obj.aData[0]
																	+ "</a>";
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<div style=\"overflow:auto; height:70px;\">"
																	+ obj.aData[1]
																	+ "</div>";
														}
													},
													null,
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<a href=\"viewImage.do?oeExamQuestionId="
																	+ obj.aData[7]
																	+ "&uid="
																	+ Math
																			.random()
																	+ "\">"
																	+ obj.aData[3]
																	+ "</a>";
														}
													},
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
					});

	function deleteExamQuestion(oeExamQuestionId) {

		var confirmDelete = confirm("Are sure want to delete the Exam Question?");
		if (confirmDelete) {
			var url = 'deleteOnlineExamQuestion.do?oeExamQuestionId='
					+ oeExamQuestionId + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					onlineExamQuestionListTable.fnFilter(' ', 0);
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
				onlineExamQuestionListTable.fnFilter(' ', 0);
			}
		});
	}

	function onsubmitCalled() {
		var fileUpload = "";
		var oeQuestion = "";
		var oeQuestionText = $("#oeQuestion1").val();
		var oeQuestionUpload = $("#fileUpload0").val();
		var oeAnswerText = "";

		if ("${onlineExam.oeExamType}" != "MCQ") {
			if ((fileCnt > 0)) {
				fileUpload = $("#fileUpload" + fileCnt).val();
				oeQuestion = $("#oeQuestion" + fileCnt).val();
				if (allTrim(oeQuestion) == "") {
					alert("Please Enter the Test Series Question Name.");
					return false;
				}
				if (allTrim(fileUpload) == undefined
						|| allTrim(fileUpload) == null
						|| allTrim(fileUpload) == "") {
					alert("Please Upload the Test Series Question.");
					return false;
				}
			}
		} else {

			if (allTrim(oeQuestionText) == ""
					&& (allTrim(oeQuestionUpload) == undefined
							|| allTrim(oeQuestionUpload) == null || allTrim(oeQuestionUpload) == "")) {
				alert("Please Type or Upload the Question.");
				return false;
			}

			if ((fileCnt > 4) && (fileCnt <= 8)) {

				fileUpload = $("#fileUpload" + fileCnt).val();
				oeAnswerText = $("#oeAnswerText" + fileCnt).val();
				if (allTrim(oeAnswerText) == ""
						&& (allTrim(fileUpload) == undefined
								|| allTrim(fileUpload) == null || allTrim(fileUpload) == "")) {
					alert("Please Type or Upload an Answer.");
					return false;
				}
			}

		}

		document.forms["onlineExamQuestionForm"].submit();
	}

	function onresetCalled() {
		document.forms["onlineExamQuestionForm"].reset();
	}

	function onCancelCalled(url) {
		window.location.href = url;
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp" />
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online Exam Question Panel</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="onlineExamQuestionForm" id="onlineExamQuestionForm"
					action="createOnlineExamQuestion.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="onlineExamQuestionDiv"
										style="width: 100%; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td align="left" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" nowrap="nowrap" colspan="10"><s:if
														test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan"
																style="color: red; font-weight: bold;"><s:property
																	escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" width="3%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;Exam Name</span> <s:hidden
														name="onlineExam.oeExamId" id="oeExamId" /></td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;</span>
													<input readonly="readonly" type="text" id="exName" name="exName" value="${onlineExam.oeExamName}"  class="exNameStyle"/>
													<s:hidden name="onlineExam.oeExamName" id="oeExamName" /></td>
												<td align="left" nowrap="nowrap" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;Exam Date</span></td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;${onlineExam.oeExamDate}</span></td>
												<td align="left" nowrap="nowrap" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;Exam Duration</span></td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;${onlineExam.oeExamDuration}</span></td>
												<td align="left" nowrap="nowrap" width="3%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" width="3%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;Subject</span></td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;${onlineExam.oeSubject}</span></td>
												<td align="left" nowrap="nowrap" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;Chapter</span></td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp; <s:if
															test="%{onlineExam.oeChapter==''}">All</s:if> <s:else>${onlineExam.oeChapter}</s:else>
												</span></td>
												<td align="left" nowrap="nowrap" width="2%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;School Type</span></td>
												<td align="left" nowrap="nowrap" width="15%"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;${onlineExam.oeSchoolType}</span></td>
												<td align="left" nowrap="nowrap" width="3%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" colspan="10">&nbsp;&nbsp;&nbsp;</td>
											</tr>
										</table>
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<s:if test="%{onlineExam.oeExamType=='MCQ'}">
													<td align="left" colspan="2"><s:include
															value="i_edutel_questions_attachments.jsp">
															<s:param name="attachmentBannerName">MCQ's Exam Documents</s:param>
														</s:include></td>
												</s:if>
												<s:else>
													<td align="left" colspan="2"><s:include
															value="i_edutel_questions_test_series_attachments.jsp">
															<s:param name="attachmentBannerName">TestSeries Exam Documents</s:param>
														</s:include></td>
												</s:else>
											</tr>
											<tr>
												<td align="center" colspan="2"><input type="button"
													class="btnstyle" name="createQuestionContinue" value="Save"
													onclick="onsubmitCalled();" style="width: 100px;" />
													&nbsp;&nbsp; <input type="button" class="btnstyle"
													value="Cancel"
													onclick="onCancelCalled('createOnlinePreExam.do')" /></td>
											</tr>
											<tr>
												<td align="left" nowrap="nowrap" colspan="3">&nbsp;&nbsp;&nbsp;</td>
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
					style="padding: 10px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="onlineExamQuestionListTableId">
							<thead>
								<tr>
									<th width="15%" align="center">Exam Name</th>
									<th width="25%" align="center">Question Text</th>
									<th width="20%" align="center">Question Image</th>
									<th width="15%" align="center">Answer</th>
									<th width="11%" align="center">Created By</th>
									<th width="10%" align="center">Created Date</th>
									<th width="2%" align="center">Status</th>
									<th width="2%" align="center">Delete</th>
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
