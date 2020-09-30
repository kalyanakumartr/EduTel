<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var onlineExamListTable;
	var examDispCol = 8;
	var blockExamCol = 9;
	var examIdCol = 10;

	$(document)
			.ready(
					function() {
						onclearCalled();
						onlineExamListTable = $('#onlineExamListTableId')
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "onlineExamListByJson.do",
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 5, 'desc' ] ],
											"sPaginationType" : "full_numbers",
											"aoColumns" : [
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<a href=\"createOnlinePreExamQuestion.do?oeExamId="
																	+ obj.aData[examIdCol]
																	+ "&uid="
																	+ Math
																			.random()
																	+ "\"  style=\"text-decoration: underline;color:blue;\">"
																	+ obj.aData[0]
																	+ "</a>";
														}
													},
													null,
													null,
													null,
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
															if (obj.aData[blockExamCol] == "true" || obj.aData[blockExamCol] == true)
															{
																if (obj.aData[examDispCol] == "true"
																		|| obj.aData[examDispCol] == true) {
																	return "<img src=\"images/png/public_access.png\" id=\"showExam\" onclick=\"displayExamPublic(\'"
																			+ obj.aData[examIdCol]
																			+ "','false')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:30px;width:30px;\" title=\"Display Online Exam to Public\"/>";
																} else {
																	return "<img src=\"images/png/private_access.png\" id=\"hideExam\" onclick=\"displayExamPublic(\'"
																			+ obj.aData[examIdCol]
																			+ "','true')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:30px;width:30px;\" title=\"Hide Online Exam from Public\"/>";
																}
															}
															else
															{
																return "<img src=\"images/png/private_access.png\" id=\"hideExam\" onclick=\"javascript:alert('Please Unlock status flag!')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:30px;width:30px;\" title=\"Please unlock status flag\"/>";
															}
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															if (obj.aData[blockExamCol] == "true"
																	|| obj.aData[blockExamCol] == true) {
																return "<img src=\"images/png/unlock.png\" id=\"blockExam\" onclick=\"blockUnlockExam(\'"
																		+ obj.aData[examIdCol]
																		+ "','false')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Block Online Exam \"/>";
															} else {
																return "<img src=\"images/png/lock.png\" id=\"unBlockExam\" onclick=\"blockUnlockExam(\'"
																		+ obj.aData[examIdCol]
																		+ "','true')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"UnBlock Online Exam \"/>";
															}
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<img src=\"images/png/cancel_48.png\" id=\"delExam\" onclick=\"deleteExam(\'"
																	+ obj.aData[examIdCol]
																	+ "\')\" style=\"align:center;vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Online Exam \"/>";
														}
													} ]
										});
					});

	function deleteExam(oeExamId) {
		var confirmDelete = confirm("Are sure want to delete the Exam?");
		if (confirmDelete) {
			var url = 'deleteOnlineExam.do?oeExamId=' + oeExamId + "&uid="
					+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					onlineExamListTable.fnFilter(' ', 0);
				}
			});
		}
	}

	function blockUnlockExam(oeExamId, status) {
		var confirmBlock = confirm("Are sure want to Block the Exam?");
		if (confirmBlock) {
			var url = 'blockUnlockExam.do?oeExamId=' + oeExamId + "&status="
					+ status + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					onlineExamListTable.fnFilter('  ', 0);
				}
			});
		}
	}

	function displayExamPublic(oeExamId, status) {
		var confirmBlock = "Are sure want to Hide the Online Exam from all Students?";
		if(status=="true")
			confirmBlock = "Are sure want to Display the Online Exam to all Students?";
		
		confirmBlock = confirm(confirmBlock);
		
		if (confirmBlock) {
			var url = 'displayExamPublic.do?oeExamId=' + oeExamId + "&displayPublic=" + status + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					
					onlineExamListTable.fnFilter('  ', 0);
					
					var alertMsg = "<div align='center' style='background-color:#ffffff;float: left;padding:10px;'><img src=\"images/png/exam.png\" /></div>";
					alertMsg += "<div align='left' style='background-color:#ffffff;float:left;vertical-align: top;'><BR><BR>";
					alertMsg += "<span style='color:red;font-size:14px; font-weight:bold;'>";
					alertMsg += data[0].value;
					alertMsg += "</span></div>";
					alertBox("Online Exam Public View" , alertMsg , 500, 225);
					
				}
			});
		}
	}

	function onsubmitCalled() {
		var examName = $("#oeExamName").val();
		var examDate = $("#oeExamDate").val();
		var examDuration = $("#oeExamDuration").val();
		var subject = $("#oeSubject").val();
		var schoolType = $("#oeSchoolType").val();
		var examType = $("#oeExamType").val();
		var onlineAssessment = $("#onlineAssessment").val();

		if (allTrim(examName) == "") {
			alert("Please type Exam Name");
			return false;
		}
		if (allTrim(examDate) == "") {
			alert("Please Select Exam Date");
			return false;
		}
		if (allTrim(examDuration) == "0.0" || allTrim(examDuration) == "") {
			alert("Please type Exam Duration");
			return false;
		}
		if (allTrim(subject) == "") {
			alert("Please select Subject");
			return false;
		}

		if (allTrim(schoolType) == "") {
			alert("Please select School Type");
			return false;
		}
		if (allTrim(examType) == "") {
			alert("Please select Exam Type");
			return false;
		}
		if (allTrim(onlineAssessment) == "") {
			alert("Please select Online Assessment");
			return false;
		}

		document.forms["onlineExamGenerateForm"].submit();
	}
	function onclearCalled() {

		$("#oeExamName").val("");
		$("#oeExamDate").val("");
		$("#oeExamDuration").val("0.0");
		$("#oeSubject").val("Physics");
		$("#oeExamType").val("MCQ");
	}
	function onresetCalled() {
		document.forms["onlineExamGenerateForm"].reset();
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width:95%;">
			<div class="latest-title">
				<p>Online Exam Panel</p>
			</div>
			<div id="signupwrapper" align="center" >
				<s:form name="onlineExamGenerateForm" id="onlineExamGenerateForm" action="createOnlineExam.do" method="post" enctype="multipart/form-data"
					autocomplete="off" show_alert="1" onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="onlineExamGenerateDiv"
										style="width: 900px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0" width="100%" style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td valign="top" align="center" width="100%" colspan="7" height="30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:if
														test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan" style="color: red; font-weight: bold;"><s:property escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Exam Name</span><span style="color: red;">*</span></td>
												<td align="left" width="20%"><s:textfield name="onlineExam.oeExamName" id="oeExamName" theme="simple" cssStyle="width: 185px;" /></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Exam Date</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="20%"><s:textfield readonly="true" class="smalltextbox" name="onlineExam.oeExamDate" id="oeExamDate"
														style="width: 185px; background: url('js/datepicker/calendar.gif') no-repeat scroll right center #FFFFFF;vertical-align: middle;" theme="simple" />
													<script>
														new Zapatec.Calendar.setup(
																{
																	inputField : 'oeExamDate',
																	ifFormat : '%m-%d-%Y %H-%M',
																	showsTime : true,
																	button : 'oeExamDate',
																	singleClick : true,
																	step : 1,
																	timeFormat : '12'
																});
													</script>&nbsp;&nbsp;</td>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="7">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Exam Duration</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="20%"><s:textfield name="onlineExam.oeExamDuration" id="oeExamDuration" theme="simple" cssStyle="width: 185px;" /></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Subject</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="20%"><s:select name="onlineExam.oeSubject" id="oeSubject" style="width: 185px;" list="subjectList" listKey="label"
														listValue="value" theme="simple" /></td>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="7">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Chapter</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="20%"><s:select name="onlineExam.oeChapter" id="oeChapter" style="width: 185px;" list="chapterList" listKey="label"
														listValue="value" theme="simple" /></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;School Type</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="20%" nowrap="nowrap"><select name="onlineExam.oeSchoolType" id="oeSchoolType" style="width: 185px;">
														<option value="State_Board">State_Board</option>
														<!-- option value="CBSE">CBSE</option -->
												</select></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="7">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Exam Type</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="20%" nowrap="nowrap"><select name="onlineExam.oeExamType" id="oeExamType" style="width: 185px;"
													onchange="enableNoOfQuestions();">
														<option value="MCQ">MCQ</option>
														<option value="TestSeries">TestSeries</option>
												</select></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Online MCQ Assessment</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="20%" nowrap="nowrap"><select name="onlineExam.onlineAssessment" id="onlineAssessment" style="width: 185px;"
													onchange="enableNoOfQuestions();">
														<option value="false">No</option>
														<option value="true">Yes</option>
												</select></td>
												<td align="left" width="5%"><script>
													function enableNoOfQuestions() {
														var onlineAssessment = $(
																"#onlineAssessment")
																.val();
														var oeExamType = $(
																"#oeExamType")
																.val();
														if (onlineAssessment == 'true'
																&& oeExamType == 'MCQ') {
															$(
																	"#noOfQuestionRowId1")
																	.show();
															$(
																	"#noOfQuestionRowId2")
																	.show();
														} else {
															$(
																	"#oeNoOfQuestions")
																	.val("");
															$(
																	"#noOfQuestionRowId1")
																	.hide();
															$(
																	"#noOfQuestionRowId2")
																	.hide();
														}
													}
												</script></td>
											</tr>
											<tr>
												<td align="left" colspan="7">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr id="noOfQuestionRowId1" style="display: none;">
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;No Of Questions</span><span style="color: red;">*</span></td>
												<td align="left" width="20%"><s:textfield name="onlineExam.oeNoOfQuestions" id="oeNoOfQuestions" theme="simple" cssStyle="width: 185px;" /></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="20%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="5%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr id="noOfQuestionRowId2" style="display: none;">
												<td align="left" colspan="7">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="7"><input type="button" class="btnstyle" name="createOnlineExam" value="Create Online Exam"
													href="javascript:void(0);" onclick="onsubmitCalled();" style="width: 150px;" /> &nbsp;&nbsp; <input type="button" class="btnstyle" value="Reset"
													onclick="onresetCalled()" /></td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="7">&nbsp;&nbsp;&nbsp;</td>
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
			<div id="signupwrapper1" align="center" style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv" style="width: 90%; padding: 10px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="onlineExamListTableId">
							<thead>
								<tr>
									<th width="20%" align="center">Exam Name</th>
									<th width="10%" align="center">Subject</th>
									<th width="10%" align="center">Chapter</th>
									<th width="8%" align="center">Syllabus Type</th>
									<th width="5%" align="center">Exam Date</th>
									<th width="7%" align="center">Duration</th>
									<th width="15%" align="center">Created By</th>
									<th width="15%" align="center">Created Date</th>
									<th width="5%" align="center">Public</th>
									<th width="2%" align="center">Status</th>
									<th width="3%" align="center">Delete</th>
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
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
