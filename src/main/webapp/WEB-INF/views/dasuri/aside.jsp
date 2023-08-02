<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="index3.html" class="brand-link"> <img
		src="/resources/adminlte3/dist/img/AdminLTELogo.png"
		alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
		style="opacity: .8"> <span class="brand-text font-weight-light">AdminLTE
			3</span>
	</a>

	<!-- CustomUser 클래스의 memberVO 멤버변수 : principal.memberVO -->
	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<!-- 로그인 되었다면 시작 -->
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="principal" />
			<div class="user-panel mt-3 pb-3 mb-3 d-flex">
				<div class="image">
					<!-- id 속성값이 imgMember인 요소를 클릭시
					ajax 비동기 통신을 통해 success의 result로 MemberVO JSON데이터를 받아온 뒤
					modal 영역의 회원아이디, 회원명, 권한목록, 그리고 증명사진을 출력해보자
					 -->
					<img src="/resources/upload${principal.memberVO.memImg }"
						data-toggle="modal" data-target="#modalMember" 
						class="img-circle elevation-2" alt="User Image" />
				</div>
				<div class="info">
					<!-- 이름을 클릭하면 modal을 띄워보자 -->
					<a href="#modalMember" id="imgMember" class="d-block" data-toggle="modal">${principal.memberVO.memName}</a>
				</div>
			</div>
		</sec:authorize>
		<!-- 로그인 되었다면 끝 -->
		<!-- 로그인 안되었다면 시작 -->
		<sec:authorize access="isAnonymous()">
			<div class="user-panel mt-3 pb-3 mb-3 d-flex">
				<div class="image">
					<img src="/resources/adminlte3/dist/img/z5.jpeg"
						class="img-circle elevation-2" alt="User Image" />
				</div>
				<div class="info">
					<a href="#" class="d-block">뚜연쓰</a>
				</div>
			</div>
	</sec:authorize>
	<!-- 로그인 안되었다면 끝 -->

	<!-- SidebarSearch Form -->
	<div class="form-inline">
		<div class="input-group" data-widget="sidebar-search">
			<input class="form-control form-control-sidebar" type="search"
				placeholder="Search" aria-label="Search">
			<div class="input-group-append">
				<button class="btn btn-sidebar">
					<i class="fas fa-search fa-fw"></i>
				</button>
			</div>
		</div>
	</div>

	<!-- Sidebar Menu -->
	<nav class="mt-2">
		<ul class="nav nav-pills nav-sidebar flex-column"
			data-widget="treeview" role="menu" data-accordion="false">
			<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
			<li class="nav-item menu-open"><a href="#"
				class="nav-link active"> <i
					class="nav-icon fas fa-tachometer-alt"></i>
					<p>
						Dashboard <i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="/resources/adminlte3/index.html"
						class="nav-link active"> <i class="far fa-circle nav-icon"></i>
							<p>Dashboard v1</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/index2.html" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>Dashboard v2</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/index3.html" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>Dashboard v3</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a
				href="/resources/adminlte3/pages/widgets.html" class="nav-link">
					<i class="nav-icon fas fa-th"></i>
					<p>
						Widgets <span class="right badge badge-danger">New</span>
					</p>
			</a></li>
			<!-- ====================상품 관리 시작===================== -->
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-copy"></i>
					<p>
						상품관리 <i class="fas fa-angle-left right"></i> <span
							class="badge badge-info right">4</span>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="/item/list" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>상품목록</p>
					</a></li>
					<li class="nav-item"><a href="/item/register" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>상품등록</p>
					</a></li>
					<li class="nav-item"><a href="/item/multiList"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>상품 멀티 이미지 목록</p>
					</a></li>
					<li class="nav-item"><a href="/item/multiRegister"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>상품 멀티 이미지 등록</p>
					</a></li>
				</ul></li>
			<!-- ====================상품 관리 끝===================== -->
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-copy"></i>
					<p>
						Layout Options <i class="fas fa-angle-left right"></i> <span
							class="badge badge-info right">6</span>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/layout/top-nav.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Top Navigation</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/layout/top-nav-sidebar.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Top Navigation + Sidebar</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/layout/boxed.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Boxed</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/layout/fixed-sidebar.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Fixed Sidebar</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/layout/fixed-sidebar-custom.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>
								Fixed Sidebar <small>+ Custom Area</small>
							</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/layout/fixed-topnav.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Fixed Navbar</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/layout/fixed-footer.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Fixed Footer</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/layout/collapsed-sidebar.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Collapsed Sidebar</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-chart-pie"></i>
					<p>
						Charts <i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/charts/chartjs.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>ChartJS</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/charts/flot.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Flot</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/charts/inline.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Inline</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/charts/uplot.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>uPlot</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-tree"></i>
					<p>
						UI Elements <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/UI/general.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>General</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/UI/icons.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>Icons</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/UI/buttons.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>Buttons</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/UI/sliders.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>Sliders</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/UI/modals.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>Modals & Alerts</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/UI/navbar.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>Navbar & Tabs</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/UI/timeline.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Timeline</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/UI/ribbons.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>Ribbons</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-edit"></i>
					<p>
						Forms <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/forms/general.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>General Elements</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/forms/advanced.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Advanced Elements</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/forms/editors.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Editors</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/forms/validation.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Validation</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-table"></i>
					<p>
						Tables <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/tables/simple.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Simple Tables</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/tables/data.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>DataTables</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/tables/jsgrid.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>jsGrid</p>
					</a></li>
				</ul></li>
			<li class="nav-header">EXAMPLES</li>
			<li class="nav-item"><a
				href="/resources/adminlte3/pages/calendar.html" class="nav-link">
					<i class="nav-icon far fa-calendar-alt"></i>
					<p>
						Calendar <span class="badge badge-info right">2</span>
					</p>
			</a></li>
			<li class="nav-item"><a
				href="/resources/adminlte3/pages/gallery.html" class="nav-link">
					<i class="nav-icon far fa-image"></i>
					<p>Gallery</p>
			</a></li>
			<li class="nav-item"><a
				href="/resources/adminlte3/pages/kanban.html" class="nav-link">
					<i class="nav-icon fas fa-columns"></i>
					<p>Kanban Board</p>
			</a></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon far fa-envelope"></i>
					<p>
						Mailbox <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/mailbox/mailbox.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Inbox</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/mailbox/compose.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Compose</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/mailbox/read-mail.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Read</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-book"></i>
					<p>
						Pages <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/invoice.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Invoice</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/profile.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Profile</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/e-commerce.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>E-commerce</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/projects.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Projects</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/project-add.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Project Add</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/project-edit.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Project Edit</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/project-detail.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Project Detail</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/contacts.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Contacts</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/faq.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>FAQ</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/contact-us.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Contact us</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon far fa-plus-square"></i>
					<p>
						Extras <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>
								Login & Register v1 <i class="fas fa-angle-left right"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="/resources/adminlte3/pages/examples/login.html"
								class="nav-link"> <i class="far fa-circle nav-icon"></i>
									<p>Login v1</p>
							</a></li>
							<li class="nav-item"><a
								href="/resources/adminlte3/pages/examples/register.html"
								class="nav-link"> <i class="far fa-circle nav-icon"></i>
									<p>Register v1</p>
							</a></li>
							<li class="nav-item"><a
								href="/resources/adminlte3/pages/examples/forgot-password.html"
								class="nav-link"> <i class="far fa-circle nav-icon"></i>
									<p>Forgot Password v1</p>
							</a></li>
							<li class="nav-item"><a
								href="/resources/adminlte3/pages/examples/recover-password.html"
								class="nav-link"> <i class="far fa-circle nav-icon"></i>
									<p>Recover Password v1</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>
								Login & Register v2 <i class="fas fa-angle-left right"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="/resources/adminlte3/pages/examples/login-v2.html"
								class="nav-link"> <i class="far fa-circle nav-icon"></i>
									<p>Login v2</p>
							</a></li>
							<li class="nav-item"><a
								href="/resources/adminlte3/pages/examples/register-v2.html"
								class="nav-link"> <i class="far fa-circle nav-icon"></i>
									<p>Register v2</p>
							</a></li>
							<li class="nav-item"><a
								href="/resources/adminlte3/pages/examples/forgot-password-v2.html"
								class="nav-link"> <i class="far fa-circle nav-icon"></i>
									<p>Forgot Password v2</p>
							</a></li>
							<li class="nav-item"><a
								href="/resources/adminlte3/pages/examples/recover-password-v2.html"
								class="nav-link"> <i class="far fa-circle nav-icon"></i>
									<p>Recover Password v2</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/lockscreen.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Lockscreen</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/legacy-user-menu.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Legacy User Menu</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/language-menu.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Language Menu</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/404.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Error 404</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/500.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Error 500</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/pace.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Pace</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/examples/blank.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Blank Page</p>
					</a></li>
					<li class="nav-item"><a href="starter.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>Starter Page</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-search"></i>
					<p>
						Search <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/search/simple.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Simple Search</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/adminlte3/pages/search/enhanced.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Enhanced</p>
					</a></li>
				</ul></li>
			<li class="nav-header">MISCELLANEOUS</li>
			<li class="nav-item"><a href="/resources/adminlte3/iframe.html"
				class="nav-link"> <i class="nav-icon fas fa-ellipsis-h"></i>
					<p>Tabbed IFrame Plugin</p>
			</a></li>
			<li class="nav-item"><a href="https://adminlte.io/docs/3.1/"
				class="nav-link"> <i class="nav-icon fas fa-file"></i>
					<p>Documentation</p>
			</a></li>
			<li class="nav-header">MULTI LEVEL EXAMPLE</li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="fas fa-circle nav-icon"></i>
					<p>Level 1</p>
			</a></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon fas fa-circle"></i>
					<p>
						Level 1 <i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>Level 2</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>
								Level 2 <i class="right fas fa-angle-left"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a href="#" class="nav-link"> <i
									class="far fa-dot-circle nav-icon"></i>
									<p>Level 3</p>
							</a></li>
							<li class="nav-item"><a href="#" class="nav-link"> <i
									class="far fa-dot-circle nav-icon"></i>
									<p>Level 3</p>
							</a></li>
							<li class="nav-item"><a href="#" class="nav-link"> <i
									class="far fa-dot-circle nav-icon"></i>
									<p>Level 3</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>Level 2</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="fas fa-circle nav-icon"></i>
					<p>Level 1</p>
			</a></li>
			<li class="nav-header">LABELS</li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon far fa-circle text-danger"></i>
					<p class="text">Important</p>
			</a></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon far fa-circle text-warning"></i>
					<p>Warning</p>
			</a></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon far fa-circle text-info"></i>
					<p>Informational</p>
			</a></li>
		</ul>
	</nav>
	<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>

