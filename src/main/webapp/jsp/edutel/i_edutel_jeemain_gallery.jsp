<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="testimo">
	<ul>
		<li>
			<div class="cntnt">
				<div class="newsContainer" style="width: 100%;">
					<ul>
						<s:iterator var="gal_row" begin="1" end="5" step="1">
							<li><div style="text-align: center;">
									<img src="images/jee_gallery/jee_gal${gal_row}.jpg" alt=""
										style="width: 548px; height: 195px;" />
								</div></li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</li>
	</ul>
</div>

