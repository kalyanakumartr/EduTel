<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />

<link href="css/bootbox/components.css" rel="stylesheet" type="text/css" />
<link href="css/bootbox/plugins.css" rel="stylesheet" type="text/css" />
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/bootbox/profile.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="js/bootstrap-fileinput/bootstrap-fileinput.js"></script>

<script src="js/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
	var studentMarkTable;

	$(document).ready(function() {
		if ("${studentMark.status}" == "false") {
			$("#student_hsc_mark_update").modal("show");
		}
		studentMarkTable = $('#studentMarkTableId').dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "studentMarksListByJson.do",
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"sServerMethod" : "POST",
			"aaSorting" : [ [ 7, 'desc' ] ]

		});
	});
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width: 98%;">
			<div class="latest-title">
				<p>Admin Student Mark Update</p>

			</div>
			<a class="img-responsive" href="#student_hsc_mark_update"
				data-toggle="modal"><img src="images/png/edit_mark.png"
				style="cursor: pointer; height: 25px; width: 25px; float: right; margin-right: 85px; margin-top: -10px;"
				title="Student Mark Update" /></a> <br>
			<div id="signupwrapper" align="center">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv"
					style="width: 87%; padding: 20px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="studentMarkTableId">
							<thead>
								<tr>
									<th width="15%" align="center">Student Id</th>
									<th width="15%" align="center">Student Name</th>
									<th width="10%" align="center">Batch</th>
									<th width="10%" align="center">HSC No</th>
									<th width="10%" align="center">HSC Mark</th>
									<th width="10%" align="center">SSLC/PUC Mark</th>
									<th width="15%" align="center">Created By</th>
									<th width="15%" align="center">Created Date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="8" class="dataTables_empty">Loading data from
										Server</td>
								</tr>
							</tbody>
						</table>
						<s:include value="m_student_hsc_mark_update.jsp"></s:include>

					</div>
				</div>
			</div>
		</div>
		<footer>
			<%@include file="../common/i_footer.jsp"%>
		</footer>
	</div>
</body>

</html>