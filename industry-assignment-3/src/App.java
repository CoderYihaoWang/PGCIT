import ictgradschool.industry.Keyboard;

public class App {

    public static void main(String[] args) {
        welcome();

        createGame().run();

        farewell();
    }

    private static Game createGame() {
        Game game;
        System.out.println("Please input a difficulty level:");
        System.out.println("\t[E]asy | [M]edium | [H]ard");
        do {
            game = createGame(Keyboard.readInput());
        } while (game == null);
        return game;
    }

    private static Game createGame(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy": case "e": case "1":
                return new Game(new EasyAI());
            case "medium": case "m": case "2":
                return new Game(new MediumAI());
            case "hard": case "h": case "3":
                return new Game(new HardAI());
            default:
                System.out.println(":( Sorry, invalid input, please try again");
                return null;
        }
    }

    private static void welcome() {
        System.out.println("***********************************************");
        System.out.println("*       Welcome to Bulls and Cows Game!       *");
        System.out.println("***********************************************");
    }

    private static void farewell() {
        System.out.println();
        System.out.println("Thank you for playing!");
        System.out.println("Author: Yihao Wang, 8/4/2020");
    }
}
