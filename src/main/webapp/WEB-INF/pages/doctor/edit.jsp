<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>

<t:doctor title="Edit doctor" action="/healthish/doctor/saveEdit.do" submitValue="Edit" readOnly="">
    <li><label for="registerNumber">Register Number: </label><input id="registerNumber" name="registerNumber" type="text" value="${doctor.registerNumber}" ${readOnly}/></li>
</t:doctor>
