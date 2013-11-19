<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<h2><spring:message code="tool.listOfPatients" /></h2>
<TABLE>
	<form method="post" action="toolLink.form">
	<TR>
		<TD>Patient identifier type</TD>
		<TD><select name="patientIdentifierType">
			<option value="TRACnet">TRACnet ID</option>
			<option value="NID">NID</option>
		</select></TD>
	</TR>
	<TR>
		<TD>FOSA NUMBER</TD>
		<TD><input type="text" name="FOSAId" value=""></TD>
	</TR>
	<TR>
		<TD></TD>
		<TD><input type="submit" value="submit" /></TD>
	</TR>
	</form>

</TABLE>
<br />


<font size="3" color="red"> ${validationMessage}</font>

<a href="updateLink.form">Update Existing FOSA NUMBER</a> <br/><br/><br/>

<c:if  test="${oldAndNewPatientIdentifiers != null}">
<table cellspacing="1" cellpadding="2" border="2">
<tr>
<td><b>Number</b></td>  <td><b> Old patient identifier</b> </td><td><b> New patient identifier</b></td></tr>
<c:forEach var="oldAndNewPatientIdentifier" items="${oldAndNewPatientIdentifiers}"
   varStatus="status">

<td>${status.count}</td><td>${oldAndNewPatientIdentifier.key}</td><td> ${oldAndNewPatientIdentifier.value}</td></tr>
   
</c:forEach> 

</table>
</c:if>
<br />



<%@ include file="/WEB-INF/template/footer.jsp"%>
