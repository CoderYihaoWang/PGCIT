package ictgradschool.industry.oop.sortingnumbers;

import ictgradschool.Keyboard;
import ictgradschool.industry.oop.Random;

/**
 * Write a program that prompts the user to enter a range – 2 integers representing a lower bound and an upper bound.
 * You should use Keyboard.readInput() for this. Then, convert these bounds from String to int using Integer.parseInt().
 * Your program should then use Math.random() to generate 3 random integers that lie between the range entered (inclusive),
 * and then use Math.min() to determine which of the random integers is the smallest.
 */
public class SortingNumbers {

    /**
     * TODO Your code here. You may also write additional methods if you like.
     */

    private void start() {
        int lower = Integer.parseInt(Keyboard.prompt("Lower bound? "));
        int upper = Integer.parseInt(Keyboard.prompt("Upper bound? "));
        if (lower > upper) {
            System.out.println("The lower bound must be smaller or equal to the upper bound!");
            return;
        }
        int r1 = Random.random(lower, upper);
        int r2 = Random.random(lower, upper);
        int r3 = Random.random(lower, upper);
        System.out.println("3 randomly generated numbers: " + r1 + " " + r2 + " and " + r3);
        System.out.println("Smallest number is " + Math.min(r1, Math.min(r2, r3)));
    }

    /**
     * Program entry point. Do not edit.
     */
    public static void main(String[] args) {

        SortingNumbers ex = new SortingNumbers();
        ex.start();

    }
}
