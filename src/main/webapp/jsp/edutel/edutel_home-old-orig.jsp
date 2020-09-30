<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<link href="3d_scripts/screen.css" rel="stylesheet">
<script src="3d_scripts/lib/modernizr.min.js"></script>
<link type="text/css" rel="stylesheet" href="css/style/style.css">
<title><%=PropFactory.getInstance().getProperty(EGeneral.Project_Title)%></title>
<script type="text/javascript">
	//<![CDATA[ 
	$(window).load(function() {
		$(".live-tile, .flip-list").not(".exclude").liveTile();
	});//]]>
</script>

</head>
<body class="sub_body">
	<a href="/edutel_static/index.html" target="_blank"><img src="edutel_static/img/Tag.png" style="position:absolute;left:-150px;top:-50px;z-index:1000;cursor:pointer;" /></a>
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
				<!-- End slide down -->
			</div>
			<!-- End Container -->
		</div>
		<!-- End top-bar -->
		<div class="main-header">
			<div class="container clearfix">
				<a href="#" class="down-button"><i class="icon-angle-down"></i></a>
				<div class="logo">
					<a href=""> <img src="images/logo.png" width="150" height="88" />
					</a>
				</div>
				<!-- End Logo -->
				<div class="two-thirds column" style="height: 55px; float: right;">
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
					${maMenuHTML}
				</div>
				<!-- End Menu -->
			</div>
			<!-- End Container -->
		</div>
		<!-- End main-header --> </header>
		<!-- <<< End Header >>> -->
	</div>
	<div class="container" >
		<!--/ Banner -->
		<div class="c_banner">
			<ul class="bxslider">
				<li><img style="margin-left: 100px; margin-top: 70px;"
					src="images/banner/banner2.png" /></li>
				<li><img src="images/banner/banner3.png" /></li>
				<li><img style="margin-left: 100px; margin-top: 45px;"
					src="images/banner/banner4.png" /></li>
				<li><img style="margin-left: 100px; margin-top: 45px;"
					src="images/banner/banner5.png" /></li>
				<li><img style="margin-left: 100px; margin-top: 45px;"
					src="images/banner/banner6.png" /></li>
				<li><img style="margin-left: 100px; margin-top: 45px;"
					src="images/banner/banner7.png" /></li>
			</ul>
		</div>
		<!-- Banner /-->
		<div class="formbg"><%@include file="i_edutel_enquiry_home.jsp"%>
		</div>
	</div>
	<!-- End slider -->
	<div class="services style-1 home bottom-3">
		<div class="container clearfix">
			<div class="imgl">
				<a href="loginPage.do?loginOption=Employee"><img
					src="images/emp.png" width="276" height="188"></a>
			</div>
			<div class="slider_rotate cf">
				<section>
				<div id="viewport-shadow" class="">
					<div id="viewport" class="fixer">
						<div id="box">
							<figure class="slide"> <img
								src="images/3d_slider/img_1.jpg"> </figure>
							<figure class="slide"> <img
								src="images/3d_slider/img_2.jpg"> </figure>
							<figure class="slide"> <img
								src="images/3d_slider/img_3.jpg"> </figure>
							<figure class="slide"> <img
								src="images/3d_slider/img_1.jpg"> </figure>
							<figure class="slide"> <img
								src="images/3d_slider/img_3.jpg"> </figure>
						</div>
					</div>
				</div>
				</section>
			</div>
			<div class="imgr">
				<a href="loginPage.do?loginOption=Student"><img
					src="images/stu.png" width="276" height="188"></a>
			</div>
		</div>
		<!-- End Container -->
	</div>
	<!-- End services -->
	<!-- Start main content -->
	<div class="full">
		<div class="container main-content clearfix">
			<div class="leftbox1">
				<h6>Autobiography</h6>
				<div class="qs">
					<h2>An Autobiography of a Question Paper</h2>
					<h2>How about placing our future into someone's hand? Yes, I
						am the one- A Question Paper,</h2>
					<h2>Who is authoritative to decide an individual's future.</h2>
					<h2>At my very sight, many get nervous, some tremble and
						perspire, while a few rejoice.</h2>
					<h2>That is how I am usually perceived.</h2>
					<a href="javascript:void(0);" class="readmoreAutoBio"><img
						class="readmore" id="readmoreAutoBio" src="images/readmore.png"
						width="75" height="22" /></a>
				</div>
			</div>
			<div class="rightbox1">
				<%@include file="i_edutel_home_testimonial.jsp"%>
			</div>
		</div>
		<!-- <<< End Container >>> -->
		<br clear="all">
	</div>
	<div class="fullwit">
		<div class="container main-content clearfix">
			<h5>Our Support</h5>
			<img src="images/gallery.png" width="1001" height="288" alt="gallery">
		</div>
		<!-- <<< End Container >>> -->
	</div>
	</div>
	<footer>
	<div class="footer-top">
		<div class="footercon">
			<div class="container clearfix">
				<div class="news">
					<%@include file="i_edutel_director_msg.jsp"%>
				</div>
				<div class="one-third column widget">
					<div class="subscribe">
						<h3 class="title" style="float: right; margin: 2px 0px 18px 50px;">Subscribe</h3>
						<div class="map">
							<iframe width="300" height="150" frameborder="0" scrolling="no"
								marginheight="0" marginwidth="0"
								src="https://maps.google.com/maps?f=q&;source=s_q&amp;hl=en&amp;geocode=&amp;q=Thiru+Nagar+2nd+Street,+Vadapalani&amp;aq=t&amp;sll=13.058555,80.210259&amp;sspn=0.006302,0.010568&amp;ie=UTF8&amp;hq=&amp;hnear=2nd+St,+Thiru+Nagar+Colony,+Tiru+Nagar,+Vadapalani,+Chennai,+Tamil+Nadu+600026,+India&amp;t=m&amp;ll=13.058576,80.210238&amp;spn=0.012542,0.025749&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
							<br /> <small><a
								href="https://maps.google.com/maps?f=q&;source=embed&amp;hl=en&amp;geocode=&amp;q=Thiru+Nagar+2nd+Street,+Vadapalani&amp;aq=t&amp;sll=13.058555,80.210259&amp;sspn=0.006302,0.010568&amp;ie=UTF8&amp;hq=&amp;hnear=2nd+St,+Thiru+Nagar+Colony,+Tiru+Nagar,+Vadapalani,+Chennai,+Tamil+Nadu+600026,+India&amp;t=m&amp;ll=13.058576,80.210238&amp;spn=0.012542,0.025749&amp;z=14&amp;iwloc=A"
								style="color: #0000FF; text-align: left">View Larger Map</a></small>
						</div>
					</div>
				</div>
				<!-- End Subscribe Widget -->
			</div>
			<!-- End container -->
		</div>
	</div>
	<%@include file="../common/i_footer.jsp"%> <!-- End footer-top -->
	</footer>
	<!-- <<< End Footer >>> -->
	</div>
	<!-- End wrap -->
	<script src="js/jquery.flexnav.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(".flexnav").flexNav();
	</script>
	<script type="text/javascript">
		$('.bxslider').bxSlider({
			mode : 'fade',
			auto : true
		});
	</script>
	<!--/ 3D Slider -->
	<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
	<script src="3d_scripts/box-slider-all.jquery.min.js"></script>
	<script>
		$(function() {

			var $box = $('#box'), $indicators = $('.goto-slide'), $effects = $('.effect'), $timeIndicator = $('#time-indicator'), slideInterval = 1000;

			var switchIndicator = function($c, $n, currIndex, nextIndex) {
				$timeIndicator.stop().css('width', 0);
				$indicators.removeClass('current').eq(nextIndex).addClass(
						'current');
			};

			var startTimeIndicator = function() {
				$timeIndicator.animate({
					width : '680px'
				}, slideInterval);
			};

			// initialize the plugin with the desired settings
			$box.boxSlider({
				speed : 1000,
				autoScroll : true,
				timeout : slideInterval,
				next : '#next',
				prev : '#prev',
				pause : '#pause',
				effect : 'scrollHorz3d',
				blindCount : 15,
				onbefore : switchIndicator,
				onafter : startTimeIndicator
			});

			startTimeIndicator();

			// pagination isn't built in simply because it's easy to
			// roll your own with the plugin API methods
			$('#controls').on('click', '.goto-slide', function(ev) {
				$box.boxSlider('showSlide', $(this).data('slideindex'));
				ev.preventDefault();
			});

			$('#effect-list').on('click', '.effect', function(ev) {
				var $effect = $(this);

				$box.boxSlider('option', 'effect', $effect.data('fx'));
				$effects.removeClass('current');
				$effect.addClass('current');

				switchIndicator(null, null, 0, 0);
				ev.preventDefault();
			});

		});
	</script>
	<!-- 3D Slider /-->
</body>
</html>