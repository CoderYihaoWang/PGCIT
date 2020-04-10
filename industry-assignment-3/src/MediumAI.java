import java.util.*;

/**
 * Name:          MediumAI
 * Description:   provides the guess method which gives a random guess each time, but with no repeats
 * Dependency:    AI, Record
 *
 * Author:        Yihao Wang
 * Last modified: 9/4/2020
 */
public class MediumAI implements AI {
    // a set maintaining all possible guesses at the moment
    protected final Set<String> possibleGuesses = new HashSet<>();

    public MediumAI() {
        // initialise the possibleGuesses set, put all valid codes in
        for (int i = 0; i <= Compute.MAX_GUESS; ++i) {
            String guess = Compute.intCodeToString(i);
            if (Compute.isValid(guess))
                possibleGuesses.add(guess);
        }
    }

    /**
     * produce a random guess which is guaranteed not the same as the old ones
     * @param feedback the result from last guess
     * @return a random guess which does not repeat previous guesses
     */
    @Override
    public String guess(Record feedback) {
        // could use a algorithm which repeatedly generates random guesses until there is one
        // which has not been used before
        // although this algorithm would be reasonably fast in the current circumstances
        // its performance relies on probability and has no guarantee over how many random guesses will be
        // produced before a non-repeating one occurs
        // the following algorithm provides this guarantee, though at the cost of some extra space

        // the feedback is null if it is the first guess, so just give a random guess
        if (feedback == null)
            return Compute.randomGuess();

        // update the possibleGuesses set based on the feedback
        update(feedback);

        // randomly return a value from the current possibleGuesses set
        int rand = (int)(Math.random() * possibleGuesses.size());
        Iterator<String> it = possibleGuesses.iterator();
        String guess = it.next();
        while (--rand >= 0)
            guess = it.next();
        return guess;
    }

    /**
     * update the current possibleGuesses set based on the feedback
     * @param feedback the result from last guess
     */
    protected void update(Record feedback) {
        // just remove the guess in the feedback
        possibleGuesses.remove(feedback.guess);
    }
}
