<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUsers"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="css/jquery.boxes.min.css" />
<script type="text/javascript" src="js/jquery.boxes.min.js"></script>
<style>
.logoutHover {
	border-color: #666 #aaa #bbb #888;
	border-width: 4px 3px 3px 4px;
	color: #000;
}

ul#navigation {
	position: fixed;
	margin: 0px;
	padding: 0px;
	top: 120px;
	left: 0px;
	list-style: none;
	z-index: 9999;
}

ul#navigation li {
	width: 100px;
}

ul#navigation li a {
	display: block;
	margin-left: -100px;
	width: 80px;
	height: 70px;
	background-repeat: no-repeat;
	background-position: center center;
	border: 0px solid #AFAFAF;
	-moz-border-radius: 0px 10px 10px 0px;
	-webkit-border-bottom-right-radius: 10px;
	-webkit-border-top-right-radius: 10px;
	-khtml-border-bottom-right-radius: 10px;
	-khtml-border-top-right-radius: 10px;
	opacity: 1;
	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=10);
}

ul#navigation .home a {
	background-image: url(images/png/home.png);
	width: 65px;
	height: 65px;
}

ul#navigation .message a {
	background-image: url(images/png/message.png);
	width: 65px;
	height: 65px;
}

ul#navigation .blueprint a {
	background-image: url(images/png/blueprint.png);
	width: 65px;
	height: 65px;
}

ul#navigation .keypoints a {
	background-image: url(images/png/keypoints.png);
	width: 65px;
	height: 65px;
}

ul#navigation .ebooks a {
	background-image: url(images/png/ebooks.png);
	width: 65px;
	height: 65px;
}

ul#navigation .alerts a {
	background-image: url(images/png/information.png);
	width: 65px;
	height: 65px;
}

ul#navigation .library a {
	background-image: url(images/png/library.png);
	width: 65px;
	height: 65px;
}

ul#navigation .forms a {
	background-image: url(images/png/app_48.png);
	width: 65px;
	height: 65px;
}
</style>
<script>
	var msgBox ;
	$(function() {
		$('#navigation a').stop().animate({
			'marginLeft' : '-25px'
		}, 200);

		$('#navigation > li').hover(function() {
			$('a', $(this)).stop().animate({
				'marginLeft' : '5px'
			}, 200);
		}, function() {
			$('a', $(this)).stop().animate({
				'marginLeft' : '-25px'
			}, 200);
		});
	});

	function alertBox(title, msgContent, windowWidth, windowHeight) {
		msgBox = $(".alert-message")
				.boxes(
						{
							height : windowHeight,
							message : msgContent,
							messString : false,
							width : windowWidth,
							height : windowHeight,
							minWidth : windowWidth,
							maxWidth : windowWidth,
							minHeight : windowHeight,
							maxHeight : windowHeight,
							autoClose : true,
							autoResize : true,
							clickOut : false,
							cancelBtnShow : true,
							cancelBtn : "Close",
							cancel : function() {
							},
							title : "<div align='center' style='background-color:#336699;height:30px;'><span style='color:#ffffff;font-size:18px; font-weight:bold;'>"
									+ title + "</span></div>"
						});
		$(".alert-message").trigger('click');
		$(".alert-message").unbind('click');
	}

	function confirmBox(title, msgContent, windowWidth, windowHeight, url) {
		msgBox = $(".alert-message")
				.boxes(
						{
							height : windowHeight,
							message : msgContent,
							messString : false,
							width : windowWidth,
							height : windowHeight,
							minWidth : windowWidth,
							maxWidth : windowWidth,
							minHeight : windowHeight,
							maxHeight : windowHeight,
							autoClose : 0,
							autoResize : true,
							clickOut : false,
							okBtnShow : true,
							cancelBtnShow : true,
							okBtn : "Ok",
							cancelBtn : "Cancel",
							ok : function() {
								window.location.href = url + "&uid="
										+ Math.random();
							},
							cancel : function() {
							},
							title : "<div align='center' style='background-color:#336699;height:30px;'><span style='color:#ffffff;font-size:18px; font-weight:bold;'>"
									+ title + "</span></div>"
						});
		$(".alert-message").trigger('click');
		$(".alert-message").unbind('click');
	}
