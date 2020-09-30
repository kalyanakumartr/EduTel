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
	top: 15px;
}

.about_desc p span {
	color: black;
}

.testimonials {
	padding-top: 0px;
}
</style>
<body>
	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">2</s:param>
	</s:include>
	<span></span>
	<br>
	<div class="wrap">
		<div class="main">
			<div class="about-top">
				<div class="cont1 about_desc">
					<!-- Start main content -->
					<div class="services style-1 home bottom-3"
						style="margin-top: -20px;">
						<div class="container clearfix">
							<div style="margin: 20px; text-align: justify;">
								<div class="banner-box3">
									<span class="text20">Terms and Conditions</span>
									<p class="link1"></p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Fees
											once paid cannot be refunded at any circumstances whatsoever
											be the reason, even part of the fee cannot be claimed; if
											he/she disinterested during the course.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Student/parent
											must be aware of the course he/she is joining. It is their
											responsibility to clear all doubts arising from the counselor
											or admission dept. </span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Please
											check whether you are eligible for any scholarship/discounts
											provided by Edutel Academy and ask for the same. FYSP
											students must surrender their 25% discount coupon to
											admission staff and ask for the same.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">One
											good quality passport size photo should be pasted on the
											application form. Edutel Academy reserves the right to use
											the photograph and name for publicity in case student secure
											higher ranks in the competitive examinations.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">The
											study center can be changed or shifted to any nearby places
											without prior notice to the students. It is not in general
											but in certain circumstances. Student/Parent need to
											cooperate with the institution.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Study
											material provided to the student will be sufficient to get
											through. But faculty / student will have the freedom to
											teach/refer more from CBSE/ICSE /IB/ MATRIC /SB / OTHERS in
											the benefit of students to get more clarity of the topic.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Any
											change in address or phone number must be informed
											immediately to the office or at the center and submit in
											written.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Edutel
											Academy does not assure for any scholarships, it helps you to
											apply when it opens.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Edutel
											Academy does not give guarantee for any college admissions,
											it helps you to prepare for the examinations conducted by the
											colleges . It helps you to score more marks in the
											board/entrance exams by supporting offline/online. </span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Any
											disputes or difference between you and Edutel Academy
											persuading shall be sought under the jurisdiction of Chennai
											court only.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Parents/guardian
											are advised to leave the campus immediately after they drop
											their ward to the study center.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Edutel
											Academy is not responsible for the loss of any belongings
											like cycle, stationery or electronic devices.</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Mobile
											phone or any other devices not related to class are strictly
											prohibited to the center. Student should bring their own
											drinking water</span>
									</p>
								</div>
								<div class="testimonials">
									<h3></h3>
									<p>
										<span class="quotes"></span><span class="quotes-down">Student
											can come to center in casual/formal dress; but 3/4th pant,
											bare footed, ear rings in case of boys, shorts, banyan, cap
											are restricted.</span>
									</p>
								</div>
							</div>
						</div>
						<!-- End Container -->
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<s:include value="edutel_home_footer.jsp" />
</body>
</html>