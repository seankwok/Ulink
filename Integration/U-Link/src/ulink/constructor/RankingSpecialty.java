package ulink.constructor;

public class RankingSpecialty {
	private int ranking;
	private String Specialty;
	private int count;

	public RankingSpecialty(int ranking, String specialty, int count) {
		super();
		this.ranking = ranking;
		this.Specialty = specialty;
		this.count = count;
	}

	public int getRanking() {
		return ranking;
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
