<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<html>
    <head>
        <title><fmt:message key="project.name"/></title>
        <script>
	        $(document).ready(function() {
	        	$("#maintenanceTab").addClass("active");
	        });
        </script>
    </head>
    <body>
        Maintenance of lots<br/>
        <ol>
        	<li>Grid/Spreadsheet view</li>
        	<li>Allows sorting</li>
        	<li>Allows scrolling, max shown = n records</li>
        </ol>
    </body>
</html>