<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="project.name"/></title>
        <script>
	        $(document).ready(function() {
	        	console.log($("#summaryTab").addClass("active"));
	        });
        </script>
    </head>
    <body>
        Summary of project<br/>
        <ol>
        	<li>Number of lots/tenants</li>
        	<li>Number of floor plans</li>
        	<li>Lease to be expired in n months</li>
        	<li>Lease in progress for renewal?</li>
        </ol>
    </body>
</html>
