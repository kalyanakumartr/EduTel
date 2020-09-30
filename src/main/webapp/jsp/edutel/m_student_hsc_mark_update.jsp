<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
	function onSubmitStudentHSCMark() {
		var HSCMark = $("#usHSCMark").val();
		if (allTrim(HSCMark) == "") {
			bootbox.alert("Please type HSC Mark");
			return false;
		}
		document.forms["studentMarkUpdateForm"].submit();
	}

	function onSkipStudent() {
		var empId = $("#usEmployeeId").val();
		/* window.location.href = "nextStudentMarkDetails.do?usEmployeeId="
				+ empId + "&uid=" + Math.random(); */

		var url = 'nextStudentMarkDetails.do?usEmployeeId=' + empId + "&uid="
				+ Math.random();
		$.getJSON(url, function(data) {
			if (data != null & data.length > 0) {
				$("#userId").text(data[0].user_id);
				$("#userName").text(data[0].user_name);
				$("#usBatch").text(data[0].student_batch);
				$("#usSSorPUCMark").text(data[0].sslc_puc);
				$("#usHSCNo").text(data[0].hsc_no);
				$("#usEmployeeId").val(data[0].employee_id);

				if (data[0].value == 'Not_Skipped'
						|| data[0].value == 'No_Data') {
					bootbox.alert("Unable to Skip. This is the Last Data");
					$("#student_hsc_mark_update").modal("hide");
				}

			}
		});
	}
</script>

<div class="modal fade bs-modal-lg" id="student_hsc_mark_update"
	tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-green-spring">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Student's HSC Mark Update</h4>
			</div>
			<div class="modal-body">
				<div class="form-body">
					<div class="row" align="left">
						<s:form name="studentMarkUpdateForm" id="studentMarkUpdateForm"
							action="saveStudentHSCMark.do" method="post"
							enctype="multipart/form-data" autocomplete="off" show_alert="1"
							onSubmit="return validateForm()">
							<div class="form-body">
								<s:hidden id="usEmployeeId" name="studentMark.usEmployeeId"
									value="%{studentMark.usEmployeeId}"></s:hidden>
								<div class="col-md-12">
									<div class="row">
										<div class="col-md-2">
											<label class="control-label">Student Id </label>
										</div>
										<div class="col-md-4">
											<span class="btn grey" id="userId">${studentMark.users.usUserID}
											</span>
										</div>
										<div class="col-md-2">
											<label class="control-label">Student Name </label>
										</div>
										<div class="col-md-4">
											<span class="btn grey" id="userName">${studentMark.users.usUserName}
											</span>
										</div>
									</div>
									<div class="row">
										<div class="col-md-2">
											<label class="control-label">Student Batch </label>
										</div>
										<div class="col-md-4">
											<span class="btn grey" id="usBatch">${studentMark.usBatch}
											</span>
										</div>
										<div class="col-md-2">
											<label class="control-label">SSLC/PUC Mark </label>
										</div>
										<div class="col-md-4">
											<span class="btn grey" id="usSSorPUCMark">${studentMark.usSSorPUCMark}
											</span>
										</div>
									</div>
									<div class="row">

										<div class="col-md-2">
											<label class="control-label">HSC No. </label>
										</div>
										<div class="col-md-4">
											<span class="btn grey" id="usHSCNo">${studentMark.usHSCNo}
											</span>
										</div>

										<div class="col-md-2">
											<label class="control-label">HSC Mark </label>
										</div>

										<div class="col-md-4">
											<input name="studentMark.usHSCMark" id="usHSCMark"
												style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
												maxlength="4" type="text" placeholder="HSC Mark"
												class="form-control">
										</div>
									</div>
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn red" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn blue" onclick="onSkipStudent();"
					name="skipStudentMark">Skip</button>
				<button type="button" class="btn blue"
					onclick="onSubmitStudentHSCMark();"
					name="save_continue_StudentMark">Save & Continue</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>