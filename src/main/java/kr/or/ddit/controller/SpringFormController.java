package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.GeneralElementsVO;
import kr.or.ddit.vo.MemberVO;

//자바빈 객체로 등록
@RequestMapping("/springform")
@Controller
public class SpringFormController {
	
	private static final Logger log = LoggerFactory.getLogger(SpringFormController.class);
	
	@Autowired
	MemberService memberService;
	
	// 요청URI : /springform/registerForm01
	// 요청방식 : get
	@GetMapping("/registerForm01")
	public String registerForm01(Model model) {

		MemberVO memberVO = new MemberVO();
		memberVO.setMemId("a001");
		memberVO.setMemName("개똥이");

		// 속성명에 memberVO를 지정하고, 폼객체를 모델에 추가
		// modelAttribute 속성값 : <form:form modelAttribute="memberVO" .jsp파일 상단
		// 이 폼객체의 속성명 : memberVO(o) //userVO(x)
		// 와 일치하지 않으면 오류가 발생함
		//"속성값"은 일치해야하고 뒤에 변수명은 달라도상관없음
		model.addAttribute("memberVO", memberVO);

		// forwarding
		return "springform/registerForm01";
	}
	
	
	
	
	//=================================================================================================================

	// 요청URI : /springform/registerForm02
	// 요청방식 : get
	// MemberVO memberVO : 폼객체
	// Model 인터페이스를 통하지 않고도 컨트롤러가 forwarding에서 폼 객체는 jsp로 전달해줌
	@GetMapping("/registerForm02")
	public String registerForm02(MemberVO memberVO) {
		
		// MemberVO memberVO = new MemberVO();를 지우고 위의 매개변수로 올림 & Model model이 사라짐
		memberVO.setMemId("a001");
		memberVO.setMemName("개똥이");

		// forwarding
		return "springform/registerForm01";
	}
	
	
	
	//=================================================================================================================
	
	// 요청URI : /springform/registerForm03
	// 요청방식 : get
	// MemberVO memberVO : 폼객체
	//<form:form modelAttribute="memberVO" 
	// memberVO와 user는 다른이름인데 오류가 안남.
	// 폼객체의 속성명(modelAttribute="""memberVO"""")의 memberVO는 
	//매개변수(user)로 전달된(jsp로) 자바빈즈 클래스(MemberVO) 타입명(MemberVO)을 이용하여 만듦
	//		폼객체의 속성명은 직접지정(골뱅이 @ModelAttribute("memberVO"))하지 않으면
	// 		폼 객체의 클래스명의 맨처음 문자를 소문자로 변환하여 처리함(스프링 폼태그 자체 규칙)
	
	//		폼 객체의 속성명(@ModelAttribute("memberVO"))은 직접 지정하지 않으면 폼 객체의 클래스명의 맨 처음 문자를 소문자로 변환하여 처리함
	//@ModelAttribute("memberVO")이것을 생략하면 직접지정을 하지 않았기에 user가 아닌 MemberVO의 첫글자를 소문자로 바꾼 결과에 대입하여 가져옴?
	@GetMapping("/registerForm03")
	public String registerForm03(MemberVO user) {
	//public String registerForm03(@ModelAttribute("memberVO") MemberVO user) {
		
		
		user.setMemId("a001");
		user.setMemName("개똥이");
		
		// forwarding
		return "springform/registerForm01";
	}
	
	
	//=================================================================================================================
	
	
	
