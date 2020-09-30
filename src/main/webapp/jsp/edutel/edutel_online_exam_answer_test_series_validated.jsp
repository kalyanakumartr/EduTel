<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	function onsubmitCalled() {

		var fileUpload = $("#fileUpload" + fileCnt).val();
		var answerVia = $("#oeTestSeriesAnswerVIA").val();

		if (allTrim(answerVia) == "Online") {
			if (allTrim(fileUpload) == undefined || allTrim(fileUpload) == null
					|| allTrim(fileUpload) == "") {
				alert("Please Upload an Validated Answer File.");
				return false;
			}

		}

		 document.forms["onlineTestSeriesValidatedForm"].submit(); 
	}

	function onCancelCalled() {
		window.history.back();
	}
	function downloadTestSeries(questionUrl) {
		window.open(questionUrl, 'open');
	}

	function checkAnswered() {
		if ("${isAnswered}" == "true") {
			window.opener.refreshStudentTestSeriesTable();
			setTimeout('window.opener.refreshEdutelTestSeriesTable()', 2000);
		}
	}
</script>
<style>
.spanLabel {
	font-size: 13px;
	font-weight: bold;
	vertical-align: middle;
	color: green;
}

.spanValue {
	font-size: 13px;
	font-weight: normal;
	vertical-align: middle;
	color: #336699;
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
	color: #006666;
	font-family: Arial, Trebuchet MS, Tahoma;
	font-size: 13px;
	font-weight: bold;
	padding-top: 5px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px 5px 0px 0px;
}

div.img {
	margin: 5px;
	padding: 5px;
	border: 2px solid #ececec;
	height: 150px;
	width: 135px;
	float: left;
	text-align: center;
}

div.img img {
	display: inline;
	margin: 5px;
	border: 2px solid #ececec;
}

div.img a:hover img {
	border: 2px solid gray;
}

div.desc {
	text-align: center;
	font-weight: bold;
	width: 125px;
	margin: 1px;
	overflow: hidden;
	font-family: Tahoma;
	font-size: 11px;
	max-width: 125px;
	height: 40px;
}

