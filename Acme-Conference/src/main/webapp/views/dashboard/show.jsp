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


<security:authorize access="hasRole('ADMINISTRATOR')">

<h2><spring:message code = "dashboard.submissionsPerConferenceStats" /></h2>
		<p><spring:message code = "dashboard.min" /> : <jstl:out value="${submissionsPerConferenceStatsMin}"/></p>
		<p><spring:message code = "dashboard.max" /> : <jstl:out value="${submissionsPerConferenceStatsMax}"/></p>
		<p><spring:message code = "dashboard.avg" /> : <jstl:out value="${submissionsPerConferenceStatsAvg}"/></p>
		<p><spring:message code = "dashboard.stddev" /> : <jstl:out value="${submissionsPerConferenceStatsStd}"/></p>
	
		<br/>

<h2><spring:message code = "dashboard.registrationsPerConferenceStats" /></h2>
		<p><spring:message code = "dashboard.min" /> : <jstl:out value="${registrationsPerConferenceStatsMin}"/></p>
		<p><spring:message code = "dashboard.max" /> : <jstl:out value="${registrationsPerConferenceStatsMax}"/></p>
		<p><spring:message code = "dashboard.avg" /> : <jstl:out value="${registrationsPerConferenceStatsAvg}"/></p>
		<p><spring:message code = "dashboard.stddev" /> : <jstl:out value="${registrationsPerConferenceStatsStd}"/></p>
	
		<br/>
		
<h2><spring:message code = "dashboard.feesPerConferenceStats" /></h2>
		<p><spring:message code = "dashboard.min" /> : <jstl:out value="${feesPerConferenceStatsMin}"/></p>
		<p><spring:message code = "dashboard.max" /> : <jstl:out value="${feesPerConferenceStatseStatsMax}"/></p>
		<p><spring:message code = "dashboard.avg" /> : <jstl:out value="${feesPerConferenceStatsAvg}"/></p>
		<p><spring:message code = "dashboard.stddev" /> : <jstl:out value="${feesPerConferenceStatsStd}"/></p>
	
		<br/>		

<h2><spring:message code = "dashboard.daysPerConferenceStats" /></h2>
		<p><spring:message code = "dashboard.min" /> : <jstl:out value="${daysPerConferenceStatsMin}"/></p>
		<p><spring:message code = "dashboard.max" /> : <jstl:out value="${daysPerConferenceStatsMax}"/></p>
		<p><spring:message code = "dashboard.avg" /> : <jstl:out value="${daysPerConferenceStatsAvg}"/></p>
		<p><spring:message code = "dashboard.stddev" /> : <jstl:out value="${daysPerConferenceStatsStd}"/></p>
	
		<br/>		


</security:authorize>