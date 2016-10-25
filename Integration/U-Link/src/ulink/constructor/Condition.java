package ulink.constructor;

public class Condition {
	private String conditionName;
	private int years;
	private int ageRequired;
	private String screening;
	private String type;


	public Condition(String conditionName, int years, int ageRequired, String screening, String type) {
		super();
		this.conditionName = conditionName;
		this.years = years;
		this.ageRequired = ageRequired;
		this.screening = screening;
		this.type = type;
	}
	public String getScreening() {
		return screening;
	}
	public void setScreening(String screening) {
		this.screening = screening;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public int getAgeRequired() {
		return ageRequired;
	}
	public void setAgeRequired(int ageRequired) {
		this.ageRequired = ageRequired;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
