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

<jstl:forEach items="${comments}" var="comment">
	<ul>
		<li><spring:message code="comment.author" />: <jstl:choose>
				<jstl:when test="${comment.author != null}">
					<jstl:out value="${comment.author.userAccount.username}" />
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="comment.author.anonimous" />
				</jstl:otherwise>
			</jstl:choose> <br /> <spring:message code="comment.title" /> : <jstl:out
				value="${comment.title}" /> <br /> <spring:message
				code="comment.moment" /> : <jstl:out value="${comment.moment}" />
			<br /> <spring:message code="comment.text" /> : <jstl:out
				value="${comment.text}" /></li>
	</ul>
</jstl:forEach>
<br />

<jstl:choose>
	<jstl:when test="${object eq 'report'}">
		<acme:button url="comment/reviewer/create.do?reportId=${reportId}"
			code="report.addComment" />

		<acme:button url="report/reviewer/display.do?reportId=${reportId}"
			code="report.back" />
	</jstl:when>

	<jstl:when test="${object eq 'conference'}">
		<acme:button
			url="comment/createToConference.do?conferenceId=${conferenceId}"
			code="conference.addComment" />

		<acme:button url="conference/show.do?conferenceId=${conferenceId}"
			code="conference.back" />
	</jstl:when>

	<jstl:when test="${object eq 'activity'}">
		<acme:button
			url="comment/createToActivity.do?activityId=${activityId}"
			code="activity.addComment" />

		<acme:button url="activity/show.do?activityId=${activityId}"
			code="activity.back" />
	</jstl:when>
</jstl:choose>