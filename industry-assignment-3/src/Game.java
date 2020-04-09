import java.io.*;
import java.util.*;

import ictgradschool.industry.Keyboard;

public class Game {
    private static final int MAX_TURNS = 7;
    private static final String PATH = "./data/";
    private final AI ai;
    private final Queue<Record> history = new LinkedList<>();
    private Queue<String> fileGuesses = new LinkedList<>();
    private String userSecret;
    private String aiSecret;
    private int winner;
    private Record computerLastGuess;

    public Game(AI ai) {
        this.ai = ai;
    }

    public void run() {
        tryLoadFile();
        userSecret = getUserSecretCode();
        aiSecret = Compute.generateRandomGuess();
        winner = play(0, true);
        showWinner();
        trySaveFile();
    }

    private void tryLoadFile() {
        String input = Console.getInput(
                "Would you like to load guesses from a file?"
                , new String[]{"y", "n"}
                , new String[]{"yes, please load guesses from a file", "no, I will play this game manually"}
        );
        if (input.equals("n"))
            return;
        loadFile();
    }

    private void loadFile() {
        boolean loadSuccessful = false;
        while (!loadSuccessful) {
            System.out.println("Please input a file to read from, or 'q' to continue game manually");
            String input = Keyboard.readInput();
            if (input.toLowerCase().equals("q"))
                return;
            try (Scanner scanner = new Scanner(new File(PATH + input))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (!Compute.validate(line)) {
                        System.out.println("The file is not in recognized format, please use another file");
                        fileGuesses.clear();
                        continue;
                    }
                    fileGuesses.add(line);
                }
                loadSuccessful = true;
            } catch (FileNotFoundException e) {
                System.out.println("The file does not exist, please try again");
            }
        }
    }

    private String getUserSecretCode() {
        return getCode("Please enter a secret code: ");
    }

    private int play(int turn, boolean isUserTurn) {
        if (turn == MAX_TURNS)
            return 0;
        if (isUserTurn) {
            System.out.println("-----");
            System.out.println("Turn " + (turn + 1));
        }
        String secret = isUserTurn ? aiSecret : userSecret;
        String guess = isUserTurn ? getUserGuess() : getAiGuess();

        Record record = new Record(secret, guess);
        showResults(record);
        System.out.println();

        history.add(record);
        if (!isUserTurn)
            computerLastGuess = record;

        if (record.bulls == Compute.LENGTH) {
            return isUserTurn ? 1 : -1;
        }

        return isUserTurn ? play(turn, false) : play(turn + 1, true);
    }

    private String getUserGuess() {
        String guess;
        if (!fileGuesses.isEmpty()) {
            guess = fileGuesses.poll();
            System.out.println("You guess: " + guess);
            return guess;
        }

        return getCode("You guess: ");
    }

    private String getAiGuess() {
        String guess;
        if (history.size() < 2)
            guess = ai.guess(null);
        else
            guess = ai.guess(computerLastGuess);
        System.out.println("Computer guess: " + guess);
        return guess;
    }

    private void showResults(Record record) {
        System.out.println(
                "Result: " + record.bulls + (record.bulls == 1 ? " bull " : " bulls ")
                + "and " + record.cows + (record.cows == 1 ? " cow" : " cows")
        );
    }

    private void showWinner() {
        if (winner == 1) {
            System.out.println("You win!");
        } else if (winner == -1) {
            System.out.println("Computer wins!");
        } else {
            System.out.println("Attempts are up, nobody wins...");
        }
    }

    private void trySaveFile() {
        String input = Console.getInput(
                "Would you like to save the results to a file?"
                , new String[]{"y", "n"}
                , new String[]{"yes, please save them to a file", "no, just exit"}
        );
        if (input.equals("n"))
            return;
        saveFile();
    }

    private void saveFile() {
        System.out.println("Please enter a file name");
        String filename = Keyboard.readInput();

        try {
            File file = new File(PATH + filename);
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            boolean isUserTurn = true;
            int turn = 0;
            pw.println("Bulls and Cows Game Result:");
            pw.println("Your code: " + userSecret);
            pw.println("Compute code: " + aiSecret);
            for (Record record : history) {
                if (isUserTurn) {
                    pw.println("-----");
                    pw.println("Turn " + ++turn + ":");
                }
                pw.print(isUserTurn ? "You guessed " : "Computer guessed ");
                pw.println(String.format(
                        "%s, scoring %d bulls and %d cows"
                        , record.guess, record.bulls, record.cows
                ));
                isUserTurn = !isUserTurn;
            }
            if (winner == 1) {
                pw.println("You won");
            } else if (winner == -1) {
                pw.println("Computer won");
            } else {
                pw.println("The game ended in a draw");
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getCode(String prompt) {
        System.out.print(prompt);
        String code = Keyboard.readInput();
        while (!Compute.validate(code)) {
            System.out.println("Sorry, invalid code, please try again");
            code = Keyboard.readInput();
        }
        return code;
    }
}
