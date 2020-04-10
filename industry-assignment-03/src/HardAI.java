/**
 * Name:          HardAI
 * Description:   provides the guess method which utilises an pruning strategy
 *                extends the MediumAI class as the code is largely the same, only the strategy to update
 *                the possibleGuesses set is different
 * Dependency:    MediumAI, Record
 *
 * Author:        Yihao Wang
 * Last modified: 9/4/2020
 */
public class HardAI extends MediumAI {
    /**
     * iterate over the possibleGuesses, remove all guesses that does not produce the same result
     * as the last guess
     * @param feedback the result from last guess
     */
    @Override
    protected void update(Record feedback) {
        possibleGuesses.removeIf(code ->
                Compute.bulls(code, feedback.guess) != feedback.bulls
                || Compute.cows(code, feedback.guess) != feedback.cows
        );
    }
}
