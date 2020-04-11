package store;

import inventory.abstractInventory.Inventory;

public class LocalStore {
    private String name;
    private String location;
    private int capacity = 0;
    private Inventory[] inventories = new Inventory[0];
    private int numOfInventory;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("The capacity cannot be negative");
        if (this.capacity == capacity)
            return;
        if (this.capacity > capacity) {
            this.capacity = numOfInventory = capacity;
            return;
        }
        Inventory[] newInventories = new Inventory[capacity];
        System.arraycopy(inventories, 0, newInventories, 0, numOfInventory);
        this.capacity = capacity;
        this.inventories = newInventories;
    }

    public boolean add(Inventory inventory) {
        if (numOfInventory == capacity)
            return false;
        inventories[numOfInventory++] = inventory;
        return true;
    }

    public void print(String category) {
        int numOfResults = 0;
        for (int i = 0; i < numOfInventory; ++i) {
            if (inventories[i].getCategory().equals(category))
                ++numOfResults;
        }
        String[] tableHead = new String[]{"No.", "Category", "Name", "Base Price", "Quantity", "Note"};
        String[][] table = new String[numOfResults + 1][tableHead.length];
        table[0] = tableHead;
        int row = 1;
        for (int i = 1; i < numOfInventory + 1; ++i) {
            if (inventories[i - 1].getCategory().equals(category)) {
                table[row] = new String[]{
                        row + "",
                        inventories[i - 1].getCategory(),
                        inventories[i - 1].getName(),
                        inventories[i - 1].getBasePrice(),
                        inventories[i - 1].getQuantityInStock(),
                        inventories[i - 1].getAdditionalInfo()
                };
                ++row;
            }
        }
        System.out.println();
        System.out.println("Showing the results for category " + category);
        System.out.println(numOfResults + "/" + numOfInventory + " results found");
        printTable(table);
    }

    public void printAll() {
        String[] tableHead = new String[]{"No.", "Category", "Name", "Base Price", "Quantity", "Note"};
        String[][] table = new String[numOfInventory + 1][tableHead.length];
        table[0] = tableHead;
        for (int i = 1; i < numOfInventory + 1; ++i) {
            table[i] = new String[]{
                    i + "",
                    inventories[i - 1].getCategory(),
                    inventories[i - 1].getName(),
                    inventories[i - 1].getBasePrice(),
                    inventories[i - 1].getQuantityInStock(),
                    inventories[i - 1].getAdditionalInfo()
            };
        }
        System.out.println();
        System.out.println("Showing the results for all inventory categories");
        System.out.println(numOfInventory + " results found");
        printTable(table);
    }

    private static void printTable(String[][] table) {
        int row = table.length;
        int col = table[0].length;
        int[] widthOfCols = new int[col];
        for (int c = 0; c < col; ++c) {
            for (String[] tableRow : table) {
                if (tableRow[c].length() + 2 > widthOfCols[c])
                    widthOfCols[c] = tableRow[c].length() + 2;
            }
        }

        printHorizontal(widthOfCols);

        System.out.print("|");
        for (int c = 0; c < col; ++c)
            System.out.print(String.format(" %-" + (widthOfCols[c] - 2) + "s |", table[0][c]));
        System.out.println();

        printHorizontal(widthOfCols);

        for (int r = 1; r < row; ++r) {
            System.out.print("|");
            for (int c = 0; c < col; ++c)
                System.out.print(String.format(" %-" + (widthOfCols[c] - 2) + "s |", table[r][c]));
            System.out.println();
        }

        printHorizontal(widthOfCols);
    }

    private static void printHorizontal(int[] widthOfCols) {
        System.out.print("+");
        for (int widthOfCol : widthOfCols) {
            System.out.print("-".repeat(widthOfCol));
            System.out.print("+");
        }
        System.out.println();
    }
}
