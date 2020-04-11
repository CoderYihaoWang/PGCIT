package inventory.concreteInventory;

import inventory.abstractInventory.VeganInfoInventory;

public class BakeryInventory extends VeganInfoInventory {
    @Override
    public String getCategory() {
        return "BAKERY";
    }
}
