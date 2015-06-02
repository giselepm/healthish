<%@attribute name="title" required="true" %>
<%@attribute name="action" required="true" %>
<%@attribute name="submitValue" required="true" %>
<%@attribute name="readOnly" required="true" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <title><c:out value="${title}"/></title>
    </head>
    <script type="text/javascript">
        function goBack() {
            window.history.back();
        }
    </script>
    <body>
        <h1><c:out value="${title}"/></h1>

        <form action="${action}" method="POST">
            <input id="id" name="id" type="hidden" value="${doctor.id}"/>
            <ul>
                <li><label for="name">Name: </label><input id="name" name="name" type="text" value="${doctor.name}" ${readOnly}/></li>
                <jsp:doBody/>
                <li><label for="street">Street: </label><input id="street" name="street" type="text" value="${doctor.street}" ${readOnly}/></li>
                <li><label for="streetNo">Street number: </label><input id="streetNo" name="streetNo" type="text" value="${doctor.streetNo}" ${readOnly}/></li>
                <li><label for="city">City: </label><input id="city" name="city" type="text" value="${doctor.city}" ${readOnly}/></li>
                <li><label for="zip">ZIP code: </label><input id="zip" name="zip" type="text" value="${doctor.zip}" ${readOnly}/></li>
            </ul>
            <input type="submit" value="${submitValue}">  <input type="button" value="Back" onclick="goBack()">

        </form>

    </body>

</html>

