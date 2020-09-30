<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="/jsp/common/init.jsp">
</s:include>
<s:include value="/jsp/common/logo_header.jsp">
</s:include>


<%
	response.setContentType("text/html; charset=UTF-8");
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	request.setCharacterEncoding("UTF8");
%>

<head>
<!-- Basic Page Needs -->
<title>EduTel</title>
<!-- Mobile Specific Metas -->
<!-- Main Style -->
<link rel="stylesheet" href="css/edutelstyle/style.css">
<link rel="stylesheet" href="css/form.css">
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
  <![endif]-->
<!-- Favicons -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/metrojs.js"></script>
<link rel="stylesheet" type="text/css" href="css/metrojs.css">

<script src="js/jquery.reveal.js"></script>
<script src="js/jquery.bxslider.js"></script>
<!-- bxSlider CSS file -->
<link href="css/jquery.bxslider.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.validate.js"></script>

<script type="text/javascript">
	function redirectLoginPageEmp() {
		document.loginFormEmp.action = "loginAuthentication.do?LOGIN_ATTEMPT=1";
		document.loginFormEmp.submit();
	}
</script>
<script language="javascript">

	var elements = [ "passWord", "submitbtn" ];
	if (document.addEventListener) 
	{
		document.addEventListener("keyup", keyCapt, false);
	} 
	else 
	{
		document.attachEvent("onkeyup", keyCapt);
	}

	function keyCapt(e) 
	{
		if (typeof window.event != "undefined") 
		{
			e = window.event;//code for IE
		}
		if (e.type == "keyup") 
		{
			if (e.keyCode == 13) 
			{
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
</head>
<%
	String lastLoginID = "";
	if (session.getAttribute(ESession.UserObject.getAttribute()) != null)
	{
		response.sendRedirect("redirectLanding.do");
		return;
	}
	else
	{

		boolean isDRSite = PropFactory.getInstance().getProperty(EDR.DR_Site).equals(ConstInterface.YES);

		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equalsIgnoreCase(ConstInterface.LASTLOGINID))
					lastLoginID = cookie.getValue();
			}
		}

	}
%>

