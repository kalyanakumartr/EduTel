<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	$(document).ready(function() {
		$("#tick").hide();
		$("#cross").hide();
		$("#warn").hide();
		$("#explanationDiv").hide();
		$("#correctAnswerTitleDisp").hide();
		$("#correctAnswerDisp").hide();
		$("#explanationTitleDiv").hide();

	});
</script>
<style>
.span13Bold {
	font-family: Arial;
	font-size: 13px;
	font-weight: bold;
}
</style>
<div id="current-bb-item${questionNo}">
	<table id="tblRequest${questionNo}" style="width: 100%;">
		<tr height="95px;">
			<td class="txtLabel" nowrap="nowrap" style="width: 90%;" colspan="6"><s:if
					test="%{onlineExamQuestion.oeQuestion!=''}">
					<textarea
						style="background-image: url(''); border: 1px solid #ffffff; width: 100%; height: 90px; resize: none; font-size: 14px;"
						readonly="readonly">${onlineExamQuestion.oeQuestion}</textarea>
				</s:if> <s:else>
					<img src="${onlineExamQuestion.oeQuestionImageURL}"
						width="${onlineExamQuestion.questionWidth}"
						height="${onlineExamQuestion.questionHeight}" />
				</s:else></td>
		</tr>
		<tr height="20px;">
			<td class="toggelCSS" nowrap="nowrap"
				style="width: 100%; height: 20px;" colspan="6"><span
				class="span13Bold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Answers</span></td>
		</tr>

		<tr height="20px;">
			<td nowrap="nowrap" style="width: 100%;" height="20px;" colspan="6"></td>
		</tr>
		<s:set id="ansNo" name="ansNo" value="%{1}" />
		<s:iterator value="onlineExamQuestion.oeAnswers">
			<s:if test="%{#ansNo == 1 || #ansNo == 3 }">
				<tr height="80px;">
			</s:if>
			<td style="width: 1%;" valign="top"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ansNo}&nbsp;.&nbsp;</span></td>
			<td style="width: 2%;" valign="top" style="margin: 5px;"><s:if
					test="%{onlinePractiseExam.oeIsOnlineExam == false || onlinePractiseExam.oeIsOnlineExam == 'false'}">
					<s:if test="isAnswered == '' || isAnswered == 'false'">
						<span><input type="radio" name="answer" id="answer${ansNo}"
							value="${ansNo}"
							onclick="setSelectedAnswer('${onlineExamQuestion.oeQuestionId}', '${questionNo}', '${ansNo}')" />&nbsp;&nbsp;</span>
					</s:if>
				</s:if> <s:else>
					<span><input type="radio" name="answer" id="answer${ansNo}"
						value="${ansNo}"
						onclick="setSelectedAnswer('${onlineExamQuestion.oeQuestionId}', '${questionNo}', '${ansNo}')" />&nbsp;&nbsp;</span>
				</s:else></td>
			<td style="width: 47%; height: 80px;" valign="top"><s:if
					test="%{oeAnswerImageURL != null && oeAnswerImageURL != ''}">
					<img src="${oeAnswerImageURL}" width="${imageWidth}"
						height="${imageHeight}" />
				</s:if> <s:else>
					<div id="answerDiv" style="overflow: auto">
						<textarea
							style="background-image: url(''); border: 1px solid #ffffff; width: 100%; height: 75px; resize: none; font-size: 14px;"
							readonly="readonly">${oeAnswerText}</textarea>
					</div>
				</s:else></td>
			<s:if test="%{#ansNo == 2 }">
				</tr>
			</s:if>
			<s:set id="ansNo" name="ansNo" value="%{#ansNo+1}" />
		</s:iterator>
		<tr height="30px;">
			<td align="center" nowrap="nowrap" colspan="3" height="30px"><span
				id="selectedAnswerDisp"
				style="font-family: Tahoma; font-weight: bold; font-size: 12px; color: #CC3333;"></span>
			</td>
			<td align="center" nowrap="nowrap" colspan="1" height="30px"><span
				id="correctAnswerTitleDisp"
				style="font-size: 12px; font-weight: bold; color: #008400;">Correct
					Answer : </span></td>

			<td align="left" nowrap="nowrap" colspan="2" height="40px">
				<div id="correctAnswerDisp"
					style="vertical-align: middle; text-align: left; font-size: 13px; color: #008400; font-weight: bold;">

					<s:if
						test="%{isAnswered == 'true' && onlineExamQuestion.oeCorrectAnswer != null && onlineExamQuestion.oeCorrectAnswer != ''}">
						<span
							style="font-family: Tahoma; font-weight: bold; font-size: 11px; color: #008400;">${onlineExamQuestion.oeCorrectAnswer}</span>
					</s:if>
					<s:else>Not Avail.</s:else>
				</div>

			</td>
		</tr>
		<s:if
			test="%{onlinePractiseExam.oeIsOnlineExam == false || onlinePractiseExam.oeIsOnlineExam == 'false'}">
			<tr height="40px;">
				<td align="center" nowrap="nowrap" colspan="6" height="40px">

					<div id="cross"
						style="display: none; vertical-align: middle; text-align: center;">
						<table>
							<tr>
								<td align="right" valign="baseline" width="50%"><img
									src="images/png/cross.png" align="bottom" width="30px"
									height="30px" /></td>
								<td align="left" valign="middle" width="50%"><span
									style="font-family: Tahoma; font-weight: bold; font-size: 13px; color: #CC3333;">
										&nbsp;&nbsp;&nbsp;Sorry you selected the Wrong Answer!</span></td>
							</tr>
						</table>
					</div>
					<div id="tick"
						style="display: none; vertical-align: middle; text-align: center;">
						<table>
							<tr>
								<td align="right" valign="baseline" width="50%"><img
									src="images/png/tick.png" align="bottom" width="30px"
									height="30px" /></td>
								<td align="left" valign="middle" width="50%"><span
									style="font-family: Tahoma; font-weight: bold; font-size: 13px; color: #CC3333;">
										&nbsp;&nbsp;&nbsp;Yaah. It's Correct Answer</span></td>
							</tr>
						</table>
					</div>
					<div id="warn"
						style="display: none; vertical-align: middle; text-align: center;">
						<table>
							<tr>
								<td align="right" valign="baseline" width="50%"><img
									src="images/png/warning_48.png" align="bottom" width="30px"
									height="30px" /></td>
								<td align="left" valign="middle" width="50%"><span
									style="font-family: Tahoma; font-weight: bold; font-size: 13px; color: #CC3333;">
										&nbsp;&nbsp;&nbsp;Sorry you have not Answered.</span></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</s:if>
		<tr>
			<td valign="top" nowrap="nowrap" colspan="6"
				style="height: 5px; border-bottom: 1px solid #ececec;"></td>
		</tr>
		<s:if
			test="%{onlinePractiseExam.oeIsOnlineExam == false || onlinePractiseExam.oeIsOnlineExam == 'false'}">
			<tr height="50px;">
				<td valign="top" nowrap="nowrap" colspan="6" height="50px">

					<div id="explanationTitleDiv"
						style="vertical-align: middle; text-align: left; font-size: 13px; color: #005E8A;">
						<span style="font-size: 13px; color: #005E8A; font-weight: bold;">Explanation
							: </span><br>
					</div>
					<div id="explanationDiv"
						style="vertical-align: middle; text-align: left; font-size: 13px; color: #005E8A;">

						<s:if
							test="%{isAnswered == 'true' && onlineExamQuestion.oeExplanation != null && onlineExamQuestion.oeExplanation != ''}">
							<textarea
								style="background-image: url(''); border: 1px solid #ffffff; width: 100%; height: 100px; resize: none; font-size: 14px;"
								readonly="readonly">${onlineExamQuestion.oeExplanation}</textarea>
						</s:if>
						<s:elseif
							test="%{isAnswered == 'true' && onlineExamQuestion.oeExplanationImageURL != null && onlineExamQuestion.oeExplanationImageURL != ''}">
							<img src="${onlineExamQuestion.oeExplanationImageURL}"
								width="${onlineExamQuestion.explanationWidth}"
								height="${onlineExamQuestion.explanationHeight}" />
						</s:elseif>
						<s:else>Not Avail.</s:else>
					</div>
				</td>
			</tr>
		</s:if>
		<tr height="20px;">
			<td align="left" nowrap="nowrap" colspan="6">&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>
