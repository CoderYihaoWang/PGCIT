import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import ictgradschool.industry.Keyboard;

public class Game {
    private final AI ai;
    private Queue<String> fileGuesses;
    private final Queue<Record> history;
    private String userSecret;
    private String aiSecret;
    private boolean isUserTurn;

    public Game(AI ai) {
        this.ai = ai;
        this.fileGuesses = new LinkedList<>();
        this.history = new LinkedList<>();
        isUserTurn = true;
    }

    public void run() {
        loadFile();
    }

    private void loadFile() {
        System.out.println("Would you like to load guesses from file?");
        System.out.println("'Y' - Yes, please load guesses from file");
        System.out.println("'N' - No, I will play this game manually");
        String input = Keyboard.readInput().toLowerCase();
        while (!input.equals("y") && !input.equals("n")) {
            System.out.println("Invalid input, please try again");
            input = Keyboard.readInput().toLowerCase();
        }

        if (input.equals("n"))
            return;

        boolean loadSuccessful = false;
        while (!loadSuccessful) {
            System.out.println("Please input a file to read from, or 'q' to continue game manually");
            input = Keyboard.readInput();
            if (input.toLowerCase().equals("q"))
                return;
            try (Scanner scanner = new Scanner(new File(input))) {
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
}
