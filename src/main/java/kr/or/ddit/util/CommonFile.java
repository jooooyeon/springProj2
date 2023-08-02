package kr.or.ddit.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonFile {
	private static final Logger log = LoggerFactory.getLogger(CommonFile.class);
	
	
	public static final String uploadFolder = 
			"C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
	
	
	//이미지 인지 여부 체킹, 썸네일은 이미지만 가능.
	public static boolean checkImageType(File file) {
		//MINE타입을 알아낼것임.   .jpeg / .jpg의 MINE타입 : image/jpeg
		
		String contentType;
		
		try {
			contentType = Files.probeContentType(file.toPath());
			log.info("contentType : " + contentType);
			
			//image/jpeg는 image로 시작함
			
			return contentType.startsWith("image");
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
		
}


	//연/월/일 폴더생성
	public static String getFolder() {
		//간단한 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		//복잡한 date를 형식에 맞게 바꾸어줌?
		//2023-05-02
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	//파일 다운로드
	//요청 URL : /download?filename=/2023/05/08/sdfsfsdfdf.개똥이.jpg
	//요청 데이터 :
	//요청방식 : 
	@ResponseBody
	@GetMapping(value = "/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String filename){
		log.info("filename : " + filename);
		String path = "C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
		
		Resource resource = new FileSystemResource(
				path + filename
				);
		
		log.info("path : "+ (path+filename));
		String resourceName = resource.getFilename();
		log.info("resourceName : " + resourceName);
		
		//헤더를 통해서 파일을 보냄
		HttpHeaders headers = new HttpHeaders();
		try {
			//파일명을 한글처리함
			headers.add("Content-Disposition", "attachment;filename=" + new String(resourceName.getBytes("UTF-8"),"ISO-8859-1")
					);
			
		}catch(UnsupportedEncodingException e) {
			return new ResponseEntity<Resource>(null,headers,HttpStatus.NOT_FOUND);
		}
		//resource : 파일 / header : 파일명 등 정보 / HttpStatus.ok : 상태 200(성공)
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
			
}
