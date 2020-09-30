<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<style>
.buttonLogin {
	background: url('images/Login/button-bg.png') repeat-x top center;
	font-family: Tahoma, Arial, verdana, helvetica, sans-serif;
	font-size: 11px;
	border: 1px solid #999;
	padding: 5px;
	color: white;
	font-weight: bold;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	width: 90px;
}

.buttonLogin:HOVER {
	background: url('images/Login/button-bg_hover.png') repeat-x top center;
	font-family: Tahoma, Arial, verdana, helvetica, sans-serif;
	font-size: 11px;
	border: 1px solid #999;
	padding: 5px;
	color: #336699;
	font-weight: bold;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	width: 90px;
}

.buttonLogin:disabled {
	background: url('images/Login/button-bg_hover.png') repeat-x top center;
	font-family: Tahoma, Arial, verdana, helvetica, sans-serif;
	font-size: 11px;
	border: 1px solid #999;
	padding: 5px;
	color: gray;
	font-weight: bold;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	width: 90px;
}

.usertext {
	font-family: Tahoma, Arial, verdana, helvetica, sans-serif;
	font-size: 11px;
	font-weight: bold;
	color: gray;
	text-decoration: none;
	vertical-align: middle;
}
</style>
<script>
	var elements = [ "serialKey", "submitbtn" ];
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
					var elementlist = document.getElementById("serialKey");
					if (elementlist.length == 0)
						elementlist.focus();
					else
						onsubmitCalled();
				} else {
					if (elementVal == "serialKey" || elementVal == "submitbtn") {
						onsubmitCalled();
					}

				}
				if (keyStatus == 0) {
					var elementlist = document.getElementById("serialKey");
					if (elementlist.length == 0)
						elementlist.focus();
					else
						onsubmitCalled();
				}
			}
		}
	}

	function onsubmitCalled() {
		if (Validate(document.forms["serialKeyActivateForm"])) {
			document.forms["serialKeyActivateForm"].submit();
		}
	}

	function onresetCalled() {
		document.forms["serialKeyActivateForm"].reset();

	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Serial Key Verification Form</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="serialKeyActivateForm" id="serialKeyActivateForm"
					method="post" action="serialKeyValidateAndActivate.do"
					show_alert="1" onsubmit="return validateKeyForm(this);">
					<div>
						<s:if test="hasActionErrors()">
							<s:iterator value="actionErrors">
								<span class="errorMessage" id="errorSpan"
									style="color: red; font-size: 12px;"><s:property
										escape="false" /></span>
							</s:iterator>
						</s:if>
						<table>
							<tr>
								<td colspan="2" nowrap="nowrap">&nbsp;</td>
							</tr>
							<tr>
								<td><br> <br> <img src="edu-images/key-icon.png"
									style="height: 100px;" width="100px;" /></td>
								<td>
									<div id="serialKeyActivateForm"
										style="height: 60%; width: 350px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td colspan="4" nowrap="nowrap">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="4" nowrap="nowrap">&nbsp;</td>
											</tr>
											<tr>
												<td nowrap="nowrap">&nbsp;</td>
												<td align="left" class="usertext" nowrap="nowrap"
													valign="top"><span style="valign: top">&nbsp;&nbsp;&nbsp;Serial
														No&nbsp;&nbsp;&nbsp;</span></td>
												<td align="right"><input
													name="serialKeyGenerate.serialNo" type="text"
													class="serialKeyId" id="serialNo" value=""
													validate="not_empty" msg="Serial should not be empty"
													fieldname="Serial No" tabindex="1" value=""
													style="width: 200px; height: 30px; font-size: 14px;" /></td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="4" nowrap="nowrap">&nbsp;</td>
											</tr>
											<tr>
												<td nowrap="nowrap">&nbsp;</td>
												<td align="left" class="usertext" nowrap="nowrap"
													valign="top"><span style="valign: top">&nbsp;&nbsp;&nbsp;Serial
														Key&nbsp;&nbsp;&nbsp;</span></td>
												<td align="right"><input
													name="serialKeyGenerate.serialKey" type="text"
													autocomplete="off" class="serialKeyId" id="serialKey"
													validate="not_empty" msg="Serial Key should not be empty"
													fieldname="Serial Key" tabindex="2"
													style="width: 200px; height: 30px; font-size: 14px;" /></td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="4" nowrap="nowrap">&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="4" nowrap="nowrap"><input
													type="button" class="buttonLogin" name="validateKey"
													value="Validate Key" href="javascript:void(0);"
													onclick="onsubmitCalled();" /> &nbsp;&nbsp; <input
													type="button" class="buttonLogin" value="Reset"
													onclick="onresetCalled()" /> &nbsp;&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td colspan="4" nowrap="nowrap">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="4" nowrap="nowrap">&nbsp;</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
