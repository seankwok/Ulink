package ulink.constructor;

public class RankingDoctorSpecialty {
	private String Specialty;
	private int engagement;
	
	
	
	
	public RankingDoctorSpecialty(String specialty, int engagement) {
		super();
		Specialty = specialty;
		this.engagement = engagement;
	}
	
	
	public String getSpecialty() {
		return Specialty;
	}
	public void setSpecialty(String specialty) {
		Specialty = specialty;
	}
	public int getEngagement() {
		return engagement;
	}
	public void setEngagement(int engagement) {
		this.engagement = engagement;
	}
	
	
	
}
