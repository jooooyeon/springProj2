package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Controller
public class ErrorController {

	//Bad Request
	@GetMapping("/error400")
	public String error400() {
		return "error/error400";
	}
	
	//Page Not Found
	@GetMapping("/error404")
	public String error404() {
		return "error/error404";
	}
	
	//서버 내부 오류
	@GetMapping("/error500")
	public String error500() {
		return "error/error500";
	}
	
	
	@GetMapping("/errorException")
	public String errorException() {
		return "error/errorException";
	}
	
}
