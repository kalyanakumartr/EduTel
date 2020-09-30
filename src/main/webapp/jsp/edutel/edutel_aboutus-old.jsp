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
.newsContainer {
	margin: 0;
}

.newsContainer li {
	font-size: 12px;
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	min-height: 40px;
	margin: 0px 0px 0px 0px;
	padding: 0px 0px 10px 0px !important;
	border: 0px solid #000;
}

.newsContainer li img {
	float: left;
	margin: 0;
}

.newsContainer li div {
	font-size: 12px;
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	line-height: 20px;
	padding: 10px;
	padding-top: 5px;
	padding-bottom: 5px;
	color: #ffffff;
}

.newsContainer li div {
	border: none;
}

.testimo {
	margin-top: 5px;
}

.testimo ul {
	
}

.testimo li {
	position: relative;
	margin-bottom: 15px;
}

.testimo li div.cntnt {
	border: 1px solid #C4C4C2;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
}

.testimo li div.cntnt:hover {
	background: ##555555
}

.testimo li div.cntnt:hover p,.testimo li div.cntnt:hover p a {
	color: #fff;
	cursor: pointer
}

.testimo li div.cntnt p {
	padding: 10px 10px 0 10px;
	color: #808181;
	font-size: 12px;
	font-weight: normal;
}

.testimo li div.cntnt p a {
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	color: #0088cc;
	text-decoration: none;
}

.testimo li div.thumb {
	width: 52px;
	height: 42px;
	background: #0088cc;
	padding: 0 5px 5px 5px;
	-webkit-border-bottom-right-radius: 3px;
	-webkit-border-bottom-left-radius: 3px;
	-moz-border-radius-bottomright: 3px;
	-moz-border-radius-bottomleft: 3px;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
}
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
	color: black;
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

.testimonials p {
	text-indent: 0px;
}

.about_desc p span {
	color: black;
}

.testimonials {
	padding-top: 10px;
}
</style>
<body>



	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">2</s:param>
	</s:include>
	<span></span>
	<br>
	<div class="wrap">
		<div class="about-top" style="text-align: center;">
			<img src="images/gallery.jpg" alt="gallery">
		</div>
		<div class="main" style="background-color: white;">
			<div class="about-top">
				<div class="cont1 span_2_of_a about_desc">
					<div class="services style-1 home bottom-3"
						style="margin-top: -20px;">

						<div class="container clearfix">

							<div style="margin: 20px; text-align: justify;">
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;About Us</span>
									<p class="link1" style="padding-top: 0px;"></p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">We are India's largest online
											& offline learning portal for 12th standard children. We
											cater to the learning needs of school students of classes XII
											across leading educational boards. We realize that the
											learning needs of every student is unique based on her
											learning style, interests and her past academic history, and
											understanding this is the key to making every student
											successful.</span>
									</p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">At Edutel , we believe that
											with technology we can transcend traditional boundaries and
											truly enable customized self-paced learning. Our experienced
											team of educationists and technologists is passionate about
											changing the status quo and challenging one another
											continuously to provide a seamless product that empowers
											tomorrow's leaders</span>
									</p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">We have introduced numerous
											innovations in the areas of content, delivery formats,
											reports, assessments, gamification techniques and many more.
											Having made a huge difference to the lives of lakhs of
											students, it will be our constant endeavour to be a worthy
											partner in their quest for excellence. Our promise remains
											the same thinking of you</span>
									</p>
								</div>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;Why Choose Edutel academy?</span>
									<p class="link1" style="padding-top: 0px;"></p>
								</div>

								<div class="testimonials">
									<p>
										<span class="quotes-down">Our one-stop services are
											tailor-made to meet the needs of students and their parents
											in different aspects. </span>
									</p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">Edutel is well recognized for
											its reliability. For its professional service and teams of
											experienced staff in the representative offices, Edutel is
											highly regarded within the industry by institutes, parents
											and students alike.</span>
									</p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">Edutel is an education portal
											that provides interactive study material for students of 12th
											standard Tamil Nadu state (Samacheer Kalvi) and CBSE boards.
										</span>
									</p>
								</div>

								<div class="testimonials">
									<p>
										<span class="quotes-down">Complete with elaborate Full
											year unique Support program with Test Series, multimedia
											tutorials, application procurement & processing , practice
											tests and expert help etc, </span>
									</p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">we endeavour our support for
											the students and help them to score more, in addition to
											curriculum aligned study material </span>
									</p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">Our support has an extensive
											personality section that helps students identify and enhance
											their Self Analysis for a holistic development. </span>
									</p>
								</div>
								<div class="banner-box3">
									<span class="text20">&nbsp;&nbsp;Our Mission</span>
									<p class="link1" style="padding-top: 0px;"></p>
								</div>

								<div class="testimonials">
									<p>
										<span class="quotes-down">Being a bridge across
											cultures and support for academic pursuit of the younger
											generation is our mission. </span>
									</p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">Our experienced education
											counsellors are ready to advise students on courses and
											career opportunities according to their educational
											background, interests, aptitude, and aspirations. Have a
											broad network with education providers, we are able to
											suggest a wide range of study options and programs to meet
											the students needs </span>
									</p>
								</div>
								<div class="testimonials">
									<p>
										<span class="quotes-down">We also stay in regular
											contact with our students and liaise with schools and parents
											in monitoring students' progress. </span>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="bottom-grid3 bottom-last">
				<s:include value="i_edutel_about_us_director_msg.jsp" />
				<s:include value="i_edutel_aboutus_extra_msg.jsp" />
			</div>

			<div class="clear"></div>
		</div>
	</div>
	<div class="clear"></div>
	<s:include value="edutel_home_footer.jsp" />
</body>
</html>