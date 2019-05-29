<%--
 * list.jsp
 *
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

<display:table pagesize="5" name="stops" id="stop"
	requestURI="${requestURI}" class="displaytag table">
	<display:column titleKey="stop.city" property="city" />
	<display:column titleKey="stop.country" property= "country"/>
	<display:column titleKey="stop.location" property= "location"/>
	<display:column titleKey="stop.date" property= "date"/>

	<security:authorize access="hasRole('ORGANIZER')">
	

	<display:column titleKey="stop.tour.validated">
	
		
	<jstl:if test="${stop.tour.validated}">
			<h3 style="color: #008000;">
				<spring:message code="stop.yes" />
			</h3>
		</jstl:if>
		<jstl:if test="${!stop.tour.validated}">
			<h3 style="color: #CB3234;">
				<spring:message code="stop.no" />
			</h3>
		</jstl:if>
		</display:column>

</security:authorize>	

<display:column titleKey="stop.show">

		<acme:cancel url="/stop/show.do?stopId=${stop.id}"

			code="stop.show" />

	</display:column>	
	
	<security:authorize access="hasRole('ORGANIZER')">
	
	<display:column titleKey="stop.edit">
			
			<jstl:if test="${!stop.tour.validated}">
	
		<acme:cancel url="/stop/organizer/edit.do?stopId=${stop.id}"

			code="tour.edit" />
			
		</jstl:if>

	</display:column>	
	
	</security:authorize>
</display:table>

	<security:authorize access="hasRole('ORGANIZER')">
<acme:cancel url="/stop/organizer/create.do?stopId=${stop.id}"
			code="stop.create" />
</security:authorize>

<security:authorize access="hasRole('ATTENDEE')">

	<acme:cancel url="/purchase/attendee/create.do?stopId=${stop.id}"
			code="stop.purchase" />
</security:authorize>
