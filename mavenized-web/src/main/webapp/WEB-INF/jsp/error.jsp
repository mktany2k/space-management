<%@page  isErrorPage="true" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title><fmt:message key="project.name"/></title>
        <link rel="shortcut icon" href="../images/svg.ico"/>
        <link rel="stylesheet" href="http://localhost:8088/svg/css/default.css" type="text/css"/>
    </head>
    <body>
        <table class="svg-main-tbl">
            <tr>
                <td class="svg-header"/>
            </tr>
            <tr>
                <td class="svg-body">
                    An error was encountered..!<br/>
                    Click <a href="/svg/login">here</a> to go to login page!
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