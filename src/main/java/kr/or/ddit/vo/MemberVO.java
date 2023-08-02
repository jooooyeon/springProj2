package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


import lombok.Data;


//자바 빈 클래스
@Data
public class MemberVO {
	/* 입력값 검증 규칙
	-NotBlank : null 체크, trim 후 길이가 0인지 체크
	-NotNull : 빈값체크
	-Size : 글자 수 체크 
	-Email : 이메일 주소 형식 체크 :이메일 주소가 유효하지 않습니다.
	-Past : 오늘보다 과거날짜(ex. 생일)인지 체크 :반드시 과거 날짜이어야 합니다.
	-Future  : 미래날짜인지 체크(ex. 예약일)
	 */
	
	
	public static MemberVO memberVO;
	//멤버 변수
	private int rnum; //행번호
	//입력값검증 규칙을 지정
	@NotBlank(message="ID를 입력해주세요")
	private String memId; //기본키
	private String memPass;
	//NotBlank뒤에 아무것도 입력하지 않으면 디폴트값인 기본 문장이나옴
	//여러개의 입력값 검증 규칙을 지정할 수 있음
	@NotBlank
	@Size(max = 3)
	private String memName;
	private String memRegno1;
	private String memRegno2;
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	@NotBlank
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String memMemorial;
	private Date memMemorialday;
	private int memMileage;
	private String memDelete;
	
	//<td><form:checkboxes items="달러{memListMap}" path="memLikeMap" /></td>
	private List<String> hobbyList;
	private String[] hobbyArray;
	private String hobby;
	
	//개발자여부(Y/N)
	private String developer;
	//외국인여부(true/false)
	private boolean foreigner;
	
	//성별
	private String gender;
	
	//국적
	private String nationality;
	
	//보유자동차
	private String[] carArray;
	private List<String> carList;
	
	//중첩된 자바빈즈
	//MemberVO(부모) : AddressVO(자식) = 1 : 1
	//입력값 검증을 적용
	@Valid
	private AddressVO addressVO;
	
	
	//중첩된 자바빈즈
	//MemberVO(부모) : CardVO(자식) = 1 : N
	//p.k memId         F.K memId
	
	@Valid
	private List<CardVO> cardVO;
	
	private String enabled;
	
	//회원의 증명사진
	private String memImg;
	
	
	//MemberAuthVO : MemberAuthVO = 1 : N
	private List<MemberAuthVO> memberAuthVOList;
	
}
