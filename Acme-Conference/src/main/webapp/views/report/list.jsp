<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="reports" id="report" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	<display:column property="submission.ticker" titleKey="report.submission" />
	<display:column property="originalityScore"
		titleKey="report.originalityScore" />
	<display:column property="qualityScore" titleKey="report.qualityScore" />
	<display:column property="readabilityScore"
		titleKey="report.readabilityScore" />
	<display:column property="decision" titleKey="report.decision" />
	<display:column titleKey="report.reports">
		<a
			href="report/reviewer/display.do?reportId=${report.id}">
			<spring:message code="report.display" />
		</a>
	</display:column>
</display:table>
<br />