package edu.gatech.cs7641.assignment4.artifacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cs7641.assignment4.artifacts.Hazard.HazardType;

public class Problem {
	private double defaultReward;
	private double goalReward;
	private Coordinates start;
	private Coordinates goal;
	private int[][] matrix;
	private List<Hazard> hazards;
	private HashMap<HazardType, Double> hazardRewards;
	private HashMap<Algorithm, Integer> numIterations;

	public Problem(String[] map, HashMap<Algorithm, Integer> numIterations, double defaultReward, double goalReward, HashMap<HazardType, Double> hazardRewards) {
		this.numIterations = numIterations;
		this.defaultReward = defaultReward;
		this.goalReward = goalReward;
		this.hazardRewards = hazardRewards;

		this.matrix = new int[map.length][map.length];
		this.hazards = new ArrayList<Hazard>();

		/*
		 * There's really not much to talk about here. Well, actually, there's something important:
		 * notice how the code below inverts the matrix before feeding it to BURLAP. If you don't
		 * invert the matrix, you'll have to invert your display to properly read the output of
		 * BURLAP. Believe me, it gets really annoying.
		 */
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length(); j++) {
				int x = j;
				int y = getWidth() - 1 - i;

				this.matrix[x][y] = 0;
				if (map[i].charAt(j) == '1') {
					this.matrix[x][y] = 1;
				}
				else if (map[i].charAt(j) == 'X') {
					this.start = new Coordinates(x, y);
				}
				else if (map[i].charAt(j) == 'G') {
					this.goal = new Coordinates(x, y);
				}
				else if (this.hazardRewards != null) {
					if (map[i].charAt(j) == 'S') {
						this.hazards.add(new Hazard(x, y, this.hazardRewards.get(Hazard.HazardType.SMALL), Hazard.HazardType.SMALL));
					}
					else if (map[i].charAt(j) == 'M') {
						this.hazards.add(new Hazard(x, y, this.hazardRewards.get(Hazard.HazardType.MEDIUM), Hazard.HazardType.MEDIUM));
					}
					else if (map[i].charAt(j) == 'L') {
						this.hazards.add(new Hazard(x, y, this.hazardRewards.get(Hazard.HazardType.LARGE), Hazard.HazardType.LARGE));
					}
				}
			}
		}
	}

	public Coordinates getStart() {
		return this.start;
	}

	public Coordinates getGoal() {
		return this.goal;
	}

	public int[][] getMatrix() {
		return this.matrix;
	}

	public int getWidth() {
		return this.matrix.length;
	}

	public List<Hazard> getHazards() {
		return this.hazards;
	}

	public double getDefaultReward() {
		return this.defaultReward;
	}

	public double getGoalReward() {
		return this.goalReward;
	}

	public int getNumberOfIterations(Algorithm algorithm) {
		if (this.numIterations != null && this.numIterations.containsKey(algorithm)) {
			return this.numIterations.get(algorithm);
		}

		return 100;
	}
}