<!--  모달창 띄우기-->
<div class="modal fade" id="modalMember" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">회원정보</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
			<!-- 내용 넣기 시작 -->
				<div class="card card-success">
					<div class="card-header">
						<h3 class="card-title" id="modalTitleMemName">${principal.memberVO.memName}</h3>
					</div>
					<div class="card-body">
						<input class="form-control form-control-lg" type="text" id="modalMemId"
							placeholder="회원아이디" value="${principal.memberVO.memId}" > <br /> 
						<input class="form-control" type="text" id="modalMemName" 
							placeholder="회원이름" value="${principal.memberVO.memName}" ><br/>
						<!-- principal.memberVO.memberAuthVOList : List<MemberAuthVO> -->
						<c:set var="auth" value="" />
						<c:set var="memberAuthVOList" value="${principal.memberVO.memberAuthVOList}" ></c:set>
						<c:forEach var="memberAuthVO" items="${memberAuthVOList}" varStatus="stat">
							<!-- var ="sum" value="달러{sum+값}" -->
							<c:set var="auth" value="${auth += ' ' += memberAuthVO.auth}" />
						</c:forEach>
						<input class="form-control form-control-sm" type="text" id="modalAuth"
							placeholder="권한정보" value="${auth}">
						<!-- <img src="" id="modalMemImg" class="img-fluid mb-2" /> -->
						<img src="/resources/upload${principal.memberVO.memImg}" id="modalMemImg" class="img-fluid mb-2" />
					</div>

				</div>
				<!-- 내용 넣기 끝 -->
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>
<!--  모달창 띄우기 끝 -->

