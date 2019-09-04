<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="${banner}" width="480" height="260"
		alt="Acme Handy Worker Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conference/administrator/list.do"><spring:message
								code="master.page.admin.conferences" /></a></li>
					<li><a href="dashboard/administrator/show.do"><spring:message
								code="master.page.administrator.dashboard" /></a></li>
					<li><a href="configurationparameters/administrator/display.do"><spring:message
								code="master.page.administrator.confParams" /></a></li>
					<li><a href="topic/administrator/list.do"><spring:message
								code="master.page.administrator.topics" /></a></li>
				</ul></li>
			<li><a class="fNiv"> <spring:message
						code="master.page.adminMessage" />
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/administrator/broadcast.do"> <spring:message
								code="master.page.broadcast" />
					</a></li>
					<li><a href="message/administrator/broadcastToAuthors.do">
							<spring:message code="master.page.broadcastToAuthors" />
					</a></li>
				</ul></li>
			<li><a class="fNiv"> <spring:message
						code="master.page.administrator.confParams" />
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="configurationparameters/administrator/edit.do"> <spring:message
								code="master.page.confParamsEdit" /></a>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('AUTHOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.author" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="submission/author/list.do"><spring:message
								code="master.page.author.submissions" /></a></li>
					<li><a href="registration/author/list.do"><spring:message
								code="master.page.author.registrations" /></a></li>
					<li><a href="finder/author/edit.do"><spring:message
								code="master.page.author.editFinder" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('REVIEWER')">
			<li><a class="fNiv"><spring:message
						code="master.page.reviewer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="submission/reviewer/list.do"><spring:message
								code="master.page.reviewer.submissionsToReview" /></a></li>
					<li><a href="report/reviewer/list.do"><spring:message
								code="master.page.reviewer.reports" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsorship/sponsor/list.do"><spring:message
								code="master.page.sponsor.sponsorships" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a href="conference/list.do"><spring:message
						code="master.page.conferences" /></a></li>
			<li><a href="actor/signUp.do"><spring:message
						code="master.page.signUp" /></a></li>

		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>

					<security:authorize access="hasRole('REVIEWER')">
						<li><a href="reviewer/edit.do"><spring:message
									code="master.page.editProfile" /></a></li>
					</security:authorize>

					<security:authorize access="hasRole('ADMINISTRATOR')">
						<li><a href="administrator/edit.do"><spring:message
									code="master.page.editProfile" /></a></li>
					</security:authorize>

					<security:authorize access="hasRole('AUTHOR')">
						<li><a href="author/edit.do"><spring:message
									code="master.page.editProfile" /></a></li>
					</security:authorize>

					<li><a href="conference/list.do"><spring:message
								code="master.page.conferences" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
			<li><a class="fNiv"> <spring:message
						code="master.page.messages" />
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/list.do"> <spring:message
								code="master.page.listMessages" />
					</a></li>
					<li><a href="message/create.do"> <spring:message
								code="master.page.sendMessage" />
					</a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>
