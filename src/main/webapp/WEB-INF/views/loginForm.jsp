<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<div align="center">
<div class="login-box">
	<div class="card">
		<div class="card-body login-card-body">
			<p class="login-box-msg">${error}</p>
			<p class="login-box-msg">${logout}</p>
			<form action="/login" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" name="username" placeholder="아이디를 입려해주세요" />
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-envelope"></span>
						</div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="password" name="password" class="form-control"  placeholder="비밀번호를 작성해주세요">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-8">
						<div class="icheck-primary">
							<!-- 로그인 상태 유지 체크박스 -->
							<input type="checkbox" name="remember-me" id="remember" /> 
							<label for="remember"> Remember Me </label>
						</div>
					</div>

					<div class="col-4">
						<button type="submit" class="btn btn-primary btn-block">Sign
							In</button>
					</div>

				</div>
				
				<!-- 스프링 시큐리티에서 form 페이지를 submit할때 꼭 써줘야 함 
				csrt(Cross-site request forgery) : 크로스 사이트 요청 위조
				공격자가 의도한 행위(수정, 삭제, 등록)을 특정 웹사이트에 요청하게 하는 공격을 막기위함
				-->
				<sec:csrfInput/>
			</form>

		</div>
		

	</div>
</div>
</div>