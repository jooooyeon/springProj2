package kr.or.ddit.vo;

import java.util.List;

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
public class Item3VO {
	private int rnum;
	private int itemId;
	private String itemName;
	private int price;
	private String description;
	
	//<input type="file" class="custom-file-input" id="pictures" name="pictures" multiple />
	//multiple이라 배열로 처리해야함
	private MultipartFile[] pictures;
	
	//중첩된(nested 자바빈
	//ITEM3 : ITEM ATTACH = 1 :N
	private List<ItemAttachVO> itemAttachVOList;
	
}
