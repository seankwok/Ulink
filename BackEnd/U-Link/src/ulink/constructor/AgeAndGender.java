package ulink.constructor;

public class AgeAndGender {
	private String Age;
	private double male;
	private double female;
	private double total;
	
	public AgeAndGender(String age, double male, double female, double total) {
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
	public double getMale() {
		return male;
	}
	public void setMale(double male) {
		this.male = male;
	}
	public double getFemale() {
		return female;
	}
	public void setFemale(double female) {
		this.female = female;
	}
	
	public double getTotal(){
		return total;
	}
	
	public void setTotal(double total){
		this.total = total;
	}
	
	
}
