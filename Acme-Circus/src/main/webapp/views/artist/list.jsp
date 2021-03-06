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
<display:table name="artists" id="artists" requestURI="${requestURI}"
	pagesize="5" class="displaytag table">
	<display:column titleKey="artist.photo">
	<img src="${artists.photo}" alt="Acme Rookies Co., Inc."style="width: 150px; height: 50px;"/>
	</display:column>
	<display:column property="name" titleKey="artist.name" />
	<display:column property="surnames" titleKey="artist.surnames" />
		<display:column property="email" titleKey="artist.email" />
		<display:column property="phone" titleKey="artist.phone" />
		<display:column titleKey="artist.performances">
			<acme:button url="/artist/owner/performances.do?artistId=${artists.id}" code="artist.performances" />
	</display:column>
	</display:table>
	</security:authorize>
	<security:authorize access="hasRole('ORGANIZER')">
<display:table name="artists" id="artists" requestURI="${requestURI}"
	pagesize="5" class="displaytag table">
	<display:column titleKey="artist.photo">
	<img src="${artists.photo}" alt="Acme Rookies Co., Inc."style="width: 150px; height: 50px;"/>
	</display:column>
	<display:column property="name" titleKey="artist.name" />
	<display:column property="surnames" titleKey="artist.surnames" />
		<display:column property="email" titleKey="artist.email" />
		<display:column property="phone" titleKey="artist.phone" />
		<display:column titleKey="artist.performances">
			
			<acme:button url="/artist/organizer/performances.do?artistId=${artists.id}" code="artist.performances" />
	</display:column>
	</display:table>
	</security:authorize>
	







