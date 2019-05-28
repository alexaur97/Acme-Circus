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

<form:form action="offer/organizer/create.do"
					modelAttribute="offerForm" class="form-horizontal" method="post">

						
						<acme:textbox type="number" code="offer.money" path="money" />							
						<acme:select items="${performances}" itemLabel="name" code="offer.performance" path="performance"/>
												<acme:select items="${tours}" itemLabel="name" code="offer.tour" path="tour"/>
												<acme:textbox code="offer.observation" path="observations" />
						
						<spring:message code = "offer.message.comma"/>	
						
						<acme:submit name="save" code="performance.save" />
				</form:form>




