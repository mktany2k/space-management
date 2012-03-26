	function selectDate(evt) {
		var jxdate = document.getElementById('jx-date');
		var jxdatemonth = document.getElementsByName('jx-date-month')[1];
		var jxdateyear = document.getElementsByName('jx-date-year')[1];
		var updDate = jxdatemonth.value + ' ' + jxdateyear.value;
		if (jxdate.innerHTML != updDate) {
			gState.dt.mmmmm = jxdatemonth.value;
			gState.dt.yyyy = jxdateyear.value;
			jxdate.innerHTML = jxdatemonth.value + ' ' + jxdateyear.value;
		}
	}

	function updateDate(evt) {
		var jxdatemonths = document.getElementsByName('jx-date-month');
		for (i = 0; i < jxdatemonths.length; i++) {
			jxdatemonths[i].value = gState.dt.mmmmm;
		}
		
		var jxdateyears = document.getElementsByName('jx-date-year');
		for (i = 0; i < jxdateyears.length; i++) {
			jxdateyears[i].value = gState.dt.yyyy;
		}
	}

	function keyPressed(evt) {
		var keyCode = evt.keyCode;
		if (keyCode == 13) {
			selectDate(evt);
		}
	}