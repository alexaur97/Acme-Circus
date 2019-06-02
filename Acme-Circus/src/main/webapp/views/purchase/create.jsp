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

				<form:form action="purchase/attendee/create.do?stopId=${stopId}"
					modelAttribute="purchaseAttendeeForm" class="form-horizontal"
					method="post">
					<div class="form-group ">



						<acme:textbox code="purchase.num" path="num" />
						<spring:message code="limit.tickets" />
						${spotsAvailable}
						<spring:message code="purchase.tickets" />
						<br> <br>


						<acme:select items="${categories}" itemLabel="category"
							code="purchase.category" path="category" />
						
						
						<br>
						<br>
						<spring:message code="purchase.type" />:
						<br>
						<jstl:forEach items="${categories}" var="x">
							<spring:message code="purchase.category" />: <jstl:out value="${x.category}"> </jstl:out>,
							<spring:message code="purchase.amount" />: <jstl:out value="${x.amount}"></jstl:out>
							<br>
						</jstl:forEach>

						<br> <br>


						<acme:submit name="save" code="purchase.save" />


						<acme:cancel url="/tour/attendee/list.do" code="purchase.cancel" />
					</div>
				</form:form>
			</fieldset>


		</div>
	</div>
</div>
