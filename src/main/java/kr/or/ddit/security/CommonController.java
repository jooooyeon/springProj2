package kr.or.ddit.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class CommonController {
	//요청 URI : /accessError
	//접근 거부 처리 메소드
	@GetMapping("/accessError")
	public String accessDenied(Authentication auth, Model model) {
		
		//auth  로그인관련정보
		log.info("access Denied : " + auth);
		
		model.addAttribute("msg","Access Denied");
		
		
		//forwarding
		return "accessError";
	}
	
	//오류 메시지, 로그아웃 메시지를 파라미터로 준비
	//<security:form-login login-page="/login" />
	@GetMapping("/login")
	public String login(String error, String logout, Model model) {
		
		log.info("error : " + error);
		log.info("logout : " + logout);
		
		if(error != null) {
			model.addAttribute("error","Login Error");
		}
		if(logout != null) {
			model.addAttribute("logout","Logout");
		}
		
		//forwarding
		return "loginForm";
	}
	
}
