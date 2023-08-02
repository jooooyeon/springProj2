package kr.or.ddit.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/notice")
@Slf4j
@Controller
public class NoticeController {
	
	//MEMBER,ADMIN, a001, b001, c001
	//or둘중하나 라도 있으면 가능
	@GetMapping("/list")
	//하단 3개중에 하나 사용해도됨. 멤버나 어드민 권한이 있으면 사용가능
	//PreAuthorize("hasRole('ROLE_MEMBER') or hasRole('ROLE_ADMIN')")
	//골뱅이PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
	@Secured({"ROLE_MEMBER","ROLE_ADMIN"})
	public String list() {
		//forwarding
		return "notice/list";
	}
	
	//로그인 한 계정의 경우 접근 가능
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public String register() {
		//forwarding
		return "notice/register";
	}
	
	
}
