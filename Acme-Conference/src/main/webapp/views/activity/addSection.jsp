<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="activity/administrator/saveSection.do?activityId=${activityId}" modelAttribute="section">

	<form:hidden path="id" />
	<form:hidden path="version" />


	<acme:textbox code="activity.section.title" path="title"/>
		<br />
		<acme:textbox code="activity.section.summary" path="summary"/>
		<br />
		<acme:textarea code="activity.section.pictures" path="pictures"/>
		<br />
				
	<acme:submit name="save" code="activity.save" />

</form:form>


	<acme:button url="/" code="activity.cancel" />