import ictgradschool.industry.Keyboard;

import java.util.Arrays;
import java.util.List;

/**
 * Name:          Console
 * Description:   A pure static class
 *                it provides static methods dealing with the console io
 * Dependency:    Keyboard
 *
 * Author:        Yihao Wang
 * Last modified: 9/4/2020
 */
public class Console {

    // this class should not be instantiated
    private Console() {}

    /**
     * first prompt the user a question, and then show a group possible inputs
     * and their description, then read the user input, it will repeatedly let the
     * user input until the user inputs a valid String in the choice Array
     * then this input is returned
     * This method assumes that all the inputs are not null
     * and that the lengths of the second and third arguments are equal
     * @param question the question to ask the user
     * @param choice the array of all valid inputs
     * @param description the array of all descriptions relating to the inputs
     * @return a valid choice
     */
    public static String ask(String question, String[] choice, String[] description) {
        System.out.println();
        System.out.println(question);
        for (int i = 0; i < choice.length; ++i) {
            System.out.print(choice[i] + " - ");
            System.out.println(description[i]);
        }
        String input = Keyboard.readInput().toLowerCase();
        List<String> choiceList = Arrays.asList(choice);
        while (!choiceList.contains(input)) {
            System.out.println("Sorry, invalid input, please try again");
            input = Keyboard.readInput().toLowerCase();
        }
        return input;
    }

    /**
     * asks the user a question, and return the user's input
     * this method does not add a new line after the question prompt
     * @param question the question to prompt the user
     * @return the user's input
     */
    public static String ask(String question) {
        System.out.print(question);
        return Keyboard.readInput();
    }
}
