package kr.or.ddit.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//자바빈클래스
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemAttachVO {
	private int itemId;
	private int seq;
	private String fullname;
	private Date regdate;
	
}
