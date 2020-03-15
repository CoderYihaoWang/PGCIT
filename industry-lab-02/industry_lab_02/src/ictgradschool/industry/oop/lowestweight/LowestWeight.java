package ictgradschool.industry.oop.lowestweight;

import ictgradschool.Keyboard;

/**
 * Write a program which asks the user to enter the weights of two people,
 * then uses Math.min() to determine the lowest weight, and prints out the result.
 */
public class LowestWeight {

    /**
     * TODO Your code here. You may also write additional methods if you like.
     */
    private void start() {
        double w1 = Double.parseDouble(Keyboard.prompt("Enter first person's weight: "));
        double w2 = Double.parseDouble(Keyboard.prompt("Enter second person's weight: "));
        System.out.println("Lowest weight is: " + Math.min(w1, w2));
    }

    /**
     * Program entry point. Do not edit.
     */
    public static void main(String[] args) {

        LowestWeight ex = new LowestWeight();
        ex.start();

    }
}
