<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.scwcd.enterprise.servlet.DefaultServlet" %>
<%
	DefaultServlet.setGlobalValues(request);
%>
<html>
	<head>
		<title><c:out value="${page_title}" escapeXml="false"/></title>
		<link rel="shortcut icon" href="images/osm.ico"/>
		<link rel="stylesheet" href="css/default.css" type="text/css"/>
	</head>
	<body>
		<table class="svg-main-tbl">
			<tr>
				<td class="svg-header"/>
			</tr>
			<tr>
				<td class="svg-body">
					Index Page<br/>
					<a href="https://localhost:8443/web-space/login">Login</a>
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