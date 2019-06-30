<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

	<spring:message code="actor.signUp.subtitle"/>
	<br/>
	
	<acme:button url="author/create.do" code="actor.signUp.author" />
	<acme:button url="reviewer/create.do" code="actor.signUp.reviewer" />
	
	<security:authorize access="hasRole('ADMIN')">
		<a href="administrator/administrator/create.do"><spring:message code="actor.signUp.administrator"/></a>
	</security:authorize>
	<br/>
	
	<acme:button url="security/login.do" code="actor.cancel"/>
	