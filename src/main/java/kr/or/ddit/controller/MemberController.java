package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.vo.MemberVO;

//프링이한테 "이 클래스는 컨트롤러야"라고 알려줌
//스프링은 자바빈(객체)으로 등록하여 관리해줌 (?서버에서?)
//클래스 레벨에서 요청 매핑 처리
@RequestMapping("/member")
@Controller
public class MemberController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// Di(의존성 주입), IoC(제어의 역전)
	@Autowired
	MemberService memberService;

	/**
	 * 요청URI : /list?size=10&currentPage=1 요청URI : /list => required = false에 의해
	 * 선택으로 처리 요청URI : /list?currentPage=2 => {size=10, currentPage=2} 요청URI :
	 * /list?size=50&cond=memName&keyword=은대 요청방식 : get
	 * 
	 * 요청 파라미터 : size=10일때 10는 "10"(숫자형문자) => int size 매개변수를 통해 자동 형변환이 됨
	 * &currentPage=1 ?요청파라미터가 없을때 @RequestParam(value = "size",required = false,
	 * defaultValue = "10")여기로 들어가서 기본 10으로 사용 이게없으면 변수값이없어서 오류남
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cond", required = false, defaultValue = "") String cond,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		// 파라미터 : hashMap : {size=10, currentPage=1}
		Map<String, String> map = new HashMap<String, String>();
		// String str1 = String.valueOf(num)
		// String str2 = Integer.toString(num)

		map.put("size", String.valueOf(size));
		map.put("currentPage", Integer.toString(currentPage));
		map.put("cond", cond);
		map.put("keyword", keyword);
		// map :{size=50, currentPage=1, cond=memName, keyword=은대}
		// map :{size=50, currentPage=1, cond=, keyword=}
		log.info("map :" + map);

		List<MemberVO> memberVOList = this.memberService.list(map);
		log.info("memberVOList : " + memberVOList);

		// 전체 글 수 구하기
		int total = this.memberService.getTotal(map);
		log.info("total : " + total);

		model.addAttribute("data", new ArticlePage<MemberVO>(total, currentPage, size, memberVOList));

		// forwarding
		// /WEB-INF/views/ + member/list + .jsp
		return "member/list";
	}

	/*
	 * 	회원 상세보기
	 	요청 URI : /member/detail?memId=a001
	 	요청URI(경로 변수 활용) : /member/detail/a001
	 	요청 파라미터 : memId=a001
	 	요청방식 :get
	 	
	 	required = true : 필수
	 */
	//@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@RequestMapping(value = "/detail/{memId}", method = RequestMethod.GET)
	public String detail(@PathVariable (value = "memId", required = true) String memId,
				@ModelAttribute MemberVO memberVO,
				Model model) {
		log.info("memId : " + memId);
		log.info("memberVO : " + memberVO);
		
		//호출 전 : memberVO [memId=a001, memName=null,memRegno1=null...
		//호출 후 : memberVO [memId=a001, memName=이뚜연,memRegno1=111...
		memberVO = this.memberService.detail(memberVO);
		log.info("memberVO(호출후) : " + memberVO);
		
		model.addAttribute("data",memberVO);
		//forwarding
		// /WEB-INF/views/ +member/ detail + /.jsp
		return "member/detail";
	}
	
	
	/**
	 	요청 URI : $(".user").attr("action","/member/updatePost"); 
		요청 방식 : $(".user").attr("method","post");
		요청 파라미터 : MemberVO타입에 맞춰서 옴
	 */
	@RequestMapping(value = "/updatePost", method=RequestMethod.POST)
	public String updatePost(@ModelAttribute MemberVO memberVO) {
		//memberVO가 null일때 memberVO.toString()를 하면 오류 발생
		//log.info("memberVO : "+ memberVO.toString()); //null일때는 tostring 할수 없기 때문에 오류가 남
		log.info("memberVO : "+ memberVO); //그래서 memberVO를 호출 -> memvervo안에들어있는 tostring사용
		
		int result = this.memberService.updatePost(memberVO);
		log.info("result : " + result);
		
		
		//redirect
		//변경 후 상세 페이지로 재요청
		return "redirect:/member/detail?memId="+memberVO.getMemId();
	}
	
	
	/**
		요청 URI : $(".user").attr("action","/member/deletePost"); 
		요청 방식 : $(".user").attr("method","post"); 
		요청 파라미터 : MemberVO타입에 맞추어 전달됨
	 */
	@RequestMapping(value = "/deletePost", method=RequestMethod.POST)
	public String deletePost(@ModelAttribute MemberVO memberVO) {
		log.info("memberVO(deletePost) : " + memberVO);
		
		int result = this.memberService.deletePost(memberVO);
		log.info("result(deletePost : " + result);
		
		//삭제가 되었으므로 목록으로 이동
		//redirect
		return "redirect:/member/list";
	}
	
	//요청URI : /member/detailMember
	//요청데이터 : let data = {"memId":memId};
	//요청방식 : post
	@ResponseBody
	@PostMapping("/detailMember")
	public MemberVO detailMember(@RequestBody MemberVO memberVO) {
		log.info("memberVO(bef) : " +memberVO);
		
		memberVO = this.memberService.detailMember(memberVO);
		log.info("memberVO(aft) : " +memberVO);
		return memberVO;
	}
	
	
}
