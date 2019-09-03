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

<display:table name="${conferences}" pagesize="10" id="row"
	class="displaytag" requestURI="${requestURI}">

	<display:column property="title" titleKey="conference.title" />
	<display:column property="venue" titleKey="conference.venue" />
	<display:column property="summary" titleKey="conference.summary" />
	<display:column property="startDate" titleKey="conference.startDate" />
	<display:column property="endDate" titleKey="conference.endDate" />

</display:table>