package kr.or.ddit.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BookVO;

//매퍼xml(book_SQL.xml)을 실행시킴
//DAO(Data Access Object)클래스 : 데이터에 접근하는 클래스
//Repository 어노테이션 : 데이터에 접근하는 클래스
//스프링이 데이터를 관리하는 클래스라고 인지하여 자바빈으로 등록하여 관리함
@Repository
public class BookDao {
	//IoC (Invertion Of control) : 제어의 역전 //new를사용해서 생성하지 않는다 빈즈에 들어있는것 사용?
	//DI(Dependency Injection) : 의존성 주입
	//new 키워드를 통해 직접생성하지 않고 
	//스프링이 미리 만들어 놓은 (서버 실행시 스프링이 미리 xml이나 어노테이션을 읽어서 객체를 인스턴스화 해 놓음)
	//SqlsessionTemplate(화이자) 타입 객체를 BookDao(개똥이)객체에 주입함
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	//<insert id="insert" parameterType="bookVO">
	//여기서  bookSQL을 실행할것임
	public int insert(BookVO bookVO) {
		//우선 bookSQL을 찾아가야함.
		//book_SQL.xml 매퍼 xml 파일의 namespace가 book이고, id가 insert인 
		//태그를 찾아서 그 안에 들어 있는 sql을 실행
		//.insert(insert id임)("namespace.id",파라미터)
		//insert, update, delete는 반영된 건수가 return됨
		return this.sqlSessionTemplate.insert("book.insert", bookVO);
		
	}
	
	//<select id="detail" parameterType="bookVO">
	//메소드이름은 아이디와 동일하게
	public BookVO detail(BookVO bookVO) {
		//sqlSessionTemplate : 쿼리를 실행해주는애
		//쿼리를 실행해주는 객체?sqlSessionTemplate(root-context.xml)
		//selectOne()메소드 : 1행을 가져올 때 사용 / selectList() 메소드 : 결과 집합 목록 반환(다중행
		//select결과가 없다면? null을 반환
		//select 결과 행 수가 2이상일때 ? TooManySesultsException 예외발생
		//BookVO(후)[bookId=10, title=더글로리, category=드라마, price=12000, insertDate=2023-04-12]
		//						.selectOne("namespce.id",파라미터)
		//								"네임스페이스.아이디",파라미터				
		return this.sqlSessionTemplate.selectOne("book.detail",bookVO);
	}

	
	//책 변경
	//	<update id="updatePost" parameterType="bookVO">
	public int updatePost(BookVO bookVO) {
		//.update("namespace.id",파라미터)
		return this.sqlSessionTemplate.update("book.updatePost",bookVO);
	}
	
	//책 삭제
	//<delete id="deletePost" parameterType="bookVO">
	public int deletePost(BookVO bookVO) {
		//								.delete("namespace.id",파라미터)
		return this.sqlSessionTemplate.delete("book.deletePost",bookVO);
	}
	
//	 <!-- 도서목록 -->
	//셀렉트아이디가 리스트라서 메소드 명도 리스트임
//	 <select id="list" resultType="bookVO">
	public List<BookVO> list(String keyword){
		//.selectList("namespace.id",파라미터(파라미터없을때는 생략)
		//.selectList("namespace.id",파라미터
	//	return this.sqlSessionTemplate.selectList("book.list");
		return this.sqlSessionTemplate.selectList("book.list",keyword);
	}
	
}
