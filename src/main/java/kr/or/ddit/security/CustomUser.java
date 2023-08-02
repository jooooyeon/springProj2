package kr.or.ddit.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.vo.MemberVO;

//사용자가 유저를 정의함
//MemberVO(사용자가 정의한 유저 및 권한) 정보를 User(스프링 시큐리티에서 정의된 유저) 객체 정보에 연계하여 넣어줌
public class CustomUser extends User{
	private MemberVO memberVO;

	//아이디 비빌번호 권한만 있음? 두번째꺼 선택함
	//User의 생성자를 처리해주는 생성자
	public CustomUser(String username, String password
			, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	
	//return memberVO == null ? null : new CustomUser(memberVO);
	public CustomUser(MemberVO memberVO) {
		//사용자가 정의한 (select한) MemberVO타입의 객체 memberVO를 
		//스프링 시큐리티에서 제공해 주고 있는 User 타입으로 변환
		//회원정보를 보내줄테니 이제부터 스프링 니가 관리해줘
		//super : User
		//memberAuthVOList[memberAuthVO[memId:admin,auth:ROLE_MEMBER], memberAuthVO[memId:admin,auth:ROLE_ADMIN]]}
		//-> Collection<? extends GrantedAutority> authorities
		//결과 : super("admin","java",[ROLE_MEMBER", "ROLE_ADMIN"])
		super(memberVO.getMemId(), memberVO.getMemPass(),
				memberVO.getMemberAuthVOList().stream().
				map(auth->new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
				);
		//CustomUser클래스의  memberVO 멤버 변수에 select한 회원/권한 정보(memberVO 매개 변수)가 setting됨
		//this.memberVO는 JSP에서 principal로 사용될 것임
		this.memberVO = memberVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
}