	//이방법을 가장 많이 사용함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!두둥!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// 요청URI : /springform/registerForm04
	// 요청방식 : get
	// MemberVO memberVO : 폼객체
	//<form:form modelAttribute="memberVO" 
	// 폼객체의 속성명(modelAttribute="memberVO")
	// 골뱅이 ModelAttribute 애너테이션으로 폼객체의 속성명 (modelAttribute="memberVO")을 직접 지정할 수 있음
	@GetMapping("/registerForm04")
	public String registerForm04(@ModelAttribute("memberVO") MemberVO memberVO
			, Model model) {
		
		//폼 객체의 프로퍼티(멤버변수, 속성)에 값을 지정함
		memberVO.setMemId("a001");
		memberVO.setMemName("뚜연이");
		//form:password의 경우 controller에서 값을 설정하여 뷰에 전달하더라도
		//패스워드 필드<form:password path="memPass" />에 바인딩 되지 않음
		memberVO.setMemPass("java");
		memberVO.setMemMemorial("<p>뚜연이는 <p/><p>배고팡</p>"); //textarea에서는 \n ,  ckeditor에서는 html형식으로
		
		//영화에 체크박스가 자동으로 처리
		memberVO.setHobby("movie");
		
		//영화 Array에 음악,영화가 자동으로 체크 처리
		String[] hobbyArray = {"music","movie"};
		memberVO.setHobbyArray(hobbyArray);
		
		//영화 List에 스포츠,영화가 자동으로 체크 처리
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("sports");
		hobbyList.add("movie");
		memberVO.setHobbyList(hobbyList);
		
		
		//체크박스 처리(취미) =여러개 선택
		//name값이 값으면 배열로 받을 수 있음
		//<input type = "checkbox" name="memLike" id="memLike1" value="sports"/>스포츠
		//<input type = "checkbox" name="memLike" id="memLike2" value="music"/>음악
		Map<String, String> memLikeMap = new HashMap<String, String>();
		memLikeMap.put("sports", "스포츠");
		memLikeMap.put("music", "음악감상");
		memLikeMap.put("movie", "영화감상");

		
		//성별에 자동 선택 처리
		memberVO.setGender("female");
		
		//라디오 버튼 처리
		// 1)<form:radiobuttons
		// 2)<form:radiobutton
		Map<String, String> genderMap = new HashMap<String, String>();
		genderMap.put("male", "남성");
		genderMap.put("female", "여성");
		genderMap.put("other", "기타");
		
		model.addAttribute("genderMap",genderMap);
		
		
		//셀렉트박스 처리
		//<form:select
		Map<String, String> nationalityMap = new HashMap<String, String>();
		nationalityMap.put("korea", "대한민국");
		nationalityMap.put("germany", "독일");
		nationalityMap.put("austrailia", "오스트레일리아");
		
		model.addAttribute("nationalityMap",nationalityMap);
		
		
		
		//멀티셀렉트박스 처리
		//<form:select... multiple="true"
		Map<String,String> carMap = new HashMap<String, String>();
		carMap.put("qm5", "큐엠파이브");
		carMap.put("benz", "벤츠");
		carMap.put("explorer", "익스플로러");
		
		model.addAttribute("carMap",carMap);
		
		
		//숨겨진 필드 요소에 바인딩
		memberVO.setMemRegno1("991122");
		
		
		
		
		//모델을 통해서 넣어야함
		//memberVO의 멤버변수에 없으므로 model을 통해서 뷰로 전달
		model.addAttribute("memLikeMap",memLikeMap);
		
		
		// forwarding
		return "springform/registerForm01";
	}
	
	//=================================================================================================================
	/*
	 * 
	요청URI : /spring/form/registerForm01Post
	요청 파라미터 : {memId=a001, memName=개똥이, memEmail=test@test.com}
	요청방식 : post
	
	Model model을 사용하지 않고도 폼객체(MemberVO memberVO)를 통해서 jsp로 데이터 전달이 가능하다
	 */
	//Model을 쓰지 않아도 폼객체를 이용하여 데이터 값을 넘길수 있음
	@PostMapping("/registerForm01Post")
	public String registerForm01Post(MemberVO memberVO) {
		
		log.info("memberVO : " + memberVO);
		
		//hobbyArray
		String[] hobbyArray = memberVO.getHobbyArray();
		log.info("hobbyArray.length : " + hobbyArray.length);
		
		if(hobbyArray != null) {
			for(String hobby : hobbyArray) {
				log.info("hobbyArray : " + hobby);
			}
		}
		
		//hobbyList
		List<String> hobbyList = memberVO.getHobbyList();	
		log.info("hobbyList.size() : " + hobbyList.size());
		
		if(hobbyList != null) {
			for(String hobby : hobbyList) {
				log.info("hobbyList : " + hobby);
			}
		}
		
		
		// forwarding
		return "springform/result";
	}
	
	
	
