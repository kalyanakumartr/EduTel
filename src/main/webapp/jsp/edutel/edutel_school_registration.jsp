<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	function onsubmitCalled() {
		var schoolName = $("#scSchoolName").val();
		var email = $("#scEmail").val();
		var confirmEmail = $("#scConfirmEmail").val();
		var mobileNo = $("#scMobileNo").val();
		var city = $("#scCity").val();
		var state = $("#scState").val();
		var country = $("#scCountry").val();

		if (allTrim(schoolName) == "") {
			alert("Please type School Name");
			return false;
		}
		if (allTrim(email) == "") {
			alert("Please type Email Id");
			return false;
		}
		if (allTrim(confirmEmail) == "") {
			alert("Please type Confirm Email Id");
			return false;
		}
		if (allTrim(mobileNo) == "") {
			alert("Please type Mobile No.");
			return false;
		}

		if (allTrim(city) == "") {
			alert("Please Select City");
			return false;
		}
		if (allTrim(state) == "") {
			alert("Please Select State");
			return false;
		}
		if (allTrim(country) == "") {
			alert("Please Select Country");
			return false;
		}

		if (allTrim(email) != allTrim(confirmEmail)) {
			alert("Email Id doesn't match ");
			return false;
		}

		document.forms["schoolRegistrationForm"].submit();
	}

	function onresetCalled() {
		document.forms["schoolRegistrationForm"].reset();
	}
</script>
</head>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>School Registration Form</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="schoolRegistrationForm" id="schoolRegistrationForm"
					action="schoolRegistration.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="schoolRegistrationDiv"
										style="width: 850px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<colgroup>
												<col width="20%" />
												<col width="30%" />
												<col width="20%" />
												<col width="30%" />
											</colgroup>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;</td>
												<td valign="top" align="center" width="100%" colspan="3"
													height="30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:if
														test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan"
																style="color: red; font-weight: bold;"><s:property
																	escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;School
													Name<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield
														name="school.scSchoolName" id="scSchoolName" size="28"
														maxlength="50" theme="simple" tabindex="5"
														onkeyup="adminUserNameValidation(this)"
														validate="not_empty" msg="School is required"
														fieldname="School Name" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;School
													Type</td>
												<td align="left"><select name="school.scSchoolType"
													id="scSchoolType" style="width: 185px;">
														<option value="State_Board">State_Board</option>
														<!-- option value="CBSE">CBSE</option -->
												</select></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Email
													Id<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="school.scEmail"
														id="scEmail" size="28" maxlength="50" theme="simple"
														tabindex="10" onkeyup="adminNameEmailValidation(this);"
														validate="email" msg="Email Id is required"
														fieldname="Email" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Confirm
													Email Id<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="scConfirmEmail"
														id="scConfirmEmail" size="28" maxlength="50"
														theme="simple" tabindex="15"
														onkeyup="adminNameEmailValidation(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Mobile
													Number<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="school.scMobileNo"
														id="scMobileNo" size="28" maxlength="10" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Alternate
													Mobile Number</td>
												<td align="left"><s:textfield
														name="school.scAlternateMobileNo" id="scAlternateMobileNo"
														size="28" maxlength="10" theme="simple" tabindex="5"
														onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 1</td>
												<td align="left"><s:textfield name="school.scPhoneNo1"
														id="scPhoneNo1" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 2</td>
												<td align="left"><s:textfield name="school.scPhoneNo2"
														id="scPhoneNo2" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 3</td>
												<td align="left"><s:textfield name="school.scPhoneNo3"
														id="scPhoneNo3" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 4</td>
												<td align="left"><s:textfield name="school.scPhoneNo4"
														id="scPhoneNo4" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 5</td>
												<td align="left"><s:textfield name="school.scPhoneNo5"
														id="scPhoneNo5" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Fax
													Number</td>
												<td align="left"><s:textfield name="school.scFaxNo"
														id="scFaxNo" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Address</td>
												<td align="left"><s:textfield name="school.scAddress1"
														id="scAddress1" size="28" maxlength="100" theme="simple"
														tabindex="5" /></td>

												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Website</td>
												<td align="left"><s:textfield
														name="school.scWebSiteURL" id="scWebSiteURL" size="28"
														maxlength="50" theme="simple" tabindex="5"
														validate="not_empty" msg="Website URL is required"
														fieldname="WebsiteURL" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td align="left"><s:textfield name="school.scAddress2"
														id="scAddress2" size="28" maxlength="100" theme="simple"
														tabindex="5" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Landmark</td>
												<td align="left"><s:textfield name="school.scLandMark"
														id="scLandMark" size="28" maxlength="100" theme="simple"
														tabindex="5" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;City<span
													style="color: red;">*</span></td>
												<td align="left"><s:select name="school.scCity"
														id="scCity" style="width: 185px;" headerKey=""
														headerValue="Select" list="cityList" listKey="label"
														listValue="value" theme="simple"></s:select></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;State<span
													style="color: red;">*</span></td>
												<td align="left"><s:select name="school.scState"
														id="scState" style="width: 185px;" list="stateList"
														listKey="label" listValue="value" theme="simple"></s:select></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Country<span
													style="color: red;">*</span></td>
												<td align="left"><select name="school.scCountry"
													id="scCountry" style="width: 185px;">
														<option value="India">India</option>
												</select></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Pincode</td>
												<td align="left"><s:textfield name="school.scPinCode"
														id="scPinCode" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);"
														validate="not_empty" msg="Pincode is required"
														fieldname="Pincode" /></td>
											</tr>
											<tr>
												<td align="left" colspan="4"></td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4"><s:include
														value="i_edutel_attachments.jsp">
														<s:param name="attachReadOnly">false</s:param>
														<s:param name="attachmentBannerName">School Image</s:param>
														<s:param name="attachmentName">Photo Image</s:param>
														<s:param name="attachmentDynamicName"></s:param>
													</s:include></td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="4"><input type="button"
													class="btnstyle" style="width: 150px;" name="saveSchool"
													value="Save School" href="javascript:void(0);"
													onclick="onsubmitCalled();" /> &nbsp;&nbsp; <input
													type="button" class="btnstyle" value="Reset"
													onclick="onresetCalled()" /> &nbsp;&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4">&nbsp;&nbsp;&nbsp;</td>
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
