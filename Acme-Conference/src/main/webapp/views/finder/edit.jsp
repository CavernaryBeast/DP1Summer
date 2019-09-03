<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="finder/author/edit.do" modelAttribute="finder">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<acme:textarea code="finder.keyword" path="keyword" />

	<jstl:choose>
		<jstl:when test="${language eq 'en'}">
			<acme:select items="categories" itemLabel="name"
				code="category.categories" path="category" />
		</jstl:when>
		<jstl:when test="${language eq 'es'}">
			<acme:select items="categories" itemLabel="nameEs"
				code="category.categories" path="category" />
		</jstl:when>
	</jstl:choose>

	<acme:textarea code="finder.startDate" path="startDate" />

	<acme:textarea code="finder.endDate" path="endDate" />

	<acme:textarea code="finder.fee" path="fee" />

	<acme:submit name="save" code="finder.save" />

	<acme:button url="/" code="finder.cancel" />
</form:form>