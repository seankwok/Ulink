package ulink.constructor;

public class KPI {
	private String date;
	private double inPatient;
	private double outPatient;
		
	public KPI(String date, double inPatient, double outPatient) {
		super();
		this.date = date;
		this.inPatient = inPatient;
		this.outPatient = outPatient;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getInPatient() {
		return inPatient;
	}
	public void setInPatient(int inPatient) {
		this.inPatient = inPatient;
	}
	public double getOutPatient() {
		return outPatient;
	}
	public void setOutPatient(int outPatient) {
		this.outPatient = outPatient;
	}
	
	
	
}
