<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.bottom-grid2 ul li {
	width: 100%;
}

.bottom-grid2 img {
	width: 100%;
	height: 200px;
}
</style>
<div class="testimo">
	<ul>
		<li>
			<div class="cntnt">
				<div class="newsContainer" style="width: 100%;">
					<ul>
						<s:iterator var="row" begin="1" end="9" step="1">
							<li><div style="text-align: center;">
									<img src="images/testimonials/${row}img.jpg" alt="" />
								</div></li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</li>
	</ul>
</div>
