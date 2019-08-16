<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="submission/author/editPaper.do" modelAttribute="paper">

	<form:hidden path="id" />
	<form:hidden path="version" />

		
		
		<fieldset>
		<legend> <spring:message   code="submission.paper"  />   </legend>
		
		<acme:textbox code="submission.paper.title" path="title"/>
		<br />
		
		<acme:select items="${authors}" itemLabel="userAccount.username"
		code="submission.paper.authors" path="authors" />
			<br />
		
		
		<acme:textbox code="submission.paper.summary" path="summary"/>
		<br />
		
		<acme:textbox code="submission.paper.document" path="document"/>
		<br />
		
		
			<acme:radiobutton code="submission.paper.cameraReady" path="cameraReady"  codeTrue="submission.yes"  codeFalse="submission.no" />
	
		</fieldset>
	<br />
			
	
	<acme:submit name="save" code="submission.save" />

</form:form>


	<acme:button url="/" code="submission.cancel" />