<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="activity/administrator/edit.do?conferenceId=${conferenceId}" modelAttribute="activity">

	<form:hidden path="id" />
	<form:hidden path="version" />
<form:hidden path="type"/>

	<acme:textbox code="acivity.title" path="title"/>
		<br />
		<acme:textbox code="acivity.title" path="startMoment"/>
		<br />
		<acme:textbox code="acivity.title" path="duration"/>
		<br />
		<acme:textbox code="acivity.title" path="room"/>
		<br />
		<acme:textbox code="acivity.title" path="summary"/>
		<br />
		<acme:textarea code="acivity.title" path="attachments"/>
		<br />
		
		<jstl:if test="${activity.type == 'PRESENTATION' }">
		<acme:select items="${submissions}" itemLabel="userAccount.username"
		code="activity.paper" path="activity.paper" />
			<br />
		
		</jstl:if>
		
		
		<fieldset>
		<legend> <spring:message   code="submission.paper"  />   </legend>
		
		<acme:textbox code="activity.paper.title" path="paper.title"/>
		<br />
		
		<acme:select items="${authors}" itemLabel="userAccount.username"
		code="submission.paper.authors" path="paper.authors" />
			<br />
		
		
		<acme:textbox code="submission.paper.summary" path="paper.summary"/>
		<br />
		
		<acme:textbox code="submission.paper.document" path="paper.document"/>
		<br />
	
		</fieldset>
	<br />
			
			<spring:message code="submission.conference" />
			<select id="conferenceId" name="conferenceId">
			<jstl:forEach var="c" items="${conferences}">
				<option value="${c.id}">${c.title}</option>
			</jstl:forEach>
		</select>
			<br />
	<br />
	<acme:submit name="save" code="submission.save" />

</form:form>


	<acme:button url="/" code="submission.cancel" />