<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF8");
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", -1); //prevents caching at the proxy server
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
<html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<!--<![endif]-->
<head>

<link type="text/css" rel="stylesheet" href="css/dropdown.css">
<link type="text/css" rel="stylesheet" href="css/dropdown_popupmenu.css">
<link type="text/css" rel="stylesheet" href="css/dropdown_one.css">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jquery.reveal.js"></script>
<script type="text/javascript" src="js/jquery.bxslider.js"></script>
<link type="text/css" rel="stylesheet" href="css/jquery.bxslider.css" />

<link type="text/css" rel="stylesheet" href="css/style/style.css">
<link type="text/css" rel="stylesheet" href="css/stylelogin.css" />
<script type="text/javascript" src="js/commonUtil.js"></script>
<title><%=PropFactory.getInstance().getProperty(
					EGeneral.Project_Title)%></title>
<script>
	var elements = [ "passWord", "submitbtn" ];
	if (document.addEventListener) {
		document.addEventListener("keyup", keyCapt, false);
	} else {
		document.attachEvent("onkeyup", keyCapt);
	}

	function keyCapt(e) {
		if (typeof window.event != "undefined") {
			e = window.event;//code for IE
		}
		if (e.type == "keyup") {
			if (e.keyCode == 13) {
				var keyStatus = 0;
				var elementVal = document.activeElement.id;
				if (elementVal.length == 0) {
					var elementlist = document.getElementById("passWord");
					if (elementlist.length == 0)
						elementlist.focus();
					else
						onsubmitCalled();
				} else {
					if (elementVal == "passWord" || elementVal == "submitbtn") {
						onsubmitCalled();
					}

				}
				if (keyStatus == 0) {
					var elementlist = document.getElementById("passWord");
					if (elementlist.length == 0)
						elementlist.focus();
					else
						onsubmitCalled();
				}
			}
		}
	}

	function onsubmitCalled() {
		if (Validate(document.forms["loginForm"])) {
			document.forms["loginForm"].submit();
		}
	}

	function onresetCalled() {
		document.forms["loginForm"].reset();
	}

	function loadWindow() {
		$("#errorSpan").show();
		var userId = $("#userId").val();
		if (userId == "")
			$("#userId").focus();
		else
			$("#passWord").focus();
	}
</script>
<style>

/* Service Box v1 */
.service-box-v1 {
	text-align: center;
	padding: 15px;
}

.service-box-v1 i {
	/* padding: 15px; */
	font-size: 30px;
}

.service-box-v1 a {
	color: #fff;
}

.service-box-v1 p {
	color: #fff;
}

.service-box-v1 h2 {
	/* padding: 15px; */
	font-size: 20px;
	margin-bottom: 0px;
	line-height: 25px;
}

.service-box-v1:hover {
	background: #d73d04;
	transition: all 0.4s ease-in-out;
	-o-transition: all 0.4s ease-in-out;
	-moz-transition: all 0.4s ease-in-out;
	-webkit-transition: all 0.4s ease-in-out;
}

.service-box-v1:hover i,.service-box-v1:hover p,.service-box-v1:hover h2,.service-box-v1:hover div
	{
	color: #fff;
}

.modal-dialog {
	width: 1024px;
}

h2,.h2 {
	margin-top: 0;
}
</style>
</head>

