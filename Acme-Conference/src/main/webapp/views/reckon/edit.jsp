<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ADMINISTRATOR')">

	<form:form id="form"
		action="reckon/administrator/edit.do?conferenceId=${conferenceId }"
		modelAttribute="reckon">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="administrator" />

		<acme:textbox code="reckon.title" path="title" />
		<br />

		<acme:textbox code="reckon.picture" path="picture" />
		<br />
		<acme:textarea code="reckon.body" path="body" />
		<br />

		<form:label path="isFinal">
			<spring:message code="reckon.isFinal" />
		</form:label>
		<form:radiobutton path="isFinal" value="true" />
		<spring:message code="reckon.yes" />
		<form:radiobutton path="isFinal" value="false" />
		<spring:message code="reckon.no" />
		<form:errors cssClass="error" path="isFinal" />
		<br />

		<input type="submit" name="save"
			value="<spring:message code = 'reckon.save' />" />
	</form:form>

	<input type="button" name="back"
		value="<spring:message code = 'reckon.back' />" class="btn"
		onclick="javascript: relativeRedir('reckon/administrator/list.do?conferenceId=${reckon.conference.id}');" />

</security:authorize>