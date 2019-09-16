<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<p><spring:message code="vaste.ticker" />: <jstl:out value = "${vaste.ticker}"/></p>
	<p><spring:message code="vaste.publicationMoment" />: <jstl:out value = "${vaste.publicationMoment}"/></p>
	<p><spring:message code="vaste.body" />: <jstl:out value = "${vaste.body}"/></p>
	<p><spring:message code="vaste.picture" />: <jstl:out value = "${vaste.picture}"/></p>
		<p><spring:message code="vaste.isFinal" />: <jstl:out value = "${vaste.isFinal}"/></p>
	<br />

