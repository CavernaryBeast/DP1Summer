<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="conference/administrator/saveReviewer.do" modelAttribute="submission">

	<form:hidden path="id" />
		<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="moment" />
	<form:hidden path="status" />
	<form:hidden path="author" />
	<form:hidden path="paper" />
<form:hidden path="reviewers" />

			<spring:message code="submission.chooseReviewers" />
			<select id="reviewerId" name="reviewerId">
			<jstl:forEach var="r" items="${reviewers}">
				<option value="${r.id}">${r.name}</option>
			</jstl:forEach>
		</select>
			<br />
	<br />
	<acme:submit name="save" code="submission.save" />

</form:form>


	<acme:button url="/" code="submission.cancel" />