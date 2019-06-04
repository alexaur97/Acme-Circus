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



<jstl:if test="${not empty artistInvoice}">
<acme:display code="invoice.date" path="${artistInvoice.dateRequested}"/>
<acme:display code="invoice.fee" path="${artistInvoice.acceptedOfferFee*100}%"/>
<h4><spring:message code="invoice.offer"/></h4>

<fieldset>
<jstl:out value="${artistInvoice.offer.performance.name}" />
<br/>
<jstl:if test="${not empty artistInvoice.offer.conditions}">
<acme:display code="invoice.conditions" path="${artistInvoice.offer.conditions}" />
</jstl:if>
<jstl:out value="${artistInvoice.offer.money}" />&#8364; 
<br/>
<br/>
<acme:display code="invoice.lastModified" path="${artistInvoice.offer.lastUpdate}" />
</fieldset>

<h3><spring:message code="invoice.total" />: <jstl:out value="${artistInvoice.total}"/>&#8364;</h3>
<acme:button url="invoice/artist/list.do" code="invoice.back"/>
</jstl:if>

<jstl:if test="${not empty bannerInvoice}">

<acme:display code="invoice.date" path="${bannerInvoice.dateRequested}"/>
<acme:display code="invoice.banner" path="${bannerInvoice.banner.description}"/>
<spring:message code="invoice.fee" />: <jstl:out value="${bannerInvoice.bannerFee}" />&#8364;
<h3><spring:message code="invoice.total" />: <jstl:out value="${bannerInvoice.total}"/>&#8364;</h3>
<acme:button url="invoice/owner/list.do" code="invoice.back"/>
</jstl:if>

<jstl:if test="${not empty circusInvoice}">
<acme:display code="invoice.date" path="${circusInvoice.dateRequested}"/>
<acme:display code="invoice.circus" path="${circusInvoice.circus.name}"/>
<spring:message code="invoice.fee" />: <jstl:out value="${circusInvoice.circusFee}" />&#8364;
<h3><spring:message code="invoice.total" />: <jstl:out value="${circusInvoice.total}"/>&#8364;</h3>
<acme:button url="invoice/owner/list.do" code="invoice.back"/>
</jstl:if>