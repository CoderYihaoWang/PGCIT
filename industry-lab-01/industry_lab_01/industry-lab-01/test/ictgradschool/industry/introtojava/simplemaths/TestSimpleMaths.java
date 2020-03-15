package ictgradschool.industry.introtojava.simplemaths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

/**
 * This code will test each of your methods you write for Exercise Three, and tell you if they're correct.
 *
 * Don't worry too much about how this works at the moment - you'll learn unit testing later on in the course :)
 */
public class TestSimpleMaths {

    private static final double DELTA = 1e-5;
    private SimpleMaths cr;

    private PipedInputStream pIn;
    private PipedOutputStream pOut;
    private BufferedReader reader;

    private PrintStream oldOut;

    @BeforeEach
    public void setup() throws IOException {
        cr = new SimpleMaths();

        oldOut = System.out;

        // Hack so we can read things that you print using System.out.println
        pIn = new PipedInputStream();
        pOut = new PipedOutputStream(pIn);
        System.setOut(new PrintStream(pOut));
        reader = new BufferedReader(new InputStreamReader(pIn));

    }

    @AfterEach
    public void tearDown() throws IOException {

        System.setOut(oldOut);
        reader.close();
        pOut.close();

    }

    @Test
    public void testKilogramsToPounds() throws IOException {

        assertEquals(121.2541, cr.kilogramsToPounds(55), DELTA);

        assertEquals(220.462, cr.kilogramsToPounds(100), DELTA);

        assertEquals(0.0, cr.kilogramsToPounds(0), DELTA);

    }

    @Test
    public void testCelsiusToFahrenheit() throws IOException {

        assertEquals(67.1, cr.convertCelsiusToFahrenheit(19.5), DELTA);

        assertEquals(8.96, cr.convertCelsiusToFahrenheit(-12.8), DELTA);

        assertEquals(32.0, cr.convertCelsiusToFahrenheit(0.0), DELTA);
    }

    @Test
    public void testCompoundInterestValue() throws IOException {

        assertEquals(7294.3937265102195, cr.getCompoundInterestValue(2500.00, 5.5, 20), DELTA);

        assertEquals(104.319, cr.getCompoundInterestValue(100.50, 3.8, 1), DELTA);

        assertEquals(228775.77567999996, cr.getCompoundInterestValue(100000, 18.0, 5), DELTA);


    }

    @Test
    public void testBMI() throws IOException {

        assertEquals(17.81749403799238, cr.getMyBMI(65.00, 1.91), DELTA);

        assertEquals(22.8807257584771, cr.getMyBMI(61.54, 1.64), DELTA);

        assertEquals(38.98532037294188, cr.getMyBMI(78.61, 1.42), DELTA);


    }

    @Test
    public void testSphereVolume() throws IOException {

        assertEquals(0.0, cr.getSphereVolume(0), DELTA);

        assertEquals(91.95232257547082, cr.getSphereVolume(2.8), DELTA);

        assertEquals(735618.5806037667, cr.getSphereVolume(56), DELTA);
    }

    @Test
    public void testDaysToWeeksAndDays() throws IOException {

        cr.convertTotalDaysIntoWeeksAndDays(0); // This is 0 weeks and 0 days.
        assertEquals("This is 0 weeks and 0 days.", reader.readLine());

        cr.convertTotalDaysIntoWeeksAndDays(25); // This is 3 weeks and 4 days.
        assertEquals("This is 3 weeks and 4 days.", reader.readLine());

        cr.convertTotalDaysIntoWeeksAndDays(14); // This is 2 weeks and 0 days.
        assertEquals("This is 2 weeks and 0 days.", reader.readLine());

        cr.convertTotalDaysIntoWeeksAndDays(3); // This is 0 weeks and 3 days.
        assertEquals("This is 0 weeks and 3 days.", reader.readLine());

    }

    @Test
    public void testFindSmallerInteger() throws IOException {

        cr.findSmallerInteger(1, 2); // 1
        assertEquals("1", reader.readLine());

        cr.findSmallerInteger(-10, -1000); // -1000
        assertEquals("-1000", reader.readLine());

        cr.findSmallerInteger(1, 1); // 1
        assertEquals("1", reader.readLine());


    }
}