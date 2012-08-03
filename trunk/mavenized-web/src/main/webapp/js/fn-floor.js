	function selectFloor(evt) {
		var element = evt.target;
		//console.log(element.constructor.name);
		if (element.constructor.name != 'HTMLOptionElement') {
			return;
		}
		var _ids = gState.id.slice(0);
		//console.log('_ids: ' + _ids);
		removeValue(gState.id, -1);
		removeValue(_ids, -1);
		//console.log('_ids: ' + _ids);

		for (i = 0; i < element.parentNode.length; i++) {
			//console.log('index: ' + i + ' | selected: ' + element.parentNode[i].selected + ' | value: ' + element.parentNode[i].value);
			if (element.parentNode[i].selected) {
				if (!contains(_ids, element.parentNode[i].value)) {
					//console.log('index: ' + i + ' | selected: ' + element.parentNode[i].selected + ' | value: ' + element.parentNode[i].value);
					//console.log(_ids + ': _ids adding value ' + element.parentNode[i].value);
					_ids.push(parseInt(element.parentNode[i].value));
				} else {
					//console.log(_ids + ': _ids already contains ' + element.parentNode[i].value);
				}
			} else {
				//console.log(_ids + ': _ids removing ' + element.parentNode[i].value);
				removeValue(_ids, element.parentNode[i].value);
				//console.log('_ids: ' + _ids);
			}
		}
		
		if (_ids.length > gState.ac.mv) {
			//console.log('_ids: ' + _ids + ' | gState.id: ' + gState.id);
			_ids.pop();
			//console.log('_ids: ' + _ids + ' | gState.id: ' + gState.id);
			for (i = 0; i < element.parentNode.length; i++) {
				//console.log('index: ' + i + ' | selected: ' + element.parentNode[i].selected + ' | value: ' + element.parentNode[i].value);
				element.parentNode[i].selected = false;
				//console.log('index: ' + i + ' | selected: ' + element.parentNode[i].selected + ' | value: ' + element.parentNode[i].value);
			}
			for (i = 0; i < element.parentNode.length; i++) {
				for (j = 0; j < gState.id.length; j++) {
					if (element.parentNode[i].value == gState.id[j]) {
						element.parentNode[i].setAttribute('selected', 'selected');
						break;
					}
				}
			}
			jAlert(gState.ac.mm[0], gState.ac.mt[0]);
			return;
		}

		gState.id = _ids.sort();
		//console.log('_ids: ' + _ids + ' | gState.id: ' + gState.id);
		$.ajax({
			url: 'ajax-sf',
			type: 'POST',
			data: {
				planId: gState.id
			},
			beforeSend: function(result) {
				$('#loading').show();
				element.parentNode.setAttribute('disabled', 'disabled');
				$('#floor-mgr').empty();
			},
			complete: function(result) {
				$('#floor-mgr').append(result.responseText);
				element.parentNode.removeAttribute('disabled');
				applyView(event);
				$('#loading').hide();
			}
		});
	}

	function updateFloor(evt) {
		var jxselectoptions = document.getElementsByName('jx-select-option');
		for (i = 0; i < jxselectoptions.length; i++) {
			for (k = 0; k < jxselectoptions[i].length; k++) {
				jxselectoptions[i][k].selected = false;
			}
		}

		//console.log(gState.id);
		for (i = 0; i < jxselectoptions.length; i++) {
			for (j = 0; j < gState.id.length; j++) {
				for (k = 0; k < jxselectoptions[i].length; k++) {
					//console.log(jxselectoptions[i][k].value + '==' + gState.id[j]);
					if (gState.id[j] == jxselectoptions[i][k].value) {
						//console.log('set selected to true: ' + jxselectoptions[i][k].value);
						jxselectoptions[i][k].setAttribute('selected', 'selected');
						break;
					}
				}
			}
		}
	}

	function removeValue(arr, val) {
		// remove all unoccupied
		var c = 0;
		while (c++ <= arr.length) {
			var v = arr.shift();
			if (v != val && v != undefined) {
				arr.push(v);
			}
		}
	}

	function contains(arr, val) {
		//console.log('arr: ' + arr + ' has ' + val + '?');
		for (k = 0; k < arr.length; k++) {
			if (arr[k] == val) {
				//console.log('yes');
				return true;
			}
		}
		//console.log('no');
		return false;
	}