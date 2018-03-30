package edu.gatech.cs7641.assignment4.artifacts;

public enum Algorithm {
	ValueIteration("Value Iteration"),
	PolicyIteration("Policy Iteration"),
	QLearning("Q-Learning");

	private String title;

	private Algorithm(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
}

