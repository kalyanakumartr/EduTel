<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(function() {
		for (var i = 1; i < 11; i++)
			$('#m' + i).removeClass("active");
		$('#m${param.menuActive}').addClass("active");
	});

	function onCalled(url) {
		window.location.href = url;
	}
</script>
<style>
.telephone span {
	font: italic 800 15px/15px "Open Sans", sans-serif;
}

.nav li {
	padding: 10px 15px;
}
</style>
<div class="header">
	<div class="wrap">
		<div class="header-top">
			<div class="logo">
				<a href="loadPage.do?p=Home"><img src="images/logo.png" alt="" /></a>
				&nbsp;&nbsp;&nbsp;&nbsp; <span class="telephone"
					style="color: white;"> Phone : <%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Phone)%>
					| E-Mail : <%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail)%>
				</span>

			</div>
			<div class="telephone">
				<span class="number" style="font-size: 15px;"> <img
					src="images/png/user-login.png" style="width: 16px;" alt="" /><a
					style="color: white;" data-toggle="modal" href="#login-modal">Login</a>
				</span> <span><img src="academia/images/watch.png" alt=""
					style="width: 16px;" />Contact Us</span><span class="number"
					style="font-size: 15px;"><%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mobile)%></span>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="menu">
		<div class="wrap">
			<div class="top-nav">
				<ul class="nav">
					<li id="m1"><a href="loadPage.do?p=Home">Home</a></li>
					<li id="m2"><a href="loadPage.do?p=AboutUs">About</a></li>
					<li id="m3"><a href="loadPage.do?p=Gallery">Gallery</a></li>
					<li id="m4"><a href="edutel_static/index.html" target="_blank">Engg./Medical</a></li>
					<li id="m5"><a href="loadPage.do?p=Careers">Careers</a></li>
					<li id="m6"><a href="loadPage.do?p=ResourceTeam">Resource
							Team</a></li>
					<li id="m7"><a href="loadPage.do?p=ContactUs">Contact</a></li>
				</ul>
				<div class="clear"></div>
			</div>

		</div>

	</div>
</div>
<div class="leftAdvertisementDiv"></div>
<div class="rightAdvertisementDiv"></div>