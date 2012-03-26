	function initHTML(evt) {
		$('#menu-accordian').accordion();
		$('#menu-tab').tabs();
	}

	function selectProject(evt, element, projectId) {
		var p = document.getElementById('project');
		p.value = projectId;
		console.log(p);
	}