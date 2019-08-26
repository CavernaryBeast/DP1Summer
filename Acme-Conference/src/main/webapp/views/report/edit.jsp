<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="report/edit.do" modelAttribute="report">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="submission" />
	<form:hidden path="reviewer" />
	<form:hidden path="comments" />

	<acme:textbox code="report.originalityScore" path="originalityScore" />

	<acme:textbox code="report.qualityScore" path="qualityScore" />

	<acme:textbox code="report.readabilityScore" path="readabilityScore" />

	<acme:textarea code="report.decision" path="decision" />

	<acme:submit name="save" code="report.save" />

	<acme:button
		url="/submission/reviewer/show.do?submissionId=${report.submission.id}"
		code="report.cancel" />

</form:form>