<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="sponsorship/sponsor/edit.do" modelAttribute="sponsorship">

	<form:hidden path="id" />
	<form:hidden path="version" />
<form:hidden path="creditCard.id" />
<form:hidden path="creditCard.version" />

	<acme:textbox code="sponsorship.banner" path="banner"/>
		<br />
	<acme:textbox code="sponsorship.targetURL" path="targetURL"/>
		
	<acme:selectNotOptional items="${conferences}" itemLabel="title"
			code="sponsorship.chooseConferences" path="conference" />
		<br />


		
	<fieldset>
		<legend>
			<spring:message code="sponsorship.creditCard" />
		</legend>

		<acme:textbox code="sponsorship.creditCard.holderName" path="creditCard.holderName" />
		<br />

		<acme:textbox code="sponsorship.creditCard.brandName" path="creditCard.brandName" />
		<br />

		<acme:textbox code="sponsorship.creditCard.number" path="creditCard.number" />
		<br />
		
		<acme:textbox code="sponsorship.creditCard.expirationMonth" path="creditCard.expirationMonth" />
		<br />
		
		<acme:textbox code="sponsorship.creditCard.expirationYear" path="creditCard.expirationYear" />
		<br />
		
		<acme:textbox code="sponsorship.creditCard.cvv" path="creditCard.cvv" />
		<br />
		
		w
		
		
			<form:label path="creditCard.make">
		<spring:message code="sponsorship.creditCard.make" />: 
	</form:label>

	<form:select id="makes" path="creditCard.make">
		<jstl:forEach items="${makes}" var="make">
			<form:option value="${make}" />
		</jstl:forEach>
	</form:select>		
	</fieldset>

		
	<acme:submit name="save" code="activity.save" />

</form:form>


	<acme:button url="/" code="activity.cancel" />