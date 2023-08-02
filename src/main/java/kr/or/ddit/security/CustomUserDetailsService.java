package kr.or.ddit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

//UserDetailsService : 스프링 시큐리티에서 제공해 주고 있는 사용자 삿에정보를 갖고 있는 인터페이스
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
	//DI(Dependenvy Injection)의존성 주임
	@Autowired
	private MemberMapper memberMapper;
	
	//username : 로그인 페이지에서 로그인 시도 시 입력된 아이디
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//1) 사용자 정보를 검색을 해서
		//username : 로그인 시 입력 받은 회원의 아이디
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(username);
		
		memberVO = this.memberMapper.detailMember(memberVO);
		//memberVO {memId:admin, memPass:java, nemName:뚜연...
 		//	, memberAuthVOList[memberAuthVO[memId:admin,auth:ROLE_MEMBER], memberAuthVO[memId:admin,auth:ROLE_ADMIN]]}
		log.info("memberVO : " + memberVO);
		//CustomUser : 사용자 정의 유저 정보. extends User를 상속받고 있음
		//2) 스프링 시큐리티의 User 객체의 정보를 넣어줌 => 스프링이 이제부터 해당 유저를 관리 (memberVO가 User에 정보를 넣어줌?)
		//User : 스프링 시큐리티에서 제공해주는 사용자 정보 클래스
		
		return memberVO == null ? null : new CustomUser(memberVO);
	}
	
}
