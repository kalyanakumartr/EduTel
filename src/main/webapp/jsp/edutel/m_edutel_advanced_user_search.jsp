<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="css/bootbox/components.css" rel="stylesheet" type="text/css" />
<link href="css/bootbox/plugins.css" rel="stylesheet" type="text/css" />
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="js/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		onAdvancedSearchCleared();
	});

	function onUserAdvancedSearch() {
		var userName = $("#usUserName").val();
		var userId = $("#usUserId").val();
		var emailId = $("#usEmailId").val();
		var mobileNo = $("#usMobileNo").val();
		var schoolName = $("#usSchoolName").val();
		var city = $("#usCity").val();
		var keySold = $("#keySold").val();
		var serialKey = $("#usSerialKey").val();
		var francheesArea = $("#usFrancheesArea").val();
		var studentBatch = $("#studentBatch").val();
		var globalSearch = $("#usGlobalSearch").val();

		var userType = "${user.usUsersType}";
		var userFilter = "";
		if (allTrim(globalSearch) == "") {
			if (allTrim(userType) == "Student") {
				userFilter = userName + "#" + userId + "#" + emailId + "#"
						+ mobileNo + "#" + schoolName + "#" + city + "#"
						+ serialKey + "#" + keySold + "#" + studentBatch + "#"
						+ "Advance";
			} else if (allTrim(userType) == "Employee") {
				userFilter = userName + "#" + userId + "#" + emailId + "#"
						+ mobileNo + "#" + city + "#" + "Advance";
			} else if (allTrim(userType) == "Franchisee") {
				userFilter = userName + "#" + userId + "#" + emailId + "#"
						+ mobileNo + "#" + francheesArea + "#" + city + "#"
						+ "Advance";
			}
		} else {
			userFilter = globalSearch + "#" + "Global";
		}
		userListTable.fnFilter(userFilter, null, false, true, true, true);
		$("#advanced_user_search").modal("hide");
	}

	function onAdvancedSearchCleared() {
		$("#usUserName").val("");
		$("#usUserId").val("");
		$("#usEmailId").val("");
		$("#usMobileNo").val("");
		$("#usSchoolName").val("");
		$("#usCity").val("");
		$("#keySold").val("");
		$("#usSerialKey").val("");
		$("#studentBatch").val("");
		$("#usFrancheesArea").val("");
		$("#usGlobalSearch").val("");

	}

	function onDisableEnableAdvanceSearchFileds() {

		var value = $("#usGlobalSearch").val();
		var userType = "${user.usUsersType}";

		if (allTrim(value) != "") {
			$("#usUserName").attr('readonly', true);
			$("#usUserId").attr('readonly', true);
			$("#usEmailId").attr('readonly', true);
			$("#usMobileNo").attr('readonly', true);
			$("#usCity").attr("disabled", true);
			$("#usCity").attr("style", "height:34px; width:100%;");

			if (allTrim(userType) == "Student") {
				$("#usSchoolName").attr('readonly', true);
				$("#usSerialKey").attr('readonly', true);
				$("#keySold").attr("disabled", true);
				$("#keySold").attr("style", "height:34px; width:100%;");
				$("#studentBatch").attr("disabled", true);
				$("#studentBatch").attr("style", "height:34px; width:100%;");
			} else if (allTrim(userType) == "Franchisee") {
				$("#usFrancheesArea").attr("disabled", true);
				$("#usFrancheesArea").attr("style", "height:34px; width:100%;");
			}
		} else {
			$("#usUserName").removeAttr('readonly');
			$("#usUserId").removeAttr('readonly');
			$("#usEmailId").removeAttr('readonly');
			$("#usMobileNo").removeAttr('readonly');
			$("#usCity").removeAttr("disabled");
			if (allTrim(userType) == "Student") {
				$("#usSchoolName").removeAttr('readonly');
				$("#usSerialKey").removeAttr('readonly');
				$("#keySold").removeAttr("disabled");
				$("#studentBatch").removeAttr("disabled");
			} else if (allTrim(userType) == "Franchisee") {
				$("#usFrancheesArea").removeAttr('disabled');
			}
		}
	}

	function onDisableEnableGlobalSearchFiled() {

		var userName = $("#usUserName").val();
		var userId = $("#usUserId").val();
		var emailId = $("#usEmailId").val();
		var mobileNo = $("#usMobileNo").val();
		var schoolName = $("#usSchoolName").val();
		var city = $("#usCity").val();
		var keySold = $("#keySold").val();
		var serialKey = $("#usSerialKey").val();
		var studentBatch = $("#studentBatch").val();
		var francheesArea = $("#usFrancheesArea").val();
		var userType = "${user.usUsersType}";
		if (allTrim(userType) == "Student") {
			if (allTrim(userName) != "" || allTrim(userId) != ""
					|| allTrim(emailId) != "" || allTrim(mobileNo) != ""
					|| allTrim(city) != "" || allTrim(schoolName) != ""
					|| allTrim(keySold) != "" || allTrim(serialKey) != ""
					|| allTrim(studentBatch) != "") {
				$("#usGlobalSearch").attr('readonly', true);
			} else {
				$("#usGlobalSearch").removeAttr('readonly');
			}
		}
		if (allTrim(userType) == "Employee") {
			if (allTrim(userName) != "" || allTrim(userId) != ""
					|| allTrim(emailId) != "" || allTrim(mobileNo) != ""
					|| allTrim(city) != "") {
				$("#usGlobalSearch").attr('readonly', true);
			} else {
				$("#usGlobalSearch").removeAttr('readonly');
			}
		}
		if (allTrim(userType) == "Franchisee") {
			if (allTrim(userName) != "" || allTrim(userId) != ""
					|| allTrim(emailId) != "" || allTrim(mobileNo) != ""
					|| allTrim(city) != "" || allTrim(francheesArea) != "") {
				$("#usGlobalSearch").attr('readonly', true);
			} else {
				$("#usGlobalSearch").removeAttr('readonly');
			}
		}
	}
