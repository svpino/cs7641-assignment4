package edu.gatech.cs7641.assignment4.artifacts;

public class Hazard {
	public enum HazardType {
		SMALL, MEDIUM, LARGE
	}
	
	private Coordinates location;
	private double reward;
	private HazardType type;

	public Hazard(int x, int y, double reward, HazardType type) {
		this.location = new Coordinates(x, y);
		this.reward = reward;
		this.type = type;
	}
	
	public Coordinates getLocation() {
		return this.location;
	}
	
	public double getReward() {
		return this.reward;
	}
	
	public HazardType getType() {
		return this.type;
	}
}
