import java.io.*;
import java.util.*;

import ictgradschool.industry.Keyboard;

/**
 * Name:          Game
 * Description:   Represent a Bulls and Cows Game
 *                it controls the logic of the game
 * Dependency:    AI, Console, Compute, Record, Keyboard
 *
 * Author:        Yihao Wang
 * Last modified: 9/4/2020
 */
public class Game {
    // the max number of attempts
    private static final int MAX_TURNS = 7;

    // the path to save and read files
    private static final String PATH = "./data/";

    // the AI instance, deciding the computer's guessing strategy
    private final AI ai;

    // the track of previous guesses, used to save to files
    private final Queue<Record> history = new LinkedList<>();

    // the guesses loaded from files
    private final Queue<String> fileGuesses = new LinkedList<>();

    // user's secret code
    private String userCode;

    // computer's secret code
    private String aiCode;

    // an enum representing the winner
    private Winner winner;
    private enum Winner {USER, AI, DRAW}

    // the computer's last guess, used to provide feedback to AI
    private Record aiLastGuess;

    // inject the AI to the Game
    public Game(AI ai) {
        this.ai = ai;
    }

    /**
     * start point of the game, controls the game logic
     */
    public void run() {
        tryLoadFile();

        userCode = getUserCode();
        aiCode = Compute.generateRandomGuess();

        winner = play(0, true);

        showWinner();

        trySaveFile();
    }

    // asks the user whether to load guesses from a file
    // if so, then load
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

    // get the file name, and load guesses from the file
    // asks the user to input again if the file does not exist
    // or the file is in invalid format
    private void loadFile() {
        boolean loadSuccessful = false;
        while (!loadSuccessful) {
            System.out.println("Please input a file to read from, or 'q' to continue game manually");
            System.out.println("only the file name with no paths, the file should be put under the \"./data\" directory");
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

    private Winner play(int turn, boolean isUserTurn) {
        if (turn == MAX_TURNS)
            return Winner.DRAW;

        if (isUserTurn) {
            System.out.println("-----");
            System.out.println("Turn " + (turn + 1));
        }
        String secret = isUserTurn ? aiCode : userCode;
        String guess = isUserTurn ? getUserGuess() : getAiGuess();

        Record record = new Record(secret, guess);
        showResults(record);
        System.out.println();

        history.add(record);
        if (!isUserTurn)
            aiLastGuess = record;

        if (record.bulls == Compute.LENGTH) {
            return isUserTurn ? Winner.USER : Winner.AI;
        }

        return isUserTurn ? play(turn, false) : play(turn + 1, true);
    }

    // get the user secret code
    private String getUserCode() {
        return getCode("Please enter a secret code: ");
    }

    // first try to get the guess from the file guesses,
    // if it is already empty, then asks the user to input a guess
    private String getUserGuess() {
        String guess;
        if (!fileGuesses.isEmpty()) {
            guess = fileGuesses.poll();
            System.out.println("You guess: " + guess);
            return guess;
        }

        return getCode("You guess: ");
    }

    // get the guess from AI, pass the last result as the feedback
    private String getAiGuess() {
        String guess = ai.guess(aiLastGuess);
        System.out.println("Computer guess: " + guess);
        return guess;
    }

    // display the result
    private void showResults(Record record) {
        System.out.println(
                "Result: " + record.bulls + (record.bulls == 1 ? " bull " : " bulls ")
                + "and " + record.cows + (record.cows == 1 ? " cow" : " cows")
        );
    }

    // display the winner information
    private void showWinner() {
        switch (winner) {
            case USER:
                System.out.println("You win!");
                break;
            case AI:
                System.out.println("Computer wins!");
                break;
            case DRAW:
                System.out.println("Attempts are up, nobody wins...");
        }
        System.out.println("Your code is: " + userCode);
        System.out.println("Computer code is: " + aiCode);
    }

    // asks the user whether to save the game to file, if so, save it
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

    // asks the user for a file name, and save the history to it
    private void saveFile() {
        System.out.println("Please enter a file name");
        System.out.println("(Only the file name with no paths, the file will be saved to the \"./data\" folder)");
        String filename = Keyboard.readInput();
        try {
            File file = new File(PATH + filename);
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            boolean isUserTurn = true;
            int turn = 0;
            pw.println("Bulls and Cows Game Result:");
            pw.println("Your code: " + userCode);
            pw.println("Compute code: " + aiCode);
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
            switch (winner) {
                case USER:
                    pw.println("You won");
                    break;
                case AI:
                    pw.println("Computer won");
                    break;
                case DRAW:
                    pw.println("The game ended in a draw");
                    break;
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get a guess or secret code from console
    // ask the user to input again until the input is a valid code
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