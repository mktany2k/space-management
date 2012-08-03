	function hoverSvgLot(evt, element) {
		var et = evt.type;
		
		if (et == 'mouseover') {
			$('#' + element.id).qtip({
				id: element.id,
				content: {
					text: element.getAttribute('l-desc'),
					title: {
		            	text: element.getAttribute('l-title'),
		            	button: false
		        	}
				},
				position: {
//					my: 'right bottom',
//					at: 'left bottom',
					target: 'mouse',
					viewport: $(window),
			        adjust: {
						x: 20,
						y: 20
					}
				},
				style: {
					classes: 'ui-tooltip-rounded ui-tooltip-shadow ui-tooltip-tipped'
				}
			}).data('qtip').show(evt);
		
//			element.style.ft = element.style.fill;
//			element.style.fill = '#ccc';
			element.style.stroke = '#ff3333';
			element.style.strokeWidth = '5';
		} else if (et == 'mouseout') {
			$('#ui-tooltip-' + element.id).remove();
			
//			element.style.fill = element.style.ft;
			element.style.stroke = '#000000';
			element.style.strokeWidth = '';
		}
	}

	function selectSvgLot(evt, element) {
		var arr = element.id.split('-');
		$.ajax({
			url: 'ajax-sl',
			type: 'POST',
			data: {
				n1: arr[1],
				n2: arr[2],
				n3: arr[3]
			},
			beforeSend: function(result) {
				$('#loading').show();
			},
			complete: function(result) {
				//console.log(result.responseText);
				$('body').append(result.responseText);
				$('body').css('overflow', 'hidden');

				centerOnScreen(event, '.ui-dialog-overlay');
				$('.ui-dialog-overlay').fadeIn();

				// center modal dialog on screen
				centerOnScreen(event, '.ui-dialog-content');
				var content = $('.ui-dialog-content');
				var cssContent = content.css('border-top-right-radius');
				if (isOverflow(content.get(0))) {
					content.css('border-top-right-radius', '0px');
					content.css('border-bottom-right-radius', '0px');
				} else if (cssContent == '0px'){
					content.css('border-top-right-radius', '6px');
					content.css('border-bottom-right-radius', '6px');
				}
				content.fadeIn();

				var infoDesc = $('.ui-dialog-info-desc');
				var cssInfoDesc = infoDesc.css('border-top-right-radius');
				if (isOverflow(infoDesc.get(0))) {
					infoDesc.css('border-top-right-radius', '0px');
					infoDesc.css('border-bottom-right-radius', '0px');
				} else if (cssInfoDesc == '0px'){
					infoDesc.css('border-top-right-radius', '6px');
					infoDesc.css('border-bottom-right-radius', '6px');
				}
				
				$('#lot-tab').tabs();
				$('#loading').hide();
			}
		});
	}

	function closeSvgLot(evt) {
		if (gState.v.d) {
			jConfirm(gState.ac.mm[1], gState.ac.mt[1], function(result) {
				if (result) {
					$('body').css('overflow', 'auto');
					gState.v.d = false;
					destroy();
				}
			});
		} else {
			$('body').css('overflow', 'auto');
			destroy();
			return true;
		}
	}

	function destroy() {
		$('.ui-dialog-content').fadeOut(400, function() {
			$('.ui-dialog-content').remove();
		});
		$('.ui-dialog-overlay').fadeOut(800, function() {
			$('.ui-dialog-overlay').remove();
		});
	}

	function updateSvgLot(evt, n1, n2, n3) {
		//console.log('lot-' + n1 + '-' + n2 + '-' + n3);
		$.ajax({
			url: 'ajax-ul',
			type: 'POST',
			data: {
				n: $('#lotName').attr('value'),
				d: $('#lotDescription').attr('value'),
				u: gState.u.id
			},
			beforeSend: function(result) {
				$('#loading').show();
			},
			complete: function(result) {
				//console.log(result);
				jAlert(gState.ac.mm[2], gState.ac.mt[2]);
				$('#loading').hide();
			}
		});
		$('body').css('overflow', 'auto');
		gState.v.d = false;
		destroy();
	}

	function keyDone(evt, element) {
		//console.log(evt.keyCode);
		if (evt.keyCode == 13) {
			element.blur();
		}
	}

	function dirtyLot(evt, element) {
		gState.v.d = true;
		//$('.ui-dialog-content').addClass('ui-dialog-content-dirty');
	}

	function uploadFile(evt, element) {
	}