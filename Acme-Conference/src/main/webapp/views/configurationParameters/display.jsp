<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:message code="confParams.sysName" />
:
<jstl:out value="${confParams.sysName}" />
<br />

<spring:message code="confParams.banner" />
:
<jstl:out value="${confParams.banner}" />
<br />

<spring:message code="confParams.message" />
:
<jstl:out value="${confParams.message}" />
<br />

<spring:message code="confParams.messageEs" />
:
<jstl:out value="${confParams.messageEs}" />
<br />

<spring:message code="confParams.countryCode" />
:
<jstl:out value="${confParams.countryCode}" />
<br />

<spring:message code="confParams.defaultCountry" />
:
<jstl:out value="${confParams.defaultCountry}" />
<br />

<spring:message code="confParams.creditCardMakes" />
:
<jstl:forEach items="${confParams.creditCardMakes}" var="make">
	<ul>
		<li><jstl:out value="${make}" /></li>
	</ul>
</jstl:forEach>

<display:table name="${confParams.topics}" id="row"
	requestURI="${requestURI}" pagesize="10" class="displaytag">

	<display:column property="name" titleKey="topic.name" />
	<display:column property="nameEs" titleKey="topic.nameEs" />
	
	<jstl:if test="${fn:length(confParams.topics) > 1}">
	<display:column>
		<a href="topic/administrator/edit.do?topicId=${row.id}"> <spring:message
				code="topic.edit" />
		</a>
	</display:column>
	</jstl:if>
	
	<display:column>
		<a href="topic/administrator/delete.do?topicId=${row.id}"> <spring:message
				code="topic.delete" />
		</a>
	</display:column>


</display:table>

<acme:button url="topic/administrator/create.do" code="topic.create" />

<br />

<acme:button url="confParams/administrator/edit.do"
	code="confParams.edit" />