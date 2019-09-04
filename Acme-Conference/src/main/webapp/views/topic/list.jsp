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

<display:table name="${topics}" id="row" pagesize="10"
	class="displaytag" requestURI="${requestURI}">
	<display:column property="name" titleKey="topic.name" />
	<display:column property="nameEs" titleKey="topic.nameEs" />
	<display:column>
		<a href="topic/administrator/edit.do?topicId=${row.id}"><spring:message
				code="topic.edit" /></a>
	</display:column>
	<jstl:if test="${fn:length(topics) > 1}">
		<display:column>
			<a href="topic/administrator/delete.do?topicId=${row.id}"><spring:message
					code="topic.delete" /></a>
		</display:column>
	</jstl:if>
	<display:column>
		<a href="topic/administrator/display.do?topicId=${row.id}"><spring:message
				code="topic.display" /></a>
	</display:column>
</display:table>

<acme:button url="topic/administrator/create.do" code="topic.create" />