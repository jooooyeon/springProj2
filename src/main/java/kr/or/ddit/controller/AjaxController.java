package kr.or.ddit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.support.StandardServletMultipartResolver;


import kr.or.ddit.vo.MemberVO2;
import net.coobird.thumbnailator.Thumbnailator;

import org.mybatis.spring.mapper.MapperScannerConfigurer;

@RequestMapping("/ajax")
@Controller
public class AjaxController {
	private String uploadFolder="C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
	private static final Logger log = LoggerFactory.getLogger(AjaxController.class);

	@GetMapping("/register01")
	public String register01() {
		log.info("/ajax/register01에 왔다");
		
		
		//forwarding
		return"ajax/register01";
	}
	
	
	//1.URL 경로상의 경로 변수값을 골뱅이PathVariable 애너테이션을 지정하여 문자열 매개변수로 처리함
	//요청URI : /ajax/register01/gaeddongi
	//요청방식 : get
	//@GetMapping->{PathVariable} // @PathVariable("userId")를 String userId변수에 담아라?
	@ResponseBody
	@GetMapping("/register01/{userId}")
	public String register01(@PathVariable("userId") String userId) {
		log.info("/ajax/register01에 왔다");
		
		log.info("userId : " + userId);
		
		//result로 들어가게됨
		return"success";
	}
	
	//요청 URI : /ajax/register02/board/3
	//요청파라미터 : {"userId :"gaeddongi", "password":"java"}
	//요청방식 : post
	// 1) URL 경로상의 어려 개의 경로 변수(PathVariable)값을 골뱅이 PathVariable 애너테이션을
	//		지정하여 여러개의 문자열 매개변수로 처리함
	//2) 객체 타입의 JSON 요청 데이터를 골뱅이 RequestBody 애너테이션을 지정하여
	// 		자바빈즈 매개변수로 처리함
	//@PostMapping("/register02/{brdId}(@PathVariable 값과 같아야함)/{seq}")
	@PostMapping("/register02/{brdId}/{seq}")
	public String register02(@PathVariable("brdId") String brdId, 
			@PathVariable("seq") String seq,
			@RequestBody MemberVO2 memberVO2) {
		log.info("/ajax/register02에 왔다");
		
		log.info("brdId : " + brdId);
		log.info("seq : " + seq);
		log.info("memberVO2 : " + memberVO2);
		
		/*   
			brdId : board
			seq : 3
			memberVO2 : MemberVO2 [userId=gaeddongi, password=java, coin=0, dateOfBirth=null, cars=null, nationality=null, foodArray=null, foods=null, hobby=null, hobbys=null, job=null, jobs=null, address=null, bital=null, cardList=null, picture=null, picture2=null]
		 */
		return "success";
	}
	
	// /ajax/register03
	//요청파라미터 : 
	//[{"userId":"gaeddongi","password":"java"},{"userId":"Harry","password":"java"}]
	@ResponseBody
	@PostMapping("/register03")
	public String register03(@RequestBody List<MemberVO2> memberVO2List) {
		log.info("/ajax/register03에 왔다");
		
		log.info("memberVO2List : " + memberVO2List);
		//###########개똥이 밖에 안나옴 선생님께 여쭤보기!!!!!!!!!
		return "suceess";
	}
	
	
	//http://localhost/ajax/register05
	@GetMapping("/register05")
	public String register05() {
		log.info("/ajax/register05에 왔다");
		
		//fowarding
		return "ajax/register05";
	}
	
