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


<jstl:set var="spanish" value="es"/>

<spring:message code="conference.filter.all" var="allLabel"/>
<spring:message code="conference.filter.forthcoming" var="forthcomingLabel"/>
<spring:message code="conference.filter.past" var="pastLabel"/>
<spring:message code="conference.filter.running" var="runningLabel"/>



<!-- Non authenticated user -->
<security:authorize access="!isAuthenticated()">
	<jstl:set var="user" value="other" />
</security:authorize>
<!-- PokemonTrainers -->
<security:authorize access="hasRole('PokemonTrainer')">
	<jstl:set var="user" value="company" />
</security:authorize>


<jstl:if test="${ requestURI eq 'conference/list.do'}">
	<form:form action="${actionFilter}" modelAttribute="filterMoveTypeForm" >
		<acme:textbox code="movement.filter" path="key"/>
			    <form:select path="category">
	        <form:option label="${allLabel}" value=""/>
	        <form:option label="${forthcomingLabel}" value="FORTHCOMING"/>
	        <form:option label="${pastLabel}" value="PAST"/>
	        <form:option label="${runningLabel}" value="RUNNING"/>
	    </form:select>
	    <br/>
		<acme:submit code="conference.filter" name="filter" />
	</form:form>
</jstl:if>

<display:table pagesize="10" name="${conferences}" id="row"
	requestURI="${requestURI}">

<display:column property="title" titleKey="conference.title" />
<display:column property="venue" titleKey="conference.venue" />
<display:column property="startDate" titleKey="conference.startDate" />
<display:column property="endDate" titleKey="conference.endDate" />		
		
	
		<display:column property="category" titleKey="movement.category" />
	<display:column property="power" titleKey="movement.power" />
	<display:column property="accurate" titleKey="movement.accurate" />
	

	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="conference.edit">
			<a href="conference/edit.do?conferenceId=${row.id}"><spring:message
					code="conference.edit" /></a>
		</display:column>

	</security:authorize>
</display:table>

<security:authorize access="hasRole('ADMIN')">

	<input type="button" name="create"
		value="<spring:message code = 'conference.create' />" class="btn" 	onclick="javascript: relativeRedir('conference/administrator/create.do');" />


</security:authorize>