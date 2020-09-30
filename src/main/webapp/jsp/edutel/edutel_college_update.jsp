<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />

<script>
	function onsubmitCalled() {

		var collegeName = $("#clCollegeName").val();
		var email = $("#clEmail").val();
		var confirmEmail = $("#clConfirmEmail").val();
		var mobileNo = $("#clMobileNo").val();
		var city = $("#clCity").val();
		var state = $("#clState").val();
		var country = $("#clCountry").val();

		if (allTrim(collegeName) == "") {
			alert("Please type College Name");
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
		document.forms["collegeUpdationForm"].submit();
	}

	function onresetCalled() {
		document.forms["collegeUpdationForm"].reset();
	}

	function onCancelCalled(url) {
		window.location.href = url;
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
				<p>College Update Form</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="collegeUpdationForm" id="collegeUpdationForm"
					action="collegeUpdate.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="userRegistrationDiv"
										style="width: 98%; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<s:set name="clCollegeID">${college.clCollegeId}</s:set>
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
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;College
													Name<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield
														name="college.clCollegeName" id="clCollegeName" size="28"
														maxlength="50" theme="simple" tabindex="5"
														onkeyup="adminUserNameValidation(this)"
														validate="not_empty" msg="College is required"
														fieldname="College Name" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;College
													Type</td>
												<td align="left"><select name="college.clCollegeType"
													id="clCollegeType">
														<option value="Arts & Science">Arts & Science</option>
														<option value="Engineering">Engineering</option>
														<option value="Medical">Medical</option>
														<option value="Management">Management</option>
												</select></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Email
													Id<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="college.clEmail"
														id="clEmail" size="28" maxlength="50" theme="simple"
														tabindex="10" onkeyup="adminNameEmailValidation(this);"
														validate="email" msg="Email Id is required"
														fieldname="Email" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Confirm
													Email Id<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="clConfirmEmail"
														id="clConfirmEmail" size="28" maxlength="50"
														theme="simple" tabindex="15"
														onkeyup="adminNameEmailValidation(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Mobile
													Number<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="college.clMobileNo"
														id="clMobileNo" size="28" maxlength="10" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Alternate
													Mobile Number</td>
												<td align="left"><s:textfield
														name="college.clAlternateMobileNo"
														id="clAlternateMobileNo" size="28" maxlength="10"
														theme="simple" tabindex="5"
														onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 1</td>
												<td align="left"><s:textfield name="college.clPhoneNo1"
														id="clPhoneNo1" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 2</td>
												<td align="left"><s:textfield name="college.clPhoneNo2"
														id="clPhoneNo2" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 3</td>
												<td align="left"><s:textfield name="college.clPhoneNo3"
														id="clPhoneNo3" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 4</td>
												<td align="left"><s:textfield name="college.clPhoneNo4"
														id="clPhoneNo4" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone
													Number 5</td>
												<td align="left"><s:textfield name="college.clPhoneNo5"
														id="clPhoneNo5" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Fax
													Number</td>
												<td align="left"><s:textfield name="college.clFaxNo"
														id="clFaxNo" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Address</td>
												<td align="left"><s:textfield name="college.clAddress1"
														id="clAddress1" size="28" maxlength="100" theme="simple"
														tabindex="5" /></td>

												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Website</td>
												<td align="left"><s:textfield
														name="college.clWebSiteURL" id="clWebSiteURL" size="28"
														maxlength="50" theme="simple" tabindex="10"
														validate="not_empty" msg="Website URL is required"
														fieldname="WebsiteURL" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td align="left"><s:textfield name="college.clAddress2"
														id="clAddress2" size="28" maxlength="100" theme="simple"
														tabindex="5" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Landmark</td>
												<td align="left"><s:textfield name="college.clLandMark"
														id="clLandMark" size="28" maxlength="100" theme="simple"
														tabindex="5" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;City<span
													style="color: red;">*</span></td>
												<td align="left"><s:select name="college.clCity"
														id="clCity" style="width: 181px;" headerKey=""
														headerValue="Select" list="cityList" listKey="label"
														listValue="value" theme="simple"></s:select></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;State<span
													style="color: red;">*</span></td>
												<td align="left"><s:select name="college.clState"
														id="clState" style="width: 185px;" headerKey=""
														headerValue="Select" list="stateList" listKey="label"
														listValue="value" theme="simple"></s:select></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Country<span
													style="color: red;">*</span></td>
												<td align="left"><select name="college.clCountry"
													id="clCountry" style="width: 181px;">
														<option value="India">India</option>
												</select></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Pincode</td>
												<td align="left"><s:textfield name="college.clPinCode"
														id="clPinCode" size="28" maxlength="20" theme="simple"
														tabindex="5" onkeyup="checkIsNumberAndLength(this);"
														validate="not_empty" msg="Pincode is required"
														fieldname="Pincode" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Application
													Form Cost For Others</td>
												<td align="left"><s:textfield
														name="college.clFormCostOthers" id="clFormCostOthers"
														size="28" maxlength="50" theme="simple" tabindex="10"
														validate="not_empty"
														msg="Application Form Price is required"
														fieldname="ApplicationFormPrice"
														onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Application
													Form Cost For SC/ST</td>
												<td align="left"><s:textfield
														name="college.clFormCostSCST" id="clFormCostSCST"
														size="28" maxlength="50" theme="simple" tabindex="10"
														validate="not_empty"
														msg="Application Form Price is required"
														fieldname="ApplicationFormPrice"
														onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Courier
													Charge</td>
												<td align="left"><s:textfield
														name="college.clCourierCharge" id="clCourierCharge"
														size="28" maxlength="50" theme="simple" tabindex="10"
														validate="price" msg="Courier Charge is required"
														fieldname="CourierCharge"
														onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;EduTel
													Service Charge (if any)</td>
												<td align="left"><s:textfield
														name="college.clEduTelCharge" id="clEduTelCharge"
														size="28" maxlength="50" theme="simple" tabindex="10"
														validate="price"
														msg="EduTel Academy Service Charge is required"
														fieldname="EduTel Academy Service"
														onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Form
													Opening Date</td>
												<td align="left"><input type="text" readonly
													class="smalltextbox" id="clFormOpenDate"
													name="college.clFormOpenDate" autocomplete="false"
													value="${college.clFormOpenDate}" size="10" validate="date"
													msg="Form Open Date is required"
													fieldname="Form Opening Date" title="Form Opening Date"
													onchange="futureDateValidation(this);" tabindex="20"
													style="width: 180px; background: url('js/datepicker/calendar.gif') no-repeat scroll right center #FFFFFF" />
													<script>
														new Zapatec.Calendar.setup(
																{
																	inputField : 'clFormOpenDate',
																	ifFormat : '%m-%d-%Y',
																	showsTime : false,
																	button : 'clFormOpenDate',
																	singleClick : true,
																	step : 1,
																	timeFormat : '12'
																});
													</script></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Form
													Closing Date</td>
												<td align="left"><input type="text" readonly
													class="smalltextbox" id="clFormCloseDate"
													name="college.clFormCloseDate" autocomplete="false"
													value="${college.clFormCloseDate}" size="10"
													validate="date" msg="Form Close Date is required"
													fieldname="Form Closing Date" title="Form Closing Date"
													tabindex="20"
													style="width: 180px; background: url('js/datepicker/calendar.gif') no-repeat scroll right center #FFFFFF" />
													<script>
														new Zapatec.Calendar.setup(
																{
																	inputField : 'clFormCloseDate',
																	ifFormat : '%m-%d-%Y',
																	showsTime : false,
																	button : 'clFormCloseDate',
																	singleClick : true,
																	step : 1,
																	timeFormat : '12'
																});
													</script></td>
											</tr>
											<tr>
												<td align="left" class="usertext">&nbsp;&nbsp;&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4"><s:include
														value="i_edutel_college_attachments.jsp">
														<s:param name="attachReadOnly">false</s:param>
														<s:param name="attachmentBannerName">Photo/Documents</s:param>
														<s:param name="attachmentName">Photo Image</s:param>
														<s:param name="attachmentDynamicName"></s:param>
													</s:include></td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="4"><input type="button"
													class="btnstyle" style="width: 150px;" name="saveCollege" value="Update College"
													href="javascript:void(0);" onclick="onsubmitCalled();" />
													&nbsp;&nbsp; <input type="button" class="btnstyle"
													value="Cancel" onclick="onCancelCalled('searchCollege.do')" />&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext">&nbsp;&nbsp;&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
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
