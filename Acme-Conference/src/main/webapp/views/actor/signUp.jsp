<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="actor.signUp.subtitle" />
<br />

<acme:button url="actor/createAuthor.do" code="actor.signUp.author" />
<acme:button url="actor/createReviewer.do" code="actor.signUp.reviewer" />

<security:authorize access="hasRole('ADMINISTRATOR')">

	<acme:button url="actor/administrator/createAdministrator.do"
		code="actor.signUp.administrator" />
	<acme:button url="actor/administrator/createSponsor.do"
		code="actor.signUp.sponsor" />

</security:authorize>
<br />

<acme:button url="security/login.do" code="actor.cancel" />
