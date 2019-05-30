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

<display:table pagesize="5" name="circus" id="circus"
	requestURI="${requestURI}" class="displaytag table">
	<display:column titleKey="circus.name" property="name" />
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="circus.active" property= "active"/>
	</security:authorize>

		<display:column titleKey="circus.history">
		<acme:cancel url="/circus/show.do?circusId=${circus.id}"
			code="circus.history" />
	</display:column>	
	
	<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${circus.active eq true}">
	<display:column titleKey="circus.deactivate">
		<acme:cancel url="/circus/administrator/deactivate.do?circusId=${circus.id}"
			code="circus.deactivate" />
	</display:column>
	</jstl:if>	
	</security:authorize>
</display:table>

	