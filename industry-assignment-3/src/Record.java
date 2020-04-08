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
