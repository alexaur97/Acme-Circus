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

<display:table name="performances" id="performances" requestURI="${requestURI}"
	pagesize="5" class="displaytag table">
	<display:column property="name" titleKey="performance.name" />
	<display:column titleKey="performance.persons" property="persons" ></display:column>
	
		<display:column titleKey="performance.video">
		<a href="${performances.video }">Video</a>
	</display:column>
	<security:authorize access="hasRole('ARTIST')">
	<jstl:if test="${!performances.copy}">
	<display:column titleKey="performance.edit">		
			<acme:button url="/performance/artist/edit.do?performanceId=${performances.id}" code="performance.edit" />
	</display:column>
	<display:column titleKey="performance.delete">		
			<acme:button url="/performance/artist/delete.do?performanceId=${performances.id}" code="performance.delete" />
	</display:column>
							</jstl:if>
	</security:authorize>

</display:table>
</br>
<security:authorize access="hasRole('ARTIST')">
	<acme:button url="/performance/artist/create.do" code="performance.create" />
	</security:authorize>




