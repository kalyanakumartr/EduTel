<style>

/* Forms
================================================== */
/* SOCIAL ICONS - GENERAL */
.social {
	list-style: none;
	margin: 30px auto 0px auto;
	width: 160px;
}

.social li {
	display: inline;
	float: left;
	background-repeat: no-repeat;
}

.social li a {
	display: block;
	width: 32px;
	height: 32px;
	padding-left: 0px;
	position: relative;
	text-decoration: none;
}

.social li a strong {
	font-weight: normal;
	position: absolute;
	left: 0px;
	top: -1px;
	color: #fff;
	padding: 3px;
	z-index: 9999;
	text-shadow: 1px 1px 0 rgba(0, 0, 0, 0.75);
	background-color: rgba(0, 0, 0, 0.7);
	-moz-border-radius: 3px;
	-moz-box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
	-webkit-border-radius: 3px;
	-webkit-box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
	border-radius: 3px;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
}

li.delicious {
	background-image: url("edu-images/delicious.png");
}

li.digg {
	background-image: url("edu-images/digg.png");
}

li.facebook {
	background-image: url("edu-images/facebook.png");
}

li.flickr {
	background-image: url("edu-images/flickr.png");
}

li.linkedin {
	background-image: url("edu-images/linkedin.png");
}

li.reddit {
	background-image: url("edu-images/reddit.png");
}

li.rss {
	background-image: url("edu-images/rss.png");
}

li.twitter {
	background-image: url("edu-images/twitter.png");
}

span.copyright {
	padding-top: 7px;
	display: block;
	font-family: Tahoma, Arial, verdana, helvetica, sans-serif;
	font-size: 11px;
	color: #fff;
}
</style>
<script>
	$(document).ready(function() {
		setFooter();
	});

	function setFooter() {
		var docHeight = $(window).height() - 45;
		var footerHeight = $('#footerDiv').height();
		var footerTop = $('#footerDiv').position().top + footerHeight;

		if (footerTop < docHeight) {
			$('#footerDiv').css('margin-top', (docHeight - footerTop) + 'px');
		}

		// Check browser support
		if (typeof (Storage) !== "undefined") {
			// Store
			localStorage.setItem("accessToken", "${accessToken}");
		} else {
			alert("Sorry, your browser does not support. please use chrome browser...");
		}
	}
</script>
<div class="footer-down" id="footerDiv" align="center"
	style="margin-top: 125px;">
	<div class="container clearfix">
		<div class="eight columns">
			<span class="copyright"> All Content Protected by Edutel
				Academy | All Rights Reserved, HBS Technology Solutions © <script>
					document.write(new Date().getFullYear());
				</script> | Violators will be prosecuted<br /> Site best viewed in IE 8.0,
				Mozilla Firefox 10.0 and above
			</span>
		</div>
		<ul class="social" id="css3">
			<li class="facebook"><a href="http://www.facebook.com/"><strong>Facebook</strong></a></li>
			<li class="linkedin"><a href="http://www.linkedin.com/"><strong>LinkedIn</strong></a></li>
			<li class="twitter"><a href="http://twitter.com/"><strong>Twitter</strong></a></li>
		</ul>
	</div>
</div>
