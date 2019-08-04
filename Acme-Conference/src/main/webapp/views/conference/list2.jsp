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


<jstl:set var="spanish" value="es"/>

<spring:message code="conference.filter.all" var="allLabel"/>
<spring:message code="conference.submission5Days" var="submissionLabel"/>
<spring:message code="conference.notification5Days" var="notificationLabel"/>
<spring:message code="conference.cameraReady5Days" var="cameraReadyLabel"/>
<spring:message code="conference.organised5Days" var="organisedLabel"/>

	<form:form action="${actionFilter}" modelAttribute="administratorfilterConferenceForm" >

			    <form:select path="typeFilter">
	        <form:option label="${allLabel}" value=""/>
	        <form:option label="${submissionLabel}" value="SUBMISSION"/>
	        <form:option label="${notificationLabel}" value="NOTIFICATION"/>
	        <form:option label="${cameraReadyLabel}" value="CAMERAREADY"/>
	        <form:option label="${organisedLabel}" value="ORGANISED"/>
	    </form:select>
	    <br/>
		<acme:submit code="conference.filter" name="filter" />
	</form:form>
	
	


<display:table pagesize="10" name="${conferences}" id="row"
	requestURI="${requestURI}">

<display:column property="title" titleKey="conference.title" />
<display:column property="venue" titleKey="conference.venue" />
<display:column property="startDate" titleKey="conference.startDate" />
<display:column property="endDate" titleKey="conference.endDate" />		

	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column titleKey="conference.edit">
			<a href="conference/edit.do?conferenceId=${row.id}"><spring:message
					code="conference.edit" /></a>
		</display:column>

	</security:authorize>
</display:table>

<security:authorize access="hasRole('ADMINISTRATOR')">

	<input type="button" name="create"
		value="<spring:message code = 'conference.create' />" class="btn" 	onclick="javascript: relativeRedir('conference/administrator/create.do');" />


</security:authorize>