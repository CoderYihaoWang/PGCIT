/**
 * Name:          App
 * Description:   Represent the Bulls and Cows Game App
 *                it is the start point of the game, and also takes the responsibility
 *                of injecting different AI instances to the Game based on user input
 * Dependency:    Game, EasyAI, MediumAI, HardAI, Console
 *
 * Author:        Yihao Wang
 * Last modified: 10/4/2020
 */
public class App {

    private static final String LAST_COMPILED_DATE = "10/4/2020";

    // start point
    public static void main(String[] args) {
        welcome();

        createGame().run();

        farewell();
    }

    // instantiate different AI according to user's input, and inject it to a Game instance
    private static Game createGame() {
        String difficulty = Console.ask(
                "Please input a difficulty level:"
                , new String[]{"e", "m", "h"}
                , new String[]{"easy", "medium", "hard"}
        );
        switch (difficulty) {
            case "e":
                return new Game(new EasyAI());
            case "m":
                return  new Game(new MediumAI());
            default:
                return new Game(new HardAI());
        }
    }

    // display opening messages
    private static void welcome() {
        System.out.println("***********************************************");
        System.out.println("*       Welcome to Bulls and Cows Game!       *");
        System.out.println("***********************************************");
    }

    // display ending messages
    private static void farewell() {
        System.out.println();
        System.out.println("Thank you for playing!");
        System.out.println("Author: Yihao Wang, " + LAST_COMPILED_DATE);
    }
}
