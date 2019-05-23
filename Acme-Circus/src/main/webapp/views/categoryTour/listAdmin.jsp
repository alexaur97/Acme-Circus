<%--
 * action-2.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="categoriesTour" id="cat" requestURI="${requestURI}" pagesize="10" class="displaytag">
<display:column property="name" titleKey="categoryTour.name"/>
<display:column property="minAge" titleKey="categoryTour.minAge"/>
<display:column titleKey="categoryTour.edit"><acme:button
					url="categoryTour/administrator/edit.do?categoryTourId=${cat.id}"
					code="categoryTour.edit" /></display:column>
</display:table>

	<acme:button url="categoryTour/administrator/create.do" code="categoryTour.create"/>
</table>