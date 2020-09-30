<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<style>
div.img {
	margin: 5px;
	padding: 5px;
	border: 2px solid #ececec;
	height: 170px;
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
</style>
<script>
	function loadEBookBySubject() {
		var url = "onlineEBookListByAjax.do?sEcho=EBook&sSearch="
				+ $("#oeSubject").val() + "&oeChapter=" + $("#oeChapter").val()
				+ "&uid=" + Math.random();
		var loadImg = "<img src='images/loading.gif' title='loading' alt='loading' style='border:0'>Loading...";

		$("#dashBoardDiv").empty().html(loadImg);

		$.get(url, function(data) {
			$("#dashBoardDiv").html(data);
		});
	}

	function downloadEBook(oeBookId) {
		var url = "downloadEBookByAjax.do?oeBookId=" + oeBookId + "&uid="
				+ Math.random();
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
				<p>Online EBooks</p>
			</div>
			<div id="signupwrapper" align="center">
				<div id="onlineEBookDiv">
					<table>
						<tr>
							<td width="0%"><s:hidden name="oeSchoolType" id="oeSchoolType" /></td>
							<td width="20%" style="padding: 5px;"><span class="lblstyle">Subject Name&nbsp;&nbsp;</span></td>
							<td width="40%" style="padding: 5px;"><s:select name="oeSubject" id="oeSubject" style="width:180px;" list="subjectList" listKey="label"
									listValue="value" theme="simple" onchange="loadEBookBySubject();" /></td>
							<td width="40%" style="padding: 5px;"><select name="oeChapter" id="oeChapter" style="width: 100px; vertical-align: middle; padding: 0px;"
								onchange="loadEBookBySubject();">
									<option value="">Chapter All</option>
									<option value="1">Chapter I</option>
									<option value="2">Chapter II</option>
									<option value="3">Chapter III</option>
									<option value="4">Chapter IV</option>
									<option value="5">Chapter V</option>
									<option value="6">Chapter VI</option>
									<option value="7">Chapter VII</option>
									<option value="8">Chapter VIII</option>
									<option value="9">Chapter IX</option>
									<option value="10">Chapter X</option>
									<option value="11">Chapter XI</option>
									<option value="12">Chapter XII</option>
									<option value="13">Chapter XIII</option>
									<option value="14">Chapter XIV</option>
									<option value="15">Chapter XV</option>
									<option value="16">Chapter XVI</option>
									<option value="17">Chapter XVII</option>
									<option value="18">Chapter XVIII</option>
									<option value="19">Chapter XIX</option>
									<option value="20">Chapter XX</option>
									<option value="21">Chapter XXI</option>
									<option value="22">Chapter XXII</option>
									<option value="23">Chapter XXIII</option>
									<option value="24">Chapter XXIV</option>
									<option value="25">Chapter XXV</option>
							</select></td>
						</tr>
					</table>
					<br> <br> <br>
				</div>
			</div>
			<div id="dashBoardDiv" align="center" style="height: 400px;">
				<s:include value="/jsp/edutel/edutel_online_ebook_view_docs.jsp" />
			</div>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
