<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
	$(document).ready(function() {
		var answeredFileName = $("#answeredFileName").val();
		if (allTrim(answeredFileName) == "") {
			$("#studentInfo").hide();
		}

	});
	function showStudentDetails() {
		var answeredFileId = $("#answeredFileName").val();

		var url = 'getAnsweredStudentInfoByAjax.do?oeAnswerId='
				+ answeredFileId + "&uid=" + Math.random();
		if (allTrim(answeredFileId) != "") {
			$("#studentInfo").show();
			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {

					$("#studentName").text(data[0].stuName);
					$("#uploadedDate").text(data[0].uploadDate);
					$("#stuEmployeeId").val(data[0].empId);
				}
			});
		} else {
			$("#studentInfo").hide();
		}

	}

	function onsubmitCalled() {
		var ansFileName = "";
		var stuName = "";
		var fileUpload = "";

		if ((fileCnt > 0) && (fileCnt <= 10)) {

			fileUpload = $("#fileUpload" + fileCnt).val();
			ansFileName = $("#answerFileName" + fileCnt).val();
			stuName = $("#createdBy" + fileCnt).val();

			if (allTrim(ansFileName) == "") {
				alert("Please Enter an Answer File Name.");
				return false;
			}
			if (allTrim(stuName) == "") {
				alert("Please Enter Student Name.");
				return false;
			}
			if (allTrim(fileUpload) == undefined || allTrim(fileUpload) == null
					|| allTrim(fileUpload) == "") {
				alert("Please Upload Validated Answer File.");
				return false;
			}
		}
		document.forms["validatedAnswersBulkUploadForm"].submit();
	}
</script>

<div class="modal fade bs-modal-lg" id="validated_answers_bulk_upload"
	tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-green-spring">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">EduTel Test Series Validated Answers
					Bulk Upload</h4>
			</div>
			<div class="modal-body">
				<div class="form-body">
					<div class="row" align="left">
						<s:form name="validatedAnswersBulkUploadForm"
							id="validatedAnswersBulkUploadForm"
							action="validatedAnswersBulkUpload.do" method="post"
							enctype="multipart/form-data" autocomplete="off" show_alert="1"
							onSubmit="return validateForm()">
							<div class="form-body">
								<div class="col-md-12">
									<div class="row" style="margin-bottom: -20px;">
										<div class="col-md-2">
											<label class="control-label"> Exam Name </label>
										</div>
										<div class="col-md-10">
											<div class="form-group">
												<span id="oeExamName" class="btn grey">${onlineExamQuestion.onlineExam.oeExamName}</span>
											</div>
										</div>

										<div class="col-md-2">
											<label class="control-label"> Subject </label>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<span id="oeSubject" class="btn grey">${onlineExamQuestion.onlineExam.oeSubject}</span>
											</div>
										</div>
										<div class="col-md-2">
											<label class="control-label"> Chapter </label>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<span id="oeChapter" class="btn grey">${onlineExamQuestion.onlineExam.oeChapter}</span>
												<script>
													var chapSpan = '${onlineTestSeriesExamAnswer.onlineExamQuestion.onlineExam.oeChapter}';
													if (chapSpan == '') {
														$("#chapSpan").text(
																"All");
													}
												</script>
											</div>
										</div>
										<div class="col-md-2">
											<label class="control-label"> Exam Date </label>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<span id="oeExamDate" class="btn grey">${onlineExamQuestion.onlineExam.oeExamDate}</span>
											</div>
										</div>
										<div class="col-md-2">
											<label class="control-label"> Duration </label>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<span id="oeExamDuration" class="btn grey">${onlineExamQuestion.onlineExam.oeExamDuration}&nbsp;&nbsp;&nbsp;Hours</span>
											</div>
										</div>
										<div class="col-md-2">
											<label class="control-label"> Answer File Name </label>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<s:select name="answeredFileName" id="answeredFileName"
													cssClass="form-control select2" headerKey=""
													headerValue="Select File Name" list="answerFileNameList"
													onchange="showStudentDetails();" listKey="label"
													listValue="value" theme="simple"></s:select>
											</div>
										</div>
										<div id="studentInfo">
											<div class="col-md-2">
												<label class="control-label"> Student Name </label>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<span id="studentName" class="btn grey"></span>
													<s:hidden name="stuEmployeeId" id="stuEmployeeId" value=""></s:hidden>

												</div>
											</div>
											<div class="col-md-2">
												<label class="control-label"> Uploaded Date </label>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<span id="uploadedDate" class="btn grey"></span>
												</div>
											</div>
										</div>
									</div>
									<hr>

									<div class="row" style="margin-bottom: 0px;">
										<s:include value="i_edutel_test_series_bulk_attachments.jsp">
											<s:param name="attachReadOnly">false</s:param>
											<s:param name="attachmentBannerName">Upload Your Answer (Max 5 MB file size & Max 10 files)</s:param>
											<s:param name="attachmentName">Answer&nbsp;&nbsp;&nbsp;</s:param>
											<s:param name="attachmentDynamicName"></s:param>
										</s:include>
									</div>
									<hr>
									<div class="row" style="margin-bottom: 0px;">
										<div class="col-md-12" align="right">
											<button type="button" class="btn red" data-dismiss="modal">Cancel</button>

											<button type="button" class="btn blue"
												onclick="onsubmitCalled();" name="save_continue_StudentMark">Upload
												Validated Answers</button>
										</div>
									</div>
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>