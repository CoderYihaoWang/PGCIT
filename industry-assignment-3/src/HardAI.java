public class HardAI extends MediumAI {

    @Override
    protected void update(Record feedback) {
        possibleGuesses.removeIf(i ->
                Compute.bulls(i.toString(), feedback.guess) != feedback.bulls
                || Compute.cows(i.toString(), feedback.guess) != feedback.cows
        );
    }
}
