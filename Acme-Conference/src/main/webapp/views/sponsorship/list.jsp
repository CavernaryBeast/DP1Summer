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



<display:table pagesize="10" name="${sponsorships}" id="row"
	requestURI="${requestURI}">
<display:column titleKey="sponsorship.banner" >
<img src="${row.banner }" style="width:50px;height:60px;">
</display:column>
<display:column property="targetURL" titleKey="sponsorship.targetURL" />
<display:column property="conference.title" titleKey="sponsorship.conference" />
	<display:column titleKey="activity.show">
				<a href="sponsorship/sponsor/show.do?sponsorshipId=${row.id}"><spring:message code = "sponsorship.show"/></a>
		</display:column>
		<display:column titleKey="sponsorship.edit">
		<a href="sponsorship/sponsor/edit.do?sponsorshipId=${row.id}"><spring:message code = "sponsorship.edit"/></a>
		</display:column>	
		<display:column titleKey="activity.delete">
		<a href="sponsorship/sponsor/delete.do?sponsorshipId=${row.id}"><spring:message code = "activity.delete"/></a>
		</display:column>
</display:table>

	
<input type="button" 
		value="<spring:message code = 'sponsorship.create' />" class="btn" 	onclick="javascript: relativeRedir('sponsorship/sponsor/create.do');" />
		<br>
