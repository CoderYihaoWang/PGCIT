/**
 * Name:          Compute
 * Description:   A pure static class
 *                it provides constants and static methods relating to the computation in the game
 * Dependency:
 *
 * Author:        Yihao Wang
 * Last modified: 9/4/2020
 */
public class Compute {
    // length of a secret code
    public static final int LENGTH = 4;

    // max integer value of a valid guess
    public static final int MAX_GUESS = (int)Math.pow(10, LENGTH) - 1;

    // this class should not be instantiated
    private Compute() {}

    /**
     * computes the bulls from a secret code and a guess
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
     * @param secret the secret code
     * @param guess the guess
     * @return the numeber of cows
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
    public static boolean validate(String guess) {
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
    public static String generateRandomGuess() {
        // randomly shuffle the char array, and return the first 4 digits
        // it is guaranteed that 10 random numbers will be generated and the
        // result is a valid code
        char[] arr = "0123456789".toCharArray();
        for (int i = 0; i < arr.length; ++i) {
            int rand = (int)(Math.random() * (arr.length - i)) + i;
            char temp = arr[i];
            arr[i] = arr[rand];
            arr[rand] = temp;
        }
        return new String(arr, 0, LENGTH);
    }

    /**
     * convert a guess code represented by an int to a String
     * @param code code in int
     * @return code in String
     */
    public static String intCodeToString(int code) {
        return String.format("%" + LENGTH + "s", "" + code).replace(' ', '0');
    }
}
