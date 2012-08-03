	function selectView(evt, id) {
		//console.log(JSON.stringify(gState));
		gState.v.id = id;
		if (id >= 999) {
			for (i = 0; i < gState.id.length; i++) {
				//console.log('.css-' + gState.pid + '-' + gState.id[i] + '-1');
				$('.css-' + gState.pid + '-' + gState.id[i] + '-1').css('fill', '#ffffff');
			}
			gState.v.id = id;
			gState.v.r = null;
			$('#legend').css('visibility', 'collapse');
			return;
		}

		applyView(evt);
	}

	function applyView(evt) {
		//console.log('apply view to floors');
		if (gState.v.id >= 999) {
			for (i = 0; i < gState.id.length; i++) {
				//console.log('.css-' + gState.pid + '-' + gState.id[i] + '-1');
				$('.css-' + gState.pid + '-' + gState.id[i] + '-1').css('fill', '#ffffff');
			}
			gState.v.r = null;
			$('#legend').css('visibility', 'collapse');
			return;
		} else {
			for (i = 0; i < gState.id.length; i++) {
				//console.log('.css-' + gState.pid + '-' + gState.id[i] + '-1');
				var spaces = $('.css-' + gState.pid + '-' + gState.id[i] + '-1');
				for (j = 0; j < spaces.length; j++) {
					var color = 'rgb(' + Math.floor(Math.random() * 256) + ', '
										 + Math.floor(Math.random() * 256) + ', '
										 + Math.floor(Math.random() * 256) + ')';
					spaces[j].style.fill = color;
				}
			}

			$.ajax({
				url: 'ajax-sv',
				type: 'POST',
				data: {
					vid: gState.v.id
				},
				beforeSend: function(result) {
					$('#legend').css('visibility', 'collapse');
					$('#loading').show();
				},
				complete: function(result) {
					gState.v.r = JSON.parse(result.responseText);

					$('#legend').empty();

					for (i = 0; i < gState.v.r.bd.length; i++) {
						var ld = '<div style="cursor: default; font-weight: bold; float: right; position: relative; width: 80%; height: 24px;" '
							   + 'id="lid-1-' + i + '" '
							   + 'onmouseout="javascript: hoverLegend(event, this, ' + i + ');" ' 
							   + 'onmouseover="javascript: hoverLegend(event, this, ' + i + ');"><p style="position: relative; vertical-align: middle; top: -8px;">' 
							   + unescape(gState.v.r.bd[i].n) + '</p></div>'
							   + '<div style="border-radius: 2px; cursor: pointer; position: relative; float: left; width: 24px; height: 24px; background: ' + gState.v.r.bd[i].c + ';" '
							   + 'id="lid-2-' + i + '" '
							   + 'onmouseout="javascript: hoverLegend(event, this, ' + i + ');" ' 
							   + 'onmouseover="javascript: hoverLegend(event, this, ' + i + ');"></div>';
						$('#legend').append(ld);
						//console.log(gState.v.r.bd[i]);
					}

					$('#legend').css('visibility', 'visible');
					$('#loading').hide();
				}
			});
		}
	}
	
	function hoverLegend(evt, element, i) {
		var et = evt.type;
		
		if (et == 'mouseover') {
			$('#' + element.id).qtip({
				id: gState.v.r.bd[i].bid,
				content: {
					text: gState.v.r.bd[i].d,
					title: {
		            	text: gState.v.r.bd[i].n,
		            	button: false
		        	}
				},
				position: {
					my: 'right center',
					at: 'left center',
					viewport: $(window),
			        adjust: {
						x: 0,
						y: 0
					}
				},
				style: {
					classes: 'ui-tooltip-rounded ui-tooltip-shadow ui-tooltip-tipped'
				}
			}).data('qtip').show(evt);
		} else if (et == 'mouseout') {
			//console.log($('#ui-tooltip-' + gState.v.r.bd[i].bid));
			$('#ui-tooltip-' + gState.v.r.bd[i].bid).remove();
		}
	}