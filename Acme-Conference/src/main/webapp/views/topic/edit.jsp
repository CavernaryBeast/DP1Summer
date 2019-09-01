<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="topic/administrator/edit.do" method="POST"
	modelAttribute="topic">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<acme:textbox code="topic.name" path="name" />

	<acme:textbox code="topic.nameEs" path="nameEs" />

	<acme:submit name="save" code="confParams.save" />

	<acme:button url="topic/administrator/list.do" code="topic.back" />

</form:form>