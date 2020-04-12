package inventory.abstractInventory;

import java.io.IOException;
import java.util.Scanner;

// VeganInfoInventory has an additional field indicating whether it is suitable for vegans
public abstract class VeganInfoInventory extends NonCleaningInventory {
    // regular expression for validating fields
    private static final String rVeganInfo = "^(true|false)$";

    // field
    protected boolean veganInfo;

    // getter/setter, only String version
    public String getVeganInfo() {
        return veganInfo ? "yes" : "no";
    }

    public void setVeganInfo(String veganInfo) {
        this.veganInfo = veganInfo.equals("true");
    }

    // append new field to getAdditionalInfo
    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() + ", suitable for vegans: " + getVeganInfo();
    }

    // first build super's fields, then additional field
    @Override
    public void build() {
        super.build();
        setVeganInfo(Inventory.readField("if this is suitable for vegans (true/false)", rVeganInfo));
    }

    @Override
    public void build(Scanner scanner) throws IOException {
        super.build(scanner);
        setVeganInfo(Inventory.readField(scanner, rVeganInfo));
    }
}
