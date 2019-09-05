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


<spring:message code="folet.yes"  var="language"/>

	<display:table pagesize="5" name="folets" id="row" requestURI="${requestURI}">
	
	<fmt:formatDate value="${row.publicationMoment}" pattern="dd" var="day" />
		<fmt:formatDate value="${row.publicationMoment}" pattern="MM" var="month" /> 
		<fmt:formatDate value="${row.publicationMoment}" pattern="yyyy" var="year" /> 
		
		<jstl:choose>
			<jstl:when test="${(year eq nowYear) and ((month eq nowMonth and day <= nowDay) or (month eq nowMonth-1 and day >= nowDay))}">
			<jstl:set var="color" value="LavenderBlush"/>
			</jstl:when>
			<jstl:when test="${(year eq nowYear) and ((month eq nowMonth-1 and day < nowDay) or (month eq nowMonth-2 and day >= nowDay))}">
			<jstl:set var="color" value="Crimson"/>
			</jstl:when>
			<jstl:otherwise>
			<jstl:set var="color" value="IndianRed"/>
			</jstl:otherwise>
		</jstl:choose>
	
			<display:column property="ticker" titleKey="folet.ticker" />

			<jstl:choose >
			<jstl:when test="${ language eq 'Yes' }">
			<display:column titleKey="folet.publicationMoment" style="background-color: ${color};">
			<fmt:formatDate value ="${row.publicationDate}"
                pattern="yyyy/MM/dd hh:mm"
                />
			</display:column>
			</jstl:when>
			
			<jstl:otherwise>
			<display:column titleKey="folet.publicationMoment" style="background-color: ${color};">
			<fmt:formatDate value ="${row.publicationDate}"
                pattern="dd-MM-yyyy hh:mm"
                />
			</display:column>
			</jstl:otherwise>
			</jstl:choose>
			
			<display:column property="isFinal" titleKey="folet.isFinal" />
			<display:column titleKey="folet.show">
				<input type="button" name="show"
					value="<spring:message code="folet.show" />"
					onclick="javascript: relativeRedir('folet/administrator/show.do?foletId=${row.id}');" />
			</display:column>
				<display:column titleKey="folet.edit">
					<jstl:if test="${not row.isFinal}">
						<a href="folet/administrator/edit.do?foletId=${row.id}"><spring:message code = "folet.edit"/></a>
					</jstl:if>
				</display:column>
				<display:column titleKey="folet.delete">
					<jstl:if test="${not row.isFinal}">
						<a href="folet/administrator/delete.do?foletId=${row.id}"><spring:message code = "folet.delete"/></a>
					</jstl:if>
				</display:column>
				
	</display:table>
	<security:authorize access = "hasRole('ADMINISTRATOR')">
	<input type="button" name = "create" value ="<spring:message code = 'folet.create' />" class = "btn"
	onclick="javascript: relativeRedir('folet/administrator/add.do?conferenceId=${conference.id}');" />
</security:authorize>