package ictgradschool.industry.assignment01.problems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Run this test to check your answers for Assignment One Part One.
 * Do not modify this class.
 */
public class TestAssignmentOnePartOne {
    private AssignmentOnePartOne assignmentOnePartOne;

    @BeforeEach
    public void setUp() throws Exception {
        assignmentOnePartOne = new AssignmentOnePartOne();
    }

    @Test
    public void testDivideTwoInts() {
        assertEquals(5, assignmentOnePartOne.divideTwoInts(10, 2));
        assertEquals(14, assignmentOnePartOne.divideTwoInts(100, 7));
        assertEquals(0, assignmentOnePartOne.divideTwoInts(0, 2));
    }

    @Test
    public void testMultiplyTwoDoubles() {
        assertEquals(0, assignmentOnePartOne.multiplyTwoDoubles(123.0, 0.0));
        assertEquals(11, assignmentOnePartOne.multiplyTwoDoubles(1.56984, 7.12));
        assertEquals(1, assignmentOnePartOne.multiplyTwoDoubles(5.1, 0.25));
    }

    @Test
    public void testMaxOfTwoNumbers() {
        assertEquals(7, assignmentOnePartOne.maxOfTwoNumbers(4, 7));
        assertEquals(1, assignmentOnePartOne.maxOfTwoNumbers(1, 1));
        assertEquals(0, assignmentOnePartOne.maxOfTwoNumbers(0, -1));
    }

    @Test
    public void testConvertCharToString() {
        assertEquals("a", assignmentOnePartOne.convertCharToString('a'));
        assertEquals("9", assignmentOnePartOne.convertCharToString('9'));
        assertEquals("A", assignmentOnePartOne.convertCharToString('\u0041'));
    }

    @Test
    public void testGetFirstThreeLetters() {
        assertEquals("Hel", assignmentOnePartOne.getFirstThreeLetters("Hello World"));
        assertEquals("123", assignmentOnePartOne.getFirstThreeLetters("12345678"));
        assertEquals("abc", assignmentOnePartOne.getFirstThreeLetters("abcde"));
    }

    @Test
    public void testLegalToBuyDrinks() {
        assertFalse(assignmentOnePartOne.legalToBuyDrinks(17));

        assertTrue(assignmentOnePartOne.legalToBuyDrinks(24));
        assertTrue(assignmentOnePartOne.legalToBuyDrinks(18));
    }

    @Test
    public void testEligibleToVote() {
        assertTrue(assignmentOnePartOne.eligibleToVote(true, 18));

        assertFalse(assignmentOnePartOne.eligibleToVote(false, 40));
        assertFalse(assignmentOnePartOne.eligibleToVote(true, 151));
    }

    @Test
    public void testImplies() {
        assertTrue(assignmentOnePartOne.implies(true, true));
        assertTrue(assignmentOnePartOne.implies(false, true));
        assertTrue(assignmentOnePartOne.implies(false, false));

        assertFalse(assignmentOnePartOne.implies(true, false));
    }

    // Tests for Q9.
    @Test
    public void testIsSubstringSameString() {
        assertEquals("Same string", assignmentOnePartOne.isSubstring("John", "John"));
        assertEquals("Same string", assignmentOnePartOne.isSubstring("University of Auckland", "University of Auckland"));
        assertEquals("Same string", assignmentOnePartOne.isSubstring("12345", "12345"));
    }

    @Test
    public void testIsSubstringNoMatch() {
        assertEquals("No match", assignmentOnePartOne.isSubstring("Auckland", "Hamilton"));
        assertEquals("No match", assignmentOnePartOne.isSubstring("12345", "Thomas"));
        assertEquals("No match", assignmentOnePartOne.isSubstring("t-rex", "REX"));
    }

    @Test
    public void testIsSubstringFirstStrIsSubStr() {
        assertEquals("First string is a substring of second string", assignmentOnePartOne.isSubstring("Ann", "Anna"));
        assertEquals("First string is a substring of second string", assignmentOnePartOne.isSubstring("of", "University of Auckland"));
        assertEquals("First string is a substring of second string", assignmentOnePartOne.isSubstring("  ko ", "pukeko"));
    }

    @Test
    public void testIsSubstringSecondStrIsSubStr() {
        assertEquals("Second string is a substring of first string", assignmentOnePartOne.isSubstring("University of Auckland", "land"));
        assertEquals("Second string is a substring of first string", assignmentOnePartOne.isSubstring("waterbottle", "bot"));
        assertEquals("Second string is a substring of first string", assignmentOnePartOne.isSubstring("auckland ict grad school", "ictgrad"));
    }

    // Test for Q10.
    @Test
    public void testMedianOfThreeInts() {
        assertEquals(0, assignmentOnePartOne.medianOfThreeInts(0, 0, 0));
        assertEquals(5, assignmentOnePartOne.medianOfThreeInts(5, 3 , 7));
        assertEquals(5, assignmentOnePartOne.medianOfThreeInts(3, 5, 7));
        assertEquals(-2, assignmentOnePartOne.medianOfThreeInts(-1, -5, -2));
        assertEquals(0, assignmentOnePartOne.medianOfThreeInts(-3, 7, 0));
    }
}