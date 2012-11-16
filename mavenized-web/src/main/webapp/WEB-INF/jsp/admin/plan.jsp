<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
        <title><fmt:message key="project.name"/></title>
        <script>
	        $(document).ready(function() {
	        	$("#planTab").addClass("active");
	        });
        </script>
	</head>
	<body>
		<div class="btn-group" data-toggle="buttons-checkbox">
			<c:forEach var="file" items="files">
				<button type="button" class="btn"><c:out value="file"/></button>
			</c:forEach>
		</div>
		<c:import url="/WEB-INF/dat/plan/1-floor.svg" charEncoding="UTF-8"/>
	</body>
</html>