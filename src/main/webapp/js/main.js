$(function() {

	
/* ----- Main Menu ----- */
	
	if($().mobileMenu) {
		$('#main-navigation').mobileMenu();
	}
	
	if($().superfish) {
		$("#main-navigation > ul").superfish({
			delay: 150, // delay on mouseout 
	        animation: { height:'show' }, // fade-in and slide-down animation 
	        speed: 'fast', // faster animation speed 
	        autoArrows: false, // disable generation of arrow mark-up 
	        dropShadows: false
		});
	}



/* ----- Carousels & Sliders ----- */
	
	// default flex parameters
	if($().flexslider) {
		$('.flexslider1').flexslider({
			controlNav: false,
			directionNav: false,
			slideshow: true
		});
		$('.flexslider').flexslider({
			controlNav: true,
			direction:"vertical",
			directionNav: false,
			slideshow: true
		});
	}



/* ----- Twitter Feed ----- */
	
	if($().tweet) {
		$(".widget_twitter > div").tweet({
			username: "envato",
			join_text: "auto",
			avatar_size: 0,
			count: 2,
			auto_join_text_default: "",
			auto_join_text_ed: "",
			auto_join_text_ing: "",
			auto_join_text_reply: "",
			auto_join_text_url: "",
			loading_text: "loading tweets..."
	    });
	}




/* ----- Blog ----- */

	if($().masonry) {
		$('#masonry img').each(function(){
			$(this).one("load", function(){
				$('#masonry').masonry({
					itemSelector : '.post'
				});
			}).each(function() {
		    	if(this.complete) $(this).trigger("load");
			});
		});
	}
	
	$(window).resize(function(){
		$('#masonry').masonry({
			itemSelector : '.post'
		});
	});
	
	$(".archive-group hgroup").click(function(){
		$(this).toggleClass("open").next().slideToggle(500);
	});
	


/* ----- Forms ----- */	

	if (!Modernizr.input.placeholder){
		$("input, textarea").each(function(){
			if($(this).val()=="" && $(this).attr("placeholder")!=""){
				$(this).val($(this).attr("placeholder"));
				$(this).focus(function(){
					if($(this).val()==$(this).attr("placeholder")) $(this).val("");
				});
				$(this).blur(function(){
					if($(this).val()=="") $(this).val($(this).attr("placeholder"));
				});
			}
		});
	}
	
	
	
/* ----- Google Map ----- */	
	
	if(typeof(google) !== 'undefined') {
		/* set your coordinates here */
		var myLatlng = new google.maps.LatLng(34.092809,-118.328661);
		
		var mapOptions = {
			scrollwheel: false,
			zoom: 8,
			center: myLatlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP,
		}
		var map = new google.maps.Map(document.getElementById("map"), mapOptions);	
		
		var image = new google.maps.MarkerImage('assets/img/marker.png',
			// This marker is 20 pixels wide by 32 pixels tall.
			new google.maps.Size(61,60),
			// The origin for this image is 0,0.
			new google.maps.Point(0,0),
			// The anchor for this image is the base of the flagpole at 0,32.
			new google.maps.Point(30, 30));
		
		var contentString = '<div id="map-info">'+
		    '<div id="siteNotice">'+
		    '</div>'+
		    '<h2 id="firstHeading" class="firstHeading">Hello!</h2>'+
		    '<div id="bodyContent">'+
		    '<p><b>Simplog</b> is here!'+
		    '</div>'+
		    '</div>';
		
		var infowindow = new google.maps.InfoWindow({
		    content: contentString
		});
	
		var marker = new google.maps.Marker({
			position: myLatlng,
			icon: image,
			title: "Hello from Simplog!"
		});
		
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(map,marker);
		});
		
		// To add the marker to the map, call setMap();
		marker.setMap(map);
	}
	

});
