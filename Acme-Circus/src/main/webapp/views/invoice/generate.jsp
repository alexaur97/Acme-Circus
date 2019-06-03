<%--
 * list.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:choose>
	<jstl:when test="${alreadyGenerated eq true}">
		<h4>
			<spring:message code="invoice.alreadyGen" />:
		</h4>
		<display:table name="circusInvoices" id="circusInvoice"
			requestURI="${requestURI}" pagesize="5" class="displaytag">
			<display:column property="circus.name" titleKey="invoice.circus" />
			<display:column property="total" titleKey="invoice.total" />
			<display:column property="dateRequested" titleKey="invoice.date" />
		</display:table>
		
		<h3>Total: <jstl:out value="${sum}"/>&#8364;</h3>
	</jstl:when>
	<jstl:otherwise>
	<h4><spring:message code="invoice.click" /></h4>
		<acme:button url="invoice/administrator/generating.do" code="invoice.gen" />
	</jstl:otherwise>
</jstl:choose>