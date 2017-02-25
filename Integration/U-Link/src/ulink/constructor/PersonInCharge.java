package ulink.constructor;

import java.util.LinkedHashMap;

public class PersonInCharge {
	private String name;
	private LinkedHashMap<Integer,Double> pointSystem;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedHashMap<Integer, Double> getPointSystem() {
		return pointSystem;
	}
	public void setPointSystem(LinkedHashMap<Integer, Double> pointSystem) {
		this.pointSystem = pointSystem;
	}
	public PersonInCharge(String name, LinkedHashMap<Integer, Double> pointSystem) {
		super();
		this.name = name;
		this.pointSystem = pointSystem;
	}
	
	
}