	//요청URI : ajax/register05Post
	//요청데이터 : <form><input type="file" name="file"/></form>
	//요청방식 : post
	//http://localhost/ajax/register05
	@ResponseBody
	@PostMapping("/register05Post")
	public String register05Post(MultipartFile file,
			HttpServletRequest request) {
		log.info("originalFilename : " + file.getOriginalFilename());//		 originalFilename : 111.jpg
		log.info("size : " + file.getSize());//		 size : 141512
		log.info("contentType : " + file.getContentType());//		 contentType : image/jpeg
		

		String absolutePath = request.getRealPath(request.getContextPath()); 
		//absolutePath : C:\eGovFrameDev-3.10.0-64bit\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\springProj\
		log.info("absolutePath : " + absolutePath);
		
		String uploadFolder = "C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
		//uploadFolder :  C:\eGovFrameDev-3.10.0-64bit\workspace\springProj\src\main\webapp\resources\\upload
		log.info("uploadFolder : " + uploadFolder);
		
		
		//연/월/일 폴더 생성 (2023-05-02) 시작--------------------------
		//파일객체를 통해서 연월일 폴더 생성.
		// .../resources/upload/2023/05/02
		File path = new File(uploadFolder,getFolder());
		
		//만약 연/월/일 해당 폴더가 없으면 생성
		if(path.exists()==false) {
			path.mkdirs();
		}
		//연/월/일 폴더 생성 (2023-05-02) 끝--------------------------
		
		String uploadFileName = file.getOriginalFilename();
		
		//파일명 중복 방지 시작------------------------------------
			//java.util.UUID => 랜덤값 생성
			UUID uuid = UUID.randomUUID();
			//원래으 ㅣ파일 이름과 구분하기 위해 uuid값_원본파일명
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
		//파일명 중복 방지 끝------------------------------------
				
		
		//해당 폴더에 파일이 들어감
		//File객체 설계 (복사할 대상 경로, 파일명)
		File saveFile = new File(path,uploadFileName);
		
		
		//설계대로 파일 복사 실행
		try {
			file.transferTo(saveFile);
			
			//썸네일 처리 시작//////////////
			//썸네일은 이미지만됨
			//이미지인지 체킹
			if(checkImageType(saveFile)) {
				FileOutputStream thumbnail = new FileOutputStream(
						new File(path,"s_"+uploadFileName)
						);
				//썸네일 생성(원본 stream, 계획, 가로크기, 세로크기)
				Thumbnailator.createThumbnail(file.getInputStream(),
						thumbnail,100,100);
				thumbnail.close();
			}
			//썸네일 처리 끝//////////////
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// "\\" : 윈도우 경로
		// "/"  : 웹경로
		// /2023/05/02/ff4e474c-5e48-4e5b-9100-9afa210fdc48_11.jpg
		return "/"+ getFolder().replace("\\", "/")+"/"+ uploadFileName;
	}
	
	
		//이미지 인지 여부 체킹, 썸네일은 이미지만 가능.
		private boolean checkImageType(File file) {
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
		public String getFolder() {
			//간단한 날짜 형식
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//날짜 객체 생성(java.util 패키지)
			Date date = new Date();
			//복잡한 date를 형식에 맞게 바꾸어줌?
			//2023-05-02
			String str = sdf.format(date);
			
			return str.replace("-", File.separator);
		}
		
	
		//http://localhost/ajax/register05
		@GetMapping("/register06")
		public String register06() {
			log.info("/ajax/register06에 왔다");
			
			//fowarding
			return "ajax/register06";
		}
	
		
		
		//요청URI : /ajax/register06Post
		//data : formData <input type="file" name="uploadFile"../>
		//요청 방식 : post
		@ResponseBody
		@PostMapping("/register06Post")
		public Map<String,String> register06Post(MultipartFile[] uploadFile) {
			log.info("/ajax/register06Post에 왔다");
			log.info("uploadFile : " + uploadFile);
			
			//연월일 폴더 처리 시작///////////////
			File uploadPath = new File(this.uploadFolder,getFolder());
			if(uploadPath.exists()==false) {
				uploadPath.mkdirs();
			}
			//연월일 폴더 처리 끝///////////////
			
			//이미지 원본 파일명을 담는 변수 초기화(할당)
			String uploadFileName="";
			
			
			//이미지 객체 수 만큼 반복
			//MultipartFile[] uploadFile
			for(MultipartFile multipartFile:uploadFile) {
				log.info("----------------------");
				log.info("file name : " + multipartFile.getOriginalFilename());
				log.info("file size : " + multipartFile.getSize());
				log.info("file contentType : " + multipartFile.getContentType());
				//개똥이.jpg
				uploadFileName = multipartFile.getOriginalFilename();
				
				//같은날 같은 이미지 업로드 시 파일 중복 방지 시작//////////////////////
				UUID uuid = UUID.randomUUID();
									//sdfsdfef_개똥이.jpg
				uploadFileName = uuid.toString()+"_"+uploadFileName;
				//같은날 같은 이미지 업로드 시 파일 중복 방지 끝//////////////////////
				
				
				//File객체 셜계(복사할 대상 경로, 파일명)
				//  \\resources\\upload\\2023\\05\\03 + "\\" + sdfsdfef_개똥이.jpg
				File saveFile = new File(uploadPath,uploadFileName);
				
				try {
					//설계대로 파일 복사 실행
					//원본. 복사하다(설계)
					//saveFile 설계도?
					multipartFile.transferTo(saveFile);
					
					//썸네일 처리 시작///////////////
					if(checkImageType(saveFile)) { //이미지면 실행
						//설계
						FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
						//썸네일 생성
						//multipartFile.getInputStream() ->new FileInputStream(saveFile)
						Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
						
						thumbnail.close();
					}
					
					//썸네일 처리 끝///////////////
					
					
				} catch (IllegalStateException | IOException e) {
					log.error(e.getMessage());
					//실패
					Map<String, String> map = new HashMap<String,String>();
					map.put("result", "failed");
					
					return map;
				}
			}//end for
			//성공
			Map<String, String> map = new HashMap<String,String>();
			map.put("result", "success");
			
			return map;
			
		}
		
}
