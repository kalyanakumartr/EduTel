<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<s:set name="noOfValidatedAnswers"
	value="oeTestSeriesValidatedAnswersNotificationList.size"></s:set>
<script type="text/javascript" src="js/jquery.min.js"></script>

<script type="text/javascript" src="amcharts/amcharts.js"></script>
<script type="text/javascript" src="amcharts/pie.js"></script>
<script type="text/javascript" src="amcharts/themes/light.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.thumbs.js"></script>





<link href="css/bootbox/components.css" rel="stylesheet" type="text/css" />
<link href="css/bootbox/plugins.css" rel="stylesheet" type="text/css" />
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/bootbox/profile.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="js/bootstrap-fileinput/bootstrap-fileinput.js"></script>

<%-- <script src="js/bootstrap/js/jquery.js" type="text/javascript"></script> --%>
<script src="js/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style>
.zzBoxes.custom .zzBoxes_inner {
	border-color: #FF4F4F;
}

.zzBoxes.custom .zzBoxes_title {
	color: #FF4F4F;
}
</style>
<style>
.chartDiv {
	width: 400px;
	height: 200px;
	font-size: 11px;
}

.roundedCornerDiv {
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	border-radius: 0px;
	border: 1px solid #DDD;
	box-shadow: 0 0 0px #888;
}

.spanStyle {
	font-family: Arial, Trebuchet MS, Tahoma;
	font-size: 11px;
	vertical-align: middle;
	white-space: pre-line;
	color: #336699;
}

.examDiv {
	padding: 2px 2px 2px 2px;
	vertical-align: middle;
	height: 70px;
	border-bottom: thin;
	border-top: thin;
	border-bottom-color: #ececec;
	border-bottom-style: solid;
	border-top-color: #ececec;
	border-top-style: solid;
}

.examDiv:HOVER {
	background-color: #4B93EB;
	padding: 2px 2px 2px 2px;
	vertical-align: middle;
	height: 70px;
	border-bottom: thin;
	border-top: thin;
	border-bottom-color: #ececec;
	border-bottom-style: solid;
	border-top-color: #ececec;
	border-top-style: solid;
	color: #000000;
}

.examPracticeDiv {
	padding: 2px 2px 2px 2px;
	vertical-align: middle;
	height: 50px;
	border-bottom: thin;
	border-top: thin;
	border-bottom-color: #ececec;
	border-bottom-style: solid;
	border-top-color: #ececec;
	border-top-style: solid;
}

.examPracticeDiv:HOVER {
	background-color: #4B93EB;
	padding: 2px 2px 2px 2px;
	vertical-align: middle;
	height: 50px;
	border-bottom: thin;
	border-top: thin;
	border-bottom-color: #ececec;
	border-bottom-style: solid;
	border-top-color: #ececec;
	border-top-style: solid;
}

.examResultDiv {
	padding: 2px 2px 2px 2px;
	vertical-align: middle;
	height: 100px;
	border-bottom: thin;
	border-top: thin;
	border-bottom-color: #ececec;
	border-bottom-style: solid;
	border-top-color: #ececec;
	border-top-style: solid;
}

.examResultDiv:HOVER {
	background-color: #4B93EB;
	padding: 2px 2px 2px 2px;
	vertical-align: middle;
	height: 100px;
	border-bottom: thin;
	border-top: thin;
	border-bottom-color: #ececec;
	border-bottom-style: solid;
	border-top-color: #ececec;
	border-top-style: solid;
}

.divHeaderBlue {
	background-color: #4390DF;
	vertical-align: middle;
	width: 100%;
	height: 35px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	padding-top: 7px;
}

.divHeaderMediumBlue {
	background-color: #0000ff;
	vertical-align: middle;
	width: 100%;
	height: 35px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	padding-top: 7px;
}

.divHeaderDarkBlue {
	background-color: #002179;
	vertical-align: middle;
	width: 100%;
	height: 35px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	padding-top: 7px;
}

.divHeaderGreen {
	background-color: #007900;
	vertical-align: middle;
	width: 100%;
	height: 35px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	padding-top: 7px;
}

.divHeaderRed {
	background-color: #f0371e;
	vertical-align: middle;
	width: 100%;
	height: 35px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	padding-top: 7px;
}

.divHeaderGray {
	background-color: #3f7694;
	vertical-align: middle;
	width: 100%;
	height: 35px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	padding-top: 7px;
}

.divBody {
	width: 100%;
	height: 250px;
	overflow-y: auto;
	padding: 5px;
	border: 2px solid #ececec;
	vertical-align: middle;
}

