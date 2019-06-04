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

<acme:display code="stats.attendeeNum" path="${attendeeNum}" />
<acme:display code="stats.artistNum" path="${artistNum}" />
<acme:display code="stats.workersNum" path="${workersNum}" />
<br />

<acme:display code="stats.circusNum" path="${circusNum}" />
<table>
	<tr>
		<th><spring:message code="stats.circus" /></th>
		<th><spring:message code="stats.owners" />: ${totalOwners}</th>
		<th><spring:message code="stats.organizers" />: ${totalOrganizers}</th>
	</tr>
	<jstl:forEach items="${circus}" var="x" varStatus="counter">
		<tr>
			<td><a href="circus/show.do?circusId=${x.id}"><jstl:out
						value="${x.name}" /></a></td>
			<td><jstl:out value="${ownersNum[counter.count-1]}" /></td>
			<td><jstl:out value="${organizersNum[counter.count-1]}" /></td>
		</tr>
	</jstl:forEach>
</table>
<br />
<table>
	<tr>
		<th><spring:message code="stats.circusEarning" /></th>
		<th><spring:message code="stats.bannerEarning" /></th>
		<th><spring:message code="stats.artistEarning" /></th>
		<th><spring:message code="stats.totalBenefit" /></th>
	</tr>
	<tr>
		<td>${circusEarning}</td>
		<td>${bannerEarning}</td>
		<td>${artistEarning}</td>
		<td>${totalBenefit}</td>
	</tr>
</table>
<br />
<acme:display code="stats.ratioAcceptedOffersPerArtist" path="${ratio1}" />
<acme:display code="stats.ratioStopsPerTour" path="${ratio2}" />
<spring:message code="stats.mostSpender" />:
<jstl:out value="${mostSpender}" />
<jstl:if test="${not empty mostSpender}">
	<a
		href="actor/administrator/show.do?userId=${mostSpender.userAccount.id}"><spring:message
			code="stats.mostSpender.show" /></a>
</jstl:if>
<acme:display code="stats.totalOffers" path="${totalOffers}" />