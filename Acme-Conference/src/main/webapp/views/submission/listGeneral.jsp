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

<fieldset>
		<legend> <spring:message   code="submission.acceptedL"  />   </legend>	
<display:table pagesize="10" name="${accepted}" id="row"
	requestURI="${requestURI}">

<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment" />
<display:column property="status" titleKey="submission.status" />
<display:column  titleKey="submission.reviewers" >
<jstl:forEach var="r" items="${row.reviewers }" >
<jstl:out value="${r.userAccount.username}"></jstl:out>
</jstl:forEach>
</display:column>
	<display:column titleKey="submission.assign">
	<jstl:choose>
	<jstl:when test="${conference.notificationDeadline < now}">
	<spring:message code="submission.cantAssingNDElapsed" />
	</jstl:when>	
	<jstl:when test="${fn:length(row.reviewers) ge 3}">
	<spring:message code="submission.cantAddMoreReviewers" />
	</jstl:when>
	<jstl:otherwise>
	<a href="conference/administrator/addReviewer.do?submissionId=${row.id}"><spring:message code = "submission.assign"/></a>
	</jstl:otherwise>
	</jstl:choose>
		</display:column>
		<display:column titleKey="submission.assignAutomatic">
		<jstl:choose>
	<jstl:when test="${fn:length(row.reviewers) ge 3}">
	<spring:message code="submission.cantAddMoreReviewers" />
	</jstl:when>
	<jstl:otherwise>

				<a href="conference/administrator/assignSubmissionAutomatic.do?submissionId=${row.id}"><spring:message code = "submission.assignAutomatic"/></a>
	</jstl:otherwise>
	</jstl:choose>
	
		</display:column>
</display:table>
</fieldset>

<fieldset>
		<legend> <spring:message   code="submission.underReviewL"  />   </legend>	
<display:table pagesize="10" name="${underReview}" id="row"
	requestURI="${requestURI}">

<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment" />
<display:column property="status" titleKey="submission.status" />
<display:column  titleKey="submission.reviewers" >
<jstl:forEach var="r" items="${row.reviewers }" >
<jstl:out value="${r.userAccount.username}"></jstl:out>
</jstl:forEach>
</display:column>
	<display:column titleKey="submission.assign">
	<jstl:choose>
	<jstl:when test="${conference.notificationDeadline < now}">
	<spring:message code="submission.cantAssingNDElapsed" />
	</jstl:when>
	<jstl:when test="${fn:length(row.reviewers) ge 3}">
	<spring:message code="submission.cantAddMoreReviewers" />
	</jstl:when>
	<jstl:otherwise>
	<a href="conference/administrator/addReviewer.do?submissionId=${row.id}"><spring:message code = "submission.assign"/></a>
	</jstl:otherwise>
	</jstl:choose>
		</display:column>
		<display:column titleKey="submission.assignAutomatic">
		<jstl:choose>
	<jstl:when test="${conference.notificationDeadline < now}">
	<spring:message code="submission.cantAssingNDElapsed" />
	</jstl:when>		
	<jstl:when test="${fn:length(row.reviewers) ge 3}">
	<spring:message code="submission.cantAddMoreReviewers" />
	</jstl:when>
	<jstl:otherwise>
				<a href="conference/administrator/assignSubmissionAutomatic.do?submissionId=${row.id}"><spring:message code = "submission.assignAutomatic"/></a>
	</jstl:otherwise>
	</jstl:choose>
	
		</display:column>
</display:table>
</fieldset>

<fieldset>
		<legend> <spring:message   code="submission.rejectedL"  />   </legend>	
<display:table pagesize="10" name="${rejected}" id="row"
	requestURI="${requestURI}">

<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment" />
<display:column property="status" titleKey="submission.status" />	
<display:column  titleKey="submission.reviewers" >
<jstl:forEach var="r" items="${row.reviewers }" >
<jstl:out value="${r.userAccount.username}"></jstl:out>
</jstl:forEach>
</display:column>
	<display:column titleKey="submission.assign">
	<jstl:choose>
	<jstl:when test="${conference.notificationDeadline < now}">
	<spring:message code="submission.cantAssingNDElapsed" />
	</jstl:when>	
	<jstl:when test="${fn:length(row.reviewers) ge 3}">
	<spring:message code="submission.cantAddMoreReviewers" />
	</jstl:when>
	<jstl:otherwise>
	<a href="conference/administrator/addReviewer.do?submissionId=${row.id}"><spring:message code = "submission.assign"/></a>
	</jstl:otherwise>
	</jstl:choose>
		</display:column>
		<display:column titleKey="submission.assignAutomatic">
		<jstl:choose>
	<jstl:when test="${fn:length(row.reviewers) ge 3}">
	<spring:message code="submission.cantAddMoreReviewers" />
	</jstl:when>
	<jstl:otherwise>

				<a href="conference/administrator/assignSubmissionAutomatic.do?submissionId=${row.id}"><spring:message code = "submission.assignAutomatic"/></a>
	</jstl:otherwise>
	</jstl:choose>
	
		</display:column>	
</display:table>
</fieldset>
<br>
	<spring:message code="submission.remember" />

