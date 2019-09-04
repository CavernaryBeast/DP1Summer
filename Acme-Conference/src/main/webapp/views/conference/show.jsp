<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jstl:set var="spanish" value="es" />

<p>
	<spring:message code="conference.title" />
	:
	<jstl:out value="${conference.title}" />
</p>
<p>
	<spring:message code="conference.acronym" />
	:
	<jstl:out value="${conference.acronym}" />
</p>
<p>
	<spring:message code="conference.venue" />
	:
	<jstl:out value="${conference.venue}" />
</p>
<p>
	<spring:message code="conference.submissionDeadline" />
	:
	<jstl:out value="${conference.submissionDeadline}" />
</p>
<p>
	<spring:message code="conference.notificationDeadline" />
	:
	<jstl:out value="${conference.notificationDeadline}" />
</p>
<p>
	<spring:message code="conference.cameraReadyDeadline" />
	:
	<jstl:out value="${conference.cameraReadyDeadline}" />
</p>
<p>
	<spring:message code="conference.startDate" />
	:
	<jstl:out value="${conference.startDate}" />
</p>
<p>
	<spring:message code="conference.endDate" />
	:
	<jstl:out value="${conference.endDate}" />
</p>
<p>
	<spring:message code="conference.summary" />
	:
	<jstl:out value="${conference.summary}" />
</p>
<p>
	<spring:message code="conference.fee" />
	:
	<jstl:out value="${conference.fee}" />
</p>
<security:authorize access="hasRole('ADMINISTRATOR')">
	<p>
		<spring:message code="conference.isFinal" />
		:
		<jstl:out value="${conference.isFinal}" />
	</p>
</security:authorize>
<p>
	<spring:message code="conference.administrator" />
	:
	<jstl:out value="${conference.administrator.userAccount.username}" />
</p>
<br>
<jstl:if test="${fn:length(sponsorships ) > 0}">
	<p>
		<spring:message code="conference.sponsoredContent" />
		: <br> <img src="${randomSponsorship.banner}"> <br />
</jstl:if>
<acme:button
	url="comment/listConferenceComments.do?conferenceId=${conference.id}"
	code="conference.listComments" />
<security:authorize access="hasRole('ADMINISTRATOR')">
	<acme:button
		url="message/administrator/actorsSubmitted.do?conferenceId=${conference.id}"
		code="conference.actorSubmitted" />

	<acme:button
		url="message/administrator/actorsRegistered.do?conferenceId=${conference.id}"
		code="conference.actorRegistered" />
</security:authorize>

<acme:button url="/" code="submission.cancel" />