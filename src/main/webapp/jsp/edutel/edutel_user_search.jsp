<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var userListTable;
	var uNameColumn = 0;
	var uIdColumn = 1;
	var uEmailColumn = 2;
	var uMultiColumn = 3;
	var uMobileColumn = 4;
	var uDOJColumn = 5;
	var uCityColumn = 6;
	var uCreatedColumn = 7;
	var uCreatedDtColumn = 8;
	var uOnline = 9;
	var uReset = 10;
	var uIdColumn = 11;
	var uTypeColumn = 12;
	var uKeySoldColumn = 13;
	var uBatch = 14;
	var uIdExamColumn = 15;
	$(document)
			.ready(
					function() {
						userListTable = $('#userListTableId')
								.dataTable(
										{
											"sDom" : 'T<"clear">lrtip',
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "userListByJson.do",
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aaSorting" : [ [ uCreatedDtColumn,
													'desc' ] ],
											"aoColumns" : [
													{
														"sName" : "ID1",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<a href=\"userPreUpdate.do?usEmployeeId="
																	+ obj.aData[uIdColumn]
																	+ "&usersType="
																	+ obj.aData[uTypeColumn]
																	+ "\" style=\"text-decoration: underline;color:blue;\">"
																	+ obj.aData[uNameColumn]
																	+ "</a>";
														}
													},
													null,
													null,
													null,
													null,
													null,
													null,
													null,
													null,
													{
														"sName" : "ID2",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															if (obj.aData[uOnline] == "true")
																return "<img src=\"images/png/online.png\"  title=\"Online\" style=\"vertical-align:bottom;width:24px;\"/>";
															else
																return "<img src=\"images/png/offline.png\"  title=\"Offline\" style=\"vertical-align:bottom;width:24px;\"/>";
														}
													},
													{
														"sName" : "ID3",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"resetPassword(\'"
																	+ obj.aData[uReset]
																	+ "\')\"><img src=\"images/png/password.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Reset User Password\"/></a>";
														}
													},
													{
														"sName" : "ID4",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteUser(\'"
																	+ obj.aData[uIdColumn]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Selected User\"/></a>";
														}
													},
													{
														"sName" : "ID5",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															if ("${param.usersType}" == "Student")
																return "<img src=\"images/png/student.png\"  title=\"Student\" style=\"vertical-align:bottom;width:32px;\"/>";
															else if ("${param.usersType}" == "Franchisee")
																return "<img src=\"images/png/franchisee.png\"  title=\"Franchisee\" style=\"vertical-align:bottom;width:32px;\"/>";
															else
																return "<img src=\"images/png/employee.png\"  title=\"Employee\" style=\"vertical-align:bottom;width:32px;\"/>";
														}
													},
													{
														"sName" : "ID6",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															if ("${param.usersType}" == "Student") {
																return obj.aData[uKeySoldColumn];
															} else {
																return "<span style='width:0px;'>-</span>";
															}
														}
													},
													{
														"sName" : "ID7",
														"bSearchable" : false,
														"bSortable" : false
													},
													{
														"sName" : "ID8",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															if ("${param.usersType}" == "Student")
																return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteOnlineExamEntry(\'"
																		+ obj.aData[uIdExamColumn]
																		+ "\')\"><img src=\"images/png/exam-reset.png\" id=\"delExamEntry\" style=\"vertical-align:bottom;cursor: pointer;height:32px;width:32px;\" title=\"Delete Current Exam Entry\"/></a>";
															else
																return "<span style='width:0px;'>-</span>";
														}
													}

											]
										});
					});
	function deleteOnlineExamEntry(employeeId) {

		var confirmDelete = confirm("Are sure want to Reset Current(Today) Online Exam?");
		if (confirmDelete) {
			var url = 'deleteOnlineExamEntryForStudent.do?usEmployeeId='
					+ employeeId + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					userListTable.fnFilter(' ', 0);
				}
			});
		}
	}

	function deleteUser(employeeId) {

		var confirmDelete = confirm("Are sure want to delete User?");
		if (confirmDelete) {
			var url = 'deleteUser.do?usEmployeeId=' + employeeId + "&uid="
					+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					userListTable.fnFilter(' ', 0);

				}
			});
		}
	}

	function resetPassword(employeeId) {

		var confirmDelete = confirm("Are sure want to Reset User Password?");
		if (confirmDelete) {
			var url = 'resetPassword.do?usEmployeeId=' + employeeId + "&uid="
					+ Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					userListTable.fnFilter(' ', 0);

				}
			});
		}
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix" style="width: 98%">
			<div class="latest-title">
				<p>${param.usersType} User Management</p>
				<s:set name="usersType">${user.usUsersType}</s:set>

			</div>
			<a class="img-responsive" href="#advanced_user_search"
				data-toggle="modal"><img src="images/png/advanced_search.png"
				style="cursor: pointer; height: 25px; width: 25px; float: right; margin-right: 25px; margin-top: -10px;"
				title="Advanced ${param.usersType} User Search" /></a> <br>
			<div id="signupwrapper1" align="center"
				style="width: 100%; padding: 0px;">
				<div id="dashBoardDiv" align="center"
					style="width: 100%; padding: 0px; font-size: 12px;">
					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors">
							<span class="errorMessage" id="errorSpan"
								style="color: red; font-weight: bold;"><s:property
									escape="false" /></span>
						</s:iterator>
					</s:if>
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="userListTableId">
							<thead>
								<tr>
									<th width="10%">User Name</th>
									<th width="10%">User Id</th>
									<th width="12%">Email Id</th>
									<s:if test="#usersType=='Student'">
										<th width="12%">School Name</th>
									</s:if>
									<s:elseif test="#usersType=='Franchisee'">
										<th width="5%">Area</th>
									</s:elseif>
									<s:else>
										<th width="5%">Father's Name</th>
									</s:else>
									<th width="5%">Mobile No</th>
									<th width="7%">Date Of Join</th>
									<th width="5%">City</th>
									<th width="8%">Created By</th>
									<th width="5%">Created Date</th>
									<th width="2%" align="center"></th>
									<th width="2%" align="center"></th>
									<th width="2%" align="center"></th>
									<th width="2%" align="center"></th>
									<s:if test="#usersType=='Student'">
										<th width="5%" align="center">Key Sold By</th>
										<th width="5%" align="center">Batch</th>
										<th width="5%" align="center"></th>
									</s:if>
									<s:else>
										<th width="3%" align="center"></th>
										<th width="3%" align="center"></th>
										<th width="3%" align="center"></th>
									</s:else>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="16" class="dataTables_empty">Loading data
										from Server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<s:include value="m_edutel_advanced_user_search.jsp"></s:include>
		</div>
		<footer>
			<%@include file="../common/i_footer.jsp"%>
		</footer>
	</div>
</body>
</html>
