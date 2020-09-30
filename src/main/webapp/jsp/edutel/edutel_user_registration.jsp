<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script type="text/javascript" src="js/commonUtil.js"></script>
<script>
	function onresetCalled() {
		document.forms["userRegistrationForm"].reset();
	}

	function onsubmitCalled() {
		var serialKey = $("#usSerialKey").val();
		var userName = $("#usUserName").val();
		var gender = $("#usSex").val();
		var usEmail = $("#usEmail").val();
		var mobileNo = $("#usMobileNo").val();
		var dateOfJoin = $("#usDateOfJoin").val();
		var dob = $("#usDob").val();
		var boardName = $("#usBoardName").val();
		var schoolId = $("#usSchoolId").val();
		var groupName = $("#usGroupName").val();
		var city = $("#usCity").val();
		var state = $("#usState").val();
		var country = $("#usCountry").val();
		var francheesArea = $("#usFrancheesArea").val();
		var userType = "${user.usUsersType}";

		if (allTrim(userType) == "Student") {
			if (allTrim(serialKey) == "") {
				alert("Please Type Serial Key");
				return false;
			}
		}

		if (allTrim(userName) == "") {
			alert("Please type " + userType + " Name");
			return false;
		}
		if (allTrim(userType) == "Student" || allTrim(userType) == "Employee") {
			if (allTrim(gender) == "") {
				alert("Please Select Gender");
				return false;
			}
		}
		if (allTrim(usEmail) == "") {
			alert("Please type Email Id ");
			return false;
		} else {
			var valEmail = validateEmail(usEmail);
			if (valEmail == false) {
				alert("Please type valid Email Id ");
				return false;
			}
		}

		if (allTrim(mobileNo) == "") {
			alert("Please type Mobile Number ");
			return false;
		} else {
			var mobLen = allTrim(mobileNo).length;
			if (mobLen < 10) {
				alert("Please type valid Mobile Number ");
				return false;
			}
		}

		if (allTrim(dateOfJoin) == "") {
			alert("Please Select Date Of Join");
			return false;
		}
		if (allTrim(userType) == "Student" || allTrim(userType) == "Employee") {
			if (allTrim(dob) == "") {
				alert("Please Select Date Of Birth");
				return false;
			}
		}
		if (allTrim(userType) == "Student") {
			if (allTrim(boardName) == "") {
				alert("Please Select Exam Board");
				return false;
			}
			if (allTrim(schoolId) == "") {
				alert("Please Select School Name");
				return false;
			}
			if (allTrim(groupName) == "") {
				alert("Please Select Subjects");
				return false;
			}
		}
		if (allTrim(userType) == "Franchisee") {
			if (allTrim(francheesArea) == "") {
				alert("Please Select Franchees Area");
				return false;
			}
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

		document.forms["userRegistrationForm"].submit();
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
				<p>${user.usUsersType} Registration Form</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="userRegistrationForm" id="userRegistrationForm" action="userRegistration.do" method="post" enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm(this)">
					<div>
						<table>
							<tr>
								<td>
									<div id="userRegistrationDiv" style="width: 98%; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<s:set name="usersType">${user.usUsersType}</s:set>
										<table align="center" cellpadding="0" cellspacing="0" width="100%" style="padding: 10px; border: 1px; border-color: gray;">
											<colgroup>
												<col width="20%" />
												<col width="30%" />
												<col width="20%" />
												<col width="30%" />
											</colgroup>
											<tr>
												<td valign="top" align="center" width="100%" colspan="4" height="30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan" style="color: red; font-weight: bold;"><s:property escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<s:if test="#usersType=='Student'">
												<tr>
													<s:if test="%{user.profileBy!='Student'}">
														<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;User Serial Key<span style="color: red;">*</span>
														</td>
														<td align="left"><s:textfield name="user.usSerialKey" id="usSerialKey" size="28" maxlength="50" theme="simple" tabindex="5" onkeyup="alphanumericValidation(this)" /></td>
													</s:if>
													<s:else>
														<s:hidden name="user.usSerialKey" id="usSerialKey" />
													</s:else>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;</td>
													<td align="left" class="lblstyle" nowrap="nowrap"><s:hidden name="user.usUsersType" id="usUsersType" /> <s:hidden name="user.profileBy" id="profileBy" /></td>
												</tr>
											</s:if>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp; ${user.usUsersType} Name<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="user.usUserName" id="usUserName" size="28" maxlength="50" theme="simple" tabindex="5" onkeyup="adminUserNameValidation(this)" validate="not_empty"
														msg="UserName is required" fieldname="User Name" /></td>
												<s:if test="#usersType=='Student' || #usersType=='Employee'">
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Last Name</td>
													<td align="left"><s:textfield name="user.usLastName" id="usLastName" size="28" maxlength="50" theme="simple" tabindex="5" onkeyup="adminUserNameValidation(this)" /></td>
												</s:if>
											</tr>
											<s:if test="#usersType=='Student' || #usersType=='Employee'">
												<tr>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Father Name</td>
													<td align="left"><s:textfield name="user.usFatherName" id="usFatherName" size="28" maxlength="50" theme="simple" tabindex="5" onkeyup="adminUserNameValidation(this)" /></td>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Gender<span style="color: red;">*</span></td>
													<td align="left" nowrap="nowrap"><s:select name="user.usSex" headerKey="" id="usSex" headerValue="Select" list="#{'Male':'Male', 'Female':'Female', 'BiGender':'BiGender'}"
															theme="simple" style="width: 180px;">
														</s:select></td>
												</tr>
											</s:if>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;User Id<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="user.usUserID" id="usUserID" size="28" maxlength="50" theme="simple" tabindex="10" validate="userid" msg="User Id is required" fieldname="User Id"
														readonly="true" value="Auto-Generated" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp; Email Id<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="user.usEmail" id="usEmail" size="28" maxlength="50" theme="simple" tabindex="15" onkeyup="adminNameEmailValidation(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Mobile Number<span style="color: red;">*</span>
												</td>
												<td align="left"><s:textfield name="user.usMobileNo" id="usMobileNo" size="28" maxlength="10" theme="simple" tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Alternate Mobile Number</td>
												<td align="left"><s:textfield name="user.usAlternateMobileNo" id="usAlternateMobileNo" size="28" maxlength="10" theme="simple" tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Phone Number</td>
												<td align="left"><s:textfield name="user.usPhoneNo" id="usPhoneNo" size="28" maxlength="20" theme="simple" tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Fax Number</td>
												<td align="left"><s:textfield name="user.usFaxNo" id="usFaxNo" size="28" maxlength="20" theme="simple" tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Date of Join<span style="color: red;">*</span>
												</td>
												<td nowrap align="left"><input type="text" readonly id="usDateOfJoin" name="user.usDateOfJoin" autocomplete="false" value="${user.usDateOfJoin}" size="10" validate="date"
													msg="Date of Join is required" fieldname="Date Of Join" onchange="futureDateValidation(this);" title="Date of Join" tabindex="20"
													style="width: 180px; background: url('js/datepicker/calendar.gif') no-repeat scroll right center #FFFFFF" /> <script>
														new Zapatec.Calendar.setup(
																{
																	inputField : 'usDateOfJoin',
																	ifFormat : '%m-%d-%Y',
																	showsTime : false,
																	button : 'usDateOfJoin',
																	singleClick : true,
																	step : 1,
																	timeFormat : '12'
																});
													</script></td>
												<s:if test="%{#user.profileBy!='Student'}">
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Date of Terminate</td>
													<td nowrap align="left"><input type="text" readonly id="usDateOfTerminate" name="user.usDateOfTerminate" autocomplete="false" value="${user.usDateOfTerminate}" size="10"
														validate="date" msg="Date of Terminate is required" fieldname="Date Of Terminate" onchange="futureDateValidation(this);" title="Date of Terminate" tabindex="20"
														style="width: 180px; background: url('js/datepicker/calendar.gif') no-repeat scroll right center #FFFFFF" /> <script>
															new Zapatec.Calendar.setup(
																	{
																		inputField : 'usDateOfTerminate',
																		ifFormat : '%m-%d-%Y',
																		showsTime : false,
																		button : 'usDateOfTerminate',
																		singleClick : true,
																		step : 1,
																		timeFormat : '12'
																	});
														</script></td>
												</s:if>
											</tr>
											<s:if test="#usersType=='Student' || #usersType=='Employee'">
												<tr>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp; <span class="lblstyle">&nbsp;Date of Birth</span> <span style="color: red;">*</span>&nbsp;
													</td>
													<td nowrap align="left"><input type="text" readonly class="smalltextbox" id="usDob" name="user.usDob" autocomplete="false" value="${user.usDob}" size="10" validate="date"
														msg="Date of Birth is required" fieldname="Date Of Birth" onchange="futureDateValidation(this);" title="Date of Birth" tabindex="20"
														style="width: 180px; background: url('js/datepicker/calendar.gif') no-repeat scroll right center #FFFFFF" /> <script>
															new Zapatec.Calendar.setup(
																	{
																		inputField : 'usDob',
																		ifFormat : '%m-%d-%Y',
																		showsTime : false,
																		button : 'usDob',
																		singleClick : true,
																		step : 1,
																		timeFormat : '12'
																	});
														</script>&nbsp;&nbsp;</td>
												<tr>
											</s:if>
											<s:if test="#usersType=='Student'">
												<tr>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Exam Board<span style="color: red;">*</span>
													</td>
													<td align="left"><select name="user.usBoardName" id="usBoardName" style="width: 180px;">
															<option value="State_Board">State_Board</option>
															<!--  option value="CBSE">CBSE</option -->
													</select></td>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;School Name<span style="color: red;">*</span>
													</td>
													<td align="left"><s:select name="user.usSchoolId" id="usSchoolId" style="width: 180px;" headerKey="" headerValue="Select" list="schoolList" listKey="scSchoolId"
															listValue="scSchoolName" theme="simple" value="%{user.usSchoolId}"></s:select></td>
												</tr>
												<tr>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Subjects<span style="color: red;">*</span></td>
													<td align="left"><s:select name="user.usGroupName" id="usGroupName" style="width: 180px;" headerKey="" headerValue="Select"
															list="#{'Physics,Chemistry,Maths,Botany,Zoology':'Physics,Chemistry,Maths,Botany,Zoology','Physics,Chemistry,Maths,Computer':'Physics,Chemistry,Maths,Computer','Physics,Chemistry,Botany,Zoology':'Physics,Chemistry,Botany,Zoology' ,'Physics,Chemistry,Botany,Zoology,Computer':'Physics,Chemistry,Botany,Zoology,Computer','Physics,Chemistry,Maths,Economics':'Physics,Chemistry,Maths,Economics','Physics,Chemistry,Business Maths,Botany,Zoology':'Physics,Chemistry,Business Maths,Botany,Zoology'}"
															theme="simple" value="%{user.usGroupName}"></s:select></td>
												</tr>
											</s:if>
											<s:if test="#usersType=='Employee'">
												<tr>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Identity Proof</td>
													<td align="left"><s:select name="user.usProofType" id="usProofType" style="width: 180px;" headerKey="" headerValue="Select"
															list="#{'StudentIdCard' : 'Student Id Card', 'PAN Card':' PANCard','Driving License':'Driving License', 'Passport' : 'Passport'}" theme="simple" value="%{user.usProofType}"></s:select></td>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Proof Number</td>
													<td align="left"><s:textfield name="user.usProofId" id="usProofId" size="28" maxlength="50" theme="simple" tabindex="5" validate="not_empty" msg="Proof Number is required"
															fieldname="Proof Number" /></td>
												</tr>
												<tr>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Qualification</td>
													<td align="left"><s:textfield name="user.usQualification" id="usQualification" size="28" maxlength="50" theme="simple" tabindex="5" onkeyup="adminUserNameValidation(this)" /></td>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Experience</td>
													<td align="left"><s:textfield name="user.usExperience" id="usExperience" size="28" maxlength="50" theme="simple" tabindex="5" onkeyup="checkIsNumberAndLength(this);" /></td>
												</tr>
											</s:if>
											<s:if test="#usersType=='Franchisee'">
												<tr>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Franchisee Area<span style="color: red;">*</span>
													</td>
													<td align="left"><s:select name="user.usFrancheesArea" id="usFrancheesArea" style="width: 180px;" headerKey="" headerValue="Select" list="cityList" listKey="label" listValue="value"
															theme="simple"></s:select></td>
												</tr>
											</s:if>
											<s:elseif test="#usersType=='Employee'">
												<tr>
													<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Company Name</td>
													<td align="left"><s:textfield name="user.usCompanyName" id="usCompanyName" size="28" maxlength="50" theme="simple" tabindex="5" /></td>
												</tr>
											</s:elseif>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Address</td>
												<td align="left"><s:textfield name="user.usAddress1" id="usAddress1" size="28" maxlength="100" theme="simple" tabindex="5" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td align="left"><s:textfield name="user.usAddress2" id="usAddress2" size="28" maxlength="100" theme="simple" tabindex="5" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Landmark</td>
												<td align="left"><s:textfield name="user.usLandMark" id="usLandMark" size="28" maxlength="100" theme="simple" tabindex="5" /></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;City<span style="color: red;">*</span></td>
												<td align="left"><s:select name="user.usCity" id="usCity" style="width: 180px;" headerKey="" headerValue="Select" list="cityList" listKey="label" listValue="value" theme="simple"></s:select></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;State<span style="color: red;">*</span></td>
												<td align="left"><s:select name="user.usState" id="usState" style="width: 180px;" list="stateList" listKey="label" listValue="value" theme="simple"></s:select></td>
											</tr>
											<tr>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Country<span style="color: red;">*</span></td>
												<td align="left"><select name="user.usCountry" id="usCountry" style="width: 180px;">
														<option value="India">India</option>
												</select></td>
												<td align="left" class="lblstyle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;Pincode</td>
												<td align="left"><s:textfield name="user.usPinCode" id="usPinCode" size="28" maxlength="20" theme="simple" tabindex="5" onkeyup="checkIsNumberAndLength(this);" validate="not_empty"
														msg="Pincode is required" fieldname="Pincode" /></td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4"><s:include value="i_edutel_attachments.jsp">
														<s:param name="attachReadOnly">false</s:param>
														<s:param name="attachmentBannerName">${user.usUsersType} Photo/Documents - (Please Upload Passport size photo, for better site experience.)</s:param>
														<s:param name="attachmentName">Photo Image </s:param>
														<s:param name="attachmentDynamicName"></s:param>
													</s:include></td>
											</tr>
											<tr>
												<td height="20px;" colspan="4" nowrap align="right"></td>
											</tr>
											<tr>
												<td align="center" colspan="4"><input type="button" class="btnstyle" style="width: 130px;" name="saveUser" value="Save ${user.usUsersType} User" href="javascript:void(0);"
													onclick="onsubmitCalled();" /> <s:if test="%{#user.profileBy!='Student'}">
														&nbsp;&nbsp; <input type="button" class="btnstyle" value="Reset" onclick="onresetCalled()" />&nbsp;&nbsp;<input type="button" class="btnstyle" value="Cancel"
															onclick="onCancelCalled('dashBoardEmployee.do')" />
													</s:if>&nbsp;&nbsp;</td>
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
