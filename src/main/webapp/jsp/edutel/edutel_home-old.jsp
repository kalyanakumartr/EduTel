<!DOCTYPE HTML>
<html>
<%
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF8");
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUsers"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUserRoles"%>
<%@ page import="com.hbs.edutel.util.CommonUtil"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="edutel_home_init.jsp" />
<style>
.close {
	display: none;
}

.testimonials {
	padding-top: 0px;
}

.about_desc p span {
	color: black;
}
</style>
<body>
	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">1</s:param>
	</s:include>
	<div class="masterDiv" align="center"
		style="background-color: #f0f0f0;">
		<div class="image-slider">
			<ul class="rslides" id="slider1">
				<li><img src="academia/images/banner/5img.jpg" alt=""></li>
				<li><img src="academia/images/banner/4img.jpg" alt=""></li>
				<li><img src="academia/images/banner/1img.jpg" alt=""></li>
				<li><img src="academia/images/banner/2img.jpg" alt=""></li>
				<li><img src="academia/images/banner/3img.jpg" alt=""></li>
			</ul>
		</div>
		<div class="wrap">
			<div class="main">
				<div class="section group">
					<div class="col_1_of_4 span_1_of_4">
						<img src="academia/images/pic.jpg" alt="" />
						<div class="banner-box3">
							<span class="text20">Full Year Support Program</span>
						</div>
						<s:include value="i_edutel_product_details.jsp" />
						<a href="loadPage.do?p=ProgramDetails" target="_blank"
							style="margin-left: 65%;">Read more... </a>
					</div>
					<div class="col_1_of_4 span_1_of_4">
						<img src="academia/images/pic1.jpg" alt="" />
						<div class="banner-box3">
							<span class="text20">To Enroll</span>
						</div>
						<img src="academia/images/payonline.jpg" alt="" /> <br>
						<%-- <s:include value="i_edutel_entroll.jsp" /> --%>
						<a href="#" target="_blank">&nbsp;&nbsp;</a>
					</div>
					<div class="col_1_of_4 span_1_of_4">
						<img src="academia/images/pic2.jpg" alt="" />
						<div class="banner-box3">
							<span class="text20">Quick Links</span>
						</div>
						<s:include value="i_edutel_quicklinks_msg.jsp" />
						<a href="#" target="_blank">&nbsp;&nbsp;</a>
					</div>
					<div class="col_1_of_4 span_1_of_4">
						<a href="serialKeyValidate.do"><img src="images/register.jpg"
							style="max-width: 105%; height: 170px;"></a>
						<div class="banner-box3">
							<span class="text20">Current Happenings</span>
						</div>
						<s:include value="i_edutel_private_tutor.jsp" />
						<a href="#" target="_blank">&nbsp;&nbsp;</a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="bottom-grids">
					<div class="bottom-grid1">
						<h3>POPULAR LINKS</h3>
						<div class="testimo">
							<ul>
								<li>
									<div class="cntnt">
										<ul style="text-align: left;">
											<li style="margin-bottom: 0px; padding: 5px;"><a
												href="#modal_iit" data-toggle="modal">Indian Institutes
													of Information Technology (IIITs, IIITM & IIITDM)</a></li>
											<li style="margin-bottom: 0px; padding: 5px;"><a
												href="#modal_cfi" data-toggle="modal">Central
													Government/State Government Funded
													Institutions(CFI)/Colleges</a></li>
											<li style="margin-bottom: 0px; padding: 5px;"><a
												href="#modal_nit" data-toggle="modal">List of NITs
													participating in JEE Main CCB counselling </a></li>
											<li style="margin-bottom: 0px; padding: 5px;"><a
												href="#modal_aipmt" data-toggle="modal">List of Medical
													Colleges with Seats filled by AIPMT 2015 Score</a></li>
										</ul>
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="bottom-grid2 bottom-mid">
						<h3>Testimonials</h3>

						<s:include value="i_edutel_testimonials.jsp" />
					</div>
					<div class="bottom-grid3 bottom-last">
						<h3>Latest News</h3>
						<s:include value="i_edutel_news.jsp" />
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<s:include value="m_edutel_iit.jsp" />
			<s:include value="m_edutel_cfi.jsp" />
			<s:include value="m_edutel_nit.jsp" />
			<s:include value="m_edutel_aipmt.jsp" />

		</div>
		<s:include value="edutel_home_pre_footer.jsp" />
		<s:include value="edutel_home_footer.jsp" />
	</div>


</body>
</html>


