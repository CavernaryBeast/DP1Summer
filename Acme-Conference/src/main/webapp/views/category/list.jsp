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

<display:table pagesize="10" name="${categories}" id="row"
	requestURI="${requestURI}" class="displaytag">
	<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<display:column property="name" titleKey="category.name" />
			<display:column titleKey="category.father.name">
				<jstl:choose>
					<jstl:when test="${row.father != null}">
						<jstl:out value="${row.father.name}" />
					</jstl:when>
					<jstl:otherwise>
						<spring:message code="category.CONFERENCE" />
					</jstl:otherwise>
				</jstl:choose>
			</display:column>
		</jstl:when>
		<jstl:when test="${lang eq 'es'}">
			<display:column property="nameEs" titleKey="category.nameEs" />
			<display:column>
				<jstl:choose>
					<jstl:when test="${row.father != null}">
						<jstl:out value="${row.father.nameEs}" />
					</jstl:when>
					<jstl:otherwise>
						<spring:message code="category.CONFERENCE" />
					</jstl:otherwise>
				</jstl:choose>
			</display:column>
		</jstl:when>
	</jstl:choose>
	<display:column titleKey="category.edit">
		<jstl:choose>
			<jstl:when test="${row.father == null}">
				<spring:message code="category.notEditable" />
			</jstl:when>
			<jstl:otherwise>
				<a href="category/administrator/edit.do?categoryId=${row.id}"> <spring:message
						code="category.edit" />
				</a>
			</jstl:otherwise>
		</jstl:choose>
	</display:column>
	<display:column titleKey="category.delete">
		<jstl:choose>
			<jstl:when test="${row.father == null}">
				<spring:message code="category.notDeletable" />
			</jstl:when>
			<jstl:otherwise>
				<a href="category/administrator/delete.do?categoryId=${row.id}">
					<spring:message code="category.delete" />
				</a>
			</jstl:otherwise>
		</jstl:choose>
	</display:column>

</display:table>