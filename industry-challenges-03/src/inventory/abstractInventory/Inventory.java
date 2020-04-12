package inventory.abstractInventory;

import util.Keyboard;

import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public abstract class Inventory {
    // regular expressions for validating fields
    private static final String rName = ".+";
    private static final String rBasePrice = "^\\d*(\\.\\d{0,2})?$";
    private static final String rQuantityInStock = "^\\d+$";

    // fields
    protected String name;
    protected BigDecimal basePrice; // BigDecimal to keep accurate
    protected int quantityInStock;

    // getters and setters
    // all only provide the version to get/set from a String, despite of the underlying type,
    // because this is what is needed in this program
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasePrice() {
        return "$" + basePrice.toPlainString();
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

    // concrete inventory must override this to provide category information
    public abstract String getCategory();

    // concrete inventory must override this to give information regarding additional fields
    public abstract String getAdditionalInfo();

    // two overloaded methods build:
    // the one without argument will build all the fields from the user input
    // the one that takes a Scanner will build all the fields from the Scanner
    // these methods must be overridden when new fields are added
    // in their overriding versions, super.build should be called to build the common fields
    public void build() {
        setName(readField("name (e.g. apple)", rName));
        setBasePrice(readField("base price (e.g. 1.25)", rBasePrice));
        setQuantityInStock(readField("quantity in stock (e.g. 10)", rQuantityInStock));
    }

    public void build(Scanner scanner) throws IOException {
        setName(readField(scanner, rName));
        setBasePrice(readField(scanner, rBasePrice));
        setQuantityInStock(readField(scanner, rQuantityInStock));
    }

    // utility methods readField:
    // when supplied with a field name String and a pattern String
    // it prompts the user to input the field, and repeatedly gets user input
    // until the input matches the pattern String
    //
    // when supplied with a Scanner and a pattern String
    // it will get the field from the Scanner
    // if there are nothing to read from the scanner, it throws an EOFException
    // if the field read does not match the pattern String, it throws an IOException
    protected static String readField(String fieldName, String regex) {
        System.out.println("Please enter the " + fieldName + ":");
        String input = Keyboard.readInput();
        while (!input.matches(regex)) {
            System.out.println("Sorry, invalid input, please try again");
            input = Keyboard.readInput();
        }
        return input;
    }

    // this method relies on properly setting the delimiter of the scanner
    // before it is passed in
    protected static String readField(Scanner scanner, String regex) throws IOException {
        if (!scanner.hasNext())
            throw new EOFException("Reached end of file while attempting to read a field from file");
        String field = scanner.next();
        if (!field.matches(regex))
            throw new IOException("Invalid file format");
        return field;
    }
}