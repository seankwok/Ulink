package ulink.constructor;

public class KPI {
	private String date;
	private int inPatient;
	private int outPatient;
		
	public KPI(String date, int inPatient, int outPatient) {
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
	public int getInPatient() {
		return inPatient;
	}
	public void setInPatient(int inPatient) {
		this.inPatient = inPatient;
	}
	public int getOutPatient() {
		return outPatient;
	}
	public void setOutPatient(int outPatient) {
		this.outPatient = outPatient;
	}
	
	
	
}
