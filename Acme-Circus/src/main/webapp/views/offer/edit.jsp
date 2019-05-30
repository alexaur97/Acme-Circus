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
<security:authorize access="hasRole('ORGANIZER')">

<form:form action="offer/organizer/create.do"
					modelAttribute="offerForm" class="form-horizontal" method="post">

						
						<acme:textbox  code="offer.money" path="money" />							
						<acme:select items="${performances}" itemLabel="name" code="offer.performance" path="performance"/>
												<acme:select items="${tours}" itemLabel="name" code="offer.tour" path="tour"/>
												<acme:textbox code="offer.observation" path="observations" />
						
						<spring:message code = "offer.message.comma"/>	
						
						<acme:submit name="save" code="performance.save" />
				</form:form>
	</security:authorize>
<security:authorize access="hasRole('ARTIST')">

<form:form action="offer/artist/accept.do"
					modelAttribute="offerForm" class="form-horizontal" method="post">

						<form:hidden path="id"/>
						<form:hidden path="version"/>
						
						<acme:textbox code="offer.conditions" path="conditions" />
						
						<spring:message code = "offer.message.comma"/>	
						
						<acme:submit name="save" code="performance.save" />
							<jstl:if test="${blanco}">
						</br>
						<p style="color:red">
						<spring:message  code = "offer.message.error"/>	
						</p>
								</jstl:if>
						
				</form:form>
	</security:authorize>



