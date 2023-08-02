package kr.or.ddit.controller;

import java.util.Date;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.ReqService;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO2;

@RequestMapping("/req")
@Controller
public class ReqController {
	//DI, IoC
	@Autowired
	ReqService reqService;
	
	//포스파이어
	private static final Logger log = LoggerFactory.getLogger(ReqController.class);
	/**
	 * 5-2. 컨트롤러 요청 처리
	 * URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있음
	 */
	//URI : /req/register?userId=a001&password=java
	//URL : /req/register
	//요청방식 : get
	//요청파라미터(QueryString) : userId=a001&password=java
	
	//@GetMapping는 method=RequsetMethod.GET과 같음
	//요청 파라미터를 매개변수로써 받을 수 있음
	//http://localhost/req/register?userId=a001&password=java
	@ResponseBody
	@GetMapping("/register")
	public String register(String userId, String password) {
		log.info("userId : " + userId + ", password : " + password );
		
		
		return "success";
	}
	
	/**
	 * URL 경로 상의 경로(path) 변수(variable)로 부터 요청 데이터를 취득할 수 있음
	 */
	//요청 URI : /req/register/c001
	//경로변수 대상 : /c001
	//자바 소스를 컴파일할 때 =g 옵션(디버깅 정보를 출력하는 모드)을 사용해야 매개 변수명을 인식할 수 있음
	//만약에 그럴 수 없다면 value(name)속성을 지정한 애너테이션(@RequestParam,@PathVariable)을 사용해야 함
	@ResponseBody
	@GetMapping(value="/register/{userId}")
	public String registerByPath(@PathVariable(value = "userId")String userId) {
		log.info("userId : " +userId);
		
		return userId;
	}
	
	//HTML 폼 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있음
	//요청 URI : /req/register01
	@GetMapping("/register01")
	public String register01() {
		log.info("register01");
		
		//forwarding
		return "req/register01";
	}
	
	/*
	 * 요청 URI : /req/register01Post
	 	요청 파라미터 : {userId=gaeddongi, password=java, coin=100 
	 	요청방식 : post
	 */
	//HTML 폼 필드가 여러개일 경우 컨트롤러 매개변수 위치는 상관없음
	//HTML 폼 필드의 값이 숫자일 경우, 컨트롤러 매개 변수 타입이 숫자형이면 숫자로 타입 변화하여 요청 데이터를 취득함
	@ResponseBody
	@PostMapping("/register01Post")
	public String register01Post(@RequestParam("userId") String userId,int coin, String password) {
		log.info("userId : " + userId);
		log.info("password : " + password);
		log.info("coin : " + coin);
		
		return "success";
	}
	
	/**
	 * 3. 요청 데이터 처리 애너테이션
	 *  @PathVariable : URL에서 경로 변수 값을 가져오기 위함
	 *  @RequestParam : 요청 파라미터 값을 가져오기 위함
	 *  @RequestHeader : 요청 헤더 값을 가져오기 위함
	 *  @RequestBody : JSON 데이터를 가져오기 위함
	 *  @CookieValue : 쿠키 값을 가져오기 위함
	 */
	
	//URL 경로 상의 경로(Path) 변수(Variable)로 부터 요청 데이터를 취득할 수 있음
	//요청 URI : /req/register/gaeddongi
	@ResponseBody
	@GetMapping("/register2/{userId}")
	public String registerByPath2(@PathVariable (value ="userId") String userId) {
		log.info("registerByPath2");
		
		log.info("userId : " +userId);
		
		return "success";
	}
	
	/*
	 * 4. 요청 처리 자바빈즈
	 * 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리함
	 */
	//요청 URI : /req/register01Post2
	//요청 파라미터 : {userId=gaeddongi, password=java, coin=100 }
	@ResponseBody
	@PostMapping("/register01Post2")
	public String register01Post2(MemberVO2 memberVO2) {
		//							MemberVO2자바빈즈 매개변수임
		
		//[userId=gaeddongi, password=java, coin=100]
		log.info("memberVO2 : " + memberVO2);
		
		return "success";
	}		
	
	/*
	 * 요청 URI : /req/register02Post
	 	요청 파라미터 : {userId=gaeddongi, dateOfBirth=2023-05-27 
	 	요청방식 : post
	 */
	//요청 파라미터(dateOfBirth=0527)로 전달받은 값(dateOfBirth=0527),(dateOfBirth=2023-05-27 ),(dateOfBirth=20230527 ) 이
	//  날짜 문자열 형식에 맞지 않아 Date타입으로 변환 실패
	//(dateOfBirth=2023/05/27)은 날짜 문자열 형식에 맞아 Date타입으로 변환 성공 (나머지 불가)
	@ResponseBody
	@PostMapping("/register02Post")
	public String register02Post(String userId, Date dateOfBirth) {
		//이것도 사용가능 대신에 jsp에서 <p>dateOfBirth : <input type="date" name="dateOfBirth" value="2023-05-27" /></p>
		//타입을 데이트로 사용해야 함& 밸류도 -로 사용?
		//public String register02Post2(String userId,@DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth) {
		log.info("register02Post에 왔다");
		
		log.info("userId:" + userId + ", dateOfBirth : " + dateOfBirth);
		
		return "sucess";
	}
	/*
	 * 요청 URI : /req/register02Post
	 	요청 파라미터 : {userId=gaeddongi, dateOfBirth=2023-04-26
	 	요청방식 : post
	 */
	
