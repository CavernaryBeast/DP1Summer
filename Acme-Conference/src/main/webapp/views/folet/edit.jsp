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

	<form:form id = "form" action="folet/administrator/edit.do" modelAttribute="folet">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<acme:textbox code="folet.picture" path="picture"/>
		<br />
		<acme:textarea code="folet.body" path="body"/>
		<br />
		
		<form:label path = "isFinal">
			<spring:message code = "folet.isFinal"/>
		</form:label>
		<form:radiobutton path="isFinal" value="true"/><spring:message code="folet.yes"/>
		<form:radiobutton path="isFinal" value="false"/><spring:message code="folet.no"/>
		<form:errors cssClass="error" path="isFinal"/>
		<br />
		
		<input type="submit" name = "save" value ="<spring:message code = 'folet.save' />"/>
	</form:form>
	
	<input type="button" name = "back" value ="<spring:message code = 'folet.back' />" class = "btn"
	onclick="javascript: relativeRedir('folet/author/list.do');" />

</security:authorize>