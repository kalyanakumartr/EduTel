<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="testimo">
	<ul>
		<li>
			<div class="cntnt">
				<div class="newsContainer" style="width: 100%;">
					<ul>
						<s:iterator var="quote_row" begin="1" end="5" step="1">
							<li><div
									style="margin: 5px 5px 5px 5px; text-align: center;">
									<img src="images/quotes/quote${quote_row}.jpg" alt=""
										style="width: 250px; height: 180px;" />
								</div></li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</li>
	</ul>
</div>

