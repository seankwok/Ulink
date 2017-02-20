package ulink.constructor;

public class RankingDoctor {
	
	private String name;
	private String clinic;
	private String speciality;
	private int numAppointment;
	private int ranking; 
	
	public RankingDoctor(int ranking, String name, String clinic, String speciality, int numAppointment) {
		super();
		this.ranking = ranking;
		this.name = name;
		this.clinic = clinic;
		this.speciality = speciality;
		this.numAppointment = numAppointment;
	}
	
	public int getRanking(){
		return ranking;
	}
	
	public void setRanking(int ranking){
		this.ranking = ranking;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public int getNumAppointment() {
		return numAppointment;
	}
	public void setNumAppointment(int numAppointment) {
		this.numAppointment = numAppointment;
	}
	
	
}
