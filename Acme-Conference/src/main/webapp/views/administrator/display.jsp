<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="actor.name"/>: <jstl:out value="${administrator.name}"/>
<br/>

<spring:message code="actor.middleName"/>: <jstl:out value="${administrator.middleName}"/>
<br/>

<spring:message code="actor.surname"/>: <jstl:out value="${administrator.surname}"/>
<br/>

<spring:message code="actor.photo"/>: <jstl:out value="${administrator.photo}"/>
<br/>

<spring:message code="actor.email"/>: <jstl:out value="${administrator.email}"/>
<br/>

<spring:message code="actor.phoneNumber"/>: <jstl:out value="${administrator.phoneNumber}"/>
<br/>

<spring:message code="actor.address"/>: <jstl:out value="${administrator.address}"/>
<br/>

<spring:message code="actor.userAccount.username"/>: <jstl:out value="${administrator.userAccount.username}"/>
<br/>

<acme:button url="conference/administrator/list.do?administratorId=${administrator.id}" code="conference.list"/>

<acme:button url="administrator/edit.do" code="actor.edit"/>

<acme:button url="/" code="actor.back.welcome"/>