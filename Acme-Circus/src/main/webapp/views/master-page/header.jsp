<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img height="200px" src="images/logo.png" alt="Acme-Circus Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
	<security:authorize access="hasRole('OWNER')">
			<li><a href="artist/owner/list.do"><spring:message code="master.page.artists" /></a></li>
			
			<li><a href="tour/owner/list.do"><spring:message code="master.page.mytours" /></a></li>
			
		</security:authorize>
		<security:authorize access="hasRole('ORGANIZER')">
			<li><a href="artist/organizer/list.do"><spring:message code="master.page.artists" /></a></li>
		    <li><a href="tour/organizer/list.do"><spring:message code="master.page.mytours" /></a></li>
						
		</security:authorize>
		<security:authorize access="hasRole('ORGANIZER')">
			<li><a href="offer/organizer/list.do"><spring:message code="master.page.offers" /></a></li>
		</security:authorize>
		<security:authorize access="hasRole('ARTIST')">
			<li><a href="performance/artist/list.do"><spring:message code="master.page.performances" /></a></li>
		</security:authorize>
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="categoryTour/administrator/list.do"><spring:message code="master.page.administrator.categoryTour" /></a></li>
					<li><a href="fee/administrator/setup.do"><spring:message code="master.page.administrator.fee" /></a></li>
					<li><a href="actor/administrator/list.do"><spring:message code="master.page.administrator.actor" /></a></li>
					<li><a href="stats/administrator/dashboard.do"><spring:message code="master.page.administrator.stats" /></a></li>					
					<li><a href="circus/administrator/list.do"><spring:message code="master.page.administrator.circus" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ATTENDEE')">
			<li><a class="fNiv" href="tour/attendee/list.do"><spring:message code="master.page.purchase" /></a></li>
			<li><a class="fNiv" href="purchase/attendee/list.do"><spring:message code="master.page.Mypurchase" /></a></li>
		
		
		</security:authorize>
		
			<security:authorize access="hasRole('OWNER')">
			<li><a class="fNiv" href="circus/owner/myList.do"><spring:message code="master.page.myListCircus" /></a></li>
			
			<li><a class="fNiv" href="categoryPrice/owner/list.do"><spring:message code="master.page.categoryPriceList" /></a></li>
			<li><a class="fNiv" href="banner/owner/myList.do"><spring:message code="master.page.banners" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="circus/list.do"><spring:message code="master.page.circus" /></a></li>
		
			<li><a class="fNiv" href="tour/listAll.do"><spring:message code="master.page.tour" /></a></li>
		
			<li><a class="fNiv" href="tour/search.do"><spring:message code="master.page.search" /></a></li>
		
			<li><a class="fNiv" href="categoryTour/list.do"><spring:message code="master.page.categoryTour" /></a></li>
		
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="attendee/signup.do"><spring:message
								code="master.page.register.attendee" /></a></li>
					<li><a href="artist/signup.do"><spring:message
								code="master.page.register.artist" /></a></li>
					<li><a href="owner/signup.do"><spring:message
								code="master.page.register.owner" /></a></li>
					
				</ul></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/edit.do"><spring:message code="master.page.editProfile" /></a></li>
					<security:authorize access="hasRole('WORKER')">
						<li><a href="message/list.do"><spring:message
								code="master.page.message" /> </a></li>
					</security:authorize>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

