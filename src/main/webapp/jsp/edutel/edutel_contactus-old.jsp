<!DOCTYPE HTML>
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
.errorMessage {
	color: #FF0000;
	font-size: 12px;
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	text-align: center;
	font-weight: bold;
}

.fltr {
	border: 1px solid #b0b0b0;
	color: #003c60;
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 14px;
	width: 100%;
}

.col-md-3 {
	width: 10%;
	float: left;
	min-height: 1px;
	padding-left: 15px;
	padding-right: 15px;
	position: relative;
}

.col-md-12 {
	width: 100%;
	float: left;
	min-height: 1px;
	padding-left: 15px;
	padding-right: 15px;
	position: relative;
}

.col-md-6 {
	width: 50%;
	float: left;
	min-height: 1px;
	padding-left: 15px;
	padding-right: 15px;
	position: relative;
}

.contact-form input[type="submit"] {
	position: relative;
	float: right;
}
</style>
<body>
	<s:include value="edutel_home_header.jsp">
		<s:param name="menuActive">7</s:param>
	</s:include>
	<div class="wrap">
		<div class="main">
			<div class="contact">
				<div class="col span_1_of_3">
					<div class="contact_info">
						<div class="banner-box3">
							<span class="text20">&nbsp;&nbsp;Find Us Here</span>
							<p class="link1"></p>
						</div>
						<BR>
						<div class="map">
							<iframe width="100%" height="130" frameborder="0" scrolling="no"
								marginheight="0" marginwidth="0"
								src="https://maps.google.co.in/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Lighthouse+Point,+FL,+United+States&amp;aq=4&amp;oq=light&amp;sll=26.275636,-80.087265&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;hnear=Lighthouse+Point,+Broward,+Florida,+United+States&amp;t=m&amp;z=14&amp;ll=26.275636,-80.087265&amp;output=embed"></iframe>
							<br> <small><a
								href="https://maps.google.com/maps?f=q&;source=embed&amp;hl=en&amp;geocode=&amp;q=Thiru+Nagar+2nd+Street,+Vadapalani&amp;aq=t&amp;sll=13.058555,80.210259&amp;sspn=0.006302,0.010568&amp;ie=UTF8&amp;hq=&amp;hnear=2nd+St,+Thiru+Nagar+Colony,+Tiru+Nagar,+Vadapalani,+Chennai,+Tamil+Nadu+600026,+India&amp;t=m&amp;ll=13.058576,80.210238&amp;spn=0.012542,0.025749&amp;z=14&amp;iwloc=A"
								style="color: #666; text-align: left; font-size: 12px">View
									Larger Map</a></small>
						</div>
					</div>
					<div class="company_address ">
						<div class="banner-box3">
							<span class="text20">&nbsp;&nbsp;Head Office</span>
							<p class="link1"></p>
						</div>
						<BR> <span style="font-weight: 16px; color: #076AA6;">Edutel
							Academy</span>
						<p>No-18/4D, 1st Floor,</p>
						<p>Thiru Nagar 2nd Street,</p>
						<p>Vadapalani, Chennai -600 026.</p>
						<p>LM:100 ft Road, Near Hotel Ambika Empire.</p>
						<p>
							Phone.<%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Phone)%></p>
						<p>
							Mobile:
							<%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mobile)%></p>
						<p>
							Email:<a href="#"><%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail)%></a>
						</p>
						<p>
							Follow on: <span>Facebook</span>, <span>Twitter</span>
						</p>
					</div>
				</div>
				<div class="col span_2_of_3">
					<div class="contact-form">
						<h3 style="margin-bottom: 0px;">Contact Us</h3>
						<form name="StudentEnquiryForm" action="studentEnquiry.do"
							onsubmit="return validateForm()" method="post" id="admin_staff">
							<div>
								<s:if test="hasActionErrors()">
									<s:iterator value="actionErrors">
										<span class="errorMessage" id="errorSpan"
											style="color: red; font-weight: bold;"><s:property
												escape="false" /></span>
									</s:iterator>
								</s:if>
							</div>
							<div>
								<span><label>NAME</label></span> <span><input type="text"
									name="studentEnquiry.enquirerName" id="enquirerName"
									class="textbox" required="required"
									onkeyup="adminUserNameValidation(this)" /></span>
							</div>
							<div>
								<span><label>E-MAIL</label></span> <span><input
									type="text" name="studentEnquiry.enquirerEmail"
									id="enquirerEmail" class="textbox" required="required" /></span>
							</div>
							<div>
								<span><label>MOBILE</label></span> <input type="text"
									name="studentEnquiry.enquirerMobileNo" id="enquirerMobileNo"
									class="textbox" maxlength="10" required="required"
									onkeyup="checkIsNumberAndLength(this);" /></span>
							</div>
							<div>
								<span><label>SCHOOL NAME</label></span> <span><input
									type="text" name="studentEnquiry.enquirerSchoolName"
									id="enquirerSchoolName" class="textbox" required="required"
									onkeyup="adminUserNameValidation(this)" /></span>
							</div>
							<div>
								<span><label>BOARD</label></span> <span><select
									name="studentEnquiry.enquirerBoard" id="enquirerBoard"
									class="txt_fild fltr">
										<option value="State_Board">State_Board</option>
										<!-- option value="CBSE">CBSE</option -->
								</select></span>
							</div>
							<div>
								<span><label>CLASS</label></span> <span><select
									name="studentEnquiry.enquirerClass" id="enquirerClass"
									class="txt_fild fltr">
										<option value="12th">12th</option>
								</select></span>
							</div>
							<div>
								<span><label>You are a</label></span>
							</div>
							<div class="col-md-12">
								<span class="col-md-3"><input type="radio"
									name="studentEnquiry.enquirerState" id="enquirerState1"
									value="Student" />Student</span> <span class="col-md-3"><input
									type="radio" name="studentEnquiry.enquirerState"
									id="enquirerState2" value="Parent" />Parent</span> <span
									class="col-md-3"><input type="radio"
									name="studentEnquiry.enquirerState" id="enquirerState3"
									value="Tutor" />Tutor</span> <span class="col-md-3"><input
									type="radio" name="studentEnquiry.enquirerState"
									id="enquirerState4" value="Teacher" />Teacher</span>
							</div>
							<%-- <div>
								<span><label>SUBJECT</label></span>
								<textarea name="studentEnquiry.enquirerSchoolName"
									id="enquirerSchoolName" required="required" maxlength="500"></textarea>
								</span>
		</div>
							<div>
								<input type="hidden" name="studentEnquiry.enquirerBoard"
									id="enquirerBoard" value="State_Board" />
		</div>
							<div>
								<input type="hidden" name="studentEnquiry.enquirerClass"
									id="enquirerClass" value="12th" />
	</div>
							<div>
								<input type="hidden" name="studentEnquiry.enquirerState"
									id="enquirerState" value="Parent" />
							</div> --%>
							<div class="col-md-12">
								<span><input type="submit" value="Submit"></span>
							</div>
						</form>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="banner-box3">
			<span class="text20">&nbsp;&nbsp;Branch Offices</span>
			<p class="link1"></p>
		</div>
		<div class="section group">
			<div class="col_1_of_4 span_1_of_4">
				<div class="banner-box3">
					<span class="text20">&nbsp;&nbsp;Chennai</span>
					<p class="link1" style="height: 48px;"></p>
				</div>
				<br>
				<p>Kasi Estate, No. 84, 2nd street,</p>
				<p>Jafferkhanpet, Ashok Nagar,</p>
				<p>Chennai - 600 083.</p>
				<p>LM:Near Kasi Theatre.</p>
				<p>Phone: +91 74012 59090</p>
				<p>
					Email:
					<%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail)%></p>
			</div>
			<div class="col_1_of_4 span_1_of_4">
				<div class="banner-box3">
					<span class="text20">&nbsp;&nbsp;Coimbatore</span>
					<p class="link1" style="height: 48px;"></p>
				</div>
				<br>
				<p>No-17/18, SKC Building,</p>
				<p>Mill Road,</p>
				<p>Coimbatore-1.</p>
				<p>LM:Near ICICI Bank,Old Bridge.</p>
				<p>Phone: +91 74012 59090</p>
				<p>
					Email:
					<%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail)%></p>
			</div>
			<div class="col_1_of_4 span_1_of_4">
				<div class="banner-box3">
					<span class="text20">&nbsp;&nbsp;Trichy</span>
					<p class="link1" style="height: 48px;"></p>
				</div>
				<br>
				<p>No-66, Vislakshmi Towers,</p>
				<p>3rd Floor, Madurai Road,</p>
				<p>Trichy - 620 008.</p>
				<p>Phone: +91 431 4021090</p>
				<p>
					Email:
					<%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail)%></p>
			</div>
			<div class="col_1_of_4 span_1_of_4">
				<div class="banner-box3">
					<span class="text20">&nbsp;&nbsp;Kumbakonam</span>
					<p class="link1" style="height: 48px;"></p>
				</div>
				<br>
				<p>No:55, Ganesh Nagar,</p>
				<p>Chetty Mandapam,</p>
				<p>Kumbakonam Taluk,</p>
				<p>Thanjavur District.</p>
				<p>Phone: +91 90035 56785</p>
				<p>
					Email:<%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail)%></p>
			</div>
			<div class="clear"></div>
		</div>










	</div>
	<s:include value="edutel_home_footer.jsp" />
</body>
</html>