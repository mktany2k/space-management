<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="project.name"/></title>
    </head>
    <body>
        <h1>User Access Control?</h1>
        <script>
            $(document).ready(function() {
                $("#administrationTab").addClass("active");
            });
        </script>
    </body>
</html>
