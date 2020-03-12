Challenge 02
==========
This challenge is worth **1%** of the final grade.

## Exercise One: Using a loop to rotate an array of ints
Write a program so that it rotates a given array of ints. Here is an example of the output of the program:
```text
Before: 4, 5, 1, 6, 2|6, 1, 0|9, 3, 1, 5, 2, 0, 4, 6, 7, 8
After being rotated:
5, 1, 6, 2, 4|1, 0, 6|3, 1, 5, 2, 0, 4, 6, 7, 8, 9
```
The program prompts the user to enter multiple arrays of ints, separated by '|'. The program converts the user input from a String to multiple arrays of ints. It has a method that has an array of ints as a parameter. The method moves every element in the array one position to the left; i.e. the value in element 1 is moved to element 0, the value in element 2 is stored in element 1, and so on. Finally, the value in the first element is moved around to the last position.

You must use a for loop to rotate the array. Note that you will need to store the value of element 0 in a temporary variable so that you can later move it into the last element once all the other elements have been moved.

Write your program in `intarrayrotation.IntArrayRotation.java`