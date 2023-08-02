package kr.or.ddit.vo;

import java.util.Date;

import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//자바빈 클래스
//PoJo : Plain Oriented Jaba Object에 역행
@Data
public class CardVO {
	private String userId; //기본키라 전이
	private String memId;
	@NotBlank(message = "카드번호를 입력해주세요")
	private String no;
	
	//20270507 : 문자 -> 2027-05-07 : 날짜 로 바꿔야함
	@Future(message = "오늘 이후의 날짜를 입력해 주세요")
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date validMonth;
	
	

	
	
}
