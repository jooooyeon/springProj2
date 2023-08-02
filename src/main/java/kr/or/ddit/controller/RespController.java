package kr.or.ddit.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemberVO;

/**
 * 
 * @author PC-11
 *
 *         4장. 컨트롤러 응답 1) void 타입 2) String 타입 3) 자바 빈즈 클래스 타입 4) 컬렉션 List타입 5)
 *         컬렉션 Map타입 6) ResponseEntity<void> 타입 7) ResponseEntity<String> 타입 8)
 *         ResponseEntity<자바 빈즈 클래스> 타입 9) ResponseEntity<List>타입 10)
 *         ResponseEntity<Map>타입 11) ResponseEntity<byte[]>타입
 */

@RequestMapping("/resp")
@Controller
public class RespController {
	private final Logger log = LoggerFactory.getLogger(RespController.class);

	// Di(의존성 주입), IoC(제어의 역전)
	@Autowired
	MemberService memberService;

	// 1) void 타입 : 호출하는 URL과 통일한 뷰 이름을 나타내기 위해 사용함
	// void라 리턴 없음
	// 요청 URI : /resp/goHome0101
	// "views/resp/goHome0101.jsp"를 포워딩; <-위에와 일치시 void사용?
	@RequestMapping(value = "/goHome0101", method = RequestMethod.GET)
	public void goHome0101() {
		log.info("gogoHome0101에 왔다");
	}

	// 요청 URI : /resp/goHome0102
	// "views/resp/goHome0102.jsp"를 포워딩;
	@RequestMapping(value = "/goHome0102", method = RequestMethod.GET)
	public void goHome0102() {
		log.info("gogoHome0102에 왔다");
	}

	/**
	 * 2. String 타입 뷰 파일의 경로와 파일 이름을 나타내기 위해 사용됨
	 */
	// String 타입이라 리턴이 있음
	// 요청URI : /resp/goHome0201
	@RequestMapping(value = "/goHome0201", method = RequestMethod.GET)
	public String goHome0201() {
		log.info("goHome0201에 왔다");

		// servlet-context.xml
		// ViewResolver 컨트롤러에서 처리한 결과를 보여줄 뷰를 결정
		// /WEB-INF/views/ + resp/goHome0201 + .jsp
		return "resp/goHome0201";
	}

	// String 타입이라 리턴이 있음
	// 요청URI : /resp/goHome0202
	@RequestMapping(value = "/goHome0202", method = RequestMethod.GET)
	public String goHome0202() {
		log.info("goHome0202에 왔다");

		// forwarding
		return "resp/goHome0202";
	}

	// 요청 URI : /resp/goHome0203
	// 반환값이 "redirect:"로 시작하면 리다이렉트 방식(URI 재요청)으로 처리함
	@RequestMapping(value = "/goHome0203", method = RequestMethod.GET)
	public String home0203() {
		log.info("goHome0203에 왔다");

		// redirect:요청 URI
		return "redirect:/resp/goHome0202";

		// uri주소는 0203이지만 0202로 요청했기 때문에 0202가뜸?
		// 로그는 0203에 왔다가 뜨고 바로 아래에 0202에 왔다가 가 뜬 후 디버그 로그 찍히기 시작?
	}

	// 요청 URI : /resp/goHome0205
	// "/"로 시작하면 웹 애플리케이션의 컨텍스트 경로에 영향을 받지 않는 절대경로를 의미함
	@RequestMapping(value = "/goHome0205", method = RequestMethod.GET)
	public String home0205() {
		log.info("gohome0205에 왔다");

		// forwarding
		return "/resp/goHome0205";
		// 여기서 맨앞의 / 는 resp이다?
		// 절대경로를 이용하면 화면이 깨진다?
	}

