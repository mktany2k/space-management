<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%
        request.setAttribute("page_title", "some title");
    %>
    <head>
        <title>${page_title}</title>
        <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/images/osm.ico"/>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/default.css" type="text/css"/>
    </head>
    <body>
        <table class="svg-main-tbl">
            <tr>
                <td class="svg-header"/>
            </tr>
            <tr>
                <td class="svg-body">
                    <form method="POST" action="login">
                        <table class="index-login-tbl" align="center">
                            <%--c:if test="${not empty message}">
                                <c:forEach var="i" items="${message}" varStatus="status">
                                    <tr>
                                        <td style="color: #ff0000;" colspan="2">
                                            ${i.value} [${i.key}]
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if--%>
                            <tr>
                                <td>
                                    username:
                                </td>
                                <td>
                                    <input type="text" name="username" required="required"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    password:
                                </td>
                                <td>
                                    <input type="password" name="password" required="required"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" name="op" value="Login" class="ui-dialog-button"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
            <tr>
                <td class="svg-footer">
                    ${requestScope.page_footer}
                </td>
            </tr>
        </table>
    </body>
</html>