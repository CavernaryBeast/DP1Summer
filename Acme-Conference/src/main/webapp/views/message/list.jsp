<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%-- <jstl:choose>
	<jstl:when test="${lang eq en}">
		<jstl:set var="item" value="name" />
	</jstl:when>
	<jstl:otherwise>
		<jstl:set var="item" value="nameEs" />
	</jstl:otherwise>
</jstl:choose>
 --%>

<display:table name="messages" id="m" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	<display:column property="subject" titleKey="message.subject" />
	<display:column property="moment" titleKey="message.moment" />
	<display:column property="${lang}" titleKey="message.topic" />
	<display:column titleKey="message.messages">
		<a href="message/display.do?messageId=<jstl:out value="${m.id}"/>">
			<spring:message code="message.display" />
		</a>
	</display:column>
</display:table>
<br />