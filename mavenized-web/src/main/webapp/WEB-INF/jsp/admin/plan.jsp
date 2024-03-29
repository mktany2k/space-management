<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title><fmt:message key="project.name"/></title>
    </head>
    <body>
        <div class="btn-group" data-toggle="buttons-checkbox">
            <c:forEach var="file" items="files">
                <button type="button" class="btn"><c:out value="file"/></button>
            </c:forEach>
        </div>
        <c:import url="/dat/plan/1-floor.svg" charEncoding="UTF-8"/>
        <script>
            $(document).ready(function() {
                $("#planTab").addClass("active");
            });
        </script>
    </body>
</html>