	//요청URI : /spring/form/registerForm05
	//요청방식 : get
	//요청 파라미터 : 
	@GetMapping("/registerForm05")
	public String registerForm05(Model model, 
			@ModelAttribute("generalElementsVO") GeneralElementsVO generalElementsVO) {
		
		Map<String, String> checkboxMap = new HashMap<String, String>();
		checkboxMap.put("apple", "사과");
		checkboxMap.put("banana", "바나나");
		checkboxMap.put("peach", "복숭아");
		model.addAttribute("checkboxMap",checkboxMap);
		
		Map<String, String> selectMap = new HashMap<String, String>();
		selectMap.put("401", "401호");
		selectMap.put("402", "402호");
		selectMap.put("403", "403호");
		selectMap.put("404", "404호");
		selectMap.put("405", "405호");
		model.addAttribute("selectMap",selectMap);
		
		
		Map<String, String> selectDisabledMap = new HashMap<String, String>();
		selectDisabledMap.put("java", "자바");
		selectDisabledMap.put("oracle", "오라클");
		selectDisabledMap.put("html", "HTML");
		selectDisabledMap.put("python", "파이썬");
		selectDisabledMap.put("spring", "스프링");
		model.addAttribute("selectDisabledMap",selectDisabledMap);
		
		
		Map<String, String> selectMultipelMap = new HashMap<String, String>();
		selectMultipelMap.put("car", "자동차");
		selectMultipelMap.put("ship", "배");
		selectMultipelMap.put("airplane", "비행기");
		model.addAttribute("selectMultipelMap",selectMultipelMap);
		
		//LinkedHashMap으로 들어가면 순서대로 나옴
		Map<String, String> selectMultipleDisabledMap = new LinkedHashMap<String, String>();
		selectMultipleDisabledMap.put("lion", "사자");
		selectMultipleDisabledMap.put("tiger", "호랑이");
		selectMultipleDisabledMap.put("cat", "고양이");
		selectMultipleDisabledMap.put("dog", "개");
		model.addAttribute("selectMultipleDisabledMap",selectMultipleDisabledMap);
		
		
		
		//forwarding
		return "springform/registerForm05";
	}
	
	
	//요청URI : "/springform/registerForm05Post" 
	//요청방식 : post
	@PostMapping("/registerForm05Post")
	public String registerForm05Post(@ModelAttribute("generalElementsVO")
			GeneralElementsVO generalElementsVO) {
		
		log.info("GeneralElementsVO",generalElementsVO);
		
		//forward
		return "springform/result";
	}
	
	
	//<form:form modelAttribute="memberVO"
	/*
	 * 입력값 검증
	 * 스프링 MVC는 Bean Validation(유효성 검사) 기능을 이용해
	 * 요청 파라미터({memId=a001, memName=개똥이, memEmail=test@test.com}) 값이 바인딩된
	 * 도메일 클래스의 입력값 검증을 함
	 * (바인딩 : 메모리에 올라간다?)
	 * 
	 * 골뱅이Validated : 입력값 검증을 할 도메인 클래스(MemberVO)에 지정  =활성화시키겠다?
	 */
	@GetMapping("/registerForm06")
	public String registerForm06(@ModelAttribute("memberVO") MemberVO memberVO) {
		
		//foward
		return "springform/registerForm06";
	}

	
	/* BindingResult :바인딩(파라미터가 vo에 setting됨) 후 유효성 검사 결과 오류 정보 확인을 해주는 인터페이스
	 	1. hasErrors() : true(오류 있음, 유효성 검사 실패) / false(오류 없음, 유효성 검사 통과 성공)
	 	2. hasGlobalErrors() : 객체와 관련된 오류가 있는가?
	 	3. hasFieldErrors() : 멤버변수와 관련된 오류가 있는가?
	 	4. hasFieldErrors(String) : 인수(파라미터)에 지정한 필드(멤버변수)에서 오류가 있는가?
	 */
	@PostMapping("/registerForm06Post")
	public String registerForm06Post(
			@Validated @ModelAttribute("memberVO") MemberVO memberVO, BindingResult result) {
		
		//BindingResult으로 검증한 내용이 오류가 있는지 없는지 확인
		//true / false
		log.info("error : " + result.hasErrors());
		//addressVO=AddressVO(memId=null, postCode=63309, location=제주특별자치도 제주시 영평동 2181, detLocation=123))
		//cardVO=[CardVO(userId=null, memId=null, no=111, validMonth=20230611), CardVO(userId=null, memId=null, no=222, validMonth=20230611)]
		log.info("memberVO : "+memberVO);
		
		if(result.hasErrors()) {//true : 오류가 있음. ex)memId가 null일때 (NotBlank유효성 검사에 걸림)
			//심사 결과 오류 확인
			List<ObjectError> allErrors = result.getAllErrors();
			//객체와 관련된 오류
			List<ObjectError> globalErrors =  result.getGlobalErrors();
			//멤버변수와 관련된 오류
			List<FieldError> fieldErrors =  result.getFieldErrors();
			
			for(ObjectError objectError : allErrors) {
				log.error("allErrors : " + objectError);
			}
			
			for(ObjectError objectError : globalErrors) {
				log.error("globalErrors : " + objectError);
			}
			
			for(FieldError fieldError : fieldErrors) {
				log.error("fieldErrors : " + fieldError.getDefaultMessage());
			}
			
			//forwarding(유효성 검사 결과 오류 발생시) //redirect는 안됨(재요청) -> 오류 정보가 사라짐
			return "springform/registerForm06";
			
		}//end if
		
		//forwarding(유효성 검사 결과 통과시)
		int mResult = this.memberService.registerMember(memberVO);
		
		
		
		return "springform/detail06";
	}
	
	
	//요청URI : /springform/checkMemId
	//요청데이터 : let data = {"memId":memId};
	//요청 방식 : post
	@ResponseBody
	@PostMapping("/checkMemId")
	public Map<String, String> checkMemId(@RequestBody MemberVO memberVO) {
		log.info("memberVO : " + memberVO);
		
		//아이디 중복 건수
		int result = this.memberService.checkMemId(memberVO);
		log.info("result : " + result);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", result + "");
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
