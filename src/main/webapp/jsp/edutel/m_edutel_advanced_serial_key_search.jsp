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
	function onSerialKeyAdvancedSearch() {
		var employeeName = $("#employeeName option:selected").text();
		var serialBatch = $("#serialKeyBatch").val();
		var serialKey = $("#serialKey").val();
		var serialNo = $("#serialNo").val();
		var promoType = $("#promotionType").val();
		var keyStatus = $("#keyStatus").val();
		var globalSearch = $("#keyGlobalSearch").val();
		var serialFilter = "";
		if (allTrim(globalSearch) == "") {
			if (allTrim(employeeName) == "--Select Employee Name--") {
				employeeName = "";
			}
			serialFilter = employeeName + "#" + serialBatch + "#" + serialKey
					+ "#" + serialNo + "#" + promoType + "#" + keyStatus + "#"
					+ "Advance";
		} else {
			serialFilter = globalSearch + "#" + "Global";
		}
		serialKeyTable.fnFilter(serialFilter, null, false, true, true, true);
		$("#advanced_serial_key_search").modal("hide");
	}

	function onAdvancedSearchCleared() {
		$("#employeeName").val("");
		$("#serialKeyBatch").val("");
		$("#serialKey").val("");
		$("#serialNo").val("");
		$("#promotionType").val("");
		$("#keyStatus").val("");
		$("#keyGlobalSearch").val("");
	}

	function onDisableEnableAdvanceSearchFileds() {
		var value = $("#keyGlobalSearch").val();
		if (allTrim(value) != "") {
			$("#employeeName").attr("disabled", true);
			$("#employeeName").attr("style", "height:34px; width:100%;");
			$("#serialKeyBatch").attr("disabled", true);
			$("#serialKeyBatch").attr("style", "height:34px; width:100%;");
			$("#serialNo").attr("readonly", true);
			$("#promotionType").attr("disabled", true);
			$("#promotionType").attr("style", "height:34px; width:100%;");
			$("#keyStatus").attr("disabled", true);
			$("#keyStatus").attr("style", "height:34px; width:100%;");
			$("#serialKey").attr('readonly', true);
		} else {
			$("#serialKey").removeAttr('readonly');
			$("#serialNo").removeAttr('readonly');
			$("#employeeName").removeAttr("disabled");
			$("#serialKeyBatch").removeAttr("disabled");
			$("#promotionType").removeAttr("disabled");
			$("#keyStatus").removeAttr("disabled");
		}
	}

	function onDisableEnableGlobalSearchFiled() {
		var employeeName = $("#employeeName option:selected").text();
		var serialBatch = $("#serialKeyBatch").val();
		var serialKey = $("#serialKey").val();
		var serialNo = $("#serialNo").val();
		var promoType = $("#promotionType").val();
		var keyStatus = $("#keyStatus").val();
		if (allTrim(employeeName) == "--Select Employee Name--") {
			employeeName = "";
		}
		if (allTrim(employeeName) != "" || allTrim(serialBatch) != ""
				|| allTrim(serialKey) != "" || allTrim(serialNo) != ""
				|| allTrim(promoType) != "" || allTrim(keyStatus) != "") {
			$("#keyGlobalSearch").attr('readonly', true);
		} else {
			$("#keyGlobalSearch").removeAttr('readonly');
		}
	}
</script>

<div class="modal fade bs-modal-lg" id="advanced_serial_key_search"
	tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-green-spring">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Advanced Serial Key Search</h4>
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
								<input name="keyGlobalSearch" id="keyGlobalSearch" type="text"
									style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
									onchange="onDisableEnableAdvanceSearchFileds();"
									placeholder="Global Search" class="form-control">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-md-2">
								<label class="control-label">Employee Name </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<s:select cssClass="form-control select2" name="employeeName"
										id="employeeName" theme="simple" list="usersList"
										onchange="onDisableEnableGlobalSearchFiled();" listKey="label"
										listValue="value" headerKey=""
										headerValue="--Select Employee Name--" />
								</div>
							</div>
							<div class="col-md-2">
								<label class="control-label">Serial Key </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<input name="serialKey" id="serialKey" type="text"
										onchange="onDisableEnableGlobalSearchFiled();"
										style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
										placeholder="Serial Key" class="form-control">
								</div>
							</div>
							<div class="col-md-2">
								<label class="control-label">Serial No.</label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<input name="serialNo" id="serialNo" type="text"
										style="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
										placeholder="Serial No." class="form-control " maxlength="6"
										onchange="onDisableEnableGlobalSearchFiled();"
										onkeyup="checkIsNumberAndLength(this);">
								</div>
							</div>
							<div class="col-md-2">
								<label class="control-label">Student Batch </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">

									<s:select cssClass="form-control select2" name="serialKeyBatch"
										id="serialKeyBatch" theme="simple" list="serialBatchList"
										listKey="label" listValue="value" headerKey=""
										onchange="onDisableEnableGlobalSearchFiled();"
										headerValue="--Select Student Batch--" />
								</div>
							</div>
							<div class="col-md-2">
								<label class="control-label">Key Status </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<s:select name="keyStatus" headerKey="" id="keyStatus"
										cssClass="form-control select2"
										headerValue="--Select Key Status--"
										list="#{'Not Sold':'Not Sold', 'Validated':'Validated'}"
										onchange="onDisableEnableGlobalSearchFiled();" theme="simple">
									</s:select>
								</div>
							</div>
							<div class="col-md-2">
								<label class="control-label">Promotion Type </label>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<s:select cssClass="form-control select2" name="promotionType"
										id="promotionType" theme="simple" list="epromoList"
										listKey="label" listValue="value" headerKey=""
										onchange="onDisableEnableGlobalSearchFiled();"
										headerValue="--Select Promotional Type--" />
								</div>
							</div>
						</div>
					</div>
				</s:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn red" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn blue"
					onclick="onAdvancedSearchCleared();" name="submitStudentMark">Reset</button>
				<button type="button" class="btn blue"
					onclick="onSerialKeyAdvancedSearch();" name="submitStudentMark">Search</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>