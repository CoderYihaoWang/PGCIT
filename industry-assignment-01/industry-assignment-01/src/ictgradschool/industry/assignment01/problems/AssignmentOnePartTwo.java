package ictgradschool.industry.assignment01.problems;

import java.util.Arrays;

/**
* Please run TestAssignmentOnePartTwo to check your answers.
* There are 10 exercises in this class. They are ordered roughly in increasing order of difficulty.
* You can do them in any order you like. Each exercise is worth the same amount towards your overall Assignment 1 mark.
* However, you will only get full marks for each exercise on confirmation by the markers that you have in fact
* correctly completed the exercise. It is not enough that you have passed all of the tests.
*
* You may modify the code in between the comments: // Answer here // . Do not modify other parts of the code.
 *
 * Write your name and UPI here: Yihao Wang, wany969.
*/
public class AssignmentOnePartTwo {

    /**
     * Q11. Complete the method countDigits that has an integer parameter, and returns the number of digits the value
     * of the parameter has. For negative values, only count the digits, not the negative sign.
     */
    public int countDigits(int number) {
        // Answer here

        // should not first negate the number if it is negative
        // because -Integer.MIN_VALUE < 0
        return ("" + number).replace("-", "").length();
        //
    }

    /**
     *  Q12. Complete the method isLeapYear that takes a year as an integer, and returns a boolean value:
     *  true if it satisfies the rules to be a leap year, otherwise false.
     *  A year is a leap year if it is divisible by four, except for years which are both divisible by 100 and
     *  not divisible by 400.
     */
    public boolean isLeapYear(int year) {
        // Answer here

        // the following solution is verbatim as a piece of code from K&R's
        // The C Programing Language e2. See Section 2.5
        // It really shocked me when I first read it, so simple, so clear
        // actually the brackets are redundant, && binds tighter than ||
        // but they help clarify the logic
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        //
    }

    /**
     * Q13. Complete the method factorial that takes an integer parameter, and returns the factorial of that value.
     * The factorial of a non-negative integer n, denoted by n!, is the product of all positive integers less than
     * or equal to n. For example, 0! = 1, 1! = 1, 3! = 3*2*1 = 6, 5! = 5*4*3*2*1 = 120,
     * n! = n*(n-1)*(n-2)*...*1. For n < 0, return 0. Marks will be deducted if you use recursion, i.e. calling itself inside the method.
     */
    public int factorial(int n) {
        // Answer here

        // factorial is overflow-prone
        // uses Math.multiplyExact instead of * to detect overflows
        // it throws an ArithmeticException on overflow
        if (n < 0)
            return 0;
        int fac = 1;
        while (n > 1)
            fac = Math.multiplyExact(fac, n--);
        return fac;
        //
    }

    /**
     * Q14. Complete the method isPrime that takes an integer parameter, and returns a boolean value: true if the given
     * integer is a prime number, otherwise false. Note that a prime number has only two exact divisors, 1 and itself.
     * 1 is not a prime number. You may assume the parameter is a positive integer.
     * Hint: the largest potential divisor for a number n is n/2.
     */
    public boolean isPrime(int num) {
        // Answer here
        if (num <= 1)
            return false;
        if (num == 2)
            return true;
        // it is enough to test up to the square root of num
        // the square root is rounded up to avoid rounding errors
        for (int i = 2; i <= Math.ceil(Math.sqrt(num)); ++i)
            if (num % i == 0)
                return false;
        return true;
        //
    }

