# CS7641 - Machine Learning - Assignment 4 - Markov Decision Processes

We are encouraged to grab, take, copy, borrow, steal (or whatever other form you can come up with) the code to run our experiments and focus all of our time doing analysis. Hopefully, this code will help others do that.

For Assignment 4, we need to come up with two interesting MDPs, one with a small number of steps, and the other with a large number of steps. While "small" and "large" are largerly subjective terms, this code uses an 8x8 grid map as the "small" example, and a 21x21 grid map as the "large" one. You are encouraged to change these examples and make something different and interesting out of them. If you don't, then your report will probably look very similar to others and you might get in trouble. Be creative.

## Running the project

The simplest way to get this thing up and running is by using [Eclipse](http://www.eclipse.org/). Of course you can also use _your-sweatheart-tool_, but Eclipse will get you there faster because that's what I used and uploaded to this repository.

Clone this repository, and import the project into Eclipse. Then you need to update the project using Maven (which should be conveniently added as an option when you right click your project inside Eclipse). Maven will download [Burlap](http://burlap.cs.brown.edu/) and a bunch of other things that come with it. Maven will literally save you 3 hours of library-chasing, so embrace it and make it work. 

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

For example, here is the "code" representation of a map followed by a more "readable" version of it:

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
![An example map](https://github.com/svpino/cs7641-assignment4/blob/master/images/map.png)

### Dealing with rewards
All those hazards are just rewards (negative rewards, in this case) that you can setup however you like. Out of the box, you'll get that small hazards are worth `-1.0`, medium hazards are worth `-2.0`, and large hazards are worth `-3.0`.

Play with these values and you'll see how your agent will react to those changes by trying to get as far away as possible from large penalties. You can change the optimal policy by just making small adjustments to these rewards.

Make your changes where you see the following:

```
hazardRewardsHashMap.put(HazardType.SMALL, -1.0);
hazardRewardsHashMap.put(HazardType.MEDIUM, -2.0);
hazardRewardsHashMap.put(HazardType.LARGE, -3.0);
```
Yes, you can set up different rewards for each problem. That's why I have two separate methods to deal with all of these.

Finally, don't forget the specific reward for reaching the goal state. Out of the box you get a positive `10`, but feel free to change it (of course, keep it positive unless you want your agent avoiding reaching the goal). You also need to worry about the default reward (a reward attached to any blank cell where the agen can move). You want to make this a negative value (out of the box you get `-0.1`) so the agent has some incentive to stop wandering around. You can change both of these values using the `Problem` constructor: 

```
return new Problem(map, numIterationsHashMap, -0.1, 10, hazardRewardsHashMap);
```

### The algorithms
TBD

## Analysis, Analysis, Analysis
TBD

