<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	$(document).ready(function() {
		onclearCalled();
	});
	function onsubmitCalled() {

		var practiseExamName = $("#oePractiseExamName").val();
		var subject = $("#oeSubject").val();
		var noOfQuestions = $("#oeNoOfQuestions").val();

		if (allTrim(practiseExamName) == "") {
			alert("Please Type practise Exam Name");
			return false;
		}
		if (allTrim(subject) == "") {
			alert("Please select Subject");
			return false;
		}
		if (allTrim(noOfQuestions) == "") {
			alert("Please select No Of Questions");
			return false;
		}
		document.forms["onlinePractiseExamGenerateForm"].submit();
	}
	function onclearCalled() {
		$("#oePractiseExamName").val("");
		$("#oeSubject").val("Physics");
		$("#oeNoOfQuestions").val("10");
	}
	function onresetCalled() {
		document.forms["onlinePractiseExamGenerateForm"].reset();
	}
</script>
<style>
.onlinePractiseExam {
	background: no-repeat scroll right center #FFFFFF;
	width: 78%;
	border: 1px solid #FFFFFF;
	font-family: Tahoma, verdana, Arial, helvetica, sans-serif;
	font-size: 13px;
	font-weight: normal;
	text-decoration: none;
	line-height: 23px;
	height: 27px;
	border: 1px solid #DDD;
	box-shadow: 0 0 5px #888;
	color: #666;
	float: left;
	padding: 5px 27px 5px 10px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

.activity {
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow: inset 0px 1px 0px 0px #ffffff;
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f9f9f9
		), color-stop(1, #e9e9e9));
	background: -moz-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -webkit-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -o-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -ms-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9',
		endColorstr='#e9e9e9', GradientType=0);
	background-color: #f9f9f9;
	border-bottom: thin;
	border-color: #dcdcdc;
	vertical-align: middle;
	width: 100%;
	height: 28px;
	display: inline-block;
	cursor: pointer;
	color: #007dc1;
	font-family: Tahoma;
	font-size: 13px;
	font-weight: bold;
	padding-top: 5px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px 5px 0px 0px;
}
</style>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online Exam Panel</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="onlinePractiseExamGenerateForm"
					id="onlinePractiseExamGenerateForm"
					action="createOnlinePractiseExam.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="onlinePractiseExamGenerateDiv"
										style="width: 500px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td align="left" colspan="4"><div align="left"
														class="activity">&nbsp;&nbsp;MCQ Practice
														Exam&nbsp;&nbsp;</div></td>
											</tr>
											<tr>
												<td align="left" class="usertext">&nbsp;&nbsp;&nbsp;</td>
												<td valign="top" align="left" width="100%" colspan="3"
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
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="40%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Practice
														Exam Name</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="40%"><s:textfield
														name="onlinePractiseExam.oePractiseExamName"
														id="oePractiseExamName" style="width: 185px;"
														theme="simple" /></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="40%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Subject</span><span
													style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="40%"><s:select
														name="onlinePractiseExam.oeSubject" id="oeSubject"
														style="width: 185px;" list="subjectList" listKey="label"
														listValue="value" theme="simple" /></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="40%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;Chapter</span><span
													style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="40%"><s:select
														name="onlinePractiseExam.oeChapter" id="oeChapter"
														style="width: 185px;" list="chapterList" listKey="label"
														listValue="value" theme="simple" /></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
												<td align="left" width="40%"><span class="lblstyle">&nbsp;&nbsp;&nbsp;No
														Of Questions</span><span style="color: red;">*</span>&nbsp;</td>
												<td align="left" width="40%"><select
													name="onlinePractiseExam.oeNoOfQuestions"
													id="oeNoOfQuestions" style="width: 185px;">
														<option value="10">10</option>
														<option value="25">25</option>
														<option value="50">50</option>
														<option value="100">100</option>
												</select></td>
												<td align="left" width="10%">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="4"><input type="button"
													class="btnstyle" name="createOnlinePractiseExam"
													value="Create Practise Exam" href="javascript:void(0);"
													onclick="onsubmitCalled();" style="width: 150px;" />
													&nbsp;&nbsp; <input type="button" class="btnstyle"
													value="Reset" onclick="onresetCalled()" /></td>
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
