/**
 * Name:          EasyAI
 * Description:   provides the guess method which gives a random guess
 * Dependency:    AI, Record
 *
 * Author:        Yihao Wang
 * Last modified: 9/4/2020
 */
public class EasyAI implements AI {
    /**
     * uses a simple random guess strategy to produce the guessed code
     * @param feedback the result from last guess, ignored
     * @return a random guess
     */
    @Override
    public String guess(Record feedback) {
        return Compute.randomGuess();
    }
}
