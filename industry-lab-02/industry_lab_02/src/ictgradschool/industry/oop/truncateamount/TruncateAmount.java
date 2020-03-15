package ictgradschool.industry.oop.truncateamount;

import ictgradschool.Keyboard;

/**
 * Write a program that prompts the user to enter an amount and a number of decimal places.  The program should then
 * truncate the amount to the user-specified number of decimal places using String methods.
 *
 * To truncate the amount to the user-specified number of decimal places, the String method indexOf() should be used
 * to find the position of the decimal point, and the method substring() should then be used to extract the amount to
 * the user-specified number of decimal places.  The program is to be written so that each task is in a separate method.
 * You need to write four methods, one method for each of the following tasks:
 *
 * 1. Printing the prompt and reading the amount from the user
 * 2. Printing the prompt and reading the number of decimal places from the user
 * 3. Truncating the amount to the user-specified number of DP's
 * 4. Printing the truncated amount
 *
 */
public class TruncateAmount {

    private void start() {

        // Use other methods you create to implement this program's functionality.
        double amount = getAmount();
        int precision = getPrecision();
        print(getNewAmount(amount, precision), precision);

    }

    // Write a method which prompts the user and reads the amount to truncate from the Keyboard
    private double getAmount() {
        return Double.parseDouble(Keyboard.prompt("Please enter an amount: "));
    }
    // Write a method which prompts the user and reads the number of DP's from the Keyboard
    private int getPrecision() {
        return Integer.parseInt(Keyboard.prompt("Please enter the number of decimal places: "));
    }

    // Write a method which truncates the specified number to the specified number of DP's
    private String getNewAmount(double amount, int precision) {
        String str = String.valueOf(amount);
        int index = str.indexOf(".");
        if (index != -1) {
            return str.substring(0, index + precision + 1);
        }
        return str;
    }

    // Write a method which prints the truncated amount
    private void print(String newAmount, int precision) {
        System.out.println("Amount truncated to "
                + precision + " decimal place"
                + (precision == 1 ? "" : "s") + " is " + newAmount);
    }

    /**
     * Program entry point. Do not edit.
     */
    public static void main(String[] args) {
        TruncateAmount ex = new TruncateAmount();
        ex.start();
    }
}
