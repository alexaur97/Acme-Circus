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

<security:authorize access="hasRole('OWNER')">
<display:table name="organizers" id="organizers" requestURI="${requestURI}"
	pagesize="5" class="displaytag table">
	<display:column titleKey="organizer.photo">
	<img src="${organizer.photo}" alt="Acme Rookies Co., Inc."style="width: 150px; height: 50px;"/>
	</display:column>
	<display:column property="name" titleKey="organizer.name" />
	<display:column property="surnames" titleKey="organizer.surnames" />
		<display:column property="email" titleKey="organizer.email" />
		<display:column property="phone" titleKey="organizer.phone" />
	</display:table>
	<acme:cancel url="/organizer/owner/create.do"
			code="organizer.create" />
	</security:authorize>

	







