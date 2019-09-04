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
<fmt:formatDate value="${schedule}" pattern="yyyy-MM-dd HH:mm:ss.S"
	var="scheduleDate" />
<p>
	<spring:message code="acivity.title" />
	:
	<jstl:out value="${activity.title}" />
</p>
<p>
	<spring:message code="activity.startMoment" />
	:
	<jstl:out value="${activity.startMoment}" />
</p>
<fieldset>
	<legend>
		<spring:message code="activity.schedule" />
	</legend>
	<p>
		<spring:message code="activity.startMoment" />
		:
		<jstl:out value="${activity.startMoment}" />
	</p>
	<p>
		<spring:message code="activity.duration" />
		:
		<jstl:out value="${activity.duration}" />
	</p>
	<p>
		<spring:message code="activity.finishHour" />
		:
		<jstl:out value="${scheduleDate}" />
	</p>
</fieldset>
<p>
	<spring:message code="activity.room" />
	:
	<jstl:out value="${activity.room}" />
</p>
<p>
	<spring:message code="activity.summary" />
	:
	<jstl:out value="${activity.summary}" />
</p>
<p>
	<spring:message code="activity.attachments" />
	:
	<jstl:out value="${activity.attachments}" />
</p>
<p>
	<spring:message code="activity.type" />
	:
	<jstl:out value="${activity.type}" />
</p>
<p>
	<spring:message code="activity.summary" />
	:
	<jstl:out value="${activity.summary}" />
</p>
<jstl:if test="${activity.type eq 'TUTORIAL' }">
	<p>
		<spring:message code="activity.sections" />
		:
		<jstl:forEach items="${activity.sections}" var="s">
			<jstl:out value="${s.title}" />
			<br>
		</jstl:forEach>
</jstl:if>

<br>

<jstl:if test="${activity.type eq 'PRESENTATION' }">
	<fieldset>
		<legend>
			<spring:message code="activity.showPaper" />
		</legend>
		<p>
			<spring:message code="activity.paper.title" />
			:
			<jstl:out value="${activity.paper.title}" />
		</p>
		<p>
			<spring:message code="activity.paper.showAuthors" />
			:
		</p>
		<jstl:forEach items="${activity.paper.authors }" var="a">
			<jstl:out value="${a.userAccount.username}" />
		</jstl:forEach>
		<p>
			<spring:message code="activity.paper.summary" />
			:
			<jstl:out value="${activity.paper.summary}" />
		</p>
		<p>
			<spring:message code="activity.paper.document" />
			:
			<jstl:out value="${activity.paper.document}" />
		</p>
		<p>
			<spring:message code="activity.paper.cameraReady" />
			:
			<jstl:out value="${activity.paper.cameraReady}" />
		</p>

	</fieldset>
	<br />
</jstl:if>
<acme:button
	url="comment/listActivityComments?activityId=${activity.id}"
	code="activity.listComments" />

<acme:button url="/" code="submission.cancel" />