<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<s:set name="noOfAnswers"
	value="onlineExamQuestionsNotificationList.size"></s:set>
<style>
.divMainPanel {
	width: 100%;
	height: 240px;
}

.divBody {
	width: 100%;
	height: 450px;
	overflow-y: scroll;
	padding: 5px;
	border: 2px solid #ececec;
	vertical-align: middle;
}

.divHeaderDarkBlue {
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

.spanStyle {
	font-family: Arial, Trebuchet MS, Tahoma;
	font-size: 11px;
	vertical-align: middle;
	white-space: pre-line;
	color: #336699;
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

.examTable tr:nth-child(even) {
	background: #eeeeee;
	width: 100%;
}

.examTable tr:nth-child(odd) {
	background: #fff;
	width: 100%;
}
</style>
<script>
	$(document).ready(function() {
		if ('${noOfAnswers}' != 0) {
			$("#test_series_dash_board_notifications").modal("show");
		}
	});

	function getTestSeriesStudentAnswers(oeQuestionId) {
		var url = "onlineTestSeriesExamAnswerSearch.do?oeExamQuestionId="
				+ oeQuestionId + "&uid=" + Math.random();
		window.location.href = url;
	}
</script>


<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width: 100%">
			<div id="signupwrapper" align="center">
				<table style="width: 100%;" rules="rows" frame="hsides">
					<tr>
						<td width="4%;"></td>
						<td width="62%" valign="top" style="text-align: center;"><br>
							<br> <span style="color: #dfdfdf; font-size: 72px;">Edutel
								Academy</span> <br> <br> <br> <br> <span
							style="color: #dfdfdf; font-size: 35px;">Number of Users
								Online </span> <br> <br> <span
							style="color: #dfdfdf; font-size: 35px;">Students :
								${noOfUsersOnline[0]} | Employees : ${noOfUsersOnline[1]} |
								Franchisee : ${noOfUsersOnline[2]}</span> <br> <br> <span
							style="color: #dfdfdf; font-size: 18px;">Results for last
								24 Hours duration. </span></td>
						<td width="30%" valign="top">
							<div id="mainDiv" style="width: 100%;">
								<table style="width: 100%;" rules="rows" frame="hsides">
									<tr>
										<td valign="top" style="padding: 2px 2px 2px 2px; width: 33%"
											nowrap="nowrap">
											<div class="divMainPanel">
												<div class="divHeaderBlue">
													<table>
														<tr>
															<td style="width: 95%;">&nbsp;&nbsp;Students
																Unvalidated Test Series Answers&nbsp;&nbsp;</td>
															<td style="width: 5%;"><a
																href="exportUnvaliatedTestSeriesAnsPDF.do"><img
																	src="images/png/download_green.png" title="Export PDF"
																	style="width: 20px; height: 20px;" /></a>&nbsp;&nbsp;</td>
														</tr>
													</table>
												</div>
												<div class="divBody" style="float: left;" align="center">
													<table
														class="table table-bordered table-advance table-hover">
														<thead style="background-color: #ddd;">
															<tr style="font-weight: bold; text-align: center;">
																<td>Student Name</td>
																<td>File Name</td>
																<td>Exam Name</td>
																<td>Date</td>
															</tr>
														</thead>
														<tbody>
															<s:iterator value="oeTestSeriesUnvalidatedAnswersList">
																<tr style="white-space: normal;">
																	<td><a href="javascript:void(0);"
																		onclick="getTestSeriesStudentAnswers('${onlineExamQuestion.oeQuestionId}');">${createdUser.usUserName}</a>
																	</td>
																	<td><span class="control-label">${uploadFileName}
																	</span></td>
																	<td><span class="control-label">${onlineExamQuestion.onlineExam.oeExamName}</span></td>
																	<td><span class="control-label">${createdDate}</span></td>
																</tr>
															</s:iterator>
														</tbody>
													</table>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td width="4%;"></td>
				</table>
			</div>
		</div>
	</div>
	<s:include value="m_edutel_test_series_dash_board_notifications.jsp"></s:include>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
