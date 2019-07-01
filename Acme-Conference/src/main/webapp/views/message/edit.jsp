<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="message/edit.do" modelAttribute="m">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="sender" />

	<acme:select items="${recipients}" itemLabel="userAccount.username"
		code="message.recipients" path="recipients" />

	<acme:textbox code="message.subject" path="subject" />

	<jstl:choose>
		<jstl:when test="${lang == en}">
			<jstl:set var="itemLabel" value="name" />
		</jstl:when>
		<jstl:otherwise>
			<jstl:set var="itemLabel" value="nameEs" />
		</jstl:otherwise>
	</jstl:choose>

	<acme:select items="${topics}" itemLabel="${itemLabel}"
		code="message.topics" path="topic" />

	<acme:textarea code="message.body" path="body" />

	<acme:submit name="save" code="message.save" />

	<acme:button url="/" code="message.cancel" />
</form:form>