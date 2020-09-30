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
.testimonials span.quotes {
	top: 15px;
}

.about_desc p span {
	color: black;
}

.testimonials {
	padding-top: 0px;
}
</style>
<body>
	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">2</s:param>
	</s:include>
	<span></span>
	<br>
	<div class="wrap">
		<div class="main">
			<div class="about-top">
				<div class="cont1 about_desc">
					<!-- Start main content -->
					<div class="services style-1 home bottom-3"
						style="margin-top: -20px;">
						<div class="container clearfix">
							<div style="margin: 20px; text-align: justify;">
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;Privacy Policy</span>
									<p class="link1"></p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Fees
											once paid cannot be refunded at any circumstances whatsoever
											be the reason, even part of the fee cannot be claimed; if
											he/she disinterested during the course.</span>
									</p>
								</div>
							</div>
						</div>
						<!-- End Container -->
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<s:include value="edutel_home_footer.jsp" />
</body>
</html>