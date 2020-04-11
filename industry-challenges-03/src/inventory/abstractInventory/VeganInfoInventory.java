package inventory.abstractInventory;

import java.io.IOException;
import java.util.Scanner;

public abstract class VeganInfoInventory extends NonCleaningInventory {
    private static final String rVeganInfo = "^(true|false)$";

    protected boolean veganInfo;

    public String getVeganInfo() {
        return Boolean.toString(veganInfo);
    }

    public void setVeganInfo(String veganInfo) {
        this.veganInfo = veganInfo.equals("true");
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() + ", suitable for vegans: " + getVeganInfo();
    }

    @Override
    public void build() {
        super.build();
        setVeganInfo(Inventory.getField("if this is suitable for vegans", rVeganInfo));
    }

    @Override
    public void build(Scanner scanner) throws IOException {
        super.build(scanner);
        setVeganInfo(Inventory.getField(scanner, rVeganInfo));
    }
}
