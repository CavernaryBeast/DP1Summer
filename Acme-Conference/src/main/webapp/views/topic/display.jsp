<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="topic.name" />
:
<jstl:out value="${topic.name}" />
<br />

<spring:message code="topic.nameEs" />
:
<jstl:out value="${topic.nameEs}" />
<br />

<acme:button url="topic/administrator/edit.do?topicId=${topic.id}"
	code="topic.edit" />

<br />

<acme:button url="topic/administrator/list.do" code="topic.back" />