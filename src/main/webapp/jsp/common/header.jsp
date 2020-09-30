<%@ page import="com.hbs.edutel.common.model.interfaces.IUsers"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUserRoles"%>
<%@ page import="com.hbs.edutel.util.CommonUtil"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set name="menuDisplay">${param.menuDisplay}</s:set>
<script type="text/javascript">
	function open_win(url_add) {
		loadCompleted();
		var size = 'width=' + myWidth + ",height=" + self.screen.availHeight
				+ ",top='0',left=0,";
		win = window
				.open(
						url_add,
						'welcome',
						size
								+ 'menubar=no,resizable=yes,status=yes,location=0,toolbar=no,scrollbars=no');
	}

	var win;
	var roleWindow;
	var delAppWindow;
	var delAppWindow1;

	function closeOpenWindows() {

		if (roleWindow != undefined && roleWindow != null && !roleWindow.closed) {
			closeRoleWindows();
		}
		if (delAppWindow != undefined && delAppWindow != null
				&& !delAppWindow.closed) {
			delAppWindow.close();
		}
		if (delAppWindow1 != undefined && delAppWindow1 != null
				&& !delAppWindow1.closed) {
			delAppWindow1.close();
		}
		if (win != null && !win.closed) {
			win.close();
		}
	}

	function doFinalize() {
		closeOpenWindows();
		window.location = "logoutPage.do";
	}

	function open_window(url_add) {
		closeOpenWindows();
		var tempwidth = self.screen.availWidth - 50;
		var tempheight = self.screen.availHeight - 50;
		var size = 'width=' + tempwidth + ",height=" + tempheight
				+ ",top='0',left=0,";
		win = window
				.open(
						url_add,
						'Welcome',
						size
								+ 'menubar=0,resizable=yes,status=0,location=no,toolbar=0,scrollbars=0');
	}
</script>
<%
	IUsers usr = null;

	String originalUserId = null;
	String originalUserName = null;
	String maMenuHTML = null;
	String originalEmployeeId = null;
	if (session.getAttribute(ESession.UserObject.getAttribute()) != null)
	{
		originalUserId = (String) session.getAttribute(ESession.OriginalUserId.getAttribute());
		originalUserName = (String) session.getAttribute(ESession.OriginalUserName.getAttribute());
		originalEmployeeId = (String) session.getAttribute(ESession.OriginalEmployeeId.getAttribute());
		maMenuHTML = (String) session.getAttribute(ESession.MaMenuHTML.getAttribute());

	}
	else
	{
		//response.sendRedirect("/index.do");
		return;
	}
%>
</head>
<body class="sub_body">
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
					<div class="two-thirds column">
						<s:include value="/jsp/common/logo_header.jsp">
							<s:param name="menuDisplay">true</s:param>
							<s:param name="userName"><%=originalUserName%></s:param>
							<s:param name="maMenuHTML"><%=maMenuHTML%></s:param>
						</s:include>
					</div>
					<!-- End Menu -->
				</div>
				<!-- End Container -->
			</div>
			<!-- End main-header -->
		</header>
		<!-- <<< End Header >>> -->
	</div>