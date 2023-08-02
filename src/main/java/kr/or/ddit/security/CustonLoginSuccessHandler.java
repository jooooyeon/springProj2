package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustonLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws ServletException, IOException {
		log.warn("onAuthenticationSuccess");
		
		//auth.getPrincipal() : 사용자 정보를 가져옴
		// *시큐리티에서 사용자 정보는 User클래스의 객체로 저장됨(CustomUser.java를 참고)
		User customUser = (User) auth.getPrincipal();
		
		//사용자 아이디를 리턴
		log.info(customUser.getUsername());
		
		//로그인 시도->로그인 성공 -> 인증 중 로그인성공과 인증 사이에서 사용되나봄?
		//흐름 : SavedRequestAwareAuthenticationSuccessHandler를 가져다가 쓰고
		//다시 SavedRequestAwareAuthenticationSuccessHandler에 반납해주어야 함
		//부모(super)에게 반납
		super.onAuthenticationSuccess(request, response, auth);
		
		
		
	}
	
	
	
	
	
	
}//클래스끝
