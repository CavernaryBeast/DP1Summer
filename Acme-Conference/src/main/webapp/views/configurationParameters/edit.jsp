<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="configurationParameters/author/edit.do" method="POST"
	modelAttribute="confParams">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="topics" />

	<acme:textbox code="confParams.sysName" path="sysName" />

	<acme:textbox code="confParams.banner" path="banner" />

	<acme:textbox code="confParams.message" path="message" />

	<acme:textbox code="confParams.messageEs" path="messageEs" />

	<acme:textbox code="confParams.countryCode" path="countryCode" />

	<acme:textbox code="confParams.defaultCountry" path="defaultCountry" />

	<acme:textarea code="confParams.creditCardMakes" path="creditCardMakes" />

	<acme:submit name="save" code="confParams.save" />

	<acme:button url="confParams/administrator/display.do"
		code="confParams.back" />

</form:form>