.divMainPanel {
	width: 100%;
	height: 240px;
}

.imgIconTD {
	width: 15%;
	height: 40px;
	vertical-align: middle;
}

.divContentStrip {
	width: 85%;
	height: 40px;
	vertical-align: middle;
	padding: 5px;
	text-transform: capitalize;
}

img:HOVER {
	filter: none;
	-webkit-filter: grayscale(0%);
}

.examTable tr:nth-child(even) {
	background: #eeeeee;
	width: 100%;
}

.examTable tr:nth-child(odd) {
	background: #fff;
	width: 100%;
}

.examPracticeTable tr:nth-child(even) {
	background: #eeeeee
}

.examPracticeTable tr:nth-child(odd) {
	background: #fff
}

.examResultTable tr:nth-child(even) {
	background: #eeeeee
}

.examResultTable tr:nth-child(odd) {
	background: #fff
}

.spanLblRed {
	font-size: 11px;
	font-weight: bold;
	vertical-align: middle;
	color: red;
}

.spanLblRed:hover {
	font-size: 12px;
	font-weight: bold;
	vertical-align: middle;
	color: #red;
}

.spanLblBold {
	font-size: 11px;
	font-weight: bold;
	vertical-align: middle;
	white-space: pre-line;
	color: #336699;
}

.spanLblBold:hover {
	font-size: 12px;
	font-weight: bold;
	vertical-align: middle;
	color: #red;
}

.spanLblNormal {
	font-size: 11px;
	font-weight: normal;
	vertical-align: middle;
	white-space: pre-line;
	color: #336699;
}

.spanLblNormal:hover {
	font-size: 12px;
	font-weight: normal;
	vertical-align: middle;
	color: #336699;
}

.modal .modal-header {
	background-color: #4390df;
	color: white;
}

input[type=text] {
	width: 100%;
	height: 34px;
	position: relative;
	left: 0.0;
	outline: none;
	background-color: white;
	border: 1px solid #e5e5e5;
	border-radius: 0;
	box-shadow: none;
	color: #333333;
	font-size: 14px;
	font-weight: normal;
}

