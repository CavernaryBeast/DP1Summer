<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

	<p><spring:message code="reckon.tracer" />: <jstl:out value = "${reckon.tracer}"/></p>
	<p><spring:message code="reckon.publicationMoment" />: <jstl:out value = "${reckon.publicationMoment}"/></p>
	<p><spring:message code="reckon.body" />: <jstl:out value = "${reckon.body}"/></p>
	<p><spring:message code="reckon.picture" />: <jstl:out value = "${reckon.picture}"/></p>
		<p><spring:message code="reckon.isFinal" />: <jstl:out value = "${reckon.isFinal}"/></p>
	<br />
	
	<acme:button url="reckon/administrator/list.do?conferenceId=${reckon.conference.id}" code="reckon.back"/>

