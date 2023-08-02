package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;

public interface MemberMapper {

	//회원정보입력
	 //<insert id="registerMember" parameterType="memberVO">
	public int registerMember(MemberVO memberVO);
	
	
	//회원 주소 정보 입력 -->
	 //<insert id="registerAddress" parameterType="addressVO" >
	public int registerAddress(AddressVO addressVO);
	
	
	 //회원 카드 정보 입력
	 //<update id="registerCard" parameterType="java.util.List">
	public int registerCard(List<CardVO> cardVO);

	//아이디 중복 체크
	//	 <select id="checkMemId" parameterType="memberVO" resultType="int">
	public int checkMemId(MemberVO memberVo);
	
	
	//회원 상세 정보
	//<select id="detailMember" parameterType="memberVO" resultMap="memberMap">
	public MemberVO detailMember(MemberVO memberVO);
	
} 
