<%@ taglib prefix="s" uri="/struts-tags"%>

<s:include value="edutel_home_init.jsp" />

<s:include value="edutel_home_header-menu-new.jsp" />

	
	<div class="map_area">
		<div class="container-fulid">
			<div class="map">
			   <!-- Start contact-map -->
					<div class="contact-map">
						<div id="#">
						<iframe  src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1943.3215530691682!2d80.20926950337403!3d13.058374506440229!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3a5266955e7852e7%3A0xccf9af97e0e6bd38!2sEdutel+Academy!5e0!3m2!1sen!2sin!4v1507377363308" width="100%" height="400" style="vertical-align:top; border:0" allowfullscreen></iframe>

						
						</div>
					</div>
				<!-- End contact-map -->
			</div>							
		</div>	
	</div>	
	<div class="contact_area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="title">
						<p class="lead">
							We are always here to hear from you.
						</p>
					</div>
				</div>
			</div>		
			<div class="row">
				<!-- start  blog left -->
				<div class="col-md-4 col-sm-4">
					<div class="contact-address">	
						<div class="media">
							<div class="media-left">
								<i class="fa fa-phone"></i>
							</div>
							<div class="media-body">
								<h4 class="media-heading">Phone</h4>
								<p>
									<span class="contact-emailto">+044-43849090</span>
								</p>
							</div>
						</div>
						<div class="media">
							<div class="media-left">
								<i class="fa fa-envelope"></i>
							</div>
							<div class="media-body">
								<h4 class="media-heading">Email</h4>
								<p>
									<span class="contact-emailto"><a href="#">admin@edutelacademy.com</a></span>
								</p>
							</div>
						</div>
						<div class="media">
							<div class="media-left">
								<i class="fa fa-map-marker"></i>
							</div>
							<div class="media-body">
								<h4 class="media-heading">Address</h4>
								<p>
									<span class="contact-emailto">
									Edutel Academy

									No-18/4D, 1st Floor,  <br/>
									
									Thiru Nagar 2nd Street,<br/>
									
									Vadapalani, Chennai -600 026.<br/>
									
									LM:100 ft Road, Near Hotel Ambika Empire. <br/>
									
									Phone.044-43849090   <br/>
									
									Mobile: +917401259090< <br/>
									
									Email:admin@edutelacademy.com <br/>
																		
									</span>
								</p>
							</div>
						</div>							
					</div>				
				</div>
				<div class="col-md-8 col-sm-8">
					<div class="contact_us">
					<form action="mail.php" method="post">
						<div class="form-group">
							<div class="col-md-4 col-sm-4">
								<p class="fnone"><label class="" for="Name">Name <em>*</em></label></p>
							</div>
							<div class="col-md-8 col-sm-8">
								<div class="i_box">									
									<input type="text" name="name" id="Name"/>								
								</div>						
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-4 col-sm-4">
								<p class="fnone"><label class="" for="Email">Email <em>*</em></label></p>
							</div>
							<div class="col-md-8 col-sm-8">
								<div class="i_box">									
									<input type="email" name="email" id="Email"/>							
								</div>						
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-4 col-sm-4">
								<p class="fnone"><label class="" for="mes">Message <em>*</em></label></p>
							</div>
							<div class="col-md-8 col-sm-8">
								<div class="i_box">									
									<textarea name="comment" id="mes" cols="30" rows="10"></textarea>							
								</div>						
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-4 col-sm-4"></div>
							<div class="col-md-8 col-sm-8">
								<p class=""><button type="submit" name="ok" class="read_more buttonc">submit</button></p>
							</div>							
						</div>
					</form>
					</div>
				</div>			
			</div>
		</div>
	</div>	
	<!--end courses  area -->
	<!-- breadcrumb-area start -->
	<div class="breadcrumb-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumb">
						<ul>
							<li><a href="index.html">Home</a> <i class="fa fa-angle-right"></i></li>						
							<li>Contact Us</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
 <s:include value="edutel_home_footer.jsp" /> 
</body>
</html>