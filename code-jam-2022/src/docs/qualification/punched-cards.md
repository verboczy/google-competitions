# Punched Cards

[Link](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4621b)

## Solution

The cards follow a give pattern. 
It always has at least two rows. 
The first two row is different from the others, because of its first column. 
After the first two rows every even row looks  the same, and every odd row looks the same.
So, there are four types of rows:
+ first row, 
+ second row, 
+ odd row, 
+ even row.

I create all four types once, and then append the appropriate row to solution when creating the output. 