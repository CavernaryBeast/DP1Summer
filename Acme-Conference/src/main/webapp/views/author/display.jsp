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

<acme:button url="paper/author/list.do?authorId=${author.id}" code="paper.list"/>

<acme:button url="registration/author/list.do?authorId=${author.id}" code="registration.list"/>

<acme:button url="submission/author/list.do?authorId=${author.id}" code="submission.list"/>

<acme:button url="activity/author/list.do?authorId=${author.id}" code="activity.list"/>

<acme:button url="conference/author/list.do?authorId=${author.id}" code="conference.list"/>

<acme:button url="creditcard/author/list.do?authorId=${author.id}" code="creditcard.list"/>

<acme:button url="author/edit.do" code="actor.edit"/>

<acme:button url="/" code="author.back.welcome"/>