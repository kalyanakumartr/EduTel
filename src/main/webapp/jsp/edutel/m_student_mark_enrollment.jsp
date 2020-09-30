<%@ taglib prefix="s" uri="/struts-tags"%>

<s:set name="attachmentName">Image</s:set>
<s:set name="uploadType">Update</s:set>


<s:set name="userImageUrl">${usUserImageUrl}</s:set>
<s:set name="gender">${user.usSex}</s:set>
<script>
	function onsubmitStudentMark() {

		var HSCNo = $("#usHSCNo").val();
		var SSLCMark = $("#usSSorPUCMark").val();

		if (allTrim(HSCNo) == "") {
			bootbox.alert("Please type HSC Registration No.");
			return false;
		}

		if (allTrim(SSLCMark) == "") {
			bootbox.alert("Please type SSLC/PUC/CGPA Mark");
			return false;
		}

		document.forms["studentMarkEnrollForm"].submit();
	}

	function checkDocumentType(str, count) {

		var filterlist = [ 'doc', 'docx', 'pdf', 'zip', '7z', 'rar' ];
		var msg = 'Invalid File Format!\n\nPlease select valid file format(doc, docx, pdf,zip,7z,rar).';
		if ('${attachmentName}'.indexOf("Image") > -1) {
			filterlist = [ 'tiff', 'tif', 'jpg', 'jpeg', 'png' ];
			msg = 'Invalid File Format!\n\nPlease select valid file format(tiff, tif, jpg, jpeg ,png).';
		}
		var ext = str.value.split('.').pop().toLowerCase();
		if ($.inArray(ext, filterlist) == -1) {
			u = document.getElementById('fileinputs' + count);
			u.innerHTML = getInnerHTML();
			alert(msg);
			return false;
		}
		return true;
	}

	function getInnerHTML() {
		var uploadHTML = "<div class=\"fileinput-new thumbnail\" style=\"max-width: 100px;\">";
		if ('${userImageUrl}' != '') {
			uploadHTML += "<img src=\"+'${usUserImageUrl}'+\" alt=\"\" />";
		} else {
			uploadHTML += "<img src=\"images/denied_user.png\" alt=\"\" style=\"max-width: 100px; border: none;\" />";
		}

		uploadHTML += "</div>";
		uploadHTML += "<div class=\"fileinput-preview fileinput-exists thumbnail\" style=\"max-width: 100px;\"></div>";
		uploadHTML += "<div><span class=\"btn default btn-file\"> ";
		uploadHTML += " <input type=\"file\" name=\"uploadFiles\" id=\"fileUpload1\" size=\"40\" onchange=\"return checkDocumentType(this,1);\" />";
		uploadHTML += " </span> <a href=\"#\" class=\"btn default fileinput-exists\" data-dismiss=\"fileinput\">";
		uploadHTML += "Remove </a> </div>";

		return uploadHTML;

	}
</script>
<style>
thumbnail>img,.thumbnail a>img {
	border: 1px solid #eee;
}
</style>
<div class="modal fade bs-modal-lg" id="student_mark_enroll"
	tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-green-spring">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">HSC Enrollment Information</h4>
			</div>
			<div class="modal-body">
				<s:form name="studentMarkEnrollForm" id="studentMarkEnrollForm"
					action="studentMarkEnroll.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div class="form-body">
						<div class="row">
							<div class="col-md-12">

								<div class="form-group" align="center">



									<div class="fileinput fileinput-new" data-provides="fileinput"
										id="fileinputs1">
										<div class="fileinput-new thumbnail" style="max-width: 100px;">

											<s:if test="#userImageUrl != ''">
												<img src="${usUserImageUrl}" alt="" />
											</s:if>
											<s:else>
												<img src="images/denied_user.png" alt=""
													style="max-width: 100px;" />
											</s:else>
										</div>
										<div class="fileinput-preview fileinput-exists thumbnail"
											style="max-width: 100px; border: none;"></div>
										<div>
											<span class="btn default btn-file"> <%-- <span
												class="fileinput-new"> Select image </span> --%> <%-- <span
												class="fileinput-exists"> Change </span> --%> <input
												type="file" name="uploadFiles" id="fileUpload1" size="40">
											</span> <a href="#" class="btn default fileinput-exists"
												data-dismiss="fileinput"> Remove </a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">

								<div class="col-md-4">
									<label class="control-label">HSC Reg. No. <span
										style="color: red;">*</span>
									</label>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input name="studentsMark.usHSCNo" id="usHSCNo" type="text"
											placeholder="HSC Reg. No." class="form-control">
									</div>
								</div>

								<div class="col-md-4">
									<label class="control-label">SSLC/PUC/CGPA Mark <span
										style="color: red;">*</span>
									</label>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<input name="studentsMark.usSSorPUCMark" id="usSSorPUCMark"
											maxlength="4" type="text" placeholder="SSLC/PUC/CGPA Mark"
											class="form-control">
									</div>
								</div>




							</div>


						</div>




					</div>
				</s:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn red" data-dismiss="modal">Cancel</button>

				<button type="button" class="btn blue"
					onclick="onsubmitStudentMark();" name="submitStudentMark">Submit</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>