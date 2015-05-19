<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
    <c:choose>
        <c:when test="${action == 'Create'}">
            <h1>New doctor</h1>
            <c:set var="readOnly" value=""/>
        </c:when>
        <c:when test="${action == 'Edit'}">
            <h1>Edit doctor</h1>
            <c:set var="readOnly" value=""/>
        </c:when>
        <c:when test="${action == 'Delete'}">
            <h1>Delete doctor</h1>
            <c:set var="readOnly" value="readOnly"/>
        </c:when>
    </c:choose>


            <form action="/healthish/doctor/save${action}.do" method="POST">
                <input id="id" name="id" type="hidden" value="${doctor.id}"/>
                <ul>
                    <li><label for="name">Name: </label><input id="name" name="name" type="text" value="${doctor.name}" ${readOnly}/></li>
                    <li><label for="street">Street: </label><input id="street" name="street" type="text" value="${doctor.street}" ${readOnly}/></li>
                    <li><label for="streetNo">Street number: </label><input id="streetNo" name="streetNo" type="text" value="${doctor.streetNo}" ${readOnly}/></li>
                    <li><label for="city">City: </label><input id="city" name="city" type="text" value="${doctor.city}" ${readOnly}/></li>
                    <li><label for="zip">ZIP code: </label><input id="zip" name="zip" type="text" value="${doctor.zip}" ${readOnly}/></li>
                </ul>
                <input type="submit" value="${action}">
            </form>

</body>
</html>