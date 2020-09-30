<div class="header_area">
		<div class="container">
			<div class="row">
				<!-- header  logo --> 
				<div class="col-md-4 col-sm-3 col-xs-12">
					<div class="logo"><a href="loadPage.do?p=Home"><img src="academia/img/logo-white.png" alt="Edutel" /></a></div>
				</div>
				<!-- end header  logo --> 
				<div class="col-md-8 col-sm-9 col-xs-12">
					<div>
						<div class="form pull-right">
							<div class="language">
								  <select class="form-lan">
								    <option value="english" selected>English</option>
								   
								  </select>
							</div>						</div>
						<div class="social_icon pull-right">
							<p>
							   <a target="_blank" href="#" class="icon-set"><i class="fa fa-facebook"></i></a> 
							   <a target="_blank" href="#" class="icon-set"><i class="fa fa-twitter"></i></a> 
							   <a target="_blank" href="#" class="icon-set"><i class="fa fa-linkedin"></i></a>
							</p>
						</div>				
					</div>
					<div class="phone_address pull-right clear">
						<p class="no-margin">
						    <small>
							  <span class="text-msg">Have any questions?</span>
							  <span class="icon-set"><i class="fa fa-phone"></i> 044-43849090</span> 
							  <span class="icon-set"><i class="fa fa-envelope"></i>admin@edutelacademy</span> 
						  </small>
						</p>				
					</div>
				</div>
			</div>
		</div>
	</div>
    <!--end header  area --> 
    <!--Start nav  area --> 
	<div class="nav_area">
		<div class="container">
			<div class="row">
				<!--nav item-->
				<div class="col-md-12 col-sm-12 col-xs-12">
					<!--  nav menu-->
					<nav class="menu">
						<ul class="navid pull-left">
						
							<li  id="m1"><a href="loadPage.do?p=Home">Home</a></li>
								
								<li  id="m2"><a href="loadPage.do?p=AboutUs">About Us</a></li>
															
								<li id="m3"><a href="loadPage.do?p=Gallery">Gallery</a></li>
			           	<!--<li id="m4"><a href="edutel_static/index.html" target="_blank">Engg./Medical</a></li> -->
					            <li id="m4"><a href="loadPage.do?p=Careers">Careers</a></li>
					            <li id="m5"><a href="loadPage.do?p=ResourceTeam">Resource
							      Team</a></li>
					             <li id="m6"><a href="loadPage.do?p=ContactUs">Contact</a></li>
					             <li id="m7"><a href="loadPage.do?p=WeAreWith">We Are With</a></li>
					             
						</ul>
					</nav>
					<!--end  nav menu-->	
				</div>
				<!--end nav item -->
			</div>	
		</div>
	</div>
	<!--end nav  area -->
	<!--Start mobile menu  area -->
	<div class="mobile_memu_area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="mobile_memu">
					<!--  nav menu-->
					<nav>
						<ul class="navid">
							<li  id="m1"><a href="loadPage.do?p=Home">Home</a></li>
								
								<li  id="m2"><a href="loadPage.do?p=AboutUs">About Us</a></li>
															
								<li id="m3"><a href="loadPage.do?p=Gallery">Gallery</a></li>
			           	<!--<li id="m4"><a href="edutel_static/index.html" target="_blank">Engg./Medical</a></li> -->
					            <li id="m4"><a href="loadPage.do?p=Careers">Careers</a></li>
					            <li id="m5"><a href="loadPage.do?p=ResourceTeam">Resource
							      Team</a></li>
					             <li id="m6"><a href="loadPage.do?p=ContactUs">Contact</a></li>
					             <li id="m7"><a href="loadPage.do?p=WeAreWith">We Are With</a></li>
					             
						</ul>
					</nav>
					<!--end  nav menu-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end mobile menu  area -->
	<!-- jquery
		============================================ -->
	<script src="academia/js/vendor/jquery-1.11.3.min.js"></script>

	<!-- active JS
		============================================ -->
	<script>
		$(document).ready(function() {
			var activeParam = "${param.activeParam}";
		    if(activeParam=='AboutUs')
		    	$("#m2").addClass('active'); // add the class to the newly clicked link
		    if(activeParam=='Gallery')
		    	$("#m3").addClass('active'); // add the class to the newly clicked link
		    if(activeParam=='Careers')
		    	$("#m4").addClass('active'); // add the class to the newly clicked link
		    if(activeParam=='Resource Team')
		    	$("#m5").addClass('active'); // add the class to the newly clicked link
		    if(activeParam=='ContactUs')
		    	$("#m6").addClass('active'); // add the class to the newly clicked link
		    if(activeParam=='We Are With')
		    	$("#m7").addClass('active'); // add the class to the newly clicked link

		});
	</script>