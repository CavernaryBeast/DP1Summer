<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="creditcard/author/edit.do" method="POST"
	modelAttribute="creditcard">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<acme:textbox code="creditCard.holderName" path="holderName" />

	<acme:textbox code="creditCard.brandName" path="brandName" />

	<acme:textbox code="creditCard.number" path="number" />

	<acme:textbox code="creditCard.expirationMonth" path="expirationMonth" />

	<acme:textbox code="creditCard.expirationYear" path="expirationYear" />

	<acme:textbox code="creditCard.cvv" path="cvv" />

	<form:label path="make">
		<spring:message code="creditCard.make" />: 
	</form:label>

	<form:select id="makes" path="creditCard.make">
		<jstl:forEach items="${makes}" var="make">
			<form:option value="${make}" />
		</jstl:forEach>
	</form:select>

	<acme:submit name="save" code="creditCard.save" />

	<acme:button url="creditcard/author/list.do" code="creditCard.back" />
</form:form>