</script>
<div class="top-bar">
	<a class="alert-message">&nbsp;&nbsp;</a>
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
			<span style="font-size: 11px; font-weight: bold;" class="logoutHover"><a href="logoutPage.do"><img src="images/logout.png" align="middle"
					width="35" height="35" />LOGOUT</a></span>
		</div>
		<!-- End slidedown -->
	</div>
	<div style="float: right; width: 250px;">
		<br>
		<%
			IUsers usr = null;

			String userImagePath = "";
			String originalUserName = "";
			String originalEmployeeId = "";
			String usDateOfJoin = "";
			if (session.getAttribute(ESession.UserObject.getAttribute()) != null)
			{
				originalUserName = (String) session.getAttribute(ESession.OriginalUserName.getAttribute());
				originalEmployeeId = (String) session.getAttribute(ESession.OriginalEmployeeId.getAttribute());
				userImagePath = (String) session.getAttribute(ESession.UserImagePath.getAttribute());
				usDateOfJoin = (String) session.getAttribute(ESession.UserJoinedDate.getAttribute());
			}
			else
			{
				//response.sendRedirect("/index.do");
				return;
			}
		%>
		<table>
			<tr>
				<td width="70%" align="right" valign="top"><span style="font-size: 13px; font-weight: bold;">Welcome&nbsp;<a
						href="userPreUpdate.do?usEmployeeId=<%=originalEmployeeId%>"><%=originalUserName%></a></span> <br> <br> <span style="font-size: 11px;"> joined
						on <%=usDateOfJoin%></span></td>
				<td width="5%">&nbsp;&nbsp;</td>
				<td width="25%" valign="top"><div>
						<a href="userPreUpdate.do?usEmployeeId=<%=originalEmployeeId%>"><img src="<%=userImagePath%>" class="thumb" width="50px" height="50px" /></a>
					</div></td>
			</tr>
		</table>
	</div>
	<!-- End Container -->
</div>
<!-- End top-bar -->
<div class="main-header" style="height: 46px;" align="left">
	<div class="container clearfix" style="width: 850px;" align="left">
		<a href="#" class="down-button"><i class="icon-angle-down"></i></a>
		<!-- this appear on small devices -->
		<div class="logo">
			<img src="images/logo.png" />
		</div>
		<!-- End Logo -->
		<div style="width: 800px;">
			${maMenuHTML}
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		</div>
		<!-- End Menu -->
	</div>
	<!-- End Container -->
</div>
<!-- End main-header -->
<%
	if (session.getAttribute(ESession.UserObject.getAttribute()) != null)
	{
		IUsers user = (IUsers) session.getAttribute(ESession.UserObject.getAttribute());
		if (user.isStudent())
		{
%>
<div style="align: left; float: left; left: 0px; top: 60px; width: 80px;">
	<ul id="navigation">
		<li class="home"><div style="align: left; float: left; width: 70px; background-color: #145164; top: 46px; border: 0px solid #145164;">
				<a href="dashBoardStudent.do" title="Home"></a>
			</div></li>
		<li>&nbsp;</li>
		<li>&nbsp;</li>
		<li class="ebooks"><div style="align: left; float: left; width: 70px; background-color: #145164; top: 46px; border: 0px solid #145164;">
				<a href="onlineEBookRepository.do" title="EBooks"></a>
			</div></li>
		<li>&nbsp;</li>
		<li>&nbsp;</li>
		<li class="blueprint"><div style="align: left; float: left; width: 70px; background-color: #145164; top: 46px; border: 0px solid #145164;">
				<a href="onlineEBluePrintRepository.do" title="Exam BluePrint"></a>
			</div></li>
		<li>&nbsp;</li>
		<li>&nbsp;</li>
		<li class="keypoints"><div style="align: left; float: left; width: 70px; background-color: #145164; top: 46px; border: 0px solid #145164;">
				<a href="onlineEKeyPointsRepository.do" title="KeyPoints"></a>
			</div></li>
		<li>&nbsp;</li>
		<li>&nbsp;</li>
		<li class="message"><div style="align: left; float: left; width: 70px; background-color: #145164; top: 46px; border: 0px solid #145164;">
				<a href="viewQueryAnswer.do" title="Discussion Board"></a>
			</div></li>
		<li>&nbsp;</li>
		<li>&nbsp;</li>
		<li class="alerts"><div style="align: left; float: left; width: 70px; background-color: #145164; top: 46px; border: 0px solid #145164;">
				<a href="infoAlertSearch.do" title="Information and Alerts"></a>
			</div></li>
		<li>&nbsp;</li>
		<li>&nbsp;</li>
		<li class="library"><div style="align: left; float: left; width: 70px; background-color: #145164; top: 46px; border: 0px solid #145164;">
				<a href="viewQueryAnswer.do?p=1" title="Library"></a>
			</div></li>
		<li>&nbsp;</li>
		<li>&nbsp;</li>
		<li class="forms"><div style="align: left; float: left; width: 70px; background-color: #145164; top: 46px; border: 0px solid #145164;">
				<a href="viewOnlineFormRequest.do" title="Forms"></a>
			</div></li>
		<li>&nbsp;</li>
		<li>&nbsp;</li>
	</ul>
</div>
<%
	}
	}
%>
