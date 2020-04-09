import java.util.*;

public class MediumAI implements AI {

    protected final Set<String> possibleGuesses = new HashSet<>();

    public MediumAI() {
        for (int i = 0; i <= Compute.MAX_GUESS; ++i) {
            String guess = Compute.integerCodeToString(i);
            if (Compute.validate(guess))
                possibleGuesses.add(guess);
        }
    }

    @Override
    public String guess(Record feedback) {
        if (feedback == null) {
            return Compute.generateRandomGuess();
        }

        update(feedback);

        int rand = (int)(Math.random() * possibleGuesses.size());
        Iterator<String> it = possibleGuesses.iterator();
        String guess = it.next();
        while (--rand >= 0) {
            guess = it.next();
        }

        return guess;
    }

    protected void update(Record feedback) {
        possibleGuesses.remove(feedback.guess);
    }
}
