<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="project.name"/></title>
    </head>
    <body>
    	This should be loaded dynamically. TODO: add event listener to nav-tabs
		<c:import url="../dat/plan/1-floor.svg" charEncoding="UTF-8"/>
    </body>
</html>
