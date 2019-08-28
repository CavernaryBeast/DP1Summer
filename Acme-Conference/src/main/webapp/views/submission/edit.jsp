<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="submission/author/edit.do"
	modelAttribute="submission">

	<form:hidden path="id" />
	<form:hidden path="version" />
<form:hidden path="reviewers" />



	<fieldset>
		<legend>
			<spring:message code="submission.paper" />
		</legend>

		<acme:textbox code="submission.paper.title" path="paper.title" />
		<br />

		<acme:select items="${authors}" itemLabel="userAccount.username"
			code="submission.paper.authors" path="paper.authors" />
		<br />


		<acme:textbox code="submission.paper.summary" path="paper.summary" />
		<spring:message code="submission.paper.exampleDocument" />
		<br />

		<acme:textbox code="submission.paper.document" path="paper.document" />
		<br />

	</fieldset>
	<br />

	<spring:message code="submission.conference" />
	<select id="conferenceId" name="conferenceId">
		<jstl:forEach var="c" items="${conferences}">
			<option value="${c.id}">${c.title}</option>
		</jstl:forEach>
	</select>
	<br />
	<br />
	<acme:submit name="save" code="submission.save" />

</form:form>


<acme:button url="/" code="submission.cancel" />