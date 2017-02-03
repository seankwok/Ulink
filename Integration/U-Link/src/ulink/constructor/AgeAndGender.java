package ulink.constructor;

public class AgeAndGender {
	private String Age;
	private int male;
	private int female;
	private int total;
	
	public AgeAndGender(String age, int male, int female, int total) {
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
	public int getMale() {
		return male;
	}
	public void setMale(int male) {
		this.male = male;
	}
	public int getFemale() {
		return female;
	}
	public void setFemale(int female) {
		this.female = female;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void setTotal(int total){
		this.total = total;
	}
	
	
}
