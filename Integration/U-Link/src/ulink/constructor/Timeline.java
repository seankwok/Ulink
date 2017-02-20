package ulink.constructor;

public class Timeline {
	private int ID;
	private String conditionName;
	private String passportNumber;
	private int yearToSend;
	
	
	public Timeline(int iD, String conditionName, String passportNumber, int yearToSend) {
		super();
		ID = iD;
		this.conditionName = conditionName;
		this.passportNumber = passportNumber;
		this.yearToSend = yearToSend;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public int getYearToSend() {
		return yearToSend;
	}
	public void setYearToSend(int yearToSend) {
		this.yearToSend = yearToSend;
	}
	
	
}
