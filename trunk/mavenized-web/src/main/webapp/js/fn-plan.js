	function initSVG(evt) {
		if (window.svgDocument == null) {
			svgDocument = evt.target.ownerDocument;
		}
	}

	function initHTML(evt) {
		// extend jQuery function - regular expression
		jQuery.expr[':'].regex = function(elem, index, match) {
		    var matchParams = match[3].split(','),
		        validLabels = /^(data|css):/,
		        attr = {
		            method: matchParams[0].match(validLabels) ? 
		                        matchParams[0].split(':')[0] : 'attr',
		            property: matchParams.shift().replace(validLabels,'')
		        },
		        regexFlags = 'ig',
		        regex = new RegExp(matchParams.join('').replace(/^\s+|\s+$/g,''), regexFlags);
		    return regex.test(jQuery(elem)[attr.method](attr.property));
		}

		// init jx-bar
		$('#info-bar').jixedbar();

		// init floating legend
		floatingMenu.add('legend', {
			// Represents distance from left or right browser window
			// border depending upon property used. Only one should be
			// specified.
			//targetLeft: 10,
			targetRight: 10,

			// Represents distance from top or bottom browser window
			// border depending upon property used. Only one should be
			// specified.
			targetTop: 10,
			//targetBottom: 0,

			// Uncomment one of those if you need centering on
			// X- or Y- axis.
			//centerX: true,
			//centerY: true,

			// Remove this one if you don't want snap effect
			snap: false
		});

		// init floating loader
		$('#loading').hide();
		floatingMenu.add('loading', {
			targetTop: 10,
			targetLeft: 10,
			snap: true
		});

		// init view function
		$('#category').click(function() {
			selectView(event);
		});

		// init date function
		$('#jx-date').html(gState.dt.mmmmm + ' ' + gState.dt.yyyy);
		$('#jx-date').click(function() {
			updateDate(event);
		});
		//$('#datepicker').datepicker();

		// init floor function
		$('#jx-floor').click(function() {
			updateFloor(event);
		});
		
		// init window onresize event
		$(window).bind('resize', function(evt) {
			if ($('.ui-dialog-overlay').length != 0) {
				centerOnScreen(evt, '.ui-dialog-overlay');
				centerOnScreen(evt, '.ui-dialog-content');

				{
					var element = $('.ui-dialog-content');
					var css = element.css('border-top-right-radius');
					if (isOverflow(element.get(0))) {
						element.css('border-top-right-radius', '0px');
						element.css('border-bottom-right-radius', '0px');
					} else if (css == '0px'){
						element.css('border-top-right-radius', '6px');
						element.css('border-bottom-right-radius', '6px');
					}
				}
				{
					var element = $('.ui-dialog-info-desc');
					var css = element.css('border-top-right-radius');
					if (isOverflow(element.get(0))) {
						element.css('border-top-right-radius', '0px');
						element.css('border-bottom-right-radius', '0px');
					} else if (css == '0px'){
						element.css('border-top-right-radius', '6px');
						element.css('border-bottom-right-radius', '6px');
					}
				}
			}
		});

		// init window onscroll event
		$(window).bind('scroll', function(evt) {
			console.log('scroll');
		});
		  
		// debug function
		$('#debug').click(function() {
			console.log(JSON.stringify(gState));
			$.ajax({ 
				url: 'ajax-debug',
				type: 'POST'
			});
		});

		//jPrompt(gState.ac.mm, '', 'Input');
	}

	function centerOnScreen(evt, selector) {
		var winH = $(window).height();
		var winW = $(window).width();
		$(selector).css('top', (winH / 2 - $(selector).height() / 2) + window.pageYOffset);
		$(selector).css('left', (winW / 2 - $(selector).width() / 2) + window.pageXOffset);
	}

	function isOverflow(el) {
		var curOverflow = el.style.overflow;
		if ( !curOverflow || curOverflow === 'visible' )el.style.overflow = 'hidden';
		var isOverflowing = el.clientWidth < el.scrollWidth || el.clientHeight < el.scrollHeight;
		el.style.overflow = curOverflow;
		return isOverflowing;
	}