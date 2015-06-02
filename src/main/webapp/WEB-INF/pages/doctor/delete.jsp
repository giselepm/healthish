<%@ taglib prefix="t" tagdir="/WEB-INF/tags/templates" %>

<t:doctor title="Delete doctor" action="/healthish/doctor/saveDelete.do" submitValue="Delete" readOnly="readOnly">
    <li><label for="registerNumber">Register Number: </label><input id="registerNumber" name="registerNumber" type="text" value="${doctor.registerNumber}" ${readOnly}/></li>
</t:doctor>

