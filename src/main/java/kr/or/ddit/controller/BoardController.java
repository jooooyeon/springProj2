package kr.or.ddit.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.vo.BoardVO;


/*
 	요청(Request) 경로 매핑(Mapping)
 	- 요청 경로(URI)는 반드시 설정해야 하는 필수 정보
 	- 속성이 하나일 때는 속성명을 생략할 수 있음
 	- 컨트롤러의 클래스 레벨과 메서드 레벨로 지정할 수 있음
 	- 클래스 레벨 요청 경로를 지정하면 메서드 레벨에서 지정한 경로의 기본 경로로 취급됨
 	- 클래스 레벨의 요청 경로에 메서드 레벨의 요청 경로를 덧붙인 형태가 최종 경로가 됨
 */

//@RequestMapping(value = "/board") -> 속성이 하나일때는 생략 가능
//@Controller자바빈으로 등록해서(메모리에 로딩) 관리
//클래스레벨
@RequestMapping("/board")
@Controller
public class BoardController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//요청 URI : /board/list
	//PreAuthorize : 특정 메소드를 실행하기 전에 role(권한) 체킹
   	//PostAuthorize : 특정 메소드를 실행한 후에 role(권한) 체킹
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@GetMapping("/list")
	public String list() {
		//forwarding
		return "board/list";
	}
	
	
	//요청 URI : /board/register
	//메서드 레벨
	@PreAuthorize("hasRole('ROLE_MEMBER') and hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/register")
	public void registerForm() {
		log.info("registerForm");
		
		// /WEB-INF/views/board/register.jsp로 forwarding
		//return "board/register";
	}
	
	/**
	 2. 경로 패턴 매핑
	 요청경로 (URI)를 동적으로 표현이 가능한 경로 패턴을 지정할 수 있음
	 - URL경로 상의 변하는 값을 경로 변수로 취급
	 - 경로 변수에 해당하는 값을 파라미터 변수에 설정할 수 있음
	 */
	
	/*
	 	요청 URI : /board/detail/{boardNo}
	 	요청 URI : /board/detail/3
	 	boardNo : 경로 변수
	 */
	//속성하나이기때문에 밸류 생략가능
	@RequestMapping("/detail/{boardNo}")
	public String detail(@PathVariable("boardNo") String boardNo) {
		log.info("boardNo : " + boardNo);
		//forwarding
		return "board/read";
	}
	
	
	/*
	 	요청 URI : /board/read?boardNo=3
	 */
	//속성하나이기때문에 밸류 생략가능
	@RequestMapping("/read")
	public String read(@RequestParam(value="boardNo",required=false) String boardNo) {
		//forwarding
		return "board/read";
	}
	
	/*
	 	Http 매서드 매핑
	 	- method 속성을 사용하여 HTTP메서드를 매핑 조건으로 지정할 수 있음
	 	- HTTP메소드로 GET 방식과 POST 방식 두가지를 사용 할 수 있음
	 */
	//요청 URI: /board/register
	//요청 방식 : get
	@PreAuthorize("hasRole('ROLE_MEMBER') and hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String register() {
		//forwarding
		return "board/register";
	}
	
	//요청 URI: /board/register
	//요청 방식 : post
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String registerPost() {
		//forwarding
		return "board/register";
	}
	
	/**
	 * 4. Params 매핑
	 * 요청 파라미터를 매핑 조건으로 지정하는 경우에는 params속성을 사용함
	 * 버튼이나 링크에 따라 호출할 매서드를 바꿔야 할 때 사용함
	 */
	//요청 URI : /board/get?register
	//요청방식(HTTP 매소드 : get
	@RequestMapping(value = "/get", method = RequestMethod.GET,
			params="register")
	public String register2() {
		//forwarding
		return "board/register";
	}
	
	/*
	요청 URI : /board/post
			  /board.post?register
	요청 방식 : post
	params : register
	 */
	@RequestMapping(value = "/post", method = RequestMethod.POST, params="register")
	public String register3() {
		//forwarding
		return "board/register";
	}
	
	/*
	요청 URI : /board/get
	요청방식 : get
	params : modify
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET, params="modify")
	public String register5() {
		//forwarding
		return "board/register";
	}
	
	/*
	요청 URI : /board/get?list
	요청방식(HTTP메소드) : get
	params : list
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET, params="list")
	public String register6() {
		//forwarding
		return "board/register";
	}
	/*
	요청 URI : /board/get?read
	요청방식(HTTP메소드) : get
	params : read
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET, params="read")
	public String register7() {
		//forwarding
		return "board/register";
	}
	
	//요청URI : /board/3  <-3은 변하는 값
	//요청방식 : post
	//요청파라미터(Json) : {"boardNo":"4","title":"4","content":"4","writer":"4"}//제이스일때는 @RequestBody
	//consumes 속성값에 "application/json"미디어 타입을 지정함
	@RequestMapping(value = "/{boardNo}",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> modify2(@PathVariable("boardNo") int boardNo, 
		@RequestBody BoardVO boardVO){
		//boardNo : 1
		log.info("boardNo : "+boardNo);
		// boardVO : BoardVO [boardNo=1, title=2, content=3, writer=4]
		log.info("boardVO : "+boardVO);
		
		//헤더로 보냄
		//HttpStatus.OK : 200(성공)
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		
		return entity;
	}
	
	//요청URI : /board/1
	//요청 방식 : get
	//응답 본문의 미디어 타입을 지정하지 않으면(consumes="application/json"을 생략하면 기본값인 "application/json"으로 자동 저장됨
	@RequestMapping(value = "/{boardNo}",method = RequestMethod.GET)
	public ResponseEntity<BoardVO> modify3
	(@PathVariable("boardNo") int boardNo){
		log.info("boardNo : "+ boardNo);
		BoardVO boardVO = new BoardVO();
		
		boardVO.setBoardNo(boardNo);
		boardVO.setTitle("뚜연이의 모험");
		boardVO.setContents("뚜연 출발");
		boardVO.setWriter("자까");
		
		ResponseEntity<BoardVO> entity = new ResponseEntity<BoardVO>(boardVO, HttpStatus.OK);
		
		return entity;
	}
	
}