<script type="text/javascript">
$(function(){
	$("#imgMember").on("click", function(){
		console.log("클릭함");
		let memId = "${principal.memberVO.memId}";
		console.log(memId)
		let data = {"memId":memId};
		
		console.log("Data: " + JSON.stringify(data));
		//contentType : 보내는 타입
		//dataType:응답타입
		//스프링 시큐리티 사용시 추가해야 할 내역 : 데이터 전송 전 헤더에 scrf(cross site request forgery)값 설정
		$.ajax({
			url:"/member/detailMember",
			contentType:"application/json;charset:utf-8",
			data:JSON.stringify(data),
			type:"post",
			dataType:"json",
			beforeSend:function(xhr){
				xhr.setRequestHeader("${_csrf.headerName}","${_csrf.token}");
			},
			success:function(result){
				//result = memberVO
				console.log("result : " + JSON.stringify(result));
				//card title
				$("#modalTitleMemName").html(result.memName);
				//회원아이디
				$("#modalMemId").val(result.memId);
				//회원이름
				$("#modalMemName").val(result.memName);
				//권한정보
				//"memberAuthVOList":[{"memId":"member","auth":"ROLE_MEMBER"}]}
				//result.memberAuthVOList : List<MemberAuthVO>
				
				let str ="";
				$.each(result.memberAuthVOList,function(index,memberAuthVO){
					let auth = memberAuthVO.auth;
					console.log(auth);
					//누적
					str += auth + " ";
				});
					$("#modalAuth").val(str);
					
				//회원 증명이미지
				//<img src="" id="modalMemImg" class="form-control" style="width:90%" />
				$("#modalMemImg").attr("src","/resources/upload" + result.memImg);
			}
		})
		

		
		
	})
})
</script>

