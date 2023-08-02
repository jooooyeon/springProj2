package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface MemberService {

	//메소드 시그니처
	public List<MemberVO> list(Map<String, String> map);

	//전체 글 수 구하기
	public int getTotal(Map<String, String> map );

	//회원 상세 보기
	public MemberVO detail(MemberVO memberVO);

	//회원 정보 변경
	public int updatePost(MemberVO memberVO);

	//회원 정보 삭제
	public int deletePost(MemberVO memberVO);
	
	//회원 정보 입력
	public int registerMember(MemberVO memberVO);
	
	//아이디 중복체크
	public int checkMemId(MemberVO memberVo);

	//회원 상세 정보(권한 포함)
	public MemberVO detailMember(MemberVO memberVO);

}