<body class="sub_body2">

	<div id="wrap" class="boxed">

		<header>
			<div class="top-bar">
				<div class="container clearfix">
					<div class="slidedown">

						<div class="eight columns">
							<div class="phone-mail" style="margin-left: -70px;">
								<a><i class="icon-phone"></i> Phone : <%= PropFactory.getInstance().getProperty(EGeneral.EduTel_Phone) %></a>
								<a><i class="icon-phone"></i> Mobile : <%= PropFactory.getInstance().getProperty(EGeneral.EduTel_Mobile) %></a>
								<a><i class="icon-envelope-alt"></i> Mail : <%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail) %></a>
							</div>
							<!-- End phone-mail -->
						</div>
						<!-- End social-icons -->

					</div>
				</div>
				<!-- End Container -->

			</div>
			<!-- End top-bar -->

			<div class="main-header">
				<div class="container clearfix">
					<a href="#" class="down-button"><i class="icon-angle-down"></i></a>
					<!-- this appear on small devices -->

					<div class="logo">
						<a href="indexweb.jsp"> <img src="images/logo.png" width="150"
							height="88" />
						</a>

					</div>
					<!-- End Logo -->

					<div class="two-thirds column">
						<!-- Nested Nav-->
						<div class="menu-button">Menu</div>


						<%@ taglib prefix="s" uri="/struts-tags"%>

						<!-- Nested Nav-->
					</div>
				</div>
				<!-- End Container -->
			</div>
			<!-- End main-header -->

		</header>
		<!-- <<< End Header >>> -->


		<div class="container whiteBg clearfix" style="" onload="loadWindow()">
			<div class="latest-title">
				<p>Employee Login Form</p>
			</div>
			<div id="signupwrapper">
				<s:form name="loginFormEmp" id="loginFormEmp" method="post"
					action="/edutel/loginAuthentication.do?LOGIN_ATTEMPT=1"
					show_alert="1" onsubmit="return Validate(this);">
					<div class="loginPage">
						<s:hidden name="loginOption" id="loginOption" value="Employee"></s:hidden>
						<div class="leftImage">
							<img src="images/login.png" />
						</div>
						<div class="rightFields">
							<table style="">
								<tr>
									<td align="left" class="usertext">User
										ID&nbsp;&nbsp;&nbsp;</td>
									<td align="right"><input name="userId" type="text"
										class="loginUserId" id="userId" value="<%=lastLoginID%>"
										validate="not_empty" msg="User Id should not be empty"
										fieldname="User Id" tabindex="1" /></td>
								</tr>

								<tr>
									<td align="left" class="usertext">Password&nbsp;&nbsp;&nbsp;
									</td>
									<td align="right"><input name="passWord" type="password"
										autocomplete="off" class="loginPassword" id="passWord"
										validate="not_empty" msg="Password should not be empty"
										fieldname="Password" tabindex="2" /></td>
								</tr>
								<tr>
									<td align="center" colspan="2"><s:hidden
											name="loginAttempt" value="%{'1'}">
										</s:hidden> <input type="button" class="buttonLogin" name="commit"
										value="Login" href="javascript:void(0);"
										onclick="redirectLoginPageEmp();" /> <input type="button"
										class="buttonLogin" name="clear" value="Reset"
										onclick="onresetCalled();" /></td>
								</tr>
							</table>
						</div>
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

					<div class="news">
						<h3 class="title">Director's Message</h3>
						<div id="tweets-footer" class='tweet query footer'></div>
						<div class="newsContainer">
							<ul>
								<li><div>1) First think yourself that who You are &
										what are you made for?.</div></li>
								<li><div>2) This is the one of your Life's decision
										so don't let it be given in the hands of others.</div></li>
								<li><div>3) Friends are only for your good time
										sharing but not for your career related. So never shake off
										your hands with them regarding this.</div></li>
								<li><div>4) Relatives are only your advisers but not
										your Life decision bond. So their advices are only to listen
										but not always to be implement.</div></li>
								<li><div>5) Parents, off course, no doubt plays a
										good role in shaping your career but sometimes they too are
										unaware about the latest scope & demand of the subject in the
										market. So instead of fighting with them, your task is to tell
										them the about the latest technologies & ideas in the current
										world situation.</div></li>
								<li><div>6) Last but not the least, YOU are the only
										one who can decide yourself that where you have to GO........?</div></li>
								<li><div>For this you can recognize yourself from the
										very beginning</div></li>
								<li><div>Your inner traits</div></li>
								<li><div>Your hidden talents</div></li>
								<li><div>Your passion for your dreams</div></li>
								<li><div>Your sources to implement your ideas</div></li>
								<li><div>Your financial & physical supporters</div></li>
								<li><div>Your challenges in the life’s</div></li>
								<li><div>Your moods of Depression</div></li>
								<li><div>Your Luck & Fate</div></li>

							</ul>
						</div>

						<script type="text/javascript" src="js/jquery.vticker-min.js"></script>
						<%-- <script src="js/main.js"></script> --%>
						<script type="text/javascript">
    $('.newsContainer').vTicker({ 
		speed: 500,
		pause: 3000,
		animation: 'fade',
		mousePause: true,
	});
    </script>




					</div>

					<div class="one-third column widget">
						<div class="subscribe">
							<h3 class="title"
								style="float: right; margin: 2px 0px 18px 50px;">Subscribe</h3>
							<div class="map">
								<iframe width="300" height="150" frameborder="0" scrolling="no"
									marginheight="0" marginwidth="0"
									src="https://maps.google.com/maps?f=q&;source=s_q&amp;hl=en&amp;geocode=&amp;q=Thiru+Nagar+2nd+Street,+Vadapalani&amp;aq=t&amp;sll=13.058555,80.210259&amp;sspn=0.006302,0.010568&amp;ie=UTF8&amp;hq=&amp;hnear=2nd+St,+Thiru+Nagar+Colony,+Tiru+Nagar,+Vadapalani,+Chennai,+Tamil+Nadu+600026,+India&amp;t=m&amp;ll=13.058576,80.210238&amp;spn=0.012542,0.025749&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
								<br />
								<small><a
									href="https://maps.google.co.in/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Chennai,+Tamil+Nadu&amp;aq=0&amp;oq=chennai&amp;sll=13.082827,80.229762&amp;sspn=0.203656,0.338173&amp;ie=UTF8&amp;hq=&amp;hnear=Chennai,+Tamil+Nadu&amp;t=m&amp;z=10&amp;ll=13.052414,80.250825"
									style="color: #fff; text-align: left">View Larger Map</a></small>

							</div>
						</div>
					</div>
					<!-- End Subscribe Widget -->

				</div>
				<!-- End container -->
			</div>
		</div>
		<!-- End footer-top -->

		<div class="footer-down">
			<div class="container clearfix">

				<div class="eight columns">
					<span class="copyright"> &copy;2013 <a href="indexweb.jsp">Edutel
							Academy</a>. All Rights Reserved. by <a
						href="http://apollocreative.in" target="_blank">Apollo
							Creative Solution Pvt Ltd.</a></span>
				</div>
				<!-- End copyright -->

				<ul class="social" id="css3">


					<li class="facebook"><a href="http://www.facebook.com/"><strong>Facebook</strong></a>
					</li>

					<li class="linkedin"><a href="http://www.linkedin.com/"><strong>LinkedIn</strong></a>
					</li>

					<li class="twitter"><a href="http://twitter.com/"><strong>Twitter</strong></a>
					</li>
				</ul>

			</div>
			<!-- End container -->
		</div>
		<!-- End footer-top -->

	</footer>
	<!-- <<< End Footer >>> -->

	</div>
	<!-- End wrap -->



	<script src="js/jquery.flexnav.min.js" type="text/javascript"></script>
	<script type="text/javascript">$(".flexnav").flexNav();</script>

	<script type="text/javascript">
    	$('.bxslider').bxSlider({
		  mode: 'fade',
		  auto: true
		});
    </script>

</body>
</html>
