<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="project.name"/></title>
        <script>
	        $(document).ready(function() {
	        	$("#homeTab").addClass("active");
	        });
        </script>
    </head>
    <body>
    	Welcome screen
    </body>
</html>
