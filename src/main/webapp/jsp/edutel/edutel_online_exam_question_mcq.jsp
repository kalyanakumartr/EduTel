<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<s:set name="noOfQuestions" value="onlineExamQuestionList.size"></s:set>
<script>
	var config;
	var dateTime;
	var refreshIntervalId;
	var isFirst = true;
	var isExamFinished = false;
	var isAlreadyAnswered = false;
	var startTime;
	var endTime;
	$(document).ready(function() {
		$("#bb-nav-prev").hide();
		$("#bb-nav-prev_disable").hide();
		$("#bb-nav-next").hide();
		$("#bb-nav-next_disable").hide();
		$("#onlineExamQuestionLeftDiv").hide();
		$("#explanationDiv").hide();
		$("#explanationTitleDiv").hide();
		$("#correctAnswerDisp").hide();
		$("#buttonRow").hide();
		$("#qNoDisplay").text("Online MCQ's Exam Instructions ");
		$('#circle').progressCircle({
			nPercent : 0,
			showPercentText : true,
			thickness : 5,
			circleSize : 100
		});

		$("#circle").hide();
		$("#displayScore").hide();
		$("#displayTimer").hide();
		$("#notFoundRow").hide();

	});

	function toSeconds(time_str) {
		// Extract hours, minutes and seconds
		var parts = time_str.split(':');
		// compute  and return total seconds
		return parseInt(parts[0]) * 3600 + // an hour has 3600 seconds
		parseInt(parts[1]) * 60 + // a minute has 60 seconds
		+parseInt(parts[2]); // seconds
	}

	function beginTest() {

		if ("${onlineExamQuestionList}" == "[]") {
			$("#notFoundRow").show();
			$("#bb-bookblock").hide();
			$("#beginTestDiv").hide();
		} else {
			if (("${onlineExam.onlineAssessment}" == "true" || "${onlineExam.onlineAssessment}" == true)
					&& ("${onlineExam.timeReached}" == "true" || "${onlineExam.timeReached}" == true)) {

				testStart();
			}

			else if ("${onlinePractiseExam.oeIsOnlineExam}" == "false"
					|| "${onlinePractiseExam.oeIsOnlineExam}" == false) {

				testStart();
			}

			else {
				
				var alertMsg = "<div align='center' style='background-color:#ffffff;float: left;padding:10px;'><img src=\"images/png/exam_missed.png\" /></div>";
				alertMsg += "<div align='left' style='background-color:#ffffff;float:right;vertical-align: top;'>";
				alertMsg += "<span style='color:red;font-size:14px; font-weight:bold;'>\nOops ! You had missed the Online MCQ Exam held on \n\n\n";
				alertMsg += "${onlineExam.oeExamDate}</span></div>";
				var windowWidth = 500;
				var windowHeight = 250;
				
				msgBox = $(".alert-message").boxes(
				{
					message : alertMsg,
					messString : false,
					width : windowWidth,
					height : windowHeight,
					minWidth : windowWidth,
					maxWidth : windowWidth,
					minHeight : windowHeight,
					maxHeight : windowHeight,
					autoClose : 0,
					autoResize : true,
					clickOut : false,
					okBtnShow : true,
					okBtn : "Ok",
					ok : function() {
						window.location.href = "dashBoardStudent.do?uid=" + Math.random();
					},
					title : "<div align='center' style='background-color:#336699;height:30px;'><span style='color:#ffffff;font-size:18px; font-weight:bold;'>Online Exam Time Elapsed</span></div>"
				});
				$(".alert-message").trigger('click');
				$(".alert-message").unbind('click');
			}
			startTime = getCurrentTime();
		}
	}

	function onCancelCalled(url) {
		window.location.href = url;
	}

	function testStart() {
		$("#beginTestDiv").hide();

		$("#buttonRow").show();
		var questionId = $("#questionId1").val();

		loadQuestionInDiv(questionId, 1, 'next');
		$("#onlineExamQuestionLeftDiv").show();

		var dateTime = addSeconds().toLocaleTimeString();

		$("#displayTimer").show();
		refreshIntervalId = setInterval(function() {
			myTimer(dateTime);
		}, 1000);
		isFirst = false;
	}

	function addSeconds() {
		return new Date(new Date().getTime()
				+ parseInt('${onlinePractiseExam.oeMCQDurationPerExam}') * 1000
				+ 1000); // 2000 is startup calibration
	}

	function closeDialog() {
		$("#alertDialog").dialog("close");
	}

	function myTimer(dateTime) {
		var difference = Math.abs(toSeconds(dateTime)
				- toSeconds(new Date().toLocaleTimeString()));

		if (difference == 0 && isFirst == false) {
			clearInterval(refreshIntervalId);

			$("#answer1").hide();
			$("#answer2").hide();
			$("#answer3").hide();
			$("#answer4").hide();

			updatePractiseExamMarkInHistory();

			alertBox("Sorry Its Time Up", getDialogDisp(), 600, 300) ;
			$("#timerDisplay").text("00:00:00");
		} else {
			var result = [ Math.floor(difference / 3600), // HOURS
			Math.floor((difference % 3600) / 60), // MINUTES
			difference % 60 // SECONDS
			];

			result = result.map(function(v) {
				return v < 10 ? '0' + v : v;
			}).join(':');

			$("#timerDisplay").text(result);
		}
	}

	function getDialogDisp() {
		var dialogDisp = "<div style=\"border:2px;padding:5px;\"><span style=\"font-size:14px; color:#005E8A; font-weight:bold;\">";
		var _Answered = "";

		var _NotAnswered = "";
		for ( var qNo = 1; qNo <= '${noOfQuestions}'; qNo++) {
			var isAnswered = $("#questionAnswered" + qNo).val();
			if (isAnswered == false || isAnswered == 'false'
					|| isAnswered == '') {
				$("#questionAnswered" + qNo).val('true');
				$("#isAnsImage" + qNo).attr("src", "images/png/warning_48.png");
				$("#isAnsImage" + qNo).show();
				$("#isCorrect" + qNo).val("NotAnswered");
				_NotAnswered += qNo + ", ";
			} else {
				_Answered += qNo + ", ";
			}
		}

		if (_Answered != "" && _Answered.indexOf(", ") > 0) {

			$("#_AnsweredQNos").val(
					_Answered.substring(0, _Answered.lastIndexOf(", ")));

			_Answered = "<span style=\"font-size:14px; color:green; font-weight:bold; white-space: pre-line;\"><p>You had Answered for the questions <br>"
					+ _Answered.substring(0, _Answered.lastIndexOf(", "))
					+ "</p></span><br>";

		}

		if (_NotAnswered != "" && _NotAnswered.indexOf(", ") > 0) {

			$("#_UnAnsweredQNos").val(
					_NotAnswered.substring(0, _NotAnswered.lastIndexOf(", ")));

			_NotAnswered = "<span style=\"font-size:14px; color:red; font-weight:bold; white-space: pre-line;\"><p>You have NOT Answered for the questions <br>"
					+ _NotAnswered.substring(0, _NotAnswered.lastIndexOf(", "))
					+ "</p></span><br>";
		}

		dialogDisp += "<span style=\"font-size:18px; color:green; font-weight:bold;\"><br><p>You have Scored : " + $("#scoredMark").val()
				+ "</p></span><br>";
		dialogDisp += _Answered;
		dialogDisp += _NotAnswered;
		dialogDisp += "</span></div>";
		return dialogDisp;
	}

	function loadPreviousQuestion(path) {
		var prevQuestionNo = $("#prevQuestionNo").val();
		var questionId = $("#questionId" + prevQuestionNo).val();

		loadQuestionInDiv(questionId, prevQuestionNo, path);
	}

	function loadNextQuestion(path) {
		var nextQuestionNo = $("#nextQuestionNo").val();
		var questionId = $("#questionId" + nextQuestionNo).val();

		loadQuestionInDiv(questionId, nextQuestionNo, path);
	}

	function loadQuestionInDiv(questionId, qNo, path) {
		$("#explanationDiv").hide();
		$("#explanationTitleDiv").hide();
		$("#circle").show();
		$("#tick").hide();
		$("#cross").hide();
		if ("${onlinePractiseExam.oeIsOnlineExam}" == "false"
				|| "${onlinePractiseExam.oeIsOnlineExam}" == false) {
			$("#displayScore").show();
		}

		$("#current-bb-item1")
				.html(
						"<span style='font-size: 14px; font-weight: bold;'><br><br><br><br><br><br><br><br><br><img src='images/loading.gif' />Loading... Please wait...</span>");

		if (path == '') {
			var prevQuestionNo = $("#prevQuestionNo").val();
			if (prevQuestionNo > qNo)
				path = 'prev';
			else
				path = 'next';
		}
		if (questionId != null && questionId != '') {
			var isAnswered = $("#questionAnswered" + qNo).val();

			isAlreadyAnswered = $("#questionAnswered" + qNo).val();

			var url = "executeOnlineExamQuestion.do?questionId=" + questionId
					+ "&questionNo=" + qNo + "&isAnswered=" + isAnswered
					+ "&uid=" + Math.random();

			$
					.get(
							url,
							function(data) {
								$("#prevQuestionNo").val(parseInt(qNo) - 1);
								$("#selectedQuestion").val(qNo);
								$("#nextQuestionNo").val(parseInt(qNo) + 1);

								var prevQuestionNo = $("#prevQuestionNo").val();

								if (prevQuestionNo == 0
										|| selectedQuestion == '') {
									$("#bb-nav-prev").hide();
									$("#bb-nav-prev_disable").show();
								} else {
									$("#bb-nav-prev").show();
									$("#bb-nav-prev_disable").hide();
								}

								if (qNo == '${noOfQuestions}') {
									$("#bb-nav-next").hide();
									$("#bb-nav-next_disable").show();
								} else {
									$("#bb-nav-next").show();
									$("#bb-nav-next_disable").hide();
								}

								$("#bb-bookblock").html(data);
								config.$bookBlock.bookblock(path);
								$("#qNoDisplay").text(" Question " + qNo);
								$("#current-bb-item" + qNo).show();
								if (isAnswered == 'true' || isAnswered == true) {
									var isCorrect = $("#isCorrect" + qNo).val();
									if (isCorrect == 'true'
											|| isCorrect == true) {
										$("#tick").show();
										$("#cross").hide();
										$("#warn").hide();

									} else if (isCorrect == 'NotAnswered') {
										$("#tick").hide();
										$("#cross").hide();
										$("#warn").show();
									} else {
										$("#tick").hide();
										$("#cross").show();
										$("#warn").hide();
									}
									$("#explanationTitleDiv").show();
									$("#explanationDiv").show();
									$("#explanationTitleDiv").show();

									$("#selectedAnswerDisp")
											.text(
													"Selected Answer : "
															+ $(
																	"#selectedAnswer"
																			+ qNo)
																	.val());

									if ("${onlinePractiseExam.oeIsOnlineExam}" == "false"
											|| "${onlinePractiseExam.oeIsOnlineExam}" == false) {

										$("#correctAnswerTitleDisp").show();
										$("#correctAnswerDisp").show();
									} else if ("${onlinePractiseExam.oeIsOnlineExam}" == "true"
											|| "${onlinePractiseExam.oeIsOnlineExam}" == true) {
										if (isExamFinished == 'true'
												|| isExamFinished == true) {
											$("#correctAnswerTitleDisp").show();
											$("#correctAnswerDisp").show();
										}
									}
								}

							});
		}

	}

	function setSelectedAnswer(questionId, qNo, ansNo) {

		if ((isAlreadyAnswered == 'true' || isAlreadyAnswered == true)
				|| (isExamFinished == 'true' || isExamFinished == true)) {
			$("#processCount").val(parseInt($("#processCount").val()));
		} else {
			$("#processCount").val(parseInt($("#processCount").val()) + 1);
		}
		var percent = Math.round(parseInt($("#processCount").val()) * 100
				/ parseInt('${noOfQuestions}'));

		$('#circle').progressCircle({
			nPercent : percent,
			showPercentText : true,
			thickness : 5,
			circleSize : 100
		});

		var answerRd = $("#answer" + ansNo).val();

		$("#selectedAnswer" + qNo).val(answerRd);

		$("#selectedAnswerDisp").text(
				"Selected Answer : " + $("#selectedAnswer" + qNo).val());

		var isAnswered = $("#questionAnswered" + qNo).val('true');

		var url = "validateSelectedAnswer.do?questionId=" + questionId
				+ "&selectedAnswer=" + answerRd + "&isAnswered=" + isAnswered
				+ "&uid=" + Math.random();

		$("#answer1").hide();
		$("#answer2").hide();
		$("#answer3").hide();
		$("#answer4").hide();

		$.getJSON(
						url,
						function(data) {
							if (data != null & data.length > 0) {
								if (data[0].isCorrect == 'true'
										|| data[0].isCorrect == true) {
									$("#scoredMark")
											.val(
													parseInt($("#scoredMark")
															.val())
															+ parseInt(data[0].scoredMark));
									if ("${onlinePractiseExam.oeIsOnlineExam}" == "false"
											|| "${onlinePractiseExam.oeIsOnlineExam}" == false) {
										$("#tick").show();
										$("#cross").hide();
										$("#warn").hide();
										$("#scoredMarkDisplay2").text(
												$("#scoredMark").val());

										$("#isAnsImage" + qNo).attr("src",
												"images/png/tick_disabled.png");
										$("#isAnsImage" + qNo).show();

									}
								} else {
									if ("${onlinePractiseExam.oeIsOnlineExam}" == "false"
											|| "${onlinePractiseExam.oeIsOnlineExam}" == false) {
										$("#tick").hide();
										$("#cross").show();
										$("#warn").hide();

										$("#isAnsImage" + qNo)
												.attr("src",
														"images/png/cross_disabled.png");
										$("#isAnsImage" + qNo).show();
									}

								}
								$("#isCorrect" + qNo).val(data[0].isCorrect);
								if ("${onlinePractiseExam.oeIsOnlineExam}" == "false"
										|| "${onlinePractiseExam.oeIsOnlineExam}" == false) {
									if (data[0].correctAnswer != null
											&& data[0].correctAnswer != ""
											|| data[0].correctAnswer != "undefined") {

										$("#correctAnswerDisp")
												.text(
														"Correct Answer : "
																+ data[0].correctAnswer);
										$("#correctAnswerDisp").show();
									} else {
										$("#correctAnswerDisp").text(
												"Correct Answer : Not Avail");
										$("#correctAnswerDisp").show();
									}

								} else if ("${onlinePractiseExam.oeIsOnlineExam}" == "true"
										|| "${onlinePractiseExam.oeIsOnlineExam}" == true) {
									if (isExamFinished == 'true'
											|| isExamFinished == true) {
										if (data[0].correctAnswer != null
												&& data[0].correctAnswer != ""
												|| data[0].correctAnswer != "undefined") {

											$("#correctAnswerDisp")
													.text(
															"Correct Answer : "
																	+ data[0].correctAnswer);
											$("#correctAnswerDisp").show();
										} else {
											$("#correctAnswerDisp")
													.text(
															"Correct Answer : Not Avail");
											$("#correctAnswerDisp").show();
										}
									}
								}

								$("#explanationDiv").attr("style",
										"width:700px;");

								if (data[0].explanation != null
										&& data[0].explanation != "") {
									$("#explanationDiv")
											.html(
													"<div> <span style=\"font-size:13px; color:#005E8A; font-weight:bold;\">Explanation : </span> <br></div>"
															+ "<div> <textarea style=\"background-image: url(''); border: 1px solid #ffffff; width: 100%; height: 100px; resize: none; font-size: 14px;\" readonly=\"readonly\">"
															+ data[0].explanation
															+ "</textarea> </div>");
									$("#explanationDiv").show();
								} else if (data[0].explanationImageUrl != null
										&& data[0].explanationImageUrl != "") {

									$("#explanationDiv")
											.html(
													"<div> <span style=\"font-size:13px; color:#005E8A; font-weight:bold;\">Explanation : </span> </div><br><div><img style=\"width: "+data[0].explanationWidth+"px; height: "+data[0].explanationHeight+"px;\" src="
															+ data[0].explanationImageUrl
															+ "> </div>");
									$("#explanationDiv").show();
								}

								else {

									$("#explanationDiv")
											.html(
													"<span style=\"font-size:13px; color:#005E8A; font-weight:bold;\">Explanation : </span>Not Avail.");
									$("#explanationDiv").show();
								}
							}
						});

	}

	function onsubmitCalled() {
		document.forms["onlineExamQuestionMCQForm"].submit();
	}

	function updatePractiseExamMarkInHistory() {

		$("#displayScore").show();
		$("#scoredMarkDisplay2").text($("#scoredMark").val());

		endTime = getCurrentTime();

		var url = "updatePractiseExamMarkInHistory.do?oePractiseExamId=${onlinePractiseExam.oePractiseExamId}";
		url += "&oePractiseExamName=${onlinePractiseExam.oePractiseExamName}&oeSubject=${onlinePractiseExam.oeSubject}";
		url += "&oeChapter=${onlinePractiseExam.oeChapter}&oeNoOfQuestions=${onlinePractiseExam.oeNoOfQuestions}";
		url += "&scoredMark=" + $("#scoredMark").val() + "&answeredQNos="
				+ $("#_AnsweredQNos").val() + "&unAnsweredQNos="
				+ $("#_UnAnsweredQNos").val();
		url += "&startTime=" + startTime + "&endTime=" + endTime;
		url += "&uid=" + Math.random();
		isExamFinished = true;

		$("#finishButton").attr("disabled", true);
		$.getJSON(url, function(data) {

		});

	}

	function onFinishTest() {

		clearInterval(refreshIntervalId);
		updatePractiseExamMarkInHistory();
		
		alertBox("Congrates...! You had Finished the Exam", getDialogDisp(), 600, 400) ;

	}

	function getCurrentTime() {
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		return currentTime.getFullYear() + "-" + month + "-"
				+ currentTime.getDate() + " " + currentTime.getHours() + ":"
				+ currentTime.getMinutes() + ":" + currentTime.getSeconds();
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width: 80%;">
			<div class="latest-title">
				<p>Multiple Choice Question</p>
			</div>
			<div id="signupwrapper" align="center" style="width: 99%;">
				<s:form name="onlineExamQuestionMCQForm" id="onlineExamQuestionMCQForm" action="executeOnlineExamQuestion.do" method="post" enctype="multipart/form-data"
					autocomplete="off" show_alert="1">
					<table style="width: 100%;">
						<tr>
							<td style="width: 14%" valign="top"><div style="width: 125px;">
									<div id="onlineExamQuestionLeftDiv" style="width: 140px; height: 400px; overflow: auto;">
										<s:set name="qNo" value="%{1}" />
										<s:hidden name="selectedQuestion" id="selectedQuestion" value=""></s:hidden>
										<s:iterator value="onlineExamQuestionList">
											<div style="width: 123px; height: 35px; vertical-align: bottom;" id="question${qNo}" class="buttonClass">
												<a href="javascript:loadQuestionInDiv('${oeQuestionId}', '${qNo}','');">
													<table>
														<tr>
															<td width="15%" valign="top"><img src="images/png/app_48.png" width="25px" height="25px" /></td>
															<td width="85%" nowrap="nowrap" valign="top" class="lblspan" style="color: #ffffff;"><img id="isAnsImage${qNo}"
																src="images/png/tick_disabled.png" width="15px" height="15px" align="right" style="display: none; font-size: 12px;" />
																&nbsp;Question&nbsp;${qNo} <input type="hidden" name="questionId" id="questionId${qNo}" value="${oeQuestionId}" /> <input type="hidden"
																name="questionAnswered" id="questionAnswered${qNo}" value="false" /> <input type="hidden" name="selectedAnswer" id="selectedAnswer${qNo}"
																value="" /> <input type="hidden" name="isCorrect" id="isCorrect${qNo}" value="false" /></td>
														</tr>
														<tr>
															<td colspan="2">&nbsp;</td>
														</tr>
													</table>
												</a>
											</div>
											<s:set name="qNo" value="%{#qNo+1}" />
										</s:iterator>
										<br> <br>
									</div>
									<s:hidden name="prevQuestionNo" id="prevQuestionNo" value="0"></s:hidden>
									<s:hidden name="nextQuestionNo" id="nextQuestionNo" value="2"></s:hidden>
									<s:hidden name="processCount" id="processCount" value="0" />
									<s:hidden name="scoredMark" id="scoredMark" value="0" />
									<s:hidden name="_AnsweredQNos" id="_AnsweredQNos" value="" />
									<s:hidden name="_UnAnsweredQNos" id="_UnAnsweredQNos" value="" />
								</div></td>
							<td valign="top" style="border: 1px solid #ececec; width: 76%;">
								<div id="onlineExamQuestionRightDiv" style="height: 700px;">
									<div align="justify" style="width: 100%;">
										<table class="toggelCSS" style="width: 100%;">
											<tr class="edutelAttachToggle" height="25" valign="bottom">
												<td width="97%" valign="middle"><span id="commentInlineheader" style="font-size: 14px; font-weight: bold;">&nbsp;&nbsp;<span
														id="qNoDisplay"></span></span></td>
												<td width="03%">&nbsp;</td>
											</tr>
										</table>
									</div>
									<div id="edutelAttachContent${questionNo}" class="bb-custom-wrapper" align="justify">
										<table id="tblRequest${questionNo}" style="width: 100%;">
											<tr>
												<td nowrap="nowrap" width="40px;" align="center" valign="middle" class="tdButton"><nav>
														<a id="bb-nav-prev" href="javascript:void(0);" class="bb-custom-icon bb-custom-icon-arrow-right"> <img src="images/png/arrow-left_blue.png" />
														</a>
													</nav> <a id="bb-nav-prev_disable" href="javascript:void(0);"> <img src="images/png/arrow-left_blue_disabled.png" />
												</a></td>
												<td nowrap="nowrap" width="90%">
													<div id="bb-bookblock" class="bb-bookblock" align="center">
														<div class="bb-item" id="current-bb-item1" align="center" style="border: 2px; vertical-align: middle;">
															<table class="admin_table tmar" style="border: 1px; font-family: Tahoma; font-size: 11px; font-weight: bold;">
																<tr>
																	<td width="10%">Subject</td>
																	<td width="40%">&nbsp;:&nbsp;${onlinePractiseExam.oeSubject}</td>
																	<td width="10%">Chapter</td>
																	<td width="40%">&nbsp;:&nbsp;${onlinePractiseExam.oeChapter}</td>
																</tr>
																<tr>
																	<td colspan="4">&nbsp;</td>
																</tr>
																<tr>
																	<td width="10%">No of Questions</td>
																	<td width="40%">&nbsp;:&nbsp;${onlinePractiseExam.oeNoOfQuestions}</td>
																	<td width="10%">Marks Per Question</td>
																	<td width="40%">&nbsp;:&nbsp;1</td>
																</tr>
																<tr>
																	<td colspan="4">&nbsp;</td>
																</tr>
																<tr>
																	<td width="10%" valign="top">Instructions</td>
																	<td valign="top" width="90%" colspan="3" style="font-family: Tahoma; font-size: 11px; font-weight: normal;"><div style="overflow: auto;">
																			<p>Please keep a pen and paper ready for rough work.</p>
																			<p>Your time countdown will begin as soon as you click the "Begin Test" button.</p>
																			<p>Your test will automatically end when the alloted time for the test runs out.</p>
																			<p>During a test, you can move backwards and forwards and jump to any question you like.</p>
																			<p>You will see how many questions you have attempted, answered and skipped on the left side.</p>
																		</div></td>
																</tr>
																<tr>
																	<td width="10%" valign="top">Caution</td>
																	<td valign="top" width="90%" colspan="3" >
																		<div style="overflow: auto; font-family: Tahoma; font-size: 11px; font-weight: normal;">
																			<p>Any unauthorized use, copying or violation of the terms shall constitute an offence under</p>
																			<p>the copyright act 1957 and shall be liable for prosecution.</p>
																			<p>Maximum preventions have been taken to assure that the information provided is correct but</p>
																			<p>we welcome communication from students and professors, especially concerning errors &amp;</p>
																			<p>deficiencies that you find. Please feel free to contact us your comments will be greatly</p>
																			<p>appreciated admin@edutelacademy.com</p>
																		</div>
																	</td>
																</tr>
																<tr>
																	<td colspan="4">&nbsp;</td>
																</tr>
															</table>
														</div>
													</div>
												</td>
												<td nowrap="nowrap" width="40px;" align="center" valign="middle" class="tdButton"><nav>
														<a id="bb-nav-next" href="javascript:void(1);" class="bb-custom-icon bb-custom-icon-arrow-left"> <img src="images/png/arrow-right_blue.png" />
														</a>
													</nav> <a id="bb-nav-next_disable" href="javascript:void(0);"> <img src="images/png/arrow-right_blue_disabled.png" />
												</a></td>
											</tr>
											<tr id="notFoundRow">
												<td align="center" colspan="3">
													<table>
														<tr>
															<td align="center"><img src="images/png/warning_48.png" /> &nbsp;&nbsp;&nbsp; <span style="color: #336699; font-size: 24px;">Questions
																	Not Found</span></td>
														</tr>
														<tr>
															<td height="15px" align="left" colspan="2">&nbsp;&nbsp;&nbsp;</td>
														</tr>
														<tr>
															<td align="center"><input type="button" class="btnstyle" value="Cancel" onclick="onCancelCalled('dashBoardStudent.do')" /></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="5">&nbsp;</td>
											</tr>
										</table>
										<div align="center">
											<div id="beginTestDiv" align="center" class="buttonBeginTest">
												<a href="javascript:beginTest();"><span style="font-family: Tahoma; font-size: 20px; font-weight: normal; color: #ffffff;">Begin MCQ Test</span></a>
											</div>
										</div>
									</div>
								</div>
								<div id="alertDialog"></div>
							</td>
							<td style="width: 10%; padding-left: 5px;" valign="top">
								<div style="position: relative; float: right; vertical-align: bottom;">
									<table style="width: 100%;">
										<tr id="buttonRow">
											<td align="center"><input type="button" class="btnstyle" id="finishButton" name="Answer" value="Finish Test" onclick="onFinishTest();"
												style="width: 100px;" /></td>
										</tr>
										<tr>
											<td width="125px" height="150px"><div id="circle"></div></td>
										</tr>
										<tr>
											<td width="135px" height="100px">
												<div id="displayScore" class="scoreCard">
													<p>
														<span id="scoredMarkDisplay1" style="font-size: 18px; font-weight: normal; color: #005E8A;">You have Scored </span>
													</p>
													<br>
													<p>
														<span id="scoredMarkDisplay2" style="font-size: 24px; font-weight: normal; color: red;">0</span><span id="scoredMarkDisplay3"
															style="font-size: 24px; font-weight: normal; color: red;">&nbsp;of&nbsp;${noOfQuestions}</span>
													</p>
												</div>
											</td>
										</tr>
										<tr>
											<td width="125px" height="20px">&nbsp;</td>
										</tr>
										<tr>
											<td width="135px" height="100px">
												<div id="displayTimer" class="scoreCard">
													<p>
														<span style="font-size: 12px; font-weight: bold; color: #005E8A;">Remaining Duration </span>
													</p>
													<br>
													<p>
														<span id="timerDisplay" style="font-size: 14px; font-weight: normal; color: red;">00:00:00</span>
													</p>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<script src="js/jquerypp.custom.js"></script>
	<script src="js/jquery.bookblock.js"></script>
	<script>
		var Page = (function() {

			config = {
				$bookBlock : $('#bb-bookblock'),
				$navNext : $('#bb-nav-next'),
				$navPrev : $('#bb-nav-prev')
			}, init = function() {
				config.$bookBlock.bookblock({
					speed : 800,
					shadowSides : 0.8,
					shadowFlip : 0.7
				});
				initEvents();
			}, initEvents = function() {

				config.$bookBlock.children();

				// add navigation events
				config.$navNext.on('click touchstart', function() {
					loadNextQuestion('next');
					return false;
				});

				config.$navPrev.on('click touchstart', function() {
					loadPreviousQuestion('prev');
					return false;
				});

			};

			return {
				init : init
			};

		})();
		Page.init();
	</script>
</body>
</html>
