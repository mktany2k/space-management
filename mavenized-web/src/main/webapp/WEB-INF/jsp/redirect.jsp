<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title><c:out value="${page_title}" escapeXml="false"/></title>
		<link rel="shortcut icon" href="images/svg.ico"/>
		<link rel="stylesheet" href="css/default.css" type="text/css"/>
	</head>
	<body>
		<table class="svg-main-tbl">
			<tr>
				<td class="svg-header"/>
			</tr>
			<tr>
				<td class="svg-body">
					You have logged in, redirecting you to main menu in 1 second.
					<script type="text/javascript">
						setTimeout('location.href = "secure/menu";', 1000);
					</script>
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