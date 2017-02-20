package ulink.constructor;

public class Consultation {
	private int ID;
	private String appointmentDate;
	private String doctorName;
	private String clinicName;
	private String passportNumber;
	
	
	public Consultation(int iD, String appointmentDate, String doctorName, String clinicName, String passportNumber) {
		super();
		ID = iD;
		this.appointmentDate = appointmentDate;
		this.doctorName = doctorName;
		this.clinicName = clinicName;
		this.passportNumber = passportNumber;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	
	

}
