<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${actionURI}" modelAttribute="comment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />

	<form:hidden path="author" />

	<acme:textarea code="comment.title" path="title" />

	<acme:textarea code="comment.text" path="text" />

	<acme:submit name="save" code="comment.save" />

	<acme:button url="/" code="comment.cancel" />
</form:form>