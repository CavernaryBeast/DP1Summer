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

	<display:table pagesize="10" name="folets" id="row" requestURI="${requestURI}">
	
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
	
	
	       <display:column property="title" titleKey="folet.title"  style="background-color: ${color};"/>
			<display:column property="ticker" titleKey="folet.ticker" style="background-color: ${color};" />


			<jstl:choose >
			<jstl:when test="${ language eq 'Yes' }">
			<display:column titleKey="folet.publicationMoment" style="background-color: ${color};">
			<fmt:formatDate value ="${row.publicationMoment}"
                pattern="yyyy/MM/dd hh:mm"
                />
			</display:column>
			</jstl:when>
			<jstl:otherwise>
			<display:column titleKey="folet.publicationMoment" style="background-color: ${color};">
			<fmt:formatDate value ="${row.publicationMoment}"
                pattern="dd-MM-yyyy hh:mm"
                />
			</display:column>
			</jstl:otherwise>
			</jstl:choose>
			<display:column property="isFinal" titleKey="folet.isFinal"  style="background-color: ${color};"/>
	</display:table>
