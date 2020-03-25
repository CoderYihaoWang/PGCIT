package ictgradschool.industry.practice01.q1;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;

public class GoodPieBakingCompetition {

    public static void main(String[] args) {
        GoodPieBakingCompetition goodPie = new GoodPieBakingCompetition();
        goodPie.start();
    }

    private void start() {
        String contestant1 = getNameFromUser();
        String contestant2 = getNameFromUser();
        String contestant3 = getNameFromUser();

        int min = getIntFromUser(0);
        int max = getIntFromUser(min);
        printMinAndMaxFromUser(min, max);

        int pieSize1 = getRandomPieSize(min, max);
        int pieSize2 = getRandomPieSize(min, max);
        int pieSize3 = getRandomPieSize(min, max);

        printContestantAndPieCircumference(contestant1, pieSize1);
        printContestantAndPieCircumference(contestant2, pieSize2);
        printContestantAndPieCircumference(contestant3, pieSize3);

        printPieSize(pieSize1, pieSize2, pieSize3);
        String winner = getWinner(contestant1, contestant2, contestant3);
        System.out.println("The winner is " + winner + "!!!");
    }

    // TODO A. Get a name from the user input
    private String getNameFromUser() {
        System.out.print("Please enter the name of the first contestant: ");
        return Keyboard.readInput();
    }

    // TODO B. Get an integer from the user. If the integer is less than or equal to the lower bound, ask the user to enter
    // the integer again
    private int getIntFromUser(int lowerBound) {
        int input;
        do {
            System.out.print("Please enter an integer number greater than " + lowerBound + ": ");
            input = Integer.parseInt(Keyboard.readInput());
        } while (input <= lowerBound);
        return input;
    }

    // TODO C. Print the minimum and maximum radius from the given values
    private void printMinAndMaxFromUser(int min, int max) {
        System.out.println("The minimum radius of a pie is: " + min);
        System.out.println("The maximum radius of a pie is: " + max);
    }

    // TODO D. Generate a random number between the given ranges and calculate the circumference using the random number as
    // TODO the radius. Return the value as an integer.
    // TODO circumference = 2 * PI * radius
    private int getRandomPieSize(int min, int max) {
        return (int)(2 * Math.PI * (Math.random() * (max - min + 1) + min));
    }

    // TODO E. Get the pie category based on the given value
    // If the size > 500, return "extra large"; If the size > 100, return "large";
    // If the size > 40, return "medium"; If the size > 10, return "small"; else return "extra small";
    private String getPieCategory(int size) {
        if (size > 500)
            return "extra large";
        if (size > 100)
            return "large";
        if (size > 40)
            return "medium";
        if (size > 10)
            return "small";
        return "extra small";
    }

    // TODO F. Get the initial from the given contestant name
    private String getContestantInitial(@NotNull String contestant) {
        return "" + contestant.charAt(0);
    }

    // TODO G. Print the following message on console (don't forget to use the methods created previously)
    // [initial] baked a [size]", which is [pie category]
    private void printContestantAndPieCircumference(String contestant, int size) {
        System.out.println(getContestantInitial(contestant)
                + " baked a" + ((size + "").charAt(0) == 8 ? "n " : " ")
                + size + "\" pie, which is " + getPieCategory(size));
    }

    // TODO H. Print the given values in descending order. Do not use conditionals.
    private void printPieSize(int pieSize1, int pieSize2, int pieSize3) {
        int[] sizes = {pieSize1, pieSize2, pieSize3};
        Arrays.sort(sizes);
        System.out.println("The pie sizes are: " + sizes[2] + ", " + sizes[1] + ", " + sizes[0]);
    }
    // TODO I. Generate a random number between 1 - 100 and return the remainder of the random number after dividing by 3
    private int getWinnerNumber() {
        return ((int)(Math.random() * 100 + 1)) % 3;
    }

    // TODO J. Get the winner's name using the method created in I.
    // If the winner number is 0, the winner is contestant1;
    // If the winner number is 1, the winner is contestant2;
    // If the winner number is 2, the winner is contestant3;
    private String getWinner(String contestant1, String contestant2, String contestant3) {
        String[] contestants = {contestant1, contestant2, contestant3};
        return contestants[getWinnerNumber()];
    }
}
