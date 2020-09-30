// Sticky Header vars
var stickyHeader;
var stickyHeaderOriginPos;
var stickyHeaderHeight = 0;
var sliderHeight = 0;

(function($) {
    "use strict";
	
	$(document).ready(function ($) {
		
		changeCF7CustomPreloader();
		
		$(function () {
			$('[data-toggle="tooltip"]').tooltip();
		})
		
		$('#searchModal').on('shown.bs.modal', function (e) {
			$('#searchform .search-input').focus();
		});
		
		$('.stm_fancybox').fancybox({
			fitToView	: false,
	        padding     : 0,
	        autoSize	: true,
	        closeClick	: false,
	        maxWidth    : '100%',
	        maxHeight   : '90%',
	        beforeShow: function () {
		        $('body').addClass('stm_locked');
	            this.title = $(this.element).attr("data-caption");
	        },
	        beforeClose: function () {
		         $('body').removeClass('stm_locked');
	        },
	        helpers:  {
		        title : {
		            type : 'inside'
		        },
		        overlay : {
			        locked : false
		        }
		    },
		});
		
		
		
		$('.video_course_preview').click(function(e){
			var url = $(this).attr('data-fancybox');
			e.preventDefault();
			e.stopPropagation();
			if(typeof url != 'undefined') {
				$.fancybox({
					type: 'iframe',
					href: url,
					fitToView	: true,
			        padding     : 0,
			        autoSize	: true,
			        closeClick	: false,
			        maxWidth    : '100%',
			        maxHeight   : '90%',
			        beforeShow: function () {
				        $('body').addClass('stm_locked');
			        },
			        beforeClose: function () {
				         $('body').removeClass('stm_locked');
			        },
			        helpers : {
				        overlay : {
				            locked : false
				        }
				    }
			    });
			}
		})
		
		/* Isotope */
		$( function() {
			// init Isotope
			if($('.wait-for-images').length) {
				if (typeof imagesLoaded == 'function') {
					$('.wait-for-images').imagesLoaded(function() {
						$('#stm_isotope').isotope({
							itemSelector: '.stm-isotope-item'
						});
					});
				}
			}
			
			// bind sort button click
			$('.gallery_terms_list a').click(function(){
				$(this).closest('li').addClass('active').siblings().removeClass('active');
				
				$('.stm-isotope-item').removeClass('stm-isotope-item-filtered');
				var selector = $(this).attr('data-filter');
				// This code hides filtered items, instead of changing opacity, to enable it, selector needs '.'
				$('#stm_isotope').isotope({ filter: selector });
				return false;
			});		  
		});

	    footerToBottom();
	    
	    // Is on screen
	    $.fn.is_on_screen = function(){
            var win = $(window);
            var viewport = {
                top : win.scrollTop(),
                left : win.scrollLeft()
            };
            viewport.right = viewport.left + win.width();
            viewport.bottom = viewport.top + win.height();

            var bounds = this.offset();
            bounds.right = bounds.left + this.outerWidth();
            bounds.bottom = bounds.top + this.outerHeight();

            return (!(viewport.right < bounds.left || viewport.left > bounds.right || viewport.bottom < bounds.top || viewport.top > bounds.bottom));
        };
        
        
        $('select').select2({
	        width: '100%',
        });
        
        if( $('.course_table').length > 1 ) {
			productTableCountLessons();
	    }
		
		$('.panel-title>a.tapable').click(function(){
			var collapsed = $(this).hasClass('collapsed');
			if(collapsed){
				$(this).closest('.panel').addClass('panel-collapsed');
			} else {
				$(this).closest('.panel').removeClass('panel-collapsed');
			}
		});
		
		review_rating_stars();
		
		// Event form validation
		$('.event_popup_form').on('submit', function () {
	        var $this = $(this);
	        $($this).removeClass('error');
	        $($this).find('.loading').css({'visibility': 'visible'});
	        $(this).ajaxSubmit({
	            url: ajaxurl,
	            dataType: 'json',
	            success: function (data) {
	                $($this).find('.loading').css({'visibility': 'hidden'});
	                if (data['redirect_url']) {
	                    top.location.href = data['redirect_url'];
	                } else if( data['success'] ){
	                    $($this).replaceWith('<p class="alert alert-success heading_font">' + data['success'] + '</p>');
	                } else {
	                    for (var k in data['errors']) {
	                        $($this).find('input[name="event[' + k + ']"]').addClass('error');
	                        $($this).find('textarea[name="event[' + k + ']"]').addClass('error');
	                    }
	                }
	            }
	        });
	        $($this).find('.error').live('hover', function () {
	            $(this).removeClass('error');
	        });
	        return false;
	    });
		
		menu_animated_line();
		
		mobileMenu();
		
		categoryFilter();
		
		// Events onload
	    $(window).load(function () {
	        footerToBottom();
	        
	        // Sticky header window load
	        if($('#header').hasClass('sticky_header')){
	        	stickyHeader = $('#header.sticky_header .header_default');
	        	stickyHeaderOriginPos = stickyHeader.offset().top;
	        	stickyHeaderHeight = stickyHeader.height() + 25;
	        	
	        	sticky_header();
	        };
	        
	        // Scroll event
			$(window).scroll(function() {
				sticky_header();
			});
			
			// Woo gallery Carousel
			woo_gallery_carousel();
			
			composerRTL();
	    });
	
	});
	
	// Resize events
	$(window).resize(function () {
	    footerToBottom();
	    
	    composerRTL();
	    
	    menu_animated_line();
	});
	
	
	
	/* Custom functions */
	
	// Sticky header
	function sticky_header() {
		if($('#header').hasClass('sticky_header')) {
			var currentPos = $(window).scrollTop();
			
			// Transparent header is best viewed on revolution slider
			if($('.rev_slider').length < 1) {
				if(currentPos > stickyHeaderOriginPos - 1) {
					stickyHeader.addClass('fixed');
					$('#header').css({
						'padding-bottom': stickyHeaderHeight + 'px'
					});
					
					if($('#header').hasClass('transparent_header')){
						$('#header').addClass('scrolling');
						$('.logo_transparent_static').addClass('hidden').removeClass('visible');
						$('.logo_colored_fixed').addClass('visible').removeClass('hidden');
					}
				} else {
					stickyHeader.removeClass('fixed');
					$('#header').css({
						'padding-bottom': 0
					});
					
					
					if($('#header').hasClass('transparent_header')){
						$('#header').removeClass('scrolling');
						$('.logo_transparent_static').addClass('visible').removeClass('hidden');
						$('.logo_colored_fixed').addClass('hidden').removeClass('visible');
					}
				}
				
			} else {
				/* When revolution slider == true and header == transparent */
				if( $('#header').hasClass('transparent_header') ) {
					sliderHeight = $('.rev_slider').height();
					
					if(currentPos > stickyHeaderOriginPos + sliderHeight - 200) {
						stickyHeader.addClass('fixed_invisible_top');
					}else {
						stickyHeader.removeClass('fixed_invisible_top');
					}
	
					if(currentPos > stickyHeaderOriginPos + sliderHeight - 45) {
						stickyHeader.addClass('fixed');
						$('#header').css({
							'padding-bottom': stickyHeaderHeight + 'px'
						});
						
						
						if($('#header').hasClass('transparent_header')){
							$('#header').addClass('scrolling');
							$('.logo_transparent_static').addClass('hidden').removeClass('visible');
							$('.logo_colored_fixed').addClass('visible').removeClass('hidden');
						}
					} else {
						stickyHeader.removeClass('fixed');
						$('#header').css({
							'padding-bottom': 0
						});
						
						if($('#header').hasClass('transparent_header')){
							$('#header').removeClass('scrolling');
							$('.logo_transparent_static').addClass('visible').removeClass('hidden');
							$('.logo_colored_fixed').addClass('hidden').removeClass('visible');
						}
					}
				} else {
					/* Rev slider == true header != transparent */
					if(currentPos > stickyHeaderOriginPos - 1) {
						stickyHeader.addClass('fixed');
						$('#header').css({
							'padding-bottom': stickyHeaderHeight + 'px'
						});
						
						if($('#header').hasClass('transparent_header')){
							$('#header').addClass('scrolling');
							$('.logo_transparent_static').addClass('hidden').removeClass('visible');
							$('.logo_colored_fixed').addClass('visible').removeClass('hidden');
						}
					} else {
						stickyHeader.removeClass('fixed');
						$('#header').css({
							'padding-bottom': 0
						});
						
						
						if($('#header').hasClass('transparent_header')){
							$('#header').removeClass('scrolling');
							$('.logo_transparent_static').addClass('visible').removeClass('hidden');
							$('.logo_colored_fixed').addClass('hidden').removeClass('visible');
						}
					}
				}
			}
		}
	}
	
	function footerToBottom() {
	    $("body").css("padding-bottom", $("#footer").outerHeight());
	    var windowHeight = $( window ).height();
		var footerHeight = $( '#footer' ).outerHeight();
		if(footerHeight + 50 > windowHeight ) {
			$('#footer').css({'position':'absolute'});
		} else {
			$('#footer').removeAttr('style');
		}
	}
	
	function woo_gallery_carousel() {
		if($('.images .thumbnails').length > 5) {
			$('.gallery-btn').removeClass('hidden');
		}
		$('.images .thumbnails').carouFredSel({
			scroll   : {
			    items: 1,
				fx : "direct",
		        duration : 600,                         
		        pauseOnHover : true
		    },
		    auto: {
				play: false,
		        timeoutDuration: 5000
		    },
		    swipe: { 
			    onTouch: true 
			},
		    width: "100%",
		    height: "auto",
		    responsive: true,
		    items: {
		        visible: {
		            min:1,
					max:5
		        },
		        height: '100%'
		    },
			onCreate: function () {
				var btn_height = 0;
				btn_height = $('.images .thumbnails').height();
				$('.gallery-btn').css({
					'height': btn_height+'px',
					'line-height': btn_height+'px',
				});
				
	            $(window).on('resize', function () {
	                $(".images .thumbnails")
	                	.parent()
	                	.add($(".images .thumbnails"))
	                	.height($(".images .thumbnails")
	                	.children()
	                	.first()
	                	.height()
	                );
    				btn_height = $('.images .thumbnails').height();
					$('.gallery-btn').css({
						'height': btn_height+'px',
						'line-height': btn_height+'px',
					});
	            }).trigger('resize');
	        },
	        prev: {
		    	button: function() {
	            	return $(this).closest('.stm_woo_gallery-wrapper').find('.gallery-prev');
	            }
	        },
		    next: {
		        button: function() {
		            return $(this).closest('.stm_woo_gallery-wrapper').find('.gallery-next');
		        }
		    },
		    
	    });
	    
	    $('.stm_thumnbails_gallery > a').click(function(e){
		    e.preventDefault();
		    var slideTo = $(this).attr('data-custom');
			
		    $('.stm_custom_product_gallery .stm_product_gallery_images').removeClass('active');
		    $('.stm_custom_product_gallery .stm_product_gallery_images[data-custom="' + slideTo +'"]').addClass('active');
		    
		    var oneSlide = 0;
		    $('.stm_custom_product_gallery .stm_product_gallery_images').removeClass('duplicate');
		    $('.stm_custom_product_gallery .stm_product_gallery_images').each(function(){
			    if( $(this).hasClass('active') ) {
				    oneSlide ++;
				    if(oneSlide>1) {
					    $(this).addClass('duplicate');
				    }
			    }
		    })
	    })
	}
	
	function productTableCountLessons(){
			var i = 0;
			var speed = 200;
			$('.course_lessons_section .course_table tr').each(function(){
				var el = this;
				speed += 400;
				setTimeout(function(){
					var number_td = $(el).find('.number')
					i++;
					number_td.addClass('number-visible').text(i);
					if(i>9 && i<99 ) {
						number_td.addClass('overten');
					} else if(i>99) {
						number_td.addClass('overthousand');
					} else {
						number_td.addClass('belowten')
					}
			    }, speed);
			});
	}
	
	function review_rating_stars() {
		$( ".woo_stm_rating_fields a" ).hover(
			function() {
				$(this).addClass('filled');
				$(this).prevAll().addClass('filled');
			}, function() {
				$(this).removeClass('filled');
				$(this).prevAll().removeClass('filled');
			}
		);
		
		$( ".woo_stm_rating_fields a" ).click(function(){
			$( ".woo_stm_rating_fields a" ).removeClass('filled-active');
			$(this).addClass('filled-active');
			$(this).prevAll().addClass('filled-active');
		})
	}
	
	function menu_animated_line() {
		menu_animated_line_first_pos();
		$('.header-menu>li').hover(
			function() {
				$('.magic_line').removeClass('line_visible');
				$(this).find('.magic_line').addClass('line_visible');
			}, function() {
				$('.magic_line').removeClass('line_visible');
				menu_animated_line_first_pos();
			}
		);
	}
	
	var appendOnce = 0;
	function menu_animated_line_first_pos() {
		appendOnce++;
		var hasCurrentMenuItem = false;
		var li_anchestor = 'undefined';
		$('.header-menu>li').each(function(){
			if(appendOnce == 1) {
				var maxLineWidth = 0;
				maxLineWidth = $(this).width();
				$(this).append('<div class="magic_line"></div>');
				$(this).find('.magic_line').css({
					'max-width': maxLineWidth + 'px',
				})
			}
			if($(this).hasClass('current-menu-item') || $(this).hasClass('current-menu-parent') || $(this).hasClass('current-menu-ancestor')) {
				hasCurrentMenuItem = true;
				li_anchestor = $(this);
			}
		});
		if(hasCurrentMenuItem && li_anchestor != 'undefined') {
			var lineFirstW = li_anchestor.innerWidth() - 28 ;
			var lineFirstPos = li_anchestor.position().left + 14;
			$(li_anchestor).find('.magic_line').addClass('line_visible');
		} 
	}
	
	function changeCF7CustomPreloader(){
		if( typeof cf7_custom_image != 'undefined') {
			$('.ajax-loader').attr('src', cf7_custom_image + 'ajax-loader.gif');
		}
	}
	
	function mobileMenu() {
		$(".header-menu-mobile .header-menu > li.menu-item-has-children > a").after('<span class="arrow"><i class="fa fa-angle-right"></i></span>');
		
		$('.header-menu-mobile .header-menu .arrow').click(function(){
			$(this).toggleClass('active');
			$(this).closest('li').toggleClass('opened');
			$(this).closest('li').find('> ul.sub-menu').slideToggle(300);
		})
		
		$(".header-menu-mobile .header-menu > li.menu-item-has-children > a").click(function (e) {
	        if( $(this).attr('href') == '#' ){
		        e.preventDefault();
	            $(this).closest('li').find(' > ul.sub-menu').slideToggle(300);
	            $(this).closest('li').toggleClass('opened');
	            $(this).closest('li').find('.arrow').toggleClass('active');
	        }
	    });
	}
	
	function categoryFilter() {
		$('select#product_categories_filter').on("select2:select", function (e) { 
			var link = $(this).val();
			window.location.href = link;
		});
	}
	
	function composerRTL() {
		if($('body').hasClass('rtl')) {
			$('div[data-vc-full-width="true"]').each(function(){
				var left = parseFloat($(this).css('left'));
				left = -left;
				$(this).css({
					left: left + 'px',
				});
			})
		}
	}
	
	if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
		$('footer').css('position','absolute');
	}
	
	if(typeof $.fn.swipe != 'undefined') {
		$.fn.swipe.defaults.excludedElements = "button, input, select, textarea, .noSwipe"
	}

	$('.stm_product_meta_single_page table.variations select').live("change", function() {
		$(this).parent().find('.select2-selection__rendered').text($(this).find('option[value="'+ $(this).val() +'"]').text());
	});
	
})(jQuery);