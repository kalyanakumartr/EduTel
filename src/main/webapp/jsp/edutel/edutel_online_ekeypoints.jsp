<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script>
	var oeKeyPointsTbl;
	$(document)
			.ready(
					function() {
						onresetCalled();
						oeKeyPointsTbl = $('#oeKeyPointsListTableId')
								.dataTable(
										{

											/* "sDom" : 'T<"clear">lfrtip',*/
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "onlineEKeyPointsListByJson.do",
											"bJQueryUI" : true,
											"sServerMethod" : "POST",
											"sPaginationType" : "full_numbers",
											"aaSorting" : [ [ 5, 'desc' ] ],
											"aoColumns" : [
													null,
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
															return "<a href=\"javascript:void(0)\" style=\"vertical-align:bottom;\" onclick=\"deleteEKeyPoints(\'"
																	+ obj.aData[6]
																	+ "\')\"><img src=\"images/png/cancel_48.png\" id=\"delBk\" style=\"vertical-align:bottom;cursor: pointer;height:22px;width:22px;\" title=\"Delete Online EKeyPoints\"/></a>";
														}
													} ]
										});
					});

	function deleteEKeyPoints(eKeyPointsId) {
		var url = 'deleteOnlineEKeyPoints.do?oeKeyPointsId=' + eKeyPointsId
				+ "&uid=" + Math.random();

		$.getJSON(url, function(data) {
			if (data != null & data.length > 0) {
				alert(data[0].value);
				oeKeyPointsTbl.fnFilter(' ', 0);

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

		if ((fileCnt > 0) && (fileCnt <= 10)) {

			fileUpload = $("#fileUpload" + fileCnt).val();
			uploadFileName = $("#uploadFileDisplayName" + fileCnt).val();
			if (allTrim(uploadFileName) == "") {
				alert("Please Enter an EKey Point Name.");
				return false;
			}
			if (allTrim(fileUpload) == undefined || allTrim(fileUpload) == null
					|| allTrim(fileUpload) == "") {
				alert("Please Upload an EKey Point.");
				return false;
			}
		}
		document.forms["onlineEKeyPointsForm"].submit();
	}

	function onresetCalled() {
		document.forms["onlineEKeyPointsForm"].reset();
	}
</script>
<body class="sub_body2">
	<div id="wrap" class="boxed">
		<header>
			<s:include value="i_edutel_header.jsp"></s:include>
		</header>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<p>Online EKeyPoints</p>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="onlineEKeyPointsForm" id="onlineEKeyPointsForm"
					action="onlineEKeyPointsRepositoryUpload.do" method="post"
					enctype="multipart/form-data" autocomplete="off" show_alert="1"
					onSubmit="return validateForm()">
					<div width="100%" align="center">
						<table width="100%">
							<tr>
								<td align="center">
									<div id="onlineEKeyPointsDiv" align="center"
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
														listKey="label" listValue="value" theme="simple"
														onchange="loadEKeyPointsBySubject();" /></td>
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
														value="i_edutel_ekeypoints_attachments.jsp">
														<s:param name="attachReadOnly">false</s:param>
														<s:param name="attachmentBannerName">Online E-KeyPoints Upload</s:param>
														<s:param name="attachmentName">EKeyPoints Name</s:param>
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
													value="Upload EKeyPointss" href="javascript:void(0);"
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
							id="oeKeyPointsListTableId">
							<thead>
								<tr>
									<th width="20%" align="center">KeyPoints Name</th>
									<th width="20%" align="center">Subject</th>
									<th width="10%" align="center">Chapter</th>
									<th width="10%" align="center">Syllabus</th>
									<th width="15%" align="center">Created By</th>
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
