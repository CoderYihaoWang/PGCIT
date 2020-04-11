package inventory.concreteInventory;

import inventory.abstractInventory.VeganInfoInventory;

public class DeliInventory extends VeganInfoInventory {
    @Override
    public String getCategory() {
        return "DELI";
    }
}
