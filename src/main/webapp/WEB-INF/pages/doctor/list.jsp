<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<script type="text/javascript">
    function goBack() {
        window.history.back();
    }
    function goCreateDoctor() {
        location.href="create";
    }

</script>
<body>
<h1>All catalogued doctors</h1>

<h4><c:out value="${doctorSuccessMsg}"/></h4>
<h3><c:out value="${doctorErrorMsg}"/></h3>
<table border="1">
    <thead>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Register Number</td>
        <td>Address</td>
        <td>City</td>
        <td>ZIP</td>
        <td colspan="2">Actions</td>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="doc" items="${allDoctors}">
            <tr>
                <td><c:out value="${doc.id}"/></td>
                <td><c:out value="${doc.name}"/></td>
                <td><c:out value="${doc.registerNumber}"/></td>
                <td><c:out value="${doc.street}"/> <c:out value="${doc.streetNo}"/> </td>
                <td><c:out value="${doc.city}"/></td>
                <td><c:out value="${doc.zip}"/></td>
                <td><a href="/healthish/doctor/edit.do?id=${doc.id}">Edit</a></td>
                <td><a href="/healthish/doctor/delete.do?id=${doc.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>

</table>
<br>
    <input type="button" value="New doctor" onclick="goCreateDoctor()">
    <input type="button" value="Back" onclick="goBack()">
</body>
</html>