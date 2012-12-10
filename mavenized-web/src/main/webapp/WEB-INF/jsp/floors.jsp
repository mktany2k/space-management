<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty param}">
	<c:set var="floors" value="${param.floors}" scope="page"/>
	<c:set var="pid" value="${param.pid}" scope="page"/>
</c:if>
<c:if test="${not empty floors}">
	<c:forEach var="floor" items="${floors}" varStatus="status">
		<c:import url="../dat/${pid}/output/${floor}" charEncoding="UTF-8"/>
	</c:forEach>
</c:if>