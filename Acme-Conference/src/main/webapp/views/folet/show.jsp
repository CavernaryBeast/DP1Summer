<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<p><spring:message code="folet.ticker" />: <jstl:out value = "${folet.ticker}"/></p>
	<p><spring:message code="folet.publicationDate" />: <jstl:out value = "${folet.publicationDate}"/></p>
	<p><spring:message code="folet.body" />: <jstl:out value = "${folet.body}"/></p>
	<p><spring:message code="folet.picture" />: <jstl:out value = "${folet.picture}"/></p>
		<p><spring:message code="folet.isFinal" />: <jstl:out value = "${folet.isFinal}"/></p>
	<br />

