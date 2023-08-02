package kr.or.ddit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesView;


///springProj/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml 위치의 이 파일이
//<context:component-scan base-package="kr.or.ddit" /> 여기에서 어노테이션을 찾으면 메모리에 올림
//컨트롤러 어노테이션
//스프링이 브라우저의 요청(request)을 받아들이는 컨트롤러라고 인지하여 
//자바빈으로 등록해서(메모리에 로딩) 관리


@Controller
public class BookController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	/*
	 스프링 프레임워크는 직접 클래스를 생성하는 것을 지양하고 인터페이시를 호출하는 것을 지향한다.
	 인터페이스를 통해 접근하는 것을 권장하고 있기 때문임(확장성/다향성)
	 */
	//호출하기 위해 의존성 주입(DI) - new를 쓰지 않고
	@Autowired
	BookService bookService;
	
	
	//요청 URI : /create 
	//요청방식 : get
	//요청과 메소드를 매핑
	///create한 이름의 겟방식이 온다?
	@RequestMapping(value = "/create",method = RequestMethod.GET)
	public ModelAndView create() {
		/*
		 ModelAndView(클래스?)
		 1) Model : controller가 반환할 데이터(String, int, List, Mat, VO..)를 담당 
		 2) View : 화면을 담당(뷰 (view : jsp)의 경로)
		 */
		
		ModelAndView mav = new ModelAndView();
		
		//<beans:property name="prefix" value="/WEB-INF/views/" />
		//<beans:property name="suffix" value=".jsp" />
		//WEB-INF/View / +book/create + .jsp //폴더경로
		//forwarding
		mav.setViewName("book/create");
		
		return mav;
	}
	
	/**
	 요청 URI : /create 
	요청방식 : post (컨드롤러에서는 겟, 여기에서는 포스트)
	요청 파라미터(HTTP 파라미터) : 서버로 전달되는 키:값 {title:개똥이의 모험, category:소설, price:12000}
	
	스프링에서는 파라미터를 매개변수 형식으로 받을 수 있음. 파라미터는 String 타입이고, 숫자형 문자일 경우 int 타입으로 받을 수 있음(자동형변환?)
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(String title, String category, int price, 
			@ModelAttribute BookVO bookVO,
			ModelAndView mav) {
		System.out.println("title : " + title);
		System.out.println("category : " + category);
		System.out.println("price : " + price);
		//실행전 bookVO : BookVO [bookId=0, title=111, category=222, price=333, insertDate=null]
		System.out.println("bookVO : " + bookVO);
		
		int result = this.bookService.insert(bookVO);
		//실행후bookVO : BookVO [bookId=2, title=111, category=222, price=333, insertDate=null]
		System.out.println("result : " + result);
		
		
		log.info("bookVO(후) : " + bookVO);
		
		if(result<1) {//등록실패
			//create.jsp로 포워딩
			mav.setViewName("book/create");
		}else { //등록성공
			//redirect
			mav.setViewName("redirect:/detail?bookId=" + bookVO.getBookId());
		}
		
		return mav;
	}
	
	/**
	 * 요청 URI : /test
		요청방식 : get
		test.jsp를 포워딩
	 */
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public ModelAndView test(ModelAndView mav) {
		//WEB-INF/views/+book/test +.jsp
		mav.setViewName("book/test");
		return mav;
	}
	
	/**
	 * 요청URI : /test
		요청방식 : post
		console에 {username:a001, password:1234}를 받아서 sysout으로 출력
		test.jsp를 포워딩
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ModelAndView createPost(String username, String password, ModelAndView mav) {
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		
		//create.jsp로 포워딩
		mav.setViewName("book/test");
		
		return mav;
	}
	
	/**
	 요청URI : detail?bookId=10
	 요청URI : detail?1
	 요청파라미터 : bookId=10
	 */
