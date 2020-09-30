(function ($) {
 "use strict";
	 /*----------------------------
	 jQuery MeanMenu
	------------------------------ */
	$('.mobile_memu nav').meanmenu({
		meanScreenWidth: "991",
		meanMenuContainer: ".mobile_memu",
	});
	/*----- main slider -----*/	
	$('#mainSlider').nivoSlider({
		directionNav: true,
		animSpeed: 500,
		slices: 18,
		pauseTime: 111110000,
		pauseOnHover: false,
		controlNav: false,
		prevText: '<i class="fa fa-angle-left nivo-prev-icon"></i>',
		nextText: '<i class="fa fa-angle-right nivo-next-icon"></i>'
	});
	/*----------------------------
	 wow js active
	------------------------------ */
	 new WOW().init();
	 
	/*----------------------------
	 owl active
	------------------------------ */  
	  $(".testimonial_owl").owlCarousel({
		  autoPlay: false, 
		  slideSpeed:2000,
		  pagination:true,
		  navigation:false,	  
		  items : 1,
		  /* transitionStyle : "fade", */    /* [This code for animation ] */
		  navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
		  itemsDesktop : [1199,1],
		  itemsDesktopSmall : [980,1],
		  itemsTablet: [768,1],
		  itemsMobile : [479,1],
	  });
	  	/*---------------------
		courses-list
		--------------------- */
	  $(".courses_owl").owlCarousel({ 
			autoPlay: false, //Set AutoPlay to seconds here					 
			slideSpeed:600,
			rewindNav : false,
			items : 4,
			itemsDesktop : [1169,3],
			itemsTablet: [991,2],
			itemsTabletSmall: [767,1],
			itemsMobile : [320,1],							  
			pagination : false,				 
			navigation : true,
			navigationText : ["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"]
	  });	  
	/*---------------------
	 collapse 
	--------------------- */ 
	$('.panel_heading a').on('click', function(){
		$('.panel_heading a').removeClass('active');
		$(this).addClass('active');
	})
	/*---------------------
	 tooltip 
	--------------------- */	
	 $('[data-toggle="tooltip"]').tooltip()		   
	 /*----------------------------
		Menu bar 
	------------------------------ */
	$(".search-open i.search").on('click', function(){
        $(".search-box input").addClass('open').fadeToggle(1000);
        $(this).addClass('hidden');
        $('.search-open i.close').removeClass('hidden');
    });
	$(".search-open i.close").on('click', function(){
        $(".search-box input").removeClass('open').fadeToggle(1000);
        $(this).addClass('hidden');
        $('.search-open i.search').removeClass('hidden');
    });
	/* default settings */
    $('.venobox_custom').venobox({
        framewidth: '700px',        // default: ''
        frameheight: '500px',       // default: ''		
		
	});	
	// scroolup
	$.scrollUp({
		scrollText: '<i class="fa fa-angle-up"></i>',
		easingType: 'linear',
		scrollSpeed: 900,
		animation: 'fade'
	});
	atvImg();
	
})(jQuery); 