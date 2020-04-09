public class HardAI extends MediumAI {

    @Override
    protected void update(Record feedback) {
        possibleGuesses.removeIf(code ->
                Compute.bulls(code, feedback.guess) != feedback.bulls
                || Compute.cows(code, feedback.guess) != feedback.cows
        );
    }
}
