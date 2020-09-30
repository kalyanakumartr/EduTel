<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<style>
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
	margin: 5px;
	overflow: hidden;
	font-family: Tahoma;
	font-size: 11px;
	max-width: 125px;
	height: 40px;
}
</style>
<script>
	function downloadEBluePrint(oeBluePrintId) {
		var url = "downloadEBluePrintByAjax.do?oeBluePrintId=" + oeBluePrintId
				+ "&uid=" + Math.random();
		window.open(url, 'open');
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online Exam EBluePrints</p>
			</div>
			<div id="dashBoardDiv" align="center" style="height: 400px;">
				<s:include
					value="/jsp/edutel/edutel_online_eblueprint_view_docs.jsp" />
			</div>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
