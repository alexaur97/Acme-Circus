<%--
 * action-2.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:display code="actor.name" path="${actor.name}" />
<acme:display code="actor.surnames" path="${actor.surnames }" />
<acme:display code="actor.email" path="${actor.email }" />
<acme:display code="actor.phone" path="${actor.phone }" />
<spring:message code="actor.photo"/>: <a href="${actor.photo}" ><jstl:out value="${actor.photo }" /></a>
<acme:display code="actor.address" path="${actor.address }" />
<acme:display code="actor.dni" path="${actor.dni }" />
<acme:display code="actor.creditCard" path="${actor.creditCard.number }" />
<jstl:if test="${isAttendee eq 1}">
	<acme:display code="actor.bornDate" path="${actor.bornDate}"/>
</jstl:if>
<acme:button code="actor.back"
	url="actor/administrator/list.do" />
