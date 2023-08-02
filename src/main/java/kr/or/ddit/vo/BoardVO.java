package kr.or.ddit.vo;

import lombok.Data;

//자바빈 클래스

public class BoardVO {
	//멤버변수
	private int boardNo;
	private String title;
	private String contents;
	private String writer;
	
	//생성자
	public BoardVO() {
	}

	//getter.setter메소드
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
	}
	
	
	
}