	@ResponseBody
	@PostMapping("/register02Post2")
	public String register02Post2(MemberVO2 memberVO2, String nationality) {
		log.info("register02Post2에 왔다");
		
		//MemberVO2 [userId=gaeddongi, password=null, 
		//coin=0, dateOfBirth=Wed Apr 26 00:00:00 KST 2023
		
		//cars=volvo,saab,opel,audi
		//스트링으로 받으면 쉼표로 구별된다
		
		//foodArray=[stake, jjajang, rice]
		//String[]로 받으면 배열로 구별된다
		log.info("memberVO2 :" + memberVO2);
		//nationality=Germany
		log.info("nationality :" + nationality);
		
		if(memberVO2.getCars() !=null) {
			String[] carsArr = memberVO2.getCars().split(","); //volvo,opel,audi
			
			for(String car : carsArr) {
				log.info("car : " + car);
			}
		}
		if(memberVO2.getFoodArray()!=null) {
			
			String[] foodArray = memberVO2.getFoodArray();//[stake, jjajang, rice]
			
			for(String food : foodArray) {
				log.info("food : " + food);
			}
		}
		
		
		return "success";
	}
	@GetMapping("/register03")
	public String register03() {
		log.info("register03에 왔다");
		
		//forwarding
		return "req/register03";
	}
	
/*
 http://localhost/req/register03
요청 URI : /reg/register03Post
요청 파라미터 : {address.postCode=12345, address.location= 대전 동구 용운동, address.detLocation=123-5}


	<p>카드1-번호 : <input type="text" name="cardList[0].no" value="1111-111-11111"/></p>
	<p>카드1-유효년월 : <input type="text" name="cardList[0].validMonth"  value="01/25"/></p>
	<p>카드2-번호 : <input type="text" name="cardList[1].no" value="2222-222-22222"/></p>
	<p>카드2-유효년월 : <input type="text" name="cardList[1].validMonth" value="06/25"/></p>
	<p>카드3-번호 : <input type="text" name="cardList[2].no" value="3333-333-33333"/></p>
	<p>카드3-유효년월 : <input type="text" name="cardList[2].validMonth" value="01/27"/></p>
	
	정리 : MemberVO2 (자바빈클래스) String 타입 memId(멤버변수)1:1, 
		Bital타입의 bital(멤버변수)(중첩된 자바빈즈)memId라는 기본키가 들어가야함 1:1, 
		List<CardVO>타입의 cardList(멤버변수)(중첩된 자바빈즈) memId라는 기본키가 들어가야함 1:n
	jsp => <input type="text" name="memId"(멤버변수),/ name="bital.height" / name="cardList[인덱스번호]"
	 =>name에 멤버변수가 그대로 들어가야함
 */
	@ResponseBody
	@PostMapping("/register03Post")
	public String register03Post(MemberVO2 memberVO2) {
//		log.info("memberVO2 : "+memberVO2);
	
		
		//memberVO2 : MemberVO2 [userId=gaeddongi, password=java, coin=100, dateOfBirth=Wed Apr 26 00:00:00 KST 2023, 
		//cars=genesis, foodArray=[stake], nationality=Korea, job=[developer], hobby=[music], 
		//address=Address [userId=null, postCode=12345, location=대전 중구 대흥동, detLocation=123-5], 
		//bital=Bital [height=185.0, weight=80.0, sight=1.5, pressure=100.0, bloodType=B]]
		//더블이라 정수입력시 뒤에 .0이 추가됨
		//cardList=[
		//CardVO [userId = gaeddongi,no=1111-111-11111, validMonth=01/25], 
		//CardVO [userId = gaeddongi,no=2222-222-22222, validMonth=06/25], 
		//CardVO [userId = gaeddongi,no=3333-333-33333, validMonth=01/27]
		// ]
		
		//foods, hobbys, jobs 멤버변수에 String[] 또는 List<String>을 변환하여  String값으로 넣어줌
		
		//String[] foodArray->String foods
		String[] foodArray = memberVO2.getFoodArray();
		memberVO2.setFoods(Arrays.toString(foodArray));
		
		//String[] hobby ->String hobbys
		String[] hobby = memberVO2.getHobby();
		memberVO2.setHobbys(Arrays.toString(hobby));
		
		
		//List<String> job ->String jobs
		List<String> job = memberVO2.getJob();
		
		//job.toString(); x 사용불가
		//외부 라이브러리를 사용(commons-lang3)
		String jobs = String.join(",",job);
		
		memberVO2.setJobs(jobs);
		
		log.info("memberVO2 : "+memberVO2);
		
		int result = this.reqService.regist(memberVO2);
		log.info("result:" + result);

		return "success";
		// memberVO2 : MemberVO2 [userId=gaeddongi, password=java, coin=100, dateOfBirth=Wed Apr 26 00:00:00 KST 2023, 
		//cars=saab,opel,audi,genesis, nationality=Korea, 
		//foodArray=[stake, jjajang, rice], foods={stake, jjajang, rice}, 
		//hobby=[sports, music, movie], hobbys={sports, music, movie}, 
		//job=[developer, musician, actor], jobs=developer,musician,actor, 
		//address=Address [userId=null, postCode=12345, location=대전 중구 대흥동, detLocation=123-5], 
		//bital=Bital [userId=null, height=185.0, weight=80.0, sight=1.5, pressure=100.0, bloodType=B]]


		
		
	}
	
}
