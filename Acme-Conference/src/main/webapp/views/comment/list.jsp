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

<jstl:forEach items="${comments}" var="comment">

	<spring:message code="comment.author" />:
	<jstl:if test="${comment.author not eq null}">
		<jstl:out value="${comment.author.userAccount.username}" />
	</jstl:if>
	<spring:message code="comment.author.anonimous" />
	<br />

	<spring:message code="comment.title" />:
	<jstl:out value="${comment.title}" />
	<br />

	<spring:message code="comment.moment" />:
	<jstl:out value="${comment.moment}" />
	<br />

	<spring:message code="comment.text" />:
	<jstl:out value="${comment-text}" />

</jstl:forEach>