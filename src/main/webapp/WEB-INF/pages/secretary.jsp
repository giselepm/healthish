<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>


<html>
<head>
    <link rel="stylesheet" href="../css/displaytag.css" type="text/css">
    <link rel="stylesheet" href="../css/screen.css" type="text/css"/>
    <link rel="stylesheet" href="../css/print.css" type="text/css"/>
    <title>Secretaries</title>
</head>
<body>

<h1>All catalogued secretaries</h1>


<display:table name="allSecretaries">
    <display:column property="id" title="ID"/>
    <display:column property="name" title="Name"/>
    <display:column property="street" title="Address"/>
    <display:column property="streetNo" title="Number"/>
    <display:column property="city" title="City"/>
    <display:column property="zip" title="ZIP Code"/>
</display:table>


</body>
</html>



