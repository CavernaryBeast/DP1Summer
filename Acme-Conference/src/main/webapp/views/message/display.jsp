<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="message.subject" />
:
<jstl:out value="${m.subject}" />
<br />
<jstl:choose>
	<jstl:when test="${m.sender != null }">
		<spring:message code="message.sender" />: <jstl:out
			value="${m.sender.userAccount.username}" />
		<br />
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="message.systemMessage" />
	</jstl:otherwise>
</jstl:choose>

<spring:message code="message.recipients" />
:
<jstl:forEach var="r" items="${m.recipients}">
	<jstl:out value="${r.userAccount.username}" />,
</jstl:forEach>
<br />

<spring:message code="message.body" />
:
<jstl:out value="${m.body}" />
<br />



<spring:message code="message.topic" />
:
<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
		<jstl:out value="${m.topic.name}" />
	</jstl:when>
	<jstl:when test="${lang eq 'es'}">
		<jstl:out value="${m.topic.nameEs}" />
	</jstl:when>
</jstl:choose>
<br />

<jstl:if test="${ownMessage == false and m.sender != null}">
	<acme:button url="/message/reply.do?messageId=${m.id}"
		code="message.reply" />
</jstl:if>

<acme:button url="/message/list.do" code="message.back" />