package ulink.constructor;

public class AgeAndGender {
	private String Age;
	private String male;
	private String female;
	private String total;
	
	public AgeAndGender(String age, String male, String female, String total) {
		super();
		Age = age;
		this.male = male;
		this.female = female;
		this.total = total;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getMale() {
		return male;
	}
	public void setMale(String male) {
		this.male = male;
	}
	public String getFemale() {
		return female;
	}
	public void setFemale(String female) {
		this.female = female;
	}
	
	public String getTotal(){
		return total;
	}
	
	public void setTotal(String total){
		this.total = total;
	}
	
	
}
