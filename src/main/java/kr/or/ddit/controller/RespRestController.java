package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.MemberVO;

//응답 데이터 타입은 JSON 오브젝트
//http://localhost/respRest/goHome0611
@RequestMapping("/respRest")
@RestController
public class RespRestController {
	private static final Logger log = LoggerFactory.getLogger(RespRestController.class);
	
	//요청 URI : /respRest/goHome0611
	@RequestMapping(value = "/goHome0611", method = RequestMethod.GET)
	public ResponseEntity<String> goHome0611(){
		log.info("goHome0611");
		
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
	}
	
	/**
	 * 8. ResponseEntity<자바빈즈 클래스>타입
	 * response 할때 Http 헤더 정보와 객체 데이터를 전달하는 용도로 사용함
	 * @return
	 */
	//요청 URI : /respRest/goHome0612
	@RequestMapping(value = "/goHome0612", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> goHome0612(){
		log.info("goHome0612");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId("a001");
		memberVO.setMemName("김은대");
		
		//데이터자리에 멤버vo가들어가야함 >json형태로 들어감
		//									데이터 ,   http헤더정보
		return new ResponseEntity<MemberVO>(memberVO,HttpStatus.OK);
	}
	
	/**
	 * 9. ResponseEntity<List>타입
	 * response 할때 Http 헤더 정보와 객체 배열 데이터를 전달하는 용도로 사용함
	 */
	//요청 URI : /respRest/goHome0613
	@RequestMapping(value = "/goHome0613", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> goHome0613(){
		log.info("goHome0613");
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId("a001");
		memberVO.setMemName("김은대");
		
		list.add(memberVO);
		
		memberVO = new MemberVO();
		memberVO.setMemId("b001");
		memberVO.setMemName("이쁜이");
		
		list.add(memberVO);
		
		//										데이터 ,   http헤더정보
		return new ResponseEntity<List<MemberVO>>(list,HttpStatus.OK);
	}
	/**
	 * 연습
	 */
	//ResponseEntity <- 헤더정보를 받기 위해 앞에 붙여줌 없으면 데이터만 전달?할수있다?
	//요청 URI : /respRest/goHome0614
	@RequestMapping(value = "/goHome0614", method = RequestMethod.GET)
	public ResponseEntity<List<String>> goHome0614(){
		log.info("goHome0614!!");
		
		List<String> list = new ArrayList<String>();
		list.add("사과");
		list.add("오렌지");
		list.add("딸기");
		list.add("수박");
		
		list.add("키위");
		list.add("복숭아");
		list.add("청포도");
		
		
		//								데이터 ,   http헤더정보
		return new ResponseEntity<List<String>>(list,HttpStatus.OK);
	}
	
	/**
	 * 10. ResponseEntity<Map>타입
	 * response 할 때 Http 헤더 정보와 객체 데이터를 Map형태로 전달하는 용도로 사용함
	 * 
	 */
	//객체의 JSON 객체 타입의 데이터와 200 OK 상태 코드를 전송
	@RequestMapping(value = "/goHome0615", method=RequestMethod.GET)
	public ResponseEntity<Map<String,String>> goHome0615(){
		log.info("gpHome0615");
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("korea","한국");
		map.put("english","미국");
		map.put("germany","독일");
													//데이터, Http헤더 정보
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		//{"korea":"한국","germany":"독일","english":"미국"} JSON타입으로.나옴 맵이라 키밸류키밸류 형태
	}
	
	
	
}
