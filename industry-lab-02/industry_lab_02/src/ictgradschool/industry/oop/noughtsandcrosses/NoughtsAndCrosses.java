package ictgradschool.industry.oop.noughtsandcrosses;

/**
 * Write a program to extract 3 rows of 6 characters from a String of 18 characters.
 * The program should print out the 3 rows, followed by the left diagonal of the 3 rows.
 * The String method substring() can be used to extract the required characters.
 */
public class NoughtsAndCrosses {
    /**
     * Runs an example output.
     */
    private void start() {
        String letters = "X X O O X O X O X ";

        String row1 = getRow(letters, 1);

        String row2 = getRow(letters, 2);

        String row3 = getRow(letters, 3);

        printRows(row1, row2, row3);

        String leftDiagonal = getLeftDiagonal(row1, row2, row3);

        printDiagonal(leftDiagonal);
    }

    /**
     * Implement this
     */
    public String getRow(String letters, int row) {
        return letters.substring((row - 1) * 6, (row - 1) * 6 + 6);
    }

    /**
     * Implement this
     */
    public void printRows(String row1, String row2, String row3) {
        System.out.println(row1);
        System.out.println(row2);
        System.out.println(row3);
    }

    /**
     * Implement this
     */
    public String getLeftDiagonal(String row1, String row2, String row3) {
        return row1.charAt(0) + " " + row2.charAt(2) + " " + row3.charAt(4) + " ";
    }

    /**
     * Implement this
     */
    public void printDiagonal(String leftDiagonal) {
        System.out.println("Diagonal: " + leftDiagonal);
    }

    /**
     * Program entry point. Do not edit.
     */
    public static void main(String[] args) {
        NoughtsAndCrosses ex = new NoughtsAndCrosses();
        ex.start();
    }
}
