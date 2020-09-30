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
	top: 10px;
}

.about_desc p span {
	color: black;
}

.testimonials {
	padding-top: 0px;
}

img {
	left: 22px;
	max-width: 100%;
}

.span_1_of_4 h3 {
	color: #fa6210;
	font-family: "Open Sans", sans-serif;
	font-size: 1.2em;
	margin: 6% 0 2%;
	text-transform: uppercase;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	border-top: medium none;
}
</style>
<body>
	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">6</s:param>
	</s:include>
	<span></span>
	<br>

	<div class="wrap">
		<div class="main">
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Karimulla. M.K.U</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Mathematics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Ed., M.Phil.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>16 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Sivagnanam. M</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Mathematics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., MCA., B.Ed.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>30 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Premila. B</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Mathematics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Phil., B.Ed.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>26 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Suresh. D</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Mathematics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Ed.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>25 Years</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Suresh Babu. R</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Mathematics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., M.Ed., M.phil.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>27 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Dr. P. Rajarajeshwari</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Mathematics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>Dr.Ph.D In Mathematics.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>16 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Siva Kumar. H</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Mathematics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Ed.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>23 Years</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
					</table>


				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Arul Raj. N</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Chemistry</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., M.Ed., Ph.D.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>19 Years</td>
						</tr>
					</table>
				</div>

			</div>
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Siva Kumar. M</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Chemistry</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., B.Ed., M.Phil., Ph.D.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>13 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Rebeca Sofiya Joice. M</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Chemistry</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>5 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Chelladurai. G</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Chemistry</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Ed.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>15 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Sudha. V</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Chemistry</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Phil.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>5 Years</td>
						</tr>
					</table>
				</div>
				<div class="clear"></div>

			</div>
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Victor Antony Raj. M</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Physics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., Ph.D.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>15 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">

					<h3 style="text-align: center;">Revathy Bai. S</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Physics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc Bio-Physics.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>3 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Thomas. T</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Physics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Ed.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>21 Years</td>
						</tr>
					</table>

				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Malai Durai. M</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Physics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., Ph.D.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>10 Years</td>
						</tr>
					</table>

				</div>

				<div class="clear"></div>
			</div>
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Shankar. C</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Physics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Ed., M.Phil.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>17 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Ganesh. T</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Physics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., Ph.D.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>7 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Antony Raj. A</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Physics</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., Ph.D.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>2 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Anandh. B. K. S</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Biology</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Ed., M.Phil.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>23 Years</td>
						</tr>
					</table>
				</div>


			</div>


			<div class="clear"></div>
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Mayilvelan. V</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Biology</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Ed., M.Phil.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>14 Years</td>
						</tr>
					</table>
					<br>
				</div>


				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Sankaran. B</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Biology</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., Ph.D.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>15 Years</td>
						</tr>
					</table>
					<br>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Pazhanisamy. M</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Biology</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., B.Ed.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>15 Years</td>
						</tr>
					</table>
					<br>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Thirunavukkarasu. N</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Biology</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., B.Sc., B.Ed.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>18 Years</td>
						</tr>
					</table>
				</div>

			</div>
			<div class="clear"></div>

			<div class="section group">

				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Umanath. R</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Biology</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., M.Phil., Ph.D., B.A.M.S.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>13 Years</td>
						</tr>
					</table>
				</div>

				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">Raj Kumar. K</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>Subject</td>
							<td>Biology</td>
						</tr>
						<tr>
							<td>Qualification</td>
							<td>M.Sc., Mphil., B.Ed., Ph.D.</td>
						</tr>
						<tr>
							<td>Experience</td>
							<td>13 Years</td>
						</tr>
					</table>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">&nbsp;&nbsp;&nbsp;</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						</tr>
					</table>
					<br>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3 style="text-align: center;">&nbsp;&nbsp;&nbsp;</h3>
					<table class="table" style="margin-bottom: 10px;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						</tr>
					</table>
					<br>
				</div>

			</div>
			<div class="clear"></div>
		</div>
	</div>
	<s:include value="edutel_home_footer.jsp" />
</body>
</html>