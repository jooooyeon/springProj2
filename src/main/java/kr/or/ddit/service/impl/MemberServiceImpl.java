package kr.or.ddit.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.dao.MemberDao;
import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

//골뱅이 Service 애너테이션 : 스프링이 자바빈으로 등록하여 관리
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	//MemberDao객체의 list()메소드를 사용해야 함
	//DI, IoC
	@Autowired
	MemberDao memberDao;
	
	@Inject
	MemberMapper memberMapper;
	
	
	
	//회원정보목록
	//파라미터 : hashMap : {size=10, currentPage=1}
	@Override
	public List<MemberVO> list(Map<String, String> map){
		return this.memberDao.list(map);
	}
	
	//전체 글 수 구하기 
	@Override
	public int getTotal(Map<String, String> map ) {
		return memberDao.getTotal(map);
	}
	
	 // 회원 상세보기 
	@Override
	public MemberVO detail(MemberVO memberVO) {
		return this.memberDao.detail(memberVO);
	}
	
	 //회원 정보 변경 
	@Override
	public int updatePost(MemberVO memberVO) { //몇행이 업데이트 되었는가
		return this.memberDao.updatePost(memberVO);
	}
	
	 //회원정보삭제
	@Override
	public int deletePost(MemberVO memberVO) {
		return this.memberDao.deletePost(memberVO);
	}
	
	
	//회원정보입력
	@Transactional
	@Override
	public int registerMember(MemberVO memberVO) {
		//MEMBER테이블에 insert
		int result = this.memberMapper.registerMember(memberVO);
		
		//ADDRESS테이블에 insert
		AddressVO addressVO = memberVO.getAddressVO();
		addressVO.setMemId(memberVO.getMemId());
		
		result += this.memberMapper.registerAddress(addressVO);
		
		
		//CARD테이블에 insert
		//memberVo.getCardVO() : List<CardVO>타입
		List<CardVO> cardVOList = memberVO.getCardVO();
		
		for(CardVO cardVo : cardVOList) {
			cardVo.setMemId(memberVO.getMemId());
		}
		
		log.info("cardVOList : " + cardVOList);
		
		result += this.memberMapper.registerCard(cardVOList);
		
		log.info("result : " + result);
		
		return result;
		
	}
	
	//아이디 중복 체크
	@Override
	public int checkMemId(MemberVO memberVo) {
		return this.memberMapper.checkMemId(memberVo);
		
	}
	//회원 상세 정보
	@Override
	public MemberVO detailMember(MemberVO memberVO) {
		return this.memberMapper.detailMember(memberVO);
	}
	
	
}
