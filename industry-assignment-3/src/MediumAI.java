import java.util.*;

public class MediumAI implements AI {

    protected final Set<Integer> possibleGuesses;

    public MediumAI() {
        possibleGuesses = new HashSet<>();

        for (int i = 0; i <= Compute.MAX_GUESS; ++i) {
            if (Compute.validate(i + ""))
                possibleGuesses.add(i);
        }
    }

    @Override
    public String guess(Record feedback) {
        if (feedback == null) {
            return Compute.generateRandomGuess();
        }

        update(feedback);

        int rand = (int)(Math.random() * possibleGuesses.size());
        Iterator<Integer> it = possibleGuesses.iterator();
        Integer guessNum = it.next();
        while (--rand >= 0) {
            guessNum = it.next();
        }

        return guessNum.toString();
    }

    protected void update(Record feedback) {
        possibleGuesses.remove(Integer.parseInt(feedback.guess));
    }
}
