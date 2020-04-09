public class EasyAI implements AI {
    @Override
    public String guess(Record feedback) {
        return Compute.generateRandomGuess();
    }
}
