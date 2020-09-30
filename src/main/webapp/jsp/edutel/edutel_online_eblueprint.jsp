<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var oeBluePrintTbl;
	$(document)
			.ready(
					function() {
						onresetCalled();
						oeBluePrintTbl = $('#oeBluePrintListTableId')
								.dataTable(
										{

											/* "sDom" : 'T<"clear">lfrtip',*/
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "onlineEBluePrintListByJson.do",
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"sPaginationType" : "full_numbers",
											"aaSorting" : [ [ 4, 'desc' ] ],
											"aoColumns" : [
													null,
													null,
													null,
													null,
													null,
													{
														"sName" : "ID",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteEBluePrint(\'"
																	+ obj.aData[5]
																	+ "\')\"><img src=\"images/png/minus.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Online EBluePrint\"/></a>";
														}
													} ]
										});
					});

	function deleteEBluePrint(oeBluePrintId) {
		var url = 'deleteOnlineEBluePrint.do?oeBluePrintId=' + oeBluePrintId
				+ "&uid=" + Math.random();

		$.getJSON(url, function(data) {
			if (data != null & data.length > 0) {
				alert(data[0].value);
				oeBluePrintTbl.fnFilter(' ', 0);

			}
		});
	}

	function onsubmitCalled() {
		var subject = $("#oeSubject").val();
		var syllabus = $("#oeSchoolType").val();
		var fileUpload = "";
		var uploadFileName = "";
		if (allTrim(subject) == "") {
			alert("Please select Subject Name");
			return false;
		}
		if (allTrim(syllabus) == "") {
			alert("Please select Syllabus");

		}

		if ((fileCnt > 0)) {

			fileUpload = $("#fileUpload" + fileCnt).val();
			uploadFileName = $("#uploadFileDisplayName" + fileCnt).val();
			if (allTrim(uploadFileName) == "") {
				alert("Please Enter an EBlue Print Name.");
				return false;
			}
			if (allTrim(fileUpload)== undefined || allTrim(fileUpload) == null
					|| allTrim(fileUpload) == "") {
				alert("Please Upload an EBlue Print.");
				return false;
			}
		}

		document.forms["onlineEBluePrintForm"].submit();
	}

	function onresetCalled() {
		document.forms["onlineEBluePrintForm"].reset();
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online EBluePrints</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="onlineEBluePrintForm" id="onlineEBluePrintForm"
					action="onlineEBluePrintRepositoryUpload.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div width="100%" align="center">
						<table width="100%">
							<tr>
								<td align="center">
									<div id="onlineEBluePrintDiv" align="center"
										style="width: 80%; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td valign="top" align="center" width="100%" colspan="4"
													height="30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:if
														test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan"
																style="color: red; font-weight: bold;"><s:property
																	escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr>
												<td align="left" width="10%" nowrap="nowrap"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;Subject Name</span><span
													style="color: red;">*</span></td>
												<td align="left" width="40%" nowrap="nowrap"><s:select
														name="oeSubject" id="oeSubject" list="subjectList"
														listKey="label" listValue="value" theme="simple" /></td>
												<td align="left" width="10%" nowrap="nowrap"><span
													class="lblstyle">&nbsp;&nbsp;&nbsp;Syllabus</span><span
													style="color: red;">*</span></td>
												<td align="left" width="40%" nowrap="nowrap"><select
													name="oeSchoolType" id="oeSchoolType">
														<option value="State_Board">State_Board</option>
														<!-- option value="CBSE">CBSE</option -->
												</select></td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4"><s:include
														value="i_edutel_eblueprint_attachments.jsp">
														<s:param name="attachReadOnly">false</s:param>
														<s:param name="attachmentBannerName">Online E-BluePrint Upload</s:param>
														<s:param name="attachmentName">EBluePrint Name</s:param>
														<s:param name="attachmentDynamicName"></s:param>
														<s:param name="isStudent">${loginUserType}</s:param>
													</s:include></td>
											</tr>
											<tr>
												<td align="left" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="center" colspan="4"><input type="button"
													class="btnstyle" name="uploadDocuments"
													value="Upload EBluePrints" href="javascript:void(0);"
													onclick="onsubmitCalled();" style="width: 150px;" />
													&nbsp;&nbsp; <input type="button" class="btnstyle"
													value="Reset" onclick="onresetCalled()" /></td>
											</tr>
											<tr>
												<td align="left" class="usertext" colspan="4">&nbsp;&nbsp;&nbsp;</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</s:form>
			</div>
			<br>
			<div id="signupwrapper1" align="center"
				style="width: 100%; padding: 1px;">
				<div id="dashBoardDiv" align="center" class="dashBoardDiv"
					style="padding: 10px; font-size: 12px;">
					<div id="dashBoardDiv" class="ui-state-default" align="center">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="oeBluePrintListTableId">
							<thead>
								<tr>
									<th width="30%" align="center">BluePrint Name</th>
									<th width="15%" align="center">Subject</th>
									<th width="10%" align="center">Syllabus</th>
									<th width="20%" align="center">Created By</th>
									<th width="24%" align="center">Created Date</th>
									<th width="1%" align="center"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="6" class="dataTables_empty">Loading data from
										Server</td>
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
