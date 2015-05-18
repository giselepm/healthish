<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
	<h1>New doctor</h1>
    <form action="/healthish/doctor/saveCreate.do" method="POST">
        <ul>
            <li><label for="name">Name: </label><input id="name" name="name" type="text"/></li>
            <li><label for="street">Street: </label><input id="street" name="street" type="text"/></li>
            <li><label for="streetNo">Street number: </label><input id="streetNo" name="streetNo" type="text"/></li>
            <li><label for="city">City: </label><input id="city" name="city" type="text"/></li>
            <li><label for="zip">ZIP code: </label><input id="zip" name="zip" type="text"/></li>
        </ul>
        <input type="submit" value="Create">
    </form>
</body>
</html>