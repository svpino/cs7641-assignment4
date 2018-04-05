# CS7641 - Machine Learning - Assignment 4 - Markov Decision Processes

We are encouraged to grab, take, copy, borrow, steal (or whatever similar concept you can come up with) the code to run our experiments and focus all of our time doing the analysis. Hopefully, this code will help others do that.

For Assignment 4, we need to come up with two interesting MDPs, one with a small number of steps, and the other with a large number of steps. While "small" and "large" are largely subjective terms, this code uses an `8x8` grid map as the "small" example and a `21x21` grid map as the "large" one. You are encouraged to change these examples and make something different and exciting out of them. If you don't, then your report will probably look very similar to others, and you might get in trouble. Be creative.

## Running the project

The simplest way to get this thing up and running is by using [Eclipse](http://www.eclipse.org/). Of course, you can also use _your-sweetheart-tool_, but Eclipse will get you there faster because that's what I used and uploaded to this repository.

Clone this repository, and import the project into Eclipse. Then you need to update the project using Maven (which should be conveniently added as an option when you right click your project inside Eclipse). Maven will download [Burlap](http://burlap.cs.brown.edu/) and a bunch of other things that come with it. Maven will save you 3 hours of library-chasing, so embrace it and make it work. Make sure you are using Java 8 or later. 

As soon as all error indicators are gone, you can run the Main.java class from inside Eclipse. That's pretty much it.

## What can you do with this thing?

### First, the map surface
Start by changing the surface of the problems. Try and be creative and come up with something cool. This shouldn't be too hard. Find the `createProblem1()` and `createProblem2()` static methods and start playing with the `map` array inside them. Here are the rules to describe the surface:

 * `X` — The starting point of the agent.
 * `0` — Represents a safe cell where the agent can move.
 * `1` — Represents a wall. The agent can't move to this cell.
 * `G` — Represents the goal that the agent wants to achieve.
 * `S` — Represents a small hazard. The agent will be penalized.
 * `M` — Represents a medium hazard. The agent will be penalized.
 * `L` — Represents a large hazard. The agent will be penalized.

For example, here is the "code" representation of a map:

```
X0011110
01000S10
010M110S
0M0000M1
01111010
00L010S0
0S001000
000000SG
```
Set the `PROBLEM` constant to the specific problem you want to execute:

```java
private static int PROBLEM = 1;
```
I set up the code with two different problems. You should be able to extend that to more problems if you want with very few modifications.

### Dealing with rewards
All those hazards are just rewards (negative rewards, in this case) that you can set up however you like. Out of the box, you'll get that small hazards are worth `-1.0`, medium hazards are worth `-2.0`, and large hazards are worth `-3.0`.

Play with these values, and you'll see how your agent will react to those changes by trying to get as far away as possible from substantial penalties. You can change the optimal policy by just making small adjustments to these rewards.

Make your changes where you see the following:

```java
hazardRewardsHashMap.put(HazardType.SMALL, -1.0);
hazardRewardsHashMap.put(HazardType.MEDIUM, -2.0);
hazardRewardsHashMap.put(HazardType.LARGE, -3.0);
```
Yes, you can set up different rewards for each problem. That's why I have two separate methods to deal with all of these.

Finally, don't forget the specific reward for reaching the goal state. Out of the box you get a positive `10`, but feel free to change it (of course, keep it positive unless you want your agent avoiding reaching the goal). You also need to worry about the default reward (a reward attached to any blank cell where the agent can move). You want to make this a negative value (out of the box you get `-0.1`), so the agent has some incentive to stop wandering around. You can change both of these values using the `Problem` constructor: 

```java
return new Problem(map, numIterationsHashMap, -0.1, 10, hazardRewardsHashMap);
```

### The algorithms
This code implements three different algorithms: Value Iteration, Policy Iteration, and Q-Learning. Only one of these algorithms runs at a time. To select which algorithm to run, modify the following constant:

```java
private final static Algorithm algorithm = Algorithm.ValueIteration;
```
The source code should be straightforward to follow to find out what are the specific things you can change to see how these algorithms work, but here are some recommendations:

* Look in the `main` method, when `runAlgorithm` is called to see the specific initialization of each one of these algorithms.
* I'm setting the value of _gamma_ for all three algorithms to `0.99`. Feel free to investigate how this value changes your results and make sure you always keep it under `1`.
* The _maxDelta_ for Value and Policy Iteration is set at `0.001`. Your specific problem might benefit from a different value.
* For Policy Iteration we need to specify the number of iterations of Value Iteration that the algorithm will use internally to compute the corresponding values. By default, the code is using the same number of iterations specified for the Value Iteration algorithm. A recommended modification is to change this value to the actual number of iterations that it takes Value Iteration to converge. This will considerably improve the runtime of the algorithm (assuming that Value Iteration converges faster than the number of configured iterations).
* For Q-Learning, the Q-value and learning rate are a couple more attributes that you can change to explore the results of the algorithm. They are currently set at `0.3` and `0.1` respectively.

Assuming you ignore all of the above, you'll probably be fine, but here is something that you definitely want to check: the specific number of iterations for each algorithm. The way you configure this is right inside the `createProblem1()` and `createProblem2()` static methods:

```java
HashMap<Algorithm, Integer> numIterationsHashMap = new HashMap<Algorithm, Integer>();
numIterationsHashMap.put(Algorithm.ValueIteration, 100);
numIterationsHashMap.put(Algorithm.PolicyIteration, 20);
numIterationsHashMap.put(Algorithm.QLearning, 1000);
```
Make sure to explore the effect the number of iterations has on the convergence of each algorithm.

## Analysis, analysis, analysis
In the end, this is what matters for this assignment. The code should help to give you some results and charts you can later use in your report. Here is what you'll get after running any of the three algorithms:
```
Episode, Steps, Reward, Time
 1, 30, -4.02, 134
 2, 30, -4.02, 177
 3, 30, -4.02, 201
 4, 24, -3.33, 140
 5, 28, -3.48, 155
 6, 18, -3.06, 157
 7, 16, -2.98, 138
 8, 18, -3.06, 152
 9, 15, -2.75, 130
10, 16, -2.98, 148
...

Average Reward:	-3.37
Average Number of Steps: 23
Minimum Number of Steps: 15	
Average Time (in milliseconds): 153
```
You can then copy this and paste it into a spreadsheet where you can generate as many charts as you consider helpful. 

In the case of Q-Learning, BURLAP offers a `LearningAlgorithmExperimenter` class that display some cool charts (pretty much the same information above, but laid out nicely in charts). To get these charts, make sure you set the `USE_LEARNING_EXPERIMENTER` constant to true.

```java
private static boolean USE_LEARNING_EXPERIMENTER = true;
```
## Good luck!
Not sure what else to put here that helps you get the coding portion of the assignment out of the way and lets you focus exclusively on the analysis. Of course, don't just run this, grab the results and write a report; it will be helpful if you try to understand the code a little bit. Knowing how this works will be a huge help when writing the report.
