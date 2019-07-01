<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="actor.name"/>: <jstl:out value="${reviewer.name}"/>
<br/>

<spring:message code="actor.middleName"/>: <jstl:out value="${reviewer.middleName}"/>
<br/>

<spring:message code="actor.surname"/>: <jstl:out value="${reviewer.surname}"/>
<br/>

<spring:message code="actor.photo"/>: <jstl:out value="${reviewer.photo}"/>
<br/>

<spring:message code="actor.email"/>: <jstl:out value="${reviewer.email}"/>
<br/>

<spring:message code="actor.phoneNumber"/>: <jstl:out value="${reviewer.phoneNumber}"/>
<br/>

<spring:message code="actor.address"/>: <jstl:out value="${reviewer.address}"/>
<br/>

<spring:message code="actor.userAccount.username"/>: <jstl:out value="${reviewer.userAccount.username}"/>
<br/>

<spring:message code="reviewer.expertise"/>: <jstl:out value="${reviewer.expertise}"/>
<br/>

<acme:button url="report/reviewer/list.do?reviewerId=${reviewer.id}" code="report.list"/>

<acme:button url="reviewer/edit.do" code="actor.edit"/>

<acme:button url="/" code="actor.back.welcome"/>