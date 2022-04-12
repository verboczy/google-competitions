# Chain Reaction

[Link](https://codingcompetitions.withgoogle.com/codejam/round/0000000000877ba5/0000000000aa8fc1)

## Solution

My basic idea was to partition the numbers the following way. 
Go through the numbers, and add the number to the partition with the smaller sum. 
If the sums of the two partitions are not equal, then the put the minimal number from the greater _(by sum)_ partition to the other. 
Repeat it until the sums are equal.

The idea was not bad, but not fast enough.
I checked the analysis of the problem, then based on that I made the following changes.

1. Generate power of 2 while they are smaller than 10^9.
2. First, partition the numbers given by the judge, then my own numbers.

### Partition example 

#### Step 0

+ Numbers: {1, 2, 3, 4}
+ Partition1: {}
+ Partition2: {}

#### Step 1

+ Numbers: {2, 3, 4}
+ Partition1: {1}
+ Partition2: {}

#### Step 2

+ Numbers: {3, 4}
+ Partition1: {1}
+ Partition2: {2}

#### Step 3

+ Numbers: {4}
+ Partition1: {1, 3}
+ Partition2: {2}

#### Step 4

+ Numbers: {}
+ Partition1: {1, 3}
+ Partition1 sum: 4
+ Partition2: {2, 4}
+ Partition2 sum: 6

#### Step 5

+ Numbers: {}
+ Partition1: {1, 2, 3}
+ Partition1 sum: 6
+ Partition2: {4}
+ Partition2 sum: 4

#### Step 6

+ Numbers: {}
+ Partition1: {2, 3}
+ Partition1 sum: 5
+ Partition2: {1, 4}
+ Partition2 sum: 5

## Experience

+ I was not confident with the interactive problem. I wasted a lot of time, because I was not sure that the communication went well. 
  + I should try the interactive runner program, provided by google.
+ First, I tried to solve an NP complete problem, I should have thought about that thoroughly.
+ If, the problem gives control over something, then there must be something tricky. I think about that in the future.