<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
	<!-- Left navbar links -->
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
			href="#" role="button"><i class="fas fa-bars"></i></a></li>
		<li class="nav-item d-none d-sm-inline-block"><a
			href="index3.html" class="nav-link">Home</a></li>
		<li class="nav-item d-none d-sm-inline-block"><a href="#"
			class="nav-link">Contact</a></li>
	</ul>

	<!-- Right navbar links -->
	<ul class="navbar-nav ml-auto">
		<!-- Navbar Search -->
		<li class="nav-item"><a class="nav-link"
			data-widget="navbar-search" href="#" role="button"> <i
				class="fas fa-search"></i>
		</a>
			<div class="navbar-search-block">
				<form class="form-inline">
					<div class="input-group input-group-sm">
						<input class="form-control form-control-navbar" type="search"
							placeholder="Search" aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-navbar" type="submit">
								<i class="fas fa-search"></i>
							</button>
							<button class="btn btn-navbar" type="button"
								data-widget="navbar-search">
								<i class="fas fa-times"></i>
							</button>
						</div>
					</div>
				</form>
			</div></li>

		<!-- Messages Dropdown Menu -->
		<li class="nav-item dropdown"><a class="nav-link"
			data-toggle="dropdown" href="#"> <i class="far fa-comments"></i>
				<span class="badge badge-danger navbar-badge">3</span>
		</a>
			<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
				<a href="#" class="dropdown-item"> <!-- Message Start -->
					<div class="media">
						<img src="/resources/adminlte3/dist/img/user1-128x128.jpg"
							alt="User Avatar" class="img-size-50 mr-3 img-circle">
						<div class="media-body">
							<h3 class="dropdown-item-title">
								Brad Diesel <span class="float-right text-sm text-danger"><i
									class="fas fa-star"></i></span>
							</h3>
							<p class="text-sm">Call me whenever you can...</p>
							<p class="text-sm text-muted">
								<i class="far fa-clock mr-1"></i> 4 Hours Ago
							</p>
						</div>
					</div> <!-- Message End -->
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item"> <!-- Message Start -->
					<div class="media">
						<img src="/resources/adminlte3/dist/img/user8-128x128.jpg"
							alt="User Avatar" class="img-size-50 img-circle mr-3">
						<div class="media-body">
							<h3 class="dropdown-item-title">
								John Pierce <span class="float-right text-sm text-muted"><i
									class="fas fa-star"></i></span>
							</h3>
							<p class="text-sm">I got your message bro</p>
							<p class="text-sm text-muted">
								<i class="far fa-clock mr-1"></i> 4 Hours Ago
							</p>
						</div>
					</div> <!-- Message End -->
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item"> <!-- Message Start -->
					<div class="media">
						<img src="/resources/adminlte3/dist/img/user3-128x128.jpg"
							alt="User Avatar" class="img-size-50 img-circle mr-3">
						<div class="media-body">
							<h3 class="dropdown-item-title">
								Nora Silvester <span class="float-right text-sm text-warning"><i
									class="fas fa-star"></i></span>
							</h3>
							<p class="text-sm">The subject goes here</p>
							<p class="text-sm text-muted">
								<i class="far fa-clock mr-1"></i> 4 Hours Ago
							</p>
						</div>
					</div> <!-- Message End -->
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item dropdown-footer">See All
					Messages</a>
			</div></li>
		<!-- Notifications Dropdown Menu -->
		<li class="nav-item dropdown"><a class="nav-link"
			data-toggle="dropdown" href="#"> <i class="far fa-bell"></i> <span
				class="badge badge-warning navbar-badge">15</span>
		</a>
			<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
				<span class="dropdown-item dropdown-header">15 Notifications</span>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item"> <i
					class="fas fa-envelope mr-2"></i> 4 new messages <span
					class="float-right text-muted text-sm">3 mins</span>
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item"> <i class="fas fa-users mr-2"></i>
					8 friend requests <span class="float-right text-muted text-sm">12
						hours</span>
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i>
					3 new reports <span class="float-right text-muted text-sm">2
						days</span>
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item dropdown-footer">See All
					Notifications</a>
			</div></li>
		<li class="nav-item"><a class="nav-link" data-widget="fullscreen"
			href="#" role="button"> <i class="fas fa-expand-arrows-alt"></i>
		</a></li>
		<li class="nav-item"><a class="nav-link"
			data-widget="control-sidebar" data-controlsidebar-slide="true"
			href="#" role="button"> <i class="fas fa-th-large"></i>
		</a></li>
		<!-- 스프링 시큐리티 표현식 : 인증정보, 권한 정보를 다룰 수 있음. 로그인한 사용자 정보를 확인
			1)인증(Authentication)정보
			- isAuthenticated() : 로그인 되었다면 true
			- isAnonymous() : 로그인 안되었다면 true
			- principal : 로그인 한 사용자 정보(CustomUser의 memberVO 멤버변수)
				MemberVO : MemberAuthVO = 1 : N
			2)권한(Autorization)정보 : 로그인 된 후
			- hasRole(role명) : 해당 role이 있으면 true
			- hasAnyRole(role명1, role명2) : 여러 role중 하나라도 해당되는지?
		 -->
		
		<!-- ////////////////////로그인 된 경우 시작///////////////////////////// -->
		<sec:authorize access="isAuthenticated()">
			<li class="nav-item">
				<form action="/logout" method="post">
					<button type="submit" class="btn btn-block btn-secondary">로그아웃</button>
					<sec:csrfInput />
				</form>
			</li>
		</sec:authorize>
		<!-- ////////////////////로그인 된 경우 끝///////////////////////////// -->
		
		<!-- ////////////////////로그인 안된 경우 시작///////////////////////////// -->
		<sec:authorize access="isAnonymous()">
			<li class="nav-item">
					<!-- security:form-login login-page="/login"... -->
					<a href="/login" button type="submit" class="btn btn-block btn-primary">로그인해주세요</a>
				</form>
			</li>
		</sec:authorize>
		
		
		
		
		<!-- ////////////////////로그인 안된 경우 끝///////////////////////////// -->
	</ul>
</nav>