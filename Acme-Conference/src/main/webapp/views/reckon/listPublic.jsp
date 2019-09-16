<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:message code="reckon.yes" var="language" />

<display:table pagesize="10" name="reckons" id="row"
	requestURI="${requestURI}">

	<fmt:formatDate value="${row.publicationMoment}" pattern="dd" var="day" />
	<fmt:formatDate value="${row.publicationMoment}" pattern="MM"
		var="month" />
	<fmt:formatDate value="${row.publicationMoment}" pattern="yyyy"
		var="year" />

	<jstl:choose>
		<jstl:when
			test="${(year eq nowYear) and ((month eq nowMonth and day <= nowDay) or (month eq nowMonth-1 and day >= nowDay))}">
			<jstl:set var="color" value="YellowGreen" />
		</jstl:when>
		<jstl:when
			test="${(year eq nowYear) and ((month eq nowMonth-1 and day < nowDay) or (month eq nowMonth-2 and day >= nowDay))}">
			<jstl:set var="color" value="LightSeaGreen" />
		</jstl:when>
		<jstl:otherwise>
			<jstl:set var="color" value="Thistle" />
		</jstl:otherwise>
	</jstl:choose>


	<display:column property="title" titleKey="reckon.title"
		style="background-color: ${color};" />
	<display:column property="ticker" titleKey="reckon.ticker"
		style="background-color: ${color};" />


	<jstl:choose>
		<jstl:when test="${ language eq 'Yes' }">
			<display:column titleKey="reckon.publicationMoment"
				style="background-color: ${color};">
				<fmt:formatDate value="${row.publicationMoment}"
					pattern="yyyy/MM/dd hh:mm" />
			</display:column>
		</jstl:when>
		<jstl:otherwise>
			<display:column titleKey="reckon.publicationMoment"
				style="background-color: ${color};">
				<fmt:formatDate value="${row.publicationMoment}"
					pattern="dd-MM-yyyy hh:mm" />
			</display:column>
		</jstl:otherwise>
	</jstl:choose>
	<display:column property="administrator.userAccount.username"
		titleKey="reckon.administrator"
		style="background-color: ${color};" />

	<display:column property="isFinal" titleKey="reckon.isFinal"
		style="background-color: ${color};" />
</display:table>
