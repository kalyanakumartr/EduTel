<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	window.onload = function() {
		$(document).ready(function() {
			// validate signup form on keyup and submit
			var validator = $("#admin_staff").validate({
				rules : {
					remail : {
						equalTo : "#enquirerEmail"
					}
				},
				// the errorPlacement has to take the table layout into account
				errorPlacement : function() {
					return false;
				}
			});
		});
	};
	function validateForm() {
		var enquirerName = document.StudentEnquiryForm.enquirerName.value;
		var enquirerEmail = document.StudentEnquiryForm.enquirerEmail.value;
		var enquirerSchoolName = document.StudentEnquiryForm.enquirerSchoolName.value;
		var enquirerMobileNo = document.StudentEnquiryForm.enquirerMobileNo.value;
		var enquirerBoard = document.StudentEnquiryForm.enquirerBoard.value;

		if (enquirerName == "" || enquirerEmail == ""
				|| enquirerSchoolName == "" || enquirerMobileNo == ""
				|| enquirerBoard == "") {
			alert("Please enter all the fields.");
			return false;
		}
		if (document.StudentEnquiryForm.enquirerState1.checked == true
				|| document.StudentEnquiryForm.enquirerState2.checked == true
				|| document.StudentEnquiryForm.enquirerState3.checked == true
				|| document.StudentEnquiryForm.enquirerState4.checked == true) {
			document.StudentEnquiryForm.submit();
		} else {
			alert("Select any One option");
			return false;
		}
	}//]]>
</script>
<style>
.errorMessage {
	color: #FF0000;
	font-size: 12px;
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	text-align: center;
	font-weight: bold;
}

.span_2_of_4 {
	font-size: 22px;
	padding: 1.5%;
	width: 45%;
}

.fltr {
	border: 1px solid #b0b0b0;
	color: #003c60;
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 14px;
	width: 98%;
}
</style>
<form name="StudentEnquiryForm" action="studentEnquiry.do"
	onsubmit="return validateForm()" method="post" id="admin_staff">
	<div class="custom_form">
		<div class="row">
			<div class="col-md-6">
				<input type="text" name="studentEnquiry.enquirerName"
					id="enquirerName" class="textbox" placeholder="Name *"
					required="required" onkeyup="adminUserNameValidation(this)" />
			</div>
			<div class="col-md-6">
				<input type="text" name="studentEnquiry.enquirerEmail"
					id="enquirerEmail" class="textbox" placeholder="Email *"
					required="required" />
			</div>
			<div class="col-md-6">
				<input type="text" name="studentEnquiry.enquirerSchoolName"
					id="enquirerSchoolName" class="textbox" placeholder="School Name *"
					required="required" onkeyup="adminUserNameValidation(this)" />
			</div>
			<div class="col-md-6">
				<input type="text" name="studentEnquiry.enquirerMobileNo"
					id="enquirerMobileNo" class="textbox" maxlength="10"
					placeholder="Mobile *" required="required"
					onkeyup="checkIsNumberAndLength(this);" />
			</div>
			<div class="col-md-6">
				<select name="studentEnquiry.enquirerBoard" id="enquirerBoard"
					class="txt_fild fltr">
					<option value="State_Board">State_Board</option>
					<!-- option value="CBSE">CBSE</option -->
				</select>
			</div>
			<div class="col-md-6">
				<select name="studentEnquiry.enquirerClass" id="enquirerClass"
					class="txt_fild fltr">
					<option value="12th">12th</option>
				</select>
			</div>
			<div class="col-md-12">
				<b>You are a</b>
			</div>
			<div class="col-md-12" style="margin-top: -10px;">
				<span class="col-md-3"><input type="radio"
					name="studentEnquiry.enquirerState" id="enquirerState1"
					value="Student" />Student</span> <span class="col-md-3"><input
					type="radio" name="studentEnquiry.enquirerState"
					id="enquirerState2" value="Parent" />Parent</span> <span class="col-md-3"><input
					type="radio" name="studentEnquiry.enquirerState"
					id="enquirerState3" value="Tutor" />Tutor</span> <span class="col-md-3"><input
					type="radio" name="studentEnquiry.enquirerState"
					id="enquirerState4" value="Teacher" />Teacher</span>
			</div>
			<div class="col-md-12" style="float: left;">
				<input type="submit" class="btn" value="Enquiry" />
			</div>
		</div>
		<div class="regfm" style="margin-top: -5px;">
			<s:if test="hasActionErrors()">
				<s:iterator value="actionErrors">
					<span class="errorMessage" id="errorSpan"
						style="color: red; font-weight: bold;"><s:property
							escape="false" /></span>
				</s:iterator>
			</s:if>
		</div>
	</div>
</form>

