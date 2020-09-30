/* A polyfill for browsers that don't support ligatures. */
/* The script tag referring to this file must be placed before the ending body tag. */

/* To provide support for elements dynamically added, this script adds
   method 'icomoonLiga' to the window object. You can pass element references to this method.
*/
(function () {
	'use strict';
	function supportsProperty(p) {
		var prefixes = ['Webkit', 'Moz', 'O', 'ms'],
			i,
			div = document.createElement('div'),
			ret = p in div.style;
		if (!ret) {
			p = p.charAt(0).toUpperCase() + p.substr(1);
			for (i = 0; i < prefixes.length; i += 1) {
				ret = prefixes[i] + p in div.style;
				if (ret) {
					break;
				}
			}
		}
		return ret;
	}
	var icons;
	if (!supportsProperty('fontFeatureSettings')) {
		icons = {
			'home': '&#xe900;',
			'house': '&#xe900;',
			'home3': '&#xe901;',
			'house3': '&#xe901;',
			'book': '&#xe902;',
			'read': '&#xe902;',
			'books': '&#xe903;',
			'library': '&#xe903;',
			'cart': '&#xe904;',
			'purchase': '&#xe904;',
			'lifebuoy': '&#xe905;',
			'support': '&#xe905;',
			'mail5': '&#xe906;',
			'contact5': '&#xe906;',
			'google': '&#xe907;',
			'brand': '&#xe907;',
			'google-plus': '&#xe908;',
			'brand2': '&#xe908;',
			'google-plus2': '&#xe909;',
			'brand3': '&#xe909;',
			'google-plus3': '&#xe90a;',
			'brand4': '&#xe90a;',
			'google-drive': '&#xe90b;',
			'brand5': '&#xe90b;',
			'facebook': '&#xe90c;',
			'brand6': '&#xe90c;',
			'facebook2': '&#xe90d;',
			'brand7': '&#xe90d;',
			'facebook3': '&#xe90e;',
			'brand8': '&#xe90e;',
			'instagram': '&#xe90f;',
			'brand10': '&#xe90f;',
			'twitter': '&#xe910;',
			'brand11': '&#xe910;',
			'twitter2': '&#xe911;',
			'brand12': '&#xe911;',
			'twitter3': '&#xe912;',
			'brand13': '&#xe912;',
			'feed2': '&#xe913;',
			'rss': '&#xe913;',
			'feed3': '&#xe914;',
			'rss2': '&#xe914;',
			'feed4': '&#xe915;',
			'rss3': '&#xe915;',
			'youtube': '&#xe916;',
			'brand14': '&#xe916;',
			'youtube2': '&#xe917;',
			'brand15': '&#xe917;',
			'youtube3': '&#xe918;',
			'brand16': '&#xe918;',
			'twitch': '&#xe919;',
			'brand18': '&#xe919;',
			'vimeo': '&#xe91a;',
			'brand19': '&#xe91a;',
			'vimeo2': '&#xe91b;',
			'brand20': '&#xe91b;',
			'vimeo3': '&#xe91c;',
			'brand21': '&#xe91c;',
			'lanyrd': '&#xe91d;',
			'brand22': '&#xe91d;',
			'flickr': '&#xe91e;',
			'brand23': '&#xe91e;',
			'flickr2': '&#xe91f;',
			'brand24': '&#xe91f;',
			'flickr3': '&#xe920;',
			'brand25': '&#xe920;',
			'flickr4': '&#xe921;',
			'brand26': '&#xe921;',
			'picassa': '&#xe922;',
			'brand27': '&#xe922;',
			'picassa2': '&#xe923;',
			'brand28': '&#xe923;',
			'dribbble': '&#xe924;',
			'brand29': '&#xe924;',
			'dribbble2': '&#xe925;',
			'brand30': '&#xe925;',
			'dribbble3': '&#xe926;',
			'brand31': '&#xe926;',
			'forrst': '&#xe927;',
			'brand32': '&#xe927;',
			'forrst2': '&#xe928;',
			'brand33': '&#xe928;',
			'deviantart': '&#xe929;',
			'brand34': '&#xe929;',
			'deviantart2': '&#xe92a;',
			'brand35': '&#xe92a;',
			'steam': '&#xe92b;',
			'brand36': '&#xe92b;',
			'steam2': '&#xe92c;',
			'brand37': '&#xe92c;',
			'dropbox': '&#xe92d;',
			'brand38': '&#xe92d;',
			'onedrive': '&#xe92e;',
			'brand39': '&#xe92e;',
			'github': '&#xe92f;',
			'brand40': '&#xe92f;',
			'github2': '&#xe930;',
			'brand41': '&#xe930;',
			'github3': '&#xe931;',
			'brand42': '&#xe931;',
			'github4': '&#xe932;',
			'brand43': '&#xe932;',
			'github5': '&#xe933;',
			'brand44': '&#xe933;',
			'wordpress': '&#xe934;',
			'brand45': '&#xe934;',
			'wordpress2': '&#xe935;',
			'brand46': '&#xe935;',
			'joomla': '&#xe936;',
			'brand47': '&#xe936;',
			'blogger': '&#xe937;',
			'brand48': '&#xe937;',
			'blogger2': '&#xe938;',
			'brand49': '&#xe938;',
			'tumblr': '&#xe939;',
			'brand50': '&#xe939;',
			'tumblr2': '&#xe93a;',
			'brand51': '&#xe93a;',
			'yahoo': '&#xe93b;',
			'brand52': '&#xe93b;',
			'tux': '&#xe93c;',
			'brand53': '&#xe93c;',
			'apple': '&#xe93d;',
			'brand54': '&#xe93d;',
			'finder': '&#xe93e;',
			'brand55': '&#xe93e;',
			'android': '&#xe93f;',
			'brand56': '&#xe93f;',
			'windows': '&#xe940;',
			'brand57': '&#xe940;',
			'windows8': '&#xe941;',
			'brand58': '&#xe941;',
			'soundcloud': '&#xe942;',
			'brand59': '&#xe942;',
			'soundcloud2': '&#xe943;',
			'brand60': '&#xe943;',
			'skype': '&#xe944;',
			'brand61': '&#xe944;',
			'reddit': '&#xe945;',
			'brand62': '&#xe945;',
			'linkedin': '&#xe946;',
			'brand63': '&#xe946;',
			'linkedin2': '&#xe947;',
			'brand64': '&#xe947;',
			'lastfm': '&#xe948;',
			'brand65': '&#xe948;',
			'lastfm2': '&#xe949;',
			'brand66': '&#xe949;',
			'delicious': '&#xe94a;',
			'brand67': '&#xe94a;',
			'stumbleupon': '&#xe94b;',
			'brand68': '&#xe94b;',
			'stumbleupon2': '&#xe94c;',
			'brand69': '&#xe94c;',
			'stackoverflow': '&#xe94d;',
			'brand70': '&#xe94d;',
			'pinterest': '&#xe94e;',
			'brand71': '&#xe94e;',
			'pinterest2': '&#xe94f;',
			'brand72': '&#xe94f;',
			'xing': '&#xe950;',
			'brand73': '&#xe950;',
			'xing2': '&#xe951;',
			'brand74': '&#xe951;',
			'flattr': '&#xe952;',
			'brand75': '&#xe952;',
			'foursquare': '&#xe953;',
			'brand76': '&#xe953;',
			'paypal': '&#xe954;',
			'brand77': '&#xe954;',
			'paypal2': '&#xe955;',
			'brand78': '&#xe955;',
			'paypal3': '&#xe956;',
			'brand79': '&#xe956;',
			'yelp': '&#xe957;',
			'brand80': '&#xe957;',
			'html5': '&#xe958;',
			'w3c': '&#xe958;',
			'html52': '&#xe959;',
			'w3c2': '&#xe959;',
			'css3': '&#xe95a;',
			'w3c3': '&#xe95a;',
			'git': '&#xe95b;',
			'svg': '&#xe95c;',
			'codepen': '&#xe95d;',
			'brand81': '&#xe95d;',
			'chrome': '&#xe95e;',
			'browser': '&#xe95e;',
			'firefox': '&#xe95f;',
			'browser2': '&#xe95f;',
			'IE': '&#xe960;',
			'browser3': '&#xe960;',
			'opera': '&#xe961;',
			'browser4': '&#xe961;',
			'safari': '&#xe962;',
			'browser5': '&#xe962;',
			'0': 0
		};
		delete icons['0'];
		window.icomoonLiga = function (els) {
			var classes,
				el,
				i,
				innerHTML,
				key;
			els = els || document.getElementsByTagName('*');
			if (!els.length) {
				els = [els];
			}
			for (i = 0; ; i += 1) {
				el = els[i];
				if (!el) {
					break;
				}
				classes = el.className;
				if (/ico-/.test(classes)) {
					innerHTML = el.innerHTML;
					if (innerHTML && innerHTML.length > 1) {
						for (key in icons) {
							if (icons.hasOwnProperty(key)) {
								innerHTML = innerHTML.replace(new RegExp(key, 'g'), icons[key]);
							}
						}
						el.innerHTML = innerHTML;
					}
				}
			}
		};
		window.icomoonLiga();
	}
}());