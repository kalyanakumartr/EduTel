<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	$(document)
			.ready(
					function() {
						$("#notFoundRow").hide();
						if ("${onlineExamQuestionList}" == "[]") {
							$("#uploadAnswerRow").hide();
							$("#uploadAnswerRow1").hide();
							$("#buttonRow").hide();
							$("#questionRow").hide();
							$("#notFoundRow").show();
						} else {

							if (("${onlineExam.onlineAssessment}" == "true" || "${onlineExam.onlineAssessment}" == true)
									&& ("${onlineExam.timeReached}" == "true" || "${onlineExam.timeReached}" == true)) {

								var oeTestSeriesAnswerVIA = $(
										"#oeTestSeriesAnswerVIA").val();
								if (oeTestSeriesAnswerVIA == 'Online') {
									$("#uploadAnswerRow").show();
									$("#uploadAnswerRow1").hide();
								} else {
									$("#uploadAnswerRow").hide();
									$("#uploadAnswerRow1").show();
								}

							} else {

								$("#uploadAnswerRow").hide();
								$("#uploadAnswerRow1").hide();
								$("#buttonRow").hide();

							}
						}
					});
	function onsubmitCalled() {
		var fileUpload = $("#fileUpload" + fileCnt).val();
		var answerVia = $("#oeTestSeriesAnswerVIA").val();

		if (allTrim(answerVia) == "Online") {
			if (allTrim(fileUpload) == undefined || allTrim(fileUpload) == null
					|| allTrim(fileUpload) == "") {
				alert("Please Upload an Answer File.");
				return false;
			}

		}
		document.forms["onlineTestSeriesExamViewForm"].submit();
	}

	function onCancelCalled(url) {
		document.forms["onlineTestSeriesExamViewForm"].action = url;
		document.forms["onlineTestSeriesExamViewForm"].submit();
	}
	function downloadTestSeries(questionUrl) {
		if (("${onlineExam.onlineAssessment}" == "true" || "${onlineExam.onlineAssessment}" == true)
				&& ("${onlineExam.timeReached}" == "true" || "${onlineExam.timeReached}" == true)) {
			window.open(questionUrl, 'open');
		} else {
			var message = "<div style=\"padding:20px; background-color:#FFFFCC;\"><span style=\"font-size:12px; color:red; font-weight:bold; white-space: pre-line;\"><p>Exam will Start on : "
					+ "${onlineExam.oeExamDate}"
					+ "</p></span><br><div align=\"center\" ><input type=\"button\" value=\"Close\" onclick=\"onCancelCalled('dashBoardStudent.do');\" style=\"font-size:11px;font-weight:bold;\"></div></div>";

			$("#alertDialog").attr("title", "Please Wait");
			$("#alertDialog").attr("style",
					"width:400px;max-width:400px;max-height:100px;");
			$("#alertDialog").html(message);
			$("#alertDialog").dialog();
		}
	}
	function onCancelCalled(url) {
		window.location.href = url;
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

.downloadBtn {
	-moz-box-shadow: inset 0px 1px 0px 0px #caefab;
	-webkit-box-shadow: inset 0px 1px 0px 0px #caefab;
	box-shadow: inset 0px 1px 0px 0px #caefab;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #77d42a
		), color-stop(1, #5cb811));
	background: -moz-linear-gradient(center top, #77d42a 5%, #5cb811 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#77d42a',
		endColorstr='#5cb811');
	background-color: #77d42a;
	-webkit-border-top-left-radius: 7px;
	-moz-border-radius-topleft: 7px;
	border-top-left-radius: 7px;
	-webkit-border-top-right-radius: 7px;
	-moz-border-radius-topright: 7px;
	border-top-right-radius: 7px;
	-webkit-border-bottom-right-radius: 7px;
	-moz-border-radius-bottomright: 7px;
	border-bottom-right-radius: 7px;
	-webkit-border-bottom-left-radius: 7px;
	-moz-border-radius-bottomleft: 7px;
	border-bottom-left-radius: 7px;
	text-indent: 0;
	border: 1px solid #268a16;
	display: inline-block;
	color: #254a04;
	font-family: Trebuchet MS;
	font-size: 15px;
	font-weight: bold;
	font-style: normal;
	height: 25px;
	line-height: 25px;
	width: 110px;
	text-decoration: none;
	text-align: center;
	text-shadow: 1px 1px 0px #aade7c;
}

.downloadBtn:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #5cb811
		), color-stop(1, #77d42a));
	background: -moz-linear-gradient(center top, #5cb811 5%, #77d42a 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#5cb811',
		endColorstr='#77d42a');
	background-color: #5cb811;
}

