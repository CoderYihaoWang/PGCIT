public class App {

    public static void main(String[] args) {
        welcome();

        createGame().run();

        farewell();
    }

    private static Game createGame() {
        String level = Console.getInput(
                "Please input a difficulty level:"
                , new String[]{"e", "m", "h"}
                , new String[]{"easy", "medium", "hard"}
        );
        switch (level) {
            case "e":
                return new Game(new EasyAI());
            case "m":
                return  new Game(new MediumAI());
            default:
                return new Game(new HardAI());
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
        System.out.println("Author: Yihao Wang, 10/4/2020");
    }
}