	// 요청 URI : /resp/goHome0206
	// "/"로 시작하면 웹 애플리케이션의 컨텍스트 경로에 영향을 받지 않는 절대경로를 의미함
	@RequestMapping(value = "/goHome0206", method = RequestMethod.GET)
	public String home0206() {
		log.info("gohome0206에 왔다");

		// forwarding
		return "/resp/goHome0206";
		// 여기서 맨앞의 / 는 resp이다?
		// 절대경로를 이용하면 화면이 깨진다?
	}

	/*
	 * 3. 자바빈즈 클래스 타입 JSON 객체 타입의 데이터를 만들어서 반환하는 용도로 사용함 jsjqProj > ch14ans폴더
	 */
	// 요청 URI : /resp/goHome0301
	// 골뱅이 responseBody를 지정하지 않으면 404 오류 발생
	// 반환값이 객체 타입이면 JSON타입으로 자동으로 변환됨
	@ResponseBody
	@RequestMapping(value = "/goHome0301", method = RequestMethod.GET)
	public MemberVO goHome0301() {
		log.info("goHome0301");

		MemberVO memberVO = new MemberVO();
		memberVO.setMemId("a001");
		memberVO.setMemName("개똥이");

		// 뷰 설정@ResponseBody? 없음.. ->404가 뜸
		return memberVO;
	}

	// 요청URI : /resp/goHome0302?memId=a001
	// 반환값을 MemberVO타입으로하여 JSON타입으로 자동 변환해 보자 ->@ResponseBody
	@ResponseBody
	@RequestMapping(value = "/goHome0302", method = RequestMethod.GET)
	public MemberVO goHome0302(@ModelAttribute MemberVO memberVO) {
		// 실행 전 : {memId=null, memName-null..
		memberVO = this.memberService.detail(memberVO);
		// 실행 후 : {memId=a001, memName-김은대..

		return memberVO;
	}

	/**
	 * 4. 컬렉션 List타입
	 * JSON 객체 배열 타입의 데이터를 만들어서 반환하는 용도로 사용함
	 */
	//반환값이 컬렉션 List타입이면 JSON 객체 배열 타입으로 자동으로 변환함
	//요청URI : /resp/goHome0401
	@ResponseBody
	@RequestMapping(value = "/goHome0401", method = RequestMethod.GET)
	public List<MemberVO> goHome0401(MemberVO memberVO){
		log.info("goHome0401에 왔다");
		
		List<MemberVO> memberVOList = new ArrayList<MemberVO>();
		
		memberVO = new MemberVO();
		
		//a001회원정보
		memberVO.setMemId("a001");
		memberVO = this.memberService.detail(memberVO);
		memberVOList.add(memberVO);
		//b001회원정보
		memberVO.setMemId("b001");
		memberVO = this.memberService.detail(memberVO);
		memberVOList.add(memberVO);
		//c001회원정보
		memberVO.setMemId("c001");
		memberVO = this.memberService.detail(memberVO);
		memberVOList.add(memberVO);
		
		return memberVOList;
	}

	// 반환값이 컬렉션 List타입이면 JSON 객체 배열 타입으로 자동으로 변환함
	// 요청URI : /resp/goHome0402
	// 모든 회원 목록을 JSON 배열 타입으로 자동 변환 해보자
	// /member/list 참고
	@ResponseBody
	@RequestMapping(value = "/goHome0402", method = RequestMethod.GET)
	public List<MemberVO> goHome0402(
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cond", required = false, defaultValue = "") String cond,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		
		Map<String, String> map = new HashMap<String, String>();

		// 전체 글 수 구하기
		int total = this.memberService.getTotal(map);

		map.put("size", String.valueOf(total));
		map.put("currentPage", Integer.toString(currentPage));
		map.put("cond", cond);
		map.put("keyword", keyword);
		
		log.info("map :" + map);

		List<MemberVO> memberVOList = this.memberService.list(map);
		log.info("memberVOList : " + memberVOList);

		return memberVOList;
				
	}
	
