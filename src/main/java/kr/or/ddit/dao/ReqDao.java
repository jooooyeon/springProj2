package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVO2;

//스프링이 자바빈으로 등록하여 관리함
@Repository
public class ReqDao {
	//DI(의존성 주입) / IoC(제어의 역전)
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//<insert id="regist" parameterType="memberVO2">
	public int regist(MemberVO2 memberVO2) {
		//.insert("namespace.id",파라미터)
		return this.sqlSessionTemplate.insert("member2.regist",memberVO2);
	}
	
	//<insert id="registAddress" parameterType="memberVO2">
	public int registAddress(MemberVO2 memberVO2) {
		return this.sqlSessionTemplate.insert("member2.registAddress",memberVO2);
	}
	
	//<insert id="registBital" parameterType="memberVO2">
	public int registBital(MemberVO2 memberVO2) {
		return this.sqlSessionTemplate.insert("member2.registBital",memberVO2);
	}
	
	//카드 등록 
	//memberVO2.cardList -> List<CardVO>
	//<update id="registCard" parameterType="java.util.List">
	public int registCard(MemberVO2 membervo2) {
		return this.sqlSessionTemplate.update("member2.registCard",membervo2.getCardList());
	}
	
}
