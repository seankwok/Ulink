package ulink.constructor;

public class RankingSpecialty {
	private String Specialty;
	private int count;
	public RankingSpecialty(String specialty, int count) {
		super();
		Specialty = specialty;
		this.count = count;
	}
	public String getSpecialty() {
		return Specialty;
	}
	public void setSpecialty(String specialty) {
		Specialty = specialty;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
