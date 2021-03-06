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

<h3><spring:message code="invoice.circusInvoice" /></h3>

<display:table name="circusInvoices" id="circusInvoice" requestURI="invoice/administrator/list.do" pagesize="5" class="displaytag">
<display:column property="circus.name" titleKey="invoice.circus"/>
<display:column property="total" titleKey="invoice.total"/>
<display:column property="dateRequested" titleKey="invoice.date"/>
</display:table>

<h3><spring:message code="invoice.bannerInvoice" /></h3>

<display:table name="bannerInvoices" id="bannerInvoice" requestURI="invoice/administrator/list.do" pagesize="5" class="displaytag">
<display:column titleKey="invoice.img"><a href="${bannerInvoice.banner.img}"><spring:message code="invoice.show" /></a></display:column>
<display:column property="banner.description" titleKey="invoice.description"/>
<display:column property="total" titleKey="invoice.total"/>
<display:column property="dateRequested" titleKey="invoice.date"/>
</display:table>

<h3><spring:message code="invoice.artistInvoice" /></h3>

<display:table name="artistInvoices" id="artistInvoice" requestURI="invoice/administrator/list.do" pagesize="5" class="displaytag">
<display:column property="offer.performance.name" titleKey="invoice.offer"/>
<display:column property="total" titleKey="invoice.total"/>
<display:column property="dateRequested" titleKey="invoice.date"/>
</display:table>
	
<h3>Total: <jstl:out value="${total}"/>&#8364;</h3>