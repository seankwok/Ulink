package ulink.constructor;

public class RankingReferredBy {
	private String name;
	private int count;
	private int ranking; 
	
	
	public RankingReferredBy(int ranking, String name, int count) {
		super();
		this.ranking = ranking;
		this.name = name;
		this.count = count;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
