package kr.or.ddit.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.FruitService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/chart")
@Slf4j
@Controller
public class ChartController {
	
	//DI
	@Autowired
	FruitService fruitService;

	@GetMapping("/chartmain")
	public String chartMain() {
		//forwarding
		return "chart/chartMain";
	}
	
	
	//json데이터 기반임 src>webapp>resources에 json폴더 생성 -> 제네럴 파일에서 simpleData.json생성 
	//요청URI : /chart/chart01
	@GetMapping("/chart01")
	public String chart01() {
		//forwarding
		return "chart/chart01";
	}
	//요청 URI : /chart/chart01Multi
	@GetMapping("/chart01Multi")
	public String chart01Multi() {
		//forwarding
		return "chart/chart01Multi";
	}
	
	//요청 URI : /chart/chart02?fruitGubun=과일",
	//Json데이터로 리턴
	@ResponseBody
	@GetMapping("/chart02")
	public JSONObject chart02(String fruitGubun) {
		return this.fruitService.selectFruit(fruitGubun);
		
	}
	
	

	
}
