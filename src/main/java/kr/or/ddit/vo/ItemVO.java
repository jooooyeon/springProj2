package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//자바빈 클래스
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemVO {
	private int rnum;
	private int itemId;
	@NotBlank(message = "상품명을 작성해 주세요")
	private String itemName;
	@Min(value = 1000, message = "1000원 이상으로 작성해 주세요")
	private int price;
	private String description;
	private String pictureUrl;
	private String pictureUrl2;
	private MultipartFile picture;
	private MultipartFile picture2;
	//삭제여부
	private String delYn;
	
}
