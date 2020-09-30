<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<script type="text/javascript" charset="utf-8" src="js/jquery.dataTables.editable.js"></script>
<script type="text/javascript" charset="utf-8" src="js/jquery.jeditable.js"></script>
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

.gradeA {
	color: red;
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
											"bProcessing" : true,
											"bServerSide" : true,
											"sAjaxSource" : "queryListByJson.do?uid="
													+ Math.random(),
											"bJQueryUI" : true,
											"sPaginationType" : "full_numbers",
											"sServerMethod" : "POST",
											"aoColumns" : [
												
													{
														"sName" : "ID",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<div style='height:90px;max-height:90px;overflow-y:auto;'>"
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
															return "<div style='cursor:pointer; height:90px;max-height:90px;overflow-y:auto;' >"
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
													},	{
														"sName" : "Subject",
														"bSearchable" : true,
														"bSortable" : true,
														"fnRender" : function(
																obj) {
															return "<div style='height:90px;max-height:90px;overflow-y:auto;'>"
																	+ obj.aData[10]
																	+ "</div>";
														}
													},
													null,
													null,
													null,
													null,
													null,
													{
														"sName" : "ID8",
														"bSearchable" : false,
														"bSortable" : false,
														"fnRender" : function(
																obj) {
															return "<img src=\"images/png/cancel_48.png\" onclick=\"deleteQuery("
																	+ obj.aData[8]
																	+ ");\" style=\"cursor:pointer; align:center;vertical-align:middle;height:18px;width:18px;\"/>";
														}
													},
													{
														"sClass" : "hide_column",
														"fnRender" : function(
																obj) {
															return obj.aData[9]
														}
													} ]
										});
						queryListId
								.makeEditable({
									"aoColumns" : [
											null,
											{
												indicator : "Saving Answer...",
												tooltip : "Double click to update the Answer",
												type : "textarea",
												submit : 'Reply For the Post',
												data : " ",
												sUpdateURL : function(value,settings) {
															$.ajax({
																type : "POST",
																url : 'queryAnswerUpdate.do?autoId=' + this.parentNode.lastChild.innerHTML,
																data : {answer:value},
																complete : function(data) 
																{
																	queryListId.fnFilter(' ',0);
																	alert("Answer Replied Successfully");
																}
															}); 
													return value;
												}, 

											},
											{
												indicator : 'Saving Rating...',
												tooltip : 'Select to move Query to Library',
												loadtext : 'loading...',
												type : 'select',
												onblur : 'submit',
												data : "{'0':' No Rating! ','3':'Good', '1':'Fair','2':'Better','4':'Very_Good','5':'Excellant'}",
												sUpdateURL : function(value,
														settings) {
													var url = "autoId="
															+ this.parentNode.lastChild.innerHTML
															+ "&rated=" + value;

													$
															.ajax({
																type : "POST",
																url : 'queryAnswerUpdateRating.do',
																data : url,
																complete : function(
																		data) {
																	queryListId
																			.fnFilter(
																					' ',
																					0);
																	alert("Answer Rated Successfully");
																}
															});
													return value;
												}
											}, null, null, null, null, null,
											null ]
								});

					});
	function deleteQuery(id) {
		var confirmDelete = confirm("Are sure want to delete the Query?");
		if (confirmDelete) {
			var url = 'deleteQuery.do?queryId=' + id + "&uid=" + Math.random();

			$.getJSON(url, function(data) {
				if (data != null & data.length > 0) {
					alert(data[0].value);
					queryListId.fnFilter(' ', 0);
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
		<div class="container whiteBg clearfix" style="width: 95%;">
			<div id="signupwrapper" align="center">
				<div class="latest-title" align="center">
					<p>Discussion Board</p>
				</div>
				<div id="signupwrapper1" align="center"
					style="width: 100%; padding: 1px;">
					<div id="queryDiv" align="center"
						style="width: 100%; padding: 20px; font-size: 12px;">
						<s:if test="hasActionErrors()">
							<s:iterator value="actionErrors">
								<span class="errorMessage" id="errorSpan"
									style="color: red; font-weight: bold;"><s:property
										escape="false" /></span>
							</s:iterator>
						</s:if>
						<div id="queryDiv" class="ui-state-default" align="center">
							<table cellpadding="0" cellspacing="0" border="0" class="display"
								id="queryListTableId">
								<thead>
									<tr>
										
										<th width="30%">Queries</th>
										<th width="30%">Replies</th>
										<th width="8%">Rating</th>
										<th width="6%">Raised By</th>
										<th width="6%">Raised Date</th>
										<th width="5%">Subject</th>
										<th width="6%">School Name</th>
										<th width="6%">Replied By</th>
										<th width="6%">Replied Date</th>
										<th width="2%"></th>
										<th width="0%"></th>
										
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="10" class="dataTables_empty">Loading data
											from Server</td>
									</tr>
								</tbody>
							</table>
						</div>
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