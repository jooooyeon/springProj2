package kr.or.ddit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/zhouyan")
@Controller
public class zhouyanController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping("/regist")
	public void regist() {
		log.info("zhouyan regist");
	}
	
	
	
	
	
	
	
}
