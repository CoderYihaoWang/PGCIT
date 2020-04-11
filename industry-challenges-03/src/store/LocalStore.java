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
        String[][] table = new String[numOfResults][tableHead.length];
        table[0] = tableHead;
        int row = 1;
        for (int i = 1; i < numOfInventory; ++i) {
            if (inventories[i].getCategory().equals(category)) {
                table[row] = new String[]{
                        row + "",
                        inventories[i].getCategory(),
                        inventories[i].getName(),
                        inventories[i].getBasePrice(),
                        inventories[i].getQuantityInStock(),
                        inventories[i].getAdditionalInfo()
                };
            }
        }
        System.out.println("Showing the result for category " + category);
        System.out.println(numOfResults + "/" + numOfInventory + " results found");
        System.out.println();
        printTable(table);
    }

    public void printAll() {
        String[] tableHead = new String[]{"No.", "Category", "Name", "Base Price", "Quantity", "Note"};
        String[][] table = new String[numOfInventory][tableHead.length];
        table[0] = tableHead;
        for (int i = 1; i < numOfInventory; ++i) {
            table[i] = new String[]{
                    i + "",
                    inventories[i].getCategory(),
                    inventories[i].getName(),
                    inventories[i].getBasePrice(),
                    inventories[i].getQuantityInStock(),
                    inventories[i].getAdditionalInfo()
            };
        }
        System.out.println("Showing the result for all inventory categories");
        System.out.println(numOfInventory + " results found");
        System.out.println();
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
        int totalWidth = 0;
        for (int width : widthOfCols)
            totalWidth += width;
        totalWidth += col + 1;
        System.out.println("+" + "-".repeat(totalWidth - 2) + "+");
        System.out.print("|");
        for (int c = 0; c < col; ++c)
            System.out.println(String.format(" %-" + (widthOfCols[c] - 2) + "s |", table[0][c]));
        System.out.println("+" + "-".repeat(totalWidth - 2) + "+");
        for (int r = 1; r < row; ++r) {
            System.out.print("|");
            for (int c = 0; c < col; ++c)
                System.out.println(String.format(" %-" + (widthOfCols[c] - 2) + "s |", table[r][c]));
        }
        System.out.println("+" + "-".repeat(totalWidth - 2) + "+");
    }
}
