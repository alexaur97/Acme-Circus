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

<display:table name="offers" id="offers" requestURI="${requestURI}"
	pagesize="5" class="displaytag table">
	<display:column property="observations" titleKey="offer.observation" />
	<display:column titleKey="offer.status" property="status" ></display:column>
		<display:column titleKey="offer.lastUpdate" property="lastUpdate" ></display:column>
		<display:column titleKey="offer.conditions" property="conditions" ></display:column>
		<display:column titleKey="offer.money" property="money" ></display:column>
<security:authorize access="hasRole('ARTIST')">
<jstl:if test="${noVacio}">
	<jstl:if test="${offers.status==s}">
	<display:column titleKey="offer.accept">		
			<acme:button url="/performance/artist/edit.do?performanceId=${performances.id}" code="offer.accept" />
	</display:column>
	<display:column titleKey="offer.reject">		
			<acme:button url="/offer/artist/reject.do?offerId=${offers.id}" code="offer.reject" />
	</display:column>
	</jstl:if>
		</jstl:if>
	
	</security:authorize>
</display:table>
</br>
<security:authorize access="hasRole('ORGANIZER')">
	<acme:button url="/offer/organizer/create.do" code="offer.create" />
</security:authorize>



