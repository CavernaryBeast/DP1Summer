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
	
<display:table pagesize="10" name="${registrations}" id="row"
	requestURI="${requestURI}">

<display:column property="author.userAccount.username" titleKey="registration.author" />
	
	<display:column titleKey="registration.show">
				<a href="registration/author/show.do?registrationId=${row.id}"><spring:message code = "registration.show"/></a>
		</display:column>
	
</display:table>

<jstl:choose >
<jstl:when test="${fn:length(conferences) > 0}">
<input type="button" name="create"
		value="<spring:message code = 'registration.create' />" class="btn" 	onclick="javascript: relativeRedir('registration/author/create.do');" />
		</jstl:when>
<jstl:otherwise>
<spring:message code="registration.conferencesNotAvailable" />
</jstl:otherwise>		
		</jstl:choose>