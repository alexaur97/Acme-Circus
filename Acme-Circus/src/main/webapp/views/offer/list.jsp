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
		<display:column property="performance.name" titleKey="performance.name" />
	<display:column property="observations" titleKey="offer.observation" />
	<display:column titleKey="offer.status" property="status" ></display:column>
		<display:column titleKey="offer.lastUpdate" property="lastUpdate" ></display:column>
		<display:column titleKey="offer.conditions" property="conditions" ></display:column>
		<display:column titleKey="offer.money" property="money" ></display:column>
<security:authorize access="hasRole('ARTIST')">

	<display:column titleKey="offer.accept">
	<jstl:if test="${noVacio}">
	<jstl:if test="${offers.status==s}">		
			<acme:button url="/offer/artist/accept.do?offerId=${offers.id}" code="offer.accept" />
	</jstl:if>
		</jstl:if>
	</display:column>
	
	<display:column titleKey="offer.reject">	
	<jstl:if test="${noVacio}">
	<jstl:if test="${offers.status==s}">	
			<acme:button url="/offer/artist/reject.do?offerId=${offers.id}" code="offer.reject" />
	</jstl:if>
		</jstl:if>
	</display:column>
	
	
	</security:authorize>
</display:table>
<jstl:if test="${e}">	
</br>
						<p style="color:red">
						<spring:message  code = "offer.message.date"/>	
						</p>	
						</jstl:if>
						<jstl:if test="${time}">	
</br>
						<p style="color:red">
						<spring:message  code = "offer.message.date"/>	
						</p>	
						</jstl:if>
</br>
<security:authorize access="hasRole('ORGANIZER')">
	<acme:button url="/offer/organizer/create.do" code="offer.create" />
</security:authorize>



