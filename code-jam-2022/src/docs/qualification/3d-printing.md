# 3D Printing

[Link](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4672b)

## Solution

I examine the minimum level for all color, in all printer. 
After I have the minimum levels, I check if reaches the minimum required level. 
It not, then the printing is IMPOSSIBLE. 
Else, I go through C, M, Y, K and choose min(level, needed) amount of an ink, where level is the ink level of the given color, and needed is needed amount of ink to complete the printing.

For example: C=10, M=10, Y=10, K=10, required ink to print: 25

+ Step 1: C - min(10, 25) -> 10. 
+ Step 2: Decrease needed amount by 10.
+ Step 3: M - min(10, 15) -> 10
+ Step 4: Decrease needed amount by 10.
+ Step 5: Y - min(10, 5) -> 5
+ Step 6: Decrease needed amount by 5.
+ Step 7: We provided the required ink amount: C=10, M=10, Y=5, K=0