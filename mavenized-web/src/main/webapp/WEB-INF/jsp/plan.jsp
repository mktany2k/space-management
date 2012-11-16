<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="stl" uri="SvgTagLibrary" %>
<html>
	<head>
		<title><fmt:message key="project.name"/></title>
		<link rel="shortcut icon" href="../images/svg.ico"/>
		<link rel="stylesheet" href="../css/default.css" type="text/css"/>
		<script type="text/javascript" src="../js/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="../js/fn-plan.js"></script>
		<script type="text/javascript" src="../js/fn-lot.js"></script>
		<script type="text/javascript" src="../js/fn-view.js"></script>
		<script type="text/javascript" src="../js/fn-date.js"></script>
		<script type="text/javascript" src="../js/fn-floor.js"></script>
		<script type="text/javascript" src="../js/floating-1.7/floating-1.7.js"></script>
		<!-- jquery-alerts starts -->
		<link rel="stylesheet" href="../js/jquery.alerts-1.1/jquery.alerts.css" type="text/css" />
		<script type="text/javascript" src="../js/jquery.alerts-1.1/jquery.alerts.js"></script>
		<!-- jquery-alerts ends -->
		<!-- jqModal starts -->
		<link rel="stylesheet" href="../js/jqModal/jqModal.css" type="text/css" />
		<script type="text/javascript" src="../js/jqModal/jqModal.js"></script>
		<!-- jqModal ends -->
		<!-- jquery-qtip starts -->
		<link rel="stylesheet" href="../js/jquery-qtip/jquery.qtip.css" type="text/css" />
		<script type="text/javascript" src="../js/jquery-qtip/jquery.qtip.js"></script>
		<!-- jquery-qtip ends -->
		<!-- jx-bar starts -->
		<link rel="stylesheet" href="../js/jxbar/themes/default/jx.stylesheet.css" type="text/css" />
		<script type="text/javascript" src="../js/jxbar/src/jquery.jixedbar.mod.js"></script>
		<!-- jx-bar ends -->
		<!-- jquery datepicker starts -->
		<style type="text/css">@import "../js/jquery-ui-1.8.13.custom/css/smoothness/jquery-ui-1.8.13.custom.css";</style>
		<script type="text/javascript" src="../js/jquery-ui-1.8.13.custom/js/jquery-ui-1.8.13.custom.min.js"></script>
		<!-- jquery datepicker ends -->
		<!-- jquery uploadify starts
		<link type="text/css" href="../js/jquery.uploadify-v2.1.4/uploadify.css" rel="stylesheet" />
		<script type="text/javascript" src="../js/jquery.uploadify-v2.1.4/swfobject.js"></script>
		<script type="text/javascript" src="../js/jquery.uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
		<!-- jquery uploadify ends -->
		<script type="text/javascript">
			$(document).ready(function() {
				initHTML(event);
			});
			var gState = ${gState};
		</script>
	</head>
	<body>
		<div id="legend" class="ui-legend">
		</div>
		<p style="vertical-align: middle;"></p>
		<div id="loading" class="ui-loading">
			<img src="../images/loading.gif"/>
		</div>
		<table cellpadding="0" cellspacing="0" border="0px" width="100%" height="100%">
			<tr>
				<td>
					<div id="floor-mgr" class="ui-floor-mgr">
						<c:import url="floors.jsp">
						 	<c:param name="floors" value="${floors}"/>
						 	<c:param name="pid" value="${project.projectId}"/>
						</c:import>
					</div>
				</td>
			</tr>
		</table>
		<!-- jx menu bar starts -->
		<div id="info-bar">
			<c:if test="${not empty views}">
				<ul class="jx-bar-button-left">
					<li title="View">
						<a id="jx-view" name="jx-view"><img src="../images/view.png" class="menu-icon-small"/></a>
						<ul>
							<c:forEach var="view" items="${views}" varStatus="status">
								<li>
									<a style="cursor: pointer;" onclick="javascript: selectView(event, ${view.viewId});"  onmouseover="javascript: this.style.backgroundColor = '#0099ff';" onmouseout="javascript: this.style.backgroundColor = '#ffffff';">
										<table cellspacing="0" cellpadding="5">
											<tr>
												<td><img src="../images/${view.icon}" class="menu-icon-large"/></td>
												<td class="menu-text">${view.name}</td>
											</tr>
										</table>
									</a>
								</li>
							</c:forEach>
						</ul>
					</li>
				</ul>
				<span class="jx-separator-left"></span>
			</c:if>
			<!--
			<ul class="jx-bar-button-left">
				<li title="Date">
					<img src="../images/calendar.png" class="menu-icon-small"/>
					<a id="jx-date" name="jx-date" href="#">
					</a>
					<ul>
						<li>
							<input id="datepicker" type="text" />
							<stl:monthYear monthId="jx-date-month" 
									   monthName="jx-date-month" 
									   yearId="jx-date-year"
									   yearName="jx-date-year"
									   monthStyle="font-size: 8pt; font-family: verdana;" 
									   yearStyle="width: 38px; text-align: center; font-size: 8pt; font-family: verdana;" 
									   onchange="javascript: selectDate(event);"
									   onkeypress="javascript: keyPressed(event);"/>
						</li>
					</ul>
				</li>
			</ul>
			<span class="jx-separator-left"></span>
			-->
			<ul id="jx-floors" class="jx-bar-button-left">
				<li title="Floors">
					<a id="jx-floor" href="#">
						<img src="../images/floor.png" class="menu-icon-small"/>
						<!--c:out value="${project.name} [${fn:length(project.plans)}]"/-->
					</a>
					<ul>
						<li>
							<select id="jx-select-option" name="jx-select-option" style="width: 100%; font-size: 10pt; font-family: verdana;" onclick="javascript: selectFloor(event);" multiple="multiple">
								<c:forEach var="plan" items="${project.plans}">
									<option value="${plan.planId}" <c:forEach var="floor" items="${floors}"><c:if test="${floor==plan.filename}">selected="selected"</c:if></c:forEach>>${plan.name}</option>
								</c:forEach>
							</select>
						</li>
						<li>
							<a href="javascript: alert('not implemented yet');">
								<table cellspacing="0" cellpadding="5">
									<tr>
										<td><img src="../images/add.png" class="menu-icon-large"/></td>
										<td class="menu-text">Add Floor Plan</td>
									</tr>
								</table>
							</a>
						</li>
					</ul>
				</li>
			</ul>
			<span class="jx-separator-left"></span>
			<ul class="jx-bar-button-left">
				<li title="Maintain"><a href="maintenance"><img src="../images/list.png" class="menu-icon-small"/></a></li>
			</ul>
			<span class="jx-separator-left"></span>
			<div id="jx-session" class="text-container" style="font-weight: bold; cursor: none;">
				${sessionScope[SESSION_USER].username} | <c:out value="${fn:substring(project.description, 0, 70)}"/>
			</div>
			<ul class="jx-bar-button-right">
				<li id="debug" title="Debug"><img src="../images/debug.png" class="menu-icon-small"/></li>
			</ul>
			<span class="jx-separator-right"></span>
			<ul class="jx-bar-button-right">
				<li title="Logout"><a href="../logout"><img src="../images/logout.png" class="menu-icon-small"/></a></li>
			</ul>
			<span class="jx-separator-right"></span>
			<ul class="jx-bar-button-right">
				<li title="Main Menu"><a href="menu"><img src="../images/mainmenu.png" class="menu-icon-small"/></a></li>
			</ul>
			<span class="jx-separator-right"></span>
			<ul class="jx-bar-button-right">
				<li title="Reports"><a href="#"><img src="../images/reporting.png" class="menu-icon-small"/></a></li>
			</ul>
			<span class="jx-separator-right"></span>
			<ul class="jx-bar-button-right">
				<li title="Search"><a href="#"><img src="../images/search.png" class="menu-icon-small"/></a></li>
			</ul>
			<span class="jx-separator-right"></span>
			<ul class="jx-bar-button-right">
				<li title="New Window"><a href="javascript: window.open('project', Math.random());"><img src="../images/new.png" class="menu-icon-small"/></a></li>
			</ul>
			<span class="jx-separator-right"></span>
		</div>
		<!-- jx menu bar ends -->
	</body>
</html>