<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="comment/edit.do?type=${type}" modelAttribute="m">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="sender" />
	<jstl:if test="${not empty m.recipients}">
		<form:hidden path="recipients"/>
	</jstl:if>

	<jstl:if test="${empty m.recipients}">
	<acme:select items="${possibleRecipients}" itemLabel="userAccount.username"
		code="message.recipients" path="recipients" />
	</jstl:if>

	<acme:textbox code="message.subject" path="subject" />

	<jstl:choose>
		<jstl:when test="${lang eq 'en'}">

			<acme:select items="${topics}" itemLabel="name" code="message.topics"
				path="topic" />

		</jstl:when>
		<jstl:when test="${lang eq 'es'}">

			<acme:select items="${topics}" itemLabel="nameEs"
				code="message.topics" path="topic" />

		</jstl:when>
	</jstl:choose>


	<acme:textarea code="message.body" path="body" />

	<acme:submit name="save" code="message.save" />

	<acme:button url="/" code="message.cancel" />
</form:form>