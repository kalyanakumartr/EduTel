<!DOCTYPE HTML>
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

<body>
	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">3</s:param>
	</s:include>
	<div class="masterDiv" align="center">
		<div class="wrap">
			<div class="main">
				<div class="banner-box3" align="left">
					<span class="text20">&nbsp;&nbsp;Our Training Gallery</span> <a href="#"
						class="link1"></a>
				</div>
				<s:iterator var="row" begin="1" end="5" step="1">
					<div class="section group">
						<s:iterator var="col" begin="1" end="4" step="1">
							<div class="col_1_of_4 span_1_of_4">
								<img src="academia/images/gallery/pic${row}${col}.jpg" alt="" />
							</div>
						</s:iterator>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</s:iterator>
			</div>
		</div>
		<s:include value="edutel_home_footer.jsp" />
	</div>
</body>
</html>
