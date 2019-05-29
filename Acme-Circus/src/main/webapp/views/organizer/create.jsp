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

<form:form id="form" action="organizer/owner/create.do"
	modelAttribute="organizerRegisterForm" method="POST">
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="organizer.personal.data" />
		</legend>
		<acme:textbox code="organizer.name" path="name" />
		<acme:textbox code="organizer.surnames" path="surnames" />
		<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<acme:textbox code="organizer.dni" path="dni" placeholder="8 numbers and 1 letter"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:textbox code="organizer.dni" path="dni" placeholder="8 n�meros y 1 letra"/>
		</jstl:otherwise>
		</jstl:choose>
		<acme:textbox code="organizer.photo" path="photo" />
		<acme:textbox code="organizer.email" path="email" />
		<acme:textbox code="organizer.phone" path="phone" placeholder="123456789" comment="organizer.phone.pattern" />
		<acme:textbox code="organizer.address" path="address" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="organizer.userAccount" />
		</legend>
		<acme:textbox code="organizer.username" path="username" />
		<acme:password code="organizer.password" path="password" />
		<acme:password code="organizer.confirmPassword" path="confirmPassword" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="organizer.creditCard" />
		</legend>
		<acme:textbox code="organizer.holderName" path="holderName" />
		<acme:textbox code="organizer.brandName" path="brandName" />
		<acme:textbox code="organizer.number" path="number" />
		<acme:textbox code="organizer.expirationMonth" path="expirationMonth" />
		<acme:textbox code="organizer.expirationYear" path="expirationYear" />
		<acme:textbox code="organizer.cvv" path="cvv" />
	</fieldset>
	<spring:message code="organizer.check" />
	<form:checkbox path="terms" />
	<form:errors path="terms" cssClass="error" />
	<br />

	<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<button type="submit" onclick="return validatePhoneNumber()"
				name="save">
				<spring:message code="organizer.save" />
			</button>
		</jstl:when>
		<jstl:otherwise>
			<button type="submit" onclick="return validatePhoneNumberEs()"
				name="save">
				<spring:message code="organizer.save" />
			</button>
		</jstl:otherwise>
	</jstl:choose>
	<acme:cancel url="/#" code="organizer.cancel" />

	<script type="text/javascript">
		function validatePhoneNumber() {
			var phoneNumber = document.getElementById("phone");
			if (!(phoneNumber.value).match("^\\+\\d{1,3}([ ]{1}[(]{1}\\d{1,3}[)]{1})?[ ]{1}\\d{4,}$|^\\d{4,}$|^$")) { return confirm("Phone number doesn't adhere to the correct pattern (9 Numbers - 123456789). Do you want to continue?"); }
		}
		function validatePhoneNumberEs() {
			var phoneNumber = document.getElementById("phone");
			if (!(phoneNumber.value).match("^\\+\\d{1,3}([ ]{1}[(]{1}\\d{1,3}[)]{1})?[ ]{1}\\d{4,}$|^\\d{4,}$|^$")) { return confirm("El tel�fono no se ajusta al patr�n correcto (9 N�meros - 123456789). �Desea continuar?"); }
		}
	</script>
</form:form>