<body class="sub_body2">
	<s:set var="loginOption">${param.loginOption}</s:set>
	<s:hidden name="loginOption" id="loginOption" value="%{#loginOption}"></s:hidden>
	<div id="wrap" class="boxed">
		<header>
		<div class="top-bar">
			<div class="container clearfix">
				<div class="slidedown">
					<div class="eight columns">
						<div class="phone-mail" align="right">
							<a>Phone :<i class="icon-phone"> <%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Phone)%></i>
							</a>&nbsp;|&nbsp; <a>Mobile : <i class="icon-phone"><%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mobile)%></i>
							</a>&nbsp;|&nbsp; <a>E-Mail : <i class="icon-envelope-alt"><%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail)%></i>
							</a>
						</div>
						<!-- End phone-mail -->
					</div>
				</div>
				<!-- End slidedown -->
			</div>
			<!-- End Container -->
		</div>
		<!-- End top-bar -->
		<div class="main-header">
			<div class="container clearfix">
				<a href="#" class="down-button"><i class="icon-angle-down"></i></a>
				<!-- this appear on small devices -->
				<div class="logo">
					<img src="images/logo.png" width="150" height="88" />
				</div>
				<!-- End Logo -->
			</div>
			<!-- End Container -->
		</div>
		<!-- End main-header --> </header>
		<!-- <<< End Header >>> -->
		<br> <br> <br>
		<div class="container whiteBg clearfix">
			<div class="latest-title">
				<s:if test="#loginOption=='Employee'">
					<p>Login Form e</p>
				</s:if>
				<s:else>
					<p> Login Form s</p>
				</s:else>
			</div>
			<div id="signupwrapper" align="center">
				<s:form name="loginForm" id="loginForm" method="post"
					action="loginAuthentication.do?LOGIN_ATTEMPT=1" show_alert="1"
					onsubmit="return Validate(this);">
					<div>
						<table>
							<tr>
								<td><br> <br> <img src="images/login.png"
									style="height: 100px;" width="100px;" /></td>
								<td>
									<div id="loginForm"
										style="height: 60%; width: 400px; border: 2px solid #999; border-color: #ececec; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;">
										<table align="center" cellpadding="0" cellspacing="0"
											width="100%"
											style="padding: 10px; border: 1px; border-color: gray;">
											<tr>
												<td valign="top" align="center" width="20%" colspan="1"
													height="30px;">&nbsp;</td>
												<td valign="top" align="left" width="80%" colspan="3"
													height="30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:if
														test="hasActionErrors()">
														<s:iterator value="actionErrors">
															<span class="errorMessage" id="errorSpan"
																style="color: red; font-weight: bold;"><s:property
																	escape="false" /></span>
														</s:iterator>
													</s:if></td>
											</tr>
											<tr>
												<td align="left" class="usertext" valign="top"><span
													style="valign: top">&nbsp;&nbsp;&nbsp;User
														ID&nbsp;&nbsp;&nbsp;</span></td>
												<td align="right"><input name="userId" type="text"
													class="loginUserId" id="userId" value=""
													validate="not_empty" msg="User Id should not be empty"
													fieldname="User Id" tabindex="1" /></td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext">&nbsp;&nbsp;&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext" valign="top"><span
													style="valign: top">&nbsp;&nbsp;&nbsp;Password&nbsp;&nbsp;&nbsp;</span></td>
												<td align="right"><input name="passWord"
													type="password" autocomplete="off" class="loginPassword"
													id="passWord" validate="not_empty"
													msg="Password should not be empty" fieldname="Password"
													tabindex="2" /></td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td height="20px;">&nbsp;</td>
												<td height="20px;" colspan="2" nowrap align="right">&nbsp;</td>
											</tr>
											<tr>
												<td align="right" colspan="3"><s:hidden
														name="loginAttempt" value="%{'1'}"></s:hidden> <input
													type="button" class="buttonLogin" name="commit"
													value="Login" href="javascript:void(0);"
													onclick="onsubmitCalled();" /> &nbsp;&nbsp; <input
													type="button" class="buttonLogin" value="Reset"
													onclick="onresetCalled()" /> &nbsp;&nbsp; <s:if
														test="#loginOption=='Student'">
														<a href="serialKeyValidate.do"><input type="button"
															class="buttonLogin" value="Registration" /></a>
													</s:if>&nbsp;&nbsp;&nbsp;&nbsp;</td>
											</tr>
											<tr>
												<td align="left" class="usertext">&nbsp;&nbsp;&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<!-- End services -->
	<footer>
	<div class="footer-top">
		<div class="footercon">
			<div class="container clearfix">
				<%-- <div class="news">
					<%@include file="i_edutel_director_msg.jsp"%>
				</div>
				<div class="one-third column widget">
					<div class="subscribe" style="width: 400px;">
						<h3 class="title" style="float: right; margin: 2px 0px 0px 0px;">Subscribe</h3>
						<div class="map">
							<iframe width="400" height="150" frameborder="0" scrolling="no"
								marginheight="0" marginwidth="0"
								src="https://maps.google.com/maps?f=q&;source=s_q&amp;hl=en&amp;geocode=&amp;q=Thiru+Nagar+2nd+Street,+Vadapalani&amp;aq=t&amp;sll=13.058555,80.210259&amp;sspn=0.006302,0.010568&amp;ie=UTF8&amp;hq=&amp;hnear=2nd+St,+Thiru+Nagar+Colony,+Tiru+Nagar,+Vadapalani,+Chennai,+Tamil+Nadu+600026,+India&amp;t=m&amp;ll=13.058576,80.210238&amp;spn=0.012542,0.025749&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
							<br /> <small><a
								href="https://maps.google.com/maps?f=q&;source=embed&amp;hl=en&amp;geocode=&amp;q=Thiru+Nagar+2nd+Street,+Vadapalani&amp;aq=t&amp;sll=13.058555,80.210259&amp;sspn=0.006302,0.010568&amp;ie=UTF8&amp;hq=&amp;hnear=2nd+St,+Thiru+Nagar+Colony,+Tiru+Nagar,+Vadapalani,+Chennai,+Tamil+Nadu+600026,+India&amp;t=m&amp;ll=13.058576,80.210238&amp;spn=0.012542,0.025749&amp;z=14&amp;iwloc=A"
								style="color: #0000FF; text-align: left">View Larger Map</a></small>
						</div>
					</div>
				</div> --%>
				<!-- End Subscribe Widget -->
			</div>
			<!-- End container -->
		</div>
	</div>
	<%@include file="../common/i_footer.jsp"%> <!-- End footer-top -->
	</footer>
	<!-- <<< End Footer >>> -->
	<!-- End wrap -->
</body>
</html>
