<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="creditCard.holderName" />
:
<jstl:out value="${creditcard.holderName}" />
<br />

<spring:message code="creditCard.brandName" />
:
<jstl:out value="${creditcard.brandName}" />
<br />

<spring:message code="creditCard.number" />
:
<jstl:out value="${creditcard.number}" />
<br />

<spring:message code="creditCard.expirationMonth" />
:
<jstl:out value="${creditcard.expirationMonth}" />
<br />

<spring:message code="creditCard.expirationYear" />
:
<jstl:out value="${creditcard.expirationYear}" />
<br />

<spring:message code="creditCard.cvv" />
:
<jstl:out value="${creditcard.cvv}" />
<br />

<spring:message code="creditCard.make" />
:
<jstl:out value="${creditcard.make}" />
<br />

<acme:button
	url="creditcard/author/edit.do?creditcardId=<jstl:out value="${creditcard.id}" />"
	code="creditCard.edit" />

<acme:button url="creditcard/author/list.do" code="creditCard.back" />