<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<link type="text/css" rel="stylesheet"
	href="css/dataTables.tableTools.css">
<script type="text/javascript" charset="utf-8" src="js/ZeroClipboard.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/dataTables.tableTools.js"></script>
<style>
.hide_column {
	display: none;
}

.width_column {
	width: 80px;
	max-width: 80px;
	min-width: 80px;
}

.nowrap_column {
	width: 90px;
	max-width: 90px;
	min-width: 90px;
	align: center;
	padding: 0px;
}

.lblspanBold {
	font-weight: bold;
	color: #336699;
	font-size: 11px;
}

.lblspanRed {
	font-weight: normal;
	color: red;
	font-size: 13px;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	var queryListId;
	$(document)
			.ready(
					function() {
						queryListId = $('#queryListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lfrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "queryListByJson.do?p=1&uid="
													+ Math.random(),
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"oTableTools" : {
												"sRowSelect" : "single",
												"aButtons" : []
											},
											"aoColumns" : [
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<div align=\"justify\" style='height:90px;max-height:90px;overflow-y:auto;'>"
																	+ obj.aData[0]
																	+ "</div>";
														}
													},
													{
														"sName" : "answerId",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<div align=\"justify\" style='cursor:pointer; height:90px;max-height:90px;overflow-y:auto;' >"
																	+ obj.aData[1]
																	+ "</div>";
														}
													},
													{
														"sName" : "ID",
														"bSearchable" : false,
														"sClass" : "nowrap_column",
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															var rating = "";
															for ( var i = 0; i < obj.aData[2]; i++) {
																rating = rating
																		+ "<img src=\"images/png/star_yellow.png\" style=\"cursor:pointer; vertical-align:middle;height:14px;width:14px;\"/>";
															}
															for ( var i = obj.aData[2]; i < 5; i++) {
																rating = rating
																		+ "<img src=\"images/png/star_yellow_disabled.png\" style=\"cursor:pointer; vertical-align:middle;height:14px;width:14px;\"/>";
															}
															return rating;
														}
													}, null, null, null, null,
													null ]
										});

						$("#queryListTableId tbody")
								.on(
										'click',
										'tr',
										function()

										{
											var iPos = queryListId
													.fnGetPosition(this);
											var aData = queryListId
													.fnGetData(iPos);
											var message = "<table width=\"650px;\" >"
													+ "<tr>"
													+ "<td width=\"15%\"><span class=\"lblspanBold\">Posted By&nbsp;</span></td>"
													+ "<td  width=\"35%\"><span class=\"lblspanBold\">"
													+ aData[3]
													+ "</span></td>"
													+ "<td width=\"15%\"><span class=\"lblspanBold\">Replied By&nbsp;</span></td>"
													+ "<td  width=\"35%\"><span class=\"lblspanBold\">"
													+ aData[6]
													+ "</span></td>"
													+ "</tr>"
													+ "<tr>"
													+ "<td colspan=\"4\">&nbsp;</td></tr><tr><td width=\"15%\"><span class=\"lblspanBold\">Posted Date&nbsp;</span></td>"
													+ "<td  width=\"35%\"><span class=\"lblspanBold\">"
													+ aData[4]
													+ "</span></td>"
													+ "<td width=\"15%\"><span class=\"lblspanBold\">Replied Date&nbsp;</span></td>"
													+ "<td  width=\"35%\"><span class=\"lblspanBold\">"
													+ aData[7]
													+ "</span></td>"
													+ "</tr>"
													+ "<tr><td colspan=\"4\">&nbsp;</td></tr>"
													+ "<tr>"
													+ "<td colspan=\"4\"><span class=\"lblspanRed\">Queries</span><br><br><div  align=\"justify\" style='border:2px;font-size:11px;color:#336699;width:650px;max-width:650px;height:90px;max-height:90px;overflow:auto;white-space: pre-line;'>"
													+ aData[0]
													+ "</div></td>"
													+ "</tr>"
													+ "<tr>"
													+ "<td colspan=\"4\">&nbsp;</td></tr><tr><td colspan=\"4\"><span class=\"lblspanRed\">Replies</span><br><br><div align=\"justify\" style='border:2px;font-size:11px;color:#336699;width:650px;max-width:650px;height:90px;max-height:90px;overflow:auto;white-space: pre-line;'>"
													+ aData[1]
													+ "</div></td>"
													+ "</tr>" + "</table>";
											$("#queryDivDialog")
													.attr("style",
															"width: 700px; height: 500px;");
											$("#queryDivDialog").attr("title",
													"Queries and Replies.");
											$("#queryDivDialog").html(message);
											$('#queryDivDialog').dialog({
												width : 700,
												autoOpen : false,
												show : {
													effect : "blind",
													duration : 500
												},
												hide : {
													effect : "blind",
													duration : 500
												}
											});
											$("#queryDivDialog").dialog("open");

										});
					});
</script>
<style type="text/css">
.actionMessage {
	color: #006600;
	font-size: 11px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: center;
}

.lblspan {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: bold;
}

.textareaStyle {
	font-family: Tahoma;
	font-size: 11px;
}
</style>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width: 85%;">
			<div id="signupwrapper" align="center">
				<div class="latest-title" align="center">
					<p>Library & Discussion Board</p>
				</div>
				<div id="signupwrapper2" align="center"
					style="width: 100%; padding: 1px;">
					<div id="queryDiv" class="ui-state-default" align="center"
						style="font-family: Trebuchet MS; font-size: 12px;">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="queryListTableId">
							<thead>
								<tr>
									<th width="30%">Queries</th>
									<th width="30%">Replies</th>
									<th width="8%">Rating</th>
									<th width="6%">Raised By</th>
									<th width="7%">Raised Date</th>
									<th width="6%">School Name</th>
									<th width="6%">Replied By</th>
									<th width="7%">Replied Date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="8" class="dataTables_empty">Loading data from
										Server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="queryDivDialog"></div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>