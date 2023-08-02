package kr.or.ddit.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.ReqDao;
import kr.or.ddit.service.ReqService;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO2;

@Service
public class ReqServiceImpl implements ReqService{
	private static final Logger log = LoggerFactory.getLogger(ReqServiceImpl.class);
	//DI, IoC
	@Autowired
	ReqDao reqDao;
	
	public int regist(MemberVO2 memberVO2) {
		int result = this.reqDao.regist(memberVO2);//회원 정보 등록 1
			result += this.reqDao.registAddress(memberVO2);//주소정보 등록 1
			result += this.reqDao.registBital(memberVO2);//생체정보등록 1
			
		//userID를 넣어주기
		List<CardVO> cardVOList = memberVO2.getCardList();
		
		for(CardVO vo : cardVOList) {
			vo.setUserId(memberVO2.getUserId());
			
		}
		
			result += this.reqDao.registCard(memberVO2);//카드등록 N
			
			log.info("result:" + result);
			return result;
	}
}
