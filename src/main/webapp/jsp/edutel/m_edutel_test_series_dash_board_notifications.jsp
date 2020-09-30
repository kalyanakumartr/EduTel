<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="css/bootbox/components.css" rel="stylesheet" type="text/css" />
<link href="css/bootbox/plugins.css" rel="stylesheet" type="text/css" />
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="js/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<div class="modal fade bs-modal-lg"
	id="test_series_dash_board_notifications" tabindex="-1" role="basic"
	aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Test Series Questions & Answers
					Notifications</h4>
			</div>
			<div class="modal-body">

				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered table-advance table-hover">
							<thead style="background-color: #ddd;">
								<tr style="font-weight: bold; text-align: center;">
									<td>Exam Name</td>
									<td>Question</td>
									<td>Created By</td>
									<td>Created Date</td>
								</tr>
							</thead>
							<s:iterator value="onlineExamQuestionsNotificationList">
								<tr>
									<td><a href="javascript:void(0);"
										onclick="getTestSeriesStudentAnswers('${oeQuestionId}');">${onlineExam.oeExamName}</a>
									</td>
									<td><span class="control-label">${oeQuestion}</span></td>
									<td><span class="control-label">${createdBy}</span></td>
									<td><span class="control-label">${createdDate}</span></td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn red" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!-- /.modal-dialog -->
