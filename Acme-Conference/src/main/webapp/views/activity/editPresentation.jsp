<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="activity/administrator/editPresentation.do?conferenceId=${conferenceId}" modelAttribute="presentation">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="type"/>
	<form:hidden path="sections"/>

	<acme:textbox code="acivity.title" path="title"/>
		<br />
		<acme:textbox code="activity.startMoment" path="startMoment"/>
		<br />
		<acme:textbox code="activity.duration" path="duration"/>
		<br />
		<acme:textbox code="activity.room" path="room"/>
		<br />
		<acme:textbox code="activity.summary" path="summary"/>
		<br />
		<acme:textarea code="activity.attachments" path="attachments"/>
		<br />
		<acme:textarea code="activity.speakers" path="speakers"/>
		<br />
		
		<jstl:if test="${  presentation.paper eq null  }">
		<acme:selectNotOptional items="${papers}" itemLabel="title"
		code="activity.paper" path="paper" />
			<br />
		</jstl:if>
		

		
	<acme:submit name="save" code="activity.save" />

</form:form>


	<acme:button url="/" code="activity.cancel" />