.onlineTestSeriesExam {
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
</style>
<body class="sub_body2" onload="checkAnswered()">
	<div id="wrap" class="boxed">
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Test Series Validated Answer Upload Panel</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="onlineTestSeriesValidatedForm"
					id="onlineTestSeriesValidatedForm"
					action="uploadValidatedAnswer.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="onlineTestSeriesExamValidatedDiv"
										style="width: 100%; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td align="left" colspan="4"><div align="left"
														class="activity">&nbsp;&nbsp;Test Series Validated
														Answer</div></td>
											</tr>
											<tr>
												<td valign="top" align="center" width="100%" colspan="4"
													height="30px;"
													style="border-style: solid; border-bottom: thin double #ececec;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:if
														test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan"
																style="color: red;"><s:property escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr
												style="border-style: solid; border-right: thin double #ececec;">
												<td align="left" colspan="4" width="500px;">
													<table width="500px;">
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Test Series Exam Name :</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue"
																style="font-weight: bold; font-size: 13px;">
																	${onlineTestSeriesExamAnswer.onlineExamQuestion.onlineExam.oeExamName}
															</span></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Subject :</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue">${onlineTestSeriesExamAnswer.onlineExamQuestion.onlineExam.oeSubject}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Chapter :</span>&nbsp;&nbsp;&nbsp;<span
																class="spanValue" id="chapSpan">${onlineTestSeriesExamAnswer.onlineExamQuestion.onlineExam.oeChapter}</span>
																<script>
																	var chapSpan = '${onlineTestSeriesExamAnswer.onlineExamQuestion.onlineExam.oeChapter}';
																	if (chapSpan == '') {
																		$(
																				"#chapSpan")
																				.text(
																						"All");
																	}
																</script></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Exam Date : </span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue">${onlineTestSeriesExamAnswer.onlineExamQuestion.onlineExam.oeExamDate}</span></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Duration :</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue">
																	${onlineTestSeriesExamAnswer.onlineExamQuestion.onlineExam.oeExamDuration}&nbsp;&nbsp;&nbsp;Hours</span></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Student Uploaded By :</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue">
																	${onlineTestSeriesExamAnswer.createdBy}</span></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Student Uploaded Date</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue"> <s:date
																		name="onlineTestSeriesExamAnswer.createdDate"
																		format="dd/MM/yyyy hh:mm a" /></span></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Student Uploaded Filename</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue" id="uploadFileNameSpan">
																	${onlineTestSeriesExamAnswer.uploadFileName}</span> <script>
																		var uploadFileNameSpan = '${onlineTestSeriesExamAnswer.uploadFileName}';
																		if (uploadFileNameSpan == '') {
																			$(
																					"#uploadFileNameSpan")
																					.text(
																							"Not Applicable");
																		}
																	</script></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Validated Answer Via :</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue"> <s:if
																		test="%{isAnswered=='false'}">
																		<select name="oeTestSeriesAnswerVIA"
																			id="oeTestSeriesAnswerVIA" style="width: 130px;"
																			onchange="enableAnswerVIA();">
																			<option value="Online">Online</option>
																			<option value="CourierOrPost">Courier Or
																				Post</option>
																		</select>
																	</s:if> <s:else>
																		<span class="spanValue"
																			style="font-weight: bold; font-size: 13px; color: red;">${oeTestSeriesAnswerVIA}</span>
																	</s:else>
															</span> <script type="text/javascript">
																function enableAnswerVIA() {
																	var oeTestSeriesAnswerVIA = $(
																			"#oeTestSeriesAnswerVIA")
																			.val();
																	if (oeTestSeriesAnswerVIA == 'Online') {
																		$(
																				"#uploadAnswerRow")
																				.show();
																		$(
																				"#uploadAnswerRow1")
																				.hide();
																	} else {
																		$(
																				"#uploadAnswerRow")
																				.hide();
																		$(
																				"#uploadAnswerRow1")
																				.show();
																	}
																}
															</script></td>
														</tr>

														<s:if test="%{isAnswered=='false'}">
															<tr id="uploadAnswerRow">
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 100%"
																	colspan="2"><s:include
																		value="i_edutel_attachments.jsp">
																		<s:param name="attachReadOnly">false</s:param>
																		<s:param name="attachmentBannerName">Upload Your Answer (Max 5 MB file size)</s:param>
																		<s:param name="attachmentName">Answer&nbsp;&nbsp;&nbsp;</s:param>
																		<s:param name="attachmentDynamicName"></s:param>
																	</s:include></td>
															</tr>
														</s:if>
														<s:else>
															<tr height="25px;">
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																	class="spanLabel">Validate & Replied By :</span></td>
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																	class="spanValue">
																		${onlineTestSeriesExamAnswer.modifiedBy}</span></td>
															</tr>
															<tr height="25px;">
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																	class="spanLabel">Validate & Replied Date</span></td>
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																	class="spanValue"> <s:date
																			name="onlineTestSeriesExamAnswer.modifiedDate"
																			format="dd/MM/yyyy hh:mm a" /></span></td>
															</tr>
															<tr height="25px;">
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																	class="spanLabel">Uploaded Filename</span></td>
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																	class="spanValue" id="uploadFileNameSpan">
																		${onlineTestSeriesExamAnswer.uploadFileName}</span> <script>
																			var uploadFileNameSpan = '${onlineTestSeriesExamAnswer.uploadFileName}';
																			if (uploadFileNameSpan == '') {
																				$(
																						"#uploadFileNameSpan")
																						.text(
																								"Not Applicable");
																			}
																		</script></td>
															</tr>
														</s:else>
														<tr id="uploadAnswerRow1"
															style="display: none; height: 90px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 100%"
																colspan="2"><div style="padding: 10px;">
																	<span
																		style="font-size: 13px; color: red; font-weight: bold;">Information
																		:</span><span style="font-size: 13px; color: red;"><br>
																		You are selected to send your Validated Answers for
																		the Test Series through Courier or By Post.<br> </span>
																</div> <span></span></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="4"><s:if
														test="%{isAnswered=='false'}">
														<input type="button" class="btnstyle" name="Answer"
															value="Submit Validated Answer"
															onclick="onsubmitCalled();" style="width: 150px;" /> &nbsp;&nbsp;</s:if>
													<input type="button" class="btnstyle" value="Close"
													onclick="self.close();" /></td>
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
					<br>
					<br>
					<br>
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>
