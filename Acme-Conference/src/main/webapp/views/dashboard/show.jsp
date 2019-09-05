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

<h2><spring:message code = "dashboard.conferencesPerCategoryStats" /></h2>
		<p><spring:message code = "dashboard.min" /> : <jstl:out value="${conferencesPerCategoryMin}"/></p>
		<p><spring:message code = "dashboard.max" /> : <jstl:out value="${conferencesPerCategoryMax}"/></p>
		<p><spring:message code = "dashboard.avg" /> : <jstl:out value="${conferencesPerCategoryAvg}"/></p>
		<p><spring:message code = "dashboard.stddev" /> : <jstl:out value="${conferencesPerCategoryStd}"/></p>
	
		<br/>	
		
<h2><spring:message code = "dashboard.commentsPerConferenceStats" /></h2>
		<p><spring:message code = "dashboard.min" /> : <jstl:out value="${commentsPerConferenceMin}"/></p>
		<p><spring:message code = "dashboard.max" /> : <jstl:out value="${commentsPerConferenceMax}"/></p>
		<p><spring:message code = "dashboard.avg" /> : <jstl:out value="${commentsPerConferenceAvg}"/></p>
		<p><spring:message code = "dashboard.stddev" /> : <jstl:out value="${commentsPerConferenceStd}"/></p>
	
		<br/>			
		
<h2><spring:message code = "dashboard.commentsPerActivityStats" /></h2>
		<p><spring:message code = "dashboard.min" /> : <jstl:out value="${commentsPerActivityStatsMin}"/></p>
		<p><spring:message code = "dashboard.max" /> : <jstl:out value="${commentsPerActivityStatsMax}"/></p>
		<p><spring:message code = "dashboard.avg" /> : <jstl:out value="${commentsPerActivityStatsAvg}"/></p>
		<p><spring:message code = "dashboard.stddev" /> : <jstl:out value="${commentsPerActivityStatsStd}"/></p>
	
		<br/>
		
		<h2><spring:message code = "dashboard.reckonsPerConferenceStats" /></h2>
		<p><spring:message code = "dashboard.avg" /> : <jstl:out value="${reckonsPerConferenceStatsAvg}"/></p>
		<p><spring:message code = "dashboard.stddev" /> : <jstl:out value="${reckonsPerConferenceStatsStd}"/></p>
		<p><spring:message code = "dashboard.ratioPublished" /> : <jstl:out value="${ratioPublished}"/></p>
		<p><spring:message code = "dashboard.ratioUnpublished" /> : <jstl:out value="${ratioUnpublished}"/></p>
	
		<br/>				


</security:authorize>