</script>
<div class="modal fade bs-modal-lg" id="advanced_user_search"
	tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-green-spring">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Advanced ${param.usersType} User Search</h4>
			</div>
			<div class="modal-body">
				<s:form name="studentMarkEnrollForm" id="studentMarkEnrollForm"
					action="studentMarkEnroll.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div class="form-body">
						<div class="row">
							<div class="col-md-2">
								<label class="control-label">Global Search</label>
							</div>
							<div class="col-md-4">
								<input name="usGlobalSearch" id="usGlobalSearch" type="text"
									onchange="onDisableEnableAdvanceSearchFileds();"
									style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
									placeholder="Global Search" class="form-control">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-md-2">
								<label class="control-label">User Name </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<input name="usUserName" id="usUserName" type="text"
										onchange="onDisableEnableGlobalSearchFiled();"
										style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
										placeholder="User Name" class="form-control" maxlength="50"
										onkeyup="adminUserNameValidation(this)">
								</div>
							</div>
							<div class="col-md-2">
								<label class="control-label">User Id </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<input name="usUserId" id="usUserId" type="text"
										onchange="onDisableEnableGlobalSearchFiled();"
										style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
										placeholder="User Id" class="form-control">
								</div>
							</div>
							<div class="col-md-2">
								<label class="control-label">Email Id </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<input name="usEmailId" id="usEmailId" type="text"
										onchange="onDisableEnableGlobalSearchFiled();"
										style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
										placeholder="Email Id" class="form-control">
								</div>
							</div>
							<div class="col-md-2">
								<label class="control-label">Mobile No. </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<input name="usMobileNo" id="usMobileNo" type="text"
										onchange="onDisableEnableGlobalSearchFiled();"
										style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
										placeholder="Mobile No." class="form-control" maxlength="10"
										onkeyup="checkIsNumberAndLength(this);">
								</div>
							</div>
							<s:if test="#usersType=='Student'">
								<div class="col-md-2">
									<label class="control-label">School Name </label>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<s:select name="usSchoolName" id="usSchoolName"
											onchange="onDisableEnableGlobalSearchFiled();"
											cssClass="form-control select2" headerKey=""
											headerValue="Select School Name" list="schoolList"
											title="School Name" listKey="scSchoolId"
											listValue="scSchoolName" theme="simple"
											value="%{user.usSchoolId}"></s:select>
									</div>
								</div>
							</s:if>
							<s:if test="#usersType=='Franchisee'">
								<div class="col-md-2">
									<label class="control-label">Franchisee Area </label>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<s:select name="usFrancheesArea" id="usFrancheesArea"
											onchange="onDisableEnableGlobalSearchFiled();"
											cssClass="form-control select2" headerKey=""
											title="Franchisee Area" headerValue="Select Franchisee Area"
											list="cityList" listKey="label" listValue="value"
											theme="simple"></s:select>
									</div>
								</div>
							</s:if>
							<div class="col-md-2">
								<label class="control-label">City </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<s:select name="usCity" id="usCity"
										cssClass="form-control select2" headerKey="" title="City"
										onchange="onDisableEnableGlobalSearchFiled();"
										headerValue="Select City" list="cityList" listKey="label"
										listValue="value" theme="simple"></s:select>
								</div>
							</div>
							<s:if test="#usersType=='Student'">
								<div class="col-md-2">
									<label class="control-label">Serial Key </label>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<input name="usSerialKey" id="usSerialKey" type="text"
											onchange="onDisableEnableGlobalSearchFiled();"
											style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
											placeholder="Serial Key" class="form-control">
									</div>
								</div>
								<div class="col-md-2">
									<label class="control-label">Key Sold By </label>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<s:select name="keySold" id="keySold"
											onchange="onDisableEnableGlobalSearchFiled();"
											cssClass="form-control select2" headerKey=""
											title="Key Sold By" headerValue="Select Key Seller"
											list="usersList" listKey="label" listValue="value"
											theme="simple"></s:select>
									</div>
								</div>
								<div class="col-md-2">
									<label class="control-label">Student Batch </label>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<s:select name="studentBatch" id="studentBatch"
											onchange="onDisableEnableGlobalSearchFiled();"
											cssClass="form-control select2" headerKey=""
											title="Key Sold By" headerValue="Select Student Batch"
											list="serialBatchList" listKey="label" listValue="value"
											theme="simple"></s:select>
									</div>
								</div>
							</s:if>
						</div>
					</div>
				</s:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn red" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn blue"
					onclick="onAdvancedSearchCleared();" name="submitStudentMark">Reset</button>
				<button type="button" class="btn blue"
					onclick="onUserAdvancedSearch();" name="submitStudentMark">Search</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>