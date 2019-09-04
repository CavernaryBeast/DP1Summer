<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="category/administrator/edit.do"
	modelAttribute="category">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<jstl:choose>
		<jstl:when test="${category.father == null}">
			<jstl:choose>
				<jstl:when test="${lang eq 'en'}">
					<acme:select1 items="${categories}" itemLabel="name"
						code="category.father" path="father" />
				</jstl:when>
				<jstl:when test="${lang eq 'es'}">
					<acme:select1 items="${categories}" itemLabel="nameEs"
						code="category.father" path="father" />
				</jstl:when>
			</jstl:choose>
		</jstl:when>
		<jstl:when test="${category.father != null}">
			<form:hidden path="father" />
		</jstl:when>
	</jstl:choose>

	<acme:textbox code="category.name" path="name" />

	<acme:textarea code="category.nameEs" path="nameEs" />

	<acme:submit name="save" code="category.save" />

	<acme:button url="category/administrator/list.do"
		code="category.cancel" />
</form:form>