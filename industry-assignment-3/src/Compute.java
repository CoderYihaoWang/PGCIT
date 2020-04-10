/**
 * Name:          Compute
 * Description:   A pure static class
 *                it provides constants and static methods relating to the computation in the game
 * Dependency:
 *
 * Author:        Yihao Wang
 * Last modified: 10/4/2020
 */
public class Compute {
    // length of a secret code
    public static final int LENGTH = 4;

    // max integer value of a valid guess
    public static final int MAX_GUESS = (int)Math.pow(10, LENGTH) - 1;

    // the char array to generate random guesses from
    // stored in a static variable to avoid reconstructing every time
    private static final char[] DIGITS = "0123456789".toCharArray();

    // this class should not be instantiated
    private Compute() {}

    /**
     * computes the bulls from a secret code and a guess
     * this method assumes that the inputs are both valid guess code
     * @param secret the secret code
     * @param guess the guess
     * @return the numeber of bulls
     */
    public static int bulls(String secret, String guess) {
        int bulls = 0;
        for (int i = 0; i < LENGTH; ++i) {
            if (secret.charAt(i) == guess.charAt(i))
                ++bulls;
        }
        return bulls;
    }

    /**
     * computes the number of cows from a secret code and a guess
     * this method assumes that the inputs are both valid guess code
     * @param secret the secret code
     * @param guess the guess
     * @return the number of cows
     */
    public static int cows(String secret, String guess) {
        int cows = 0;
        boolean[] exist = new boolean[10];
        for (char c : secret.toCharArray())
            exist[c - '0'] = true;
        for (int i = 0; i < LENGTH; ++i) {
            if (exist[guess.charAt(i) - '0'] && guess.charAt(i) != secret.charAt(i))
                ++cows;
        }
        return cows;
    }

    /**
     * test whether a String is a valid guess
     * @param guess the String to be tested
     * @return true if the argument is a valid guess, else false
     */
    public static boolean isValid(String guess) {
        if (guess.length() != LENGTH)
            return false;
        boolean[] exist = new boolean[10];
        for (char c : guess.toCharArray()) {
            if (!Character.isDigit(c) || exist[c - '0'])
                return false;
            exist[c - '0'] = true;
        }
        return true;
    }

    /**
     * generate a random valid guess
     * uses an algorithm whose performance does not depend on probability
     * @return a random valid guess
     */
    public static String randomGuess() {
        // randomly shuffle the 0-9 char array, and return the first 4 digits
        // it is guaranteed that 4 random numbers will be generated and the
        // result is a valid code
        shuffle();
        return new String(DIGITS, 0, LENGTH);
    }

    // shuffle the first LENGTH digits in the DIGITS array
    private static void shuffle() {
        for (int i = 0; i < LENGTH; ++i) {
            int rand = (int)(Math.random() * (DIGITS.length - i)) + i;
            char temp = DIGITS[i];
            DIGITS[i] = DIGITS[rand];
            DIGITS[rand] = temp;
        }
    }

    /**
     * convert a guess code represented by an int to a String
     * this method assumes the input is a valid guess code
     * @param code code in int
     * @return code in String
     */
    public static String intCodeToString(int code) {
        return String.format("%" + LENGTH + "s", "" + code).replace(' ', '0');
    }
}
