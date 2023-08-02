package kr.or.ddit.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

//자바빈 클래스
@Data
public class AddressVO {
	
	//Primary key, Foreign Key(Member테이블의 mem_id컬럼을 참조)
	private String memId; //조인조건
	
	//우편번호
	@NotBlank(message = "우편번호를 입력해 주세요")
	private String postCode;
	//주소
	@NotBlank(message = "주소를 입력해 주세요")
	private String location;
	//상세주소
	private String detLocation;
}
