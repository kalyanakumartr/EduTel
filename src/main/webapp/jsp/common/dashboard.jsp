<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="/jsp/common/header.jsp" />
<link href="css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables_themeroller.css" rel="stylesheet"
	type="text/css" />

<script src="js/jquery-1.4.3.min.js"></script>
<script src="js/jquery.dataTables.js"></script>

<script>
	document.body.style.overflow = 'hidden';
	document.body.scroll = 'no';
	$(document).ready(function() {
		$('#userListTableId').dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "userListByJson.do",
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"sServerMethod" : "POST",
			"sColumns" : [ "usUserName", "usUserPwd" ]
		});
	});
</script>



<div id="mydiv" style="padding: 50px 50px 50px 50px;" align="center">

	<div id="dashBoardDiv" class="dashBoardDiv" align="center">
		<table cellpadding="0" cellspacing="0" border="0" class="display"
			id="userListTableId">
			<thead>
				<tr>
					<th width="35%">User Name</th>
					<th width="35%">User Id</th>
					<th width="30%">Password</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="3" class="dataTables_empty">Loading data from
						Server</td>
				</tr>
			</tbody>
		</table>
	</div>

</div>
<%@ include file="/jsp/common/i_footer.jsp"%>

