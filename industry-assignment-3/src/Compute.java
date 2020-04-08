public class Compute {
    public static final int LENGTH = 4;
    public static final int MAX_GUESS = (int)Math.pow(10, LENGTH) - 1;

    private Compute() {}

    public static int bulls(String secret, String guess) {
        int bulls = 0;
        for (int i = 0; i < LENGTH; ++i) {
            if (secret.charAt(i) == guess.charAt(i))
                ++bulls;
        }
        return bulls;
    }

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
}
