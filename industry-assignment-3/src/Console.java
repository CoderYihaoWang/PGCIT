import ictgradschool.industry.Keyboard;

import java.util.Arrays;
import java.util.List;

public class Console {
    private Console() {}

    public static void invalidInput() {
        System.out.println("Sorry, invalid input, please try again");
    }

    public static String getInput(String question, String[] choice, String[] description) {
        assert question != null && choice != null && description != null && choice.length == description.length;

        System.out.println(question);
        for (int i = 0; i < choice.length; ++i) {
            System.out.print(choice[i] + " - ");
            System.out.println(description[i]);
        }
        System.out.println();

        String input = Keyboard.readInput().toLowerCase();
        List<String> choiceList = Arrays.asList(choice);
        while (!choiceList.contains(input)) {
            invalidInput();
            input = Keyboard.readInput().toLowerCase();
        }

        return input;
    }
}
