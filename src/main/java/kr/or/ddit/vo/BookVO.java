package kr.or.ddit.vo;

import java.util.Date;

//자바빈(규약) 클래스 /  (자바빈객체와 다름 @이가 붙은 애들 : 서버가 기동될때 메모리에 올리는것)
//자바빈 규약 : 1)멤버면수 2)기본생성자 3)getter/setter메소드
public class BookVO {
	//멤버변수
	private int bookId;
	private String title;
	private String category;
	private int price;
	private Date insertDate;
	
	//기본생성자. 생략 가능
	public BookVO() {}

	
	//getter, setter메소드
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", title=" + title + ", category=" + category + ", price=" + price
				+ ", insertDate=" + insertDate + "]";
	}
	
	
	
	
	
}
