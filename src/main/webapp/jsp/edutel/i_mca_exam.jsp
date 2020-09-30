<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{oeExamId=='EXM150902042729258'}">
	<tr>
		<td><s:set var="isMCQDisplay" value="%{'true'}" />
			<div style="width: 100%;" align="left">
				<s:if test="%{timeReached==true}">
					<s:if test="%{status==true}">
						<table style="width: 100%" class="examDiv">
							<tr>
								<td class="imgIconTD"><s:if test="%{assignedExam==true}">
										<img src="images/png/exam_assigned.png" />
									</s:if> <s:else>
										<img src="images/png/testseries.png" />
									</s:else></td>
								<td class="divContentStrip" nowrap="nowrap"><div
										style="width: 100%; margin-top: -20px;">
										<span class="spanLblNormal" style="font-size: 13px;"> <a
											href="javascript:void(0);"
											onclick="executeLink('performOnlineTestSeriesExam.do?oeExamId=${oeExamId}&oeSubject=${oeSubject}&oeChapter=${oeChapter}')">
												${oeExamName} - ${oeSubject} - chapter - ${oeChapter} </a>
										</span>
									</div>
									<div style="width: 100%;">
										<span class="spanLblRed"> Date &amp; Time :
											${oeExamDate}<br>Duration
											:&nbsp;${oeExamDuration}&nbsp;Hrs.
										</span>
									</div></td>
							</tr>
						</table>
					</s:if>
				</s:if>
			</div></td>
	</tr>
</s:if>