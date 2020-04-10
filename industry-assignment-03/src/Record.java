/**
 * Name:          Record
 * Description:   A data only class, representing the result of one guess,
 *                convenient to be used as the arguments or return value
 * Dependency:
 *
 * Author:        Yihao Wang
 * Last modified: 9/4/2020
 */
public class Record {
    public final String guess;
    public final int bulls;
    public final int cows;

    public Record(String secret, String guess) {
        this.guess = guess;
        this.bulls = Compute.bulls(secret, guess);
        this.cows = Compute.cows(secret, guess);
    }
}
