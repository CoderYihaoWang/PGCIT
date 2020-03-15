package ictgradschool.industry.oop.luckynumbers;

import ictgradschool.industry.oop.Random;

/**
 * Write a program which generates 2 random integers between 25 and 30 (inclusive),
 * then uses Math.min() and Math.max() to display them in descending sequence.
 */
public class LuckyNumbers {
    /**
     * TODO Your code here. You may also write additional methods if you like.
     */

    private void start() {
        int n1 = Random.random(25, 30);
        int n2 = Random.random(25, 30);
        System.out.println("Your lucky numbers are " + n1 + " and " + n2);
    }

    /**
     * Program entry point. Do not edit.
     */
    public static void main(String[] args) {

        LuckyNumbers ex = new LuckyNumbers();
        ex.start();

    }
}
