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

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	border-top: medium none;
}
</style>
<body>
	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">5</s:param>
	</s:include>
	<span></span>
	<br>
	<div class="wrap">
		<div class="main">
			<div class="about-top">
				<div class="cont1 span_2_of_a about_desc" style="width: 97%;">
					<!-- Start main content -->
					<div class="services style-1 home bottom-3"
						style="margin-top: -20px;">
						<div class="container clearfix">
							<div style="margin: 20px; text-align: justify;">
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;Career Page</span>
									<p class="link1" style="padding-top: 0px;"></p>
								</div>
								<div class="section group">
									<div class="col_1_of_4 span_1_of_4" style="width: 45%;">
										<table class="table" style="margin-bottom: 10px;">
											<tr>
												<td>Openning For</td>
												<td style="font-weight: bold;">MARKETING</td>
											</tr>
											<tr>
												<td>Designation</td>
												<td>INTERFACE - SALES</td>
											</tr>
											<tr>
												<td>Knowledge</td>
												<td>HARDCORE SELLING</td>
											</tr>
											<tr>
												<td>Experiance</td>
												<td>FRESHER</td>
											</tr>
											<tr>
												<td>Qualification</td>
												<td>ANY DEGREE</td>
											</tr>
											<tr>
												<td>Salary</td>
												<td>TO THE STANDARDS - NEGOTIABLE</td>
											</tr>
											<tr>
												<td>Contact No</td>
												<td>+91-74012 59090</td>
											</tr>
											<tr>
												<td>Mail Us</td>
												<td>hr@edutelacademy.com</td>
											</tr>
										</table>
									</div>
									<div class="col_1_of_4 span_1_of_4" style="width: 45%;">
										<table class="table" style="margin-bottom: 10px;">
											<tr>
												<td>Openning For</td>
												<td style="font-weight: bold;">CUSTOMER SERVICE</td>
											</tr>
											<tr>
												<td>Designation</td>
												<td>CUSTOMER CARE</td>
											</tr>
											<tr>
												<td>Knowledge</td>
												<td>SERVICE</td>
											</tr>
											<tr>
												<td>Experiance</td>
												<td>FRESHER CAN APPLY</td>
											</tr>
											<tr>
												<td>Qualification</td>
												<td>ANY DEGREE</td>
											</tr>
											<tr>
												<td>Salary</td>
												<td>NEGOTIABLE</td>
											</tr>
											<tr>
												<td>Contact No</td>
												<td>+91-74012 59090</td>
											</tr>
											<tr>
												<td>Mail Us</td>
												<td>hr@edutelacademy.com</td>
											</tr>
										</table>
									</div>
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