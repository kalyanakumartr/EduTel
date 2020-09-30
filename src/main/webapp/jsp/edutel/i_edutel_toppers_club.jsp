<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$.fn.stars = function() {
		return $(this).each(function() {
			var val = parseFloat($(this).html());
			var size = Math.max(0, (Math.min(5, val))) * 12;
			var $span = $('<span />').width(size);
			$(this).html($span);
		});
	};

	$(function() {
		$('span.stars').stars();
	});
</script>
<style>
span.stars,span.stars span {
	display: block;
	background: url('images/png/stars.png') 0 -12px repeat-x;
	width: 80px;
	height: 10px;
}

span.stars span {
	background-position: 0 0;
}
</style>
<div style="width: 100%; height: 80px;">
	<s:iterator value="toppersClubList">
		<div>
			<s:set var="isToppersDisplay" value="%{'true'}" />
			<ul>
				<li>
					<div>
						<ul>
							<li><div>
									<table style="width: 100%; height: 75px;">
										<tr>
											<td width="30%" style="vertical-align: middle;"><img src="${usUserImageUrl}" class="thumb" width="50" height="50"/></td>
											<td width="70%" style="vertical-align: middle;">
												<table style="width: 100%;">
													<tr>
														<td valign="middle" style="font-size: 9px;">${tpStudentName}</td>
													</tr>
													<tr>
														<td valign="middle" style="font-size: 9px;">${tpExamName}</td>
													</tr>
													<tr>
														<td valign="middle" style="font-size: 9px;">${tpSchoolName}</td>
													</tr>
													<tr>
														<td valign="middle" style="font-size: 9px;"><span class="stars"
													id="rateStars">${tpRatings}</span></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
	</s:iterator>
</div>