<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="registration/author/edit.do" modelAttribute="registration">

	<form:hidden path="id" />
	<form:hidden path="version" />

	
		
		<fieldset>
		<legend> <spring:message   code="registration.CreditCard"  />   </legend>
		
		<acme:textbox code="registration.creditCard.holderName" path="creditCard.holderName"/>
		<br />
				
		<acme:textbox code="registration.creditCard.brandName" path="creditCard.brandName"/>
		<br />
		
		<acme:textbox code="registration.creditCard.number" path="creditCard.number"/>
		<br />
		
		<acme:textbox code="registration.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
		<br />
		
		<acme:textbox code="registration.creditCard.expirationYear" path="creditCard.expirationYear"/>
		<br />
		
		<acme:textbox code="registration.creditCard.cvv" path="creditCard.cvv"/>
		<br />		

<spring:message code="registration.creditCard.make" />
<form:select path="creditCard.make">
<form:options  items="${makes}"/>
</form:select>
			<br />
	
		</fieldset>
	<br />
			
			<spring:message code="registration.conference" />
			<select id="conferenceId" name="conferenceId">
			<jstl:forEach var="c" items="${conferences}">
				<option value="${c.id}">${c.title}</option>
			</jstl:forEach>
		</select>
			<br />
	<br />
	<acme:submit name="save" code="registration.save" />

</form:form>


	<acme:button url="/registration/author/list.do" code="submission.cancel" />