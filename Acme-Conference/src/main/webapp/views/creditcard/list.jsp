<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="creditcards" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column>
		<a href="creditCard/author/edit.do?creditcardId=${row.id}"> <spring:message
				code="creditCard.edit" />
		</a>
	</display:column>
	<display:column property="holderName" titleKey="creditCard.holderName" />
	<display:column property="brandName" titleKey="creditCard.brandName" />
	<display:column property="number" titleKey="creditCard.number" />

	<display:column property="expirationMonth"
		titleKey="creditCard.expirationMonth" />

	<display:column property="expirationYear"
		titleKey="creditCard.expirationYear" />

	<display:column>
		<a href="creditCard/author/display.do?creditcardId=${row.id}"> <spring:message
				code="creditCard.display" />
		</a>
	</display:column>


</display:table>

<acme:button url="creditCard/author/create.do" code="creditCard.create" />