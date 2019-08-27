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

<fieldset>
		<legend> <spring:message   code="submission.acceptedL"  />   </legend>	
<display:table pagesize="10" name="${accepted}" id="row"
	requestURI="${requestURI}">

<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment" />
<display:column property="status" titleKey="submission.status" />
<display:column property="reviewers" titleKey="submission.reviewers" />
	<display:column titleKey="submission.assign">
				<a href="conference/administrator/addReviewer.do?submissionId=${row.id}"><spring:message code = "submission.assign"/></a>
		</display:column>
	<display:column titleKey="submission.assignAutomatic">
				<a href="conference/administrator/assignSubmissionAutomatic.do?submissionId=${row.id}"><spring:message code = "submission.assignAutomatic"/></a>
		</display:column>
</display:table>
</fieldset>

<fieldset>
		<legend> <spring:message   code="submission.underReviewL"  />   </legend>	
<display:table pagesize="10" name="${underReview}" id="row"
	requestURI="${requestURI}">

<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment" />
<display:column property="status" titleKey="submission.status" />
<display:column property="reviewers" titleKey="submission.reviewers" />
	<display:column titleKey="submission.assign">
				<a href="conference/administrator/addReviewer.do?submissionId=${row.id}"><spring:message code = "submission.assign"/></a>
		</display:column>
	<display:column titleKey="submission.assignAutomatic">
				<a href="conference/administrator/assignSubmissionAutomatic.do?submissionId=${row.id}"><spring:message code = "submission.assignAutomatic"/></a>
		</display:column>

</display:table>
</fieldset>

<fieldset>
		<legend> <spring:message   code="submission.rejectedL"  />   </legend>	
<display:table pagesize="10" name="${rejected}" id="row"
	requestURI="${requestURI}">

<display:column property="ticker" titleKey="submission.ticker" />
<display:column property="moment" titleKey="submission.moment" />
<display:column property="status" titleKey="submission.status" />	
<display:column property="reviewers" titleKey="submission.reviewers" />
	<display:column titleKey="submission.assign">
				<a href="conference/administrator/addReviewer.do?submissionId=${row.id}"><spring:message code = "submission.assign"/></a>
		</display:column>
			<display:column titleKey="submission.assignAutomatic">
				<a href="conference/administrator/assignSubmissionAutomatic.do?submissionId=${row.id}"><spring:message code = "submission.assignAutomatic"/></a>
		</display:column>		
</display:table>
</fieldset>

