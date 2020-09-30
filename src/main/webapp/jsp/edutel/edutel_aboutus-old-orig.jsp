<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="edutel_home_init.jsp" />
<html class="no-js" lang="">
    <head>
      
	<div class="header_area">
		<div class="container">
			<div class="row">
				<!-- header  logo --> 
				<div class="col-md-4 col-sm-3 col-xs-12">
					<div class="logo"><a href="index.html"><img src="img/logo.png" alt="" /></a></div>
				</div>
				<!-- end header  logo --> 
				<div class="col-md-8 col-sm-9 col-xs-12">
					<div>
						<div class="form pull-right">
							
						</div>
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
							  <span class="icon-set"><i class="fa fa-phone"></i> <%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Phone)%></span> 
							  <span class="icon-set"><i class="fa fa-envelope"></i> <%=PropFactory.getInstance().getProperty(EGeneral.EduTel_Mail)%></span> 
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
							<li><a href="index.html">Home <i class="fa fa-angle-down"></i></a>
								<ul>
									<li><a href="index-2.html">Home 2</a></li>
									<li><a href="index-3.html">Home 3</a></li>
									<li><a href="index-4.html">Home Box Layout</a></li>
								</ul>
							</li>
							<li><a href="#">Courses <i class="fa fa-angle-down"></i></a>
								<ul>
									<li><a href="courses-item-1.html">Courses List layout 1</a></li>
									<li><a href="courses-item-2.html">Courses List layout 2 </a></li>
									<li><a href="single-courses.html">Course Item </a></li>
								</ul>							
							</li>
							<li><a href="#">Pages <i class="fa fa-angle-down"></i></a>
								<ul>
									<li><a href="faq.html">FAQ </a></li>
									<li><a href="login.html">Login Page  </a></li>
									<li><a href="gellary.html">Image Gallery </a></li>
									<li><a href="about-page.html">About Page </a></li>
									<li><a href="news-bulletin.html">News Bulletin  </a></li>
									<li><a href="registration.html">Registration Form</a></li>
									<li><a href="contract.html">Contacts </a></li>
									<li><a href="404.html">404 </a></li>
								</ul>							
							</li>							
							<li><a href="store.html">Store</a></li>
							<li><a href="blog.html">Blog</a></li>							
							<li><a href="about-page.html">About Us</a></li>
							<li><a href="contract.html">Contact</a></li>
						</ul>
					</nav>
					<!--end  nav menu-->	
					<div class="search pull-right">
						<div class="search-box">
							<input type="text" class="form_control" placeholder="search" />
							<span class="search-open"><i class="fa fa-search search"></i><i class="fa fa-close hidden close"></i></span>
						</div>
					</div>
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
							<li><a href="index.html">Home</a>
								<ul>
									<li><a href="index-2.html">Home 2</a></li>
									<li><a href="index-3.html">Home 3</a></li>
									<li><a href="index-4.html">Home Box Layout</a></li>
								</ul>
							</li>
							<li><a href="#">Courses</a>
								<ul>
									<li><a href="courses-item-1.html">Courses List layout 1</a></li>
									<li><a href="courses-item-2.html">Courses List layout 2 </a></li>
									<li><a href="single-courses.html">Course Item </a></li>
								</ul>							
							</li>
							<li><a href="#">Pages</a>
								<ul>
									<li><a href="faq.html">FAQ </a></li>
									<li><a href="login.html">Login Page  </a></li>
									<li><a href="gellary.html">Image Gallery </a></li>
									<li><a href="about-page.html">About Page </a></li>
									<li><a href="news-bulletin.html">News Bulletin  </a></li>
									<li><a href="registration.html">Registration Form</a></li>
									<li><a href="contract.html">Contacts </a></li>
									<li><a href="404.html">404 </a></li>
								</ul>							
							</li>							
							<li><a href="store.html">Store</a></li>
							<li><a href="blog.html">Blog</a></li>							
							<li><a href="about-page.html">About Us</a></li>
							<li><a href="contract.html">Contact</a></li>
						</ul>
					</nav>
					<!--end  nav menu-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end mobile menu  area -->	
    <!--Start about title  area --> 
	<div class="about_area_s">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="heading_about">
						<h1 class="page-title">
						<small>We believe in us and </small>
							Education is Power 
						</h1>					
					</div>
				</div>
			</div>
		</div>
	</div>
    <!--Start about  area --> 
	<div class="about_area page">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="title">
						<h3 class="module-title about_titlea">
						  <span>At vero eos et accusamus et iusto odio dignissimos</span>
						</h3>
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="content-inner">
						<div class="content-desc">
							<div class="">
								<p class="about_pra_top">Architecto beatae vitae dicta sunt explicabo.</p>
							</div>

							<div class="">
								<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
								
								<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. </p>
								<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>
								<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
								<p>Architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius</p>
							</div>
						  </div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="ab_thumb">
						<img src="img/home1/about1.jpg" alt="" class="img-thumbnail" />
					  </div>				
				</div>
				<div class="col-md-12 col-sm-12">
					<div class="about_bottom_text">
						<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
						<div class="text-center"><a href="#" class="readmore about_more">Get in Touch!</a></div>
					</div>
				</div>				
			</div>
		</div>
	</div>
	<!--end about  area -->	
	<!--start banar  area -->	
	<div class="banar_area">
		<div class="container">
			<div class="row gg">
				<div class="banner_item_area">
				<!--start single banar  item -->	
				<div class="col-md-6 col-sm-12 pdr0">
					<div class="single_banar">
						<div class="banar_icon">
							<i class="fa fa-lightbulb-o"></i>
						</div>
						<div class="banar_content">
							<h2>MISSION</h2>
							<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur</p>
						</div>
					</div>
				</div>
				<!--start single banar  item -->
				<div class="col-md-6 col-sm-12 pdrl">
					<div class="single_banar">
						<div class="banar_icon">
							<i class="fa fa-university"></i>
						</div>
						<div class="banar_content">
							<h2>WORK</h2>
							<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur</p>
						</div>
					</div>
				</div>
				<!--start single banar  item -->	
				<div class="col-md-6 col-sm-12 pdr0">
					<div class="single_banar">
						<div class="banar_icon">
							<i class="fa fa-plane"></i>
						</div>
						<div class="banar_content">
							<h2>INSIGN</h2>
							<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur</p>
						</div>
					</div>
				</div>
				<!--start single banar  item -->
				<div class="col-md-6 col-sm-12 pdrl">
					<div class="single_banar">
						<div class="banar_icon">
							<i class="fa fa-sort-alpha-asc"></i>
						</div>
						<div class="banar_content">
							<h2>BELL</h2>
							<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur</p>
						</div>
					</div>
				</div>				
				</div>
			</div>
		</div>
	</div>
	<!--end banar  area -->	
	<!--start share  area -->
	<div class="share_area">
		<div class="container">
			<div class="row">
				<div class="share-container">
					<!-- single item brand -->
					<div class="col-md-3 col-sm-6 col-lg-3">
						<div class="share-item">
							<div class="brand_content content_left_fb">
								<a href="#">
									<i class="fa fa-facebook"></i>
									<div class="icone_text">
									<p>893K Followers</p>
									<h5>Follow Us</h5>
									</div>
								</a>
							</div>
						</div>
					</div>
					<!-- end single item brand -->
					<!-- single item brand -->
					<div class="col-md-3 col-sm-6 col-lg-3">
						<div class="share-item">
							<div class="brand_content content_left_tw">
								<a href="#">
									<i class="fa fa-twitter"></i>
									<div class="icone_text">
									<p>893K Followers</p>
									<h5>Follow Us</h5>
									</div>
								</a>
							</div>
						</div>
					</div>
					<!-- end single item brand -->
					<!-- single item brand -->
					<div class="col-md-3 col-sm-6 col-lg-3">
						<div class="share-item">
							<div class="brand_content content_left_pn">
								<a href="#">
									<i class="fa fa-google-plus"></i>
									<div class="icone_text">
									<p>893K Followers</p>
									<h5>Follow Us</h5>
									</div>
								</a>
							</div>
						</div>
					</div>
					<!-- end single item brand -->
					<!-- single item brand -->
					<div class="col-md-3 col-sm-6 col-lg-3">
						<div class="share-item">
							<div class="brand_content content_left_in">
								<a href="#">
									<i class="fa fa-linkedin"></i>
									<div class="icone_text">
									<p>893K Followers</p>
									<h5>Follow Us</h5>
									</div>
								</a>
							</div>
						</div>
					</div>
					<!-- end single item brand -->				
				</div>
			</div>
		</div>
	</div>	
	<!--end share  area -->	
	<!--start offer  area -->
	<div class="offer_area">
		<div class="container">	
			<div class="row">
				<div class="col-md-6 col-sm-6 col-lg-6">
					<div class="title">
						<h3 class="offer-title">
							Like what you're learning   <span> Get started today!</span>
						</h3>
					</div>				
					<div class="offer_item">
						<div class="offer_content">							
							<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>
							<a href="#" class="readmore">Sing Up</a>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 col-lg-6">
					<div class="media-box">
						<a class="venobox_custom mfp-iframe video-button text-uppercase" data-type="iframe" href="https://www.youtube.com/embed/JC82Il2cjqA">
						  <i class="fa fa-play-circle-o"></i>
						  <span>Watch Video</span>
						</a>    
					  </div>
				</div>				
			</div>
		</div>
	</div>	
	<!--end offer  area -->
	<!-- breadcrumb-area start -->
	<div class="breadcrumb-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumb">
						<ul>
							<li><a href="index.html">Home</a> <i class="fa fa-angle-right"></i></li>							
							<li>About Us</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- breadcrumb-area end -->	
	<!-- footer  area -->	
	<div class="footer_area">
		<div class="container">
			<div class="row">
				<div class="borderbottom">			
					<div class="col-md-6 col-sm-12">
						<div class="footer_top_left">
							<img src="img/logo-white.png" alt="" />
							<span>Learning language is easier then ever before</span>
						</div>
					</div>
					<div class="col-md-6 col-sm-12">
						<div class="footer_top_right">
						 <span>256 Tutors 20,690 Tutorials 646 Video Courses </span>
						 <a href="" class="read_more">sing up</a>
						</div>
					</div>
				</div>
			</div>
			<!-- widget area -->
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<!--single widget item -->
					<div class="single_widget">
						<div class="widget_thumb">
							<img src="img/home1/camp-1.jpg" alt="" />
						</div>
						<div class="widget_content">
							<h2>Come & enjoy our free webinner</h2>
							<p>Pellentesque habitant morbi tristique senectus et netus et malesuada </p>
							 <a href="" class="read_more">JOIN NOW</a>
						</div>
					</div>
					<!--single widget item -->
					<div class="single_widget">						
						<div class="widget_thumb">
							<img src="img/home1/camp-2.jpg" alt="" />
						</div>
						<div class="widget_content">
							<h2>Come & enjoy our free webinner</h2>
							<p>Pellentesque habitant morbi tristique senectus et netus et malesuada </p>
							 <a href="" class="read_more">JOIN NOW</a>
						</div>
					</div>					
				</div>
				<div class="col-md-3 col-sm-6">
					<!--single widget item -->
					<div class="single_widget">
						<div class="widget_content">
							<h2>Meet Academia</h2>
							<ul>
								<li><a href="#">Get Started</a></li>
								<li><a href="#">Download</a></li>
								<li><a href="#">Scaffolding</a></li>
								<li><a href="#">Base CSS</a></li>
							</ul>
						</div>
					</div>
					<!--single widget item -->
					<div class="single_widget">
						<div class="widget_content">
							<h2>Help and Support</h2>
							<ul>
								<li><a href="#">Get Started</a></li>
								<li><a href="#">Download</a></li>
								<li><a href="#">Scaffolding</a></li>
								<li><a href="#">Base CSS</a></li>
								<li><a href="#">Base CSS</a></li>
							</ul>
						</div>
					</div>					
				</div>
				<div class="col-md-3 col-sm-6">
					<!--single widget item -->
					<div class="single_widget">
						<div class="widget_content">
							<h2>Join Our Community</h2>
							<ul>
								<li><a href="#">Official LESS Website</a></li>
								<li><a href="#">LESS Wiki</a></li>
								<li><a href="#">Source Code</a></li>
								<li><a href="#">Base CSS</a></li>
							</ul>
						</div>
					</div>
					<!--single widget item -->
					<div class="single_widget">
						<div class="widget_content">
							<h2>Subscribe Newsletter</h2>
							<p>Get latest updates, news, survays & offers</p>
							<div class="inputbox">
								<input type="text" placeholder="your email here" name="name"/>
								<button type="submit" class="read_more buttons">Subscribe  <i class="fa fa-graduation-cap"></i></button>
							</div>
						</div>
					</div>
					<!--end single widget item -->
				</div>
			</div>
			<!-- end widget area -->
		</div>
	</div>
	<!-- end footer  area -->
	
		
    	<s:include value="edutel_home_footer.jsp" />
</html>