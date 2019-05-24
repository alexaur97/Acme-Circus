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
	
		<display:column titleKey="tour.stops">
		<acme:cancel url="/stop/attendee/list.do?tourId=${tour.id}"
			code="tour.stops" />
	</display:column>	
	
</display:table>

	