//	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@RequestMapping(value = "/detail/{bookId}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable(value = "bookId", required=true) String bookId,
			BookVO bookVO,
			ModelAndView mav) {
		//BookVO [bookId=10, title=null, category=null, price=0, insertDate=null]
		log.info("BookVO : " + bookVO);
		
		//BookVO [bookId=10, title=null, category=null, price=0, insertDate=null]
		BookVO data = this.bookService.detail(bookVO);
		//BookVO [bookId=10, title=더글로리, category=드라마, price=12000, insertDate=2023-04-12]
		
		
		log.info("data : " + data);
		//model : 데이터를 jsp로 넘겨줌
		mav.addObject("data", data);
		mav.addObject("bookId", data.getBookId());
		
		
		
		//jsp경로
		//forwarding : jsp를 리턴 + 데이터도 함께 담아서 감
		//vs redirect : 반면에 redirect는 요청 URI를 재요청. 데이터를 못담음
		// /WEB-INF/views/ + book/detail + .jsp
		mav.setViewName("book/detail");
		
		return mav;
	}
	
	//요청 URI : /update?bookId=10
	//요청 파라미터 : bookId=10
	//update(int bookId)
	//public ModelAndView update(int bookId) { 이렇게도 받을 수 있음
	//update(@RequestParam int bookId)
	//bookId=10을 받으면 10은 "10" / String인데 자동으로 형변환됨
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@ModelAttribute BookVO bookVO, ModelAndView mav) {
		//책 변경 화면 => 책 입력 화면 + 책 상세 화면
		//책 입력 화면 형식을 그대로 따라가고 빈칸을 데이터로 채워주면 됨
		log.info("bookVO : " +bookVO);
		
		//BookVO [bookId=10, title=null, category=null, price=0, insertDate=null]
		BookVO data = this.bookService.detail(bookVO);
		//BookVO [bookId=10, title=더글로리, category=드라마, price=12000, insertDate=2023-04-12]
		
		
		log.info("data : " + data);
		//model : 데이터를 jsp로 넘겨줌
		mav.addObject("data", data);
		
		
		//forwarding
		// /WEB-INF/views/ +book/update + .jsp
		mav.setViewName("book/update");
		
		return mav;
	}
	
	
	/**
	 요청 URI : /updatePost 
	 요청 파라미터 : {bookId:4, title=뚜연이의, category=여행, price=50000}
	 요청방식 : post
	 */
	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute BookVO bookVO, ModelAndView mav) {
		log.info("bookVO : " + bookVO);
		
		//서비스(기능) 로직 호출
		//result : 몇 행이 update 처리 되었나
		int result = this.bookService.updatePost(bookVO);
		log.info("result : " + result);
		
		//변경 성공 : 책 상세페이지(/detail?bookId=4)로 이동
		if(result >0) {
			//redirect : 요청URI 재요청
			mav.setViewName("redirect:/detail?bookId="+ bookVO.getBookId());
		}else {
			//변경 실패 : 변경페이지(/update?bookId=4)로 이동
			mav.setViewName("redirect:/update?bookId=" + bookVO.getBookId());
		}
		
		return mav;
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////
	/**
	 요청 URI : /deletePost 
	 요청 파라미터 : {bookId:4, title=뚜연이의, category=여행, price=50000}
	 요청방식 : post
	 */
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public ModelAndView deletePost(@ModelAttribute BookVO bookVO, ModelAndView mav) {
		log.info("bookVO : " + bookVO);
		
		//서비스(기능) 로직 호출
		//result : 몇 행이 delete 처리 되었나
		int result = this.bookService.deletePost(bookVO);
		log.info("result : " + result);
		
		//변경 성공 : 책 상세페이지(/detail?bookId=4)로 이동
		if(result >0) {
			//redirect : 요청URI 재요청
			mav.setViewName("redirect:/list");
		}else {
			//변경 실패 : 변경페이지(/update?bookId=4)로 이동
			mav.setViewName("redirect:/detail?bookId="+ bookVO.getBookId());
		}
		
		return mav;
		
	}
	
	/**
	 요청 URI : /list     <= required=false(선택 : keyword가 없을 수 도 있음)
	 	      /list?keyword=글로리
	 		  /list?keyword=
	 요청방식 : get
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView mav, @RequestParam(value="keyword", required = false) String keyword) {
		
		//vo리스트가 넘어옴
		List<BookVO> data = this.bookService.list(keyword);
		//data : [BookVO [bookId=2, title=대항해시대, category=여행, price=32000, insertDate=Wed Apr 12 14:25:27 KST 2023], 
		//		  BookVO [bookId=4, title=모범택시를 탄 뚜연이, category=드라마, price=12000, insertDate=Tue Apr 11 16:23:16 KST 2023], 
		//		  BookVO [bookId=3, title=뚜연이의모험, category=소설, price=10000, insertDate=Tue Apr 11 16:22:56 KST 2023], 
		//		  BookVO [bookId=1, title=개똥이 모험, category=소설, price=10000, insertDate=Tue Apr 11 14:16:16 KST 2023]]
		//여러개가 한번에 들어옴 <- volist임?
		log.info("data : "+data);
		
		//select 결과 목록을 데이터로 넣어줌. jsp로 리턴
		mav.addObject("data",data);
		
		//forwarding
		// /WEB-INF /views/ + book/list + .jsp
		mav.setViewName("book/list");
		
		return mav;
		
	}
	
	
	
}
