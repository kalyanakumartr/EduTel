<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />

<script>
	function onsubmitCalled() {
		document.forms["studentDashBoardForm"].submit();
	}

	function onresetCalled() {
		//$("#errorSpan").hide();
		document.forms["studentDashBoardForm"].reset();

	}
</script>

</head>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div id="signupwrapper" align="center"></div>
		</div>
	</div>
	<!-- End services -->
	<footer>
		<div class="footer-top">
			<div class="footercon">
				<div class="container clearfix">&nbsp;</div>
				<!-- End container -->
			</div>
		</div>
		<%@include file="../common/i_footer.jsp"%>
		<!-- End footer-top -->
	</footer>
	<!-- <<< End Footer >>> -->
	<!-- End wrap -->
</body>
</html>
