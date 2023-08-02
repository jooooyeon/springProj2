package kr.or.ddit.vo;

//자바빈 클래스 기본생성자, 게터세터, 투스트링
//테이블 따로 만들어줌=> 오라클에서
public class Address {
	private String userId;//기본키라 넣어줌
	private String postCode;
	private String location;
	private String detLocation;
	
	public Address() {}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDetLocation() {
		return detLocation;
	}
	public void setDetLocation(String detLocation) {
		this.detLocation = detLocation;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Address [userId=" + userId + ", postCode=" + postCode + ", location=" + location + ", detLocation="
				+ detLocation + "]";
	}
	
	
	
}
