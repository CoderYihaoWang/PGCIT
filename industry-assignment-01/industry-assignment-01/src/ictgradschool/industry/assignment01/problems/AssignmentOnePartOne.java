package ictgradschool.industry.assignment01.problems;

/**
 * Please run TestAssignmentOnePartOne to check your answers.
 * There are 10 exercises in this class. They are ordered roughly in increasing order of difficulty.
 * You can do them in any order you like. Each exercise is worth the same amount towards your overall Assignment 1 mark.
 * However, you will only get full marks for each exercise on confirmation by the markers that you have in fact
 * correctly completed the exercise. It is not enough that you have passed all of the tests.
 *
 * You may modify the code in between the comments: // Answer here // . Do not modify other parts of the code.
 *
 * Write your name and UPI here: Yihao Wang, wany969.
 */
public class AssignmentOnePartOne {

    /**
     * Q1. Complete the method divideTwoInts, that will, when given two integer parameters,
     * return the quotient, which is also an integer.
     */
    public int divideTwoInts(int dividend, int divisor) {
        int quotient = 0;
        // Answer here

        // allows division by zero error to happen naturally
        quotient = dividend / divisor;
        //
        return quotient;
    }

    /**
     * Q2. Complete the method called multiplyTwoDoubles that will, when given two double parameters,
     * return their product as an integer.
     */
    public int multiplyTwoDoubles(double multiplicand, double multiplier) {
        int product = 0;
        // Answer here

        // using Math.round makes more sense than a direct casting,
        // the latter does not provide the commonly expected result
        // the provided test cases, however, are not sufficient to decide whether to truncate or round
        product = (int)Math.round(multiplicand * multiplier);
        //
        return product;
    }

    /**
     * Q3. Complete the method maxOfTwoNumbers that takes two integer parameters, and returns the larger of the two
     * (or either of them if they are the same). Do not use if statements. Hint: Use Math.max()
     */
    public int maxOfTwoNumbers(int numberOne, int numberTwo) {
        // Answer here
        return Math.max(numberOne, numberTwo);
        //
    }

    /**
     * Q4. Complete the method convertCharToString that takes a character parameter and returns a String consisting of
     * just that character. Hint: Understand how string concatenation works in Java.
     */
    public String convertCharToString(char character) {
        // Answer here
        return "" + character;
        //
    }

    /**
     * Q5. Complete the method getFirstThreeLetters that takes a String parameter, and returns the first three letters of the String.
     * You may assume that the String is always non-empty and always has more than three letters.
     */
    public String getFirstThreeLetters(String text) {
        // Answer here
        return text.substring(0, 3);
        //
    }

    /**
     * Q6. Complete the method legalToBuyDrinks that takes an integer parameter representing a person's age
     * and returns a boolean value: true if the person is old enough to buy alcohol in New Zealand (minimum age 18)
     * otherwise false.
     */
    public boolean legalToBuyDrinks(int age) {
        // Answer here
        return age >= 18;
        //
    }

    /**
     * Q7. Complete the method eligibleToVote that takes a boolean parameter representing if a person is a
     * New Zealand resident, and an integer parameter representing a person's age. The method returns a boolean value:
     * true if the person is a New Zealand resident and is between the age of 18 and 150 inclusively, otherwise false.
     */
    public boolean eligibleToVote(boolean nzResident, int age) {
        // Answer here
        return nzResident && age >= 18 && age <= 150;
        //
    }

    /**
     * Q8. Complete the method called implies that takes two boolean parameters a and b,
     * and returns the result of the boolean expression a => b. That is, if a is true and b is true, the result is true.
     * If a is false, the result is true. Otherwise, the result is false.
     */
    public boolean implies(boolean a, boolean b) {
        //Answer here

        // a -> b  |=|  !a || b
        return !a || b;
        //
    }

    /**
     * Q9. Complete the method isSubstring that takes two String parameters representing two strings,
     * and returns a String value:
     * "Same string" if the two strings match exactly, or
     * "First string is a substring of second string", or
     * "Second string is a substring of first string, otherwise "No match".
     *
     * Note that this method is case sensitive and ignores spaces.
     */
    public String isSubstring(String firstStr, String secondStr) {
        //Answer here

        // removing white spaces
        firstStr = firstStr.replaceAll("\\s+", "");
        secondStr = secondStr.replaceAll("\\s+", "");

        if (firstStr.equals(secondStr))
            return "Same string";
        if (firstStr.contains(secondStr))
            return "Second string is a substring of first string";
        if (secondStr.contains(firstStr))
            return "First string is a substring of second string";
        return "No match";
        //
    }

    /**
     * Q10. Complete the method medianOfThreeInts that takes three integer parameters, and
     * returns the median of the three values. That is, the method returns the second largest (or
     * second smallest) of the three values.
     */
    public int medianOfThreeInts(int numOne, int numTwo, int numThree) {
        // Answer here

        // could just put the three numbers in an array and Arrays.sort it
        // but in fact, java's library sorting algorithm itself relies on an algorithm
        // to find the median of three medians
        // obviously if we are trying to write that piece of code, we cannot
        // rely on any exiting sorting methods, that would be infinite recursion

        // first sort numOne and numTwo into ascending order
        if (numOne > numTwo) {
            int temp = numOne;
            numOne = numTwo;
            numTwo = temp;
        }

        // if numThree is between numOne and numTwo, it is the median
        if (numThree > numOne && numThree < numTwo)
            return numThree;

        // if numThree is the smallest, numOne is the median
        if (numThree <= numOne)
            return numOne;

        // otherwise numThree is the largest, and median is numTwo
        return numTwo;
        //
    }
}
