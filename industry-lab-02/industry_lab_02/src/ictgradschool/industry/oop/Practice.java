package ictgradschool.industry.oop;
import ictgradschool.industry.oop.fruits.Fruit;

public class Practice {
    // E1:
    //  1. a: 1
    //     b: 9
    //     c: 10
    //  2. The fruit is red apple
    //     The fruit is orange
    //     The fruit is red apple
    //     The fruit is green apple
    //     The fruit is navel orange
    //     The fruit is green apple

    // E2:
    // 1.
    private char getRandomLetter(String word) {
        int position = (int)(Math.random() * word.length());
        return word.charAt(position);
    }

    // 2.
    private String getSurname(String name) {
        int positionOfSpace = name.indexOf(" ");
        return name.substring(positionOfSpace + 1);
    }

    // 3.
    private double getBMI(double weight, double height) {
        double bmi = weight / Math.pow(height, 2);
        return bmi;
    }

    // 4.
    private void printTemperature(int degrees) {
        System.out.println("The temperature is " + degrees);
    }

    // E3:
    //  1.
    //    a. 9.0
    //    b. 177.0
    //    c. 5 + 319
    //    d. 75965
    //  2.
    //    first: range
    //    second: redo
    //    third: ROW
    //    length: 3
    //    position1: -1
    //    position2: 10
    //    position3: 3

    public static void start() {
        String colours, first, second, third;
        int position1, position2, position3, length;

        colours = "redorangeyellow";

        first = colours.substring(4, 9);
        second = colours.substring(0, 4);
        third = colours.charAt(0) + colours.substring(13);

        length = third.length();
        third = third.toUpperCase();

        position1 = colours.indexOf('A');
        position2 = colours.indexOf("el");
        position3 = colours.indexOf("or");

        System.out.println("first: " + first);
        System.out.println("second: " + second);
        System.out.println("third: " + third);
        System.out.println("length: " + length);
        System.out.println("position1: " + position1);
        System.out.println("position2: " + position2);
        System.out.println("position3: " + position3);
    }

    public static void main(String[] args) {
        System.out.println((int)2.9 * Double.parseDouble("4.5"));
        System.out.println("17" + Integer.parseInt("2") * 3.5);
        System.out.println("5 + 3" + 19 % 2 + 19 / 2);
        System.out.println(2 + 5 + "59" + 3 * 2 + (3 + 2));
        start();
    }
}
