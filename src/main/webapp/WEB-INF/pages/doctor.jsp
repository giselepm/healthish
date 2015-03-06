<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
	<h1>All catalogued doctors</h1>

    <c:forEach var="doc" items="${allDoctors}">
    Id: <c:out value="${doc.id}"/><br>
    Name: <c:out value="${doc.name}"/><br>
    </c:forEach>

</body>
</html>