<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="actor.name"/>: <jstl:out value="${sponsor.name}"/>
<br/>

<spring:message code="actor.middleName"/>: <jstl:out value="${sponsor.middleName}"/>
<br/>

<spring:message code="actor.surname"/>: <jstl:out value="${sponsor.surname}"/>
<br/>

<spring:message code="actor.photo"/>: <jstl:out value="${sponsor.photo}"/>
<br/>

<spring:message code="actor.email"/>: <jstl:out value="${sponsor.email}"/>
<br/>

<spring:message code="actor.phoneNumber"/>: <jstl:out value="${sponsor.phoneNumber}"/>
<br/>

<spring:message code="actor.address"/>: <jstl:out value="${sponsor.address}"/>
<br/>

<spring:message code="actor.userAccount.username"/>: <jstl:out value="${sponsor.userAccount.username}"/>
<br/>

<acme:button url="sponsorship/sponsor/list.do?sponsorId=${sponsor.id}" code="sponsorships.list"/>

<acme:button url="sponsor/edit.do" code="actor.edit"/>

<acme:button url="/" code="actor.back.welcome"/>