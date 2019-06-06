<%--
 * list.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<fieldset class="col-md-6 col-md-offset-3">

				<form:form action="tour/organizer/edit.do"
					modelAttribute="tour" class="form-horizontal" method="post">
					<div class="form-group ">
						
					<form:hidden path="id"/>
						<form:hidden path="version"/>
								
						<acme:textbox code="tour.name" path="name" />
						<acme:textarea code="tour.description" path="description" />
						<acme:textbox placeholder="yyyy-MM-dd" code="tour.startDate" path="startDate" />
						<acme:textbox placeholder="yyyy-MM-dd" code="tour.endDate" path="endDate" />
						<acme:textbox code="tour.link" path="link" />
						<acme:textarea code="tour.tags" path="tags" />
						<spring:message code="tour.categoryTour"/>	
						<form:select  id="categoryTour" code="tour.categoryTour" path="categoryTour">
							<form:options items="${categoryTours}" itemLabel="name" itemValue="id" />
						</form:select>
						
						
						<acme:submit name="save" code="tour.save" />
						
						<acme:cancel url="/tour/organizer/list.do"
							code="tour.cancel" />
							
						
						 <jstl:if test="${tour.id!=0}">
							<acme:submitConfirmation name="delete" code="tour.delete"
								onclick="tour.delete.confirmation" />
						</jstl:if> 
			
					</div>
				</form:form>
			</fieldset>

			
		<spring:message code="tour.aviso.tags"/>

		</div>
	</div>
</div>
