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

<h4><spring:message code="actor.administrators" /></h4>

<display:table name="admins" id="admin" requestURI="${requestURI}" pagesize="3" class="displaytag">
<display:column property="name" titleKey="actor.name"/>
<display:column property="surnames" titleKey="actor.surnames"/>
<display:column property="email" titleKey="actor.email"/>
<display:column property="phone" titleKey="actor.phone"/>
<display:column titleKey="actor.show"><acme:button
					url="actor/administrator/show.do?userId=${admin.userAccount.id}"
					code="actor.show" /></display:column>
</display:table>

<h4><spring:message code="actor.attendees" /></h4>

<display:table name="attendees" id="attendee" requestURI="${requestURI}" pagesize="3" class="displaytag">
<display:column property="name" titleKey="actor.name"/>
<display:column property="surnames" titleKey="actor.surnames"/>
<display:column property="email" titleKey="actor.email"/>
<display:column property="phone" titleKey="actor.phone"/>
<display:column titleKey="actor.show"><acme:button
					url="actor/administrator/show.do?userId=${attendee.userAccount.id}"
					code="actor.show" /></display:column>
</display:table>

<h4><spring:message code="actor.owners" /></h4>

<display:table name="owners" id="owner" requestURI="${requestURI}" pagesize="3" class="displaytag">
<display:column property="name" titleKey="actor.name"/>
<display:column property="surnames" titleKey="actor.surnames"/>
<display:column property="email" titleKey="actor.email"/>
<display:column property="phone" titleKey="actor.phone"/>
<display:column titleKey="actor.show"><acme:button
					url="actor/administrator/show.do?userId=${owner.userAccount.id}"
					code="actor.show" /></display:column>
</display:table>

<h4><spring:message code="actor.organizers" /></h4>

<display:table name="organizers" id="organizer" requestURI="${requestURI}" pagesize="3" class="displaytag">
<display:column property="name" titleKey="actor.name"/>
<display:column property="surnames" titleKey="actor.surnames"/>
<display:column property="email" titleKey="actor.email"/>
<display:column property="phone" titleKey="actor.phone"/>
<display:column titleKey="actor.show"><acme:button
					url="actor/administrator/show.do?userId=${organizer.userAccount.id}"
					code="actor.show" /></display:column>
</display:table>

<h4><spring:message code="actor.artists" /></h4>

<display:table name="artists" id="artist" requestURI="${requestURI}" pagesize="3" class="displaytag">
<display:column property="name" titleKey="actor.name"/>
<display:column property="surnames" titleKey="actor.surnames"/>
<display:column property="email" titleKey="actor.email"/>
<display:column property="phone" titleKey="actor.phone"/>
<display:column titleKey="actor.show"><acme:button
					url="actor/administrator/show.do?userId=${artist.userAccount.id}"
					code="actor.show" /></display:column>
</display:table>

