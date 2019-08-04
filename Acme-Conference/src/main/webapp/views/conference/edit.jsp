<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="conference/administrator/edit.do" modelAttribute="conference">

	<form:hidden path="id" />
	<form:hidden path="version" />


		<acme:textbox code="conference.title" path="title"/>
		<br />
		
		<acme:textbox code="conference.acronym" path="acronym"/>
		<br />
		
		<acme:textbox code="conference.venue" path="venue"/>
		<br />
		
		<acme:textbox code="conference.submissionDeadline" path="submissionDeadline"/>
		<br />
		
		<acme:textbox code="conference.notificationDeadline" path="notificationDeadline"/>
		<br />
		
		<acme:textbox code="conference.cameraReadyDeadline" path="cameraReadyDeadline"/>
		<br />
		
		<acme:textbox code="conference.startDate" path="startDate"/>
		<br />
		
		<acme:textbox code="conference.endDate" path="endDate"/>
		<br />
		
		<acme:textbox code="conference.summary" path="summary"/>
		<br />
		
		<acme:textbox code="conference.fee" path="fee"/>
		<br />
		
		<acme:radiobutton code="conference.isFinal" path="isFinal"  codeTrue="conference.yes"  codeFalse="conference.no" />

	
	<acme:submit name="save" code="conference.save" />

	<acme:button url="/" code="conference.cancel" />
</form:form>