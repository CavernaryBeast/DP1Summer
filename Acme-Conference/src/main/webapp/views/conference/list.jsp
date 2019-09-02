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


<jstl:set var="spanish" value="es" />

<spring:message code="conference.filter.all" var="allLabel" />
<spring:message code="conference.filter.forthcoming"
	var="forthcomingLabel" />
<spring:message code="conference.filter.past" var="pastLabel" />
<spring:message code="conference.filter.running" var="runningLabel" />


<form:form action="${actionFilter}"
	modelAttribute="filterConferenceForm">
	<acme:textbox code="conference.filter" path="keyWord" />
	<form:select path="typeDate">
		<form:option label="${allLabel}" value="" />
		<form:option label="${forthcomingLabel}" value="FORTHCOMING" />
		<form:option label="${runningLabel}" value="RUNNING" />
		<form:option label="${pastLabel}" value="PAST" />
	</form:select>

	<br />
	<acme:submit code="conference.filter" name="filter" />
</form:form>




<display:table pagesize="10" name="${conferences}" id="row"
	requestURI="${requestURI}">

	<display:column property="title" titleKey="conference.title" />
	<display:column property="venue" titleKey="conference.venue" />
	<display:column property="summary" titleKey="conference.summary" />
	<display:column property="startDate" titleKey="conference.startDate" />
	<display:column property="endDate" titleKey="conference.endDate" />
		<display:column titleKey="conference.show">
				<a href="conference/show.do?conferenceId=${row.id}"><spring:message
						code="conference.show" /></a>
		</display:column>


	<security:authorize access="hasRole('AUTHOR')">
		<display:column titleKey="conference.edit">
			<jstl:if test="${ row.isFinal eq false}">
				<a href="conference/administrator/edit.do?conferenceId=${row.id}"><spring:message
						code="conference.edit" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>

</display:table>