.modal-dialog {
	width: 900px;
}
</style>
<script>
	$(document)
	.ready(
			function() {
				/*if ("${studentsMark.status}" == "false") {
					$("#student_mark_enroll").modal("show");
				}
				else */
				if ('${noOfValidatedAnswers}' != 0) {
					$("#test_series_validated_answers_notification").modal("show");
				}
				else if ("${informationAlerts.iaStatus}" == "true")
				{
					getInfoAlert();
				}
			});
	
	function getInfoAlert()
	{
		var alertMsg=$('#showMsgHid').val();
		var alertTitle = "Edutel Academy Information and Alerts";
		bootbox.dialog({
			message : alertMsg,
			title : "<strong>" + alertTitle + "</strong>",
			buttons : {
			}
		});
	}
	function executeLink(url) {
		if(url!= null && url != undefined && allTrim(url)!="")
		{
			if(url.indexOf("performOnlineMCQExam.do")>=0)
			{
				var confirmMsg = "<div align='center' style='background-color:#ffffff;float: left;padding:0px;vertical-align: bottom;'><BR><BR><img src=\"images/png/exam.png\" /></div>";
				confirmMsg += "<div align='left' style='background-color:#ffffff;float:right;vertical-align: top; margin-top:-10px;'>";
				confirmMsg += "<span style='color:green;font-size:16px; font-weight:bold;vertical-align: middle;'>\nHi Welcome,\n\nYou are trying to perform Online CAT MCQ Exam.";
				confirmMsg += "\n\nAre you sure to perform Exam ?</span>";
				confirmMsg += "<BR><BR><span style='color:red;font-size:14px; font-weight:bold;'>Note : Once Exam perform will not be taken again.</span></div>";
				confirmBox("Online CAT MCQ Exam" , confirmMsg , 500, 300,url);
			}
			else	
			{
				window.location.href = url;
			}
		}
	}
	
	function onAlert(date, timeElapsed)
	{
		if(timeElapsed=='true')
		{
			var alertMsg = "<div align='center' style='background-color:#ffffff;float: left;padding:10px;'><img src=\"images/png/exam_missed.png\" /></div>";
			alertMsg += "<div align='left' style='background-color:#ffffff;float:right;vertical-align: top; margin-top:-10px;'>";
			alertMsg += "<span style='color:red;font-size:14px; font-weight:bold;'>\nOops ! You had missed the Online MCQ Exam held on \n\n\n";
			alertMsg += date;
			alertMsg += "</span></div>";
			alertBox("Online Exam Time Elapsed" , alertMsg , 500, 225);
		}
		else
		{
			var alertMsg = "<div align='center' style='background-color:#ffffff;float: left;padding:10px;vertical-align: bottom;'><img src=\"images/png/exam_timer.png\" /></div>";
			alertMsg += "<div align='left' style='background-color:#ffffff; float:right; vertical-align: top; margin-top:-10px;'>";
			alertMsg += "<span style='color:red;font-size:14px; font-weight:bold;'>\nPlease Wait Until\n\n";
			alertMsg += date;
			alertMsg += "\n\nOnce Time Reached Refresh Dashboard To Activate Link.</span></div>";
			alertBox("Online Exam Time Not Reached" , alertMsg, 500, 225);
		}
	}
	
	function downloadTestSeriesValidatedAnswers(ansId, answerFile, questionId, downloadBy, table) {
		  var url = "downloadTestSeriesAnswerByAjax.do?oeQuestionId="+questionId+"&oeTestSeriesAutoId="
			+ ansId + "&oeFileName=" + answerFile + "&downloadBy="+downloadBy+"&uid=" + Math.random();
	window.open(url, 'open'); 
	deleteValidatedAnswersRow(table);
	}
	
	
	function deleteValidatedAnswersRow(src) {

		var oRow = src.parentNode.parentNode;
		var table = document.getElementById('validated_answers');
		
		if (oRow.rowIndex > 0) {
			uploadRowIndex = oRow.rowIndex;
			document.getElementById("validated_answers").deleteRow(oRow.rowIndex);
		}
		var rowCount = table.rows.length;
		if(rowCount <= 1)
			{
			$("#test_series_validated_answers_notification").modal("hide");
			}
	}
	function onDownloadCATQuestionsByAjax(subject, chapter, examType,
			examId, examMode) {
		 var url = 'downloadCATQuestionsByAjax.do?oeSubject=' + subject
				+ '&oeChapter=' + chapter + '&oeExamType=' + examType
				+ '&oeExamId=' + examId + '&oeExamMode=' + examMode + '&uid='
				+ Math.random();
			var confirmMsg = "<div align='center' style='background-color:#ffffff;float: left;padding:0px;vertical-align: bottom;'><BR><BR><img src=\"images/png/exam.png\" /></div>";
			confirmMsg += "<div align='left' style='background-color:#ffffff;float:right;vertical-align: top; margin-top:-10px;'>";
			confirmMsg += "<span style='color:green;font-size:16px; font-weight:bold;vertical-align: middle;'>\nHi Welcome,\n\nYou are trying to Download Online CAT MCQ \nExam Questions.";
			confirmMsg += "\n\nAre you sure to Download Exam Questions?</span>";
			confirmMsg += "<BR><BR><span style='color:red;font-size:14px; font-weight:bold;'>Note : Once Exam Questions Download, \nwill not be taken again.</span></div>";
			confirmBox("Online CAT MCQ Exam Questions Download" , confirmMsg , 500, 300,url);
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div id="dialog-message" title="Information Alert"></div>
		<marquee
			style="background-color: yellow; color: inherit; font-size: 14px; font-weight: 600; width: 100%;"
			direction="left">${marqueeMsg}</marquee>
		<div class="container whiteBg clearfix" style="width: 98%;">
			<div id="signupwrapper" align="center">
				<table style="width: 98%;" rules="rows" frame="hsides">
					<tr>
						<td width="4%" valign="top">&nbsp;</td>
						<td width="96%" valign="top" colspan="4"><div id="pathDiv"
								style="width: 100%; height: 25px; border-bottom: thin; border-bottom-color: #ececec; border-bottom-style: solid;">
								<s:hidden id="showMsgHid" name="showMsgHid"
									value="%{informationAlerts.iaInformationMsg}"></s:hidden>
								<table style="width: 100%;">
									<tr>
										<td width="4%" height="25px;" valign="middle" align="left"><span
											id="pathSpan"
											style="font-size: 12px; font-family: Arial, Trebuchet MS, Tahoma; font-weight: bold;">DashBoard
										</span></td>
										<td width="67%" height="25px;" valign="middle" align="left"><s:if
												test="hasActionErrors()">
												<s:iterator value="actionErrors">
													<span class="errorMessage" id="errorSpan"
														style="color: red;"><s:property escape="false" /></span>
												</s:iterator>
											</s:if></td>
										<td width="8%" height="25px;" valign="middle" align="left"
											nowrap><a class="showMsg" href="javascript:void(0);"
											onclick="getInfoAlert();"><img
												src="images/png/info_bulb.png"
												style="cursor: pointer; height: 25px; width: 25px;"
												align="top" title="Show Message Box" />&nbsp;&nbsp;</a><a
											href="http://www.edutelacademy.com/OnlineManualBookForFullYearSupportProgram.pdf"
											target="_blank"><img src="edu-images/help.png"
												style="cursor: pointer; height: 25px; width: 25px;"
												align="top" title="Show Help" /></a></td>
										<td width="2%" height="25px;" valign="middle" align="right"><img
											src="images/user-login.png"
											style="cursor: pointer; height: 25px; width: 25px;"
											align="top" /></td>
										<td width="15%" height="25px;" valign="middle" align="right"
											nowrap="nowrap"><span
											style="font-size: 11px; font-weight: bold;">&nbsp;&nbsp;
												Last Login Time : <s:if
													test="usLastLoginTime!=null&&usLastLoginTime!='null'">
													<s:date name="usLastLoginTime"
														format="dd-MMM-yyyy HH:mm:ss" />
												</s:if>
										</span></td>
									</tr>
								</table>
							</div></td>
					</tr>
					<tr>
						<td width="4%" valign="top">&nbsp;</td>
						<td width="78%" valign="top">
							<div id="mainDiv" style="width: 100%;">
								<table style="width: 100%;" rules="rows" frame="hsides">
									<tr>
										<td valign="top" style="padding: 2px 2px 2px 2px; width: 33%"
											nowrap="nowrap">
											<div class="divMainPanel">
												<div class="divHeaderRed">
													<table>
														<tr>
															<td>&nbsp;&nbsp;Test Series Exam's&nbsp;&nbsp;</td>
														</tr>
													</table>
												</div>
												<div class="divBody" style="float: left;" align="center">
													<s:set var="isTestSeriesDisplay" value="%{'false'}" />
													<table class="examTable" width="100%">
														<s:iterator value="onlineExamList" var="bean">
															<s:if
																test="%{oeExamType!='MCQ' && oeExamId!='EXM150902042729258'}">
																<tr>
																	<td><s:set var="isTestSeriesDisplay"
																			value="%{'true'}" />
																		<div style="width: 100%;" align="left">
																			<s:if test="%{timeReached==true}">
																				<s:if test="%{status==true}">
																					<table style="width: 100%" class="examDiv">
																						<tr>
																							<td class="imgIconTD"><img
																								src="images/png/testseries.png" /></td>
																							<td class="divContentStrip" nowrap="nowrap"><div
																									style="width: 100%; margin-top: -20px;">
																									<span class="spanLblNormal"
																										style="font-size: 13px;"> <a
																										href="javascript:void(0);"
																										onclick="executeLink('performOnlineTestSeriesExam.do?oeExamId=${oeExamId}&oeSubject=${oeSubject}&oeChapter=${oeChapter}')">
																											${oeExamName} - ${oeSubject} - chapter - ${oeChapter} </a>
																									</span>
																								</div>
																								<div style="width: 100%;">
																									<span class="spanLblRed"> Date &amp;
																										Time : ${oeExamDate}<br>Duration:&nbsp;${oeExamDuration}&nbsp;Hrs.
																									</span>
																								</div></td>
																						</tr>
																					</table>
																				</s:if>
																				<s:else>
																					<table style="width: 100%" class="examDiv">
																						<tr>
																							<td class="imgIconTD"><img
																								src="images/png/testseries.png" /></td>
																							<td class="divContentStrip" nowrap="nowrap"><div
																									style="width: 100%; margin-top: -20px;">
																									<span class="spanLblNormal">
																										${oeExamName} - ${oeSubject} - Chapter - ${oeChapter} </span>
																								</div>
																								<div style="width: 100%;">
																									<span class="spanLblRed"> Date &amp;
																										Time : ${oeExamDate}<br>Duration:&nbsp;${oeExamDuration}&nbsp;Hrs.
																									</span>
																								</div></td>
																						</tr>
																					</table>
																				</s:else>
																			</s:if>
																			<s:else>
																				<table style="width: 100%" class="examDiv">
																					<tr>
																						<td class="imgIconTD"><img
																							src="images/png/testseries.png" /></td>
																						<td class="divContentStrip" nowrap="nowrap"><div
																								style="width: 100%; margin-top: -20px;">
																								<span class="spanLblNormal"> <s:if
																										test="%{status==true}">
																										<a href="javascript:void(0);"
																											onclick="executeLink('performOnlineTestSeriesExam.do?oeExamId=${oeExamId}&oeSubject=${oeSubject}&oeChapter=${oeChapter}')">
																											${oeExamName} - ${oeSubject} - Chapter - ${oeChapter}</a>
																									</s:if> <s:else> 
																							${oeExamName} - ${oeSubject}- Chapter ${oeChapter}
																						</s:else>
																								</span>
																							</div>
																							<div style="width: 100%;">
																								<span class="spanLblRed"> Date &amp; Time
																									: ${oeExamDate}&nbsp;&nbsp;Duration:&nbsp;${oeExamDuration}&nbsp;Hrs. </span>
																							</div></td>
																					</tr>
																				</table>
																			</s:else>
																		</div></td>
																</tr>
															</s:if>
														</s:iterator>
													</table>
													<s:if test="#isTestSeriesDisplay=='false'">
														<br>
														<br>
														<br>
														<br>
														<span style="color: #dfdfdf;">No Test Series Exam's
															avail</span>
													</s:if>
												</div>
											</div>
										</td>
										<td valign="top" style="padding: 2px 2px 2px 2px; width: 33%"
											nowrap="nowrap">
											<div class="divMainPanel">
												<div class="divHeaderGreen">
													<table>
														<tr>
															<td>&nbsp;&nbsp;Live Online Class&nbsp;&nbsp;</td>
														</tr>
													</table>
												</div>
												<div class="divBody" style="float: left;" align="center">
													<table style="width: 100%" class="examDiv">
														<tr>
															
															<td class="divContentStrip" >
																<div
																	style="width: 100%; margin-top: -20px;">
																	<span class="spanLblNormal"
																		style="font-size: 13px;"> <a
																		href="javascript:void(0);"
																		onclick="executeLink('https://zoom.us/join ')">
																			<img src="edu-images/dashboard/ZoomVideos.jpg" /></a>
																	</span>
																</div></td>
														</tr>
													</table>
												</div>
											</div>
										</td>
										<td valign="top" style="padding: 2px 2px 2px 2px; width: 34%"
											nowrap="nowrap">
											<div class="divMainPanel">
												<div class="divHeaderDarkBlue">
													<table>
														<tr>
															<td style="width: 95%;">&nbsp;&nbsp;Online MCQ Test Engine&nbsp;&nbsp;</td>
															
														</tr>
													</table>
												</div>
												<div class="divBody" style="float: right;" align="center">
												<table style="width: 100%" class="examDiv">
													<tr>
														
														<td class="divContentStrip" nowrap="nowrap">
															<div
																style="width: 100%; margin-top: -20px;">
																<span class="spanLblNormal"
																	style="font-size: 13px;"> <a
																	href="javascript:void(0);"
																	onclick="executeLink('https://online.edutelacademy.com/')">
																		<img src="edu-images/dashboard/OnlineTest.jpg"/></a>
																</span>
															</div>
														</td>
													</tr>
												</table>
													
												</div>
											</div>
										</td>
									</tr>
									<tr>
										
													<td valign="top"
														style="padding: 2px 2px 2px 2px; width: 33%"
														nowrap="nowrap">
														<div class="divMainPanel">
															<div class="divHeaderGray">
																<table>
																	<tr>
																		<td>&nbsp;&nbsp;Recorded Videos&nbsp;&nbsp;</td>
																	</tr>
																</table>
															</div>
															<div class="divBody" style="float: left;" align="center">
																<a href="${endUserVideoURL}">
																	<img src="edu-images/dashboard/Recordedvideos.jpg" /></a>												
															</div>
														</div>
													</td>
													<td valign="top"
														style="padding: 2px 2px 2px 2px; width: 33%"
														>
														<div class="divMainPanel">
															<div class="divHeaderGreen">&nbsp;&nbsp;Entrance Exams&nbsp;&nbsp;</div>
															<div class="divBody" style="float: left;" align="center">
																<img src="edu-images/dashboard/NeetJee.jpg" />
																
															</div>
														</div>
													</td>
													<td valign="top"
														style="padding: 2px 2px 2px 2px; width: 34%"
														nowrap="nowrap">
														<div class="divMainPanel">
															<div class="divHeaderGreen">&nbsp;&nbsp;Best Encrypted Material &nbsp;&nbsp;</div>
															<div class="divBody" style="float: left;" align="center">

																<img src="edu-images/dashboard/BestEncMat.jpg" width="50" height ="50" />
															</div>
														</div>
													</td>
	
												
									</tr>
								</table>
							</div>
						</td>
						<td valign="top" nowrap="nowrap" width="28%">
							<div id="mainRightDiv" style="width: 100%;">
								<table style="width: 100%;" rules="rows" frame="hsides">
									<tr>
										<td valign="top" style="padding: 2px 2px 2px 2px;"
											nowrap="nowrap">
											<div class="divMainPanel">
												<div class="divHeaderBlue">&nbsp;&nbsp;Doubt Clearing Session&nbsp;&nbsp;</div>
												<div class="divBody" style="float: left;" align="center">
													<s:set var="isToppersDisplay" value="%{'false'}" />
													
													<s:if test="#isToppersDisplay=='false'">
														
														<span class="spanLblNormal"
																		style="font-size: 13px;"> <a
																		href="javascript:void(0);"
																		onclick="executeLink('viewQueryAnswer.do?subject=Physics')">
																			<img src="edu-images/dashboard/Physics.jpg" width="110"/></a>
																	</span>
																	<span class="spanLblNormal"
																		style="font-size: 13px;"> <a
																		href="javascript:void(0);"
																		onclick="executeLink('viewQueryAnswer.do?subject=Chemistry')">
																			<img src="edu-images/dashboard/Chemistry.jpg" width="110" /></a>
																	</span>
																	<span class="spanLblNormal"
																		style="font-size: 13px;"> <a
																		href="javascript:void(0);"
																		onclick="executeLink('viewQueryAnswer.do?subject=Maths')">
																			<img src="edu-images/dashboard/Maths.jpg" width="110"/></a>
																	</span>
																	<span class="spanLblNormal"
																		style="font-size: 13px;"> <a
																		href="javascript:void(0);"
																		onclick="executeLink('viewQueryAnswer.do?subject=Biology')">
																			<img src="edu-images/dashboard/Biology.jpg" width="110"/></a>
																	</span>
																	
																	<span class="spanLblNormal"
																		style="font-size: 13px;"> <a
																		href="javascript:void(0);"
																		onclick="executeLink('viewQueryAnswer.do?subject=CareerCounselling')">
																			<img src="edu-images/dashboard/Counselling.jpg" width="110"/></a>
																	</span>
														
													</s:if>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td valign="top" style="padding: 2px 2px 2px 2px;"
											nowrap="nowrap">
											<div class="divMainPanel">
												<div class="divHeaderDarkBlue">&nbsp;&nbsp;Your
													Activities&nbsp;&nbsp;</div>
												<div class="divBody" style="float: left;" align="center">
													<s:set var="isActivityDisplay" value="%{'false'}" />
													<table style="width: 98%">
														<s:iterator value="userActivityList">
															<tr>
																<td style="width: 100%; height: 15px;" valign="bottom"><s:set
																		var="isActivityDisplay" value="%{'true'}" />
																	<div align="left"
																		style="padding: 2px 2px 2px 2px; vertical-align: middle; height: 25px; border-bottom: thin; border-top: thin; border-bottom-color: #ececec; border-bottom-style: solid; border-top-color: #ececec; border-top-style: solid;">
																		<span class="spanStyle"> ${event.eventName} - <s:date
																				name="createdDate" format="dd-MMM-yyyy HH:mm" />
																		</span>
																	</div></td>
															</tr>
														</s:iterator>
													</table>
													<s:if test="#isActivityDisplay=='false'">
														<br>
														<br>
														<br>
														<br>
														<br>
														<span style="color: #dfdfdf;">Your activities are
															here </span>
													</s:if>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<s:include value="m_student_mark_enrollment.jsp"></s:include>
				<s:include
					value="m_edutel_test_series_validated_answers_notifications.jsp"></s:include>
			</div>
		</div>
		<footer>
			<%@include file="../common/i_footer.jsp"%>
		</footer>
	</div>
</body>
<script>
	var chart = AmCharts.makeChart("chartDiv", {
		"type" : "pie",
		"theme" : "none",
		"dataProvider" : ${chartDivData},
		"valueField" : "Scored",
		"titleField" : "Subject",
		"exportConfig" : {
			menuItems : [ {
				icon : 'amcharts/images/export.png',
				format : 'png'
			} ]
		}
	});
</script>
</html>