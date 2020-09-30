<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	function onsubmitCalled() {
		document.forms["onlinePractiseExamGenerateForm"].submit();
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
</style>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online Practice Exam Panel</p>
			</div>
			<div id="signupwrapper" align="center">
				<div>
					<table>
						<tr>
							<td>
								<div id="onlinePractiseExamGenerateDiv"
									style="width: 500px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
									<s:iterator value="onlinePractiseExamList" var="oePExam">
										<div id="questionDiv">
											<a href="performOnlineMCQPracticeExam.do?oePractiseExamId=${oePractiseExamId}&oeSubject=${oeSubject}&oeChapter=${oeChapter}&oeNoOfQuestions=${oeNoOfQuestions}">${oePractiseExamName} - ${oeSubject} - ${oeChapter}</a>
										</div>
										<br>
									</s:iterator>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
