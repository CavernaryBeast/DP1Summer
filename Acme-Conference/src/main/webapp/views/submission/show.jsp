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

	<p><spring:message code="submission.ticker" />: <jstl:out value = "${submission.ticker}"/></p>
	<p><spring:message code="submission.moment" />: <jstl:out value = "${submission.moment}"/></p>
	<p><spring:message code="submission.status" />: <jstl:out value = "${submission.status}"/></p>
	<p><spring:message code="submission.author" />: <jstl:out value = "${submission.author.userAccount.username}"/></p>



	<fieldset>
		<legend> <spring:message   code="submission.paper"  />   </legend>
	
		<p><spring:message code="submission.paper.title" />: <jstl:out value = "${submission.paper.title}"/></p>
		<p><spring:message code="submission.paper.showAuthors" />:</p>
		<jstl:forEach  items="${submission.paper.authors }" var="a">
		
		 <jstl:out value = "${a.userAccount.username}"/>
		
		</jstl:forEach>
		
		<p><spring:message code="submission.paper.summary" />: <jstl:out value = "${submission.paper.summary}"/></p>
		<p><spring:message code="submission.paper.document" />: <jstl:out value = "${submission.paper.document}"/></p>
		<p><spring:message code="submission.paper.cameraReady" />: <jstl:out value = "${submission.paper.cameraReady}"/></p>
	

</fieldset>
	<br />
<acme:button url="/submission/author/list.do" code="submission.cancel" />