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

	<script type="text/javascript" >
function showHideDiv(ele) {
	var srcElement = document.getElementById(ele);
	if (srcElement != null) {
		if (srcElement.style.display == "block") {
			srcElement.style.display = 'none';
		}
		else {
			srcElement.style.display = 'block';
		}
		return false;
	}
}
</script>
	
	
	<fieldset>
		<legend> <spring:message   code="activity.type.presentation"  />   </legend>
<display:table pagesize="10" name="${presentations}" id="row"
	requestURI="${requestURI}">
<display:column property="title" titleKey="acivity.title" />
<display:column property="room" titleKey="activity.room" />
<display:column property="summary" titleKey="activity.summary" />
<display:column property="type" titleKey="activity.type" />
<display:column property="paper.title" titleKey="activity.paper.title" />
	<display:column titleKey="activity.show">
				<a href="activity/admin/show.do?activityId=${row.id}"><spring:message code = "activity.show"/></a>
		</display:column>
</display:table>
</fieldset>

	<fieldset>
		<legend> <spring:message   code="activity.type.tutorials"  />   </legend>
<display:table pagesize="10" name="${tutorials}" id="row"
	requestURI="${requestURI}">
<display:column property="title" titleKey="acivity.title" />
<display:column property="room" titleKey="activity.room" />
<display:column property="summary" titleKey="activity.summary" />
<display:column property="type" titleKey="activity.type" />
	<display:column titleKey="activity.show">
				<a href="activity/admin/show.do?activityId=${row.id}"><spring:message code = "activity.show"/></a>
		</display:column>
</display:table>
</fieldset>

	<fieldset>
		<legend> <spring:message   code="activity.type.panels"  />   </legend>
<display:table pagesize="10" name="${panels}" id="row"
	requestURI="${requestURI}">
<display:column property="title" titleKey="acivity.title" />
<display:column property="room" titleKey="activity.room" />
<display:column property="summary" titleKey="activity.summary" />
<display:column property="type" titleKey="activity.type" />
	<display:column titleKey="activity.show">
				<a href="activity/admin/show.do?activityId=${row.id}"><spring:message code = "activity.show"/></a>
		</display:column>
</display:table>
</fieldset>

	<input type="button" name="presentation"
		value="<spring:message code = 'activity.create.presentation' />" class="btn" 	onclick="javascript: relativeRedir('activity/administrator/create.do?conferenceId=${conferenceId}');" />
		<br>
		<input type="button" name="tutorial"
		value="<spring:message code = 'activity.create.tutorial' />" class="btn" 	onclick="javascript: relativeRedir('activity/administrator/create.do?conferenceId=${conferenceId}');" />
		<br>
		<input type="button" name="panel"
		value="<spring:message code = 'activity.create.panel' />" class="btn" 	onclick="javascript: relativeRedir('activity/administrator/create.do?conferenceId=${conferenceId}');" />
		<br>
