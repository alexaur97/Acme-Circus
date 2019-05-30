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

<form:form id="form" action="attendee/signup.do"
	modelAttribute="attendeeRegisterForm" method="POST">
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="attendee.personal.data" />
		</legend>
		<acme:textbox code="attendee.name" path="name" />
		<acme:textbox code="attendee.surnames" path="surnames" />
		<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<acme:textbox code="attendee.dni" path="dni" placeholder="8 numbers and 1 letter"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:textbox code="attendee.dni" path="dni" placeholder="8 números y 1 letra"/>
		</jstl:otherwise>
		</jstl:choose>
		<acme:textbox code="attendee.bornDate" path="bornDate" placeholder="yyyy-MM-dd"/>
		<acme:textbox code="attendee.photo" path="photo" />
		<acme:textbox code="attendee.email" path="email" />
		<acme:textbox code="attendee.phone" path="phone" placeholder="123456789" comment="attendee.phone.pattern" />
		<acme:textbox code="attendee.address" path="address" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="attendee.userAccount" />
		</legend>
		<acme:textbox code="attendee.username" path="username" />
		<acme:password code="attendee.password" path="password" />
		<acme:password code="attendee.confirmPassword" path="confirmPassword" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="attendee.creditCard" />
		</legend>
		<acme:textbox code="attendee.holderName" path="holderName" />
		<acme:textbox code="attendee.brandName" path="brandName" />
		<acme:textbox code="attendee.number" path="number" />
		<acme:textbox code="attendee.expirationMonth" path="expirationMonth" />
		<acme:textbox code="attendee.expirationYear" path="expirationYear" />
		<acme:textbox code="attendee.cvv" path="cvv" />
	</fieldset>
	<spring:message code="attendee.check" />
	<form:checkbox path="terms" />
	<form:errors path="terms" cssClass="error" />
	<br />

	<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<button type="submit" onclick="return validatePhoneNumber()"
				name="save">
				<spring:message code="attendee.save" />
			</button>
		</jstl:when>
		<jstl:otherwise>
			<button type="submit" onclick="return validatePhoneNumberEs()"
				name="save">
				<spring:message code="attendee.save" />
			</button>
		</jstl:otherwise>
	</jstl:choose>
	<acme:cancel url="/#" code="attendee.cancel" />

	<script type="text/javascript">
		function validatePhoneNumber() {
			var phoneNumber = document.getElementById("phone");
			if ((phoneNumber.value != "")) {
					if (!(phoneNumber.value).match("[0-9]{10}")) {
						if (!(phoneNumber.value).match("[0-9]{9}")) {
							return confirm("Phone number doesn't adhere to the correct pattern (9 Numbers - 123456789). Do you want to continue?"); 
						}
					}else{
						return confirm("Phone number doesn't adhere to the correct pattern (9 Numbers - 123456789). Do you want to continue?"); 
					}								
			}
		}
		function validatePhoneNumberEs() {
			var phoneNumber = document.getElementById("phone");
			if ((phoneNumber.value != "")) {
				if (!(phoneNumber.value).match("[0-9]{10}")) {
					if (!(phoneNumber.value).match("[0-9]{9}")) {
						return confirm("Phone number doesn't adhere to the correct pattern (9 Numbers - 123456789). Do you want to continue?"); 
					}
				}else{
					return confirm("El teléfono no se ajusta al patrón correcto (9 Números - 123456789). ¿Desea continuar?"); 
				}
			}
		}
	</script>
</form:form>