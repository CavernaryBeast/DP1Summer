<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="report.reviewer" />
:
<jstl:out value="${report.reviewer.userAccount.username}" />
<br />

<spring:message code="report.submission" />
:
<a
	href="submission/<jstl:out value="${role}" />
	/display.do?submissionId=${report.submission.id}">
	<jstl:out value="${report.submission.ticker}" />
</a>
<br />

<spring:message code="report.originalityScore" />
:
<jstl:out value="${report.originalityScore}" />
<br />

<spring:message code="report.qualityScore" />
:
<jstl:out value="${report.qualityScore}" />
<br />

<spring:message code="report.readabilityScore" />
:
<jstl:out value="${report.readabilityScore}" />
<br />

<spring:message code="report.decision" />
:
<jstl:out value="${report.decision}" />
<br />

<jstl:choose>

	<jstl:when test="${role eq 'AUTHOR'}">
		<acme:button
			url="submission/author/display.do?submissionId=${report.submission.id}"
			code="report.back" />
	</jstl:when>

	<jstl:when test="${role eq 'REVIEWER'}">
		<acme:button url="report/reviewer/list.do" code="report.back" />
	</jstl:when>

</jstl:choose>