package inventory.abstractInventory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

// NonCleaningInventory has an expiry date and a weight in kilos, in addition to common fields
public abstract class NonCleaningInventory extends Inventory {
    // regular expressions for validating fields
    private static final String rExpiryDate = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    private static final String rWeight = "^\\d*(\\.\\d*)?$";

    // added fields
    protected String expiryDate;
    protected BigDecimal weight; // BigDecimal to keep accurate

    // getters and setters, only String version needed
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getWeight() {
        return weight.toPlainString() + "kg";
    }

    public void setWeight(String weight) {
        this.weight = new BigDecimal(weight);
    }

    // append additional fields information to getAdditionalInfo
    @Override
    public String getAdditionalInfo() {
        return String.format("expiry date: %s, weight: %s", getExpiryDate(), getWeight());
    }

    // first call super to build common fields, then build added fields
    @Override
    public void build() {
        super.build();
        setExpiryDate(Inventory.readField("expiry date (yyyy-mm-dd)", rExpiryDate));
        setWeight(Inventory.readField("weight in kg (e.g. 3.5)", rWeight));
    }

    @Override
    public void build(Scanner scanner) throws IOException {
        super.build(scanner);
        setExpiryDate(Inventory.readField(scanner, rExpiryDate));
        setWeight(Inventory.readField(scanner, rWeight));
    }
}
