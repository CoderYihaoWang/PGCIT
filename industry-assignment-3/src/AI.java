/**
 * Name:          AI
 * Description:   requires the method guess which returns a guess code
 *                it takes the result from the last guess as the argument,
 *                this can be used to produce the next result
 * Dependency:    Record
 *
 * Author:        Yihao Wang
 * Last modified: 9/4/2020
 */
public interface AI {
    /**
     * produce an AI guess
     * @param feedback the result from last guess
     * @return a String representing the guessed code
     */
    String guess(Record feedback);
}
