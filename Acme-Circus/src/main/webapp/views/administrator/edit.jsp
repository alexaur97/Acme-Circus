<%--
 * action-1.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="administrator/administrator/edit.do"
	modelAttribute="administratorRegisterForm" method="POST">

	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="administrator.personal.data" />
		</legend>

	<acme:textbox code="administrator.name" path="name" />
	<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<acme:textbox code="administrator.dni" path="dni" placeholder="8 numbers and 1 letter"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:textbox code="administrator.dni" path="dni" placeholder="8 números y 1 letra"/>
		</jstl:otherwise>
		</jstl:choose>
	<acme:textbox code="administrator.surnames" path="surnames" />
	<acme:textbox code="administrator.photo" path="photo" />
	<acme:textbox code="administrator.email" path="email" />
	<acme:textbox code="administrator.phone" path="phone" placeholder="123456789" comment="administrator.phone.pattern" />
	<acme:textbox code="administrator.address" path="address" />

	</fieldset>
	<br />

	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="administrator.userAccount" />
		</legend>
		<acme:textbox code="administrator.username" path="username" />
		<acme:password code="administrator.password" path="password" />
		<acme:password code="administrator.confirmPassword" path="confirmPassword" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="administrator.creditCard" />
		</legend>
		<acme:textbox code="administrator.holderName" path="holderName" />
		<acme:textbox code="administrator.brandName" path="brandName" />
		<acme:textbox code="administrator.number" path="number" />
		<acme:textbox code="administrator.expirationMonth" path="expirationMonth" />
		<acme:textbox code="administrator.expirationYear" path="expirationYear" />
		<acme:textbox code="administrator.cvv" path="cvv" />
	</fieldset>
	<spring:message code="administrator.check" />
	<form:checkbox path="terms" />
	<form:errors path="terms" cssClass="error" />
	<br/>


	<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<button type="submit" onclick="return validatePhoneNumber()" name="save">
				<spring:message code="administrator.save" />
			</button>
		</jstl:when>
		<jstl:otherwise>
			<button type="submit" onclick="return validatePhoneNumberEs()" name="save">
				<spring:message code="administrator.save" />
			</button>
		</jstl:otherwise>
	</jstl:choose>
	<acme:cancel url="/#" code="administrator.cancel" />
<script type="text/javascript">
		function validatePhoneNumber() {
			var phoneNumber = document.getElementById("phone");
			if (!(phoneNumber.value).match("\\+\\d{2}([ ]{1}[(]{1}\\d{1,3}[)]{1})? \\d{4,}|\\+\\d{2} \\d{4,}|\\d{4,}|Null")) { return confirm("Phone number doesn't adhere to the correct pattern (9 Numbers - 123456789). Do you want to continue?"); }
		}

		function validatePhoneNumberEs() {
			var phoneNumber = document.getElementById("phone");
			if (!(phoneNumber.value).match("\\+\\d{2}([ ]{1}[(]{1}\\d{1,3}[)]{1})? \\d{4,}|\\+\\d{2} \\d{4,}|\\d{4,}|Null")) { return confirm("El teléfono no se ajusta al patrón correcto (9 Números - 123456789). ¿Desea continuar?"); }
		}
	</script>
</form:form>
