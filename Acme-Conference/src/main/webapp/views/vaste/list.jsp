<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:message code="vaste.yes"  var="language"/>

	<display:table pagesize="10" name="vastes" id="row" requestURI="${requestURI}">
	
	
	
	<fmt:formatDate value="${row.publicationMoment}" pattern="dd" var="day" />
		<fmt:formatDate value="${row.publicationMoment}" pattern="MM" var="month" /> 
		<fmt:formatDate value="${row.publicationMoment}" pattern="yyyy" var="year" /> 
	
	
		
		<jstl:choose>
			<jstl:when test="${(year eq nowYear) and ((month eq nowMonth and day <= nowDay) or (month eq nowMonth-1 and day >= nowDay))}">
			<jstl:set var="color" value="BurlyWood"/>
			</jstl:when>
			<jstl:when test="${(year eq nowYear) and ((month eq nowMonth-1 and day < nowDay) or (month eq nowMonth-2 and day >= nowDay))}">
			<jstl:set var="color" value="SlateGrey"/>
			</jstl:when>
			<jstl:otherwise>
			<jstl:set var="color" value="DarkViolet"/>
			</jstl:otherwise>
		</jstl:choose>
	
	
	<display:column property="title" titleKey="vaste.title" style="background-color: ${color};" />
	
		<display:column property="administrator.userAccount.username" titleKey="vaste.administrator" style="background-color: ${color};" />
		
			<display:column property="ticker" titleKey="vaste.ticker" style="background-color: ${color};" />



			<jstl:choose >
			<jstl:when test="${ language eq 'Yes' }">
			<display:column titleKey="vaste.publicationMoment" style="background-color: ${color};">
			<fmt:formatDate value ="${row.publicationMoment}"
                pattern="yy/MM/dd hh:mm"
                />
			</display:column>
			</jstl:when>
			<jstl:otherwise>
			<display:column titleKey="vaste.publicationMoment" style="background-color: ${color};">
			<fmt:formatDate value ="${row.publicationMoment}"
                pattern="dd-MM-yy hh:mm"
                />
			</display:column>
			</jstl:otherwise>
			</jstl:choose>
			<display:column property="isFinal" titleKey="vaste.isFinal"  style="background-color: ${color};"/>
			<display:column titleKey="vaste.show"  style="background-color: ${color};" >
				<input type="button" name="show"
					value="<spring:message code="vaste.show" />"
					onclick="javascript: relativeRedir('vaste/administrator/show.do?vasteId=${row.id}');" />
			</display:column>
				<display:column titleKey="vaste.edit" style="background-color: ${color};">
			<jstl:choose>
			<jstl:when test="${not row.isFinal and row.administrator eq administrator }">
			<a href="vaste/administrator/edit.do?vasteId=${row.id}"><spring:message code = "vaste.edit"/></a>
			</jstl:when>
			<jstl:otherwise>
			<spring:message code="vaste.cantEdit" />
			</jstl:otherwise>
			</jstl:choose>
				</display:column>
				<display:column titleKey="vaste.delete" style="background-color: ${color};">
			<jstl:choose>
			<jstl:when test="${not row.isFinal and row.administrator eq administrator}">
			<a href="vaste/administrator/delete.do?vasteId=${row.id}"><spring:message code = "vaste.delete"/></a>
			</jstl:when>
			<jstl:otherwise>
			<spring:message code="vaste.cantDelete" />
			</jstl:otherwise>
			</jstl:choose>
				</display:column>
				
	</display:table>
	<security:authorize access = "hasRole('ADMINISTRATOR')">
	<input type="button" name = "create" value ="<spring:message code = 'vaste.create' />" class = "btn"
	onclick="javascript: relativeRedir('vaste/administrator/add.do?conferenceId=${conferenceId}');" />
</security:authorize>