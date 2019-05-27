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


<display:table pagesize="5" name="banners" id="banner"
	requestURI="${requestURI}" class="displaytag table">
	<display:column titleKey="banner.description" property="description"/>
	<display:column titleKey="banner.startDate" property="startDate"/>
	<display:column titleKey="banner.endDate" property= "endDate"/>
	<display:column titleKey="banner.img" property= "img"/>

		<display:column titleKey="banner.tour">
		<acme:cancel url="/tour/owner/show.do?tourId=${banner.tour.id}"
			code="banner.tour" />
	</display:column>	
	
		<display:column titleKey="banner.edit">
		<acme:cancel url="/banner/owner/edit.do?bannerId=${banner.id}"
			code="banner.edit" />
	</display:column>	
	
	
</display:table>
<br>
<br>

	<acme:cancel url="/banner/owner/create.do" code="banner.create" />


	