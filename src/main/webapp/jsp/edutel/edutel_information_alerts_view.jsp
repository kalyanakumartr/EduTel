<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<link type="text/css" rel="stylesheet" href="css/dataTables.tableTools.css">
<script type="text/javascript" charset="utf-8" src="js/ZeroClipboard.js"></script>
<script type="text/javascript" charset="utf-8" src="js/dataTables.tableTools.js"></script>
<link rel="stylesheet" href="css/jquery.boxes.min.css" />
<script type="text/javascript" src="js/jquery.boxes.min.js"></script>
<script>
	var infoAlertsListTable;
	var msgBox ;
	$(document)
			.ready(
					function() {
						infoAlertsListTable = $('#infoAlertsListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lfrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "infoAlertListByJson.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ 0, 'desc' ] ],
											"oTableTools" : {
												"sRowSelect" : "single",
												"aButtons" : []
											},
											"aoColumns" : [
													null,
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<div style='height:75px;overflow-y:auto;'> "
																	+ obj.aData[1]
																	+ "</div>";
														}
													} ]
										});
						$("#infoAlertsListTableId tbody")
								.click(
										function(event) {
											var oTT = TableTools.fnGetInstance('infoAlertsListTableId');
											var aSelectedTrs = oTT.fnGetSelected();
											var nRow = aSelectedTrs[0];
											var nTds = $('td', nRow);
											var msgContent = $(nTds[1]).html();
											var windowWidth = $(window).width()-200;
											var length = "<div style='height:75px;overflow-y:auto;'>".length;
											msgContent = msgContent.substring(msgContent.indexOf("<div style='height:75px;overflow-y:auto;'>")+ parseInt(length)+1);
											msgBox = $(".showMsg").boxes({
												height : '100%',
												message : msgContent,
												messString : false,
												width: 'auto',
												height: 'auto',
												minWidth:windowWidth,
												maxWidth:windowWidth,
												autoClose: 1,
												autoResize: true,
												clickOut: false,
												title: "<div align='center' style='background-color:#336699;height:40px;'><span style='color:#ffffff;font-size:22px; font-weight:bold;'>Edutel Academy Information and Alerts</span></div>"
											});
											$(".showMsg").trigger('click');
											$(".showMsg").unbind('click');
											
										});

					});
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width: 90%;">
			<div class="latest-title">
				<p>Information and Alerts</p>
			</div>
			<div id="signupwrapper" align="center" style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv" style="width: 99%; padding: 20px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="infoAlertsListTableId">
							<thead>
								<tr>
									<th width="2%">Info Id</th>
									<th width="95%">Information & Alert Message</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="10" class="dataTables_empty">Loading data from Server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<a class="showMsg" href="#">&nbsp;&nbsp;</a>
		</div>
		<footer>
			<%@include file="../common/i_footer.jsp"%>
		</footer>
	</div>
</body>
</html>
