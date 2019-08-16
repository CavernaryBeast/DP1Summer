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

<jstl:set var="checkStatus" value ="ACCEPTED"/>

	
<display:table pagesize="10" name="${submissions}" id="row"
	requestURI="${requestURI}">

<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment" />
<display:column property="status" titleKey="submission.status" />
	
	<display:column titleKey="submission.show">
				<a href="submission/author/show.do?submissionId=${row.id}"><spring:message code = "submission.show"/></a>
		</display:column>
	
	
	
	<display:column titleKey="submission.update">
<jstl:choose>
<jstl:when test="${ row.status eq  checkStatus and  not row.paper.cameraReady}">
<a href="submission/author/editPaper.do?submissionId=${row.id}"><spring:message code = "submission.update"/></a>
</jstl:when>
<jstl:when test="${ row.status eq  checkStatus and row.paper.cameraReady}">
<spring:message code="submission.paper.isCameraReady" />
</jstl:when>
<jstl:otherwise>
<spring:message code="submission.notUpdatePaper" />
</jstl:otherwise>
</jstl:choose>
		</display:column>
	
	



	
	
</display:table>

<jstl:choose >
<jstl:when test="${fn:length(conferences) > 0}">
<input type="button" name="create"
		value="<spring:message code = 'submission.create' />" class="btn" 	onclick="javascript: relativeRedir('submission/author/create.do');" />
		</jstl:when>
<jstl:otherwise>
<spring:message code="submission.conferencesNotAvailable" />
</jstl:otherwise>		
		</jstl:choose>