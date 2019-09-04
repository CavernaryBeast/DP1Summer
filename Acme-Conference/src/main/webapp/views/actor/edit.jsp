<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!-- <script>
	function checkPhoneNumber() {
		var phone = document.getElementById("phone").value;
		pattern = /^((\+(\d{1}|\d{2}|\d{3}))* *(\((\d{1}|\d{2}|\d{3})\))* )*(\d{4,})$/igm;
		var result = pattern.test(phone);
		if (result == false) {
			confirm("<spring:message code='actor.checkPhone'/>");
		}
	}
</script>
 -->

<form:form action="${role}/edit.do" modelAttribute="actor">

	<spring:message code="actor.required" />
	<br />
	<spring:message code="actor.attachments" />
	<br />
	<br />

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />

	<jstl:if test="${role eq 'author'}">
		<form:hidden path="finder" />
	</jstl:if>

	<acme:textbox code="actor.name" path="name" />

	<acme:textbox code="actor.middleName" path="middleName" />

	<acme:textbox code="actor.surname" path="surname" />

	<acme:textbox code="actor.photo" path="photo" />

	<acme:textbox code="actor.email" path="email" />

	<acme:textbox code="actor.phoneNumber" path="phoneNumber" />

	<acme:textbox code="actor.address" path="address" />

	<jstl:choose>
		<jstl:when test="${actor.id == 0 }">
			<acme:textbox code="actor.userAccount.username"
				path="userAccount.username" />
		</jstl:when>
		<jstl:otherwise>
			<form:hidden path="userAccount.id" />
			<form:hidden path="userAccount.version" />
			<form:hidden path="userAccount.username" />
		</jstl:otherwise>
	</jstl:choose>

	<acme:password code="actor.userAccount.password"
		path="userAccount.password" />

	<jstl:if test="${role eq 'author'}">
		<acme:textbox code="author.alias" path="alias" />
	</jstl:if>

	<jstl:if test="${role eq 'reviewer'}">
		<acme:textarea code="reviewer.expertise" path="expertise" />
		<spring:message code="reviewer.exampleExpertise" />
		<br />
	</jstl:if>

	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" class="btn"
		onclick="if(!/^\+[1-9]\d{0,2}\([1-9]\d{0,2}\)\d{4,}|\+[1-9]\d{0,2} \([1-9]\d{0,2}\) \d{4,}|\+[1-9]\d{0,2}\d{4,}|\+[1-9]\d{0,2} \d{4,}$/.test(document.getElementById('phoneNumber').value)) { return confirm('<spring:message code="actor.confirm.phone" />')}" />

	<acme:button url="/" code="actor.cancel" />

</form:form>