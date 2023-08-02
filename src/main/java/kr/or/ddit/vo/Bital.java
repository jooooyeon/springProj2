package kr.or.ddit.vo;

//자바빈 클래스
public class Bital {

	private String userId; //기본키라 전이
	private double height;
	private double weight;
	private double sight;
	private double pressure;
	private String bloodType;
	
	
	
	public Bital() {}



	public double getHeight() {
		return height;
	}



	public void setHeight(double height) {
		this.height = height;
	}



	public double getWeight() {
		return weight;
	}



	public void setWeight(double weight) {
		this.weight = weight;
	}



	public double getSight() {
		return sight;
	}



	public void setSight(double sight) {
		this.sight = sight;
	}



	public double getPressure() {
		return pressure;
	}



	public void setPressure(double pressure) {
		this.pressure = pressure;
	}



	public String getBloodType() {
		return bloodType;
	}



	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "Bital [userId=" + userId + ", height=" + height + ", weight=" + weight + ", sight=" + sight
				+ ", pressure=" + pressure + ", bloodType=" + bloodType + "]";
	}



	





}
