package store;

import inventory.abstractInventory.Inventory;

import java.util.function.Predicate;

public class LocalStore {
    private String name;
    private String location;

    // the initial capacity is set to 0
    private int capacity = 0;
    private Inventory[] inventories = new Inventory[capacity];

    // how many inventories are already in stock
    private int numOfInventory;

    // getters/setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumOfInventory() {
        return numOfInventory;
    }

    public int getCapacity() {
        return capacity;
    }

    // setCapacity contains the logic to resize the inventories array
    // not actually needed in this program,
    // but supplied nevertheless in case in future the capacity needs to change
    public void setCapacity(int capacity) {
        // capacity below 0 is illegal
        if (capacity < 0)
            throw new IllegalArgumentException("The capacity cannot be negative");

        // if the capacity is the same as the old capacity, no need to change
        if (this.capacity == capacity)
            return;

        // if capacity shrinks, just cut off
        if (this.capacity > capacity) {
            this.capacity = numOfInventory = capacity;
            return;
        }

        // else capacity grows, make a larger array and copies the existing inventories to it
        Inventory[] newInventories = new Inventory[capacity];
        System.arraycopy(inventories, 0, newInventories, 0, numOfInventory);
        this.capacity = capacity;
        this.inventories = newInventories;
    }

    // add an inventory to the inventories array
    // the returned boolean indicates whether the addition is successful
    public boolean add(Inventory inventory) {
        if (numOfInventory == capacity)
            return false;
        inventories[numOfInventory++] = inventory;
        return true;
    }

    // print inventories in a particular category
    public void print(String category) {
        String[][] table = buildTable(inventory -> inventory.getCategory().equals(category));
        System.out.println();
        System.out.println("Showing the results for category " + category);
        printTable(table);
    }

    // print all inventories
    public void printAll() {
        String[][] table = buildTable(inventory -> true);
        System.out.println();
        System.out.println("Showing the results for all inventory categories");
        printTable(table);
    }

    // build up a 2d String array containing all inventories which satisfy the predicate
    private String[][] buildTable(Predicate<Inventory> predicate) {
        int numOfResults = 0;
        for (int i = 0; i < numOfInventory; ++i) {
            if (predicate.test(inventories[i]))
                ++numOfResults;
        }
        String[] tableHead = new String[]{"No.", "Category", "Name", "Base Price", "Quantity", "Additional Information"};
        String[][] table = new String[numOfResults + 1][tableHead.length];
        table[0] = tableHead;
        int row = 1;
        for (int i = 0; i < numOfInventory; ++i) {
            if (predicate.test(inventories[i])) {
                table[row] = new String[]{
                        row + "",
                        inventories[i].getCategory(),
                        inventories[i].getName(),
                        inventories[i].getBasePrice(),
                        inventories[i].getQuantityInStock(),
                        inventories[i].getAdditionalInfo()
                };
                ++row;
            }
        }
        return table;
    }

    // print the result 2d array in the format of a table
    private void printTable(String[][] table) {
        int row = table.length;
        int col = table[0].length;

        System.out.println((table.length - 1) + "/" + numOfInventory + " results found");

        // calculate the width needed for each column
        int[] widthOfCols = new int[col];
        for (int c = 0; c < col; ++c) {
            for (String[] tableRow : table) {
                if (tableRow[c].length() + 2 > widthOfCols[c])
                    widthOfCols[c] = tableRow[c].length() + 2;
            }
        }

        // print table lines
        printHorizontal(widthOfCols);
        printRow(table[0], widthOfCols);
        printHorizontal(widthOfCols);
        for (int r = 1; r < row; ++r) {
            printRow(table[r], widthOfCols);
        }
        printHorizontal(widthOfCols);
    }

    // print the horizontal lines in a table
    private static void printHorizontal(int[] widthOfCols) {
        System.out.print("+");
        for (int widthOfCol : widthOfCols) {
            System.out.print("-".repeat(widthOfCol));
            System.out.print("+");
        }
        System.out.println();
    }

    // print a table row
    private static void printRow(String[] tableLine, int[] widthOfCols) {
        System.out.print("|");
        for (int c = 0; c < tableLine.length; ++c)
            System.out.print(String.format(" %-" + (widthOfCols[c] - 2) + "s |", tableLine[c]));
        System.out.println();
    }
}