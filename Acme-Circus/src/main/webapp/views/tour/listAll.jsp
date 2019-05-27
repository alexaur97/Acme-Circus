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



<display:table pagesize="5" name="tours" id="tour"

	requestURI="${requestURI}" class="displaytag table">

	<display:column titleKey="tour.name" property="name" />

	<display:column titleKey="tour.description" property= "description"/>

	<display:column titleKey="tour.startDate" property= "startDate"/>

	<display:column titleKey="tour.endDate" property= "endDate"/>	

	<display:column titleKey="tour.link" property= "link"/>

	<display:column titleKey="tour.validated">
		
	<jstl:if test="${tour.validated}">
			<h3 style="color: #008000;">
				<spring:message code="tour.yes" />
			</h3>
		</jstl:if>
		<jstl:if test="${!tour.validated}">
			<h3 style="color: #CB3234;">
				<spring:message code="tour.no" />
			</h3>
		</jstl:if>
		</display:column>

		<display:column titleKey="tour.stops">

		<acme:cancel url="/stop/listByTour.do?tourId=${tour.id}"

			code="tour.stops" />

	</display:column>	
	
	
		<security:authorize access="hasRole('ORGANIZER')">
		
	
	<display:column titleKey="tour.edit">
			
			<jstl:if test="${!tour.validated}">
	

		<acme:cancel url="/tour/organizer/edit.do?tourId=${tour.id}"

			code="tour.edit" />
			
		</jstl:if>

	</display:column>	
	
		</security:authorize>
	
	<security:authorize access="hasRole('OWNER')">
	<display:column titleKey="tour.validate">
		<acme:cancel url="/tour/owner/validate.do?tourId=${tour.id}"
			code="tour.validate" />
	</display:column>	
	</security:authorize>
	

</display:table>

<security:authorize access="hasRole('ORGANIZER')">

	<acme:cancel url="/tour/organizer/create.do"
			code="tour.create" />

	</security:authorize>

	