package ulink.constructor;

public class Condition {
	private String conditionName;
	private int years;

	public Condition(String conditionName, int years) {
		super();
		this.conditionName = conditionName;
		this.years = years;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	
	
}
