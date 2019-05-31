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

<form:form id="form" action="owner/signup.do"
	modelAttribute="ownerRegisterForm" method="POST">
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="owner.personal.data" />
		</legend>
		<acme:textbox code="owner.name" path="name" />
		<acme:textbox code="owner.surnames" path="surnames" />
		<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<acme:textbox code="owner.dni" path="dni" placeholder="8 numbers and 1 letter"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:textbox code="owner.dni" path="dni" placeholder="8 números y 1 letra"/>
		</jstl:otherwise>
		</jstl:choose>
		<acme:textbox code="owner.photo" path="photo" />
		<acme:textbox code="owner.email" path="email" />
		<acme:textbox code="owner.phone" path="phone" placeholder="123456789" comment="owner.phone.pattern" />
		<acme:textbox code="owner.address" path="address" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="owner.userAccount" />
		</legend>
		<acme:textbox code="owner.username" path="username" />
		<acme:password code="owner.password" path="password" />
		<acme:password code="owner.confirmPassword" path="confirmPassword" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="owner.creditCard" />
		</legend>
		<acme:textbox code="owner.holderName" path="holderName" />
		<acme:textbox code="owner.brandName" path="brandName" />
		<acme:textbox code="owner.number" path="number" />
		<acme:textbox code="owner.expirationMonth" path="expirationMonth" />
		<acme:textbox code="owner.expirationYear" path="expirationYear" />
		<acme:textbox code="owner.cvv" path="cvv" />
	</fieldset>
	<br />
	<fieldset style="border-width: 4px">
		<legend>
			<spring:message code="owner.circus" />
		</legend>
		<acme:textbox code="owner.circusName" path="circusName" />
		<acme:textbox code="owner.circusVAT" path="VAT" placeholder="ESANNNNNNNA"
			comment="owner.vat.pattern"/>
		<acme:textarea code="owner.circusHistory" path="history" />
	</fieldset>
	<spring:message code="owner.check" />
	<form:checkbox path="terms" />
	<form:errors path="terms" cssClass="error" />
	<br />

	<jstl:choose>
		<jstl:when test="${lang eq 'en'}">
			<button type="submit" onclick="return validatePhoneNumber()"
				name="save">
				<spring:message code="owner.save" />
			</button>
		</jstl:when>
		<jstl:otherwise>
			<button type="submit" onclick="return validatePhoneNumberEs()"
				name="save">
				<spring:message code="owner.save" />
			</button>
		</jstl:otherwise>
	</jstl:choose>
	<acme:cancel url="/#" code="owner.cancel" />

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