.downloadBtn:active {
	position: relative;
	top: 1px;
}
</style>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online TestSeries Exam Panel</p>
			</div>
			<div id="alertDialog"></div>
			<div id="signupwrapper" align="center">
				<s:form name="onlineTestSeriesExamViewForm"
					id="onlineTestSeriesExamViewForm"
					action="submitOnlineTestSeriesExamAnswer.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div>
						<table>
							<tr>
								<td>
									<div id="onlineTestSeriesExamViewDiv"
										style="width: 650px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td align="left" colspan="4"><div align="left"
														class="activity">&nbsp;&nbsp;Test Series&nbsp;&nbsp;</div></td>
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
											<tr id="questionRow"
												style="border-style: solid; border-right: thin double #ececec;">
												<td align="center" valign="top"
													style="border-style: solid; border-right: thin double #ececec; border-bottom: thin double #ececec; width: 20%;"><s:iterator
														value="onlineExamQuestion.oeAnswers">
														<div class="img" align="center">
															<img src="images/png/${uploadFileScreenShot}"
																alt="${uploadFileScreenShot}" width="100" height="80"
																style="cursor: pointer;">
															<div class="desc">
																<a href="#" class="downloadBtn"
																	onclick="downloadTestSeries('${downloadFileUrl}')">Download</a>
															</div>
														</div>
													</s:iterator></td>
												<td align="left" colspan="3" width="500px;">
													<table width="500px;">
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Test Series Exam Name :</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue"
																style="font-weight: bold; font-size: 13px;">
																	${onlineExamQuestion.onlineExam.oeExamName} </span> <s:hidden
																	name="onlineExamQuestion.onlineExam.oeExamId"
																	id="oeExamId" /></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Subject :</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue">${onlineExamQuestion.onlineExam.oeSubject}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Chapter :</span>&nbsp;&nbsp;&nbsp;<span
																class="spanValue" id="chapSpan">${onlineExamQuestion.onlineExam.oeChapter}</span>
																<script>
																	var chapSpan = '${onlineExamQuestion.onlineExam.oeChapter}';
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
																class="spanValue">${onlineExamQuestion.onlineExam.oeExamDate}</span></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Duration :</span></td>
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																class="spanValue">
																	${onlineExamQuestion.onlineExam.oeExamDuration}&nbsp;&nbsp;&nbsp;Hours</span></td>
														</tr>
														<tr height="25px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																class="spanLabel">Answer Via :</span></td>
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
															<s:set name="optionDisplay">Submitted</s:set>
															<s:if test="%{oeTestSeriesAnswerVIA=='Online'}">
																<s:set name="optionDisplay">Uploaded</s:set>
															</s:if>
															<tr height="25px;">
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																	class="spanLabel">${optionDisplay} By :</span></td>
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																	class="spanValue">
																		${onlineTestSeriesExamAnswer.createdBy}</span></td>
															</tr>
															<tr height="25px;">
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																	class="spanLabel">${optionDisplay} Date</span></td>
																<td
																	style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																	class="spanValue"> <s:date
																			name="onlineTestSeriesExamAnswer.createdDate"
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
															<s:if
																test="%{onlineTestSeriesExamAnswerValidated.oeValidatedAnswer==false}">
																<tr height="25px;">
																	<td
																		style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																		class="spanLabel">Validated Answer File :</span></td>
																	<td
																		style="border-style: solid; border-bottom: thin double #ececec; width: 60%"><span
																		class="spanValue"
																		style="color: red; font-weight: bold;"> Under
																			Validation Process</span></td>
																</tr>
															</s:if>
															<s:else>
																<tr height="40px;">
																	<td
																		style="border-style: solid; border-bottom: thin double #ececec; width: 40%">&nbsp;&nbsp;&nbsp;<span
																		class="spanLabel">Validated Answer File :</span></td>
																	<td
																		style="border-style: solid; border-bottom: thin double #ececec; width: 60%"
																		valign="middle">
																		<table>
																			<tr>
																				<td width="20%"><img
																					src="images/png/${onlineTestSeriesExamAnswerValidated.uploadFileScreenShot}"
																					alt="${onlineTestSeriesExamAnswerValidated.uploadFileScreenShot}"
																					width="50" height="50"
																					style="cursor: pointer; border: 1px;"
																					onclick="downloadTestSeries('${downloadValidatedAnswerUrl}')"></td>
																				<td width="80%" valign="middle">&nbsp;<span
																					class="spanValue"
																					style="cursor: pointer; font-weight: bold; font-size: 13px; color: red;"
																					onclick="downloadTestSeries('${downloadValidatedAnswerUrl}')">
																						Download
																						&nbsp;${onlineTestSeriesExamAnswerValidated.uploadFileName}</span></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</s:else>
														</s:else>
														<tr id="uploadAnswerRow1"
															style="display: none; height: 90px;">
															<td
																style="border-style: solid; border-bottom: thin double #ececec; width: 100%"
																colspan="2"><div style="padding: 10px;">
																	<span
																		style="font-size: 13px; color: red; font-weight: bold;">Information
																		:</span><span style="font-size: 13px; color: red;"><br>
																		You are selected to send your Answers for the Test
																		Series through Courier or By Post.Please send your
																		Answer sheet, within a Week Duration.<br> <br>Note:
																		You have to Click <span
																		style="font-size: 13px; color: green; font-weight: bold;">'Submit
																			Your Answer'</span> to receive your results Online. </span>
																</div> <span></span></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="notFoundRow">
												<td align="center" colspan="4">
													<table>
														<tr>
															<td align="center"><img
																src="images/png/warning_48.png" /> &nbsp;&nbsp;&nbsp; <span
																style="color: #336699; font-size: 24px;">Questions
																	Not Found</span></td>
														</tr>
														<tr>
															<td height="15px" align="left" colspan="2">&nbsp;&nbsp;&nbsp;</td>
														</tr>
														<tr>
															<td align="center"><input type="button"
																class="btnstyle" value="Cancel"
																onclick="onCancelCalled('dashBoardStudent.do')" /></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>

											<tr>
												<td align="center" colspan="4" id="buttonRow"><s:if
														test="%{isAnswered=='false'}">
														<input type="button" class="btnstyle" name="Answer"
															value="Submit Your Answer" onclick="onsubmitCalled();"
															style="width: 150px;" /> &nbsp;&nbsp;</s:if> <input
													type="button" class="btnstyle" value="Cancel"
													onclick="onCancelCalled('dashBoardStudent.do')" /></td>
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
