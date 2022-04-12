# Double or One Thing

[Link](https://codingcompetitions.withgoogle.com/codejam/round/0000000000877ba5/0000000000aa8e9c)

## Solution

There are four basic cases.

1. The character is the last character.
2. The character is smaller than the next one.
3. The character is greater than the next one.
4. The character is equal to the next one.

### Case 1

We do not duplicate the character, because that would make the result greater.

#### Example

Input: a

We must not duplicate the 'a' character, because _aa > a_.

### Case 2

We have to duplicate the character, because this way the result will be alphabetically smaller.

#### Example

Input: ab

We have to duplicate the 'a' character, because _aab < ab_.

### Case 3

We do not duplicate the character, because that would make the result greater.

#### Example

Input: ba

We must not duplicate the 'b' character, because _bba > ba_.

### Case 4

This is the most complicated case. 
We have to check for the next different character. 
Depending on that character we should or should not duplicate the character.

#### Example 1

If the next different character is greater, than we duplicate.

Input: aaaab

We have to duplicate all 'a' character, because that pushes 'b' the furthest: _aaaaaaaab < aaaab_.

#### Example 2

If the next different character is smaller, than we don't duplicate.

Input: bbbba

We must not duplicate any 'b' character, because _bbbba < bbbbba_.

#### Example 3

If there is no next different character, we do not duplicate.

Input: aaaa

We must not duplicate any 'a' character, because _aaaa < aaaaa_.

## Experience

+ At first, I almost forgot about case 4, but fortunately the samples contained a case for that.
+ Automated tests, with some own case would have made the progress faster.