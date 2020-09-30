<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="js/commonUtil.js"></script>

<script>
	var elements = [ "passWord", "submitbtn" ];
	if (document.addEventListener) {
		document.addEventListener("keyup", keyCapt, false);
	} else {
		document.attachEvent("onkeyup", keyCapt);
	}

	function keyCapt(e) {
		if (typeof window.event != "undefined") {
			e = window.event;//code for IE
		}
		if (e.type == "keyup") {
			if (e.keyCode == 13) {
				var keyStatus = 0;
				var elementVal = document.activeElement.id;
				if (elementVal.length == 0) {
					var elementlist = document.getElementById("passWord");
					if (elementlist.length == 0)
						elementlist.focus();
					else
						onsubmitCalled();
				} else {
					if (elementVal == "passWord" || elementVal == "submitbtn") {
						onsubmitCalled();
					}

				}
				if (keyStatus == 0) {
					var elementlist = document.getElementById("passWord");
					if (elementlist.length == 0)
						elementlist.focus();
					else
						onsubmitCalled();
				}
			}
		}
	}

	function onsubmitCalled() {
		if (Validate(document.forms["loginForm"])) {
			document.forms["loginForm"].submit();
		}
	}

	function onresetCalled() {
		document.forms["loginForm"].reset();
	}

	function loadWindow() {
		$("#errorSpan").show();
		var userId = $("#userId").val();
		if (userId == "")
			$("#userId").focus();
		else
			$("#passWord").focus();
	}
</script>
<style>
.userId-text {
	background: #ffffff url("images/Login/user_icon.png") no-repeat scroll
		right center;
}
</style>
<div class="modal fade in" id="login-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header login_modal_header">
				<h2 class="modal-title" id="myModalLabel">
					<center>EduTel Login</center>
				</h2>
			</div>
			<div class="modal-body login-modal">

				<s:form name="loginForm" id="loginForm" method="post"
					action="loginAuthentication.do?LOGIN_ATTEMPT=1" show_alert="1"
					onsubmit="return Validate(this);">
					<div class="form-body">
						<div class="form-group" style="margin-bottom: 15px;"
							align="center">
							<s:iterator value="actionErrors">
								<span class="errorMessage" id="errorSpan"
									style="color: red; font-weight: bold;"><s:property
										escape="false" /></span>
							</s:iterator>
						</div>
						<div class="form-group" style="margin-bottom: 15px;"
							align="center">
							<div class="input-group">
								<input name="userId" type="text"
									class="form-control userId-text" placeholder="User Id"
									id="userId" value="" validate="not_empty"
									style="width: 100%; height: 30px;"
									msg="User Id should not be empty" fieldname="User Id"
									tabindex="1" />
							</div>
						</div>
						<div class="form-group" style="margin-bottom: 15px;"
							align="center">
							<div class="input-group">

								<input name="passWord" type="password" autocomplete="off"
									class="form-control" id="passWord" validate="not_empty"
									placeholder="Password" style="width: 100%; height: 30px;"
									msg="Password should not be empty" fieldname="Password"
									tabindex="2" />
							</div>
						</div>
						<s:hidden name="loginAttempt" value="%{'1'}"></s:hidden>
					</div>

				</s:form>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer login_modal_footer">
				<div class="form-actions" align="right">
					<button class="btn blue" type="button" onclick="onsubmitCalled();">Login</button>
					<button class="btn blue" type="button" onclick="onresetCalled()">Reset</button>
				</div>
			</div>

		</div>
	</div>
</div>