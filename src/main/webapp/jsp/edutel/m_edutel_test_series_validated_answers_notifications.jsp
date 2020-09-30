<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="modal fade bs-modal-lg"
	id="test_series_validated_answers_notification" tabindex="-1"
	role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Test Series Validated Answers
					Notifications</h4>
			</div>
			<div class="modal-body">

				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered table-advance table-hover"
							id="validated_answers">
							<thead style="background-color: #ddd;">
								<tr style="font-weight: bold; text-align: center;">
									<td>Exam Name</td>
									<td>Question</td>
									<td>Validated Answer</td>
									<td>Uploaded By</td>
									<td>Uploaded Date</td>
								</tr>
							</thead>
							<s:iterator value="oeTestSeriesValidatedAnswersNotificationList">
								<tr>
									<td><a href="javascript:void(0);"
										onclick="downloadTestSeriesValidatedAnswers('${oeExamTestSeriesAutoId}','${uploadFileName}','${onlineExamQuestion.oeQuestionId}','Student', this);">${onlineExamQuestion.onlineExam.oeExamName}</a>
									</td>
									<td><span class="control-label">${onlineExamQuestion.oeQuestion}</span></td>
									<td><span class="control-label">${uploadFileName}</span></td>
									<td><span class="control-label">${modifiedBy}</span></td>
									<td><span class="control-label">${modifiedDate}</span></td>
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
