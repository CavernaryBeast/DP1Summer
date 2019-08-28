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

	<p><spring:message code="registration.author" />: <jstl:out value = "${registration.author.userAccount.username}"/></p>


	<fieldset>
		<legend> <spring:message   code="registration.CreditCard"  />   </legend>
		
		<p><spring:message code="registration.creditCard.holderName" />: <jstl:out value = "${registration.creditCard.holderName}"/></p>
		<p><spring:message code="registration.creditCard.brandName" />: <jstl:out value = "${registration.creditCard.brandName}"/></p>
		<p><spring:message code="registration.creditCard.number" />: <jstl:out value = "${registration.creditCard.number}"/></p>
		<p><spring:message code="registration.creditCard.expirationMonth" />: <jstl:out value = "${registration.creditCard.expirationMonth}"/></p>
		<p><spring:message code="registration.creditCard.expirationYear" />: <jstl:out value = "${registration.creditCard.expirationYear}"/></p>
		<p><spring:message code="registration.creditCard.cvv" />: <jstl:out value = "${registration.creditCard.cvv}"/></p>
		<p><spring:message code="registration.creditCard.make" />: <jstl:out value = "${registration.creditCard.make}"/></p>

</fieldset>
	<br />
<acme:button url="/" code="registration.cancel" />