<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.footer {
	padding: 15px;
}

.footer-grids {
	padding-bottom: 0px;
}
</style>
<div class="footer">
	<div class="wrap">
		<div class="footer-grids">
			<div class="footer-grid">
				<h3>Quotes</h3>
				<s:include value="i_edutel_quotes.jsp" />
			</div>
			<div class="footer-grid" style="width: 48%;">
				<h3>JEE Main Toppers</h3>
				<s:include value="i_edutel_jeemain_gallery.jsp" />
			</div>
			<div class="footer-grid footer-lastgrid">
				<h3>CONTACT US</h3>

				<div class="footer-grid-address" style="border: 1px solid #c4c4c2;">
					<br> <br> <br>
					<p>
						Phone.<%=PropFactory.getInstance().getProperty(
					EGeneral.EduTel_Phone)%></p>
					<p>
						Mobile:
						<%=PropFactory.getInstance().getProperty(
					EGeneral.EduTel_Mobile)%></p>
					<p>
						Email:<a class="email-link" href="#"><%=PropFactory.getInstance().getProperty(
					EGeneral.EduTel_Mail)%></a>
					</p>
					<br> <br> <br> <br>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('.newsContainer').vTicker({
		speed : 3000,
		pause : 5000,
		height : 200,
		animation : 'fade',
		mousePause : true
	});

	
</script>