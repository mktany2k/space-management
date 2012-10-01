<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title><c:out value="${page_title}" escapeXml="false"/></title>
        <link rel="stylesheet" href="<s:url value="/css/default.css"/>" type="text/css"/>
        <script type="text/javascript" src="<s:url value="/js/fn-menu.js"/>"></script>
		<style type="text/css">@import "${pageContext.servletContext.contextPath}/js/jquery-ui-1.8.13.custom/css/smoothness/jquery-ui-1.8.13.custom.css";</style>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-ui-1.8.13.custom/js/jquery-ui-1.8.13.custom.min.js"></script>

		<!-- jquery-blueimp starts -->
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/js/blueimp/jquery.fileupload-ui.css" type="text/css"/>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/blueimp/jquery.fileupload.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/blueimp/jquery.fileupload-ui.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/blueimp/jquery.iframe-transport.js"></script>
		<script type="text/javascript"  src="${pageContext.servletContext.contextPath}/js/jquery.tmpl.min.js"></script>
		<!-- jquery-blueimp ends -->
		<script type="text/javascript">
			$(document).ready(function() {
				initHTML(event);
			});
		</script>
	</head>
	<body>
		<table>
			<tr>
				<td class="svg-body">
					<div id="menu-accordian" class="ui-menu-accordian">
						<h3><a href="#" class="text-bolded">New Project</a></h3>
						<div class="ui-menu-tab">
							<div class="ui-menu-new">
								<form method="POST" action="new">
									<table class="ui-display-table" border="0" cellspacing="0" cellpadding="4">
										<tr>
											<td class="ui-display-table-header" colspan="2">Properties</td>
										</tr>
										<tr>
											<td class="ui-display-table-field-left ui-width-20pc">
												Name
											</td>
											<td class="ui-display-table-field-right">
												<input name="name" type="text" class="ui-menu-input" required="required"/>
											</td>
										</tr>
										<tr>
											<td class="ui-display-table-field-left">
												Description
											</td>
											<td class="ui-display-table-field-right">
												<textarea name="description" 
														  class="ui-menu-input ui-width-40pc" 
														  rows="1" 
														  cols="1"></textarea>
											</td>
										</tr>
										<tr>
											<td class="ui-display-table-field-left">
												SVG Format
											</td>
											<td class="ui-display-table-field-right">
												<select name="parser" class="ui-menu-input">
													<!-- TODO: dynamically generated -->
													<option value="VISIO2007">Visio 2007</option>
													<option value="AUTOCAD">AutoCAD</option>
													<option value="DEFAULT">Default</option>
												</select>
											</td>
										</tr>
										<tr>
											<td class="ui-display-table-field-left">
												Unit
											</td>
											<td class="ui-display-table-field-right">
												<select name="unit" class="ui-menu-input">
													<option value="sf">sf</option>
													<option value="m²">m²</option>
												</select>
											</td>
										</tr>
										<tr>
											<td class="ui-display-table-field-left">
												Levels
											</td>
											<td class="ui-display-table-field-right">
												<input name="levels" type="number" value="1" min="1" max="999" step="1" required="required"/>
											</td>
										</tr>
									</table>
									<div class="ui-menu-tab-bottom-nav" align="right">
										<input type="submit" name="op" value="New" class="ui-dialog-button"/>
									</div>
								</form>
							</div>
						</div>
						<h3><a href="#" class="text-bolded">Existing Project</a></h3>
						<div class="ui-menu-tab">
							<form method="POST" action="open">
								<c:choose>
									<c:when test="${not empty projects}">
										<div id="menu-tab" class="ui-menu-open">
											<ul>
												<c:forEach var="i" items="${projects}" varStatus="status">
													<li><a href="#tab-${i.projectId}" class="ui-dialog-tab-link" onclick="javascript: selectProject(event, this, ${i.projectId});">${i.name}</a></li>
												</c:forEach>
											</ul>
											<c:forEach var="i" items="${projects}" varStatus="status">
												<c:if test="${status.first}">
													<input type="hidden" id="project" name="projectId" value="${i.projectId}"/>
												</c:if>
												<div id="tab-${i.projectId}" class="ui-dialog-tab">
													${i.description}
												</div>
											</c:forEach>
										</div>
										<div class="ui-menu-tab-bottom-nav" align="right">
											<input type="submit" name="op" value="Delete" class="ui-dialog-button"/>
											<input type="submit" name="op" value="Open" class="ui-dialog-button"/>
										</div>
									</c:when>
									<c:otherwise>
										Empty
									</c:otherwise>
								</c:choose>
							</form>
						</div>
						<h3><a href="#" class="text-bolded">Settings</a></h3>
						<div class="ui-menu-tab">
							Configuration
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="svg-footer">
					<c:out value="${page_footer}" escapeXml="false"/>
				</td>
			</tr>
		</table>
	</body>
</html>