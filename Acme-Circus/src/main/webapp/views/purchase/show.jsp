<%--
 * Copyright (C) 2019 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>
 
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="purchase.totalPrice"/>: <jstl:out value="${purchase.totalPrice}"></jstl:out>
<br/>
<spring:message code="purchase.name"/>: <jstl:out value="${purhase.attendee.name}"></jstl:out>
<br/>
<spring:message code="purchase.creditCard"/>: <jstl:out value="${purchase.creditCard}"></jstl:out>
<br/>
<spring:message code="purchase.tickets"/>
<jstl:forEach items="${tickets}" var="x">
		<spring:message code="ticket.refNumber"/>: <jstl:out value="${x.refNumber}"></jstl:out>
		<spring:message code="ticket.categoryPrice"/>: <jstl:out value="${x.categoryPrice}"></jstl:out>
		
	</jstl:forEach>





	