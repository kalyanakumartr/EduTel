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
	top: 10px;
}

.about_desc p span {
	color: black;
}

.testimonials {
	padding-top: 0px;
}

img {
	left: 22px;
	max-width: 100%;
}

.span_1_of_4 h3 {
	color: #fa6210;
	font-family: "Open Sans", sans-serif;
	font-size: 1.2em;
	margin: 6% 0 2%;
	text-transform: uppercase;
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
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/1imgs.jpg" alt="img"></li>
						</ul>
					</div>
					<h3 style="text-align: center;">Test
						series</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">10
							offline test series all covering the core subject.</span> <span
							class="quotes"></span><span class="quotes-down">More than
							100 online test series all covering the core subject with
							evaluation and doubt clearing sessions.</span> <span class="quotes"></span><span
							class="quotes-down">Various questions and question
							patterns based on your blue print to ensure a complete
							understanding of the subjects, concepts and retention of
							methodologies.</span>
						<br><br>
					</p>

				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/2imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">Online test engine - UNLIMITED
						MCQ'S (core subjects)</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">Take
							test in your weak areas, practise regularly and view result
							instantly with graphical representation of your testing skills. </span>
						<span class="quotes"></span><span class="quotes-down">Results
							generated give a detailed analysis on the students learning and
							answering pattern. </span> <span class="quotes"></span><span
							class="quotes-down">Take up the tests at your convenient
							time and place</span> <span class="quotes"></span><span
							class="quotes-down">Gives an opportunity to the student to
							brush up on weak areas.</span>

					</p>

				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/3imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">E- BOOK DIRECTORY</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">E-Book
							Directory is a daily growing list of freely downloadable e-books,
							documents and guest lecture notes from your core subjects found
							all over the world from experts.</span><br>
						<br>
						<br>
						<br> <br>
					</p>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/4imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">Dash board services</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">Instant
							alerts on the dash board and your mobile phone on current
							happenings in education sector across the world. </span> <span
							class="quotes"></span><span class="quotes-down">Information
							updates on courses, colleges, universities, scholarships,
							educational loans, cut-offs, Entrance exam dates, etc... </span> <br>
						<br> <br> <br>
					</p>
				</div>
				<div class="clear"></div>
			</div>
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/5imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">Query box & library</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">Ask
							any questions on career across the world and get instant answer</span> <span
							class="quotes"></span><span class="quotes-down">(JEE MAIN,
							JEE ADVANCE, AIPMT/NEET,AIPVT,TNEA,TNAU, CMC, JIPMER,AIIMS,
							COMEDK, ST.JOHNS, MGIMS, AFMS, AFMC, CPL, NDA, HOTEL MGT,CLAT
							(LAW), NUS,NTU, SAT,CAT,MAT,XAT, TOEFL, IELETS, MARINE, CIVIL,
							AEROSPACE, BIO-ENGN, Right up to NANO tech.</span>
					</p>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/6imgs.jpg" alt="img"
								style="max-width: 75%;"></li>
						</ul>
					</div>
					<h3 style="text-align: center;">Application forms -
						procurement & processing</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">Get
							any colleges and universities application form across the world
							at your door steps</span> <span class="quotes"></span><span
							class="quotes-down">Get any colleges and universities
							application form across the world at your door steps</span> <span
							class="quotes"></span><span class="quotes-down">Get any
							colleges and universities application form across the world at
							your door steps</span>
					</p>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/7imgs.jpg" alt="img"
								style="left: 7px; max-width: 97%;"></li>
						</ul>
					</div>
					<h3 style="text-align: center;">Psychometric Counselling</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">The
							PMT analysis is a live session conducted by the experts in your
							near by campus Benefits of PMT :</span> <span class="quotes"></span><span
							class="quotes-down">Identifying the right career.</span> <span
							class="quotes"></span><span class="quotes-down">Awareness
							on SWOT analysis (Strength , weakness, opportunities , threats)</span> <span
							class="quotes"></span><span class="quotes-down">It is an
							integrated test to discover all aspects of you.</span> <span
							class="quotes"></span><span class="quotes-down">You and
							parents can rest as there will be ZERO error in your mind.</span>
					</p>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/8imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">Common academy Test (CAT) /
						Compulsory Questions at a Glance</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">Online
							test paper will be created by the experts and Monthly test will
							conducted.</span> <span class="quotes"></span><span class="quotes-down">Compulsory
							questions will be posted in regular frequency, 90% accurate which
							will appear for your board exam.</span> <br>
					</p>
				</div>
				<div class="clear"></div>
			</div>
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/9imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">Key points</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">It
							helps to focus on the area€™s to score full marks and get centum
							in your core subjects </span> <span class="quotes"></span><span
							class="quotes-down">It also can be used as a ready
							reckoner and used to memorize the points in a ease manner. </span> <span
							class="quotes"></span><span class="quotes-down">It covers
							your entire text book question and helps to be meticulous in your
							answers. </span>
					</p>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/10imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">Topperas club</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">Online
							Academy test will be conducted among the entire student across
							tamilnadu from all premium schools.</span> <span class="quotes"></span><span
							class="quotes-down">All Toppers Name with photograph will
							be displayed in the dashboard.</span> <span class="quotes"></span><span
							class="quotes-down">Star of the Topperâ€™s club will be
							awarded with Rs. 1,00,000 /- scholarship.</span>

					</p>

				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/11imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">Smart Guide</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">Separate
							Modules will be posted on Formulae, Definitions, Diagrams,
							Problems, Theorems from the core subjects (PCMB).</span><br> <br>
						<br>
					</p>

					<br>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="image_grid portfolio_4col">
						<ul style="" id="list" class="portfolio_list da-thumbs">
							<li><img src="images/product_details/12imgs.jpg" alt="img">
							</li>
						</ul>
					</div>
					<h3 style="text-align: center;">Entrance Cracker</h3>
					<p>
						<span class="quotes"></span><span class="quotes-down">Practise
							session on MCQ's for JEE-MAIN & AIPMT to crack your engineering
							and medical entrance exams.</span> <br> <br> <br> <br>
					</p>
				</div>
				<div class="clear"></div>

			</div>
		</div>

	</div>
	<div class="clear"></div>
	<s:include value="edutel_home_footer.jsp" />
</body>
</html>