    /**
     * Q15. Complete the method reverseString that takes a String parameter, and returns the given String in reverse
     * order. You may assume that the String is non-empty.
     */
    public String reverseString(String str) {
        // Answer here

        // for performance
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; --i)
            sb.append(str.charAt(i));
        return sb.toString();
        //
    }

    /**
     * Q16. Complete the method gcd that takes two integer parameters and returns their greatest common divisor. That is,
     * the largest integer that divides both of them with no remainder. You may assume both parameters are positive.
     */
    public int gcd(int num1, int num2) {
        // Answer here

        // it does not say recursion is forbidden

        // Euclidean Algorithm
        // it works as well when num1 < num2
        if (num2 == 0)
            return num1;
        return gcd(num2, num1 % num2);
        //
    }


    /**
     * Q17. Complete the method isIntPalindrome that takes an integer parameter, and returns a boolean value: true if
     * the digits of the given integer are palindromic (i.e. reads the same backwards as forwards). Any leading negative
     * signs should be ignored for the purposes of this exercise.
     */
    public boolean isIntPalindrome(int number) {
        // Answer here

        // remove negative sign
        String str = ("" + number).substring(number < 0 ? 1 : 0);

        for (int i = 0; i < str.length() / 2; ++i)
            if (str.charAt(i) != str.charAt(str.length() - i - 1))
                return false;
        return true;
        //
    }

    /**
     *  Q18. Complete the method sortNumbersByAscending that takes four integers as arguments, and
     *  returns a String of the numbers in ascending order. Note that the numbers are separated by commas with no space.
     *  You may use if statements.
     */
    public String sortNumbersByAscending(int num1, int num2, int num3, int num4) {
        // Answer here
        int[] arr = new int[]{num1, num2, num3, num4};
        Arrays.sort(arr);
        return arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3];

        //  the following is a genius solution modified from
        //      https://algs4.cs.princeton.edu/21elementary/Sort4.java.html
        //  it sorts four elements with only 5 comparisons and exchanges!
        //
        //  -- code begins --
        //  if (num1 > num2) { int t = num2; num2 = num1; num1 = t; }
        //  if (num3 > num4) { int t = num4; num4 = num3; num3 = t; }
        //  if (num1 > num3) { int t = num3; num3 = num1; num1 = t; }
        //  if (num2 > num4) { int t = num4; num4 = num2; num2 = t; }
        //  if (num2 > num3) { int t = num3; num3 = num2; num2 = t; }
        //  return num1 + "," + num2 + "," + num3 + "," + num4;
        //  -- code ends --
        //
        //  And I can prove that this solution is the most optimized, in terms of
        //  numbers of comparisons and exchanges:
        //
        //  The number of possible outcomes of sorting four numbers is 4! = 24
        //  If we draw a decision tree to represent the paths to these outcomes,
        //  with the outcomes being leaves,
        //  then the height of the tree,
        //  which represent numbers comparisons and possible exchanges, is log24,
        //  the smallest integer above log24 is 5
        //  that means you cannot find a solution with fewer comparisons to cover all the possible outcomes
        //
        //
    }

    /**
     * Q19. Complete the method simpleMultiplicationTable that takes an integer parameter, and returns a String showing the multiplication table
     * (rows and columns) starting at 1 and up to and including that number. Any 'cell'  in the table should display the result of
     * multiplying that row number by that column number. For example, the method would return the following String for an integer parameter of 2:
     * 1 2
     * 2 4
     * For an integer parameter of 3, the method would result in a 3x3 table:
     * 1 2 3
     * 2 4 6
     * 3 6 9
     * Hint: Remember that you can nest loops too. To print new line, use "\n".
     */
    public String simpleMultiplicationTable(int num) {
        // Answer here
        if (num < 1)
            throw new IllegalArgumentException("The size must be at least 1");

        StringBuilder table = new StringBuilder();
        for (int i = 1; i <= num; ++i)
            for (int j = 1; j <= num; ++j)
                table.append(i * j).append(j == num ? '\n' : ' ');
        table.setLength(table.length() - 1);
        return table.toString();
        //
    }

    /**
     *  Q20. Complete the method printPrimeNumbers that takes an integer parameter and returns a String containing a space separated list of all of the
     *  prime numbers starting at 2 and all the way up to and including the given integer. If no prime numbers are found, return "No prime number found".
     *  Hint: you can reuse code from another question (that is make use of method calls) to complete this question.
     *  Note that there is no extra space at the end of the String returned.
     */
    public String printPrimeNumbers(int num) {
        // Answer
        if (num < 2)
            return "No prime number found";

        // sieve of Eratosthenes
        StringBuilder primes = new StringBuilder();
        boolean[] visited = new boolean[num + 1];
        for (int i = 2; i <= num; ++i) {
            if (!visited[i]) {
                primes.append(i).append(' ');
                for (int j = i + i; j <= num; j += i)
                    visited[j] = true;
            }
        }
        primes.setLength(primes.length() - 1);
        return primes.toString();
        //
    }
}
