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

		$('#maintenance-accordian').accordion();
	}
	
	function updateMaintenance(evt) {
		console.log(evt);
	}
	
	function maintainLot(evt, id) {
		console.log(id);
	}

	function focusInput(event, header, element, offset) {
		var e = $('#' + element.id);
		e.css('top', offset.top);

		console.log($('.ui-display-header-' + header).css('height'));
		console.log(e.css('height'));

		$('.ui-display-header-' + header).css('height', e.css('height'));
		console.log($('.ui-display-header-' + header));

		console.log($('.ui-display-header-' + header).css('height'));
		console.log(e.css('height'));
		
		//console.log(e);
		//console.log(e.css('width'));
		//console.log(offset);
	}

	function scrollInput(event, element, top, left) {
		element = $('#' + element.id);
		element.scrollTop(top + 'px');
		element.scrollLeft(left + 'px');
		element.css('top', 'auto');
	}

	function find(evt, p) {
		var v = prompt('Look for', '');
		if (v == null || v == '') {
			return;
		}

		var x = $('*:regex(id, ' + p + '-.*)');
		for (i = 0; i < x.length; i++) {
			var hc = x[i].id.split('-')[2];
			var e = $('#' + p + '-' + hc);
			if (e.length != 0) {
				var f = e.attr('value');
				if (f == v) {
					var container = $('#maintenance-container');
					//container.scrollTop(e.offset().top - container.offset().top);
					//container.scrollLeft(e.offset().left - container.offset().left);
					container.animate({ scrollLeft: e.offset().left - container.offset().left });

					$('.ui-highlight-' + e.attr('id').split('-')[2]).effect('highlight', {}, 1000);
					return;
				}
			}
		}

		alert('Not found');
	}

	function isOverflow(el) {
		var curOverflow = el.style.overflow;
		if ( !curOverflow || curOverflow === 'visible' ) el.style.overflow = 'hidden';
		var isOverflowing = el.clientWidth < el.scrollWidth || el.clientHeight < el.scrollHeight;
		el.style.overflow = curOverflow;
		return isOverflowing;
	}