	/**
	 * 5. 컬렉션 Map 타입
	 * Map 형태의 컬렉션 자료를 JSON 객체 타입의 데이터로 만들어서 반환하는 용도로 사용함
	 */
	
	//반환값이 컬렉션 Map 타입이면 JSON 객체 타입으로 자동으로 변환함
	@ResponseBody
	@RequestMapping(value = "/goHome0501", method=RequestMethod.GET)
	public Map<String, String>goHome0501(){
		log.info("goHome0501에 왔다");
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("apple", "사과");
		map.put("orange", "오렌지");
		map.put("banana", "바나나");
		
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "goHome0502", method=RequestMethod.GET)
	public List<Map<String,MemberVO>> goHome0502(){
		log.info("goHome0502에 왔다");
		
		List<Map<String,MemberVO>> mapList = new ArrayList<Map<String,MemberVO>>();
		
		Map<String,MemberVO> map = new HashMap<String, MemberVO>();
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId("a001");
		map.put("banjang", this.memberService.detail(memberVO));
		mapList.add(map);
		
		map = new HashMap<String, MemberVO>();
		memberVO = new MemberVO();
		memberVO.setMemId("b001");
		map.put("chongmu", this.memberService.detail(memberVO));
		mapList.add(map);
		
		return mapList;
		
	}
	
	/**
	 * ResponseEntity<Void> 타입
	 * response 할 때 Http 헤더 정보와 내용을 가공하는 용도로 사용함
	 * 요청 URI : /resp/goHome0601
	 * 응답 : 응답되는 내용은 없고 헤더정보(HttpStatus.OK)만 응답됨
	 */
	//제이슨으로 변환된 데이터????확인해보기
	@ResponseBody
	@RequestMapping(value = "/goHome0601", method=RequestMethod.GET) 
	public ResponseEntity<Void> goHome0601(){
		log.info("goHome0601");
		
		//http상태 200(성공)
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * 7.ResponseEntity<String>타입
	 * response 될 때 1)Http 헤더 정보와 2)문자열 데이터를 전달하는 용도로 사용함
	 * ResponseBody 가 붙은 파라미터에는 HTTP 요청의 분문 body 부분이 그대로 전달된다
	 */
	@ResponseBody
	@RequestMapping(value = "/goHome0701", method=RequestMethod.GET)
	public ResponseEntity<String> goHome0701(){
		log.info("goHome0701");
		
		//HttpStatus.OK : Http 헤더 정보
		//"SUCCESS"  : 문자열 데이터
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	
	/**
	 * 11.ResponseEntity<byte[]>타입
	 * response 할 때 Http 헤더 정보와 바이너리 파일데이터를 전달하는 용도로 사용함
	 * @throws IOException 
	 */
	//http://localhost/resp/goHome1101
	@ResponseBody
	@RequestMapping("/goHome1101")
	public ResponseEntity<byte[]> goHome1101() throws IOException{
		log.info("goHome1101");
		//파일을 읽음
		InputStream in = null;
		//리턴(파일 + Http헤더 정보)
		ResponseEntity<byte[]> entity = null;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			//sample.jpg 이미지를 읽음
			in = new FileInputStream("C:\\upload\\sample.jpg");
			//헤더 정보에 파일의 미디어 타입을 세팅(jpg파일)
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in),headers, HttpStatus.CREATED
					);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/goHome1102", method=RequestMethod.GET)
	public ResponseEntity<byte[]> gohome1102() throws IOException{
		log.info("goHome1102");
		
		//0011101011100011010101011
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		
//		String fileName="sample.jpg";
		String fileName="upload.zip";
		
		try {
			in = new FileInputStream("C:\\upload\\" + fileName);
			//OCTET : 8bit로 자름
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename=\"" + 
					new String(fileName.getBytes("UTF-8"),"ISO-8859-1") + "\"");
			entity= new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers,HttpStatus.CREATED);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
		
		////////////////컨트롤러 응답 처리 끝//////////////////////
	}
	
	
	
	
}
