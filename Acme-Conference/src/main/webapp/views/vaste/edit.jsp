<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access = "hasRole('ADMINISTRATOR')">

	<form:form id = "form" action="vaste/administrator/edit.do?conferenceId=${conferenceId }" modelAttribute="vaste">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<acme:textbox code="vaste.picture" path="picture"/>
		<br />
		<acme:textarea code="vaste.body" path="body"/>
		<br />
		
		<form:label path = "isFinal">
			<spring:message code = "vaste.isFinal"/>
		</form:label>
		<form:radiobutton path="isFinal" value="true"/><spring:message code="vaste.yes"/>
		<form:radiobutton path="isFinal" value="false"/><spring:message code="vaste.no"/>
		<form:errors cssClass="error" path="isFinal"/>
		<br />
		
		<input type="submit" name = "save" value ="<spring:message code = 'vaste.save' />"/>
	</form:form>
	
	<input type="button" name = "back" value ="<spring:message code = 'vaste.back' />" class = "btn"
	onclick="javascript: relativeRedir('vaste/administrator/list.do?conferenceId=${conferenceId }');" />

</security:authorize>