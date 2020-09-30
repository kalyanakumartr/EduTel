<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	function assignMessageToNewGroup() {
		var userGroupNameDialog = $("#userGroupNameDialog").val();
		if (allTrim(userGroupNameDialog) == "") {
			alert("Please enter User Group Name");
			return false;
		} else {
			$("#userGroupNameHidden").val($("#userGroupNameDialog").val());
			document.forms["messagesUserForm"].submit();
		}

	}

	function assignMessageToUsers() {
		$("#userGroupNameHidden").val("");
		document.forms["messagesUserForm"].submit();
	}
</script>
<div class="modal fade bs-modal-lg" id="message_user_group"
	tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg" style="width: 600px;">
		<div class="modal-content">
			<div class="modal-header bg-green-spring">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">User Group</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-3" align="center">
						<img src="images/png/user_group.png" style="width: 150px;" />
					</div>
					<div class="col-md-9">
						<br> <br> <br>
						<div class="col-md-4">
							<label class="control-label">Group Name </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">

								<s:textfield name="userGroupNameDialog" id="userGroupNameDialog"
									cssStyle="width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;"
									placeholder="Group Name" theme="simple" cssClass="form-control" />
							</div>
						</div>

					</div>

				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn red" data-dismiss="modal">Close</button>

				<button type="button" class="btn blue" id="assignBtn"
					onclick="assignMessageToNewGroup();" name="assignBtn">Proceed</button>

				<button type="button" class="btn blue" id="resetBtn"
					onclick="assignMessageToUsers();" name="resetBtn">Skip and
					Continue</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>