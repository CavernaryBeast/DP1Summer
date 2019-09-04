<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="actor.name"/>: <jstl:out value="${author.name}"/>
<br/>

<spring:message code="actor.middleName"/>: <jstl:out value="${author.middleName}"/>
<br/>

<spring:message code="actor.surname"/>: <jstl:out value="${author.surname}"/>
<br/>

<spring:message code="actor.photo"/>: <jstl:out value="${author.photo}"/>
<br/>

<spring:message code="actor.email"/>: <jstl:out value="${author.email}"/>
<br/>

<spring:message code="actor.phoneNumber"/>: <jstl:out value="${author.phoneNumber}"/>
<br/>

<spring:message code="actor.address"/>: <jstl:out value="${author.address}"/>
<br/>

<spring:message code="actor.userAccount.username"/>: <jstl:out value="${author.userAccount.username}"/>
<br/>

<spring:message code="author.alias"/>: <jstl:out value="${author.alias}"/>
<br/>

<jstl:choose>
<jstl:when test="${author.puntuation != null and author.puntuation != 0 }">
<spring:message code="author.score"/>: <jstl:out value="${author.puntuation}"/>
<br/>
</jstl:when>
<jstl:otherwise>
<spring:message code="author.NotCalculatedYet"/>:
</jstl:otherwise>
</jstl:choose> 
<br>

<acme:button url="/" code="actor.back.welcome"/>