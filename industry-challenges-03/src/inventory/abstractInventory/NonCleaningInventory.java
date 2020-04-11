package inventory.abstractInventory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public abstract class NonCleaningInventory extends Inventory {
    private static final String rExpiryDate = "\\d{4}-\\d{2}-\\d{2}";
    private static final String rWeight = "\\d*(\\.\\d*)?";

    protected String expiryDate;
    protected BigDecimal weight;

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getWeight() {
        return expiryDate;
    }

    public void setWeight(String weight) {
        this.weight = new BigDecimal(weight);
    }

    @Override
    public String getAdditionalInfo() {
        return String.format("expiry date: %s, weight: %s", getExpiryDate(), getWeight());
    }

    @Override
    public void build() {
        super.build();
        setExpiryDate(Inventory.getField("expiry date", rExpiryDate));
        setWeight(Inventory.getField("weight", rWeight));
    }

    @Override
    public void build(Scanner scanner) throws IOException {
        super.build();
        setExpiryDate(Inventory.getField(scanner, rExpiryDate));
        setWeight(Inventory.getField(scanner, rWeight));
    }
}
