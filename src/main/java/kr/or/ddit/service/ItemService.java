package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.Item3VO;
import kr.or.ddit.vo.ItemVO;

public interface ItemService {
	//메소드 시그니처
	
	
	//ITEM테이블에서 insert
	public int create(ItemVO itemVO);

	//이미지가 두개일때
	public int create2(ItemVO itemVO);
	
	//상세보기
	public ItemVO detail(ItemVO itemVO);

	//목록보기
	public List<ItemVO> list();

	//삭제
	public int deletePost(ItemVO itemVO);
	
	//다중이미지등록
	public int multiRegisterPost(Item3VO item3VO);

	//상품상세
	public Item3VO detailMulti(Item3VO item3vo);

	public List<Item3VO> multiList();
}
