package ictgradschool.industry.assignment01.problems;

import ictgradschool.industry.assignment01.problems.AssignmentOnePartTwo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Run this test to check your answers for Assignment One Part Two.
 * Do not modify this class.
 */
public class TestAssignmentOnePartTwo {
    private AssignmentOnePartTwo assignmentOnePartTwo;

    @BeforeEach
    public void setUp() throws Exception {
        assignmentOnePartTwo = new AssignmentOnePartTwo();
    }

    @Test
    public void testCountDigits() {
        assertEquals(1, assignmentOnePartTwo.countDigits(0));
        assertEquals(1, assignmentOnePartTwo.countDigits(1));
        assertEquals(2, assignmentOnePartTwo.countDigits(17));
        assertEquals(1, assignmentOnePartTwo.countDigits(-1));
        assertEquals(10, assignmentOnePartTwo.countDigits(1234567890));
        assertEquals(10, assignmentOnePartTwo.countDigits(-1234567890));
    }

    @Test
    public void testIsLeapYear() {
        assertTrue(assignmentOnePartTwo.isLeapYear(0));
        assertTrue(assignmentOnePartTwo.isLeapYear(-44));
        assertTrue(assignmentOnePartTwo.isLeapYear(1984));
        assertTrue(assignmentOnePartTwo.isLeapYear(2000));

        assertFalse(assignmentOnePartTwo.isLeapYear(1900));
        assertFalse(assignmentOnePartTwo.isLeapYear(1));
        assertFalse(assignmentOnePartTwo.isLeapYear(1993));

    }

    @Test
    public void testFactorial() {
        assertEquals(1, assignmentOnePartTwo.factorial(0));
        assertEquals(1, assignmentOnePartTwo.factorial(1));
        assertEquals(2, assignmentOnePartTwo.factorial(2));
        assertEquals(5040, assignmentOnePartTwo.factorial(7));
        assertEquals(0, assignmentOnePartTwo.factorial(-10));
    }

    @Test
    public void testIsPrime() {
        assertTrue(assignmentOnePartTwo.isPrime(2));
        assertTrue(assignmentOnePartTwo.isPrime(3));
        assertTrue(assignmentOnePartTwo.isPrime(499));
        assertTrue(assignmentOnePartTwo.isPrime(2749));

        assertFalse(assignmentOnePartTwo.isPrime(10));
        assertFalse(assignmentOnePartTwo.isPrime(1));
        assertFalse(assignmentOnePartTwo.isPrime(3170));
    }

    @Test
    public void testReverseString() {
        assertEquals("cba", assignmentOnePartTwo.reverseString("abc"));
        assertEquals("!dlroW olleH", assignmentOnePartTwo.reverseString("Hello World!"));
        assertEquals("16320101", assignmentOnePartTwo.reverseString("10102361"));
        assertEquals("z", assignmentOnePartTwo.reverseString("z"));
    }

    @Test
    public void testGcd() {
        assertEquals(1, assignmentOnePartTwo.gcd(1,1));
        assertEquals(2, assignmentOnePartTwo.gcd(2,4));
        assertEquals(3, assignmentOnePartTwo.gcd(15,6));
        assertEquals(6, assignmentOnePartTwo.gcd(6,12));
    }

    @Test
    public void testIsIntegerPalindrome() {
        assertTrue(assignmentOnePartTwo.isIntPalindrome(101));
        assertTrue(assignmentOnePartTwo.isIntPalindrome(-101));
        assertTrue(assignmentOnePartTwo.isIntPalindrome(3));
        assertTrue(assignmentOnePartTwo.isIntPalindrome(88));
        assertTrue(assignmentOnePartTwo.isIntPalindrome(0));

        assertFalse(assignmentOnePartTwo.isIntPalindrome(2369));
        assertFalse(assignmentOnePartTwo.isIntPalindrome(2012));
        assertFalse(assignmentOnePartTwo.isIntPalindrome(1234));
    }

    @Test
    public void testSortNumberAscending() {
        assertEquals("-4,6,7,35", assignmentOnePartTwo.sortNumbersByAscending(35, -4, 7, 6));
        assertEquals("-10,-1,0,18", assignmentOnePartTwo.sortNumbersByAscending(-1, 0, 18, -10));
        assertEquals("1,2,3,4", assignmentOnePartTwo.sortNumbersByAscending(1, 2, 3, 4));

    }

    @Test
    public void testSimpleMultiplicationTable() {
        String result = assignmentOnePartTwo.simpleMultiplicationTable(1);
        if (result.contains(" \n")) {
            fail("Your output table has one or more extra spaces before the newline character. You can use the trim() function to remove additional spaces");
        }
        assertEquals("1", result);

        result = assignmentOnePartTwo.simpleMultiplicationTable(2);
        if (result.contains(" \n")) {
            fail("Your output table has one or more extra spaces before the newline character. You can use the trim() function to remove additional spaces");
        }
        assertEquals("1 2\n2 4", result);

        final String timesSeven = "1 2 3 4 5 6 7\n"
                + "2 4 6 8 10 12 14\n"
                + "3 6 9 12 15 18 21\n"
                + "4 8 12 16 20 24 28\n"
                + "5 10 15 20 25 30 35\n"
                + "6 12 18 24 30 36 42\n"
                + "7 14 21 28 35 42 49";

        result = assignmentOnePartTwo.simpleMultiplicationTable(7);
        if (result.contains(" \n")) {
            fail("Your output table has one or more extra spaces before the newline character. You can use the trim() function to remove additional spaces");
        }
        assertEquals(timesSeven, result);
    }

    @Test
    public void testPrintPrimeNumbers() {
        assertEquals("2 3 5 7 11 13 17 19", assignmentOnePartTwo.printPrimeNumbers(20));
        assertEquals("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101", assignmentOnePartTwo.printPrimeNumbers(101));
        assertEquals("2 3 5 7", assignmentOnePartTwo.printPrimeNumbers(8));

        assertEquals("No prime number found", assignmentOnePartTwo.printPrimeNumbers(1));
        assertEquals("No prime number found", assignmentOnePartTwo.printPrimeNumbers(0));
        assertEquals("No prime number found", assignmentOnePartTwo.printPrimeNumbers(-1));
    }

}