package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

//스프링이 자바빈으로 등록하여 관리함
@Repository
public class MemberDao {
	//쿼리를 실행하기 위한 객체
	//DI(의존성 주입)
	//IoC(제어의 역전)
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//회원정보목록
	//파라미터 : hashMap : {size=10, currentPage=1}
	//<select id="list" parameterType="hashMap" resultType="memberVO">
	public List<MemberVO> list(Map<String, String> map){
		//.selectList("namespace.id",파라미터(파라미터 없으면 생략가능))
		return this.sqlSessionTemplate.selectList("member.list", map);
	}
	//전체 글 수 구하기 
//	<select id="getTotal" resultType="int">
	public int getTotal(Map<String, String> map ) {
		return this.sqlSessionTemplate.selectOne("member.getTotal",map);
	}
	
	 // 회원 상세보기 
	 //<select id="detail" parameterType="memberVO" resultType="memberVO">
	public MemberVO detail(MemberVO memberVO) {
		return this.sqlSessionTemplate.selectOne("member.detail",memberVO);
	}
	 //<!-- 회원 정보 변경 -->
	// <update id="updatePost" parameterType="memberVO" > 메소드명 id와 동일하게 작성
	public int updatePost(MemberVO memberVO) {
		return this.sqlSessionTemplate.update("member.updatePost",memberVO);
	}
	
	 //회원정보삭제
	 //<delete id="deletePost" parameterType="memberVO">
	public int deletePost(MemberVO memberVO) {
		return this.sqlSessionTemplate.delete("member.deletePost",memberVO);
	}
	
}
