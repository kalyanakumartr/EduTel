<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	$(document).ready(function() {
		$('#studentEnqTableId').dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "studentEdutelEnquiryList.do",
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"sServerMethod" : "POST"
		});
	});
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>EduTel Academy Enquiry</p>
			</div>
			<div id="signupwrapper1" align="center" style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv" style="padding:10px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="studentEnqTableId">
							<thead>
								<tr>
									<th width="25%">Name</th>
									<th width="10%">Mobile No</th>
									<th width="20%">Email</th>
									<th width="20%">School Name</th>
									<th width="15%">Board</th>
									<th width="10%">Class</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="6" class="dataTables_empty">Loading data from Server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<%@include file="../common/i_footer.jsp"%>
	</footer>
</body>
</html>
