<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>

<t:doctor title="New doctor" action="/healthish/doctor/saveCreate.do" submitValue="Create" readOnly="">
    <li><label for="registerNumber">Register Number: </label><input id="registerNumber" name="registerNumber" type="text" value="${doctor.registerNumber}" ${readOnly}/></li>
</t:doctor>
