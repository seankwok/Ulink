package ulink.constructor;

public class ClientByIllness {
	private String name;
	private int age;
	private String email;
	private String gender;
	private String screeningName;
	private String conditionName;
	private String date;
	private String followUpPerson;
	
	public String getDate(){
		return date;
	}
	
	public String getFollowUpPerson(){
		return followUpPerson;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ClientByIllness(String name, int age, String email, String gender, String screeningName, String conditionName, String date, String followUpPerson) {
		super(); 
		this.name = name;
		this.age = age;
		this.email = email;
		this.gender = gender;
		this.screeningName = screeningName;
		this.conditionName= conditionName;
		this.date = date;
		this.followUpPerson = followUpPerson;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getScreeningName(){
		return screeningName;
	}
	
	public String getConditionName(){
		return conditionName;
	}
	
	
}
