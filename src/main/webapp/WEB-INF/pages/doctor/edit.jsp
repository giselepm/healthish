<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
	<h1>New doctor</h1>
    <form action="/healthish/doctor/saveEdit.do" method="POST">
        <input id="id" name="id" type="hidden" value="${doctor.id}"/>
        <ul>
            <li><label for="name">Name: </label><input id="name" name="name" type="text" value="${doctor.name}"/></li>
            <li><label for="street">Street: </label><input id="street" name="street" type="text" value="${doctor.street}"/></li>
            <li><label for="streetNo">Street number: </label><input id="streetNo" name="streetNo" type="text" value="${doctor.streetNo}"/></li>
            <li><label for="city">City: </label><input id="city" name="city" type="text" value="${doctor.city}"/></li>
            <li><label for="zip">ZIP code: </label><input id="zip" name="zip" type="text" value="${doctor.zip}"/></li>
        </ul>
        <input type="submit" value="Save">
    </form>
</body>
</html>