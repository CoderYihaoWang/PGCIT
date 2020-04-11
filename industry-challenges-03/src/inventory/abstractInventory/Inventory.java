package inventory.abstractInventory;

import util.Keyboard;

import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public abstract class Inventory {
    private static final String rName = ".+";
    private static final String rBasePrice = "^\\d*(\\.\\d{0,2})?$";
    private static final String rQuantityInStock = "^\\d+$";

    protected String name;
    protected BigDecimal basePrice;
    protected int quantityInStock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasePrice() {
        return basePrice.toPlainString();
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = new BigDecimal(basePrice);
    }

    public String getQuantityInStock() {
        return quantityInStock + "";
    }

    public void setQuantityInStock(String quantityInStock) {
        this.quantityInStock = Integer.parseInt(quantityInStock);
    }

    public abstract String getCategory();

    public abstract String getAdditionalInfo();

    public void build() {
        setName(getField("name", rName));
        setBasePrice(getField("base price", rBasePrice));
        setQuantityInStock(getField("quantity in stock", rQuantityInStock));
    }

    public void build(Scanner scanner) throws IOException {
        setName(getField(scanner, rName));
        setBasePrice(getField(scanner, rBasePrice));
        setQuantityInStock(getField(scanner, rQuantityInStock));
    }

    protected static String getField(String fieldName, String regex) {
        System.out.println("Please enter the " + fieldName + ":");
        String input = Keyboard.readInput();
        while (!input.matches(regex)) {
            System.out.println("Sorry, invalid input, please try again");
            input = Keyboard.readInput();
        }
        return input;
    }

    protected static String getField(Scanner scanner, String regex) throws IOException {
        if (!scanner.hasNext())
            throw new EOFException("Reached end of file while attempting to read a field from file");
        String field = scanner.next();
        if (!field.matches(regex))
            throw new IOException("Invalid file format");
        return field;
    }
}
