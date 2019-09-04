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

	<p><spring:message code="sponsorship.banner" />: 
	<br>
	<img src="${sponsorship.banner}" style="width:250px;height:250px;">
	<br>
	<p><spring:message code="sponsorship.targetURL" />: <jstl:out value = "${sponsorship.targetURL}"/></p>
	<p><spring:message code="sponsorship.sponsor" />: <jstl:out value = "${sponsorship.sponsor.userAccount.username}"/></p>
	<p><spring:message code="sponsorship.conference" />: <jstl:out value = "${sponsorship.conference.title}"/></p>
	
		<fieldset>
		<legend> <spring:message   code="sponsorship.creditCard"  />   </legend>
			<p><spring:message code="sponsorship.creditCard.holderName" />: <jstl:out value = "${sponsorship.creditCard.holderName}"/></p>
			<p><spring:message code="sponsorship.creditCard.brandName" />: <jstl:out value = "${sponsorship.creditCard.brandName}"/></p>
			<p><spring:message code="sponsorship.creditCard.number" />: <jstl:out value = "${sponsorship.creditCard.number}"/></p>
			<p><spring:message code="sponsorship.creditCard.expirationMonth" />: <jstl:out value = "${sponsorship.creditCard.expirationMonth}"/></p>
			<p><spring:message code="sponsorship.creditCard.expirationYear" />: <jstl:out value = "${sponsorship.creditCard.expirationYear}"/></p>
			<p><spring:message code="sponsorship.creditCard.cvv" />: <jstl:out value = "${sponsorship.creditCard.cvv}"/></p>
		</fieldset>

<acme:button url="/" code="submission.cancel" />