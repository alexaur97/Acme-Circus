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


<acme:display code="categoryTour.name" path="name" />
<acme:display code="categoryTour.minAge" path="minAge" />
<jstl:if test="${isNotUsed eq true}">
	<acme:button code="categoryTour.edit"
		url="categoryTour/administrator/edit.do?categoryTourId=${categoryTour.id}" />
</jstl:if>
<acme:button code="categoryTour.back"
	url="categoryTour/administrator/list.do" />
