package ictgradschool.industry.assignment02.farmmanager;

import ictgradschool.Keyboard;

/**
 * This class consists of methods for running the Farm Manager 1964 game.
 * Simply run this class to start the game. Do not modify this class.
 */
public class FarmManager {
    private Farm farm;

    /**
     * Construct a FarmManager object for starting the game.
     *
     * @param args The array of string parsed from command-line
     */
    public static void main(String[] args) {
        FarmManager fm = new FarmManager();
        fm.start();
    }

    /**
     * Constructor of FarmManager
     */
    public FarmManager() {
        farm = new Farm();
    }

    /**
     * Prints a welcome message, processes commands from the user, and
     * prints an exit message when the user quits.
     */
    public void start() {
        printWelcomeMessage();
        while (true) {
            // Get a command from the user and parse it
            String input = getCommand();
            if (input.equals("quit")) {
                break;
            }
            parseCommand(input);
        }
        printExitMessage();
    }

    /**
     * Prints the welcome message of the game.
     */
    private void printWelcomeMessage() {
        System.out.println("Welcome to Farm Manager 1964!");
    }

    /**
     * Prints the exit message of the game, including the money
     * left and the profit / loss made from the farm.
     */
    private void printExitMessage() {
        System.out.println("Thanks for playing Farm Manager 1964!");
        System.out.println("Your farm finished with $" + farm.getMoney());

        int profit = farm.getMoney() - farm.getStartingMoney();
        if (profit > 0) {
            System.out.println("You made a profit of $" + profit);
        } else if (profit < 0) {
            System.out.println("You made a loss of $" + (-profit));
        } else {
            System.out.println("You finished where you started.");
        }
    }

    /**
     * Gets a command from the user.
     *
     * @return A command from the user
     */
    private String getCommand() {
        System.out.print(">> ");
        return Keyboard.readInput();
    }

    /**
     * Parses a string into a command plus arguments and executes them.
     *
     * @param rawCmd The string to be parsed.
     */
    private void parseCommand(String rawCmd) {
        String[] args = rawCmd.split(" ");

        // Empty commands can be ignored
        if (args.length == 0) {
            return;
        }

        String command = args[0];

        switch (command) {
            case "buy":
                buyAnimalCommand(args);
                break;
            case "stock":
                farm.printStock();
                break;
            case "money":
                System.out.println("$" + farm.getMoney());
                break;
            case "sell":
                System.out.println("You sold all your stock for $" + farm.sell());
                break;
            case "feed":
                feedAnimalCommand(args);
                break;
            case "harvest":
                farm.harvestAll();
                break;
            default:
                System.out.println("Unrecognised command");
        }
    }

    /**
     * Tells the farm to feed all animals on the farm or
     * all animals of a specific type on the farm.     *
     *
     * @param args The arguments from the command.
     */
    private void feedAnimalCommand(String[] args) {
        // People should buy exactly one animal at a time
        if (args.length == 1) {
            farm.feedAll();
        } else if (args.length == 2) {
            farm.feed(args[1]);
        } else {
            System.out.println("Wrong number of arguments");
        }
    }

    /**
     * Tells the farm to buy an animal.
     *
     * @param args The arguments from the command.
     */
    private void buyAnimalCommand(String[] args) {
        // People should buy exactly one animal at a time
        if (args.length != 2) {
            System.out.println("Wrong number of arguments");
        } else {
            boolean success = farm.buyAnimal(args[1]);
            if (!success) {
                System.out.println("Could not buy a " + args[1] +
                        ". You might have insufficient funds, or " +
                        "your farm might be full, or " +
                        "the animal wasn't recognized.");
            }
        }
    }
}
