<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(".technology").hover(function() {
		window.open("http://www.hatchbirdsolutions.com");
	});
</script>
<s:include value="i_edutel_login.jsp" />
<div class="copy-right" style="background-color: #001C32">

	<p class="technology">
		<%-- <a href="loadPage.do?p=Disclaimer" target="_blank"
			style="border-bottom: none;">Disclaimer</a></span>&nbsp;&nbsp;&nbsp; |
		&nbsp;&nbsp;&nbsp; <span><a href="loadPage.do?p=WebTerms"
			target="_blank" style="border-bottom: none;">Web-site Terms &
				Conditions</a></span>&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;<span><a
			href="loadPage.do?p=Privacy" target="_blank"
			style="border-bottom: none;">Privacy Policy</a> --%>
		<a href="loadPage.do?p=Disclaimer" target="_blank"
			style="border-bottom: none;">Disclaimer</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a
			href="javascript:void(0)" style="border-bottom: none;">Web-site
			Terms & Conditions</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a
			href="javascript:void(0)" style="border-bottom: none;">Privacy
			Policy</a><br>Technology By <br> All Content Protected by
		Edutel Academy <br> All Rights Reserved, <br>&copy; Copy
		Rights
		<script>
			document.write(new Date().getFullYear());
		</script>
		| Violators will be prosecuted.<br> <a
			href="http://www.hatchbirdsolutions.com" target="_blank"><img
			src="images/banner/hatchbird.png" alt="HatchBird Solutions" /></a>
	</p>
	<br>
</div>
