# CS7641 - Machine Learning - Assignment 4 - Markov Decision Processes

We are encouraged to grab, take, copy, borrow, steal (or whatever other form you can come up with) the code to run our experiments and focus all of our time doing analysis. Hopefully, this code will help others do that.

For Assignment 4, we need to come up with two interesting MDPs, one with a small number of steps, and the other with a large number of steps. While "small" and "large" are largerly subjective terms, this code uses an 8x8 grid map as the "small" example, and a 21x21 grid map as the "large" one. You are encouraged to change these examples and make something different and interesting out of them. If you don't, then your report will probably look very similar to others and you might get in trouble. Be creative.

## Running the project

The simplest way to get this thing up and running is by using [Eclipse](http://www.eclipse.org/). Of course you can also use _your-sweatheart-tool_, but Eclipse will get you there faster because that's what I used and uploaded to this repository.

Clone this repository, and import the project into Eclipse. Then you need to update the project using Maven (which should be conveniently added as an option when you right click your project inside Eclipse). Maven will download [Burlap](http://burlap.cs.brown.edu/) and a bunch of other things that come with it. Maven will literally save you 3 hours of library-chasing, so embrace it and make it work. 

As soon as all error indicators are gone, you can run the Main.java class from inside Eclipse. That's pretty much it.

## What can you do with this thing?

Start by changing the surface of the problems. Try and be creative and come up with something cool. This shouldn't be too hard. Find the `createProblem1()` and `createProblem2()` static methods and start playing with the `map` array inside them. Here are the rules to describe the surface:

 * `X` — The starting point of the agent.
 * `0` — Represents a safe cell where the agent can move.
 * `1` — Represents a wall. The agent can't move to this cell.
 * `G` — Represents the goal that the agent wants to achieve.
 * `S` — Represents a small hazard. The agent will be penalized.
 * `M` — Represents a medium hazard. The agent will be penalized.
 * `L` — Represents a large hazard. The agent will be penalized.

For example, the following surface:

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

Represents the following map:

